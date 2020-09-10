package com.jess.gdfp.Controller;

import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jess.gdfp.DatenBank.Jobs;
import com.jess.gdfp.MainActivity;
import com.jess.gdfp.R;

import java.util.ArrayList;

public class jobAdapter extends ArrayAdapter<Jobs> {
    private View listitem;
    private static Jobs jobs;
    private TextView id;
    private TextView jobname;
    private CardView cardView;

    public  jobAdapter (Activity context , ArrayList<Jobs> jobs){
        super(context,0,jobs);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        listitem =convertView;
        if (listitem == null) {
            listitem = LayoutInflater.from(getContext()).inflate(R.layout.item_for_job, parent, false);
        }
        id=listitem.findViewById(R.id.job_nr);
        jobname=listitem.findViewById(R.id.job_name);
        cardView=listitem.findViewById(R.id.card_view);

        jobs = getItem(position);
        id.setText(jobs.getNum());
        jobname.setText(jobs.getName());
        Log.i("position",String.valueOf(position));

        if (position == 0){
            id.setTextColor(Color.BLACK);
            jobname.setTextColor(Color.BLACK);
            cardView.setCardBackgroundColor(Color.GRAY);
        }

        return listitem;
    }
}
