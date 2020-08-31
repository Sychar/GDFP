package com.jess.gdfp.DatenBank;

import android.net.Uri;
import android.provider.BaseColumns;

public final class KennlinienContract {

    private KennlinienContract(){};
    public  static  final String CONTEN_AUTHORITY = "com.felhr.serialportexample.kennlinien";
    public static final Uri BASE_CONTENT_URI =Uri.parse("content://"+CONTEN_AUTHORITY);
    public static final  String Path_kenn ="kennlinie";


    public static final class Kennlinie implements BaseColumns{

        public static final String TABEL_NAME = "kennliene";
        public static final String _ID = BaseColumns._ID;
        public static final String UiKennNR = "uiKennNr";
        public static final String ucKennType = "ucKennType";
        public static final String ucVerfahren = "ucVerfahren";
        public static final String ucDrahtDurchm = "ucDrahtDurchm";
        public static final String ucGas = "ucGas";
        public static final String ucWerkstof = "ucWerkstof";
        public static final String ucReglerTyp = "ucReglerTyp";
        public static final String ucGasVor = "ucReglerTyp";
        public static final String ucGasNach = "ucGasNach";
        public static final String ucUpSlope = "ucUpSlope";
        public static final String ucDownSlopeSTRING = "ucDownSlopeSTRING";
        public static final String ucZuendEgPrz = "ucZuendEgPrz";
        public static final String ucEndKraterPrz = "ucEndKraterPrz";
        public static final String ucZuendDauer = "ucZuendDauer";
        public static final String ucEndKraDauer = "ucEndKraDauer";
        public static final String ucFreiBrandPrz = "ucFreiBrandPrz";
        public static final String ucPPEnergie2 = "ucPPEnergie2 ";
        public static final String ucPPTime1 = "ucPPTime1";
        public static final String ucPPTime2 = "ucPPTime2";
        public static final String ucPP_LBR_E2 = "ucPP_LBR_E2";
        public static final String ucProtokollTyp = "ucProtokollTyp";
        public static final String ucDrossRes2 = "ucDrossRes2 ";
        public static final String ucDrossRes5 = "ucDrossRes5";
        public static final String ucDrossRes6 = "ucDrossRes6";
        public static final String ucDrossRes1 = "ucDrossRes1";
        public static final String ucDrossRes7 = "ucDrossRes7";
        public static final String ucDrossRes3 = "ucDrossRes3";
        public static final String ucDrossRes4 = "ucDrossRes4";
        public static final String ucLBR_Mode = "ucLBR_Mode";
        public static final String ucKS_Erkennung = "ucKS_Erkennung";
        public static final String ucKS_Aufhebung = "ucKS_Aufhebung";
        public static final String ucVerwZeitPN = "ucVerwZeitPN";
        public static final String ucVerwZeitNP = "ucVerwZeitNP";
        public static final String ucStromSchwell = "ucStromSchwell";
        public static final String ucPositivZeit = "ucPositivZeit";
        public static final String ulKennEssNr = "ulKennEssNr";
        public static final String stKennBez1 = "stKennBez1 ";
        public static final String stKennBez2 = "stKennBez2";
        public static final String stKennKomm = "stKennKomm";
        public static final String uiGrundCRC = "uiGrundCRC";






    }

}

