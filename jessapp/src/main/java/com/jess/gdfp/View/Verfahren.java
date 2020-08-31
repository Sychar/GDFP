package com.jess.gdfp.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.jess.gdfp.R;

public class Verfahren extends AppCompatActivity {
    LinearLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verfahren);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width =dm.widthPixels;
        int height =dm.heightPixels;
        getWindow().setLayout((int) (width*0.8),(int )(height*0.5));
        layout = (LinearLayout) findViewById(R.id.zweitelayout);
        Button button =(Button) findViewById(R.id.exit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Verfahren.this.finish();
            }

        });


    }
}
