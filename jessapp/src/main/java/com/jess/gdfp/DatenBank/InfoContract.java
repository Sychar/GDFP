/**
 * *@Autor Sakhr Aljendi
 * @Version 1.0
 * 08.01.2020
 * */
package com.jess.gdfp.DatenBank;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * @klass
 * wir brauchen hier müssen wir kein instanze erzugen
 * deswegen die klass ist final
 * die aufgabe der klass ist
 */
public final class InfoContract {
    private InfoContract(){}

    public static final String CONTEN_AUTHORITY ="com.jess.gdfp.infos" ;
    public static final Uri BASE_CONTENT_URI =Uri.parse("content://"+CONTEN_AUTHORITY);
    public static final String PATH_INFOS ="infos";


    public static final class infoEntry implements BaseColumns {
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_INFOS);
        public static final String TABEL_NAME = "info";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_TIME_ = "time";
        public static final String Verfahren = "Verfahren";
        public static final String Betriebsart = "Betriebsart";
        public static final String Drahtdurchmesser = "Drahtdurchmesser";
        public static final String Gas = "Gas";

        public static final String Werkstoff = "Werkstoff";

        public static final String Reglertyp = "Reglertyp";


        public static final String StatusMSR = "Status_MSR";

        public static final String StatusFLG = "Status_FLG";

        public static final String kennliniennummer = "kennliniennummer";

        public static final String Jobnummer = "Jobnummer";

        public static final String kennlinienTyp = "kennlinien_Typ";

        public static final String JobKommando = "Job_Kommando";

        public static final String JobStatus = "Job_Status";

        public static final String Verriegelungsstufe = "Verriegelungsstufe";

        public static final String Gasvorströmen = "Gasvorströmen";

        public static final String Gasnachströmen = "Gasnachströmen";

        public static final String Einschleichenabsolut = "Einschleichen_absolut";

        public static final String EinschleichenKorrektur = "Einschleichen_Korrektur_in";

        public static final String UPSlope = "UP_Slope";
        public static final String DownSlope = "Down_Slope";

        public static final String Zündenergiein = "euündenergiein";

        public static final String Endkraterenergie = "Endkraterenergie";

        public static final String GebirgeStatus = "Gebirge_Status";

        public static final String SchweißState = "Schweiß_State";


        public static final String Freibrand = "Freibrand";

        public static final String FreibrandKorrektur = "Freibrand_Korrektur";

        public static final String KorrekturPulsamplitude = "Korrektur_Pulsamplitude";

        public static final String KorrekturDrossel = "Korrektur_Drossel";

        public static final String Einfädeln = "Einfeadeln";

        public static final String GastestZeit = "Gastest_Zeit";


        public static final String Pausenzeitbeiinterval = "Pausenzeit_bei_interval";


        public static final String Punktzeit = "Punktzeit";
        public static final String ZündDauer = "Zünd_Dauer";
        public static final String EndkraterDauer = "Endkrater_Dauer";
        public static final String SynergieVorgabeBasis = "Synergie_Vorgabe_Basis";

        public static final String AnzahlLeistungsmodule = "Anzahl_Leistungsmodule";
        public static final String PowerpulsEinAus = "Powerpuls_Ein_Aus";

        public static final String PowerpilsEnergie = "Powerpils_Energie_2";

        public static final String PowerPulsTime1fürEnergie1 = "Power_Puls_Time_1_für_Energie_1";

        public static final String PowerPulsTime2fürEnergie1 = "Power_Puls_Time_2_für_Energie_1";

        public static final String PowerPulsLBKorrEnergie2 = "Power_Puls_LB_Korr_Energie_2";

        public static final String PowerPulsUpSlope = "PowerPulsUp_Slope";

        public static final String PowerPulsDownSlope = "Power_Puls_Down_Slope";

        public static final String JobSlope = "Job_Slope";

        public static final String ZündStrom = "Zeund_Strom";

        public static final String ZündSpannung = "Zeund_Spannung";
        public static final String ZündEnergie = "Zeund_Energie";

        public static final String ZündDrossel = "Zeund_Drossel";

        public static final String ZündLichtbogenkorrektur = "Zeund_Lichtbogenkorrektur";

        public static final String Strom1 = "Strom_1";

        public static final String Spannung1 = "Spannung_1";

        public static final String Energie1 = "Energie_1";

        public static final String Drossel1 = "Drossel_1";

        public static final String Lichtbogenkorrektur1 = "Lichtbogenkorrektur_1";

        public static final String Strom2 = "Strom_2";

        public static final String Spannung2 = "Spannung_2";

        public static final String Energie2 = "Energie_2";

        public static final String Drossel2 = "Drossel_2";

        public static final String Lichtbogenkorrektur2 = "Lichtbogenkorrektur_2";

        public static final String Strom3 = "Strom_3";

        public static final String Spannung3 = "Spannung_3";

        public static final String Energie3 = "Energie_3";

        public static final String Drossel3 = "Drossel_3";

        public static final String Lichtbogenkorrektur3 = "Lichtbogenkorrektur_3";

        public static final String Endkraterstrom = "Endkrater_strom";


        public static final String EndkraterSpannung = "Endkrater_Spannung";

        public static final String EndkraterEnergie = "Endkrater_Energie";

        public static final String EndkraterDrossel = "Endkrater_Drossel";

        public static final String EndkraterLichtbogenkorrektur = "Endkrater_Lichtbogenkorrektur";
        public static final String VorschubSetwert = "Vorschub_Setwert";

        public static final String Vorschubistwert = "Vorschub_istwert";

        public static final String VorschubHoldwert = "Vorschub_Holdwert";

        public static final String VorschubStatus = "Vorschub_Status";

        public static final String VorschubausKennlinie = "Vorschub_aus_Kennlinie";

        public static final String StromSetwert = "Strom_Setwert";

        public static final String Stromistwert = "Strom_istwert";

        public static final String StromHoldwert = "Strom_Holdwert";

        public static final String StromStatus = "Strom_Status";

        public static final String StromInkremental = "Strom_Inkremental";

        public static final String SpannungSetwert = "Spannung_Setwert";

        public static final String SpannungIstwert = "Spannung_Istwert";

        public static final String SpannungHoldwert = "Spannung_Holdwert";

        public static final String SpannungStatus = "Spannung_Status";

        public static final String SpannungInkremental = "Spannung_Inkremental";

        public static final String BlechdickeSetwert = "Blechdicke_Setwert";

        public static final String BlechdickeIstwert = "Blechdicke_Istwert";

        public static final String BlechdickeHoldwert = "Blechdicke_Holdwert";

        public static final String BlechdickeStatus = "Blechdicke_Status";

        public static final String Reset = "Reset";

        public static final String ElektrodeStromSetwert = "Elektrode_Strom_Setwert";

        public static final String ElektrodeStromIstwert = "Elektrode_Strom_Istwert";

        public static final String HotstartDauer = "Hotstart_Dauer";

        public static final String Hotstart = "Hotstart";

        public static final String ArcForce = "Arc_Force";

        public static final String InnenwiderstandfürElektrode = "Innenwiderstand_für_Elektrode";

        public static final String RMTPosAmplitude = "RMT_Pos_Amplitude";

        public static final String RMTNegAmplitude = "RMT_Neg_Amplitude";
        public static final String StartAmplitude = "Start_Amplitude";

        public static final String StartZeit = "Start_Zeit";

        public static final String Startueberhoehung = "Start_euberhöhung";

        public static final String InnenwiderstandfürDossel = "Innenwiderstand_feur_Dossel";

        public static final String Überblendzeit = "ueberblendzeit";

        public static final String DrosselAbfall = "Drossel_Abfall";

        public static final String MotorFlanke = "Motor_Flanke";

        public static final String DrosselDynamic = "Drossel_Dynamic";

        public static final String MAGACPositiveZeit = "MAG_AC_Positive_Zeit";

        public static final String MAGACStromschwellwert = "MAG_AC_Stromschwellwert";

        public static final String LBRMode = "LBR_Mode";

        public static final String MAGACBetriebsart = "MAG_AC_Betriebsart";

        public static final String MAGACKältewert = "MAG_AC_Kältewert";

        public static final String MAGACNegativZeit = "MAG_AC_Negativ_Zeit";

        public static final String MAGACKurzschlusserkennung = "MAG_AC_Kurzschlusserkennung";

        public static final String MAGACKurzschlussaufhebung = "MAG_AC_Kurzschlussaufhebung";

        public static final String MAGAGVerweilzeitPosNeg = "MAG_AGVerweilzeit_Pos_Neg";

        public static final String MAGAGVerweilzeitNegPos = "MAG_AGVerweilzeit_Neg_Pos";

        public static final String WIGSpeedPulsFrequenz = "WIG_Speed_Puls_Frequenz";

        public static final String WIGSpeedPulsI1Anteil = "WIG_Speed_Puls_I1_Anteil";

        public static final String WIGSpeedPulsI3 = "WIG_Speed_Puls_I3";

        public static final String GasSollwert = "Gas_Sollwert";

        public static final String UserNummer = "User_Nummer";

        public static final String WIGACStromoffset = "WIG_AC_Stromoffset";

        public static final String WIGstatus = "WIG_status";

        public static final String WIGACFrequenz = "WIGACFrequenz";

        public static final String WIGACBalance = "WIG_AC_Balance";

        public static final String WIGDurchmesserWolframElektrode = "WIG_Durchmesser_Wolfram_Elektrode";

        public static final String WIGBetriebsartWechselrichtwer = "WIG_Betriebsart_Wechselrichtwer";

        public static final String KaltdrahtpulsenT1 = "Kaltdraht_pulsen_T1";

        public static final String WIGStromLimit = "WIG_Strom_Limit";

        public static final String KHMode = "KH_Mode";
        public static final String VerzögerungsZeitKaltdrahtEin = "Verzögerungs_Zeit_Kaltdraht_Ein";

        public static final String VerzögerungsZeitKaltdrahtAus = "Verzögerungs_Zeit_Kaltdraht_Aus";

        public static final String VerzögerungsZeitHeißdrahteuberwachung = "Verzoegerungs_Zeit_Heißdraht_Überwachung";

        public static final String Vorpositionierungsstrecke = "Vorpositionierungsstrecke";

        public static final String Rückzugsstrecke = "Reuckzugsstrecke";

        public static final String KH_Status = "KH_Status";
























    }
}
