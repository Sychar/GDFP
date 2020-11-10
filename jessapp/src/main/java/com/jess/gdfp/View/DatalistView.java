/**
 * @Autor Sakhr Aljendi
 * @Version 1.0
 * 011.01.2020
 */

package com.jess.gdfp.View;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.jess.gdfp.Controller.DatenLoggerAdapter;
import com.jess.gdfp.DatenBank.CSVWriter;
import com.jess.gdfp.DatenBank.Datenlogger;
import com.jess.gdfp.DatenBank.InfoContract;
import com.jess.gdfp.DatenBank.InfoDataBase;
import com.jess.gdfp.DatenObjekte;
import com.jess.gdfp.GlobalVariable;
import com.jess.gdfp.R;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class DatalistView extends AppCompatActivity {
    public static int timer = 1;
    private static final int COUNT_LOADR = 0;
    DatenLoggerAdapter datenLoggerAdapter;
    private TextView Time_view;
    private TextView Date_view;
    private ListView listView;
    static public String dateString = "1";
    private String[] Array_Data = new String[infosArray.length];
    private Uri uri;
    //private static String[] NEW_STRING ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datalist_view);

        listView = (ListView) findViewById(R.id.datalistview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        Time_view = findViewById(R.id.TIME_DL);
        Date_view = findViewById(R.id.DATE_DL);
        //datenLoggerAdapter=new DatenLoggerAdapter(this,initDatenlogger());
        //listView.setAdapter(datenLoggerAdapter);
        datenLoggerAdapter = new DatenLoggerAdapter(DatalistView.this, initDatenlogger());
        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             System.out.println(loadFile(view.getContext()));
            }
        });
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
            InfoContract.infoEntry.Einfädeln,
            InfoContract.infoEntry.GastestZeit,
            InfoContract.infoEntry.Pausenzeitbeiinterval,
            InfoContract.infoEntry.Punktzeit,
            InfoContract.infoEntry.ZündDauer,
            InfoContract.infoEntry.EndkraterDauer,
            InfoContract.infoEntry.SynergieVorgabeBasis,
            InfoContract.infoEntry.AnzahlLeistungsmodule,
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
            InfoContract.infoEntry.Drossel1,
            InfoContract.infoEntry.Lichtbogenkorrektur1,
            InfoContract.infoEntry.Strom2,
            InfoContract.infoEntry.Spannung2,
            InfoContract.infoEntry.Energie2,
            InfoContract.infoEntry.Drossel2,
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
            InfoContract.infoEntry.BlechdickeSetwert,
            InfoContract.infoEntry.BlechdickeIstwert,
            InfoContract.infoEntry.BlechdickeHoldwert,
            InfoContract.infoEntry.BlechdickeStatus,
            //InfoContract.infoEntry.Reset,
            InfoContract.infoEntry.ElektrodeStromSetwert,
            InfoContract.infoEntry.ElektrodeStromIstwert,
            InfoContract.infoEntry.HotstartDauer,
            InfoContract.infoEntry.Hotstart,
            InfoContract.infoEntry.ArcForce,
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

    private static String[] VALUE_STRING = {
            GlobalVariable.Verfahren,
            GlobalVariable.Betriebsart_String[GlobalVariable.SV1pos2],
            String.valueOf(GlobalVariable.Drahtdurchmesser), //pos 3 DrahtDurchmesser
            GlobalVariable.Gas,
            GlobalVariable.Werkstoff,
            GlobalVariable.Reglertyp,
            GlobalVariable.StatusMSR,
            GlobalVariable.StatusFLG,
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

    private void datetime_thread() {
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
                                Time_view.setText(String.valueOf(DatenObjekte.HOUR) + ":" + String.valueOf(DatenObjekte.MINUTE) + ":" + String.valueOf(DatenObjekte.SECOND));
                                Date_view.setText(String.valueOf(DatenObjekte.DAY) + "/" + String.valueOf(DatenObjekte.MONTH) + "/" + "20" + String.valueOf(DatenObjekte.YEAR));
                                //  datenLoggerAdapter.clear();
                                ArrayList Datenlogger = new ArrayList<>();
                                for (int i = 0; i < infosArray.length; i++) {
                                    Datenlogger.add(new Datenlogger(infosArray[i], DATALOGGER_PARAM[i]));
                                    exportDB();
                                }
                                ContentValues values = new ContentValues();
                                for (int i = 0; i < DATALOGGER_PARAM.length; i++) {
                                    values.put(infosArray[i], DATALOGGER_PARAM[i]);

                                }

                                getContentResolver().insert(InfoContract.infoEntry.CONTENT_URI, values);
                                datenLoggerAdapter = new DatenLoggerAdapter(DatalistView.this, Datenlogger);
                                if (DatenObjekte.HOUR < 10)
                                    Time_view.setText("0" + String.valueOf(DatenObjekte.HOUR) + ":" + String.valueOf(DatenObjekte.MINUTE) + ":" + String.valueOf(DatenObjekte.SECOND));
                                else if (DatenObjekte.MINUTE < 10)
                                    Time_view.setText(String.valueOf(DatenObjekte.HOUR) + ":0" + String.valueOf(DatenObjekte.MINUTE) + ":" + String.valueOf(DatenObjekte.SECOND));
                                else if (DatenObjekte.SECOND < 10)
                                    Time_view.setText(String.valueOf(DatenObjekte.HOUR) + ":" + String.valueOf(DatenObjekte.MINUTE) + ":0" + String.valueOf(DatenObjekte.SECOND));
                                else
                                    Time_view.setText(String.valueOf(DatenObjekte.HOUR) + ":" + String.valueOf(DatenObjekte.MINUTE) + ":" + String.valueOf(DatenObjekte.SECOND));

                                if (DatenObjekte.DAY < 10)
                                    Date_view.setText("0" + String.valueOf(DatenObjekte.DAY) + "/0" + String.valueOf(DatenObjekte.MONTH) + "/" + "20" + String.valueOf(DatenObjekte.YEAR));
                                else if (DatenObjekte.MONTH < 10)
                                    Date_view.setText(String.valueOf(DatenObjekte.DAY) + "/0" + String.valueOf(DatenObjekte.MONTH) + "/" + "20" + String.valueOf(DatenObjekte.YEAR));
                                else if (DatenObjekte.YEAR < 10)
                                    Date_view.setText(String.valueOf(DatenObjekte.DAY) + "/" + String.valueOf(DatenObjekte.MONTH) + "/" + "200" + String.valueOf(DatenObjekte.YEAR));
                                else
                                    Date_view.setText(String.valueOf(DatenObjekte.DAY) + "/" + String.valueOf(DatenObjekte.MONTH) + "/" + "20" + String.valueOf(DatenObjekte.YEAR));
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

    private ArrayList initDatenlogger() {
        ArrayList Datenlogger = new ArrayList<>();
        for (int i = 0; i < infosArray.length; i++) {
            Datenlogger.add(new Datenlogger(infosArray[i], VALUE_STRING[i]));
        }
        return Datenlogger;
    }

    private static String[] RefreshString() {
        String[] NEW_STRING = {
                GlobalVariable.Verfahren_String[GlobalVariable.SV1pos1], //Verfahren
                GlobalVariable.Betriebsart_String[GlobalVariable.SV1pos2], //Betriebsart
                String.valueOf(GlobalVariable.Drahtdurchmesser), //pos 3 DrahtDurchmesser
                GlobalVariable.Gas_String[GlobalVariable.SV1pos2], //Gas
                GlobalVariable.Werksotff_String[GlobalVariable.SV1pos5], //Werkstoff
                GlobalVariable.Reglertyp,
                GlobalVariable.StatusMSR,
                GlobalVariable.StatusFLG,
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
                GlobalVariable.KHStatus_String
        };
        return NEW_STRING;
    }


    private void exportDB() {
        InfoDataBase infoDataBase = new InfoDataBase(getApplicationContext());
        File exportDir = new File(Environment.getExternalStorageDirectory(), "");
        if (!exportDir.exists()) {
            exportDir.mkdir();
        }
        File file = new File(exportDir, "Datalger.csv");
        String data = "Daten, die in die Datei geschrieben werden sollen.";
        try {
            FileOutputStream fos = openFileOutput("DataLogger.txt", Context.MODE_PRIVATE);
            fos.write(data.getBytes());
            //String []arrayinternal =RefreshString();
            //for(int i =0;i<RefreshString().length;i++){
            //  fos.write(arrayinternal[i].getBytes());

            //}
            Log.i("this", "saved file on internal storage: " + "DataLoger.txt");
            fos.close();
            file.createNewFile();
            CSVWriter csvWriter = new CSVWriter(new FileWriter(file));

            // uri = Uri.parse("content://com.felhr.serialportexample.jobs/jobs/");
            uri = InfoContract.infoEntry.CONTENT_URI;
            Cursor cursor = getContentResolver().query(uri, DatalistView.infosArray, null, null, null);
            while (cursor.moveToNext()) {
                for (int i = 0; i < DatalistView.infosArray.length; i++) {
                    Array_Data[i] = cursor.getString(cursor.getColumnIndexOrThrow(DatalistView.infosArray[i]));
                    //FinalArray[i]=JobsDetails.jobdetails[i] + ":"+ Array_Data[i] ;
                    //System.out.println(Array_Data[i]);

                }
                // Array_Data[112]="-----------------------------------------------------------------------";

                csvWriter.writeNext(RefreshString());


            }


            csvWriter.close();
        } catch (Exception E) {
            E.printStackTrace();
        }
    }

    private static String convertStreamToString(InputStream stream) throws IOException {
        // To convert the InputStream to String we use the
        // Reader.read(char[] buffer) method. We iterate until the
        // Reader return -1 which means there's no more data to
        // read. We use the StringWriter class to produce the string.
        if (stream != null) {
            Writer writer = new StringWriter();

            char[] buffer = new char[1024];
            try {
                Reader reader = new BufferedReader(new InputStreamReader(stream,
                        StandardCharsets.UTF_8));
                int n;
                while ((n = reader.read(buffer)) != -1) {
                    writer.write(buffer, 0, n);
                }
            } finally {
                stream.close();
            }
            return writer.toString();
        }
        return "";
    }

    public static   String loadFile(Context context) {

        String filename = "DataLogger.txt";
        String data;

        FileInputStream fileInputStream;
        try {
            fileInputStream = context.openFileInput(filename);
            InputStream stream = new BufferedInputStream(fileInputStream);
            data = convertStreamToString(stream);
            fileInputStream.close();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }

return null;
    }
}