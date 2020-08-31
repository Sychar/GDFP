package com.jess.gdfp;

public class NewJob {

    private static byte[] JOB_UPDATE = new byte[350];
    private static byte[] JOB_UPDATE1 = new byte[350];
    public static byte[] FIRST_FRAME = new byte[800];
    public static byte[] SECOND_FRAME = new byte[800];

    /*public static void CreateNewJob(int JOB_NUM){
        JobUpdate.SaveTheJob();

        FIRST_FRAME[0] = 36; //0x24
        FIRST_FRAME[1] = 19; //0x13
        FIRST_FRAME[2] = 2;
        FIRST_FRAME[3] = 6; //msb can id
        FIRST_FRAME[4] = (byte) 224; //0xE0 lsb can id
        FIRST_FRAME[5] = 8; //data length
        FIRST_FRAME[6] = 22; //0x16 Header
        FIRST_FRAME[7] = 1;
        FIRST_FRAME[8] = 1;
        FIRST_FRAME[9] = 19; //0x13
        FIRST_FRAME[10] = 1; //Header data
        FIRST_FRAME[11] = 40; //0x28
        FIRST_FRAME[12] = 32; //0x20
        FIRST_FRAME[13] = 2;
        FIRST_FRAME[14] = 35; //0x23

        Checksum tempcheck = new CRC32();
        tempcheck.update(FIRST_FRAME, 0, 15);// update the current checksum with the specified array of bytes
        long crcValue = tempcheck.getValue();// get the current checksum value //checksum is correct
        //Log.i("tempValue",String.valueOf(crcValue));
        FIRST_FRAME[14] = (byte) ((crcValue >>> 24) & 0xFF); //msb
        FIRST_FRAME[15] = (byte) ((crcValue >>> 16) & 0xFF);
        FIRST_FRAME[16] = (byte) ((crcValue >>> 8) & 0xFF);
        FIRST_FRAME[17] = (byte) (crcValue & 0xFF); //lsb
        FIRST_FRAME[18] = 35; //0x23

        //int num = 8;
        int num = 1;
        int nr = 19;
        for (int k = 0; k < 36; k++) { //send 36 frames to the machine

            FIRST_FRAME[nr] = 36; //0x24
            FIRST_FRAME[nr+1] = 19; //0x13
            FIRST_FRAME[nr+2] = 2;
            FIRST_FRAME[nr+3] = 6; //msb can id
            FIRST_FRAME[nr+4] = (byte) 224; //0xE0 lsb can id
            FIRST_FRAME[nr+5] = 8; //data length
            *//*firstframe[nr+6] = DatenObjekte.JOB_FRAME[num];
            firstframe[nr+7] = DatenObjekte.JOB_FRAME[num + 1];
            firstframe[nr+8] = DatenObjekte.JOB_FRAME[num + 2];
            firstframe[nr+9] = DatenObjekte.JOB_FRAME[num + 3];
            firstframe[nr+10] = DatenObjekte.JOB_FRAME[num + 4];
            firstframe[nr+11] = DatenObjekte.JOB_FRAME[num + 5];
            firstframe[nr+12] = DatenObjekte.JOB_FRAME[num + 6];
            firstframe[nr+13] = DatenObjekte.JOB_FRAME[num + 7];*//*
            FIRST_FRAME[nr+6] = JobUpdate.SAVE_JOB[num];
            FIRST_FRAME[nr+7] = JobUpdate.SAVE_JOB[num + 1];
            FIRST_FRAME[nr+8] = JobUpdate.SAVE_JOB[num + 2];
            FIRST_FRAME[nr+9] = JobUpdate.SAVE_JOB[num + 3];
            FIRST_FRAME[nr+10] = JobUpdate.SAVE_JOB[num + 4];
            FIRST_FRAME[nr+11] = JobUpdate.SAVE_JOB[num + 5];
            FIRST_FRAME[nr+12] = JobUpdate.SAVE_JOB[num + 6];
            FIRST_FRAME[nr+13] = JobUpdate.SAVE_JOB[num + 7];
            FIRST_FRAME[nr+14] = 35; //0x23

            Checksum tempcheck1 = new CRC32();
            tempcheck1.update(FIRST_FRAME, nr, 15);// update the current checksum with the specified array of bytes
            long crcValue1 = tempcheck1.getValue();// get the current checksum value
            FIRST_FRAME[nr+14] = (byte) ((crcValue1 >>> 24) & 0xFF); //msb checksum
            FIRST_FRAME[nr+15] = (byte) ((crcValue1 >>> 16) & 0xFF);
            FIRST_FRAME[nr+16] = (byte) ((crcValue1 >>> 8) & 0xFF);
            FIRST_FRAME[nr+17] = (byte) (crcValue1 & 0xFF); //lsb
            FIRST_FRAME[nr+18] = 35; //0x23

            num = num + 8; //last num is 296
            nr = nr +19;
        }

        for(int i=0; i<294; i++){ //store data bytes
            //jobupdate[i] = DatenObjekte.JOB_FRAME[i + 8];
            JOB_UPDATE[i] = JobUpdate.SAVE_JOB[i + 1];
        }

        //calculate CRC16
        int getCRC = JobUpdate.findCRC16(JOB_UPDATE,294); //total 294 bytes of data
        FIRST_FRAME[703] = 36; //0x24
        FIRST_FRAME[704] = 19; //0x13
        FIRST_FRAME[705] = 2;
        FIRST_FRAME[706] = 6; //msb can id
        FIRST_FRAME[707] = (byte) 224; //0xE0 lsb can id
        FIRST_FRAME[708] = 8; //data length
        *//*firstframe[709] = DatenObjekte.JOB_FRAME[296];
        firstframe[710] = DatenObjekte.JOB_FRAME[297];
        firstframe[711] = DatenObjekte.JOB_FRAME[298];
        firstframe[712] = DatenObjekte.JOB_FRAME[299];
        firstframe[713] = DatenObjekte.JOB_FRAME[300];
        firstframe[714] = DatenObjekte.JOB_FRAME[301];*//*
        FIRST_FRAME[709] = SAVE_JOB[289];
        FIRST_FRAME[710] = SAVE_JOB[290];
        FIRST_FRAME[711] = SAVE_JOB[291];
        FIRST_FRAME[712] = SAVE_JOB[292];
        FIRST_FRAME[713] = SAVE_JOB[293];
        FIRST_FRAME[714] = SAVE_JOB[294];
        FIRST_FRAME[715] = (byte)(getCRC&0xFF); //LSB
        FIRST_FRAME[716] = (byte)((getCRC>>>8)&0xFF); //MSB
        FIRST_FRAME[717] = 35; //0x23

        Checksum tempcheck2 = new CRC32();
        tempcheck2.update(FIRST_FRAME, 703, 15);// update the current checksum with the specified array of bytes
        long crcValue2 = tempcheck2.getValue();// get the current checksum value
        FIRST_FRAME[717] = (byte) ((crcValue2 >>> 24) & 0xFF); //msb
        FIRST_FRAME[718] = (byte) ((crcValue2 >>> 16) & 0xFF);
        FIRST_FRAME[719] = (byte) ((crcValue2 >>> 8) & 0xFF);
        FIRST_FRAME[720] = (byte) (crcValue2 & 0xFF); //lsb
        FIRST_FRAME[721] = 35; //0x23

        FIRST_FRAME[722] = 36; //0x24
        FIRST_FRAME[723] = 19; //0x13
        firstframe[724] = 2;
        firstframe[725] = 6; // msb can id
        firstframe[726] = (byte) 224; //0xE0 lsb can id
        firstframe[727] = 8; //data length
        firstframe[728] = 0; //Footer Data
        firstframe[729] = 0;
        firstframe[730] = 3;
        firstframe[731] = 4;
        firstframe[732] = 22; //0x16 Footer
        firstframe[733] = 1;
        firstframe[734] = 1;
        firstframe[735] = 19; //0x13
        firstframe[736] = 35; //0x23

        Checksum tempcheck3 = new CRC32();
        tempcheck3.update(firstframe, 722, 15);// update the current checksum with the specified array of bytes
        long crcValue3 = tempcheck3.getValue();// get the current checksum value
        firstframe[736] = (byte) ((crcValue3 >>> 24) & 0xFF); //msb
        firstframe[737] = (byte) ((crcValue3 >>> 16) & 0xFF);
        firstframe[738] = (byte) ((crcValue3 >>> 8) & 0xFF);
        firstframe[739] = (byte) (crcValue3 & 0xFF); //lsb
        firstframe[740] = 35; //0x23

    }*/
}
