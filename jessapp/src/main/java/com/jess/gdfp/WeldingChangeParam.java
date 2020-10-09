package com.jess.gdfp;

import android.util.Log;

import com.jess.gdfp.View.JobsUser;

import static com.jess.gdfp.View.Setting.JOBUSER_TOKEN;
import static com.jess.gdfp.View.Setting.KENN_TOKEN;

public class WeldingChangeParam {

    public void incrementEncoder1(int val_encoder){
        Log.i("incrementEncoder1","is called");

        if(!GlobalVariable.JOB_NUM_TOKEN) { //Job button in home page is not pressed
            //Log.i(TAG,"job not pressed");
            GlobalVariable.CONTROL_PANEL_MODE = 1;
            switch(GlobalVariable.HOME_COUNTER){
                case 0: if ((DatenObjekte.SV1pos1 == 1) && (DatenObjekte.mpm_display < 240)) { //Normal
                    GlobalVariable.mm_a_display = DatenObjekte.mpm_display + val_encoder; // m/min
                    //progressBar.setProgress((int) (DatenObjekte.a_display) * (100 / 232) - (800 / 232));
                } else if ((DatenObjekte.SV1pos1 == 2) && (DatenObjekte.mpm_display < 120)) { //Synergie
                    GlobalVariable.mm_a_display = DatenObjekte.mpm_display + val_encoder; // m/min
                    //progressBar.setProgress((int) ((DatenObjekte.a_display) * 100 / 80 - 50));
                } else if ((DatenObjekte.SV1pos1 == 3) && (DatenObjekte.mpm_display < 120)) {//Pulse
                    GlobalVariable.mm_a_display = DatenObjekte.mpm_display + val_encoder; // m/min
                    //progressBar.setProgress(DatenObjekte.a_display - 20);
                }
                    break;
                case 1: GlobalVariable.mm_a_display = DatenObjekte.a_display + val_encoder; //mm
                    //progressBar.setProgress((mm_a_display) * (123 / 8) - 23);
                    break;
                case 2: GlobalVariable.mm_a_display = DatenObjekte.a_display + val_encoder; //A
                    break;
                case 3: //DatenObjekte.korrektur_display = DatenObjekte.korrektur_display + val_encoder; //korrektur
                    break;
                case 4: //verfahren
                    break;
                case 5: GlobalVariable.gas_token = true; //gas
                    break;
                case 6: GlobalVariable.werkstoff_token = true; //gas
                    break;
                case 7: GlobalVariable.job_token = true; //job
                    break;
            }

            //---------------------------------- Verfahren mode ------------------------------------
            if (GlobalVariable.ACTIVATE_VARFAHREN) {
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
            //---------------------------Increment Job number---------------------------------------
            DatenObjekteSend incrementJob = new DatenObjekteSend();
            incrementJob.ChangeParameter(4,0, 1);
            //Log.i("incrementJob","is called");
        }
    }

    public void decrementEncoder1(int val_encoder){
        if(!GlobalVariable.JOB_NUM_TOKEN) { //Job button in home page is not pressed
            GlobalVariable.CONTROL_PANEL_MODE = 1;
             /*if ((DatenObjekte.SV1pos1 == 1) && (DatenObjekte.mpm_display > 8)) { //Normal
                 DatenObjekte.mpm_display = DatenObjekte.mpm_display - val_encoder; // m/min
             } else if ((DatenObjekte.SV1pos1 == 2) && (DatenObjekte.mpm_display > 40)) { //Synergie
                 DatenObjekte.mpm_display = DatenObjekte.mpm_display - val_encoder; // m/min
             } else if ((DatenObjekte.SV1pos1 == 3) && (DatenObjekte.mpm_display > 20)) { //Pulse
                 DatenObjekte.mpm_display = DatenObjekte.mpm_display - val_encoder; // m/min
             }*/
            switch(GlobalVariable.HOME_COUNTER){
                case 0: if ((DatenObjekte.SV1pos1 == 1) && (DatenObjekte.mpm_display < 240)) { //Normal
                    GlobalVariable.mm_a_display = DatenObjekte.a_display - val_encoder; // m/min
                    //progressBar.setProgress((int) (DatenObjekte.a_display) * (100 / 232) - (800 / 232));
                } else if ((DatenObjekte.SV1pos1 == 2) && (DatenObjekte.mpm_display < 120)) { //Synergie
                    GlobalVariable.mm_a_display = DatenObjekte.a_display - val_encoder; // m/min
                    //progressBar.setProgress((int) ((DatenObjekte.a_display) * 100 / 80 - 50));
                } else if ((DatenObjekte.SV1pos1 == 3) && (DatenObjekte.mpm_display < 120)) {//Pulse
                    GlobalVariable.mm_a_display = DatenObjekte.a_display - val_encoder; // m/min
                    //progressBar.setProgress(DatenObjekte.a_display - 20);
                }
                    break;
                case 1: GlobalVariable.mm_a_display = DatenObjekte.a_display - val_encoder; //mm
                    //progressBar.setProgress((mm_a_display) * (123 / 8) - 23);
                    break;
                case 2: GlobalVariable.mm_a_display = DatenObjekte.a_display - val_encoder; //A
                    break;
                case 3: //DatenObjekte.korrektur_display = DatenObjekte.korrektur_display + val_encoder; //korrektur
                    break;
                case 4: //verfahren
                    break;
                case 5: GlobalVariable.gas_token = true; //gas
                    break;
                case 6: GlobalVariable.werkstoff_token = true; //werkstoff
                    break;
                case 7: GlobalVariable.job_token = true; //job
                    break;
            }

            //---------------------------------- Verfahren mode ------------------------------------
            if (GlobalVariable.ACTIVATE_VARFAHREN) {
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
            //---------------------------Decrement Job number--------------------------------------
            DatenObjekteSend decrementJob = new DatenObjekteSend();
            decrementJob.ChangeParameter(6,0, 1);
            //Log.i("decrementJob","is called");
        }
    }

}
