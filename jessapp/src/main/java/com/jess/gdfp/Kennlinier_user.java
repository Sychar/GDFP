package com.jess.gdfp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;

import com.jess.gdfp.Controller.Kennlinie_user_adapter;
import com.jess.gdfp.Controller.jobAdapter;
import com.jess.gdfp.DatenBank.Jobs;
import com.jess.gdfp.DatenBank.Kennlinie;
import com.jess.gdfp.DatenBank.MyJeson;
import com.jess.gdfp.View.JobsDetails;
import com.jess.gdfp.View.Setting;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


import static com.jess.gdfp.Controller.JsonKennlinie.get_all_Daten;
import static com.jess.gdfp.Controller.JsonKennlinie.get_all_Daten2;

public class Kennlinier_user extends AppCompatActivity {
    private Intent intent;
    CardView myCardView;
    CheckBox checkBox;
    private static ListView rv;
    private static Kennlinie_user_adapter kennlinie_user_adapter;
    private ArrayList<Kennlinie> jobs;
    public static int KENN_POINTER;
    private static List<String[]> data;
    private static List<String[]> data1;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kennlinier_user);

     //  myCardView = findViewById(R.id.card_view);
       // checkBox=findViewById(R.id.cheak_box);
        // myCardView.setCardBackgroundColor(Color.TRANSPARENT);

        rv = findViewById(R.id.list_for_jobs);
        jobs = initKENN();


        kennlinie_user_adapter = new Kennlinie_user_adapter(this, jobs);



        rv.setAdapter(kennlinie_user_adapter);
        rv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    System.out.println(i);
                  dataübertragung(data1.get(i));
            }
        });
    }

  //  public static void changeKennBackground(int counter){
    //    KENN_POINTER = counter;
      //kennlinie_user_adapter.notifyDataSetChanged();
       // rv.smoothScrollToPosition(KENN_POINTER);
    //}

    public void jobuser(View view) {
        intent = new Intent(this, kennlinie_setting.class);
        startActivity(intent);
        //JOB_POINTER++;
        // myjobAdapte.notifyDataSetChanged();
    }
    public  void exit(View view){
        intent=new Intent(this, Setting.class);
        startActivity(intent);
        this.fileList();
    }

    private ArrayList initKENN() {
        String conditions[]={"Verfahren", "Werkstof","Draht","Gas","KennNr"};
        String[] params={
                "Verfahren","Werkstof" ,"Gas" ,"Draht" , "Bezeichnung" ,"Vorsch-Min","Vorsch-max","A-min","A-max","v/Hz-Min","v/Hz-Max","mm-Max","ESS-Nr",
        };

       data1=get_all_Daten2(params,this);
      data =get_all_Daten(conditions,this);
            System.out.println(data.size());
        ArrayList Kennlinies = new ArrayList<>();
      for(int j=0;j<data.size();j++){
          Kennlinie kennlinie =new Kennlinie(data.get(j)[0],data.get(j)[1],data.get(j)[2],data.get(j)[3],data.get(j)[4]);
          System.out.println(data.get(j)[0]);
          Kennlinies.add(kennlinie);
      }



        return Kennlinies;
    }
    private void dataübertragung(String[]s){
        Intent explicitIntent = new Intent(this, kennlinie_setting.class);
        explicitIntent.putExtra("data1", s);
        startActivity(explicitIntent);
    }
}
