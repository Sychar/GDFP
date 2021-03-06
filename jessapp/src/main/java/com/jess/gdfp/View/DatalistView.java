/**
 * @Autor Sakhr Aljendi
 * @Version 1.0
 * 011.01.2020
 */

package com.jess.gdfp.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.ListView;
import android.widget.TextView;
import com.jess.gdfp.Controller.DatenLoggerAdapter;
import com.jess.gdfp.DatenBank.Datenlogger;
import com.jess.gdfp.DatenBank.InfoContract;
import com.jess.gdfp.DatenObjekte;
import com.jess.gdfp.GlobalVariable;
import com.jess.gdfp.R;
import java.util.ArrayList;

public class DatalistView extends AppCompatActivity  {
    public static int timer=1;
    private static final int COUNT_LOADR=0;
    DatenLoggerAdapter datenLoggerAdapter;
    private TextView Time_view;
    private TextView Date_view;
    private ListView listView;
    static public String dateString ="1";
    //private static String[] NEW_STRING ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datalist_view);

        listView =(ListView) findViewById(R.id.datalistview);
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(this);
        Time_view = findViewById(R.id.TIME_DL);
        Date_view = findViewById(R.id.DATE_DL);
        //datenLoggerAdapter=new DatenLoggerAdapter(this,initDatenlogger());
        //listView.setAdapter(datenLoggerAdapter);
        datenLoggerAdapter = new DatenLoggerAdapter(DatalistView.this,initDatenlogger());
        datetime_thread();
    }

    static public String[] infosArray = {
            //InfoContract.infoEntry.COLUMN_TIME_ ,
            InfoContract.infoEntry.Verfahren,
            InfoContract.infoEntry.Betriebsart,
            InfoContract.infoEntry.Drahtdurchmesser,
            InfoContract.infoEntry.Gas,
            InfoContract.infoEntry.Werkstoff,
            InfoContract.infoEntry.Reglertyp,
            InfoContract.infoEntry.StatusMSR,
            InfoContract.infoEntry.StatusFLG,
            InfoContract.infoEntry.kennliniennummer,
            InfoContract.infoEntry.Jobnummer,
            InfoContract.infoEntry.kennlinienTyp,
            InfoContract.infoEntry.JobKommando,
            InfoContract.infoEntry.JobStatus,
            InfoContract.infoEntry.Verriegelungsstufe,
            InfoContract.infoEntry.Gasvorströmen,
            InfoContract.infoEntry.Gasnachströmen,
            InfoContract.infoEntry.Einschleichenabsolut,
            InfoContract.infoEntry.EinschleichenKorrektur,
            InfoContract.infoEntry.UPSlope,
            InfoContract.infoEntry.DownSlope,
            InfoContract.infoEntry.Zündenergiein,
            InfoContract.infoEntry.EndkraterEnergie,
            InfoContract.infoEntry.GebirgeStatus,
            InfoContract.infoEntry.SchweißState,
            InfoContract.infoEntry.Freibrand,
            InfoContract.infoEntry.FreibrandKorrektur,
            InfoContract.infoEntry.KorrekturPulsamplitude,
            InfoContract.infoEntry.KorrekturDrossel,
            InfoContract.infoEntry.Einfädeln ,
            InfoContract.infoEntry.GastestZeit,
            InfoContract.infoEntry.Pausenzeitbeiinterval,
            InfoContract.infoEntry.Punktzeit,
            InfoContract.infoEntry.ZündDauer,
            InfoContract.infoEntry.EndkraterDauer,
            InfoContract.infoEntry.SynergieVorgabeBasis,
            InfoContract.infoEntry.AnzahlLeistungsmodule ,
            InfoContract.infoEntry.PowerpulsEinAus,
            InfoContract.infoEntry.PowerpilsEnergie,
            InfoContract.infoEntry.PowerPulsTime1fürEnergie1,
            InfoContract.infoEntry.PowerPulsTime2fürEnergie1,
            InfoContract.infoEntry.PowerPulsLBKorrEnergie2,
            InfoContract.infoEntry.PowerPulsUpSlope,
            InfoContract.infoEntry.PowerPulsDownSlope,
            InfoContract.infoEntry.JobSlope,
            InfoContract.infoEntry.ZündStrom,
            InfoContract.infoEntry.ZündSpannung,
            InfoContract.infoEntry.ZündEnergie,
            InfoContract.infoEntry.ZündDrossel,
            InfoContract.infoEntry.ZündLichtbogenkorrektur,
            InfoContract.infoEntry.Strom1,
            InfoContract.infoEntry.Spannung1,
            InfoContract.infoEntry.Energie1,
            InfoContract.infoEntry.Drossel1 ,
            InfoContract.infoEntry.Lichtbogenkorrektur1,
            InfoContract.infoEntry.Strom2,
            InfoContract.infoEntry.Spannung2,
            InfoContract.infoEntry.Energie2,
            InfoContract.infoEntry.Drossel2 ,
            InfoContract.infoEntry.Lichtbogenkorrektur2,
            InfoContract.infoEntry.Strom3,
            InfoContract.infoEntry.Spannung3,
            InfoContract.infoEntry.Energie3,
            InfoContract.infoEntry.Drossel3,
            InfoContract.infoEntry.Lichtbogenkorrektur3,
            InfoContract.infoEntry.Endkraterstrom,
            InfoContract.infoEntry.EndkraterSpannung,
            InfoContract.infoEntry.Endkraterenergie,
            InfoContract.infoEntry.EndkraterDrossel,
            InfoContract.infoEntry.EndkraterLichtbogenkorrektur,
            InfoContract.infoEntry.VorschubSetwert,
            InfoContract.infoEntry.Vorschubistwert,
            InfoContract.infoEntry.VorschubHoldwert,
            InfoContract.infoEntry.VorschubStatus,
            InfoContract.infoEntry.VorschubausKennlinie,
            InfoContract.infoEntry.StromSetwert,
            InfoContract.infoEntry.Stromistwert,
            InfoContract.infoEntry.StromHoldwert,
            InfoContract.infoEntry.StromStatus,
            InfoContract.infoEntry.StromInkremental,
            InfoContract.infoEntry.SpannungSetwert,
            InfoContract.infoEntry.SpannungIstwert,
            InfoContract.infoEntry.SpannungHoldwert,
            InfoContract.infoEntry.SpannungStatus,
            InfoContract.infoEntry.SpannungInkremental,
            InfoContract.infoEntry.BlechdickeSetwert ,
            InfoContract.infoEntry.BlechdickeIstwert,
            InfoContract.infoEntry.BlechdickeHoldwert,
            InfoContract.infoEntry.BlechdickeStatus,
            //InfoContract.infoEntry.Reset,
            InfoContract.infoEntry.ElektrodeStromSetwert,
            InfoContract.infoEntry.ElektrodeStromIstwert,
            InfoContract.infoEntry.HotstartDauer,
            InfoContract.infoEntry.Hotstart,
            InfoContract.infoEntry.ArcForce ,
            InfoContract.infoEntry.InnenwiderstandfürElektrode,
            InfoContract.infoEntry.RMTPosAmplitude,
            InfoContract.infoEntry.RMTNegAmplitude,
            InfoContract.infoEntry.StartAmplitude,
            InfoContract.infoEntry.StartZeit,
            InfoContract.infoEntry.Startueberhoehung,
            InfoContract.infoEntry.InnenwiderstandfürDossel,
            InfoContract.infoEntry.Überblendzeit,
            InfoContract.infoEntry.DrosselAbfall,
            InfoContract.infoEntry.MotorFlanke,
            InfoContract.infoEntry.DrosselDynamic,
            InfoContract.infoEntry.MAGACPositiveZeit,
            InfoContract.infoEntry.MAGACStromschwellwert,
            InfoContract.infoEntry.LBRMode,
            InfoContract.infoEntry.MAGACBetriebsart,
            InfoContract.infoEntry.MAGACKältewert,
            InfoContract.infoEntry.MAGACNegativZeit,
            InfoContract.infoEntry.MAGACKurzschlusserkennung,
            InfoContract.infoEntry.MAGACKurzschlussaufhebung,
            InfoContract.infoEntry.MAGAGVerweilzeitPosNeg,
            InfoContract.infoEntry.MAGAGVerweilzeitNegPos,
            InfoContract.infoEntry.WIGSpeedPulsFrequenz,
            InfoContract.infoEntry.WIGSpeedPulsI1Anteil,
            InfoContract.infoEntry.WIGSpeedPulsI3,
            InfoContract.infoEntry.GasSollwert,
            InfoContract.infoEntry.UserNummer,
            InfoContract.infoEntry.WIGACStromoffset,
            InfoContract.infoEntry.WIGstatus,
            InfoContract.infoEntry.WIGACFrequenz,
            InfoContract.infoEntry.WIGACBalance,
            InfoContract.infoEntry.WIGDurchmesserWolframElektrode,
            InfoContract.infoEntry.WIGBetriebsartWechselrichtwer,
            InfoContract.infoEntry.KaltdrahtpulsenT1,
            InfoContract.infoEntry.WIGStromLimit,
            InfoContract.infoEntry.KHMode,
            InfoContract.infoEntry.VerzögerungsZeitKaltdrahtEin,
            InfoContract.infoEntry.VerzögerungsZeitKaltdrahtAus,
            InfoContract.infoEntry.VerzögerungsZeitHeißdrahteuberwachung,
            InfoContract.infoEntry.Vorpositionierungsstrecke,
            InfoContract.infoEntry.Rückzugsstrecke,
            InfoContract.infoEntry.KaltdrahtpulsenT1,
            InfoContract.infoEntry.KH_Status,
    };

    private  static String[] VALUE_STRING = {
            GlobalVariable.Verfahren,
            GlobalVariable.Betriebsart_String[GlobalVariable.SV1pos2],
            String.valueOf(GlobalVariable.Drahtdurchmesser), //pos 3 DrahtDurchmesser
            GlobalVariable.Gas,
            GlobalVariable.Werkstoff,
            GlobalVariable.Reglertyp,
            String.valueOf(GlobalVariable.StatusMSR),
            GlobalVariable.StatusFLG_String,
            String.valueOf(GlobalVariable.Kennliniennummer),
            String.valueOf(GlobalVariable.Jobnummer),
            GlobalVariable.KennlinienTyp_String,
            String.valueOf(GlobalVariable.JobKommando),
            String.valueOf(GlobalVariable.JobStatus_String),
            String.valueOf(GlobalVariable.Verriegelungsstufe),
            String.valueOf(GlobalVariable.Gasvorströmen),
            String.valueOf(GlobalVariable.Gasnachströmen),
            String.valueOf(GlobalVariable.EinschleichenAbsolut),
            String.valueOf(GlobalVariable.EinschleichenKorrektur),
            String.valueOf(GlobalVariable.UpSlope),
            String.valueOf(GlobalVariable.DownSlope),
            String.valueOf(GlobalVariable.Zündenergie),
            String.valueOf(GlobalVariable.Endkraterenergie),
            String.valueOf(GlobalVariable.GebirgeStatus),
            String.valueOf(GlobalVariable.SchweißState),
            String.valueOf(GlobalVariable.Freibrand),
            String.valueOf(GlobalVariable.FreibandKorrektur),
            String.valueOf(GlobalVariable.KorrekturPulsamplitude),
            String.valueOf(GlobalVariable.KorrekturDrossel),
            String.valueOf(GlobalVariable.Einfädeln),
            String.valueOf(GlobalVariable.GastestZeit),
            String.valueOf(GlobalVariable.PausenZeit),
            String.valueOf(GlobalVariable.Punktzeit),
            String.valueOf(GlobalVariable.ZündDauer),
            String.valueOf(GlobalVariable.EndkraterDauer),
            String.valueOf(GlobalVariable.SynergieVorgabe),
            String.valueOf(GlobalVariable.AnzahlLeistungsmodule),
            GlobalVariable.PowerpulsEinAus_String,
            String.valueOf(GlobalVariable.PowerpulsE2),
            String.valueOf(GlobalVariable.PowerpulsT1E1),
            String.valueOf(GlobalVariable.PowerpulsT2E1),
            String.valueOf(GlobalVariable.PowerpulsLBKorrE2),
            String.valueOf(GlobalVariable.PowerpulsUpSlope),
            String.valueOf(GlobalVariable.PowerpulsDownSlope),
            String.valueOf(GlobalVariable.JobSlope),
            String.valueOf(GlobalVariable.ZündStrom),
            String.valueOf(GlobalVariable.ZündSpannung),
            String.valueOf(GlobalVariable.ZündEnergie),
            String.valueOf(GlobalVariable.ZündDrossel),
            String.valueOf(GlobalVariable.ZündLichtbogenkorrektur),
            String.valueOf(GlobalVariable.Strom1),
            String.valueOf(GlobalVariable.Spannung1),
            String.valueOf(GlobalVariable.Energie1),
            String.valueOf(GlobalVariable.Drossel1),
            String.valueOf(GlobalVariable.Lichtbogenkorrektur1),
            String.valueOf(GlobalVariable.Strom2),
            String.valueOf(GlobalVariable.Spannung2),
            String.valueOf(GlobalVariable.Energie2),
            String.valueOf(GlobalVariable.Drossel2),
            String.valueOf(GlobalVariable.Lichtbogenkorrektur2),
            String.valueOf(GlobalVariable.Strom3),
            String.valueOf(GlobalVariable.Spannung3),
            String.valueOf(GlobalVariable.Energie3),
            String.valueOf(GlobalVariable.Drossel3),
            String.valueOf(GlobalVariable.Lichtbogenkorrektur3),
            String.valueOf(GlobalVariable.EndkraterStrom),
            String.valueOf(GlobalVariable.EndkraterSpannung),
            String.valueOf(GlobalVariable.EndkraterEnergie),
            String.valueOf(GlobalVariable.EndkraterDrossel),
            String.valueOf(GlobalVariable.EndKraterLichtbogenkorrektur),
            String.valueOf(GlobalVariable.VorschubSetwert),
            String.valueOf(GlobalVariable.VorschubIstwert),
            String.valueOf(GlobalVariable.VorschubHoldwert),
            String.valueOf(GlobalVariable.VorschubStatus),
            String.valueOf(GlobalVariable.VorschubAusKennlinie),
            String.valueOf(GlobalVariable.StromSetwert),
            String.valueOf(GlobalVariable.StromIstwert),
            String.valueOf(GlobalVariable.StromHoldwert),
            String.valueOf(GlobalVariable.StromStatus),
            String.valueOf(GlobalVariable.StromInkremental),
            String.valueOf(GlobalVariable.SpannungSetwert),
            String.valueOf(GlobalVariable.SpannungIstwert),
            String.valueOf(GlobalVariable.SpannungHoldwert),
            String.valueOf(GlobalVariable.SpannungStatus),
            String.valueOf(GlobalVariable.SpannungInkremental),
            String.valueOf(GlobalVariable.BlechdickeSetwert),
            String.valueOf(GlobalVariable.BlechdickeIstwert),
            String.valueOf(GlobalVariable.BlechdickeHoldwert),
            String.valueOf(GlobalVariable.BlechdickeStatus),
            //DatenObjekte.Reset_String,
            String.valueOf(GlobalVariable.ElektrodeStromSetwert),
            String.valueOf(GlobalVariable.ElektrodeStromIstwert),
            String.valueOf(GlobalVariable.HotstartDauer),
            String.valueOf(GlobalVariable.Hotstart),
            String.valueOf(GlobalVariable.ArcForce),
            String.valueOf(GlobalVariable.InnenwiderstandfürElektrode),
            String.valueOf(GlobalVariable.RMTPosAmplitude),
            String.valueOf(GlobalVariable.RMTNegAmplitude),
            String.valueOf(GlobalVariable.StartAmplitude),
            String.valueOf(GlobalVariable.StartZeit),
            String.valueOf(GlobalVariable.StartÜberhöhung),
            String.valueOf(GlobalVariable.InnenwiderstandfürDossel),
            String.valueOf(GlobalVariable.Überblendzeit),
            String.valueOf(GlobalVariable.DrosselAbfall),
            String.valueOf(GlobalVariable.MotorFlanke),
            String.valueOf(GlobalVariable.DrosselDynamic),
            String.valueOf(GlobalVariable.MAGACPositiveZeit),
            String.valueOf(GlobalVariable.MAGACStromschwellwert),
            String.valueOf(GlobalVariable.LBRMode),
            GlobalVariable.MAGACBetriebsart_String,
            String.valueOf(GlobalVariable.MAGACKältewert),
            String.valueOf(GlobalVariable.MAGACNegativZeit),
            String.valueOf(GlobalVariable.MAGACKurzschlusserkennung),
            String.valueOf(GlobalVariable.MAGACKurzschlussaufhebung),
            String.valueOf(GlobalVariable.MACAGVerweilzeitPosNeg),
            String.valueOf(GlobalVariable.MACAGVerweilzeitNegPos),
            String.valueOf(GlobalVariable.WIGSpeedPulsFrequenz),
            String.valueOf(GlobalVariable.WIGSpeedPulsI1Anteil),
            String.valueOf(GlobalVariable.WIGSpeedPulsI3),
            String.valueOf(GlobalVariable.GasSollwert),
            String.valueOf(GlobalVariable.UserNummer),
            String.valueOf(GlobalVariable.WIGACStromoffset),
            GlobalVariable.WIGStatus_String,
            String.valueOf(GlobalVariable.WIGACFrequenz),
            String.valueOf(GlobalVariable.WIGACBalance),
            String.valueOf(GlobalVariable.WIGDurchmesserWolframElektrode),
            GlobalVariable.WIGBetriebsartWechselrichter_String,
            String.valueOf(GlobalVariable.KaltdrahtpulsenT1SV21_5),
            String.valueOf(GlobalVariable.WIGStromLimit),
            GlobalVariable.KHMode_String,
            String.valueOf(GlobalVariable.VerzögerungsZeitKaltdrahtEin),
            String.valueOf(GlobalVariable.VerzögerungsZeitKaltdrahtAus),
            String.valueOf(GlobalVariable.VerzögerungsZeitHeißdrahtÜberwachung),
            String.valueOf(GlobalVariable.Vorpositionierungsstrecke),
            String.valueOf(GlobalVariable.Rückzugsstrecke),
            String.valueOf(GlobalVariable.KaltdrahtpulsenT1SV22_7),
            GlobalVariable.KHStatus_String,
    };

    private void datetime_thread(){
        Thread t = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                initDatenlogger();
                                String[] DATALOGGER_PARAM = RefreshString();
                                listView.setAdapter(datenLoggerAdapter);
                                Time_view.setText(String.valueOf(DatenObjekte.HOUR)+":"+String.valueOf(DatenObjekte.MINUTE)+":"+String.valueOf(DatenObjekte.SECOND));
                                Date_view.setText(String.valueOf(DatenObjekte.DAY)+"/"+String.valueOf(DatenObjekte.MONTH)+"/"+"20"+String.valueOf(DatenObjekte.YEAR));
                              //  datenLoggerAdapter.clear();
                                ArrayList Datenlogger = new ArrayList<>();
                                for (int i=0; i<infosArray.length; i++) {
                                    Datenlogger.add(new Datenlogger(infosArray[i],DATALOGGER_PARAM[i]));
                                }
                                datenLoggerAdapter=new DatenLoggerAdapter(DatalistView.this,Datenlogger);
                                if(DatenObjekte.HOUR<10) Time_view.setText("0"+String.valueOf(DatenObjekte.HOUR)+":"+String.valueOf(DatenObjekte.MINUTE)+":"+String.valueOf(DatenObjekte.SECOND));
                                else if(DatenObjekte.MINUTE<10) Time_view.setText(String.valueOf(DatenObjekte.HOUR)+":0"+String.valueOf(DatenObjekte.MINUTE)+":"+String.valueOf(DatenObjekte.SECOND));
                                else if(DatenObjekte.SECOND<10) Time_view.setText(String.valueOf(DatenObjekte.HOUR)+":"+String.valueOf(DatenObjekte.MINUTE)+":0"+String.valueOf(DatenObjekte.SECOND));
                                else Time_view.setText(String.valueOf(DatenObjekte.HOUR)+":"+String.valueOf(DatenObjekte.MINUTE)+":"+String.valueOf(DatenObjekte.SECOND));

                                if(DatenObjekte.DAY<10) Date_view.setText("0"+String.valueOf(DatenObjekte.DAY)+"/0"+String.valueOf(DatenObjekte.MONTH)+"/"+"20"+String.valueOf(DatenObjekte.YEAR));
                                else if(DatenObjekte.MONTH<10) Date_view.setText(String.valueOf(DatenObjekte.DAY)+"/0"+String.valueOf(DatenObjekte.MONTH)+"/"+"20"+String.valueOf(DatenObjekte.YEAR));
                                else if(DatenObjekte.YEAR<10) Date_view.setText(String.valueOf(DatenObjekte.DAY)+"/"+String.valueOf(DatenObjekte.MONTH)+"/"+"200"+String.valueOf(DatenObjekte.YEAR));
                                else Date_view.setText(String.valueOf(DatenObjekte.DAY)+"/"+String.valueOf(DatenObjekte.MONTH)+"/"+"20"+String.valueOf(DatenObjekte.YEAR));
                                initDatenlogger().clear();
                              //  datenLoggerAdapter=new DatenLoggerAdapter(DatalistView.this,initDatenlogger());
                                datenLoggerAdapter.notifyDataSetChanged();
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        t.start();
    }

    private ArrayList initDatenlogger(){
        ArrayList Datenlogger =new ArrayList<>();
        for (int i=0;i<infosArray.length;i++){
            Datenlogger.add(new Datenlogger(infosArray[i],VALUE_STRING[i]));
        }
        return Datenlogger;
    }

    private static String[] RefreshString(){
          String[] NEW_STRING = {
                GlobalVariable.Verfahren_String[GlobalVariable.SV1pos1], //Verfahren
                GlobalVariable.Betriebsart_String[GlobalVariable.SV1pos2], //Betriebsart
                String.valueOf(GlobalVariable.Drahtdurchmesser), //pos 3 DrahtDurchmesser
                GlobalVariable.Gas_String[GlobalVariable.SV1pos2], //Gas
                GlobalVariable.Werksotff_String[GlobalVariable.SV1pos5], //Werkstoff
                GlobalVariable.Reglertyp,
                String.valueOf(GlobalVariable.StatusMSR),
                GlobalVariable.StatusFLG_String,
                String.valueOf(GlobalVariable.Kennliniennummer),
                String.valueOf(GlobalVariable.Jobnummer),
                GlobalVariable.KennlinienTyp_String,
                String.valueOf(GlobalVariable.JobKommando),
                String.valueOf(GlobalVariable.JobStatus_String),
                String.valueOf(GlobalVariable.Verriegelungsstufe),
                String.valueOf(GlobalVariable.Gasvorströmen),
                String.valueOf(GlobalVariable.Gasnachströmen),
                String.valueOf(GlobalVariable.EinschleichenAbsolut),
                String.valueOf(GlobalVariable.EinschleichenKorrektur),
                String.valueOf(GlobalVariable.UpSlope),
                String.valueOf(GlobalVariable.DownSlope),
                String.valueOf(GlobalVariable.Zündenergie),
                String.valueOf(GlobalVariable.Endkraterenergie),
                String.valueOf(GlobalVariable.GebirgeStatus),
                String.valueOf(GlobalVariable.SchweißState),
                String.valueOf(GlobalVariable.Freibrand),
                String.valueOf(GlobalVariable.FreibandKorrektur),
                String.valueOf(GlobalVariable.KorrekturPulsamplitude),
                String.valueOf(GlobalVariable.KorrekturDrossel),
                String.valueOf(GlobalVariable.Einfädeln),
                String.valueOf(GlobalVariable.GastestZeit),
                String.valueOf(GlobalVariable.PausenZeit),
                String.valueOf(GlobalVariable.Punktzeit),
                String.valueOf(GlobalVariable.ZündDauer),
                String.valueOf(GlobalVariable.EndkraterDauer),
                String.valueOf(GlobalVariable.SynergieVorgabe),
                String.valueOf(GlobalVariable.AnzahlLeistungsmodule),
                GlobalVariable.PowerpulsEinAus_String,
                String.valueOf(GlobalVariable.PowerpulsE2),
                String.valueOf(GlobalVariable.PowerpulsT1E1),
                String.valueOf(GlobalVariable.PowerpulsT2E1),
                String.valueOf(GlobalVariable.PowerpulsLBKorrE2),
                String.valueOf(GlobalVariable.PowerpulsUpSlope),
                String.valueOf(GlobalVariable.PowerpulsDownSlope),
                String.valueOf(GlobalVariable.JobSlope),
                String.valueOf(GlobalVariable.ZündStrom),
                String.valueOf(GlobalVariable.ZündSpannung),
                String.valueOf(GlobalVariable.ZündEnergie),
                String.valueOf(GlobalVariable.ZündDrossel),
                String.valueOf(GlobalVariable.ZündLichtbogenkorrektur),
                String.valueOf(GlobalVariable.Strom1),
                String.valueOf(GlobalVariable.Spannung1),
                String.valueOf(GlobalVariable.Energie1),
                String.valueOf(GlobalVariable.Drossel1),
                String.valueOf(GlobalVariable.Lichtbogenkorrektur1),
                String.valueOf(GlobalVariable.Strom2),
                String.valueOf(GlobalVariable.Spannung2),
                String.valueOf(GlobalVariable.Energie2),
                String.valueOf(GlobalVariable.Drossel2),
                String.valueOf(GlobalVariable.Lichtbogenkorrektur2),
                String.valueOf(GlobalVariable.Strom3),
                String.valueOf(GlobalVariable.Spannung3),
                String.valueOf(GlobalVariable.Energie3),
                String.valueOf(GlobalVariable.Drossel3),
                String.valueOf(GlobalVariable.Lichtbogenkorrektur3),
                String.valueOf(GlobalVariable.EndkraterStrom),
                String.valueOf(GlobalVariable.EndkraterSpannung),
                String.valueOf(GlobalVariable.EndkraterEnergie),
                String.valueOf(GlobalVariable.EndkraterDrossel),
                String.valueOf(GlobalVariable.EndKraterLichtbogenkorrektur),
                String.valueOf(GlobalVariable.VorschubSetwert),
                String.valueOf(GlobalVariable.VorschubIstwert),
                String.valueOf(GlobalVariable.VorschubHoldwert),
                String.valueOf(GlobalVariable.VorschubStatus),
                String.valueOf(GlobalVariable.VorschubAusKennlinie),
                String.valueOf(GlobalVariable.StromSetwert),
                String.valueOf(GlobalVariable.StromIstwert),
                String.valueOf(GlobalVariable.StromHoldwert),
                String.valueOf(GlobalVariable.StromStatus),
                String.valueOf(GlobalVariable.StromInkremental),
                String.valueOf(GlobalVariable.SpannungSetwert),
                String.valueOf(GlobalVariable.SpannungIstwert),
                String.valueOf(GlobalVariable.SpannungHoldwert),
                String.valueOf(GlobalVariable.SpannungStatus),
                String.valueOf(GlobalVariable.SpannungInkremental),
                String.valueOf(GlobalVariable.BlechdickeSetwert),
                String.valueOf(GlobalVariable.BlechdickeIstwert),
                String.valueOf(GlobalVariable.BlechdickeHoldwert),
                String.valueOf(GlobalVariable.BlechdickeStatus),
                //GlobalVariable.Reset_String,
                String.valueOf(GlobalVariable.ElektrodeStromSetwert),
                String.valueOf(GlobalVariable.ElektrodeStromIstwert),
                String.valueOf(GlobalVariable.HotstartDauer),
                String.valueOf(GlobalVariable.Hotstart),
                String.valueOf(GlobalVariable.ArcForce),
                String.valueOf(GlobalVariable.InnenwiderstandfürElektrode),
                String.valueOf(GlobalVariable.RMTPosAmplitude),
                String.valueOf(GlobalVariable.RMTNegAmplitude),
                String.valueOf(GlobalVariable.StartAmplitude),
                String.valueOf(GlobalVariable.StartZeit),
                String.valueOf(GlobalVariable.StartÜberhöhung),
                String.valueOf(GlobalVariable.InnenwiderstandfürDossel),
                String.valueOf(GlobalVariable.Überblendzeit),
                String.valueOf(GlobalVariable.DrosselAbfall),
                String.valueOf(GlobalVariable.MotorFlanke),
                String.valueOf(GlobalVariable.DrosselDynamic),
                String.valueOf(GlobalVariable.MAGACPositiveZeit),
                String.valueOf(GlobalVariable.MAGACStromschwellwert),
                String.valueOf(GlobalVariable.LBRMode),
                GlobalVariable.MAGACBetriebsart_String,
                String.valueOf(GlobalVariable.MAGACKältewert),
                String.valueOf(GlobalVariable.MAGACNegativZeit),
                String.valueOf(GlobalVariable.MAGACKurzschlusserkennung),
                String.valueOf(GlobalVariable.MAGACKurzschlussaufhebung),
                String.valueOf(GlobalVariable.MACAGVerweilzeitPosNeg),
                String.valueOf(GlobalVariable.MACAGVerweilzeitNegPos),
                String.valueOf(GlobalVariable.WIGSpeedPulsFrequenz),
                String.valueOf(GlobalVariable.WIGSpeedPulsI1Anteil),
                String.valueOf(GlobalVariable.WIGSpeedPulsI3),
                String.valueOf(GlobalVariable.GasSollwert),
                String.valueOf(GlobalVariable.UserNummer),
                String.valueOf(GlobalVariable.WIGACStromoffset),
                GlobalVariable.WIGStatus_String,
                String.valueOf(GlobalVariable.WIGACFrequenz),
                String.valueOf(GlobalVariable.WIGACBalance),
                String.valueOf(GlobalVariable.WIGDurchmesserWolframElektrode),
                GlobalVariable.WIGBetriebsartWechselrichter_String,
                String.valueOf(GlobalVariable.KaltdrahtpulsenT1SV21_5),
                String.valueOf(GlobalVariable.WIGStromLimit),
                GlobalVariable.KHMode_String,
                String.valueOf(GlobalVariable.VerzögerungsZeitKaltdrahtEin),
                String.valueOf(GlobalVariable.VerzögerungsZeitKaltdrahtAus),
                String.valueOf(GlobalVariable.VerzögerungsZeitHeißdrahtÜberwachung),
                String.valueOf(GlobalVariable.Vorpositionierungsstrecke),
                String.valueOf(GlobalVariable.Rückzugsstrecke),
                String.valueOf(GlobalVariable.KaltdrahtpulsenT1SV22_7),
                GlobalVariable.KHStatus_String,
                  //------------------------ DEV DSP --------------------------------------
                /*GlobalVariable.StartZeit_WIG_DVE,
                  GlobalVariable.EndZeit_WIG_DVE,
                  GlobalVariable.Freibrand,
                  GlobalVariable.Freibrand,
                  GlobalVariable.KorrekturPulsamplitude,
                  GlobalVariable.KorrekturDrossel,
                  GlobalVariable.Einfädeln,
                  GlobalVariable.VorschubSollLow_DVE,
                  GlobalVariable.VorschubSollHigh_DVE,
                  GlobalVariable.VorschubEindchleichenLow_DVE,
        GlobalVariable.VorschubEindchleichenHigh_DVE,
                  GlobalVariable.Brennertasten,
                  GlobalVariable.Optionen,
                  GlobalVariable.VorschubStatus0,
                  GlobalVariable.VorschubStatus1,
                  GlobalVariable.KHMode,
                  GlobalVariable.VerzögerungsZeitKaltdrahtEin,
                  GlobalVariable.VerzögerungsZeitKaltdrahtAus,
                  GlobalVariable.VerzögerungsZeitHeißdrahtÜberwachung,
                  GlobalVariable.Vorpositionierungsstrecke,
                  GlobalVariable.KaltdrahtpulsenT1SV22_7, // 0x303
                  GlobalVariable.KHStatus,
                  GlobalVariable.InnenwiderstandfürDossel,
                  GlobalVariable.Überblendzeit,
                  GlobalVariable.DrosselAbfall,
                  GlobalVariable.MotorFlanke,
                  GlobalVariable.DrosselDynamic,
                  GlobalVariable.MAGACPositiveZeit,
                  GlobalVariable.MAGACStromschwellwert,
                  GlobalVariable.LBRMode,
                  GlobalVariable.IndexMOTORIni_DVE,
                  GlobalVariable.Status_DVE //Status 0x701
                  GlobalVariable.StatusMSR_DVE,
                  GlobalVariable.SeriennummerMonat_DVE,
                  GlobalVariable.SeriennummerJahr_DVE,
                  GlobalVariable.SeriennummerLSB_DVE,
                  GlobalVariable.SeriennummerMSB_DVE,
                  GlobalVariable.PCBKennungBestückung_DVE,
                  GlobalVariable.DrahtvorschubIst, //low and high bytes
                  GlobalVariable.SchweissspanungIst, //low and high bytes
                  GlobalVariable.Brennertasten,
                  GlobalVariable.Optionen,
                  GlobalVariable.VorschubStatus0,
                  GlobalVariable.VorschubStatus1,
                  GlobalVariable.PistoleIstLow_DVE,
                  GlobalVariable.PistoleIstHigh_DVE,
                  GlobalVariable.SchweissstromistLow_DVE,
                  GlobalVariable.SchweissspgHigh_DVE,
                  GlobalVariable.DrahtgeschwindigkeitLow_DVE,
                  GlobalVariable.DrahtgeschwindigkeitHigh_DVE,
                  GlobalVariable.DurchflussLow_DVE,
                  GlobalVariable.DurchflussHigh_DVE,
                  GlobalVariable.Status1_DVE,
                  GlobalVariable.SeriennummerMonat1_DVE,
                  GlobalVariable.SeriennummerJahr1_DVE,
                  GlobalVariable.SeriennummerLSB1_DVE,
                  GlobalVariable.SeriennummerMSB1_DVE,
                  GlobalVariable.PCBKennungBestückung1_DVE,
                  //------------------------------ IDSP ---------------------------------
                  GlobalVariable.EffektivstromLow_IDSP,
                  GlobalVariable.EffektivstromHigh_IDSP,
                  GlobalVariable.EffektivspannungLow_IDSP,
                  GlobalVariable.EffektivspannungHigh_IDSP,
                  GlobalVariable.LüfterdrehzahlLow_IDSP,
                  GlobalVariable.LüfterdrehzahlHigh_IDSP,
                  GlobalVariable.StartStop_IDSP,
                  GlobalVariable.Stromfließt_IDSP,
                  GlobalVariable.Machinentyp_IDSP,
                  GlobalVariable.Modulzahl_IDSP,
                  GlobalVariable.Statusflag1_IDSP,
                  GlobalVariable.Statusflag2_IDSP,
                  GlobalVariable.Statusflag3_IDSP,
                  GlobalVariable.Statusflag4_IDSP,
                  GlobalVariable.Statusflag1_IDSP,
                  GlobalVariable.Stromanteil_iDSP1,
                  GlobalVariable.Stromanteil_iDSP2,
                  GlobalVariable.Stromanteil_iDSP3,
                  GlobalVariable.Stromanteil_iDSP4,
                  GlobalVariable.Stromanteil_iDSP5,
                  GlobalVariable.Stromanteil_iDSP6,
                  GlobalVariable.Stromanteil_iDSP7,
                  GlobalVariable.Stromanteil_iDSP8,
                  GlobalVariable.Index_IDSP,
                  GlobalVariable.Zugriffsart_IDSP,
                  GlobalVariable.Status_IDSP,
                  GlobalVariable.SeriennummerMonat_IDSP,
                  GlobalVariable.SeriennummerJahr_IDSP,
                  GlobalVariable.SeriennummerLSB_IDSP,
                  GlobalVariable.SeriennummerMSB_IDSP,
                  GlobalVariable.PCBKennung_Bestückung_IDSP,
                  GlobalVariable.Status1_IDSP,
                  GlobalVariable.Status2_IDSP,
                  GlobalVariable.Status3_IDSP,
                  GlobalVariable.Temperatur1_IDSP,
                  GlobalVariable.Temperatur2_IDSP,
                  GlobalVariable.ZwischenKreisspgLow_IDSP,
                  GlobalVariable.ZwischenKreisspgHigh_IDSP,
                  GlobalVariable.PrimärstromLow_IDSP,
                  GlobalVariable.PrimärstromHigh_IDSP,
                  GlobalVariable.Index1_IDSP,
                  GlobalVariable.Zugriffsart1_IDSP,
                  GlobalVariable.Status1_IDSP,
                  GlobalVariable.SeriennummerMonat1_IDSP,
                  GlobalVariable.SeriennummerJahr1_IDSP,
                  GlobalVariable.SeriennummerLSB1_IDSP,
                  GlobalVariable.SeriennummerMSB1_IDSP,
                  GlobalVariable.PCBKennung_Bestückung1_IDSP,
                  //------------------Regler DSP------------------------------
                  GlobalVariable.SV1pos1,
                  GlobalVariable.SV1pos2,
                  GlobalVariable.Drahtdurchmesser,
                  GlobalVariable.SV1pos4,
                  GlobalVariable.SV1pos5,
                  GlobalVariable.SV1pos6,
                  GlobalVariable.StatusMSR,
                  GlobalVariable.StatusFLG,
                  GlobalVariable.LichtbogenLängeLow_ReglerDSP,
                  GlobalVariable.LichtbogenLängeHigh_ReglerDSP,
                  GlobalVariable.LichtbogenKorrektur1_ReglerDSP,
                  GlobalVariable.LichtbogenKorrektur2_ReglerDSP,*/
                  /*GlobalVariable.LichtbogenKorrektur3_ReglerDSP,
                  GlobalVariable.LichtbogenKorrektur4_ReglerDSP,
                  GlobalVariable.LichtbogenKorrektur5_ReglerDSP,
                  GlobalVariable.Endpulsdauer_ReglerDSP,
                  GlobalVariable.Gasvorströmen,
                  GlobalVariable.Gasnachströmen,
                  GlobalVariable.UpSlope,
                  GlobalVariable.DownSlope,
                  GlobalVariable.Zündenergie,
                  GlobalVariable.Endkraterenergie,
                  GlobalVariable.PowerpulsEinAus, //281h
                  GlobalVariable.PowerpulsE2,
                  GlobalVariable.PowerpulsT1E1,
                  GlobalVariable.PowerpulsT2E1,
                  GlobalVariable.PowerpulsLBKorrE2,
                  GlobalVariable.PowerpulsUpSlope,
                  GlobalVariable.PowerpulsDownSlope,
                  GlobalVariable.JobSlope,
                  GlobalVariable*/












































        };
          return NEW_STRING;
    }
}