package com.jess.gdfp;

import android.util.Log;

import java.util.zip.CRC32;
import java.util.zip.Checksum;

public class SendEncoder {

    private final static String TAG = SendEncoder.class.getSimpleName(); //name of this class
    public static byte[] ENCODER_FRAME = new byte[20];
    private static String GETHEX = "";

    public static void changeEncoder(String HEXDATA){ //HEXDATA is 10 bytes

        char POSITION_13 = HEXDATA.charAt(12);
        char POSITION_14 = HEXDATA.charAt(13);
        char POSITION_15 = HEXDATA.charAt(14);
        char POSITION_16 = HEXDATA.charAt(15);

        StringBuilder JOINCHAR = new StringBuilder();
        GETHEX = JOINCHAR.append(POSITION_13).append(POSITION_14).append(POSITION_15).append(POSITION_16).toString();
        //Log.i("GETHEX ",GETHEX);

        MainActivity MA_OBJECT = new MainActivity();

        if((UartService.ByteArray[6] & 0xFF) == 0 && (UartService.ByteArray[7] & 0xF0) == 0){ //increment encoder 0
            //Log.i(TAG,String.valueOf((UartService.ByteArray[8] & 0xF0))); //always 00
            MA_OBJECT.incrementEncoder1(UartService.ByteArray[7] & 0x0F);
            //Log.i(TAG,"incrementEncoder0 is called");
        }else if((UartService.ByteArray[6] & 0xFF) == 0 && (UartService.ByteArray[7] & 0xF0) == 240){ //decrement encoder 0
            //Log.i(TAG,String.valueOf((UartService.ByteArray[8] & 0xF0))); //always F0
            MA_OBJECT.decrementEncoder1(16 - UartService.ByteArray[7] & 0x0F);
            //Log.i(TAG,"decrementEncoder0 is called");
        }else if((UartService.ByteArray[6] & 0xFF) == 1 && (UartService.ByteArray[7] & 0xF0) == 0){ //increment encoder 1
            //Log.i(TAG,String.valueOf((UartService.ByteArray[8] & 0xF0))); //always 00
            //MA_OBJECT.incrementEncoder1(UartService.ByteArray[7] & 0x0F);
            //Log.i(TAG,"incrementEncoder1 is called");
        }else if((UartService.ByteArray[6] & 0xFF) == 1 && (UartService.ByteArray[7] & 0xF0) == 240){ //decrement encoder 1
            //Log.i(TAG,String.valueOf((UartService.ByteArray[8] & 0xF0))); //always F0
            //MA_OBJECT.decrementEncoder1(16 - UartService.ByteArray[7] & 0x0F);
            //Log.i(TAG,"decrementEncoder1 is called");
        }else if((UartService.ByteArray[6] & 0xFF) == 2 && (UartService.ByteArray[7] & 0xF0) == 0){ //increment encoder 2
            //Log.i(TAG,String.valueOf((UartService.ByteArray[8] & 0xF0))); //always 00
            //MA_OBJECT.incrementEncoder1(UartService.ByteArray[7] & 0x0F);
            //Log.i(TAG,"incrementEncoder2 is called");
        }else if((UartService.ByteArray[6] & 0xFF) == 2 && (UartService.ByteArray[7] & 0xF0) == 240){ //decrement encoder 2
            //Log.i(TAG,String.valueOf((UartService.ByteArray[8] & 0xF0))); //always F0
            //MA_OBJECT.decrementEncoder1(16 - UartService.ByteArray[7] & 0x0F);
            //Log.i(TAG,"decrementEncoder2 is called");
        }else if((UartService.ByteArray[6] & 0xFF) == 153 && (UartService.ByteArray[7] & 0xFF) == 0){ //button 0
            //Log.i(TAG,String.valueOf((UartService.ByteArray[8] & 0xF0))); //always 00
            MA_OBJECT.pressButton0(); //Menu button
            Log.i(TAG,"button0 is called");
        }else if((UartService.ByteArray[6] & 0xFF) == 153 && (UartService.ByteArray[7] & 0xFF) == 1){ //button 1
            //Log.i(TAG,String.valueOf((UartService.ByteArray[8] & 0xF0))); //always 00
            MA_OBJECT.pressButton1(); //Test button
            //Log.i(TAG,"button1 is called");
        }else if((UartService.ByteArray[6] & 0xFF) == 153 && (UartService.ByteArray[7] & 0xFF) == 2){ //button 2
            //Log.i(TAG,String.valueOf((UartService.ByteArray[8] & 0xF0))); //always 00
            MA_OBJECT.pressButton2(); // Home button
            Log.i(TAG,"button2 is called");
        }else if((UartService.ByteArray[6] & 0xFF) == 153 && (UartService.ByteArray[7] & 0xFF) == 3){ //button 3
            //Log.i(TAG,String.valueOf((UartService.ByteArray[8] & 0xF0))); //always 00
            MA_OBJECT.pressButton3(); //Drossel button
            Log.i(TAG,"button3 is called");
        }else if((UartService.ByteArray[6] & 0xFF) == 153 && (UartService.ByteArray[7] & 0xFF) == 4){ //button 4
            //Log.i(TAG,String.valueOf((UartService.ByteArray[8] & 0xF0))); //always 00
            MA_OBJECT.pressButton4(); //Daten button
            Log.i(TAG,"button4 is called");
        }else if((UartService.ByteArray[6] & 0xFF) == 153 && (UartService.ByteArray[7] & 0xFF) == 5){ //button 5
            //Log.i(TAG,String.valueOf((UartService.ByteArray[8] & 0xF0))); //always 00
            MA_OBJECT.pressButton5(); //Verfahren button
            Log.i(TAG,"button5 is called");
        }else if((UartService.ByteArray[6] & 0xFF) == 153 && (UartService.ByteArray[7] & 0xFF) == 6){ //button 6
            //Log.i(TAG,String.valueOf((UartService.ByteArray[8] & 0xF0))); //always 00
            MA_OBJECT.pressButton6(); //Kennlinie button
            Log.i(TAG,"button6 is called");
        }else if((UartService.ByteArray[6] & 0xFF) == 153 && (UartService.ByteArray[7] & 0xFF) == 7){ //button 7
            //Log.i(TAG,String.valueOf((UartService.ByteArray[8] & 0xF0))); //always 00
            MA_OBJECT.pressButton7(); //Betriebsart button
            Log.i(TAG,"button7 is called");
        }else if((UartService.ByteArray[6] & 0xFF) == 153 && (UartService.ByteArray[7] & 0xFF) == 8){ //encoder0 button
            //Log.i(TAG,String.valueOf((UartService.ByteArray[8] & 0xF0))); //always 00
            MA_OBJECT.pressbuttonEncoder0();
            //Log.i(TAG,"encoder0 is called");
        }else if((UartService.ByteArray[6] & 0xFF) == 153 && (UartService.ByteArray[7] & 0xFF) == 9){ //encoder1 button
            //Log.i(TAG,String.valueOf((UartService.ByteArray[8] & 0xF0))); //always 00
            MA_OBJECT.pressbuttonEncoder1();
            //Log.i(TAG,"encoder1 is called");
        }else if((UartService.ByteArray[6] & 0xFF) == 153 && (UartService.ByteArray[7] & 0xFF) == 10){ //encoder2 button
            //Log.i(TAG,String.valueOf((UartService.ByteArray[8] & 0xF0))); //always 00
            MA_OBJECT.pressbuttonEncoder2();
            //Log.i(TAG,"encoder2 is called");
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