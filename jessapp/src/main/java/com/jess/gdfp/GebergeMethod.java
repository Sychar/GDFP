package com.jess.gdfp;

public class GebergeMethod {
    private static DatenObjekteSend sendToDatenObjekteSend = new DatenObjekteSend();
    //------------------------ Geberge right button ---------------------------------------------

    public static void Plus_NormalPunkten(){
        GlobalVariable.GEBERGE_NORMAL_PUNKTEN++;
        if(GlobalVariable.GEBERGE_NORMAL_PUNKTEN==1) sendToDatenObjekteSend.ChangeParameter(48,0,0);
        else if(GlobalVariable.GEBERGE_NORMAL_PUNKTEN==2) sendToDatenObjekteSend.ChangeParameter(49,0,0);
        else if(GlobalVariable.GEBERGE_NORMAL_PUNKTEN==3) sendToDatenObjekteSend.ChangeParameter(50,0,0);
        else if(GlobalVariable.GEBERGE_NORMAL_PUNKTEN==4) sendToDatenObjekteSend.ChangeParameter(51,0,0);
        else if(GlobalVariable.GEBERGE_NORMAL_PUNKTEN==5) sendToDatenObjekteSend.ChangeParameter(52,0,0);
        else if(GlobalVariable.GEBERGE_NORMAL_PUNKTEN==6) sendToDatenObjekteSend.ChangeParameter(53,0,0);
        else if(GlobalVariable.GEBERGE_NORMAL_PUNKTEN==7) sendToDatenObjekteSend.ChangeParameter(54,0,0);
        else if (GlobalVariable.GEBERGE_NORMAL_PUNKTEN > 7) GlobalVariable.GEBERGE_NORMAL_PUNKTEN = 0;
    }

    public static void Plus_Normal2Takt4Takt(){
        GlobalVariable.GEBERGE_NORMAL_4TAKT++;
        if(GlobalVariable.GEBERGE_NORMAL_4TAKT==1) sendToDatenObjekteSend.ChangeParameter(48,0,0);
        else if(GlobalVariable.GEBERGE_NORMAL_4TAKT==2) sendToDatenObjekteSend.ChangeParameter(49,0,0);
        else if(GlobalVariable.GEBERGE_NORMAL_4TAKT==3) sendToDatenObjekteSend.ChangeParameter(50,0,0);
        else if(GlobalVariable.GEBERGE_NORMAL_4TAKT==4) sendToDatenObjekteSend.ChangeParameter(51,0,0);
        else if(GlobalVariable.GEBERGE_NORMAL_4TAKT==5) sendToDatenObjekteSend.ChangeParameter(53,0,0);
        else if(GlobalVariable.GEBERGE_NORMAL_4TAKT==6) sendToDatenObjekteSend.ChangeParameter(54,0,0);
        else if (GlobalVariable.GEBERGE_NORMAL_4TAKT > 6) GlobalVariable.GEBERGE_NORMAL_4TAKT = 0;
    }

    public static void Plus_Normal4TaktSonder(){
        GlobalVariable.GEBERGE_NORMAL_4TAKTSONDER++;
        if(GlobalVariable.GEBERGE_NORMAL_4TAKTSONDER==1) sendToDatenObjekteSend.ChangeParameter(48,0,0);
        else if(GlobalVariable.GEBERGE_NORMAL_4TAKTSONDER==2) sendToDatenObjekteSend.ChangeParameter(55,0,0);
        else if(GlobalVariable.GEBERGE_NORMAL_4TAKTSONDER==3) sendToDatenObjekteSend.ChangeParameter(56,0,0);
        else if(GlobalVariable.GEBERGE_NORMAL_4TAKTSONDER==4) sendToDatenObjekteSend.ChangeParameter(57,0,0);
        else if(GlobalVariable.GEBERGE_NORMAL_4TAKTSONDER==5) sendToDatenObjekteSend.ChangeParameter(58,0,0);
        else if(GlobalVariable.GEBERGE_NORMAL_4TAKTSONDER==6) sendToDatenObjekteSend.ChangeParameter(49,0,0);
        else if(GlobalVariable.GEBERGE_NORMAL_4TAKTSONDER==7) sendToDatenObjekteSend.ChangeParameter(50,0,0);
        else if(GlobalVariable.GEBERGE_NORMAL_4TAKTSONDER==8) sendToDatenObjekteSend.ChangeParameter(51,0,0);
        else if(GlobalVariable.GEBERGE_NORMAL_4TAKTSONDER==9) sendToDatenObjekteSend.ChangeParameter(59,0,0);
        else if(GlobalVariable.GEBERGE_NORMAL_4TAKTSONDER==10) sendToDatenObjekteSend.ChangeParameter(60,0,0);
        else if(GlobalVariable.GEBERGE_NORMAL_4TAKTSONDER==11) sendToDatenObjekteSend.ChangeParameter(61,0,0);
        else if(GlobalVariable.GEBERGE_NORMAL_4TAKTSONDER==12) sendToDatenObjekteSend.ChangeParameter(62,0,0);
        else if(GlobalVariable.GEBERGE_NORMAL_4TAKTSONDER==13) sendToDatenObjekteSend.ChangeParameter(63,0,0);
        else if(GlobalVariable.GEBERGE_NORMAL_4TAKTSONDER==14) sendToDatenObjekteSend.ChangeParameter(64,0,0);
        else if(GlobalVariable.GEBERGE_NORMAL_4TAKTSONDER==15) sendToDatenObjekteSend.ChangeParameter(53,0,0);
        else if(GlobalVariable.GEBERGE_NORMAL_4TAKTSONDER==16) sendToDatenObjekteSend.ChangeParameter(54,0,0);
        else if (GlobalVariable.GEBERGE_NORMAL_4TAKTSONDER > 16) GlobalVariable.GEBERGE_NORMAL_4TAKTSONDER = 0;
    }

    public static void Plus_SynergiePunkten(){
        GlobalVariable.GEBERGE_SYNERGIE_PUNKTEN++;
        if(GlobalVariable.GEBERGE_SYNERGIE_PUNKTEN==1) sendToDatenObjekteSend.ChangeParameter(65,0,0);
        else if(GlobalVariable.GEBERGE_SYNERGIE_PUNKTEN==2) sendToDatenObjekteSend.ChangeParameter(66,0,0);
        else if(GlobalVariable.GEBERGE_SYNERGIE_PUNKTEN==3) sendToDatenObjekteSend.ChangeParameter(67,0,0);
        else if(GlobalVariable.GEBERGE_SYNERGIE_PUNKTEN==4) sendToDatenObjekteSend.ChangeParameter(68,0,0);
        else if(GlobalVariable.GEBERGE_SYNERGIE_PUNKTEN==5) sendToDatenObjekteSend.ChangeParameter(52,0,0);
        else if(GlobalVariable.GEBERGE_SYNERGIE_PUNKTEN==6) sendToDatenObjekteSend.ChangeParameter(69,0,0);
        else if(GlobalVariable.GEBERGE_SYNERGIE_PUNKTEN==7) sendToDatenObjekteSend.ChangeParameter(70,0,0);
        else if(GlobalVariable.GEBERGE_SYNERGIE_PUNKTEN==8) sendToDatenObjekteSend.ChangeParameter(53,0,0);
        else if(GlobalVariable.GEBERGE_SYNERGIE_PUNKTEN==9) sendToDatenObjekteSend.ChangeParameter(54,0,0);
        else if (GlobalVariable.GEBERGE_SYNERGIE_PUNKTEN > 9) GlobalVariable.GEBERGE_SYNERGIE_PUNKTEN = 0;
    }

    public static void Plus_Synergie2Takt4Takt(){
        GlobalVariable.GEBERGE_SYNERGIE_2TAKT++;
        if(GlobalVariable.GEBERGE_SYNERGIE_2TAKT==1) sendToDatenObjekteSend.ChangeParameter(65,0,0);
        else if(GlobalVariable.GEBERGE_SYNERGIE_2TAKT==2) sendToDatenObjekteSend.ChangeParameter(66,0,0);
        else if(GlobalVariable.GEBERGE_SYNERGIE_2TAKT==3) sendToDatenObjekteSend.ChangeParameter(71,0,0);
        else if(GlobalVariable.GEBERGE_SYNERGIE_2TAKT==4) sendToDatenObjekteSend.ChangeParameter(72,0,0);
        else if(GlobalVariable.GEBERGE_SYNERGIE_2TAKT==5) sendToDatenObjekteSend.ChangeParameter(69,0,0);
        else if(GlobalVariable.GEBERGE_SYNERGIE_2TAKT==6) sendToDatenObjekteSend.ChangeParameter(70,0,0);
        else if(GlobalVariable.GEBERGE_SYNERGIE_2TAKT==7) sendToDatenObjekteSend.ChangeParameter(53,0,0);
        else if(GlobalVariable.GEBERGE_SYNERGIE_2TAKT==8) sendToDatenObjekteSend.ChangeParameter(54,0,0);
        else if (GlobalVariable.GEBERGE_SYNERGIE_2TAKT > 8) GlobalVariable.GEBERGE_SYNERGIE_2TAKT = 0;
    }

    public static void Plus_Synergie4TaktSonder(){
        GlobalVariable.GEBERGE_SYNERGIE_4TAKTSONDER++;
        if(GlobalVariable.GEBERGE_SYNERGIE_4TAKTSONDER==1) sendToDatenObjekteSend.ChangeParameter(65,0,0);
        else if(GlobalVariable.GEBERGE_SYNERGIE_4TAKTSONDER==2) sendToDatenObjekteSend.ChangeParameter(71,0,0);
        else if(GlobalVariable.GEBERGE_SYNERGIE_4TAKTSONDER==3) sendToDatenObjekteSend.ChangeParameter(72,0,0);
        else if(GlobalVariable.GEBERGE_SYNERGIE_4TAKTSONDER==4) sendToDatenObjekteSend.ChangeParameter(58,0,0);
        else if(GlobalVariable.GEBERGE_SYNERGIE_4TAKTSONDER==5) sendToDatenObjekteSend.ChangeParameter(49,0,0);
        else if(GlobalVariable.GEBERGE_SYNERGIE_4TAKTSONDER==6) sendToDatenObjekteSend.ChangeParameter(73,0,0);
        else if(GlobalVariable.GEBERGE_SYNERGIE_4TAKTSONDER==7) sendToDatenObjekteSend.ChangeParameter(69,0,0);
        else if(GlobalVariable.GEBERGE_SYNERGIE_4TAKTSONDER==8) sendToDatenObjekteSend.ChangeParameter(59,0,0);
        else if(GlobalVariable.GEBERGE_SYNERGIE_4TAKTSONDER==9) sendToDatenObjekteSend.ChangeParameter(74,0,0);
        else if(GlobalVariable.GEBERGE_SYNERGIE_4TAKTSONDER==10) sendToDatenObjekteSend.ChangeParameter(60,0,0);
        else if(GlobalVariable.GEBERGE_SYNERGIE_4TAKTSONDER==11) sendToDatenObjekteSend.ChangeParameter(75,0,0);
        else if(GlobalVariable.GEBERGE_SYNERGIE_4TAKTSONDER==12) sendToDatenObjekteSend.ChangeParameter(61,0,0);
        else if(GlobalVariable.GEBERGE_SYNERGIE_4TAKTSONDER==13) sendToDatenObjekteSend.ChangeParameter(76,0,0);
        else if(GlobalVariable.GEBERGE_SYNERGIE_4TAKTSONDER==14) sendToDatenObjekteSend.ChangeParameter(77,0,0);
        else if(GlobalVariable.GEBERGE_SYNERGIE_4TAKTSONDER==15) sendToDatenObjekteSend.ChangeParameter(53,0,0);
        else if(GlobalVariable.GEBERGE_SYNERGIE_4TAKTSONDER==16) sendToDatenObjekteSend.ChangeParameter(54,0,0);
        else if (GlobalVariable.GEBERGE_SYNERGIE_4TAKTSONDER > 16) GlobalVariable.GEBERGE_SYNERGIE_4TAKTSONDER = 0;
    }

    public static void Plus_PulsPunkten(){
        GlobalVariable.GEBERGE_PULS_PUNKTEN++;
        if(GlobalVariable.GEBERGE_PULS_PUNKTEN==1) sendToDatenObjekteSend.ChangeParameter(65,0,0);
        else if(GlobalVariable.GEBERGE_PULS_PUNKTEN==2) sendToDatenObjekteSend.ChangeParameter(66,0,0);
        else if(GlobalVariable.GEBERGE_PULS_PUNKTEN==3) sendToDatenObjekteSend.ChangeParameter(71,0,0);
        else if(GlobalVariable.GEBERGE_PULS_PUNKTEN==4) sendToDatenObjekteSend.ChangeParameter(67,0,0);
        else if(GlobalVariable.GEBERGE_PULS_PUNKTEN==5) sendToDatenObjekteSend.ChangeParameter(52,0,0);
        else if(GlobalVariable.GEBERGE_PULS_PUNKTEN==6) sendToDatenObjekteSend.ChangeParameter(69,0,0);
        else if(GlobalVariable.GEBERGE_PULS_PUNKTEN==7) sendToDatenObjekteSend.ChangeParameter(70,0,0);
        else if(GlobalVariable.GEBERGE_PULS_PUNKTEN==8) sendToDatenObjekteSend.ChangeParameter(53,0,0);
        else if(GlobalVariable.GEBERGE_PULS_PUNKTEN==9) sendToDatenObjekteSend.ChangeParameter(54,0,0);
        else if (GlobalVariable.GEBERGE_PULS_PUNKTEN > 9) GlobalVariable.GEBERGE_PULS_PUNKTEN = 0;
    }

    public static void Plus_Puls2Takt4Takt(){
        GlobalVariable.GEBERGE_PULS_2TAKT++;
        if(GlobalVariable.GEBERGE_PULS_2TAKT==1) sendToDatenObjekteSend.ChangeParameter(65,0,0);
        else if(GlobalVariable.GEBERGE_PULS_2TAKT==2) sendToDatenObjekteSend.ChangeParameter(66,0,0);
        else if(GlobalVariable.GEBERGE_PULS_2TAKT==3) sendToDatenObjekteSend.ChangeParameter(71,0,0);
        else if(GlobalVariable.GEBERGE_PULS_2TAKT==4) sendToDatenObjekteSend.ChangeParameter(67,0,0);
        else if(GlobalVariable.GEBERGE_PULS_2TAKT==5) sendToDatenObjekteSend.ChangeParameter(52,0,0);
        else if(GlobalVariable.GEBERGE_PULS_2TAKT==6) sendToDatenObjekteSend.ChangeParameter(69,0,0);
        else if(GlobalVariable.GEBERGE_PULS_2TAKT==7) sendToDatenObjekteSend.ChangeParameter(70,0,0);
        else if(GlobalVariable.GEBERGE_PULS_2TAKT==8) sendToDatenObjekteSend.ChangeParameter(53,0,0);
        else if(GlobalVariable.GEBERGE_PULS_2TAKT==9) sendToDatenObjekteSend.ChangeParameter(54,0,0);
        else if (GlobalVariable.GEBERGE_PULS_2TAKT > 9) GlobalVariable.GEBERGE_PULS_2TAKT = 0;
    }

    public static void Plus_Puls4TaktSonder(){
        GlobalVariable.GEBERGE_PULS_4TAKTSONDER++;
        if(GlobalVariable.GEBERGE_PULS_4TAKTSONDER==1) sendToDatenObjekteSend.ChangeParameter(65,0,0);
        else if(GlobalVariable.GEBERGE_PULS_4TAKTSONDER==2) sendToDatenObjekteSend.ChangeParameter(71,0,0);
        else if(GlobalVariable.GEBERGE_PULS_4TAKTSONDER==3) sendToDatenObjekteSend.ChangeParameter(72,0,0);
        else if(GlobalVariable.GEBERGE_PULS_4TAKTSONDER==4) sendToDatenObjekteSend.ChangeParameter(58,0,0);
        else if(GlobalVariable.GEBERGE_PULS_4TAKTSONDER==5) sendToDatenObjekteSend.ChangeParameter(49,0,0);
        else if(GlobalVariable.GEBERGE_PULS_4TAKTSONDER==6) sendToDatenObjekteSend.ChangeParameter(73,0,0);
        else if(GlobalVariable.GEBERGE_PULS_4TAKTSONDER==7) sendToDatenObjekteSend.ChangeParameter(69,0,0);
        else if(GlobalVariable.GEBERGE_PULS_4TAKTSONDER==8) sendToDatenObjekteSend.ChangeParameter(59,0,0);
        else if(GlobalVariable.GEBERGE_PULS_4TAKTSONDER==9) sendToDatenObjekteSend.ChangeParameter(60,0,0);
        else if(GlobalVariable.GEBERGE_PULS_4TAKTSONDER==10) sendToDatenObjekteSend.ChangeParameter(61,0,0);
        else if(GlobalVariable.GEBERGE_PULS_4TAKTSONDER==11) sendToDatenObjekteSend.ChangeParameter(76,0,0);
        else if(GlobalVariable.GEBERGE_PULS_4TAKTSONDER==12) sendToDatenObjekteSend.ChangeParameter(77,0,0);
        else if(GlobalVariable.GEBERGE_PULS_4TAKTSONDER==13) sendToDatenObjekteSend.ChangeParameter(53,0,0);
        else if(GlobalVariable.GEBERGE_PULS_4TAKTSONDER==14) sendToDatenObjekteSend.ChangeParameter(54,0,0);
        else if (GlobalVariable.GEBERGE_PULS_4TAKTSONDER > 14) GlobalVariable.GEBERGE_PULS_4TAKTSONDER = 0;
    }

    public static void Minus_NormalPunkten(){
        GlobalVariable.GEBERGE_NORMAL_PUNKTEN--;
        if(GlobalVariable.GEBERGE_NORMAL_PUNKTEN==0) sendToDatenObjekteSend.ChangeParameter(47,0,0);
        else if(GlobalVariable.GEBERGE_NORMAL_PUNKTEN==1) sendToDatenObjekteSend.ChangeParameter(48,0,0);
        else if(GlobalVariable.GEBERGE_NORMAL_PUNKTEN==2) sendToDatenObjekteSend.ChangeParameter(49,0,0);
        else if(GlobalVariable.GEBERGE_NORMAL_PUNKTEN==3) sendToDatenObjekteSend.ChangeParameter(50,0,0);
        else if(GlobalVariable.GEBERGE_NORMAL_PUNKTEN==4) sendToDatenObjekteSend.ChangeParameter(51,0,0);
        else if(GlobalVariable.GEBERGE_NORMAL_PUNKTEN==5) sendToDatenObjekteSend.ChangeParameter(52,0,0);
        else if(GlobalVariable.GEBERGE_NORMAL_PUNKTEN==6) sendToDatenObjekteSend.ChangeParameter(53,0,0);
        else if (GlobalVariable.GEBERGE_NORMAL_PUNKTEN < 0) GlobalVariable.GEBERGE_NORMAL_PUNKTEN = 0;
    }

    public static void Minus_Normal2Takt4Takt(){
        GlobalVariable.GEBERGE_NORMAL_4TAKT--;
        if(GlobalVariable.GEBERGE_NORMAL_4TAKT==0) sendToDatenObjekteSend.ChangeParameter(47,0,0);
        else if(GlobalVariable.GEBERGE_NORMAL_4TAKT==1) sendToDatenObjekteSend.ChangeParameter(48,0,0);
        else if(GlobalVariable.GEBERGE_NORMAL_4TAKT==2) sendToDatenObjekteSend.ChangeParameter(49,0,0);
        else if(GlobalVariable.GEBERGE_NORMAL_4TAKT==3) sendToDatenObjekteSend.ChangeParameter(50,0,0);
        else if(GlobalVariable.GEBERGE_NORMAL_4TAKT==4) sendToDatenObjekteSend.ChangeParameter(51,0,0);
        else if(GlobalVariable.GEBERGE_NORMAL_4TAKT==5) sendToDatenObjekteSend.ChangeParameter(53,0,0);
        else if (GlobalVariable.GEBERGE_NORMAL_4TAKT < 0) GlobalVariable.GEBERGE_NORMAL_4TAKT = 0;
    }

    public static void Minus_Normal4TaktSonder(){
        GlobalVariable.GEBERGE_NORMAL_4TAKTSONDER--;
        if(GlobalVariable.GEBERGE_NORMAL_4TAKTSONDER==0) sendToDatenObjekteSend.ChangeParameter(47,0,0);
        else if(GlobalVariable.GEBERGE_NORMAL_4TAKTSONDER==1) sendToDatenObjekteSend.ChangeParameter(48,0,0);
        else if(GlobalVariable.GEBERGE_NORMAL_4TAKTSONDER==2) sendToDatenObjekteSend.ChangeParameter(55,0,0);
        else if(GlobalVariable.GEBERGE_NORMAL_4TAKTSONDER==3) sendToDatenObjekteSend.ChangeParameter(56,0,0);
        else if(GlobalVariable.GEBERGE_NORMAL_4TAKTSONDER==4) sendToDatenObjekteSend.ChangeParameter(57,0,0);
        else if(GlobalVariable.GEBERGE_NORMAL_4TAKTSONDER==5) sendToDatenObjekteSend.ChangeParameter(58,0,0);
        else if(GlobalVariable.GEBERGE_NORMAL_4TAKTSONDER==6) sendToDatenObjekteSend.ChangeParameter(49,0,0);
        else if(GlobalVariable.GEBERGE_NORMAL_4TAKTSONDER==7) sendToDatenObjekteSend.ChangeParameter(50,0,0);
        else if(GlobalVariable.GEBERGE_NORMAL_4TAKTSONDER==8) sendToDatenObjekteSend.ChangeParameter(51,0,0);
        else if(GlobalVariable.GEBERGE_NORMAL_4TAKTSONDER==9) sendToDatenObjekteSend.ChangeParameter(59,0,0);
        else if(GlobalVariable.GEBERGE_NORMAL_4TAKTSONDER==10) sendToDatenObjekteSend.ChangeParameter(60,0,0);
        else if(GlobalVariable.GEBERGE_NORMAL_4TAKTSONDER==11) sendToDatenObjekteSend.ChangeParameter(61,0,0);
        else if(GlobalVariable.GEBERGE_NORMAL_4TAKTSONDER==12) sendToDatenObjekteSend.ChangeParameter(62,0,0);
        else if(GlobalVariable.GEBERGE_NORMAL_4TAKTSONDER==13) sendToDatenObjekteSend.ChangeParameter(63,0,0);
        else if(GlobalVariable.GEBERGE_NORMAL_4TAKTSONDER==14) sendToDatenObjekteSend.ChangeParameter(64,0,0);
        else if(GlobalVariable.GEBERGE_NORMAL_4TAKTSONDER==15) sendToDatenObjekteSend.ChangeParameter(53,0,0);
        else if (GlobalVariable.GEBERGE_NORMAL_4TAKTSONDER < 0) GlobalVariable.GEBERGE_NORMAL_4TAKTSONDER = 0;
    }

    public static void Minus_SynergiePunkten(){
        GlobalVariable.GEBERGE_SYNERGIE_PUNKTEN--;
        if(GlobalVariable.GEBERGE_SYNERGIE_PUNKTEN==0) sendToDatenObjekteSend.ChangeParameter(47,0,0);
        else if(GlobalVariable.GEBERGE_SYNERGIE_PUNKTEN==1) sendToDatenObjekteSend.ChangeParameter(65,0,0);
        else if(GlobalVariable.GEBERGE_SYNERGIE_PUNKTEN==2) sendToDatenObjekteSend.ChangeParameter(66,0,0);
        else if(GlobalVariable.GEBERGE_SYNERGIE_PUNKTEN==3) sendToDatenObjekteSend.ChangeParameter(67,0,0);
        else if(GlobalVariable.GEBERGE_SYNERGIE_PUNKTEN==4) sendToDatenObjekteSend.ChangeParameter(68,0,0);
        else if(GlobalVariable.GEBERGE_SYNERGIE_PUNKTEN==5) sendToDatenObjekteSend.ChangeParameter(52,0,0);
        else if(GlobalVariable.GEBERGE_SYNERGIE_PUNKTEN==6) sendToDatenObjekteSend.ChangeParameter(69,0,0);
        else if(GlobalVariable.GEBERGE_SYNERGIE_PUNKTEN==7) sendToDatenObjekteSend.ChangeParameter(70,0,0);
        else if(GlobalVariable.GEBERGE_SYNERGIE_PUNKTEN==8) sendToDatenObjekteSend.ChangeParameter(53,0,0);
        else if (GlobalVariable.GEBERGE_SYNERGIE_PUNKTEN < 0) GlobalVariable.GEBERGE_SYNERGIE_PUNKTEN = 0;
    }

    public static void Minus_Synergie2Takt4Takt(){
        GlobalVariable.GEBERGE_SYNERGIE_2TAKT--;
        if(GlobalVariable.GEBERGE_SYNERGIE_2TAKT==0) sendToDatenObjekteSend.ChangeParameter(47,0,0);
        else if(GlobalVariable.GEBERGE_SYNERGIE_2TAKT==1) sendToDatenObjekteSend.ChangeParameter(65,0,0);
        else if(GlobalVariable.GEBERGE_SYNERGIE_2TAKT==2) sendToDatenObjekteSend.ChangeParameter(66,0,0);
        else if(GlobalVariable.GEBERGE_SYNERGIE_2TAKT==3) sendToDatenObjekteSend.ChangeParameter(71,0,0);
        else if(GlobalVariable.GEBERGE_SYNERGIE_2TAKT==4) sendToDatenObjekteSend.ChangeParameter(72,0,0);
        else if(GlobalVariable.GEBERGE_SYNERGIE_2TAKT==5) sendToDatenObjekteSend.ChangeParameter(69,0,0);
        else if(GlobalVariable.GEBERGE_SYNERGIE_2TAKT==6) sendToDatenObjekteSend.ChangeParameter(70,0,0);
        else if(GlobalVariable.GEBERGE_SYNERGIE_2TAKT==7) sendToDatenObjekteSend.ChangeParameter(53,0,0);
        else if(GlobalVariable.GEBERGE_SYNERGIE_2TAKT==8) sendToDatenObjekteSend.ChangeParameter(54,0,0);
        else if (GlobalVariable.GEBERGE_SYNERGIE_2TAKT < 0) GlobalVariable.GEBERGE_SYNERGIE_2TAKT = 0;
    }

    public static void Minus_Synergie4TaktSonder(){
        GlobalVariable.GEBERGE_SYNERGIE_4TAKTSONDER--;
        if(GlobalVariable.GEBERGE_SYNERGIE_4TAKTSONDER==0) sendToDatenObjekteSend.ChangeParameter(47,0,0);
        else if(GlobalVariable.GEBERGE_SYNERGIE_4TAKTSONDER==1) sendToDatenObjekteSend.ChangeParameter(65,0,0);
        else if(GlobalVariable.GEBERGE_SYNERGIE_4TAKTSONDER==2) sendToDatenObjekteSend.ChangeParameter(71,0,0);
        else if(GlobalVariable.GEBERGE_SYNERGIE_4TAKTSONDER==3) sendToDatenObjekteSend.ChangeParameter(72,0,0);
        else if(GlobalVariable.GEBERGE_SYNERGIE_4TAKTSONDER==4) sendToDatenObjekteSend.ChangeParameter(58,0,0);
        else if(GlobalVariable.GEBERGE_SYNERGIE_4TAKTSONDER==5) sendToDatenObjekteSend.ChangeParameter(49,0,0);
        else if(GlobalVariable.GEBERGE_SYNERGIE_4TAKTSONDER==6) sendToDatenObjekteSend.ChangeParameter(73,0,0);
        else if(GlobalVariable.GEBERGE_SYNERGIE_4TAKTSONDER==7) sendToDatenObjekteSend.ChangeParameter(69,0,0);
        else if(GlobalVariable.GEBERGE_SYNERGIE_4TAKTSONDER==8) sendToDatenObjekteSend.ChangeParameter(59,0,0);
        else if(GlobalVariable.GEBERGE_SYNERGIE_4TAKTSONDER==9) sendToDatenObjekteSend.ChangeParameter(74,0,0);
        else if(GlobalVariable.GEBERGE_SYNERGIE_4TAKTSONDER==10) sendToDatenObjekteSend.ChangeParameter(60,0,0);
        else if(GlobalVariable.GEBERGE_SYNERGIE_4TAKTSONDER==11) sendToDatenObjekteSend.ChangeParameter(75,0,0);
        else if(GlobalVariable.GEBERGE_SYNERGIE_4TAKTSONDER==12) sendToDatenObjekteSend.ChangeParameter(61,0,0);
        else if(GlobalVariable.GEBERGE_SYNERGIE_4TAKTSONDER==13) sendToDatenObjekteSend.ChangeParameter(76,0,0);
        else if(GlobalVariable.GEBERGE_SYNERGIE_4TAKTSONDER==14) sendToDatenObjekteSend.ChangeParameter(77,0,0);
        else if(GlobalVariable.GEBERGE_SYNERGIE_4TAKTSONDER==15) sendToDatenObjekteSend.ChangeParameter(53,0,0);
        else if (GlobalVariable.GEBERGE_SYNERGIE_4TAKTSONDER < 0) GlobalVariable.GEBERGE_SYNERGIE_4TAKTSONDER = 0;
    }

    public static void Minus_PulsPunkten(){
        GlobalVariable.GEBERGE_PULS_PUNKTEN--;
        if(GlobalVariable.GEBERGE_PULS_PUNKTEN==0) sendToDatenObjekteSend.ChangeParameter(47,0,0);
        else if(GlobalVariable.GEBERGE_PULS_PUNKTEN==1) sendToDatenObjekteSend.ChangeParameter(65,0,0);
        else if(GlobalVariable.GEBERGE_PULS_PUNKTEN==2) sendToDatenObjekteSend.ChangeParameter(66,0,0);
        else if(GlobalVariable.GEBERGE_PULS_PUNKTEN==3) sendToDatenObjekteSend.ChangeParameter(71,0,0);
        else if(GlobalVariable.GEBERGE_PULS_PUNKTEN==4) sendToDatenObjekteSend.ChangeParameter(67,0,0);
        else if(GlobalVariable.GEBERGE_PULS_PUNKTEN==5) sendToDatenObjekteSend.ChangeParameter(52,0,0);
        else if(GlobalVariable.GEBERGE_PULS_PUNKTEN==6) sendToDatenObjekteSend.ChangeParameter(69,0,0);
        else if(GlobalVariable.GEBERGE_PULS_PUNKTEN==7) sendToDatenObjekteSend.ChangeParameter(70,0,0);
        else if(GlobalVariable.GEBERGE_PULS_PUNKTEN==8) sendToDatenObjekteSend.ChangeParameter(53,0,0);
        else if (GlobalVariable.GEBERGE_PULS_PUNKTEN < 0) GlobalVariable.GEBERGE_PULS_PUNKTEN = 0;
    }

    public static void Minus_Puls2Takt4Takt(){
        GlobalVariable.GEBERGE_PULS_2TAKT--;
        if(GlobalVariable.GEBERGE_PULS_2TAKT==0) sendToDatenObjekteSend.ChangeParameter(47,0,0);
        else if(GlobalVariable.GEBERGE_PULS_2TAKT==1) sendToDatenObjekteSend.ChangeParameter(65,0,0);
        else if(GlobalVariable.GEBERGE_PULS_2TAKT==2) sendToDatenObjekteSend.ChangeParameter(66,0,0);
        else if(GlobalVariable.GEBERGE_PULS_2TAKT==3) sendToDatenObjekteSend.ChangeParameter(71,0,0);
        else if(GlobalVariable.GEBERGE_PULS_2TAKT==4) sendToDatenObjekteSend.ChangeParameter(67,0,0);
        else if(GlobalVariable.GEBERGE_PULS_2TAKT==5) sendToDatenObjekteSend.ChangeParameter(52,0,0);
        else if(GlobalVariable.GEBERGE_PULS_2TAKT==6) sendToDatenObjekteSend.ChangeParameter(69,0,0);
        else if(GlobalVariable.GEBERGE_PULS_2TAKT==7) sendToDatenObjekteSend.ChangeParameter(70,0,0);
        else if(GlobalVariable.GEBERGE_PULS_2TAKT==8) sendToDatenObjekteSend.ChangeParameter(53,0,0);
        else if (GlobalVariable.GEBERGE_PULS_2TAKT < 0) GlobalVariable.GEBERGE_PULS_2TAKT = 0;
    }

    public static void Minus_Puls4TaktSonder(){
        GlobalVariable.GEBERGE_PULS_4TAKTSONDER--;
        if(GlobalVariable.GEBERGE_PULS_4TAKTSONDER==0) sendToDatenObjekteSend.ChangeParameter(47,0,0);
        else if(GlobalVariable.GEBERGE_PULS_4TAKTSONDER==1) sendToDatenObjekteSend.ChangeParameter(65,0,0);
        else if(GlobalVariable.GEBERGE_PULS_4TAKTSONDER==2) sendToDatenObjekteSend.ChangeParameter(71,0,0);
        else if(GlobalVariable.GEBERGE_PULS_4TAKTSONDER==3) sendToDatenObjekteSend.ChangeParameter(72,0,0);
        else if(GlobalVariable.GEBERGE_PULS_4TAKTSONDER==4) sendToDatenObjekteSend.ChangeParameter(58,0,0);
        else if(GlobalVariable.GEBERGE_PULS_4TAKTSONDER==5) sendToDatenObjekteSend.ChangeParameter(49,0,0);
        else if(GlobalVariable.GEBERGE_PULS_4TAKTSONDER==6) sendToDatenObjekteSend.ChangeParameter(73,0,0);
        else if(GlobalVariable.GEBERGE_PULS_4TAKTSONDER==7) sendToDatenObjekteSend.ChangeParameter(69,0,0);
        else if(GlobalVariable.GEBERGE_PULS_4TAKTSONDER==8) sendToDatenObjekteSend.ChangeParameter(59,0,0);
        else if(GlobalVariable.GEBERGE_PULS_4TAKTSONDER==9) sendToDatenObjekteSend.ChangeParameter(60,0,0);
        else if(GlobalVariable.GEBERGE_PULS_4TAKTSONDER==10) sendToDatenObjekteSend.ChangeParameter(61,0,0);
        else if(GlobalVariable.GEBERGE_PULS_4TAKTSONDER==11) sendToDatenObjekteSend.ChangeParameter(76,0,0);
        else if(GlobalVariable.GEBERGE_PULS_4TAKTSONDER==12) sendToDatenObjekteSend.ChangeParameter(77,0,0);
        else if(GlobalVariable.GEBERGE_PULS_4TAKTSONDER==13) sendToDatenObjekteSend.ChangeParameter(53,0,0);
        else if (GlobalVariable.GEBERGE_PULS_4TAKTSONDER < 0) GlobalVariable.GEBERGE_PULS_4TAKTSONDER = 0;
    }

}
