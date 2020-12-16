
/**
 * *@Autor Sakhr Aljendi
 * @Version 1.0
 * 08.01.2020
 */
package com.jess.gdfp.DatenBank;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
//import androidx.core.app.INotificationSideChannel;


public class InfoDataBase extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "infos.db";
    private static final String TYPE = " TEXT ";
    private static final String COMMA_SEP = ",";
    //hier wurde den context von create table const defeniert.
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + InfoContract.infoEntry.TABEL_NAME +
            "(" + InfoContract.infoEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            InfoContract.infoEntry.COLUMN_TIME_ + TYPE + COMMA_SEP +
            InfoContract.infoEntry.AnzahlLeistungsmodule + TYPE + COMMA_SEP +
            InfoContract.infoEntry.ArcForce + TYPE + COMMA_SEP +
            InfoContract.infoEntry.Betriebsart + TYPE + COMMA_SEP +
            InfoContract.infoEntry.BlechdickeHoldwert + TYPE + COMMA_SEP +
            InfoContract.infoEntry.BlechdickeIstwert + TYPE + COMMA_SEP +
            InfoContract.infoEntry.BlechdickeSetwert + TYPE + COMMA_SEP +
            InfoContract.infoEntry.BlechdickeStatus + TYPE + COMMA_SEP +
            InfoContract.infoEntry.DownSlope + TYPE + COMMA_SEP +
            InfoContract.infoEntry.Drahtdurchmesser + TYPE + COMMA_SEP +
            InfoContract.infoEntry.Drossel1 + TYPE + COMMA_SEP +
            InfoContract.infoEntry.Drossel2 + TYPE + COMMA_SEP +
            InfoContract.infoEntry.Drossel3 + TYPE + COMMA_SEP +
            InfoContract.infoEntry.DrosselAbfall + TYPE + COMMA_SEP +
            InfoContract.infoEntry.DrosselDynamic + TYPE + COMMA_SEP +
            InfoContract.infoEntry.Einfädeln + TYPE + COMMA_SEP +
            InfoContract.infoEntry.Einschleichenabsolut + TYPE + COMMA_SEP +
            InfoContract.infoEntry.EinschleichenKorrektur + TYPE + COMMA_SEP +
            InfoContract.infoEntry.ElektrodeStromIstwert + TYPE + COMMA_SEP +
            InfoContract.infoEntry.ElektrodeStromSetwert + TYPE + COMMA_SEP +
            InfoContract.infoEntry.EndkraterDauer + TYPE + COMMA_SEP +
            InfoContract.infoEntry.EndkraterDrossel + TYPE + COMMA_SEP +
            InfoContract.infoEntry.EndkraterEnergie + TYPE + COMMA_SEP +
            InfoContract.infoEntry.Endkraterenergie + TYPE + COMMA_SEP +
            InfoContract.infoEntry.EndkraterLichtbogenkorrektur + TYPE + COMMA_SEP +
            InfoContract.infoEntry.EndkraterSpannung + TYPE + COMMA_SEP +
            InfoContract.infoEntry.Endkraterstrom + TYPE + COMMA_SEP +
            InfoContract.infoEntry.Energie1 + TYPE + COMMA_SEP +
            InfoContract.infoEntry.Energie2 + TYPE + COMMA_SEP +
            InfoContract.infoEntry.Energie3 + TYPE + COMMA_SEP +
            InfoContract.infoEntry.Freibrand + TYPE + COMMA_SEP +
            InfoContract.infoEntry.FreibrandKorrektur + TYPE + COMMA_SEP +
            InfoContract.infoEntry.Gas + TYPE + COMMA_SEP +
            InfoContract.infoEntry.Gasnachströmen + TYPE + COMMA_SEP +
            InfoContract.infoEntry.GasSollwert + TYPE + COMMA_SEP +
            InfoContract.infoEntry.GastestZeit + TYPE + COMMA_SEP +
            InfoContract.infoEntry.Gasvorströmen + TYPE + COMMA_SEP +
            InfoContract.infoEntry.GebirgeStatus + TYPE + COMMA_SEP +
            InfoContract.infoEntry.Hotstart + TYPE + COMMA_SEP +
            InfoContract.infoEntry.HotstartDauer + TYPE + COMMA_SEP +
            InfoContract.infoEntry.InnenwiderstandfürDossel + TYPE + COMMA_SEP +
            InfoContract.infoEntry.InnenwiderstandfürElektrode + TYPE + COMMA_SEP +
            InfoContract.infoEntry.JobKommando + TYPE + COMMA_SEP +
            InfoContract.infoEntry.Jobnummer + TYPE + COMMA_SEP +
            InfoContract.infoEntry.JobSlope + TYPE + COMMA_SEP +
            InfoContract.infoEntry.JobStatus + TYPE + COMMA_SEP +
            InfoContract.infoEntry.KaltdrahtpulsenT1 + TYPE + COMMA_SEP +
            InfoContract.infoEntry.kennliniennummer + TYPE + COMMA_SEP +
            InfoContract.infoEntry.kennlinienTyp + TYPE + COMMA_SEP +
            InfoContract.infoEntry.KH_Status + TYPE + COMMA_SEP +
            InfoContract.infoEntry.KHMode + TYPE + COMMA_SEP +
            InfoContract.infoEntry.KorrekturDrossel + TYPE + COMMA_SEP +
            InfoContract.infoEntry.KorrekturPulsamplitude + TYPE + COMMA_SEP +
            InfoContract.infoEntry.LBRMode + TYPE + COMMA_SEP +
            InfoContract.infoEntry.Lichtbogenkorrektur1 + TYPE + COMMA_SEP +
            InfoContract.infoEntry.Lichtbogenkorrektur2 + TYPE + COMMA_SEP +
            InfoContract.infoEntry.Lichtbogenkorrektur3 + TYPE + COMMA_SEP +
            InfoContract.infoEntry.MAGACBetriebsart + TYPE + COMMA_SEP +
            InfoContract.infoEntry.MAGACKurzschlussaufhebung + TYPE + COMMA_SEP +
            InfoContract.infoEntry.MAGACKurzschlusserkennung + TYPE + COMMA_SEP +
            InfoContract.infoEntry.MAGACKältewert + TYPE + COMMA_SEP +
            InfoContract.infoEntry.MAGACNegativZeit + TYPE + COMMA_SEP +
            InfoContract.infoEntry.MAGACPositiveZeit + TYPE + COMMA_SEP +
            InfoContract.infoEntry.MAGACStromschwellwert + TYPE + COMMA_SEP +
            InfoContract.infoEntry.MAGAGVerweilzeitNegPos + TYPE + COMMA_SEP +
            InfoContract.infoEntry.MAGAGVerweilzeitPosNeg + TYPE + COMMA_SEP +
            InfoContract.infoEntry.MotorFlanke + TYPE + COMMA_SEP +
            InfoContract.infoEntry.Pausenzeitbeiinterval + TYPE + COMMA_SEP +
            InfoContract.infoEntry.PowerpilsEnergie + TYPE + COMMA_SEP +
            InfoContract.infoEntry.PowerPulsDownSlope + TYPE + COMMA_SEP +
            InfoContract.infoEntry.PowerpulsEinAus + TYPE + COMMA_SEP +
            InfoContract.infoEntry.PowerPulsLBKorrEnergie2 + TYPE + COMMA_SEP +
            InfoContract.infoEntry.PowerPulsTime1fürEnergie1 + TYPE + COMMA_SEP +
            InfoContract.infoEntry.PowerPulsTime2fürEnergie1 + TYPE + COMMA_SEP +
            InfoContract.infoEntry.PowerPulsUpSlope + TYPE + COMMA_SEP +
            InfoContract.infoEntry.Punktzeit + TYPE + COMMA_SEP +
            InfoContract.infoEntry.Reglertyp + TYPE + COMMA_SEP +
            InfoContract.infoEntry.Reset + TYPE + COMMA_SEP +
            InfoContract.infoEntry.RMTNegAmplitude + TYPE + COMMA_SEP +
            InfoContract.infoEntry.RMTPosAmplitude + TYPE + COMMA_SEP +
            InfoContract.infoEntry.Rückzugsstrecke + TYPE + COMMA_SEP +
            InfoContract.infoEntry.SchweißState + TYPE + COMMA_SEP +
            InfoContract.infoEntry.Spannung1 + TYPE + COMMA_SEP +
            InfoContract.infoEntry.Spannung2 + TYPE + COMMA_SEP +
            InfoContract.infoEntry.Spannung3 + TYPE + COMMA_SEP +
            InfoContract.infoEntry.SpannungHoldwert + TYPE + COMMA_SEP +
            InfoContract.infoEntry.SpannungInkremental + TYPE + COMMA_SEP +
            InfoContract.infoEntry.SpannungIstwert + TYPE + COMMA_SEP +
            InfoContract.infoEntry.SpannungSetwert + TYPE + COMMA_SEP +
            InfoContract.infoEntry.SpannungStatus + TYPE + COMMA_SEP +
            InfoContract.infoEntry.StartAmplitude + TYPE + COMMA_SEP +
            InfoContract.infoEntry.Startueberhoehung + TYPE + COMMA_SEP +
            InfoContract.infoEntry.StartZeit + TYPE + COMMA_SEP +
            InfoContract.infoEntry.StatusFLG + TYPE + COMMA_SEP +
            InfoContract.infoEntry.StatusMSR + TYPE + COMMA_SEP +
            InfoContract.infoEntry.Strom2 + TYPE + COMMA_SEP +
            InfoContract.infoEntry.Strom1 + TYPE + COMMA_SEP +
            InfoContract.infoEntry.Strom3 + TYPE + COMMA_SEP +
            InfoContract.infoEntry.StromHoldwert + TYPE + COMMA_SEP +
            InfoContract.infoEntry.StromInkremental + TYPE + COMMA_SEP +
            InfoContract.infoEntry.Stromistwert + TYPE + COMMA_SEP +
            InfoContract.infoEntry.StromSetwert + TYPE + COMMA_SEP +
            InfoContract.infoEntry.StromStatus + TYPE + COMMA_SEP +
            InfoContract.infoEntry.SynergieVorgabeBasis + TYPE + COMMA_SEP +
            InfoContract.infoEntry.UPSlope + TYPE + COMMA_SEP +
            InfoContract.infoEntry.Verfahren + TYPE + COMMA_SEP +
            InfoContract.infoEntry.Verriegelungsstufe + TYPE + COMMA_SEP +
            InfoContract.infoEntry.VerzögerungsZeitHeißdrahteuberwachung + TYPE + COMMA_SEP +
            InfoContract.infoEntry.VerzögerungsZeitKaltdrahtAus + TYPE + COMMA_SEP +
            InfoContract.infoEntry.VerzögerungsZeitKaltdrahtEin + TYPE + COMMA_SEP +
            InfoContract.infoEntry.Vorpositionierungsstrecke + TYPE + COMMA_SEP +
            InfoContract.infoEntry.UserNummer + TYPE + COMMA_SEP +
            InfoContract.infoEntry.VorschubausKennlinie + TYPE + COMMA_SEP +
            InfoContract.infoEntry.Vorschubistwert + TYPE + COMMA_SEP +
            InfoContract.infoEntry.VorschubSetwert + TYPE + COMMA_SEP +
            InfoContract.infoEntry.VorschubStatus + TYPE + COMMA_SEP +
            InfoContract.infoEntry.Werkstoff + TYPE + COMMA_SEP +
            InfoContract.infoEntry.WIGACBalance + TYPE + COMMA_SEP +
            InfoContract.infoEntry.WIGACFrequenz + TYPE + COMMA_SEP +
            InfoContract.infoEntry.WIGACStromoffset + TYPE + COMMA_SEP +
            InfoContract.infoEntry.WIGBetriebsartWechselrichtwer + TYPE + COMMA_SEP +
            InfoContract.infoEntry.WIGDurchmesserWolframElektrode + TYPE + COMMA_SEP +
            InfoContract.infoEntry.WIGSpeedPulsFrequenz + TYPE + COMMA_SEP +
            InfoContract.infoEntry.WIGSpeedPulsI1Anteil + TYPE + COMMA_SEP +
            InfoContract.infoEntry.WIGSpeedPulsI3 + TYPE + COMMA_SEP +
            InfoContract.infoEntry.WIGstatus + TYPE + COMMA_SEP +
            InfoContract.infoEntry.WIGStromLimit + TYPE + COMMA_SEP +
            InfoContract.infoEntry.ZündDauer + TYPE + COMMA_SEP +
            InfoContract.infoEntry.ZündDrossel + TYPE + COMMA_SEP +
            InfoContract.infoEntry.ZündEnergie + TYPE + COMMA_SEP +
            InfoContract.infoEntry.ZündLichtbogenkorrektur + TYPE + COMMA_SEP +
            InfoContract.infoEntry.Zündenergiein + TYPE + COMMA_SEP +
            InfoContract.infoEntry.ZündSpannung + TYPE + COMMA_SEP +
            InfoContract.infoEntry.ZündStrom + TYPE + COMMA_SEP +
            InfoContract.infoEntry.Überblendzeit + TYPE + ");";

    //hier wurde den context von delete table als const defeniert,
    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + InfoContract.infoEntry.TABEL_NAME;

    public InfoDataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersoin, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }
}
