package com.jess.gdfp.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ListView;

import com.jess.gdfp.Controller.jobAdapter;
import com.jess.gdfp.DatenBank.Jobs;
import com.jess.gdfp.R;

import java.util.ArrayList;

public class JobsUser extends AppCompatActivity {
    private Intent intent;
    CardView myCardView;
    private ArrayList<Jobs> jobs;
    public static int JOB_POINTER;
    private static jobAdapter myjobAdapte;
    private static ListView rv;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs_user);

        myCardView = findViewById(R.id.card_view);
        // myCardView.setCardBackgroundColor(Color.TRANSPARENT);
        LinearLayoutManager mLayoutManager0 = new LinearLayoutManager (this );
        mLayoutManager0.setOrientation(LinearLayoutManager.VERTICAL);
        rv = findViewById(R.id.list_for_jobs);
        jobs = initJobs();
        myjobAdapte = new jobAdapter(this, jobs);
        rv.setAdapter(myjobAdapte);

    }

    public static void changeBackground(int counter) {
        JOB_POINTER = counter;
        myjobAdapte.notifyDataSetChanged();
        rv.smoothScrollToPosition(JOB_POINTER);
        //else if ( JOB_POINTER > 5) rv.smoothScrollToPosition(JOB_POINTER);
    }

    public void jobuser(View view) {
        intent = new Intent(this, JobsDetails.class);
        startActivity(intent);
    }

    private ArrayList initJobs() {
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