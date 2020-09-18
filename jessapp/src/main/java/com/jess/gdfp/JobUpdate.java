package com.jess.gdfp;

import java.util.zip.CRC32;
import java.util.zip.Checksum;

public class JobUpdate {

private static byte[] jobupdate = new byte[350];
private static byte[] jobupdate1 = new byte[350];
public static byte[] firstframe = new byte[800];
public static byte[] secondframe = new byte[800];
public static byte[] SAVE_JOB = new byte[800];
private static String temp = "";

    public static void SaveTheJob(){
        SAVE_JOB[1] = (byte)(DatenObjekte.Jobnummer&0xFF);//lsb
        SAVE_JOB[2] = (byte)(DatenObjekte.Jobnummer>>>8 & 0xFF);//msb
        SAVE_JOB[3] = DatenObjekte.KENNLINIE_FRAME[10]; //unknown
        SAVE_JOB[4] = (byte)(DatenObjekte.UserNummer&0xFF);
        SAVE_JOB[5] = (byte)(DatenObjekte.Kennliniennummer&0xFF);//lsb
        SAVE_JOB[6] = (byte)(DatenObjekte.Kennliniennummer>>>8 & 0xFF);//msb
        SAVE_JOB[7] = (byte)(DatenObjekte.KennlinienTyp&0xFF);
        SAVE_JOB[8] = (byte)(DatenObjekte.SV1pos1 & 0xFF);//Verfahren
        SAVE_JOB[9] = (byte)(DatenObjekte.SV1pos2 & 0xFF);//Betriebsart
        SAVE_JOB[10] = (byte)(DatenObjekte.SV1pos3 & 0xFF);//Drahtdurchmesser
        SAVE_JOB[11] = (byte)(DatenObjekte.SV1pos4 & 0xFF);//Gas
        SAVE_JOB[12] = (byte)(DatenObjekte.SV1pos5 & 0xFF);//Werkstoff
        SAVE_JOB[13] = (byte)(DatenObjekte.SV1pos6 & 0xFF);//Reglertyp
        SAVE_JOB[14] = (byte)(DatenObjekte.Gasvorströmen & 0xFF);
        SAVE_JOB[15] = (byte)(DatenObjekte.Gasnachströmen & 0xFF);
        SAVE_JOB[16] = (byte)(DatenObjekte.EinschleichenAbsolut & 0xFF);
        SAVE_JOB[17] = (byte)(DatenObjekte.EinschleichenKorrektur & 0xFF);
        SAVE_JOB[18] = (byte)(DatenObjekte.UpSlope & 0xFF);
        SAVE_JOB[19] = (byte)(DatenObjekte.DownSlope & 0xFF);
        SAVE_JOB[20] = (byte)(DatenObjekte.Zündenergie & 0xFF);
        SAVE_JOB[21] = (byte)(DatenObjekte.Endkraterenergie & 0xFF);
        SAVE_JOB[22] = (byte)(DatenObjekte.Freibrand & 0xFF);
        SAVE_JOB[23] = (byte)(DatenObjekte.FreibandKorrektur & 0xFF);
        SAVE_JOB[24] = (byte)(DatenObjekte.SynergieVorgabe & 0xFF);
        SAVE_JOB[25] = (byte)(DatenObjekte.KorrekturPulsamplitude & 0xFF);
        SAVE_JOB[26] = (byte)(DatenObjekte.KorrekturDrossel & 0xFF);
        SAVE_JOB[27] = (byte)(DatenObjekte.PausenZeit & 0xFF);//lsb
        SAVE_JOB[28] = (byte)(DatenObjekte.PausenZeit>>>8 & 0xFF);//msb
        SAVE_JOB[29] = (byte)(DatenObjekte.Punktzeit & 0xFF);//lsb
        SAVE_JOB[30] = (byte)(DatenObjekte.Punktzeit>>>8 & 0xFF);//msb
        SAVE_JOB[31] = (byte)(DatenObjekte.Einfädeln & 0xFF);
        SAVE_JOB[32] = (byte)(DatenObjekte.ZündDauer & 0xFF);
        SAVE_JOB[33] = (byte)(DatenObjekte.EndkraterDauer & 0xFF);
        SAVE_JOB[34] = (byte)(DatenObjekte.PowerpulsEinAus & 0xFF);
        SAVE_JOB[35] = (byte)(DatenObjekte.PowerpulsE2 & 0xFF);
        SAVE_JOB[36] = (byte)(DatenObjekte.PowerpulsT1E1 & 0xFF);
        SAVE_JOB[37] = (byte)(DatenObjekte.PowerpulsT2E1 & 0xFF);
        SAVE_JOB[38] = (byte)(DatenObjekte.PowerpulsLBKorrE2 & 0xFF);
        SAVE_JOB[39] = (byte)(DatenObjekte.ZündStrom & 0xFF);//lsb
        SAVE_JOB[40] = (byte)(DatenObjekte.ZündStrom>>>8 & 0xFF);//msb
        SAVE_JOB[41] = (byte)(DatenObjekte.ZündSpannung & 0xFF);//lsb
        SAVE_JOB[42] = (byte)(DatenObjekte.ZündSpannung>>>8 & 0xFF);//msb
        SAVE_JOB[43] = (byte)(DatenObjekte.ZündEnergie & 0xFF);//lsb
        SAVE_JOB[44] = (byte)(DatenObjekte.ZündEnergie>>>8 & 0xFF);//msb
        SAVE_JOB[45] = (byte)(DatenObjekte.ZündDrossel & 0xFF);
        SAVE_JOB[46] = (byte)(DatenObjekte.ZündLichtbogenkorrektur & 0xFF);
        SAVE_JOB[47] = (byte)(DatenObjekte.Strom1 & 0xFF);//lsb
        SAVE_JOB[48] = (byte)(DatenObjekte.Strom1>>>8 & 0xFF);//msb
        SAVE_JOB[49] = (byte)(DatenObjekte.Spannung1 & 0xFF);//lsb
        SAVE_JOB[50] = (byte)(DatenObjekte.Spannung1>>>8 & 0xFF);//msb
        SAVE_JOB[51] = (byte)(DatenObjekte.Energie1 & 0xFF);//lsb
        SAVE_JOB[52] = (byte)(DatenObjekte.Energie1>>>8 & 0xFF);//msb
        SAVE_JOB[53] = (byte)(DatenObjekte.Drossel1 & 0xFF);
        SAVE_JOB[54] = (byte)(DatenObjekte.Lichtbogenkorrektur1 & 0xFF);
        SAVE_JOB[55] = (byte)(DatenObjekte.Strom2 & 0xFF);//lsb
        SAVE_JOB[56] = (byte)(DatenObjekte.Strom2>>>8 & 0xFF);//msb
        SAVE_JOB[57] = (byte)(DatenObjekte.Spannung2 & 0xFF);//lsb
        SAVE_JOB[58] = (byte)(DatenObjekte.Spannung2>>>8 & 0xFF);//msb
        SAVE_JOB[59] = (byte)(DatenObjekte.Energie2 & 0xFF);//lsb
        SAVE_JOB[60] = (byte)(DatenObjekte.Energie2>>>8 & 0xFF);//msb
        SAVE_JOB[61] = (byte)(DatenObjekte.Drossel2 & 0xFF);
        SAVE_JOB[62] = (byte)(DatenObjekte.Lichtbogenkorrektur2 & 0xFF);
        SAVE_JOB[63] = (byte)(DatenObjekte.Strom3 & 0xFF);//lsb
        SAVE_JOB[64] = (byte)(DatenObjekte.Strom3>>>8 & 0xFF);//msb
        SAVE_JOB[65] = (byte)(DatenObjekte.Spannung3 & 0xFF);//lsb
        SAVE_JOB[66] = (byte)(DatenObjekte.Spannung3>>>8 & 0xFF);//msb
        SAVE_JOB[67] = (byte)(DatenObjekte.Energie3 & 0xFF);//lsb
        SAVE_JOB[68] = (byte)(DatenObjekte.Energie3>>>8 & 0xFF);//msb
        SAVE_JOB[69] = (byte)(DatenObjekte.Drossel3 & 0xFF);
        SAVE_JOB[70] = (byte)(DatenObjekte.Lichtbogenkorrektur3 & 0xFF);
        SAVE_JOB[71] = (byte)(DatenObjekte.EndkraterStrom & 0xFF);//lsb
        SAVE_JOB[72] = (byte)(DatenObjekte.EndkraterStrom>>>8 & 0xFF);//msb
        SAVE_JOB[73] = (byte)(DatenObjekte.EndkraterSpannung & 0xFF);//lsb
        SAVE_JOB[74] = (byte)(DatenObjekte.EndkraterSpannung>>>8 & 0xFF);//msb
        SAVE_JOB[75] = (byte)(DatenObjekte.EndkraterEnergie & 0xFF);//lsb
        SAVE_JOB[76] = (byte)(DatenObjekte.EndkraterEnergie>>>8 & 0xFF);//msb
        SAVE_JOB[77] = (byte)(DatenObjekte.EndkraterDrossel & 0xFF);
        SAVE_JOB[78] = (byte)(DatenObjekte.EndKraterLichtbogenkorrektur & 0xFF);
        SAVE_JOB[79] = (byte)(DatenObjekte.VorschubSetwert & 0xFF);//lsb
        SAVE_JOB[80] = (byte)(DatenObjekte.VorschubSetwert>>>8 & 0xFF);//msb
        SAVE_JOB[81] = (byte)(DatenObjekte.StromSetwert & 0xFF);//lsb
        SAVE_JOB[82] = (byte)(DatenObjekte.StromSetwert>>>8 & 0xFF);//msb
        SAVE_JOB[83] = (byte)(DatenObjekte.SpannungSetwert & 0xFF);//lsb
        SAVE_JOB[84] = (byte)(DatenObjekte.SpannungSetwert>>>8 & 0xFF);//msb
        SAVE_JOB[85] = (byte)(DatenObjekte.BlechdickeSetwert & 0xFF);//lsb
        SAVE_JOB[86] = (byte)(DatenObjekte.BlechdickeSetwert>>>8 & 0xFF);//msb
        SAVE_JOB[87] = (byte)(DatenObjekte.ElektrodeStromSetwert & 0xFF);//lsb
        SAVE_JOB[88] = (byte)(DatenObjekte.ElektrodeStromSetwert>>>8 & 0xFF);//msb
        SAVE_JOB[89] = (byte)(DatenObjekte.HotstartDauer & 0xFF);
        SAVE_JOB[90] = (byte)(DatenObjekte.Hotstart & 0xFF);
        SAVE_JOB[91] = (byte)(DatenObjekte.ArcForce & 0xFF);
        SAVE_JOB[92] = (byte)(DatenObjekte.InnenwiderstandfürElektrode & 0xFF);
        SAVE_JOB[93] =  (byte)(DatenObjekte.KENNLINIE_FRAME[10]&0xFF); //unknown
        SAVE_JOB[94] =  (byte)(DatenObjekte.KENNLINIE_FRAME[10]>>>8 & 0xFF); //unknown
        SAVE_JOB[95] =  (byte)(DatenObjekte.KENNLINIE_FRAME[10]&0xFF); //unknown
        SAVE_JOB[96] =  (byte)(DatenObjekte.KENNLINIE_FRAME[10]>>>8 & 0xFF); //unknown
        SAVE_JOB[97] = (byte)(DatenObjekte.StartAmplitude & 0xFF);//lsb
        SAVE_JOB[98] = (byte)(DatenObjekte.StartAmplitude>>>8 & 0xFF);//msb
        SAVE_JOB[99] = (byte)(DatenObjekte.StartZeit & 0xFF);
        SAVE_JOB[100] = (byte)(DatenObjekte.StartÜberhöhung & 0xFF);
        SAVE_JOB[101] = (byte)(DatenObjekte.InnenwiderstandfürDossel & 0xFF);
        SAVE_JOB[102] = (byte)(DatenObjekte.Überblendzeit & 0xFF);
        SAVE_JOB[103] = (byte)(DatenObjekte.DrosselAbfall & 0xFF);
        SAVE_JOB[104] = (byte)(DatenObjekte.MotorFlanke & 0xFF);
        SAVE_JOB[105] = (byte)(DatenObjekte.DrosselDynamic & 0xFF);
        SAVE_JOB[106] = (byte)(DatenObjekte.LBRMode & 0xFF);
        SAVE_JOB[107] = (byte)(DatenObjekte.MAGACBetriebsart & 0xFF);
        SAVE_JOB[108] = (byte)(DatenObjekte.MAGACPositiveZeit & 0xFF);
        SAVE_JOB[109] = (byte)(DatenObjekte.MAGACNegativZeit & 0xFF);//lsb
        SAVE_JOB[110] = (byte)(DatenObjekte.MAGACNegativZeit>>>8 & 0xFF);//msb
        SAVE_JOB[111] = (byte)(DatenObjekte.MAGACKurzschlusserkennung & 0xFF);
        SAVE_JOB[112] = (byte)(DatenObjekte.MAGACKurzschlussaufhebung & 0xFF);
        SAVE_JOB[113] = (byte)(DatenObjekte.MACAGVerweilzeitPosNeg & 0xFF);
        SAVE_JOB[114] = (byte)(DatenObjekte.MACAGVerweilzeitNegPos & 0xFF);
        SAVE_JOB[115] = (byte)(DatenObjekte.MAGACKältewert & 0xFF);
        SAVE_JOB[116] = (byte)(DatenObjekte.MAGACStromschwellwert & 0xFF);
        SAVE_JOB[117] = (byte)(DatenObjekte.KENNLINIE_FRAME[10]&0xFF);//unknown
        SAVE_JOB[118] = (byte)(DatenObjekte.KENNLINIE_FRAME[10]>>>8 & 0xFF);//unknown
        SAVE_JOB[119] = (byte)(DatenObjekte.KENNLINIE_FRAME[126] & 0xFF); //unknown
        SAVE_JOB[120] = (byte)(DatenObjekte.KENNLINIE_FRAME[127] & 0xFF); //unknown
        SAVE_JOB[121] = (byte)(DatenObjekte.KENNLINIE_FRAME[128] & 0xFF); //unknown
        SAVE_JOB[122] = (byte)(DatenObjekte.KENNLINIE_FRAME[129] & 0xFF); //unknown
        SAVE_JOB[123] = (byte)(DatenObjekte.KENNLINIE_FRAME[130] & 0xFF); //unknown
        SAVE_JOB[124] = (byte)(DatenObjekte.KENNLINIE_FRAME[131] & 0xFF); //unknown
        SAVE_JOB[125] = (byte)(DatenObjekte.KENNLINIE_FRAME[132] & 0xFF); //unknown
        SAVE_JOB[126] = (byte)(DatenObjekte.KENNLINIE_FRAME[133] & 0xFF); //unknown
        SAVE_JOB[127] = (byte)(DatenObjekte.KENNLINIE_FRAME[134] & 0xFF); //unknown
        SAVE_JOB[128] = (byte)(DatenObjekte.KENNLINIE_FRAME[135] & 0xFF); //unknown
        SAVE_JOB[129] = (byte)(DatenObjekte.KENNLINIE_FRAME[136] & 0xFF); //unknown
        SAVE_JOB[130] = (byte)(DatenObjekte.KENNLINIE_FRAME[137] & 0xFF); //unknown

        for(int i=131;i<295;i++){
            SAVE_JOB[i] = (byte)32; //0x20
        }

    }

    public static int findCRC16(byte[] buf, int len){
        int crc = 0xFFFF;

        for (int pos = 0; pos < len; pos++) {
            crc ^= (int)buf[pos] & 0xFF;   // XOR byte into least sig. byte of crc

            for (int i = 8; i != 0; i--) {    // Loop over each bit
                if ((crc & 0x0001) != 0) {      // If the LSB is set
                    crc >>= 1;                    // Shift right and XOR 0xA001
                    crc ^= 0xA001;
                }
                else                            // Else LSB is not set
                    crc >>= 1;                    // Just shift right
            }
        }
        // Note, this number has low and high bytes swapped, so use it accordingly (or swap bytes)
        return crc;
    }

    public static void firstJobSend(int JOB_NUM){

        firstframe[0] = 36; //0x24
        firstframe[1] = 19; //0x13
        firstframe[2] = 2;
        firstframe[3] = 6; //msb can id
        firstframe[4] = (byte) 224; //0xE0 lsb can id
        firstframe[5] = 8; //data length
        firstframe[6] = 22; //0x16 Header
        firstframe[7] = 1;
        firstframe[8] = 1;
        firstframe[9] = 19; //0x13
        firstframe[10] = 1; //Header data
        firstframe[11] = 40; //0x28
        firstframe[12] = 32; //0x20
        firstframe[13] = 2;
        firstframe[14] = 35; //0x23

        Checksum tempcheck = new CRC32();
        tempcheck.update(firstframe, 0, 15);// update the current checksum with the specified array of bytes
        long crcValue = tempcheck.getValue();// get the current checksum value //checksum is correct
        //Log.i("tempValue",String.valueOf(crcValue));
        firstframe[14] = (byte) ((crcValue >>> 24) & 0xFF); //msb
        firstframe[15] = (byte) ((crcValue >>> 16) & 0xFF);
        firstframe[16] = (byte) ((crcValue >>> 8) & 0xFF);
        firstframe[17] = (byte) (crcValue & 0xFF); //lsb
        firstframe[18] = 35; //0x23

        //int num = 8;
        int num = 1;
        int nr = 19;
        for (int k = 0; k < 36; k++) { //send 36 frames to the machine

            firstframe[nr] = 36; //0x24
            firstframe[nr+1] = 19; //0x13
            firstframe[nr+2] = 2;
            firstframe[nr+3] = 6; //msb can id
            firstframe[nr+4] = (byte) 224; //0xE0 lsb can id
            firstframe[nr+5] = 8; //data length
            /*firstframe[nr+6] = DatenObjekte.KENN_FRAME[num];
            firstframe[nr+7] = DatenObjekte.KENN_FRAME[num + 1];
            firstframe[nr+8] = DatenObjekte.KENN_FRAME[num + 2];
            firstframe[nr+9] = DatenObjekte.KENN_FRAME[num + 3];
            firstframe[nr+10] = DatenObjekte.KENN_FRAME[num + 4];
            firstframe[nr+11] = DatenObjekte.KENN_FRAME[num + 5];
            firstframe[nr+12] = DatenObjekte.KENN_FRAME[num + 6];
            firstframe[nr+13] = DatenObjekte.KENN_FRAME[num + 7];*/
            firstframe[nr+6] = SAVE_JOB[num];
            if(k==0) firstframe[nr+6] = (byte)(JOB_NUM & 0xFF);
            firstframe[nr+7] = SAVE_JOB[num + 1];
            if(k==0) firstframe[nr+7] = (byte)(JOB_NUM>>>8 & 0xFF);
            firstframe[nr+8] = SAVE_JOB[num + 2];
            if(k==0) firstframe[nr+8] = 1;
            firstframe[nr+9] = SAVE_JOB[num + 3];
            firstframe[nr+10] = SAVE_JOB[num + 4];
            firstframe[nr+11] = SAVE_JOB[num + 5];
            firstframe[nr+12] = SAVE_JOB[num + 6];
            firstframe[nr+13] = SAVE_JOB[num + 7];
            firstframe[nr+14] = 35; //0x23

            Checksum tempcheck1 = new CRC32();
            tempcheck1.update(firstframe, nr, 15);// update the current checksum with the specified array of bytes
            long crcValue1 = tempcheck1.getValue();// get the current checksum value
            firstframe[nr+14] = (byte) ((crcValue1 >>> 24) & 0xFF); //msb checksum
            firstframe[nr+15] = (byte) ((crcValue1 >>> 16) & 0xFF);
            firstframe[nr+16] = (byte) ((crcValue1 >>> 8) & 0xFF);
            firstframe[nr+17] = (byte) (crcValue1 & 0xFF); //lsb
            firstframe[nr+18] = 35; //0x23

            num = num + 8; //last num is 296
            nr = nr +19;
            }

        for(int i=0; i<294; i++){ //store data bytes
            //jobupdate[i] = DatenObjekte.KENN_FRAME[i + 8];
            jobupdate[i] = SAVE_JOB[i + 1];
        }

        //calculate CRC16
        int getCRC = findCRC16(jobupdate,294); //total 294 bytes of data
        firstframe[703] = 36; //0x24
        firstframe[704] = 19; //0x13
        firstframe[705] = 2;
        firstframe[706] = 6; //msb can id
        firstframe[707] = (byte) 224; //0xE0 lsb can id
        firstframe[708] = 8; //data length
        /*firstframe[709] = DatenObjekte.KENN_FRAME[296];
        firstframe[710] = DatenObjekte.KENN_FRAME[297];
        firstframe[711] = DatenObjekte.KENN_FRAME[298];
        firstframe[712] = DatenObjekte.KENN_FRAME[299];
        firstframe[713] = DatenObjekte.KENN_FRAME[300];
        firstframe[714] = DatenObjekte.KENN_FRAME[301];*/
        firstframe[709] = SAVE_JOB[289];
        firstframe[710] = SAVE_JOB[290];
        firstframe[711] = SAVE_JOB[291];
        firstframe[712] = SAVE_JOB[292];
        firstframe[713] = SAVE_JOB[293];
        firstframe[714] = SAVE_JOB[294];
        firstframe[715] = (byte)(getCRC&0xFF); //LSB
        firstframe[716] = (byte)((getCRC>>>8)&0xFF); //MSB
        firstframe[717] = 35; //0x23

        Checksum tempcheck2 = new CRC32();
        tempcheck2.update(firstframe, 703, 15);// update the current checksum with the specified array of bytes
        long crcValue2 = tempcheck2.getValue();// get the current checksum value
        firstframe[717] = (byte) ((crcValue2 >>> 24) & 0xFF); //msb
        firstframe[718] = (byte) ((crcValue2 >>> 16) & 0xFF);
        firstframe[719] = (byte) ((crcValue2 >>> 8) & 0xFF);
        firstframe[720] = (byte) (crcValue2 & 0xFF); //lsb
        firstframe[721] = 35; //0x23

        firstframe[722] = 36; //0x24
        firstframe[723] = 19; //0x13
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
    }

    public static void secondJobSend(int JOB_NUM){

        secondframe[0] = 36; //0x24
        secondframe[1] = 19; //0x13
        secondframe[2] = 2;
        secondframe[3] = 6; //msb can id
        secondframe[4] = (byte) 224; //0xf0 lsb can id
        secondframe[5] = 8; //data length
        secondframe[6] = 1; //Header data
        secondframe[7] = 40; //0x28
        secondframe[8] = 32; //0x20
        secondframe[9] = 2;
        /*secondframe[10] = DatenObjekte.KENN_FRAME[8];
        secondframe[11] = DatenObjekte.KENN_FRAME[9];
        secondframe[12] = DatenObjekte.KENN_FRAME[10];
        secondframe[13] = DatenObjekte.KENN_FRAME[11];*/
        //secondframe[10] = SAVE_JOB[1];
        secondframe[10] = (byte)(JOB_NUM & 0xFF);
        //secondframe[11] = SAVE_JOB[2];
        secondframe[11] = (byte)(JOB_NUM>>>8 &0xFF);
        secondframe[12] = SAVE_JOB[3];
        secondframe[13] = SAVE_JOB[4];
        secondframe[14] = 35; //0x23

        Checksum tempcheck = new CRC32();
        tempcheck.update(secondframe, 0, 15);// update the current checksum with the specified array of bytes
        long crcValue = tempcheck.getValue();// get the current checksum value //checksum is correct
        //Log.i("tempValue",String.valueOf(tempValue));
        secondframe[14] = (byte) ((crcValue >>> 24) & 0xFF); //msb
        secondframe[15] = (byte) ((crcValue >>> 16) & 0xFF);
        secondframe[16] = (byte) ((crcValue >>> 8) & 0xFF);
        secondframe[17] = (byte) (crcValue & 0xFF); //lsb
        secondframe[18] = 35; //0x23

        //int num = 12;
        int num = 5;
        int increment = 19;
        for (int k = 0; k < 36; k++) { //send 36 frames to the machine

            secondframe[increment] = 36; //0x24
            secondframe[increment+1] = 19; //0x13
            secondframe[increment+2] = 2;
            secondframe[increment+3] = 6; //msb can id
            secondframe[increment+4] = (byte) 224; //0xE0 lsb can id
            secondframe[increment+5] = 8; //data length
            /*secondframe[increment+6] = DatenObjekte.KENN_FRAME[num];
            secondframe[increment+7] = DatenObjekte.KENN_FRAME[num + 1];
            secondframe[increment+8] = DatenObjekte.KENN_FRAME[num + 2];
            secondframe[increment+9] = DatenObjekte.KENN_FRAME[num + 3];
            secondframe[increment+10] = DatenObjekte.KENN_FRAME[num + 4];
            secondframe[increment+11] = DatenObjekte.KENN_FRAME[num + 5];
            secondframe[increment+12] = DatenObjekte.KENN_FRAME[num + 6];
            secondframe[increment+13] = DatenObjekte.KENN_FRAME[num + 7];*/
            secondframe[increment+6] = SAVE_JOB[num];
            secondframe[increment+7] = SAVE_JOB[num + 1];
            secondframe[increment+8] = SAVE_JOB[num + 2];
            secondframe[increment+9] = SAVE_JOB[num + 3];
            secondframe[increment+10] = SAVE_JOB[num + 4];
            secondframe[increment+11] = SAVE_JOB[num + 5];
            secondframe[increment+12] = SAVE_JOB[num + 6];
            secondframe[increment+13] = SAVE_JOB[num + 7];
            secondframe[increment+14] = 35; //0x23

            Checksum tempcheck1 = new CRC32();
            tempcheck1.update(secondframe, increment, 15);// update the current checksum with the specified array of bytes
            long crcValue1 = tempcheck1.getValue();// get the current checksum value
            //Log.i("tempValue1", String.valueOf(tempValue1));
            secondframe[increment+14] = (byte) ((crcValue1 >>> 24) & 0xFF); //msb checksum
            secondframe[increment+15] = (byte) ((crcValue1 >>> 16) & 0xFF);
            secondframe[increment+16] = (byte) ((crcValue1 >>> 8) & 0xFF);
            secondframe[increment+17] = (byte) (crcValue1 & 0xFF); //lsb
            secondframe[increment+18] = 35; //0x23

            num = num + 8; //last num is 296
            increment = increment + 19;
        }

        for(int i=0; i<294; i++){ //store data bytes
            //jobupdate1[i] = DatenObjekte.KENN_FRAME[i + 8];
            jobupdate1[i] = SAVE_JOB[i + 1];
        }

        //calculate CRC16
        int getCRC1 = findCRC16(jobupdate1,294); //total 294 bytes of data
        //Log.i("checksum1",String.valueOf(getCRC)); //correct checksum
        secondframe[703] = 36; //0x24
        secondframe[704] = 19; //0x13
        secondframe[705] = 2;
        secondframe[706] = 6; //msb can id
        secondframe[707] = (byte) 224; //0xE0 lsb can id
        secondframe[708] = 8; //data length
        /*secondframe[709] = DatenObjekte.KENN_FRAME[300];
        secondframe[710] = DatenObjekte.KENN_FRAME[301];*/
        secondframe[709] = SAVE_JOB[293];
        secondframe[710] = SAVE_JOB[294];
        secondframe[711] = (byte)(getCRC1&0xFF); //LSB
        secondframe[712] = (byte)((getCRC1>>>8)&0xFF); //MSB
        secondframe[713] = 0;
        secondframe[714] = 0;
        secondframe[715] = 3;
        secondframe[716] = 4;
        secondframe[717] = 35; //0x23

        Checksum tempcheck2 = new CRC32();
        tempcheck2.update(secondframe, 703, 15);// update the current checksum with the specified array of bytes
        long crcValue2 = tempcheck2.getValue();// get the current checksum value
        //Log.i("tempValue2", String.valueOf(crcValue2)); //correct checksum
        secondframe[717] = (byte) ((crcValue2 >>> 24) & 0xFF); //msb
        secondframe[718] = (byte) ((crcValue2 >>> 16) & 0xFF);
        secondframe[719] = (byte) ((crcValue2 >>> 8) & 0xFF);
        secondframe[720] = (byte) (crcValue2 & 0xFF); //lsb
        secondframe[721] = 35; //0x23
    }
}
