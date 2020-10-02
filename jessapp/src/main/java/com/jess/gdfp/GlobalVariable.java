package com.jess.gdfp;

import android.graphics.Color;
import android.util.Log;

public class GlobalVariable {

    public static boolean  verfahren_gedrückt = false;
    public static int VERFAHREN_MODE = 4;

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
    public static boolean JOB_NUM_TOKEN = false;
    public static int CONTROL_PANEL_MODE = 0;
    public static int mm_a_display = 0;
    public static boolean gas_token = false;
    public static boolean werkstoff_token = false;
    public static boolean job_token = false;

    public static boolean SETTING_TOKEN = false;
    public static boolean CHANGE_TOKEN = false;
    public static int SETTING_COUNTER = 0;
    public static int JOBBTN_COUNTER = 0;
    public static int KENNBTN_COUNTER = 0;

    public static void delayInMilli(int DELAY_MILLISEC){
        try {
            Thread.sleep(DELAY_MILLISEC);
        }catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    public static void MMNormal_btn_onclick(){
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
    }

    public static void MMSynergy_btn_onclick(){
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

    public static void MMPuls_btn_onclick(){
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

    public static void ElektrodeMMA_btn_onclick(){
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

    /**
     * Send to Can multiple time
     * @param iteration
     * @param num number of parameters in DatenObjekteSend
     * @param value value of Value Frame
     * @param mode 0 or 1
     */
    public static void callChangeParameter(int iteration, int num, int value,int mode){
        DatenObjekteSend sendToDatenObjekte = new DatenObjekteSend();
        if(iteration==1){
            sendToDatenObjekte.ChangeParameter(num,value,mode);
        }else if(iteration==2){
            sendToDatenObjekte.ChangeParameter(num,value,mode);
            GlobalVariable.delayInMilli(100);
            sendToDatenObjekte.ChangeParameter(num,value,mode);
        }else if(iteration==3) {
            sendToDatenObjekte.ChangeParameter(num, value, mode);
            GlobalVariable.delayInMilli(100);
            sendToDatenObjekte.ChangeParameter(num, value, mode);
            GlobalVariable.delayInMilli(100);
            sendToDatenObjekte.ChangeParameter(num, value, mode);
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
}