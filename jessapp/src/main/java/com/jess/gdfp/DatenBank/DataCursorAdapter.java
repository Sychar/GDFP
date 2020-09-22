

/**
 * *@Autor Sakhr Aljendi
 * @Version 1.0
 * 16.01.2020
 * */
package com.jess.gdfp.DatenBank;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.jess.gdfp.R;

public class DataCursorAdapter extends CursorAdapter
{

public DataCursorAdapter(Context context , Cursor c){
    super(context,c,0);
}
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.data_listitem,viewGroup,false);
    }
    /**
     * @methode
     * zeigen die dataen in listview
     */

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView idText =(TextView) view.findViewById(R.id.id);
        TextView length=(TextView) view.findViewById(R.id.lenth_);
        TextView canid =(TextView) view.findViewById(R.id.canid_);
        TextView candata =(TextView) view.findViewById(R.id.Mydata_);
        try {
            String id = cursor.getString(cursor.getColumnIndexOrThrow(
                    InfoContract.infoEntry.COLUMN_TIME_

            ));
            idText.setText(id);
        }catch (Exception e){
            e.printStackTrace();
        }
       /* try {
            String canData = cursor.getString(cursor.getColumnIndexOrThrow(InfoContract.infoEntry.COLUMN_CANDATA_));
            candata.setText(canData);
        }catch (Exception e){
            e.printStackTrace();
        }*/

        //String len =cursor.getString(cursor.getColumnIndexOrThrow(InfoContract.infoEntry.COLUMN_LEN_));
        //String  Canid =cursor.getString(cursor.getColumnIndexOrThrow(InfoContract.infoEntry.COLUMN_CANID));



       // length.setText(len);
        //canid.setText(Canid);

    }
}
