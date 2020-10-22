package com.jess.gdfp;

import android.app.AlertDialog;

import android.content.ContentValues;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.felhr.usbserial.UsbSerialDevice;
import com.jess.gdfp.Controller.MainActivity_Controller;
import com.jess.gdfp.View.BetriebsArt;
import com.jess.gdfp.View.BlankFragment;
import com.jess.gdfp.View.JobsDetails;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.InvalidParameterException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import android_serialport_api.SerialPort;
import static com.jess.gdfp.UartService.mOutputStream;

import static java.lang.StrictMath.abs;

public class MainActivity extends AppCompatActivity implements BlankFragment.OnFragmentInteractionListener , BetriebsArt.OnFragmentInteractionListener{

    private final static String TAG = MainActivity.class.getSimpleName(); //name of this class
    static TextView txtProgress ;
    private TextView StromHoldwertTV;
    private TextView SpannungHoldwertTV;
    private TextView HoldIst;
    private ProgressBar progressBarPlus;
    private ProgressBar progressBarMinus;
    private LinearLayout hold_layout;
    private static ProgressBar progressBar;
    private Handler handler = new Handler();
    private Handler KENN_HANDLER = new Handler();
    private Handler newHandler = new Handler();

    private Button m_min;
    private Button current;
    private Button voltage;
    private Button droessel;
    private Button circle_button;
    private Button minus_button;
    private Button betriebsart;
    private Button plus;
    private Button WIG_btn;
    private Button MMNormal_btn;
    private Button MMSynergy_btn;
    private Button MMPuls_btn;
    private Button ElectrodeMMA_btn;
    private Button WIGPulsen_btn;
    private Button WIGSpeed_btn;
    private Button WIGSpeedPulsen_btn;
    private Button Fav1_btn;
    private Button Fav2_btn;
    private Button Fav3_btn;
    private Button Fav4_btn;
    private ImageButton backHome;
    private int len;
    private TextView ANZEIGE1;
    private TextView ANZEIGE2;
    private TextView ANZEIGE3;
    private TextView ANZEIGE4;
    private TextView tdate;
    private TextView tdate2;
    private Button button_yes;
    private Button button_no;
    private static TextView txtprogress;
    private ImageButton Setting;
    private View frame;
    private View kenn_fragment;
    private View betriebsart_fragement;
    private Button kennlinie;
    public static Button JOB_NUM;

    public static boolean JOB_TOKEN = false;
    public static boolean STOP_DATENOBJEKTE = false;
    public static boolean PARSE_TOKEN = true;
    private static boolean kenn_token = false;
    private Button verfahren_btn;

    public static int headercounter=0;
    private static int counterDisplay = 0;
    private static int counterDisplay1 = 0;

    private static byte[] TEMP_BA = new byte[40];
    private static String TEMP_STRING = "";
    private static String KENN_STRING = "";
    public static String msg_for_me = "";
    public static String msg_for_can = "";
    public static String msg_for_can1 = "";
    private static int countFrame = 0;

    static boolean kennlinie_gedrückt = false;
    static boolean job_gedrückt = false;
    private boolean fav1_gedrückt = false;
    private boolean fav2_gedrückt = false;
    private boolean fav3_gedrückt = false;
    private boolean fav4_gedrückt = false;

    private static byte[] TMP_KENNFRAME = new byte[430];
    public DatenObjekteSend sendEnergie = new DatenObjekteSend();

    private static Charset iso88591charset = Charset.forName("ISO-8859-1");

    private static Boolean prograssHeid = true;
    private static  boolean check = true;
    private  static  String temp ="Energie";

    private UsbSerialDevice serial;

    private UartService.MyHandler mHandler;
    private static UartService uartService;

    private AlertDialog.Builder builder;
    private AlertDialog dialog;

    public static byte READVAL_STATUS[] = new byte[10];

     private void serial_init(){
         try {
             //UartService.mSerialPort = mApplication.getSerialPort();
             UartService.mSerialPort = new SerialPort(new File("/dev/ttyS4"),500000, 0); //Open the serial port //2000000
             mOutputStream = UartService.mSerialPort.getOutputStream();
             UartService.mInputStream = UartService.mSerialPort.getInputStream();

             //Create a receiving thread
             UartService.mReadThread = new UartService.ReadThread();
             UartService.mReadThread.start();
             Log.i(TAG,"ReadThread starts");
         } catch (SecurityException e) {
             Log.e(TAG,"SecurityException");
             //DisplayError(R.string.error_security);
         } catch (IOException e) {
             //DisplayError(R.string.error_unknown);
             Log.e(TAG,"IOException");
         } catch (InvalidParameterException e) {
             //DisplayError(R.string.error_configuration);
             Log.e(TAG,"InvalidParameterException");
         }
     }

    MainActivity_Controller mainActivityController = new MainActivity_Controller(MainActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        READVAL_STATUS[1]=0; //m/min
        READVAL_STATUS[2]=0; //korrektur
        READVAL_STATUS[3]=0; //Voltage

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init_view();
        //progrssinit();
        setVisibility();
        serial_init();

        //-------------------------------- For job button only -------------------------------------
        builder = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_layout,null);
        builder.setView(dialogView);

        button_no = dialogView.findViewById(R.id.button_no);
        button_yes = dialogView.findViewById(R.id.button_yes);
        dialog = builder.create();
        button_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //----------------------- store job ------------------------------------------------
                DatenObjekteSend storejob = new DatenObjekteSend();
                storejob.ChangeParameter(37,13,0); //store job mode
                //------------------------- Exit job -----------------------------------------------
                GlobalVariable.JOB_PRESSED = false;
                JOB_NUM.setText("JOB");
                JOB_NUM.setTextColor(Color.WHITE);
                JOB_NUM.setBackgroundColor(Color.BLACK);
                Uri uri = Uri.parse("content://com.jess.gdfp.jobs/jobs");
                ContentValues values= new ContentValues();
                for(int i=0;i<DatenObjekteJob.DataBase().length ;i++){
                    values.put(JobsDetails.jobdetails[i],DatenObjekteJob.DataBase()[i]);
                }
                Cursor cursor = (Cursor) getContentResolver().insert(uri,values);
                job_gedrückt = false;
                dialog.cancel();
            }
        });

        button_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //------------------------- Exit job -----------------------------------------------
                GlobalVariable.JOB_PRESSED = false;
                JOB_NUM.setText("JOB");
                JOB_NUM.setTextColor(Color.WHITE);
                JOB_NUM.setBackgroundColor(Color.BLACK);
                job_gedrückt = false;
                dialog.cancel();
            }
        });
        //------------------------------------------------------------------------------------------

        mHandler = new UartService.MyHandler();
        Setting= findViewById(R.id.setting);
        Setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view ) {
                GlobalVariable.SETTING_TOKEN = true;
                mainActivityController.onClick_newActivity(com.jess.gdfp.View.Setting.class);
                WeldingChangeParam.MA_TOKEN = false;
            }
        });

        circle_button.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                shortclick();
                return false;
            }
        });

        View view = findViewById(R.id.fragment_test);
        view.setVisibility(View.INVISIBLE);


        verfahren_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!kenn_token) {
                    v = findViewById(R.id.framelayout);
                    allgemeinOnclick(v);
                    ausblinden(kenn_fragment, betriebsart_fragement);
                    droessel_gone();
                    hold_layout_gone();
                }
            }
        });

        final ProgressBar progress = findViewById(R.id.progress);
        progress.setVisibility(View.GONE);
        betriebsart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!kenn_token) {
                    allgemeinOnclick(betriebsart_fragement);
                    ausblinden(frame, kenn_fragment);
                    droessel_gone();
                    hold_layout_gone();
                }
            }
        });

        //-------------------------------- Verfahren button ----------------------------------------
        WIG_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlobalVariable.VERFAHREN_MODE = 5;
                GlobalVariable.Verfahren_Token = true;
                if (!GlobalVariable.WIG_btn_gedrückt) {
                    //WIG_btn.setTextColor(Color.BLACK);
                    //WIG_btn.setBackgroundColor(Color.WHITE);
                    GlobalVariable.WIG_btn_gedrückt=true;
                } else {
                    //WIG_btn.setTextColor(Color.WHITE);
                    //WIG_btn.setBackgroundResource(R.drawable.border2);
                    GlobalVariable.WIG_btn_gedrückt=false;
                }
            }
        });

        MMNormal_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //MMSynergy_btn.setTextColor(Color.WHITE);
                //MMPuls_btn.setTextColor(Color.WHITE);
                //ElectrodeMMA_btn.setTextColor(Color.WHITE);
                GlobalVariable.VERFAHREN_MODE = 1;
                GlobalVariable.Verfahren_Token = true;
                if (!GlobalVariable.MMNormal_btn_gedrückt) {
                    GlobalVariable.MMNormal_btn_gedrückt=true;
                } else {
                    //MMNormal_btn.setTextColor(Color.WHITE);
                    //MMNormal_btn.setBackgroundResource(R.drawable.border2);
                    GlobalVariable.MMNormal_btn_gedrückt=false;
                }
            }
        });

        MMSynergy_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlobalVariable.VERFAHREN_MODE = 2;
                GlobalVariable.Verfahren_Token = true;
                if (!GlobalVariable.MMSynergy_btn_gedrückt) {
                    MMNormal_btn.setTextColor(Color.WHITE);
                    //MMSynergy_btn.setTextColor(Color.BLACK);
                    //MMSynergy_btn.setBackgroundColor(Color.WHITE);
                    MMPuls_btn.setTextColor(Color.WHITE);
                    ElectrodeMMA_btn.setTextColor(Color.WHITE);
                    GlobalVariable.MMSynergy_btn_gedrückt = true;
                } else {
                    //MMSynergy_btn.setTextColor(Color.WHITE);
                    //MMSynergy_btn.setBackgroundResource(R.drawable.border2);
                    GlobalVariable.MMSynergy_btn_gedrückt = false;
                }
                GlobalVariable.MMSynergy_btn_onclick();
            }
        });

        MMPuls_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlobalVariable.VERFAHREN_MODE = 3;
                GlobalVariable.Verfahren_Token = true;
                if (!GlobalVariable.MMPuls_btn_gedrückt) {
                    MMNormal_btn.setTextColor(Color.WHITE);
                    MMSynergy_btn.setTextColor(Color.WHITE);
                    //MMPuls_btn.setTextColor(Color.BLACK);
                   // MMPuls_btn.setBackgroundColor(Color.WHITE);
                    ElectrodeMMA_btn.setTextColor(Color.WHITE);
                    GlobalVariable.MMPuls_btn_gedrückt = true;
                } else {
                    //MMPuls_btn.setTextColor(Color.WHITE);
                    //MMPuls_btn.setBackgroundResource(R.drawable.border2);
                    GlobalVariable.MMPuls_btn_gedrückt = false;
                }
            }
        });

        ElectrodeMMA_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlobalVariable.VERFAHREN_MODE = 4;
                GlobalVariable.Verfahren_Token = true;
                if (!GlobalVariable.MMPuls_btn_gedrückt) {
                    MMNormal_btn.setTextColor(Color.WHITE);
                    MMSynergy_btn.setTextColor(Color.WHITE);
                    MMPuls_btn.setTextColor(Color.WHITE);
                    //ElectrodeMMA_btn.setTextColor(Color.BLACK);
                    //ElectrodeMMA_btn.setBackgroundColor(Color.WHITE);
                    GlobalVariable.MMPuls_btn_gedrückt = true;
                } else {
                    //ElectrodeMMA_btn.setTextColor(Color.WHITE);
                    //ElectrodeMMA_btn.setBackgroundResource(R.drawable.border2);
                    GlobalVariable.MMPuls_btn_gedrückt = false;
                }
            }
        });

        /*WIGPulsen_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlobalVariable.VERFAHREN_MODE = 6;
                if(!GlobalVariable.verfahren_gedrückt) {
                    WIGPulsen_btn.setTextColor(Color.GREEN);
                    GlobalVariable.verfahren_gedrückt=true;
                }else if(GlobalVariable.verfahren_gedrückt){
                    WIGPulsen_btn.setTextColor(Color.WHITE);
                    GlobalVariable.verfahren_gedrückt=false;
                }
            }
        });*/

        /*WIGSpeed_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlobalVariable.VERFAHREN_MODE = 7;
                if(!GlobalVariable.verfahren_gedrückt) {
                    WIGSpeed_btn.setTextColor(Color.GREEN);
                    GlobalVariable.verfahren_gedrückt=true;
                }else if(GlobalVariable.verfahren_gedrückt){
                    WIGSpeed_btn.setTextColor(Color.WHITE);
                    GlobalVariable.verfahren_gedrückt=false;
                }
            }
        });*/

        /*WIGSpeedPulsen_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlobalVariable.VERFAHREN_MODE = 8;
                if(!GlobalVariable.verfahren_gedrückt) {
                    WIGSpeedPulsen_btn.setTextColor(Color.GREEN);
                    GlobalVariable.verfahren_gedrückt=true;
                }else if(GlobalVariable.verfahren_gedrückt){
                    WIGSpeedPulsen_btn.setTextColor(Color.WHITE);
                    GlobalVariable.verfahren_gedrückt=false;
                }
            }
        });*/
        //------------------------------------------------------------------------------------------

        kennlinie = findViewById(R.id.kenn);
        kennlinie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view=findViewById(R.id.fragment_test);
                allgemeinOnclick(view);
                ausblinden(frame,betriebsart_fragement);
                if (!kennlinie_gedrückt) {
                    kenn_token = true;
                    KENN_HANDLER.post(KENN_TIMER); //start the timer handler
                    kennlinie.setBackgroundColor(Color.GRAY);
                    kennlinie_gedrückt = true;
                    STOP_DATENOBJEKTE = true;
                    droessel_gone();
                    hold_layout_gone();
                } else {
                    kenn_token = false;
                    PARSE_TOKEN = true;
                    kennlinie.setBackgroundColor(Color.BLACK);
                    kennlinie_gedrückt = false;
                    STOP_DATENOBJEKTE = false;
                    KENN_HANDLER.removeCallbacks(KENN_TIMER); //stop the timer handler
                    backHome.performClick();
                    Drossel_view();
                    hold_layout_view();
                }
            }
        });

        droessel = findViewById(R.id.drossel);
        Drossel_view();
        droessel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (prograssHeid) {
                    progressBarPlus.setVisibility(View.GONE);
                    progressBarMinus.setVisibility(View.INVISIBLE);
                    minus_button.setVisibility(View.GONE);
                    plus.setVisibility(View.INVISIBLE);
                    prograssHeid = false;
                } else {
                    Drossel_view();
                }
            }
        });

        backHome.setOnClickListener(view1 -> {
            View layout = findViewById(R.id.zweitelayout);
            layout.setVisibility(View.VISIBLE);
            frame.setVisibility(View.INVISIBLE);
            kenn_fragment.setVisibility(View.INVISIBLE);
            View layout1 = findViewById(R.id.circleButton);
            betriebsart_fragement.setVisibility(View.INVISIBLE);
            layout1.setVisibility(View.VISIBLE);
            Drossel_view();
            hold_layout_view();
        });

        JOB_NUM.setOnClickListener(view12 -> {
            if (!job_gedrückt) {
                GlobalVariable.JOB_PRESSED = true;
                JOB_NUM.setTextColor(Color.BLACK);
                JOB_NUM.setBackgroundColor(Color.GRAY);
                JOB_NUM.setText("FAV1");
                //JOB_DISPLAY.setTextColor(Color.BLACK);
                //JOB_DISPLAY.setBackgroundColor(Color.GRAY);

                //---------------------------- Start Job ----------------------------------------
                DatenObjekteSend startJob = new DatenObjekteSend();
                startJob.ChangeParameter(20, 0, 0);
                job_gedrückt = true;
                //---------------------------- Store Job -------------------------------------------
                //DatenObjekteSend storeJob = new DatenObjekteSend();
                //storeJob.ChangeParameter(37, 0, 1);

                //---------------------------- Request Job -----------------------------------------
                //String FIRSTSTR = UartService.getJob(); //ascii string
                //String SECSTR = UartService.getJob1(1); //ascii string

            } else {
                GlobalVariable.JOB_PRESSED = false;
                JOB_NUM.setText("JOB");
                JOB_NUM.setTextColor(Color.WHITE);
                JOB_NUM.setBackgroundColor(Color.BLACK);
                job_gedrückt = false;
            }

            //---------------------------Increment Job number---------------------------------------
            /*DatenObjekteSend incrementJob = new DatenObjekteSend();
            incrementJob.ChangeParameter(4,0, 1);*/
            //---------------------------Decrement Job number---------------------------------------
            /*DatenObjekteSend decrementJob = new DatenObjekteSend();
            decrementJob.ChangeParameter(6,0, 1);*/
        });

        Fav1_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!fav1_gedrückt) {
                    //----------------------- get the job ------------------------------------------
                    DatenObjekteSend activateJob = new DatenObjekteSend();
                    activateJob.ChangeParameter(5, 0, 1);
                    fav1_gedrückt = true;
                } else {
                    DatenObjekteSend decrementJob = new DatenObjekteSend();
                    decrementJob.ChangeParameter(6,0, 1);
                    fav1_gedrückt = false;
                }
            }
        });

        Fav2_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!fav2_gedrückt) {
                    //----------------------- get the job ------------------------------------------
                    fav2_gedrückt = true;
                } else {
                    fav2_gedrückt = false;

                }

            }
        });

        Fav3_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!fav3_gedrückt) {
                    //----------------------- get the job ------------------------------------------
                    fav3_gedrückt = true;
                } else {
                    fav3_gedrückt = false;

                }

            }
        });

        Fav4_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!fav4_gedrückt) {
                    //----------------------- get the job ------------------------------------------
                    fav4_gedrückt = true;
                } else {
                    fav4_gedrückt = false;

                }

            }
        });

        newHandler.post(TimerHandler);
    }
/*------------------------------------------------------------------------------------------
    END of onCreate() method
------------------------------------------------------------------------------------------*/
    public void shortclick() {
        PopupMenu popupMenu = new PopupMenu(MainActivity.this, circle_button);
        popupMenu.getMenuInflater().inflate(R.menu.menu1, popupMenu.getMenu());
        popupMenu.show();
    }

    public void ChangeTextprogressBar(Button btn) {
        int id = btn.getId();
        if (id == R.id.btn_korrektur) {
            TextView textView = findViewById(R.id.korrektur);
            String s= textView.getText().toString();
            String s1 = btn.getText().toString();
            String s2= txtProgress.getText().toString();
            textView.setText(temp);
            btn.setText(s2);
            txtProgress.setText(s1);
            temp=s;
            check=false;
        }else if (id == R.id.btn_strom) {
            TextView textView = findViewById(R.id.Mm);
            String s = textView.getText().toString();
            String s1 = btn.getText().toString();
            String s2 = txtProgress.getText().toString();
            textView.setText(temp);
            txtProgress.setText(s1);
            btn.setText(s2);
            temp = s;
            check=false;
        }else if (id == R.id.btn_voltage) {
            TextView textView = findViewById(R.id.strom);
            String s= textView.getText().toString();
            String s1 =btn.getText().toString();
            String s2= txtProgress.getText().toString();
            textView.setText(temp);
            txtProgress.setText(s1);
            btn.setText(s2);
            temp=s;
            check=false;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        //setFilters();  // Start listening notifications from UartService
        UartService UsbObj = new UartService(this);//mine
        UsbObj.startService(UartService.class, UartService.usbConnection, null); // Start UartService(if it was not started before) and Bind it
        //final Intent  intent = new Intent(this, DatalistView.class);
        //startActivity(intent);
    }

    @Override
    public void onPause() {
        super.onPause();
        //unregisterReceiver(mUsbReceiver);
        unbindService(UartService.usbConnection);
    }

    protected void onStop() {
        super.onStop();
    }

    protected void onDestroy(){
        super.onDestroy();
        //For Khadas Vim
        if (UartService.mReadThread != null) UartService.mReadThread.interrupt();
        UartService.mSerialPort = null;
    }

    /**
     * Start timer to display data
     */
    private Runnable TimerHandler = new Runnable() {
        @Override
        public void run() {
            UartService.token = false;
            //setFilters();  // Start listening notifications from UartService
            counterDisplay++;
            counterDisplay1++;

            switch(WeldingChangeParam.HOME_COUNTER) {
                case 0:
                    if ((GlobalVariable.SV1pos1 == 1) && (GlobalVariable.mpm_display < 240)) { //Normal
                        progressBar.setProgress((int) (GlobalVariable.mpm_display) * (100 / 232) - (800 / 232));
                    } else if ((GlobalVariable.SV1pos1 == 2) && (GlobalVariable.mpm_display < 120)) { //Synergie
                        progressBar.setProgress((int) ((GlobalVariable.mpm_display) * 100 / 80 - 50));
                    } else if ((GlobalVariable.SV1pos1 == 3) && (GlobalVariable.mpm_display < 120)) {//Pulse
                        progressBar.setProgress(GlobalVariable.mpm_display - 20);
                    }
                    break;
            }

            if (READVAL_STATUS[1] == 1) {
                switch (WeldingChangeParam.HOME_COUNTER) {
                    case 0:
                        if(GlobalVariable.VERFAHREN_VAL!=4) {
                            txtprogress.setText(String.valueOf(GlobalVariable.mpm_display / 10) + "," + String.valueOf(GlobalVariable.mpm_display % 10) + "\n" + "m/min"); // m/min
                            //Log.i("mpm_display", String.valueOf(GlobalVariable.mpm_display));
                        } else {
                            WeldingChangeParam.HOME_COUNTER = 2;
                        }
                        break;
                    case 1:
                        txtprogress.setText(String.valueOf(GlobalVariable.BlechdickeSetwert / 10) + "," + String.valueOf(GlobalVariable.BlechdickeSetwert % 10) + "\n" + "mm"); //Drahtdurchmesser
                        break;
                    case 2:
                        txtprogress.setText(String.valueOf(GlobalVariable.StromSetwert) + "\n" + " A"); //A
                        break;
                    case 3:
                        txtprogress.setText(String.valueOf(GlobalVariable.Lichtbogenkorrektur1) + "\n" + "Korrektur"); //Korrektur
                        break;
                    case 4:
                        txtprogress.setText(GlobalVariable.Verfahren); //Verfahren
                        break;
                    case 5:
                        txtprogress.setText(GlobalVariable.Gas); //Gas
                        break;
                    case 6:
                        txtprogress.setText(GlobalVariable.Werkstoff + "\n" + "Werkstoff"); //Werkstoff
                        break;
                    case 7:
                        txtprogress.setText(GlobalVariable.Draht_String[GlobalVariable.Drahtdurchmesser]+ "\n" + "mm"); //Thickness mm
                        break;
                    case 8:
                        txtprogress.setText(String.valueOf(GlobalVariable.SpannungSetwert / 10) + "," + String.valueOf(GlobalVariable.SpannungSetwert % 10)+ "\n" + "Volt"); //Voltage
                        break;
                    case 9:
                        if(GlobalVariable.encoder){
                            txtprogress.setText(String.valueOf(GlobalVariable.Jobnummer) + "\n" + "Job"); //Job
                        }else txtprogress.setText("Job"); //Job

                        if (GlobalVariable.JOBCOUNT==0) txtprogress.setText("Job");
                        break;
                }
            }
                if (check) {
                    //if(DatenObjekte.SV1pos1 != 4) txtProgress.setText(String.valueOf(DatenObjekte.Energie1/10) + "," + String.valueOf(DatenObjekte.Energie1%10)+"\n"+"m/min"); // m/min
                    // if(DatenObjekte.SV1pos1 != 4) txtProgress.setText(String.valueOf(DatenObjekte.mpm_display/10) + "," + String.valueOf(DatenObjekte.mpm_display%10)+"\n"+"m/min"); // m/min
                    //else txtProgress.setText(String.valueOf(DatenObjekte.ElektrodeStromSetwert+" A")); // Elektrode mode
                    ANZEIGE1.setText(GlobalVariable.Verfahren_String[GlobalVariable.SV1pos1]); // Verfahren
                    ANZEIGE2.setText(GlobalVariable.Werksotff_String[GlobalVariable.SV1pos5]); // Werkstoff
                    ANZEIGE3.setText(GlobalVariable.Draht_String[GlobalVariable.Drahtdurchmesser]);
                    ANZEIGE4.setText(GlobalVariable.Gas_String[GlobalVariable.SV1pos4]); // Gas

                    //Log.i("Brennertasten1_string",GlobalVariable.Brennertasten1_string);
                    if(GlobalVariable.Brennertasten1_string.equals("Brennertaste1 Active")){
                        HoldIst.setText("Ist");
                        StromHoldwertTV.setText(String.valueOf(GlobalVariable.StromIstwert)+" A");
                        SpannungHoldwertTV.setText(String.valueOf(GlobalVariable.SpannungIstwert)+" V");
                    } else {
                        HoldIst.setText("Hold");
                        StromHoldwertTV.setText(String.valueOf(GlobalVariable.StromHoldwert)+" A");
                        SpannungHoldwertTV.setText(String.valueOf(GlobalVariable.SpannungHoldwert)+" V");
                    }

                    //---------------------------Display job number in textview-----------------------------
                    //JOB_DISPLAY.setText(String.valueOf(DatenObjekte.Jobnummer));
                    //JOB_DISPLAY.setTextColor(Color.WHITE);
                    //JOB_NUM.setText("JOB");
                    //JOB_NUM.setTextColor(Color.WHITE);

                    Button Korrektur = findViewById(R.id.btn_korrektur); //korrektur textview
                    Button strom = findViewById(R.id.btn_strom); //Drahtdurchmesser mm
                    voltage = findViewById(R.id.btn_voltage);

                    Korrektur.setText(String.valueOf(GlobalVariable.Lichtbogenkorrektur1));
                    //Log.i("Lichtbogenkorrektur1",String.valueOf(GlobalVariable.Lichtbogenkorrektur1));




                    if (GlobalVariable.SV1pos1 != 4) { // Not elektrode mode
                        voltage.setTextColor(Color.WHITE);
                        voltage.setText(String.valueOf((GlobalVariable.SpannungSetwert)/ 10 + "," + String.valueOf(GlobalVariable.StromSetwert % 10)));
                        Korrektur.setTextColor(Color.WHITE);
                        strom.setTextColor(Color.WHITE);
                    } else {
                        voltage.setTextColor(Color.BLACK);
                        voltage.setText(String.valueOf((GlobalVariable.ElektrodeStromSetwert))); // CanId 0186
                        Korrektur.setTextColor(Color.BLACK);
                        strom.setTextColor(Color.BLACK);
                    }

                    if (GlobalVariable.SV1pos1 != 1) { //Normal mode
                        strom.setText(String.valueOf(GlobalVariable.StromSetwert )); //StromSetwert A
                    } else {
                        strom.setText(""); //StromSetwert A
                    }
                }

                m_min.setOnClickListener((View v) -> {
                    new MainActivity_Controller().ChangeTextprogressBar(m_min, txtProgress, temp, check);
                    ChangeTextprogressBar(m_min);
                });

                current.setOnClickListener((View v) -> {
                    ChangeTextprogressBar(current);
                });

                long date = System.currentTimeMillis();
                SimpleDateFormat sdf = new SimpleDateFormat("h-mm-ss a");
                sdf.setTimeZone(TimeZone.getTimeZone("Germany/Berlin"));
                String dateString = sdf.format(date);
                String s = dateString;
                //getContentResolver().insert(InfoContract.infoEntry.CONTENT_URI,values); // this line crash suddenly

            if (UartService.HeaderFound == 1) { //found '$'
                headercounter++;
                if (headercounter > 10) { //count until 10 millisecond
                    UartService.HeaderFound = 0;
                    headercounter = 0;
                }
            }

            if (DatenObjekte.HFound == 1) {
                countFrame++;
                if (countFrame > 1000) {//&& (DatenObjekte.jobtoken != 1)) { //more than 1s and data is not complete
                    countFrame = 0;
                    DatenObjekte.HFound = 0;
                    DatenObjekte.jobtoken = 0;
                }
            }

            //--------------------------------------- Verfahren ------------------------------------------------------------------
            if (GlobalVariable.Verfahren_Token){
                if(GlobalVariable.SV1pos1 != GlobalVariable.VERFAHREN_MODE) sendEnergie.ChangeParameter(28, 5, 0);
                else GlobalVariable.Verfahren_Token = false;
            }

            if (counterDisplay == 50) { //update every 0.1s

                if(GlobalVariable.Lichtbogenkorrektur1>0){
                    progressBarPlus.setProgress(100*GlobalVariable.Lichtbogenkorrektur1/30);
                    progressBarMinus.setProgress(0);
                } else {
                    progressBarMinus.setProgress(abs(100*GlobalVariable.Lichtbogenkorrektur1/30));
                    progressBarPlus.setProgress(0);
                }

                if(GlobalVariable.JOB_PRESSED && GlobalVariable.ENCODER_PRESSED){
                    GlobalVariable.ENCODER_PRESSED = false;
                    dialog.show();
                }else if (GlobalVariable.JOB_PRESSED){
                    switch(GlobalVariable.Jobnummer){
                        case 1: //FAV1
                            MainActivity.JOB_NUM.setText("FAV1");
                            break;
                        case 2: //FAV2
                            MainActivity.JOB_NUM.setText("FAV2");
                            break;
                        case 3: //FAV3
                            MainActivity.JOB_NUM.setText("FAV3");
                            break;
                        case 4: //FAV4
                            MainActivity.JOB_NUM.setText("FAV4");
                            break;
                        default:
                            MainActivity.JOB_NUM.setText(String.valueOf(GlobalVariable.Jobnummer));
                            break;
                    }
                }
                String second=String.valueOf(GlobalVariable.SECOND);
                String minute=String.valueOf(GlobalVariable.MINUTE);
                String hour=String.valueOf(GlobalVariable.HOUR);

                if(GlobalVariable.HOUR<10)  hour="0"+String.valueOf(GlobalVariable.HOUR);
                if(GlobalVariable.MINUTE<10)  minute="0"+String.valueOf(GlobalVariable.MINUTE);
                if(GlobalVariable.SECOND<10)  second="0"+String.valueOf(GlobalVariable.SECOND);
                tdate.setText(hour+":"+minute+":"+second);

                String day=String.valueOf(GlobalVariable.DAY);
                String month=String.valueOf(GlobalVariable.MONTH);
                String year=String.valueOf(GlobalVariable.YEAR);

                if(GlobalVariable.DAY<10) day="0"+String.valueOf(GlobalVariable.DAY);
                if(GlobalVariable.MONTH<10) month="0"+String.valueOf(GlobalVariable.MONTH);
                if(GlobalVariable.YEAR<10) year="200"+String.valueOf(GlobalVariable.YEAR);
                tdate2.setText(day+"/"+month+"/"+year);

                //-------------------------------------- Betriebsart ---------------------------------------------------------------
                //Log.i("Betriebsart",GlobalVariable.Betriebsart_string[GlobalVariable.SV1pos2]);
                if (GlobalVariable.Betriebsart_Token){
                    //Log.i("Betriebsart_Token"," is true");
                    if(GlobalVariable.SV1pos2 != WeldingProcess.BETRIEBSART) sendEnergie.ChangeParameter(42,0,0);
                    else GlobalVariable.Betriebsart_Token = false;
                }

                if (GlobalVariable.CONTROL_PANEL_MODE == 1) {
                    if (DatenObjekte.SV1pos1 != 4) { //Not elektrode mode
                        switch (WeldingChangeParam.HOME_COUNTER) {
                            case 0:
                                if (GlobalVariable.mpm_display != GlobalVariable.Energie1)
                                    sendEnergie.ChangeParameter(2, GlobalVariable.mpm_display, 1); //m/min
                                else GlobalVariable.CONTROL_PANEL_MODE = 0;
                                break;
                            case 1:
                                if (GlobalVariable.mm_a_display != GlobalVariable.Drahtdurchmesser)
                                    sendEnergie.ChangeParameter(3, GlobalVariable.mm_a_display, 1); //Thickness mm
                                else GlobalVariable.CONTROL_PANEL_MODE = 0;
                                break;
                            case 2:
                                if (GlobalVariable.mm_a_display != GlobalVariable.StromSetwert)
                                    sendEnergie.ChangeParameter(1, GlobalVariable.mm_a_display, 1); //strom
                                break;
                            case 3: //Korrektur
                                if (GlobalVariable.korrektur_display != GlobalVariable.Lichtbogenkorrektur1)
                                    sendEnergie.ChangeParameter(21, GlobalVariable.korrektur_display, 1);
                                break;
                            case 4: //verfahren
                                break;
                            case 5:
                                if (GlobalVariable.gas_token) sendEnergie.ChangeParameter(10, 0, 1); //Gas
                                GlobalVariable.gas_token = false;
                                break;
                            case 6:
                                if (GlobalVariable.werkstoff_token) sendEnergie.ChangeParameter(10, 0, 1); //werkstoff
                                GlobalVariable.werkstoff_token = false;
                                break;
                            case 7:
                                if (GlobalVariable.Drahtdurchmesser_token) sendEnergie.ChangeParameter(41, 0, 0); //Drahtdurchmesser
                                GlobalVariable.Drahtdurchmesser_token = false;
                                break;
                            case 8:
                                if (GlobalVariable.voltage_display != GlobalVariable.Spannung1) sendEnergie.ChangeParameter(15, GlobalVariable.voltage_display, 1); //voltage
                                break;
                            case 9: //if (job_token) sendEnergie.ChangeParameter(5, 0, 1); //job
                                GlobalVariable.job_token = false;
                                break;
                        }
                    }
                } else if (GlobalVariable.CONTROL_PANEL_MODE == 0) {
                    DatenObjekte.mpm_display = DatenObjekte.Energie1;
                }
               counterDisplay = 0;
            }

            //---------------------------------- button thread -------------------------------------

            if (counterDisplay1 == 10) { //update every 10ms
                if (WeldingChangeParam.TEST_TOKEN) {
                    Log.i(TAG, "TEST_TOKEN is true.");
                    JOB_NUM.performClick();
                    WeldingChangeParam.TEST_TOKEN = false;
                }

                if ((WeldingChangeParam.HOME_TOKEN) && (WeldingChangeParam.MA_TOKEN)) {
                    Log.i(TAG, "HOME_TOKEN is true.");
                    backHome.performClick();
                    WeldingChangeParam.HOME_TOKEN = false;
                }

                if (WeldingChangeParam.DROSSEL_TOKEN) {
                    Log.i(TAG, "DROSSEL_TOKEN is true.");
                    droessel.performClick();
                    WeldingChangeParam.DROSSEL_TOKEN = false;
                }

                if (WeldingChangeParam.DATEN_TOKEN) {
                    Log.i(TAG, "DATEN_TOKEN is true.");
                    //data.performClick();
                    WeldingChangeParam.DATEN_TOKEN = false;
                }

                if (WeldingChangeParam.VERFAHREN_TOKEN) {
                    Log.i(TAG, "VERFAHREN_TOKEN is true.");
                    verfahren_btn.performClick();
                    WeldingChangeParam.VERFAHREN_TOKEN = false;
                }

                if (WeldingChangeParam.KENNLINIE_TOKEN) {
                    Log.i(TAG, "KENNLINIE_TOKEN is true.");
                    kennlinie.performClick();
                    WeldingChangeParam.KENNLINIE_TOKEN = false;
                }

                if (WeldingChangeParam.BETRIEBSART_TOKEN) {
                    Log.i(TAG, "BETRIEBSART_TOKEN is true.");
                    betriebsart.performClick();
                    WeldingChangeParam.BETRIEBSART_TOKEN = false;
                }

                if (WeldingChangeParam.MENU_TOKEN) {
                    Log.i(TAG, "MENU_TOKEN is true.");
                    Setting.performClick();
                    WeldingChangeParam.MENU_TOKEN = false;
                }
                counterDisplay1 = 0;
            }

            newHandler.postDelayed(TimerHandler, 1); //reads data from service every 1ms
            UartService.token = true;
        }
    };
    /**
     * Start the timer when Kennlinie Button is pressed
     */
    private Runnable KENN_TIMER = new Runnable() {
        @Override
        public void run() {
            testbtn_onclick1(); //request kennlinie response
            GlobalVariable.delayInMilli(500);
            sendKennToMachine();
            KENN_HANDLER.postDelayed(KENN_TIMER,3000);
        }
    };

    void allgemeinOnclick(View view ){
        View layout = findViewById(R.id.zweitelayout);
        View layout1 = findViewById(R.id.circleButton);
        layout.setVisibility(View.INVISIBLE);
        layout1.setVisibility(View.INVISIBLE);
        view.setVisibility(View.VISIBLE);
    }

    private void progrssinit(){
        progressBarMinus.setProgress(0);
        progressBarPlus.setProgress(0);
    }
    @Override
    public void onFragmentInteraction(Uri uri) {
    }

    public void onpressed_Minus_plus(View view ) {
        mainActivityController.minus_plus_interagieren(progressBarMinus,progressBarPlus,view);
    }

    private void init_view(){
        kenn_fragment = findViewById(R.id.fragment_test);
        betriebsart_fragement = findViewById(R.id.frgment_Betriebsart);
        plus = findViewById(R.id.plus);
        progressBarMinus = findViewById(R.id.progress);
        progressBarPlus = findViewById(R.id.progress1);
        WIG_btn = findViewById(R.id.WIG_btn);
        MMNormal_btn = findViewById(R.id.MMNormal_btn);
        MMSynergy_btn = findViewById(R.id.MMSynergy_btn);
        MMPuls_btn = findViewById(R.id.MMPuls_btn);
        ElectrodeMMA_btn = findViewById(R.id.ElectrodeMMA_btn);
        minus_button = findViewById(R.id.minus);
        current = findViewById(R.id.btn_voltage);
        m_min = findViewById(R.id.btn_strom);
        backHome = findViewById(R.id.backhome);
        betriebsart = findViewById(R.id.Betribsart);
        progressBar = findViewById(R.id.progressBar);
        circle_button = findViewById(R.id.button1);
        frame = findViewById(R.id.framelayout);
        ANZEIGE1 = findViewById(R.id.anzeige1);
        ANZEIGE2 = findViewById(R.id.anzeige2);
        ANZEIGE3 = findViewById(R.id.anzeige3);
        ANZEIGE4 = findViewById(R.id.anzeige4);
        txtprogress = findViewById(R.id.txtpro);
        JOB_NUM  = findViewById(R.id.job_btn);
        tdate = findViewById(R.id.date); //right top button
        tdate2 = findViewById(R.id.date2); //left top button
        Fav1_btn = findViewById(R.id.Button_fav1);
        Fav2_btn = findViewById(R.id.Button_fav2);
        Fav3_btn = findViewById(R.id.Button_fav3);
        Fav4_btn = findViewById(R.id.Button_fav4);
        StromHoldwertTV = findViewById(R.id.strom_hold);
        SpannungHoldwertTV = findViewById(R.id.spannung_hold);
        HoldIst = findViewById(R.id.hold_ist);
        hold_layout = findViewById(R.id.hold_layout);
        verfahren_btn = findViewById(R.id.button_menu); // Verfahren
    }

    private void setVisibility(){
        betriebsart_fragement.setVisibility(View.INVISIBLE);
        plus.setVisibility(View.INVISIBLE);
        current.setVisibility(View.VISIBLE);
        progressBarPlus.setVisibility(View.INVISIBLE);
        progressBarMinus.setVisibility(View.INVISIBLE);
        frame.setVisibility(View.INVISIBLE);
        circle_button.setVisibility(View.GONE);
        minus_button.setVisibility(View.GONE);
    }

    private  void ausblinden(View view1, View view2){
        view1.setVisibility(View.INVISIBLE);
        view2.setVisibility(View.INVISIBLE);
    }

    private void testbtn_onclick1(){
        //----------------------------Deactivate data parsing-----------
        TEMP_BA = GetKennlinieDaten.readKennDaten();
        StringBuilder tempsb = new StringBuilder();
        for(int i=0;i<23;i++){
            //TEMP_STRING = tempsb.append(String.format("%02x", (int) ((TEMP_BA[i]) & 0xFF)).toUpperCase()).toString();
            TEMP_STRING = tempsb.append((char)(TEMP_BA[i]&0xFF)).toString();
        }
        //Log.i("TEMP_STRING ",TEMP_STRING);
        UartService.WriteToSerial(TEMP_STRING);
        TEMP_STRING = "";
    }

    private void sendKennToMachine(){
        /**
         * This method is to send kennlinie_setting Grunddaten to the machine
         **/
        TMP_KENNFRAME = GetKennlinieDaten.UpdateKennlinie();
        //String[] x = new String[230];
        //StringBuilder sby = new StringBuilder(); //data in hex
        //String y = "";

        for(int i=0;i<222;i++){
            KENN_STRING = KENN_STRING + (char) (TMP_KENNFRAME[i] & 0xFF);
            //x[i] = String.format("%02x", (int) ((TMP_KENNFRAME[i]) & 0xFF)).toUpperCase(); //convert byte to hex value
            //y = sby.append(x[i]).toString(); //hex string
        }
        //System.out.println(y);
        UartService.WriteToSerial(KENN_STRING);
        KENN_STRING = "";
    }

    public void createAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        /*LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_layout,null);
        builder.setView(dialogView);
        final AlertDialog dialog = builder.create();
        // Display the custom alert dialog on interface
        dialog.show();*/
    }

    private void Drossel_view(){
        progressBarPlus.setVisibility(View.VISIBLE);
        progressBarMinus.setVisibility(View.VISIBLE);
        minus_button.setVisibility(View.VISIBLE);
        plus.setVisibility(View.VISIBLE);
        prograssHeid = true;
    }

    private void droessel_gone(){
        //-------------------------- make droessel invisible -------------------------------
        progressBarPlus.setVisibility(View.GONE);
        progressBarMinus.setVisibility(View.INVISIBLE);
        minus_button.setVisibility(View.GONE);
        plus.setVisibility(View.INVISIBLE);
    }

    private void hold_layout_gone(){
        hold_layout.setVisibility(View.INVISIBLE);
    }

    private void hold_layout_view(){
        hold_layout.setVisibility(View.VISIBLE);
    }
}