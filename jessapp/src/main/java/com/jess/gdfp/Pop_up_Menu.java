package com.jess.gdfp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jess.gdfp.Controller.Read_Usb_Device;

import java.io.File;

public class Pop_up_Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up__menu);
        TextView textView =findViewById(R.id.popup);
        TextView textView2 =findViewById(R.id.popup2);
        Button exit= findViewById(R.id.popup_exit);
        Button update=findViewById(R.id.popup_update);

        Bundle extras = getIntent().getExtras();
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getRealMetrics(dm);
        int width =dm.widthPixels;
        int height=dm.heightPixels;

        getWindow().setLayout((int)(width*.7),(int)(height*.7));
        long i = extras.getLong("Isusb");
         String str = Read_Usb_Device.str;
        if(i!=0){
            textView.setText("usb is available ");
            textView2.setText(str);
            textView2.setTextColor(Color.GREEN);
            textView.setTextColor(Color.GREEN);

        }
        else {
            textView.setText("usb is not  aVailable");
            textView.setTextColor(Color.RED);
            update.setEnabled(false);
            textView2.setText(str);
            textView.setTextColor(Color.RED);
        }
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //update_method();
            }
        });
    }

    void update_method(){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(new File
                (Environment.getExternalStorageDirectory()  + "/myapk.apk")), "application/vnd.android.package-archive");
        startActivity(intent);
    }


}