/**
 * @Autor Sakhr Aljendi
 * @Version 1.0
 * 09.02.2020
 */

package com.jess.gdfp.View;

import android.content.Intent;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.jess.gdfp.DatenBank.JobContract;
import com.jess.gdfp.DatenObjekteJob;
import com.jess.gdfp.R;

public class JobsDetails extends AppCompatActivity {
    private static final int ALL_Jobs = 100;
    private static final int ID_JOBS = 101;
    private static final UriMatcher sUriMathcer = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMathcer.addURI(JobContract.CONTEN_AUTHRITY, JobContract.PATH_JOBS, ALL_Jobs);
        sUriMathcer.addURI(JobContract.CONTEN_AUTHRITY, JobContract.PATH_JOBS + "/#", ID_JOBS);

    }

    public static String[] modiJobsdeateils = new String[112];
    public static String[] Mesdeateils = new String[112];
    private Uri uri;

    public static String[] jobdetails = {
            JobContract.jobEntry.HI_UI_JOB_NR,
            JobContract.jobEntry.HI_ELEKTOSTROM_SET,
            JobContract.jobEntry.HI_ST_STUF1SPANN,
            JobContract.jobEntry.HI_ST_STUF3SPANN,
            JobContract.jobEntry.HI_UI_ENDKRA_SPANN,
            JobContract.jobEntry.HI_UI_JOBCRC,
            JobContract.jobEntry.HI_UI_KRA_ENERGIE,
            JobContract.jobEntry.HI_UI_LIMIT_MIT_LBKORR,
            JobContract.jobEntry.HI_UI_NEGEZEIT,
            JobContract.jobEntry.HI_UI_PAUSE_ZEIT,
            JobContract.jobEntry.HI_UI_PORTOKOLLTYPE,
            JobContract.jobEntry.HI_UI_PUNKT_ZEIt,
            JobContract.jobEntry.HI_UI_START_AMPLITUDE,
            JobContract.jobEntry.HI_UI_STROM_SET,
            JobContract.jobEntry.HI_UI_STUF2STROM,
            JobContract.jobEntry.HI_UI_STUF3STROM,
            JobContract.jobEntry.HI_UI_STUFE1ENERGIE,
            JobContract.jobEntry.HI_UI_STUFE2ENERGIE,
            JobContract.jobEntry.HI_UI_STUFE3ENERGIE,
            JobContract.jobEntry.HI_UI_VOR_SCHUB_SET,
            JobContract.jobEntry.HI_UI_ZUEND_STROM,
            JobContract.jobEntry.HI_UIKENN_NR,
            JobContract.jobEntry.HI_UISTAFE1STROM,
            JobContract.jobEntry.HI_ULI_MIT_ENERGIE,
            JobContract.jobEntry.HI_ZUEND_ENERGIE,
            JobContract.jobEntry.HI_ZUEND_SPANN,
            JobContract.jobEntry.LO_ELKTOSTROM_SET,
            JobContract.jobEntry.LO_SI_STUFE1SPANN,
            JobContract.jobEntry.LO_SI_STUFE2SPANN,
            JobContract.jobEntry.LO_SI_STUFE3SPANN,
            JobContract.jobEntry.LO_UI_ENDKAR_STROM,
            JobContract.jobEntry.LO_UI_ENDKRA_SPANN,
            JobContract.jobEntry.LO_UI_JOB_NR,
            JobContract.jobEntry.lO_UI_JOBCRC,
            JobContract.jobEntry.LO_UI_KRA_ENERGIE,
            JobContract.jobEntry.LO_UI_LI_MIT_ENERGIE,
            JobContract.jobEntry.LO_UI_LIMIT_MIT_LBKORR,
            JobContract.jobEntry.LO_UI_NEGEZEIT,
            JobContract.jobEntry.LO_UI_PAUSE_ZEIT,
            JobContract.jobEntry.LO_UI_PROTOKOLLTYPE,
            JobContract.jobEntry.LO_UI_PUNKT_ZEIT,
            JobContract.jobEntry.LO_UI_START_AMPLITUDE,
            JobContract.jobEntry.LO_UI_STROM_SET,
            JobContract.jobEntry.LO_UI_STUFE1ENERGIE,
            JobContract.jobEntry.LO_UI_STUFE2ENERGIE,
            JobContract.jobEntry.LO_UI_STUFE2STROM,
            JobContract.jobEntry.LO_UI_STUFE3ENERGIE,
            JobContract.jobEntry.LO_UI_STUFE3STROM,
            JobContract.jobEntry.LO_UI_VOR_SCHUB_SET,
            JobContract.jobEntry.LO_UI_ZUEND_STROM,
            JobContract.jobEntry.LO_UIKENN_NR,
            JobContract.jobEntry.LO_UISTAFE1STROM,
            JobContract.jobEntry.lo_ZUEND_ENERGIE,
            JobContract.jobEntry.LO_ZUEND_SPANN,
            JobContract.jobEntry.SC_EINSCHPROZ,
            JobContract.jobEntry.SC_ENDKRA_LBKORR,
            JobContract.jobEntry.SC_PP_LBR_E2,
            JobContract.jobEntry.SC_STUFE1BKORR,
            JobContract.jobEntry.SC_STUFE2BKORR,
            JobContract.jobEntry.SC_STUFE3BKORR,
            JobContract.jobEntry.SC_ZUEND_BKORR,
            JobContract.jobEntry.UC_ARC_FORCE,
            JobContract.jobEntry.UC_BETRIBMAGAC,
            JobContract.jobEntry.UC_BETRIEB_ART,
            JobContract.jobEntry.UC_DOWN_SLOP,
            JobContract.jobEntry.UC_DRAH_DURCH,
            JobContract.jobEntry.UC_DROSS_DYNAMIC,
            JobContract.jobEntry.UC_DROSSEL_ABFALL,
            JobContract.jobEntry.UC_DROSSELRI,
            JobContract.jobEntry.UC_EBDKRATERORZ,
            JobContract.jobEntry.UC_EIN_FAEDELN,
            JobContract.jobEntry.UC_EINSCHLABS,
            JobContract.jobEntry.UC_ELEKTRODERI,
            JobContract.jobEntry.UC_END_KRA_DAUER,
            JobContract.jobEntry.UC_ENDKRA_DROSSEL,
            JobContract.jobEntry.UC_FREI_BRANDKORR,
            JobContract.jobEntry.UC_GAS_TYPE,
            JobContract.jobEntry.UC_GAS_VOR,
            JobContract.jobEntry.UC_GASNACH,
            JobContract.jobEntry.UC_HOT_START,
            JobContract.jobEntry.UC_HOT_START_DAUER,
            JobContract.jobEntry.UC_JOB_TYPE,
            JobContract.jobEntry.UC_KAELTEWERT,
            JobContract.jobEntry.UC_KENN_TYPE,
            JobContract.jobEntry.UC_KORR_DROSSEL,
            JobContract.jobEntry.UC_KORR_PULS_AMPL,
            JobContract.jobEntry.UC_LBR_MODE,
            JobContract.jobEntry.UC_MOTOR_FLANKE,
            JobContract.jobEntry.UC_POSZEIT,
            JobContract.jobEntry.UC_PP_EINERGIE2,
            JobContract.jobEntry.UC_PP_ON_OFF,
            JobContract.jobEntry.UC_PP_TIME1,
            JobContract.jobEntry.UC_PP_TIME2,
            JobContract.jobEntry.UC_REGLERTYPE,
            JobContract.jobEntry.UC_START_ZEIT,
            JobContract.jobEntry.UC_STROM_SCHWELLE,
            JobContract.jobEntry.UC_STUFE1DROESSEL,
            JobContract.jobEntry.UC_STUFE2DROESSEL,
            JobContract.jobEntry.UC_STUFE3DROESSEL,
            JobContract.jobEntry.UC_SYN_BASIS,
            JobContract.jobEntry.UC_UEBER_BlEND_ZEIT,
            JobContract.jobEntry.UC_UPSLOP,
            JobContract.jobEntry.UC_USER_NR,
            JobContract.jobEntry.UC_VERFAHREN,
            JobContract.jobEntry.UC_VERWZEITNP,
            JobContract.jobEntry.UC_VERWZEITPN,
            JobContract.jobEntry.UC_WERKSTOFF,
            JobContract.jobEntry.UC_ZEUNDEGPRZ,
            JobContract.jobEntry.UC_ZUEND_DROSSEL,
            JobContract.jobEntry.UC_ZUENDDAUER,
            JobContract.jobEntry.UCKS_AUFHEBUNG,
            JobContract.jobEntry.UCKS_ERKENNUNG,
            JobContract.jobEntry._ID};
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs_details);
        Intent getinet = getIntent();
        Bundle getdata = getinet.getExtras();
        String idOfJob = (String) getdata.get("jobs");
        ListView listView = (ListView) findViewById(R.id.jobdeateils);
        //System.out.println("was vrom view kommt" + idOfJob);
        //System.out.println(jobdetails.length);
        iint_datejob();
       
        uri = Uri.parse("content://com.felhr.serialportexample.jobs/jobs/" + idOfJob);
        System.out.println(uri);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.jobdeatailslist, R.id.jobdeateils, dataholen("1"));
        listView.setAdapter(arrayAdapter);
    }

    public void iint_datejob() {
        modiJobsdeateils[30] = "id";
        modiJobsdeateils[1] = String.valueOf(DatenObjekteJob.uiJobNr);
        modiJobsdeateils[2] = String.valueOf(DatenObjekteJob.uiStufe1Spann);
        modiJobsdeateils[3] = String.valueOf(DatenObjekteJob.uiStufe3Spann);
        modiJobsdeateils[4] = String.valueOf(DatenObjekteJob.uiEndKraSpann);
        modiJobsdeateils[5] = String.valueOf(DatenObjekteJob.uiElektrStromSet);
        modiJobsdeateils[6] = String.valueOf(DatenObjekteJob.uiJobCRC);
        modiJobsdeateils[7] = String.valueOf(DatenObjekteJob.uiEndKraEnergie);
        modiJobsdeateils[8] = String.valueOf(DatenObjekteJob.uiLimitEnergie);
        modiJobsdeateils[9] = String.valueOf(DatenObjekteJob.uiLimitLbKorr);
        modiJobsdeateils[10] = String.valueOf(DatenObjekteJob.uiNegZeit);
        modiJobsdeateils[11] = String.valueOf(DatenObjekteJob.uiNegZeit);
        // modiJobsdeateils[12]=String.valueOf(DatenObjekteJob.uiPausenZeit);
        modiJobsdeateils[12] = String.valueOf(DatenObjekteJob.uiProtokollTyp);
        modiJobsdeateils[13] = String.valueOf(DatenObjekteJob.uiPunktZeit);
        modiJobsdeateils[14] = String.valueOf(DatenObjekteJob.uiStartAmplitude);
        modiJobsdeateils[15] = String.valueOf(DatenObjekteJob.uiStromSet);
        modiJobsdeateils[16] = String.valueOf(DatenObjekteJob.uiStufe1Energie);
        modiJobsdeateils[17] = String.valueOf(DatenObjekteJob.uiStufe2Energie);
        modiJobsdeateils[18] = String.valueOf(DatenObjekteJob.uiStufe3Energie);
        modiJobsdeateils[19] = String.valueOf(DatenObjekteJob.uiVorschubSet);
        modiJobsdeateils[20] = String.valueOf(DatenObjekteJob.uiZuendStrom);
        modiJobsdeateils[21] = String.valueOf(DatenObjekteJob.uiKennNr);
        modiJobsdeateils[22] = String.valueOf(DatenObjekteJob.uiElektrStromSet);
        modiJobsdeateils[23] = String.valueOf(DatenObjekteJob.uiZuendEnergie);
        modiJobsdeateils[24] = String.valueOf(DatenObjekteJob.uiZuendSpann);
        modiJobsdeateils[25] = String.valueOf(DatenObjekteJob.uiElektrStromSet);
        modiJobsdeateils[26] = String.valueOf(DatenObjekteJob.uiStufe1Spann);
        modiJobsdeateils[27] = String.valueOf(DatenObjekteJob.uiStufe2Spann);
        modiJobsdeateils[28] = String.valueOf(DatenObjekteJob.uiStufe3Spann);
        modiJobsdeateils[29] = String.valueOf(DatenObjekteJob.uiEndKraStrom);

        for (int i = 31; i < 112; i++) {
            modiJobsdeateils[i] = "test";
        }


    }

    /**
     *
     * @param IdofJob
     * @return
     *
     * hier wurde alle die daten nach der id aufgerufen und return Array String
     *
     */
    private String[] dataholen( String IdofJob) {
        uri = Uri.parse("content://com.felhr.serialportexample.jobs/jobs/" + IdofJob);
        System.out.println(uri);
        Cursor cursor = getContentResolver().query(uri, jobdetails, null, null, null);
        while (cursor.moveToNext()) {
            modiJobsdeateils[0] = cursor.getString(cursor.getColumnIndexOrThrow(JobsDetails.jobdetails[112]));
            Mesdeateils[0] = jobdetails[112] + "  :  " + modiJobsdeateils[0];
            for (int i = 1; i < 112; i++) {
                modiJobsdeateils[i] = cursor.getString(cursor.getColumnIndexOrThrow(JobsDetails.jobdetails[i - 1]));
            }
            for (int i = 1; i < jobdetails.length - 1; i++) {
                Mesdeateils[i] = jobdetails[i - 1] + "  :  " + modiJobsdeateils[i];
            }
        }
        return Mesdeateils;
    }

    public void close(View view) {
        JobsDetails.this.finish();
    }
}
