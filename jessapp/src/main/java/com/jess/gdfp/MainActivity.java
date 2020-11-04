package com.jess.gdfp;

import android.app.AlarmManager;
import android.app.AlertDialog;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
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
import android.widget.Toast;

import com.jess.gdfp.Controller.MainActivity_Controller;
import com.jess.gdfp.View.BetriebsArt;
import com.jess.gdfp.View.BlankFragment;
import com.jess.gdfp.View.DatalistView;
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
    public Button Value2;
    public Button Value3;
    public Button Value1;
    private Button circle_button;
    private Button Drossel_Plus;
    private Button Setting;
    private Button Menu;
    private Button Drossel_Minus;
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
    public TextView Label1;
    public TextView Label2;
    public TextView Label3;
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
    private AlertDialog.Builder builder_verfahren;
    private AlertDialog dialog_verfahren;
    private TextView Ver_Tv;

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
        READVAL_STATUS[3]=0; //Value3

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init_view();
        //progrssinit();
        setVisibility();
        serial_init();
        hold_layout_gone();

        /*Intent intent=new Intent();
        intent.setComponent(new ComponentName("com.android.settings",
                "com.android.settings.DateTimeSettingsSetupWizard"));
        startActivity(intent);*/

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

        //------------------------------- For verfahren only ---------------------------------------
        builder_verfahren = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater_verfahren = getLayoutInflater();
        View dialogView_verfahren = inflater_verfahren.inflate(R.layout.layout_dialog,null);
        builder_verfahren.setView(dialogView_verfahren);
        Ver_Tv = dialogView_verfahren.findViewById(R.id.textView_ver);

        Button dialogButton = dialogView_verfahren.findViewById(R.id.button_ok);
        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_verfahren.dismiss();
                Toast.makeText(getApplicationContext(),"Dismiss", Toast.LENGTH_SHORT).show();
            }
        });
        dialog_verfahren = builder_verfahren.create();
        //------------------------------------------------------------------------------------------

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
                JOB_NUM.setBackground(getResources().getDrawable( R.drawable.border2));
                GlobalVariable.JOBCOUNT = 0;
                /*Uri uri = Uri.parse("content://com.jess.gdfp.jobs/jobs");
                ContentValues values= new ContentValues();
                for(int i=0;i<DatenObjekteJob.DataBase().length ;i++){
                    values.put(JobsDetails.jobdetails[i],DatenObjekteJob.DataBase()[i]);
                }
                Cursor cursor = (Cursor) getContentResolver().insert(uri,values);*/
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
                home_view();
                JOB_NUM.setTextColor(Color.WHITE);
                JOB_NUM.setBackgroundColor(Color.BLACK);
                JOB_NUM.setBackground(getResources().getDrawable( R.drawable.border2));
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
                BlankFragment.setButtonVisible();
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
                changeSystemTime("2020","11","3","9","31","50");
                Log.i("Geberge button","is pressed");
                //SetDefautlTime();
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
            //---------------------------- Request Job -----------------------------------------
            //String FIRSTSTR = UartService.getJob(); //ascii string
            //String SECSTR = UartService.getJob1(1); //ascii string
            home_view();
            //tv.setText("ALERT! THIS PARAM IS NOT AVAILABLE!");
            //dialog_verfahren.show();
        });

        JOB_NUM.setOnClickListener(view12 -> {
            if (!job_gedrückt) {
                BlankFragment.setButtonInvisible();
                BlankFragment.tv.setText("JOB");
                BlankFragment.detail = BlankFragment.init_Job();
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
                view12 = findViewById(R.id.fragment_test);
                allgemeinOnclick(view12);
                GlobalVariable.JOB_PRESSED = true;
                JOB_NUM.setTextColor(Color.BLACK);
                JOB_NUM.setBackgroundColor(Color.GRAY);
                job_gedrückt = true;
                //---------------------------- Store Job -------------------------------------------
                //DatenObjekteSend storeJob = new DatenObjekteSend();
                //storeJob.ChangeParameter(37, 0, 1);
            } else {
                GlobalVariable.JOB_PRESSED = false;
                GlobalVariable.Load_Job = false;
                GlobalVariable.Save_Job = false;
               //GlobalVariable.Job_Token = false; //so that load and save layout can work
                WeldingChangeParam.HOME_COUNTER = 0;
                JOB_NUM.setTextColor(Color.WHITE);
                JOB_NUM.setBackgroundColor(Color.BLACK);
                JOB_NUM.setBackground(getResources().getDrawable( R.drawable.border2));
                sendEnergie.ChangeParameter(40,0,0); //deactivate job directly
                hold_layout_gone();
                job_gedrückt = false;
            }

            //---------------------------Decrement Job number---------------------------------------
            //DatenObjekteSend decrementJob = new DatenObjekteSend();
            //decrementJob.ChangeParameter(6,0, 1);
        });

        //---------------------------------------- Drossel -----------------------------------------
        Drossel_Minus.setOnClickListener(view1 -> {
            GlobalVariable.Drossel_token = true;
            int drossel_val = GlobalVariable.KorrekturDrossel;
            drossel_val--;
            //Log.i("drossel_val",String.valueOf(drossel_val));
            DatenObjekteSend test = new DatenObjekteSend();
            test.ChangeParameter(43,drossel_val,1);
        });

        Drossel_Plus.setOnClickListener(view1 -> {
            GlobalVariable.Drossel_token = true;
            int drossel_val = GlobalVariable.KorrekturDrossel;
            drossel_val++;
            //Log.i("drossel_val",String.valueOf(drossel_val));
            DatenObjekteSend test = new DatenObjekteSend();
            test.ChangeParameter(43,drossel_val,1);
        });
        //------------------------------------------------------------------------------------------

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
                } else fav2_gedrückt = false;
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
                } else fav4_gedrückt = false;
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

            /*switch(WeldingChangeParam.HOME_COUNTER) {
                case 0:
                    if ((GlobalVariable.SV1pos1 == 1) && (GlobalVariable.mpm_display < 240)) { //Normal
                        progressBar.setProgress((int) (GlobalVariable.mpm_display) * (100 / 232) - (800 / 232));
                    } else if ((GlobalVariable.SV1pos1 == 2) && (GlobalVariable.mpm_display < 120)) { //Synergie
                        progressBar.setProgress((int) ((GlobalVariable.mpm_display) * 100 / 80 - 50));
                    } else if ((GlobalVariable.SV1pos1 == 3) && (GlobalVariable.mpm_display < 120)) {//Pulse
                        progressBar.setProgress(GlobalVariable.mpm_display - 20);
                    }
                    break;
            }*/

            if (READVAL_STATUS[1] == 1) {
                switch (WeldingChangeParam.HOME_COUNTER) {
                    case 0:
                        if(GlobalVariable.VERFAHREN_VAL!=4) {
                            txtprogress.setText(String.valueOf(GlobalVariable.mpm_display / 10) + "." + String.valueOf(GlobalVariable.mpm_display % 10)); // m/min
                            unitprogress.setText("m/min");
                            if (GlobalVariable.SV1pos1 == 3 || GlobalVariable.SV1pos1 == 2) { // Puls mode and synergie mode
                                Label1.setText("mm");
                                Label2.setText("Ampere");
                                Label3.setText("KorrLB");
                                Value1.setText(String.valueOf((GlobalVariable.BlechdickeSetwert)/ 10 + "." + String.valueOf(GlobalVariable.BlechdickeSetwert % 10)));
                                Value2.setText(String.valueOf(GlobalVariable.StromSetwert));
                                Value3.setText(String.valueOf((GlobalVariable.Lichtbogenkorrektur1)));
                            } else if (GlobalVariable.SV1pos1 == 1) { // Normal mode
                                Label1.setText("KorrLB");
                                Label2.setText("Voltage");
                                Label3.setText("");
                                Value1.setText(String.valueOf((GlobalVariable.Lichtbogenkorrektur1)));
                                Value2.setText(String.valueOf((GlobalVariable.SpannungSetwert)/ 10 + "." + String.valueOf(GlobalVariable.SpannungSetwert % 10)));
                                Value3.setText("");
                            }
                        } else WeldingChangeParam.HOME_COUNTER = 2;
                        break;
                    case 1: // ----------------------------------------------- MM ------------------------------------------------------------------------------------
                        txtprogress.setText(String.valueOf(GlobalVariable.BlechdickeSetwert / 10) + "." + String.valueOf(GlobalVariable.BlechdickeSetwert % 10)); //mm
                        unitprogress.setText("mm");
                        if (GlobalVariable.SV1pos1 == 3 || GlobalVariable.SV1pos1 == 2) { // Puls mode and synergie mode
                            Label1.setText("m/min");
                            Label2.setText("Ampere");
                            Label3.setText("KorrLB");
                            Value1.setText(String.valueOf((GlobalVariable.Energie1)/ 10 + "." + String.valueOf(GlobalVariable.Energie1 % 10)));
                            Value2.setText(String.valueOf(GlobalVariable.StromSetwert));
                            Value3.setText(String.valueOf((GlobalVariable.Lichtbogenkorrektur1)));
                        }
                        break;
                    case 2: // ----------------------------------------------- Ampere --------------------------------------------------------------------------------
                        txtprogress.setText(String.valueOf(GlobalVariable.StromSetwert));
                        unitprogress.setText("A");
                        if (GlobalVariable.SV1pos1 == 3 || GlobalVariable.SV1pos1 == 2) { // Puls mode and synergie mode
                            Label1.setText("m/min");
                            Label2.setText("mm");
                            Label3.setText("KorrLB");

                            Value1.setText(String.valueOf((GlobalVariable.Energie1)/ 10 + "." + String.valueOf(GlobalVariable.Energie1 % 10)));
                            Value2.setText(String.valueOf((GlobalVariable.BlechdickeSetwert)/ 10 + "." + String.valueOf(GlobalVariable.BlechdickeSetwert % 10)));
                            Value3.setText(String.valueOf((GlobalVariable.Lichtbogenkorrektur1)));
                        } else if (GlobalVariable.SV1pos1 == 4) { // Elektrode mode
                            Label1.setText("KorrLB");
                            Label2.setText("");
                            Label3.setText("");
                            Value1.setText(String.valueOf((GlobalVariable.Lichtbogenkorrektur1)));
                            Value2.setText("");
                            Value3.setText("");
                        }
                        break;
                    case 3: // ----------------------------------------------- Korrektur ----------------------------------------------------------------------------
                        txtprogress.setText(String.valueOf(GlobalVariable.Lichtbogenkorrektur1));
                        unitprogress.setText("%");
                        if (GlobalVariable.SV1pos1 == 3 || GlobalVariable.SV1pos1 == 2) { // Puls mode and synergie mode
                            Label1.setText("m/min");
                            Label2.setText("mm");
                            Label3.setText("Ampere");
                            Value1.setText(String.valueOf((GlobalVariable.Energie1)/ 10 + "." + String.valueOf(GlobalVariable.Energie1 % 10)));
                            Value2.setText(String.valueOf((GlobalVariable.BlechdickeSetwert)/ 10 + "." + String.valueOf(GlobalVariable.BlechdickeSetwert % 10)));
                            Value3.setText(String.valueOf(GlobalVariable.StromSetwert));
                        } else if (GlobalVariable.SV1pos1 == 1) { // Normal mode
                            Label1.setText("m/min");
                            Label2.setText("Voltage");
                            Label3.setText("");
                            Value1.setText(String.valueOf((GlobalVariable.Energie1)/ 10 + "." + String.valueOf(GlobalVariable.Energie1 % 10)));
                            Value2.setText(String.valueOf((GlobalVariable.SpannungSetwert)/ 10 + "." + String.valueOf(GlobalVariable.SpannungSetwert % 10)));
                            Value3.setText("");
                        } else if (GlobalVariable.SV1pos1 == 4) { // Elektrode mode
                            Label1.setText("Ampere");
                            Label2.setText("");
                            Label3.setText("");
                            Value1.setText(String.valueOf(GlobalVariable.StromSetwert));
                            Value2.setText("");
                            Value3.setText("");
                        }
                        break;
                    case 4: // ----------------------------------------------- Voltage --------------------------------------------------------------------------------
                        txtprogress.setText(String.valueOf(GlobalVariable.SpannungSetwert / 10) + "." + String.valueOf(GlobalVariable.SpannungSetwert % 10)); //Voltage
                        unitprogress.setText("V");
                        if (GlobalVariable.SV1pos1 == 1) { // Normal mode
                            Label1.setText("m/min");
                            Label2.setText("KorrLB");
                            Label3.setText("");
                            Value1.setText(String.valueOf((GlobalVariable.Energie1)/ 10 + "." + String.valueOf(GlobalVariable.Energie1 % 10)));
                            Value2.setText(String.valueOf(GlobalVariable.Lichtbogenkorrektur1));
                            Value3.setText("");
                        }
                        break;
                    case 5: // ----------------------------------------------------- Job --------------------------------------------------------------------------------
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
                    if (GlobalVariable.SV1pos1!=1 && GlobalVariable.SV1pos1!=4) {
                        ANZEIGE2.setText(GlobalVariable.Werksotff_String[GlobalVariable.SV1pos5]); // Werkstoff
                        ANZEIGE3.setText(GlobalVariable.Draht_String[GlobalVariable.Drahtdurchmesser]); // Drahtdurchmesser
                        if (!GlobalVariable.Betriebsart_Token)
                            ANZEIGE4.setText(GlobalVariable.Betriebsart_String[GlobalVariable.SV1pos2]); // Betriebsart
                        ANZEIGE5.setText(GlobalVariable.Gas_String[GlobalVariable.SV1pos4]); // Gas
                    } else {
                        ANZEIGE2.setText("");
                        ANZEIGE3.setText("");
                        ANZEIGE4.setText("");
                        ANZEIGE5.setText("");
                    }

                    //------------------------------------------------ Brennertasten ---------------------------------------------------------------------------------------
                    if (GlobalVariable.Brennertasten1_string.equals("Brennertaste1 Active")){
                        //Log.i("Brennertasten1_string","is active");
                        hold_layout_view();
                        HoldIst.setText("Ist");
                        StromHoldwertTV.setText(String.valueOf(GlobalVariable.StromIstwert)+" A");
                        SpannungHoldwertTV.setText(String.valueOf(GlobalVariable.SpannungIstwert / 10) + "." + String.valueOf(GlobalVariable.SpannungIstwert % 10)+"V");
                        GlobalVariable.Hold_Token=true;
                        GlobalVariable.countHold = 500;
                   } else if ((GlobalVariable.countHold > 0) && (GlobalVariable.Hold_Token)){
                        //Log.i("StromHoldwertTV","is here");
                        //System.out.println(GlobalVariable.countHold);
                        GlobalVariable.countHold--;
                        hold_layout_view();
                        HoldIst.setText("Hold");
                        StromHoldwertTV.setText(String.valueOf(GlobalVariable.StromHoldwert)+" A");
                        SpannungHoldwertTV.setText(String.valueOf(GlobalVariable.SpannungHoldwert / 10) + "." + String.valueOf(GlobalVariable.SpannungHoldwert % 10)+"V");
                    } else {
                        GlobalVariable.Hold_Token=false;
                        GlobalVariable.BT1_Interrupt = false;
                        hold_layout_gone();
                    }
                    //------------------------------------------------------- Display variables in textview -----------------------------------------------------------------
                    if (GlobalVariable.KorrekturDrossel >= 0) DROSSEL.setText("DROSSEL   "+"[ "+ String.valueOf(GlobalVariable.KorrekturDrossel)+" ]");
                    else DROSSEL.setText("DROSSEL  "+"[ "+ String.valueOf(GlobalVariable.KorrekturDrossel)+" ]");

                    //-------------------------------------------------------------------- Job button -----------------------------------------------------------------------
                    if ((!GlobalVariable.JOB_PRESSED) && (GlobalVariable.Job_Token)){
                        //System.out.println("is also here");
                        if (!GlobalVariable.JobStatus_Display.equals("Inactive"))
                            sendEnergie.ChangeParameter(6,0,0);  //decrement job
                        else {
                            GlobalVariable.Job_Token = false;
                            GlobalVariable.JOBCOUNT = 0;
                            hold_layout_gone();
                        }
                    }

                    if (GlobalVariable.Job_Token && !GlobalVariable.BT1_Interrupt) {
                        //System.out.println("is here");
                        if (GlobalVariable.Load_Job) { //LOAD
                            if (GlobalVariable.JobStatus_Display.equals("Inactive")) sendEnergie.ChangeParameter(5,0,0);
                             //Log.i("LOAD","is here");
                            home_view();
                            hold_layout_view();
                            HoldIst.setText("");
                            StromHoldwertTV.setText("    JOB");
                        } else if (GlobalVariable.Save_Job) { //SAVE
                            home_view();
                            hold_layout_view();
                            HoldIst.setText("");
                            StromHoldwertTV.setText("    JOB");
                        }
                        switch (GlobalVariable.Jobnummer) {
                            case 1: //FAV1
                                SpannungHoldwertTV.setText("FAV1");
                                break;
                            case 2: //FAV2
                                SpannungHoldwertTV.setText("FAV2");
                                break;
                            case 3: //FAV3
                                SpannungHoldwertTV.setText("FAV3");
                                break;
                            case 4: //FAV4
                                SpannungHoldwertTV.setText("FAV4");
                                break;
                            default:
                                SpannungHoldwertTV.setText(String.valueOf(GlobalVariable.Jobnummer));
                                break;
                        }
                    }
                    //-----------------------------------------------------------------------------------------------------------------------------------------------
                }
                Value2.setOnClickListener((View v) -> {
                    new MainActivity_Controller().ChangeTextprogressBar(Value2, txtProgress, temp, CHECK);
                    ChangeTextprogressBar(Value2);
                });

                Value3.setOnClickListener((View v) -> {
                    ChangeTextprogressBar(Value3);
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
            //------------------------------------------------ Menu ------------------------------------------------------
            if (GlobalVariable.Menu_Token){
                if (GlobalVariable.MENU_MODE == 0) onClickJobUser(); //Jobs
                else if (GlobalVariable.MENU_MODE == 1) onClickDatenlogger(); //Datenlogger
                else if (GlobalVariable.MENU_MODE == 2) onClickKennlinie(); //Kennlinie
                else if (GlobalVariable.MENU_MODE == 3) onClickKennlinie(); //Guide
                GlobalVariable.Menu_Token = false;
            }
            //------------------------------------------------------ Korrektur Drossel ------------------------------------------------------------
            if(GlobalVariable.KorrekturDrossel > 0){
                progressBarPlus.setProgress(100*GlobalVariable.KorrekturDrossel/30);
                progressBarMinus.setProgress(0);
            } else {
                progressBarMinus.setProgress(abs(100*GlobalVariable.KorrekturDrossel/30));
                progressBarPlus.setProgress(0);
            }

            if(GlobalVariable.Save_Job && GlobalVariable.ENCODER_PRESSED){
                GlobalVariable.ENCODER_PRESSED = false;
                dialog.show();
            }

            if (counterDisplay == 20) { //update every 0.02s
                long date = System.currentTimeMillis();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm ss");
                dateString = sdf.format(date);
                SimpleDateFormat sdf2 = new SimpleDateFormat("MMM dd yyyy");
                dateString2 = sdf2.format(date);
                tdate.setText(dateString);
                tdate2.setText(dateString2);
                //--------------------------------------------- Verfahren ------------------------------------------------------------------
                if (GlobalVariable.Verfahren_Token){
                    if (GlobalVariable.SV1pos1 != GlobalVariable.VERFAHREN_MODE && GlobalVariable.VERFAHREN_COUNTER<8) {
                        sendEnergie.ChangeParameter(28, 5, 0);
                        GlobalVariable.VERFAHREN_COUNTER++;
                    } else if (GlobalVariable.VERFAHREN_COUNTER<8){
                        GlobalVariable.Verfahren_Token = false;
                        GlobalVariable.VERFAHREN_COUNTER = 0;
                    } else {
                        GlobalVariable.Verfahren_Token = false;
                        Ver_Tv.setText("ALERT! THIS PARAM IS NOT AVAILABLE!");
                        dialog_verfahren.show();
                        GlobalVariable.VERFAHREN_COUNTER = 0;
                    }
                    Log.i("VERFAHREN_COUNTER",String.valueOf(GlobalVariable.VERFAHREN_COUNTER));
                }
                //-------------------------------------------------- Gas ------------------------------------------------------------------
                if (GlobalVariable.Gas_Token){
                    if (GlobalVariable.SV1pos4 != GlobalVariable.GAS_MODE && GlobalVariable.GAS_COUNTER<18) {
                        sendEnergie.ChangeParameter(10, 0, 1);
                        GlobalVariable.GAS_COUNTER++;
                    } else if (GlobalVariable.GAS_COUNTER<18) {
                        GlobalVariable.Gas_Token = false;
                        GlobalVariable.GAS_COUNTER = 0;
                    } else {
                        GlobalVariable.Gas_Token = false;
                        Ver_Tv.setText("ALERT! THIS PARAM IS NOT AVAILABLE!");
                        dialog_verfahren.show();
                        GlobalVariable.GAS_COUNTER = 0;
                    }
                    Log.i("GAS_COUNTER",String.valueOf(GlobalVariable.GAS_COUNTER));
                }
                //------------------------------------------------ Betriebsart ----------------------------------------------------------
                if (GlobalVariable.Betriebsart_Token){
                    if (GlobalVariable.SV1pos2 != GlobalVariable.BETRIEBSART_MODE && GlobalVariable.BETRIEBSART_COUNTER<8) {
                        sendEnergie.ChangeParameter(42,0,0);
                        GlobalVariable.BETRIEBSART_COUNTER++;
                    } else if(GlobalVariable.BETRIEBSART_COUNTER<8) {
                        GlobalVariable.Betriebsart_Token = false;
                        GlobalVariable.BETRIEBSART_COUNTER = 0;
                    } else {
                        GlobalVariable.Betriebsart_Token = false;
                        Ver_Tv.setText("ALERT! THIS PARAM IS NOT AVAILABLE!");
                        dialog_verfahren.show();
                        GlobalVariable.BETRIEBSART_COUNTER = 0;
                    }
                    Log.i("BETRIEBSART_COUNTER",String.valueOf(GlobalVariable.BETRIEBSART_COUNTER));
                }
                //------------------------------------------------------ Drahtdurchmesser ------------------------------------------------------------
                if (GlobalVariable.Drahtdurchmesser_Token){
                    if (GlobalVariable.Drahtdurchmesser != GlobalVariable.DRAHTDURCHMESSER_MODE && GlobalVariable.DRAHTDURCHMESSER_COUNTER<10) {
                        sendEnergie.ChangeParameter(41, 0, 0);
                        GlobalVariable.DRAHTDURCHMESSER_COUNTER++;
                    } else if (GlobalVariable.DRAHTDURCHMESSER_COUNTER<10) {
                        GlobalVariable.Drahtdurchmesser_Token = false;
                        GlobalVariable.DRAHTDURCHMESSER_COUNTER = 0;
                    } else {
                        GlobalVariable.Drahtdurchmesser_Token = false;
                        Ver_Tv.setText("ALERT! THIS PARAM IS NOT AVAILABLE!");
                        dialog_verfahren.show();
                        GlobalVariable.DRAHTDURCHMESSER_COUNTER = 0;
                    }
                    Log.i("DRAHT_COUNTER",String.valueOf(GlobalVariable.DRAHTDURCHMESSER_COUNTER));
                }
                //------------------------------------------------ Werkstoff ----------------------------------------------------------
                if (GlobalVariable.Werkstoff_Token){
                    if (GlobalVariable.SV1pos5 != GlobalVariable.WERKSTOFF_MODE && GlobalVariable.WERKSTOFF_COUNTER<10) {
                        sendEnergie.ChangeParameter(44, 0, 0);
                        GlobalVariable.WERKSTOFF_COUNTER++;
                    } else if(GlobalVariable.WERKSTOFF_COUNTER<10) {
                        GlobalVariable.Werkstoff_Token = false;
                        GlobalVariable.WERKSTOFF_COUNTER = 0;
                    } else {
                        GlobalVariable.Werkstoff_Token = false;
                        Ver_Tv.setText("ALERT! THIS PARAM IS NOT AVAILABLE!");
                        dialog_verfahren.show();
                        GlobalVariable.WERKSTOFF_COUNTER = 0;
                    }
                    Log.i("WERKSTOFF_COUNTER",String.valueOf(GlobalVariable.WERKSTOFF_COUNTER));
                }
                //--------------------------------------------------------------------------------------------------------------------
                //if ((GlobalVariable.JOB_MODE==0) || GlobalVariable.JOB_MODE==1) hold_layout_gone();
                if (GlobalVariable.CONTROL_PANEL_MODE == 1) {
                    if (DatenObjekte.SV1pos1 != 4) { //Not elektrode mode
                        Log.i("Energie1",String.valueOf(GlobalVariable.Energie1));
                        switch (WeldingChangeParam.HOME_COUNTER) {
                            case 0:
                                if (GlobalVariable.mpm_display != GlobalVariable.Energie1) {
                                    Log.i("mpm_display",String.valueOf(GlobalVariable.mpm_display));
                                    sendEnergie.ChangeParameter(2, GlobalVariable.mpm_display, 1); //m/min
                                    //System.out.println("Its here");
                                } else GlobalVariable.CONTROL_PANEL_MODE = 0;
                                break;
                            case 1:
                                if (GlobalVariable.mm_a_display != GlobalVariable.Drahtdurchmesser)
                                    sendEnergie.ChangeParameter(3, GlobalVariable.mm_a_display, 1); //Thickness mm
                                else GlobalVariable.CONTROL_PANEL_MODE = 0;
                                break;
                            case 2:
                                if (GlobalVariable.mm_a_display != GlobalVariable.BlechdickeSetwert)
                                    sendEnergie.ChangeParameter(1, GlobalVariable.mm_a_display, 1); //strom
                                break;
                            case 3: //Korrektur
                                if (GlobalVariable.korrektur_display != GlobalVariable.Lichtbogenkorrektur1)
                                    sendEnergie.ChangeParameter(21, GlobalVariable.korrektur_display, 1);
                                break;
                            case 4:
                                if (GlobalVariable.voltage_display != GlobalVariable.Spannung1) sendEnergie.ChangeParameter(15, GlobalVariable.voltage_display, 1); //voltage
                                break;
                        }
                    }
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
        Drossel_Minus = findViewById(R.id.Drossel_minus);
        Drossel_Plus = findViewById(R.id.Drossel_plus);
        progressBarPlus = findViewById(R.id.progress);
        progressBarMinus = findViewById(R.id.progress1);
        Value1 = findViewById(R.id.btn_korrektur);
        Value2 = findViewById(R.id.btn_mmin);
        Value3 = findViewById(R.id.btn_voltage);
        Label1 = findViewById(R.id.korrektur);
        Label2 = findViewById(R.id.mmin);
        Label3 = findViewById(R.id.strom);
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
        Fav1_btn.setTextSize(27);
        Fav2_btn.setTextSize(27);
        Fav3_btn.setTextSize(27);
        Fav4_btn.setTextSize(27);
        StromHoldwertTV = findViewById(R.id.strom_hold);
        SpannungHoldwertTV = findViewById(R.id.spannung_hold);
        StromHoldwertTV.setTextSize(70);
        SpannungHoldwertTV.setTextSize(70);
        HoldIst = findViewById(R.id.hold_ist);
        hold_layout = findViewById(R.id.hold_layout);
        Methode_btn = findViewById(R.id.methode_button); // Verfahren
        home = findViewById(R.id.home);
        DROSSEL = findViewById(R.id.drossel_value);
    }

    private void setVisibility(){
        betriebsart_fragement.setVisibility(View.INVISIBLE);
        Drossel_Minus.setVisibility(View.INVISIBLE);
        Value3.setVisibility(View.VISIBLE);
        progressBarPlus.setVisibility(View.INVISIBLE);
        progressBarMinus.setVisibility(View.INVISIBLE);
        //frame.setVisibility(View.INVISIBLE);
        circle_button.setVisibility(View.GONE);
        Drossel_Plus.setVisibility(View.GONE);
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
        Drossel_Plus.setVisibility(View.VISIBLE);
        Drossel_Minus.setVisibility(View.VISIBLE);
        prograssHeid = true;
    }

    private void droessel_gone(){
        //-------------------------- make Werkstoff invisible -------------------------------
        DROSSEL.setVisibility(View.INVISIBLE);
        progressBarPlus.setVisibility(View.GONE);
        progressBarMinus.setVisibility(View.INVISIBLE);
        Drossel_Plus.setVisibility(View.GONE);
        Drossel_Minus.setVisibility(View.INVISIBLE);
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

    private void home_view(){
        View layout = findViewById(R.id.zweitelayout);
        layout.setVisibility(View.VISIBLE);
        //frame.setVisibility(View.INVISIBLE);
        kenn_fragment.setVisibility(View.INVISIBLE);
        View layout1 = findViewById(R.id.circleButton);
        betriebsart_fragement.setVisibility(View.INVISIBLE);
        layout1.setVisibility(View.VISIBLE);
        Drossel_view();
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
    }

    //------------------------------------- User set the default time -----------------------------------------
    private void changeSystemTime(String year,String month,String day,String hour,String minute,String second){
        try {
            Process process = Runtime.getRuntime().exec("su");
            DataOutputStream os = new DataOutputStream(process.getOutputStream());
            String command = "date -s "+year+month+day+"."+hour+minute+second+"\n";
            Log.e("command",command);
            os.writeBytes(command);
            os.flush();
            os.writeBytes("exit\n");
            os.flush();
            process.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}