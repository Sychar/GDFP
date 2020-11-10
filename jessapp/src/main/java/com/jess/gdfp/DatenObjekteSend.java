package com.jess.gdfp;

public class DatenObjekteSend {

    private final static String TAG = DatenObjekteSend.class.getSimpleName(); //name of this class

    private byte VALTOKEN;//1(yes)
    private byte PARAMTOKEN;//1(yes)
    private byte PARAM_POS3;
    private byte PARAM_POS1;
    private double VAL_POS3;
    private int VAL_POS4;
    private int VAL_POS1;
    private double Mm;

    public void ChangeParameter(int num,int value,int mode){

        switch(num){
            case 1: //Strom
                PARAM_POS3 = 0;
                PARAM_POS1 = 34;//22H
                VAL_POS3 = value&0xFF;//lsb
                VAL_POS4 = (value>>8)&0xFF;//msb
                VAL_POS1 = 51; //33H
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
                PARAM_POS3 = 1;
                PARAM_POS1 = 34;
                VAL_POS3 = value&0xFF;//lsb
                VAL_POS4 = 0;//msb
                VAL_POS1 = 51; //33H
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
                PARAM_POS3 = 2;
                PARAM_POS1 = 34;
                //Mm = ((float) value) /10.0 ;
                //Log.i("Mn: " , String.valueOf(Mm));
                VAL_POS3 = value & 0xFF;//lsb
                VAL_POS4 = 0;//msb
                VAL_POS1 = 51; //33H
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
                PARAM_POS3 = 1;
                PARAM_POS1 = 11; //0x0B
                VAL_POS3 = 0;
                VAL_POS4 = 0;
                VAL_POS1 = 0;
                PARAMTOKEN = 1;
                VALTOKEN= 0; // no value id
                break;
            case 5: //job activate
                //Log.i(TAG,"activate job");
                PARAM_POS3 = 5;
                PARAM_POS1 = 11; //0x0B
                VAL_POS3 = 0;
                VAL_POS4 = 0;
                VAL_POS1 = 0;
                PARAMTOKEN = 1;
                VALTOKEN= 0; // no value id
                break;
            case 6: //job deactivate/decrement
                PARAM_POS3 = 3;
                PARAM_POS1 = 11; //0x0B
                VAL_POS3 = 0;
                VAL_POS4 = 0;
                VAL_POS1 = 0;
                PARAMTOKEN = 1;
                VALTOKEN= 0; // no value id
                break;
            case 7: //spannung
                PARAM_POS3 = 4;
                PARAM_POS1 = 0;
                VAL_POS3 = value&0xFF;//lsb
                VAL_POS4 = (value>>8)&0xFF;//msb
                VAL_POS1 = 50;//32H
                PARAMTOKEN = 1;
                VALTOKEN= 1;
                break;
            case 8: //Drossel
                PARAM_POS3 = 4;
                PARAM_POS1 = 0;
                VAL_POS3 = value&0xFF;//lsb
                VAL_POS4 = 0;//msb
                VAL_POS1 = 52;//34H
                PARAMTOKEN = 1;
                VALTOKEN= 1;
                break;
            case 9://Lichtbogenkorr(bei 2 Takt)
                PARAM_POS3 = 2;
                PARAM_POS1 = 0;
                VAL_POS3 = value&0xFF;//lsb
                VAL_POS4 = (value>>8)&0xFF;//msb
                VAL_POS1 = 53;//35H
                if(mode==0) {
                    PARAMTOKEN = 1;
                    VALTOKEN = 0;
                }else{
                    PARAMTOKEN = 0;
                    VALTOKEN = 1;
                }
                break;
            case 10://CrNi(bei synergie)
                PARAM_POS3 = 0;
                PARAM_POS1 = 0;
                VAL_POS3 = 1;//lsb
                VAL_POS4 = 0;//msb
                VAL_POS1 = 4;//04H
                if(mode==1) {
                    PARAMTOKEN = 0;//no param id
                    VALTOKEN = 1;
                }
                break;
            case 11://AlMg(bei synergie)
                PARAM_POS3 = 0;
                PARAM_POS1 = 0;
                VAL_POS3 = 2;//lsb
                VAL_POS4 = 0;//msb
                VAL_POS1 = 4;
                if(mode==1) {
                    PARAMTOKEN = 0;//no param id
                    VALTOKEN = 1;
                }
                break;
            case 12://AlSi(bei synergie)
                PARAM_POS3 = 0;
                PARAM_POS1 = 0;
                VAL_POS3 = 3;//lsb
                VAL_POS4 = 0;//msb
                VAL_POS1 = 4;
                if(mode==1) {
                    PARAMTOKEN = 0;//no param id
                    VALTOKEN = 1;
                }
                break;
            case 13://Spz(bei synergie)
                PARAM_POS3 = 0;
                PARAM_POS1 = 0;
                VAL_POS3 = 4;//lsb
                VAL_POS4 = 0;//msb
                VAL_POS1 = 4;
                if(mode==1) {
                    PARAMTOKEN = 0;//no param id
                    VALTOKEN = 1;
                }
                break;
            case 14://Fe(bei synergie)
                PARAM_POS3 = 0;
                PARAM_POS1 = 0;
                VAL_POS3 = 10;//lsb
                VAL_POS4 = 0;//msb
                VAL_POS1 = 4;
                if(mode==1) {
                    PARAMTOKEN = 0;//no param id
                    VALTOKEN = 1;
                }
                break;
            case 15: //voltage
                PARAM_POS3 = 0;
                PARAM_POS1 = 0;
                VAL_POS3 = value&0xFF;//lsb
                VAL_POS4 = 0;//msb
                VAL_POS1 = 50;//new 32H //original 34H
                if(mode==1) {
                    PARAMTOKEN = 0;//no param id
                    VALTOKEN = 1;
                }
                break;
            case 16: //A
                PARAM_POS3 = 0;
                PARAM_POS1 = 0;
                VAL_POS3 = value&0xFF;//lsb
                VAL_POS4 = 0;//msb
                VAL_POS1 = 34;//22H
                PARAMTOKEN = 0;
                VALTOKEN= 1;
                break;
            case 17: //mm
                PARAM_POS3 = 0;
                PARAM_POS1 = 0;
                VAL_POS3 = 0;//lsb
                VAL_POS4 = 0;//msb
                VAL_POS1 = 2;//2H
                PARAMTOKEN = 0;
                VALTOKEN= 1;
                break;
            case 18: //m/min
                PARAM_POS3 = 0;
                PARAM_POS1 = 0;
                VAL_POS3 = 0;//lsb
                VAL_POS4 = 0;//msb
                VAL_POS1 = 1;//2H
                PARAMTOKEN = 0;
                VALTOKEN= 1;
                break;
            case 19: // %
                PARAM_POS3 = 0;
                PARAM_POS1 = 0;
                VAL_POS3 = value&0xFF;//lsb
                VAL_POS4 = 0;//msb
                VAL_POS1 = 50;//32H
                PARAMTOKEN = 0;
                VALTOKEN= 1;
                break;
            case 20: // job change
                PARAM_POS3 = 11;
                PARAM_POS1 =11;
                VAL_POS3 = 0;//lsb
                VAL_POS4 = 0;//msb
                VAL_POS1 = 0;//bH
                PARAMTOKEN = 1;
                VALTOKEN= 0;
                break;

            case 21: //Lichtbogenkorrektur(bei synergie)
                PARAM_POS3 = 0;
                PARAM_POS1 = 0;
                VAL_POS3 = value&0xFF;//lsb
                VAL_POS4 = (value>>8)&0xFF;//msb
                VAL_POS1 = 53;//35H
                if (mode==0) {
                    PARAMTOKEN = 1;
                    VALTOKEN = 0;
                } else {
                    PARAMTOKEN = 0;
                    VALTOKEN = 1;
                }
                break;
            case 22: //energie 1(bei synergie)
                PARAM_POS3 = 1;
                PARAM_POS1 = 0;
                VAL_POS3 = value&0xFF;//lsb
                VAL_POS4 = (value>>8)&0xFF;//msb
                VAL_POS1 = 27;//1BH
                PARAMTOKEN = 1;
                VALTOKEN = 1;
                break;

            case 23: //Draht(bei synergie)
                PARAM_POS3 = 0;
                PARAM_POS1 = 0;
                VAL_POS3 = value&0xFF;
                VAL_POS4 = 0;
                VAL_POS1 = 2;
                PARAMTOKEN = 0;
                VALTOKEN = 1;
                break;
            case 24: //Elekto(bei 2.Pic)
                PARAM_POS3 = 0;
                PARAM_POS1 = 0;
                VAL_POS3 = value&0xFF;
                VAL_POS4 = 0;
                VAL_POS1 = 89;//59H
                PARAMTOKEN = 0;
                VALTOKEN = 1;
                break;
            case 25: //Synergie Verfahren
                PARAM_POS3 = 1;
                PARAM_POS1 = 0;
                //VAL_POS3 = 0;
                VAL_POS3 = value&0xFF;
                VAL_POS4 = 0;
                //VAL_POS1 = 0;
                VAL_POS1 = 51; //0x33
                PARAMTOKEN = 1;
                //VALTOKEN = 0;//no value id
                if(mode == 0) VALTOKEN = 0;
                else VALTOKEN = 1;
                break;
            case 26: //2 Takt
                PARAM_POS3 = 2;
                PARAM_POS1 = 0;
                VAL_POS3 = 0;
                VAL_POS4 = 0;
                VAL_POS1 = 0;
                PARAMTOKEN = 1;
                VALTOKEN = 0;//no value id
                break;
            case 27: //2. Pic
                PARAM_POS3 = 3;
                PARAM_POS1 = 0;
                VAL_POS3 = 0;
                VAL_POS4 = 0;
                VAL_POS1 = 0;
                PARAMTOKEN = 1;
                VALTOKEN = 0;//no value id
                break;
            case 28: //Normal Verfahren
                PARAM_POS3 = 4;
                PARAM_POS1 = 0;
                VAL_POS3 = value&0xFF;
                //VAL_POS3 = 0;
                VAL_POS4 = 0;
                //VAL_POS1 = 0;
                VAL_POS1 = 51; //0x33
                PARAMTOKEN = 1;
                //VALTOKEN = 0;//no value id
                if(mode == 0) VALTOKEN = 0;
                else VALTOKEN = 1;
                break;
            case 29: //Puls Verfahren
                PARAM_POS3 = 2;
                PARAM_POS1 = 0;
                VAL_POS3 = value&0xFF;
                VAL_POS4 = 0;
                VAL_POS1 = 51; //0x33
                PARAMTOKEN = 1;
                if(mode == 0) VALTOKEN = 0;
                else VALTOKEN = 1;
                break;
            case 30: //Elektrode Verfahren
                PARAM_POS3 = 3;
                PARAM_POS1 = 0;
                VAL_POS3 = value&0xFF;
                VAL_POS4 = 0;
                VAL_POS1 = 89; //0x59
                PARAMTOKEN = 1;
                if(mode == 0) VALTOKEN = 0;
                else VALTOKEN = 1;
                break;
            case 32: //volt(bei norm)
                PARAM_POS3 = 0;
                PARAM_POS1 = 0;
                VAL_POS3 = value&0xFF;//lsb
                VAL_POS4 = (value>>8)&0xFF;//msb
                VAL_POS1 = 50;//32H
                PARAMTOKEN = 0;
                VALTOKEN = 1;
                break;
            case 33: //Verfahren(bei norm)
                PARAM_POS3 = 0;
                PARAM_POS1 = 0;
                VAL_POS3 = value&0xFF;//01/02/03/05
                VAL_POS4 = 0;
                VAL_POS1 = 1;
                PARAMTOKEN = 0;
                VALTOKEN = 1;
                break;
            case 34: //Energie2
                PARAM_POS3 = 48;//30H
                PARAM_POS1 = 0;
                VAL_POS3 = 30;//1EH
                VAL_POS4 = 0;
                VAL_POS1 = 0;
                PARAMTOKEN = 1;
                VALTOKEN = 1;
                break;
            case 35: //Lichtbogenkorr (bei 2 takt blinking)
                PARAM_POS3 = 0;
                PARAM_POS1 = 0;
                VAL_POS3 = value&0xFF;
                VAL_POS4 = 0;
                VAL_POS1 = 26;//1AH
                PARAMTOKEN = 0;
                VALTOKEN = 1;
                break;
            case 36: //Lichtbogenkorr2 (bei 2 takt)
                PARAM_POS3 = 0;
                PARAM_POS1 = 0;
                VAL_POS3 = value&0xFF;//lsb
                VAL_POS4 = (value>>>8)&0xFF;//msb
                VAL_POS1 = 53;//35H
                PARAMTOKEN = 0;
                VALTOKEN = 1;
                break;
            case 37: //store job
                PARAM_POS3 = (byte) (value&0xFF);
                PARAM_POS1 = 11;
                VAL_POS3 = 0;
                VAL_POS4 = 0;
                VAL_POS1 = 0;
                if(mode==0) {
                    PARAMTOKEN = 1;
                    VALTOKEN = 0; // no value id
                }else{
                    PARAMTOKEN = 0;
                    VALTOKEN = 1; // no value id
                }
                break;
            case 38: //Strom (Elektrode)
                PARAM_POS3 = 0;
                PARAM_POS1 = 34;//22H
                VAL_POS3 = value&0xFF;//lsb
                VAL_POS4 = (value>>8)&0xFF;//msb
                VAL_POS1 = 89; //59H
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
            case 39: //job(no value) increment
                PARAM_POS3 = 7;
                PARAM_POS1 = 11; //0x0B
                VAL_POS3 = 0;
                VAL_POS4 = 0;
                VAL_POS1 = 0;
                PARAMTOKEN = 1;
                VALTOKEN= 0; // no value id
                break;
            case 40: ////job deactivate (0B 00 09 00)
                PARAM_POS3 = 9;
                PARAM_POS1 = 11; //0x0B
                VAL_POS3 = 0;
                VAL_POS4 = 0;
                VAL_POS1 = 0;
                if(mode==0) {
                    PARAMTOKEN = 1;
                    VALTOKEN = 0; // no value id
                }else{
                    PARAMTOKEN = 0;
                    VALTOKEN = 1; // no value id
                }
                break;
            case 41: //Thickness mm
                PARAM_POS3 = 2;
                PARAM_POS1 = 2; //0x0B
                VAL_POS3 = 0;
                VAL_POS4 = 0;
                VAL_POS1 = 0;
                if(mode==0) {
                    PARAMTOKEN = 1;
                    VALTOKEN = 0; // no value id
                }else{
                    PARAMTOKEN = 0;
                    VALTOKEN = 1;
                }
                break;
            case 42: //Betriebsart
                PARAM_POS3 = 5;
                PARAM_POS1 = 1;
                VAL_POS3 = 0;
                VAL_POS4 = 0;
                VAL_POS1 = 0;
                if(mode==0) {
                    PARAMTOKEN = 1;
                    VALTOKEN = 0; // no value id
                }else{
                    PARAMTOKEN = 0;
                    VALTOKEN = 1;
                }
                break;
            case 43: //KorrekturDrossel
                PARAM_POS3 = 0;
                PARAM_POS1 = 0;
                VAL_POS3 = value & 0xFF;
                VAL_POS4 = 255; //FFH
                VAL_POS1 = 27; //1BH
                if(mode==0) {
                    PARAMTOKEN = 1;
                    VALTOKEN = 0; // no value id
                }else{
                    PARAMTOKEN = 0;
                    VALTOKEN = 1;
                }
                break;
            case 44: //Werkstoff
                PARAM_POS3 = 1;
                PARAM_POS1 = 4;
                VAL_POS3 = 0;
                VAL_POS4 = 0;
                VAL_POS1 = 0;
                if(mode==0) {
                    PARAMTOKEN = 1;
                    VALTOKEN = 0; // no value id
                }else{
                    PARAMTOKEN = 0;
                    VALTOKEN = 1;
                }
                break;
            case 45: //Motor forward 06 00 02 01
                PARAM_POS3 = 0;
                PARAM_POS1 = 0;
                VAL_POS3 = 2;
                VAL_POS4 = 1;
                VAL_POS1 = 6;
                if(mode==0) {
                    PARAMTOKEN = 1;
                    VALTOKEN = 0; // no value id
                }else{
                    PARAMTOKEN = 0;
                    VALTOKEN = 1;
                }
                break;
            case 46: //Motor backwards 06 00 03 01
                PARAM_POS3 = 0;
                PARAM_POS1 = 0;
                VAL_POS3 = 3;
                VAL_POS4 = 1;
                VAL_POS1 = 6;
                if(mode==0) {
                    PARAMTOKEN = 1;
                    VALTOKEN = 0; // no value id
                }else{
                    PARAMTOKEN = 0;
                    VALTOKEN = 1;
                }
                break;
            /*case 47: //Motor forwards 00 00 01 01
                PARAM_POS3 = 0;
                PARAM_POS1 = 0;
                VAL_POS3 = 1;
                VAL_POS4 = 1;
                VAL_POS1 = 0;
                if(mode==0) {
                    PARAMTOKEN = 1;
                    VALTOKEN = 0; // no value id
                }else{
                    PARAMTOKEN = 0;
                    VALTOKEN = 1;
                }
                break;
            case 48: //Motor backwards 00 00 01 00
                PARAM_POS3 = 0;
                PARAM_POS1 = 0;
                VAL_POS3 = 1;
                VAL_POS4 = 0;
                VAL_POS1 = 0;
                if(mode==0) {
                    PARAMTOKEN = 1;
                    VALTOKEN = 0; // no value id
                }else{
                    PARAMTOKEN = 0;
                    VALTOKEN = 1;
                }
                break;*/
            }
            UartService.canSend(VALTOKEN,PARAMTOKEN, PARAM_POS3, PARAM_POS1,(byte) VAL_POS3,(byte) VAL_POS4,(byte) VAL_POS1);
        }
    }