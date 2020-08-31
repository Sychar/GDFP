package com.jess.gdfp;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import static android.content.ContentValues.TAG;

public class BluetoothService {
    private static final String NAME = "Bluetooth";
    //private static final String NAME = "BPI-M64";
    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"); //original value
    //private static final UUID MY_UUID = UUID.fromString("22b5b7b2-65b6-4df2-8bb3-e50be8f08fc7");
    //private static final UUID MY_UUID = UUID.fromString("0000110A-0000-1000-8000-00805F9B34FB");
    //private static final UUID MY_UUID = UUID.fromString("0000110a-0000-1000-8000-00805f9b34fb");

    private final BluetoothAdapter mAdapter;
    private final Handler mHandler;
    private AcceptThread mAcceptThread;
    private ConnectThread mConnectThread;
    private ConnectedThread mConnectedThread;
    private int mState;
    public Boolean configured_status=false;

    public static final int STATE_NONE = 0;       // we're doing nothing
    public static final int STATE_LISTEN = 1;     // now listening for incoming connections
    public static final int STATE_CONNECTING = 2; // now initiating an outgoing connection
    public static final int STATE_CONNECTED = 3;  // now connected to a remote device
    public static final int STATE_LOST = 4;  // now lost connection to a remote device

    private BluetoothDevice deviceTemp;
    private BluetoothSocket mSocket = null;

    public BluetoothService(Context context, Handler handler) {
        mAdapter = BluetoothAdapter.getDefaultAdapter(); //first step
        if(mAdapter == null){
            Toast.makeText(context,"Bluetooth not supported",Toast.LENGTH_SHORT).show(); //my code
        }else Toast.makeText(context,"Bluetooth supported",Toast.LENGTH_SHORT).show(); //my code
        mState = STATE_NONE;
        mHandler = handler;
    }

    private synchronized void setState(int state) {
        mState = state;
        mHandler.obtainMessage(BTConstants.MESSAGE_STATE_CHANGE, state, -1).sendToTarget(); //Returns a new Message from the global message pool.
    }

    public synchronized int getState() {
        return mState;
    }

    public synchronized void start(BluetoothDevice myDevice) {
        // Cancel any thread attempting to make a connection
        if (mConnectThread != null) {
            mConnectThread.cancel();
            mConnectThread = null;
        }

        // Cancel any thread currently running a connection
        if (mConnectedThread != null) {
            mConnectedThread.cancel();
            mConnectedThread = null;
        }

        setState(STATE_LISTEN);

        // Start the thread to listen on a BluetoothServerSocket
        /*if (mAcceptThread == null) { //disable temporarily
            mAcceptThread = new AcceptThread(myDevice,true);
            mAcceptThread.start();
            //Log.i("Accept thread","starts");
        }*/
    }

    public void toConnect(BluetoothDevice device){
        Log.i("void","toConnect()");
        deviceTemp = device;

        BluetoothSocket tmp = null;
        UUID DEFAULT_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

        try {
            // Use the UUID of the device that discovered // TODO Maybe need extra device object
            if (deviceTemp != null)
            {
                Log.i("MSG", "Device Name: " + deviceTemp.getName());
                Log.i("MSG", "Device UUID: " + deviceTemp.getUuids()[0].getUuid());
                tmp = device.createRfcommSocketToServiceRecord(deviceTemp.getUuids()[0].getUuid());

            }
            else Log.d("MSG", "Device is null.");
        }
        catch (NullPointerException e)
        {
            Log.d("MSG", " UUID from device is null, Using Default UUID, Device name: " + device.getName());
            try {
                tmp = device.createRfcommSocketToServiceRecord(DEFAULT_UUID);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        catch (IOException e) { }

        configured_status=true;
        try {
            mSocket = device.createRfcommSocketToServiceRecord(MY_UUID); //Create an RFCOMM BluetoothSocket ready to start a secure outgoing connection to this remote device using SDP lookup of uuid.
            setState(STATE_CONNECTING);
        } catch (IOException e) {
            setState(STATE_NONE);
        }
        try {
            mSocket.connect();
            connected(mSocket,device);
        } catch (IOException e) {
            try {
                mSocket.close();
                connectionFailed();
            } catch (IOException e2) {
                connectionFailed();
            }
        }
    }

    public synchronized void connect(BluetoothDevice device) {
        //Log.d(TAG, "connect to: " + device);

        // Cancel any thread attempting to make a connection
        if (mState == STATE_CONNECTING) {
            if (mConnectThread != null) {
                mConnectThread.cancel();
                mConnectThread = null;
            }
        }

        // Cancel any thread currently running a connection
        if (mConnectedThread != null) {
            mConnectedThread.cancel();
            mConnectedThread = null;
        }

        // Start the thread to connect with the given device
        mConnectThread = new ConnectThread(device, true);
        mConnectThread.start();
        setState(STATE_CONNECTING);
    }

    /**
     * Start the ConnectedThread to begin managing a Bluetooth connection
     *
     * @param socket The BluetoothSocket on which the connection was made
     * @param device The BluetoothDevice that has been connected
     */
    public synchronized void connected(BluetoothSocket socket, BluetoothDevice device) {

        setState(STATE_CONNECTED);
        // Cancel the thread that completed the connection
        if (mConnectThread != null) {
            mConnectThread.cancel();
            mConnectThread = null;
        }

        // Cancel any thread currently running a connection
        if (mConnectedThread != null) {
            mConnectedThread.cancel();
            mConnectedThread = null;
        }

        // Cancel the accept thread because we only want to connect to one device
        if ((!configured_status) && (mAcceptThread != null)) {
            mAcceptThread.cancel();
            mAcceptThread = null;
        }

        //if (threat_status){
        // Start the thread to manage the connection and perform transmissions
        mConnectedThread = new ConnectedThread(socket);
        mConnectedThread.start();
        // }

        // Send the name of the connected device back to the UI Activity
        Message msg = mHandler.obtainMessage(BTConstants.MESSAGE_DEVICE_NAME); //Returns a new Message from the global message pool.
        Bundle bundle = new Bundle();
        bundle.putString(BTConstants.DEVICE_NAME, device.getName());
        msg.setData(bundle);
        mHandler.sendMessage(msg);//Pushes a message onto the end of the message queue after all pending messages before the current time.
    }

    /**
     * Stop all threads
     */
    public synchronized void stop() {

        if (mConnectThread != null) {
            mConnectThread.cancel();
            mConnectThread = null;
        }

        if (mConnectedThread != null) {
            mConnectedThread.cancel();
            mConnectedThread = null;
        }

        if (mAcceptThread != null) {
            mAcceptThread.cancel();
            mAcceptThread = null;
        }
        setState(STATE_NONE);
    }

    /**
     * Write to the ConnectedThread in an unsynchronized manner
     *
     * @param out The bytes to write
     * @see ConnectedThread#write(byte[])
     */
    public void write(byte[] out) {
        // Create temporary object
        ConnectedThread r;
        // Synchronize a copy of the ConnectedThread
        synchronized (this) {
            if (mState != STATE_CONNECTED) return;
            r = mConnectedThread;
        }
        // Perform the write unsynchronized
        r.write(out);
    }

    public void cancel() { //used by connecttoGateway() to close the socket.
        // Create temporary object
        ConnectedThread r;
        r = mConnectedThread;
        r.cancel();
    }

    /**
     * Indicate that the connection attempt failed and notify the UI Activity.
     */
    private void connectionFailed() {
        // Send a failure message back to the Activity
        Message msg = mHandler.obtainMessage(BTConstants.MESSAGE_TOAST);
        Bundle bundle = new Bundle();
        bundle.putString(BTConstants.TOAST, "Unable to connect Gateway");
        msg.setData(bundle);
        mHandler.sendMessage(msg);

        //setState(STATE_LOST); //original code
        setState(STATE_LISTEN);
        // Start the service over to restart listening mode
        //BluetoothService.this.start(); //not sure
    }

    /**
     * Indicate that the connection was lost and notify the UI Activity.
     */
    private void connectionLost() {
        // Send a failure message back to the Activity
        Message msg = mHandler.obtainMessage(BTConstants.MESSAGE_TOAST);
        Bundle bundle = new Bundle();
        bundle.putString(BTConstants.TOAST, "Gateway Connection lost");
        msg.setData(bundle);
        mHandler.sendMessage(msg);

        //setState(STATE_LOST); //original code
        setState(STATE_LISTEN);

        // Start the service over to restart listening mode
        //BluetoothServiceFull.this.start();
    }

    /**
     * This thread runs while listening for incoming connections. It behaves
     * like a server-side client. It runs until a connection is accepted
     * (or until cancelled).
     */

    //thread for the server component that accepts incoming connections:
    public class AcceptThread extends Thread { //accepts() should not be executed in the main activity UI thread so that your application can still respond to other user interactions.
        // The local server socket
        private final BluetoothServerSocket mmServerSocket;
        //private final BluetoothSocket mmSocket; //my code
        BluetoothDevice TheDevice; //my code

        private AcceptThread(BluetoothDevice BTDevice, boolean secure) {
            // Use a temporary object that is later assigned to mmServerSocket because mmServerSocket is final.
            BluetoothServerSocket servertmp = null;
            //BluetoothSocket tmp = null; //my code
            TheDevice = BTDevice; //my code

            // Get a BluetoothSocket to connect with the given BluetoothDevice. This code below show how to do it and handle the case that the UUID from the device is not found and trying a default UUID.

            // Default UUID
            UUID DEFAULT_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

            /*try {
                // Use the UUID of the device that discovered // TODO Maybe need extra device object
                if (TheDevice != null) {
                    Log.i(TAG,"Device not null");
                    Log.i(TAG, "Device Name: " + TheDevice.getName());
                    Log.i(TAG, "Device UUID: " + TheDevice.getUuids()[0].getUuid());
                    tmp = BTDevice.createRfcommSocketToServiceRecord(TheDevice.getUuids()[0].getUuid());
                } else Log.d(TAG, "Device is null.");
            }
            catch (NullPointerException e) {
                Log.d(TAG, " UUID from device is null, Using Default UUID, Device name: " + BTDevice.getName());
                try {
                    tmp = BTDevice.createRfcommSocketToServiceRecord(DEFAULT_UUID);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            catch (IOException e) { }*/
            //mmSocket = tmp;

            /*try { //disable temporarily
                //Create a listening, secure RFCOMM Bluetooth socket with Service Record.
                //if(secure) {
                    servertmp = mAdapter.listenUsingRfcommWithServiceRecord(NAME,MY_UUID);
                    Log.i("listen", "socket successfully");
                *//*}else{
                    servertmp = mAdapter.listenUsingInsecureRfcommWithServiceRecord(NAME, MY_UUID);
                    Log.i("Cant get the", "Uuid");
                }*//*
            } catch (IOException e) {
                Log.e(TAG, "Socket's listen() method failed", e);
                //e.printStackTrace();
            }*/
            mmServerSocket = servertmp;
        }

        public void run() {
            BluetoothSocket socket = null;
            if(mmServerSocket == null){
                Log.d("MSG", "Server socket is null");
            }
            // Keep listening until exception occurs or a socket is returned.
            Log.i("mState",String.valueOf(mState));
            //while (mState != STATE_CONNECTED) { //mState = 3
            while (mState != STATE_CONNECTED) {
                Log.i("Not","STATE_CONNECTED");
                try {
                    socket = mmServerSocket.accept(); //When successful, accept() returns a connected BluetoothSocket.
                    Log.i("Socket","is accepted");
                } catch (IOException e) {
                    Log.e(TAG, "Socket's accept() method failed", e);
                    break;
                }

                if (socket != null) {
                    Log.i("mState","Its here mann");
                    // A connection was accepted. Perform work associated with
                    // the connection in a separate thread.
                    synchronized (BluetoothService.this) {
                        switch (mState) {
                            case STATE_LISTEN:
                            case STATE_CONNECTING:
                                configured_status=false;
                                connected(socket, socket.getRemoteDevice()); //at this point the Connectedthread starts and mState = STATE_CONNECTED.
                                break;
                            case STATE_NONE:
                            case STATE_CONNECTED:
                                // Either not ready or already connected. Terminate new socket.
                                //mHandler.obtainMessage(Constants.MESSAGE_STATE_CHANGE, STATE_CONNECTED, -1).sendToTarget();
                                try {
                                    socket.close();
                                } catch (IOException e) {
                                }
                                break;
                        }
                    }
                }
            }
        }

        /*public void sendToast(String toastData) {
            // Send a failure message back to the Activity
            Message msg = mHandler.obtainMessage(BTConstants.MESSAGE_TOAST);
            Bundle bundle = new Bundle();
            bundle.putString(BTConstants.TOAST, toastData);
            msg.setData(bundle);
            mHandler.sendMessage(msg);
        }*/

        // Closes the connect socket and causes the thread to finish.
        public void cancel() {
            try {
                mmServerSocket.close();
            } catch (IOException e) {
                Log.e(TAG, "Could not close the connect socket", e);
                //e.printStackTrace();
            }
        }
    }

    //client thread that initiates a Bluetooth connection:
    private class ConnectThread extends Thread { //Client class
        private final BluetoothSocket mmSocket;
        private final BluetoothDevice mmDevice;

        private ConnectThread(BluetoothDevice device, boolean secure) {
            mmDevice = device;
            BluetoothSocket tmp = null;
            try {
                tmp = device.createRfcommSocketToServiceRecord(MY_UUID);
            } catch (IOException e) {
                Log.e(TAG, "Socket's create() method failed", e);
                //e.printStackTrace();
            }
            mmSocket = tmp;
        }

        public void run() {
            // Cancel discovery because it otherwise slows down the connection.
            mAdapter.cancelDiscovery();
            try {
                // Connect to the remote device through the socket. This call blocks
                // until it succeeds or throws an exception.
                mmSocket.connect();
            } catch (IOException e) {
                try {
                    // Unable to connect; close the socket and return.
                    mmSocket.close();
                } catch (IOException e2) {
                    Log.e(TAG, "Could not close the client socket", e2);
                    //e.printStackTrace();
                }
                connectionFailed();
                return;
            }
            // The connection attempt succeeded. Perform work associated with
            // the connection in a separate thread.
            synchronized (BluetoothService.this) {
                mConnectThread = null;
            }
            connected(mmSocket, mmDevice);
        }

        // Closes the client socket and causes the thread to finish.
        private void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) {
                Log.e(TAG, "Could not close the client socket", e);
                //e.printStackTrace();
            }
        }
    }

    /**
     * This thread runs during a connection with a remote device.
     * It handles all incoming and outgoing transmissions.
     */
    //ClientClass, starts in connected() method
    public class ConnectedThread extends Thread { //A thread is a small execution unit within a process. It helps to increase performance and allow resource sharing.
        private final BluetoothSocket mmSocket;
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;

        public ConnectedThread(BluetoothSocket socket) { //constructor
            mmSocket = socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            try {
                tmpIn = socket.getInputStream(); //Get the input stream associated with this socket.
                tmpOut = socket.getOutputStream(); //Get the output stream associated with this socket.
            } catch (IOException e) {
                connectionLost();
            }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

        public void run() {
            byte[] buffer = new byte[1000];
            int bytes;
            Log.i("ConnectedThread","Running");

            // Keep listening to the InputStream until an exception occurs.
            while (mState == STATE_CONNECTED)  {
                Log.i("mState","STATE_CONNECTED");
                try {
                    bytes = mmInStream.read(buffer); //reads number of bytes of arg.length from the input stream to the buffer array arg. The bytes read by read() method are returned as int.
                    mHandler.obtainMessage(BTConstants.MESSAGE_READ, bytes, -1, buffer).sendToTarget(); //MESSAGE_READ=2, arg1=bytes, arg2=-1(nothing), object=buffer
                    buffer[bytes] = '\0';
                    String BAtoString = new String(buffer);
                    Log.i("Read",BAtoString.substring(0,bytes));
                } catch (IOException e) {
                    connectionLost();
                    //BluetoothServiceFull.this.start();
                    break;
                }
            }
        }

        /**
         * Write to the connected OutStream.
         *
         * @param buffer The bytes to write
         */
        // Call this from the main activity to send data to the remote device.
        public void write(byte[] buffer) { // write to the server
            try {
                mmOutStream.write(buffer);
                buffer[0] = '\0';
                //mHandler.obtainMessage(BTConstants.MESSAGE_WRITE, -1, -1, buffer).sendToTarget();
            } catch (IOException e) {
                Log.e(TAG, "Error occurred when sending data", e);
                //e.printStackTrace();
            }
        }

        // Call this method from the main activity to shut down the connection.
        public void cancel() {
            try {
                mmSocket.close(); //Closes this stream and releases any system resources associated with it.
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

