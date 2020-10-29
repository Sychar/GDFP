package com.jess.gdfp;

import android.app.AlarmManager;
import android.app.AlertDialog;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jess.gdfp.Controller.MainActivity_Controller;
import com.jess.gdfp.View.BetriebsArt;
import com.jess.gdfp.View.BlankFragment;
import com.jess.gdfp.View.DatalistView;
import com.jess.gdfp.View.JobsDetails;
import com.jess.gdfp.View.JobsUser;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.InvalidParameterException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
    private Button Mmin;
    private Button Voltage;
    private Button Korrektur;
    private Button circle_button;
    private Button minus_button;
    private Button Setting;
    private Button Menu;
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
    private Button home;
    private int len;
    private TextView ANZEIGE1;
    private TextView ANZEIGE2;
    private TextView ANZEIGE3;
    private TextView ANZEIGE4;
    private TextView ANZEIGE5;
    private TextView tdate;
    private TextView tdate2;
    private TextView DROSSEL;
    private Button button_yes;
    private Button button_no;
    private static TextView txtprogress;
    private static TextView unitprogress;
    private View frame;
    private View kenn_fragment;
    private View betriebsart_fragement;
    private Button Geberge;
    public static Button JOB_NUM;
    private static String dateString = "";
    private static String dateString2 = "";

    public static boolean JOB_TOKEN = false;
    public static boolean STOP_DATENOBJEKTE = false;
    public static boolean PARSE_TOKEN = true;
    public static boolean kenn_token = false;
    private Button Methode_btn;

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
    private static  boolean CHECK = true;
    private  static  String temp ="Energie";

    private UartService.MyHandler mHandler;

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

        //AlarmManager alarm = (AlarmManager) getSystemService(ALARM_SERVICE);
        //alarm.setTime(1330082817);
        //boolean correct = SystemClock.setCurrentTimeMillis(500);
        //((Activity)context).startActivity(intent);
        /*Intent intent1 = new Intent(android.provider.Settings.ACTION_DATE_SETTINGS);
        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplicationContext().startActivity(intent1);*/
        //Calendar cal = Calendar.getInstance();
        //System.out.println("Current time is : " + cal.getTimeInMillis());
        //SystemClock.setCurrentTimeMillis(1603887328868);

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

        circle_button.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                shortclick();
                return false;
            }
        });
        View view = findViewById(R.id.fragment_test);
        view.setVisibility(View.INVISIBLE);

        Methode_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BlankFragment.tv.setText("VERFAHREN");
                BlankFragment.detail = BlankFragment.init_verfahren();
                ArrayAdapter<String> adapter2= new ArrayAdapter<String>(getApplicationContext(),R.layout.item_for_kennlinie,R.id.textBetriebsart,BlankFragment.detail){
                    @NonNull
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                        View view1 =super.getView(position,convertView,parent);
                        return  view1;
                    }
                };
                BlankFragment.rv.setAdapter(adapter2);
                droessel_gone();
                hold_layout_gone();
                view = findViewById(R.id.fragment_test);
                allgemeinOnclick(view);
            }
        });

        Geberge = findViewById(R.id.geberge);
        Geberge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetDefautlTime();
                /*BlankFragment.tv.setText("DRAHTDURCHMESSER");
                BlankFragment.detail = BlankFragment.init_durchmesser();
                ArrayAdapter<String> adapter2= new ArrayAdapter<String>(getApplicationContext(),R.layout.item_for_kennlinie,R.id.textBetriebsart,BlankFragment.detail){
                    @NonNull
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                        View view1 =super.getView(position,convertView,parent);
                        return  view1;
                    }
                };
                BlankFragment.rv.setAdapter(adapter2);
                droessel_gone();
                hold_layout_gone();
                view=findViewById(R.id.fragment_test);
                allgemeinOnclick(view);*/
            }
        });

        final ProgressBar progress = findViewById(R.id.progress);
        progress.setVisibility(View.GONE);
        Setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BlankFragment.setButtonInvisible();
                BlankFragment.tv.setText("SETTING");
                BlankFragment.detail = BlankFragment.init_Setting();
                ArrayAdapter<String> adapter2= new ArrayAdapter<String>(getApplicationContext(),R.layout.item_for_kennlinie,R.id.textBetriebsart,BlankFragment.detail){
                    @NonNull
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                        View view1 =super.getView(position,convertView,parent);
                        return  view1;
                    }
                };
                BlankFragment.rv.setAdapter(adapter2);
                droessel_gone();
                hold_layout_gone();
                view=findViewById(R.id.fragment_test);
                allgemeinOnclick(view);
            }
        });

        Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view ) {
                BlankFragment.setButtonInvisible();
                BlankFragment.tv.setText("MENU");
                BlankFragment.detail = BlankFragment.init_Menu();
                ArrayAdapter<String> adapter2= new ArrayAdapter<String>(getApplicationContext(),R.layout.item_for_kennlinie,R.id.textBetriebsart,BlankFragment.detail){
                    @NonNull
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                        View view1 = super.getView(position,convertView,parent);
                        return  view1;
                    }
                };
                BlankFragment.rv.setAdapter(adapter2);
                droessel_gone();
                hold_layout_gone();
                view = findViewById(R.id.fragment_test);
                allgemeinOnclick(view);

                //--------------------- For encoder -------------------------------------------
                /*GlobalVariable.SETTING_TOKEN = true;
                mainActivityController.onClick_newActivity(com.jess.gdfp.View.Menu.class);
                WeldingChangeParam.MA_TOKEN = false;*/
            }
        });
        Drossel_view();

        home.setOnClickListener(view1 -> {
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

        plus.setOnClickListener(view1 -> {
            GlobalVariable.Drossel_token = true;
            int drossel_val = GlobalVariable.KorrekturDrossel;
            drossel_val++;
            Log.i("drossel_val",String.valueOf(drossel_val));
            DatenObjekteSend test = new DatenObjekteSend();
            test.ChangeParameter(43,drossel_val,1);
        });

        minus_button.setOnClickListener(view1 -> {
            GlobalVariable.Drossel_token = true;
            int drossel_val = GlobalVariable.KorrekturDrossel;
            drossel_val--;
            Log.i("drossel_val",String.valueOf(drossel_val));
            DatenObjekteSend test = new DatenObjekteSend();
            test.ChangeParameter(43,drossel_val,1);
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
        Fav1_btn.setTextSize(25);

        Fav2_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!fav2_gedrückt) {
                    //----------------------- get the job ------------------------------------------
                    fav2_gedrückt = true;
                } else fav2_gedrückt = false;
            }
        });
        Fav2_btn.setTextSize(25);

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
        Fav3_btn.setTextSize(25);

        Fav4_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!fav4_gedrückt) {
                    //----------------------- get the job ------------------------------------------
                    fav4_gedrückt = true;
                } else fav4_gedrückt = false;
            }
        });
        Fav4_btn.setTextSize(25);

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
            CHECK =false;
        }else if (id == R.id.btn_mmin) {
            TextView textView = findViewById(R.id.mmin);
            String s = textView.getText().toString();
            String s1 = btn.getText().toString();
            String s2 = txtProgress.getText().toString();
            textView.setText(temp);
            txtProgress.setText(s1);
            btn.setText(s2);
            temp = s;
            CHECK =false;
        }else if (id == R.id.btn_voltage) {
            TextView textView = findViewById(R.id.strom);
            String s= textView.getText().toString();
            String s1 =btn.getText().toString();
            String s2= txtProgress.getText().toString();
            textView.setText(temp);
            txtProgress.setText(s1);
            btn.setText(s2);
            temp=s;
            CHECK =false;
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
                            txtprogress.setText(String.valueOf(GlobalVariable.mpm_display / 10) + "." + String.valueOf(GlobalVariable.mpm_display % 10)); // m/min
                            unitprogress.setText("m/min");
                        } else WeldingChangeParam.HOME_COUNTER = 2;
                        break;
                    case 1:
                        txtprogress.setText(String.valueOf(GlobalVariable.BlechdickeSetwert / 10) + "." + String.valueOf(GlobalVariable.BlechdickeSetwert % 10)); //mm
                        unitprogress.setText("mm");
                        break;
                    case 2:
                        txtprogress.setText(String.valueOf(GlobalVariable.StromSetwert)); //A
                        unitprogress.setText("A");
                        break;
                    case 3:
                        txtprogress.setText(String.valueOf(GlobalVariable.Lichtbogenkorrektur1)); //Korrektur
                        unitprogress.setText("%");
                        break;
                    case 4:
                        txtprogress.setText(String.valueOf(GlobalVariable.SpannungSetwert / 10) + "." + String.valueOf(GlobalVariable.SpannungSetwert % 10)); //Voltage
                        txtprogress.setText(String.valueOf(GlobalVariable.SpannungSetwert / 10) + "." + String.valueOf(GlobalVariable.SpannungSetwert % 10)); //Voltage
                        unitprogress.setText("V");
                        break;
                    case 5:
                        if (GlobalVariable.encoder) {
                            txtprogress.setText(String.valueOf(GlobalVariable.Jobnummer)); //Job
                            unitprogress.setText("Job");
                        } else {
                            txtprogress.setText("Job");
                            unitprogress.setText("");
                        }
                        if (GlobalVariable.JOBCOUNT==0) txtprogress.setText("Job");
                        break;
                }
            }
                if (CHECK) {
                    if (!GlobalVariable.Verfahren_Token) ANZEIGE1.setText(GlobalVariable.Verfahren_String[GlobalVariable.SV1pos1]); // Verfahren
                    ANZEIGE2.setText(GlobalVariable.Werksotff_String[GlobalVariable.SV1pos5]); // Werkstoff
                    ANZEIGE3.setText(GlobalVariable.Draht_String[GlobalVariable.Drahtdurchmesser]); // Drahtdurchmesser
                    if (!GlobalVariable.Betriebsart_Token) ANZEIGE4.setText(GlobalVariable.Betriebsart_String[GlobalVariable.SV1pos2]); // Betriebsart
                    ANZEIGE5.setText(GlobalVariable.Gas_String[GlobalVariable.SV1pos4]); // Gas

                    //------------------------------------------------ Brennertasten ---------------------------------------------------------------------------------------
                    if(GlobalVariable.Brennertasten1_string.equals("Brennertaste1 Active")){
                        HoldIst.setText("Ist");
                        StromHoldwertTV.setText(String.valueOf(GlobalVariable.StromIstwert)+" A");
                        SpannungHoldwertTV.setText(String.valueOf(GlobalVariable.SpannungIstwert)+" V");
                    } else {
                        HoldIst.setText("Hold");
                        //StromHoldwertTV.setText(String.valueOf(GlobalVariable.StromHoldwert)+" A");
                        //SpannungHoldwertTV.setText(String.valueOf(GlobalVariable.SpannungHoldwert)+" V");
                        StromHoldwertTV.setTextSize(70);
                        SpannungHoldwertTV.setTextSize(70);
                        StromHoldwertTV.setText(String.valueOf(350)+" A");
                        SpannungHoldwertTV.setText(String.valueOf(35)+",9"+" V");
                    }

                    //------------------------------------------------------- Display variables in textview -----------------------------------------------------------------
                    //Log.i("Lichtbogenkorrektur1",String.valueOf(GlobalVariable.Lichtbogenkorrektur1));

                    if (GlobalVariable.KorrekturDrossel >= 0) DROSSEL.setText("DROSSEL   "+"[ "+ String.valueOf(GlobalVariable.KorrekturDrossel)+" ]");
                    else DROSSEL.setText("DROSSEL  "+"[ -"+ String.valueOf(GlobalVariable.KorrekturDrossel)+" ]");

                    if (GlobalVariable.SV1pos1 != 4) { // Not elektrode mode
                        Korrektur.setTextColor(Color.WHITE);
                        Mmin.setText(String.valueOf((GlobalVariable.Energie1)/ 10 + "." + String.valueOf(GlobalVariable.Energie1 % 10)));
                        Mmin.setTextColor(Color.WHITE);
                    } else {
                        Voltage.setTextColor(Color.BLACK);
                        Korrektur.setTextColor(Color.BLACK);
                        Mmin.setText("");
                        Mmin.setTextColor(Color.BLACK);
                    }

                    if (GlobalVariable.SV1pos1 == 1) { // Normal mode
                        Korrektur.setText(String.valueOf((GlobalVariable.Drossel1)));
                        Voltage.setTextColor(Color.WHITE);
                        Voltage.setText(String.valueOf((GlobalVariable.SpannungSetwert)/ 10 + "." + String.valueOf(GlobalVariable.SpannungSetwert % 10)));
                    } else {
                        Korrektur.setText(String.valueOf((GlobalVariable.Lichtbogenkorrektur1)));
                        Voltage.setTextColor(Color.BLACK);
                        Voltage.setText("");
                    }
                }

                Mmin.setOnClickListener((View v) -> {
                    new MainActivity_Controller().ChangeTextprogressBar(Mmin, txtProgress, temp, CHECK);
                    ChangeTextprogressBar(Mmin);
                });

                Voltage.setOnClickListener((View v) -> {
                    ChangeTextprogressBar(Voltage);
                });

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
                if (GlobalVariable.SV1pos1 != GlobalVariable.VERFAHREN_MODE) sendEnergie.ChangeParameter(28, 5, 0);
                else GlobalVariable.Verfahren_Token = false;
            }

            //------------------------------------------------ Gas ------------------------------------------------------------------
            if (GlobalVariable.Gas_Token){
                if (GlobalVariable.SV1pos4 != GlobalVariable.GAS_MODE) sendEnergie.ChangeParameter(10, 0, 1);
                else GlobalVariable.Gas_Token = false;
            }

            //------------------------------------------------ Betriebsart ----------------------------------------------------------
            if (GlobalVariable.Betriebsart_Token){
                if (GlobalVariable.SV1pos2 != GlobalVariable.BETRIEBSART_MODE) sendEnergie.ChangeParameter(42,0,0);
                else GlobalVariable.Betriebsart_Token = false;
            }

            //------------------------------------------------ Menu ------------------------------------------------------
            if (GlobalVariable.Menu_Token){
                if (GlobalVariable.MENU_MODE == 0) onClickJobUser(); //Jobs
                else if (GlobalVariable.MENU_MODE == 1) onClickDatenlogger(); //Datenlogger
                else if (GlobalVariable.MENU_MODE == 2) onClickKennlinie(); //Kennlinie
                else if (GlobalVariable.MENU_MODE == 3) onClickKennlinie(); //Guide
                GlobalVariable.Menu_Token = false;
            }

            //------------------------------------------------------ Drahtdurchmesser ------------------------------------------------------------
            if (GlobalVariable.Drahtdurchmesser_token){
                if (GlobalVariable.Drahtdurchmesser != GlobalVariable.DRAHTDURCHMESSER_MODE) sendEnergie.ChangeParameter(41, 0, 0);
                else GlobalVariable.Drahtdurchmesser_token = false;
            }

            //------------------------------------------------------ Drossel ------------------------------------------------------------
            /*if (GlobalVariable.Drossel_token){
                if (GlobalVariable.KorrekturDrossel != GlobalVariable.DRAHTDURCHMESSER_MODE) sendEnergie.ChangeParameter(41, 0, 0);
                else GlobalVariable.Drossel_token = false;
            }*/

            if(GlobalVariable.KorrekturDrossel > 0){
                progressBarPlus.setProgress(100*GlobalVariable.KorrekturDrossel/30);
                progressBarMinus.setProgress(0);
            } else {
                progressBarMinus.setProgress(abs(100*GlobalVariable.KorrekturDrossel/30));
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

            if (counterDisplay == 25) { //update every 0.02s
                long date = System.currentTimeMillis();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm ss");
                dateString = sdf.format(date);
                SimpleDateFormat sdf2 = new SimpleDateFormat("MMM dd yyyy");
                dateString2 = sdf2.format(date);
                tdate.setText(dateString);
                tdate2.setText(dateString2);

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
                                //if (GlobalVariable.gas_token) sendEnergie.ChangeParameter(10, 0, 1); //Gas
                                //GlobalVariable.gas_token = false;
                                break;
                            case 6:
                                if (GlobalVariable.werkstoff_token) sendEnergie.ChangeParameter(10, 0, 1); //Werkstoff
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
                    GlobalVariable.mpm_display = GlobalVariable.Energie1;
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
                    //Gas.performClick();
                    WeldingChangeParam.HOME_TOKEN = false;
                }

                if (WeldingChangeParam.DROSSEL_TOKEN) {
                    Log.i(TAG, "DROSSEL_TOKEN is true.");
                    //Werkstoff.performClick();
                    WeldingChangeParam.DROSSEL_TOKEN = false;
                }

                if (WeldingChangeParam.DATEN_TOKEN) {
                    Log.i(TAG, "DATEN_TOKEN is true.");
                    //data.performClick();
                    WeldingChangeParam.DATEN_TOKEN = false;
                }

                if (WeldingChangeParam.VERFAHREN_TOKEN) {
                    Log.i(TAG, "VERFAHREN_TOKEN is true.");
                    Methode_btn.performClick();
                    WeldingChangeParam.VERFAHREN_TOKEN = false;
                }

                if (WeldingChangeParam.KENNLINIE_TOKEN) {
                    Log.i(TAG, "KENNLINIE_TOKEN is true.");
                    Geberge.performClick();
                    WeldingChangeParam.KENNLINIE_TOKEN = false;
                }

                if (WeldingChangeParam.BETRIEBSART_TOKEN) {
                    Log.i(TAG, "BETRIEBSART_TOKEN is true.");
                    Setting.performClick();
                    WeldingChangeParam.BETRIEBSART_TOKEN = false;
                }

                if (WeldingChangeParam.MENU_TOKEN) {
                    Log.i(TAG, "MENU_TOKEN is true.");
                    Menu.performClick();
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
            testbtn_onclick1(); //request Geberge response
            GlobalVariable.delayInMilli(500);
            sendKennToMachine();
            KENN_HANDLER.postDelayed(KENN_TIMER,250);
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
        //WIG_btn = findViewById(R.id.WIG_btn);
        //MMNormal_btn = findViewById(R.id.MMNormal_btn);
        //MMSynergy_btn = findViewById(R.id.MMSynergy_btn);
        //MMPuls_btn = findViewById(R.id.MMPuls_btn);
        //ElectrodeMMA_btn = findViewById(R.id.ElectrodeMMA_btn);
        minus_button = findViewById(R.id.minus);
        Voltage = findViewById(R.id.btn_voltage);
        Mmin = findViewById(R.id.btn_mmin);
        Korrektur = findViewById(R.id.btn_korrektur); //korrektur textview
        Setting = findViewById(R.id.setting_btn);
        Menu = findViewById(R.id.menu_btn);
        progressBar = findViewById(R.id.progressBar);
        circle_button = findViewById(R.id.button1);
        //frame = findViewById(R.id.framelayout);
        ANZEIGE1 = findViewById(R.id.anzeige1);
        ANZEIGE2 = findViewById(R.id.anzeige2);
        ANZEIGE3 = findViewById(R.id.anzeige3);
        ANZEIGE4 = findViewById(R.id.anzeige4);
        ANZEIGE5 = findViewById(R.id.anzeige5);
        txtprogress = findViewById(R.id.txtpro);
        txtprogress.setTextSize(120);
        unitprogress = findViewById(R.id.unit);

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
        Methode_btn = findViewById(R.id.methode_button); // Verfahren
        home = findViewById(R.id.home);
        DROSSEL = findViewById(R.id.drossel_value);
    }

    private void setVisibility(){
        betriebsart_fragement.setVisibility(View.INVISIBLE);
        plus.setVisibility(View.INVISIBLE);
        Voltage.setVisibility(View.VISIBLE);
        progressBarPlus.setVisibility(View.INVISIBLE);
        progressBarMinus.setVisibility(View.INVISIBLE);
        //frame.setVisibility(View.INVISIBLE);
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
        DROSSEL.setVisibility(View.VISIBLE);
        progressBarPlus.setVisibility(View.VISIBLE);
        progressBarMinus.setVisibility(View.VISIBLE);
        minus_button.setVisibility(View.VISIBLE);
        plus.setVisibility(View.VISIBLE);
        prograssHeid = true;
    }

    private void droessel_gone(){
        //-------------------------- make Werkstoff invisible -------------------------------
        DROSSEL.setVisibility(View.INVISIBLE);
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

    public void onClickJobUser(){
        GlobalVariable.JOBUSER_TOKEN = true;
        Intent intent = new Intent(this, JobsUser.class);
        startActivity(intent);
    }

    public void onClickDatenlogger() {
        Intent intent = new Intent(this, DatalistView.class);
        startActivity(intent);
    }

    public void onClickKennlinie() {
        GlobalVariable.KENN_TOKEN = true;
        Intent intent = new Intent(this, Kennlinier_user.class);
        startActivity(intent);
    }

    private void SetDefautlTime(){
        /*if (ShellInterface.isSuAvailable()) {
            ShellInterface.runCommand("chmod 666 /dev/alarm");
            SystemClock.setCurrentTimeMillis(time);
            ShellInterface.runCommand("chmod 664 /dev/alarm");
        }*/

        Calendar c = Calendar.getInstance();
        c.set(2020, 10, 29, 12, 34, 56);
        AlarmManager am = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        am.setTime(c.getTimeInMillis());

        /*try {
            Process process = Runtime.getRuntime().exec("su");
            DataOutputStream os = new DataOutputStream(process.getOutputStream());
            os.writeBytes("date -s 20120419.024012; \n");
            Log.i("Date","is set");
        } catch (Exception e) {
            Log.d(TAG,"error=="+e.toString());
            e.printStackTrace();
        }*/
    }
}