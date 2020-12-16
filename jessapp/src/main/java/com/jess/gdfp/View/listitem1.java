package com.jess.gdfp.View;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;


import com.jess.gdfp.R;

import java.util.ArrayList;


public class listitem1 extends AppCompatActivity {
    private ArrayList<Details> detail ;

    ListView listView;
    listitem1 listitem2 =this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listitem1);
        DisplayMetrics dm= new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int widht=dm.widthPixels;
        int height =dm.heightPixels;
        getWindow().setLayout((int )(widht*0.8),(int)(height*0.5));
        Button button =(Button)findViewById(R.id.exitBetrieb);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listitem1.this.finish();
            }
        });
        listView=(ListView) findViewById(R.id.listview);
        detail= new ArrayList<>();
        detail.add(new Details("betrieb1",R.drawable.test));
        detail.add(new Details("betrieb2",R.drawable.test));
        detail.add(new Details("betrieb3",R.drawable.test));
        detail.add(new Details("betrieb4",R.drawable.test));
        RVAdapter rvAdapter =new RVAdapter(listitem1.this,detail);
        listView.setAdapter(rvAdapter);
    }
}
