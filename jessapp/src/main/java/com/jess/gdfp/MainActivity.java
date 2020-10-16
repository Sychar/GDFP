package com.jess.gdfp;

import android.annotation.SuppressLint;
import android.app.admin.DevicePolicyManager;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.felhr.usbserial.UsbSerialDevice;
import com.jess.gdfp.Controller.MainActivity_Controller;
import com.jess.gdfp.View.BetriebsArt;
import com.jess.gdfp.View.BlankFragment;
import com.jess.gdfp.View.JobsUser;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.InvalidParameterException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import android_serialport_api.SerialPort;
import static com.jess.gdfp.UartService.mOutputStream;

import static com.jess.gdfp.View.Setting.JOBUSER_TOKEN;
import static com.jess.gdfp.View.Setting.KENN_TOKEN;

public class MainActivity extends AppCompatActivity implements BlankFragment.OnFragmentInteractionListener , BetriebsArt.OnFragmentInteractionListener{

    private final static String TAG = MainActivity.class.getSimpleName(); //name of this class

    static TextView txtProgress ;
    private ProgressBar progressBarPlus;
    private ProgressBar progressBarMinus;
    private static ProgressBar progressBar;
    private Handler handler = new Handler();
    private Handler KENN_HANDLER = new Handler();
    private Handler newHandler = new Handler();

    private Button m_min;
    private Button current;
    private Button strom;

    private Button droessel;
    private Button circle_button;
    private Button minus_button;
    private Button betribsart;
    private Button plus;
    private Button WIG_btn;
    private Button MMNormal_btn;
    private Button MMSynergy_btn;
    private Button MMPuls_btn;
    private Button ElectrodeMMA_btn;
    private Button WIGPulsen_btn;
    private Button WIGSpeed_btn;
    private Button WIGSpeedPulsen_btn;
    private ImageButton backHome;
    private int len;
    private TextView ANZEIGE1;
    private TextView ANZEIGE2;
    private TextView ANZEIGE3;
    private TextView tdate;
    private TextView tdate2;
    private static TextView txtprogress;
    private ImageButton Setting;
    private View frame;
    private View kenn_fragment;
    private View betriebsart_fragement;
    private Button kennlinie;
    private Button JOB_NUM;

    public static boolean JOB_TOKEN = false;
    public static boolean STOP_DATENOBJEKTE = false;
    public static boolean PARSE_TOKEN = true;
    private Button button_menu;

    public static int headercounter=0;
    private static int counterDisplay = 0;
    private static int counterDisplay1 = 0;
    private static int counterDisplay2 = 0;

    private static byte[] TEMP_BA = new byte[40];
    private static String TEMP_STRING = "";
    private static String KENN_STRING = "";
    public static String msg_for_me = "";
    public static String msg_for_can = "";
    public static String msg_for_can1 = "";
    private static int countFrame = 0;

    static boolean kennlinie_gedrückt = false;
    static boolean job_gedrückt = false;

    private static byte[] TMP_KENNFRAME = new byte[430];
    public DatenObjekteSend sendEnergie = new DatenObjekteSend();

    private static Charset iso88591charset = Charset.forName("ISO-8859-1");

    private static Boolean prograssHeid = true;
    private static  boolean check = true;
    private  static  String temp ="Energie";

    private UsbSerialDevice serial;

    private UartService.MyHandler mHandler;
    private static UartService uartService;

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

        READVAL_STATUS[1]=0;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG,"oncreate()");

        init_view();
        progrssinit();
        setVisibility();
        serial_init();

        mHandler = new UartService.MyHandler();
        Setting= findViewById(R.id.setting);
        Setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view ) {
                GlobalVariable.SETTING_TOKEN = true;
                mainActivityController.onClick_newActivity(com.jess.gdfp.View.Setting.class);
                GlobalVariable.MA_TOKEN = false;
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

        button_menu = findViewById(R.id.button_menu); // Verfahren
        button_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v=findViewById(R.id.framelayout);
                allgemeinOnclick(v);
                ausblinden(kenn_fragment,betriebsart_fragement);
            }
        });

        final ProgressBar progress = findViewById(R.id.progress);
        progress.setVisibility(View.GONE);

        betribsart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allgemeinOnclick(betriebsart_fragement);
                ausblinden(frame,kenn_fragment);
            }
        });

        //-------------------------------- Verfahren button ----------------------------------------
        WIG_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlobalVariable.VERFAHREN_MODE = 1;
                if(!GlobalVariable.verfahren_gedrückt) {
                    WIG_btn.setTextColor(Color.GREEN);
                    GlobalVariable.verfahren_gedrückt=true;
                }else if(GlobalVariable.verfahren_gedrückt){
                    WIG_btn.setTextColor(Color.WHITE);
                    GlobalVariable.verfahren_gedrückt=false;
                }
            }
        });

        MMNormal_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MMNormal_btn.setTextColor(Color.GREEN);
                MMSynergy_btn.setTextColor(Color.WHITE);
                MMPuls_btn.setTextColor(Color.WHITE);
                ElectrodeMMA_btn.setTextColor(Color.WHITE);
                GlobalVariable.MMNormal_btn_onclick();
                if(!GlobalVariable.verfahren_gedrückt) {
                    MMNormal_btn.setTextColor(Color.GREEN);
                    GlobalVariable.verfahren_gedrückt=true;
                }else if(GlobalVariable.verfahren_gedrückt){
                    MMNormal_btn.setTextColor(Color.WHITE);
                    GlobalVariable.verfahren_gedrückt=false;
                }
            }
        });

        MMSynergy_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MMNormal_btn.setTextColor(Color.WHITE);
                MMSynergy_btn.setTextColor(Color.GREEN);
                MMPuls_btn.setTextColor(Color.WHITE);
                ElectrodeMMA_btn.setTextColor(Color.WHITE);
                GlobalVariable.MMSynergy_btn_onclick();
            }
        });

        MMPuls_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MMNormal_btn.setTextColor(Color.WHITE);
                MMSynergy_btn.setTextColor(Color.WHITE);
                MMPuls_btn.setTextColor(Color.GREEN);
                ElectrodeMMA_btn.setTextColor(Color.WHITE);
                GlobalVariable.MMPuls_btn_onclick();
            }
        });

        ElectrodeMMA_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MMNormal_btn.setTextColor(Color.WHITE);
                MMSynergy_btn.setTextColor(Color.WHITE);
                MMPuls_btn.setTextColor(Color.WHITE);
                ElectrodeMMA_btn.setTextColor(Color.GREEN);
                GlobalVariable.ElektrodeMMA_btn_onclick();
            }
        });

        WIGPulsen_btn.setOnClickListener(new View.OnClickListener() {
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
        });

        WIGSpeed_btn.setOnClickListener(new View.OnClickListener() {
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
        });

        WIGSpeedPulsen_btn.setOnClickListener(new View.OnClickListener() {
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
        });

        kennlinie = findViewById(R.id.kenn);
        kennlinie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view=findViewById(R.id.fragment_test);
                allgemeinOnclick(view);
                ausblinden(frame,betriebsart_fragement);
                if (!kennlinie_gedrückt) {
                    KENN_HANDLER.post(KENN_TIMER); //start the timer handler
                    kennlinie.setBackgroundColor(Color.GRAY);
                    kennlinie_gedrückt = true;
                    STOP_DATENOBJEKTE = true;
                } else {
                    PARSE_TOKEN = true;
                    kennlinie.setBackgroundColor(Color.BLACK);
                    kennlinie_gedrückt = false;
                    STOP_DATENOBJEKTE = false;
                    KENN_HANDLER.removeCallbacks(KENN_TIMER); //stop the timer handler
                    backHome.performClick();
                }
            }
        });

        droessel = findViewById(R.id.drossel);
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
                    progressBarPlus.setVisibility(View.VISIBLE);
                    progressBarMinus.setVisibility(View.VISIBLE);
                    minus_button.setVisibility(View.VISIBLE);
                    plus.setVisibility(View.VISIBLE);
                    prograssHeid = true;
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
        });

        JOB_NUM.setOnClickListener(view12 -> {
            if (!job_gedrückt) {
                //GlobalVariable.JOB_NUM_TOKEN = true;
                JOB_NUM.setTextColor(Color.BLACK);
                JOB_NUM.setBackgroundColor(Color.GRAY);
                //JOB_DISPLAY.setTextColor(Color.BLACK);
                //JOB_DISPLAY.setBackgroundColor(Color.GRAY);

                //---------------------------- Activate Job ----------------------------------------
                //DatenObjekteSend activateJob = new DatenObjekteSend();
                //activateJob.ChangeParameter(5, 0, 1);
                job_gedrückt = true;
                //---------------------------- Store Job -------------------------------------------
                //DatenObjekteSend storeJob = new DatenObjekteSend();
                //storeJob.ChangeParameter(37, 0, 1);

                //---------------------------- Request Job -----------------------------------------
                //String FIRSTSTR = UartService.getJob(); //ascii string
                //String SECSTR = UartService.getJob1(1); //ascii string

            } else {
                //GlobalVariable.JOB_NUM_TOKEN = false;
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
        }else if (id == R.id.btn_mm) {
            TextView textView = findViewById(R.id.Mm);
            String s = textView.getText().toString();
            String s1 = btn.getText().toString();
            String s2 = txtProgress.getText().toString();
            textView.setText(temp);
            txtProgress.setText(s1);
            btn.setText(s2);
            temp = s;
            check=false;
        }else if (id == R.id.btn_current) {
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
            counterDisplay2++;

            switch(GlobalVariable.HOME_COUNTER) {
                case 0:
                    if ((DatenObjekte.SV1pos1 == 1) && (DatenObjekte.mpm_display < 240)) { //Normal
                        progressBar.setProgress((int) (GlobalVariable.mm_a_display) * (100 / 232) - (800 / 232));
                    } else if ((DatenObjekte.SV1pos1 == 2) && (DatenObjekte.mpm_display < 120)) { //Synergie
                        progressBar.setProgress((int) ((GlobalVariable.mm_a_display) * 100 / 80 - 50));
                    } else if ((DatenObjekte.SV1pos1 == 3) && (DatenObjekte.mpm_display < 120)) {//Pulse
                        progressBar.setProgress(GlobalVariable.mm_a_display - 20);
                    }
                    break;
            }

            if (READVAL_STATUS[1] == 1) {
                switch (GlobalVariable.HOME_COUNTER) {
                    case 0:
                        if(GlobalVariable.VERFAHREN_VAL!=4) txtprogress.setText(String.valueOf(GlobalVariable.mm_a_display / 10) + "," + String.valueOf(GlobalVariable.mm_a_display % 10) + "\n" + "m/min"); // m/min
                        else {
                            GlobalVariable.HOME_COUNTER = 2;
                        }
                        break;
                    case 1:
                        txtprogress.setText(String.valueOf(DatenObjekte.SV1pos3 / 10) + "," + String.valueOf(DatenObjekte.SV1pos3 % 10) + "\n" + "mm"); //mm
                        break;
                    case 2:
                        txtprogress.setText(String.valueOf(DatenObjekte.StromSetwert) + "\n" + " A"); //A
                        break;
                    case 3:
                        txtprogress.setText(String.valueOf(DatenObjekte.Lichtbogenkorrektur1) + "\n" + "Korrektur"); //Korrektur
                        break;
                    case 4:
                        txtprogress.setText(DatenObjekte.Verfahren); //Verfahren
                        break;
                    case 5:
                        txtprogress.setText(DatenObjekte.Gas); //Gas
                        break;
                    case 6:
                        txtprogress.setText(DatenObjekte.Werkstoff + "\n" + "Werkstoff"); //Werkstoff
                        break;
                    case 7:
                        txtprogress.setText(String.valueOf(DatenObjekte.Jobnummer) + "\n" + "Job"); //Job
                        break;
                }
            }
                if (check) {
                    //if(DatenObjekte.SV1pos1 != 4) txtProgress.setText(String.valueOf(DatenObjekte.Energie1/10) + "," + String.valueOf(DatenObjekte.Energie1%10)+"\n"+"m/min"); // m/min
                    // if(DatenObjekte.SV1pos1 != 4) txtProgress.setText(String.valueOf(DatenObjekte.mpm_display/10) + "," + String.valueOf(DatenObjekte.mpm_display%10)+"\n"+"m/min"); // m/min
                    //else txtProgress.setText(String.valueOf(DatenObjekte.ElektrodeStromSetwert+" A")); // Elektrode mode
                    ANZEIGE1.setText(DatenObjekte.Verfahren);
                    ANZEIGE2.setText(DatenObjekte.Werkstoff);
                    ANZEIGE3.setText(DatenObjekte.Gas);
                    //---------------------------Display job number in textview-----------------------------
                    //JOB_DISPLAY.setText(String.valueOf(DatenObjekte.Jobnummer));
                    //JOB_DISPLAY.setTextColor(Color.WHITE);
                    JOB_NUM.setText("JOB");
                    JOB_NUM.setTextColor(Color.WHITE);

                    Button Korrektur = findViewById(R.id.btn_korrektur); //korrektur textview
                    Button m_min = findViewById(R.id.btn_mm); //Drahtdurchmesser mm
                    strom = findViewById(R.id.btn_current);

                    Korrektur.setText(String.valueOf(DatenObjekte.Lichtbogenkorrektur1));
                    m_min.setText(String.valueOf(DatenObjekte.SV1pos3 / 10 + "," + String.valueOf(DatenObjekte.SV1pos3 % 10))); //Drahtdurchmesser (mm)

                    if (DatenObjekte.SV1pos1 != 4) { // Not elektrode mode
                        strom.setTextColor(Color.WHITE);
                        strom.setText(String.valueOf((DatenObjekte.StromSetwert))); // CanId 0482
                        Korrektur.setTextColor(Color.WHITE);
                        m_min.setTextColor(Color.WHITE);
                    } else {
                        strom.setTextColor(Color.BLACK);
                        strom.setText(String.valueOf((DatenObjekte.ElektrodeStromSetwert))); // CanId 0186
                        Korrektur.setTextColor(Color.BLACK);
                        m_min.setTextColor(Color.BLACK);
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
                len = DatenObjekte.LengthProtocol2;
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

            //---------------------------------- date thread ---------------------------------------
            //if (counterDisplay2 == 1) { //update every 1ms

            //tdate.setText(String.valueOf(DatenObjekte.HOUR) + ":" + String.valueOf(DatenObjekte.MINUTE) + ":" + String.valueOf(DatenObjekte.SECOND));
            //tdate2.setText(String.valueOf(DatenObjekte.DAY) + "/" + String.valueOf(DatenObjekte.MONTH) + "/" + "20" + String.valueOf(DatenObjekte.YEAR));
            String second=String.valueOf(DatenObjekte.SECOND);
            String minute=String.valueOf(DatenObjekte.MINUTE);
            String hour=String.valueOf(DatenObjekte.HOUR);

            if(DatenObjekte.HOUR<10)  hour="0"+String.valueOf(DatenObjekte.HOUR);
            if(DatenObjekte.MINUTE<10)  minute="0"+String.valueOf(DatenObjekte.MINUTE);
            if(DatenObjekte.SECOND<10)  second="0"+String.valueOf(DatenObjekte.SECOND);
            tdate.setText(hour+":"+minute+":"+second);

            String day=String.valueOf(DatenObjekte.DAY);
            String month=String.valueOf(DatenObjekte.MONTH);
            String year=String.valueOf(DatenObjekte.YEAR);

            if(DatenObjekte.DAY<10) day="0"+String.valueOf(DatenObjekte.DAY);
            if(DatenObjekte.MONTH<10) month="0"+String.valueOf(DatenObjekte.MONTH);
            if(DatenObjekte.YEAR<10) year="200"+String.valueOf(DatenObjekte.YEAR);
            tdate2.setText(day+"/"+month+"/"+year);
            //counterDisplay2 = 0;
            //}

            if (counterDisplay == 100) { //update every 0.1s
                if (GlobalVariable.CONTROL_PANEL_MODE == 1) {
                    if (DatenObjekte.SV1pos1 != 4) { //Not elektrode mode
                        switch (GlobalVariable.HOME_COUNTER) {
                            case 0:
                                if (GlobalVariable.mm_a_display != DatenObjekte.Energie1)
                                    sendEnergie.ChangeParameter(2, GlobalVariable.mm_a_display, 1); //m/min
                                else GlobalVariable.CONTROL_PANEL_MODE = 0;
                                break;
                            case 1:
                                if (GlobalVariable.mm_a_display != DatenObjekte.SV1pos3)
                                    sendEnergie.ChangeParameter(3, GlobalVariable.mm_a_display, 1); //mm
                                else GlobalVariable.CONTROL_PANEL_MODE = 0;
                                break;
                            case 2:
                                if (GlobalVariable.mm_a_display != DatenObjekte.StromSetwert)
                                    sendEnergie.ChangeParameter(1, GlobalVariable.mm_a_display, 1); //strom
                                break;
                            case 3: //Korrektur
                                break;
                            case 4: //verfahren
                                break;
                            case 5:
                                if (GlobalVariable.gas_token) sendEnergie.ChangeParameter(10, 0, 1); //Gas
                                GlobalVariable.gas_token = false;
                                break;
                            case 6:
                                if (GlobalVariable.werkstoff_token)
                                    sendEnergie.ChangeParameter(10, 0, 1); //werkstoff
                                GlobalVariable.werkstoff_token = false;
                                break;
                            case 7: //if (job_token) sendEnergie.ChangeParameter(5, 0, 1); //job
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
                if (GlobalVariable.TEST_TOKEN) {
                    Log.i(TAG, "TEST_TOKEN is true.");
                    JOB_NUM.performClick();
                    GlobalVariable.TEST_TOKEN = false;
                }

                if ((GlobalVariable.HOME_TOKEN) && (GlobalVariable.MA_TOKEN)) {
                    Log.i(TAG, "HOME_TOKEN is true.");
                    backHome.performClick();
                    GlobalVariable.HOME_TOKEN = false;
                }

                if (GlobalVariable.DROSSEL_TOKEN) {
                    Log.i(TAG, "DROSSEL_TOKEN is true.");
                    droessel.performClick();
                    GlobalVariable.DROSSEL_TOKEN = false;
                }

                if (GlobalVariable.DATEN_TOKEN) {
                    Log.i(TAG, "DATEN_TOKEN is true.");
                    //data.performClick();
                    GlobalVariable.DATEN_TOKEN = false;
                }

                if (GlobalVariable.VERFAHREN_TOKEN) {
                    Log.i(TAG, "VERFAHREN_TOKEN is true.");
                    button_menu.performClick();
                    GlobalVariable.VERFAHREN_TOKEN = false;
                }

                if (GlobalVariable.KENNLINIE_TOKEN) {
                    Log.i(TAG, "KENNLINIE_TOKEN is true.");
                    kennlinie.performClick();
                    GlobalVariable.KENNLINIE_TOKEN = false;
                }

                if (GlobalVariable.BETRIEBSART_TOKEN) {
                    Log.i(TAG, "BETRIEBSART_TOKEN is true.");
                    betribsart.performClick();
                    GlobalVariable.BETRIEBSART_TOKEN = false;
                }

                if (GlobalVariable.MENU_TOKEN) {
                    Log.i(TAG, "MENU_TOKEN is true.");
                    Setting.performClick();
                    GlobalVariable.MENU_TOKEN = false;
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

    private  void  progrssinit(){
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
        WIGPulsen_btn = findViewById(R.id.WIGPulsen_btn);
        WIGSpeed_btn = findViewById(R.id.WIGSpeed_btn);
        WIGSpeedPulsen_btn = findViewById(R.id.WIGSpeedPulsen_btn);
        minus_button = findViewById(R.id.minus);
        current = findViewById(R.id.btn_current);
        m_min = findViewById(R.id.btn_mm);
        backHome = findViewById(R.id.backhome);
        betribsart = findViewById(R.id.Betribsart);
        progressBar = findViewById(R.id.progressBar);
        circle_button = findViewById(R.id.button1);
        frame = findViewById(R.id.framelayout);
        ANZEIGE1 = findViewById(R.id.anzeige1);
        ANZEIGE2 = findViewById(R.id.anzeige2);
        ANZEIGE3 = findViewById(R.id.anzeige3);
        txtprogress = findViewById(R.id.txtpro);
        JOB_NUM  = findViewById(R.id.job_btn);
        tdate = findViewById(R.id.date); //right top button
        tdate2 = findViewById(R.id.date2); //left top button
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
}