package com.jess.gdfp;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.jess.gdfp.DatenBank.InfoContract;
import com.jess.gdfp.DatenBank.InfoDataBase;
import com.jess.gdfp.DatenBank.JobContract;
import com.jess.gdfp.DatenBank.MyDataProvider;
import com.jess.gdfp.View.DatalistView;

import java.io.IOException;
import java.nio.charset.Charset;

import static com.jess.gdfp.UartService.data1;
import static com.jess.gdfp.UartService.getJob1;
import static com.jess.gdfp.UartService.mOutputStream;
import static java.security.AccessController.getContext;

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

    public static void VerfahrenParam(int number) {
        switch (number) {
            case 0:
                Verfahren = "NONE";
                break;
            case 1:
                Verfahren = "MAG_Normal";
                break;
            case 2:
                Verfahren = "MAG-Synergie";
                break;
            case 3:
                Verfahren = "MAG Puls";
                break;
            case 4:
                Verfahren = "Elektrode";
                break;
            case 5:
                Verfahren = "WIG";
                break;
            case 6:
                Verfahren = "WIG-Puls";
                break;
            case 7:
                Verfahren = "WIG-Speed-Puls";
                break;
            case 8:
                Verfahren = "WIG-Speed_Puls + Puls";
                break;
            case 9:
                Verfahren = "HC_MAG";
                break;
            default:
                Verfahren = "Error";
                break;
        }
    }

    public static void GasParam(int number) {
        switch (number) {
            case 0:
                Gas = "82% Argon 18% CO2";
                break;
            case 1:
                Gas = "98% Argon 2% CO2";
                break;
            case 2:
                Gas = "100% Argon";
                break;
            case 3:
                Gas = "100% CO2";
                break;
            case 4:
                Gas = "92% Argon 8% CO2";
                break;
            case 5:
                Gas = "99% Argon 1% O2";
                break;
            case 6:
                Gas = "98% Argon 2% O2";
                break;
            case 7:
                Gas = "97% Argon 3% O2";
                break;
            case 8:
                Gas = "92% Argon 8% O2";
                break;
            case 9:
                Gas = "90% Argon 5% O2 5% CO2";
                break;
            case 10:
                Gas = "100% Helium";
                break;
            case 11:
                Gas = "80% Argon 20% He";
                break;
            case 12:
                Gas = "69% Argon 30% He 1% O2";
                break;
            case 13:
                Gas = "50% Argon 50% Helium";
                break;
            case 14:
                Gas = "98% Argon 2% H2";
                break;
            case 15:
                Gas = "94% Argon 6% H2";
                break;
            case 16:
                Gas = "50% Argon 50% H2";
                break;
            case 17:
                Gas = "30% Argon 70% H2";
                break;
            case 18:
                Gas = "Spezial";
                break;
            default:
                Gas = "Error";
                break;
        }
    }

    public static void WerkstoffParam(int number) {
        switch (number) {
            case 0:
                Werkstoff = "NONE";
                break;
            case 1:
                Werkstoff = "Fe";
                break;
            case 2:
                Werkstoff = "CrNi";
                break;
            case 3:
                Werkstoff = "AlMg";
                System.out.println("AlMg");
                break;
            case 4:
                Werkstoff = "AlSi";
                break;
            case 5:
                Werkstoff = "CuSi";
                break;
            case 6:
                Werkstoff = "AlMg3";
                break;
            case 7:
                Werkstoff = "AlMg5";
                break;
            case 8:
                Werkstoff = "AlMg4,5Mn";
                break;
            case 9:
                Werkstoff = "AlBz";
                break;
            case 10:
                Werkstoff = "Spezial";
                break;

            default:
                Werkstoff = "Error";
                break;
        }

    }

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
            } else if (MainActivity.PARSE_TOKEN) {
                //Log.i("PARSE_TOKEN","Datenobjekte");
                if (gethex.equals("0720")) { //send to Heartbeat class
                    HeartBeat.sendHeartBeat(); //you don’t have to create an object from a class before you can use static methods defined by the class.
                    // Log.i("gethex",gethex);

                } else if (gethex.equals("0181")) {
                    //Log.i("gethex ","0181");
                    SV1pos1 = (int) DO_FRAME[6];//pos 1
                    //Log.i("Verfahren mode",String.valueOf(SV1pos1));
                    VerfahrenParam(SV1pos1);

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

                    SV1pos4 = (int) DO_FRAME[9];//pos 4
                    GasParam(SV1pos4);

                    SV1pos5 = (int) DO_FRAME[10];//pos 5
                    WerkstoffParam(SV1pos5);

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
                    //System.out.println("StatusMSR  "+result);

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
                        GlobalVariable.mm_a_display = mpm_display;
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
                    Log.i("MINUTE",String.valueOf(MINUTE));
                    Log.i("SECOND",String.valueOf(SECOND));*/

                }

                if (gethex.equals("0484")) {
                    //Log.i("gethex ","0484");
                    SV1pos3 = (int) DO_FRAME[6];//pos 3 DrahtDurchmesser
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
            //LengthProtocol2=LengthProtocol1;
            DO_FRAME[CounterData1]=Inn; //DO_FRAME[18]=Inn;

            int ByteCompare2=Byte.compare(DO_FRAME[CounterData1],(byte)35); //int ByteCompare2=Byte.compare(DO_FRAME[18],(byte)35);
            if(ByteCompare2==0){ //received footer
                //Log.i(TAG,"Receive footer");
                String[] value = new String[250];
                StringBuilder sb_data = new StringBuilder(); //data in hex

                int tempCANID=DO_FRAME[4];
                if (DO_FRAME[4]<0) tempCANID=DO_FRAME[4]+256;
                if((DO_FRAME[3]==6) && (tempCANID== 240) && (DO_FRAME[9]==5)  ) { //CanID 06F0
                    //Log.i(TAG,"Reach here");
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
                //Log.i("dataCombiner","called");

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