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
import android.widget.Button;
import android.widget.TextView;

import com.jess.gdfp.DatenBank.Jobs;
import com.jess.gdfp.R;

import com.jess.gdfp.View.JobsUser;

import java.util.ArrayList;

public class jobAdapter extends ArrayAdapter<Jobs> {
    private View listitem;
    private static Jobs jobs;
    private TextView jobnum;
    private TextView jobname;
    private CardView cardView;
    private Button EDIT_BTN;
    private JobsUser JOB_OBJECT = new JobsUser();

    public jobAdapter(Activity context, ArrayList<Jobs> jobs) {
        super(context, 0, jobs);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        listitem = convertView;
        if (listitem == null) {
            listitem = LayoutInflater.from(getContext()).inflate(R.layout.item_for_job, parent, false);
        }

        jobnum = listitem.findViewById(R.id.job_nr);
        jobname = listitem.findViewById(R.id.job_name);
        cardView = listitem.findViewById(R.id.card_view);
        /*EDIT_BTN = listitem.findViewById(R.id.edit_btn);
        EDIT_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JOB_OBJECT.jobuser();
            }
        });*/
        jobs = getItem(position);
        jobnum.setText(jobs.getNum());
        jobname.setText(jobs.getName());
        changebackround(position,JobsUser.JOB_POINTER);

        return listitem;
    }

    void changebackround(int pos,int pointer){
        if(pos!=pointer){
            jobnum.setTextColor(Color.WHITE);
            jobname.setTextColor(Color.WHITE);
            cardView.setCardBackgroundColor(Color.BLACK);
        }else{
            jobnum.setTextColor(Color.BLACK);
            jobname.setTextColor(Color.BLACK);
            cardView.setCardBackgroundColor(Color.GRAY);
        }
    }
}