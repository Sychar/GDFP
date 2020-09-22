package com.jess.gdfp.Controller;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import com.jess.gdfp.DatenBank.Datenlogger;
import com.jess.gdfp.R;

import java.util.ArrayList;

public class DatenLoggerAdapter extends ArrayAdapter {

    private  TextView param ;
    private TextView Vaule;
    private View mylist ;
    private Datenlogger datenloggerObjekt;



    public DatenLoggerAdapter(Activity context , ArrayList<Datenlogger> datenlogers){
        super(context,0,datenlogers);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        mylist=convertView;
        if(mylist==null){
            mylist= LayoutInflater.from(getContext()).inflate(R.layout.item_for_datenlogger,parent,false);

        }

        param=mylist.findViewById(R.id.job_name);
        Vaule=mylist.findViewById(R.id.job_nr);
        datenloggerObjekt=(Datenlogger) getItem(position);
        param.setText(datenloggerObjekt.getParam());
        Vaule.setText(datenloggerObjekt.getVaule());
        return mylist;


    }
}
