package com.jess.gdfp;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattServer;
import android.bluetooth.BluetoothGattServerCallback;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Service for managing connection and data communication with a GATT server hosted on a
 * given Bluetooth LE device via the Android BLE API.
 */
public class BluetoothLEService extends Service {
    private final static String TAG = BluetoothLEService.class.getSimpleName(); //name of this class

    public BluetoothManager mBluetoothManager;
    public BluetoothAdapter mBluetoothAdapter;
    private BluetoothGatt mBluetoothGatt; //Gatt client
    public BluetoothGattServer mBluetoothGattServer;
    private Set<BluetoothDevice> mRegisteredDevices = new HashSet<>();

    public final static String ACTION_GATT_CONNECTED =
            "com.felhr.serialportexample.ACTION_GATT_CONNECTED";
    public final static String ACTION_GATT_DISCONNECTED =
            "com.felhr.serialportexample.ACTION_GATT_DISCONNECTED";
    public final static String ACTION_GATT_SERVICES_DISCOVERED =
            "com.felhr.serialportexample.ACTION_GATT_SERVICES_DISCOVERED";
    public final static String ACTION_DATA_AVAILABLE =
            "com.felhr.serialportexample.ACTION_DATA_AVAILABLE";
    public final static String EXTRA_DATA =
            "com.felhr.serialportexample.EXTRA_DATA";

    public final static UUID UUID_NOTIFY = UUID.fromString("0000ffe1-0000-1000-8000-00805f9b34fb");
    public final static UUID UUID_SERVICE = UUID.fromString("0000ffe0-0000-1000-8000-00805f9b34fb");
    public static UUID CLIENT_CONFIG = UUID.fromString("00002902-0000-1000-8000-00805f9b34fb");

    public BluetoothGattCharacteristic mNotifyCharacteristic;

    public void WriteValue(String strValue) {
        mNotifyCharacteristic.setValue(strValue.getBytes());
        mBluetoothGatt.writeCharacteristic(mNotifyCharacteristic);
    }

    /*
    We loop through every service & characteristics and enable notification for characteristics
    and then write them on the BluetoothGatt object defined in our Service.
     */
    public void findService(List<BluetoothGattService> gattServices) {
        Log.i(TAG, "Service count is:" + gattServices.size());
        if(gattServices==null){
            Log.e(TAG,"Custom BLE Service not found");
            return;
        }

        // Loops through available GATT Services.
        for (BluetoothGattService gattService : gattServices) {

            Log.i(TAG, gattService.getUuid().toString());
            Log.i(TAG, UUID_SERVICE.toString());

            if(gattService.getUuid().toString().equalsIgnoreCase(UUID_SERVICE.toString())) {

                List<BluetoothGattCharacteristic> gattCharacteristics = gattService.getCharacteristics();
                Log.i(TAG, "Characteristics count is:" + gattCharacteristics.size());
                // Loops through available Characteristics.
                for (BluetoothGattCharacteristic gattCharacteristic : gattCharacteristics) {

                    if(gattCharacteristic.getUuid().toString().equalsIgnoreCase(UUID_NOTIFY.toString())) {
                        Log.i(TAG, gattCharacteristic.getUuid().toString());
                        Log.i(TAG, UUID_NOTIFY.toString());
                        mNotifyCharacteristic = gattCharacteristic; //BluetoothGattCharacteristic mNotifyCharacteristic
                        setCharacteristicNotification(gattCharacteristic, true); //Enable or disable notifications/indications for a given characteristic.
                        //broadcastUpdate(ACTION_GATT_SERVICES_DISCOVERED);
                        return;
                    }
                }
            }
        }
    }

    // Implements callback methods for GATT events that the app cares about.  For example,
    // connection change and services discovered.
    private final BluetoothGattCallback mGattCallback = new BluetoothGattCallback() {
        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            super.onConnectionStateChange(gatt, status, newState);
            String intentAction;
            Log.e(TAG, "OnConnectionStateChanged");
            //Log.i(TAG, "oldStatus=" + status + " NewStates=" + newState);

            if (newState == BluetoothGatt.STATE_CONNECTED) {
                intentAction = ACTION_GATT_CONNECTED;
                broadcastUpdate(intentAction);
                // Attempts to discover services after successful connection.
                Log.i(TAG, "Connected to GATT server.");
                Log.i(TAG, "Attempting to start service discovery:" +
                        mBluetoothGatt.discoverServices()); //Discovers services offered by a remote device as well as their characteristics and descriptors.
            } else if (newState == BluetoothGatt.STATE_DISCONNECTED) {
                intentAction = ACTION_GATT_DISCONNECTED;
                Log.i(TAG, "Disconnected from GATT server.");
                mBluetoothGatt.close();
                mBluetoothGatt = null;
                broadcastUpdate(intentAction);
            }

           /* if(status == BluetoothGatt.GATT_SUCCESS)
            {

                if (newState == BluetoothProfile.STATE_CONNECTED) {
                    intentAction = ACTION_GATT_CONNECTED;

                    broadcastUpdate(intentAction);
                    Log.i(TAG, "Connected to GATT server.");
                    // Attempts to discover services after successful connection.
                    Log.i(TAG, "Attempting to start service discovery:" +
                            mBluetoothGatt.discoverServices());
                } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                    intentAction = ACTION_GATT_DISCONNECTED;
                    mBluetoothGatt.close();
                    mBluetoothGatt = null;
                    Log.i(TAG, "Disconnected from GATT server.");
                    broadcastUpdate(intentAction);
                }
        	} else if (newState == BluetoothGatt.STATE_DISCONNECTED){
                intentAction = ACTION_GATT_DISCONNECTED;
                mBluetoothGatt.close();
                mBluetoothGatt = null;
                Log.i(TAG, "Disconnected from GATT server.");
                broadcastUpdate(intentAction);
            }*/
        }

        @Override
        // Callback invoked when the list of remote services, characteristics and descriptors
        // for the remote device have been updated, ie new services have been discovered.
        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            if (status == BluetoothGatt.GATT_SUCCESS) { //A GATT operation completed successfully.
                broadcastUpdate(ACTION_GATT_SERVICES_DISCOVERED);
                Log.w(TAG, "onServicesDiscovered received: " + status);
                findService(gatt.getServices()); //Returns a list of GATT services offered by the remote device.
            } else if (mBluetoothGatt.getDevice().getUuids() == null){
                    Log.w(TAG, "onServicesDiscovered received: " + status);
            }
        }

        @Override
        //Callback reporting the result of a characteristic read operation.
        public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            super.onCharacteristicChanged(gatt, characteristic);
            if (status == BluetoothGatt.GATT_SUCCESS) {
                broadcastUpdate(ACTION_DATA_AVAILABLE, characteristic);
            }
            Log.e(TAG, "OnCharacteristicRead");
        }

        @Override
        //As we’re all set up, everytime the value of characteristics changes, we received it in the onCharacteristicChanged.
        //Callback triggered as a result of a remote characteristic notification.
        //Once notifications are enabled for a characteristic, an onCharacteristicChanged() callback is triggered if the characteristic changes on the remote device:
        public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
            super.onCharacteristicChanged(gatt, characteristic);
            broadcastUpdate(ACTION_DATA_AVAILABLE, characteristic);
            Log.e(TAG, "OnCharacteristicChanged");
            Log.i("OnCharacteristicChanged",characteristic.getUuid()+","+characteristic.getValue());
        }

        @Override
        public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            Log.e(TAG, "OnCharacteristicWrite");
        }

        @Override
        //Callback reporting the result of a descriptor read operation.
        public void onDescriptorRead(BluetoothGatt gatt,
                                     BluetoothGattDescriptor bd,
                                     int status) {
            Log.e(TAG, "onDescriptorRead");
        }

        @Override
        //Callback indicating the result of a descriptor write operation.
        public void onDescriptorWrite(BluetoothGatt gatt,
                                      BluetoothGattDescriptor bd,
                                      int status) {
            Log.e(TAG, "onDescriptorWrite");
        }

        @Override
        //Callback reporting the RSSI for a remote device connection.
        public void onReadRemoteRssi(BluetoothGatt gatt, int rssi, int status) {
            Log.e(TAG, "onReadRemoteRssi");
        }

        @Override
        //Callback invoked when a reliable write transaction has been completed.
        public void onReliableWriteCompleted(BluetoothGatt gatt, int a) {
            Log.e(TAG, "onReliableWriteCompleted");
        }
    };

    private BluetoothGattServerCallback mGattServerCallback = new BluetoothGattServerCallback() {
        @Override
        public void onConnectionStateChange(BluetoothDevice device, int status, int newState) {
            //Callback indicating when a remote device has been connected or disconnected.
            super.onConnectionStateChange(device, status, newState);
            if(newState== BluetoothProfile.STATE_CONNECTED){
                Log.i(TAG,"BluetoothDevice CONNECTED: "+ device);
            }else if(newState==BluetoothProfile.STATE_DISCONNECTED){
                //mBluetoothGattServer.close();
                Log.i(TAG,"BluetoothDevice DISCONNECTED: "+ device);
                //Remove device from any active subscriptions
                mRegisteredDevices.remove(device); //Set<BluetoothDevice> mRegisteredDevices = new HashSet<>();
            }
        }

        @Override
        public void onCharacteristicReadRequest(BluetoothDevice device, int requestId, int offset, BluetoothGattCharacteristic characteristic) {
            super.onCharacteristicReadRequest(device, requestId, offset, characteristic);
            Log.i(TAG,"A remote client has requested to read a local characteristic. ");

            //if (TimeProfile.CURRENT_TIME.equals(characteristic.getUuid())) {
            //Send a response to a read or write request to a remote device.
            mBluetoothGattServer.sendResponse(device,
                    requestId,
                    BluetoothGatt.GATT_SUCCESS,
                    0,
                    "Testing".getBytes());
            Log.i(TAG,"send a response upon read request");
            //}
        }

        @Override
        public void onDescriptorReadRequest(BluetoothDevice device, int requestId, int offset, BluetoothGattDescriptor descriptor) {
            super.onDescriptorReadRequest(device, requestId, offset, descriptor);
            Log.i(TAG,"onDescriptorReadRequest");
        }

        @Override
        public void onDescriptorWriteRequest(BluetoothDevice device, int requestId, BluetoothGattDescriptor descriptor, boolean preparedWrite, boolean responseNeeded, int offset, byte[] value) {
            super.onDescriptorWriteRequest(device, requestId, descriptor, preparedWrite, responseNeeded, offset, value);
            Log.i(TAG,"onDescriptorWriteRequest");
        }
    };

    private void broadcastUpdate(final String action) {
        final Intent intent = new Intent(action);
        sendBroadcast(intent); //send to Main Activity
    }

    private void broadcastUpdate(final String action, final BluetoothGattCharacteristic characteristic) {
        final Intent intent = new Intent(action);

        // This is special handling for the Heart Rate Measurement profile.  Data parsing is
        // carried out as per profile specifications:
        // http://developer.bluetooth.org/gatt/characteristics/Pages/CharacteristicViewer.aspx?u=org.bluetooth.characteristic.heart_rate_measurement.xml
        // For all other profiles, writes the data formatted in HEX.
        final byte[] data = characteristic.getValue();
        if (data != null && data.length > 0) {
            String strdata = new String(data);
            Log.i("broadcastUpdate data",strdata);
            //final StringBuilder stringBuilder = new StringBuilder(data.length);
            //for(byte byteChar : data)
            //    stringBuilder.append(String.format("%02X ", byteChar));
            //intent.putExtra(EXTRA_DATA, new String(data) + "\n" + stringBuilder.toString());

            //The broadcast message is wrapped in an Intent object. The intent's action string
            // must provide the app's Java package name syntax and uniquely identify the broadcast event.
            intent.putExtra(EXTRA_DATA, new String(data));//can store string, int,bytes[] etc.
        }
        sendBroadcast(intent);
    }

    public class LocalBinder extends Binder {
        BluetoothLEService getService() {
            return BluetoothLEService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        // After using a given device, you should make sure that BluetoothGatt.close() is called
        // such that resources are cleaned up properly.  In this particular example, close() is
        // invoked when the UI is disconnected from the Service.
        close();
        return super.onUnbind(intent);
    }

    private final IBinder mBinder = new LocalBinder();

    /**
     * Initializes a reference to the local Bluetooth adapter.
     *
     * @return Return true if the initialization is successful.
     */
    public boolean initialize() {
        // For API level 18 and above, get a reference to BluetoothAdapter through BluetoothManager.
        if (mBluetoothManager == null) {
            mBluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
            if (mBluetoothManager == null) {
                Log.e(TAG, "Unable to initialize BluetoothManager.");
                return false;
            }else Log.i(TAG, "Initialize BluetoothManager.");
        }

        mBluetoothAdapter = mBluetoothManager.getAdapter();
        if(!mBluetoothAdapter.isEnabled()){
            Log.e(TAG, "Bluetooth is currently disabled...enabling");
            mBluetoothAdapter.enable();
        }else{
            Log.d(TAG, "Bluetooth enabled...starting services");
            startServer();
        }
        // We can't continue without proper Bluetooth support, checkBluetoothSupport()
        if (mBluetoothAdapter == null) {
            Log.e(TAG, "Unable to obtain a BluetoothAdapter.");
            return false;
        }else Log.i(TAG, "Obtained a BluetoothAdapter.");

        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            Log.e(TAG, "Bluetooth LE is not supported");
            return false;
        }else Log.i(TAG, "Bluetooth LE is supported");

        return true;
    }

    /**
     * Initialize the GATT server instance with the services/characteristics
     * from the Time Profile.
     */
    public void startServer(){
        Log.i(TAG,"startServer");
        mBluetoothGattServer = mBluetoothManager.openGattServer(this,mGattServerCallback);
        if (mBluetoothGattServer == null){
            Log.e(TAG,"Unable to create GATT server");
        } else Log.w(TAG,"GATT server is created");

        mBluetoothGattServer.addService(CreateService());

    }

    /**
     * Shut down the GATT server.
     */
    public void stopServer(){
        Log.e(TAG,"stopServer");
        if(mBluetoothGattServer==null)return;

        mBluetoothGattServer.close();
    }

    private BluetoothGattService CreateService(){

        BluetoothGattService service = new BluetoothGattService(UUID_SERVICE,
                BluetoothGattService.SERVICE_TYPE_PRIMARY);

        return service;
    }

    /**
     * Connects to the GATT server hosted on the Bluetooth LE device.
     *
     * @param address The device address of the destination device.
     *
     * @return Return true if the connection is initiated successfully. The connection result
     *         is reported asynchronously through the
     *         {@code BluetoothGattCallback#onConnectionStateChange(android.bluetooth.BluetoothGatt, int, int)}
     *         callback.
     */
    /*public boolean connect(final String address) {
        if (mBluetoothAdapter == null || address == null) {
            Log.e(TAG, "BluetoothAdapter not initialized or unspecified address.");
            return false;
        }
*//*
        // Previously connected device.  Try to reconnect.
        if (mBluetoothDeviceAddress != null && address.equals(mBluetoothDeviceAddress)
                && mBluetoothGatt != null) {
            Log.d(TAG, "Trying to use an existing mBluetoothGatt for connection.");
            if (mBluetoothGatt.connect()) {
                mConnectionState = STATE_CONNECTING;
                return true;
            } else {
                return false;
            }
        }
*//*
        final BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(address); //address: Bluetooth address of pairing device
        if (device == null) {
            Log.e(TAG, "Device not found. Unable to connect.");
            return false;
        }
        // We want to directly connect to the device, so we are setting the autoConnect parameter to false.
        if(mBluetoothGatt != null) {
            mBluetoothGatt.close();
            mBluetoothGatt = null;
        }
        *//**
        * This connects to the GATT server hosted by the BLE device, and returns a BluetoothGatt instance, which you can then use to conduct GATT client operations.
        * @param autoConnect (boolean indicating whether to automatically connect to the BLE device as soon as it becomes available)
        * @param BluetoothGattCallback is used to deliver results to the client, such as connection status, as well as any further GATT client operations.
         *//*
        mBluetoothGatt = device.connectGatt(this, false, mGattCallback);
        //mBluetoothGatt.connect();

        Log.d(TAG, "Trying to create a new connection.");
        return true;
    }*/

    /**
     * Disconnects an existing connection or cancel a pending connection. The disconnection result
     * is reported asynchronously through the
     * {@code BluetoothGattCallback#onConnectionStateChange(android.bluetooth.BluetoothGatt, int, int)}
     * callback.
     */
    public void disconnect() {
        if (mBluetoothAdapter == null || mBluetoothGatt == null) {
            Log.w(TAG, "BluetoothAdapter not initialized");
            return;
        }
        mBluetoothGatt.disconnect();
    }

    /**
     * After using a given BLE device, the app must call this method to ensure resources are
     * released properly.
     */
    public void close() {
        if (mBluetoothGatt == null) {
            return;
        }
        mBluetoothGatt.close();
        mBluetoothGatt = null;
    }

    /**
     * Request a read on a given {@code BluetoothGattCharacteristic}. The read result is reported
     * asynchronously through the {@code BluetoothGattCallback#onCharacteristicRead(android.bluetooth.BluetoothGatt, android.bluetooth.BluetoothGattCharacteristic, int)}
     * callback.
     *
     * @param characteristic The characteristic to read from.
     */
    public void readCharacteristic(BluetoothGattCharacteristic characteristic) {
        if (mBluetoothAdapter == null || mBluetoothGatt == null) {
            Log.w(TAG, "BluetoothAdapter not initialized");
            return;
        }
        mBluetoothGatt.readCharacteristic(characteristic);
    }

    /**
     * Enables or disables notification on a give characteristic.
     *
     * @param characteristic Characteristic to act on.
     * @param enabled If true, enable notification.  False otherwise.
     */
    public void setCharacteristicNotification(BluetoothGattCharacteristic characteristic, boolean enabled) {
        if (mBluetoothAdapter == null || mBluetoothGatt == null) {
            Log.w(TAG, "BluetoothAdapter not initialized");
            return;
        }
        mBluetoothGatt.setCharacteristicNotification(characteristic, enabled);
/*
        // This is specific to Heart Rate Measurement.
        if (UUID_HEART_RATE_MEASUREMENT.equals(characteristic.getUuid())) {
            BluetoothGattDescriptor descriptor = characteristic.getDescriptor(
                    UUID.fromString(SampleGattAttributes.CLIENT_CHARACTERISTIC_CONFIG));
            descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
            mBluetoothGatt.writeDescriptor(descriptor);
        }
        */
    }

    private void notifyRegisteredDevices() {
        if (mRegisteredDevices.isEmpty()) {
            Log.i(TAG, "No subscribers registered");
            return;
        }

        Log.i(TAG, "Sending update to " + mRegisteredDevices.size() + " subscribers");
        for (BluetoothDevice device : mRegisteredDevices) {
            BluetoothGattCharacteristic timeCharacteristic = mBluetoothGattServer
                    .getService(UUID_SERVICE)
                    .getCharacteristic(UUID_NOTIFY);
            timeCharacteristic.setValue("Testing".getBytes());
            mBluetoothGattServer.notifyCharacteristicChanged(device, timeCharacteristic, false);
        }
    }

    /**
     * Retrieves a list of supported GATT services on the connected device. This should be
     * invoked only after {@code BluetoothGatt#discoverServices()} completes successfully.
     *
     * @return A {@code List} of supported services.
     */
    public List<BluetoothGattService> getSupportedGattServices() {
        if (mBluetoothGatt == null) return null;

        return mBluetoothGatt.getServices();
    }
}
