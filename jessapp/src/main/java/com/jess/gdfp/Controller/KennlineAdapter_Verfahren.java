package com.jess.gdfp.Controller;

import android.app.Activity;
import android.graphics.Color;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.jess.gdfp.DatenBank.Kennline_text;
import com.jess.gdfp.R;

import java.util.ArrayList;

public class KennlineAdapter_Verfahren extends ArrayAdapter<Kennline_text> {
    static public int i = 0;

    static boolean ampel = false;
    static boolean ampe2 = false;
    static boolean ampe3 = false;


    static boolean isAmpel = true;
    private View listitem;
    private static Kennline_text kennlineText;
    private static Kennline_text kennlineText1;

    static Button mitte1;
    static Button mitte2;
    static Button mitte3;
     ArrayList<Kennline_text> detais;
    public KennlineAdapter_Verfahren(Activity context, ArrayList<Kennline_text> detais) {
        super(context, 0, detais);
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        listitem = convertView;
        if (listitem == null) {
            listitem = LayoutInflater.from(getContext()).inflate(R.layout.item_for_kennlinie, parent, false);
        }
        kennlineText = getItem(position);
        //System.out.println(kennlineText.getTitel());
        //System.out.println(position);
        //System.out.println(kennlineText.getTitel());
        Button button = listitem.findViewById(R.id.listButton);
        button.setText(kennlineText.getTitel());

        if (position == 0) {
            if (ampel == true && ampe2 == true) {
                mitte2.setTextColor(Color.GRAY);
                mitte1.setTextColor(Color.GREEN);

            }
        }
        if (position == 1) {
            if (ampe3 == true && ampe2 == true) {
                mitte3.setTextColor(Color.GRAY);
                mitte2.setTextColor(Color.GREEN);
            }
        }

        if (position == 2) {
            mitte1 = listitem.findViewById(R.id.listButton);
            mitte1.setTextColor(Color.GREEN);
            ampel = true;
        }
        if (position == 3) {
            mitte2 = listitem.findViewById(R.id.listButton);
            ampe2 = true;
        }
        if (position == 4) {
            mitte3 = listitem.findViewById(R.id.listButton);
            ampe3 = true;
        }

        if (position == 5) {

            mitte1.setTextColor(Color.GRAY);
            mitte2.setTextColor(Color.GREEN);
            mitte3.setTextColor(Color.GRAY);


        }
        if (position == 6) {
            mitte1.setTextColor(Color.GRAY);
            mitte2.setTextColor(Color.GRAY);
            mitte3.setTextColor(Color.GREEN);

        }


        return listitem;
    }
}

