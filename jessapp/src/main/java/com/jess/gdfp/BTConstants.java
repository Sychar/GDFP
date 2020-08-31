package com.jess.gdfp;

/**
 * Defines several constants used between {@link BluetoothService & BLEService} and the UI.
 */
public class BTConstants {

    // Message types sent from the BluetoothChatService Handler
    public static final int MESSAGE_STATE_CHANGE = 1;
    public static final int MESSAGE_READ = 2;
    public static final int MESSAGE_WRITE = 3;
    public static final int MESSAGE_DEVICE_NAME = 4;
    public static final int MESSAGE_TOAST = 5;

    // Key names received from the BluetoothChatService Handler
    public static final String DEVICE_NAME = "device_name";
    public static final String TOAST = "toast";


    // Message types sent from the BluetoothChatService Handler
    public static final int BLE_MESSAGE_STATE_CHANGE = 1;
    public static final int BLE_MESSAGE_READ = 2;
    public static final int BLE_MESSAGE_WRITE = 3;
    public static final int BLE_MESSAGE_DEVICE_NAME = 4;
    public static final int BLE_MESSAGE_TOAST = 5;

    // Key names received from the BluetoothChatService Handler
    public static final String BLE_DEVICE_NAME = "device_name";
    public static final String BLE_TOAST = "toast";

}
