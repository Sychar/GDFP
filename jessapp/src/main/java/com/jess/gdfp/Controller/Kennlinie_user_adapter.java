package com.jess.gdfp.Controller;

import android.app.Activity;
import android.graphics.Color;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.jess.gdfp.DatenBank.Kennlinie;
import com.jess.gdfp.Kennlinier_user;
import com.jess.gdfp.R;

import java.util.ArrayList;

public class Kennlinie_user_adapter extends ArrayAdapter<Kennlinie> {
    private View listView;
    private TextView Verfahren;
    private TextView Werkstof;
    private TextView Gas;
    private TextView Draht;
    private Kennlinie kennlinie;
    private TextView KennNR;
    private CardView cardView;

    public Kennlinie_user_adapter (Activity context , ArrayList<Kennlinie>Kennlinies  ){
        super(context,0,Kennlinies );
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        listView = convertView;
        if(listView == null){
            listView= LayoutInflater.from(getContext()).inflate(R.layout.item_for_job,parent,false);

        }
       Werkstof=listView.findViewById(R.id.Werkstoff);
        Verfahren=listView.findViewById(R.id.Verfahren);
        Gas=listView.findViewById(R.id.Gas);
        Draht=listView.findViewById(R.id.Draht);
        KennNR=listView.findViewById(R.id.KennNR);
        cardView = listView.findViewById(R.id.card_view);

        kennlinie=getItem(position);
        KennNR.setText(kennlinie.getKennNR());
        Verfahren.setText(kennlinie.getVerfahren());
        Werkstof.setText(kennlinie.getWerkstoff());
        Draht.setText(kennlinie.getDraht());
        Gas.setText(kennlinie.getGas());
        changeKennbackround(position, Kennlinier_user.KENN_POINTER);

        return listView;
    }

    void changeKennbackround(int pos,int pointer){
        if(pos!=pointer){
            Verfahren.setTextColor(Color.WHITE);
            Werkstof.setTextColor(Color.WHITE);
            Gas.setTextColor(Color.WHITE);
            Draht.setTextColor(Color.WHITE);
            cardView.setCardBackgroundColor(Color.BLACK);
        }else{
            Verfahren.setTextColor(Color.BLACK);
            Werkstof.setTextColor(Color.BLACK);
            Gas.setTextColor(Color.BLACK);
            Draht.setTextColor(Color.BLACK);
            cardView.setCardBackgroundColor(Color.GRAY);
        }
    }
}