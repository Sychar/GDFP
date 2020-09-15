package com.jess.gdfp.Controller;

import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.jess.gdfp.DatenBank.Jobs;
import com.jess.gdfp.DatenBank.Kennlinie;
import com.jess.gdfp.Kennlinier_user;
import com.jess.gdfp.R;

import java.util.ArrayList;

public class Kennlinie_user_adapter extends ArrayAdapter<Jobs> {
    private View listView;
    private TextView param;
    private TextView wert;
    private Jobs kennlinie;
    private CardView cardView;

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
        cardView = listView.findViewById(R.id.card_view);

        kennlinie=getItem(position);
        param.setText(kennlinie.getName());
        wert.setText(kennlinie.getNum());
        changeKennbackround(position, Kennlinier_user.KENN_POINTER);

        return listView;
    }

    void changeKennbackround(int pos,int pointer){
        if(pos!=pointer){
            param.setTextColor(Color.WHITE);
            wert.setTextColor(Color.WHITE);
            cardView.setCardBackgroundColor(Color.BLACK);
        }else{
            param.setTextColor(Color.BLACK);
            wert.setTextColor(Color.BLACK);
            cardView.setCardBackgroundColor(Color.GRAY);
        }
    }
}