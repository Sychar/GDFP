package com.jess.gdfp;

import android.util.Log;

import java.util.zip.CRC32;
import java.util.zip.Checksum;

public class ChangeKennlinie {

    private byte[] FIRST_KENNLINIE = new byte[50];
    private byte[] FIRST_FULLFRAME = new byte[200];
    private byte[] SECOND_KENNLINIE = new byte[50];
    private byte[] SECOND_FULLFRAME = new byte[200];
    private byte[] THIRD_KENNLINIE = new byte[50];
    private byte[] THIRD_FULLFRAME = new byte[200];

    public byte[] sendKennlinieBaseData(){
        FIRST_KENNLINIE[0] = 22; //0x16
        FIRST_KENNLINIE[1] = 1;
        FIRST_KENNLINIE[2] = 1;
        FIRST_KENNLINIE[3] = 8;
        FIRST_KENNLINIE[4] = 0;
        FIRST_KENNLINIE[5] = (byte)58; //0x3A
        FIRST_KENNLINIE[6] = (byte)78; //0x4E
        FIRST_KENNLINIE[7] = 2;
        FIRST_KENNLINIE[8] = 1;
        FIRST_KENNLINIE[9] = 0;
        FIRST_KENNLINIE[10] = (byte)65; //0x41
        FIRST_KENNLINIE[11] = 1;
        FIRST_KENNLINIE[12] = 12; //0x0C
        FIRST_KENNLINIE[13] = 0;
        FIRST_KENNLINIE[14] = 0;
        FIRST_KENNLINIE[15] = 0;
        FIRST_KENNLINIE[16] = 0;
        FIRST_KENNLINIE[17] = 0;
        FIRST_KENNLINIE[18] = 0;
        FIRST_KENNLINIE[19] = (byte)146; //0x92
        FIRST_KENNLINIE[20] = 5;
        FIRST_KENNLINIE[21] = 0;
        FIRST_KENNLINIE[22] = 5;
        FIRST_KENNLINIE[23] = 0;
        FIRST_KENNLINIE[24] = 0;
        FIRST_KENNLINIE[25] = 0;
        FIRST_KENNLINIE[26] = 0;
        FIRST_KENNLINIE[27] = 0;
        FIRST_KENNLINIE[28] = 5;
        FIRST_KENNLINIE[29] = 0;
        FIRST_KENNLINIE[30] = 0;
        FIRST_KENNLINIE[31] = 0;
        FIRST_KENNLINIE[32] = 5;
        FIRST_KENNLINIE[33] = 0;
        FIRST_KENNLINIE[34] = 0;
        FIRST_KENNLINIE[35] = 0;
        FIRST_KENNLINIE[36] = 5;
        FIRST_KENNLINIE[37] = 0;
        FIRST_KENNLINIE[38] = 0;
        FIRST_KENNLINIE[39] = 0;
        FIRST_KENNLINIE[40] = 0;
        FIRST_KENNLINIE[41] = 0;
        FIRST_KENNLINIE[42] = 0;
        FIRST_KENNLINIE[43] = 0;
        FIRST_KENNLINIE[44] = 0;
        FIRST_KENNLINIE[45] = 0;
        FIRST_KENNLINIE[46] = 0;
        FIRST_KENNLINIE[47] = (byte)238; //0xEE
        FIRST_KENNLINIE[48] = 0;
        FIRST_KENNLINIE[49] = 0;
        FIRST_KENNLINIE[50] = 0;
        FIRST_KENNLINIE[51] = 0;
        FIRST_KENNLINIE[52] = 5;
        FIRST_KENNLINIE[53] = 0;
        FIRST_KENNLINIE[54] = 0;
        FIRST_KENNLINIE[55] = (byte)100; //0x64
        FIRST_KENNLINIE[56] = 0;
        FIRST_KENNLINIE[57] = 0;
        FIRST_KENNLINIE[58] = 0;
        FIRST_KENNLINIE[59] = 0;
        FIRST_KENNLINIE[60] = 0;
        FIRST_KENNLINIE[61] = 0;
        FIRST_KENNLINIE[62] = 0;
        FIRST_KENNLINIE[63] = 0;
        FIRST_KENNLINIE[64] = (byte)83; //0x53
        FIRST_KENNLINIE[65] = (byte)237; //0xED
        FIRST_KENNLINIE[66] = 0;
        FIRST_KENNLINIE[67] = 0;
        FIRST_KENNLINIE[68] = 3;
        FIRST_KENNLINIE[69] = 4;

        int nr = 0;
        int num = 0;
        for (int k = 0; k < 8; k++) { //send 36 frames to the machine
            FIRST_FULLFRAME[nr] = 36; //0x24
            FIRST_FULLFRAME[nr + 1] = 19; //0x13
            FIRST_FULLFRAME[nr + 2] = 2;
            FIRST_FULLFRAME[nr + 3] = 6; //msb can id
            FIRST_FULLFRAME[nr + 4] = (byte) 224; //0xE0 lsb can id
            FIRST_FULLFRAME[nr + 5] = 8; //data length
            FIRST_FULLFRAME[nr + 6] = FIRST_KENNLINIE[num];
            FIRST_FULLFRAME[nr + 7] = FIRST_KENNLINIE[num + 1];
            FIRST_FULLFRAME[nr + 8] = FIRST_KENNLINIE[num + 2];
            FIRST_FULLFRAME[nr + 9] = FIRST_KENNLINIE[num + 3];
            FIRST_FULLFRAME[nr + 10] = FIRST_KENNLINIE[num + 4];
            FIRST_FULLFRAME[nr + 11] = FIRST_KENNLINIE[num + 5];
            FIRST_FULLFRAME[nr + 12] = FIRST_KENNLINIE[num + 6];
            FIRST_FULLFRAME[nr + 13] = FIRST_KENNLINIE[num + 7];
            FIRST_FULLFRAME[nr + 14] = 35; //0x23

            Checksum tmpchecksum = new CRC32();
            tmpchecksum.update(FIRST_FULLFRAME, nr, 15);// update the current checksum with the specified array of bytes
            long crcValue = tmpchecksum.getValue();// get the current checksum value //checksum is correct
            //Log.i("tempValue",String.valueOf(crcValue));
            FIRST_FULLFRAME[nr + 14] = (byte) ((crcValue >>> 24) & 0xFF); //msb
            FIRST_FULLFRAME[nr + 15] = (byte) ((crcValue >>> 16) & 0xFF);
            FIRST_FULLFRAME[nr + 16] = (byte) ((crcValue >>> 8) & 0xFF);
            FIRST_FULLFRAME[nr + 17] = (byte) (crcValue & 0xFF); //lsb
            FIRST_FULLFRAME[nr + 18] = 35; //0x23
            nr = nr + 19;
            num = num + 8;
        }
        FIRST_FULLFRAME[152] = 36; //0x24
        FIRST_FULLFRAME[153] = 17; //0x11
        FIRST_FULLFRAME[154] = 2;
        FIRST_FULLFRAME[155] = 6; //msb can id
        FIRST_FULLFRAME[156] = (byte) 224; //0xE0 lsb can id
        FIRST_FULLFRAME[157] = 6; //data length
        FIRST_FULLFRAME[158] = FIRST_KENNLINIE[64];
        FIRST_FULLFRAME[159] = FIRST_KENNLINIE[65];
        FIRST_FULLFRAME[160] = FIRST_KENNLINIE[66];
        FIRST_FULLFRAME[161] = FIRST_KENNLINIE[67];
        FIRST_FULLFRAME[162] = FIRST_KENNLINIE[68];
        FIRST_FULLFRAME[163] = FIRST_KENNLINIE[69];
        FIRST_FULLFRAME[164] = 35; //0x23

        Checksum tmpchecksum1 = new CRC32();
        tmpchecksum1.update(FIRST_FULLFRAME, 152, 13);// update the current checksum with the specified array of bytes
        long crcValue1 = tmpchecksum1.getValue();// get the current checksum value
        FIRST_FULLFRAME[164] = (byte) ((crcValue1 >>> 24) & 0xFF); //msb
        FIRST_FULLFRAME[165] = (byte) ((crcValue1 >>> 16) & 0xFF);
        FIRST_FULLFRAME[166] = (byte) ((crcValue1 >>> 8) & 0xFF);
        FIRST_FULLFRAME[167] = (byte) (crcValue1 & 0xFF); //lsb
        FIRST_FULLFRAME[168] = 35; //0x23

        return FIRST_FULLFRAME;
    }

    public byte[] sendKennlinieStützpunkt(){

        SECOND_KENNLINIE[0] = 22; //0x16
        SECOND_KENNLINIE[1] = 1;
        SECOND_KENNLINIE[2] = 1;
        SECOND_KENNLINIE[3] = 9;
        SECOND_KENNLINIE[4] = 0;
        SECOND_KENNLINIE[5] = 4;
        SECOND_KENNLINIE[6] = (byte)68; //0x44
        SECOND_KENNLINIE[7] = 2;
        SECOND_KENNLINIE[8] = 1;
        SECOND_KENNLINIE[9] = 0;
        SECOND_KENNLINIE[10] = (byte)65; //0x41
        SECOND_KENNLINIE[11] = 2;
        SECOND_KENNLINIE[12] = (byte)73; //0x49
        SECOND_KENNLINIE[13] = (byte)176; //0xB0
        SECOND_KENNLINIE[14] = 3;
        SECOND_KENNLINIE[15] = 4;

        int nr = 0;
        int num = 0;
        for (int k = 0; k < 2; k++) { //send 2 frames to the machine
            SECOND_FULLFRAME[nr] = 36; //0x24
            SECOND_FULLFRAME[nr + 1] = 19; //0x13
            SECOND_FULLFRAME[nr + 2] = 2;
            SECOND_FULLFRAME[nr + 3] = 6; //msb can id
            SECOND_FULLFRAME[nr + 4] = (byte) 224; //0xE0 lsb can id
            SECOND_FULLFRAME[nr + 5] = 8; //data length
            SECOND_FULLFRAME[nr + 6] = SECOND_KENNLINIE[num];
            SECOND_FULLFRAME[nr + 7] = SECOND_KENNLINIE[num + 1];
            SECOND_FULLFRAME[nr + 8] = SECOND_KENNLINIE[num + 2];
            SECOND_FULLFRAME[nr + 9] = SECOND_KENNLINIE[num + 3];
            SECOND_FULLFRAME[nr + 10] = SECOND_KENNLINIE[num + 4];
            SECOND_FULLFRAME[nr + 11] = SECOND_KENNLINIE[num + 5];
            SECOND_FULLFRAME[nr + 12] = SECOND_KENNLINIE[num + 6];
            SECOND_FULLFRAME[nr + 13] = SECOND_KENNLINIE[num + 7];
            SECOND_FULLFRAME[nr + 14] = 35; //0x23

            Checksum tmpchecksum2 = new CRC32();
            tmpchecksum2.update(SECOND_FULLFRAME, nr, 15);// update the current checksum with the specified array of bytes
            long crcValue2 = tmpchecksum2.getValue();// get the current checksum value //checksum is correct
            //Log.i("tempValue",String.valueOf(crcValue));
            SECOND_FULLFRAME[nr + 14] = (byte) ((crcValue2 >>> 24) & 0xFF); //msb
            SECOND_FULLFRAME[nr + 15] = (byte) ((crcValue2 >>> 16) & 0xFF);
            SECOND_FULLFRAME[nr + 16] = (byte) ((crcValue2 >>> 8) & 0xFF);
            SECOND_FULLFRAME[nr + 17] = (byte) (crcValue2 & 0xFF); //lsb
            SECOND_FULLFRAME[nr + 18] = 35; //0x23
            nr = nr + 19;
            num = num + 8;
        }
        return SECOND_FULLFRAME;
    }

    public byte[] sendKennlinieSatz(){ //27 frames

        THIRD_KENNLINIE[0] = 22; //0x16
        THIRD_KENNLINIE[1] = 1;
        THIRD_KENNLINIE[2] = 1;
        THIRD_KENNLINIE[3] = 5;
        THIRD_KENNLINIE[4] = 0;
        THIRD_KENNLINIE[5] = (byte)202; //0xCA
        THIRD_KENNLINIE[6] = (byte)42; //0x2A
        THIRD_KENNLINIE[7] = 2;
        THIRD_KENNLINIE[8] = 1;
        THIRD_KENNLINIE[9] = 0;
        THIRD_KENNLINIE[10] = (byte)65; //0x41
        THIRD_KENNLINIE[11] = 1;
        THIRD_KENNLINIE[12] = 1;
        THIRD_KENNLINIE[13] = 0;
        THIRD_KENNLINIE[14] = 0;
        THIRD_KENNLINIE[15] = 0;
        THIRD_KENNLINIE[16] = 0;
        THIRD_KENNLINIE[17] = 0;
        THIRD_KENNLINIE[18] = 0;
        THIRD_KENNLINIE[19] = 0;
        THIRD_KENNLINIE[20] = 0;
        THIRD_KENNLINIE[21] = 0;
        THIRD_KENNLINIE[22] = 0;
        THIRD_KENNLINIE[23] = 0;
        THIRD_KENNLINIE[24] = 0;
        THIRD_KENNLINIE[25] = (byte)50; //0x32
        THIRD_KENNLINIE[26] = 10;
        THIRD_KENNLINIE[27] = 10;
        THIRD_KENNLINIE[28] = 0;
        THIRD_KENNLINIE[29] = (byte)50; //0x32
        THIRD_KENNLINIE[30] = 0;
        THIRD_KENNLINIE[31] = 0;
        THIRD_KENNLINIE[32] = 0;
        THIRD_KENNLINIE[33] = 0;
        THIRD_KENNLINIE[34] = 0;
        THIRD_KENNLINIE[35] = 0;
        THIRD_KENNLINIE[36] = 0;
        THIRD_KENNLINIE[37] = 1;
        THIRD_KENNLINIE[38] = (byte)100; //0x64
        THIRD_KENNLINIE[39] = (byte)130; //0x82
        THIRD_KENNLINIE[40] = (byte)100; //0x64
        THIRD_KENNLINIE[41] = (byte)100; //0x64
        THIRD_KENNLINIE[42] = (byte)30; //0x1E
        THIRD_KENNLINIE[43] = 2;
        THIRD_KENNLINIE[44] = (byte)240; //0xF0
        THIRD_KENNLINIE[45] = (byte)69; //0x45
        THIRD_KENNLINIE[46] = (byte)205; //0xCD
        THIRD_KENNLINIE[47] = 1;

        for(int b=48; b<208; b++) {
            THIRD_KENNLINIE[b] = (byte) 32; //0x20
        }

        THIRD_KENNLINIE[208] = (byte)21; //0x15
        THIRD_KENNLINIE[209] = (byte)114; //0x72
        THIRD_KENNLINIE[210] = 0;
        THIRD_KENNLINIE[211] = 0;
        THIRD_KENNLINIE[212] = 3;
        THIRD_KENNLINIE[213] = 4;

        int nr = 0;
        int num = 0;
        for (int k = 0; k < 26; k++) { //send 26 frames to the machine
            THIRD_FULLFRAME[nr] = 36; //0x24
            THIRD_FULLFRAME[nr + 1] = 19; //0x13
            THIRD_FULLFRAME[nr + 2] = 2;
            THIRD_FULLFRAME[nr + 3] = 6; //msb can id
            THIRD_FULLFRAME[nr + 4] = (byte) 224; //0xE0 lsb can id
            THIRD_FULLFRAME[nr + 5] = 8; //data length
            THIRD_FULLFRAME[nr + 6] = THIRD_KENNLINIE[num];
            THIRD_FULLFRAME[nr + 7] = THIRD_KENNLINIE[num + 1];
            THIRD_FULLFRAME[nr + 8] = THIRD_KENNLINIE[num + 2];
            THIRD_FULLFRAME[nr + 9] = THIRD_KENNLINIE[num + 3];
            THIRD_FULLFRAME[nr + 10] = THIRD_KENNLINIE[num + 4];
            THIRD_FULLFRAME[nr + 11] = THIRD_KENNLINIE[num + 5];
            THIRD_FULLFRAME[nr + 12] = THIRD_KENNLINIE[num + 6];
            THIRD_FULLFRAME[nr + 13] = THIRD_KENNLINIE[num + 7];
            THIRD_FULLFRAME[nr + 14] = 35; //0x23

            Checksum tmpchecksum3 = new CRC32();
            tmpchecksum3.update(THIRD_FULLFRAME, nr, 15);// update the current checksum with the specified array of bytes
            long crcValue3 = tmpchecksum3.getValue();// get the current checksum value //checksum is correct
            //Log.i("tempValue",String.valueOf(crcValue));
            THIRD_FULLFRAME[nr + 14] = (byte) ((crcValue3 >>> 24) & 0xFF); //msb
            THIRD_FULLFRAME[nr + 15] = (byte) ((crcValue3 >>> 16) & 0xFF);
            THIRD_FULLFRAME[nr + 16] = (byte) ((crcValue3 >>> 8) & 0xFF);
            THIRD_FULLFRAME[nr + 17] = (byte) (crcValue3 & 0xFF); //lsb
            THIRD_FULLFRAME[nr + 18] = 35; //0x23
            nr = nr + 19;
            num = num + 8;
        }
        Log.i("nr is",String.valueOf(nr));

        THIRD_FULLFRAME[493] = 36; //0x24
        THIRD_FULLFRAME[494] = 17; //0x11
        THIRD_FULLFRAME[495] = 2; //can id
        THIRD_FULLFRAME[496] = 6; //msb can id
        THIRD_FULLFRAME[497] = (byte) 224; //0xE0 lsb can id
        THIRD_FULLFRAME[498] = 6; //Data length
        THIRD_FULLFRAME[499] = (byte)21; //0x15
        THIRD_FULLFRAME[500] = (byte)114; //0x72
        THIRD_FULLFRAME[501] = 0;
        THIRD_FULLFRAME[502] = 0;
        THIRD_FULLFRAME[503] = 3;
        THIRD_FULLFRAME[504] = 4;
        THIRD_FULLFRAME[505] = 35; //0x23

        Checksum tmpchecksum4 = new CRC32();
        tmpchecksum4.update(THIRD_FULLFRAME, 493, 15);// update the current checksum with the specified array of bytes
        long crcValue4 = tmpchecksum4.getValue();// get the current checksum value //checksum is correct
        //Log.i("tempValue",String.valueOf(crcValue));
        THIRD_FULLFRAME[505] = (byte) ((crcValue4 >>> 24) & 0xFF); //msb
        THIRD_FULLFRAME[506] = (byte) ((crcValue4 >>> 16) & 0xFF);
        THIRD_FULLFRAME[507] = (byte) ((crcValue4 >>> 8) & 0xFF);
        THIRD_FULLFRAME[508] = (byte) (crcValue4 & 0xFF); //lsb
        THIRD_FULLFRAME[509] = 35; //0x23

        return  THIRD_FULLFRAME;
    }

}
