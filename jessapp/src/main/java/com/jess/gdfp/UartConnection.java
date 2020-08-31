package com.jess.gdfp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.android.things.pio.PeripheralManager;
import com.google.android.things.pio.UartDevice;
import com.google.android.things.pio.UartDeviceCallback;

import java.io.IOException;

public class UartConnection {
    private final static String TAG = MainActivity.class.getSimpleName(); //name of this class

    private UartDevice mUartDevice;
    //public Context context;
    private final String UART_NAME = "UART0";
    private final int BAUD_RATE = 921600;

    public void start(){
        Log.i(TAG,"UartConnection start() called");
        try {
            // We receive an instance of the "UART0" device.
            mUartDevice = PeripheralManager.getInstance().openUartDevice(UART_NAME);
            // Some basic UART configuration.
            // Make sure to configure the other device the exact same way.
            mUartDevice.setBaudrate(BAUD_RATE);
            mUartDevice.setDataSize(8);
            mUartDevice.setParity(UartDevice.PARITY_NONE);
            mUartDevice.setStopBits(1);
            // Finally, we register a callback on a background thread that will be
            // triggered when some data is received via UART.
            mUartDevice.registerUartDeviceCallback(mInputHandler,mCallback);
        }catch (IOException e){
            Log.e(TAG,"Unable to open UartDevice");
        }
    }

    private UartDeviceCallback mCallback = new UartDeviceCallback() {

        @Override
        public boolean onUartDeviceDataAvailable(UartDevice uartDevice) {
            try {
                // Read the received data
                byte[] buffer = new byte[512];
                int read;
                while ((read = mUartDevice.read(buffer, buffer.length)) > 0) {
                    // And send it back to the other device, as a loopback.
                    mUartDevice.write(buffer, read);
                }
            }catch (IOException e){
                Log.w(TAG,"Unable to transfer data over UART");
            }
            // Continue listening for more interrupts.
            return false;
        }

        @Override
        public void onUartDeviceError(UartDevice uart, int error) {
            Log.w(TAG, uart + ": Error event " + error);
        }
    };

    @SuppressLint("HandlerLeak")
    private final Handler mInputHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

        }

    };

}
