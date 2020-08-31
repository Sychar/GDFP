package com.jess.gdfp.Controller;

import com.jess.gdfp.DatenObjekte;

public class ErrorMessagesEng {

    private static String message = "";
    private static String getError = "";
    private static int myfirstint;
    private static String getHEX = "";

    public void ListOfErrorMessageEng(String stringdata){// stringdata is hex string

        //get CanID string
        char post_5 = stringdata.charAt(6);
        char post_6 = stringdata.charAt(7);
        char post_7 = stringdata.charAt(8);
        char post_8 = stringdata.charAt(9);

        StringBuilder joincharrr = new StringBuilder();
        getHEX = joincharrr.append(post_5).append(post_6).append(post_7).append(post_8).toString();

        if(getHEX.equals("0001")){

            DatenObjekte myobj = new DatenObjekte();
            myfirstint = (int)(myobj.DO_FRAME[6]);

            switch(myfirstint){

                case 1:
                    getError = "Status report: ESS-CAN not ok" + "\r\n" + "Description: Fault in general CAN" + "\r\n" + "Remedy: Notify service";
                    break;
                case 2:
                    getError = "Status report: DSP-CAN not ok" + "\r\n" + "Description: Störung im Inverter - CAN" + "\r\n" + "Remedy: Notify service";
                    break;
                case 3:
                    getError = "Status report: Overtemperature" + "\r\n" + "Description: Overtemperature of inverter" + "\r\n" + "Remedy: After cooling; machine OFF/ON";
                    break;
                case 4:
                    getError = "Status report: Water shortage" + "\r\n" + "Description: Coolant does not flow" + "\r\n" + "Remedy: Top up coolant; machine OFF/ON";
                    break;
                case 5:
                    getError = "Status report: FLG double error" + "\r\n" + "Description: Two identically coded FLGs in the system" + "\r\n" + "Remedy: Notify service";
                    break;
                case 6:
                    getError = "Status report: iDSP-type-error" + "\r\n" + "Description: Undefined machine type(E2/M2)" + "\r\n" + "Remedy: Notify service";
                    break;
                case 7:
                    getError = "Status report: iDSP-Adr-error" + "\r\n" + "Description: Same address detected" + "\r\n" + "Remedy: Notify service";
                    break;
                case 8:
                    getError = "Status report: iDSP-error" + "\r\n" + "Description: iDSP not accessible" + "\r\n" + "Remedy: Notify service";
                    break;
                case 9:
                    getError = "Status report: iDSP-SN-error" + "\r\n" + "Description: Wrong serial number detected" + "\r\n" + "Remedy: Notify service";
                    break;
                case 10:
                    getError = "Status report: Current limiting" + "\r\n" + "Description: disabled" + "\r\n" + "Remedy: Notify service";
                    break;
                case 11:
                    getError = "Status report: Overcurrent sec." + "\r\n" + "Description: Overcurrent secondary" + "\r\n" + "Remedy: Notify service";
                    break;
                case 12:
                    getError = "Status report: Fan not ok" + "\r\n" + "Description: Faulty fan" + "\r\n" + "Remedy: Notify service";
                    break;
                case 13:
                    getError = "Status report: Shor. temp. sens." + "\r\n" + "Description: Temperature sensor short circuit" + "\r\n" + "Remedy: Notify service";
                    break;
                case 14:
                    getError = "Status report: Break. temp. sens." + "\r\n" + "Description: Temperature sensor break" + "\r\n" + "Remedy: Notify service";
                    break;
                case 15:
                    getError = "Status report: Colective error" + "\r\n" + "Description: Summary for various inverter faults" + "\r\n" + "Remedy: Notify service";
                    break;
                case 16:
                    getError = "Status report: Controller-DSP" + "\r\n" + "Description: Controller DSP not ready" + "\r\n" + "Remedy: Notify service";
                    break;
                case 17:
                    getError = "Status report:    " + "\r\n" + "Description: disabled" + "\r\n" + "Remedy: Notify service";
                    break;
                case 18:
                    getError = "Status report: iDSP Heart-error." + "\r\n" + "Description: iDSP heart-beat-error" + "\r\n" + "Remedy: Notify service";
                    break;
                case 19:
                    getError = "Status report: iDSP SPI-error" + "\r\n" + "Description: iDSP SPI time-out-error" + "\r\n" + "Remedy: Notify service";
                    break;
                case 20:
                    getError = "Status report: iDSP PFC 1 > Max" + "\r\n" + "Description: iDSP PFC-overcurrent" + "\r\n" + "Remedy: Notify service";
                    break;
                case 21:
                    getError = "Status report: iDSP mains < 450" + "\r\n" + "Description: iDSP mains < 450V" + "\r\n" + "Remedy: Notify service";
                    break;
                case 22:
                    getError = "Status report: iDSP mains > 750" + "\r\n" + "Description: iDSP mains > 750V" + "\r\n" + "Remedy: Notify service";
                    break;
                case 23:
                    getError = "Status report: iDSP Gate 1 > Max" + "\r\n" + "Description: iDSP overcurrent Gate" + "\r\n" + "Remedy: Notify service";
                    break;
                case 24:
                    getError = "Status report: iDSP Bridg. 1 > Max" + "\r\n" + "Description: iDSP overcurrent bridge" + "\r\n" + "Remedy: Notify service";
                    break;
                case 25:
                    getError = "Status report: iDSP Sens. break" + "\r\n" + "Description: iDSP Temperature sensor break" + "\r\n" + "Remedy: Notify service";
                    break;
                case 26:
                    getError = "Status report: iDSP Sens. short." + "\r\n" + "Description: iDSP Temperature sensor short-circuit" + "\r\n" + "Remedy: Notify service";
                    break;
                case 27:
                    getError = "Status report: iDSP UZK800" + "\r\n" + "Description: iDSP UZK > 800V" + "\r\n" + "Remedy: Notify service";
                    break;
                case 28:
                    getError = "Status report: Water overtemp." + "\r\n" + "Description: Coolant-overtemperature" + "\r\n" + "Remedy: After cooling; machine OFF/ON";
                    break;
                case 29:
                    getError = "Status report: KGP t-sens. break" + "\r\n" + "Description: KGP-temperature sensor break" + "\r\n" + "Remedy: Notify service";
                    break;
                case 30:
                    getError = "Status report: KGP t-sens short" + "\r\n" + "Description: KGP-Temperature sensor short-circuit" + "\r\n" + "Remedy: Notify service";
                    break;
                case 31:
                    getError = "Status report:   " + "\r\n" + "Description:   " + "\r\n" + "Remedy: Notify service";
                    break;
                default:
                    getError = "Status report: Error" + "\r\n" + "Description: Error" + "\r\n" + "Remedy: Error";
                    break;
            }
            //Log.i("getErrorEng ",getError);

        }else{

            //getError = "Status report: No Error" + "\r\n" + "Description: No Error" + "\r\n" + "Remedy: No Error";
            //message = "No error in CanId.";
            //Log.i("ErrorMessagesEng ",message);
            //Log.i("ErrorMessagesEng ",getError);

        }
    }
}
