
/**
 * *@Autor Sakhr Aljendi
 * @Version 1.0
 * 20.01.2020
 * */
package com.jess.gdfp.DatenBank;

import android.net.Uri;
import android.provider.BaseColumns;

public class JobContract {
    private JobContract(){}

    public static final String CONTEN_AUTHRITY = "com.felhr.serialportexample.jobs";
    public static final Uri BASE_CONTENT_URI =Uri.parse("content://"+CONTEN_AUTHRITY);
    public static final String PATH_JOBS ="jobs";

    public static final class jobEntry implements BaseColumns{
        public static final  String TABEL_NAME = "jobs";
        public static final  String _ID = BaseColumns._ID;
        public static final  Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_JOBS);
      //  public static final  Uri CONTENT_ID_URI=Uri.withAppendedPath(CONTENT_URI,_ID);
        public static final  String LO_UI_JOB_NR ="Lo_job_nr";
        public static final  String HI_UI_JOB_NR ="Hi_job_nr";
        public static final  String UC_JOB_TYPE="UC_job_type";
        public static final  String UC_USER_NR="UC_user_nr";
        public static final  String LO_UIKENN_NR="LO_ui_kenn_nr";
        public static final  String HI_UIKENN_NR="HI_ui_kenn_nr";
        public static final  String UC_KENN_TYPE="UC_kenn_type";
        public static final  String  UC_VERFAHREN="UC_verfahren";
        public static final  String UC_BETRIEB_ART="UC_betreib_art";
        public static final  String UC_DRAH_DURCH="UC_drahdurch";
        public static final  String UC_GAS_TYPE="UC_gas_type";
        public static final  String UC_WERKSTOFF="UC_werkstoff";
        public static final  String UC_REGLERTYPE ="UC_regelertype";
        public static final  String UC_GAS_VOR="UC_gas_vor";
        public static final  String UC_GASNACH ="UC_gas_nach";
        public static final  String UC_EINSCHLABS="UC_einschlabs";
        public static final  String SC_EINSCHPROZ="SC_einschlproz";
        public static final  String UC_UPSLOP="UC_upslope";
        public static final  String UC_DOWN_SLOP="UC_downslop";
        public static final  String UC_ZEUNDEGPRZ="UC_zuendegprz";
        public static final  String UC_EBDKRATERORZ="UC_endkraterprz";
        public static final  String UC_FREI_BRANDKORR="UC_frei_brandkorr";
        public static final  String UC_SYN_BASIS="UC_syn_basis";
        public static final  String UC_KORR_PULS_AMPL="UC_korr_puls_ampl";
        public static final  String UC_KORR_DROSSEL="UC_korr_drossel";
        public static final  String LO_UI_PAUSE_ZEIT="LO_ui_pause_zeit";
        public static final  String HI_UI_PAUSE_ZEIT="Hi_ui_pause_zeit";
        public static final  String LO_UI_PUNKT_ZEIT="Lo_ui_punkt_zeit";
        public static final  String HI_UI_PUNKT_ZEIt="HI_ui_punkt_zeit";
        public static final  String UC_EIN_FAEDELN="UC_ein_faedeln";
        public static final  String UC_ZUENDDAUER ="UC_zuenddauer";
        public static final  String UC_END_KRA_DAUER="UC_endkradauer";
        public static final  String UC_PP_ON_OFF="UC_PP_On_Of";
        public static final  String UC_PP_EINERGIE2="UC_pp_energie2";
        public static final  String UC_PP_TIME1="UC_pp_time1";
        public static final  String UC_PP_TIME2="UC_pp_time2";
        public static final  String SC_PP_LBR_E2="SC_pp_lbre2";
        public static final  String LO_UI_ZUEND_STROM="LO_ui_zuendstrom";
        public static final  String HI_UI_ZUEND_STROM ="HI_ui_zeund_strom";
        public static final  String LO_ZUEND_SPANN="LO_zund_spann";
        public static final  String HI_ZUEND_SPANN="HI_zund_spann";
        public static final  String lo_ZUEND_ENERGIE="LO_zund_energie";
        public static final  String HI_ZUEND_ENERGIE="HI_zund_energier";
        public static final  String UC_ZUEND_DROSSEL ="UC_zuend_drossel";
        public static final  String SC_ZUEND_BKORR="Sc_zuend_korr";
        public static final  String LO_UISTAFE1STROM ="LO_uistufel_strom";
        public static final  String HI_UISTAFE1STROM ="HI_ui_stufel_strom";
        public static final  String LO_SI_STUFE1SPANN="LO_si_stufe1spann";
        public static final  String HI_ST_STUF1SPANN="HI_st_stuf1spann";
        public static final  String LO_UI_STUFE1ENERGIE ="LO_ui_stufe1energei";
        public static final  String HI_UI_STUFE1ENERGIE="Hi_ui_stufe1energie";
        public static final  String UC_STUFE1DROESSEL="UC_stufe1drossel";
        public static final  String SC_STUFE1BKORR="SC_stufe1_bkorr";
        public static final  String LO_UI_STUFE2STROM="LO_ui_stufe2strom";
        public static final  String HI_UI_STUF2STROM="HI_ui_stufe2strom";
        public static final  String LO_SI_STUFE2SPANN="LO_si_stufe2spann";
        public static final  String HI_ST_STUF2SPANN="HI_st_stufe2spann";
        public static final  String LO_UI_STUFE2ENERGIE ="LO_ui_stufe2energie";
        public static final  String HI_UI_STUFE2ENERGIE="HI_ui_stufe2energie";
        public static final  String UC_STUFE2DROESSEL="UC_stufe2drossel";
        public static final  String SC_STUFE2BKORR="SC_stufe2bkorr";
        public static final  String LO_UI_STUFE3STROM="LO_ui_stufe3strom";
        public static final  String HI_UI_STUF3STROM="HI_ui_stufe3strom";
        public static final  String LO_SI_STUFE3SPANN="LO_si_stufe3spann";
        public static final  String HI_ST_STUF3SPANN="HI_si_stufe3spann";
        public static final  String LO_UI_STUFE3ENERGIE ="LO_ui_stufe3energie";
        public static final  String HI_UI_STUFE3ENERGIE="HI_ui_stufe3energie";
        public static final  String UC_STUFE3DROESSEL="UC_stufe3drossel";
        public static final  String SC_STUFE3BKORR="SC_stufe3bkorr";
        public static final  String LO_UI_ENDKAR_STROM="LO_ui_end_kar_strom";
        public static final  String HI_UI_ENDKRA_STROM="HI_ui_end_kar_strom";
        public static final  String LO_UI_ENDKRA_SPANN="Lo_ui_end_kar_spann";
        public static final  String HI_UI_ENDKRA_SPANN="HI_ui_end_kar_spann";
        public static final  String LO_UI_KRA_ENERGIE="Lo_ui_kar_energie";
        public static final  String HI_UI_KRA_ENERGIE="HI_ui_kar_energie";
        public static final  String UC_ENDKRA_DROSSEL="UC_end_kar_drossel";
        public static final  String SC_ENDKRA_LBKORR="SC_endkar_lbkorr";
        public static final  String LO_UI_VOR_SCHUB_SET="Lo_ui_vor_schub_set";
        public static final  String HI_UI_VOR_SCHUB_SET="HI_ui_vor_schub_set";
        public static final  String LO_UI_STROM_SET="LO_ui_strom_set";
        public static final  String HI_UI_STROM_SET="HI_ui_strom_set";
        public static final  String LO_ELKTOSTROM_SET="LO_elekt_strom";
        public static final  String HI_ELEKTOSTROM_SET="HI_elekt_strom";
        public static final  String UC_HOT_START_DAUER="UC_hot_start_dauer";
        public static final  String UC_HOT_START="UC_hot_start";
        public static final  String UC_ARC_FORCE="UC_arc_force";
        public static final  String UC_ELEKTRODERI="UC_elek_troderi";
        public static final  String LO_UI_LI_MIT_ENERGIE="LO_ui_li_mit_energie";
        public static final  String HI_ULI_MIT_ENERGIE="HI_uli_mit_energie";
        public static final  String LO_UI_LIMIT_MIT_LBKORR="LO_ui_limit_mit_lbkorr";
        public static final  String HI_UI_LIMIT_MIT_LBKORR="Lo_ui_limit_lbkorr";
        public static final  String LO_UI_START_AMPLITUDE="LO_ui_start_amplitude";
        public static final  String HI_UI_START_AMPLITUDE="HI_ui_start_amplitude";
        public static final  String UC_START_ZEIT="UC_start_zeit";
        public static final  String UC_DROSSELRI="UC_drosselri";
        public static final  String UC_UEBER_BlEND_ZEIT="UC_ueber_blend_zeit";
        public static final  String UC_DROSSEL_ABFALL="UC_drossel_abfall";
        public static final  String UC_MOTOR_FLANKE="UC_motor_flanke";
        public static final  String UC_DROSS_DYNAMIC="UC_drossel_dynamic";
        public static final  String UC_LBR_MODE="UC_lbr_mode";
        public static final  String UC_BETRIBMAGAC="UC_betrieb_mang";
        public static final  String UC_POSZEIT="UC_pos_zeit";
        public static final  String LO_UI_NEGEZEIT="LO_ui_negezeit";
        public static final  String HI_UI_NEGEZEIT="HI_ui_negezeit";
        public static final  String  UCKS_ERKENNUNG="UCKS_be_kennung";
        public static final  String UCKS_AUFHEBUNG="UCKS_aufhebung";
        public static final  String UC_VERWZEITPN="UC_verwzeitpn";
        public static final  String UC_VERWZEITNP="UC_verwzeitnp";
        public static final  String UC_KAELTEWERT="UC_kaelte_wert";
        public static final  String UC_STROM_SCHWELLE="UC_strom_schwelle";
        public static final  String LO_UI_PROTOKOLLTYPE="LO_ui_protokoll_type";
        public static final  String HI_UI_PORTOKOLLTYPE="Hi_ui_portokoll_type";
        public static final  String ST_JOB_BEZZ1="ST_job_bezz1";
        public static final  String lO_UI_JOBCRC="LO_ui_jobcrc";
        public static final  String HI_UI_JOBCRC="HI_ui_crc";

    }
}
