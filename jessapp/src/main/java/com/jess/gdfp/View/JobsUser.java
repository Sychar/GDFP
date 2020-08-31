package com.jess.gdfp.View;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;

import com.jess.gdfp.Controller.jobAdapter;
import com.jess.gdfp.DatenBank.Jobs;
import com.jess.gdfp.R;

import java.util.ArrayList;
import java.util.List;

public class JobsUser extends AppCompatActivity {
    private Intent intent;
    CardView myCardView;
    private  ArrayList<Jobs> jobs;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs_user);
        myCardView =(CardView) findViewById(R.id.card_view);
       // myCardView.setCardBackgroundColor(Color.TRANSPARENT);
        ListView rv = findViewById(R.id.list_for_jobs);
        jobs=initJobs();
        final jobAdapter myjobAdapte= new jobAdapter(this,jobs);
        rv.setAdapter(myjobAdapte);




    }
   public void jobuser (View view){
        intent = new Intent(this, UserDeatils.class);
        startActivity(intent);

    }

    private  ArrayList initJobs(){
        ArrayList jobs =new ArrayList<>();
        jobs.add(new Jobs("11","1","1"));
        jobs.add(new Jobs("11","2","1"));
        jobs.add(new Jobs("11","3","1"));
        jobs.add(new Jobs("11","4","1"));
        jobs.add(new Jobs("11","5","1"));
        jobs.add(new Jobs("11","6","1"));
        return jobs;
    }


}
