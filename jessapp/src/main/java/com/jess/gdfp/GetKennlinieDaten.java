package com.jess.gdfp;

import android.util.Log;

public class GetKennlinieDaten {

    private static byte[] KENN_FRAME = new byte[40];
    private static byte[] WHOLE_JOB_FRAME = new byte[230];

    public static byte[] readKennDaten() {
        //--------------------------First frame------------------------
        KENN_FRAME[0] = 36; //0x24
        KENN_FRAME[1] = 16; //0x10
        KENN_FRAME[2] = 2;
        KENN_FRAME[3] = 6; //0x06 msb can id
        KENN_FRAME[4] = (byte)224; //0xE0 lsb can id
        //KENN_FRAME[4] = (byte)1;
        KENN_FRAME[5] = 8; //data length
        KENN_FRAME[6] = 22; //0x16
        KENN_FRAME[7] = 1;
        KENN_FRAME[8] = 1;
        KENN_FRAME[9] = 4;
        KENN_FRAME[10] = 0;
        KENN_FRAME[11] = 3;
        KENN_FRAME[12] = (byte)215; //0xD7
        KENN_FRAME[13] = 2;
        KENN_FRAME[14] = 35; //0x23

        /**
         * Calculate the checksum of dataframe
         */

        int CHECKSUM = 0;

        for (int i = 0; i < 15; i++) {
            int tempcheck;
            if((KENN_FRAME[i])<0){
                tempcheck = 256+(KENN_FRAME[i]);
            }else{
                tempcheck = KENN_FRAME[i];
            }
            CHECKSUM = CHECKSUM + tempcheck;
        }
        //CHECKSUM = (CHECKSUM + (KENN_FRAME[22]));
        //Log.i("CHECKSUM1 ",String.valueOf(CHECKSUM));
        //Log.i("Masked Checksum ",String.valueOf(CHECKSUM & 0x000000FF));
        KENN_FRAME[14] = (byte)(CHECKSUM & 0x000000FF);
        KENN_FRAME[15] = 35; //0x23

        //-----------------------Second frame-----------------------------
        KENN_FRAME[16] = 36; //0x24
        KENN_FRAME[17] = 15; //0x0F
        KENN_FRAME[18] = 2;
        KENN_FRAME[19] = 6; //0x06 msb can id
        KENN_FRAME[20] = (byte)224; //0xE0 lsb can id
        //KENN_FRAME[20] = (byte)1;
        KENN_FRAME[21] = 7;
        KENN_FRAME[22] = 1;
        KENN_FRAME[23] = 0;
        KENN_FRAME[24] = 2;
        KENN_FRAME[25] = (byte)193; //0xC1
        KENN_FRAME[26] = (byte)161; //0xA1
        KENN_FRAME[27] = 3;
        KENN_FRAME[28] = 4;
        KENN_FRAME[29] = 35; //0x23

        /**
         * Calculate the checksum of dataframe
         */

        int CHECKSUM1 = 0;
        for (int i = 16; i < 30; i++) {
            int tempcheck;
            if((KENN_FRAME[i])<0){
                tempcheck = 256+(KENN_FRAME[i]);
            }else{
                tempcheck = KENN_FRAME[i];
            }
            CHECKSUM1 = CHECKSUM1 + tempcheck;
        }
        //CHECKSUM = (CHECKSUM1 + (KENN_FRAME[22]));
        //Log.i("CHECKSUM2 ",String.valueOf(CHECKSUM1));
        //Log.i("Masked Checksum ",String.valueOf(CHECKSUM1 & 0x000000FF));
        KENN_FRAME[29] = (byte)(CHECKSUM1& 0x000000FF);
        KENN_FRAME[30] = 35; //0x23

     return KENN_FRAME;
    }

    public static byte[] UpdateKennlinie(){
        //------------------------First frame-------------------------------------------------------
        /*WHOLE_JOB_FRAME[0] = 36; //0x24
        WHOLE_JOB_FRAME[1] = 16; //0x10
        WHOLE_JOB_FRAME[2] = 2;
        WHOLE_JOB_FRAME[3] = 6; //msb can id
        WHOLE_JOB_FRAME[4] = (byte) 224; //0xE0 lsb can id
        WHOLE_JOB_FRAME[5] = 8; //data length
        WHOLE_JOB_FRAME[6] = 22; //0x16 Header
        WHOLE_JOB_FRAME[7] = 1;
        WHOLE_JOB_FRAME[8] = 1;
        WHOLE_JOB_FRAME[9] = 5;
        WHOLE_JOB_FRAME[10] = 0;
        WHOLE_JOB_FRAME[11] = (byte)202; //0xCA
        WHOLE_JOB_FRAME[12] = 42; //0x2A
        WHOLE_JOB_FRAME[13] = 2;
        WHOLE_JOB_FRAME[14] = 35; //0x23

        *//**
         * Calculate the checksum of dataframe
         *//*
        int CHECKSUM = 0;

        for (int i = 0; i < 15 ; i++) {
            CHECKSUM = CHECKSUM + WHOLE_JOB_FRAME[i];
        }

        WHOLE_JOB_FRAME[14]=(byte)(CHECKSUM & 0xFF);
        WHOLE_JOB_FRAME[15]=35; //Footer 0x23*/
        //------------------First frame to 26th frame----------------------------------------------
        /*int num = 0;
        int nr = 0;
        int CHECKSUM1;
        for (int k = 0; k < 6; k++) { //send 6 frames to the machine

            WHOLE_JOB_FRAME[nr] = 36; //0x24
            WHOLE_JOB_FRAME[nr+1] = 16; //0x10
            WHOLE_JOB_FRAME[nr+2] = 2;
            WHOLE_JOB_FRAME[nr+3] = 6; //msb can id
            WHOLE_JOB_FRAME[nr+4] = (byte) 224; //0xE0 lsb can id
            WHOLE_JOB_FRAME[nr+5] = 8; //data length
            WHOLE_JOB_FRAME[nr+6] = DatenObjekte.JOB_FRAME[num];
            WHOLE_JOB_FRAME[nr+7] = DatenObjekte.JOB_FRAME[num + 1];
            WHOLE_JOB_FRAME[nr+8] = DatenObjekte.JOB_FRAME[num + 2];
            WHOLE_JOB_FRAME[nr+9] = DatenObjekte.JOB_FRAME[num + 3];
            if(k==0) WHOLE_JOB_FRAME[nr+9] = (byte)MainActivity.VERFAHREN;
            WHOLE_JOB_FRAME[nr+10] = DatenObjekte.JOB_FRAME[num + 4];
            if(k==0) WHOLE_JOB_FRAME[nr+10] = (byte)MainActivity.DRAHTDURCHMESSER;
            WHOLE_JOB_FRAME[nr+11] = DatenObjekte.JOB_FRAME[num + 5];
            if(k==0) WHOLE_JOB_FRAME[nr+11] = (byte) MainActivity.GAS;
            WHOLE_JOB_FRAME[nr+12] = DatenObjekte.JOB_FRAME[num + 6];
            if(k==0) WHOLE_JOB_FRAME[nr+12] = (byte) MainActivity.WERKSTOFF;
            WHOLE_JOB_FRAME[nr+13] = DatenObjekte.JOB_FRAME[num + 7];
            WHOLE_JOB_FRAME[nr+14] = 35; //0x23

            *//**
             * Calculate the checksum of dataframe
             *//*
            CHECKSUM1 = 0;

            for (int i = nr; i < nr+15 ; i++) {
                CHECKSUM1 = CHECKSUM1 + WHOLE_JOB_FRAME[i];
            }

            WHOLE_JOB_FRAME[nr+14]=(byte)(CHECKSUM1 & 0xFF);
            WHOLE_JOB_FRAME[nr+15]=35; //Footer 0x23

            num = num + 8; //last num is 296
            nr = nr +16;
        }*/

        //-------------------------Frames with all data 0x32-------------------------
        /*int count = 96;
        for (int k = 0; k < 20; k++) { //send 20 frames to the machine

            WHOLE_JOB_FRAME[count] = 36; //0x24
            WHOLE_JOB_FRAME[count+1] = 16; //0x10
            WHOLE_JOB_FRAME[count+2] = 2;
            WHOLE_JOB_FRAME[count+3] = 6; //msb can id
            WHOLE_JOB_FRAME[count+4] = (byte) 224; //0xE0 lsb can id
            WHOLE_JOB_FRAME[count+5] = 8; //data length
            WHOLE_JOB_FRAME[count+6] = 32; //0x20
            WHOLE_JOB_FRAME[count+7] = 32;
            WHOLE_JOB_FRAME[count+8] = 32;
            WHOLE_JOB_FRAME[count+9] = 32;
            WHOLE_JOB_FRAME[count+10] = 32;
            WHOLE_JOB_FRAME[count+11] = 32;
            WHOLE_JOB_FRAME[count+12] = 32;
            WHOLE_JOB_FRAME[count+13] = 32;
            WHOLE_JOB_FRAME[count+14] = 35; //0x23

            *//**
             * Calculate the checksum of dataframe
             *//*
            CHECKSUM1 = 0;

            for (int i = count; i < count+15 ; i++) {
                CHECKSUM1 = CHECKSUM1 + WHOLE_JOB_FRAME[i];
            }

            WHOLE_JOB_FRAME[count+14]=(byte)(CHECKSUM1 & 0xFF);
            WHOLE_JOB_FRAME[count+15]=35; //Footer 0x23

            count = count +16;
        }*/

        /*DatenObjekte.JOB_FRAME[11] = (byte) MainActivity.VERFAHREN;
        DatenObjekte.JOB_FRAME[12] = (byte) MainActivity.DRAHTDURCHMESSER;
        DatenObjekte.JOB_FRAME[13] = (byte) MainActivity.GAS;
        DatenObjekte.JOB_FRAME[14] = (byte) MainActivity.WERKSTOFF;*/

        //------------------------------Last frame-------------------------------------
        /**
         * Find CRC16 @data pos 6 and 7
         */

        /*WHOLE_JOB_FRAME[416] = 36; //0x24
        WHOLE_JOB_FRAME[417] = 14; //0x0E
        WHOLE_JOB_FRAME[418] = 2;
        WHOLE_JOB_FRAME[419] = 6; //msb can id
        WHOLE_JOB_FRAME[420] = (byte) 224; //0xE0 lsb can id
        WHOLE_JOB_FRAME[421] = 6; //data length
        WHOLE_JOB_FRAME[422] = 1; //CRC 16 msb
        WHOLE_JOB_FRAME[423] = 1; //CRC 16 lsb
        WHOLE_JOB_FRAME[424] = 0;
        WHOLE_JOB_FRAME[425] = 0;
        WHOLE_JOB_FRAME[426] = 3;
        WHOLE_JOB_FRAME[427] = 4;
        WHOLE_JOB_FRAME[428] = 35; //0x23*/

        /**
         * Calculate the checksum of dataframe
         */
        /*CHECKSUM1 = 0;

        for (int i = 416; i < 429 ; i++) {
            CHECKSUM1 = CHECKSUM1 + WHOLE_JOB_FRAME[i];
        }

        WHOLE_JOB_FRAME[428] = (byte)(CHECKSUM1 & 0xFF);
        WHOLE_JOB_FRAME[429] = 35; //Footer 0x23*/

        /**
         * Only one frame which is 222 bytes
         */
        WHOLE_JOB_FRAME[0] = 36; //0x24
        WHOLE_JOB_FRAME[1] = (byte)222; //0xE0 frame length
        WHOLE_JOB_FRAME[2] = 2;
        WHOLE_JOB_FRAME[3] = 6; //msb can id
        WHOLE_JOB_FRAME[4] = (byte) 224; //0xE0 lsb can id
        WHOLE_JOB_FRAME[5] = (byte)214; //data length 0xD6
        System.arraycopy(DatenObjekte.JOB_FRAME,0,WHOLE_JOB_FRAME,6,214);
        WHOLE_JOB_FRAME[17] = (byte)MainActivity.VERFAHREN;
        WHOLE_JOB_FRAME[18] = (byte)MainActivity.DRAHTDURCHMESSER;
        WHOLE_JOB_FRAME[19] = (byte)MainActivity.GAS;
        WHOLE_JOB_FRAME[20] = (byte)MainActivity.WERKSTOFF;
        WHOLE_JOB_FRAME[220] = 35; //0x23
        /**
         * Calculate the checksum of dataframe
         */
        int MYCHECKSUM = 0;

        for (int i = 0; i < 221; i++) {
            int tempcheck;
            if((WHOLE_JOB_FRAME[i])<0){
                tempcheck = 256+(WHOLE_JOB_FRAME[i]);
            }else{
                tempcheck = WHOLE_JOB_FRAME[i];
            }
            MYCHECKSUM = MYCHECKSUM + tempcheck;
        }

        WHOLE_JOB_FRAME[220] = (byte)(MYCHECKSUM & 0x000000FF);
        WHOLE_JOB_FRAME[221] = 35; //Footer 0x23
        //for(int i=6;i<222;i++) WHOLE_JOB_FRAME[i] = DatenObjekte.JOB_FRAME[i-6];

        return WHOLE_JOB_FRAME;
    }
}
