/**
 * @Autor Sakhr Aljendi
 * @Version 1.0
 * 011.01.2020
 */


package com.jess.gdfp.View;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.ListView;
import android.widget.TextView;

import com.jess.gdfp.Controller.DatenLoggerAdapter;
import com.jess.gdfp.DatenBank.Datenlogger;
import com.jess.gdfp.DatenBank.InfoContract;
import com.jess.gdfp.DatenBank.MyDataProvider;
import com.jess.gdfp.DatenObjekte;
import com.jess.gdfp.R;

import java.text.SimpleDateFormat;
import java.time.temporal.ValueRange;
import java.util.ArrayList;

public class DatalistView extends AppCompatActivity  {
    public static int timer=1;
    private static final int COUNT_LOADR=0;
    DatenLoggerAdapter datenLoggerAdapter;
    private TextView Time_view;
    private TextView Date_view;
    private ListView listView;
    static public String dateString ="1";

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
        datenLoggerAdapter=new DatenLoggerAdapter(DatalistView.this,initDatenlogger());
      finden(infosArray);

        datetime_thread();

    }

   void  finden (String[] str){
        for( int i=0;i<str.length;i++){
            for(int j=0;j<str[i].length();j++){
                if(str[i].charAt(j)== ' ' || str[i].charAt(j)== '-'){
                  System.out.println(str[i]);
                }
            }
        }

    }

    static public String[] infosArray={
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



    private  String[] VALUE_STRING = {
            DatenObjekte.Verfahren,
            DatenObjekte.Betriebsart,
            String.valueOf(DatenObjekte.SV1pos3), //pos 3 DrahtDurchmesser
            DatenObjekte.Gas,
            DatenObjekte.Werkstoff,
            DatenObjekte.Reglertyp,
            DatenObjekte.StatusMSR,
            DatenObjekte.StatusFLG,
            String.valueOf(DatenObjekte.Kennliniennummer),
            String.valueOf(DatenObjekte.Jobnummer),
            DatenObjekte.KennlinienTyp_String,
            String.valueOf(DatenObjekte.JobKommando),
            String.valueOf(DatenObjekte.JobStatus_String),
            String.valueOf(DatenObjekte.Verriegelungsstufe),
            String.valueOf(DatenObjekte.Gasvorströmen),
            String.valueOf(DatenObjekte.Gasnachströmen),
            String.valueOf(DatenObjekte.EinschleichenAbsolut),
            String.valueOf(DatenObjekte.EinschleichenKorrektur),
            String.valueOf(DatenObjekte.UpSlope),
            String.valueOf(DatenObjekte.DownSlope),
            String.valueOf(DatenObjekte.Zündenergie),
            String.valueOf(DatenObjekte.Endkraterenergie),
            String.valueOf(DatenObjekte.GebirgeStatus),
            String.valueOf(DatenObjekte.SchweißState),
            String.valueOf(DatenObjekte.Freibrand),
            String.valueOf(DatenObjekte.FreibandKorrektur),
            String.valueOf(DatenObjekte.KorrekturPulsamplitude),
            String.valueOf(DatenObjekte.KorrekturDrossel),
            String.valueOf(DatenObjekte.Einfädeln),
            String.valueOf(DatenObjekte.GastestZeit),
            String.valueOf(DatenObjekte.PausenZeit),
            String.valueOf(DatenObjekte.Punktzeit),
            String.valueOf(DatenObjekte.ZündDauer),
            String.valueOf(DatenObjekte.EndkraterDauer),
            String.valueOf(DatenObjekte.SynergieVorgabe),
            String.valueOf(DatenObjekte.AnzahlLeistungsmodule),
            DatenObjekte.PowerpulsEinAus_String,
            String.valueOf(DatenObjekte.PowerpulsE2),
            String.valueOf(DatenObjekte.PowerpulsT1E1),
            String.valueOf(DatenObjekte.PowerpulsT2E1),
            String.valueOf(DatenObjekte.PowerpulsLBKorrE2),
            String.valueOf(DatenObjekte.PowerpulsUpSlope),
            String.valueOf(DatenObjekte.PowerpulsDownSlope),
            String.valueOf(DatenObjekte.JobSlope),
            String.valueOf(DatenObjekte.ZündStrom),
            String.valueOf(DatenObjekte.ZündSpannung),
            String.valueOf(DatenObjekte.ZündEnergie),
            String.valueOf(DatenObjekte.ZündDrossel),
            String.valueOf(DatenObjekte.ZündLichtbogenkorrektur),
            String.valueOf(DatenObjekte.Strom1),
            String.valueOf(DatenObjekte.Spannung1),
            String.valueOf(DatenObjekte.Energie1),
            String.valueOf(DatenObjekte.Drossel1),
            String.valueOf(DatenObjekte.Lichtbogenkorrektur1),
            String.valueOf(DatenObjekte.Strom2),
            String.valueOf(DatenObjekte.Spannung2),
            String.valueOf(DatenObjekte.Energie2),
            String.valueOf(DatenObjekte.Drossel2),
            String.valueOf(DatenObjekte.Lichtbogenkorrektur2),
            String.valueOf(DatenObjekte.Strom3),
            String.valueOf(DatenObjekte.Spannung3),
            String.valueOf(DatenObjekte.Energie3),
            String.valueOf(DatenObjekte.Drossel3),
            String.valueOf(DatenObjekte.Lichtbogenkorrektur3),
            String.valueOf(DatenObjekte.EndkraterStrom),
            String.valueOf(DatenObjekte.EndkraterSpannung),
            String.valueOf(DatenObjekte.EndkraterEnergie),
            String.valueOf(DatenObjekte.EndkraterDrossel),
            String.valueOf(DatenObjekte.EndKraterLichtbogenkorrektur),
            String.valueOf(DatenObjekte.VorschubSetwert),
            String.valueOf(DatenObjekte.VorschubIstwert),
            String.valueOf(DatenObjekte.VorschubHoldwert),
            String.valueOf(DatenObjekte.VorschubStatus),
            String.valueOf(DatenObjekte.VorschubAusKennlinie),
            String.valueOf(DatenObjekte.StromSetwert),
            String.valueOf(DatenObjekte.StromIstwert),
            String.valueOf(DatenObjekte.StromHoldwert),
            String.valueOf(DatenObjekte.StromStatus),
            String.valueOf(DatenObjekte.StromInkremental),
            String.valueOf(DatenObjekte.SpannungSetwert),
            String.valueOf(DatenObjekte.SpannungIstwert),
            String.valueOf(DatenObjekte.SpannungHoldwert),
            String.valueOf(DatenObjekte.SpannungStatus),
            String.valueOf(DatenObjekte.SpannungInkremental),
            String.valueOf(DatenObjekte.BlechdickeSetwert),
            String.valueOf(DatenObjekte.BlechdickeIstwert),
            String.valueOf(DatenObjekte.BlechdickeHoldwert),
            String.valueOf(DatenObjekte.BlechdickeStatus),
            //DatenObjekte.Reset_String,
            String.valueOf(DatenObjekte.ElektrodeStromSetwert),
            String.valueOf(DatenObjekte.ElektrodeStromIstwert),
            String.valueOf(DatenObjekte.HotstartDauer),
            String.valueOf(DatenObjekte.Hotstart),
            String.valueOf(DatenObjekte.ArcForce),
            String.valueOf(DatenObjekte.InnenwiderstandfürElektrode),
            String.valueOf(DatenObjekte.RMTPosAmplitude),
            String.valueOf(DatenObjekte.RMTNegAmplitude),
            String.valueOf(DatenObjekte.StartAmplitude),
            String.valueOf(DatenObjekte.StartZeit),
            String.valueOf(DatenObjekte.StartÜberhöhung),
            String.valueOf(DatenObjekte.InnenwiderstandfürDossel),
            String.valueOf(DatenObjekte.Überblendzeit),
            String.valueOf(DatenObjekte.DrosselAbfall),
            String.valueOf(DatenObjekte.MotorFlanke),
            String.valueOf(DatenObjekte.DrosselDynamic),
            String.valueOf(DatenObjekte.MAGACPositiveZeit),
            String.valueOf(DatenObjekte.MAGACStromschwellwert),
            String.valueOf(DatenObjekte.LBRMode),
            DatenObjekte.MAGACBetriebsart_String,
            String.valueOf(DatenObjekte.MAGACKältewert),
            String.valueOf(DatenObjekte.MAGACNegativZeit),
            String.valueOf(DatenObjekte.MAGACKurzschlusserkennung),
            String.valueOf(DatenObjekte.MAGACKurzschlussaufhebung),
            String.valueOf(DatenObjekte.MACAGVerweilzeitPosNeg),
            String.valueOf(DatenObjekte.MACAGVerweilzeitNegPos),
            String.valueOf(DatenObjekte.WIGSpeedPulsFrequenz),
            String.valueOf(DatenObjekte.WIGSpeedPulsI1Anteil),
            String.valueOf(DatenObjekte.WIGSpeedPulsI3),
            String.valueOf(DatenObjekte.GasSollwert),
            String.valueOf(DatenObjekte.UserNummer),
            String.valueOf(DatenObjekte.WIGACStromoffset),
            DatenObjekte.WIGStatus_String,
            String.valueOf(DatenObjekte.WIGACFrequenz),
            String.valueOf(DatenObjekte.WIGACBalance),
            String.valueOf(DatenObjekte.WIGDurchmesserWolframElektrode),
            DatenObjekte.WIGBetriebsartWechselrichter_String,
            String.valueOf(DatenObjekte.KaltdrahtpulsenT1SV21_5),
            String.valueOf(DatenObjekte.WIGStromLimit),
            DatenObjekte.KHMode_String,
            String.valueOf(DatenObjekte.VerzögerungsZeitKaltdrahtEin),
            String.valueOf(DatenObjekte.VerzögerungsZeitKaltdrahtAus),
            String.valueOf(DatenObjekte.VerzögerungsZeitHeißdrahtÜberwachung),
            String.valueOf(DatenObjekte.Vorpositionierungsstrecke),
            String.valueOf(DatenObjekte.Rückzugsstrecke),
            String.valueOf(DatenObjekte.KaltdrahtpulsenT1SV22_7),
            DatenObjekte.KHStatus_String,
    };

    private void datetime_thread(){
        Thread t = new Thread() {
            int j =1;
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                              dateString = String.valueOf(j);
                                j++;
                                System.out.println(j);
                                initDatenlogger();
                                listView.setAdapter(datenLoggerAdapter);
                                Time_view.setText(String.valueOf(DatenObjekte.HOUR)+":"+String.valueOf(DatenObjekte.MINUTE)+":"+String.valueOf(DatenObjekte.SECOND));
                                Date_view.setText(String.valueOf(DatenObjekte.DAY)+"/"+String.valueOf(DatenObjekte.MONTH)+"/"+"20"+String.valueOf(DatenObjekte.YEAR));
                              //  datenLoggerAdapter.clear();
                                ArrayList Datenlogger = new ArrayList<>();
                                for(int i=0;i<infosArray.length;i++){
                                    Datenlogger.add(new Datenlogger(infosArray[i],VALUE_STRING[i]));
                                }
                                datenLoggerAdapter=new DatenLoggerAdapter(DatalistView.this,Datenlogger);
                                datenLoggerAdapter.notifyDataSetChanged();
                                ContentValues values = new ContentValues();
                                for (int i = 0; i < DatalistView.infosArray.length; i++) {
                                    values.put(DatalistView.infosArray[i], VALUE_STRING[i]);

                                }

                         getContentResolver().insert(InfoContract.infoEntry.CONTENT_URI, values);

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
            Datenlogger.add(new Datenlogger(infosArray[i],dateString));
        }

        return Datenlogger;
    }


}