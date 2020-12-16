package com.jess.gdfp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ListView;

import com.jess.gdfp.Controller.kennlinieAdapte;

import java.util.ArrayList;


public class kennlinie_setting extends AppCompatActivity {

    static  String[]s;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kennlinie);
        ListView ls = findViewById(R.id.kennlinie_setting);
        Intent intent = this.getIntent();
        s =intent.getStringArrayExtra("data1");
       ArrayList myArray = initkennline();
        kennlinieAdapte myAdapter = new kennlinieAdapte(this,myArray);
        ls.setAdapter(myAdapter);

    }

private  String cond[]={
        "Verfahren","Werkstof" ,
        "Gas" ,"Draht" , "Bezeichnung"
        ,"Vorsch-Min","Vorsch-max",
        "A-min","A-max",
        "v/Hz-Min","v/Hz-Max",
        "mm-Max","ESS-Nr"};

   private  ArrayList initkennline() {

        ArrayList Kennline_array = new ArrayList<>();
        for( int i=0;i<cond.length;i++){
            System.out.println("len"+s.length);
         Kennline_array.add(new com.jess.gdfp.DatenBank.Jobs(cond[i],s[i]));
        }
            return Kennline_array;
    }


}
