package com.jess.gdfp;

import android.util.Log;

public class GlobalVariable {

    public static boolean  verfahren_gedrückt = false;
    public static boolean  WIG_btn_gedrückt = false;
    public static boolean  MMNormal_btn_gedrückt = false;
    public static boolean  MMSynergy_btn_gedrückt = false;
    public static boolean  MMPuls_btn_gedrückt = false;
    public static boolean  ElectrodeMMA_btn_gedrückt = false;
    public static int VERFAHREN_MODE = 0;

    public static boolean JOB_NUM_TOKEN = false;
    public static boolean JOB_PRESSED = false;
    public static boolean ENCODER_PRESSED = false;
    public static int JOB_PRESSED_COUNTER = 0;
    public static int CONTROL_PANEL_MODE = 0;
    public static int mm_a_display = 0;
    public static boolean gas_token = false;
    public static boolean werkstoff_token = false;
    public static boolean Drahtdurchmesser_token = false;
    public static boolean job_token = false;
    public static boolean voltage_token = false;
    public static boolean encoder = false;
    public static boolean Betriebsart_Token = false;
    public static boolean Verfahren_Token = false;

    public static boolean SETTING_TOKEN = false;
    public static boolean CHANGE_TOKEN = false;
    public static int SETTING_COUNTER = 0;
    public static int JOBBTN_COUNTER = 0;
    public static int KENNBTN_COUNTER = 0;
    public static int VERFAHREN_COUNTER = 0;
    public static int JOBCOUNT = 0;

    public static int VERFAHREN_VAL = 0;
    public static int mpm_display = 0;
    public static int blechdicke_display = 0;
    public static int voltage_display = 0;
    public static int Mirror = 0;
    public static int mirror_display = 0;
    public static String[][] VER_PAR_ARR = new String[1][2]; //[Row_size][Column_size]

    //-------------------------------- Variables for daten objekte ---------------------------------
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
    public static int Drahtdurchmesser;

    public static String[] Draht_String = {"NONE","0,6 Ø","0,8 Ø","0,9 Ø","1,0 Ø","1,2 Ø","1,4 Ø","1,6 Ø","2,0 Ø","2,4 Ø","Spez"};
    public static String[] Betriebsart_string = {"NONE","2-Takt","4-Takt","4-Takt Sonder","Programm","Punkten",
            "Intervall","Extern","2-Takt + HF","4-Takt + HF"};
    public static String[] Verfahren_String = {"NONE","MAG_Normal","MAG-Synergie","MAG Puls","Elektrode","WIG","WIG-Puls",
            "WIG-Speed-Puls","WIG-Speed_Puls + Puls","HC_MAG"};
    public static String[] Gas_String = {"82% Argon 18% CO2","98% Argon 2% CO2","100% Argon","100% CO2","92% Argon 8% CO2","99% Argon 1% O2",
            "98% Argon 2% O2","97% Argon 3% O2","92% Argon 8% O2","90% Argon 5% O2 5% CO2","100% Helium","80% Argon 20% He","69% Argon 30% He 1% O2",
            "50% Argon 50% Helium","98% Argon 2% H2","94% Argon 6% H2","50% Argon 50% H2","30% Argon 70% H2","Spezial"};
    public static String[] Werksotff_String = {"NONE","Fe","CrNi","AlMg","AlSi","CuSi","AlMg3","AlMg5","AlMg4,5Mn","AlBz","Spezial"};

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
    public static int korrektur_display;

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
    public static int StromHoldwert;
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

    //------------------------------------- Brennertasten ------------------------------------------
    public static int DrahtvorschubIst = 0;
    public static int SchweissspanungIst = 0;
    public static int Brennertasten = 0;
    public static String Brennertasten1_string = "";
    public static String Brennertasten2_string = "";
    public static String Brennertasten3_string = "";
    public static String Gastest_string = "";
    public static String Einfaedeln_string = "";
    public static String SAS_string = "";
    public static String Werkstueck_string = "";
    public static String Drahtende_string = "";

    public static byte Optionen = 0;
    public static String Optionen_string = "";
    public static String Gasventil_string = "";
    public static String Ausblasventil_string = "";
    public static String Werkstücksuche_string = "";
    public static String Rückzugszündung_string = "";
    public static String EinfädelnZüruck_string = "";
    public static byte VorschubStatus0 = 0;
    public static String VorschubStatus0_string = "";
    public static byte VorschubStatus1 = 0;
    public static String VorschubStatus1_string = "";
    //----------------------------------------------------------------------------------------------
    public static void delayInMilli(int DELAY_MILLISEC){
        try {
            Thread.sleep(DELAY_MILLISEC);
        }catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    public static void MMNormal_btn_onclick(){
        //Log.i("Normal","called");
        /*DatenObjekteSend SendMMNormal = new DatenObjekteSend();
        if(GlobalVariable.SV1pos1 == 4) { // Elektrode
            SendMMNormal.ChangeParameter(28,5,0);
            delayInMilli(200);
            SendMMNormal.ChangeParameter(28,5,0);
        }else if(GlobalVariable.SV1pos1 == 3){ //Puls
            SendMMNormal.ChangeParameter(28,5,0);
            delayInMilli(200);
            SendMMNormal.ChangeParameter(28,5,0);
            delayInMilli(200);
            SendMMNormal.ChangeParameter(28,5,0);
        }else if(GlobalVariable.SV1pos1 == 2) { //Synergie
            SendMMNormal.ChangeParameter(28, 5, 0);
            delayInMilli(200);
            SendMMNormal.ChangeParameter(28, 5, 0);
            delayInMilli(200);
            SendMMNormal.ChangeParameter(28, 5, 0);
            delayInMilli(200);
            SendMMNormal.ChangeParameter(28, 5, 0);
        } else if(GlobalVariable.SV1pos1 == 5) { //WIG
            SendMMNormal.ChangeParameter(28, 5, 0);
        }else Log.i("Verfahren mode ","MMNormal");*/
    }

    public static void MMSynergy_btn_onclick(){
        /*DatenObjekteSend SendMMSynergie = new DatenObjekteSend();
        if(GlobalVariable.SV1pos1 == 4) { // Elektrode
            SendMMSynergie.ChangeParameter(25, 10, 0);
            delayInMilli(200);
            SendMMSynergie.ChangeParameter(25, 10, 0);
            delayInMilli(200);
            SendMMSynergie.ChangeParameter(25,5,0);
        }else if(GlobalVariable.SV1pos1 == 3){ //Puls
            SendMMSynergie.ChangeParameter(25, 10, 0);
            delayInMilli(200);
            SendMMSynergie.ChangeParameter(25, 10, 0);
            delayInMilli(200);
            SendMMSynergie.ChangeParameter(25, 10, 0);
            delayInMilli(200);
            SendMMSynergie.ChangeParameter(25, 10, 0);
        }else if(GlobalVariable.SV1pos1 == 1){ //Normal
            SendMMSynergie.ChangeParameter(25, 10, 0);
        } else if(GlobalVariable.SV1pos1 == 5) { //WIG
            SendMMSynergie.ChangeParameter(25, 10, 0);
            delayInMilli(200);
            SendMMSynergie.ChangeParameter(25, 10, 0);
        }else Log.i("Verfahren mode ","MMSynergy");*/
    }

    public static void MMPuls_btn_onclick(){

        /*DatenObjekteSend SendMMPuls = new DatenObjekteSend();
        if(GlobalVariable.SV1pos1 == 4){ // Elektrode
            SendMMPuls.ChangeParameter(29,5,0);
            delayInMilli(200);
            SendMMPuls.ChangeParameter(29,5,0);
            delayInMilli(200);
            SendMMPuls.ChangeParameter(29,5,0);
            delayInMilli(200);
            SendMMPuls.ChangeParameter(29,5,0);
        }else if(GlobalVariable.SV1pos1 == 2){ // Synergie
            SendMMPuls.ChangeParameter(29,5,0);
        }else if(GlobalVariable.SV1pos1 == 1){ // Normal
            SendMMPuls.ChangeParameter(29,5,0);
            delayInMilli(200);
            SendMMPuls.ChangeParameter(29,5,0);
        } else if(GlobalVariable.SV1pos1 == 5) { //WIG
            SendMMPuls.ChangeParameter(29,5,0);
            delayInMilli(200);
            SendMMPuls.ChangeParameter(29,5,0);
            delayInMilli(200);
            SendMMPuls.ChangeParameter(29,5,0);
        }else Log.i("Verfahren mode ","MMPuls");*/
    }

    public static void ElektrodeMMA_btn_onclick(){

        /*DatenObjekteSend SendElectrodeMMA = new DatenObjekteSend();
        if(GlobalVariable.SV1pos1 == 3){ // Puls
            SendElectrodeMMA.ChangeParameter(30,30,0);
        }else if(GlobalVariable.SV1pos1 == 2){ // Synergie
            SendElectrodeMMA.ChangeParameter(30,30,0);
            delayInMilli(200);
            SendElectrodeMMA.ChangeParameter(30,30,0);
        }else if(GlobalVariable.SV1pos1 == 1){ // Normal
            SendElectrodeMMA.ChangeParameter(30,30,0);
            delayInMilli(200);
            SendElectrodeMMA.ChangeParameter(30,30,0);
            delayInMilli(200);
            SendElectrodeMMA.ChangeParameter(30,30,0);
        } else if(GlobalVariable.SV1pos1 == 5) { //WIG
            SendElectrodeMMA.ChangeParameter(30,30,0);
            delayInMilli(200);
            SendElectrodeMMA.ChangeParameter(30,30,0);
            delayInMilli(200);
            SendElectrodeMMA.ChangeParameter(30,30,0);
            delayInMilli(200);
            SendElectrodeMMA.ChangeParameter(30,30,0);
        }else Log.i("Verfahren mode ","ElektrodeMMA");*/
    }

    public static void WIG_btn_onclick(){
        //Log.i("WIG","called");

        /*DatenObjekteSend SendWIG = new DatenObjekteSend();
        if(GlobalVariable.SV1pos1 == 4) { // Elektrode
            SendWIG.ChangeParameter(28,5,0);
        }else if(GlobalVariable.SV1pos1 == 3){ //Puls
            SendWIG.ChangeParameter(28,5,0);
            delayInMilli(200);
            SendWIG.ChangeParameter(28,5,0);
        }else if(GlobalVariable.SV1pos1 == 2) { //Synergie
            SendWIG.ChangeParameter(28, 5, 0);
            delayInMilli(200);
            SendWIG.ChangeParameter(28, 5, 0);
            delayInMilli(200);
            SendWIG.ChangeParameter(28, 5, 0);
        } else if(GlobalVariable.SV1pos1 == 1) { //Normal
            SendWIG.ChangeParameter(28, 5, 0);
            delayInMilli(200);
            SendWIG.ChangeParameter(28, 5, 0);
            delayInMilli(200);
            SendWIG.ChangeParameter(28, 5, 0);
            delayInMilli(200);
            SendWIG.ChangeParameter(28, 5, 0);
        }else Log.i("Verfahren mode ","WIG");*/
    }

    /**
     * Send to Can multiple time
     * @param iteration
     * @param num number of parameters in DatenObjekteSend
     * @param value value of Value Frame
     * @param mode 0 or 1
     */
    public static void callChangeParameter(int iteration, int num, int value,int mode){
        DatenObjekteSend sendToDatenObjekte = new DatenObjekteSend();
        if(iteration==1){
            sendToDatenObjekte.ChangeParameter(num,value,mode);
        }else if(iteration==2){
            sendToDatenObjekte.ChangeParameter(num,value,mode);
            GlobalVariable.delayInMilli(100);
            sendToDatenObjekte.ChangeParameter(num,value,mode);
        }else if(iteration==3) {
            sendToDatenObjekte.ChangeParameter(num, value, mode);
            GlobalVariable.delayInMilli(100);
            sendToDatenObjekte.ChangeParameter(num, value, mode);
            GlobalVariable.delayInMilli(100);
            sendToDatenObjekte.ChangeParameter(num, value, mode);
        }
    }


}