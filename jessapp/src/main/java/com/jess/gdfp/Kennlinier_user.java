package com.jess.gdfp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;

import com.jess.gdfp.Controller.Kennlinie_user_adapter;
import com.jess.gdfp.Controller.jobAdapter;
import com.jess.gdfp.DatenBank.Jobs;
import com.jess.gdfp.View.JobsDetails;

import java.util.ArrayList;

public class Kennlinier_user extends AppCompatActivity {
    private Intent intent;
    CardView myCardView;
    CheckBox checkBox;
    private static ListView rv;
    private static Kennlinie_user_adapter kennlinie_user_adapter;
    private ArrayList<Jobs> jobs;
    public static int KENN_POINTER;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kennlinier_user);

        myCardView = findViewById(R.id.card_view);
        checkBox=findViewById(R.id.cheak_box);
        // myCardView.setCardBackgroundColor(Color.TRANSPARENT);
        LinearLayoutManager mLayoutManager0 = new LinearLayoutManager (this );
        mLayoutManager0.setOrientation(LinearLayoutManager.VERTICAL);
        rv = findViewById(R.id.list_for_jobs);
        jobs = initKENN();
        kennlinie_user_adapter = new Kennlinie_user_adapter(this, jobs);
        rv.setAdapter(kennlinie_user_adapter);
    }

    public static void changeKennBackground(int counter){
        KENN_POINTER = counter;
        kennlinie_user_adapter.notifyDataSetChanged();
        rv.smoothScrollToPosition(KENN_POINTER);
    }

    public void jobuser(View view) {
        intent = new Intent(this, kennlinie_setting.class);
        startActivity(intent);
        //JOB_POINTER++;
        // myjobAdapte.notifyDataSetChanged();
    }

    private ArrayList initKENN() {
        ArrayList jobs = new ArrayList<>();
        jobs.add(new Jobs("1", "info1"));
        jobs.add(new Jobs("2", "info2"));
        jobs.add(new Jobs("3", "info3"));
        jobs.add(new Jobs("4", "info4"));
        jobs.add(new Jobs("5", "info5"));
        jobs.add(new Jobs("6", "info6"));
        jobs.add(new Jobs("7", "info1"));
        jobs.add(new Jobs("8", "info2"));
        jobs.add(new Jobs("9", "info3"));
        jobs.add(new Jobs("10", "info4"));
        jobs.add(new Jobs("11", "info5"));
        jobs.add(new Jobs("12", "info6"));
        jobs.add(new Jobs("13", "info1"));
        jobs.add(new Jobs("14", "info2"));
        jobs.add(new Jobs("15", "info3"));
        jobs.add(new Jobs("16", "info4"));
        jobs.add(new Jobs("17", "info5"));
        jobs.add(new Jobs("18", "info6"));
        return jobs;
    }
}
