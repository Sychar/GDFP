package com.jess.gdfp;

import android.util.Log;

import java.util.zip.CRC32;
import java.util.zip.Checksum;

public class SendEncoder {

    private final static String TAG = SendEncoder.class.getSimpleName(); //name of this class
    public static byte[] ENCODER_FRAME = new byte[20];
    private static String GETHEX = "";

    public static void changeEncoder(String HEXDATA){ //HEXDATA is 12 bytes

        char POSITION_13 = HEXDATA.charAt(12);
        char POSITION_14 = HEXDATA.charAt(13);
        char POSITION_15 = HEXDATA.charAt(14);
        char POSITION_16 = HEXDATA.charAt(15);
        char POSITION_17 = HEXDATA.charAt(16);
        char POSITION_18 = HEXDATA.charAt(17);
        int INT_POS17 = UartService.ByteArray[8] & 0xFF;
        int INT_POS18 = POSITION_18& 0xFF;
        //Log.i("INT_POS17 ",String.valueOf(INT_POS17));
        //Log.i("INT_POS18 ",String.valueOf(INT_POS18));

        StringBuilder JOINCHAR = new StringBuilder();
        GETHEX = JOINCHAR.append(POSITION_13).append(POSITION_14).append(POSITION_15).append(POSITION_16).append(POSITION_17).append(POSITION_18).toString();

        MainActivity MA_OBJECT = new MainActivity();

        //MA_OBJECT.incrementEncoder1(INT_POS17+INT_POS18);

        if((UartService.ByteArray[8] & 0xF0) == 0 && (UartService.ByteArray[7] & 0xFF) == 0 && (UartService.ByteArray[6] & 0xFF) == 0) { //increment encoder
            //Log.i(TAG,String.valueOf((UartService.ByteArray[8] & 0xF0))); //always 00
            MA_OBJECT.incrementEncoder1(UartService.ByteArray[8] & 0x0F);
            //Log.i(TAG,"incrementEncoder1 is called");
        }else if((UartService.ByteArray[8] & 0xF0) == 240 && (UartService.ByteArray[7] & 0xFF) == 0 && (UartService.ByteArray[6] & 0xFF) == 0){ //decrement encoder
            //Log.i(TAG,String.valueOf((UartService.ByteArray[8] & 0xF0))); //always F0
            MA_OBJECT.decrementEncoder1(16 - UartService.ByteArray[8] & 0x0F);
            //Log.i(TAG,"decrementEncoder1 is called");
        }

        switch (GETHEX){
            /*case "000001" : MA_OBJECT.incrementEncoder1(1); //clockwise encoder
                Log.i(TAG,"increment encoder 1");
                break;
            case "000002" : MA_OBJECT.incrementEncoder1(2); //clockwise encoder
                Log.i(TAG,"increment encoder 1");
                break;
            case "000003" : MA_OBJECT.incrementEncoder1(3); //clockwise encoder
                Log.i(TAG,"increment encoder 1");
                break;
            case "000081" : MA_OBJECT.decrementEncoder1(1); //anticlockwise encoder
                Log.i(TAG,"decrement encoder 1");
                break;*/
            case "000100" : MA_OBJECT.buttonEncoder1();
                //Log.i(TAG,"press button encoder 1");
                break;
            case "010000" : MA_OBJECT.pressButton1(); //Test button
                //Log.i(TAG,"Test button is pressed");
                break;
            case "020000" : MA_OBJECT.pressButton2(); // Home button
                //Log.i(TAG,"Home button is pressed");
                break;
            case "040000" : MA_OBJECT.pressButton3(); //Drossel button
                //Log.i(TAG,"Drossel button is pressed");
                break;
            case "080000" : MA_OBJECT.pressButton4(); //Daten button
                //Log.i(TAG,"Daten button is pressed");
                break;
            case "100000" : MA_OBJECT.pressButton5(); //Verfahren button
                //Log.i(TAG,"Verfahren button is pressed");
                break;
            case "200000" : MA_OBJECT.pressButton6(); //Kennlinie button
                //Log.i(TAG,"Kennlinie button is pressed");
                break;
            case "400000" : MA_OBJECT.pressButton7(); //Betriebsart button
                //Log.i(TAG,"Betriebsart button is pressed");
                break;
            case "800000" : MA_OBJECT.pressButton8(); //Menu button
                //Log.i(TAG,"Menu button is pressed");
                break;

        }

        ENCODER_FRAME[0] = 36; //0x24
        ENCODER_FRAME[1] = 15; //0x0F
        ENCODER_FRAME[2] = 5; //Frame ID
        ENCODER_FRAME[3] = 0; //msb Can ID
        ENCODER_FRAME[4] = 0; //lsb Can ID
        ENCODER_FRAME[5] = 0; //Data Length
        ENCODER_FRAME[6] = 0; //Button Status
        ENCODER_FRAME[7] = 0; //Encoder button status
        ENCODER_FRAME[8] = 0; //Encoder 1 increment/decrement
        ENCODER_FRAME[9] = 0; //Encoder 2 increment/decrement
        ENCODER_FRAME[10] = 35; //0x23

        Checksum tempcheck = new CRC32();
        tempcheck.update(ENCODER_FRAME,0,11);// update the current checksum with the specified array of bytes
        long crcValue = tempcheck.getValue();// get the current checksum value

        ENCODER_FRAME[10] = (byte) ((crcValue >>> 24) & 0xFF); //msb CRC
        ENCODER_FRAME[11] = (byte) ((crcValue >>> 16) & 0xFF);
        ENCODER_FRAME[12] = (byte) ((crcValue >>> 8) & 0xFF);
        ENCODER_FRAME[13] = (byte) (crcValue & 0xFF); //lsb CRC
        ENCODER_FRAME[14] = 35; //0x23

        //SendUart(ENCODER_FRAME,15);
        //SendUart(ENCODER_FRAME,15);
    }

}
