package com.jess.gdfp.DatenBank;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class KennlinerDatenBase extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "kennlienie.db";
    private static final String TYPE = " TEXT ";
    private static final String COMMA_SEP = ",";


    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + KennlinienContract.Kennlinie.TABEL_NAME +
            "(" + KennlinienContract.Kennlinie._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            KennlinienContract.Kennlinie.stKennBez1 + TYPE + COMMA_SEP + KennlinienContract.Kennlinie.stKennBez2 + TYPE + COMMA_SEP +
            KennlinienContract.Kennlinie.stKennKomm + TYPE + COMMA_SEP + KennlinienContract.Kennlinie.ucDownSlopeSTRING + TYPE + COMMA_SEP +
            KennlinienContract.Kennlinie.ucDrahtDurchm + TYPE + COMMA_SEP + KennlinienContract.Kennlinie.ucDrossRes1 + TYPE + COMMA_SEP +
            KennlinienContract.Kennlinie.ucDrossRes2 + TYPE + COMMA_SEP + KennlinienContract.Kennlinie.ucDrossRes3 + TYPE + COMMA_SEP +
            KennlinienContract.Kennlinie.ucDrossRes4 + TYPE + COMMA_SEP + KennlinienContract.Kennlinie.ucDrossRes5 + TYPE + COMMA_SEP +
            KennlinienContract.Kennlinie.ucDrossRes6 + TYPE + COMMA_SEP + KennlinienContract.Kennlinie.ucDrossRes7 + TYPE + COMMA_SEP +
            KennlinienContract.Kennlinie.ucEndKraDauer + TYPE + COMMA_SEP + KennlinienContract.Kennlinie.ucEndKraterPrz + TYPE + COMMA_SEP +
            KennlinienContract.Kennlinie.ucFreiBrandPrz + TYPE + COMMA_SEP + KennlinienContract.Kennlinie.ucGas + KennlinienContract.Kennlinie.ucGasNach + TYPE + COMMA_SEP +
            KennlinienContract.Kennlinie.ucGasVor + TYPE + COMMA_SEP + KennlinienContract.Kennlinie.ucKennType + KennlinienContract.Kennlinie.ucKS_Aufhebung + TYPE + COMMA_SEP +
            KennlinienContract.Kennlinie.ucKS_Erkennung + TYPE + COMMA_SEP + KennlinienContract.Kennlinie.ucLBR_Mode + TYPE + COMMA_SEP +
            KennlinienContract.Kennlinie.ucPositivZeit + TYPE + COMMA_SEP + KennlinienContract.Kennlinie.ucPP_LBR_E2 + TYPE + COMMA_SEP +
            KennlinienContract.Kennlinie.ucPPEnergie2 + TYPE + COMMA_SEP + KennlinienContract.Kennlinie.ucPPTime1 + TYPE + COMMA_SEP +
            KennlinienContract.Kennlinie.ucPPTime2 + TYPE + COMMA_SEP + KennlinienContract.Kennlinie.ucProtokollTyp + TYPE + COMMA_SEP +
            KennlinienContract.Kennlinie.ucReglerTyp + TYPE + COMMA_SEP + KennlinienContract.Kennlinie.ucStromSchwell + TYPE + COMMA_SEP +
            KennlinienContract.Kennlinie.ucUpSlope + TYPE + COMMA_SEP + KennlinienContract.Kennlinie.ucVerfahren + TYPE + COMMA_SEP +
            KennlinienContract.Kennlinie.ucVerwZeitNP + TYPE + COMMA_SEP + KennlinienContract.Kennlinie.ucVerwZeitPN + TYPE + COMMA_SEP +
            KennlinienContract.Kennlinie.ucWerkstof + TYPE + COMMA_SEP + KennlinienContract.Kennlinie.ucZuendDauer + TYPE + COMMA_SEP +
            KennlinienContract.Kennlinie.ucZuendEgPrz + TYPE + COMMA_SEP + KennlinienContract.Kennlinie.uiGrundCRC + TYPE + COMMA_SEP +
            KennlinienContract.Kennlinie.UiKennNR + TYPE + COMMA_SEP + KennlinienContract.Kennlinie.ulKennEssNr + ");";

    public KennlinerDatenBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + KennlinienContract.Kennlinie.TABEL_NAME;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }
}
