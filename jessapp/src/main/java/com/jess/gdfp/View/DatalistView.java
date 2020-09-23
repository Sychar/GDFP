/**
 * @Autor Sakhr Aljendi
 * @Version 1.0
 * 011.01.2020
 */


package com.jess.gdfp.View;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ListView;

import com.jess.gdfp.Controller.DatenLoggerAdapter;
import com.jess.gdfp.DatenBank.DataCursorAdapter;
import com.jess.gdfp.DatenBank.Datenlogger;
import com.jess.gdfp.DatenBank.InfoContract;
import com.jess.gdfp.IO.Exit;
import com.jess.gdfp.R;

import java.util.ArrayList;

public class DatalistView extends AppCompatActivity  {
    public static int timer=1;
    private static final int COUNT_LOADR=0;
    DatenLoggerAdapter datenLoggerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datalist_view);


        ListView listView =(ListView) findViewById(R.id.datalistview);

        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(this);

        datenLoggerAdapter=new DatenLoggerAdapter(this,initDatenlogger());
        listView.setAdapter(datenLoggerAdapter);


    }



    private  String[] infosArray={
                   // InfoContract.infoEntry.COLUMN_TIME_ ,
                    InfoContract.infoEntry.AnzahlLeistungsmodule ,
                    InfoContract.infoEntry.ArcForce ,
                    InfoContract.infoEntry.Betriebsart,
                    InfoContract.infoEntry.BlechdickeHoldwert,
                    InfoContract.infoEntry.BlechdickeIstwert,
                    InfoContract.infoEntry.BlechdickeSetwert ,
                    InfoContract.infoEntry.BlechdickeStatus,
                    InfoContract.infoEntry.Drahtdurchmesser,
                    InfoContract.infoEntry.Drossel1 ,
                    InfoContract.infoEntry.Drossel2 ,
                    InfoContract.infoEntry.Drossel3,
                    InfoContract.infoEntry.DrosselAbfall,
                    InfoContract.infoEntry.DrosselDynamic,
                    InfoContract.infoEntry.Einfädeln ,
                    InfoContract.infoEntry.Einschleichenabsolut,
                    InfoContract.infoEntry.EinschleichenKorrektur,
                    InfoContract.infoEntry.ElektrodeStromIstwert,
                    InfoContract.infoEntry.ElektrodeStromSetwert,
                    InfoContract.infoEntry.EndkraterDauer,
                    InfoContract.infoEntry.EndkraterDrossel,
                    InfoContract.infoEntry.EndkraterEnergie,
                    InfoContract.infoEntry.Endkraterenergie,
                    InfoContract.infoEntry.EndkraterLichtbogenkorrektur,
                    InfoContract.infoEntry.EndkraterSpannung,
                    InfoContract.infoEntry.Endkraterstrom,
                    InfoContract.infoEntry.Energie1,
                    InfoContract.infoEntry.Energie2,
                    InfoContract.infoEntry.Energie3,
                    InfoContract.infoEntry.Freibrand,
                    InfoContract.infoEntry.FreibrandKorrektur,
                    InfoContract.infoEntry.Gas,
                    InfoContract.infoEntry.Gasnachströmen,
                    InfoContract.infoEntry.GasSollwert,
                    InfoContract.infoEntry.GastestZeit,
                    InfoContract.infoEntry.Gasvorströmen,
                    InfoContract.infoEntry.GebirgeStatus,
                    InfoContract.infoEntry.Hotstart,
                    InfoContract.infoEntry.HotstartDauer,
                    InfoContract.infoEntry.InnenwiderstandfürDossel,
                    InfoContract.infoEntry.InnenwiderstandfürElektrode,
                    InfoContract.infoEntry.JobKommando,
                    InfoContract.infoEntry.Jobnummer,
                    InfoContract.infoEntry.JobSlope,
                    InfoContract.infoEntry.JobStatus,
                    InfoContract.infoEntry.KaltdrahtpulsenT1,
                    InfoContract.infoEntry.kennliniennummer,
                    InfoContract.infoEntry.kennlinienTyp,
                    InfoContract.infoEntry.KH_Status,
                    InfoContract.infoEntry.KHMode,
                    InfoContract.infoEntry.KorrekturDrossel,
                    InfoContract.infoEntry.KorrekturPulsamplitude,
                    InfoContract.infoEntry.LBRMode,
                    InfoContract.infoEntry.Lichtbogenkorrektur1,
                    InfoContract.infoEntry.Lichtbogenkorrektur2,
                    InfoContract.infoEntry.Lichtbogenkorrektur3,
                    InfoContract.infoEntry.MAGACBetriebsart,
                    InfoContract.infoEntry.MAGACKurzschlussaufhebung,
                    InfoContract.infoEntry.MAGACKurzschlusserkennung,
                    InfoContract.infoEntry.MAGACKältewert,
                    InfoContract.infoEntry.MAGACNegativZeit,
                    InfoContract.infoEntry.MAGACPositiveZeit,
                    InfoContract.infoEntry.MAGACStromschwellwert,
                    InfoContract.infoEntry.MAGAGVerweilzeitNegPos,
                    InfoContract.infoEntry.MAGAGVerweilzeitPosNeg,
                    InfoContract.infoEntry.MotorFlanke,
                    InfoContract.infoEntry.Pausenzeitbeiinterval,
                    InfoContract.infoEntry.PowerpilsEnergie,
                    InfoContract.infoEntry.PowerPulsDownSlope,
                    InfoContract.infoEntry.PowerpulsEinAus,
                    InfoContract.infoEntry.PowerPulsLBKorrEnergie2,
                    InfoContract.infoEntry.PowerPulsTime1fürEnergie1,
                    InfoContract.infoEntry.PowerPulsTime2fürEnergie1,
                    InfoContract.infoEntry.PowerPulsUpSlope,
                    InfoContract.infoEntry.Punktzeit,
                    InfoContract.infoEntry.Reglertyp,
                    InfoContract.infoEntry.Reset,
                    InfoContract.infoEntry.RMTNegAmplitude,
                    InfoContract.infoEntry.RMTPosAmplitude,
                    InfoContract.infoEntry.Rückzugsstrecke,
                    InfoContract.infoEntry.SchweißState,
                    InfoContract.infoEntry.Spannung1,
                    InfoContract.infoEntry.Spannung2,
                    InfoContract.infoEntry.Spannung3,
                    InfoContract.infoEntry.SpannungHoldwert,
                    InfoContract.infoEntry.SpannungInkremental,
                    InfoContract.infoEntry.SpannungIstwert,
                    InfoContract.infoEntry.SpannungSetwert,
                    InfoContract.infoEntry.SpannungStatus,
                    InfoContract.infoEntry.StartAmplitude,
                    InfoContract.infoEntry.Startueberhoehung,
                    InfoContract.infoEntry.StartZeit,
                    InfoContract.infoEntry.StatusFLG,
                    InfoContract.infoEntry.StatusMSR,
                    InfoContract.infoEntry.Strom2,
                    InfoContract.infoEntry.Strom1,
                    InfoContract.infoEntry.Strom3,
                    InfoContract.infoEntry.StromHoldwert,
                    InfoContract.infoEntry.StromInkremental,
                    InfoContract.infoEntry.Stromistwert,
                    InfoContract.infoEntry.StromSetwert,
                    InfoContract.infoEntry.StromStatus,
                    InfoContract.infoEntry.SynergieVorgabeBasis,
                    InfoContract.infoEntry.UPSlope,
                    InfoContract.infoEntry.Verfahren,
                    InfoContract.infoEntry.Verriegelungsstufe,
                    InfoContract.infoEntry.VerzögerungsZeitHeißdrahteuberwachung,
                    InfoContract.infoEntry.VerzögerungsZeitKaltdrahtAus,
                    InfoContract.infoEntry.VerzögerungsZeitKaltdrahtEin,
                    InfoContract.infoEntry.Vorpositionierungsstrecke,
                    InfoContract.infoEntry.UserNummer,
                    InfoContract.infoEntry.VorschubausKennlinie,
                    InfoContract.infoEntry.Vorschubistwert,
                    InfoContract.infoEntry.VorschubSetwert,
                    InfoContract.infoEntry.VorschubStatus,
                    InfoContract.infoEntry.Werkstoff,
                    InfoContract.infoEntry.WIGACBalance,
                    InfoContract.infoEntry.WIGACFrequenz,
                    InfoContract.infoEntry.WIGACStromoffset,
                    InfoContract.infoEntry.WIGBetriebsartWechselrichtwer,
                    InfoContract.infoEntry.WIGDurchmesserWolframElektrode,
                    InfoContract.infoEntry.WIGSpeedPulsFrequenz,
                    InfoContract.infoEntry.WIGSpeedPulsI1Anteil,
                    InfoContract.infoEntry.WIGSpeedPulsI3,
                    InfoContract.infoEntry.WIGstatus,
                    InfoContract.infoEntry.WIGStromLimit,
                    InfoContract.infoEntry.ZündDauer,
                    InfoContract.infoEntry.ZündDrossel,
                    InfoContract.infoEntry.ZündEnergie,
                    InfoContract.infoEntry.ZündLichtbogenkorrektur,
                    InfoContract.infoEntry.Zündenergiein,
                    InfoContract.infoEntry.ZündSpannung,
                    InfoContract.infoEntry.ZündStrom,
                    InfoContract.infoEntry.Überblendzeit,
    };

    private ArrayList initDatenlogger(){
        ArrayList Datenlogger =new ArrayList<>();
        for (int i=0;i<infosArray.length;i++){
            Datenlogger.add(new Datenlogger(infosArray[i],"-"));
        }
        return Datenlogger;
    }
}
