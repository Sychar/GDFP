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
        public static final String COLUMN_LEN_ = "len";
        public static final String COLUMN_CANID = "canid";
        public static final String COLUMN_CANDATA_ = "candata";

    }
}
