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

    public static final String CONTEN_AUTHORITY ="com.example.android.infos" ;
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


        public static final String StatusMSR = "Status MSR";

        public static final String StatusFLG = "Status FLG";

        public static final String kennliniennummer = "kennliniennummer";

        public static final String Jobnummer = "Jobnummer";

        public static final String kennlinienTyp = "kennlinien_Typ";

        public static final String JobKommando = "Job -Kommando";

        public static final String JobStatus = "Job-Status";

        public static final String Verriegelungsstufe = "Verriegelungsstufe";

        public static final String Gasvorströmen = "Gasvorströmen";

        public static final String Gasnachströmen = "Gasnachströmen";

        public static final String Einschleichenabsolut = "Einschleichen absolut";

        public static final String EinschleichenKorrektur = "Einschleichen Korrektur in %";

        public static final String UPSlope = "UP - Slope";
        public static final String DownSlope = "Down - Slope";

        public static final String Zündenergiein = "Zündenergie in %";

        public static final String Endkraterenergie = "Endkraterenergie in %";

        public static final String GebirgeStatus = "Gebirge-Status";

        public static final String SchweißState = "Schweiß-State (State - Machine)";


        public static final String Freibrand = "Freibrand";

        public static final String FreibrandKorrektur = "Freibrand Korrektur";

        public static final String KorrekturPulsamplitude = "Korrektur Pulsamplitude";

        public static final String KorrekturDrossel = "Korrektur Drossel";

        public static final String Einfädeln = "Einfädeln";

        public static final String GastestZeit = "Gastest-Zeit";


        public static final String Pausenzeitbeiinterval = "Pausenzeit bei interval";


        public static final String Punktzeit = "Punktzeit";
        public static final String ZündDauer = "Zünd - Dauer";
        public static final String EndkraterDauer = "Endkrater Dauer";
        public static final String SynergieVorgabeBasis = "Synergie Vorgabe-Basis";

        public static final String AnzahlLeistungsmodule = "Anzahl Leistungsmodule";
        public static final String PowerpulsEinAus = "Powerpuls Ein/Aus";

        public static final String PowerpilsEnergie = "Powerpils Energie 2";

        public static final String PowerPulsTime1fürEnergie1 = "Power - Puls Time 1 für Energie 1";

        public static final String PowerPulsTime2fürEnergie1 = "Power - Puls Time 2 für Energie 1";

        public static final String PowerPulsLBKorrEnergie2 = "Power - Puls LB-Korr. Energie 2";

        public static final String PowerPulsUpSlope = "PowerPulsUp-Slope";

        public static final String PowerPulsDownSlope = "Power - Puls     Down-Slope";

        public static final String JobSlope = "Job-Slope";

        public static final String ZündStrom = "Zünd - Strom";

        public static final String ZündSpannung = "Zünd - Spannung";
        public static final String ZündEnergie = "Zünd - Energie";

        public static final String ZündDrossel = "Zünd - Drossel";

        public static final String ZündLichtbogenkorrektur = "Zünd - Lichtbogenkorrektur";

        public static final String Strom1 = "Strom 1";

        public static final String Spannung1 = "Spannung 1";

        public static final String Energie1 = "Energie 1";

        public static final String Drossel1 = "Drossel 1";

        public static final String Lichtbogenkorrektur1 = "Lichtbogenkorrektur 1";

        public static final String Strom2 = "Strom 2";

        public static final String Spannung2 = "Spannung 2";

        public static final String Energie2 = "Energie 2";

        public static final String Drossel2 = "Drossel 2";

        public static final String Lichtbogenkorrektur2 = "Lichtbogenkorrektur 2";

        public static final String Strom3 = "Strom 3";

        public static final String Spannung3 = "Spannung 3";

        public static final String Energie3 = "Energie 3";

        public static final String Drossel3 = "Drossel 3";

        public static final String Lichtbogenkorrektur3 = "Lichtbogenkorrektur 3";

        public static final String Endkraterstrom = "Endkrater strom";


        public static final String EndkraterSpannung = "Endkrater Spannung";

        public static final String EndkraterEnergie = "Endkrater Energie";

        public static final String EndkraterDrossel = "Endkrater Drossel";

        public static final String EndkraterLichtbogenkorrektur = "Endkrater Lichtbogenkorrektur";
        public static final String VorschubSetwert = "Vorschub Setwert";

        public static final String Vorschubistwert = "Vorschub istwert";

        public static final String VorschubHoldwert = "Vorschub Holdwert";

        public static final String VorschubStatus = "Vorschub Status";

        public static final String VorschubausKennlinie = "Vorschub aus Kennlinie";

        public static final String StromSetwert = "Strom Setwert";

        public static final String Stromistwert = "Strom istwert";

        public static final String StromHoldwert = "Strom Holdwert";

        public static final String StromStatus = "Strom Status";

        public static final String StromInkremental = "Strom Inkremental";

        public static final String SpannungSetwert = "Spannung Setwert";

        public static final String SpannungIstwert = "Spannung Istwert";

        public static final String SpannungHoldwert = "Spannung Holdwert";

        public static final String SpannungStatus = "Spannung Status";

        public static final String SpannungInkremental = "Spannung Inkremental";

        public static final String BlechdickeSetwert = "Blechdicke Setwert";

        public static final String BlechdickeIstwert = "Blechdicke Istwert";

        public static final String BlechdickeHoldwert = "Blechdicke Holdwert";

        public static final String BlechdickeStatus = "Blechdicke Status";

        public static final String Reset = "Reset";

        public static final String ElektrodeStromSetwert = "Elektrode Strom Setwert";

        public static final String ElektrodeStromIstwert = "Elektrode Strom Istwert";

        public static final String HotstartDauer = "Hotstart Dauer";

        public static final String Hotstart = "Hotstart";

        public static final String ArcForce = "Arc-Force";

        public static final String InnenwiderstandfürElektrode = "Innenwiderstand für Elektrode";

        public static final String RMTPosAmplitude = "RMT Pos. Amplitude";

        public static final String RMTNegAmplitude = "RMT Neg. Amplitude";
        public static final String StartAmplitude = "Start-Amplitude";

        public static final String StartZeit = "Start_Zeit";

        public static final String Startueberhoehung = "Start-Überhöhung";

        public static final String InnenwiderstandfürDossel = "Innenwiderstand für Dossel";

        public static final String Überblendzeit = "Überblendzeit";

        public static final String DrosselAbfall = "Drossel-Abfall";

        public static final String MotorFlanke = "Motor-Flanke";

        public static final String DrosselDynamic = "Drossel Dynamic";

        public static final String MAGACPositiveZeit = "MAG-AC Positive Zeit";

        public static final String MAGACStromschwellwert = "MAG-AC Stromschwellwert";

        public static final String LBRMode = "LBR-Mode";

        public static final String MAGACBetriebsart = "MAG-AC Betriebsart";

        public static final String MAGACKältewert = "MAG-AC Kältewert";

        public static final String MAGACNegativZeit = "MAG-AC Negativ-Zeit";

        public static final String MAGACKurzschlusserkennung = "MAG-AC Kurzschlusserkennung";

        public static final String MAGACKurzschlussaufhebung = "MAG-AC Kurzschlussaufhebung";

        public static final String MAGAGVerweilzeitPosNeg = "MAG-AGVerweilzeit Pos/Neg";

        public static final String MAGAGVerweilzeitNegPos = "MAG-AGVerweilzeit Neg/Pos";

        public static final String WIGSpeedPulsFrequenz = "WIG Speed -Puls Frequenz";

        public static final String WIGSpeedPulsI1Anteil = "WIG Speed -Puls I1-Anteil";

        public static final String WIGSpeedPulsI3 = "WIG Speed-Puls I3";

        public static final String GasSollwert = "Gas - Sollwert";

        public static final String UserNummer = "User-Nummer";

        public static final String WIGACStromoffset = "WIG AC-Stromoffset";

        public static final String WIGstatus = "WIG status";

        public static final String WIGACFrequenz = "WIGACFrequenz";

        public static final String WIGACBalance = "WIG AC-Balance";

        public static final String WIGDurchmesserWolframElektrode = "WIG Durchmesser Wolfram-Elektrode";

        public static final String WIGBetriebsartWechselrichtwer = "WIG Betriebsart Wechselrichtwer";

        public static final String KaltdrahtpulsenT1 = "Kaltdraht pulsen T1";

        public static final String WIGStromLimit = "WIG Strom-Limit";

        public static final String KHMode = "KH-Mode";
        public static final String VerzögerungsZeitKaltdrahtEin = "Verzögerungs-Zeit Kaltdraht Ein";

        public static final String VerzögerungsZeitKaltdrahtAus = "Verzögerungs-Zeit Kaltdraht Aus";

        public static final String VerzögerungsZeitHeißdrahteuberwachung = "Verzögerungs-Zeit Heißdraht-Überwachung";

        public static final String Vorpositionierungsstrecke = "Vorpositionierungsstrecke";

        public static final String Rückzugsstrecke = "Rückzugsstrecke";

        public static final String KH_Status = "KH_Status";
























    }
}
