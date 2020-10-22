package com.jess.gdfp;

import android.util.Log;

import com.jess.gdfp.View.JobsUser;

import static com.jess.gdfp.View.Setting.JOBUSER_TOKEN;
import static com.jess.gdfp.View.Setting.KENN_TOKEN;

public class WeldingChangeParam {
    private final static String TAG = WeldingChangeParam.class.getSimpleName(); //name of this class

    public static boolean ACTIVATE_VARFAHREN = false;
    public static boolean VERFAHREN_TOKEN = false;
    public static boolean KENNLINIE_TOKEN = false;
    public static boolean BETRIEBSART_TOKEN = false;
    public static boolean MENU_TOKEN = false;
    public static boolean TEST_TOKEN = false;
    public static boolean HOME_TOKEN = false;
    public static boolean MA_TOKEN = true;
    public static boolean DROSSEL_TOKEN = false;
    public static boolean DATEN_TOKEN = false;
    public static boolean ENCODERBUTTON_TOKEN = false;
    public static boolean HOME = false;
    public static int HOME_COUNTER = 0;

    public void incrementEncoder1(int val_encoder){
        GlobalVariable.encoder = true;
        Log.i("incrementEncoder1","is called");

        if (GlobalVariable.JOB_PRESSED){
            //Log.i("JOB_PRESSED_COUNTER",String.valueOf(GlobalVariable.JOB_PRESSED_COUNTER));
            GlobalVariable.JOB_PRESSED_COUNTER++;
            DatenObjekteSend incrementJob = new DatenObjekteSend();
            incrementJob.ChangeParameter(39, 0, 0);
            if(GlobalVariable.JOB_PRESSED_COUNTER > 100) GlobalVariable.JOB_PRESSED_COUNTER = 0;

        } else if(!GlobalVariable.JOB_NUM_TOKEN) { //Job button in home page is not pressed
            //Log.i(TAG,"job not pressed");
            GlobalVariable.CONTROL_PANEL_MODE = 1;
            switch(HOME_COUNTER){
                case 0: if ((GlobalVariable.SV1pos1 == 1) && (GlobalVariable.mpm_display < 240)) { //Normal
                    GlobalVariable.mpm_display = GlobalVariable.Energie1 + val_encoder; // m/min
                    //progressBar.setProgress((int) (DatenObjekte.a_display) * (100 / 232) - (800 / 232));
                } else if ((GlobalVariable.SV1pos1 == 2) && (GlobalVariable.mpm_display < 120)) { //Synergie
                    GlobalVariable.mpm_display = GlobalVariable.Energie1 + val_encoder; // m/min
                    //progressBar.setProgress((int) ((DatenObjekte.a_display) * 100 / 80 - 50));
                } else if ((GlobalVariable.SV1pos1 == 3) && (GlobalVariable.mpm_display < 120)) {//Pulse
                    GlobalVariable.mpm_display = GlobalVariable.Energie1 + val_encoder; // m/min
                    //progressBar.setProgress(DatenObjekte.a_display - 20);
                }
                    break;
                case 1:
                    GlobalVariable.mm_a_display = GlobalVariable.mirror_display + val_encoder; //mm
                    //progressBar.setProgress((mm_a_display) * (123 / 8) - 23);
                    break;
                case 2:
                    GlobalVariable.mm_a_display = GlobalVariable.mirror_display + val_encoder; //A
                    break;
                case 3:
                    GlobalVariable.korrektur_display = GlobalVariable.korrektur_display + val_encoder; //korrektur
                    if(GlobalVariable.korrektur_display > 30) GlobalVariable.korrektur_display = 30;
                    break;
                case 4: //verfahren
                    break;
                case 5:
                    GlobalVariable.gas_token = true; //gas
                    break;
                case 6:
                    GlobalVariable.werkstoff_token = true; //werkstoff
                    break;
                case 7:
                    GlobalVariable.Drahtdurchmesser_token = true; //Drahtdurchmesser
                    break;
                case 8:
                    GlobalVariable.voltage_display = GlobalVariable.voltage_display + val_encoder; //voltage
                    break;
                case 9: GlobalVariable.job_token = true; //job
                    break;
            }

            //---------------------------------- Verfahren mode ------------------------------------
            if (ACTIVATE_VARFAHREN) {
                DatenObjekteSend sendObject = new DatenObjekteSend();
                sendObject.ChangeParameter(28,0,0); //activate verfahren mode
                //GlobalVariable.VERFAHREN_COUNTER++;
                //if (GlobalVariable.VERFAHREN_COUNTER > 4) GlobalVariable.VERFAHREN_COUNTER = 1;
            }

            if (GlobalVariable.SETTING_TOKEN) { //Setting button is pressed
                //Log.i(TAG,"Turn encoder");
                GlobalVariable.SETTING_COUNTER++;
                if (GlobalVariable.SETTING_COUNTER > 5) GlobalVariable.SETTING_COUNTER = 1;
                GlobalVariable.CHANGE_TOKEN = true;
                //Log.i("SETTING_COUNTER",String.valueOf(SETTING_COUNTER));
            }
            if (JOBUSER_TOKEN) { //Job button in setting is pressed
                GlobalVariable.JOBBTN_COUNTER++;
                //Log.i("JOBBTN_COUNTER",String.valueOf(JOBBTN_COUNTER));
                if (GlobalVariable.JOBBTN_COUNTER > 17) GlobalVariable.JOBBTN_COUNTER = 17;
                JobsUser.changeBackground(GlobalVariable.JOBBTN_COUNTER);
            }
            if (KENN_TOKEN) { //Kennlinie button in setting is pressed
                GlobalVariable.KENNBTN_COUNTER++;
                //Log.i("KENNBTN_COUNTER",String.valueOf(KENNBTN_COUNTER));
                if (GlobalVariable.KENNBTN_COUNTER > 17) GlobalVariable.KENNBTN_COUNTER = 17;
                Kennlinier_user.changeKennBackground(GlobalVariable.KENNBTN_COUNTER);
            }
        } else { //Job button in home page is pressed
            GlobalVariable.JOBCOUNT++;
            //---------------------------- Activate Job ----------------------------------------
            if(GlobalVariable.JOBCOUNT==1) {
                DatenObjekteSend activateJob = new DatenObjekteSend();
                activateJob.ChangeParameter(5, 0, 1);
                //GlobalVariable.delayInMilli(200);
                //activateJob.ChangeParameter(4,0, 1);
            }else {
            //---------------------------Increment Job number---------------------------------------
            DatenObjekteSend incrementJob = new DatenObjekteSend();
            incrementJob.ChangeParameter(4,0, 1);
            //Log.i("incrementJob","is called");
            }
        }
    }

    public void decrementEncoder1(int val_encoder){
        GlobalVariable.encoder = true;
        //Log.i("decrementEncoder1","is called");

        if (GlobalVariable.JOB_PRESSED){
            //Log.i("JOB_PRESSED_COUNTER",String.valueOf(GlobalVariable.JOB_PRESSED_COUNTER));
            GlobalVariable.JOB_PRESSED_COUNTER--;
            DatenObjekteSend decrementJob1 = new DatenObjekteSend();
            decrementJob1.ChangeParameter(40, 0, 0);
            if(GlobalVariable.JOB_PRESSED_COUNTER < 0) GlobalVariable.JOB_PRESSED_COUNTER = 0;

        } else if(!GlobalVariable.JOB_NUM_TOKEN) { //Job button in home page is not pressed
            GlobalVariable.CONTROL_PANEL_MODE = 1;
             /*if ((DatenObjekte.SV1pos1 == 1) && (DatenObjekte.mpm_display > 8)) { //Normal
                 DatenObjekte.mpm_display = DatenObjekte.mpm_display - val_encoder; // m/min
             } else if ((DatenObjekte.SV1pos1 == 2) && (DatenObjekte.mpm_display > 40)) { //Synergie
                 DatenObjekte.mpm_display = DatenObjekte.mpm_display - val_encoder; // m/min
             } else if ((DatenObjekte.SV1pos1 == 3) && (DatenObjekte.mpm_display > 20)) { //Pulse
                 DatenObjekte.mpm_display = DatenObjekte.mpm_display - val_encoder; // m/min
             }*/
            switch(HOME_COUNTER){
                case 0:
                    if ((GlobalVariable.SV1pos1 == 1) && (GlobalVariable.Energie1 < 240)) { //Normal
                        GlobalVariable.mpm_display = GlobalVariable.Energie1 - val_encoder; // m/min
                        //progressBar.setProgress((int) (DatenObjekte.a_display) * (100 / 232) - (800 / 232));
                    } else if ((GlobalVariable.SV1pos1 == 2) && (GlobalVariable.mpm_display < 120)) { //Synergie
                        GlobalVariable.mpm_display = GlobalVariable.Energie1 - val_encoder; // m/min
                        //progressBar.setProgress((int) ((DatenObjekte.a_display) * 100 / 80 - 50));
                    } else if ((GlobalVariable.SV1pos1 == 3) && (GlobalVariable.mpm_display < 120)) {//Pulse
                        GlobalVariable.mpm_display = GlobalVariable.Energie1 - val_encoder; // m/min
                        //progressBar.setProgress(DatenObjekte.a_display - 20);
                    }
                    break;
                case 1:
                    GlobalVariable.mm_a_display = GlobalVariable.mirror_display - val_encoder; //mm
                    //progressBar.setProgress((mm_a_display) * (123 / 8) - 23);
                    break;
                case 2:
                    GlobalVariable.mm_a_display = GlobalVariable.mirror_display - val_encoder; //A
                    break;
                case 3:
                    GlobalVariable.korrektur_display = GlobalVariable.korrektur_display - val_encoder; //korrektur
                    if(GlobalVariable.korrektur_display<-30)GlobalVariable.korrektur_display = -30;
                    break;
                case 4: //verfahren
                    break;
                case 5:
                    GlobalVariable.gas_token = true; //gas
                    break;
                case 6:
                    GlobalVariable.werkstoff_token = true; //werkstoff
                    break;
                case 7:
                    GlobalVariable.Drahtdurchmesser_token = true; //Drahtdurchmesser
                    break;
                case 8:
                    GlobalVariable.voltage_display = GlobalVariable.voltage_display - val_encoder; //voltage
                    break;
                case 9:
                    GlobalVariable.job_token = true; //job
                    break;
            }

            //---------------------------------- Verfahren mode ------------------------------------
            if (ACTIVATE_VARFAHREN) {
                DatenObjekteSend sendObject = new DatenObjekteSend();
                sendObject.ChangeParameter(28,0,0); //activate verfahren mode
                //GlobalVariable.VERFAHREN_COUNTER++;
                //if (GlobalVariable.VERFAHREN_COUNTER > 4) GlobalVariable.VERFAHREN_COUNTER = 1;
            }

            if (GlobalVariable.SETTING_TOKEN) { //Setting button is pressed
                //Log.i(TAG,"Turn encoder");
                GlobalVariable.SETTING_COUNTER--;
                if (GlobalVariable.SETTING_COUNTER < 1) GlobalVariable.SETTING_COUNTER = 5;
                GlobalVariable.CHANGE_TOKEN = true;
                //Log.i("SETTING_COUNTER",String.valueOf(SETTING_COUNTER));
            }
            if (JOBUSER_TOKEN) { //Job button is pressed
                GlobalVariable.JOBBTN_COUNTER--;
                //Log.i("JOBBTN_COUNTER",String.valueOf(JOBBTN_COUNTER));
                if (GlobalVariable.JOBBTN_COUNTER < 1) GlobalVariable.JOBBTN_COUNTER = 0;
                JobsUser.changeBackground(GlobalVariable.JOBBTN_COUNTER);
            }
            if (KENN_TOKEN) { //Kennlinie button in setting is pressed
                GlobalVariable.KENNBTN_COUNTER--;
                //Log.i("KENNBTN_COUNTER",String.valueOf(KENNBTN_COUNTER));
                if (GlobalVariable.KENNBTN_COUNTER < 1) GlobalVariable.KENNBTN_COUNTER = 0;
                Kennlinier_user.changeKennBackground(GlobalVariable.KENNBTN_COUNTER);
            }
        } else { //Job button in home page is pressed
            //GlobalVariable.JOBCOUNT--;
            if(GlobalVariable.Jobnummer==1) GlobalVariable.JOBCOUNT = 0;
            //---------------------------Decrement Job number--------------------------------------
            DatenObjekteSend decrementJob = new DatenObjekteSend();
            decrementJob.ChangeParameter(6,0, 1);
            //Log.i("decrementJob","is called");
        }
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

    public void pressbuttonEncoder1() {
        GlobalVariable.ENCODER_PRESSED = true;
        //Log.i(TAG,"pressbuttonEncoder1 is pressed");
        if (!GlobalVariable.JOB_PRESSED) {
            DatenObjekteSend sendsth = new DatenObjekteSend();
            if (!HOME) {
                //Log.i("HOME_COUNTER",String.valueOf(HOME_COUNTER));
                HOME_COUNTER++;
                switch (GlobalVariable.VERFAHREN_VAL) {
                    case 1: //Normal mode
                        if (HOME_COUNTER == 1 || HOME_COUNTER == 2) HOME_COUNTER = 3;
                        else if(HOME_COUNTER == 7) HOME_COUNTER = 8;
                        break;
                    case 2: //Synergie mode
                        if (HOME_COUNTER == 8) HOME_COUNTER = 9;
                        break;
                    case 3: //Pulse mode
                        if (HOME_COUNTER == 8) HOME_COUNTER = 9;
                        break;
                    case 4: //Elektrode mode
                        if (HOME_COUNTER == 10 || HOME_COUNTER == 1) HOME_COUNTER = 2;
                        else if(HOME_COUNTER == 7 || HOME_COUNTER == 8) HOME_COUNTER = 9;
                        //Log.i("HOME_COUNTER", String.valueOf(HOME_COUNTER));
                        break;
                }
                //-------------------------------activate parameter mode--------------------------------
                switch (HOME_COUNTER) {
                    case 1:
                        sendsth.ChangeParameter(3, 0, 0); //activate drahtdurchmesser mode
                        break;
                    case 2:
                        sendsth.ChangeParameter(1, 0, 0); //activate strom mode
                        break;
                    case 3: //sendsth.ChangeParameter(9,DatenObjekte.mm_display,0); //activate korrektur mode
                        ACTIVATE_VARFAHREN = false;
                        break;
                    case 4: //sendsth.ChangeParameter(28,0,0); //Verfahren not necessary to send mode
                        ACTIVATE_VARFAHREN = true;
                        break;
                    case 5: //sendsth.ChangeParameter(9,DatenObjekte.mm_display,0);//Gas not necessary to send mode
                        ACTIVATE_VARFAHREN = false;
                        break;
                    case 6: //sendsth.ChangeParameter(9,DatenObjekte.mm_display,0);//Werkstoff not necessary
                        break;
                    case 7: //Drahtdurchmesser not necessary
                        break;
                    case 8: //sendsth.ChangeParameter(15,5,1);// activate voltage mode
                        break;
                    case 9: //sendsth.ChangeParameter(5,0,0); //activate job mode
                        GlobalVariable.JOB_NUM_TOKEN = true;
                        break;
                    case 10:
                        GlobalVariable.JOB_NUM_TOKEN = false;
                        sendsth.ChangeParameter(2, 0, 0); //activate energie mode
                        HOME_COUNTER = 0;
                        break;
                }
            } else ENCODERBUTTON_TOKEN = true;
        }
    }
}