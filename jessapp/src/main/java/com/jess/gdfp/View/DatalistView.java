/**
 * @Autor Sakhr Aljendi
 * @Version 1.0
 * 011.01.2020
 */


package com.jess.gdfp.View;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.jess.gdfp.DatenBank.DataCursorAdapter;
import com.jess.gdfp.DatenBank.InfoContract;
import com.jess.gdfp.IO.Exit;
import com.jess.gdfp.R;

public class DatalistView extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> , Exit {
    public static int timer=1;
    private static final int COUNT_LOADR=0;
    DataCursorAdapter dataCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datalist_view);


        ListView listView =(ListView) findViewById(R.id.datalistview);

        dataCursorAdapter = new DataCursorAdapter(this,null);
        listView.setAdapter(dataCursorAdapter);
        getLoaderManager().initLoader(COUNT_LOADR,null,this);


    }
    public void close(View view){
        DatalistView.this.finish();
    }
    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        String[] projectio= {
                InfoContract.infoEntry._ID,
                InfoContract.infoEntry.COLUMN_TIME_,
                InfoContract.infoEntry.COLUMN_LEN_,
                InfoContract.infoEntry.COLUMN_CANID,
               //InfoContract.infoEntry.COLUMN_CANDATA_

                };
        return new CursorLoader(this, InfoContract.infoEntry.CONTENT_URI,projectio,null,null,null);
    }


    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

dataCursorAdapter.swapCursor(cursor);
if(timer==0){
    timer++;
    finish();
}

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
dataCursorAdapter.swapCursor(null);
    }
}

