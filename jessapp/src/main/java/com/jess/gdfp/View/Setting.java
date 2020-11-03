package com.jess.gdfp.View;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.jess.gdfp.Controller.ReadCSV;
import com.jess.gdfp.DatenBank.InfoDataBase;
import com.jess.gdfp.GlobalVariable;
import com.jess.gdfp.Kennlinier_user;
import com.jess.gdfp.MainActivity;
import com.jess.gdfp.R;
import com.jess.gdfp.WeldingChangeParam;

import java.io.File;

public class Setting extends AppCompatActivity {

    private final static String TAG = Setting.class.getSimpleName(); //name of this class
    private Intent intent;
    private InfoDataBase datenReader;
    public static boolean JOBUSER_TOKEN = false;
    public static boolean KENN_TOKEN = false;
    private boolean INIT_ENCODER = false;

    private Button Kennlinie;
    private Button jobuser;
    private Button account;
    private Button datalogger;
    private Button menu_setting;

    private void settingThread(){
        new Thread(() -> {
            while(true) {
                Kennlinie = findViewById(R.id.setting_kennlinie);
                jobuser = findViewById(R.id.jobsUser);
                account = findViewById(R.id.accoutnt); //EXIT
                datalogger = findViewById(R.id.dlogger);
                menu_setting = findViewById(R.id.menusetting);

                runOnUiThread(() -> {
                    if (GlobalVariable.CHANGE_TOKEN) {
                        //Log.i(TAG, "CHANGE_TOKEN true");
                        if (GlobalVariable.SETTING_COUNTER == 1) {
                            //Log.i("Setting ", "jobs");
                            jobuser.setTextColor(Color.BLACK);
                            jobuser.setBackground(getResources().getDrawable( R.drawable.job_button_chosen ));
                            datalogger.setTextColor(Color.WHITE);
                            datalogger.setBackground(getResources().getDrawable( R.drawable.job_button ));
                            Kennlinie.setTextColor(Color.WHITE);
                            Kennlinie.setBackground(getResources().getDrawable( R.drawable.job_button ));
                            menu_setting.setTextColor(Color.WHITE);
                            menu_setting.setBackground(getResources().getDrawable( R.drawable.job_button ));
                            account.setTextColor(Color.WHITE);
                            account.setBackground(getResources().getDrawable( R.drawable.job_button ));
                            if (WeldingChangeParam.ENCODERBUTTON_TOKEN) {
                                jobuser.performClick();
                                WeldingChangeParam.ENCODERBUTTON_TOKEN =  false;
                                Log.i("Jobs button", "is pressed");
                            }
                        } else if (GlobalVariable.SETTING_COUNTER == 2) {
                            //Log.i("Setting ", "Datenlogger");
                            datalogger.setTextColor(Color.BLACK);
                            datalogger.setBackground(getResources().getDrawable( R.drawable.job_button_chosen ));
                            jobuser.setTextColor(Color.WHITE);
                            jobuser.setBackground(getResources().getDrawable( R.drawable.job_button ));
                            Kennlinie.setTextColor(Color.WHITE);
                            Kennlinie.setBackground(getResources().getDrawable( R.drawable.job_button ));
                            menu_setting.setTextColor(Color.WHITE);
                            menu_setting.setBackground(getResources().getDrawable( R.drawable.job_button ));
                            account.setTextColor(Color.WHITE);
                            account.setBackground(getResources().getDrawable( R.drawable.job_button ));
                            if (WeldingChangeParam.ENCODERBUTTON_TOKEN) {
                                datalogger.performClick();
                                WeldingChangeParam.ENCODERBUTTON_TOKEN =  false;
                                Log.i("Datalogger button", "is pressed");
                            }
                        } else if (GlobalVariable.SETTING_COUNTER == 3) {
                            //Log.i("Setting ", "Kennlinie");
                            Kennlinie.setTextColor(Color.BLACK);
                            Kennlinie.setBackground(getResources().getDrawable( R.drawable.job_button_chosen ));
                            jobuser.setTextColor(Color.WHITE);
                            jobuser.setBackground(getResources().getDrawable( R.drawable.job_button ));
                            datalogger.setTextColor(Color.WHITE);
                            datalogger.setBackground(getResources().getDrawable( R.drawable.job_button ));
                            menu_setting.setTextColor(Color.WHITE);
                            menu_setting.setBackground(getResources().getDrawable( R.drawable.job_button ));
                            account.setTextColor(Color.WHITE);
                            account.setBackground(getResources().getDrawable( R.drawable.job_button ));
                            if (WeldingChangeParam.ENCODERBUTTON_TOKEN) {
                                Kennlinie.performClick();
                                WeldingChangeParam.ENCODERBUTTON_TOKEN =  false;
                                Log.i("Kennlinie button", "is pressed");
                            }
                        } else if (GlobalVariable.SETTING_COUNTER == 4) {
                            //Log.i("Setting ", "Setting");
                            menu_setting.setTextColor(Color.BLACK);
                            menu_setting.setBackground(getResources().getDrawable( R.drawable.job_button_chosen ));
                            jobuser.setTextColor(Color.WHITE);
                            jobuser.setBackground(getResources().getDrawable( R.drawable.job_button ));
                            datalogger.setTextColor(Color.WHITE);
                            datalogger.setBackground(getResources().getDrawable( R.drawable.job_button ));
                            Kennlinie.setTextColor(Color.WHITE);
                            Kennlinie.setBackground(getResources().getDrawable( R.drawable.job_button ));
                            account.setTextColor(Color.WHITE);
                            account.setBackground(getResources().getDrawable( R.drawable.job_button ));
                            if (WeldingChangeParam.ENCODERBUTTON_TOKEN) {
                                menu_setting.performClick();
                                WeldingChangeParam.ENCODERBUTTON_TOKEN =  false;
                                Log.i("Setting button", "is pressed");
                            }
                        } else if (GlobalVariable.SETTING_COUNTER == 5) {
                            //Log.i("Setting ", "exit");
                            account.setTextColor(Color.BLACK);
                            account.setBackground(getResources().getDrawable( R.drawable.job_button_chosen ));
                            jobuser.setTextColor(Color.WHITE);
                            jobuser.setBackground(getResources().getDrawable( R.drawable.job_button ));
                            datalogger.setTextColor(Color.WHITE);
                            datalogger.setBackground(getResources().getDrawable( R.drawable.job_button ));
                            Kennlinie.setTextColor(Color.WHITE);
                            Kennlinie.setBackground(getResources().getDrawable( R.drawable.job_button ));
                            menu_setting.setTextColor(Color.WHITE);
                            menu_setting.setBackground(getResources().getDrawable( R.drawable.job_button ));
                            if (WeldingChangeParam.ENCODERBUTTON_TOKEN) {
                                account.performClick();
                                WeldingChangeParam.ENCODERBUTTON_TOKEN = false;
                                Log.i("Exit button", "is pressed");
                            }
                        }
                    }else if(!INIT_ENCODER){
                        if (WeldingChangeParam.ENCODERBUTTON_TOKEN) {
                            //Log.i("Setting ", "jobs");
                            jobuser.performClick();
                            WeldingChangeParam.ENCODERBUTTON_TOKEN =  false;
                            INIT_ENCODER = true;
                        }
                    }
                    if ((WeldingChangeParam.HOME_TOKEN) && (!WeldingChangeParam.MA_TOKEN)){
                        intent = new Intent(this, MainActivity.class);
                        startActivity(intent);
                        WeldingChangeParam.HOME_TOKEN = false;
                        WeldingChangeParam.MA_TOKEN = true;
                        Log.i("Home button", "is pressed");
                    }
                });
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

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
        account.setTextColor(Color.WHITE);
        account.setBackground(getResources().getDrawable( R.drawable.job_button ));
        datalogger.setTextColor(Color.WHITE);
        datalogger.setBackground(getResources().getDrawable( R.drawable.job_button ));
        menu_setting.setTextColor(Color.WHITE);
        menu_setting.setBackground(getResources().getDrawable( R.drawable.job_button ));
        Kennlinie.setTextColor(Color.WHITE);
        Kennlinie.setBackground(getResources().getDrawable( R.drawable.job_button ));
        if (GlobalVariable.SETTING_TOKEN) { //only runs one time
            jobuser.setTextColor(Color.BLACK);
            jobuser.setBackground(getResources().getDrawable( R.drawable.job_button_chosen ));
        }
        settingThread();

        jobuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickJobUser();
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
        menu_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClick_MenuSetting();
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();

        if (GlobalVariable.SETTING_TOKEN) {
            Button jobuser_btn = findViewById(R.id.jobsUser);
            jobuser_btn.setTextColor(Color.BLACK);
            jobuser_btn.setBackground(getResources().getDrawable( R.drawable.job_button_chosen ));
            GlobalVariable.SETTING_COUNTER = 1;
            //Log.i(TAG, "onResume");
        }
    }

    void onClickJobUser(){
        JOBUSER_TOKEN = true;
        intent = new Intent(this, JobsUser.class);
        startActivity(intent);
    }
    void onClickAccount(){
        this.finish();
    }
    void onClick_Datenlogger() {
        ReadCSV.readCSv(this);
        Intent intent = new Intent(this, DatalistView.class);
        startActivity(intent);
        ReadCSV.readCSv(this);

    }
    void onClick_Kennlinie() {

        KENN_TOKEN = true;
        Intent intent = new Intent(this, Kennlinier_user.class);
        startActivity(intent);
    }
    void onClick_MenuSetting() {
        Log.i(TAG,"say hi");
    }

    public String Database() {
    String str="sakhr";
        for (int i = 0; i < 100; i++) {
            str = str.concat("Aljendi");
        }
        return  str;
    }
}