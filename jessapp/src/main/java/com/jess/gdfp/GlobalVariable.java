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
    public static int DRAHTDURCHMESSER_MODE = 0;
    public static int GAS_MODE = 0;
    public static int WERKSTOFF_MODE = 0;
    public static int BETRIEBSART_MODE = 0;
    public static int MENU_MODE = 0;
    public static int JOB_MODE = 0;
    public static int SETTING_MODE = 0;
    public static int DRAHTDURCHMESSER = 1;
    public static int GAS = 1;
    public static int VERFAHREN = 1;
    public static int WERKSTOFF = 1;
    public static int BETRIEBSART = 1;

    public static boolean JOB_NUM_TOKEN = false;
    public static boolean JOB_PRESSED = false;
    public static boolean MENU_PRESSED = false;
    public static boolean SETTING_PRESSED = false;
    public static boolean INSIDE_JOB = false;
    public static boolean INSIDE_MENU = false;
    public static boolean INSIDE_SETTING = false;
    public static boolean ENCODER_PRESSED = false;
    public static boolean ENCODER2_PRESSED = false;
    public static int ENCODER2_COUNT = 0;
    public static int JOB_PRESSED_COUNTER = 0;
    public static int CONTROL_PANEL_MODE = 0;
    public static int CONTROL_PANEL_MODE1 = 0;
    public static int mm_a_display = 0;
    public static boolean gas_token = false;
    public static boolean werkstoff_token = false;
    public static boolean Drahtdurchmesser_token = false;
    public static boolean Drossel_token = false;
    public static boolean job_token = false;
    public static boolean voltage_token = false;
    public static boolean encoder = false;
    public static boolean Betriebsart_Token = false;
    public static boolean Menu_Token = false;
    public static boolean Job_Token = false;
    public static boolean Setting_Token = false;
    public static boolean Hold_Token = false;
    public static int countHold = 0;
    public static boolean Verfahren_Token = false;
    public static boolean Drahtdurchmesser_Token = false;
    public static boolean Gas_Token = false;
    public static boolean Werkstoff_Token = false;
    public static boolean Kennlinie_Token = false;
    public static boolean JOBUSER_TOKEN = false;
    public static boolean KENN_TOKEN = false;
    public static boolean Load_Job = false;
    public static boolean Save_Job = false;
    public static boolean MOL_gedrückt = false;
    public static boolean MOR_gedrückt = false;
    public static boolean Methode_Token = false;
    public static boolean Methode_Verfahren = false;
    public static boolean Methode_Werkstoff = false;
    public static boolean Methode_Drahtdurchmesser = false;
    public static boolean Methode_Betriebsart = false;
    public static boolean Methode_Gas = false;

    public static boolean SETTING_TOKEN = false;
    public static boolean CHANGE_TOKEN = false;
    public static int SETTING_COUNTER = 0;
    public static int JOBBTN_COUNTER = 0;
    public static int KENNBTN_COUNTER = 0;
    public static int VERFAHREN_COUNTER = 0;
    public static int WERKSTOFF_COUNTER = 0;
    public static int DRAHTDURCHMESSER_COUNTER = 0;
    public static int BETRIEBSART_COUNTER = 0;
    public static int GAS_COUNTER = 0;
    public static int JOBCOUNT = 0;

    public static int VERFAHREN_VAL = 0;
    public static int mpm_display = 0;
    public static int blechdicke_display = 0;
    public static int voltage_display = 0;
    public static int Mirror = 0;
    public static int mirror_display = 0;

    //-------------------------------- Variables for daten objekte ---------------------------------
    //SV 1
    public static String Verfahren = "";
    public static String Betriebsart = "";
    public static String Gas = "";
    public static String Werkstoff;
    public static String Reglertyp;
    public static String StatusMSR_Bit1 = "";
    public static String StatusMSR_Bit2 = "";
    public static String StatusMSR_Bit3 = "";
    public static String StatusMSR_Bit4 = "";
    public static String StatusMSR_Bit5 = "";
    public static String StatusMSR_Bit6 = "";
    public static String StatusMSR_Bit7 = "";
    public static int StatusMSR;
    public static String StatusMSR_BT;
    public static String StatusFLG_String;
    public static int StatusFLG;
    public static int SV1pos1;
    public static int SV1pos2;
    public static int Drahtdurchmesser;

    public static String[] Draht_String = {"NONE","0.6 Ø","0.8 Ø","0.9 Ø","1.0 Ø","1.2 Ø","1.4 Ø","1.6 Ø","2.0 Ø","2.4 Ø","Spez"};
    public static String[] Betriebsart_String = {"NONE","2-Takt","4-Takt","4-TaktS","Programm","Punkten",
            "Intervall","Extern","2-Takt + HF","4-Takt + HF"};
    public static String[] Verfahren_String = {"NONE","MAG_Normal","MAG-Synergie","MAG Puls","Elektrode","WIG","WIG-Puls",
            "WIG-Speed-Puls","WIG-Speed_Puls + Puls","HC_MAG"};
    public static String[] Gas_String = {"82% Argon 18% CO₂","98% Argon 2% CO₂","100% Argon","100% CO₂","92% Argon 8% CO₂","99% Argon 1% O₂",
            "98% Argon 2% O₂","97% Argon 3% O₂","92% Argon 8% O₂","90% Argon 5% O₂ 5% CO₂","100% Helium","80% Argon 20% He","69% Argon 30% He 1% O₂",
            "50% Argon 50% Helium","98% Argon 2% H₂","94% Argon 6% H₂","50% Argon 50% H₂","30% Argon 70% H₂","Spezial"};
    public static String[] Werksotff_String = {"NONE","Fe","CrNi","AlMg","AlSi","CuSi","AlMg3","AlMg5","AlMg4,5Mn","AlBz","Spezial"};
    public static int[] ChangeValue = {0,0,0,0,0};

    //-------------------------------------------- For Geberge Normal mode ------------------------------------------------------------------------
    public static String[] Geberge_Normal_Punkten = {"GASVOR [s]","EINSCHLEISEN [%]","ENERGIE1 [m/min]", //8
            "SPANNUNG1 [V]","DROSSEL1 [%]","PUNKTZ. [s]","FREIBRENNER [%]","GASNACH [s]"};
    public static String[] Geberge_Normal_2Takt = {"GASVOR [s]","EINSCHLEISEN [%]","ENERGIE1 [m/min]",//7
            "SPANNUNG [V]","DROSSEL","FREIBRENNER [%]","GASNACH [s]"};
    public static String[] Geberge_Normal_4Takt = {"GASVOR [s]","EINSCHLEISEN [%]","ENERGIE1 [m/min]",//7
            "SPANNUNG1 [V]","DROSSEL1 [%]","FREIBRENNER [%]","GASNACH [s]"};
    public static String[] Geberge_Normal_4TaktSonder = {"GASVOR [s]","EINSCHLEISEN [%]","ZÜNDENERGIE [m/min]",//17
            "ZÜNDSPANNUNG [V]","ZÜNDDROSSEL [%]","UP-SLP [s]","ENERGIE1 [m/min]","SPANNUNG1 [V]","DROSSEL1 [%]","ENERGIE2 [m/min]",
            "ENERGIE3 [m/min]","DOWN-SLP [s]","END. ENERGIE [m/min]","END. SPANNUNG [V]","END. DROSSEL [%]","FREIBRENNER [%]","GASNACH [s]"};
    //-------------------------------------------- For Geberge Synergie mode ------------------------------------------------------------------------
    public static String[] Geberge_Synergie_Punkten = {"GASVOR [s]","EINSCHLEISEN [%]","ZÜNDDAUER [s]",//10
            "ZÜNDENERGIE [m/min]","ZÜNDLBKORR [%]","PUNKTZ. [s]","POWERPULS","END. DAUER [s]","FREIBRENNER [%]","GASNACH [s]"};
    public static String[] Geberge_Synergie_2Takt = {"GASVOR [s]","EINSCHLEISEN [%]","ZÜNDDAUER",//9
            "ZÜNDENERGIE [m/min]","ZÜNDLBKORR [%]","POWERPULS","END. DAUER [s]","FREIBRENNER [%]","GASNACH [s]"};
    public static String[] Geberge_Synergie_4Takt = {"GASVOR [s]","EINSCHLEISEN [%]","DAUER1",//9
            "ENERGIE1 [m/min]","LBKORR","POWERPULS","DAUER2","FREIBRENNER [%]","GASNACH [s]"};
    public static String[] Geberge_Synergie_4TaktSonder = {"GASVOR [s]","EINSCHLEISEN [%]","ZÜNDENERGIE [m/min]",//17
            "ZÜNDLBKORR [%]","UP-SLP [s]","ENERGIE1 [m/min]","LBKORR1 [%]","POWERPULS","ENERGIE2 [m/min]","LBKORR2 [%]","ENERGIE3 [m/min]",
            "LBKORR3 [%]","DOWN-SLP [s]","End. Energie [m/min]","End. LBKORR [%]","FREIBRENNER [%]","GASNACH [s]"};
    //-------------------------------------------- For Geberge Puls mode -----------------------------------------------------------------------------
    public static String[] Geberge_Puls_Punkten = {"GASVOR [s]","EINSCHLEISEN [%]","ZÜNDDAUER [s]",//10
            "ENERGIE1 [m/min]","LBKORR [%]", "PUNKTZ. [s]","POWERPULS","END. DAUER [s]","FREIBRENNER [%]","GASNACH [s]"};
    public static String[] Geberge_Puls_2Takt = {"GASVOR [s]","EINSCHLEISEN [%]","DAUER1 [s]",//9
            "ENERGIE1 [m/min]","LBKORR [%]","POWERPULS","DAUER2 [s]","FREIBRENNER [%]","GASNACH [s]"};
    public static String[] Geberge_Puls_4Takt = {"GASVOR [s]","EINSCHLEISEN [%]","DAUER1 [s]",//9
            "ENERGIE1 [m/min]","LBKORR [%]","POWERPULS","DAUER2 [s]","FREIBRENNER [%]","GASNACH [s]"};
    public static String[] Geberge_Puls_4TaktSonder = {"GASVOR [s]","EINSCHLEISEN [%]",//15
            "ENERGIE [m/min]","LBKORR [%]","UP-SLP [s]","ENERGIE [m/min]","LBKORR [%]","POWERPULS","ENERGIE [m/min]",
            "ENERGIE [m/min]","DOWN-SLP [s]","ENERGIE/E1 [m/min]","LBKORR [%]","FREIBRENNER [%]","GASNACH [s]"};
    //-------------------------------------------------------------------------------------------------------------------------------------------------

    public static int Boot_time = 0;
    public static boolean Boot_token = false;

    public static float Geberge_XNorm4TaktS = 0;
    public static float Geberge_YNorm4TaktS = 0;
    public static float Geberge_XNorm24Takt = 0;
    public static float Geberge_YNorm24Takt = 0;
    public static float Geberge_XNormPunkt = 0;
    public static float Geberge_YNormPunkt = 0;

    public static float Geberge_XSyn4TaktS = 0;
    public static float Geberge_YSyn4TaktS = 0;
    public static float Geberge_XSyn24Takt = 0;
    public static float Geberge_YSyn24Takt = 0;
    public static float Geberge_XSynPunkt = 0;
    public static float Geberge_YSynPunkt = 0;

    public static float Geberge_XPuls4TaktS = 0;
    public static float Geberge_YPuls4TaktS = 0;
    public static float Geberge_XPuls24Takt = 0;
    public static float Geberge_YPuls24Takt = 0;
    public static float Geberge_XPulsPunkt = 0;
    public static float Geberge_YPulsPunkt = 0;

    public static int GEBERGE_NORMAL_PUNKTEN = 0;
    public static int GEBERGE_NORMAL_2TAKT = 0;
    public static int GEBERGE_NORMAL_4TAKT = 0;
    public static int GEBERGE_NORMAL_4TAKTSONDER = 0;
    public static int GEBERGE_SYNERGIE_PUNKTEN = 0;
    public static int GEBERGE_SYNERGIE_2TAKT = 0;
    public static int GEBERGE_SYNERGIE_4TAKT = 0;
    public static int GEBERGE_SYNERGIE_4TAKTSONDER = 0;
    public static int GEBERGE_PULS_PUNKTEN = 0;
    public static int GEBERGE_PULS_2TAKT = 0;
    public static int GEBERGE_PULS_4TAKT = 0;
    public static int GEBERGE_PULS_4TAKTSONDER = 0;
    public static boolean GEBERGE_PRESSED = false;

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
    public static String JobStatus_Bit0 = "";
    public static String JobStatus_Bit1 = "";
    public static String JobStatus_Bit2 = "";
    public static String JobStatus_Bit3 = "";
    public static String JobStatus_Bit4 = "";
    public static String JobStatus_Bit5 = "";
    public static String JobStatus_Bit6 = "";
    public static String JobStatus_Bit7 = "";
    public static byte Verriegelungsstufe;

    //SV 3
    public static int Gasvorströmen;
    public static boolean Gasvorströmen_token = false;
    public static byte Gasnachströmen;
    public static boolean Gasnachströmen_token = false;
    public static byte EinschleichenAbsolut;
    public static boolean EinschleichenAbsolut_token = false;
    public static byte EinschleichenKorrektur;
    public static boolean EinschleichenKorrektur_token = false;
    public static byte UpSlope;
    public static boolean UpSlope_token = false;
    public static byte DownSlope;
    public static boolean DownSlope_token = false;
    public static int Zündenergie;
    public static boolean Zündenergie_token = false;
    public static int Endkraterenergie;
    public static boolean Endkraterenergie_token = false;
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
    public static boolean Spannung1_token = false;
    public static int Energie1;
    public static boolean Energie1_token = false;
    public static byte Drossel1;
    public static boolean Drossel1_token = false;
    public static byte Lichtbogenkorrektur1;
    public static boolean Lichtbogenkorrektur1_token = false;
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

    //------------------ DVE DSP -----------------------------------
    public static byte StartZeit_WIG_DVE;
    public static byte EndZeit_WIG_DVE;
    public static byte Freibrand_DVE;
    public static byte FreibrandKorrektur_DVE;
    public static byte PulsamplitudeKorrektur_DVE;
    public static byte DrosselKorrektur_DVE;
    public static byte Einfaedeln_DVE;
    public static byte VorschubSollLow_DVE;
    public static byte VorschubSollHigh_DVE;
    public static byte VorschubEindchleichenLow_DVE;
    public static byte VorschubEindchleichenHigh_DVE;
    public static byte KHMode_DVE;
    public static byte VerzögerungZeitKDEin_DVE;
    public static byte VerzögerungZeitKDAus_DVE;
    public static byte VerzögerungHeißdrahtüberwachung_DVE;
    public static byte Vorpositionierung_DVE;
    public static byte Rückzug_DVE;
    public static byte KDPulsenT1_DVE;
    public static byte KHStatus_DVE;
    public static byte RI_DVE;
    public static byte Überblendzeit_DVE;
    public static byte Drosselabfall_DVE;
    public static byte Motorflanke_DVE;
    public static byte Drosseldyn_DVE;
    public static byte HCPosZeit_DVE;
    public static byte HCISchwelle_DVE;
    public static byte LBRmode_DVE;
    public static byte IndexMOTORIni_DVE;
    public static byte Status_DVE;
    public static byte StatusMSR_DVE;
    public static byte SeriennummerMonat_DVE;
    public static byte SeriennummerJahr_DVE;
    public static byte SeriennummerLSB_DVE;
    public static byte SeriennummerMSB_DVE;
    public static byte PCBKennungBestückung_DVE;
    public static byte DrahtvorschubistLow_DVE;
    public static byte DrahtvorschubistHigh_DVE;
    public static byte SchweissspgLow_DVE;
    public static byte SchweissspgHigh_DVE;
    public static byte Brennertasten2_DVE;
    public static byte Optionen2_DVE;
    public static byte VorschubStatus0_DVE;
    public static byte VorschubStatus1_DVE;
    public static byte PistoleIstLow_DVE;
    public static byte PistoleIstHigh_DVE;
    public static byte SchweissstromistLow_DVE;
    public static byte SchweissstromistHigh_DVE;
    public static byte DrahtgeschwindigkeitLow_DVE;
    public static byte DrahtgeschwindigkeitHigh_DVE;
    public static byte DurchflussLow_DVE;
    public static byte DurchflussHigh_DVE;
    public static byte Status1_DVE;
    public static byte SeriennummerMonat1_DVE;
    public static byte SeriennummerJahr1_DVE;
    public static byte SeriennummerLSB1_DVE;
    public static byte SeriennummerMSB1_DVE;
    public static byte PCBKennungBestückung1_DVE;

    //---------------------------------------- IDSP ------------------------------------------------
    public static byte EffektivstromLow_IDSP;
    public static byte EffektivstromHigh_IDSP;
    public static byte EffektivspannungLow_IDSP;
    public static byte EffektivspannungHigh_IDSP;
    public static byte LüfterdrehzahlLow_IDSP;
    public static byte LüfterdrehzahlHigh_IDSP;
    public static byte StartStop_IDSP;
    public static byte Stromfließt_IDSP;
    public static byte Machinentyp_IDSP;
    public static byte Modulzahl_IDSP;
    public static byte Statusflag1_IDSP;
    public static byte Statusflag2_IDSP;
    public static byte Statusflag3_IDSP;
    public static byte Statusflag4_IDSP;
    public static byte StromanteiliDSP1;
    public static byte StromanteiliDSP2;
    public static byte StromanteiliDSP3;
    public static byte StromanteiliDSP4;
    public static byte StromanteiliDSP5;
    public static byte StromanteiliDSP6;
    public static byte StromanteiliDSP7;
    public static byte StromanteiliDSP8;
    public static byte Index_IDSP;
    public static byte Zugriffsart_IDSP;
    public static byte Status_IDSP;
    public static byte SeriennummerMonat_IDSP;
    public static byte SeriennummerJahr_IDSP;
    public static byte SeriennummerLSB_IDSP;
    public static byte SeriennummerMSB_IDSP;
    public static byte PCBKennung_Bestückung_IDSP;
    public static byte Status1_IDSP;
    public static byte Status2_IDSP;
    public static byte Status3_IDSP;
    public static byte Temperatur1_IDSP;
    public static byte Temperatur2_IDSP;
    public static byte ZwischenKreisspgLow_IDSP;
    public static byte ZwischenKreisspgHigh_IDSP;
    public static byte PrimärstromLow_IDSP;
    public static byte PrimärstromHigh_IDSP;
    public static byte Index1_IDSP;
    public static byte Zugriffsart1_IDSP;
    public static byte Status4_IDSP;
    public static byte Maschinentyp_IDSP;
    public static byte SeriennummerMonat1_IDSP;
    public static byte SeriennummerJahr1_IDSP;
    public static byte SeriennummerLSB1_IDSP;
    public static byte SeriennummerMSB1_IDSP;
    public static byte PCBKennung_Bestückung1_IDSP;

    //------------------------------------ Regler DSP ----------------------------------------------
    public static byte SollstromLow_ReglerDSP;
    public static byte SollstromHigh_ReglerDSP;
    public static byte SollspannungLow_ReglerDSP;
    public static byte SollspannungHigh_ReglerDSP;
    public static byte Drossel_ReglerDSP;
    public static byte Reglertyp_ReglerDSP;
    public static byte Betriebsart_ReglerDSP;
    public static byte StartStop_ReglerDSP;
    public static byte LichtbogenLängeLow_ReglerDSP;
    public static byte LichtbogenLängeHigh_ReglerDSP;
    public static byte LichtbogenKorrektur1_ReglerDSP;
    public static byte LichtbogenKorrektur2_ReglerDSP;
    public static byte LichtbogenKorrektur3_ReglerDSP;
    public static byte LichtbogenKorrektur4_ReglerDSP;
    public static byte LichtbogenKorrektur5_ReglerDSP;
    public static byte Endpulsdauer_ReglerDSP;
    public static byte PositiveAmplitude_ReglerDSP;
    public static byte NegativeAmplitude_ReglerDSP;
    public static byte Endpulshöhe_ReglerDSP;
    public static byte Abfall_ReglerDSP;
    public static byte Dynnamik_ReglerDSP;
    public static byte Überblendzeit_ReglerDSP;
    public static byte FrequenzLow_ReglerDSP;
    public static byte FrequenzHigh_ReglerDSP;
    public static byte Rundung_ReglerDSP;
    public static byte Zündzeit_ReglerDSP;
    public static byte ZündamplitudeLow_ReglerDSP;
    public static byte ZündamplitudeHigh_ReglerDSP;
    public static byte RU_ReglerDSP;
    public static byte RI_ReglerDSP;
    public static byte GrundstromLow_ReglerDSP;
    public static byte GrundstromHigh_ReglerDSP;
    public static byte Pulsamplitude1Low_ReglerDSP;
    public static byte Pulsamplitude1High_ReglerDSP;
    public static byte Pulsamplitude2Low_ReglerDSP;
    public static byte Pulsamplitude2High_ReglerDSP;
    public static byte Pulsamplitude3Low_ReglerDSP;
    public static byte Pulsamplitude3High_ReglerDSP;
    public static byte Slope1_ReglerDSP;
    public static byte Slope2_ReglerDSP;
    public static byte Slope3_ReglerDSP;
    public static byte Slope4_ReglerDSP;
    public static byte PulsZeit1_ReglerDSP;
    public static byte PulsZeit2_ReglerDSP;
    public static byte PulsZeit3_ReglerDSP;
    public static byte Startüberhöhung_ReglerDSP;
    public static byte AusgangskalierungStrom_ReglerDSP;
    public static byte AusgangskalierungSpannung_ReglerDSP;
    public static byte Status1_ReglerDSP;
    public static byte Status2_ReglerDSP;
    public static byte Status3_ReglerDSP;
    public static byte TemperaturSekDiode_ReglerDSP;
    public static byte TemperaturElko_ReglerDSP;
    public static byte ZwischenkreisspannungLow_ReglerDSP;
    public static byte ZwischenkreisspannungHigh_ReglerDSP;
    public static byte PrimärstromLow_ReglerDSP;
    public static byte PrimärstromHigh_ReglerDSP;
    public static byte Symmetriewert_ReglerDSP;
    public static byte Index_ReglerDSP;
    public static byte Zugriffsart_ReglerDSP;
    public static byte Index1_ReglerDSP;
    public static byte Zugriffsart1_ReglerDSP;
    public static byte Index2_ReglerDSP;
    public static byte Zugriffsart2_ReglerDSP;
    public static byte Status_ReglerDSP;
    public static byte Machinentyp_ReglerDSP;
    public static byte SeriennrLSBLW_ReglerDSP;
    public static byte SeriennrMSBLW_ReglerDSP;
    public static byte SeriennrLSBLW1_ReglerDSP;
    public static byte SeriennrMSBLW1_ReglerDSP;
    public static byte PCBKennungBestückung_ReglerDSP;
    public static byte EffektivstromLow_ReglerDSP;
    public static byte EffektivstromHigh_ReglerDSP;
    public static byte EffektivspannungLow_ReglerDSP;
    public static byte EffektivspannungHigh_ReglerDSP;
    public static byte LüfterdrehzahlLow_ReglerDSP;
    public static byte LüfterdrehzahlHigh_ReglerDSP;
    public static byte Effektivstrom3fachLow_ReglerDSP;
    public static byte Effektivstrom2fachHigh_ReglerDSP;
    public static byte StartStop1_ReglerDSP;
    public static byte Stromfließt_ReglerDSP;
    public static byte Maschinentyp_ReglerDSP;
    public static byte Modulanzahl_ReglerDSP;
    public static byte Statusflag1_ReglerDSP;
    public static byte Statusflag2_ReglerDSP;
    public static byte Statusflag3_iDSPs;
    public static byte Statusflag4_iDSPs;
    public static byte Stromanteil_iDSP1;
    public static byte Stromanteil_iDSP2;
    public static byte Stromanteil_iDSP3;
    public static byte Stromanteil_iDSP4;
    public static byte Stromanteil_iDSP5;
    public static byte Stromanteil_iDSP6;
    public static byte Stromanteil_iDSP7;
    public static byte Stromanteil_iDSP8;
    public static byte Stromanteil_iDSP9;
    public static byte Stromanteil_iDSP10;
    public static byte Stromanteil_iDSP11;
    public static byte Stromanteil_iDSP12;
    public static byte Stromanteil_iDSP13;
    public static byte Stromanteil_iDSP14;
    public static byte Stromanteil_iDSP15;
    public static byte Stromanteil_iDSP16;
    public static byte TemperaturNetzteil_ReglerDSP;
    public static byte Sekundärdrossel_ReglerDSP;
    public static byte Status_Fehlerstatus_ReglerDSP;
    public static byte Index3_ReglerDSP;
    public static byte Zugriffsart3_ReglerDSP;
    public static byte Index4_ReglerDSP;
    public static byte Zugriffsart4_ReglerDSP;
    public static byte Index5_ReglerDSP;
    public static byte Zugriffsart5_ReglerDSP;

    //----------------------------------- WIG Speedpuls Koffer1 ------------------------------------
    public static byte FrequenyLow_WIGSpeedpulsKoffer1;
    public static byte FrequenyHigh_WIGSpeedpulsKoffer1;
    public static byte I1Anteil_WIGSpeedpulsKoffer1;
    public static byte I3_WIGSpeedpulsKoffer1;
    public static byte ACOffset_WIGSpeedpulsKoffer1;

    //----------------------------------- WIG Speedpuls Koffer2 ------------------------------------
    public static byte FrequenyLow_WIGSpeedpulsKoffer2;
    public static byte FrequenyHigh_WIGSpeedpulsKoffer2;
    public static byte I1Anteil_WIGSpeedpulsKoffer2;
    public static byte I3_WIGSpeedpulsKoffer2;
    public static byte ACOffset_WIGSpeedpulsKoffer2;

    //----------------------------------- WIG AC Koffer1 ------------------------------------
    public static byte FrequenyLow_WIGACKoffer1;
    public static byte FrequenyHigh_WIGACKoffer1;
    public static byte Balance_WIGACKoffer1;
    public static byte Durchmesser_WIGACKoffer1;
    public static byte Wechselrichter_WIGACKoffer1;

    //----------------------------------- WIG AC Koffer2 ------------------------------------
    public static byte FrequenyLow_WIGACKoffer2;
    public static byte FrequenyHigh_WIGACKoffer2;
    public static byte Balance_WIGACKoffer2;
    public static byte Durchmesser_WIGACKoffer2;
    public static byte Wechselrichter_WIGACKoffer2;

    //----------------------------------------- Blech Koffer1 --------------------------------------

    public static byte BlechdickeSet_BlechKoffer1;
    public static byte BlechdickeIst_BlechKoffer1;
    public static byte BlechdickeHold_BlechKoffer1;
    public static byte BlechdickeStatus_BlechKoffer1;
    public static byte Reset_BlechKoffer1;

    //----------------------------------------- Blech Koffer2 --------------------------------------
    public static byte BlechdickeSet_BlechKoffer2;
    public static byte BlechdickeIst_BlechKoffer2;
    public static byte BlechdickeHold_BlechKoffer2;
    public static byte BlechdickeStatus_BlechKoffer2;
    public static byte Reset_BlechKoffer2;

    //---------------------------------- MACs Prozess1 Koffer1 -------------------------------------
    public static byte ACMixKurvenform_MACKoffer1;

    //---------------------------------- MACs Prozess1 Koffer2 -------------------------------------
    public static byte ACMixKurvenform_MACKoffer2;

    //------------------------------------- Istwert DVE1 -------------------------------------------
    public static byte DrahtvorschubLow_IstwertDVE1;
    public static byte DrahtvorschubHigh_IstwertDVE1;
    public static byte SchweissspannungLow_IstwertDVE1;
    public static byte SchweissspannungHigh_IstwertDVE1;
    public static byte Brennertasten_IstwertDVE1;
    public static byte Optionen_IstwertDVE1;
    public static byte Status0_IstwertDVE1;
    public static byte Status1_IstwertDVE1;

    //------------------------------------- Istwert DVE2 -------------------------------------------
    public static byte DrahtvorschubLow_IstwertDVE2;
    public static byte DrahtvorschubHigh_IstwertDVE2;
    public static byte SchweissspannungLow_IstwertDVE2;
    public static byte SchweissspannungHigh_IstwertDVE2;
    public static byte Brennertasten_IstwertDVE2;
    public static byte Optionen_IstwertDVE2;
    public static byte Status0_IstwertDVE2;
    public static byte Status1_IstwertDVE2;

    public static byte Index6_ReglerDSP;
    public static byte Zugriffsart6_ReglerDSP;
    public static byte Index7_ReglerDSP;
    public static byte Zugriffsart7_ReglerDSP;
    public static byte Index8_ReglerDSP;
    public static byte Zugriffsart8_ReglerDSP;
    public static byte Index9_ReglerDSP;
    public static byte Zugriffsart9_ReglerDSP;
    public static byte Status4_ReglerDSP;
    public static byte MSRE2Status_ReglerDSP;
    public static byte SeriennummerMonat_ReglerDSP;
    public static byte SeriennummerJahr_ReglerDSP;
    public static byte SeriennummerLSB_ReglerDSP;
    public static byte SeriennummerMSB_ReglerDSP;
    public static byte PCBKennungBestückung1_ReglerDSP;
    public static byte Index10_ReglerDSP;
    public static byte Zugriffsart10_ReglerDSP;
    public static byte Index11_ReglerDSP;
    public static byte Zugriffsart11_ReglerDSP;
    public static byte Index12_ReglerDSP;
    public static byte Zugriffsart12_ReglerDSP;
    public static byte Status5_ReglerDSP;
    public static byte Frei_ReglerDSP;
    public static byte SeriennummerMonat1_ReglerDSP;
    public static byte SeriennummerJahr1_ReglerDSP;
    public static byte SeriennummerLSB1_ReglerDSP;
    public static byte SeriennummerMSB1_ReglerDSP;
    public static byte PCBKennungBestückung2_ReglerDSP;
    public static byte Status6_ReglerDSP;

    //------------------------------------------- MACS ---------------------------------------------
    public static byte VorschubSollLow_MACS;
    public static byte VorschubSollHigh_MACS;
    public static byte VorschubEinschleichenLow_MACS;
    public static byte VorschubEinschleichenHigh_MACS;
    public static byte Brennertasten_MACS;
    public static byte Ventile_MACS;
    public static byte Vorschub0_MACS;
    public static byte Vorschub1_MACS;
    public static byte DrosselRI_MACS;
    public static byte UberblendZeit_MACS;
    public static byte DrosselAbfall_MACS;
    public static byte Motorflanke_MACS;
    public static byte DrosselDynamic_MACS;
    public static byte MACACPositivzeit_MACS;
    public static byte MACACStromschwelle_MACS;
    public static byte LBRMode_MACS;
    public static byte MACACBetriebsart_MACS;
    public static byte MACACKälte_MACS;
    public static byte MACACNegativzeitLow_MACS;
    public static byte MACACNegativzeitHigh_MACS;
    public static byte MACACKrurzschlusserkennung_MACS;
    public static byte MACACKrurzschlussaufhebung_MACS;
    public static byte MACACpos2negDelay_MACS;
    public static byte MACACneg2negDelay_MACS;
    public static byte Index_MACS;
    public static byte Zugriffsart_MACS;
    public static byte Status_MACS;
    public static byte StatusMSR_MACS;
    public static byte SeriennummerMonat_MACS;
    public static byte SeriennummerJahr_MACS;
    public static byte SeriennummerLSB_MACS;
    public static byte SeriennummerMSB_MACS;
    public static byte PCBKennungBestückung_MACS;
    public static byte UIstLowByte_MACS;
    public static byte UIstHighByte_MACS;
    public static byte IIstLowByte_MACS;
    public static byte IIstHighByte_MACS;
    public static byte Temperatur1_MACS;
    public static byte Temperatur2_MACS;
    public static byte StatusLow_MACS;
    public static byte StatusHigh_MACS;
    public static byte Index1_MACS;
    public static byte Zugriffsart1_MACS;
    public static byte Status1_MACS;
    public static byte StatusMSR1_MACS;
    public static byte SeriennummerMonat1_MACS;
    public static byte SeriennummerJahr1_MACS;
    public static byte SeriennummerLSB1_MACS;
    public static byte SeriennummerMSB1_MACS;
    public static byte PCBKennungBestückung1_MACS;
    //----------------------------------------------------------------------------------------------

    //------------------------------------------ Time ----------------------------------------------
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
    public static Boolean BT1_Interrupt = false;
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

    //---------------------------------- Get from Database -----------------------------------------
    public static int MIN_MPM = 0;
    public static int MAX_MPM = 0;

    //----------------------------------------------------------------------------------------------
    public static void delayInMilli(int DELAY_MILLISEC){
        try {
            Thread.sleep(DELAY_MILLISEC);

        }catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
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

    public static byte[] ChangeToJob (int jobnum){ //00 xx 28 00 3C 00 00 00 (xx is job number)
        int temp = 0;
        if (jobnum < 0) temp = 256 + jobnum;
        else temp = jobnum;
        byte[] TEMPBA = new byte[20];
        TEMPBA[0] = 36;
        TEMPBA[1] = 15;
        TEMPBA[2] = 2;
        TEMPBA[3] = 5;
        TEMPBA[4] = (byte) 56; //38
        TEMPBA[5] = 8;
        TEMPBA[6] = 0;
        TEMPBA[7] = (byte)temp;
        TEMPBA[8] = 40; //28
        TEMPBA[9] = 0;
        TEMPBA[10] = 60; //3C
        TEMPBA[10] = 0;
        TEMPBA[11] = 0;
        TEMPBA[12] = 0;
        TEMPBA[13] = 35;
        /**
         * Calculate the checksum of dataframe
         */
        int MYCHECKSUM = 0;

        for (int i = 0; i < 14; i++) {
            int tempcheck;
            if((TEMPBA[i])<0){
                tempcheck = 256 + (TEMPBA[i]);
            }else{
                tempcheck = TEMPBA[i];
            }
            MYCHECKSUM = MYCHECKSUM + tempcheck;
        }
        TEMPBA[13] = (byte)(MYCHECKSUM & 0x000000FF);
        TEMPBA[14] = 35;
        return TEMPBA;
    }

}