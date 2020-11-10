package com.jess.gdfp;

import android.util.Log;

import java.util.zip.CRC32;
import java.util.zip.Checksum;

public class SendEncoder {

    private final static String TAG = SendEncoder.class.getSimpleName(); //name of this class
    public static byte[] ENCODER_FRAME = new byte[20];

    public static void changeEncoder(String HEXDATA){ //HEXDATA is 10 bytes

        WeldingChangeParam WC_OBJECT = new WeldingChangeParam();

        if((UartService.ByteArray[6] & 0xFF) == 2 && (UartService.ByteArray[7] & 0xF0) == 0){ //increment encoder 0 (left)
            //Log.i(TAG,String.valueOf((UartService.ByteArray[8] & 0xF0))); //always 00
            //WC_OBJECT.incrementEncoder0(UartService.ByteArray[7] & 0x0F);
            //Log.i(TAG,"incrementEncoder0 is called");
        }else if((UartService.ByteArray[6] & 0xFF) == 2 && (UartService.ByteArray[7] & 0xF0) == 240){ //decrement encoder 0 (left)
            //Log.i(TAG,String.valueOf((UartService.ByteArray[8] & 0xF0))); //always F0
            //WC_OBJECT.decrementEncoder0(16 - UartService.ByteArray[7] & 0x0F);
            //Log.i(TAG,"decrementEncoder0 is called");
        }else if((UartService.ByteArray[6] & 0xFF) == 1 && (UartService.ByteArray[7] & 0xF0) == 0){ //increment encoder 1 (middle)
            //Log.i(TAG,String.valueOf((UartService.ByteArray[8] & 0xF0))); //always 00
            WC_OBJECT.incrementEncoder1(UartService.ByteArray[7] & 0x0F);
            //Log.i(TAG,"incrementEncoder1 is called");
        }else if((UartService.ByteArray[6] & 0xFF) == 1 && (UartService.ByteArray[7] & 0xF0) == 240){ //decrement encoder 1 (middle)
            //Log.i(TAG,String.valueOf((UartService.ByteArray[8] & 0xF0))); //always F0
            WC_OBJECT.decrementEncoder1(16 - UartService.ByteArray[7] & 0x0F);
            //Log.i(TAG,"decrementEncoder1 is called");
        }else if((UartService.ByteArray[6] & 0xFF) == 0 && (UartService.ByteArray[7] & 0xF0) == 0){ //increment encoder 2 (right)
            //Log.i(TAG,String.valueOf((UartService.ByteArray[8] & 0xF0))); //always 00
            //WC_OBJECT.incrementEncoder2(UartService.ByteArray[7] & 0x0F);
            //Log.i(TAG,"incrementEncoder2 is called");
        }else if((UartService.ByteArray[6] & 0xFF) == 0 && (UartService.ByteArray[7] & 0xF0) == 240){ //decrement encoder 2 (right)
            //Log.i(TAG,String.valueOf((UartService.ByteArray[8] & 0xF0))); //always F0
            //WC_OBJECT.decrementEncoder2(16 - UartService.ByteArray[7] & 0x0F);
            //Log.i(TAG,"decrementEncoder2 is called");
        }else if((UartService.ByteArray[6] & 0xFF) == 153 && (UartService.ByteArray[7] & 0xFF) == 0){ //button 0
            //Log.i(TAG,String.valueOf((UartService.ByteArray[8] & 0xF0))); //always 00
            WC_OBJECT.pressVerfahren(); //Verfahren button
            //Log.i(TAG,"button5 is called");
        }else if((UartService.ByteArray[6] & 0xFF) == 153 && (UartService.ByteArray[7] & 0xFF) == 1){ //button 1
            //Log.i(TAG,String.valueOf((UartService.ByteArray[8] & 0xF0))); //always 00
            WC_OBJECT.pressKennlinie(); //Kennlinie button
            //Log.i(TAG,"button6 is called");
        }else if((UartService.ByteArray[6] & 0xFF) == 153 && (UartService.ByteArray[7] & 0xFF) == 2){ //button 2
            //Log.i(TAG,String.valueOf((UartService.ByteArray[8] & 0xF0))); //always 00
            WC_OBJECT.pressBetriebsart(); //Betriebsart button
            //Log.i(TAG,"button7 is called");
        }else if((UartService.ByteArray[6] & 0xFF) == 153 && (UartService.ByteArray[7] & 0xFF) == 3){ //button 3
            //Log.i(TAG,String.valueOf((UartService.ByteArray[8] & 0xF0))); //always 00
            WC_OBJECT.pressMenu(); //Menu button
            //Log.i(TAG,"button0 is called");
        }else if((UartService.ByteArray[6] & 0xFF) == 153 && (UartService.ByteArray[7] & 0xFF) == 4){ //button 4
            //Log.i(TAG,String.valueOf((UartService.ByteArray[8] & 0xF0))); //always 00
            WC_OBJECT.pressDaten(); //Daten button
            //Log.i(TAG,"button4 is called");
        }else if((UartService.ByteArray[6] & 0xFF) == 153 && (UartService.ByteArray[7] & 0xFF) == 5){ //button 5
            //Log.i(TAG,String.valueOf((UartService.ByteArray[8] & 0xF0))); //always 00
            WC_OBJECT.pressHome(); //Home button
            //Log.i(TAG,"button2 is called");
        }else if((UartService.ByteArray[6] & 0xFF) == 153 && (UartService.ByteArray[7] & 0xFF) == 6){ //button 6
            //Log.i(TAG,String.valueOf((UartService.ByteArray[8] & 0xF0))); //always 00
            WC_OBJECT.pressDroessel(); //Droessel button
            //Log.i(TAG,"button3 is called");
        }else if((UartService.ByteArray[6] & 0xFF) == 153 && (UartService.ByteArray[7] & 0xFF) == 7){ //button 7
            //Log.i(TAG,String.valueOf((UartService.ByteArray[8] & 0xF0))); //always 00
            WC_OBJECT.pressJob(); //Job button
            //Log.i(TAG,"button1 is called");
        }else if((UartService.ByteArray[6] & 0xFF) == 153 && (UartService.ByteArray[7] & 0xFF) == 10){ //encoder0 button(left)
            //Log.i(TAG,String.valueOf((UartService.ByteArray[8] & 0xF0))); //always 00
            //WC_OBJECT.pressbuttonEncoder0();
            //Log.i(TAG,"encoder0 button is pressed");
        }else if((UartService.ByteArray[6] & 0xFF) == 153 && (UartService.ByteArray[7] & 0xFF) == 9){ //encoder1 button(middle)
            //Log.i(TAG,String.valueOf((UartService.ByteArray[8] & 0xF0))); //always 00
            WC_OBJECT.pressbuttonEncoder1();
            //Log.i(TAG,"encoder1 button is press");
        }else if((UartService.ByteArray[6] & 0xFF) == 153 && (UartService.ByteArray[7] & 0xFF) == 8){ //encoder2 button(right)
            //Log.i(TAG,String.valueOf((UartService.ByteArray[8] & 0xF0))); //always 00
            WC_OBJECT.pressbuttonEncoder2();
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
    }
}