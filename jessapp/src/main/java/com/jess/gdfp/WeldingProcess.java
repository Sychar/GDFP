package com.jess.gdfp;

public class WeldingProcess {

    public static int VERFAHREN = 0;
    public static int DRAHTDURCHMESSER = 0;
    public static int GAS = 0;
    public static int WERKSTOFF = 0;
    public static int BETRIEBSART = 0;

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

    public static void setBetriebsart(String s){
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

}
