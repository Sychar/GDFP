package com.jess.gdfp.DatenBank;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class JopDataProvider extends ContentProvider {

    private static final int ALL_Jobs=100;
    private static final int ID_JOBS=101;
    private static final UriMatcher sUriMathcer = new UriMatcher(UriMatcher.NO_MATCH);
    private JobDataBase datenReader;

    /**
     * static block zum testen was von Uri wurde aufgerufen
     */
    static {
        sUriMathcer.addURI(JobContract.CONTEN_AUTHRITY, JobContract.PATH_JOBS, ALL_Jobs);
        sUriMathcer.addURI(JobContract.CONTEN_AUTHRITY, JobContract.PATH_JOBS + "/#", ID_JOBS);

    }
    @Override
    public boolean onCreate() {

        datenReader = new JobDataBase(getContext());
        return false;
    }
    @Nullable
    @Override
    /**
     * @hier wurde geprüfen welche Uri wurde aufgerufen und die richtige Cursor zurückgibt
     */
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        // datenReader = new JobDataBase(getContext());
        SQLiteDatabase database = datenReader.getReadableDatabase();
        Cursor cursor = null;
        int match = sUriMathcer.match(uri);
        switch (match) {
            case ALL_Jobs:

                cursor = database.query(JobContract.jobEntry.TABEL_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;

            case ID_JOBS:
                selection = JobContract.jobEntry.HI_UI_JOB_NR + "=?" +"AND" +JobContract.jobEntry.HI_UI_STROM_SET + "=?";//hier wurde die id =? zuwiesen
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};//hier welche id ist das
                cursor = database.query(JobContract.jobEntry.TABEL_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("can not query unknown URI " + uri);
        }
        return cursor;
    }
    @Nullable
    @Override
    //insert data
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {

        final int match = sUriMathcer.match(uri);
        switch (match) {
            case ALL_Jobs:
                return insertDaten(uri, contentValues);
            default:
                throw new IllegalArgumentException("insertion is not support " + uri);


        }
    }
    private Uri insertDaten(Uri uri, ContentValues vaues) {
        SQLiteDatabase db =datenReader.getWritableDatabase();
        long id = db.insert(JobContract.jobEntry.TABEL_NAME,null,vaues);
        if(id==-1){
            return null;
        }
        return  ContentUris.withAppendedId(uri,id);
    }
    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        SQLiteDatabase database = datenReader.getWritableDatabase();
        final  int match =sUriMathcer.match(uri);
        switch (match){
            case ALL_Jobs:
                return database.delete(JobContract.jobEntry.TABEL_NAME,s,strings);
            case ID_JOBS:
                s= JobContract.jobEntry._ID +"=?";
                strings= new String[]{String.valueOf(ContentUris.parseId(uri))};
                return database.delete(JobContract.jobEntry.TABEL_NAME,s,strings);
            default:
                throw  new IllegalArgumentException("Delete is not support ");
        }

    }
    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        final int match = sUriMathcer.match(uri);
        switch (match) {
            case ALL_Jobs:
                return updateData(uri, contentValues, s, strings);
            case ID_JOBS:
                s = JobContract.jobEntry._ID + "=?";
                strings = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return updateData(uri, contentValues, s, strings);
            default:
                throw new IllegalArgumentException("update ist not  supported");
        }
    }
    private  int updateData(Uri uri , ContentValues values, String selection, String[] selectionArgs){
        if (values.size() ==0){
            return  0;
        }
        SQLiteDatabase database = datenReader.getWritableDatabase();
        return database.update(JobContract.jobEntry.TABEL_NAME,values,selection,selectionArgs);
    }
    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }
}


