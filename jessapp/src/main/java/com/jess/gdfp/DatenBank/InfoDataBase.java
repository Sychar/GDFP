
/**
 * *@Autor Sakhr Aljendi
 * @Version 1.0
 * 08.01.2020
 */
package com.jess.gdfp.DatenBank;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class InfoDataBase extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "infos.db";
    private static final String TYPE = " TEXT ";
    private static final String COMMA_SEP = ",";
    //hier wurde den context von create table const defeniert.
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + InfoContract.infoEntry.TABEL_NAME +
            "(" + InfoContract.infoEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            InfoContract.infoEntry.COLUMN_TIME_ + TYPE + COMMA_SEP +
            InfoContract.infoEntry.COLUMN_LEN_ + TYPE + COMMA_SEP +
            InfoContract.infoEntry.COLUMN_CANID + TYPE + COMMA_SEP +
            InfoContract.infoEntry.COLUMN_CANDATA_ + TYPE + ");";

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
