package com.jess.gdfp.View;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.jess.gdfp.DatenBank.InfoDataBase;
import com.jess.gdfp.Kennlinie;
import com.jess.gdfp.MainActivity;
import com.jess.gdfp.R;

public class Setting extends AppCompatActivity {
    private Intent intent;
    private InfoDataBase datenReader;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        datenReader=new InfoDataBase(this);

        Button Kennlinie = findViewById(R.id.setting_kennlinie);
        Button jobuser = findViewById(R.id.jobsUser);
        Button account = findViewById(R.id.accoutnt); //EXIT
        Button datalogger = findViewById(R.id.dlogger);
        Button menu_setting = findViewById(R.id.menusetting);

        if(MainActivity.SETTING_TOKEN){
            jobuser.setTextColor(Color.WHITE);
            jobuser.setBackgroundColor(Color.BLACK);
        }else{
            jobuser.setTextColor(Color.BLACK);
            jobuser.setBackgroundColor(Color.GRAY);
        }

        if(MainActivity.CHANGE_TOKEN){
            if(MainActivity.SETTING_COUNTER == 1) {
                datalogger.setTextColor(Color.WHITE);
                datalogger.setBackgroundColor(Color.BLACK);
                jobuser.setTextColor(Color.BLACK);
                jobuser.setBackgroundColor(Color.GRAY);
                Kennlinie.setTextColor(Color.BLACK);
                Kennlinie.setBackgroundColor(Color.GRAY);
                menu_setting.setTextColor(Color.BLACK);
                menu_setting.setBackgroundColor(Color.GRAY);
                account.setTextColor(Color.BLACK);
                account.setBackgroundColor(Color.GRAY);
            }else if(MainActivity.SETTING_COUNTER == 2){
                Kennlinie.setTextColor(Color.WHITE);
                Kennlinie.setBackgroundColor(Color.BLACK);
                jobuser.setTextColor(Color.BLACK);
                jobuser.setBackgroundColor(Color.GRAY);
                datalogger.setTextColor(Color.BLACK);
                datalogger.setBackgroundColor(Color.GRAY);
                menu_setting.setTextColor(Color.BLACK);
                menu_setting.setBackgroundColor(Color.GRAY);
                account.setTextColor(Color.BLACK);
                account.setBackgroundColor(Color.GRAY);
            }else if(MainActivity.SETTING_COUNTER == 3){
                menu_setting.setTextColor(Color.WHITE);
                menu_setting.setBackgroundColor(Color.BLACK);
                jobuser.setTextColor(Color.BLACK);
                jobuser.setBackgroundColor(Color.GRAY);
                datalogger.setTextColor(Color.BLACK);
                datalogger.setBackgroundColor(Color.GRAY);
                Kennlinie.setTextColor(Color.BLACK);
                Kennlinie.setBackgroundColor(Color.GRAY);
                account.setTextColor(Color.BLACK);
                account.setBackgroundColor(Color.GRAY);
            }else if(MainActivity.SETTING_COUNTER == 4){
                account.setTextColor(Color.WHITE);
                account.setBackgroundColor(Color.BLACK);
                jobuser.setTextColor(Color.BLACK);
                jobuser.setBackgroundColor(Color.GRAY);
                datalogger.setTextColor(Color.BLACK);
                datalogger.setBackgroundColor(Color.GRAY);
                Kennlinie.setTextColor(Color.BLACK);
                Kennlinie.setBackgroundColor(Color.GRAY);
                menu_setting.setTextColor(Color.BLACK);
                menu_setting.setBackgroundColor(Color.GRAY);
                MainActivity.SETTING_COUNTER = 0;
            }
        }else{
            datalogger.setTextColor(Color.BLACK);
            datalogger.setBackgroundColor(Color.GRAY);
            Kennlinie.setTextColor(Color.BLACK);
            Kennlinie.setBackgroundColor(Color.GRAY);
            menu_setting.setTextColor(Color.BLACK);
            menu_setting.setBackgroundColor(Color.GRAY);
        }

        jobuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClicJobUser();
            }
        });
        datalogger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClick_Datenlogger();
            }
        });
        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickAccount();
            }
        });
        Kennlinie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClick_Kennlinie();
            }
        });

    }
    void onClicJobUser(){
        intent = new Intent(this, JobsUser.class);
        startActivity(intent);
    }

    void onClickAccount(){
        this.finish();
    }
    void onClick_Datenlogger() {
        Intent  intent = new Intent(this, DatalistView.class);
        startActivity(intent);
    }
    void onClick_Kennlinie() {
        Intent  intent = new Intent(this, Kennlinie.class);
        startActivity(intent);
    }
}

