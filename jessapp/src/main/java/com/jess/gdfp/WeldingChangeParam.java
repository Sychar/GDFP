package com.jess.gdfp;

import android.util.Log;

import com.jess.gdfp.View.BlankFragment;
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
    private static boolean job_select = false;
    private static int menucount = 0;
    private static int settingcount = 0;
    private DatenObjekteSend ChangeDrossel = new DatenObjekteSend();
    private DatenObjekteSend ChangeVerfahren = new DatenObjekteSend();

    public void incrementEncoder1(int val_encoder){ //middle
        GlobalVariable.encoder = true;
        //System.out.println("its here");
        if (GlobalVariable.JOB_PRESSED) { //job button is pressed
            if (GlobalVariable.INSIDE_JOB) { //LOAD and SAVE scrollchoice
                if (!job_select) { //SAVE
                    //System.out.println("here");
                    GlobalVariable.Job_Token = true;
                    BlankFragment.sc.addItems(BlankFragment.detail, 1);
                    job_select = true;
                } else if (job_select) { //LOAD
                    //System.out.println("here 2");
                    GlobalVariable.Job_Token = true;
                    BlankFragment.sc.addItems(BlankFragment.detail, 0);
                    job_select = false;
                }
            } else {
                GlobalVariable.JOB_PRESSED_COUNTER++;
                DatenObjekteSend incrementJob = new DatenObjekteSend();
                if (GlobalVariable.Load_Job) incrementJob.ChangeParameter(4, 0, 0);
                if (GlobalVariable.Save_Job) incrementJob.ChangeParameter(39, 0, 0);
                if (GlobalVariable.JOB_PRESSED_COUNTER > 100)
                    GlobalVariable.JOB_PRESSED_COUNTER = 1;
            }
        } else if (!GlobalVariable.JOB_NUM_TOKEN) { //Job button_left in home page is not pressed
            //System.out.println("its here");

            if (GlobalVariable.INSIDE_MENU) {
                //Log.i("INSIDE_MENU","here");
                if (menucount == 0) { //DATENLOGGER
                    //Log.i("INSIDE_MENU","here 1");
                    BlankFragment.sc.addItems(BlankFragment.detail, 1);
                    menucount = 1;
                } else if (menucount == 1) { //KENNLINIE
                    //Log.i("INSIDE_MENU","here 2");
                    BlankFragment.sc.addItems(BlankFragment.detail, 2);
                    menucount = 2;
                } else if (menucount == 2) { //JOBS
                    //Log.i("INSIDE_MENU","here 3");
                    BlankFragment.sc.addItems(BlankFragment.detail, 0);
                    menucount = 0;
                }
            } else if (GlobalVariable.INSIDE_SETTING) {
                //Log.i("INSIDE_SETTING","here");
                if (settingcount == 0) { //DISPLAY
                    //Log.i("INSIDE_MENU","here 1");
                    BlankFragment.sc.addItems(BlankFragment.detail, 1);
                    settingcount = 1;
                } else if (settingcount == 1) { //LANGUAGE
                    //Log.i("INSIDE_MENU","here 2");
                    BlankFragment.sc.addItems(BlankFragment.detail, 2);
                    settingcount = 2;
                } else if (settingcount == 2) { //UPDATE
                    //Log.i("INSIDE_MENU","here 3");
                    BlankFragment.sc.addItems(BlankFragment.detail, 3);
                    settingcount = 3;
                } else if (settingcount == 3) { //DATE TIME
                    //Log.i("INSIDE_MENU","here 3");
                    BlankFragment.sc.addItems(BlankFragment.detail, 0);
                    settingcount = 0;
                }
            } else {
                if (GlobalVariable.Methode_Verfahren) ChangeVerfahren.ChangeParameter(28, 5, 0);
                else if (GlobalVariable.Methode_Werkstoff) ChangeVerfahren.ChangeParameter(44, 0, 0);
                else if (GlobalVariable.Methode_Drahtdurchmesser)
                    ChangeVerfahren.ChangeParameter(41, 0, 0);
                else if (GlobalVariable.Methode_Betriebsart) ChangeVerfahren.ChangeParameter(42, 0, 0);
                else if (GlobalVariable.Methode_Gas) ChangeVerfahren.ChangeParameter(10, 0, 1);
                else {
                    //Log.i(TAG,"job not pressed");
                    GlobalVariable.CONTROL_PANEL_MODE = 1;
                    switch (HOME_COUNTER) {
                        case 0: /*if ((GlobalVariable.SV1pos1 == 1) && (GlobalVariable.Energie1 < 240)) { //Normal
                        GlobalVariable.mpm_display = GlobalVariable.Energie1 + val_encoder; // m/min
                        //progressBar.setProgress((int) (DatenObjekte.a_display) * (100 / 232) - (800 / 232));
                    } else if ((GlobalVariable.SV1pos1 == 2) && (GlobalVariable.Energie1 < 120)) { //Synergie
                        GlobalVariable.mpm_display = GlobalVariable.Energie1 + val_encoder; // m/min
                        //progressBar.setProgress((int) ((DatenObjekte.a_display) * 100 / 80 - 50));
                    } else if ((GlobalVariable.SV1pos1 == 3) && (GlobalVariable.Energie1 < 120)) {//Pulse
                        GlobalVariable.mpm_display = GlobalVariable.Energie1 + val_encoder; // m/min
                        //progressBar.setProgress(DatenObjekte.a_display - 20);
                    }*/
                            GlobalVariable.MAX_MPM = 200;
                            if ((GlobalVariable.SV1pos1 != 4) && (GlobalVariable.Energie1 <= GlobalVariable.MAX_MPM)) { //Except Elektrode
                                GlobalVariable.mpm_display = GlobalVariable.Energie1 + val_encoder; // m/min
                                if (GlobalVariable.mpm_display > GlobalVariable.MAX_MPM)
                                    GlobalVariable.mpm_display = GlobalVariable.MAX_MPM;
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
                            GlobalVariable.korrektur_display = GlobalVariable.korrektur_display + val_encoder; //lichtbogenkorrektur
                            if (GlobalVariable.korrektur_display > 30)
                                GlobalVariable.korrektur_display = 30;
                            break;
                        case 4:
                            //GlobalVariable.Drossel_token = true;
                            int drossel_val = GlobalVariable.KorrekturDrossel;
                            drossel_val++;
                            //Log.i("drossel_val",String.valueOf(drossel_val));
                            ChangeDrossel.ChangeParameter(43, drossel_val, 1);
                            break;
                        case 5:
                            GlobalVariable.voltage_display = GlobalVariable.voltage_display + val_encoder; //voltage
                            break;
                    }
                }
                //---------------------------------- Verfahren mode ------------------------------------
                if (ACTIVATE_VARFAHREN) {
                    DatenObjekteSend sendObject = new DatenObjekteSend();
                    sendObject.ChangeParameter(28, 0, 0); //activate verfahren mode
                    //GlobalVariable.VERFAHREN_COUNTER++;
                    //if (GlobalVariable.VERFAHREN_COUNTER > 4) GlobalVariable.VERFAHREN_COUNTER = 1;
                }

                if (GlobalVariable.SETTING_TOKEN) { //Setting button_left is pressed
                    //Log.i(TAG,"Turn encoder");
                    GlobalVariable.SETTING_COUNTER++;
                    if (GlobalVariable.SETTING_COUNTER > 5) GlobalVariable.SETTING_COUNTER = 1;
                    GlobalVariable.CHANGE_TOKEN = true;
                }
                if (JOBUSER_TOKEN) { //Job button_left in setting is pressed
                    GlobalVariable.JOBBTN_COUNTER++;
                    if (GlobalVariable.JOBBTN_COUNTER > 17) GlobalVariable.JOBBTN_COUNTER = 17;
                    JobsUser.changeBackground(GlobalVariable.JOBBTN_COUNTER);
                }
                if (KENN_TOKEN) { //Kennlinie button_left in setting is pressed
                    GlobalVariable.KENNBTN_COUNTER++;
                    if (GlobalVariable.KENNBTN_COUNTER > 17) GlobalVariable.KENNBTN_COUNTER = 17;
                    Kennlinier_user.changeKennBackground(GlobalVariable.KENNBTN_COUNTER);
                }
            }
        } else { //Job button_left in home page is pressed
            GlobalVariable.JOBCOUNT++;
            if (GlobalVariable.JOB_MODE == 1 && GlobalVariable.JOBCOUNT == 1) {
                //---------------------------- Start Job ----------------------------------------
                DatenObjekteSend startJob = new DatenObjekteSend();
                startJob.ChangeParameter(20, 0, 0);
            } else {
                //---------------------------Increment Job number---------------------------------------
                DatenObjekteSend incrementJob = new DatenObjekteSend();
                incrementJob.ChangeParameter(4, 0, 1);
                //Log.i("incrementJob","is called");
            }
        }
    }

    public void decrementEncoder1(int val_encoder){ //middle
        GlobalVariable.encoder = true;
        if (GlobalVariable.JOB_PRESSED){
            if (GlobalVariable.INSIDE_JOB) { //LOAD and SAVE scrollchoice
                if (!job_select) { //SAVE
                    GlobalVariable.Job_Token = true;
                    job_select = true;
                    BlankFragment.sc.addItems(BlankFragment.detail, 1);
                    //System.out.println("here");
                } else { //LOAD
                    GlobalVariable.Job_Token = true;
                    job_select = false;
                    BlankFragment.sc.addItems(BlankFragment.detail, 0);
                    //System.out.println("here 2");
                }
            } else {
                //Log.i("JOB_PRESSED_COUNTER",String.valueOf(GlobalVariable.JOB_PRESSED_COUNTER));
                GlobalVariable.JOB_PRESSED_COUNTER--;
                DatenObjekteSend decrementJob1 = new DatenObjekteSend();
                if (GlobalVariable.Load_Job) decrementJob1.ChangeParameter(6, 0, 0);
                if (GlobalVariable.Save_Job) decrementJob1.ChangeParameter(40, 0, 0);
                if (GlobalVariable.JOB_PRESSED_COUNTER < 0) GlobalVariable.JOB_PRESSED_COUNTER = 0;
            }
        } else if(!GlobalVariable.JOB_NUM_TOKEN) { //Job button_left in home page is not pressed
            if (GlobalVariable.INSIDE_MENU) {
                //Log.i("INSIDE_MENU", "here");
                if (menucount == 0) { //DATENLOGGER
                    //Log.i("INSIDE_MENU","here 1");
                    BlankFragment.sc.addItems(BlankFragment.detail, 2);
                    menucount = 2;
                } else if (menucount == 1) { //KENNLINIE
                    //Log.i("INSIDE_MENU","here 2");
                    BlankFragment.sc.addItems(BlankFragment.detail, 0);
                    menucount = 0;
                } else if (menucount == 2) { //JOBS
                    //Log.i("INSIDE_MENU","here 3");
                    BlankFragment.sc.addItems(BlankFragment.detail, 1);
                    menucount = 1;
                }
            } else if (GlobalVariable.INSIDE_SETTING) {
                //Log.i("INSIDE_SETTING","here");
                if (settingcount == 0) { //UPDATE
                    //Log.i("INSIDE_MENU","here 1");
                    BlankFragment.sc.addItems(BlankFragment.detail, 3);
                    settingcount = 3;
                } else if (settingcount == 1) { //DATE TIME
                    //Log.i("INSIDE_MENU","here 2");
                    BlankFragment.sc.addItems(BlankFragment.detail, 0);
                    settingcount = 0;
                } else if (settingcount == 2) { //DISPLAY
                    //Log.i("INSIDE_MENU","here 3");
                    BlankFragment.sc.addItems(BlankFragment.detail, 1);
                    settingcount = 1;
                } else if (settingcount == 3) { //LANGUAGE
                    //Log.i("INSIDE_MENU","here 3");
                    BlankFragment.sc.addItems(BlankFragment.detail, 2);
                    settingcount = 2;
                }
            } else {
                GlobalVariable.CONTROL_PANEL_MODE = 1;
                switch (HOME_COUNTER) {
                    case 0:
                    /*if ((GlobalVariable.SV1pos1 == 1) && (GlobalVariable.Energie1 > 8)) { //Normal
                        GlobalVariable.mpm_display = GlobalVariable.Energie1 - val_encoder; // m/min
                        //progressBar.setProgress((int) (DatenObjekte.a_display) * (100 / 232) - (800 / 232));
                    } else if ((GlobalVariable.SV1pos1 == 2) && (GlobalVariable.Energie1 <= 120)) { //Synergie
                        GlobalVariable.mpm_display = GlobalVariable.Energie1 - val_encoder; // m/min
                        Log.i("decrease encoder",String.valueOf(GlobalVariable.mpm_display));
                        //progressBar.setProgress((int) ((DatenObjekte.a_display) * 100 / 80 - 50));
                    } else if ((GlobalVariable.SV1pos1 == 3) && (GlobalVariable.Energie1 <= 120)) {//Pulse
                        GlobalVariable.mpm_display = GlobalVariable.Energie1 - val_encoder; // m/min
                        //progressBar.setProgress(DatenObjekte.a_display - 20);
                    }*/
                        GlobalVariable.MIN_MPM = 0;
                        if ((GlobalVariable.SV1pos1 != 4) && (GlobalVariable.mpm_display >= GlobalVariable.MIN_MPM + 1)) { //Except Elektrode
                            GlobalVariable.mpm_display = GlobalVariable.Energie1 - val_encoder; // m/min
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
                        if (GlobalVariable.korrektur_display < -30)
                            GlobalVariable.korrektur_display = -30;
                        break;
                    case 4:
                        //GlobalVariable.Drossel_token = true;
                        int drossel_val = GlobalVariable.KorrekturDrossel;
                        drossel_val--;
                        //Log.i("drossel_val",String.valueOf(drossel_val));
                        ChangeDrossel.ChangeParameter(43, drossel_val, 1);
                        break;
                    case 5:
                        GlobalVariable.voltage_display = GlobalVariable.voltage_display - val_encoder; //voltage
                        break;
                }
                //---------------------------------- Verfahren mode ------------------------------------
                if (ACTIVATE_VARFAHREN) {
                    DatenObjekteSend sendObject = new DatenObjekteSend();
                    sendObject.ChangeParameter(28, 0, 0); //activate verfahren mode
                    //GlobalVariable.VERFAHREN_COUNTER++;
                    //if (GlobalVariable.VERFAHREN_COUNTER > 4) GlobalVariable.VERFAHREN_COUNTER = 1;
                }

                if (GlobalVariable.SETTING_TOKEN) { //Setting button_left is pressed
                    GlobalVariable.SETTING_COUNTER--;
                    if (GlobalVariable.SETTING_COUNTER < 1) GlobalVariable.SETTING_COUNTER = 5;
                    GlobalVariable.CHANGE_TOKEN = true;
                    //Log.i("SETTING_COUNTER",String.valueOf(SETTING_COUNTER));
                }
                if (JOBUSER_TOKEN) { //Job button_left is pressed
                    GlobalVariable.JOBBTN_COUNTER--;
                    if (GlobalVariable.JOBBTN_COUNTER < 1) GlobalVariable.JOBBTN_COUNTER = 0;
                    JobsUser.changeBackground(GlobalVariable.JOBBTN_COUNTER);
                }
                if (KENN_TOKEN) { //Kennlinie button_left in setting is pressed
                    GlobalVariable.KENNBTN_COUNTER--;
                    //Log.i("KENNBTN_COUNTER",String.valueOf(KENNBTN_COUNTER));
                    if (GlobalVariable.KENNBTN_COUNTER < 1) GlobalVariable.KENNBTN_COUNTER = 0;
                    Kennlinier_user.changeKennBackground(GlobalVariable.KENNBTN_COUNTER);
                }
            }
        } else { //Job button_left in home page is pressed
            if(GlobalVariable.Jobnummer==1) GlobalVariable.JOBCOUNT = 0;
            //---------------------------Decrement Job number--------------------------------------
            DatenObjekteSend decrementJob = new DatenObjekteSend();
            decrementJob.ChangeParameter(6, 0, 1);
            //Log.i("decrementJob","is called")
        }
    }

    public void pressMenu(){ MENU_TOKEN = true; }

    public void pressJob(){TEST_TOKEN = true; }

    public void pressHome(){ HOME_TOKEN = true; }

    public void pressDroessel(){ DROSSEL_TOKEN = true; }

    public void pressDaten(){ DATEN_TOKEN = true; }

    public void pressVerfahren(){ VERFAHREN_TOKEN = true; }

    public void pressKennlinie(){ KENNLINIE_TOKEN = true; }

    public void pressBetriebsart(){ BETRIEBSART_TOKEN = true; }

    public void pressbuttonEncoder1() { //middle
        if (!GlobalVariable.JOB_PRESSED) { //job button is not pressed
            DatenObjekteSend sendsth = new DatenObjekteSend();
            if (!HOME) {
                HOME_COUNTER++;
                switch (GlobalVariable.VERFAHREN_VAL) {
                    case 1: //Normal mode
                        if (HOME_COUNTER == 1 || HOME_COUNTER == 2 || HOME_COUNTER == 3) HOME_COUNTER = 4;
                        break;
                    case 2: //Synergie mode
                        if (HOME_COUNTER == 5) HOME_COUNTER = 6;
                        break;
                    case 3: //Puls mode
                        if (HOME_COUNTER == 5) HOME_COUNTER = 6;
                        break;
                }
                //------------------------- When Kennlinie button_left is active ------------------------
                /*if (MainActivity.kenn_token) { // if button_left is pressed
                    //---------------- skip verfahren,gas,werkstoff and drahtdurchmesser -----------
                    if (HOME_COUNTER == 4 || HOME_COUNTER == 5 ||
                            HOME_COUNTER == 6 || HOME_COUNTER == 7) HOME_COUNTER = 8;
                }*/

                //-------------------------------activate parameter mode----------------------------
                switch (HOME_COUNTER) {
                    case 1:
                        sendsth.ChangeParameter(3, 0, 0); //activate Blechdicke mode (mm)
                        break;
                    case 2:
                        sendsth.ChangeParameter(1, 0, 0); //activate strom mode
                        break;
                    case 3: //activate lichtbogenkorrektur mode
                        break;
                    case 4: //activate korrektur drossel
                        break;
                    case 5: //sendsth.ChangeParameter(15,5,1);// activate voltage mode
                        break;
                    case 6:
                        sendsth.ChangeParameter(2, 0, 0); //activate energie mode (m/min)
                        HOME_COUNTER = 0;
                        break;
                }
            } else ENCODERBUTTON_TOKEN = true;
        } else { //job button is pressed
            if (GlobalVariable.INSIDE_JOB) { //LOAD and SAVE scrollchoice
                GlobalVariable.Job_Token = true;
                if (job_select) { //SAVE
                    GlobalVariable.Save_Job = true;
                    GlobalVariable.Load_Job = false;
                } else { //LOAD
                    GlobalVariable.Load_Job = true;
                    GlobalVariable.Save_Job = false;
                }
                GlobalVariable.INSIDE_JOB = false;
            } else GlobalVariable.ENCODER_PRESSED = true; //out of LOAD and SAVE scrollchoice
        }
    }
}