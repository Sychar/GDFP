package com.jess.gdfp.View;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.jess.gdfp.DatenBank.InfoDataBase;
import com.jess.gdfp.Kennlinie;
import com.jess.gdfp.MainActivity;
import com.jess.gdfp.R;

public class Setting extends AppCompatActivity {

    private final static String TAG = Setting.class.getSimpleName(); //name of this class
    private Intent intent;
    private InfoDataBase datenReader;
    public static boolean JOBUSER_TOKEN = false;

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
                //Log.i("TAG", "settingThread is running");

                runOnUiThread(() -> {

                    if (MainActivity.CHANGE_TOKEN) {
                        //Log.i(TAG, "CHANGE_TOKEN true");
                        if (MainActivity.SETTING_COUNTER == 1) {
                            //Log.i("Setting ", "Datenlogger");
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
                            if (MainActivity.ENCODERBUTTON_TOKEN) {
                                datalogger.performClick();
                                MainActivity.ENCODERBUTTON_TOKEN =  false;
                            }
                        } else if (MainActivity.SETTING_COUNTER == 2) {
                            //Log.i("Setting ", "Kennlinie");
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
                            if (MainActivity.ENCODERBUTTON_TOKEN) {
                                Kennlinie.performClick();
                                MainActivity.ENCODERBUTTON_TOKEN =  false;
                            }
                        } else if (MainActivity.SETTING_COUNTER == 3) {
                            //Log.i("Setting ", "Setting");
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
                            if (MainActivity.ENCODERBUTTON_TOKEN) {
                                menu_setting.performClick();
                                MainActivity.ENCODERBUTTON_TOKEN =  false;
                            }
                        } else if (MainActivity.SETTING_COUNTER == 4) {
                            //Log.i("Setting ", "exit");
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
                            if (MainActivity.ENCODERBUTTON_TOKEN) {
                                account.performClick();
                                MainActivity.ENCODERBUTTON_TOKEN = false;
                            }

                        }else if (MainActivity.SETTING_COUNTER == 5) {
                            //Log.i("Setting ", "jobs");
                            jobuser.setTextColor(Color.WHITE);
                            jobuser.setBackgroundColor(Color.BLACK);
                            datalogger.setTextColor(Color.BLACK);
                            datalogger.setBackgroundColor(Color.GRAY);
                            Kennlinie.setTextColor(Color.BLACK);
                            Kennlinie.setBackgroundColor(Color.GRAY);
                            menu_setting.setTextColor(Color.BLACK);
                            menu_setting.setBackgroundColor(Color.GRAY);
                            account.setTextColor(Color.BLACK);
                            account.setBackgroundColor(Color.GRAY);
                            if (MainActivity.ENCODERBUTTON_TOKEN) {
                                jobuser.performClick();
                                MainActivity.ENCODERBUTTON_TOKEN =  false;
                            }
                        }
                    } /*else {
                        //Log.i(TAG, "CHANGE_TOKEN false");
                        datalogger.setTextColor(Color.BLACK);
                        datalogger.setBackgroundColor(Color.GRAY);
                        Kennlinie.setTextColor(Color.BLACK);
                        Kennlinie.setBackgroundColor(Color.GRAY);
                        menu_setting.setTextColor(Color.BLACK);
                        menu_setting.setBackgroundColor(Color.GRAY);
                    }*/

                });

                /*if (MainActivity.SETTING_TOKEN) { //this part works
                    jobuser.setTextColor(Color.WHITE);
                    jobuser.setBackgroundColor(Color.BLACK);
                    Log.i("Setting button", "is pressed");
                } else {
                    jobuser.setTextColor(Color.BLACK);
                    jobuser.setBackgroundColor(Color.GRAY);
                }*/

                /*if (MainActivity.CHANGE_TOKEN) {
                    Log.i(TAG, "CHANGE_TOKEN true");
                    if (MainActivity.SETTING_COUNTER == 1) {
                        Log.i("Setting ", "Datenlogger");
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
                    } else if (MainActivity.SETTING_COUNTER == 2) {
                        Log.i("Setting ", "Kennlinie");
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
                    } else if (MainActivity.SETTING_COUNTER == 3) {
                        Log.i("Setting ", "Setting");
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
                    } else if (MainActivity.SETTING_COUNTER == 4) {
                        Log.i("Setting ", "exit");
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
                } else {
                    Log.i(TAG, "CHANGE_TOKEN false");
                    datalogger.setTextColor(Color.BLACK);
                    datalogger.setBackgroundColor(Color.GRAY);
                    Kennlinie.setTextColor(Color.BLACK);
                    Kennlinie.setBackgroundColor(Color.GRAY);
                    menu_setting.setTextColor(Color.BLACK);
                    menu_setting.setBackgroundColor(Color.GRAY);
                }*/

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
        account.setBackgroundColor(Color.GRAY);
        datalogger.setBackgroundColor(Color.GRAY);
        menu_setting.setBackgroundColor(Color.GRAY);
        Kennlinie.setBackgroundColor(Color.GRAY);
        if (MainActivity.SETTING_TOKEN) { //only runs one time
            jobuser.setTextColor(Color.WHITE);
            jobuser.setBackgroundColor(Color.BLACK);
            //Log.i("Setting button", "is pressed");
        } else {
            jobuser.setTextColor(Color.BLACK);
            jobuser.setBackgroundColor(Color.GRAY);
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

        if (MainActivity.SETTING_TOKEN) { //only runs one time
            Button jobuser_btn = findViewById(R.id.jobsUser);
            jobuser_btn.setTextColor(Color.WHITE);
            jobuser_btn.setBackgroundColor(Color.BLACK);
            MainActivity.SETTING_COUNTER = 5;
            Log.i("Setting button", "is pressed");
        } /*else {
            jobuser_btn.setTextColor(Color.BLACK);
            jobuser_btn.setBackgroundColor(Color.GRAY);
        }*/
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
        Intent  intent = new Intent(this, DatalistView.class);
        startActivity(intent);
    }
    void onClick_Kennlinie() {
        Intent  intent = new Intent(this, Kennlinie.class);
        startActivity(intent);
    }
    void onClick_MenuSetting() {
        Log.i(TAG,"say hi");
    }
}

