package com.jess.gdfp.View;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.jess.gdfp.GlobalVariable;
import com.jess.gdfp.R;
import com.webianks.library.scroll_choice.ScrollChoice;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BlankFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static List detail;
    protected Handler handler;
    static int zähler =1;
    public static TextView tv;
    public static ScrollChoice sc;
    public static Button next;
    public static Button previous;
    private  TextView textView;
    private Intent intent;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;

    public BlankFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragment newInstance(String param1, String param2) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_kennline, container, false);

        next = view.findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNext_previous(v);
            }
        });

        previous = view.findViewById(R.id.previous);
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNext_previous(v);
            }
        });

        sc = view.findViewById(R.id.scroll_choice);
        tv = view.findViewById(R.id.listview_title);

       /* sc.setOnItemSelectedListener(new ScrollChoice.OnItemSelectedListener() {
            @Override
            public void onItemSelected(ScrollChoice scrollChoice, int position, String name) {
                Log.i("Position",String.valueOf(position));
                *//***************** JJ Code *************************//*
                if(tv.getText().equals("DRAHTDURCHMESSER")){
                    GlobalVariable.DRAHTDURCHMESSER_MODE = position + 1;
                    GlobalVariable.Drahtdurchmesser_Token = true;
                }else if(tv.getText().equals("VERFAHREN")){
                    //---------------------------------- Verfahren ---------------------------------
                    GlobalVariable.VERFAHREN_MODE = position + 1;
                    GlobalVariable.Verfahren_Token = true;
                }else if(tv.getText().equals("GAS")){
                    //-------------------------------- GAS -----------------------------------------
                    GlobalVariable.GAS_MODE = position;
                    GlobalVariable.Gas_Token = true;
                }else if(tv.getText().equals("WERKSTOFF")){
                    //-------------------------- Werkstoff -----------------------------------------
                    GlobalVariable.WERKSTOFF_MODE = position + 1;
                    GlobalVariable.Werkstoff_Token = true;
                }else if(tv.getText().equals("BETRIEBSART")){
                    //---------------------------- Betriebsart -----------------------------------------
                    GlobalVariable.BETRIEBSART_MODE = position + 1;
                    GlobalVariable.Betriebsart_Token = true;
                }else if(tv.getText().equals("MENU")){
                    //---------------------------- Menu -----------------------------------------
                    GlobalVariable.MENU_MODE = position;
                    GlobalVariable.Menu_Token = true;
                }else if(tv.getText().equals("SETTING")){
                    //---------------------------- setting -----------------------------------------
                    GlobalVariable.SETTING_MODE = position + 1;
                    GlobalVariable.Setting_Token = true;
                } else if(tv.getText().equals("JOB")){
                    //---------------------------- job -----------------------------------------
                    GlobalVariable.JOB_MODE = position;
                    if (GlobalVariable.JOB_MODE ==0) GlobalVariable.Load_Job = true;
                    if (GlobalVariable.JOB_MODE ==1)  GlobalVariable.Save_Job = true;
                    GlobalVariable.Job_Token = true;
                }
            }
        });*/
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

   @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

     public static List init_verfahren(){
       List detail = new ArrayList();
         detail.add(("MIG/MAG NORMAL"));
         detail.add(("MIG/MAG SYNERGIE"));
         detail.add(("MIG/MAG PULS"));
         detail.add(("ELEKTRODE"));
         return detail;
     }

     public static List init_werkstoff(){
         List detail2 = new ArrayList<>();
         detail2.add(("Fe"));
         detail2.add(("Cr/Ni"));
         detail2.add(("AL/Mg"));
         detail2.add(("AL/Si"));
         detail2.add(("Cu/Si"));
         detail2.add(("Al/mg3"));
         detail2.add(("Al/mg5"));
         detail2.add(("Al/mg4/5Mn"));
         detail2.add(("Al/Bz"));
         detail2.add(("Spezial"));
         return detail2;
     }

     public static List init_durchmesser(){
         List  detail3 = new ArrayList<>();
         detail3.add(("0.6 mm"));
         detail3.add(("0.8 mm"));
         detail3.add(("0.9 mm"));
         detail3.add(("1.0 mm"));
         detail3.add(("1.2 mm"));
         detail3.add(("1.4 mm"));
         detail3.add(("1.6 mm"));
         detail3.add(("2.0 mm"));
         detail3.add(("2.4 mm"));
         detail3.add(("Spezial"));
         return detail3;
     }

    public static List init_Betriebsart(){
        List detail3 = new ArrayList<>();
        detail3.add(("2-TAKT"));
        detail3.add(("4-TAKT"));
        detail3.add(("4-TAKT SONDER"));
        //detail3.add(("PROGRAMM"));
        detail3.add(("PUNKTEN"));
        //detail3.add(("INTERVALL"));
        //detail3.add(("EXTERN"));
        //detail3.add(("2-TAKT+HF"));
        //detail3.add(("4-TAKT+HF"));
        return detail3;
    }

     public static List init_Gas(){
         List detail4 = new ArrayList<>();
         detail4.add(("82%AR / 18%CO"));
         detail4.add(("98%AR / 2%CO"));
         detail4.add(("100%AR"));
         detail4.add(("100%CO"));
         detail4.add(("92%AR / 8%CO"));
         detail4.add(("99%AR / 1%O2"));
         detail4.add(("98%AR / 2%O2"));
         detail4.add(("97%AR / 3%O2"));
         detail4.add(("92%AR / 8%O2"));
         detail4.add(("90%AR / 5%O2/ 5%CO"));
         detail4.add(("100%HE"));
         detail4.add(("80%AR / 20%HE"));
         detail4.add(("69%AR/ 30%HE/1%O2"));
         detail4.add(("50Ar / 50%HE"));
         detail4.add(("98Ar / 2%H2"));
         detail4.add(("94Ar / 6%H2"));
         detail4.add(("50Ar / 50%H2"));
         detail4.add(("30Ar / 70%H2"));
         detail4.add(("Spezial"));
         return detail4;
     }

    public static List init_Menu(){
        List detail3 = new ArrayList<>();
        detail3.add(("JOBS"));
        detail3.add(("DATENLOGGER"));
        detail3.add(("KENNLINIE"));
        return detail3;
    }

    public static List init_Setting(){
        List detail3 = new ArrayList<>();
        detail3.add(("DATE TIME"));
        detail3.add(("DISPLAY"));
        detail3.add(("LANGUAGE"));
        detail3.add(("UPDATE"));
        return detail3;
    }

    public static List init_Job(){
        List detail3 = new ArrayList<>();
        detail3.add(("LOAD"));
        detail3.add(("SAVE"));
        return detail3;
    }

     public void onNext_previous(View  view){

        if( view.getId() == R.id.next) {
            if ((tv.getText().equals("VERFAHREN"))) {
                GlobalVariable.Methode_Verfahren = false;
                GlobalVariable.Methode_Werkstoff = false;
                GlobalVariable.Methode_Drahtdurchmesser = false;
                GlobalVariable.Methode_Betriebsart = true;
                GlobalVariable.Methode_Gas = false;
                tv.setText("BETRIEBSART");
                detail = init_Betriebsart();
            }else if ((tv.getText().equals("BETRIEBSART"))){
                GlobalVariable.Methode_Verfahren = false;
                GlobalVariable.Methode_Werkstoff = true;
                GlobalVariable.Methode_Drahtdurchmesser = false;
                GlobalVariable.Methode_Betriebsart = false;
                GlobalVariable.Methode_Gas = false;
                tv.setText("WERKSTOFF");
                detail = init_werkstoff();
            } else if ((tv.getText().equals("WERKSTOFF"))){
                GlobalVariable.Methode_Verfahren = false;
                GlobalVariable.Methode_Werkstoff = false;
                GlobalVariable.Methode_Drahtdurchmesser = true;
                GlobalVariable.Methode_Betriebsart = false;
                GlobalVariable.Methode_Gas = false;
                tv.setText("DRAHTDURCHMESSER");
                detail = init_durchmesser();
            }else if ((tv.getText().equals("DRAHTDURCHMESSER"))){
                GlobalVariable.Methode_Verfahren = false;
                GlobalVariable.Methode_Werkstoff = false;
                GlobalVariable.Methode_Drahtdurchmesser = false;
                GlobalVariable.Methode_Betriebsart = false;
                GlobalVariable.Methode_Gas = true;
                tv.setText("GAS");
                detail = init_Gas();
            }else if ((tv.getText().equals("GAS"))){
                GlobalVariable.Methode_Werkstoff = false;
                GlobalVariable.Methode_Drahtdurchmesser = false;
                GlobalVariable.Methode_Betriebsart = false;
                GlobalVariable.Methode_Gas = false;
                GlobalVariable.Methode_Verfahren = true;
                tv.setText("VERFAHREN");
                detail = init_verfahren();
            }
            //CreateAdapter();
            }

            if(view.getId()==R.id.previous) {
                if ((tv.getText().equals("VERFAHREN"))) {
                    GlobalVariable.Methode_Verfahren = false;
                    GlobalVariable.Methode_Werkstoff = false;
                    GlobalVariable.Methode_Drahtdurchmesser = false;
                    GlobalVariable.Methode_Betriebsart = false;
                    GlobalVariable.Methode_Gas = true;
                    tv.setText("GAS");
                    detail = init_Gas();
                }else if ((tv.getText().equals("GAS"))){
                    GlobalVariable.Methode_Verfahren = false;
                    GlobalVariable.Methode_Werkstoff = false;
                    GlobalVariable.Methode_Drahtdurchmesser = true;
                    GlobalVariable.Methode_Gas = false;
                    GlobalVariable.Methode_Betriebsart = false;
                    tv.setText("DRAHTDURCHMESSER");
                    detail = init_durchmesser();
                }else if ((tv.getText().equals("DRAHTDURCHMESSER"))){
                    GlobalVariable.Methode_Verfahren = false;
                    GlobalVariable.Methode_Werkstoff = true;
                    GlobalVariable.Methode_Betriebsart = false;
                    GlobalVariable.Methode_Gas = false;
                    GlobalVariable.Methode_Drahtdurchmesser = false;
                    tv.setText("WERKSTOFF");
                    detail = init_werkstoff();
                }else if ((tv.getText().equals("WERKSTOFF"))){
                    GlobalVariable.Methode_Verfahren = false;
                    GlobalVariable.Methode_Drahtdurchmesser = false;
                    GlobalVariable.Methode_Betriebsart = true;
                    GlobalVariable.Methode_Gas = false;
                    GlobalVariable.Methode_Werkstoff = false;
                    tv.setText("BETRIEBSART");
                    detail = init_Betriebsart();
                }else if ((tv.getText().equals("BETRIEBSART"))){
                    GlobalVariable.Methode_Drahtdurchmesser = false;
                    GlobalVariable.Methode_Betriebsart = false;
                    GlobalVariable.Methode_Gas = false;
                    GlobalVariable.Methode_Werkstoff = false;
                    GlobalVariable.Methode_Verfahren = true;
                    tv.setText("VERFAHREN");
                    detail = init_verfahren();
                }
                //CreateAdapter();
            }
        }

     public void CreateAdapter(){
         /*ArrayAdapter<String>adapter2= new ArrayAdapter<String>(getContext(),R.layout.item_for_kennlinie,R.id.textBetriebsart,detail){
             @NonNull
             @Override
             public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                 View view1 =super.getView(position,convertView,parent);
                 //textView = convertView.findViewById(R.id.textBetriebsart);
                    *//* if(position==2){
                        sc.setBackgroundResource(R.drawable.background_button_grau);
                     }*//*
                 return  view1;
             }
         };
         sc.setAdapter(adapter2);*/
         sc.addItems(detail,5);
     }

     public static void setButtonInvisible(){
         next.setVisibility(View.INVISIBLE);
         previous.setVisibility(View.INVISIBLE);
     }

    public static void setButtonVisible(){
        next.setVisibility(View.VISIBLE);
        previous.setVisibility(View.VISIBLE);
    }
}