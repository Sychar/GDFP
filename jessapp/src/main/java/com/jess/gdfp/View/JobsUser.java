package com.jess.gdfp.View;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.jess.gdfp.Controller.jobAdapter;
import com.jess.gdfp.DatenBank.Jobs;
import com.jess.gdfp.R;

import java.util.ArrayList;
import java.util.List;

public class JobsUser extends AppCompatActivity {
    private Intent intent;
    CardView myCardView;
    CheckBox checkBox;
    ListView rv;
    private ArrayList<Jobs> jobs;
    static  public int pointer =0;
    private jobAdapter myjobAdapte;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs_user);

        if(Setting.JOBUSER_TOKEN){
            Setting.JOBUSER_TOKEN = false;
        }
        myCardView = (CardView) findViewById(R.id.card_view);
        checkBox=findViewById(R.id.cheak_box);
        // myCardView.setCardBackgroundColor(Color.TRANSPARENT);
        LinearLayoutManager mLayoutManager0 = new LinearLayoutManager (this );
        mLayoutManager0.setOrientation(LinearLayoutManager.VERTICAL);
         rv = findViewById(R.id.list_for_jobs);
        jobs = initJobs();
          myjobAdapte = new jobAdapter(this, jobs);

        rv.setAdapter(myjobAdapte);





    }

    public void jobuser(View view) {
       // intent = new Intent(this, JobsDetails.class);
        //startActivity(intent);
          pointer++;
          myjobAdapte.notifyDataSetChanged();


    }

    private ArrayList initJobs() {
        ArrayList jobs = new ArrayList<>();
        jobs.add(new Jobs("1", "info1"));
        jobs.add(new Jobs("2", "info2"));
        jobs.add(new Jobs("3", "info3"));
        jobs.add(new Jobs("4", "info4"));
        jobs.add(new Jobs("5", "info5"));
        jobs.add(new Jobs("6", "info6"));
        return jobs;
    }
}