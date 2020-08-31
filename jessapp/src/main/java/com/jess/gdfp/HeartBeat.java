package com.jess.gdfp;

import java.util.zip.CRC32;
import java.util.zip.Checksum;

public class HeartBeat {
    //private static UartService mUsbService;
    private static byte[] HEART_BEAT = new byte[20];
    private static String heartbeatStr = "";

    public static void sendHeartBeat(){
        //Log.i("sendHearBeat","is called");
        HEART_BEAT[0] = 36; //0x24
        HEART_BEAT[1] = 19; //0x13
        HEART_BEAT[2] = 2; //0x02
        HEART_BEAT[3] = 7; //0x07 Can Id
        HEART_BEAT[4] = (byte)124; //0x7C Can Id
        HEART_BEAT[5] = 8; //0x08
        HEART_BEAT[6] = 1;
        HEART_BEAT[7] = 1;
        HEART_BEAT[8] = 0;
        HEART_BEAT[9] = 0;
        HEART_BEAT[10] = 0;
        HEART_BEAT[11] = 0;
        HEART_BEAT[12] = 0;
        HEART_BEAT[13] = 0;
        HEART_BEAT[14] = 35; //0x23

        Checksum checkhb = new CRC32();
        checkhb.update(HEART_BEAT, 0, 15);// update the current checksum with the specified array of bytes
        long crcHB = checkhb.getValue();// get the current checksum value //checksum is correct
        //Log.i("tempValue",String.valueOf(crcValue));
        HEART_BEAT[14] = (byte) ((crcHB >>> 24) & 0xFF); //msb
        HEART_BEAT[15] = (byte) ((crcHB >>> 16) & 0xFF);
        HEART_BEAT[16] = (byte) ((crcHB >>> 8) & 0xFF);
        HEART_BEAT[17] = (byte) (crcHB & 0xFF); //lsb
        HEART_BEAT[18] = 35; //0x23

        for(int i=0;i<19;i++){
            heartbeatStr = heartbeatStr + (char)(HEART_BEAT[i]&0xFF);
        }
        //Log.i("heartbeatStr is",heartbeatStr);
        //if(mUsbService != null) {
            UartService.responseToHeartBeat(heartbeatStr);
            heartbeatStr = "";
            //Log.i("mUsbService","not null");
        //} else Log.i("mUsbService","is null");
    }
}
