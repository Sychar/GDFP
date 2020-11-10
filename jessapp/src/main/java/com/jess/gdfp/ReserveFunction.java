package com.jess.gdfp;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jess.gdfp.View.JobsDetails;

import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

import static com.jess.gdfp.UartService.mOutputStream;

public class ReserveFunction {

    private final static String TAG = ReserveFunction.class.getSimpleName(); //name of this class

    private static byte[] TEST_FRAME = new byte[20];
    private static int random = 0;
    private static byte[] DISABLE_KENN = new byte[20];
    private Boolean btCom_status = false;
    private BluetoothService BSF = null;
    private Button btConn;
    private Button bleConn;
    private Button ConnectBLE;
    private TextView status;
    private BluetoothDevice btDevice;
    private BluetoothLEService mBluetoothLEService;
    private BluetoothLEService mBLES;
    //private BroadcastReceiver mBluetoothReceiver; //BLE
    private BluetoothManager mBluetoothManager; //BLE
    private BluetoothAdapter mBluetoothAdapter; //BLE
    //private BluetoothGattServer mBluetoothGattServer; //BLE
    private Set<BluetoothDevice> mRegisteredDevices = new HashSet<>(); //BLE
    private static String cj = "";
    private static String js = "";
    private static Charset iso88591charset = Charset.forName("ISO-8859-1");
    public static String TEST_STR = "";
    public static int COUNTER = 0;
    private Handler testhandler = new Handler();
    private BluetoothAdapter btAdapter;
    private Timer timer;
    private TimerTask timerCount;
    private static int counter_savejob;
    private TimerTask timertask;
    private CountDownTimer waitTimer;
    private static int rannum = 0;
    private static int JOB_COUNT = 5;

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
        //delayInMilli(200);
        //Deactivate Job
        //sendobj.ChangeParameter(34, 12); //Job Deactivated
        //writeJOB();
        //delayInMilli(100);
        //SaveJob();

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
            //getContentResolver().insert(JobContract.jobEntry.CONTENT_URI, values);
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
            //fulljobstring = sbfulljobstring.append((char)(DatenObjekte.KENNLINIE_FRAME[i]&0xFF)).toString();
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

        //random++;
        //UartService.token = true;
    }

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
                        //send parameter frame to the machine
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
                //invalidateOptionsMenu();
            } else if (BluetoothLEService.ACTION_GATT_DISCONNECTED.equals(action)) {
                Log.i("mGattUpdateReceiver","ACTION_GATT_DISCONNECTED");
                //conBLE="000";
                mBluetoothLEService.close();
                //BSF.cancel();
                //invalidateOptionsMenu();
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
                //invalidateOptionsMenu(); //is used to say Android, that contents of menu have changed, and menu should be redrawn.
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

    //------------------------------------ onResume ------------------------------------------------
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
    //----------------------------------------------------------------------------------------------

    //------------------------------------ onDestroy ------------------------------------------------
    /* if(BSF != null) BSF.stop(); //Stop the Bluetooth chat services //disable temporarily

        BluetoothAdapter bluetoothAdapter = mBluetoothLEService.mBluetoothManager.getAdapter();
        if(bluetoothAdapter.isEnabled()){
            mBluetoothLEService.stopServer();
            Log.i(TAG,"mBluetoothLEService.stopServer()");
        }

        unregisterReceiver(mBluetoothReceiver); //BLE*/
    //----------------------------------------------------------------------------------------------

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

    //----------------------------------------------------------------------------------------------

    /*private void progressbar_thread(){
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
    }*/
    //----------------------------------------------------------------------------------------------

    public void onpressedButton(View view) {
        if (view.getId() == R.id.Drossel_plus) {
            //progressBarMinus = (ProgressBar) findViewById(R.id.progress);
            //int y = progressBarMinus.getProgress();
            //progressBarMinus.setProgress(y - 4);
        }
        if (view.getId() == R.id.Drossel_minus) {
            //progressBarMinus = (ProgressBar) findViewById(R.id.progress);
            //int y = progressBarMinus.getProgress();
            //progressBarMinus.setProgress(y + 4);
        }
    }

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

    private void setFilters() { //call in onResume()
        /*IntentFilter filter = new IntentFilter();
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
        this.registerReceiver(mReceiver3,intfilter3);//registering our receiver*/
    }

    /*void ausblinden_drossel(){
        progressBarPlus.setVisibility(View.GONE);
        progressBarMinus.setVisibility(View.INVISIBLE);
        //  circle_button.setVisibility(View.GONE);
        minus_button.setVisibility(View.GONE);
        plus.setVisibility(View.INVISIBLE);
        droessel.setEnabled(false);
    }*/

    //-------------------------------- onPause -----------------------------------------------------
    /*this.unregisterReceiver(this.mReceiver); //unregister our receiver
    this.unregisterReceiver(this.mReceiver1); //unregister our receiver
    this.unregisterReceiver(this.mReceiver2); //unregister my receiver
    this.unregisterReceiver(this.mReceiver3); //unregister my receiver*/

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

    //----------------------------- For Encoder 2 --------------------------------------------------

    /*if (GlobalVariable.ENCODER2_PRESSED){
                if (GlobalVariable.ENCODER2_COUNT==1) {
                    Value1.setTextColor(Color.BLACK);
                    Value1.setBackgroundColor(Color.GRAY);
                    Value2.setTextColor(Color.WHITE);
                    Value2.setBackground(getResources().getDrawable(R.drawable.border2));
                    Value3.setTextColor(Color.WHITE);
                    Value3.setBackground(getResources().getDrawable(R.drawable.border2));
                    if (Label1.getText().equals("KorrLB")) {
                        GlobalVariable.ChangeValue[3] = 1;
                        GlobalVariable.ChangeValue[0] = 0;
                        GlobalVariable.ChangeValue[1] = 0;
                        GlobalVariable.ChangeValue[2] = 0;
                        GlobalVariable.ChangeValue[4] = 0;
                    } else if (Label1.getText().equals("Voltage")) {
                        GlobalVariable.ChangeValue[4] = 1;
                        GlobalVariable.ChangeValue[0] = 0;
                        GlobalVariable.ChangeValue[1] = 0;
                        GlobalVariable.ChangeValue[2] = 0;
                        GlobalVariable.ChangeValue[3] = 0;
                    } else if (Label1.getText().equals("Ampere")) {
                        GlobalVariable.ChangeValue[2] = 1;
                        GlobalVariable.ChangeValue[0] = 0;
                        GlobalVariable.ChangeValue[1] = 0;
                        GlobalVariable.ChangeValue[3] = 0;
                        GlobalVariable.ChangeValue[4] = 0;
                    } else if (Label1.getText().equals("mm")) {
                        GlobalVariable.ChangeValue[1] = 1;
                        GlobalVariable.ChangeValue[0] = 0;
                        GlobalVariable.ChangeValue[2] = 0;
                        GlobalVariable.ChangeValue[3] = 0;
                        GlobalVariable.ChangeValue[4] = 0;
                    } else if (Label1.getText().equals("m/min")) {
                        GlobalVariable.ChangeValue[0] = 1;
                        GlobalVariable.ChangeValue[1] = 0;
                        GlobalVariable.ChangeValue[2] = 0;
                        GlobalVariable.ChangeValue[3] = 0;
                        GlobalVariable.ChangeValue[4] = 0;
                    }
                } else if (GlobalVariable.ENCODER2_COUNT==2) {
                    Value2.setTextColor(Color.BLACK);
                    Value2.setBackgroundColor(Color.GRAY);
                    Value1.setTextColor(Color.WHITE);
                    Value1.setBackground(getResources().getDrawable(R.drawable.border2));
                    Value3.setTextColor(Color.WHITE);
                    Value3.setBackground(getResources().getDrawable(R.drawable.border2));
                    if (Label2.getText().equals("KorrLB")) {
                        GlobalVariable.ChangeValue[3] = 1;
                        GlobalVariable.ChangeValue[0] = 0;
                        GlobalVariable.ChangeValue[1] = 0;
                        GlobalVariable.ChangeValue[2] = 0;
                        GlobalVariable.ChangeValue[4] = 0;
                    } else if (Label2.getText().equals("Voltage")) {
                        GlobalVariable.ChangeValue[4] = 1;
                        GlobalVariable.ChangeValue[0] = 0;
                        GlobalVariable.ChangeValue[1] = 0;
                        GlobalVariable.ChangeValue[2] = 0;
                        GlobalVariable.ChangeValue[3] = 0;
                    } else if (Label2.getText().equals("Ampere")) {
                        GlobalVariable.ChangeValue[2] = 1;
                        GlobalVariable.ChangeValue[0] = 0;
                        GlobalVariable.ChangeValue[1] = 0;
                        GlobalVariable.ChangeValue[3] = 0;
                        GlobalVariable.ChangeValue[4] = 0;
                    } else if (Label2.getText().equals("mm")) {
                        GlobalVariable.ChangeValue[1] = 1;
                        GlobalVariable.ChangeValue[0] = 0;
                        GlobalVariable.ChangeValue[2] = 0;
                        GlobalVariable.ChangeValue[3] = 0;
                        GlobalVariable.ChangeValue[4] = 0;
                    } else if (Label2.getText().equals("m/min")) {
                        GlobalVariable.ChangeValue[0] = 1;
                        GlobalVariable.ChangeValue[1] = 0;
                        GlobalVariable.ChangeValue[2] = 0;
                        GlobalVariable.ChangeValue[3] = 0;
                        GlobalVariable.ChangeValue[4] = 0;
                    }
                } else if (GlobalVariable.ENCODER2_COUNT==3 && !Label3.getText().equals("")) {
                    Value3.setTextColor(Color.BLACK);
                    Value3.setBackgroundColor(Color.GRAY);
                    Value1.setTextColor(Color.WHITE);
                    Value1.setBackground(getResources().getDrawable(R.drawable.border2));
                    Value2.setTextColor(Color.WHITE);
                    Value2.setBackground(getResources().getDrawable(R.drawable.border2));
                    if (Label3.getText().equals("KorrLB")) {
                        GlobalVariable.ChangeValue[3] = 1;
                        GlobalVariable.ChangeValue[0] = 0;
                        GlobalVariable.ChangeValue[1] = 0;
                        GlobalVariable.ChangeValue[2] = 0;
                        GlobalVariable.ChangeValue[4] = 0;
                    } else if (Label3.getText().equals("Voltage")) {
                        GlobalVariable.ChangeValue[4] = 1;
                        GlobalVariable.ChangeValue[0] = 0;
                        GlobalVariable.ChangeValue[1] = 0;
                        GlobalVariable.ChangeValue[2] = 0;
                        GlobalVariable.ChangeValue[3] = 0;
                    } else if (Label3.getText().equals("Ampere")) {
                        GlobalVariable.ChangeValue[2] = 1;
                        GlobalVariable.ChangeValue[0] = 0;
                        GlobalVariable.ChangeValue[1] = 0;
                        GlobalVariable.ChangeValue[3] = 0;
                        GlobalVariable.ChangeValue[4] = 0;
                    } else if (Label3.getText().equals("mm")) {
                        GlobalVariable.ChangeValue[1] = 1;
                        GlobalVariable.ChangeValue[0] = 0;
                        GlobalVariable.ChangeValue[2] = 0;
                        GlobalVariable.ChangeValue[3] = 0;
                        GlobalVariable.ChangeValue[4] = 0;
                    } else if (Label3.getText().equals("m/min")) {
                        GlobalVariable.ChangeValue[0] = 1;
                        GlobalVariable.ChangeValue[1] = 0;
                        GlobalVariable.ChangeValue[2] = 0;
                        GlobalVariable.ChangeValue[3] = 0;
                        GlobalVariable.ChangeValue[4] = 0;
                    }
                } else if (GlobalVariable.ENCODER2_COUNT==3) GlobalVariable.ENCODER2_COUNT=1;
            }*/

    /*if (GlobalVariable.CONTROL_PANEL_MODE1 == 1) {
                    if ((GlobalVariable.ChangeValue[0] == 1)) {
                        if (GlobalVariable.mpm_display != GlobalVariable.Energie1)
                            sendEnergie.ChangeParameter(2, GlobalVariable.mpm_display, 1); //m/min
                        else GlobalVariable.CONTROL_PANEL_MODE1 = 0;
                    } else if ((GlobalVariable.ChangeValue[4] == 1)) {
                        if (GlobalVariable.voltage_display != GlobalVariable.Spannung1)
                            sendEnergie.ChangeParameter(15, GlobalVariable.voltage_display, 1); //voltage
                        else GlobalVariable.CONTROL_PANEL_MODE1 = 0;
                    } else if ((GlobalVariable.ChangeValue[1] == 1)) {
                        if (GlobalVariable.mm_a_display != GlobalVariable.mirror_display)
                            sendEnergie.ChangeParameter(3, GlobalVariable.mm_a_display, 1); //Thickness mm
                        else GlobalVariable.CONTROL_PANEL_MODE1 = 0;
                    } else if ((GlobalVariable.ChangeValue[2] == 1)) {
                        if (GlobalVariable.mm_a_display != GlobalVariable.mirror_display)
                            sendEnergie.ChangeParameter(1, GlobalVariable.mm_a_display, 1); //strom
                        else GlobalVariable.CONTROL_PANEL_MODE1 = 0;
                    } else if ((GlobalVariable.ChangeValue[3] == 1)) {
                        if (GlobalVariable.korrektur_display != GlobalVariable.Lichtbogenkorrektur1)
                            sendEnergie.ChangeParameter(21, GlobalVariable.korrektur_display, 1);
                        else GlobalVariable.CONTROL_PANEL_MODE1 = 0;
                    }
                }*/

}

