package com.jess.gdfp;

import android.util.Log;

public class DatenObjekteSend {

    private final static String TAG = DatenObjekteSend.class.getSimpleName(); //name of this class

    private byte VALTOKEN;//1(yes)
    private byte PARAMTOKEN;//1(yes)
    private byte FRAMEVAL;
    private byte FRAMEEXTRA;
    private double FIRVAL;
    private int SECVAL;
    private int THDVAL;
    private double Mm;

    public void ChangeParameter(int num,int value,int mode){

        switch(num){
            case 1: //Strom
                FRAMEVAL = 0;
                FRAMEEXTRA = 34;//22H
                FIRVAL = value&0xFF;//lsb
                SECVAL = (value>>8)&0xFF;//msb
                THDVAL = 51; //33H
                if(mode==0) {
                    PARAMTOKEN = 1;
                    VALTOKEN = 0;
                }else{
                    PARAMTOKEN = 0;
                    VALTOKEN = 1;
                }
                //PARAMTOKEN = 1;
                //VALTOKEN= 1;
                break;
            case 2: //Energie (m/min)
                //Log.i("Change",String.valueOf(value));
                FRAMEVAL = 1;
                FRAMEEXTRA = 34;
                FIRVAL = value&0xFF;//lsb
                SECVAL = 0;//msb
                THDVAL = 51; //33H
                if(mode==0) {
                    PARAMTOKEN = 1;
                    VALTOKEN = 0;
                }else{
                    PARAMTOKEN = 0;
                    VALTOKEN = 1;
                }
                //PARAMTOKEN = 1;
                //VALTOKEN= 1;
                break;
            case 3: //mm
                //Log.i("DatenObjekte.a_display","Debug");
                FRAMEVAL = 2;
                FRAMEEXTRA = 34;
                //Mm = ((float) value) /10.0 ;
                //Log.i("Mn: " , String.valueOf(Mm));
                FIRVAL = value & 0xFF;//lsb
                SECVAL = 0;//msb
                THDVAL = 51; //33H
                if(mode==0) {
                    PARAMTOKEN = 1;
                    VALTOKEN = 0;
                }else{
                    PARAMTOKEN = 0;
                    VALTOKEN = 1;
                }
                break;
            case 4: //job(no value) increment
                //Log.i("Change","Job");
                FRAMEVAL = 1;
                FRAMEEXTRA = 11; //0x0B
                FIRVAL = 0;
                SECVAL = 0;
                THDVAL = 0;
                PARAMTOKEN = 1;
                VALTOKEN= 0; // no value id
                break;
            case 5: //job activate
                //Log.i(TAG,"activate job");
                FRAMEVAL = 5;
                FRAMEEXTRA = 11; //0x0B
                FIRVAL = 0;
                SECVAL = 0;
                THDVAL = 0;
                PARAMTOKEN = 1;
                VALTOKEN= 0; // no value id
                break;
            case 6: //job deactivate/decrement
                FRAMEVAL = 3;
                FRAMEEXTRA = 11; //0x0B
                FIRVAL = 0;
                SECVAL = 0;
                THDVAL = 0;
                PARAMTOKEN = 1;
                VALTOKEN= 0; // no value id
                break;
            case 7: //spannung
                FRAMEVAL = 4;
                FRAMEEXTRA = 0;
                FIRVAL = value&0xFF;//lsb
                SECVAL = (value>>8)&0xFF;//msb
                THDVAL = 50;//32H
                PARAMTOKEN = 1;
                VALTOKEN= 1;
                break;
            case 8: //Drossel
                FRAMEVAL = 4;
                FRAMEEXTRA = 0;
                FIRVAL = value&0xFF;//lsb
                SECVAL = 0;//msb
                THDVAL = 52;//34H
                PARAMTOKEN = 1;
                VALTOKEN= 1;
                break;
            case 9://Lichtbogenkorr(bei 2 Takt)
                FRAMEVAL = 2;
                FRAMEEXTRA = 0;
                FIRVAL = value&0xFF;//lsb
                SECVAL = (value>>8)&0xFF;//msb
                THDVAL = 53;//35H
                if(mode==0) {
                    PARAMTOKEN = 1;
                    VALTOKEN = 0;
                }else{
                    PARAMTOKEN = 0;
                    VALTOKEN = 1;
                }
                break;
            case 10://CrNi(bei synergie)
                FRAMEVAL = 0;
                FRAMEEXTRA = 0;
                FIRVAL = 1;//lsb
                SECVAL = 0;//msb
                THDVAL = 4;//04H
                if(mode==1) {
                    PARAMTOKEN = 0;//no param id
                    VALTOKEN = 1;
                }
                break;
            case 11://AlMg(bei synergie)
                FRAMEVAL = 0;
                FRAMEEXTRA = 0;
                FIRVAL = 2;//lsb
                SECVAL = 0;//msb
                THDVAL = 4;
                if(mode==1) {
                    PARAMTOKEN = 0;//no param id
                    VALTOKEN = 1;
                }
                break;
            case 12://AlSi(bei synergie)
                FRAMEVAL = 0;
                FRAMEEXTRA = 0;
                FIRVAL = 3;//lsb
                SECVAL = 0;//msb
                THDVAL = 4;
                if(mode==1) {
                    PARAMTOKEN = 0;//no param id
                    VALTOKEN = 1;
                }
                break;
            case 13://Spz(bei synergie)
                FRAMEVAL = 0;
                FRAMEEXTRA = 0;
                FIRVAL = 4;//lsb
                SECVAL = 0;//msb
                THDVAL = 4;
                if(mode==1) {
                    PARAMTOKEN = 0;//no param id
                    VALTOKEN = 1;
                }
                break;
            case 14://Fe(bei synergie)
                FRAMEVAL = 0;
                FRAMEEXTRA = 0;
                FIRVAL = 10;//lsb
                SECVAL = 0;//msb
                THDVAL = 4;
                if(mode==1) {
                    PARAMTOKEN = 0;//no param id
                    VALTOKEN = 1;
                }
                break;
            case 15: //voltage
                FRAMEVAL = 0;
                FRAMEEXTRA = 0;
                FIRVAL = value&0xFF;//lsb
                SECVAL = 0;//msb
                THDVAL = 52;//34H
                PARAMTOKEN = 0;
                VALTOKEN= 1;
                break;
            case 16: //A
                FRAMEVAL = 0;
                FRAMEEXTRA = 0;
                FIRVAL = value&0xFF;//lsb
                SECVAL = 0;//msb
                THDVAL = 34;//22H
                PARAMTOKEN = 0;
                VALTOKEN= 1;
                break;
            case 17: //mm
                FRAMEVAL = 0;
                FRAMEEXTRA = 0;
                FIRVAL = 0;//lsb
                SECVAL = 0;//msb
                THDVAL = 2;//2H
                PARAMTOKEN = 0;
                VALTOKEN= 1;
                break;
            case 18: //m/min
                FRAMEVAL = 0;
                FRAMEEXTRA = 0;
                FIRVAL = 0;//lsb
                SECVAL = 0;//msb
                THDVAL = 1;//2H
                PARAMTOKEN = 0;
                VALTOKEN= 1;
                break;
            case 19: // %
                FRAMEVAL = 0;
                FRAMEEXTRA = 0;
                FIRVAL = value&0xFF;//lsb
                SECVAL = 0;//msb
                THDVAL = 50;//32H
                PARAMTOKEN = 0;
                VALTOKEN= 1;
                break;
            case 20: // job change
                FRAMEVAL = 11;
                FRAMEEXTRA =11;
                FIRVAL = 0;//lsb
                SECVAL = 0;//msb
                THDVAL = 0;//bH
                PARAMTOKEN = 1;
                VALTOKEN= 0;
                break;

            case 21: //Lichtbogenkorrektur(bei synergie)
                FRAMEVAL = 0;
                FRAMEEXTRA = 0;
                FIRVAL = value&0xFF;//lsb
                SECVAL = (value>>8)&0xFF;//msb
                THDVAL = 53;//35H
                PARAMTOKEN = 0;
                VALTOKEN = 1;
                break;
            case 22: //energie 1(bei synergie)
                FRAMEVAL = 1;
                FRAMEEXTRA = 0;
                FIRVAL = value&0xFF;//lsb
                SECVAL = (value>>8)&0xFF;//msb
                THDVAL = 27;//1BH
                PARAMTOKEN = 1;
                VALTOKEN = 1;
                break;

            case 23: //Draht(bei synergie)
                FRAMEVAL = 0;
                FRAMEEXTRA = 0;
                FIRVAL = value&0xFF;
                SECVAL = 0;
                THDVAL = 2;
                PARAMTOKEN = 0;
                VALTOKEN = 1;
                break;
            case 24: //Elekto(bei 2.Pic)
                FRAMEVAL = 0;
                FRAMEEXTRA = 0;
                FIRVAL = value&0xFF;
                SECVAL = 0;
                THDVAL = 89;//59H
                PARAMTOKEN = 0;
                VALTOKEN = 1;
                break;
            case 25: //Synergie Verfahren
                FRAMEVAL = 1;
                FRAMEEXTRA = 0;
                //FIRVAL = 0;
                FIRVAL = value&0xFF;
                SECVAL = 0;
                //THDVAL = 0;
                THDVAL = 51; //0x33
                PARAMTOKEN = 1;
                //VALTOKEN = 0;//no value id
                if(mode == 0) VALTOKEN = 0;
                else VALTOKEN = 1;
                break;
            case 26: //2 Takt
                FRAMEVAL = 2;
                FRAMEEXTRA = 0;
                FIRVAL = 0;
                SECVAL = 0;
                THDVAL = 0;
                PARAMTOKEN = 1;
                VALTOKEN = 0;//no value id
                break;
            case 27: //2. Pic
                FRAMEVAL = 3;
                FRAMEEXTRA = 0;
                FIRVAL = 0;
                SECVAL = 0;
                THDVAL = 0;
                PARAMTOKEN = 1;
                VALTOKEN = 0;//no value id
                break;
            case 28: //Normal Verfahren
                FRAMEVAL = 4;
                FRAMEEXTRA = 0;
                FIRVAL = value&0xFF;
                //FIRVAL = 0;
                SECVAL = 0;
                //THDVAL = 0;
                THDVAL = 51; //0x33
                PARAMTOKEN = 1;
                //VALTOKEN = 0;//no value id
                if(mode == 0) VALTOKEN = 0;
                else VALTOKEN = 1;
                break;
            case 29: //Puls Verfahren
                FRAMEVAL = 2;
                FRAMEEXTRA = 0;
                FIRVAL = value&0xFF;
                SECVAL = 0;
                THDVAL = 51; //0x33
                PARAMTOKEN = 1;
                if(mode == 0) VALTOKEN = 0;
                else VALTOKEN = 1;
                break;
            case 30: //Elektrode Verfahren
                FRAMEVAL = 3;
                FRAMEEXTRA = 0;
                FIRVAL = value&0xFF;
                SECVAL = 0;
                THDVAL = 89; //0x59
                PARAMTOKEN = 1;
                if(mode == 0) VALTOKEN = 0;
                else VALTOKEN = 1;
                break;
            case 32: //volt(bei norm)
                FRAMEVAL = 0;
                FRAMEEXTRA = 0;
                FIRVAL = value&0xFF;//lsb
                SECVAL = (value>>8)&0xFF;//msb
                THDVAL = 50;//32H
                PARAMTOKEN = 0;
                VALTOKEN = 1;
                break;
            case 33: //Verfahren(bei norm)
                FRAMEVAL = 0;
                FRAMEEXTRA = 0;
                FIRVAL = value&0xFF;//01/02/03/05
                SECVAL = 0;
                THDVAL = 1;
                PARAMTOKEN = 0;
                VALTOKEN = 1;
                break;
            case 34: //Energie2
                FRAMEVAL = 48;//30H
                FRAMEEXTRA = 0;
                FIRVAL = 30;//1EH
                SECVAL = 0;
                THDVAL = 0;
                PARAMTOKEN = 1;
                VALTOKEN = 1;
                break;
            case 35: //Lichtbogenkorr (bei 2 takt blinking)
                FRAMEVAL = 0;
                FRAMEEXTRA = 0;
                FIRVAL = value&0xFF;
                SECVAL = 0;
                THDVAL = 26;//1AH
                PARAMTOKEN = 0;
                VALTOKEN = 1;
                break;
            case 36: //Lichtbogenkorr2 (bei 2 takt)
                FRAMEVAL = 0;
                FRAMEEXTRA = 0;
                FIRVAL = value&0xFF;//lsb
                SECVAL = (value>>>8)&0xFF;//msb
                THDVAL = 53;//35H
                PARAMTOKEN = 0;
                VALTOKEN = 1;
                break;
            case 37: //store job
                FRAMEVAL = (byte) (value&0xFF);
                FRAMEEXTRA = 11;
                FIRVAL = 0;
                SECVAL = 0;
                THDVAL = 0;
                PARAMTOKEN = 1;
                VALTOKEN= 0; // no value id
                break;
            case 38: //Strom (Elektrode)
                FRAMEVAL = 0;
                FRAMEEXTRA = 34;//22H
                FIRVAL = value&0xFF;//lsb
                SECVAL = (value>>8)&0xFF;//msb
                THDVAL = 89; //59H
                if(mode==0) {
                    PARAMTOKEN = 0;
                    VALTOKEN = 1;
                }else{
                    PARAMTOKEN = 1;
                    VALTOKEN = 1;
                }
                //PARAMTOKEN = 1;
                //VALTOKEN= 1;
                break;
            /**
             * Material
             */
            case 39://Al/mg4/5Mn(bei synergie)
                FRAMEVAL = 0;
                FRAMEEXTRA = 0;
                FIRVAL = 10;//lsb
                SECVAL = 0;//msb
                THDVAL = 4;
                if(mode==1) {
                    PARAMTOKEN = 0;//no param id
                    VALTOKEN = 1;
                }
                break;
            case 40://Al/mg5(bei synergie)
                FRAMEVAL = 0;
                FRAMEEXTRA = 0;
                FIRVAL = 10;//lsb
                SECVAL = 0;//msb
                THDVAL = 4;
                if(mode==1) {
                    PARAMTOKEN = 0;//no param id
                    VALTOKEN = 1;
                }
                break;
            case 41://Al/mg3(bei synergie)
                FRAMEVAL = 0;
                FRAMEEXTRA = 0;
                FIRVAL = 10;//lsb
                SECVAL = 0;//msb
                THDVAL = 4;
                if(mode==1) {
                    PARAMTOKEN = 0;//no param id
                    VALTOKEN = 1;
                }
                break;
            case 42://Cu/Si(bei synergie)
                FRAMEVAL = 0;
                FRAMEEXTRA = 0;
                FIRVAL = 10;//lsb
                SECVAL = 0;//msb
                THDVAL = 4;
                if(mode==1) {
                    PARAMTOKEN = 0;//no param id
                    VALTOKEN = 1;
                }
                break;
        }
        UartService.canSend(VALTOKEN,PARAMTOKEN,FRAMEVAL,FRAMEEXTRA,(byte)FIRVAL,(byte)SECVAL,(byte)THDVAL);
        //Log.i(TAG,"canSend called");
    }
}