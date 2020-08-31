
/**
 * *@Autor Sakhr Aljendi
 * @Version 1.0
 * 09.02.2020
 * */


package com.jess.gdfp.DatenBank;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class JobDataBase extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "jobs.db";
    private static final String TYPE = " TEXT ";
    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + JobContract.jobEntry.TABEL_NAME +
            "(" + JobContract.jobEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            JobContract.jobEntry.HI_ELEKTOSTROM_SET + TYPE + COMMA_SEP +
            JobContract.jobEntry.HI_ST_STUF1SPANN + TYPE + COMMA_SEP +
            JobContract.jobEntry.HI_ST_STUF2SPANN + TYPE + COMMA_SEP +
            JobContract.jobEntry.HI_ST_STUF3SPANN + TYPE + COMMA_SEP +
            JobContract.jobEntry.HI_UI_ENDKRA_SPANN + TYPE + COMMA_SEP +
            JobContract.jobEntry.HI_UI_ENDKRA_STROM + TYPE + COMMA_SEP +
            JobContract.jobEntry.HI_UI_JOB_NR + TYPE + COMMA_SEP +
            JobContract.jobEntry.HI_UI_JOBCRC + TYPE + COMMA_SEP +
            JobContract.jobEntry.HI_UI_KRA_ENERGIE + TYPE + COMMA_SEP +
            JobContract.jobEntry.HI_UI_LIMIT_MIT_LBKORR + TYPE + COMMA_SEP +
            JobContract.jobEntry.HI_UI_NEGEZEIT + TYPE + COMMA_SEP +
            JobContract.jobEntry.HI_UI_PAUSE_ZEIT + TYPE + COMMA_SEP +
            JobContract.jobEntry.HI_UI_PORTOKOLLTYPE + TYPE + COMMA_SEP +
            JobContract.jobEntry.HI_UI_PUNKT_ZEIt + TYPE + COMMA_SEP +
            JobContract.jobEntry.HI_UI_START_AMPLITUDE + TYPE + COMMA_SEP +
            JobContract.jobEntry.HI_UI_STROM_SET + TYPE + COMMA_SEP +
            JobContract.jobEntry.HI_UI_STUF2STROM + TYPE + COMMA_SEP +
            JobContract.jobEntry.HI_UI_STUF3STROM + TYPE + COMMA_SEP +
            JobContract.jobEntry.HI_UI_STUFE1ENERGIE + TYPE + COMMA_SEP +
            JobContract.jobEntry.HI_UI_STUFE2ENERGIE + TYPE + COMMA_SEP +
            JobContract.jobEntry.HI_UI_STUFE3ENERGIE + TYPE + COMMA_SEP +
            JobContract.jobEntry.HI_UI_VOR_SCHUB_SET + TYPE + COMMA_SEP +
            JobContract.jobEntry.HI_UI_ZUEND_STROM + TYPE + COMMA_SEP +
            JobContract.jobEntry.HI_UIKENN_NR + TYPE + COMMA_SEP +
            JobContract.jobEntry.HI_UISTAFE1STROM + TYPE + COMMA_SEP +
            JobContract.jobEntry.HI_ULI_MIT_ENERGIE + TYPE + COMMA_SEP +
            JobContract.jobEntry.HI_ZUEND_ENERGIE + TYPE + COMMA_SEP +
            JobContract.jobEntry.HI_ZUEND_SPANN + TYPE + COMMA_SEP +
            JobContract.jobEntry.LO_ELKTOSTROM_SET + TYPE + COMMA_SEP +
            JobContract.jobEntry.LO_SI_STUFE1SPANN + TYPE + COMMA_SEP +
            JobContract.jobEntry.LO_SI_STUFE2SPANN + TYPE + COMMA_SEP +
            JobContract.jobEntry.LO_SI_STUFE3SPANN + TYPE + COMMA_SEP +
            JobContract.jobEntry.LO_UI_ENDKAR_STROM + TYPE + COMMA_SEP +
            JobContract.jobEntry.LO_UI_ENDKRA_SPANN + TYPE + COMMA_SEP +
            JobContract.jobEntry.LO_UI_JOB_NR + TYPE + COMMA_SEP +
            JobContract.jobEntry.lO_UI_JOBCRC + TYPE + COMMA_SEP +
            JobContract.jobEntry.LO_UI_KRA_ENERGIE + TYPE + COMMA_SEP +
            JobContract.jobEntry.LO_UI_LI_MIT_ENERGIE + TYPE + COMMA_SEP +
            JobContract.jobEntry.LO_UI_LIMIT_MIT_LBKORR + TYPE + COMMA_SEP +
            JobContract.jobEntry.LO_UI_NEGEZEIT + TYPE + COMMA_SEP +
            JobContract.jobEntry.LO_UI_PAUSE_ZEIT + TYPE + COMMA_SEP +
            JobContract.jobEntry.LO_UI_PROTOKOLLTYPE + TYPE + COMMA_SEP +
            JobContract.jobEntry.LO_UI_PUNKT_ZEIT + TYPE + COMMA_SEP +
            JobContract.jobEntry.LO_UI_START_AMPLITUDE + TYPE + COMMA_SEP +
            JobContract.jobEntry.LO_UI_STROM_SET + TYPE + COMMA_SEP +
            JobContract.jobEntry.LO_UI_STUFE1ENERGIE + TYPE + COMMA_SEP +
            JobContract.jobEntry.LO_UI_STUFE2ENERGIE + TYPE + COMMA_SEP +
            JobContract.jobEntry.LO_UI_STUFE2STROM + TYPE + COMMA_SEP +
            JobContract.jobEntry.LO_UI_STUFE3ENERGIE + TYPE + COMMA_SEP +
            JobContract.jobEntry.LO_UI_STUFE3STROM + TYPE + COMMA_SEP +
            JobContract.jobEntry.LO_UI_VOR_SCHUB_SET + TYPE + COMMA_SEP +
            JobContract.jobEntry.LO_UI_ZUEND_STROM + TYPE + COMMA_SEP +
            JobContract.jobEntry.LO_UIKENN_NR + TYPE + COMMA_SEP +
            JobContract.jobEntry.LO_UISTAFE1STROM + TYPE + COMMA_SEP +
            JobContract.jobEntry.lo_ZUEND_ENERGIE + TYPE + COMMA_SEP +
            JobContract.jobEntry.LO_ZUEND_SPANN + TYPE + COMMA_SEP +
            JobContract.jobEntry.SC_EINSCHPROZ + TYPE + COMMA_SEP +
            JobContract.jobEntry.SC_ENDKRA_LBKORR + TYPE + COMMA_SEP +
            JobContract.jobEntry.SC_PP_LBR_E2 + TYPE + COMMA_SEP +
            JobContract.jobEntry.SC_STUFE1BKORR + TYPE + COMMA_SEP +
            JobContract.jobEntry.SC_STUFE2BKORR + TYPE + COMMA_SEP +
            JobContract.jobEntry.SC_STUFE3BKORR + TYPE + COMMA_SEP +
            JobContract.jobEntry.SC_ZUEND_BKORR + TYPE + COMMA_SEP +
            JobContract.jobEntry.ST_JOB_BEZZ1 + TYPE + COMMA_SEP +
            JobContract.jobEntry.UC_ARC_FORCE + TYPE + COMMA_SEP +
            JobContract.jobEntry.UC_BETRIBMAGAC + TYPE + COMMA_SEP +
            JobContract.jobEntry.UC_BETRIEB_ART + TYPE + COMMA_SEP +
            JobContract.jobEntry.UC_DOWN_SLOP + TYPE + COMMA_SEP +
            JobContract.jobEntry.UC_DRAH_DURCH + TYPE + COMMA_SEP +
            JobContract.jobEntry.UC_DROSS_DYNAMIC + TYPE + COMMA_SEP +
            JobContract.jobEntry.UC_DROSSEL_ABFALL + TYPE + COMMA_SEP +
            JobContract.jobEntry.UC_DROSSELRI + TYPE + COMMA_SEP +
            JobContract.jobEntry.UC_EBDKRATERORZ + TYPE + COMMA_SEP +
            JobContract.jobEntry.UC_EIN_FAEDELN + TYPE + COMMA_SEP +
            JobContract.jobEntry.UC_EINSCHLABS + TYPE + COMMA_SEP +
            JobContract.jobEntry.UC_ELEKTRODERI + TYPE + COMMA_SEP +
            JobContract.jobEntry.UC_END_KRA_DAUER + TYPE + COMMA_SEP +
            JobContract.jobEntry.UC_ENDKRA_DROSSEL + TYPE + COMMA_SEP +
            JobContract.jobEntry.UC_FREI_BRANDKORR + TYPE + COMMA_SEP +
            JobContract.jobEntry.UC_GAS_TYPE + TYPE + COMMA_SEP +
            JobContract.jobEntry.UC_GAS_VOR + TYPE + COMMA_SEP +
            JobContract.jobEntry.UC_GASNACH + TYPE + COMMA_SEP +
            JobContract.jobEntry.UC_HOT_START + TYPE + COMMA_SEP +
            JobContract.jobEntry.UC_HOT_START_DAUER + TYPE + COMMA_SEP +
            JobContract.jobEntry.UC_JOB_TYPE + TYPE + COMMA_SEP +
            JobContract.jobEntry.UC_KAELTEWERT + TYPE + COMMA_SEP +
            JobContract.jobEntry.UC_KENN_TYPE + TYPE + COMMA_SEP +
            JobContract.jobEntry.UC_KORR_DROSSEL + TYPE + COMMA_SEP +
            JobContract.jobEntry.UC_KORR_PULS_AMPL + TYPE + COMMA_SEP +
            JobContract.jobEntry.UC_LBR_MODE + TYPE + COMMA_SEP +
            JobContract.jobEntry.UC_MOTOR_FLANKE + TYPE + COMMA_SEP +
            JobContract.jobEntry.UC_POSZEIT + TYPE + COMMA_SEP +
            JobContract.jobEntry.UC_PP_EINERGIE2 + TYPE + COMMA_SEP +
            JobContract.jobEntry.UC_PP_ON_OFF + TYPE + COMMA_SEP +
            JobContract.jobEntry.UC_PP_TIME1 + TYPE + COMMA_SEP +
            JobContract.jobEntry.UC_PP_TIME2 + TYPE + COMMA_SEP +
            JobContract.jobEntry.UC_REGLERTYPE + TYPE + COMMA_SEP +
            JobContract.jobEntry.UC_START_ZEIT + TYPE + COMMA_SEP +
            JobContract.jobEntry.UC_STROM_SCHWELLE + TYPE + COMMA_SEP +
            JobContract.jobEntry.UC_STUFE1DROESSEL + TYPE + COMMA_SEP +
            JobContract.jobEntry.UC_STUFE2DROESSEL + TYPE + COMMA_SEP +
            JobContract.jobEntry.UC_STUFE3DROESSEL + TYPE + COMMA_SEP +
            JobContract.jobEntry.UC_SYN_BASIS + TYPE + COMMA_SEP +
            JobContract.jobEntry.UC_UEBER_BlEND_ZEIT + TYPE + COMMA_SEP +
            JobContract.jobEntry.UC_UPSLOP + TYPE + COMMA_SEP +
            JobContract.jobEntry.UC_USER_NR + TYPE + COMMA_SEP +
            JobContract.jobEntry.UC_VERFAHREN + TYPE + COMMA_SEP +
            JobContract.jobEntry.UC_VERWZEITNP + TYPE + COMMA_SEP +
            JobContract.jobEntry.UC_VERWZEITPN + TYPE + COMMA_SEP +
            JobContract.jobEntry.UC_WERKSTOFF + TYPE + COMMA_SEP +
            JobContract.jobEntry.UC_ZEUNDEGPRZ + TYPE + COMMA_SEP +
            JobContract.jobEntry.UC_ZUEND_DROSSEL + TYPE + COMMA_SEP +
            JobContract.jobEntry.UC_ZUENDDAUER + TYPE + COMMA_SEP +
            JobContract.jobEntry.UCKS_AUFHEBUNG + TYPE + COMMA_SEP +
            JobContract.jobEntry.UCKS_ERKENNUNG + TYPE +  ");";



    public JobDataBase(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + JobContract.jobEntry.TABEL_NAME;
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
