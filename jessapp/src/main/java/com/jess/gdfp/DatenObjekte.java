package com.jess.gdfp;

import android.util.Log;

import java.io.IOException;
import java.nio.charset.Charset;

import static com.jess.gdfp.MainActivity.PARSE_TOKEN;
import static com.jess.gdfp.UartService.mOutputStream;

public class DatenObjekte {

    private final static String TAG = DatenObjekte.class.getSimpleName(); //name of this class
    private static String HexData = "";
    private HeartBeat mHeartBeat;
    public static byte[] DO_FRAME = new byte[250];
    //private static String strCanId = "";
    //private static byte[] check1;
    private static byte[] str1;
    public static String gethex = "";
    private static int countertoken = 0;
    public static int jobtoken = 0;
    public static int checktoken = 0;
    private static int JOB_COUNTER = 0;
    public static byte[] KENNLINIE_FRAME = new byte[350];
    public static int y = 0;
    public static int HFound = 0;
    private static int HeaderFound1 = 0;
    private static int LengthFound1 = 0;
    private static int LengthProtocol1 = 0;
    public static int LengthProtocol2 = 0;
    private static int CounterData1 = 0;

    public static int Stromtest;
    private static String HEXADECIMAL_DATA = "";

    //SV 1
    public static String Verfahren = "";
    public static String Betriebsart = "";
    public static String Gas = "";
    public static String Werkstoff;
    public static String Reglertyp;
    public static String StatusMSR;
    public static String StatusFLG;
    public static int SV1pos1;
    public static int SV1pos2;
    public static int SV1pos3;
    public static int Drahtdurchmesser;
    public static int SV1pos4;
    public static int SV1pos5;
    public static int SV1pos6;

    //SV 2
    public static int Kennliniennummer;
    public static int Jobnummer;
    public static byte KennlinienTyp;
    public static String KennlinienTyp_String = "";

    public static int JobKommando;
    public static String JobStatus_String;
    public static byte Verriegelungsstufe;

    //SV 3
    public static byte Gasvorströmen;
    public static byte Gasnachströmen;
    public static byte EinschleichenAbsolut;
    public static byte EinschleichenKorrektur;
    public static byte UpSlope;
    public static byte DownSlope;
    public static int Zündenergie;
    public static int Endkraterenergie;

    //SV 4
    public static int GebirgeStatus;
    public static byte SchweißState;
    public static byte Freibrand;
    public static byte FreibandKorrektur;
    public static byte KorrekturPulsamplitude;
    public static byte KorrekturDrossel;
    public static byte Einfädeln;
    public static byte GastestZeit;

    //Sv 5
    public static int PausenZeit;
    public static int Punktzeit;
    public static byte ZündDauer;
    public static byte EndkraterDauer;
    public static byte SynergieVorgabe;
    public static byte AnzahlLeistungsmodule;

    //SV 6
    public static byte PowerpulsEinAus;
    public static byte PowerpulsE2;
    public static byte PowerpulsT1E1;
    public static byte PowerpulsT2E1;
    public static byte PowerpulsLBKorrE2;
    public static byte PowerpulsUpSlope;
    public static byte PowerpulsDownSlope;
    public static byte JobSlope;
    public static String PowerpulsEinAus_String = "";

    //SV 7
    public static int ZündStrom;
    public static int ZündSpannung;
    public static int ZündEnergie;
    public static byte ZündDrossel;
    public static byte ZündLichtbogenkorrektur;

    //SV 8
    public static int Strom1;
    public static int Spannung1;
    public static int Energie1;
    public static byte Drossel1;
    public static byte Lichtbogenkorrektur1;

    //SV 9
    public static int Strom2;
    public static int Spannung2;
    public static int Energie2;
    public static byte Drossel2;
    public static byte Lichtbogenkorrektur2;

    //SV 10
    public static int Strom3;
    public static int Spannung3;
    public static int Energie3;
    public static byte Drossel3;
    public static byte Lichtbogenkorrektur3;

    //SV 11
    public static int EndkraterStrom;
    public static int EndkraterSpannung;
    public static int EndkraterEnergie;
    public static byte EndkraterDrossel;
    public static byte EndKraterLichtbogenkorrektur;

    //SV 12
    public static int VorschubSetwert;
    public static int VorschubIstwert;
    public static int VorschubHoldwert;
    public static byte VorschubStatus;
    public static byte VorschubAusKennlinie;

    //SV 13
    public static int StromSetwert;
    public static int StromIstwert;
    public static int StromHoldwert = 100;
    public static byte StromStatus;
    public static byte StromInkremental;

    //SV 14
    public static int SpannungSetwert;
    public static int SpannungIstwert;
    public static int SpannungHoldwert;
    public static byte SpannungStatus;
    public static byte SpannungInkremental;

    //SV 15
    public static int BlechdickeSetwert;
    public static int BlechdickeIstwert;
    public static int BlechdickeHoldwert;
    public static byte BlechdickeStatus;
    public static byte Reset;
    public static String Reset_String = "";

    //SV 16
    public static int ElektrodeStromSetwert;
    public static int ElektrodeStromIstwert;
    public static byte HotstartDauer;
    public static int Hotstart;
    public static byte ArcForce;
    public static byte InnenwiderstandfürElektrode;

    //SV 17
    public static int RMTPosAmplitude;
    public static int RMTNegAmplitude;
    public static int StartAmplitude;
    public static byte StartZeit;
    public static int StartÜberhöhung;

    //SV 18
    public static byte InnenwiderstandfürDossel;
    public static byte Überblendzeit;
    public static byte DrosselAbfall;
    public static int MotorFlanke;
    public static byte DrosselDynamic;
    public static byte MAGACPositiveZeit;
    public static int MAGACStromschwellwert;
    public static byte LBRMode;

    //SV 19
    public static byte MAGACBetriebsart;
    public static String MAGACBetriebsart_String = "";
    public static byte MAGACKältewert;
    public static int MAGACNegativZeit;
    public static byte MAGACKurzschlusserkennung;
    public static byte MAGACKurzschlussaufhebung;
    public static int MACAGVerweilzeitPosNeg;
    public static int MACAGVerweilzeitNegPos;

    //SV 20
    public static int WIGSpeedPulsFrequenz;
    public static byte WIGSpeedPulsI1Anteil;
    public static byte WIGSpeedPulsI3;
    public static byte GasSollwert;
    public static int UserNummer;
    public static byte WIGACStromoffset;
    public static byte WIGStatus;
    public static String WIGStatus_String = "";

    //SV 21
    public static int WIGACFrequenz;
    public static byte WIGACBalance;
    public static byte WIGDurchmesserWolframElektrode;
    public static byte WIGBetriebsartWechselrichter;
    public static int KaltdrahtpulsenT1SV21_5;
    public static int WIGStromLimit;
    public static String WIGBetriebsartWechselrichter_String = "";

    //SV 22
    public static byte KHMode;
    public static byte VerzögerungsZeitKaltdrahtEin;
    public static byte VerzögerungsZeitKaltdrahtAus;
    public static byte VerzögerungsZeitHeißdrahtÜberwachung;
    public static byte Vorpositionierungsstrecke;
    public static byte Rückzugsstrecke;
    public static int KaltdrahtpulsenT1SV22_7;
    public static byte KHStatus;
    public static String KHMode_String = "";
    public static String KHStatus_String = "";

    //Time
    public static byte SECOND = 0;
    public static byte MINUTE = 0;
    public static byte HOUR = 0;
    public static byte DAY = 0;
    public static byte MONTH = 0;
    public static byte YEAR = 0;

    //-------------------------------------------

    public static int mpm_display = 0;
    public static int mm_display = 0;
    public static int a_display = 0;

    //-------------------------------------------

    public static int counterTest = 0;
    private static int flag_first = 0;

    private static Charset iso88591charset = Charset.forName("ISO-8859-1");

    public static void callme(String msgReceiver) {//msgReceiver is in hex String

        char pos_7 = msgReceiver.charAt(6);
        char pos_8 = msgReceiver.charAt(7);
        char pos_9 = msgReceiver.charAt(8);
        char pos_10 = msgReceiver.charAt(9);

        StringBuilder joinchar = new StringBuilder();
        gethex = joinchar.append(pos_7).append(pos_8).append(pos_9).append(pos_10).toString();
        //Log.i("gethex",gethex);

        if (flag_first == 0) {
            flag_first = 1;

            /**
             * Init mm
             */
            DatenObjekteSend sendDrahtdurchmeser = new DatenObjekteSend();
            sendDrahtdurchmeser.ChangeParameter(3, 0, 0);

            if (MainActivity.msg_for_can != null && !MainActivity.msg_for_can.equals("")) { //send parameter frame to the machine
                try {
                    if (mOutputStream != null) {
                        //Log.i("mOutputStream", "not null");
                        mOutputStream.write(MainActivity.msg_for_can.getBytes(iso88591charset));
                        //Log.i(TAG, "Write " + msg_for_can + " change parameter");
                        MainActivity.msg_for_can = "";
                        //OutputStream.write('\n');
                    }

                } catch (IOException e) {
                    Log.e("Tag", "Cant write to the console");
                }
            }
        } else {
            /**
             * Get Job frame
             */
            if (MainActivity.STOP_DATENOBJEKTE) {
                if (gethex.equals("06F0")) {

                    //Log.i("Last Data  ",String.valueOf(DO_FRAME[219]));
                    //-----------------First frame-------------------------------------
                    //if ((DO_FRAME[6] == 22) && (DO_FRAME[7] == 1) && (HFound == 0)) {
                    countertoken = 1;
                    HFound = 1; //Header is found
                    for (int i = 6; i < 220; i++) {
                        KENNLINIE_FRAME[y] = DO_FRAME[i];// in bytes
                        y++;
                    }

                    //Log.i(TAG,"Complete frame");
                    y = 0;
                    // -------------Second frame to 26th frame-------------------------
                    //} /*else if (countertoken == 1 && JOB_COUNTER <= 26) {
                    /*for (int i = 6; i < 14; i++) {
                        KENN_FRAME[y] = DO_FRAME[i];// in bytes
                        y++;
                    }*/
                    //Log.i(TAG,"Middle frame");
                    //----------------Last frame----------------------------------------
                /*if (DO_FRAME[10] == 3 && DO_FRAME[11] == 4 && JOB_COUNTER == 27) {
                    JOB_COUNTER = 0;
                    countertoken = 0;
                    HFound = 0; // reset header
                    for (int i = 6; i < 10; i++) {
                        KENN_FRAME[y] = DO_FRAME[i];// in bytes
                        y++; //last y = 307
                    }
                    checktoken = 1;
                    jobtoken = 1; //Data is complete
                    Log.i(TAG,"End frame");
                    y = 0;
                }*/

                    /*String[] x = new String[230];
                    StringBuilder sby = new StringBuilder(); //data in hex
                    String y = "";

                    for (int i = 0; i < 214; i++) { //214 bytes data
                        x[i] = String.format("%02x", (int) ((KENNLINIE_FRAME[i]) & 0xFF)).toUpperCase(); //convert byte to hex value
                        y = sby.append(x[i]).toString(); //hex string
                    }
                    System.out.println("Kenn response : " + y);*/
                }
            } else if (PARSE_TOKEN) {
                //Log.i("PARSE_TOKEN","Datenobjekte");
                if (gethex.equals("3232")) {
                    GlobalVariable.SV1pos1 = (int) DO_FRAME[6];//pos 1 Verfahren
                    GlobalVariable.VERFAHREN_VAL = (int) DO_FRAME[6];//pos 1
                    //Log.i("Verfahren",String.valueOf(GlobalVariable.SV1pos1));

                    GlobalVariable.SV1pos2 = (int) DO_FRAME[7];//pos 2 Betriebsart
                    GlobalVariable.Drahtdurchmesser = (int) DO_FRAME[8];//pos 3 Drahtdurchmesser
                    mm_display = GlobalVariable.Drahtdurchmesser;

                    GlobalVariable.SV1pos4 = (int) DO_FRAME[9];//pos 4 Gas
                    //Log.i("GlobalVariable.SV1pos4",String.valueOf(GlobalVariable.SV1pos4));

                    GlobalVariable.SV1pos5 = (int) DO_FRAME[10];//pos 5 Werkstoff
                    //Log.i("GlobalVariable.SV1pos5",String.valueOf(GlobalVariable.SV1pos5));

                    GlobalVariable.SV1pos6 = (int) DO_FRAME[11];//pos 6 Reglertyp
                    //Log.i("GlobalVariable.SV1pos6",String.valueOf(GlobalVariable.SV1pos6));
                    switch (GlobalVariable.SV1pos6) {
                        case 0:
                            GlobalVariable.Reglertyp = "U/I - Regler ohne Lichtbogen-Regler";
                            break;
                        case 1:
                            GlobalVariable.Reglertyp = "I/I - Regler ohne Lichtbogen-Regler";
                            break;
                        case 2:
                            GlobalVariable.Reglertyp = "U/I - Regler mit Lichtbogen-Regler";
                            break;
                        case 3:
                            GlobalVariable.Reglertyp = "I/I - Regler mit Lichtbogen-Regler";
                            break;
                        default:
                            GlobalVariable.Reglertyp = "Error";
                            break;
                    }
                    GlobalVariable.StatusMSR = (int) DO_FRAME[12];//pos 7 Status MSR
                    int result = GlobalVariable.StatusMSR;
                    if ((result & 0x01) == 1) GlobalVariable.StatusMSR_BT = "Schweißen Ein";
                    else GlobalVariable.StatusMSR_BT = "Schweißen Aus";

                    result = result >>> 1;//shift first time

                    if ((result & 1) == 0) GlobalVariable.StatusMSR_String = "akt. Koffer 1";
                    else GlobalVariable.StatusMSR_String = "akt. Koffer 2";

                    result = result >>> 1;//shift second time

                    if ((result & 1) == 0) GlobalVariable.StatusMSR_String = "Synergie Ein";
                    else GlobalVariable.StatusMSR_String = "Synergie Aus";

                    result = result >>> 1;//shift third time

                    if ((result & 1) == 1) GlobalVariable.StatusMSR_String = "Einfädeln Vor";

                    result = result >>> 1;//shift fourth time

                    if ((result & 1) == 1) GlobalVariable.StatusMSR_String = "Kühlen";

                    result = result >>> 1;//shift fifth time

                    if ((result & 1) == 1) GlobalVariable.StatusMSR_String = "Einfädeln Zurück";

                    result = result >>> 1;//shift sixth time

                    if ((result & 1) == 1) GlobalVariable.StatusMSR_String = "Gas-Test";

                    result = result >>> 1;//shift seventh time

                    if ((result & 1) == 1) GlobalVariable.StatusMSR_String = "Tastenklick Ein";

                    GlobalVariable.StatusFLG = (int) DO_FRAME[13];//pos 8 Status FLG
                    int iSFLG = GlobalVariable.StatusFLG;

                    if ((iSFLG & 1) == 1) GlobalVariable.StatusFLG_String = "FLG im Gebirge-Mode";

                    iSFLG = iSFLG >>> 1;//shift first time

                    if ((iSFLG & 1) == 1) GlobalVariable.StatusFLG_String = "FLG mit Gebirge";

                    iSFLG = iSFLG >>> 1;//shift second time

                    if ((iSFLG & 1) == 1) GlobalVariable.StatusFLG_String = "Zünden Aus";

                    iSFLG = iSFLG >>> 1;//shift third time

                    if ((iSFLG & 1) == 1) GlobalVariable.StatusFLG_String = "Rückzugs-Zündung Aus";

                    iSFLG = iSFLG >>> 1;//shift fourth time

                    if ((iSFLG & 1) == 1) GlobalVariable.StatusFLG_String = "Endpuls Aus";

                    iSFLG = iSFLG >>> 1;//shift fifth time

                    if ((iSFLG & 1) == 1) GlobalVariable.StatusFLG_String = "Locking-Edit_Mode";

                    iSFLG = iSFLG >>> 1;//shift sixth time

                    if ((iSFLG & 1) == 1) GlobalVariable.StatusFLG_String = "Wasser fließt";

                    iSFLG = iSFLG >>> 1;//shift seventh time

                    if ((iSFLG & 1) == 1) GlobalVariable.StatusFLG_String = "Freiband-Warnung";

                    GlobalVariable.Kennliniennummer = (DO_FRAME[14] & 0xFF) + ((DO_FRAME[15] & 0xFF) << 8);//pos 1 or 2 Kennliniennummer
                    //Log.i("(int) DO_FRAME[6]",String.valueOf((int) DO_FRAME[6]));
                    //System.out.println("DO_FRAME[6] & 0xFF) + ((DO_FRAME[7] & 0xFF) << 8 "+(DO_FRAME[6] & 0xFF) + ((DO_FRAME[7] & 0xFF) << 8));
                    GlobalVariable.Jobnummer = (DO_FRAME[16] & 0xFF) + ((DO_FRAME[17] & 0xFF) << 8);//pos 3 or 4 Jobnummer

                    //Log.i("Jobnummer",String.valueOf(GlobalVariable.Jobnummer));
                    GlobalVariable.KennlinienTyp = DO_FRAME[18];
                    int iKT = (int) DO_FRAME[18];//pos 5 Kennlinien-Typ

                    if ((iKT & 1) == 1) GlobalVariable.KennlinienTyp_String = "Typ-Bit 1 = 2^0";

                    iKT = iKT >>> 1;//shift first time

                    if ((iKT & 1) == 1) GlobalVariable.KennlinienTyp_String = "Typ-Bit 2 = 2^1";

                    iKT = iKT >>> 1;//shift second time

                    if ((iKT & 1) == 1) GlobalVariable.KennlinienTyp_String = "Typ-Bit 3 = 2^2";

                    iKT = iKT >>> 1;//shift third time

                    if ((iKT & 1) == 1) GlobalVariable.KennlinienTyp_String = "Typ-Bit 4 = 2^4";

                    iKT = iKT >>> 1;//shift fourth time

                    if ((iKT & 1) == 1) GlobalVariable.KennlinienTyp_String = "Res.";

                    iKT = iKT >>> 1;//shift fifth time

                    if ((iKT & 1) == 1) GlobalVariable.KennlinienTyp_String = "RMT-Verfahren ist ein";

                    iKT = iKT >>> 1;//shift sixth time

                    if ((iKT & 1) == 1) GlobalVariable.KennlinienTyp_String = "HC-MAG ist ein";

                    iKT = iKT >>> 1;//shift seventh time

                    if ((iKT & 1) == 1) GlobalVariable.KennlinienTyp_String = "Kennlinie sichtbar";

                    GlobalVariable.JobKommando = (int) DO_FRAME[19];//pos 6 Job-Kommando

                    int iJS = (int) DO_FRAME[20];//pos 5 Job-Status

                    //Log.i("JobStatus_Display",String.valueOf(iJS));

                    if ((iJS & 1) == 1) GlobalVariable.JobStatus_Display = "Jobschweißen aktiv";
                    else GlobalVariable.JobStatus_Display = "Inactive";

                    iJS = iJS >>> 1;//shift first time

                    if ((iJS & 1) == 1) GlobalVariable.JobStatus_String = "Job-Edit";

                    iJS = iJS >>> 1;//shift second time

                    if ((iJS & 1) == 0) GlobalVariable.JobStatus_String = "Job-Nr. Frei";
                    else GlobalVariable.JobStatus_String = "Job-Nr. belegt";

                    iJS = iJS >>> 1;//shift third time

                    if ((iJS & 1) == 1) GlobalVariable.JobStatus_String = "Keine Jobs im Speicher";

                    iJS = iJS >>> 1;//shift fourth time

                    if ((iJS & 1) == 1) GlobalVariable.JobStatus_String = "Job gespeichert";

                    iJS = iJS >>> 1;//shift fifth time

                    if ((iJS & 1) == 1) GlobalVariable.JobStatus_String = "Display";

                    iJS = iJS >>> 1;//shift sixth time

                    if ((iJS & 1) == 1) GlobalVariable.JobStatus_String = "Job Extern";

                    iJS = iJS >>> 1;//shift seventh time

                    if ((iJS & 1) == 1) GlobalVariable.JobStatus_String  = "";


                    GlobalVariable.Verriegelungsstufe = DO_FRAME[21];//pos 8 Verriegelungsstufe
                    GlobalVariable.Gasvorströmen = DO_FRAME[22];//pos 1 Gasvorströmen
                    GlobalVariable.Gasnachströmen = DO_FRAME[23];//pos 2 Gasnachströmen
                    GlobalVariable.EinschleichenAbsolut = DO_FRAME[24];//pos 3 Einschleichen absolut
                    GlobalVariable.EinschleichenKorrektur = DO_FRAME[25];//pos 4 Einschliechen Korrektur in %
                    GlobalVariable.UpSlope = DO_FRAME[26];//pos 5 Up-Slope
                    GlobalVariable.DownSlope = DO_FRAME[27];//pos 6 Down-Slope
                    GlobalVariable.Zündenergie = DO_FRAME[28];//pos 7 Zündenergie in %
                    GlobalVariable.Endkraterenergie = (int) DO_FRAME[29];//pos 8 Endkraterenergie in %

                    GlobalVariable.GebirgeStatus = DO_FRAME[30];//pos 1 Gebirge-Status
                    GlobalVariable.SchweißState = DO_FRAME[31];//pos 2 Schweiß-State
                    GlobalVariable.Freibrand = DO_FRAME[32];//pos 3 Freiband
                    GlobalVariable.FreibandKorrektur = DO_FRAME[33];//pos 4 Freiband Korrektur
                    GlobalVariable.KorrekturPulsamplitude = DO_FRAME[34];//pos 5 Korrektur Pulsamplitude
                    GlobalVariable.KorrekturDrossel = DO_FRAME[35];//pos 6 Korrektur Drossel
                    GlobalVariable.Einfädeln = DO_FRAME[36];//pos 7 Einfädeln
                    GlobalVariable.GastestZeit = DO_FRAME[37];//pos 8 Gastest-Zeit

                    GlobalVariable.PausenZeit = (DO_FRAME[38] & 0xFF) + ((DO_FRAME[39] & 0xFF) << 8);//pos 1 or 2 Pausenzeit bei Intervall
                    GlobalVariable.Punktzeit = (DO_FRAME[40] & 0xFF) + ((DO_FRAME[41] & 0xFF) << 8);//pos 3 or 4 Punktzeit
                    GlobalVariable.ZündDauer = DO_FRAME[42];//pos 5 Zünd-Dauer
                    GlobalVariable.EndkraterDauer = DO_FRAME[43];//pos 6 Endkrater-Dauer
                    GlobalVariable.SynergieVorgabe = DO_FRAME[44];//pos 7 Synergie Vorgabe-Basis
                    GlobalVariable.AnzahlLeistungsmodule = DO_FRAME[45];//pos 8 Anzahl Leistungsmodule

                    GlobalVariable.PowerpulsEinAus = DO_FRAME[46];//pos 1 Powerpuls Ein/Aus
                    if (GlobalVariable.PowerpulsEinAus == 0) GlobalVariable.PowerpulsEinAus_String = "Aus";
                    else if (GlobalVariable.PowerpulsEinAus == 1) GlobalVariable.PowerpulsEinAus_String = "Aktiv";
                    GlobalVariable.PowerpulsE2 = DO_FRAME[47];//pos 2 Powerpuls Energie 2
                    GlobalVariable.PowerpulsT1E1 = DO_FRAME[48];//pos 3 Power-Puls Time 1 für Energie 1
                    GlobalVariable.PowerpulsT2E1 = DO_FRAME[49];//pos 4 Power-Puls Time 2 für Energie 1
                    GlobalVariable.PowerpulsLBKorrE2 = DO_FRAME[50];//pos 5 Power-Puls LB-Korr. Energie 2
                    GlobalVariable.PowerpulsUpSlope = DO_FRAME[51];//pos 6 Power-Puls Up-Slope
                    GlobalVariable.PowerpulsDownSlope = DO_FRAME[52];//pos 7 Power-Puls Down-Slope
                    GlobalVariable.JobSlope = DO_FRAME[53];//pos 8 Job-Slope

                    GlobalVariable.ZündStrom = (DO_FRAME[54] & 0xFF) + ((DO_FRAME[55] & 0xFF) << 8);//pos 1 or 2 Zünd-Strom
                    GlobalVariable.ZündSpannung = (DO_FRAME[56] & 0xFF) + ((DO_FRAME[57] & 0xFF) << 8);//pos 3 or 4 Zünd-Spannung
                    GlobalVariable.ZündEnergie = (DO_FRAME[58] & 0xFF) + ((DO_FRAME[59] & 0xFF) << 8);//pos 5 or 6 Zünd-Energie
                    GlobalVariable.ZündDrossel = DO_FRAME[60];//pos 7 Zünd-Drossel
                    GlobalVariable.ZündLichtbogenkorrektur = DO_FRAME[61];//pos 8 Zünd-Lichtbogenkorrektur

                    GlobalVariable.Strom1 = (DO_FRAME[62] & 0xFF) + ((DO_FRAME[63] & 0xFF) << 8);//pos 1 or 2 Strom 1
                    GlobalVariable.Spannung1 = (DO_FRAME[64] & 0xFF) + ((DO_FRAME[65] & 0xFF) << 8);//pos 3 or 4 Spannung 1
                    if (MainActivity.READVAL_STATUS[3] != 1) {
                        MainActivity.READVAL_STATUS[3] = 1;
                        GlobalVariable.voltage_display = GlobalVariable.Spannung1;
                    }
                    GlobalVariable.Energie1 = (DO_FRAME[66] & 0xFF) + ((DO_FRAME[67] & 0xFF) << 8);//pos 5 or 6 Energie 1
                    GlobalVariable.Drossel1 = DO_FRAME[68];//pos 7 Drossel 1
                    GlobalVariable.Lichtbogenkorrektur1 = DO_FRAME[69];//pos 8 Lichtbogenkorrektur 1
                    if (MainActivity.READVAL_STATUS[2] != 1) {
                        MainActivity.READVAL_STATUS[2] = 1;
                        GlobalVariable.korrektur_display = GlobalVariable.Lichtbogenkorrektur1;
                    }
                    if (MainActivity.READVAL_STATUS[1] != 1) {
                        MainActivity.READVAL_STATUS[1] = 1;
                        GlobalVariable.mpm_display = GlobalVariable.Energie1;
                        //GlobalVariable.mm_a_display = GlobalVariable.mpm_display;
                    }

                    GlobalVariable.Strom2 = (DO_FRAME[70] & 0xFF) + ((DO_FRAME[71] & 0xFF) << 8);//pos 1 or 2 Strom 2
                    GlobalVariable.Spannung2 = (DO_FRAME[72] & 0xFF) + ((DO_FRAME[73] & 0xFF) << 8);//pos 3 or 4 Spannung 2
                    GlobalVariable.Energie2 = (DO_FRAME[74] & 0xFF) + ((DO_FRAME[75] & 0xFF) << 8);//pos 5 or 6 Energie 2
                    GlobalVariable.Drossel2 = DO_FRAME[76];//pos 7 Drossel 2
                    GlobalVariable.Lichtbogenkorrektur2 = DO_FRAME[77];//pos 8 Lichtbogenkorrektur 2

                    GlobalVariable.Strom3 = (DO_FRAME[78] & 0xFF) + ((DO_FRAME[79] & 0xFF) << 8);//pos 1 or 2 Strom 3
                    GlobalVariable.Spannung3 = (DO_FRAME[80] & 0xFF) + ((DO_FRAME[81] & 0xFF) << 8);//pos 3 or 4 Spannung 3
                    GlobalVariable.Energie3 = (DO_FRAME[82] & 0xFF) + ((DO_FRAME[83] & 0xFF) << 8);//pos 5 or 6 Energie 3
                    GlobalVariable.Drossel3 = DO_FRAME[84];//pos 7 Drossel 3
                    GlobalVariable.Lichtbogenkorrektur3 = DO_FRAME[85];//pos 8 Lichtbogenkorrektur 3

                    GlobalVariable.EndkraterStrom = (DO_FRAME[86] & 0xFF) + ((DO_FRAME[87] & 0xFF) << 8);//pos 1 or 2 Endkrater Strom
                    GlobalVariable.EndkraterSpannung = (DO_FRAME[88] & 0xFF) + ((DO_FRAME[89] & 0xFF) << 8);//pos 3 or 4 Endkrater Spannung
                    GlobalVariable.EndkraterEnergie = (DO_FRAME[90] & 0xFF) + ((DO_FRAME[91] & 0xFF) << 8);//pos 5 or 6 Endkrater Energie
                    GlobalVariable.EndkraterDrossel = DO_FRAME[92];//pos 7 Endkrater Drossel
                    GlobalVariable.EndKraterLichtbogenkorrektur = DO_FRAME[93];//pos 8 Endkrater Lichtbogenkorrektur

                    GlobalVariable.VorschubSetwert = (DO_FRAME[94] & 0xFF) + ((DO_FRAME[95] & 0xFF) << 8);//pos 1 or 2 Vorschub Setwert
                    GlobalVariable.VorschubIstwert = (DO_FRAME[96] & 0xFF) + ((DO_FRAME[97] & 0xFF) << 8);//pos 3 or 4 Vorschub Istwert
                    GlobalVariable.VorschubHoldwert = (DO_FRAME[98] & 0xFF) + ((DO_FRAME[99] & 0xFF) << 8);//pos 5 or 6 Vorschub Holdwert
                    GlobalVariable.VorschubStatus = DO_FRAME[100];//pos 7 Vorschub Status
                    GlobalVariable.VorschubAusKennlinie = DO_FRAME[101];//pos 8 Vorschub aus Kennlinie

                    GlobalVariable.StromSetwert = (DO_FRAME[102] & 0xFF) + ((DO_FRAME[103] & 0xFF) << 8);//pos 1 and 2 Strom Setwert
                    GlobalVariable.StromIstwert = (DO_FRAME[104] & 0xFF) + ((DO_FRAME[105] & 0xFF) << 8);//pos 3 and 4 Strom Istwert
                    GlobalVariable.StromHoldwert = (DO_FRAME[106] & 0xFF) + ((DO_FRAME[107] & 0xFF) << 8);//pos 5 and 6 Strom Holdwert
                    GlobalVariable.StromStatus = DO_FRAME[108];//pos 7 Strom Status
                    GlobalVariable.StromInkremental = DO_FRAME[109];//pos 8 Strom Inkremental

                    GlobalVariable.SpannungSetwert = (DO_FRAME[110] & 0xFF) + ((DO_FRAME[111] & 0xFF) << 8);//pos 1 and 2 Spannung Setwert
                    GlobalVariable.SpannungIstwert = (DO_FRAME[112] & 0xFF) + ((DO_FRAME[113] & 0xFF) << 8);//pos 3 or 4 Spannung Istwert
                    GlobalVariable.SpannungHoldwert = (DO_FRAME[114] & 0xFF) + ((DO_FRAME[115] & 0xFF) << 8);//pos 5 or 6 Spannung Holdwert
                    GlobalVariable.SpannungStatus = DO_FRAME[116];//pos 7 Spannung Status
                    GlobalVariable.SpannungInkremental = DO_FRAME[117];//pos 8 Spannung Inkremental

                    GlobalVariable.BlechdickeSetwert = (DO_FRAME[118] & 0xFF) + ((DO_FRAME[119] & 0xFF) << 8);//pos 1 and 2 mm
                    if (MainActivity.READVAL_STATUS[4] != 1) {
                        MainActivity.READVAL_STATUS[4] = 1;
                        GlobalVariable.blechdicke_display = GlobalVariable.BlechdickeSetwert;
                    }
                    GlobalVariable.BlechdickeIstwert = (DO_FRAME[120] & 0xFF) + ((DO_FRAME[121] & 0xFF) << 8);//pos 3 and 4
                    GlobalVariable.BlechdickeHoldwert = (DO_FRAME[122] & 0xFF) + ((DO_FRAME[123] & 0xFF) << 8);//pos 5 and 6
                    GlobalVariable.BlechdickeStatus = DO_FRAME[124];//pos 7

                    GlobalVariable.Reset = DO_FRAME[125];//pos 8
                    if ((GlobalVariable.Reset & 1) == 1) GlobalVariable.Reset_String = "Error-Reset";
                    else GlobalVariable.Reset_String = "No Error";

                    GlobalVariable.ElektrodeStromSetwert = (DO_FRAME[126] & 0xFF) + ((DO_FRAME[127] & 0xFF) << 8);//pos 1 and 2
                    GlobalVariable.ElektrodeStromIstwert = (DO_FRAME[128] & 0xFF) + ((DO_FRAME[129] & 0xFF) << 8);//pos 3 or 4
                    GlobalVariable.HotstartDauer = DO_FRAME[130];//pos 5
                    GlobalVariable.Hotstart = DO_FRAME[131];//pos 6
                    GlobalVariable.ArcForce = DO_FRAME[132];//pos 7
                    GlobalVariable.InnenwiderstandfürElektrode = DO_FRAME[133];//pos 8

                    GlobalVariable.RMTPosAmplitude = (DO_FRAME[134] & 0xFF) + ((DO_FRAME[135] & 0xFF) << 8);//pos 1 and 2
                    GlobalVariable.RMTNegAmplitude = (DO_FRAME[136] & 0xFF) + ((DO_FRAME[137] & 0xFF) << 8);//pos 3 and 4
                    GlobalVariable.StartAmplitude = (DO_FRAME[138] & 0xFF) + ((DO_FRAME[139] & 0xFF) << 8);//pos 5 or 6
                    GlobalVariable.StartZeit = DO_FRAME[140];//pos 7
                    GlobalVariable.StartÜberhöhung = (int) DO_FRAME[141];//pos 8

                    GlobalVariable.InnenwiderstandfürDossel = DO_FRAME[142];//pos 1
                    GlobalVariable.Überblendzeit = DO_FRAME[143];//pos 2
                    GlobalVariable.DrosselAbfall = DO_FRAME[144];//pos 3
                    GlobalVariable.MotorFlanke = (int) DO_FRAME[145];//pos 4
                    GlobalVariable.DrosselDynamic = DO_FRAME[146];//pos 5
                    GlobalVariable.MAGACPositiveZeit = DO_FRAME[147];//pos 6
                    GlobalVariable.MAGACStromschwellwert = (int) DO_FRAME[148];//pos 7
                    GlobalVariable.LBRMode = DO_FRAME[149];//pos 8

                    GlobalVariable.MAGACBetriebsart = DO_FRAME[150];//pos 1
                    if (GlobalVariable.MAGACBetriebsart == 0) GlobalVariable.MAGACBetriebsart_String = "DC+ (entspricht AUS)";
                    else if (GlobalVariable.MAGACBetriebsart == 1) GlobalVariable.MAGACBetriebsart_String = "DC-";
                    else if (GlobalVariable.MAGACBetriebsart == 2) GlobalVariable.MAGACBetriebsart_String = "AC";

                    GlobalVariable.MAGACKältewert = DO_FRAME[151];//pos 2
                    GlobalVariable.MAGACNegativZeit = (DO_FRAME[152] & 0xFF) + ((DO_FRAME[153] & 0xFF) << 8);//pos 3 and 4
                    GlobalVariable.MAGACKurzschlusserkennung = DO_FRAME[154];//pos 5
                    GlobalVariable.MAGACKurzschlussaufhebung = DO_FRAME[155];//pos 6
                    GlobalVariable.MACAGVerweilzeitPosNeg = (int) DO_FRAME[156];//pos 7
                    GlobalVariable.MACAGVerweilzeitNegPos = (int) DO_FRAME[157];//pos 8

                    GlobalVariable.WIGSpeedPulsFrequenz = (DO_FRAME[158] & 0xFF) + ((DO_FRAME[159] & 0xFF) << 8);//pos 1 and 2
                    GlobalVariable.WIGSpeedPulsI1Anteil = DO_FRAME[160];//pos 3
                    GlobalVariable.WIGSpeedPulsI3 = DO_FRAME[161];//pos 4
                    GlobalVariable.GasSollwert = DO_FRAME[162];//pos 5
                    GlobalVariable.UserNummer = (int) DO_FRAME[163];//pos 6
                    GlobalVariable.WIGACStromoffset = DO_FRAME[164];//pos 7

                    GlobalVariable.WIGStatus = DO_FRAME[165]; //pos 8
                    int iWIGStatus = (int) GlobalVariable.WIGStatus;

                    if ((iWIGStatus & 1) == 1) GlobalVariable.WIGStatus_String = "WIG-Brennertaste 2 ein";

                    iWIGStatus = iWIGStatus >>> 1;//shift first time

                    if ((iWIGStatus & 1) == 1) GlobalVariable.WIGStatus_String = "Puls I2";

                    iWIGStatus = iWIGStatus >>> 1;//shift second time

                    if ((iWIGStatus & 1) == 1) GlobalVariable.WIGStatus_String = "Fußpedal vorhanden";

                    iWIGStatus = iWIGStatus >>> 1;//shift third time

                    if ((iWIGStatus & 1) == 1) GlobalVariable.WIGStatus_String = "Automatisierung";

                    iWIGStatus = iWIGStatus >>> 1;//shift fourth time

                    if ((iWIGStatus & 1) == 1) GlobalVariable.WIGStatus_String = "Wechselrichter vorhanden";

                    iWIGStatus = iWIGStatus >>> 1;//shift fifth time

                    if ((iWIGStatus & 1) == 1) GlobalVariable.WIGStatus_String = "I2 ist in % von I1";

                    iWIGStatus = iWIGStatus >>> 1;//shift sixth time

                    if ((iWIGStatus & 1) == 1) GlobalVariable.WIGStatus_String = "Reserve";

                    iWIGStatus = iWIGStatus >>> 1;//shift seventh time

                    if ((iWIGStatus & 1) == 1) GlobalVariable.WIGStatus_String = "Reserve";

                    GlobalVariable.WIGACFrequenz = (DO_FRAME[166] & 0xFF) + ((DO_FRAME[167] & 0xFF) << 8);//pos 1 and 2
                    GlobalVariable.WIGACBalance = DO_FRAME[168];//pos 3
                    GlobalVariable.WIGDurchmesserWolframElektrode = DO_FRAME[169];//pos 4

                    GlobalVariable.WIGBetriebsartWechselrichter = DO_FRAME[170];//pos 5
                    if (GlobalVariable.WIGBetriebsartWechselrichter == 0) GlobalVariable.WIGBetriebsartWechselrichter_String = "NONE";
                    else if (GlobalVariable.WIGBetriebsartWechselrichter == 1) GlobalVariable.WIGBetriebsartWechselrichter_String = "DC - Minus";
                    else if (GlobalVariable.WIGBetriebsartWechselrichter == 2) GlobalVariable.WIGBetriebsartWechselrichter_String = "DC - Plus";
                    else if (GlobalVariable.WIGBetriebsartWechselrichter == 3) GlobalVariable.WIGBetriebsartWechselrichter_String = "AC - Sinus";
                    else if (GlobalVariable.WIGBetriebsartWechselrichter == 4) GlobalVariable.WIGBetriebsartWechselrichter_String = "AC - Rechteck";
                    else if (GlobalVariable.WIGBetriebsartWechselrichter == 5) GlobalVariable.WIGBetriebsartWechselrichter_String = "AC - MIX";

                    GlobalVariable.KaltdrahtpulsenT1SV21_5 = (int) DO_FRAME[171];//pos 6
                    GlobalVariable.WIGStromLimit = (DO_FRAME[172] & 0xFF) + ((DO_FRAME[173] & 0xFF) << 8);//pos 7 and 8

                    GlobalVariable.KHMode = DO_FRAME[174]; //pos 1
                    int iKHMode = (int) GlobalVariable.KHMode;

                    if ((iKHMode & 1) == 1) GlobalVariable.KHMode_String = "Kaltdraht Freigabe";

                    iKHMode = iKHMode >>> 1;//shift first time

                    if ((iKHMode & 1) == 1) GlobalVariable.KHMode_String = "Kaltdraht Start Roboter";

                    iKHMode = iKHMode >>> 1;//shift second time

                    if ((iKHMode & 1) == 0) GlobalVariable.KHMode_String = "Kaltdraht Stop wenn Min. Unterschr";

                    iKHMode = iKHMode >>> 1;//shift third time

                    if ((iKHMode & 1) == 1) GlobalVariable.KHMode_String = "Kaltdraht Pulsen an Strom gekoppelt";

                    iKHMode = iKHMode >>> 1;//shift fourth time

                    if ((iKHMode & 1) == 1) GlobalVariable.KHMode_String = "Heißdraht Freigabe";

                    iKHMode = iKHMode >>> 1;//shift fifth time

                    if ((iKHMode & 1) == 1) GlobalVariable.KHMode_String = "Heißdraht Start Roboter";

                    iKHMode = iKHMode >>> 1;//shift sixth time

                    if ((iKHMode & 1) == 1) GlobalVariable.KHMode_String = "Kaltdraht Pulsen Freigabe";

                    iKHMode = iKHMode >>> 1;//shift seventh time

                    if ((iKHMode & 1) == 1) GlobalVariable.KHMode_String = "Kaltdraht V2 Absolut in m/min";

                    GlobalVariable.VerzögerungsZeitKaltdrahtEin = DO_FRAME[175];//pos 2
                    GlobalVariable.VerzögerungsZeitKaltdrahtAus = DO_FRAME[176];//pos 3
                    GlobalVariable.VerzögerungsZeitHeißdrahtÜberwachung = DO_FRAME[177];//pos 4
                    GlobalVariable.Vorpositionierungsstrecke = DO_FRAME[178];//pos 5
                    GlobalVariable.Rückzugsstrecke = DO_FRAME[179];//pos 6
                    GlobalVariable.KaltdrahtpulsenT1SV22_7 = (int) DO_FRAME[180];//pos 7

                    GlobalVariable.KHStatus = DO_FRAME[181];//pos 8
                    int iKHStatus = (int) GlobalVariable.KHStatus;
                    if ((iKHStatus & 1) == 1) GlobalVariable.KHStatus_String = "Kaltdraht ok(DVC im System";

                    iKHStatus = iKHStatus >>> 1;//shift first time

                    if ((iKHStatus & 1) == 1) GlobalVariable.KHStatus_String = "Kalt-Draht Ein";

                    iKHStatus = iKHStatus >>> 1;//shift second time

                    if ((iKHStatus & 1) == 0) GlobalVariable.KHStatus_String = "Kalt-Draht fördert";

                    iKHStatus = iKHStatus >>> 1;//shift third time

                    if ((iKHStatus & 1) == 1) GlobalVariable.KHStatus_String = " ";

                    iKHStatus = iKHStatus >>> 1;//shift fourth time

                    if ((iKHStatus & 1) == 1) GlobalVariable.KHStatus_String = "Heißdraht ok(MSRC-HW DVC im System)";

                    iKHStatus = iKHStatus >>> 1;//shift fifth time

                    if ((iKHStatus & 1) == 1) GlobalVariable.KHStatus_String = "Heiß-Draht Ein";

                    iKHStatus = iKHStatus >>> 1;//shift sixth time

                    if ((iKHStatus & 1) == 1) GlobalVariable.KHStatus_String = "Heiß-Draht Strom fließt";

                    iKHStatus = iKHStatus >>> 1;//shift seventh time

                    if ((iKHStatus & 1) == 1) GlobalVariable.KHStatus_String = "Heiß-Draht-Strom ok";

                    GlobalVariable.YEAR = DO_FRAME[184];
                    GlobalVariable.MONTH = DO_FRAME[185];
                    GlobalVariable.DAY = DO_FRAME[186];
                    GlobalVariable.HOUR = DO_FRAME[187];
                    GlobalVariable.MINUTE = DO_FRAME[188];
                    GlobalVariable.SECOND = DO_FRAME[189];
                    GlobalVariable.Mirror = ((int) (DO_FRAME[190])) & 0xFF; //8 bytes DO_FRAME[190] to DO_FRAME[197]
                    GlobalVariable.mirror_display = GlobalVariable.Mirror;

                    GlobalVariable.DrahtvorschubIst = (DO_FRAME[198] & 0xFF) + ((DO_FRAME[199] & 0xFF) << 8);//pos 0 and 1
                    GlobalVariable.SchweissspanungIst = (DO_FRAME[200] & 0xFF) + ((DO_FRAME[201] & 0xFF) << 8);//pos 2 and 3
                    int temp = (int) DO_FRAME[202];
                    if(temp < 0) temp = temp +256;
                    GlobalVariable.Brennertasten = temp;
                    int BT = GlobalVariable.Brennertasten;

                    if ((BT & 1) == 1) {
                        GlobalVariable.Brennertasten1_string = "Brennertaste1 Active";
                        GlobalVariable.BT1_Interrupt = true;
                    } else {
                        GlobalVariable.Brennertasten1_string = "Not active";
                        //GlobalVariable.BT1_Interrupt = false;
                    }
                    //System.out.println(GlobalVariable.Brennertasten1_string);

                    BT = BT >>> 1;//shift first time

                    if ((BT & 1) == 1) GlobalVariable.Brennertasten2_string = "Brennertaste2 Active";
                    else GlobalVariable.Brennertasten2_string = "Not active";

                    BT = BT >>> 1;//shift second time

                    if ((BT & 1) == 0) GlobalVariable.Brennertasten3_string = "Brennertaste3 Active";
                    else GlobalVariable.Brennertasten3_string = "Not active";

                    BT = BT >>> 1;//shift third time

                    if ((BT & 1) == 1) GlobalVariable.Gastest_string = "Gastest Active";
                    else GlobalVariable.Gastest_string = "Not active";

                    BT = BT >>> 1;//shift fourth time

                    if ((BT & 1) == 1) GlobalVariable.Einfaedeln_string = "Einfaedeln Active";
                    else GlobalVariable.Einfaedeln_string = "Not active";

                    BT = BT >>> 1;//shift fifth time

                    if ((BT & 1) == 1) GlobalVariable.SAS_string = " SAS Active";
                    else GlobalVariable.SAS_string = "Not active";

                    BT = BT >>> 1;//shift sixth time

                    if ((BT & 1) == 1) GlobalVariable.Werkstueck_string = "Werkstueck active";
                    else GlobalVariable.Werkstueck_string = "Not active";

                    BT = BT >>> 1;//shift seventh time

                    if ((BT & 1) == 1) GlobalVariable.Drahtende_string = "Drahtende active";
                    else GlobalVariable.Drahtende_string = "Not active";

                    GlobalVariable.Optionen = DO_FRAME[203];
                    int Op = (int) GlobalVariable.Optionen;
                    if ((Op & 1) == 1) GlobalVariable.Gasventil_string = "Gasventil Active";
                    else GlobalVariable.Gasventil_string = "Not active";

                    Op = Op >>> 1;//shift first time

                    if ((Op & 1) == 1) GlobalVariable.Ausblasventil_string = "Ausblasventil Active";
                    else GlobalVariable.Ausblasventil_string = "Not active";

                    Op = Op >>> 1;//shift second time

                    if ((Op & 1) == 0) GlobalVariable.Optionen_string = "";
                    else GlobalVariable.Optionen_string = "";

                    Op = Op >>> 1;//shift third time

                    if ((Op & 1) == 1) GlobalVariable.Optionen_string = "";
                    else GlobalVariable.Optionen_string = "";

                    Op = Op >>> 1;//shift fourth time

                    if ((Op & 1) == 1) GlobalVariable.Optionen_string = "";
                    else GlobalVariable.Optionen_string = "";

                    Op = Op >>> 1;//shift fifth time

                    if ((Op & 1) == 1) GlobalVariable.Werkstücksuche_string = "Werkstücksuche An/Aus active";
                    else GlobalVariable.Werkstücksuche_string = "";

                    Op = Op >>> 1;//shift sixth time

                    if ((Op & 1) == 1) GlobalVariable.Rückzugszündung_string = "Rückzugszündung Aus active";
                    else GlobalVariable.Rückzugszündung_string = "Not active";

                    Op = Op >>> 1;//shift seventh time

                    if ((Op & 1) == 1) GlobalVariable.EinfädelnZüruck_string = "Einfädeln Zurück active";
                    else GlobalVariable.EinfädelnZüruck_string = "Not active";

                    GlobalVariable.VorschubStatus0 = DO_FRAME[204];
                    int VorStatus0 = (int) GlobalVariable.VorschubStatus0;
                    if ((VorStatus0 & 1) == 1) GlobalVariable.VorschubStatus0_string = "An/Aus";
                    else GlobalVariable.VorschubStatus0_string = "Not active";

                    VorStatus0 = VorStatus0 >>> 1;//shift first time

                    if ((VorStatus0 & 1) == 1) GlobalVariable.VorschubStatus0_string = "Strom fließt";
                    else GlobalVariable.VorschubStatus0_string = "Not active";

                    VorStatus0 = VorStatus0 >>> 1;//shift second time

                    if ((VorStatus0 & 1) == 0) GlobalVariable.VorschubStatus0_string = "Einfädeln Vorwärts";
                    else GlobalVariable.VorschubStatus0_string = "Not active";

                    VorStatus0 = VorStatus0 >>> 1;//shift third time

                    if ((VorStatus0 & 1) == 1) GlobalVariable.VorschubStatus0_string = "Koffer1/2";
                    else GlobalVariable.VorschubStatus0_string = "Not active";

                    VorStatus0 = VorStatus0 >>> 1;//shift fourth time

                    if ((VorStatus0 & 1) == 1) GlobalVariable.VorschubStatus0_string = "Zwischentrieb";
                    else GlobalVariable.VorschubStatus0_string = "Not active";

                    VorStatus0 = VorStatus0 >>> 1;//shift fifth time

                    if ((VorStatus0 & 1) == 1) GlobalVariable.VorschubStatus0_string = "Prozeßdaten HP gesteckt";
                    else GlobalVariable.VorschubStatus0_string = "Not active";

                    VorStatus0 = VorStatus0 >>> 1;//shift sixth time

                    if ((VorStatus0 & 1) == 1) GlobalVariable.VorschubStatus0_string = "Push Pull Betrieb:HP Pistole gesteckt";
                    else GlobalVariable.VorschubStatus0_string = "Not active";

                    VorStatus0 = VorStatus0 >>> 1;//shift seventh time

                    if ((VorStatus0 & 1) == 1) GlobalVariable.VorschubStatus0_string = "kein Encoder für Drahtvorschub";
                    else GlobalVariable.VorschubStatus0_string = "Not active";

                    GlobalVariable.VorschubStatus1 = DO_FRAME[205];
                    int VorStatus1 = (int) GlobalVariable.VorschubStatus1;
                    if ((VorStatus1 & 1) == 1) GlobalVariable.VorschubStatus1_string = "kein Encoder für Pistole";

                    VorStatus1 = VorStatus1 >>> 1;//shift first time

                    if ((VorStatus1 & 1) == 1) GlobalVariable.VorschubStatus1_string = "Drahtvorschub Überstrom Warnung";

                    VorStatus1 = VorStatus1 >>> 1;//shift second time

                    if ((VorStatus1 & 1) == 0) GlobalVariable.VorschubStatus1_string = "Pistole Überstrom Warnung";

                    VorStatus1 = VorStatus1 >>> 1;//shift third time

                    if ((VorStatus1 & 1) == 1) GlobalVariable.VorschubStatus1_string = "Überstromabschaltung";

                    VorStatus1 = VorStatus1 >>> 1;//shift fourth time

                    if ((VorStatus1 & 1) == 1) GlobalVariable.VorschubStatus1_string = "Drahtvorschub Encoderfehler";

                    VorStatus1 = VorStatus1 >>> 1;//shift fifth time

                    if ((VorStatus1 & 1) == 1) GlobalVariable.VorschubStatus1_string = "Pistole Encoderfehler";

                    VorStatus1 = VorStatus1 >>> 1;//shift sixth time

                    if ((VorStatus1 & 1) == 1) GlobalVariable.VorschubStatus1_string = "Übertemperatur Drahtvorschub";

                    VorStatus1 = VorStatus1 >>> 1;//shift seventh time

                    if ((VorStatus1 & 1) == 1) GlobalVariable.VorschubStatus1_string = "Übertemperatur Pistole";

                } else if (gethex.equals("0720")) { //send to Heartbeat class
                    HeartBeat.sendHeartBeat(); //you don’t have to create an object from a class before you can use static methods defined by the class.

                } else if (gethex.equals("0181")) {
                    Log.i("gethex ","0181");
                    SV1pos1 = (int) DO_FRAME[6];//pos 1
                    GlobalVariable.VERFAHREN_VAL = (int) DO_FRAME[6];//pos 1
                    //Log.i("Verfahren mode",String.valueOf(SV1pos1));
                    //VerfahrenParam(SV1pos1);

                    SV1pos2 = (int) DO_FRAME[7];//pos 2
                    switch (SV1pos2) {
                        case 0:
                            Betriebsart = "NONE";
                            break;
                        case 1:
                            Betriebsart = "2-Takt";
                            break;
                        case 2:
                            Betriebsart = "4-Takt";
                            break;
                        case 3:
                            Betriebsart = "4-Takt Sonder";
                            break;
                        case 4:
                            Betriebsart = "Programm";
                            break;
                        case 5:
                            Betriebsart = "Punkten";
                            break;
                        case 6:
                            Betriebsart = "Intervall";
                            break;
                        case 7:
                            Betriebsart = "Extern";
                            break;
                        case 8:
                            Betriebsart = "2-Takt + HF";
                            break;
                        case 9:
                            Betriebsart = "4-Takt + HF";
                            break;
                        default:
                            Betriebsart = "Error";
                            break;
                    }

                    Drahtdurchmesser = (int) DO_FRAME[8];//pos 3

                    SV1pos4 = (int) DO_FRAME[9];//pos 4
                    //GasParam(SV1pos4);

                    SV1pos5 = (int) DO_FRAME[10];//pos 5
                    //WerkstoffParam(SV1pos5);

                    SV1pos6 = (int) DO_FRAME[11];//pos 6
                    switch (SV1pos6) {
                        case 0:
                            Reglertyp = "U/I - Regler ohne Lichtbogen-Regler";
                            break;
                        case 1:
                            Reglertyp = "I/I - Regler ohne Lichtbogen-Regler";
                            break;
                        case 2:
                            Reglertyp = "U/I - Regler mit Lichtbogen-Regler";
                            break;
                        case 3:
                            Reglertyp = "I/I - Regler mit Lichtbogen-Regler";
                            break;
                        default:
                            Reglertyp = "Error";
                            break;
                    }
                    int result = (int) DO_FRAME[12];//pos 7
                    //System.out.println("StatusMSR_String  "+result);

                    if ((result & 0x01) == 1) {
                        StatusMSR = "Schweißen Ein";
                    }

                    result = result >>> 1;//shift first time

                    if ((result & 1) == 0) {
                        StatusMSR = "akt. Koffer 1";
                    } else {
                        StatusMSR = "akt. Koffer 2";
                    }

                    result = result >>> 1;//shift second time

                    if ((result & 1) == 0) {
                        StatusMSR = "Synergie Ein";
                    } else {
                        StatusMSR = "Synergie Aus";
                    }

                    result = result >>> 1;//shift third time

                    if ((result & 1) == 1) {
                        StatusMSR = "Einfädeln Vor";
                    }

                    result = result >>> 1;//shift fourth time

                    if ((result & 1) == 1) {
                        StatusMSR = "Kühlen";
                    }

                    result = result >>> 1;//shift fifth time

                    if ((result & 1) == 1) {
                        StatusMSR = "Einfädeln Zurück";
                    }

                    result = result >>> 1;//shift sixth time

                    if ((result & 1) == 1) {
                        StatusMSR = "Gas-Test";
                    }

                    result = result >>> 1;//shift seventh time

                    if ((result & 1) == 1) {
                        StatusMSR = "Tastenklick Ein";
                    }

                    int iSFLG = (int) DO_FRAME[13];//pos 8

                    if ((iSFLG & 1) == 1) {
                        StatusFLG = "FLG im Gebirge-Mode";
                    }

                    iSFLG = iSFLG >>> 1;//shift first time

                    if ((iSFLG & 1) == 1) {
                        StatusFLG = "FLG mit Gebirge";
                    }

                    iSFLG = iSFLG >>> 1;//shift second time

                    if ((iSFLG & 1) == 1) {
                        StatusFLG = "Zünden Aus";
                    }

                    iSFLG = iSFLG >>> 1;//shift third time

                    if ((iSFLG & 1) == 1) {
                        StatusFLG = "Rückzugs-Zündung Aus";
                    }

                    iSFLG = iSFLG >>> 1;//shift fourth time

                    if ((iSFLG & 1) == 1) {
                        StatusFLG = "Endpuls Aus";
                    }

                    iSFLG = iSFLG >>> 1;//shift fifth time

                    if ((iSFLG & 1) == 1) {
                        StatusFLG = "Locking-Edit_Mode";
                    }

                    iSFLG = iSFLG >>> 1;//shift sixth time

                    if ((iSFLG & 1) == 1) {
                        StatusFLG = "Wasser fließt";
                    }

                    iSFLG = iSFLG >>> 1;//shift seventh time

                    if ((iSFLG & 1) == 1) {
                        StatusFLG = "Freiband-Warnung";
                    }

                } else if (gethex.equals("0182")) {

                    Kennliniennummer = (DO_FRAME[6] & 0xFF) + ((DO_FRAME[7] & 0xFF) << 8);//pos 1 or 2
                    //Log.i("(int) DO_FRAME[6]",String.valueOf((int) DO_FRAME[6]));
                    //System.out.println("DO_FRAME[6] & 0xFF) + ((DO_FRAME[7] & 0xFF) << 8 "+(DO_FRAME[6] & 0xFF) + ((DO_FRAME[7] & 0xFF) << 8));
                    Jobnummer = (DO_FRAME[8] & 0xFF) + ((DO_FRAME[9] & 0xFF) << 8);//pos 3 or 4

                    int iKT = (int) DO_FRAME[10];//pos 5 (Kennlinien-Typ)

                    if ((iKT & 1) == 1) {
                        KennlinienTyp_String = "Typ-Bit 1 = 2^0";
                    }

                    iKT = iKT >>> 1;//shift first time

                    if ((iKT & 1) == 1) {
                        KennlinienTyp_String = "Typ-Bit 2 = 2^1";
                    }

                    iKT = iKT >>> 1;//shift second time

                    if ((iKT & 1) == 1) {
                        KennlinienTyp_String = "Typ-Bit 3 = 2^2";
                    }

                    iKT = iKT >>> 1;//shift third time

                    if ((iKT & 1) == 1) {
                        KennlinienTyp_String = "Typ-Bit 4 = 2^4";
                    }

                    iKT = iKT >>> 1;//shift fourth time

                    if ((iKT & 1) == 1) {
                        KennlinienTyp_String = "Res.";
                    }

                    iKT = iKT >>> 1;//shift fifth time

                    if ((iKT & 1) == 1) {
                        KennlinienTyp_String = "RMT-Verfahren ist ein";
                    }

                    iKT = iKT >>> 1;//shift sixth time

                    if ((iKT & 1) == 1) {
                        KennlinienTyp_String = "HC-MAG ist ein";
                    }

                    iKT = iKT >>> 1;//shift seventh time

                    if ((iKT & 1) == 1) {
                        KennlinienTyp_String = "Kennlinie sichtbar";
                    }

                    JobKommando = (int) DO_FRAME[11];//pos 6

                    int iJS = (int) DO_FRAME[12];//pos 5
                    if ((iJS & 1) == 1) {
                        JobStatus_String = "Jobschweißen aktiv";
                    }

                    iJS = iJS >>> 1;//shift first time

                    if ((iJS & 1) == 1) {
                        JobStatus_String = "Job-Edit";
                    }

                    iJS = iJS >>> 1;//shift second time

                    if ((iJS & 1) == 0) {
                        JobStatus_String = "Job-Nr. Frei";
                    } else {
                        JobStatus_String = "Job-Nr. belegt";
                    }

                    iJS = iJS >>> 1;//shift third time

                    if ((iJS & 1) == 1) {
                        JobStatus_String = "Keine Jobs im Speicher";
                    }

                    iJS = iJS >>> 1;//shift fourth time

                    if ((iJS & 1) == 1) {
                        JobStatus_String = "Job gespeichert";
                    }

                    iJS = iJS >>> 1;//shift fifth time

                    if ((iJS & 1) == 1) {
                        JobStatus_String = "Display";
                    }

                    iJS = iJS >>> 1;//shift sixth time

                    if ((iJS & 1) == 1) {
                        JobStatus_String = "Job Extern";
                    }

                    iJS = iJS >>> 1;//shift seventh time

                    if ((iJS & 1) == 1) {
                        JobStatus_String = " ";
                    }
                    Verriegelungsstufe = DO_FRAME[13];//pos 8

                } else if (gethex.equals("0183")) {

                    Gasvorströmen = DO_FRAME[6];//pos 1
                    Gasnachströmen = DO_FRAME[7];//pos 2
                    EinschleichenAbsolut = DO_FRAME[8];//pos 3
                    EinschleichenKorrektur = DO_FRAME[9];//pos 4
                    UpSlope = DO_FRAME[10];//pos 5
                    DownSlope = DO_FRAME[11];//pos 6
                    Zündenergie = DO_FRAME[12];//pos 7
                    Endkraterenergie = (int) DO_FRAME[13];//pos 8

                } else if (gethex.equals("0184")) {

                    GebirgeStatus = DO_FRAME[6];//pos 1
                    SchweißState = DO_FRAME[7];//pos 2
                    Freibrand = DO_FRAME[8];//pos 3
                    FreibandKorrektur = DO_FRAME[9];//pos 4
                    KorrekturPulsamplitude = DO_FRAME[10];//pos 5
                    KorrekturDrossel = DO_FRAME[11];//pos 6
                    Einfädeln = DO_FRAME[12];//pos 7
                    GastestZeit = DO_FRAME[13];//pos 8

                } else if (gethex.equals("0185")) {

                    PausenZeit = (DO_FRAME[6] & 0xFF) + ((DO_FRAME[7] & 0xFF) << 8);//pos 1 or 2
                    Punktzeit = (DO_FRAME[8] & 0xFF) + ((DO_FRAME[9] & 0xFF) << 8);//pos 3 or 4
                    ZündDauer = DO_FRAME[10];//pos 5
                    EndkraterDauer = DO_FRAME[11];//pos 6
                    SynergieVorgabe = DO_FRAME[12];//pos 7
                    AnzahlLeistungsmodule = DO_FRAME[13];//pos 8

                } else if (gethex.equals("0281")) {

                    PowerpulsEinAus = DO_FRAME[6];//pos 1
                    if (PowerpulsEinAus == 0) {
                        PowerpulsEinAus_String = "Aus";
                    } else if (PowerpulsEinAus == 1) {
                        PowerpulsEinAus_String = "Aktiv";
                    }
                    PowerpulsE2 = DO_FRAME[7];//pos 2
                    PowerpulsT1E1 = DO_FRAME[8];//pos 3
                    PowerpulsT2E1 = DO_FRAME[9];//pos 4
                    PowerpulsLBKorrE2 = DO_FRAME[10];//pos 5
                    PowerpulsUpSlope = DO_FRAME[11];//pos 6
                    PowerpulsDownSlope = DO_FRAME[12];//pos 7
                    JobSlope = DO_FRAME[13];//pos 8

                } else if (gethex.equals("0282")) {

                    ZündStrom = (DO_FRAME[6] & 0xFF) + ((DO_FRAME[7] & 0xFF) << 8);//pos 1 or 2
                    ZündSpannung = (DO_FRAME[8] & 0xFF) + ((DO_FRAME[9] & 0xFF) << 8);//pos 3 or 4
                    ZündEnergie = (DO_FRAME[10] & 0xFF) + ((DO_FRAME[11] & 0xFF) << 8);//pos 5 or 6
                    ZündDrossel = DO_FRAME[12];//pos 7
                    ZündLichtbogenkorrektur = DO_FRAME[13];//pos 8

                } else if (gethex.equals("0283")) {

                    Strom1 = (DO_FRAME[6] & 0xFF) + ((DO_FRAME[7] & 0xFF) << 8);//pos 1 or 2
                    Spannung1 = (DO_FRAME[8] & 0xFF) + ((DO_FRAME[9] & 0xFF) << 8);//pos 3 or 4
                    //Energie1 = DO_FRAME[10] & 0xFF;//pos 5 or 6
                    Energie1 = (DO_FRAME[10] & 0xFF) + ((DO_FRAME[11] & 0xFF) << 8);//pos 5 or 6
                    Drossel1 = DO_FRAME[12];//pos 7
                    Lichtbogenkorrektur1 = DO_FRAME[13];//pos 8
                    if (MainActivity.READVAL_STATUS[1] != 1) {
                        MainActivity.READVAL_STATUS[1] = 1;
                        mpm_display = Energie1;
                        //GlobalVariable.mm_a_display = mpm_display;
                    }
                }

                if (gethex.equals("0201")) {
                    Stromtest = ((int) (DO_FRAME[6])) & 0xFF;
                    a_display = Stromtest;
                } else if (gethex.equals("0284")) {

                    Strom2 = (DO_FRAME[6] & 0xFF) + ((DO_FRAME[7] & 0xFF) << 8);//pos 1 or 2
                    Spannung2 = (DO_FRAME[8] & 0xFF) + ((DO_FRAME[9] & 0xFF) << 8);//pos 3 or 4
                    Energie2 = (DO_FRAME[10] & 0xFF) + ((DO_FRAME[11] & 0xFF) << 8);//pos 5 or 6
                    Drossel2 = DO_FRAME[12];//pos 7
                    Lichtbogenkorrektur2 = DO_FRAME[13];//pos 8

                } else if (gethex.equals("0285")) {

                    Strom3 = (DO_FRAME[6] & 0xFF) + ((DO_FRAME[7] & 0xFF) << 8);//pos 1 or 2
                    Spannung3 = (DO_FRAME[8] & 0xFF) + ((DO_FRAME[9] & 0xFF) << 8);//pos 3 or 4
                    Energie3 = (DO_FRAME[10] & 0xFF) + ((DO_FRAME[11] & 0xFF) << 8);//pos 5 or 6
                    Drossel3 = DO_FRAME[12];//pos 7
                    Lichtbogenkorrektur3 = DO_FRAME[13];//pos 8

                } else if (gethex.equals("0286")) {

                    EndkraterStrom = (DO_FRAME[6] & 0xFF) + ((DO_FRAME[7] & 0xFF) << 8);//pos 1 or 2
                    EndkraterSpannung = (DO_FRAME[8] & 0xFF) + ((DO_FRAME[9] & 0xFF) << 8);//pos 3 or 4
                    EndkraterEnergie = (DO_FRAME[10] & 0xFF) + ((DO_FRAME[11] & 0xFF) << 8);//pos 5 or 6
                    EndkraterDrossel = DO_FRAME[12];//pos 7
                    EndKraterLichtbogenkorrektur = DO_FRAME[13];//pos 8

                } else if (gethex.equals("0481")) {

                    VorschubSetwert = (DO_FRAME[6] & 0xFF) + ((DO_FRAME[7] & 0xFF) << 8);//pos 1 or 2
                    VorschubIstwert = (DO_FRAME[8] & 0xFF) + ((DO_FRAME[9] & 0xFF) << 8);//pos 3 or 4
                    VorschubHoldwert = (DO_FRAME[10] & 0xFF) + ((DO_FRAME[11] & 0xFF) << 8);//pos 5 or 6
                    VorschubStatus = DO_FRAME[12];//pos 7
                    VorschubAusKennlinie = DO_FRAME[13];//pos 8

                } else if (gethex.equals("0482")) {

                    StromSetwert = (DO_FRAME[6] & 0xFF) + ((DO_FRAME[7] & 0xFF) << 8);//pos 1 and 2
                    StromIstwert = (DO_FRAME[8] & 0xFF) + ((DO_FRAME[9] & 0xFF) << 8);//pos 3 and 4
                    StromHoldwert = (DO_FRAME[10] & 0xFF) + ((DO_FRAME[11] & 0xFF) << 8);//pos 5 and 6
                    StromStatus = DO_FRAME[12];//pos 7
                    StromInkremental = DO_FRAME[13];//pos 8
                    //a_display = StromSetwert;

                } else if (gethex.equals("0483")) {

                    SpannungSetwert = (DO_FRAME[6] & 0xFF) + ((DO_FRAME[7] & 0xFF) << 8);//pos 1 and 2
                    SpannungIstwert = (DO_FRAME[8] & 0xFF) + ((DO_FRAME[9] & 0xFF) << 8);//pos 3 or 4
                    SpannungHoldwert = (DO_FRAME[10] & 0xFF) + ((DO_FRAME[11] & 0xFF) << 8);//pos 5 or 6
                    SpannungStatus = DO_FRAME[12];//pos 7
                    SpannungInkremental = DO_FRAME[13];//pos 8

                } else if (gethex.equals("0484")) {

                    BlechdickeSetwert = (DO_FRAME[6] & 0xFF) + ((DO_FRAME[7] & 0xFF) << 8);//pos 1 and 2
                    BlechdickeIstwert = (DO_FRAME[8] & 0xFF) + ((DO_FRAME[9] & 0xFF) << 8);//pos 3 and 4
                    BlechdickeHoldwert = (DO_FRAME[10] & 0xFF) + ((DO_FRAME[11] & 0xFF) << 8);//pos 5 and 6
                    BlechdickeStatus = DO_FRAME[12];//pos 7

                    Reset = DO_FRAME[13];//pos 8
                    if ((Reset & 1) == 1) {
                        Reset_String = "Error-Reset";
                    } else Reset_String = "No Error";

                } else if (gethex.equals("0186")) {

                    ElektrodeStromSetwert = (DO_FRAME[6] & 0xFF) + ((DO_FRAME[7] & 0xFF) << 8);//pos 1 and 2
                    ElektrodeStromIstwert = (DO_FRAME[8] & 0xFF) + ((DO_FRAME[9] & 0xFF) << 8);//pos 3 or 4
                    HotstartDauer = DO_FRAME[10];//pos 5
                    Hotstart = DO_FRAME[11];//pos 6
                    ArcForce = DO_FRAME[12];//pos 7
                    InnenwiderstandfürElektrode = DO_FRAME[13];//pos 8

                } else if (gethex.equals("0385")) {

                    RMTPosAmplitude = (DO_FRAME[6] & 0xFF) + ((DO_FRAME[7] & 0xFF) << 8);//pos 1 and 2
                    RMTNegAmplitude = (DO_FRAME[8] & 0xFF) + ((DO_FRAME[9] & 0xFF) << 8);//pos 3 and 4
                    StartAmplitude = (DO_FRAME[10] & 0xFF) + ((DO_FRAME[11] & 0xFF) << 8);//pos 5 or 6
                    StartZeit = DO_FRAME[12];//pos 7
                    StartÜberhöhung = (int) DO_FRAME[13];//pos 8

                } else if (gethex.equals("0386")) {

                    InnenwiderstandfürDossel = DO_FRAME[6];//pos 1
                    Überblendzeit = DO_FRAME[7];//pos 2
                    DrosselAbfall = DO_FRAME[8];//pos 3
                    MotorFlanke = (int) DO_FRAME[9];//pos 4
                    DrosselDynamic = DO_FRAME[10];//pos 5
                    MAGACPositiveZeit = DO_FRAME[11];//pos 6
                    MAGACStromschwellwert = (int) DO_FRAME[12];//pos 7
                    LBRMode = DO_FRAME[13];//pos 8

                } else if (gethex.equals("0485")) {

                    MAGACBetriebsart = DO_FRAME[6];//pos 1
                    if (MAGACBetriebsart == 0) {
                        MAGACBetriebsart_String = "DC+ (entspricht AUS)";
                    } else if (MAGACBetriebsart == 1) {
                        MAGACBetriebsart_String = "DC-";
                    } else if (MAGACBetriebsart == 2) {
                        MAGACBetriebsart_String = "AC";
                    }

                    MAGACKältewert = DO_FRAME[7];//pos 2
                    MAGACNegativZeit = (DO_FRAME[8] & 0xFF) + ((DO_FRAME[9] & 0xFF) << 8);//pos 3 and 4
                    MAGACKurzschlusserkennung = DO_FRAME[10];//pos 5
                    MAGACKurzschlussaufhebung = DO_FRAME[11];//pos 6
                    MACAGVerweilzeitPosNeg = (int) DO_FRAME[12];//pos 7
                    MACAGVerweilzeitNegPos = (int) DO_FRAME[13];//pos 8

                } else if (gethex.equals("0301")) {

                    WIGSpeedPulsFrequenz = (DO_FRAME[6] & 0xFF) + ((DO_FRAME[7] & 0xFF) << 8);//pos 1 and 2
                    WIGSpeedPulsI1Anteil = DO_FRAME[8];//pos 3
                    WIGSpeedPulsI3 = DO_FRAME[9];//pos 4
                    GasSollwert = DO_FRAME[10];//pos 5
                    UserNummer = (int) DO_FRAME[11];//pos 6
                    WIGACStromoffset = DO_FRAME[12];//pos 7

                    WIGStatus = DO_FRAME[13]; //pos 8
                    int iWIGStatus = (int) WIGStatus;

                    if ((iWIGStatus & 1) == 1) {
                        WIGStatus_String = "WIG-Brennertaste 2 ein";
                    }

                    iWIGStatus = iWIGStatus >>> 1;//shift first time

                    if ((iWIGStatus & 1) == 1) {
                        WIGStatus_String = "Puls I2";
                    }

                    iWIGStatus = iWIGStatus >>> 1;//shift second time

                    if ((iWIGStatus & 1) == 1) {
                        WIGStatus_String = "Fußpedal vorhanden";
                    }

                    iWIGStatus = iWIGStatus >>> 1;//shift third time

                    if ((iWIGStatus & 1) == 1) {
                        WIGStatus_String = "Automatisierung";
                    }

                    iWIGStatus = iWIGStatus >>> 1;//shift fourth time

                    if ((iWIGStatus & 1) == 1) {
                        WIGStatus_String = "Wechselrichter vorhanden";
                    }

                    iWIGStatus = iWIGStatus >>> 1;//shift fifth time

                    if ((iWIGStatus & 1) == 1) {
                        WIGStatus_String = "I2 ist in % von I1";
                    }

                    iWIGStatus = iWIGStatus >>> 1;//shift sixth time

                    if ((iWIGStatus & 1) == 1) {
                        WIGStatus_String = "Reserve";
                    }

                    iWIGStatus = iWIGStatus >>> 1;//shift seventh time

                    if ((iWIGStatus & 1) == 1) {
                        WIGStatus_String = "Reserve";
                    }

                } else if (gethex.equals("0302")) {

                    WIGACFrequenz = (DO_FRAME[6] & 0xFF) + ((DO_FRAME[7] & 0xFF) << 8);//pos 1 and 2
                    WIGACBalance = DO_FRAME[8];//pos 3
                    WIGDurchmesserWolframElektrode = DO_FRAME[9];//pos 4

                    WIGBetriebsartWechselrichter = DO_FRAME[10];//pos 5
                    if (WIGBetriebsartWechselrichter == 0) {
                        WIGBetriebsartWechselrichter_String = "NONE";
                    } else if (WIGBetriebsartWechselrichter == 1) {
                        WIGBetriebsartWechselrichter_String = "DC - Minus";
                    } else if (WIGBetriebsartWechselrichter == 2) {
                        WIGBetriebsartWechselrichter_String = "DC - Plus";
                    } else if (WIGBetriebsartWechselrichter == 3) {
                        WIGBetriebsartWechselrichter_String = "AC - Sinus";
                    } else if (WIGBetriebsartWechselrichter == 4) {
                        WIGBetriebsartWechselrichter_String = "AC - Rechteck";
                    } else if (WIGBetriebsartWechselrichter == 5) {
                        WIGBetriebsartWechselrichter_String = "AC - MIX";
                    }

                    KaltdrahtpulsenT1SV21_5 = (int) DO_FRAME[11];//pos 6
                    WIGStromLimit = (DO_FRAME[12] & 0xFF) + ((DO_FRAME[13] & 0xFF) << 8);//pos 7 and 8

                } else if (gethex.equals("0303")) {

                    KHMode = DO_FRAME[6]; //pos 1
                    int iKHMode = (int) KHMode;

                    if ((iKHMode & 1) == 1) {
                        KHMode_String = "Kaltdraht Freigabe";
                    }

                    iKHMode = iKHMode >>> 1;//shift first time

                    if ((iKHMode & 1) == 1) {
                        KHMode_String = "Kaltdraht Start Roboter";
                    }

                    iKHMode = iKHMode >>> 1;//shift second time

                    if ((iKHMode & 1) == 0) {
                        KHMode_String = "Kaltdraht Stop wenn Min. Unterschr";
                    }

                    iKHMode = iKHMode >>> 1;//shift third time

                    if ((iKHMode & 1) == 1) {
                        KHMode_String = "Kaltdraht Pulsen an Strom gekoppelt";
                    }

                    iKHMode = iKHMode >>> 1;//shift fourth time

                    if ((iKHMode & 1) == 1) {
                        KHMode_String = "Heißdraht Freigabe";
                    }

                    iKHMode = iKHMode >>> 1;//shift fifth time

                    if ((iKHMode & 1) == 1) {
                        KHMode_String = "Heißdraht Start Roboter";
                    }

                    iKHMode = iKHMode >>> 1;//shift sixth time

                    if ((iKHMode & 1) == 1) {
                        KHMode_String = "Kaltdraht Pulsen Freigabe";
                    }

                    iKHMode = iKHMode >>> 1;//shift seventh time

                    if ((iKHMode & 1) == 1) {
                        KHMode_String = "Kaltdraht V2 Absolut in m/min";
                    }

                    VerzögerungsZeitKaltdrahtEin = DO_FRAME[7];//pos 2
                    VerzögerungsZeitKaltdrahtAus = DO_FRAME[8];//pos 3
                    VerzögerungsZeitHeißdrahtÜberwachung = DO_FRAME[9];//pos 4
                    Vorpositionierungsstrecke = DO_FRAME[10];//pos 5
                    Rückzugsstrecke = DO_FRAME[11];//pos 6
                    KaltdrahtpulsenT1SV22_7 = (int) DO_FRAME[12];//pos 7

                    KHStatus = DO_FRAME[13];//pos 8
                    int iKHStatus = (int) KHStatus;
                    if ((iKHStatus & 1) == 1) {
                        KHStatus_String = "Kaltdraht ok(DVC im System";
                    }

                    iKHStatus = iKHStatus >>> 1;//shift first time

                    if ((iKHStatus & 1) == 1) {
                        KHStatus_String = "Kalt-Draht Ein";
                    }

                    iKHStatus = iKHStatus >>> 1;//shift second time

                    if ((iKHStatus & 1) == 0) {
                        KHStatus_String = "Kalt-Draht fördert";
                    }

                    iKHStatus = iKHStatus >>> 1;//shift third time

                    if ((iKHStatus & 1) == 1) {
                        KHStatus_String = " ";
                    }

                    iKHStatus = iKHStatus >>> 1;//shift fourth time

                    if ((iKHStatus & 1) == 1) {
                        KHStatus_String = "Heißdraht ok(MSRC-HW DVC im System)";
                    }

                    iKHStatus = iKHStatus >>> 1;//shift fifth time

                    if ((iKHStatus & 1) == 1) {
                        KHStatus_String = "Heiß-Draht Ein";
                    }

                    iKHStatus = iKHStatus >>> 1;//shift sixth time

                    if ((iKHStatus & 1) == 1) {
                        KHStatus_String = "Heiß-Draht Strom fließt";
                    }

                    iKHStatus = iKHStatus >>> 1;//shift seventh time

                    if ((iKHStatus & 1) == 1) {
                        KHStatus_String = "Heiß-Draht-Strom ok";
                    }
                } else if (gethex.equals("07FF")) {

                    YEAR = DO_FRAME[8];
                    MONTH = DO_FRAME[9];
                    DAY = DO_FRAME[10];
                    HOUR = DO_FRAME[11];
                    MINUTE = DO_FRAME[12];
                    SECOND = DO_FRAME[13];
                    /*Log.i("MONTH",String.valueOf(MONTH));
                    Log.i("DAY",String.valueOf(DAY));
                    Log.i("HOUR",String.valueOf(HOUR));
                    Log.i("MINUTE",String.valueOf(MINUTE));*/
                    //Log.i("SECOND",String.valueOf(SECOND));
                }

                if (gethex.equals("0484")) {
                    //Log.i("gethex ","0484");
                    SV1pos3 = (int) DO_FRAME[6];//pos 3 Thickness mm
                    mm_display = SV1pos3;
                }
            }
        }
    }

/*    public  void FromSaha(String var, int value){ // call in Main Activity

        if(var.equals("Verfahren")){

            canid = "0181";
            pos = 0;
            //usbObject.changeParam(canid, value, pos);

        }else if(var.equals("Betriebsart")){

            canid = "0181";
            pos = 2;
            //usbObject.changeParam(canid, value, pos);

        }else if(var.equals("kennlinie_setting")){

            canid = "0182";
            pos = 5;
            //usbObject.changeParam(canid, value, pos);

        }
    }*/

    public static void buffParsing(String Receiverdata) {
        str1 = Receiverdata.getBytes(iso88591charset);//convert String to byte array
        for (int c = 0; c < Receiverdata.length(); c++) {
            byte ccb = str1[c];//get char from string
            dataCombiner1(ccb);
        }
    }

    public static void dataCombiner1(byte DataRec){
        byte Inn = DataRec;
        //if(Inn<0) Inn = (byte)(Inn + 256);
        if(HeaderFound1==0){
            int ByteCompareee=Byte.compare(DataRec,(byte)36);
            if(ByteCompareee==0){
                HeaderFound1=1;
                DO_FRAME[CounterData1]=Inn;
                CounterData1++;
            }
        }else if(LengthFound1==0){
            LengthFound1=1;
            byte abcd = Inn;
            LengthProtocol1=(int)abcd;
            if(LengthProtocol1<0)LengthProtocol1 = LengthProtocol1 + 256;
            //Log.i("Length Protocol",String.valueOf(LengthProtocol1));
            DO_FRAME[CounterData1]=Inn;
            CounterData1++;

        }else if(CounterData1<LengthProtocol1 - 1){ //counter data starts to count from 2 to 17
            DO_FRAME[CounterData1]=Inn;
            CounterData1++;
        }
        if(CounterData1 == LengthProtocol1 - 1){
            DO_FRAME[CounterData1]=Inn; //DO_FRAME[18]=Inn;
            int ByteCompare2=Byte.compare(DO_FRAME[CounterData1],(byte)35); //int ByteCompare2=Byte.compare(DO_FRAME[18],(byte)35);
            if(ByteCompare2==0){ //received footer
                String[] value = new String[250];
                StringBuilder sb_data = new StringBuilder(); //data in hex

                int tempCANID=DO_FRAME[4];
                if (DO_FRAME[4]<0) tempCANID=DO_FRAME[4]+256;
                if((DO_FRAME[3]==6) && (tempCANID== 240) && (DO_FRAME[9]==5)  ) { //CanID 06F0
                    //Log.i("Length Protocol1",String.valueOf(LengthProtocol1));
                    //Log.i("DO_FRAME[17]",String.valueOf(DO_FRAME[17]));
                    /*for (int i = 0; i < LengthProtocol1; i++) {
                        value[i] = String.format("%02x", (int) ((DO_FRAME[i]) & 0xFF)).toUpperCase(); //convert byte to hex value
                        HEXADECIMAL_DATA = sb_data.append(value[i]).toString(); //hex string
                    }
                    Log.i("HEXADECIMAL_DATA",HEXADECIMAL_DATA);*/
                }

                /*String[] VAL = new String[250];
                StringBuilder sbhex_data = new StringBuilder(); //data in hex

                for (int i = 0; i < LengthProtocol1; i++) {
                    VAL[i] = String.format("%02x", (int) ((DO_FRAME[i]) & 0xFF)).toUpperCase(); //convert byte to hex value
                    HexData = sbhex_data.append(VAL[i]).toString(); //hex string
                }*/
                //if(HexData !=null && !HexData.equals("")) Log.i("dataCombiner",HexData);
                HeaderFound1=0;
                CounterData1=0;
                LengthFound1=0;
                LengthProtocol1=0;
            }
        }
    }

    private static String[] VALUE_STRING_IN_SECOND = {
            DatenObjekte.Verfahren,
            DatenObjekte.Betriebsart,
            String.valueOf(DatenObjekte.SV1pos3), //pos 3 DrahtDurchmesser
            DatenObjekte.Gas,
            DatenObjekte.Werkstoff,
            DatenObjekte.Reglertyp,
            DatenObjekte.StatusMSR,
            DatenObjekte.StatusFLG,
            String.valueOf(DatenObjekte.Kennliniennummer),
            String.valueOf(DatenObjekte.Jobnummer),
            DatenObjekte.KennlinienTyp_String,
            String.valueOf(DatenObjekte.JobKommando),
            String.valueOf(DatenObjekte.JobStatus_String),
            String.valueOf(DatenObjekte.Verriegelungsstufe),
            String.valueOf(DatenObjekte.Gasvorströmen),
            String.valueOf(DatenObjekte.Gasnachströmen),
            String.valueOf(DatenObjekte.EinschleichenAbsolut),
            String.valueOf(DatenObjekte.EinschleichenKorrektur),
            String.valueOf(DatenObjekte.UpSlope),
            String.valueOf(DatenObjekte.DownSlope),
            String.valueOf(DatenObjekte.Zündenergie),
            String.valueOf(DatenObjekte.Endkraterenergie),
            String.valueOf(DatenObjekte.GebirgeStatus),
            String.valueOf(DatenObjekte.SchweißState),
            String.valueOf(DatenObjekte.Freibrand),
            String.valueOf(DatenObjekte.FreibandKorrektur),
            String.valueOf(DatenObjekte.KorrekturPulsamplitude),
            String.valueOf(DatenObjekte.KorrekturDrossel),
            String.valueOf(DatenObjekte.Einfädeln),
            String.valueOf(DatenObjekte.GastestZeit),
            String.valueOf(DatenObjekte.PausenZeit),
            String.valueOf(DatenObjekte.Punktzeit),
            String.valueOf(DatenObjekte.ZündDauer),
            String.valueOf(DatenObjekte.EndkraterDauer),
            String.valueOf(DatenObjekte.SynergieVorgabe),
            String.valueOf(DatenObjekte.AnzahlLeistungsmodule),
            DatenObjekte.PowerpulsEinAus_String,
            String.valueOf(DatenObjekte.PowerpulsE2),
            String.valueOf(DatenObjekte.PowerpulsT1E1),
            String.valueOf(DatenObjekte.PowerpulsT2E1),
            String.valueOf(DatenObjekte.PowerpulsLBKorrE2),
            String.valueOf(DatenObjekte.PowerpulsUpSlope),
            String.valueOf(DatenObjekte.PowerpulsDownSlope),
            String.valueOf(DatenObjekte.JobSlope),
            String.valueOf(DatenObjekte.ZündStrom),
            String.valueOf(DatenObjekte.ZündSpannung),
            String.valueOf(DatenObjekte.ZündEnergie),
            String.valueOf(DatenObjekte.ZündDrossel),
            String.valueOf(DatenObjekte.ZündLichtbogenkorrektur),
            String.valueOf(DatenObjekte.Strom1),
            String.valueOf(DatenObjekte.Spannung1),
            String.valueOf(DatenObjekte.Energie1),
            String.valueOf(DatenObjekte.Drossel1),
            String.valueOf(DatenObjekte.Lichtbogenkorrektur1),
            String.valueOf(DatenObjekte.Strom2),
            String.valueOf(DatenObjekte.Spannung2),
            String.valueOf(DatenObjekte.Energie2),
            String.valueOf(DatenObjekte.Drossel2),
            String.valueOf(DatenObjekte.Lichtbogenkorrektur2),
            String.valueOf(DatenObjekte.Strom3),
            String.valueOf(DatenObjekte.Spannung3),
            String.valueOf(DatenObjekte.Energie3),
            String.valueOf(DatenObjekte.Drossel3),
            String.valueOf(DatenObjekte.Lichtbogenkorrektur3),
            String.valueOf(DatenObjekte.EndkraterStrom),
            String.valueOf(DatenObjekte.EndkraterSpannung),
            String.valueOf(DatenObjekte.EndkraterEnergie),
            String.valueOf(DatenObjekte.EndkraterDrossel),
            String.valueOf(DatenObjekte.EndKraterLichtbogenkorrektur),
            String.valueOf(DatenObjekte.VorschubSetwert),
            String.valueOf(DatenObjekte.VorschubIstwert),
            String.valueOf(DatenObjekte.VorschubHoldwert),
            String.valueOf(DatenObjekte.VorschubStatus),
            String.valueOf(DatenObjekte.VorschubAusKennlinie),
            String.valueOf(DatenObjekte.StromSetwert),
            String.valueOf(DatenObjekte.StromIstwert),
            String.valueOf(DatenObjekte.StromHoldwert),
            String.valueOf(DatenObjekte.StromStatus),
            String.valueOf(DatenObjekte.StromInkremental),
            String.valueOf(DatenObjekte.SpannungSetwert),
            String.valueOf(DatenObjekte.SpannungIstwert),
            String.valueOf(DatenObjekte.SpannungHoldwert),
            String.valueOf(DatenObjekte.SpannungStatus),
            String.valueOf(DatenObjekte.SpannungInkremental),
            String.valueOf(DatenObjekte.BlechdickeSetwert),
            String.valueOf(DatenObjekte.BlechdickeIstwert),
            String.valueOf(DatenObjekte.BlechdickeHoldwert),
            String.valueOf(DatenObjekte.BlechdickeStatus),
            //DatenObjekte.Reset_String,
            String.valueOf(DatenObjekte.ElektrodeStromSetwert),
            String.valueOf(DatenObjekte.ElektrodeStromIstwert),
            String.valueOf(DatenObjekte.HotstartDauer),
            String.valueOf(DatenObjekte.Hotstart),
            String.valueOf(DatenObjekte.ArcForce),
            String.valueOf(DatenObjekte.InnenwiderstandfürElektrode),
            String.valueOf(DatenObjekte.RMTPosAmplitude),
            String.valueOf(DatenObjekte.RMTNegAmplitude),
            String.valueOf(DatenObjekte.StartAmplitude),
            String.valueOf(DatenObjekte.StartZeit),
            String.valueOf(DatenObjekte.StartÜberhöhung),
            String.valueOf(DatenObjekte.InnenwiderstandfürDossel),
            String.valueOf(DatenObjekte.Überblendzeit),
            String.valueOf(DatenObjekte.DrosselAbfall),
            String.valueOf(DatenObjekte.MotorFlanke),
            String.valueOf(DatenObjekte.DrosselDynamic),
            String.valueOf(DatenObjekte.MAGACPositiveZeit),
            String.valueOf(DatenObjekte.MAGACStromschwellwert),
            String.valueOf(DatenObjekte.LBRMode),
            DatenObjekte.MAGACBetriebsart_String,
            String.valueOf(DatenObjekte.MAGACKältewert),
            String.valueOf(DatenObjekte.MAGACNegativZeit),
            String.valueOf(DatenObjekte.MAGACKurzschlusserkennung),
            String.valueOf(DatenObjekte.MAGACKurzschlussaufhebung),
            String.valueOf(DatenObjekte.MACAGVerweilzeitPosNeg),
            String.valueOf(DatenObjekte.MACAGVerweilzeitNegPos),
            String.valueOf(DatenObjekte.WIGSpeedPulsFrequenz),
            String.valueOf(DatenObjekte.WIGSpeedPulsI1Anteil),
            String.valueOf(DatenObjekte.WIGSpeedPulsI3),
            String.valueOf(DatenObjekte.GasSollwert),
            String.valueOf(DatenObjekte.UserNummer),
            String.valueOf(DatenObjekte.WIGACStromoffset),
            DatenObjekte.WIGStatus_String,
            String.valueOf(DatenObjekte.WIGACFrequenz),
            String.valueOf(DatenObjekte.WIGACBalance),
            String.valueOf(DatenObjekte.WIGDurchmesserWolframElektrode),
            DatenObjekte.WIGBetriebsartWechselrichter_String,
            String.valueOf(DatenObjekte.KaltdrahtpulsenT1SV21_5),
            String.valueOf(DatenObjekte.WIGStromLimit),
            DatenObjekte.KHMode_String,
            String.valueOf(DatenObjekte.VerzögerungsZeitKaltdrahtEin),
            String.valueOf(DatenObjekte.VerzögerungsZeitKaltdrahtAus),
            String.valueOf(DatenObjekte.VerzögerungsZeitHeißdrahtÜberwachung),
            String.valueOf(DatenObjekte.Vorpositionierungsstrecke),
            String.valueOf(DatenObjekte.Rückzugsstrecke),
            String.valueOf(DatenObjekte.KaltdrahtpulsenT1SV22_7),
            DatenObjekte.KHStatus_String,
    };
}