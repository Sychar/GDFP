package com.jess.gdfp;

import android.annotation.SuppressLint;
import android.app.admin.DevicePolicyManager;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
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
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
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
import com.jess.gdfp.DatenBank.JobContract;
import com.jess.gdfp.View.BetriebsArt;
import com.jess.gdfp.View.BlankFragment;
import com.jess.gdfp.View.JobsDetails;
import com.jess.gdfp.View.JobsUser;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.InvalidParameterException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

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
    private Handler testhandler = new Handler();
    private Button m_min;
    private Button current;
    private Button strom;
    private Button testbtn;
    private Intent intent;
    private UartService uartService;
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
    private static int VERFAHREN_MODE = 4;

    private ImageButton backHome;
    private int len;
    private TextView ANZEIGE1;
    private TextView ANZEIGE2;
    private TextView ANZEIGE3;
    private  TextView TxtPr;
    private static TextView txtprogress;
    public TextView TxtProgress;
    private ImageButton Setting;
    private View frame;
    private View kenn_fragment;
    private View betriebsart_fragement;
    private Button kennlinie;
    private Button JOB_NUM;
    private TextView JOB_DISPLAY;
    public static boolean SETTING_TOKEN = false;
    public static boolean CHANGE_TOKEN = false;
    public static int SETTING_COUNTER = 0;
    public static int JOBBTN_COUNTER = 0;
    public static int KENNBTN_COUNTER = 0;
    //public static int JOB_INCREMENT = 0;
    public static boolean ENCODERBUTTON_TOKEN = false;
    public static boolean JOB_TOKEN = false;
    public static boolean STOP_DATENOBJEKTE = false;
    public static boolean PARSE_TOKEN = true;
    public static boolean HOME = false;
    public static int HOME_COUNTER = 0;
    public static boolean gas_token = false;
    public static boolean werkstoff_token = false;
    public static boolean job_token = false;
    public  static int mm_a_display = 0;

    private BluetoothService BSF = null;
    private Boolean btCom_status = false;
    private Button button_menu;
    private Button btConn;
    private Button bleConn;
    private Button ConnectBLE;
    private TextView status;
    private Timer timer;
    private BluetoothAdapter btAdapter;
    private TimerTask timerCount;
    private static int counter_savejob;
    private BluetoothDevice btDevice;
    private BluetoothLEService mBluetoothLEService;
    private BluetoothLEService mBLES;
    //private BroadcastReceiver mBluetoothReceiver; //BLE
    private BluetoothManager mBluetoothManager; //BLE
    private BluetoothAdapter mBluetoothAdapter; //BLE
    //private BluetoothGattServer mBluetoothGattServer; //BLE
    private Set<BluetoothDevice> mRegisteredDevices = new HashSet<>(); //BLE

    private static int rannum = 0;
    public static int headercounter=0;
    private static int counterDisplay = 0;
    private TimerTask timertask;
    private CountDownTimer waitTimer;

    private static int JOB_COUNT = 5;
    private static String cj = "";
    private static String js = "";
    public static String TEST_STR = "";
    public static int COUNTER = 0;
    private static int random = 0;
    private static byte[] TEMP_BA = new byte[40];
    private static String TEMP_STRING = "";
    private static String KENN_STRING = "";
    public static String msg_for_me = "";
    public static String msg_for_me1 = "";
    public static String msg_for_me2 = "";
    public static String msg_for_can = "";
    public static String msg_for_can1 = "";
    public static int getCRCval;
    private static String fulljobstring = "";
    private static int countFrame = 0;
    private ChangeKennlinie changekennlinie;
    static boolean  verfahren_gedrückt = false;
    static boolean kennlinie_gedrückt = false;
    static boolean job_gedrückt = false;
    private static byte[] TEST_FRAME = new byte[20];
    private static byte[] DISABLE_KENN = new byte[20];
    private static byte[] TMP_KENNFRAME = new byte[430];
    public DatenObjekteSend sendEnergie = new DatenObjekteSend();

    private static Charset iso88591charset = Charset.forName("ISO-8859-1");

    private BroadcastReceiver mReceiver;
    private BroadcastReceiver mReceiver1;
    private BroadcastReceiver mReceiver2;
    private BroadcastReceiver mReceiver3;

    private static Boolean prograssHeid = true;
    private static  boolean check =true;
    private  static  String temp ="Energie";

    private static Context actvitiyContext;

    private UsbSerialDevice serial;

    //private UartConnection mUartConnection;

    private UartService.MyHandler mHandler;

    static final int RESULT_ENABLE = 1 ;
    DevicePolicyManager deviceManger ;
    ComponentName compName ;
    Button btnEnable , btnLock ;

    private static boolean VERFAHREN_TOKEN = false;
    private static boolean KENNLINIE_TOKEN = false;
    private static boolean BETRIEBSART_TOKEN = false;
    private static boolean MENU_TOKEN = false;
    private static boolean TEST_TOKEN = false;
    public static boolean HOME_TOKEN = false;
    public static boolean MA_TOKEN = true;
    private static boolean DROSSEL_TOKEN = false;
    private static boolean DATEN_TOKEN = false;
    public static byte READVAL_STATUS[] = new byte[10];
    private static boolean JOB_NUM_TOKEN = false;

    public static int VERFAHREN = 0;
    public static int DRAHTDURCHMESSER = 0;
    public static int GAS = 0;
    public static int WERKSTOFF = 0;

    static int CONTROL_PANEL_MODE = 0;
    //private MainActivity PROGRESSBAR_DISPLAY = new MainActivity();

    /*
 //Command comando = new Command(0, "mount -o remount, rw -t /dev/block/mmcblk0 /system"); //original code
       /* Command comando = new Command(0, "mount -o remount," + "" + "" + "rw /system");
        try {
            RootTools.getShell(true).add(comando);
            Log.i(TAG,"getshell");
        } catch (IOException | RootDeniedException | TimeoutException ex) {
            Log.e(TAG,"does not getshell");
            ex.printStackTrace();
        }
        execute();*/

//final Intent  intent = new Intent(this, DatalistView.class);
//startActivity(intent);


    //txtprogress = findViewById(R.id.txtpro);
    //txtprogress.setText(DatenObjekte.mpm_display);

    //mUartConnection.start();

    //  txtProgress = findViewById(R.id.txtpro);

        /*deviceManger = (DevicePolicyManager) getSystemService(Context. DEVICE_POLICY_SERVICE ) ;
        compName = new ComponentName( this, DeviceAdmin. class ) ;
        boolean active = deviceManger .isAdminActive( compName ) ;
        if (active) {
            btnEnable .setText( "Disable" ) ;
            btnLock .setVisibility(View. VISIBLE ) ;
        } else {
            btnEnable .setText( "Enable" ) ;
            btnLock .setVisibility(View. GONE ) ;
        }*/

    //status = (TextView) findViewById(R.id.anzeige3);
    //  btConn = findViewById(R.id.fav1);
    //BSF = new BluetoothService(this, btHandler); //disable temporarily
        /*btConn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String words = String.valueOf(edittext.gettext());
                //sendReceive.write(words.getBytes(iso88591charset));
                Log.i("Msg","Write to client");
            }
        });*/

    //  bleConn = (Button)findViewById(R.id.BLEbtn);
    //Intent gattServiceIntent = new Intent(this, BluetoothLEService.class); //disable temporarily
    //registerReceiver(mGattUpdateReceiver, makeGattUpdateIntentFilter()); //BroadcastReceiver,IntentFilter
    //bindService(gattServiceIntent, mServiceConnection, BIND_AUTO_CREATE); //Called when a connection to the Service has been established, with the IBinder of the communication channel to the Service. //disable temporarily

    //UartService.mApplication = (Application) getApplication();

    //Register for system Bluetooth events

    //IntentFilter filter = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED); //disable temporarily
    //registerReceiver(mBluetoothReceiver,filter); //BLE //disable temporarily

    //final MainActivity mainActivity = this;


    //Connect to bluetooth gatt server

    //ConnectBLE = (Button)findViewById(R.id.Connectbtn); //disable temporarily
        /*ConnectBLE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mBluetoothLEService.mBluetoothGattServer!=null){
                    mBluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
                    mBluetoothAdapter = mBluetoothManager.getAdapter();
                    BluetoothDevice device = mBluetoothAdapter.getRemoteDevice("9C:28:40:1F:A1:8E");
                    mBluetoothLEService.mBluetoothGattServer.connect(device,false);
                    Log.i(TAG,"ConnectBLE onClicked()");
                } else Log.e(TAG,"mBluetoothGattServer is null");
            }
        });

     //CheckBluetooth(); //disable temporarily

     // data = (Button) findViewById(R.id.date1);
        /*data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclickData();
            }
        });*/
         /*Setting=(Button)findViewById(R.id.setting);
         Setting.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 onClickMenu();
             }
         });*/

         /*new Thread(new Runnable() {
            @Override
            public void run() {
                while (pStatus <= 100) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(pStatus);
                        }
                    });
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    pStatus++;
                }
            }
        }).start();

        public void CheckBluetooth() {

        btAdapter = BluetoothAdapter.getDefaultAdapter(); //Get a handle to the default local Bluetooth adapter.
        Set<BluetoothDevice> pairedDevices = btAdapter.getBondedDevices();
        // If there are paired devices
        if (pairedDevices.size() > 0) {
            // Loop through paired devices
            for (BluetoothDevice device : pairedDevices) {

                Log.e("Mac Addressess","are:  "+btAdapter.getRemoteDevice(device.getAddress()));
            }
        }
        try {
            Method getUuidsMethod = BluetoothAdapter.class.getDeclaredMethod("getUuids", null);
            ParcelUuid[] uuids = (ParcelUuid[]) getUuidsMethod.invoke(btAdapter, null);

            if(uuids != null) {
                for (ParcelUuid uuid : uuids) {
                    Log.i("Here", "UUID: " + uuid.getUuid().toString());
                }
            }else{
                Log.i("Uuids not found","be sure to enable bluetooth");
            }

        }catch(NoSuchMethodException e){
            //e.printStackTrace();
            Log.i("Uuids not found","NoSuchMethodException");
        }catch(IllegalAccessException e){
            //e.printStackTrace();
            Log.i("Uuids not found","IllegalAccessException");
        }catch(InvocationTargetException e){
            //e.printStackTrace();
            Log.i("Uuids not found","InvocationTargetException");
        }

        String name = btAdapter.getName(); //get the Local Bluetooth Name
        if(name == null){
            name = btAdapter.getAddress(); //Returns the hardware address of the local Bluetooth adapter.
        }
        //status.setText(name);
        if (btAdapter == null) {
            status.setText("The device does not support Bluetooth");
            finish();
        } else if (!btAdapter.isEnabled()) { //Return true if Bluetooth is currently enabled and ready for use.
            status.setText("Turning ON Bluetooth");
            btAdapter.enable(); //Turn on the local Bluetooth adapter—do not use without explicit user action to turn on Bluetooth.
            startTimer("OnBluetooth");
        } else {
            connecttoGateway();
        }
    }

    public void startTimer(String type) {
        timer = new Timer();
        if (type.equals("OnBluetooth")) {
            timer_OnBT();
            timer.schedule(timerCount, 1000, 1000); //task to be scheduled,First time at which task is to be executed,time in milliseconds between successive task executions.
        }
    }

    public void timer_OnBT() {
        timerCount = new TimerTask() {
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        if (btAdapter.isEnabled()) { //Return true if Bluetooth is currently enabled and ready for use.
                            stopTheTimer();
                        }
                    }
                });
            }
        };
    }

    public void stopTheTimer() {
        if (timer != null) {
            timer.cancel(); //Terminates this timer, discarding any currently scheduled tasks.
            timer = null;
        }
    }

    private void connecttoGateway() {
        //addressGateway:="98:D3:32:20:8F:AC";
        //btDevice = btAdapter.getRemoteDevice("20:14:04:11:31:66");//original code
        //btDevice = btAdapter.getRemoteDevice("22:22:6e:40:80:75"); //Get a BluetoothDevice object for the given Bluetooth hardware address.
        //btDevice = btAdapter.getRemoteDevice("9c:28:40:1f:a1:8e"); //Xello
        if (BSF.getState() == BluetoothService.STATE_CONNECTED) {
            BSF.cancel(); //Closes this stream and releases any system resources associated with it.
        } //else BSF.toConnect(btDevice);
    }

    public void check_ble() {
        // Use this check to determine whether BLE is supported on the device. Then
        // you can selectively disable BLE-related features.
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            status.setText("BLE Not Supported");
            finish();
        } else {
            status.setText("Bluetooth Active");
            //check_configfile();
        }
    }

    private final ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            Log.i(TAG,"onServiceConnected");
            mBluetoothLEService = ((BluetoothLEService.LocalBinder) service).getService();
            if (!mBluetoothLEService.initialize()) {
                Log.e("onServiceConnected", "Unable to initialize Bluetooth");
                finish();
            } else Log.i("onServiceConnected","Initialise bluetooth");

            //Log.e(TAG, "mBluetoothLeService is okay");
            // Automatically connects to the device upon successful start-up initialization.
            //String mDeviceAddress = "67:36:5D:52:61:A6";
            //String mDeviceAddress = "d0:c5:d3:81:56:ec";
            //mBluetoothLEService.connect(mDeviceAddress);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.e(TAG,"onServiceDisconnected");
            mBluetoothLEService = null;
        }
    };

    public boolean initialize() {
        // For API level 18 and above, get a reference to BluetoothAdapter through BluetoothManager.
        if (mBluetoothManager == null) {
            mBluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
            if (mBluetoothManager == null) {
                Log.e(TAG, "Unable to initialize BluetoothManager.");
                return false;
            }else Log.e(TAG, "Initialize BluetoothManager.");
        }

        mBluetoothAdapter = mBluetoothManager.getAdapter();
        // We can't continue without proper Bluetooth support
        if (mBluetoothAdapter == null) {
            Log.e(TAG, "Unable to obtain a BluetoothAdapter.");
            return false;
        }else Log.e(TAG, "Obtained a BluetoothAdapter.");
        return true;
    }

    private void SaveJob() {
        waitTimer = new CountDownTimer(200, 20) {
            public void onTick(long millisUntilFinished) {

                if (counter_savejob==0) {
                    //Log.i("Job", "Start");
                    DatenObjekteSend sendobj = new DatenObjekteSend();
                    sendobj.ChangeParameter(34, 11, 0); //DatenObjekteSend class
                    counter_savejob++;
                } else if (counter_savejob==1) {
                    writeJOB ();
                    counter_savejob++;
                } else if (counter_savejob==2) {
                    //Log.i("Job", "++");
                    DatenObjekteSend sendobj = new DatenObjekteSend();
                    sendobj.ChangeParameter(34, 7, 0);
                    counter_savejob++;
                } else if (counter_savejob==3) {
                    writeJOB ();
                    counter_savejob++;
                } else if (counter_savejob==4) {
                    //Log.i("Job", "Chosen");
                    DatenObjekteSend sendobj = new DatenObjekteSend();
                    sendobj.ChangeParameter(34, 13, 0);
                    counter_savejob++;
                } else if (counter_savejob==5) {
                    writeJOB ();
                    counter_savejob++;
                } else if (counter_savejob==6) {
                    //Log.i("Job", "Deactivated");
                    DatenObjekteSend sendobj = new DatenObjekteSend();
                    sendobj.ChangeParameter(34, 12, 0);
                    counter_savejob++;
                } else if (counter_savejob==7) {
                    writeJOB ();
                    counter_savejob++;
                }
            }

            public void onFinish() {
                counter_savejob=0;
                stopTimer();
            }
        }.start();
    }

    private void writeJOB () {
        if (msg_for_can != null && !msg_for_can.equals("")) { //send parameter frame to the machine
            if (uartService != null) {
                uartService.write(msg_for_can.getBytes(iso88591charset));
                msg_for_can = "";
                //Log.i("Write",msg_for_can);
            }
        }
        delayInMilli(200);
        if (msg_for_can1 != null && !msg_for_can1.equals("")) { //send value frame to the machine
            if (uartService != null) {
                uartService.write(msg_for_can1.getBytes(iso88591charset));
                //Log.i("Write",msg_for_can1);
                msg_for_can1 = "";
            }
        }
    }

    private void stopTimer(){
        if(waitTimer != null) {
            waitTimer.cancel();
            waitTimer = null;
        }
    }

    private void SaveJob1(int JOB_NUM){

        DatenObjekteSend sendobj = new DatenObjekteSend();
            msg_for_can = "";
            //sendobj.ChangeParameter(34, 11, 0); //DatenObjekteSend class
            delayInMilli(500);
            if (msg_for_can != null && !msg_for_can.equals("")) { //send parameter frame to the machine
                if (uartService != null) {
                    uartService.write(msg_for_can.getBytes(iso88591charset));
                    msg_for_can = "";
                    Log.i("Job", "Start");
                }
            }
            delayInMilli(200);
            //sendobj.ChangeParameter(34, 12, 0); //Job Deactivated
            if (msg_for_can != null && !msg_for_can.equals("")) { //send parameter frame to the machine
                if (uartService != null) {
                    uartService.write(msg_for_can.getBytes(iso88591charset));
                    msg_for_can = "";
                    Log.i("Job","Deactivate");
                }
            }
            delayInMilli(200);

            /*for (int i = 0; i < JOB_NUM; i++) {
                Log.i("Job", "++");
                sendobj.ChangeParameter(34, 7); //Job increment

                if (msg_for_can != null && !msg_for_can.equals("")) { //send parameter frame to the machine
                    if (uartService != null) uartService.write(msg_for_can.getBytes(iso88591charset));
                    msg_for_can = "";
                }
                /*
                delayInMilli(200);
            }
            delayInMilli(200);
        *//*else if(JOB_COUNT==2){
            Log.i("Job","++");
            sendobj.ChangeParameter(34, 7); //DatenObjekteSend class

            if (msg_for_can != null && !msg_for_can.equals("")) { //send parameter frame to the machine
                if (uartService != null) {
                    uartService.write(msg_for_can.getBytes(iso88591charset));
                }
                msg_for_can = "";
            }
            delayInMilli(50); //delay 0.05s
            if (msg_for_can1 != null && !msg_for_can1.equals("")) { //send value frame to the machine
                if (uartService != null) {
                    uartService.write(msg_for_can1.getBytes(iso88591charset));
                }
                msg_for_can1 = "";
            }
            JOB_COUNT++;
        }else if(JOB_COUNT==3){
            Log.i("Job","++");
            sendobj.ChangeParameter(34, 7); //DatenObjekteSend class

            if (msg_for_can != null && !msg_for_can.equals("")) { //send parameter frame to the machine
                if (uartService != null) {
                    uartService.write(msg_for_can.getBytes(iso88591charset));
                }
                msg_for_can = "";
            }
            delayInMilli(50); //delay 0.05s
            if (msg_for_can1 != null && !msg_for_can1.equals("")) { //send value frame to the machine
                if (uartService != null) {
                    uartService.write(msg_for_can1.getBytes(iso88591charset));
                }
                msg_for_can1 = "";
            }
            JOB_COUNT++;
        }else if(JOB_COUNT==4){
            Log.i("Job","++");
            sendobj.ChangeParameter(34, 7); //DatenObjekteSend class

            if (msg_for_can != null && !msg_for_can.equals("")) { //send parameter frame to the machine
                if (uartService != null) {
                    uartService.write(msg_for_can.getBytes(iso88591charset));
                }
                msg_for_can = "";
            }
            delayInMilli(50); //delay 0.05s
            if (msg_for_can1 != null && !msg_for_can1.equals("")) { //send value frame to the machine
                if (uartService != null) {
                    uartService.write(msg_for_can1.getBytes(iso88591charset));
                }
                msg_for_can1 = "";
            }
            JOB_COUNT++;
        }else if(JOB_COUNT==5){
            Log.i("Job","++");
            sendobj.ChangeParameter(34, 7); //DatenObjekteSend class

            if (msg_for_can != null && !msg_for_can.equals("")) { //send parameter frame to the machine
                if (uartService != null) {
                    uartService.write(msg_for_can.getBytes(iso88591charset));
                }
                msg_for_can = "";
            }
            delayInMilli(50); //delay 0.05s
            if (msg_for_can1 != null && !msg_for_can1.equals("")) { //send value frame to the machine
                if (uartService != null) {
                    uartService.write(msg_for_can1.getBytes(iso88591charset));
                }
                msg_for_can1 = "";
            }
            JOB_COUNT++;
        }else if(JOB_COUNT==6){
            Log.i("Job","++");
            sendobj.ChangeParameter(34, 7); //DatenObjekteSend class

            if (msg_for_can != null && !msg_for_can.equals("")) { //send parameter frame to the machine
                if (uartService != null) {
                    uartService.write(msg_for_can.getBytes(iso88591charset));
                }
                msg_for_can = "";
            }
            delayInMilli(50); //delay 0.05s
            if (msg_for_can1 != null && !msg_for_can1.equals("")) { //send value frame to the machine
                if (uartService != null) {
                    uartService.write(msg_for_can1.getBytes(iso88591charset));
                }
                msg_for_can1 = "";
            }
            JOB_COUNT++;
        }else if(JOB_COUNT==7){
            Log.i("Job","++");
            sendobj.ChangeParameter(34, 7); //DatenObjekteSend class

            if (msg_for_can != null && !msg_for_can.equals("")) { //send parameter frame to the machine
                if (uartService != null) {
                    uartService.write(msg_for_can.getBytes(iso88591charset));
                }
                msg_for_can = "";
            }
            delayInMilli(50); //delay 0.05s
            if (msg_for_can1 != null && !msg_for_can1.equals("")) { //send value frame to the machine
                if (uartService != null) {
                    uartService.write(msg_for_can1.getBytes(iso88591charset));
                }
                msg_for_can1 = "";
            }
            JOB_COUNT++;
        }else if(JOB_COUNT==8){
            Log.i("Job","++");
            sendobj.ChangeParameter(34, 7); //DatenObjekteSend class

            if (msg_for_can != null && !msg_for_can.equals("")) { //send parameter frame to the machine
                if (uartService != null) {
                    uartService.write(msg_for_can.getBytes(iso88591charset));
                }
                msg_for_can = "";
            }
            delayInMilli(50); //delay 0.05s
            if (msg_for_can1 != null && !msg_for_can1.equals("")) { //send value frame to the machine
                if (uartService != null) {
                    uartService.write(msg_for_can1.getBytes(iso88591charset));
                }
                msg_for_can1 = "";
            }
            JOB_COUNT++;*//*
        //}else if(JOB_COUNT == 2){
            Log.i("Job","Chosen");
            sendobj.ChangeParameter(34, 13); //DatenObjekteSend class

            if (msg_for_can != null && !msg_for_can.equals("")) { //send parameter frame to the machine
                if (uartService != null) uartService.write(msg_for_can.getBytes(iso88591charset));
                msg_for_can = "";
            }
            delayInMilli(200);
        }

 */
     private void runThread(){
         new Thread(new Runnable() {
             @Override
             public void run() {
                 while(true) {
                     handler.post(new Runnable() {
                         @Override
                         public void run() {
                             if (VERFAHREN_TOKEN) {
                                 //Log.i(TAG, "VERFAHREN_TOKEN is true.");
                                 kennlinie.performClick();
                                 //VERFAHREN_TOKEN = false;
                             }//else Log.i(TAG, "VERFAHREN_TOKEN is false.");
                         }
                     });
                 }
             }
         }).start();
     }

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

     private void progressbar_thread(){
         new Thread(new Runnable() {
             @Override
             public void run() {
                 while (true) {
                     //Log.i("Energie",String.valueOf(DatenObjekte.Energie1));
                     //Log.i("VERFAHREN MODE ",String.valueOf(VERFAHREN_MODE));
                     //Log.i("Elektrode strom",String.valueOf(DatenObjekte.ElektrodeStromSetwert));
                     if (DatenObjekte.SV1pos1 == 1) VERFAHREN_MODE = 2; //Normal
                     else if (DatenObjekte.SV1pos1 == 2) VERFAHREN_MODE = 3; //Synergie
                     else if (DatenObjekte.SV1pos1 == 3) VERFAHREN_MODE = 4; //Puls
                     else if (DatenObjekte.SV1pos1 == 4) VERFAHREN_MODE = 5; //Elektrode

                     int PROGRESSBARSTATUS;

                     //Verfahren Normal mode

                     float value;

                     //PROGRESSBARSTATUS = Energie - 20;

                     if (VERFAHREN_MODE == 4) { //Puls mode
                         PROGRESSBARSTATUS = DatenObjekte.mpm_display - 20;
                         progressBar.setProgress(PROGRESSBARSTATUS);
                     } else if (VERFAHREN_MODE == 2) { // Normal mode
                         value = (DatenObjekte.mpm_display ) * 100 / 232 - 800 / 232;
                         //Log.i("PROGRESSBARSTATUS is",String.valueOf(value));
                         progressBar.setProgress((int) value);
                     } else if (VERFAHREN_MODE == 3) { //Synergie mode
                         value = (DatenObjekte.mpm_display ) * 100 / 80 - 50;
                         //Log.i("PROGRESSBARSTATUS is",String.valueOf(value));
                         progressBar.setProgress((int) value);
                     } else if (VERFAHREN_MODE == 5) { //Elektrode mode
                         value = (DatenObjekte.ElektrodeStromSetwert) / 2 - 5 / 2;
                         progressBar.setProgress((int) value);
                         //progressBar.setProgress(0);
                     }

                     try {
                         Thread.sleep(10);
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }
                 }
             }
         }).start();
     }

     private void button_thread(){
         new Thread(new Runnable() {
             @Override
             public void run() {
                 while (true) {
                     handler.post(new Runnable() {
                         @Override
                         public void run() {

                             if (TEST_TOKEN) {
                                 Log.i(TAG, "TEST_TOKEN is true.");
                                 JOB_NUM.performClick();
                                 TEST_TOKEN = false;
                             }

                             if ((HOME_TOKEN) && (MA_TOKEN)){
                                 Log.i(TAG, "HOME_TOKEN is true.");
                                 backHome.performClick();
                                 HOME_TOKEN = false;
                             }

                             if (DROSSEL_TOKEN) {
                                 Log.i(TAG, "DROSSEL_TOKEN is true.");
                                 droessel.performClick();
                                 DROSSEL_TOKEN = false;
                             }

                             if (DATEN_TOKEN) {
                                 Log.i(TAG, "DATEN_TOKEN is true.");
                                 //data.performClick();
                                 DATEN_TOKEN = false;
                             }

                             if (VERFAHREN_TOKEN) {
                                 Log.i(TAG, "VERFAHREN_TOKEN is true.");
                                 button_menu.performClick();
                                 VERFAHREN_TOKEN = false;
                             }

                             if (KENNLINIE_TOKEN) {
                                 Log.i(TAG, "KENNLINIE_TOKEN is true.");
                                 kennlinie.performClick();
                                 KENNLINIE_TOKEN = false;
                             }

                             if (BETRIEBSART_TOKEN) {
                                 Log.i(TAG, "BETRIEBSART_TOKEN is true.");
                                 betribsart.performClick();
                                 BETRIEBSART_TOKEN = false;
                             }

                             if (MENU_TOKEN) {
                                 Log.i(TAG, "MENU_TOKEN is true.");
                                 Setting.performClick();
                                 MENU_TOKEN = false;
                             }
                         }
                     });
                     try {
                         Thread.sleep(10);
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }
                 }
             }
         }).start();
     }

     private void date_thread(){
         Thread t = new Thread() {
             @Override
             public void run() {
                 try {
                     while (!isInterrupted()) {
                         Thread.sleep(1000);
                         runOnUiThread(new Runnable() {
                             @Override
                             public void run() {
                                 TextView tdate = findViewById(R.id.date); //right top button
                                 TextView tdate2 = findViewById(R.id.date2); //left top button

                                 long date = System.currentTimeMillis();

                                 SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
                                 String dateString = sdf.format(date);
                                 SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
                                 String dateString2 = sdf2.format(date);
                                 tdate.setText(String.valueOf(DatenObjekte.HOUR)+":"+String.valueOf(DatenObjekte.MINUTE)+":"+String.valueOf(DatenObjekte.SECOND));
                                 tdate2.setText(String.valueOf(DatenObjekte.DAY)+"/"+String.valueOf(DatenObjekte.MONTH)+"/"+"20"+String.valueOf(DatenObjekte.YEAR));

                             }
                         });
                     }
                 } catch (InterruptedException e) {
                 }
             }
         };
         t.start();
     }

    private void MMNormal_btn_onclick(){
        Log.i("Normal","called");
        VERFAHREN_MODE = 2;
        DatenObjekteSend SendMMNormal = new DatenObjekteSend();
        if(DatenObjekte.SV1pos1 == 4) { // Elektrode
            SendMMNormal.ChangeParameter(28,5,0);
        }else if(DatenObjekte.SV1pos1 == 3){ //Puls
            SendMMNormal.ChangeParameter(28,5,0);
            delayInMilli(100);
            SendMMNormal.ChangeParameter(28,5,0);
        }else if(DatenObjekte.SV1pos1 == 2){ //Synergie
            SendMMNormal.ChangeParameter(28,5,0);
            delayInMilli(100);
            SendMMNormal.ChangeParameter(28,5,0);
            delayInMilli(100);
            SendMMNormal.ChangeParameter(28,5,0);
        }else Log.i("Verfahren mode ","MMNormal");

        if(!verfahren_gedrückt) {
            MMNormal_btn.setTextColor(Color.GREEN);
            verfahren_gedrückt=true;
        }else if(verfahren_gedrückt){
            MMNormal_btn.setTextColor(Color.WHITE);
            verfahren_gedrückt=false;
        }
    }

    MainActivity_Controller mainActivityController = new MainActivity_Controller(MainActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        READVAL_STATUS[1]=0;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        variablen_init();
        progrssinit();
        setVisibility();
        serial_init();
        /*MENU_JOBS.setBackgroundColor(Color.BLACK);
        MENU_JOBS.setTextColor(Color.WHITE);
*/
        mHandler = new UartService.MyHandler();
        Setting= findViewById(R.id.setting);
        Setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view ) {

                SETTING_TOKEN = true;
                mainActivityController.onClick_newActivity(com.jess.gdfp.View.Setting.class);
                MA_TOKEN = false;

            }
        });

        testbtn = findViewById(R.id.testbutton);
        testbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //testbtn_onclick();
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

        //progressbar_thread();
        button_thread();
        date_thread();

        betribsart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allgemeinOnclick(betriebsart_fragement);
                ausblinden(frame,kenn_fragment);
            }
        });

        //Verfahren button
        WIG_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VERFAHREN_MODE = 1;
                if(!verfahren_gedrückt) {
                    WIG_btn.setTextColor(Color.GREEN);
                    verfahren_gedrückt=true;
                }else if(verfahren_gedrückt){
                    WIG_btn.setTextColor(Color.WHITE);
                    verfahren_gedrückt=false;
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
                MMNormal_btn_onclick();
            }
        });

        MMSynergy_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MMNormal_btn.setTextColor(Color.WHITE);
                MMSynergy_btn.setTextColor(Color.GREEN);
                MMPuls_btn.setTextColor(Color.WHITE);
                ElectrodeMMA_btn.setTextColor(Color.WHITE);
                MMSynergy_btn_onclick();
            }
        });

        MMPuls_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MMNormal_btn.setTextColor(Color.WHITE);
                MMSynergy_btn.setTextColor(Color.WHITE);
                MMPuls_btn.setTextColor(Color.GREEN);
                ElectrodeMMA_btn.setTextColor(Color.WHITE);
                MMPuls_btn_onclick();
            }
        });

        ElectrodeMMA_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MMNormal_btn.setTextColor(Color.WHITE);
                MMSynergy_btn.setTextColor(Color.WHITE);
                MMPuls_btn.setTextColor(Color.WHITE);
                ElectrodeMMA_btn.setTextColor(Color.GREEN);
                ElektrodeMMA_btn_onclick();
            }
        });

        WIGPulsen_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VERFAHREN_MODE = 6;
                if(!verfahren_gedrückt) {
                    WIGPulsen_btn.setTextColor(Color.GREEN);
                    verfahren_gedrückt=true;
                }else if(verfahren_gedrückt){
                    WIGPulsen_btn.setTextColor(Color.WHITE);
                    verfahren_gedrückt=false;
                }
            }
        });

        WIGSpeed_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VERFAHREN_MODE = 7;
                if(!verfahren_gedrückt) {
                    WIGSpeed_btn.setTextColor(Color.GREEN);
                    verfahren_gedrückt=true;
                }else if(verfahren_gedrückt){
                    WIGSpeed_btn.setTextColor(Color.WHITE);
                    verfahren_gedrückt=false;
                }
            }
        });

        WIGSpeedPulsen_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VERFAHREN_MODE = 8;
                if(!verfahren_gedrückt) {
                    WIGSpeedPulsen_btn.setTextColor(Color.GREEN);
                    verfahren_gedrückt=true;
                }else if(verfahren_gedrückt){
                    WIGSpeedPulsen_btn.setTextColor(Color.WHITE);
                    verfahren_gedrückt=false;
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
                    /*WriteToSerial("5");
                    try {
                        UartService.mSerialPort = new SerialPort(new File("/dev/ttyS4"), 500000, 0); //Open the serial port //500000
                        mOutputStream = UartService.mSerialPort.getOutputStream();
                        UartService.mInputStream = UartService.mSerialPort.getInputStream();
                    } catch (SecurityException e) {
                        Log.e(TAG,"SecurityException");
                        //DisplayError(R.string.error_security);
                    } catch (IOException e) {
                        //DisplayError(R.string.error_unknown);
                        Log.e(TAG,"IOException");
                    } catch (InvalidParameterException e) {
                        //DisplayError(R.string.error_configuration);
                        Log.e(TAG,"InvalidParameterException");
                    }*/
                    KENN_HANDLER.post(KENN_TIMER); //start the timer handler
                    //kennlinie.setTextColor(Color.GREEN);
                    kennlinie.setBackgroundColor(Color.GRAY);
                    kennlinie_gedrückt = true;
                    STOP_DATENOBJEKTE = true;
                } else {
                    PARSE_TOKEN = true;
                    /*WriteToSerial("5");
                    try {
                        UartService.mSerialPort = new SerialPort(new File("/dev/ttyS4"), 2000000, 0); //Open the serial port //500000
                        mOutputStream = UartService.mSerialPort.getOutputStream();
                        UartService.mInputStream = UartService.mSerialPort.getInputStream();
                    } catch (SecurityException e) {
                        Log.e(TAG,"SecurityException");
                        //DisplayError(R.string.error_security);
                    } catch (IOException e) {
                        //DisplayError(R.string.error_unknown);
                        Log.e(TAG,"IOException");
                    } catch (InvalidParameterException e) {
                        //DisplayError(R.string.error_configuration);
                        Log.e(TAG,"InvalidParameterException");
                    }*/
                    //kennlinie.setTextColor(Color.WHITE);
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
                //JOB_NUM_TOKEN = true;
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
                //JOB_NUM_TOKEN = false;
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
    private final ServiceConnection usbConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName arg0, IBinder arg1) {
            uartService = ((UartService.UsbBinder) arg1).getService();
            uartService.setHandler(mHandler);
            uartService.setHandler1(newHandler);
        }
        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            uartService = null;
        }
    };

    public static void setVerfahren(String s){
        //Log.i("Verfahren",String.valueOf(DatenObjekte.Verfahren));
        switch (s){
            case "WIG SPEED":
                //System.out.println("WIG SPEED");
                VERFAHREN = 7;
                break;

            case"WIG PULSEN":
                //System.out.println("WIG PULSEN");
                VERFAHREN = 6;
                break;

            case "WIG":
                //System.out.println("WIG");
                VERFAHREN = 5;
                break;

            case"ElECTRODE":
                //System.out.println("ELECTRODE");
                VERFAHREN = 4;
                /*VERFAHREN_MODE = 5;
                if(DatenObjekte.SV1pos1 == 3){ // Puls
                    //callChangeParameter(1,30,30,0);
                }else if(DatenObjekte.SV1pos1 == 2){ // Synergie
                    //callChangeParameter(2,30,30,0);
                }else if(DatenObjekte.SV1pos1 == 1){ // Normal
                    //callChangeParameter(3,30,30,0);
                }else Log.i("Verfahren mode ","ElektrodeMMA");*/
                break;

            case"PLUS":
                //System.out.println("PULS");
                VERFAHREN = 3;
                /*VERFAHREN_MODE = 4;
                if(DatenObjekte.SV1pos1 == 4){ // Elektrode
                    //callChangeParameter(3,29,5,0);
                }else if(DatenObjekte.SV1pos1 == 2){ // Synergie
                    //callChangeParameter(1,29,5,0);
                }else if(DatenObjekte.SV1pos1 == 1){ // Normal
                    //callChangeParameter(2,29,5,0);
                }else Log.i("Verfahren mode ","MMPuls");*/
                break;

            case"MIG/MAG synregy":
                //System.out.println("MIG/MAG SYNERGY");
                VERFAHREN = 2;
                /*if(DatenObjekte.SV1pos1 == 4) { // Elektrode
                    //callChangeParameter(2,25,10,0);
                }else if(DatenObjekte.SV1pos1 == 3){ //Puls
                    ///callChangeParameter(3,25,10,0);
                }else if(DatenObjekte.SV1pos1 == 1){ //Normal
                    //callChangeParameter(1,25,10,0);
                }else Log.i("Verfahren mode ","MMSynergy");*/
                break;

            case"MIG/MAG Normal":
                //System.out.println("MIG/MAG NORMAL");
                VERFAHREN = 1;
                /*VERFAHREN_MODE = 2;
                if(DatenObjekte.SV1pos1 == 4) { // Elektrode
                    //callChangeParameter(1,28,5,0);
                }else if(DatenObjekte.SV1pos1 == 3){ //Puls
                    //callChangeParameter(2,29,5,0);
                }else if(DatenObjekte.SV1pos1 == 2){ //Synergie
                    //callChangeParameter(3,29,5,0);
                }else Log.i("Verfahren mode ","MMNormal");*/
                break;
        }
    }

    public static void setMaterial(String s){
        switch (s){
            case "Al/mg4/5Mn": //case 8
                WERKSTOFF = 8;
                //System.out.println("Al/mg4/5Mn");
                break;
            case"Al/mg5": //case 7
                WERKSTOFF = 7;
                //System.out.println("Al/mg5");
                break;
            case "Al/mg3": //case 6
                WERKSTOFF = 6;
                //System.out.println("Al/mg3");
                break;
            case"Cu/Si": //case 5
                WERKSTOFF = 5;
                //System.out.println("Cu/Si");
                break;
            case"AL/Si": //case 4
                WERKSTOFF = 4;
                //System.out.println("AL/Si");
                //callChangeParameter(1,12,0,1);
                break;
            case"AL/Mg":
                WERKSTOFF = 3;
                //System.out.println("AL/Mg");
                //callChangeParameter(1,11,0,1);
                break;
            case"Cr/Ni":
                WERKSTOFF = 2;
                //System.out.println("Cr/Ni");
                //callChangeParameter(1,10,0,1);
                break;
            case"Fe":
                WERKSTOFF = 1;
                //System.out.println("Fe");
                //callChangeParameter(1,14,0,1);
                break;
            case"Al/Bz": // case 9
                WERKSTOFF = 9;
                //System.out.println("Al/Bz");
                break;
            case"Spezial":
                WERKSTOFF = 10;
                //System.out.println("Spezial");
                //callChangeParameter(1,13,0,1);
                break;
        }
    }

    public static void setDrahtDM(String s){ //mm
        switch (s){
            case "0.8":
                DRAHTDURCHMESSER = 0;
                break;
            case"0.9":
                DRAHTDURCHMESSER = 1;
                break;
            case "1.0":
                DRAHTDURCHMESSER = 2;
                break;
            case"1.1":
                DRAHTDURCHMESSER = 3;
                break;
            case"1.2":
                DRAHTDURCHMESSER = 4;
                //callChangeParameter(1,12,0,1);
                break;
            case"1.3":
                DRAHTDURCHMESSER = 5;
                //callChangeParameter(1,11,0,1);
                break;
            case"1.4":
                DRAHTDURCHMESSER = 6;
                //callChangeParameter(1,10,0,1);
                break;
            case"1.5":
                DRAHTDURCHMESSER = 7;
                //callChangeParameter(1,14,0,1);
                break;
            case"1.6":
                DRAHTDURCHMESSER = 8;
                break;
            case"1.7":
                DRAHTDURCHMESSER = 9;
                break;
            case"1.8":
                DRAHTDURCHMESSER = 10;
                break;
            case"1.9":
                DRAHTDURCHMESSER = 11;
                break;
            case"2.0":
                DRAHTDURCHMESSER = 12;
                break;
            case"2.1":
                DRAHTDURCHMESSER = 13;
                break;
            case"2.2":
                DRAHTDURCHMESSER = 14;
                break;
            case"2.3":
                DRAHTDURCHMESSER = 15;
                break;
            case"2.4":
                DRAHTDURCHMESSER = 16;
                break;
            case"2.5":
                DRAHTDURCHMESSER = 17;
                break;
            case"2.6":
                DRAHTDURCHMESSER = 18;
                break;
            case"2.7":
                DRAHTDURCHMESSER = 19;
                break;
            case"2.8":
                DRAHTDURCHMESSER = 20;
                break;
            case"2.9":
                DRAHTDURCHMESSER = 21;
                break;
            case"3.0":
                DRAHTDURCHMESSER = 22;
                break;
            case"3.1":
                DRAHTDURCHMESSER = 23;
                break;
            case"3.2":
                DRAHTDURCHMESSER = 24;
                break;
            case"3.3":
                DRAHTDURCHMESSER = 25;
                break;
            case"3.4":
                DRAHTDURCHMESSER = 26;
                break;
            case"3.5":
                break;
            case"3.6":
                break;
            case"3.7":
                break;
            case"3.8":
                break;
            case"3.9":
                break;
            case"4.0":
                break;
            case"4.1":
                break;
            case"4.2":
                break;
            case"4.3":
                break;
            case"4.4":
                break;
            case"4.5":
                break;
            case"4.6":
                break;
            case"4.7":
                break;
            case"4.8":
                break;
            case"4.9":
                break;
            case"5.0":
                break;
            case"5.1":
                break;
            case"5.2":
                break;
            case"5.3":
                break;
            case"5.4":
                break;
            case"5.5":
                break;
            case"5.6":
                break;
            case"5.7":
                break;
            case"5.8":
                break;
            case"5.9":
                break;
            case"6.0":
                break;
            case"6.1":
                break;
            case"6.2":
                break;
            case"6.3":
                break;
            case"6.4":
                break;
            case"6.5":
                break;
            case"6.6":
                break;
            case"6.7":
                break;
            case"6.8":
                break;
            case"6.9":
                break;
            case"7.0":
                break;
            case"7.1":
                break;
            case"7.2":
                break;
            case"7.3":
                break;
            case"7.4":
                break;
            case"7.5":
                break;
            case"7.6":
                break;
            case"7.7":
                break;
            case"7.8":
                break;
            case"7.9":
                break;
            case"8.0":
                break;
            case"8.1":
                break;
            case"8.2":
                break;
            case"8.3":
                break;
            case"8.4":
                break;
            case"8.5":
                break;
            case"8.6":
                break;
            case"8.7":
                break;
            case"8.8":
                break;
            case"8.9":
                break;
            case"9.0":
                break;
            case"9.1":
                break;
            case"9.2":
                break;
            case"9.3":
                break;
            case"9.4":
                break;
            case"9.5":
                break;
            case"9.6":
                break;
            case"9.7":
                break;
            case"9.8":
                break;
            case"9.9":
                break;
            case"10.0":
                break;
            case"10.1":
                break;
            case"10.2":
                break;
            case"10.3":
                break;
            case"10.4":
                break;
            case"10.5":
                break;
            case"10.6":
                break;
            case"10.7":
                break;
            case"10.8":
                break;
            case"10.9":
                break;
            case"11.0":
                break;
            case"11.1":
                break;
            case"11.2":
                break;
            case"11.3":
                break;
            case"11.4":
                break;
            case"11.5":
                break;
            case"11.6":
                break;
            case"11.7":
                break;
            case"11.8":
                break;
            case"11.9":
                break;
            case"12.0":
                break;
            case"12.1":
                break;
            case"12.2":
                break;
            case"12.3":
                break;
            case"12.4":
                break;
            case"12.5":
                break;
            case"12.6":
                break;
            case"12.7":
                break;
            case"12.8":
                break;
            case"12.9":
                break;
            case"13.0":
                break;
            case"13.1":
                break;
            case"13.2":
                break;
            case"13.3":
                break;
            case"13.4":
                break;
            case"13.5":
                break;
            case"13.6":
                break;
            case"13.7":
                break;
            case"13.8":
                break;
            case"13.9":
                break;
            case"14.0":
                break;
            case"14.1":
                break;
            case"14.2":
                break;
            case"14.3":
                break;
            case"14.4":
                break;
            case"14.5":
                break;
            case"14.6":
                break;
            case"14.7":
                break;
            case"14.8":
                break;
            case"14.9":
                break;
            case"15.0":
                break;
            case"15.1":
                break;
            case"15.2":
                break;
            case"15.3":
                break;
            case"15.4":
                break;
            case"15.5":
                break;
            case"15.6":
                break;
            case"15.7":
                break;
            case"15.8":
                break;
            case"15.9":
                break;
            case"16.0":
                break;
            case"Spezial":
                System.out.println("Spezial");
                break;
        }
    }

    public static void setGas(String s){
        switch (s){
            case "50Ar / 50%H2": //case 16
                GAS = 16;
                //System.out.println("50Ar / 50%H2");
                break;
            case"30Ar / 70%H2": //case 17
                GAS = 17;
                //System.out.println("30Ar / 70%H2");
                break;
            case "82%AR / 18%CO":
                GAS = 0;
                //System.out.println("82%AR / 18%CO");
                break;
            case"98%AR / 2%CO":
                GAS = 1;
                //System.out.println("98%AR / 2%CO");
                break;
            case"100%AR": // case 2
                GAS = 2;
                //System.out.println("100%AR");
                break;
            case"100%CO":
                GAS = 3;
                //System.out.println("100%CO");
                break;
            case"92%AR / 8%CO":
                GAS = 4;
                //System.out.println("92%AR / 8%CO");
                break;
            case"99%AR / 1%O2": //case 5
                GAS = 5;
                //System.out.println("99%AR / 1%O2");
                break;
            case"98%AR / 2%O2": //case 6
                GAS = 6;
                //System.out.println("98%AR / 2%O2");
                break;
            case"97%AR / 3%O2": //case 7
                GAS = 7;
                //System.out.println("97%AR / 3%O2");
                //callChangeParameter(1,11,0,1);
                break;
            case"92%AR / 8%O2": //case 8
                GAS = 8;
                //System.out.println("92%AR / 8%O2");
                break;
            case"90%AR / 5%O2/ 5%CO": //case 9
                GAS = 9;
                //System.out.println("90%AR / 5%O2/ 5%CO");
                break;
            case"100%HE": //case 10
                GAS = 10;
                //System.out.println("100%HE");
                break;
            case"80%AR / 20%HE": //case 11
                GAS = 11;
                //System.out.println("80%AR / 20%HE");
                break;
            case"69%AR/ 30%HE/1%O2": //case 12
                GAS = 12;
                //System.out.println("69%AR/ 30%HE/1%O2");
                break;
            case"50Ar / 50%HE": //case 13
                GAS = 13;
                //System.out.println("50Ar / 50%HE");
                break;
            case"98Ar / 2%H2": //case 14
                GAS = 14;
                //System.out.println("98Ar / 2%H2");
                break;
            case"94Ar / 6%H2": //case 15
                GAS = 15;
                //System.out.println("94Ar / 6%H2");
                break;
            case"Spezial": //case 18
                GAS = 18;
                //System.out.println("Spezial");
                break;
        }
    }

    /**
     * Send to Can multiple time
     * @param iteration
     * @param num number of parameters in DatenObjekteSend
     * @param value value of Value Frame
     * @param mode 0 or 1
     */

    private static void callChangeParameter(int iteration, int num, int value,int mode){
        DatenObjekteSend sendToDatenObjekte = new DatenObjekteSend();
        if(iteration==1){
            sendToDatenObjekte.ChangeParameter(num,value,mode);
        }else if(iteration==2){
            sendToDatenObjekte.ChangeParameter(num,value,mode);
            delayInMilli(100);
            sendToDatenObjekte.ChangeParameter(num,value,mode);
        }else if(iteration==3) {
            sendToDatenObjekte.ChangeParameter(num, value, mode);
            delayInMilli(100);
            sendToDatenObjekte.ChangeParameter(num, value, mode);
            delayInMilli(100);
            sendToDatenObjekte.ChangeParameter(num, value, mode);
        }
    }

    public static void delayInMilli(int DELAY_MILLISEC){
        try {
            Thread.sleep(DELAY_MILLISEC);
        }catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

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

    public void onpressedButton(View view) {
        if (view.getId() == R.id.minus) {
            progressBarMinus = (ProgressBar) findViewById(R.id.progress);
            int y = progressBarMinus.getProgress();
            progressBarMinus.setProgress(y - 4);
        }
        if (view.getId() == R.id.plus) {
            progressBarMinus = (ProgressBar) findViewById(R.id.progress);
            int y = progressBarMinus.getProgress();
            progressBarMinus.setProgress(y + 4);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        /*if(BSF != null) { //disable temporarily
            //BSF = new BluetoothService(this, btHandler);
            //String address = "9C:28:40:1F:A1:8E"; //banana pi mac address
            //btDevice = btAdapter.getRemoteDevice("20:14:04:11:31:66");
            //btDevice = btAdapter.getRemoteDevice(address);
            if (BSF.getState() == BSF.STATE_NONE) {
                BSF.start(btDevice);
                Log.i("onResume","BSF start");
            }
        }*/
        setFilters();  // Start listening notifications from UartService
        UartService UsbObj = new UartService(this);//mine
        UsbObj.startService(UartService.class, usbConnection, null); // Start UartService(if it was not started before) and Bind it//mine
        //startService(UartService.class, usbConnection, null);

        //final Intent  intent = new Intent(this, DatalistView.class);
        //startActivity(intent);
    }

    @Override
    public void onPause() {
        super.onPause();
        unregisterReceiver(mUsbReceiver);
        unbindService(usbConnection);
        this.unregisterReceiver(this.mReceiver); //unregister our receiver
        this.unregisterReceiver(this.mReceiver1); //unregister our receiver
        this.unregisterReceiver(this.mReceiver2); //unregister my receiver
        this.unregisterReceiver(this.mReceiver3); //unregister my receiver
    }

    protected void onStop() {
        super.onStop();
        //Toast.makeText(getApplicationContext(), "onStop called", Toast.LENGTH_LONG).show();
    }

    protected void onDestroy(){ //for bluetooth and bluetooth le
        super.onDestroy();

       /* if(BSF != null) BSF.stop(); //Stop the Bluetooth chat services //disable temporarily

        BluetoothAdapter bluetoothAdapter = mBluetoothLEService.mBluetoothManager.getAdapter();
        if(bluetoothAdapter.isEnabled()){
            mBluetoothLEService.stopServer();
            Log.i(TAG,"mBluetoothLEService.stopServer()");
        }

        unregisterReceiver(mBluetoothReceiver); //BLE*/

        /**
         * For Raspberry Pi
         */
        if (UartService.mReadThread != null) UartService.mReadThread.interrupt();
        //mApplication.closeSerialPort();
        UartService.mSerialPort = null;
    }

    private final BroadcastReceiver mUsbReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case UartService.ACTION_USB_PERMISSION_GRANTED: // USB PERMISSION GRANTED
                    //Toast.makeText(context, "USB Ready", Toast.LENGTH_SHORT).show();
                    break;
                case UartService.ACTION_USB_PERMISSION_NOT_GRANTED: // USB PERMISSION NOT GRANTED
                    //Toast.makeText(context, "USB Permission not granted", Toast.LENGTH_SHORT).show();
                    break;
                case UartService.ACTION_NO_USB: // NO USB CONNECTED
                    //Toast.makeText(context, "No USB connected", Toast.LENGTH_SHORT).show();
                    break;
                case UartService.ACTION_USB_DISCONNECTED: // USB DISCONNECTED
                    //Toast.makeText(context, "USB disconnected", Toast.LENGTH_SHORT).show();
                    break;
                case UartService.ACTION_USB_NOT_SUPPORTED: // USB NOT SUPPORTED
                    //Toast.makeText(context, "USB device not supported", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    private final BroadcastReceiver mBluetoothReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e(TAG," mBluetoothReceiver received");
            int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.STATE_OFF);

            switch (state) {
                case BluetoothAdapter.STATE_ON:
                    Log.i(TAG,"BluetoothAdapter.STATE_ON");
                    //startAdvertising();
                    mBluetoothLEService.startServer();
                    Log.i(TAG,"startServer");
                    break;
                case BluetoothAdapter.STATE_OFF:
                    Log.i(TAG,"BluetoothAdapter.STATE_OFF");
                    mBluetoothLEService.stopServer();
                    Log.i(TAG,"stopServer");
                    //stopAdvertising();
                    break;
                default:
                    // Do nothing
            }
        }
    };

    private static boolean execute(){

        Process process = null;
        DataOutputStream dataOutputStream = null;

        try {
            process = Runtime.getRuntime().exec("su");
            dataOutputStream = new DataOutputStream(process.getOutputStream());
            dataOutputStream.writeBytes("chmod 777 /dev/ttyS4\n");
            dataOutputStream.writeBytes("exit\n");
            dataOutputStream.flush();
            process.waitFor();
            Log.i(TAG,"write chmod");
        } catch (Exception e) {
            return false;
        } finally {
            try {
                if (dataOutputStream != null) {
                    dataOutputStream.close();
                    Log.i(TAG,"dataOutputStream is closed.");
                }
                process.destroy();
            } catch (Exception e) {
            }
        }
        return true;
    }

    private static IntentFilter makeGattUpdateIntentFilter() {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothLEService.ACTION_GATT_CONNECTED);
        intentFilter.addAction(BluetoothLEService.ACTION_GATT_DISCONNECTED);
        intentFilter.addAction(BluetoothLEService.ACTION_GATT_SERVICES_DISCOVERED);
        intentFilter.addAction(BluetoothLEService.ACTION_DATA_AVAILABLE);
        intentFilter.addAction(BluetoothDevice.ACTION_UUID);
        return intentFilter;
    }

    private final BroadcastReceiver mGattUpdateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            if (BluetoothLEService.ACTION_GATT_CONNECTED.equals(action)) {
                //conBLE="001";
                bleConn.setBackgroundColor(Color.YELLOW);
                Log.i("mGattUpdateReceiver","ACTION_GATT_CONNECTED");
                status.setVisibility(View.GONE);
                //bleCom_status = true;
                invalidateOptionsMenu();
            } else if (BluetoothLEService.ACTION_GATT_DISCONNECTED.equals(action)) {
                Log.i("mGattUpdateReceiver","ACTION_GATT_DISCONNECTED");
                //conBLE="000";
                mBluetoothLEService.close();
                //BSF.cancel();
                invalidateOptionsMenu();
                //bleCom_status = false;
                status.setVisibility(View.VISIBLE);
                status.setText("Control Lost");
                bleConn.setBackgroundColor(Color.RED);
                //if (menu_status) menu_out();
                //completeDataGlove = "";
                /*if (!appFinished) {
                    if (btCom_status) BSF.cancel();
                    else connecttoGateway();
                }*/
            } else if(BluetoothLEService.ACTION_GATT_SERVICES_DISCOVERED.equals(action)) {
                Log.i("mGattUpdateReceiver","ACTION_GATT_SERVICES_DISCOVERED");
                // Show all the supported services and characteristics on the user interface.
                invalidateOptionsMenu(); //is used to say Android, that contents of menu have changed, and menu should be redrawn.
            } else if (BluetoothLEService.ACTION_DATA_AVAILABLE.equals(action)) { //reads data from client's device
                Log.i("mGattUpdateReceiver","ACTION_DATA_AVAILABLE");
                bleConn.setBackgroundColor(Color.MAGENTA);
                String data = intent.getStringExtra(BluetoothLEService.EXTRA_DATA);
                if (data != null) {
                    status.setVisibility(View.VISIBLE);
                    status.setText(data);
                    Log.i("status",data);
                    //buffParsing(data, 0);
                }
            }
        }
    };

    private void setFilters() { //call in onResume()
        IntentFilter filter = new IntentFilter();
        filter.addAction(UartService.ACTION_USB_PERMISSION_GRANTED);
        filter.addAction(UartService.ACTION_NO_USB);
        filter.addAction(UartService.ACTION_USB_DISCONNECTED);
        filter.addAction(UartService.ACTION_USB_NOT_SUPPORTED);
        filter.addAction(UartService.ACTION_USB_PERMISSION_NOT_GRANTED);
        registerReceiver(mUsbReceiver, filter);

        IntentFilter intfilter = new IntentFilter("Main.Activity");

        mReceiver =  new BroadcastReceiver() { //my code
            @Override
            public void onReceive(Context context, Intent intent) {
                msg_for_me = intent.getStringExtra("msg_service");//extract our message from intent
            }
        };
        this.registerReceiver(mReceiver, intfilter);//registering our receiver //my code

        IntentFilter intfilter1 = new IntentFilter("Main.Activity1"); // my code
        mReceiver1 = new BroadcastReceiver() { //my code
            @Override
            public void onReceive(Context context, Intent intent) {
                msg_for_me1 = intent.getStringExtra("msg_service1");//extract our message from intent
            }
        };
        this.registerReceiver(mReceiver1, intfilter1);//registering our receiver //my code

        IntentFilter intfilter2 = new IntentFilter("Main.Activity2"); // my code
        mReceiver2 = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                msg_for_can = intent.getStringExtra("change_param");//extract modified hex data from intent
            }
        };
        this.registerReceiver(mReceiver2,intfilter2);//registering our receiver

        IntentFilter intfilter3 = new IntentFilter("Main.Activity3");
        mReceiver3 = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                msg_for_can1 = intent.getStringExtra("change_param1");
            }
        };
        this.registerReceiver(mReceiver3,intfilter3);//registering our receiver
    }

    /*private void sendMessage(String message) {

        // Check that we're actually connected before trying anything
        if (BSF.getState() != BluetoothService.STATE_CONNECTED) {
            Toast.makeText(this, R.string.not_connected, Toast.LENGTH_SHORT).show();
            return;
        }
        // Check that there's actually something to send
        if (message.length() > 0) {
            // Get the message bytes and tell the BluetoothChatService to write
            byte[] send = message.getBytes();
            BSF.write(send);
            // Reset out string buffer to zero and clear the edit text field
            mOutStringBuffer.setLength(0);
            mOutEditText.setText(mOutStringBuffer);
        }
    }*/

    @SuppressLint("HandlerLeak")
    private final Handler btHandler = new Handler() { //A handler is a method which is specialised in a certain type of data that focusing on a certain task. Helps to perform a specific task based on an event.
        @SuppressLint("HandlerLeak")
        @Override
        public void handleMessage(Message msg) {
            //super.handleMessage(msg);
            switch (msg.what) {
                case BTConstants.MESSAGE_STATE_CHANGE:
                    switch (msg.arg1) {
                        case BluetoothService.STATE_CONNECTED:
                            //conBT="001";
                            //indicator();
                            btCom_status = true;
                            btConn.setBackgroundColor(Color.YELLOW); //For Adapter ONLY
                            //status.setText("Machine Connected");
                            status.setVisibility(View.GONE);
                            //if ((!bleCom_status) && (RUN)) connecttoKeypad();                         //For Adapter ONLY
                            break;
                        case BluetoothService.STATE_CONNECTING:
                            //Toast.makeText(MainActivity.this, "Gateway Connecting", Toast.LENGTH_SHORT).show();
                            //status.setVisibility(View.GONE);
                            //status.setText("Machine Connecting...");  //For Adapter Only
                            //status.setText("Connecting...");   //For Display Only
                            break;
                        case BluetoothService.STATE_LISTEN:
                        case BluetoothService.STATE_NONE:
                            break;
                        case BluetoothService.STATE_LOST:
                            btCom_status = false;
                            btConn.setBackgroundColor(Color.RED);
                            break;
                    }
                    break;
                case BTConstants.MESSAGE_WRITE:
                    //btConn.setBackgroundColor(Color.GREEN); //For Adapter ONLY
                    byte[] writeBuf = (byte[]) msg.obj;
                    String writeMessage = new String(writeBuf);
                    //BSF.write(writeBuf); //write to paired devices
                    Log.i("MESSAGE_WRITE",writeMessage);
                    //status.setText(writeMessage); //display data from bluetooth
                    //status.setVisibility(View.VISIBLE);
                    break;
                case BTConstants.MESSAGE_READ: //receive from ConnectedThread class
                    btConn.setBackgroundColor(Color.BLUE); //For Adapter ONLY
                    //if ((btCom_status) && (bleCom_status)) {
                    byte[] readBuf = (byte[]) msg.obj;
                    String readRX = new String(readBuf, 0, msg.arg1);
                    if(readRX.equals("changeParam")){
                        Log.i("readRX","is changeParam");
                        //change parameters of the machine
                        //DatenObjekteSend sendobj = new DatenObjekteSend();
                        //sendobj.ChangeParameter(rannum, 11, 0); //AlMg
                        Log.i("rannum is",String.valueOf(rannum));
                        rannum++;
                        if(rannum==14){
                            rannum=11;
                        }
                        if (msg_for_can1 != null && !msg_for_can1.equals("")) { //send parameter frame to the machine
                            if (uartService != null) {
                                uartService.write(msg_for_can1.getBytes(iso88591charset));
                                msg_for_can1 = "";
                                Log.i("Change to", "AlMg");
                            }
                        }
                    }
                    status.setText(readRX); //display data from bluetooth
                    status.setVisibility(View.VISIBLE);
                    //if (!configured_status) buffParsing(readRX, 2);
                    //else buffParsing(readRX, 1);
                    //buffParsing(readRX, 1);
                    //}
                    break;
                case BTConstants.MESSAGE_DEVICE_NAME: //from BluetoothService connected().
                    //bt_name = msg.getData().getString(Constants.DEVICE_NAME);
                    break;
                case BTConstants.MESSAGE_TOAST:
                    // status.setText(msg.getData().getString(Constants.TOAST));
                    break;
            }
        }
    };

    /**
     * Start timer to display data
     */

    private Runnable TimerHandler = new Runnable() {
        @Override
        public void run() {
            UartService.token = false;
            //setFilters();  // Start listening notifications from UartService
            counterDisplay++;
            if (READVAL_STATUS[1]==1) {
                switch(HOME_COUNTER){
                    case 0: txtprogress.setText(String.valueOf(mm_a_display / 10) + "," + String.valueOf(mm_a_display  % 10) + "\n" + "m/min"); // m/min
                        break;
                    case 1: txtprogress.setText(String.valueOf(DatenObjekte.SV1pos3/10)+ "," + String.valueOf(DatenObjekte.SV1pos3 % 10) + "\n" + "mm"); //mm
                        break;
                    case 2: txtprogress.setText(String.valueOf(DatenObjekte.StromSetwert)+"\n"+" A"); //A
                        break;
                    case 3: txtprogress.setText(String.valueOf(DatenObjekte.Lichtbogenkorrektur1)+"\n"+"Korrektur"); //Korrektur
                        break;
                    case 4: txtprogress.setText(DatenObjekte.Verfahren); //Verfahren
                        break;
                    case 5: txtprogress.setText(DatenObjekte.Gas); //Gas
                        break;
                    case 6: txtprogress.setText(DatenObjekte.Werkstoff+"\n"+"Werkstoff"); //Werkstoff
                        break;
                    case 7: txtprogress.setText(String.valueOf(DatenObjekte.Jobnummer)+"\n"+"Job"); //Job
                        break;
                }

                //txtprogress.setText(String.valueOf(DatenObjekte.mpm_display/10) + "," + String.valueOf(DatenObjekte.mpm_display%10)+"\n"+"m/min"); // m/min
            }

                if (msg_for_me != null && !msg_for_me.equals("")) { //data in hex string

                if (check) {
                    //txtProgress.setText(String.valueOf(Drahtdurchmesser));
                    //if(DatenObjekte.SV1pos1 != 4) txtProgress.setText(String.valueOf(DatenObjekte.Energie1/10) + "," + String.valueOf(DatenObjekte.Energie1%10)+"\n"+"m/min"); // m/min
                   // if(DatenObjekte.SV1pos1 != 4) txtProgress.setText(String.valueOf(DatenObjekte.mpm_display/10) + "," + String.valueOf(DatenObjekte.mpm_display%10)+"\n"+"m/min"); // m/min
                    //else txtProgress.setText(String.valueOf(DatenObjekte.ElektrodeStromSetwert+" A")); // Elektrode mode

                    //if (!PARSE_TOKEN) { //if token is false
                        //Validate CAN ID 0x6F0
                        //int tempCANID=UartService.ByteArray[4];
                        //if (UartService.ByteArray[4]<0) tempCANID=UartService.ByteArray[4]+256;

                        //if((UartService.ByteArray[3]==6) && (tempCANID== 240) && (UartService.ByteArray[9]==5)) { //CanID 06F0
                            ANZEIGE1.setText(DatenObjekte.Verfahren);
                            //Log.i("Verfahren", String.valueOf(tempDO));
                            ANZEIGE2.setText(DatenObjekte.Werkstoff);
                            ANZEIGE3.setText(DatenObjekte.Gas);
                        //}
                    //}

                    //---------------------------Display job number in textview-----------------------------
                    //JOB_DISPLAY.setText(String.valueOf(DatenObjekte.Jobnummer));
                    //JOB_DISPLAY.setTextColor(Color.WHITE);
                    JOB_NUM.setText("JOB");
                    JOB_NUM.setTextColor(Color.WHITE);

                    Button Korrektur = findViewById(R.id.btn_korrektur); //korrektur textview
                    Button m_min = findViewById(R.id.btn_mm); //Drahtdurchmesser mm
                    strom = findViewById(R.id.btn_current);

                    Korrektur.setText(String.valueOf(DatenObjekte.Lichtbogenkorrektur1));
                    m_min.setText(String.valueOf(DatenObjekte.SV1pos3/10+ "," + String.valueOf(DatenObjekte.SV1pos3%10))); //Drahtdurchmesser (mm)
                    //m_min.setText(String.valueOf(DatenObjekte.mpm_display/10+ "," + String.valueOf(DatenObjekte.mpm_display%10))); //Drahtdurchmesser (mm)

                    if(DatenObjekte.SV1pos1!=4) { // Not elektrode mode
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
                    new MainActivity_Controller().ChangeTextprogressBar(m_min,txtProgress,temp,check);
                    ChangeTextprogressBar(m_min);
                });

                current.setOnClickListener((View v) -> {
                    ChangeTextprogressBar(current);
                });

                long date = System.currentTimeMillis();
                SimpleDateFormat sdf = new SimpleDateFormat("h-mm-ss a");
                sdf.setTimeZone(TimeZone.getTimeZone("Germany/Berlin"));
                String dateString = sdf.format(date);
                String s= dateString;
                len = DatenObjekte.LengthProtocol2;

                //getContentResolver().insert(InfoContract.infoEntry.CONTENT_URI,values); // this line crash suddenly

                msg_for_me = "";

                /*uartService.HeaderFound = 0;
                uartService.CounterData = 0;
                uartService.LengthProtocol = 0;
                uartService.LengthFound = 0;*/
            //}
        }
            if (UartService.HeaderFound == 1){ //found '$'
                headercounter++;
                if(headercounter > 10){ //count until 10 millisecond
                    UartService.HeaderFound=0;
                    headercounter=0;
                }
            }

            if(DatenObjekte.HFound == 1) {
                countFrame++;
                if (countFrame > 1000) {//&& (DatenObjekte.jobtoken != 1)) { //more than 1s and data is not complete
                    countFrame = 0;
                    DatenObjekte.HFound = 0;
                    DatenObjekte.jobtoken = 0;
                }
            }

         if (counterDisplay==100) { //update every 0.1s
             if (CONTROL_PANEL_MODE==1) {
                 if(DatenObjekte.SV1pos1 != 4) { //Not elektrode mode
                     switch(HOME_COUNTER){
                         case 0: if (mm_a_display!=DatenObjekte.Energie1)  sendEnergie.ChangeParameter(2, mm_a_display, 1); //m/min
                                else CONTROL_PANEL_MODE = 0;
                                break;
                         case 1:
                             if (mm_a_display!=DatenObjekte.SV1pos3) sendEnergie.ChangeParameter(3, mm_a_display, 1); //mm
                             else CONTROL_PANEL_MODE = 0;
                                break;
                         case 2: if (mm_a_display!=DatenObjekte.StromSetwert) sendEnergie.ChangeParameter(1,mm_a_display,1); //strom
                             break;
                         case 3: //Korrektur
                             break;
                         case 4: //verfahren
                             break;
                         case 5: if (gas_token) sendEnergie.ChangeParameter(10, 0, 1); //Gas
                             gas_token = false;
                             break;
                         case 6: if (werkstoff_token) sendEnergie.ChangeParameter(10, 0, 1); //werkstoff
                             werkstoff_token = false;
                             break;
                         case 7: //if (job_token) sendEnergie.ChangeParameter(5, 0, 1); //job
                             job_token = false;
                             break;
                         }
                 }
             } else if (CONTROL_PANEL_MODE==0){
                 DatenObjekte.mpm_display=DatenObjekte.Energie1;
             }
             counterDisplay=0;
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
            //Log.i("Timer ","is counting");
            testbtn_onclick1(); //request kennlinie response
            delayInMilli(500);
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

    public void incrementEncoder1(int val_encoder){
        //Log.i(TAG,"incrementEncoder1 is called");

        if(!JOB_NUM_TOKEN) { //Job button in home page is not pressed
            //Log.i(TAG,"job not pressed");
            CONTROL_PANEL_MODE = 1;
            switch(HOME_COUNTER){
                case 0: if ((DatenObjekte.SV1pos1 == 1) && (DatenObjekte.mpm_display < 240)) { //Normal
                    mm_a_display = DatenObjekte.a_display + val_encoder; // m/min
                    progressBar.setProgress((int) (DatenObjekte.a_display) * (100 / 232) - (800 / 232));
                } else if ((DatenObjekte.SV1pos1 == 2) && (DatenObjekte.mpm_display < 120)) { //Synergie
                    mm_a_display = DatenObjekte.a_display + val_encoder; // m/min
                    progressBar.setProgress((int) ((DatenObjekte.a_display) * 100 / 80 - 50));
                } else if ((DatenObjekte.SV1pos1 == 3) && (DatenObjekte.mpm_display < 120)) {//Pulse
                    mm_a_display = DatenObjekte.a_display + val_encoder; // m/min
                    progressBar.setProgress(DatenObjekte.a_display - 20);
                }
                break;
                case 1: mm_a_display = DatenObjekte.a_display + val_encoder; //mm
                    progressBar.setProgress((mm_a_display) * (123 / 8) - 23);
                    break;
                case 2: mm_a_display = DatenObjekte.a_display + val_encoder; //A
                    break;
                case 3: //DatenObjekte.korrektur_display = DatenObjekte.korrektur_display + val_encoder; //korrektur
                    break;
                case 4: //verfahren
                    break;
                case 5: gas_token = true; //gas
                    break;
                case 6: werkstoff_token = true; //gas
                    break;
                case 7: job_token = true; //job
                    break;
            }

            //Log.i("mpm_display",String.valueOf());
            //---------------------------------------Energie--------------------------------------------
            /*if (DatenObjekte.SV1pos1 != 4) {
                switch(HOME_COUNTER){
                    case 0: txtprogress.setText(String.valueOf(DatenObjekte.mpm_display / 10) + "," + String.valueOf(DatenObjekte.mpm_display % 10) + "\n" + "m/min"); // m/min
                        break;
                    case 1: txtprogress.setText(String.valueOf(DatenObjekte.SV1pos3)); //mm
                        break;
                    case 2: txtprogress.setText(String.valueOf(DatenObjekte.StromSetwert)); //A
                        break;
                    case 3: txtprogress.setText(String.valueOf(DatenObjekte.Lichtbogenkorrektur1)); //Korrektur
                        break;
                    case 4: txtprogress.setText(DatenObjekte.Verfahren); //Verfahren
                        break;
                    case 5: txtprogress.setText(DatenObjekte.Gas); //Gas
                        break;
                    case 6: txtprogress.setText(DatenObjekte.Werkstoff); //Werkstoff
                        break;
                    case 7: txtprogress.setText(String.valueOf(DatenObjekte.Jobnummer)); //Job
                        break;
                }
                //txtprogress.setText(String.valueOf(DatenObjekte.mpm_display / 10) + "," + String.valueOf(DatenObjekte.mpm_display % 10) + "\n" + "m/min"); // m/min
            } else txtprogress.setText(String.valueOf(DatenObjekte.ElektrodeStromSetwert + " A")); // Elektrode mode*/

            if (SETTING_TOKEN) { //Setting button is pressed
                //Log.i(TAG,"Turn encoder");
                SETTING_COUNTER++;
                if (SETTING_COUNTER > 5) SETTING_COUNTER = 1;
                CHANGE_TOKEN = true;
                //Log.i("SETTING_COUNTER",String.valueOf(SETTING_COUNTER));
            }
            if (JOBUSER_TOKEN) { //Job button in setting is pressed
                JOBBTN_COUNTER++;
                //Log.i("JOBBTN_COUNTER",String.valueOf(JOBBTN_COUNTER));
                if (JOBBTN_COUNTER > 17) JOBBTN_COUNTER = 17;
                JobsUser.changeBackground(JOBBTN_COUNTER);
            }
            if (KENN_TOKEN) { //Kennlinie button in setting is pressed
                KENNBTN_COUNTER++;
                //Log.i("KENNBTN_COUNTER",String.valueOf(KENNBTN_COUNTER));
                if (KENNBTN_COUNTER > 17) KENNBTN_COUNTER = 17;
                Kennlinier_user.changeKennBackground(KENNBTN_COUNTER);
            }
        } else { //Job button in home page is pressed
            //---------------------------Increment Job number---------------------------------------
            DatenObjekteSend incrementJob = new DatenObjekteSend();
            incrementJob.ChangeParameter(4,0, 1);
            //Log.i("incrementJob","is called");
        }
    }

     public void decrementEncoder1(int val_encoder){

         if(!JOB_NUM_TOKEN) { //Job button in home page is not pressed
             CONTROL_PANEL_MODE = 1;
             /*if ((DatenObjekte.SV1pos1 == 1) && (DatenObjekte.mpm_display > 8)) { //Normal
                 DatenObjekte.mpm_display = DatenObjekte.mpm_display - val_encoder; // m/min
             } else if ((DatenObjekte.SV1pos1 == 2) && (DatenObjekte.mpm_display > 40)) { //Synergie
                 DatenObjekte.mpm_display = DatenObjekte.mpm_display - val_encoder; // m/min
             } else if ((DatenObjekte.SV1pos1 == 3) && (DatenObjekte.mpm_display > 20)) { //Pulse
                 DatenObjekte.mpm_display = DatenObjekte.mpm_display - val_encoder; // m/min
             }*/
             switch(HOME_COUNTER){
                 case 0: if ((DatenObjekte.SV1pos1 == 1) && (DatenObjekte.mpm_display < 240)) { //Normal
                     mm_a_display = DatenObjekte.a_display - val_encoder; // m/min
                     progressBar.setProgress((int) (DatenObjekte.a_display) * (100 / 232) - (800 / 232));
                 } else if ((DatenObjekte.SV1pos1 == 2) && (DatenObjekte.mpm_display < 120)) { //Synergie
                     mm_a_display = DatenObjekte.a_display - val_encoder; // m/min
                     progressBar.setProgress((int) ((DatenObjekte.a_display) * 100 / 80 - 50));
                 } else if ((DatenObjekte.SV1pos1 == 3) && (DatenObjekte.mpm_display < 120)) {//Pulse
                     mm_a_display = DatenObjekte.a_display - val_encoder; // m/min
                     progressBar.setProgress(DatenObjekte.a_display - 20);
                 }
                     break;
                 case 1: mm_a_display = DatenObjekte.a_display - val_encoder; //mm
                     progressBar.setProgress((mm_a_display) * (123 / 8) - 23);
                     break;
                 case 2: mm_a_display = DatenObjekte.a_display - val_encoder; //A
                     break;
                 case 3: //DatenObjekte.korrektur_display = DatenObjekte.korrektur_display + val_encoder; //korrektur
                     break;
                 case 4: //verfahren
                     break;
                 case 5: gas_token = true; //gas
                     break;
                 case 6: werkstoff_token = true; //werkstoff
                     break;
                 case 7: job_token = true; //job
                     break;
             }

             if (DatenObjekte.SV1pos1 != 4)
                 txtprogress.setText(String.valueOf(DatenObjekte.mpm_display / 10) + "," + String.valueOf(DatenObjekte.mpm_display % 10) + "\n" + "m/min"); // m/min
             else
                 txtprogress.setText(String.valueOf(DatenObjekte.ElektrodeStromSetwert + " A")); // Elektrode mode

             if (SETTING_TOKEN) { //Setting button is pressed
                 //Log.i(TAG,"Turn encoder");
                 SETTING_COUNTER--;
                 if (SETTING_COUNTER < 1) SETTING_COUNTER = 5;
                 CHANGE_TOKEN = true;
                 //Log.i("SETTING_COUNTER",String.valueOf(SETTING_COUNTER));
             }
             if (JOBUSER_TOKEN) { //Job button is pressed
                 JOBBTN_COUNTER--;
                 //Log.i("JOBBTN_COUNTER",String.valueOf(JOBBTN_COUNTER));
                 if (JOBBTN_COUNTER < 1) JOBBTN_COUNTER = 0;
                 JobsUser.changeBackground(JOBBTN_COUNTER);
             }
             if (KENN_TOKEN) { //Kennlinie button in setting is pressed
                 KENNBTN_COUNTER--;
                 //Log.i("KENNBTN_COUNTER",String.valueOf(KENNBTN_COUNTER));
                 if (KENNBTN_COUNTER < 1) KENNBTN_COUNTER = 0;
                 Kennlinier_user.changeKennBackground(KENNBTN_COUNTER);
             }
         } else { //Job button in home page is pressed
             //---------------------------Decrement Job number--------------------------------------
            DatenObjekteSend decrementJob = new DatenObjekteSend();
            decrementJob.ChangeParameter(6,0, 1);
             //Log.i("decrementJob","is called");
         }
      }

    public void pressbuttonEncoder1(){
        //Log.i(TAG,"pressbuttonEncoder1 is pressed");
        DatenObjekteSend sendsth = new DatenObjekteSend();
        if(!HOME){
            //Log.i("HOME_COUNTER",String.valueOf(HOME_COUNTER));
            HOME_COUNTER++;
            //-------------------------------activate parameter mode--------------------------------
            switch(HOME_COUNTER) {
                case 1: sendsth.ChangeParameter(3,0,0); //activate drahtdurchmesser mode
                    break;
                case 2: sendsth.ChangeParameter(1,0,0); //activate strom mode
                    break;
                case 3: //sendsth.ChangeParameter(9,DatenObjekte.mm_display,0); //activate korrektur mode
                    break;
                case 4: //sendsth.ChangeParameter(9,DatenObjekte.mm_display,0); //activate verfahren mode
                    break;
                case 5: //sendsth.ChangeParameter(9,DatenObjekte.mm_display,0);//Gas not necessary to send mode
                    break;
                case 6: //sendsth.ChangeParameter(9,DatenObjekte.mm_display,0);//Werkstoff not necessary
                    break;
                case 7: //sendsth.ChangeParameter(5,0,0); //activate job mode
                    break;
                case 8: sendsth.ChangeParameter(2,0,0); //activate energie mode
                    HOME_COUNTER=0;
                    break;
            }
        }else ENCODERBUTTON_TOKEN = true;
    }

    public void pressMenu(){
        MENU_TOKEN = true;
        //txtprogress.setText("Button0");
    }

    public void pressJob(){
        TEST_TOKEN = true;
        //txtprogress.setText("Button1");
    }

    public void pressHome(){
        HOME_TOKEN = true;
        //txtprogress.setText("Button2");
    }

    public void pressDroessel(){
        DROSSEL_TOKEN = true;
        //txtprogress.setText("Button3");
    }

    public void pressDaten(){
        DATEN_TOKEN = true;
        //txtprogress.setText("Button4");
    }

    public void pressVerfahren(){
        VERFAHREN_TOKEN = true;
        //txtprogress.setText("Button5");
    }

    public void pressKennlinie(){
        KENNLINIE_TOKEN = true;
        //txtprogress.setText("Button6");
    }

    public void pressBetriebsart(){
        BETRIEBSART_TOKEN = true;
        //txtprogress.setText("Button7");
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

    private void variablen_init(){

       // TxtPr = findViewById(R.id.txtpro);
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
     //   JOB_DISPLAY = findViewById(R.id.job_Nummer);
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

    private void testbtn_onclick(){
        //Disable the kennlinie_setting mode

        DISABLE_KENN[0] = 36;


        DatenObjekteSend sendobj = new DatenObjekteSend();
        //BluetoothService.ConnectedThread BTobj = new BluetoothService.ConnectedThread();
        String buff = String.valueOf(random);
        btHandler.obtainMessage(BTConstants.MESSAGE_WRITE, -1, -1, buff.getBytes()).sendToTarget(); //MESSAGE_READ=2, arg1=bytes, arg2=-1(nothing), object=buffer
        random++;

        TEST_FRAME[0] = 36; //0x24
        TEST_FRAME[1] = 19; //0x13
        TEST_FRAME[2] = 2;
        TEST_FRAME[3] = 9; //0x09 msb can id
        TEST_FRAME[4] = 0; //0x00 lsb can id
        TEST_FRAME[5] = 8; //data length
        TEST_FRAME[6] = 0;
        TEST_FRAME[7] = 1;
        TEST_FRAME[8] = 2;
        TEST_FRAME[9] = 3;
        TEST_FRAME[10] = 4;
        TEST_FRAME[11] = 5;
        TEST_FRAME[12] = 6;
        TEST_FRAME[13] = 7;
        TEST_FRAME[14] = 35; //0x23

        Checksum tmp = new CRC32();
        tmp.update(TEST_FRAME, 0, 15);// update the current checksum with the specified array of bytes
        long tmpCrc = tmp.getValue();// get the current checksum value //checksum is correct
        //Log.i("tempValue",String.valueOf(crcValue));
        TEST_FRAME[14] = (byte) ((tmpCrc >>> 24) & 0xFF); //msb
        TEST_FRAME[15] = (byte) ((tmpCrc >>> 16) & 0xFF);
        TEST_FRAME[16] = (byte) ((tmpCrc >>> 8) & 0xFF);
        TEST_FRAME[17] = (byte) (tmpCrc & 0xFF); //lsb
        TEST_FRAME[18] = 35; //0x23

        StringBuilder TEST_SB = new StringBuilder();

        for(int j=0; j<19; j++ ){
            TEST_STR = TEST_SB.append((char) ((TEST_FRAME[j]) & 0xFF)).toString();
            //Log.i("TEST_STR ",TEST_STR);
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (COUNTER < 100) {
                    testhandler.post(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                if(mOutputStream != null) {
                                    Log.i("mOutputStream","not null");
                                    mOutputStream.write(TEST_STR.getBytes(iso88591charset));
                                    mOutputStream.write('\n');
                                }
                                Log.i(TAG,"Write "+TEST_STR+" to console");
                            } catch (IOException e){
                                Log.e(TAG,"Cant write to the console");
                            }
                        }
                    });
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    COUNTER++;
                }
                Log.i("Counter ",String.valueOf(COUNTER));
            }
        }).start();


        //Write something from Raspberry Pi

        String TEST = "Testing";
        try {
            if(mOutputStream != null) {
                //Log.i("mOutputStream","not null");
                mOutputStream.write(TEST.getBytes(iso88591charset));
                mOutputStream.write('\n');
            }
            //Log.i(TAG,"Write "+TEST+" to console");
        } catch (IOException e){
            Log.e(TAG,"Cant write to the console");
        }


        //---------------------------- Activate Job ------------------------------------------------

        //sendobj.ChangeParameter(10, rannum); //DatenObjekteSend class
        //rannum++;
        //SaveJob();
        if (msg_for_can != null && !msg_for_can.equals("")) { //send parameter frame to the machine
            if (uartService != null) {
                uartService.write(msg_for_can.getBytes(iso88591charset));
                msg_for_can = "";
                //Log.i("Send", "Drahtdurchmesser");
            }
        }
        //delayInMilli(200);
        //Deactivate Job
        //sendobj.ChangeParameter(34, 12); //Job Deactivated
        //writeJOB();
        //delayInMilli(100);
        //SaveJob();

        if (msg_for_can != null && !msg_for_can.equals("")) { //send parameter frame to the machine
            if (uartService != null) {
                //uartService.write(msg_for_can.getBytes(iso88591charset));
                //Log.i("Write",msg_for_can);
                msg_for_can = "";
            }
        }
        delayInMilli(100);
        if (msg_for_can1 != null && !msg_for_can1.equals("")) { //send value frame to the machine
            if (uartService != null) {
                //uartService.write(msg_for_can1.getBytes(iso88591charset));
                //Log.i("Write",msg_for_can1);
                msg_for_can1 = "";
            }
        }
        //Increment Job number
                /*for (int i=0; i<JOB_COUNT; i++){
                    DatenObjekteSend JOB_INCREMENT = new DatenObjekteSend();
                    JOB_INCREMENT.ChangeParameter(34,7);
                    writeJOB();
                    delayInMilli(200);
                }*/
                /*if(changekennlinie != null) {
                    byte[] FIRST_ARRAY = changekennlinie.sendKennlinieBaseData();

                    if (uartService != null) {
                        int exp = 0;
                        int exp1 = 152; //last frame
                        for (int g = 0; g < 8; g++) { //frames with 8 byte data
                            for (int h = exp; h < exp + 19; h++) {
                                firstframestr = firstframestr + (char) (FIRST_ARRAY[h] & 0xFF);
                            }
                            uartService.write(firstframestr.getBytes(iso88591charset));
                            firstframestr = "";
                            delayInMilli(10); //delay 10ms
                            exp = exp + 19;
                        }
                        for (int h = exp1; h < exp1 + 17; h++) { //frames with 6 byte data
                            firstframestr = firstframestr + (char) (FIRST_ARRAY[h] & 0xFF);
                        }
                        uartService.write(firstframestr.getBytes(iso88591charset));
                        firstframestr = "";
                        delayInMilli(10); //delay 10ms
                        Log.i(TAG, "First frame is sent");
                    }
                }*/
                /*if(changekennlinie != null) {
                    byte[] SECOND_ARRAY = changekennlinie.sendKennlinieStützpunkt();

                    if (uartService != null) {
                        int exp = 0;
                        for (int g = 0; g < 2; g++) {
                            for (int h = exp; h < exp + 19; h++) {
                                secondframestr = secondframestr + (char) (SECOND_ARRAY[h] & 0xFF);
                            }
                            uartService.write(secondframestr.getBytes(iso88591charset));
                            secondframestr = "";
                            delayInMilli(10); //delay 10ms
                            exp = exp + 19;
                        }
                        Log.i(TAG, "Second frame is sent");
                    }
                }*/
        /**
         * This method is to send kennlinie_setting Satz to the machine
         **/

                /*if(changekennlinie != null) {
                    byte[] THIRD_ARRAY = changekennlinie.sendKennlinieSatz();

                    if (uartService != null) {
                        int exp = 0;
                        int exp1 = 493;
                        for (int g = 0; g < 26; g++) { //frames with 8 byte data
                            for (int h = exp; h < exp + 19; h++) {
                                thirdframestr = thirdframestr + (char) (THIRD_ARRAY[h] & 0xFF);
                            }
                            uartService.write(thirdframestr.getBytes(iso88591charset));
                            thirdframestr = "";
                            delayInMilli(10); //delay 10ms
                            exp = exp + 19;
                        }
                        for (int h = exp1; h < exp1 + 17; h++) { //frames with 6 byte data
                            thirdframestr = thirdframestr + (char) (THIRD_ARRAY[h] & 0xFF);
                        }
                        uartService.write(thirdframestr.getBytes(iso88591charset));
                        thirdframestr = "";
                        delayInMilli(10); //delay 10ms
                        Log.i(TAG, "Third frame is sent");
                    }
                }*/
        //UartService.token = false;
        //sendobj.ChangeParameter(12, random); //DatenObjekteSend class
        //delayInMilli(50); //delay 0.05s
        cj = UartService.getJob();
        js = UartService.getJob1(13); //get job params

        if (DatenObjekte.checktoken == 0){
            if  (cj != null && !cj.equals("")) { //first job frame
                if (uartService != null) uartService.write(cj.getBytes(iso88591charset));
            }
            delayInMilli(50); //delay 0.05s
            if (js != null && !js.equals("")) { //second job frame
                if (uartService != null) uartService.write(js.getBytes(iso88591charset));
            }
            DatenObjekte.checktoken = 0;
        }

        delayInMilli(50); //delay 0.05s
        DatenObjekteJob.DataBase();
        //System.out.println("daten ist :" + DatenObjekteJob.uiJobNr);
        JobsDetails jobsDetails = new JobsDetails();
        jobsDetails.iint_datejob();

        ContentValues values = new ContentValues();
        if (DatenObjekteJob.uiJobNr!=0) {
            for (int i = 0; i < 112; i++) {
                //System.out.println(JobsDetails.modiJobsdeateils[i]);
                values.put(jobsDetails.jobdetails[i], JobsDetails.modiJobsdeateils[i]);
            }
            getContentResolver().insert(JobContract.jobEntry.CONTENT_URI, values);
        }

        //if (DatenObjekte.checktoken == 1){ //return from job request
        //   UartService.UpdateJobFlag = 1;
                   /* JobUpdate.firstJobSend(12);
                    if (uartService != null) {
                        exp = 0;
                        for(int g=0; g<39; g++) {
                            for(int h=exp; h<exp+19; h++){
                                firstframestr = firstframestr + (char)(JobUpdate.firstframe[h]&0xFF);
                            }
                            uartService.write(firstframestr.getBytes(iso88591charset));
                            firstframestr="";
                            delayInMilli(10);
                            exp = exp + 19;
                        }
                    }
                    delayInMilli(1000); //delay 1s before sending second jobupdate frame
                    JobUpdate.secondJobSend(12);
                    if (uartService != null) {
                        exp1 = 0;
                        for(int g=0; g<38; g++) {
                            for(int h=exp1; h<exp1+19; h++){
                                secondframestr = secondframestr + (char)(JobUpdate.secondframe[h]&0xFF);
                            }
                            uartService.write(secondframestr.getBytes(iso88591charset));
                            secondframestr="";
                            delayInMilli(10); //delay 0.01s
                            exp1 = exp1 + 19;
                        }
                    }*/
        // UartService.UpdateJobFlag = 0;
        StringBuilder sbfulljobstring = new StringBuilder();
        for(int i=8;i<302;i++){ //from second frame till last second frame
            fulljobstring = sbfulljobstring.append((char)(DatenObjekte.KENNLINIE_FRAME[i]&0xFF)).toString();
        }
        DatenObjekteJob.DataBase();
        //fulljobba = fulljobstring.getBytes(iso88591charset);
        //calculate checksum
        //getCRCval = UartService.calcCRC16(fulljobba,fulljobba.length);

        //compare with checksum
        int getCRC16 = ((DatenObjekte.KENNLINIE_FRAME[303] & 0xFF) << 8) | //flip F9 49 to 49 F9
                ((DatenObjekte.KENNLINIE_FRAME[302] & 0xFF));
        //Log.i("getCRCval",String.valueOf(getCRCval));
        //if (getCRCval == getCRC16) Log.i("Good news","Correct checksum");
        //DatenObjekte.checktoken = 0;
        // }

        if (msg_for_can != null && !msg_for_can.equals("")) { //send parameter frame to the machine
            if (uartService != null) uartService.write(msg_for_can.getBytes(iso88591charset));
            msg_for_can = "";
        }
        delayInMilli(50); //delay 0.05s
        if (msg_for_can1 != null && !msg_for_can1.equals("")) { //send value frame to the machine
            if (uartService != null) uartService.write(msg_for_can1.getBytes(iso88591charset));
            msg_for_can1 = "";
        }
        //random++;
        //UartService.token = true;
    }

    private void testbtn_onclick1(){
        //----------------------------Deactivate data parsing-----------
        TEMP_BA = GetKennlinieDaten.readKennDaten();
        StringBuilder tempsb = new StringBuilder();
        for(int i=0;i<23;i++){
            //TEMP_STRING = tempsb.append(String.format("%02x", (int) ((TEMP_BA[i]) & 0xFF)).toUpperCase()).toString();
            TEMP_STRING = tempsb.append((char)(TEMP_BA[i]&0xFF)).toString();
        }
        //Log.i("firstframe ",TEMP_STRING);
        WriteToSerial(TEMP_STRING);
        TEMP_STRING = "";
        /*StringBuilder tempsb1 = new StringBuilder();
        delayInMilli(500);
        for(int i=16;i<31;i++){
            //TEMP_STRING = tempsb1.append(String.format("%02x", (int) ((TEMP_BA[i]) & 0xFF)).toUpperCase()).toString();
            TEMP_STRING = tempsb1.append((char)(TEMP_BA[i]&0xFF)).toString();
        }
        //Log.i("secondframe ",TEMP_STRING);
        WriteToSerial(TEMP_STRING);*/
    }

    private void MMSynergy_btn_onclick(){
        VERFAHREN_MODE = 3;

        DatenObjekteSend SendMMSynergie = new DatenObjekteSend();
        if(DatenObjekte.SV1pos1 == 4) { // Elektrode
            SendMMSynergie.ChangeParameter(25, 10, 0);
            delayInMilli(200);
            SendMMSynergie.ChangeParameter(25, 10, 0);
        }else if(DatenObjekte.SV1pos1 == 3){ //Puls
            SendMMSynergie.ChangeParameter(25, 10, 0);
            delayInMilli(200);
            SendMMSynergie.ChangeParameter(25, 10, 0);
            delayInMilli(200);
            SendMMSynergie.ChangeParameter(25, 10, 0);
        }else if(DatenObjekte.SV1pos1 == 1){ //Normal
            SendMMSynergie.ChangeParameter(25, 10, 0);
        }else Log.i("Verfahren mode ","MMSynergy");

    }
    private void MMPuls_btn_onclick(){
        VERFAHREN_MODE = 4;

        DatenObjekteSend SendMMPuls = new DatenObjekteSend();
        if(DatenObjekte.SV1pos1 == 4){ // Elektrode
            SendMMPuls.ChangeParameter(29,5,0);
            delayInMilli(200);
            SendMMPuls.ChangeParameter(29,5,0);
            delayInMilli(200);
            SendMMPuls.ChangeParameter(29,5,0);
        }else if(DatenObjekte.SV1pos1 == 2){ // Synergie
            SendMMPuls.ChangeParameter(29,5,0);
        }else if(DatenObjekte.SV1pos1 == 1){ // Normal
            SendMMPuls.ChangeParameter(29,5,0);
            delayInMilli(200);
            SendMMPuls.ChangeParameter(29,5,0);
        }else Log.i("Verfahren mode ","MMPuls");
    }

    private void ElektrodeMMA_btn_onclick(){
        VERFAHREN_MODE = 5;

        DatenObjekteSend SendElectrodeMMA = new DatenObjekteSend();
        if(DatenObjekte.SV1pos1 == 3){ // Puls
            SendElectrodeMMA.ChangeParameter(30,30,0);
        }else if(DatenObjekte.SV1pos1 == 2){ // Synergie
            SendElectrodeMMA.ChangeParameter(30,30,0);
            delayInMilli(200);
            SendElectrodeMMA.ChangeParameter(30,30,0);
        }else if(DatenObjekte.SV1pos1 == 1){ // Normal
            SendElectrodeMMA.ChangeParameter(30,30,0);
            delayInMilli(200);
            SendElectrodeMMA.ChangeParameter(30,30,0);
            delayInMilli(200);
            SendElectrodeMMA.ChangeParameter(30,30,0);
        }else Log.i("Verfahren mode ","ElektrodeMMA");
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
        WriteToSerial(KENN_STRING);
        KENN_STRING = "";

            /*for(int i=0;i<430;i++){
                System.out.println(String.format("%02x", (int)((TMP_KENNFRAME[i]) & 0xFF)).toUpperCase());
            }*/
                /*int NUM = 0;
                for (int i = 0; i < 26; i++) { //frames with 8 byte data
                    for (int j=NUM; j<NUM + 16; j++) {
                        KENN_STRING = KENN_STRING + (char) (TMP_KENNFRAME[j] & 0xFF);
                    }
                    WriteToSerial(KENN_STRING);
                    KENN_STRING = "";
                    delayInMilli(200); //delay 10ms
                    NUM = NUM + 16;
                }
                for (int k=416; k<430; k++) { //frames with 6 byte data
                    KENN_STRING = KENN_STRING + (char) (TMP_KENNFRAME[k] & 0xFF);
                }
                WriteToSerial(KENN_STRING);
                KENN_STRING = "";*/
                //delayInMilli(100); //delay 10ms
                //Log.i(TAG, "Update kennlinie_setting");
    }

    private void WriteToSerial(String s){
        try {
            if(mOutputStream != null) {
                //Log.i("mOutputStream","not null");
                mOutputStream.write(s.getBytes(iso88591charset));
                //mOutputStream.write('\n');
            }
            //Log.i(TAG,"Write "+ s +" to console");
        } catch (IOException e){
            Log.e(TAG,"Cant write to the console");
        }
    }

    void ausblinden_drossel(){
        progressBarPlus.setVisibility(View.GONE);
        progressBarMinus.setVisibility(View.INVISIBLE);
        //  circle_button.setVisibility(View.GONE);
        minus_button.setVisibility(View.GONE);
        plus.setVisibility(View.INVISIBLE);
        droessel.setEnabled(false);
    }
}