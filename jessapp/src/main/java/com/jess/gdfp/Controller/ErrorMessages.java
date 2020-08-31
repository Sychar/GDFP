package com.jess.gdfp.Controller;

import android.util.Log;

public class ErrorMessages {

    private static String message = "";
    private static String getError = "";
    private static int myfirstint;
    private static String getHEX = "";

    public void ListOfErrorMessage(String stringdata){// stringdata is hex string

        //get CanID string
        char post_5 = stringdata.charAt(6);
        char post_6 = stringdata.charAt(7);
        char post_7=stringdata.charAt(8);
        char post_8=stringdata.charAt(9);

        StringBuilder joincharrr = new StringBuilder();
        getHEX = joincharrr.append(post_5).append(post_6).append(post_7).append(post_8).toString();

        if(getHEX.equals("0000")){

            //DatenObjekte myobj = new DatenObjekte();
            //myfirstint = (int)(myobj.DO_FRAME[5]);
            //myfirstint = (int)(DatenObjekte.DO_FRAME[6]);
            myfirstint = 2;
            Log.e("ErrorMessage class ",String.valueOf(myfirstint));

            switch(myfirstint){

                case 1:
                    getError = "Anzeige: ESS-CAN nicht ok" + "\r\n" + "Erklärung: Störung im allgemeinen CAN" + "\r\n" + "Abhilfe: Service verständigen";
                    break;
                case 2:
                    getError = "Anzeige: DSP-CAN nicht ok" + "\r\n" + "Erklärung: Störung im Inverter - CAN" + "\r\n" + "Abhilfe: Service verständigen";
                    break;
                case 3:
                    getError = "Anzeige: Uebertemperatur" + "\r\n" + "Erklärung: Übertemperatur des Inverters" + "\r\n" + "Abhilfe: nach Abkühlen; aus/an";
                    break;
                case 4:
                    getError = "Anzeige: Wassermangel" + "\r\n" + "Erklärung: Kühlwasser fließt nicht" + "\r\n" + "Abhilfe: Wasser nachfüllen; aus/an";
                    break;
                case 5:
                    getError = "Anzeige: Bedienpanel" + "\r\n" + "Erklärung: Zwei gleich codierte FLGs im System" + "\r\n" + "Abhilfe: Service verständigen";
                    break;
                case 6:
                    getError = "Anzeige: iDSP-Typ-Fehler" + "\r\n" + "Erklärung: Unklarer Maschinentyp(E2/M2)" + "\r\n" + "Abhilfe: Service verständigen";
                    break;
                case 7:
                    getError = "Anzeige: iDSP-Adr-Fehler" + "\r\n" + "Erklärung: Gleiche Adresse entdeckt" + "\r\n" + "Abhilfe: Service verständigen";
                    break;
                case 8:
                    getError = "Anzeige: iDSP-Ausfall" + "\r\n" + "Erklärung: iDSP nicht erreichbar" + "\r\n" + "Abhilfe: Service verständigen";
                    break;
                case 9:
                    getError = "Anzeige: iDSP-SN-Fehler" + "\r\n" + "Erklärung: Falsche Seriennummer entdeckt" + "\r\n" + "Abhilfe: Service verständigen";
                    break;
                case 10:
                    getError = "Anzeige: Abregelung" + "\r\n" + "Erklärung: n.n." + "\r\n" + "Abhilfe: Service verständigen";
                    break;
                case 11:
                    getError = "Anzeige: Ueberstrom Sek." + "\r\n" + "Erklärung: Überstrom sekundär" + "\r\n" + "Abhilfe: Service verständigen";
                    break;
                case 12:
                    getError = "Anzeige: Luefter nicht ok" + "\r\n" + "Erklärung: Lüfter defekt" + "\r\n" + "Abhilfe: Service verständigen";
                    break;
                case 13:
                    getError = "Anzeige: Kurz. Temp. Sens." + "\r\n" + "Erklärung: Temperatursensorkurzschluss" + "\r\n" + "Abhilfe: Service verständigen";
                    break;
                case 14:
                    getError = "Anzeige: Bruch. Temp. Sens." + "\r\n" + "Erklärung: Temperatursensorbruch" + "\r\n" + "Abhilfe: Service verständigen";
                    break;
                case 15:
                    getError = "Anzeige: Summenfehler" + "\r\n" + "Erklärung: Summenanzeige Inverterfehler" + "\r\n" + "Abhilfe: Service verständigen";
                    break;
                case 16:
                    getError = "Anzeige: Regler-DSP" + "\r\n" + "Erklärung: Regler DSP ist nicht bereit" + "\r\n" + "Abhilfe: Service verständigen";
                    break;
                case 17:
                    getError = "Anzeige:  " + "\r\n" + "Erklärung: n.n" + "\r\n" + "Abhilfe: Service verständigen";
                    break;
                case 18:
                    getError = "Anzeige: iDSP Heart-Fehl." + "\r\n" + "Erklärung: iDSP Heart-Beat-Fehler" + "\r\n" + "Abhilfe: Service verständigen";
                    break;
                case 19:
                    getError = "Anzeige: iDSP SPI-Fehler" + "\r\n" + "Erklärung: iDSP SPI Time-Out-Fehler" + "\r\n" + "Abhilfe: Service verständigen";
                    break;
                case 20:
                    getError = "Anzeige: iDSP PFC |> Max" + "\r\n" + "Erklärung: iDSP PFC-Überstrom" + "\r\n" + "Abhilfe: Service verständigen";
                    break;
                case 21:
                    getError = "Anzeige: iDSP Netz < 450" + "\r\n" + "Erklärung: iDSP Netz < 450V" + "\r\n" + "Abhilfe: Service verständigen";
                    break;
                case 22:
                    getError = "Anzeige: iDSP Netz > 750" + "\r\n" + "Erklärung: iDSP Netz > 750V" + "\r\n" + "Abhilfe: Service verständigen";
                    break;
                case 23:
                    getError = "Anzeige: iDSP Gate |> Max" + "\r\n" + "Erklärung: iDSP Überstrom Gate" + "\r\n" + "Abhilfe: Service verständigen";
                    break;
                case 24:
                    getError = "Anzeige: iDSP Bridg. |> Max" + "\r\n" + "Erklärung: iDSP Überstrom Brücke" + "\r\n" + "Abhilfe: Service verständigen";
                    break;
                case 25:
                    getError = "Anzeige: iDSP Sens. Bruch" + "\r\n" + "Erklärung: iDSP Temperatursensor-Bruch" + "\r\n" + "Abhilfe: Service verständigen";
                    break;
                case 26:
                    getError = "Anzeige: iDSP Sens. Kurz." + "\r\n" + "Erklärung: iDSP Temperatursensor-Kurzschluss" + "\r\n" + "Abhilfe: Service verständigen";
                    break;
                case 27:
                    getError = "Anzeige: iDSP UZK800" + "\r\n" + "Erklärung: iDSP UZK > 800V" + "\r\n" + "Abhilfe: Service verständigen";
                    break;
                case 28:
                    getError = "Anzeige: Wasser-Übertemp." + "\r\n" + "Erklärung: Kühlwasser-Übertemperatur" + "\r\n" + "Abhilfe: nach Abkühlen; aus/an";
                    break;
                case 29:
                    getError = "Anzeige: KGP t-Sens Bruch" + "\r\n" + "Erklärung: KGP-Temperatursensor-Bruch" + "\r\n" + "Abhilfe: Service verständigen";
                    break;
                case 30:
                    getError = "Anzeige: KGP t-Sens Kurz." + "\r\n" + "Erklärung: KGP-Temperatursensor-Kurzschluss" + "\r\n" + "Abhilfe: Service verständigen";
                    break;
                case 31:
                    getError = "Anzeige:   " + "\r\n" + "Erklärung:   " + "\r\n" + "Abhilfe: Service verständigen";
                    break;
                default:
                    getError = "Anzeige: Error" + "\r\n" + "Erklärung: Error" + "\r\n" + "Abhilfe: Error";
                    break;
            }
            Log.e("Error Messages",getError);

        }else{

            /*getError = "Anzeige: Kein Fehler" + "\r\n" + "Erklärung: Kein Fehler" + "\r\n" + "Abhilfe: Kein Fehler";
            message = "Kein Fehler im CanId.";
            Log.i("Fehlermeldungen ",message);
            Log.i("Fehlermeldungen ",getError);*/

        }
    }
}