package com.jess.gdfp.Controller;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.jess.gdfp.DatenBank.Jobs;
import com.jess.gdfp.DatenBank.Kennlinie;
import com.jess.gdfp.R;

import java.util.ArrayList;

public class Kennlinie_user_adapter extends ArrayAdapter<Jobs> {
    private View listView;
    TextView param;
    TextView wert;
    private Jobs kennlinie;


    public Kennlinie_user_adapter (Activity context , ArrayList<Jobs>Kennlinies  ){
        super(context,0,Kennlinies );
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        listView = convertView;
        if(listView == null){
            listView= LayoutInflater.from(getContext()).inflate(R.layout.item_for_job,parent,false);

        }
        param=listView.findViewById(R.id.job_name);
        wert=listView.findViewById(R.id.job_nr);
        kennlinie=getItem(position);
        param.setText(kennlinie.getName());
        wert.setText(kennlinie.getNum());


        return listView;
    }


}
