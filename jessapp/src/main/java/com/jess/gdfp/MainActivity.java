package com.jess.gdfp;

import android.app.AlertDialog;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.jess.gdfp.Controller.JsonKennlinie;
import com.jess.gdfp.Controller.MainActivity_Controller;
import com.jess.gdfp.View.BetriebsArt;
import com.jess.gdfp.View.BlankFragment;
import com.jess.gdfp.View.DatalistView;
import com.jess.gdfp.View.JobsUser;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.InvalidParameterException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

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
    private TextView Drossel_Plus;
    private Button Setting;
    private Button Menu;
    private TextView Drossel_Minus;
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
    private ImageButton MOL;
    private ImageButton MOR;
    private static TextView txtprogress;
    private static TextView unitprogress;
    private View frame;
    private View kenn_fragment;
    private View betriebsart_fragement;
    private Button Geberge_button;
    public static Button JOB_NUM;
    private FrameLayout geberge_layout;
    private FrameLayout frame_main;
    private Button Geberge_Left;
    private Button Geberge_Right;
    private TextView Geberge_textview1;
    private TextView Geberge_textview2;
    private boolean Geberge_Btn1_gedrückt = false;
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
    static boolean geberge_gedrückt = false;
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
    private AlertDialog dialog_favbtn;
    private AlertDialog.Builder builder_favbtn;
    private TextView Ver_Tv;

    private LineChart lineChart;
    private LineData lineData;
    private LineDataSet lineDataSet;
    private ArrayList lineEntries = new ArrayList<>();
    private XAxis xAxis;
    private YAxis yAxis;

    public static byte READVAL_STATUS[] = new byte[10];

     private void serial_init(){
         try {
             //UartService.mSerialPort = mApplication.getSerialPort();
             UartService.mSerialPort = new SerialPort(new File("/dev/ttyS4"),1000000, 0); //Open the serial port //2000000
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
       // System.out.println(DatalistView.loadFile(this));
        READVAL_STATUS[1]=0; //m/min
        READVAL_STATUS[2]=0; //korrektur
        READVAL_STATUS[3]=0; //Value3

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init_view();
        setVisibility();
        serial_init();
        hold_layout_gone();
        Stop_Can();
        //Init_Can();
        mHandler = new UartService.MyHandler();
        //-------------------------------------- For Json ------------------------------------------------------------------------------------------
        JsonKennlinie.KennlinieSchrieben(this);
        JsonKennlinie.Kennlinielesen(this);
        String conditions[]={"Werkstof", "Gas"};
        String params[]={"CrNi","spezial"};
        List<org.json.simple.JSONObject> list =JsonKennlinie.query_kennlinie(1,"MIG/MAG pul","Verfahren" ,null,null,this );
        //Log.i("List size",String.valueOf(list.size()));
        String[]filters={"Werkstof","Gas"};
        //Log.i("JsonKennlinie",String.valueOf(JsonKennlinie.getValue_mitFilter(filters,list)));
        //---------------------------------- For line chart ------------------------------------------------------------------------------------------------------------------

        lineChart.setBackgroundColor(Color.DKGRAY);
        lineChart.setNoDataText("ERROR!");
        lineChart.setScaleEnabled(false);
        Legend l = lineChart.getLegend(); //representation of entries on the plotted area of chart.
        l.setEnabled(false);//disable the legend
        /*l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
          l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
          l.setOrientation(Legend.LegendOrientation.VERTICAL);
          l.setDrawInside(false);*/
        /*getSyn4TaktSonderEntries();
        lineDataSet = new LineDataSet(lineEntries, "DateSet 1");
        lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
        //lineDataSet.setDrawCircles(false);
        lineDataSet.setVisible(true);
        lineDataSet.setColors(Color.WHITE);
        lineDataSet.setValueTextColor(Color.WHITE);
        lineDataSet.setValueTextSize(13f);
        XAxis xAxis;
        xAxis = lineChart.getXAxis();
        xAxis.setAxisMaximum(10f);
        // vertical grid lines
        xAxis.disableGridDashedLine();
        xAxis.disableAxisLineDashedLine();
        xAxis.enableGridDashedLine(0f, 20f, 0f);
        xAxis.setAxisLineColor(Color.BLACK);

        YAxis yAxis;
        yAxis = lineChart.getAxisLeft();
        yAxis.setAxisMaximum(24f);
        // disable dual axis (only use LEFT axis)
        lineChart.getAxisRight().setEnabled(false);
        // horizontal grid lines
        yAxis.enableGridDashedLine(0f, 10f, 0f);
        yAxis.setAxisLineColor(Color.BLACK);*/
        //lineChart.setNoDataText("No chart");
        //lineData.setDrawValues(true);
        //-------------------------------- For job button only -------------------------------------
        builder = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_layout,null);
        builder.setView(dialogView);

        button_no = dialogView.findViewById(R.id.button_no);
        button_yes = dialogView.findViewById(R.id.button_yes);
        dialog = builder.create();

        //-------------------------------- For Fav button only -------------------------------------
        builder_favbtn = new AlertDialog.Builder(MainActivity.this);
        builder_favbtn.setView(dialogView);
        dialog_favbtn = builder_favbtn.create();

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
                //Toast.makeText(getApplicationContext(),"Dismiss", Toast.LENGTH_SHORT).show();
            }
        });
        dialog_verfahren = builder_verfahren.create();

        //--------------------------------- Button to store job ------------------------------------
        button_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //Log.i("button_yes","is pressed");
                //----------------------- store job ------------------------------------------------
                DatenObjekteSend storejob = new DatenObjekteSend();
                storejob.ChangeParameter(37,GlobalVariable.Jobnummer,0); //store job mode
                //------------------------- Exit job -----------------------------------------------
                Disable_AllJobToken();
                JOB_NUM.setTextColor(Color.WHITE);
                JOB_NUM.setBackgroundColor(Color.BLACK);
                //JOB_NUM.setBackground(getResources().getDrawable( R.drawable.border2));
                GlobalVariable.JOBCOUNT = 0;
                /*Uri uri = Uri.parse("content://com.jess.gdfp.jobs/jobs");
                ContentValues values= new ContentValues();
                for(int i=0;i<DatenObjekteJob.DataBase().length ;i++){
                    values.put(JobsDetails.jobdetails[i],DatenObjekteJob.DataBase()[i]);
                }
                Cursor cursor = (Cursor) getContentResolver().insert(uri,values);*/
                job_gedrückt = false;
                dialog.cancel();
                dialog_favbtn.cancel();
            }
        });

        button_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //Log.i("button_no","is pressed");
                //------------------------- Exit job -----------------------------------------------
                Disable_AllJobToken();
                home_view();
                JOB_NUM.setTextColor(Color.WHITE);
                JOB_NUM.setBackgroundColor(Color.BLACK);
                //JOB_NUM.setBackground(getResources().getDrawable( R.drawable.border2));
                job_gedrückt = false;
                dialog.cancel();
                dialog_favbtn.cancel();
            }
        });
        //------------------------------------------------------------------------------------------
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
                if ((!GlobalVariable.JOB_PRESSED) && (!GlobalVariable.GEBERGE_PRESSED)) {//if job and geberge button not pressed
                    Disable_AllMenuToken();
                    Disable_AllSettingToken();
                    BlankFragment.tv.setText("VERFAHREN");
                    GlobalVariable.Methode_Token = true;
                    GlobalVariable.Methode_Verfahren = true;
                    BlankFragment.detail = BlankFragment.init_verfahren();
                    BlankFragment.sc.addItems(BlankFragment.detail, 5);
                    BlankFragment.setButtonVisible();
                    geberge_layout_gone();
                    droessel_gone();
                    hold_layout_gone();
                    main_frame_view(view);
                   /* UartService UsbObj = new UartService(MainActivity.this);//mine
                    UsbObj.startService(UartService.class, UartService.usbConnection, null); // Start UartService(if it was not started before) and Bind it
                    Init_Can();
                    newHandler.post(TimerHandler);*/
                }
            }
        });
        //---------------------------------------------- Geberge_button button -------------------------------------------------------------
        Geberge_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!GlobalVariable.JOB_PRESSED) { //if job button not pressed
                    Disable_AllMethodeToken();
                    Disable_AllMenuToken();
                    Disable_AllSettingToken();
                    if (!geberge_gedrückt) {
                        sendEnergie.ChangeParameter(47, 0, 0); //gasvor aka activation
                        GlobalVariable.GEBERGE_PRESSED = true;
                        BlankFragment.tv.setText("");
                        BlankFragment.setButtonInvisible();
                        geberge_layout_view();
                        droessel_gone();
                        hold_layout_gone();
                        view = findViewById(R.id.fragment_test);
                        allgemeinOnclick(view);
                        frame_main.setVisibility(View.INVISIBLE);
                        geberge_gedrückt = true;
                        Geberge_button.setBackgroundColor(Color.GRAY);
                    } else {
                        GlobalVariable.GEBERGE_PRESSED = false;
                        sendEnergie.ChangeParameter(78, 0, 0); //deactivation
                        home_view();
                        Geberge_button.setBackgroundColor(Color.BLACK);
                        geberge_gedrückt = false;
                    }
                }
            }
        });

        Geberge_Left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //----------------------- Normal && Punkten --------------------------------------------------------------------
                if (GlobalVariable.SV1pos1==1 && GlobalVariable.SV1pos2==5) {
                    GebergeMethod.Minus_NormalPunkten();
                }//----------------------- Normal && 2-Takt or 4-Takt --------------------------------------------------------
                else if (GlobalVariable.SV1pos1==1 && (GlobalVariable.SV1pos2==1 || GlobalVariable.SV1pos2==2)) {
                    GebergeMethod.Minus_Normal2Takt4Takt();
                }//----------------------- Normal && 4-Takt Sonder --------------------------------------------------------
                else if (GlobalVariable.SV1pos1==1 && GlobalVariable.SV1pos2==3) {
                    GebergeMethod.Minus_Normal4TaktSonder();
                }//----------------------- Synergie && Punkten --------------------------------------------------------
                else if (GlobalVariable.SV1pos1==2 && GlobalVariable.SV1pos2==5) {
                    GebergeMethod.Minus_SynergiePunkten();
                }//----------------------- Synergie && 2-Takt or 4_Takt --------------------------------------------------------
                else if (GlobalVariable.SV1pos1==2 && (GlobalVariable.SV1pos2==1 || GlobalVariable.SV1pos2==2)) {
                    GebergeMethod.Minus_Synergie2Takt4Takt();
                }//----------------------- Synergie && 4-Takt Sonder --------------------------------------------------------
                else if (GlobalVariable.SV1pos1==2 && GlobalVariable.SV1pos2==3) {
                    GebergeMethod.Minus_Synergie4TaktSonder();
                }//----------------------- Puls && Punkten --------------------------------------------------------
                else if (GlobalVariable.SV1pos1==3 && GlobalVariable.SV1pos2==5) {
                    GebergeMethod.Minus_PulsPunkten();
                }//----------------------- Puls && 2-Takt or 4-Takt --------------------------------------------------------
                else if (GlobalVariable.SV1pos1==3 && (GlobalVariable.SV1pos2==1 || GlobalVariable.SV1pos2==2)) {
                    GebergeMethod.Minus_Puls2Takt4Takt();
                }//----------------------- Puls && 4-Takt Sonder --------------------------------------------------------
                else if (GlobalVariable.SV1pos1==3 && GlobalVariable.SV1pos2==3) {
                    GebergeMethod.Minus_Puls4TaktSonder();
                }
            }
        });

        Geberge_Right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //------------------------------------------ Normal && Punkten --------------------------------------------------------
                if (GlobalVariable.SV1pos1==1 && GlobalVariable.SV1pos2==5) {
                    GebergeMethod.Plus_NormalPunkten();
                }//------------------------------ Normal && 2-Takt or 4-Takt --------------------------------------------------------
                else if (GlobalVariable.SV1pos1==1 && (GlobalVariable.SV1pos2==1 || GlobalVariable.SV1pos2==2)) {
                    GebergeMethod.Plus_Normal2Takt4Takt();
                }//----------------------- Normal && 4-Takt Sonder --------------------------------------------------------
                else if (GlobalVariable.SV1pos1==1 && GlobalVariable.SV1pos2==3) {
                   GebergeMethod.Plus_Normal4TaktSonder();
                }//----------------------- Synergie && Punkten --------------------------------------------------------
                else if (GlobalVariable.SV1pos1==2 && GlobalVariable.SV1pos2==5) {
                    GebergeMethod.Plus_SynergiePunkten();
                }//----------------------- Synergie && 2-Takt or 4_Takt --------------------------------------------------------
                else if (GlobalVariable.SV1pos1==2 && (GlobalVariable.SV1pos2==1 || GlobalVariable.SV1pos2==2)) {
                    GebergeMethod.Plus_Synergie2Takt4Takt();
                }//----------------------- Synergie && 4-Takt Sonder --------------------------------------------------------
                else if (GlobalVariable.SV1pos1==2 && GlobalVariable.SV1pos2==3) {
                    GebergeMethod.Plus_Synergie4TaktSonder();
                }//----------------------- Puls && Punkten --------------------------------------------------------
                else if (GlobalVariable.SV1pos1==3 && GlobalVariable.SV1pos2==5) {
                    GebergeMethod.Plus_PulsPunkten();
                }//----------------------- Puls && 2-Takt or 4-Takt --------------------------------------------------------
                else if (GlobalVariable.SV1pos1==3 && (GlobalVariable.SV1pos2==1 || GlobalVariable.SV1pos2==2)) {
                    GebergeMethod.Plus_Puls2Takt4Takt();
                }//----------------------- Puls && 4-Takt Sonder --------------------------------------------------------
                else if (GlobalVariable.SV1pos1==3 && GlobalVariable.SV1pos2==3) {
                    GebergeMethod.Plus_Puls4TaktSonder();
                }
            }
        });
        //------------------------------------------------------------------------------------------------------------------------------------------------

        JOB_NUM.setOnClickListener(view12 -> {
            if (!GlobalVariable.GEBERGE_PRESSED) {
                Disable_AllMethodeToken();
                Disable_AllMenuToken();
                Disable_AllSettingToken();
                if (!job_gedrückt) { //pressed
                    GlobalVariable.JOB_PRESSED = true;
                    GlobalVariable.INSIDE_JOB = true;
                    BlankFragment.setButtonInvisible();
                    BlankFragment.tv.setText("JOB");
                    BlankFragment.detail = BlankFragment.init_Job();
                    BlankFragment.sc.addItems(BlankFragment.detail, 0);
                    geberge_layout_gone();
                    droessel_gone();
                    hold_layout_gone();
                    main_frame_view(view12);
                    JOB_NUM.setTextColor(Color.BLACK);
                    JOB_NUM.setBackgroundColor(Color.GRAY);
                    job_gedrückt = true;
                } else { //not pressed
                    GlobalVariable.JOB_PRESSED = false;
                    GlobalVariable.INSIDE_JOB = false;
                    GlobalVariable.Load_Job = false;
                    GlobalVariable.Save_Job = false;
                    //GlobalVariable.Job_Token = false; //so that load and save layout can work
                    WeldingChangeParam.HOME_COUNTER = 0;
                    JOB_NUM.setTextColor(Color.WHITE);
                    JOB_NUM.setBackgroundColor(Color.BLACK);
                    JOB_NUM.setBackgroundColor(Color.BLACK);
                    sendEnergie.ChangeParameter(40, 0, 0); //deactivate job directly
                    hold_layout_gone();
                    home_view();
                    job_gedrückt = false;
                }
            }
        });

        MOL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((!GlobalVariable.JOB_PRESSED) && (!GlobalVariable.GEBERGE_PRESSED)) {
                    Disable_AllMethodeToken();
                    if (!GlobalVariable.MOL_gedrückt) {
                        GlobalVariable.MOL_gedrückt = true;
                        UartService.Move_Motor(2);
                    } else {
                        GlobalVariable.MOL_gedrückt = false;
                        UartService.Move_Motor(0);
                        HoldIst.setText("");
                        StromHoldwertTV.setText("");
                        SpannungHoldwertTV.setText("");
                    }
                }
            }
        });

        final ProgressBar progress = findViewById(R.id.progress);
        progress.setVisibility(View.GONE);

        home.setOnClickListener(view1 -> {home_view();});

        Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view ) {
                if ((!GlobalVariable.JOB_PRESSED) && (!GlobalVariable.GEBERGE_PRESSED)) {
                    Disable_AllMethodeToken();
                    GlobalVariable.MENU_PRESSED = true;
                    GlobalVariable.INSIDE_MENU = true;
                    geberge_layout_gone();
                    BlankFragment.setButtonInvisible();
                    BlankFragment.tv.setText("MENU");
                    BlankFragment.detail = BlankFragment.init_Menu();
                    BlankFragment.sc.addItems(BlankFragment.detail, 0);
                    droessel_gone();
                    hold_layout_gone();
                    main_frame_view(view);
                    //--------------------- For encoder -------------------------------------------
                /*GlobalVariable.SETTING_TOKEN = true;
                mainActivityController.onClick_newActivity(com.jess.gdfp.View.Menu.class);
                WeldingChangeParam.MA_TOKEN = false;*/
                }
            }
        });
        Drossel_view();

        Setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((!GlobalVariable.JOB_PRESSED) && (!GlobalVariable.GEBERGE_PRESSED)) {
                    Disable_AllMethodeToken();
                    Disable_AllMenuToken();
                    geberge_layout_gone();
                    GlobalVariable.SETTING_PRESSED = true;
                    GlobalVariable.INSIDE_SETTING = true;
                    BlankFragment.setButtonInvisible();
                    BlankFragment.tv.setText("SETTING");
                    BlankFragment.detail = BlankFragment.init_Setting();
                    BlankFragment.sc.addItems(BlankFragment.detail, 1);
                    droessel_gone();
                    hold_layout_gone();
                    main_frame_view(view);
                }
            }
        });

        MOR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((!GlobalVariable.JOB_PRESSED) && (!GlobalVariable.GEBERGE_PRESSED)) {
                    Disable_AllMethodeToken();
                    if (!GlobalVariable.MOR_gedrückt) {
                        GlobalVariable.MOR_gedrückt = true;
                        UartService.Move_Motor(1);
                    } else {
                        GlobalVariable.MOR_gedrückt = false;
                        UartService.Move_Motor(0);
                        HoldIst.setText("");
                        StromHoldwertTV.setText("");
                        SpannungHoldwertTV.setText("");
                    }
                }
            }
        });
        //---------------------------------------- Drossel -----------------------------------------
        /*Drossel_Minus.setOnClickListener(view1 -> {
            GlobalVariable.Drossel_token = true;
            int drossel_val = GlobalVariable.KorrekturDrossel;
            drossel_val--;
            //Log.i("drossel_val",String.valueOf(drossel_val));
            DatenObjekteSend test = new DatenObjekteSend();
            test.ChangeParameter(43,drossel_val,1);
        });*/

        /*Drossel_Plus.setOnClickListener(view1 -> {
            GlobalVariable.Drossel_token = true;
            int drossel_val = GlobalVariable.KorrekturDrossel;
            drossel_val++;
            //Log.i("drossel_val",String.valueOf(drossel_val));
            DatenObjekteSend test = new DatenObjekteSend();
            test.ChangeParameter(43,drossel_val,1);
        });*/
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

        Fav1_btn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Log.i("Fav1_btn","is long pressed");
                dialog_favbtn.show();
                return false;
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
                Log.i("Fav4_btn","is short pressed");
                if (!fav4_gedrückt) {
                    //----------------------- get the job ------------------------------------------
                    fav4_gedrückt = true;
                } else fav4_gedrückt = false;
            }
        });

        Fav4_btn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                dialog_favbtn.show();
                Log.i("Fav4_btn","is long pressed");
                return false;
            }
        });
        //newHandler.post(TimerHandler);
        UartService UsbObj = new UartService(MainActivity.this);//mine
        UsbObj.startService(UartService.class, UartService.usbConnection, null); // Start UartService(if it was not started before) and Bind it
        Init_Can();
        newHandler.post(TimerHandler);


    }
/*--------------------------------------------------------------------------------------------------
                                  END of onCreate() method
---------------------------------------------------------------------------------------------------*/
    public void shortclick() {
        PopupMenu popupMenu = new PopupMenu(MainActivity.this, circle_button);
        popupMenu.getMenuInflater().inflate(R.menu.menu1, popupMenu.getMenu());
        popupMenu.show();
    }

    public void ChangeTextprogressBar(Button btn) {
        int id = btn.getId();
        if (id == R.id.Value1) {
            TextView textView = findViewById(R.id.Label1);
            String s= textView.getText().toString();
            String s1 = btn.getText().toString();
            String s2= txtProgress.getText().toString();
            textView.setText(temp);
            btn.setText(s2);
            txtProgress.setText(s1);
            temp=s;
            CHECK =false;
        }else if (id == R.id.Value2) {
            TextView textView = findViewById(R.id.Label2);
            String s = textView.getText().toString();
            String s1 = btn.getText().toString();
            String s2 = txtProgress.getText().toString();
            textView.setText(temp);
            txtProgress.setText(s1);
            btn.setText(s2);
            temp = s;
            CHECK =false;
        }else if (id == R.id.Value3) {
            TextView textView = findViewById(R.id.Label3);
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
        //UartService UsbObj = new UartService(this);//mine
        //UsbObj.startService(UartService.class, UartService.usbConnection, null); // Start UartService(if it was not started before) and Bind it
        //final Intent  intent = new Intent(this, DatalistView.class);
        //startActivity(intent);
    }

    @Override
    public void onPause() {
        super.onPause();
        //unregisterReceiver(mUsbReceiver);
        //unbindService(UartService.usbConnection);
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
                            txtprogress.setText(String.valueOf(GlobalVariable.Energie1 / 10) + "." + String.valueOf(GlobalVariable.Energie1 % 10)); // m/min
                            unitprogress.setText("m/min");
                            if (GlobalVariable.SV1pos1 == 3 || GlobalVariable.SV1pos1 == 2) { // Puls mode and synergie mode
                                Label1.setText("mm");
                                Label2.setText("Ampere");
                                Label3.setText("KorrLB");
                                Value1.setText(String.valueOf((GlobalVariable.BlechdickeSetwert)/ 10 + "." + String.valueOf(GlobalVariable.BlechdickeSetwert % 10)));
                                Value2.setText(String.valueOf(GlobalVariable.StromSetwert));
                                Value3.setText(String.valueOf((GlobalVariable.Lichtbogenkorrektur1)));
                            } else if (GlobalVariable.SV1pos1 == 1) { // Normal mode
                                Label1.setText("Voltage");
                                Label2.setText("");
                                Label3.setText("");
                                Value1.setText(String.valueOf((GlobalVariable.SpannungSetwert)/ 10 + "." + String.valueOf(GlobalVariable.SpannungSetwert % 10)));
                                Value2.setText("");
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
                        } else if (GlobalVariable.SV1pos1 == 1) { // Normal mode
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
                        unitprogress.setText("LB [%]");
                        if (GlobalVariable.SV1pos1 == 3 || GlobalVariable.SV1pos1 == 2) { // Puls mode and synergie mode
                            Label1.setText("m/min");
                            Label2.setText("mm");
                            Label3.setText("Ampere");
                            Value1.setText(String.valueOf((GlobalVariable.Energie1)/ 10 + "." + String.valueOf(GlobalVariable.Energie1 % 10)));
                            Value2.setText(String.valueOf((GlobalVariable.BlechdickeSetwert)/ 10 + "." + String.valueOf(GlobalVariable.BlechdickeSetwert % 10)));
                            Value3.setText(String.valueOf(GlobalVariable.StromSetwert));
                        } else if (GlobalVariable.SV1pos1 == 4) { // Elektrode mode
                            Label1.setText("Ampere");
                            Label2.setText("");
                            Label3.setText("");
                            Value1.setText(String.valueOf(GlobalVariable.StromSetwert));
                            Value2.setText("");
                            Value3.setText("");
                        }
                        break;
                    case 4: // ----------------------------------------------- Korrektur Drossel --------------------------------------------------------------------------------
                        txtprogress.setText(String.valueOf(GlobalVariable.KorrekturDrossel));
                        unitprogress.setText("Drossel");
                        break;
                    case 5: // ----------------------------------------------- Voltage --------------------------------------------------------------------------------
                        txtprogress.setText(String.valueOf(GlobalVariable.SpannungSetwert / 10) + "." + String.valueOf(GlobalVariable.SpannungSetwert % 10)); //Voltage
                        unitprogress.setText("V");
                        if (GlobalVariable.SV1pos1 == 1) { // Normal mode
                            Label1.setText("m/min");
                            Label2.setText("");
                            Label3.setText("");
                            Value1.setText(String.valueOf((GlobalVariable.Energie1)/ 10 + "." + String.valueOf(GlobalVariable.Energie1 % 10)));
                            Value2.setText("");
                            Value3.setText("");
                        }
                        break;
                }
            }
                if (CHECK) {
                    Log.i("m/min",String.valueOf(GlobalVariable.Energie1));
                    Log.i("mm",String.valueOf(GlobalVariable.BlechdickeSetwert));
                    Log.i("ampere",String.valueOf(GlobalVariable.StromSetwert));
                    Log.i("drossel",String.valueOf(GlobalVariable.KorrekturDrossel));
                    Log.i("lbkorr",String.valueOf(GlobalVariable.Lichtbogenkorrektur1));

                    //----------------------------------------------- Obere layout --------------------------------------------------------
                    if (!GlobalVariable.Verfahren_Token) ANZEIGE1.setText(GlobalVariable.Verfahren_String[GlobalVariable.SV1pos1]); // Verfahren }
                    if (!GlobalVariable.Betriebsart_Token) ANZEIGE2.setText(GlobalVariable.Betriebsart_String[GlobalVariable.SV1pos2]); // Betriebsart
                    if (GlobalVariable.SV1pos1!=1 && GlobalVariable.SV1pos1!=4) {
                        if (!GlobalVariable.Werkstoff_Token) ANZEIGE3.setText(GlobalVariable.Werksotff_String[GlobalVariable.SV1pos5]); // Werkstoff
                        if (!GlobalVariable.Drahtdurchmesser_Token) ANZEIGE4.setText(GlobalVariable.Draht_String[GlobalVariable.Drahtdurchmesser]); // Drahtdurchmesser
                        if (!GlobalVariable.Gas_Token) ANZEIGE5.setText(GlobalVariable.Gas_String[GlobalVariable.SV1pos4]); // Gas
                    } else {
                        ANZEIGE3.setText("");
                        ANZEIGE4.setText("");
                        ANZEIGE5.setText("");
                    }
                    //------------------------------------------------ Brennertasten ---------------------------------------------------------------------------------------
                    if (GlobalVariable.Brennertasten1_string.equals("Brennertaste1 Active")){
                        hold_layout_view();
                        HoldIst.setText("Ist");
                        StromHoldwertTV.setText(String.valueOf(GlobalVariable.StromIstwert)+" A");
                        SpannungHoldwertTV.setText(String.valueOf(GlobalVariable.SpannungIstwert / 10) + "." + String.valueOf(GlobalVariable.SpannungIstwert % 10)+"V");
                        GlobalVariable.Hold_Token=true;
                        GlobalVariable.countHold = 500;
                   } else if ((GlobalVariable.countHold > 0) && (GlobalVariable.Hold_Token)){
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
                    //--------------------------------------- Display KorrekturDrossel in textview -----------------------------------------------------------------
                    if (GlobalVariable.SV1pos1!=1) { //Not normal mode
                        if (GlobalVariable.KorrekturDrossel > 0) {
                            DROSSEL.setText("DROSSEL   " + "[ +" + String.valueOf(GlobalVariable.KorrekturDrossel) + " ]");
                            progressBarPlus.setProgress(100 * GlobalVariable.KorrekturDrossel / 30);
                            progressBarMinus.setProgress(0);
                        } else {
                            DROSSEL.setText("DROSSEL  " + "[ " + String.valueOf(GlobalVariable.KorrekturDrossel) + " ]");
                            progressBarMinus.setProgress(abs(100 * GlobalVariable.KorrekturDrossel / 30));
                            progressBarPlus.setProgress(0);
                        }
                    } else {
                        //------------------------------------------------------ Korrektur Drossel ------------------------------------------------------------
                        DROSSEL.setText("DROSSEL   [ 0 ]");
                        progressBarMinus.setProgress(0);
                        progressBarPlus.setProgress(0);
                    }
                    //-------------------------------------------------------------------- Job button -----------------------------------------------------------------------
                    if ((!GlobalVariable.JOB_PRESSED) && (GlobalVariable.Job_Token)){
                        if (!GlobalVariable.JobStatus_Bit0.equals("Inactive")) sendEnergie.ChangeParameter(6,0,0);  //decrement job
                        else {
                            GlobalVariable.Job_Token = false;
                            GlobalVariable.JOBCOUNT = 0;
                            hold_layout_gone();
                        }
                    }
                    //------------------------------------------------ Display Geberge_button Textview -------------------------------------------------------------------------------
                    if (GlobalVariable.GEBERGE_PRESSED) { //if geberge button is pressed
                        xAxis = lineChart.getXAxis();
                        xAxis.disableGridDashedLine();// vertical grid lines
                        xAxis.disableAxisLineDashedLine();
                        //xAxis.enableGridDashedLine(0f, 20f, 0f);
                        //xAxis.setAxisLineColor(Color.BLACK);
                        xAxis.setEnabled(false);
                        yAxis = lineChart.getAxisLeft();
                        lineChart.getAxisRight().setEnabled(false);// disable dual axis (only use LEFT axis)
                        //yAxis.enableGridDashedLine(0f, 10f, 0f);// horizontal grid lines
                        //yAxis.setAxisLineColor(Color.BLACK);
                        yAxis.setEnabled(false);

                        if (GlobalVariable.SV1pos1==1 && GlobalVariable.SV1pos2==5) {
                            //Normal mode, Punkten
                            /*GlobalVariable.Geberge_XNormPunkt = GlobalVariable.Gasvorströmen*7 + GlobalVariable.Gasnachströmen;
                            GlobalVariable.Geberge_YNormPunkt = GlobalVariable.Energie1*2 + GlobalVariable.Spannung1 + GlobalVariable.Drossel1 + GlobalVariable.Punktzeit + GlobalVariable.Freibrand;
                            xAxis.setAxisMaximum(GlobalVariable.Geberge_XNormPunkt);
                            yAxis.setAxisMaximum(GlobalVariable.Geberge_YNormPunkt);*/
                            xAxis.setAxisMaximum(8f);
                            yAxis.setAxisMaximum(5f);
                            getNormPunktenEntries();
                            switch (GlobalVariable.GEBERGE_NORMAL_PUNKTEN) {
                                case 0:
                                    Geberge_textview1.setText(String.valueOf(GlobalVariable.Gasvorströmen / 10 + "." + String.valueOf(GlobalVariable.Gasvorströmen % 10)));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Normal_Punkten[0]);
                                    if (!GlobalVariable.Gasvorströmen_token) {
                                        sendEnergie.ChangeParameter(47,0,0);
                                        GlobalVariable.Gasvorströmen_token = true;
                                    }
                                    break;
                                case 1:
                                    Geberge_textview1.setText(String.valueOf(GlobalVariable.EinschleichenAbsolut));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Normal_Punkten[1]);
                                    if (!GlobalVariable.EinschleichenAbsolut_token) {
                                        GlobalVariable.Gasvorströmen_token = true;
                                        sendEnergie.ChangeParameter(47,0,0);
                                        GlobalVariable.EinschleichenAbsolut_token = true;
                                    }
                                    break;
                                case 2:
                                    Geberge_textview1.setText(String.valueOf((GlobalVariable.Energie1) / 10 + "." + String.valueOf(GlobalVariable.Energie1 % 10)));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Normal_Punkten[2]);
                                    if (!GlobalVariable.Energie1_token) {
                                        GlobalVariable.EinschleichenAbsolut_token = true;
                                        sendEnergie.ChangeParameter(47,0,0);
                                        GlobalVariable.Energie1_token = true;
                                    }
                                    break;
                                case 3:
                                    Geberge_textview1.setText(String.valueOf(GlobalVariable.Spannung1));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Normal_Punkten[3]);
                                    if (!GlobalVariable.Energie1_token) {
                                        GlobalVariable.EinschleichenAbsolut_token = true;
                                        sendEnergie.ChangeParameter(47,0,0);
                                        GlobalVariable.Energie1_token = true;
                                    }
                                    break;
                                case 4:
                                    Geberge_textview1.setText(String.valueOf(GlobalVariable.Drossel1));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Normal_Punkten[4]);
                                    break;
                                case 5:
                                    Geberge_textview1.setText(String.valueOf((GlobalVariable.Punktzeit) / 10 + "." + String.valueOf(GlobalVariable.Punktzeit % 10)));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Normal_Punkten[5]);
                                    break;
                                case 6:
                                    Geberge_textview1.setText(String.valueOf(GlobalVariable.Freibrand));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Normal_Punkten[6]);
                                    break;
                                case 7:
                                    Geberge_textview1.setText(String.valueOf(GlobalVariable.Gasnachströmen / 10 + "." + String.valueOf(GlobalVariable.Gasnachströmen % 10)));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Normal_Punkten[7]);
                                    break;
                            }
                        } else if (GlobalVariable.SV1pos1==1 && (GlobalVariable.SV1pos2==1 || GlobalVariable.SV1pos2==2)) {
                            //Normal mode, 2-Takt and 4-Takt
                            /*GlobalVariable.Geberge_XNorm24Takt = GlobalVariable.Gasvorströmen*6 + GlobalVariable.Gasnachströmen;
                            GlobalVariable.Geberge_YNorm24Takt = GlobalVariable.Energie1*2 + GlobalVariable.Spannung1 + GlobalVariable.Drossel1 + GlobalVariable.Freibrand;
                            xAxis.setAxisMaximum(GlobalVariable.Geberge_XNorm24Takt);
                            yAxis.setAxisMaximum(GlobalVariable.Geberge_YNorm24Takt);*/
                            xAxis.setAxisMaximum(7f);
                            yAxis.setAxisMaximum(4f);
                            getNorm24TaktEntries();
                            switch (GlobalVariable.GEBERGE_NORMAL_4TAKT) {
                                case 0:
                                    Geberge_textview1.setText(String.valueOf(GlobalVariable.Gasvorströmen / 10 + "." + String.valueOf(GlobalVariable.Gasvorströmen % 10)));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Normal_4Takt[0]);
                                    break;
                                case 1:
                                    Geberge_textview1.setText(String.valueOf(GlobalVariable.EinschleichenKorrektur));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Normal_4Takt[1]);
                                    break;
                                case 2:
                                    Geberge_textview1.setText(String.valueOf((GlobalVariable.Energie1) / 10 + "." + String.valueOf(GlobalVariable.Energie1 % 10)));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Normal_4Takt[2]);
                                    break;
                                case 3:
                                    Geberge_textview1.setText(String.valueOf(GlobalVariable.Spannung1));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Normal_4Takt[3]);
                                    break;
                                case 4:
                                    Geberge_textview1.setText(String.valueOf(GlobalVariable.Drossel1));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Normal_4Takt[4]);
                                    break;
                                case 5:
                                    Geberge_textview1.setText(String.valueOf(GlobalVariable.Freibrand));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Normal_4Takt[5]);
                                    break;
                                case 6:
                                    Geberge_textview1.setText(String.valueOf(GlobalVariable.Gasnachströmen / 10 + "." + String.valueOf(GlobalVariable.Gasnachströmen % 10)));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Normal_4Takt[6]);
                                    break;
                            }
                        } else if (GlobalVariable.SV1pos1==1 && GlobalVariable.SV1pos2==3) {
                            //Normal mode, 4-Takt Sonder
                            /*GlobalVariable.Geberge_XNorm4TaktS = GlobalVariable.Gasvorströmen*14 + GlobalVariable.UpSlope + GlobalVariable.DownSlope + GlobalVariable.Gasnachströmen;
                            GlobalVariable.Geberge_YNorm4TaktS = GlobalVariable.ZündEnergie*2 + GlloobalVariable.ZündSpannung + GlobalVariable.ZündDrossel
                                    + GlobalVariable.Energie1 + GlobalVariable.Spannung1 + GlobalVariable.Drossel1 + GlobalVariable.Energie2 + GlobalVariable.Energie3
                                    + GlobalVariable.EndkraterEnergie + GlobalVariable.EndkraterSpannung + GlobalVariable.EndkraterDrossel + GlobalVariable.Freibrand;
                            xAxis.setAxisMaximum(GlobalVariable.Geberge_XNorm4TaktS);
                            yAxis.setAxisMaximum(GlobalVariable.Geberge_YNorm4TaktS);*/
                            xAxis.setAxisMaximum(16f);
                            yAxis.setAxisMaximum(7f);
                            getNorm4TaktSonderEntries();
                            switch (GlobalVariable.GEBERGE_NORMAL_4TAKTSONDER) {
                                case 0:
                                    Geberge_textview1.setText(String.valueOf(GlobalVariable.Gasvorströmen / 10 + "." + String.valueOf(GlobalVariable.Gasvorströmen % 10)));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Normal_4TaktSonder[0]);
                                    break;
                                case 1:
                                    Geberge_textview1.setText(String.valueOf(GlobalVariable.EinschleichenAbsolut));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Normal_4TaktSonder[1]);
                                    break;
                                case 2:
                                    Geberge_textview1.setText(String.valueOf((GlobalVariable.ZündEnergie) / 10 + "." + String.valueOf(GlobalVariable.ZündEnergie % 10)));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Normal_4TaktSonder[2]);
                                    break;
                                case 3:
                                    Geberge_textview1.setText(String.valueOf(GlobalVariable.ZündSpannung));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Normal_4TaktSonder[3]);
                                    break;
                                case 4:
                                    Geberge_textview1.setText(String.valueOf(GlobalVariable.ZündDrossel));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Normal_4TaktSonder[4]);
                                    break;
                                case 5:
                                    Geberge_textview1.setText(String.valueOf((GlobalVariable.UpSlope) / 10 + "." + String.valueOf(GlobalVariable.UpSlope % 10)));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Normal_4TaktSonder[5]);
                                    break;
                                case 6:
                                    Geberge_textview1.setText(String.valueOf((GlobalVariable.Energie1) / 10 + "." + String.valueOf(GlobalVariable.Energie1 % 10)));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Normal_4TaktSonder[6]);
                                    break;
                                case 7:
                                    Geberge_textview1.setText(String.valueOf(GlobalVariable.Spannung1));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Normal_4TaktSonder[7]);
                                    break;
                                case 8:
                                    Geberge_textview1.setText(String.valueOf(GlobalVariable.Drossel1));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Normal_4TaktSonder[8]);
                                    break;
                                case 9:
                                    Geberge_textview1.setText(String.valueOf(GlobalVariable.Energie2 / 10 + "." + String.valueOf(GlobalVariable.Energie2 % 10)));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Normal_4TaktSonder[9]);
                                    break;
                                case 10:
                                    Geberge_textview1.setText(String.valueOf(GlobalVariable.Energie3 / 10 + "." + String.valueOf(GlobalVariable.Energie3 % 10)));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Normal_4TaktSonder[10]);
                                    break;
                                case 11:
                                    Geberge_textview1.setText(String.valueOf((GlobalVariable.DownSlope) / 10 + "." + String.valueOf(GlobalVariable.DownSlope % 10)));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Normal_4TaktSonder[11]);
                                    break;
                                case 12:
                                    Geberge_textview1.setText(String.valueOf((GlobalVariable.EndkraterEnergie) / 10 + "." + String.valueOf(GlobalVariable.EndkraterEnergie % 10)));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Normal_4TaktSonder[12]);
                                    break;
                                case 13:
                                    Geberge_textview1.setText(String.valueOf(GlobalVariable.EndkraterSpannung));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Normal_4TaktSonder[13]);
                                    break;
                                case 14:
                                    Geberge_textview1.setText(String.valueOf(GlobalVariable.EndkraterDrossel));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Normal_4TaktSonder[14]);
                                    break;
                                case 15:
                                    Geberge_textview1.setText(String.valueOf(GlobalVariable.Freibrand));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Normal_4TaktSonder[15]);
                                    break;
                                case 16:
                                    Geberge_textview1.setText(String.valueOf(GlobalVariable.Gasnachströmen / 10 + "." + String.valueOf(GlobalVariable.Gasnachströmen % 10)));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Normal_4TaktSonder[16]);
                                    break;
                            }
                        } else if (GlobalVariable.SV1pos1==2 && (GlobalVariable.SV1pos2==5)) {
                            //Synergie mode, Punkten
                            /*GlobalVariable.Geberge_XSynPunkt = GlobalVariable.Gasvorströmen*7 + GlobalVariable.Gasnachströmen;
                            GlobalVariable.Geberge_YSynPunkt = GlobalVariable.Zündenergie*3 + GlobalVariable.EndkraterDauer + GlobalVariable.Freibrand;
                            xAxis.setAxisMaximum(GlobalVariable.Geberge_XSynPunkt);
                            yAxis.setAxisMaximum(GlobalVariable.Geberge_YSynPunkt);*/
                            xAxis.setAxisMaximum(8f);
                            yAxis.setAxisMaximum(5f);
                            getSynPunktenEntries();
                            switch (GlobalVariable.GEBERGE_SYNERGIE_PUNKTEN) {
                                case 0:
                                    Geberge_textview1.setText(String.valueOf(GlobalVariable.Gasvorströmen / 10 + "." + String.valueOf(GlobalVariable.Gasvorströmen % 10)));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Synergie_Punkten[0]);
                                    break;
                                case 1:
                                    Geberge_textview1.setText(String.valueOf(GlobalVariable.EinschleichenKorrektur));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Synergie_Punkten[1]);
                                    break;
                                case 2:
                                    Geberge_textview1.setText(String.valueOf(GlobalVariable.ZündDauer));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Synergie_Punkten[2]);
                                    break;
                                case 3:
                                    Geberge_textview1.setText(String.valueOf((GlobalVariable.Zündenergie) / 10 + "." + String.valueOf(GlobalVariable.Zündenergie % 10)));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Synergie_Punkten[3]);
                                    break;
                                case 4:
                                    Geberge_textview1.setText(String.valueOf(GlobalVariable.ZündLichtbogenkorrektur));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Synergie_Punkten[4]);
                                    break;
                                case 5:
                                    Geberge_textview1.setText(String.valueOf((GlobalVariable.Punktzeit) / 10 + "." + String.valueOf(GlobalVariable.Punktzeit % 10)));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Synergie_Punkten[5]);
                                    break;
                                case 6:
                                    Geberge_textview1.setText(GlobalVariable.PowerpulsEinAus_String);
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Synergie_Punkten[6]);
                                    break;
                                case 7:
                                    Geberge_textview1.setText(String.valueOf(GlobalVariable.EndkraterDauer));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Synergie_Punkten[7]);
                                    break;
                                case 8:
                                    Geberge_textview1.setText(String.valueOf(GlobalVariable.Freibrand));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Synergie_Punkten[8]);
                                    break;
                                case 9:
                                    Geberge_textview1.setText(String.valueOf(GlobalVariable.Gasnachströmen / 10 + "." + String.valueOf(GlobalVariable.Gasnachströmen % 10)));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Synergie_Punkten[9]);
                                    break;
                            }
                        } else if (GlobalVariable.SV1pos1==2 && (GlobalVariable.SV1pos2==1 || GlobalVariable.SV1pos2==2)) {
                            //Synergie mode, 2-Takt or 4-Takt
                            /*GlobalVariable.Geberge_XSyn24Takt = GlobalVariable.Gasvorströmen*6 + GlobalVariable.Gasnachströmen;
                            GlobalVariable.Geberge_YSyn24Takt = GlobalVariable.Zündenergie*3+GlobalVariable.Punktzeit+GlobalVariable.EndkraterDauer+GlobalVariable.Freibrand;
                            xAxis.setAxisMaximum(GlobalVariable.Geberge_XSyn24Takt);
                            yAxis.setAxisMaximum(GlobalVariable.Geberge_YSyn24Takt);*/
                            xAxis.setAxisMaximum(6f);
                            yAxis.setAxisMaximum(5f);
                            getSyn24TaktEntries();
                            switch (GlobalVariable.GEBERGE_SYNERGIE_2TAKT) {
                                case 0:
                                    Geberge_textview1.setText(String.valueOf(GlobalVariable.Gasvorströmen / 10 + "." + String.valueOf(GlobalVariable.Gasvorströmen % 10)));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Synergie_2Takt[0]);
                                    break;
                                case 1:
                                    Geberge_textview1.setText(String.valueOf(GlobalVariable.EinschleichenKorrektur));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Synergie_2Takt[1]);
                                    break;
                                case 2:
                                    Geberge_textview1.setText(String.valueOf(GlobalVariable.ZündDauer));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Synergie_2Takt[2]);
                                    break;
                                case 3:
                                    Geberge_textview1.setText(String.valueOf((GlobalVariable.Zündenergie) / 10 + "." + String.valueOf(GlobalVariable.Zündenergie % 10)));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Synergie_2Takt[3]);
                                    break;
                                case 4:
                                    Geberge_textview1.setText(String.valueOf(GlobalVariable.ZündLichtbogenkorrektur));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Synergie_2Takt[4]);
                                    break;
                                case 5:
                                    Geberge_textview1.setText(GlobalVariable.PowerpulsEinAus_String);
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Synergie_2Takt[5]);
                                    break;
                                case 6:
                                    Geberge_textview1.setText(String.valueOf(GlobalVariable.EndkraterDauer));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Synergie_2Takt[6]);
                                    break;
                                case 8:
                                    Geberge_textview1.setText(String.valueOf(GlobalVariable.Freibrand));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Synergie_2Takt[8]);
                                    break;
                                case 9:
                                    Geberge_textview1.setText(String.valueOf(GlobalVariable.Gasnachströmen / 10 + "." + String.valueOf(GlobalVariable.Gasnachströmen % 10)));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Synergie_2Takt[9]);
                                    break;
                            }
                        } else if (GlobalVariable.SV1pos1==2 && GlobalVariable.SV1pos2==3) {
                            //Synergie mode, 4-Takt Sonder
                            /*GlobalVariable.Geberge_XSyn4TaktS = GlobalVariable.Gasvorströmen*8 + GlobalVariable.UpSlope + GlobalVariable.DownSlope + GlobalVariable.Gasnachströmen;
                            GlobalVariable.Geberge_YSyn4TaktS = GlobalVariable.Zündenergie*2 + GlobalVariable.Energie1 + GlobalVariable.Energie2 + GlobalVariable.Energie3 + GlobalVariable.Endkraterenergie + GlobalVariable.Freibrand;
                            xAxis.setAxisMaximum(GlobalVariable.Geberge_XSyn4TaktS);
                            yAxis.setAxisMaximum(GlobalVariable.Geberge_YSyn4TaktS);*/
                            xAxis.setAxisMaximum(11);
                            yAxis.setAxisMaximum(5);
                            getSyn4TaktSonderEntries();
                            switch (GlobalVariable.GEBERGE_SYNERGIE_4TAKTSONDER) {
                                case 0:
                                    Geberge_textview1.setText(String.valueOf(GlobalVariable.Gasvorströmen / 10 + "." + String.valueOf(GlobalVariable.Gasvorströmen % 10)));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Synergie_4TaktSonder[0]);
                                    break;
                                case 1:
                                    Geberge_textview1.setText(String.valueOf(GlobalVariable.EinschleichenKorrektur));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Synergie_4TaktSonder[1]);
                                    break;
                                case 2:
                                    Geberge_textview1.setText(String.valueOf((GlobalVariable.Zündenergie)));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Synergie_4TaktSonder[2]);
                                    break;
                                case 3:
                                    Geberge_textview1.setText(String.valueOf(GlobalVariable.ZündLichtbogenkorrektur));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Synergie_4TaktSonder[3]);
                                    break;
                                case 4:
                                    Geberge_textview1.setText(String.valueOf((GlobalVariable.UpSlope) / 10 + "." + String.valueOf(GlobalVariable.UpSlope % 10)));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Synergie_4TaktSonder[4]);
                                    break;
                                case 5:
                                    Geberge_textview1.setText(String.valueOf((GlobalVariable.Energie1) / 10 + "." + String.valueOf(GlobalVariable.Energie1 % 10)));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Synergie_4TaktSonder[5]);
                                    break;
                                case 6:
                                    Geberge_textview1.setText(String.valueOf(GlobalVariable.Lichtbogenkorrektur1));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Synergie_4TaktSonder[6]);
                                    break;
                                case 7:
                                    Geberge_textview1.setText(GlobalVariable.PowerpulsEinAus_String);
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Synergie_4TaktSonder[7]);
                                    break;
                                case 8:
                                    Geberge_textview1.setText(String.valueOf((GlobalVariable.Energie2) / 10 + "." + String.valueOf(GlobalVariable.Energie2 % 10)));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Synergie_4TaktSonder[8]);
                                    break;
                                case 9:
                                    Geberge_textview1.setText(String.valueOf(GlobalVariable.Lichtbogenkorrektur2));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Synergie_4TaktSonder[9]);
                                    break;
                                case 10:
                                    Geberge_textview1.setText(String.valueOf((GlobalVariable.Energie3) / 10 + "." + String.valueOf(GlobalVariable.Energie3 % 10)));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Synergie_4TaktSonder[10]);
                                    break;
                                case 11:
                                    Geberge_textview1.setText(String.valueOf(GlobalVariable.Lichtbogenkorrektur3));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Synergie_4TaktSonder[11]);
                                    break;
                                case 12:
                                    Geberge_textview1.setText(String.valueOf((GlobalVariable.DownSlope) / 10 + "." + String.valueOf(GlobalVariable.DownSlope % 10)));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Synergie_4TaktSonder[12]);
                                    break;
                                case 13:
                                    Geberge_textview1.setText(String.valueOf((GlobalVariable.Endkraterenergie) / 10 + "." + String.valueOf(GlobalVariable.Endkraterenergie % 10)));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Synergie_4TaktSonder[13]);
                                    break;
                                case 14:
                                    Geberge_textview1.setText(String.valueOf(GlobalVariable.EndKraterLichtbogenkorrektur));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Synergie_4TaktSonder[14]);
                                    break;
                                case 15:
                                    Geberge_textview1.setText(String.valueOf(GlobalVariable.Freibrand));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Synergie_4TaktSonder[15]);
                                    break;
                                case 16:
                                    Geberge_textview1.setText(String.valueOf(GlobalVariable.Gasnachströmen / 10 + "." + String.valueOf(GlobalVariable.Gasnachströmen % 10)));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Synergie_4TaktSonder[16]);
                                    break;
                            }
                        } else if (GlobalVariable.SV1pos1==3 && GlobalVariable.SV1pos2==5) {
                            //Puls mode, Punkten
                            /*GlobalVariable.Geberge_XPulsPunkt = GlobalVariable.Gasvorströmen*7 + GlobalVariable.Gasnachströmen;
                            GlobalVariable.Geberge_YPulsPunkt = GlobalVariable.Energie1*2 + GlobalVariable.ZündDauer + GlobalVariable.Punktzeit + GlobalVariable.EndkraterDauer + GlobalVariable.Freibrand;
                            xAxis.setAxisMaximum(GlobalVariable.Geberge_XPulsPunkt);
                            yAxis.setAxisMaximum(GlobalVariable.Geberge_YPulsPunkt);*/
                            xAxis.setAxisMaximum(8f);
                            yAxis.setAxisMaximum(4f);
                            getPulsPunktenEntries();
                            switch (GlobalVariable.GEBERGE_PULS_PUNKTEN) {
                                case 0:
                                    Geberge_textview1.setText(String.valueOf(GlobalVariable.Gasvorströmen / 10 + "." + String.valueOf(GlobalVariable.Gasvorströmen % 10)));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Puls_Punkten[0]);
                                    break;
                                case 1:
                                    Geberge_textview1.setText(String.valueOf(GlobalVariable.EinschleichenKorrektur));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Puls_Punkten[1]);
                                    break;
                                case 2:
                                    Geberge_textview1.setText(String.valueOf(GlobalVariable.ZündDauer));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Puls_Punkten[2]);
                                    break;
                                case 3:
                                    Geberge_textview1.setText(String.valueOf((GlobalVariable.Energie1) / 10 + "." + String.valueOf(GlobalVariable.Energie1 % 10)));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Puls_Punkten[3]);
                                    break;
                                case 4:
                                    Geberge_textview1.setText(String.valueOf(GlobalVariable.Lichtbogenkorrektur1));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Puls_Punkten[4]);
                                    break;
                                case 5:
                                    Geberge_textview1.setText(String.valueOf((GlobalVariable.Punktzeit) / 10 + "." + String.valueOf(GlobalVariable.Punktzeit % 10)));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Puls_Punkten[5]);
                                    break;
                                case 6:
                                    Geberge_textview1.setText(GlobalVariable.PowerpulsEinAus_String);
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Puls_Punkten[6]);
                                    break;
                                case 7:
                                    Geberge_textview1.setText(String.valueOf(GlobalVariable.EndkraterDauer));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Puls_Punkten[7]);
                                    break;
                                case 8:
                                    Geberge_textview1.setText(String.valueOf(GlobalVariable.Freibrand));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Puls_Punkten[8]);
                                    break;
                                case 9:
                                    Geberge_textview1.setText(String.valueOf(GlobalVariable.Gasnachströmen / 10 + "." + String.valueOf(GlobalVariable.Gasnachströmen % 10)));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Puls_Punkten[9]);
                                    break;
                            }
                        } else if (GlobalVariable.SV1pos1==3 && (GlobalVariable.SV1pos2==1 || GlobalVariable.SV1pos2==2)) {
                            //Puls mode, 2-Takt or 4-Takt
                            /*GlobalVariable.Geberge_XPuls24Takt = GlobalVariable.Gasvorströmen*6 + GlobalVariable.Gasnachströmen;
                            GlobalVariable.Geberge_YPuls24Takt = GlobalVariable.Energie1*2 + GlobalVariable.ZündDauer + GlobalVariable.EndkraterDauer + GlobalVariable.Freibrand;
                            xAxis.setAxisMaximum(GlobalVariable.Geberge_XPuls24Takt);
                            yAxis.setAxisMaximum(GlobalVariable.Geberge_YPuls24Takt);*/
                            xAxis.setAxisMaximum(7f);
                            yAxis.setAxisMaximum(4f);
                            getPuls24TaktEntries();
                            switch (GlobalVariable.GEBERGE_PULS_2TAKT) {
                                case 0:
                                    Geberge_textview1.setText(String.valueOf(GlobalVariable.Gasvorströmen / 10 + "." + String.valueOf(GlobalVariable.Gasvorströmen % 10)));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Puls_2Takt[0]);
                                    break;
                                case 1:
                                    Geberge_textview1.setText(String.valueOf(GlobalVariable.EinschleichenKorrektur));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Puls_2Takt[1]);
                                    break;
                                case 2:
                                    Geberge_textview1.setText(String.valueOf(GlobalVariable.ZündDauer));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Puls_2Takt[2]);
                                    break;
                                case 3:
                                    Geberge_textview1.setText(String.valueOf((GlobalVariable.Energie1) / 10 + "." + String.valueOf(GlobalVariable.Energie1 % 10)));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Puls_2Takt[3]);
                                    break;
                                case 4:
                                    Geberge_textview1.setText(String.valueOf(GlobalVariable.Lichtbogenkorrektur1));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Puls_2Takt[4]);
                                    break;
                                case 5:
                                    Geberge_textview1.setText(GlobalVariable.PowerpulsEinAus_String);
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Puls_2Takt[5]);
                                    break;
                                case 6:
                                    Geberge_textview1.setText(String.valueOf(GlobalVariable.EndkraterDauer));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Puls_2Takt[6]);
                                    break;
                                case 7:
                                    Geberge_textview1.setText(String.valueOf(GlobalVariable.Freibrand));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Puls_2Takt[7]);
                                    break;
                                case 8:
                                    Geberge_textview1.setText(String.valueOf(GlobalVariable.Gasnachströmen / 10 + "." + String.valueOf(GlobalVariable.Gasnachströmen % 10)));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Puls_2Takt[8]);
                                    break;
                            }
                        } else if (GlobalVariable.SV1pos1==3 && GlobalVariable.SV1pos2==3) {
                            //Puls mode, 4-Takt Sonder
                            /*GlobalVariable.Geberge_XPuls4TaktS = GlobalVariable.Gasvorströmen*8 + GlobalVariable.UpSlope + GlobalVariable.DownSlope + GlobalVariable.Gasnachströmen;
                            GlobalVariable.Geberge_YPuls4TaktS = GlobalVariable.Zündenergie*2 + GlobalVariable.Energie1 + GlobalVariable.Energie2 + GlobalVariable.Energie3 + GlobalVariable.Zündenergie + GlobalVariable.Freibrand;
                            xAxis.setAxisMaximum(GlobalVariable.Geberge_XPuls4TaktS);
                            yAxis.setAxisMaximum(GlobalVariable.Geberge_YPuls4TaktS);*/
                            xAxis.setAxisMaximum(11f);
                            yAxis.setAxisMaximum(6f);
                            getPuls4TaktSonderEntries();
                            switch (GlobalVariable.GEBERGE_PULS_4TAKTSONDER) {
                                case 0:
                                    Geberge_textview1.setText(String.valueOf(GlobalVariable.Gasvorströmen / 10 + "." + String.valueOf(GlobalVariable.Gasvorströmen % 10)));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Puls_4TaktSonder[0]);
                                    break;
                                case 1:
                                    Geberge_textview1.setText(String.valueOf(GlobalVariable.EinschleichenKorrektur));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Puls_4TaktSonder[1]);
                                    break;
                                case 2:
                                    Geberge_textview1.setText(String.valueOf((GlobalVariable.Zündenergie) / 10 + "." + String.valueOf(GlobalVariable.Zündenergie % 10)));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Puls_4TaktSonder[2]);
                                    break;
                                case 3:
                                    Geberge_textview1.setText(String.valueOf(GlobalVariable.ZündLichtbogenkorrektur));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Puls_4TaktSonder[3]);
                                    break;
                                case 4:
                                    Geberge_textview1.setText(String.valueOf((GlobalVariable.UpSlope) / 10 + "." + String.valueOf(GlobalVariable.UpSlope % 10)));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Puls_4TaktSonder[4]);
                                    break;
                                case 5:
                                    Geberge_textview1.setText(String.valueOf((GlobalVariable.Energie1) / 10 + "." + String.valueOf(GlobalVariable.Energie1 % 10)));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Puls_4TaktSonder[5]);
                                    break;
                                case 6:
                                    Geberge_textview1.setText(String.valueOf(GlobalVariable.Lichtbogenkorrektur1));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Puls_4TaktSonder[6]);
                                    break;
                                case 7:
                                    Geberge_textview1.setText(GlobalVariable.PowerpulsEinAus_String);
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Puls_4TaktSonder[7]);
                                    break;
                                case 8:
                                    Geberge_textview1.setText(String.valueOf((GlobalVariable.Energie2) / 10 + "." + String.valueOf(GlobalVariable.Energie2 % 10)));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Puls_4TaktSonder[8]);
                                    break;
                                case 9:
                                    Geberge_textview1.setText(String.valueOf((GlobalVariable.Energie3) / 10 + "." + String.valueOf(GlobalVariable.Energie3 % 10)));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Puls_4TaktSonder[9]);
                                    break;
                                case 10:
                                    Geberge_textview1.setText(String.valueOf((GlobalVariable.DownSlope) / 10 + "." + String.valueOf(GlobalVariable.DownSlope % 10)));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Puls_4TaktSonder[10]);
                                    break;
                                case 11:
                                    Geberge_textview1.setText(String.valueOf((GlobalVariable.Zündenergie) / 10 + "." + String.valueOf(GlobalVariable.Zündenergie % 10)));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Puls_4TaktSonder[11]);
                                    break;
                                case 12:
                                    Geberge_textview1.setText(String.valueOf(GlobalVariable.EndKraterLichtbogenkorrektur));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Puls_4TaktSonder[12]);
                                    break;
                                case 13:
                                    Geberge_textview1.setText(String.valueOf(GlobalVariable.Freibrand));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Puls_4TaktSonder[13]);
                                    break;
                                case 14:
                                    Geberge_textview1.setText(String.valueOf(GlobalVariable.Gasnachströmen / 10 + "." + String.valueOf(GlobalVariable.Gasnachströmen % 10)));
                                    Geberge_textview2.setText(GlobalVariable.Geberge_Puls_4TaktSonder[14]);
                                    break;
                            }
                        }
                        lineDataSet = new LineDataSet(lineEntries,"");
                        lineData = new LineData(lineDataSet);
                        lineChart.invalidate(); //refresh the line chart when called
                        lineChart.setData(lineData);
                        lineDataSet.setVisible(true);
                        lineDataSet.setDrawCircles(false);
                        lineDataSet.setLabel("TEST");
                        lineDataSet.setColors(Color.YELLOW);
                        lineDataSet.setValueTextColor(Color.WHITE);
                        lineDataSet.setValueTextSize(15f);

                    }
                    //-----------------------------------------------------------------------------------------------------------------------

                    if (GlobalVariable.Job_Token && !GlobalVariable.BT1_Interrupt) {
                        if (GlobalVariable.Load_Job) { //LOAD
                            if (GlobalVariable.JobStatus_Bit0.equals("Inactive")) sendEnergie.ChangeParameter(5,0,0);
                            home_view();
                            hold_layout_view();
                            HoldIst.setText("");
                            StromHoldwertTV.setText("    JOB");
                        } else if (GlobalVariable.Save_Job) { //SAVE
                            View layout = findViewById(R.id.zweitelayout);
                            layout.setVisibility(View.VISIBLE);
                            kenn_fragment.setVisibility(View.INVISIBLE);
                            View layout1 = findViewById(R.id.circleButton);
                            betriebsart_fragement.setVisibility(View.INVISIBLE);
                            layout1.setVisibility(View.VISIBLE);
                            Drossel_view();
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

                    //---------------------------------------------- Motor Rotation ----------------------------------------------------------------
                    if (GlobalVariable.DrahtvorschubIst>0 && GlobalVariable.DrahtvorschubIst<=32767) { //Motor forward
                        hold_layout_view();
                        //Log.i("DrahtvorschubIst",String.valueOf(GlobalVariable.DrahtvorschubIst));
                        HoldIst.setText("MOTOR");
                        StromHoldwertTV.setText("       M");
                        SpannungHoldwertTV.setText(">>");
                    } else if (GlobalVariable.DrahtvorschubIst>32767 && GlobalVariable.DrahtvorschubIst<65535) { //Motor backward
                        hold_layout_view();
                        //Log.i("DrahtvorschubIst",String.valueOf(GlobalVariable.DrahtvorschubIst));
                        HoldIst.setText("MOTOR");
                        StromHoldwertTV.setText("       <<");
                        SpannungHoldwertTV.setText("M");
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

            if(GlobalVariable.Save_Job && GlobalVariable.ENCODER_PRESSED){
                GlobalVariable.ENCODER_PRESSED = false;
                dialog.show();
            }

            /*if ((GlobalVariable.Boot_time==2000) && (!GlobalVariable.Boot_token)) {
                Init_Can();
                GlobalVariable.Boot_token=true;
            } else if (!GlobalVariable.Boot_token) GlobalVariable.Boot_time++;
            ANZEIGE2.setText(String.valueOf(GlobalVariable.Boot_time));*/

            if (counterDisplay > 20) { //update every 0.02s
                long date = System.currentTimeMillis();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                dateString = sdf.format(date);
                SimpleDateFormat sdf2 = new SimpleDateFormat("dd.MM.yy");
                dateString2 = sdf2.format(date);
                tdate.setText(dateString);
                tdate2.setText(dateString2);
                //--------------------------------------------- Verfahren ------------------------------------------------------------------
                /*if (GlobalVariable.Verfahren_Token){
                    if (GlobalVariable.SV1pos1 != GlobalVariable.VERFAHREN_MODE && GlobalVariable.VERFAHREN_COUNTER<4) {
                        sendEnergie.ChangeParameter(28, 5, 0);
                        GlobalVariable.VERFAHREN_COUNTER++;
                    } else if (GlobalVariable.VERFAHREN_COUNTER<4){
                        WeldingChangeParam.HOME_COUNTER = 0;
                        GlobalVariable.Verfahren_Token = false;
                        GlobalVariable.VERFAHREN_COUNTER = 0;
                    } else {
                        GlobalVariable.Verfahren_Token = false;
                        Ver_Tv.setText("THE VERFAHREN IS NOT AVAILABLE!");
                        dialog_verfahren.show();
                        GlobalVariable.VERFAHREN_COUNTER = 0;
                    }
                    //Log.i("VERFAHREN_COUNTER",String.valueOf(GlobalVariable.VERFAHREN_COUNTER));
                }*/
                //-------------------------------------------------- Gas ------------------------------------------------------------------
                /*if (GlobalVariable.Gas_Token){
                    if (GlobalVariable.SV1pos4 != GlobalVariable.GAS_MODE && GlobalVariable.GAS_COUNTER<18) {
                        sendEnergie.ChangeParameter(10, 0, 1);
                        GlobalVariable.GAS_COUNTER++;
                    } else if (GlobalVariable.GAS_COUNTER<18) {
                        GlobalVariable.Gas_Token = false;
                        GlobalVariable.GAS_COUNTER = 0;
                    } else {
                        GlobalVariable.Gas_Token = false;
                        Ver_Tv.setText("THE GAS IS NOT AVAILABLE!");
                        dialog_verfahren.show();
                        GlobalVariable.GAS_COUNTER = 0;
                    }
                    //Log.i("GAS_COUNTER",String.valueOf(GlobalVariable.GAS_COUNTER));
                }*/
                //------------------------------------------------ Betriebsart ----------------------------------------------------------
                /*if (GlobalVariable.Betriebsart_Token){
                    if (GlobalVariable.SV1pos2 != GlobalVariable.BETRIEBSART_MODE && GlobalVariable.BETRIEBSART_COUNTER<8) {
                        sendEnergie.ChangeParameter(42,0,0);
                        GlobalVariable.BETRIEBSART_COUNTER++;
                    } else if(GlobalVariable.BETRIEBSART_COUNTER<8) {
                        GlobalVariable.Betriebsart_Token = false;
                        GlobalVariable.BETRIEBSART_COUNTER = 0;
                    } else {
                        GlobalVariable.Betriebsart_Token = false;
                        Ver_Tv.setText("THE BETRIEBSART IS NOT AVAILABLE!");
                        dialog_verfahren.show();
                        GlobalVariable.BETRIEBSART_COUNTER = 0;
                    }
                    //Log.i("BETRIEBSART_COUNTER",String.valueOf(GlobalVariable.BETRIEBSART_COUNTER));
                }*/
                //-------------------------------------------- Drahtdurchmesser ------------------------------------------------------------
                /*if (GlobalVariable.Drahtdurchmesser_Token){
                    if (GlobalVariable.Drahtdurchmesser != GlobalVariable.DRAHTDURCHMESSER_MODE && GlobalVariable.DRAHTDURCHMESSER_COUNTER<10) {
                        sendEnergie.ChangeParameter(41, 0, 0);
                        GlobalVariable.DRAHTDURCHMESSER_COUNTER++;
                    } else if (GlobalVariable.DRAHTDURCHMESSER_COUNTER<10) {
                        GlobalVariable.Drahtdurchmesser_Token = false;
                        GlobalVariable.DRAHTDURCHMESSER_COUNTER = 0;
                    } else {
                        GlobalVariable.Drahtdurchmesser_Token = false;
                        Ver_Tv.setText("THE WIRE DIAMETER IS NOT AVAILABLE!");
                        dialog_verfahren.show();
                        GlobalVariable.DRAHTDURCHMESSER_COUNTER = 0;
                    }
                    //Log.i("DRAHT_COUNTER",String.valueOf(GlobalVariable.DRAHTDURCHMESSER_COUNTER));
                }*/
                //------------------------------------------------ Werkstoff ----------------------------------------------------------
                /*if (GlobalVariable.Werkstoff_Token){
                    if (GlobalVariable.SV1pos5 != GlobalVariable.WERKSTOFF_MODE && GlobalVariable.WERKSTOFF_COUNTER<10) {
                        sendEnergie.ChangeParameter(44, 0, 0);
                        GlobalVariable.WERKSTOFF_COUNTER++;
                    } else if(GlobalVariable.WERKSTOFF_COUNTER<10) {
                        GlobalVariable.Werkstoff_Token = false;
                        GlobalVariable.WERKSTOFF_COUNTER = 0;
                    } else {
                        GlobalVariable.Werkstoff_Token = false;
                        Ver_Tv.setText("THE WERKSTOFF IS NOT AVAILABLE!");
                        dialog_verfahren.show();
                        GlobalVariable.WERKSTOFF_COUNTER = 0;
                    }
                    //Log.i("WERKSTOFF_COUNTER",String.valueOf(GlobalVariable.WERKSTOFF_COUNTER));
                }*/
                //--------------------------------------------------------------------------------------------------------------------
                //if ((GlobalVariable.JOB_MODE==0) || GlobalVariable.JOB_MODE==1) hold_layout_gone();
                if (GlobalVariable.CONTROL_PANEL_MODE == 1) {
                    if (DatenObjekte.SV1pos1 != 4) { //Not elektrode mode
                        //Log.i("Energie1",String.valueOf(GlobalVariable.Energie1));
                        switch (WeldingChangeParam.HOME_COUNTER) {
                            case 0:
                                if (GlobalVariable.mpm_display != GlobalVariable.Energie1) {
                                    //Log.i("mpm_display",String.valueOf(GlobalVariable.mpm_display));
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
                            case 3: //Lichtbogenkorrektur
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
                //------------------------------------------ Methode button -------------------------------------------------
                if (GlobalVariable.Methode_Token) {
                    if (GlobalVariable.Methode_Verfahren)
                        BlankFragment.sc.addItems(BlankFragment.detail, GlobalVariable.SV1pos1 - 1);
                    else if (GlobalVariable.Methode_Werkstoff)
                        BlankFragment.sc.addItems(BlankFragment.detail, GlobalVariable.SV1pos5 - 1);
                    else if (GlobalVariable.Methode_Drahtdurchmesser)
                        BlankFragment.sc.addItems(BlankFragment.detail, GlobalVariable.Drahtdurchmesser - 1);
                    else if (GlobalVariable.Methode_Betriebsart)
                        BlankFragment.sc.addItems(BlankFragment.detail, GlobalVariable.SV1pos2 - 1);
                    else if (GlobalVariable.Methode_Gas)
                        BlankFragment.sc.addItems(BlankFragment.detail, GlobalVariable.SV1pos4);
                }

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
                    Geberge_button.performClick();
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
            newHandler.postDelayed(TimerHandler, 1/100); //reads data from service every 1ms
            UartService.token = true;
        }
    };
    /**
     * Start the timer when Kennlinie Button is pressed
     */
    private Runnable KENN_TIMER = new Runnable() {
        @Override
        public void run() {
            testbtn_onclick1(); //request Geberge_button response
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

    @Override
    public void onFragmentInteraction(Uri uri) {
    }

    public void onpressed_Minus_plus(View view ) {
        mainActivityController.minus_plus_interagieren(progressBarMinus,progressBarPlus,view);
    }

    private void init_view(){
        kenn_fragment = findViewById(R.id.fragment_test);
        betriebsart_fragement = findViewById(R.id.frgment_Betriebsart);
        //------------------------------------ Zweite layout ---------------------------------------
        Label1 = findViewById(R.id.Label1);
        Label2 = findViewById(R.id.Label2);
        Label3 = findViewById(R.id.Label3);
        Value1 = findViewById(R.id.Value1);
        Value2 = findViewById(R.id.Value2);
        Value3 = findViewById(R.id.Value3);
        //------------------------------------ Rechte layout ---------------------------------------
        tdate = findViewById(R.id.date); //right top button
        home = findViewById(R.id.home);
        Menu = findViewById(R.id.menu_btn);
        Setting = findViewById(R.id.setting_btn);
        MOR = findViewById(R.id.MOR_btn);
        //------------------------------------ Obere layout ----------------------------------------
        ANZEIGE1 = findViewById(R.id.anzeige1);
        ANZEIGE2 = findViewById(R.id.anzeige2);
        ANZEIGE3 = findViewById(R.id.anzeige3);
        ANZEIGE4 = findViewById(R.id.anzeige4);
        ANZEIGE5 = findViewById(R.id.anzeige5);
        //------------------------------------ Circle layout ---------------------------------------
        progressBar = findViewById(R.id.progressBar);
        circle_button = findViewById(R.id.button1);
        txtprogress = findViewById(R.id.txtpro);
        txtprogress.setTextSize(120);
        unitprogress = findViewById(R.id.unit);
        //------------------------------------ Untere layout ---------------------------------------
        Fav1_btn = findViewById(R.id.Button_fav1);
        Fav2_btn = findViewById(R.id.Button_fav2);
        Fav3_btn = findViewById(R.id.Button_fav3);
        Fav4_btn = findViewById(R.id.Button_fav4);
        Fav1_btn.setTextSize(27);
        Fav2_btn.setTextSize(27);
        Fav3_btn.setTextSize(27);
        Fav4_btn.setTextSize(27);
        //------------------------------------ Drossel layout --------------------------------------
        DROSSEL = findViewById(R.id.drossel_value);
        Drossel_Minus = findViewById(R.id.Drossel_minus);
        Drossel_Plus = findViewById(R.id.Drossel_plus);
        progressBarPlus = findViewById(R.id.progress);
        progressBarMinus = findViewById(R.id.progress1);
        StromHoldwertTV = findViewById(R.id.strom_hold);
        SpannungHoldwertTV = findViewById(R.id.spannung_hold);
        StromHoldwertTV.setTextSize(70);
        SpannungHoldwertTV.setTextSize(70);
        HoldIst = findViewById(R.id.hold_ist);
        hold_layout = findViewById(R.id.hold_layout);
        //------------------------------------ Linke layout ----------------------------------------
        tdate2 = findViewById(R.id.date2); //left top button
        Methode_btn = findViewById(R.id.methode_button); // Verfahren
        geberge_layout = findViewById(R.id.frame_geberge);
        frame_main = findViewById(R.id.frame_main);
        Geberge_button = findViewById(R.id.geberge);
        Geberge_Left = findViewById(R.id.Geberge_leftbtn);
        Geberge_Right = findViewById(R.id.Geberge_rightbtn);
        Geberge_textview1 = findViewById(R.id.Geberge_tv1);
        Geberge_textview2 = findViewById(R.id.Geberge_tv2);
        lineChart = findViewById(R.id.chart);
        lineChart.setMaxVisibleValueCount(12);
        lineChart.setBackgroundColor(Color.BLACK);
        lineChart.getDescription().setEnabled(false); // disable description text
        lineChart.setTouchEnabled(true); // enable touch gestures
        lineChart.setDrawGridBackground(false);
        lineChart.setDrawBorders(false);
        lineChart.setBorderColor(Color.RED);
        lineChart.setGridBackgroundColor(Color.BLACK);
        lineChart.setScaleXEnabled(false);
        lineChart.setScaleYEnabled(false);
        lineChart.setPinchZoom(true);// force pinch zoom along both axis
        JOB_NUM  = findViewById(R.id.job_btn);
        MOL = findViewById(R.id.MOL_btn);
    }

    private void setVisibility(){
        betriebsart_fragement.setVisibility(View.INVISIBLE);
        Drossel_Minus.setVisibility(View.INVISIBLE);
        Value3.setVisibility(View.VISIBLE);
        progressBarPlus.setVisibility(View.INVISIBLE);
        progressBarMinus.setVisibility(View.INVISIBLE);
        circle_button.setVisibility(View.GONE);
        Drossel_Plus.setVisibility(View.GONE);
    }

    private  void ausblinden(View view1, View view2){
        view1.setVisibility(View.INVISIBLE);
        view2.setVisibility(View.INVISIBLE);
    }

    private void testbtn_onclick1(){
        //----------------------------------------- Deactivate data parsing --------------------------------------------
        TEMP_BA = GetKennlinieDaten.readKennDaten();
        StringBuilder tempsb = new StringBuilder();
        for(int i=0;i<23;i++){
            TEMP_STRING = tempsb.append((char)(TEMP_BA[i]&0xFF)).toString();
        }
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
        UartService.WriteToSerial(KENN_STRING);
        KENN_STRING = "";
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
        //-------------------------------- make Werkstoff invisible -----------------------------------------------
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

    private void geberge_layout_gone(){
        geberge_layout.setVisibility(View.INVISIBLE);
    }

    private void geberge_layout_view(){
        geberge_layout.setVisibility(View.VISIBLE);
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
        if ((!GlobalVariable.JOB_PRESSED) && (!GlobalVariable.GEBERGE_PRESSED)) {
            View layout = findViewById(R.id.zweitelayout);
            layout.setVisibility(View.VISIBLE);
            kenn_fragment.setVisibility(View.INVISIBLE);
            View layout1 = findViewById(R.id.circleButton);
            betriebsart_fragement.setVisibility(View.INVISIBLE);
            layout1.setVisibility(View.VISIBLE);
            Disable_AllMethodeToken();
            Disable_AllMenuToken();
            Disable_AllSettingToken();
            Drossel_view();
            /*if (geberge_gedrückt) { //unclick geberge button
                GlobalVariable.GEBERGE_PRESSED = false;
                geberge_gedrückt = false;
            }*/
        }
    }

    private void main_frame_view(View view){
        view = findViewById(R.id.fragment_test);
        allgemeinOnclick(view);
        frame_main.setVisibility(View.VISIBLE);
    }

    private static void Init_Can(){
        String InitCanString = "";
        byte[] INIT_FRAME = new byte[9];
        INIT_FRAME[0] = 36; //header
        INIT_FRAME[1] = 9; //frame length
        INIT_FRAME[2] = 16; // frame id
        INIT_FRAME[3] = 0; //can id
        INIT_FRAME[4] = 0; //can id
        INIT_FRAME[5] = 1; //data length
        INIT_FRAME[6] = 3; //data
        INIT_FRAME[7] = 35; //footer
            /**
             * Calculate the checksum of dataframe
             */
            int CHECKSUM = 0;
            for (int i = 0; i < 8 ; i++) {
                int temp;
                if((INIT_FRAME[i]) < 0){ //negative value
                    temp = 256 + (INIT_FRAME[i]);
                }else{
                    temp = INIT_FRAME[i];
                }
                CHECKSUM = CHECKSUM + temp;
            }
        INIT_FRAME[7] = (byte)(CHECKSUM & 0x000000FF);
        INIT_FRAME[8] = 35;

        StringBuilder sbcanSend = new StringBuilder();
        //StringBuilder shexSend = new StringBuilder();
        for (int i = 0; i < 9; i++) {//parameterId and valueId ascii string
            //value[i] = String.format("%02x", (int) ((MOTOR_FRAME[i]) & 0xFF)).toUpperCase(); //convert byte to hex value
            //motorString = shexSend.append(value[i]).toString(); //hex string
            InitCanString = sbcanSend.append((char)(INIT_FRAME[i]&0xFF)).toString(); //ascii string
        }
        UartService.WriteToSerial(InitCanString);
    }

    private static void Stop_Can() {
        String InitCanString = "";
        byte[] INIT_FRAME = new byte[9];
        INIT_FRAME[0] = 36; //header
        INIT_FRAME[1] = 9; //frame length
        INIT_FRAME[2] = 16; // frame id
        INIT_FRAME[3] = 0; //can id
        INIT_FRAME[4] = 0; //can id
        INIT_FRAME[5] = 1; //data length
        INIT_FRAME[6] = 4; //data
        INIT_FRAME[7] = 35; //footer
        /**
         * Calculate the checksum of dataframe
         */
        int CHECKSUM = 0;
        for (int i = 0; i < 8 ; i++) {
            int temp;
            if((INIT_FRAME[i]) < 0){ //negative value
                temp = 256 + (INIT_FRAME[i]);
            }else{
                temp = INIT_FRAME[i];
            }
            CHECKSUM = CHECKSUM + temp;
        }
        INIT_FRAME[7] = (byte)(CHECKSUM & 0x000000FF);
        INIT_FRAME[8] = 35;

        StringBuilder sbcanSend = new StringBuilder();
        //StringBuilder shexSend = new StringBuilder();
        for (int i = 0; i < 9; i++) {//parameterId and valueId ascii string
            //value[i] = String.format("%02x", (int) ((MOTOR_FRAME[i]) & 0xFF)).toUpperCase(); //convert byte to hex value
            //motorString = shexSend.append(value[i]).toString(); //hex string
            InitCanString = sbcanSend.append((char)(INIT_FRAME[i]&0xFF)).toString(); //ascii string
        }
        UartService.WriteToSerial(InitCanString);
    }

    private void getNormPunktenEntries() {
        lineEntries.clear();
        lineEntries.add(new Entry(0f, 0)); //Gasvor
        lineEntries.add(new Entry(1f, 0)); //Gasvor
        lineEntries.add(new Entry(1f,1f)); //EinschleisenKorrektur
        lineEntries.add(new Entry(2f,1f));//EinschleisenKorrektur
        lineEntries.add(new Entry(2f,2f)); //Energie1
        lineEntries.add(new Entry(3f,2f));//Energie1
        lineEntries.add(new Entry(3f,3f)); //Spannung1
        lineEntries.add(new Entry(4f,3f));//Spannung1
        lineEntries.add(new Entry(4f,4f)); //Drossel1
        lineEntries.add(new Entry(5f,4f));//Drossel1
        lineEntries.add(new Entry(5f,5f));//Punktenzeit
        lineEntries.add(new Entry(6f,5f));//Punktenzeit
        lineEntries.add(new Entry(6f,3f));//Freibrand
        lineEntries.add(new Entry(7f,3f));//Freibrand
        lineEntries.add(new Entry(7f,0));//Gasnach
        lineEntries.add(new Entry(8f,0));//Gasnach
        /*lineEntries.add(new Entry(0f, 0)); //Gasvor
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen, 0)); //Gasvor
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen, GlobalVariable.Zündenergie)); //EinschleisenKorrektur
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*2,GlobalVariable.Zündenergie));//EinschleisenKorrektur
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*2, GlobalVariable.Zündenergie+8)); //Energie1
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*3,GlobalVariable.Zündenergie+8));//Energie1
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*3, GlobalVariable.Zündenergie+8)); //Spannung1
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*4,GlobalVariable.Zündenergie+8));//Spannung1
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*4, GlobalVariable.Zündenergie+8)); //Drossel1
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*5,GlobalVariable.Zündenergie+8));//Drossel1
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*5,GlobalVariable.Punktzeit+8));//Punktenzeit
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*6,GlobalVariable.Punktzeit+8));//Punktenzeit
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*6,GlobalVariable.Zündenergie+8));//Freibrand
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*7,GlobalVariable.Zündenergie+8));//Freibrand
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*7,0));//Gasnach
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*8,0));//Gasnach*/
    }

    private void getNorm24TaktEntries() {
        lineEntries.clear();
        lineEntries.add(new Entry(0f, 0)); //Gasvor
        lineEntries.add(new Entry(1f, 0)); //Gasvor
        lineEntries.add(new Entry(1f,1f)); //EinschleisenKorrektur
        lineEntries.add(new Entry(2f,1f));//EinschleisenKorrektur
        lineEntries.add(new Entry(2f,2f)); //Energie1
        lineEntries.add(new Entry(3f,2f));//Energie1
        lineEntries.add(new Entry(3f,3f)); //Spannung1
        lineEntries.add(new Entry(4f,3f));//Spannung1
        lineEntries.add(new Entry(4f,4f)); //Drossel1
        lineEntries.add(new Entry(5f,4f));//Drossel1
        lineEntries.add(new Entry(5f,3f));//Freibrand
        lineEntries.add(new Entry(6f,3f));//Freibrand
        lineEntries.add(new Entry(6f,0));//Gasnach
        lineEntries.add(new Entry(7f,0));//Gasnach
        /*lineEntries.add(new Entry(0f, 0)); //Gasvor
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen, 0)); //Gasvor
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen, GlobalVariable.Zündenergie)); //EinschleisenKorrektur
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*2,GlobalVariable.Zündenergie));//EinschleisenKorrektur
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*2, GlobalVariable.Zündenergie+8)); //Energie1
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*3,GlobalVariable.Zündenergie+8));//Energie1
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*3, GlobalVariable.Zündenergie+8)); //Spannung1
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*4,GlobalVariable.Zündenergie+8));//Spannung1
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*4, GlobalVariable.Zündenergie+8)); //Drossel1
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*5,GlobalVariable.Zündenergie+8));//Drossel1
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*5,GlobalVariable.Zündenergie+8));//Freibrand
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*6,GlobalVariable.Zündenergie+8));//Freibrand
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*6,0));//Gasnach
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*7,0));//Gasnach*/
    }

    private void getNorm4TaktSonderEntries() {
        lineEntries.clear();
        lineEntries.add(new Entry(0f, 0)); //Gasvor
        lineEntries.add(new Entry(1f, 0)); //Gasvor
        lineEntries.add(new Entry(1f,1f)); //EinschleisenKorrektur
        lineEntries.add(new Entry(2f,1f));//EinschleisenKorrektur
        lineEntries.add(new Entry(2f,2f)); //ZündEnergie
        lineEntries.add(new Entry(3f,2f));//ZündEnergie
        lineEntries.add(new Entry(3f,3f)); //ZündSpannung
        lineEntries.add(new Entry(4f,3f));//ZündSpannung
        lineEntries.add(new Entry(4f,4f)); //ZündDrossel
        lineEntries.add(new Entry(5f,4f));//ZündDrossel
        lineEntries.add(new Entry(6f,5f));//UpSlope
        lineEntries.add(new Entry(6f,5f));//Energie1
        lineEntries.add(new Entry(6f,6f));//Spannung1
        lineEntries.add(new Entry(7f,6f));//Spannung1
        lineEntries.add(new Entry(7f,7f));//Drossel1
        lineEntries.add(new Entry(8f,7f));//Drossel1
        lineEntries.add(new Entry(8f,6f));//Energie2
        lineEntries.add(new Entry(9f,6f));//Energie2
        lineEntries.add(new Entry(9f,5f));//Energie3
        lineEntries.add(new Entry(10f,5f));//Energie3
        lineEntries.add(new Entry(11f,4f));//DownSlope
        lineEntries.add(new Entry(12f,4f));//EndkraterEnergie
        lineEntries.add(new Entry(12f,3f));//EndkraterSpannung
        lineEntries.add(new Entry(13f,3f));//EndkraterSpannung
        lineEntries.add(new Entry(13f,2f));//EndkraterDrossel
        lineEntries.add(new Entry(14f,2f));//EndkraterDrossel
        lineEntries.add(new Entry(14f,1f));//Freibrand
        lineEntries.add(new Entry(15f,1f));//Freibrand
        lineEntries.add(new Entry(15f,0));//Gasnach
        lineEntries.add(new Entry(16f,0));//Gasnach
        /*lineEntries.add(new Entry(0f, 0)); //Gasvor
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen, 0)); //Gasvor
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen, GlobalVariable.Zündenergie)); //EinschleisenKorrektur
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*2,GlobalVariable.Zündenergie));//EinschleisenKorrektur
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*2, GlobalVariable.Zündenergie+8)); //ZündEnergie
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*3,GlobalVariable.Zündenergie+8));//ZündEnergie
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*3, GlobalVariable.Zündenergie+8)); //ZündSpannung
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*4,GlobalVariable.Zündenergie+8));//ZündSpannung
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*4, GlobalVariable.Zündenergie+8)); //ZündDrossel
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*5,GlobalVariable.Zündenergie+8));//ZündDrossel
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*5,GlobalVariable.Punktzeit+8));//UpSlope
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*6,GlobalVariable.Punktzeit+8));//UpSlope
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*6,GlobalVariable.Punktzeit+8));//Energie1
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*7,GlobalVariable.Punktzeit+8));//Energie1
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*7,GlobalVariable.Punktzeit+8));//Spannung1
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*8,GlobalVariable.Punktzeit+8));//Spannung1
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*8,GlobalVariable.Punktzeit+8));//Drossel1
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*9,GlobalVariable.Punktzeit+8));//Drossel1
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*9,GlobalVariable.Punktzeit+8));//Energie2
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*10,GlobalVariable.Punktzeit+8));//Energie2
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*10,GlobalVariable.Punktzeit+8));//Energie3
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*11,GlobalVariable.Punktzeit+8));//Energie3
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*11,GlobalVariable.Punktzeit+8));//DownSlope
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*8,GlobalVariable.Punktzeit+8));//DownSlope
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*8,GlobalVariable.EndkraterDauer+8));//EndkraterEnergie
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*9,GlobalVariable.Zündenergie+8));//EndkraterEnergie
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*8,GlobalVariable.EndkraterDauer+8));//EndkraterSpannung
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*9,GlobalVariable.Zündenergie+8));//EndkraterSpannung
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*8,GlobalVariable.EndkraterDauer+8));//EndkraterDrossel
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*9,GlobalVariable.Zündenergie+8));//EndkraterDrossel
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*9,GlobalVariable.Zündenergie+8));//Freibrand
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*10,GlobalVariable.Zündenergie+8));//Freibrand
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*10,0));//Gasnach
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*11,0));//Gasnach*/
    }

    private void getSyn4TaktSonderEntries() { //done
        lineEntries.clear();
        lineEntries.add(new Entry(0f, 0)); //Gasvor
        lineEntries.add(new Entry(1f, 0)); //Gasvor
        lineEntries.add(new Entry(1f, 1f)); //Einschl.Korrektur
        lineEntries.add(new Entry(2f, 1f)); //Einschl.Korrektur
        lineEntries.add(new Entry(2f,2f)); //Zündenergie
        lineEntries.add(new Entry(3f,2f)); //Zündenergie
        lineEntries.add(new Entry(4f,5f));//UpSlope
        lineEntries.add(new Entry(5f,5f));//Energie1
        lineEntries.add(new Entry(5f,4f));//Energie2
        lineEntries.add(new Entry(6f,4f));//Energie2
        lineEntries.add(new Entry(6f,3f));//Energie3
        lineEntries.add(new Entry(7f,3f));//Energie3
        lineEntries.add(new Entry(8f,2f));//DownSlope
        lineEntries.add(new Entry(9f,2f));//Endkraterenergie
        lineEntries.add(new Entry(9f,1f));//Freibrand
        lineEntries.add(new Entry(10f,1f));//Freibrand
        lineEntries.add(new Entry(10f,0));//Gasnach
        lineEntries.add(new Entry(11f,0));//Gasnach
        /*lineEntries.add(new Entry(0f, 0)); //Gasvor
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen, 0)); //Gasvor
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen, GlobalVariable.EinschleichenAbsolut)); //Einschl.Korrektur
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*2, GlobalVariable.EinschleichenAbsolut)); //Einschl.Korrektur
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*2,GlobalVariable.EinschleichenAbsolut+GlobalVariable.Zündenergie)); //Zündenergie
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*3,GlobalVariable.EinschleichenAbsolut+GlobalVariable.Zündenergie)); //Zündenergie
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*3 + GlobalVariable.UpSlope,GlobalVariable.EinschleichenAbsolut+GlobalVariable.Zündenergie+GlobalVariable.Energie1/10+GlobalVariable.Energie1%10));//UpSlope
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*4 + GlobalVariable.UpSlope,GlobalVariable.EinschleichenAbsolut+GlobalVariable.Zündenergie+GlobalVariable.Energie1/10+GlobalVariable.Energie1%10));//Energie1
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*4 + GlobalVariable.UpSlope,GlobalVariable.EinschleichenAbsolut+GlobalVariable.Zündenergie+GlobalVariable.Energie2/10+GlobalVariable.Energie2%10));//Energie2
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*5 + GlobalVariable.UpSlope,GlobalVariable.EinschleichenAbsolut+GlobalVariable.Zündenergie+GlobalVariable.Energie2/10+GlobalVariable.Energie2%10));//Energie2
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*5 + GlobalVariable.UpSlope,GlobalVariable.EinschleichenAbsolut+GlobalVariable.Zündenergie+GlobalVariable.Energie3/10+GlobalVariable.Energie3%10));//Energie3
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*6 + GlobalVariable.UpSlope,GlobalVariable.EinschleichenAbsolut+GlobalVariable.Zündenergie+GlobalVariable.Energie3/10+GlobalVariable.Energie3%10));//Energie3
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*6 + GlobalVariable.UpSlope + GlobalVariable.DownSlope,GlobalVariable.Endkraterenergie+GlobalVariable.Freibrand));//DownSlope
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*7 + GlobalVariable.UpSlope + GlobalVariable.DownSlope,GlobalVariable.Endkraterenergie+GlobalVariable.Freibrand));//Endkraterenergie
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*7 + GlobalVariable.UpSlope + GlobalVariable.DownSlope,GlobalVariable.Freibrand));//Freibrand
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*8 + GlobalVariable.UpSlope + GlobalVariable.DownSlope,GlobalVariable.Freibrand));//Freibrand
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*8 + GlobalVariable.UpSlope + GlobalVariable.DownSlope,0));//Gasnach
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*8 + GlobalVariable.UpSlope + GlobalVariable.DownSlope + GlobalVariable.Gasnachströmen,0));//Gasnach*/
    }

    private void getSynPunktenEntries() { //done
        lineEntries.clear();
        lineEntries.add(new Entry(0f, 0)); //Gasvor
        lineEntries.add(new Entry(1f, 0)); //Gasvor
        lineEntries.add(new Entry(1f,1f)); //EinschleisenKorrektur
        lineEntries.add(new Entry(2f,1f));//EinschleisenKorrektur
        lineEntries.add(new Entry(2f,2f)); //ZündDauer
        lineEntries.add(new Entry(3f,2f));//ZündDauer
        lineEntries.add(new Entry(3f,3.5f)); //Zündenergie
        lineEntries.add(new Entry(4f,3.5f));//Zündenergie
        lineEntries.add(new Entry(4f,5f));//Punktenzeit
        lineEntries.add(new Entry(5f,5f));//Punktenzeit
        lineEntries.add(new Entry(5f,3f));//EndkraterDauer
        lineEntries.add(new Entry(6f,3f));//EndkraterDauer
        lineEntries.add(new Entry(6f,2f));//Freibrand
        lineEntries.add(new Entry(7f,2f));//Freibrand
        lineEntries.add(new Entry(7f,0));//Gasnach
        lineEntries.add(new Entry(8f,0));//Gasnach
        /*lineEntries.add(new Entry(0f, 0)); //Gasvor
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen, 0)); //Gasvor
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen, GlobalVariable.Zündenergie)); //EinschleisenKorrektur
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*2,GlobalVariable.Zündenergie));//EinschleisenKorrektur
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*2, GlobalVariable.Zündenergie*2)); //Zündenergie
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*3,GlobalVariable.Zündenergie*2));//Zündenergie
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*3,GlobalVariable.Zündenergie*2+GlobalVariable.Punktzeit));//Punktenzeit
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*4,GlobalVariable.Zündenergie*2+GlobalVariable.Punktzeit));//Punktenzeit
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*4,GlobalVariable.EndkraterDauer+GlobalVariable.Freibrand));//EndkraterDauer
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*4 + GlobalVariable.EndkraterDauer,GlobalVariable.EndkraterDauer+GlobalVariable.Freibrand));//EndkraterDauer
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*4 + GlobalVariable.EndkraterDauer,GlobalVariable.Freibrand));//Freibrand
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*5 + GlobalVariable.EndkraterDauer,GlobalVariable.Freibrand));//Freibrand
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*5 + GlobalVariable.EndkraterDauer,0));//Gasnach
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*5 + GlobalVariable.EndkraterDauer + GlobalVariable.Gasnachströmen,0));//Gasnach*/
    }

    private void getSyn24TaktEntries() { //done
        lineEntries.clear();
        lineEntries.add(new Entry(0f, 0)); //Gasvor
        lineEntries.add(new Entry(1f, 0)); //Gasvor
        lineEntries.add(new Entry(1f,2f )); //EinschleisenKorrektur
        lineEntries.add(new Entry(2f,2f));//EinschleisenKorrektur
        lineEntries.add(new Entry(2f,4f)); //Zündenergie
        lineEntries.add(new Entry(3f,4f));//Zündenergie
        lineEntries.add(new Entry(3f,3f));//EndkraterDauer
        lineEntries.add(new Entry(4f,3f));//EndkraterDauer
        lineEntries.add(new Entry(4f,1f));//Freibrand
        lineEntries.add(new Entry(5f,1f));//Freibrand
        lineEntries.add(new Entry(5f,0));//Gasnach
        lineEntries.add(new Entry(6f,0));//Gasnach
        /*lineEntries.add(new Entry(0f, 0)); //Gasvor
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen, 0)); //Gasvor
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen, GlobalVariable.Zündenergie)); //EinschleisenKorrektur
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*2,GlobalVariable.Zündenergie));//EinschleisenKorrektur
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*2, GlobalVariable.Zündenergie*2)); //Zündenergie
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*3,GlobalVariable.Zündenergie*2));//Zündenergie
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*3,GlobalVariable.EndkraterDauer+GlobalVariable.Freibrand));//EndkraterDauer
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*3 + GlobalVariable.EndkraterDauer,GlobalVariable.EndkraterDauer+GlobalVariable.Freibrand));//EndkraterDauer
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*3 + GlobalVariable.EndkraterDauer,GlobalVariable.Freibrand));//Freibrand
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*4 + GlobalVariable.EndkraterDauer,GlobalVariable.Freibrand));//Freibrand
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*4 + GlobalVariable.EndkraterDauer,0));//Gasnach
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*4 + GlobalVariable.EndkraterDauer + GlobalVariable.Gasnachströmen,0));//Gasnach*/
    }

    private void getPulsPunktenEntries() {
        lineEntries.clear();
        lineEntries.add(new Entry(0f, 0)); //Gasvor
        lineEntries.add(new Entry(1f, 0)); //Gasvor
        lineEntries.add(new Entry(1f,1f)); //EinschleisenKorrektur
        lineEntries.add(new Entry(2f,1f));//EinschleisenKorrektur
        lineEntries.add(new Entry(2f,2f)); //ZündDauer
        lineEntries.add(new Entry(3f,2f));//ZündDauer
        lineEntries.add(new Entry(3f,3f)); //Energie1
        lineEntries.add(new Entry(4f,3f));//Energie1
        lineEntries.add(new Entry(4f,4f));//Punktenzeit
        lineEntries.add(new Entry(5f,4f));//Punktenzeit
        lineEntries.add(new Entry(5f,3f));//EndkraterDauer
        lineEntries.add(new Entry(6f,3f));//EndkraterDauer
        lineEntries.add(new Entry(6f,2f));//Freibrand
        lineEntries.add(new Entry(7f,2f));//Freibrand
        lineEntries.add(new Entry(7f,0));//Gasnach
        lineEntries.add(new Entry(8f,0));//Gasnach
        /*lineEntries.add(new Entry(0f, 0)); //Gasvor
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen, 0)); //Gasvor
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen, GlobalVariable.Zündenergie)); //EinschleisenKorrektur
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*2,GlobalVariable.Zündenergie));//EinschleisenKorrektur
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*2, GlobalVariable.Zündenergie+8)); //ZündDauer
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*3,GlobalVariable.Zündenergie+8));//ZündDauer
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*3, GlobalVariable.Zündenergie+8)); //Energie1
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*4,GlobalVariable.Zündenergie+8));//Energie1
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*4,GlobalVariable.Punktzeit+8));//Punktenzeit
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*5,GlobalVariable.Punktzeit+8));//Punktenzeit
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*5,GlobalVariable.EndkraterDauer+8));//EndkraterDauer
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*5 + GlobalVariable.EndkraterDauer,GlobalVariable.Zündenergie+8));//EndkraterDauer
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*5 + GlobalVariable.EndkraterDauer,GlobalVariable.Zündenergie+8));//Freibrand
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*6 + GlobalVariable.EndkraterDauer,GlobalVariable.Zündenergie+8));//Freibrand
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*6 + GlobalVariable.EndkraterDauer,0));//Gasnach
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*6 + GlobalVariable.EndkraterDauer + GlobalVariable.Gasnachströmen,0));//Gasnach*/
    }

    private void getPuls24TaktEntries() {
        lineEntries.clear();
        lineEntries.add(new Entry(0f, 0)); //Gasvor
        lineEntries.add(new Entry(1f, 0)); //Gasvor
        lineEntries.add(new Entry(1f,2f)); //EinschleisenKorrektur
        lineEntries.add(new Entry(2f,2f));//EinschleisenKorrektur
        lineEntries.add(new Entry(2f,3f)); //ZündDauer
        lineEntries.add(new Entry(3f,3f));//ZündDauer
        lineEntries.add(new Entry(3f,4f)); //Energie1
        lineEntries.add(new Entry(4f,4f));//Energie1
        lineEntries.add(new Entry(4f,3f));//EndkraterDauer
        lineEntries.add(new Entry(5f,3f));//EndkraterDauer
        lineEntries.add(new Entry(5f,2f));//Freibrand
        lineEntries.add(new Entry(6f,2f));//Freibrand
        lineEntries.add(new Entry(6f,0));//Gasnach
        lineEntries.add(new Entry(7f,0));//Gasnach
        /*lineEntries.add(new Entry(0f, 0)); //Gasvor
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen, 0)); //Gasvor
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen, GlobalVariable.Zündenergie)); //EinschleisenKorrektur
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*2,GlobalVariable.Zündenergie));//EinschleisenKorrektur
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*2, GlobalVariable.Zündenergie+8)); //ZündDauer
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*3,GlobalVariable.Zündenergie+8));//ZündDauer
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*3, GlobalVariable.Zündenergie+8)); //Energie1
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*4,GlobalVariable.Zündenergie+8));//Energie1
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*4,GlobalVariable.Zündenergie+GlobalVariable.ZündDauer+GlobalVariable.EndkraterDauer));//EndkraterDauer
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*4 + GlobalVariable.EndkraterDauer,GlobalVariable.Zündenergie+GlobalVariable.ZündDauer+GlobalVariable.Zündenergie));//EndkraterDauer
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*4 + GlobalVariable.EndkraterDauer,GlobalVariable.Zündenergie+8));//Freibrand
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*5 + GlobalVariable.EndkraterDauer,GlobalVariable.Zündenergie+8));//Freibrand
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*5 + GlobalVariable.EndkraterDauer,0));//Gasnach
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*5 + GlobalVariable.EndkraterDauer + GlobalVariable.Gasnachströmen,0));//Gasnach*/
    }

    private void getPuls4TaktSonderEntries() {
        lineEntries.clear();
        lineEntries.add(new Entry(0f, 0)); //Gasvor
        lineEntries.add(new Entry(1f, 0)); //Gasvor
        lineEntries.add(new Entry(1f,1f)); //EinschleisenKorrektur
        lineEntries.add(new Entry(2f,1f));//EinschleisenKorrektur
        lineEntries.add(new Entry(2f,2f)); //Zündenergie
        lineEntries.add(new Entry(3f,2f));//Zündenergie
        lineEntries.add(new Entry(4f,6f));//UpSlope
        lineEntries.add(new Entry(5f,6f));//Energie1
        lineEntries.add(new Entry(5f,5f));//Energie2
        lineEntries.add(new Entry(6f,5f));//Energie2
        lineEntries.add(new Entry(6f,4f));//Energie3
        lineEntries.add(new Entry(7f,4f));//Energie3
        lineEntries.add(new Entry(8f,3f));//DownSlope
        lineEntries.add(new Entry(9f,3f));//Zündenergie
        lineEntries.add(new Entry(9f,1f));//Freibrand
        lineEntries.add(new Entry(10f,1f));//Freibrand
        lineEntries.add(new Entry(10f,0));//Gasnach
        lineEntries.add(new Entry( 11f,0));//Gasnach
        /*lineEntries.add(new Entry(0f, 0)); //Gasvor
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen, 0)); //Gasvor
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen, GlobalVariable.Zündenergie)); //EinschleisenKorrektur
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*2,GlobalVariable.Zündenergie));//EinschleisenKorrektur
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*2, GlobalVariable.Zündenergie+8)); //Zündenergie
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*3,GlobalVariable.Zündenergie+8));//Zündenergie
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*3,GlobalVariable.Punktzeit+8));//UpSlope
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*4,GlobalVariable.Punktzeit+8));//UpSlope
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*4,GlobalVariable.Punktzeit+8));//Energie1
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*5,GlobalVariable.Punktzeit+8));//Energie1
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*5,GlobalVariable.Punktzeit+8));//Energie2
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*6,GlobalVariable.Punktzeit+8));//Energie2
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*6,GlobalVariable.Punktzeit+8));//Energie3
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*7,GlobalVariable.Punktzeit+8));//Energie3
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*7,GlobalVariable.Punktzeit+8));//DownSlope
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*8,GlobalVariable.Punktzeit+8));//DownSlope
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*8,GlobalVariable.EndkraterDauer+8));//Zündenergie
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*9,GlobalVariable.Zündenergie+8));//Zündenergie
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*9,GlobalVariable.Zündenergie+8));//Freibrand
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*10,GlobalVariable.Zündenergie+8));//Freibrand
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*10,0));//Gasnach
        lineEntries.add(new Entry(GlobalVariable.Gasvorströmen*11 ,0));//Gasnach*/
    }

    private void Disable_AllMethodeToken() {
        GlobalVariable.Methode_Token = false;
        GlobalVariable.Methode_Verfahren = false;
        GlobalVariable.Methode_Werkstoff = false;
        GlobalVariable.Methode_Drahtdurchmesser = false;
        GlobalVariable.Methode_Betriebsart = false;
        GlobalVariable.Methode_Gas = false;
    }

    private void Disable_AllJobToken() {
        GlobalVariable.JOB_PRESSED = false;
        GlobalVariable.Load_Job = false;
        GlobalVariable.Save_Job = false;
        GlobalVariable.INSIDE_JOB = false;
    }
    private void Disable_AllMenuToken() {
        GlobalVariable.MENU_PRESSED = false;
        GlobalVariable.INSIDE_MENU = false;
    }

    private void Disable_AllSettingToken() {
        GlobalVariable.SETTING_PRESSED = false;
        GlobalVariable.INSIDE_SETTING = false;
    }
}