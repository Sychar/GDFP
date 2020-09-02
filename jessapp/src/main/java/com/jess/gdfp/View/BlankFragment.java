package com.jess.gdfp.View;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.jess.gdfp.Controller.KennlineAdapter_Gas;
import com.jess.gdfp.Controller.KennlineAdapter_Matrial;
import com.jess.gdfp.Controller.KennlineAdapter_Verfah;
import com.jess.gdfp.Controller.KennlineAdapter_durchmesser;
import com.jess.gdfp.DatenBank.Kennline_text;
import com.jess.gdfp.IO.OnloadMoreListener;
import com.jess.gdfp.R;

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
    private  ListView listView ;
    private RecyclerView listView2 ;
    private  ListView listView3;
    private  ListView listView4;
    private ArrayList<Kennline_text> detail;
    private List<Kennline_text> detail2;
    private  List<Kennline_text> detail3;
    private  List<Kennline_text>detail4;
    protected Handler handler;


    private static int i =0;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_kennline, container, false);



        detail= intit_verfahren();
        RecyclerView rv0= view.findViewById(R.id.listone);
        LinearLayoutManager mLayoutManager0 = new LinearLayoutManager(getContext() );
        mLayoutManager0.setOrientation(LinearLayoutManager.VERTICAL);
        mLayoutManager0.setReverseLayout(true);
        mLayoutManager0.setStackFromEnd(true);
        rv0.setLayoutManager(mLayoutManager0);
        final KennlineAdapter_Verfah adapter_verfah=new KennlineAdapter_Verfah(detail,rv0);
        rv0.setAdapter(adapter_verfah);
        adapter_verfah.notifyDataSetChanged();


        /*************************************************************************/
        RecyclerView rv = view.findViewById(R.id.listzwei);
        detail2=init_matrial();
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext() );
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        rv.setLayoutManager(mLayoutManager);
        final KennlineAdapter_Matrial adapter_matrial=new KennlineAdapter_Matrial(detail2,rv);
        rv.setAdapter(adapter_matrial);
        adapter_matrial.notifyDataSetChanged();


        /*************************************************************************/
        RecyclerView rv1 = view.findViewById(R.id.listdrei);
        detail3= init_Gas();
        LinearLayoutManager mLayoutManager1 = new LinearLayoutManager(getContext() );
        mLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
        mLayoutManager1.setReverseLayout(true);
        mLayoutManager1.setStackFromEnd(true);
        rv1.setLayoutManager(mLayoutManager1);
        final KennlineAdapter_Gas adpater_gas = new KennlineAdapter_Gas(detail3,rv1);
        rv1.setAdapter(adpater_gas);
        adpater_gas.notifyDataSetChanged();
        /*************************************************************************/


        RecyclerView rv2 = view.findViewById(R.id.listvier);
        detail4= init_durchmesser();
        LinearLayoutManager mLayoutManager2 = new LinearLayoutManager(getContext() );
        mLayoutManager2.setOrientation(LinearLayoutManager.VERTICAL);
       // mLayoutManager2.setReverseLayout(true);
        mLayoutManager2.setStackFromEnd(true);
        rv2.setLayoutManager(mLayoutManager2);
        final KennlineAdapter_durchmesser adpater_durchmesser = new KennlineAdapter_durchmesser(detail4,rv2);
        rv2.setAdapter(adpater_durchmesser);
        adpater_durchmesser.notifyDataSetChanged();

        /*********************************************************************************/

adapter_verfah.setOnLoadMoreListener(new OnloadMoreListener() {
    @Override
    public void onLoadMore() {
        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<7;i++){
                    detail.add(detail.get(i));
                    adapter_verfah.notifyItemInserted(detail.size());
                    adapter_verfah.notifyDataSetChanged();
                }
                adapter_verfah.setLoaded();
            }
        },200);
    }
});

        adapter_matrial.setOnLoadMoreListener(new OnloadMoreListener() {
         @Override
         public void onLoadMore() {

             handler =new Handler();
             handler.postDelayed(new Runnable() {
                 @Override
                 public void run() {
                     for (int i = 0; i < 10; i++) {

                         detail2.add(detail2.get(i));
                         adapter_matrial.notifyItemInserted(detail2.size());
                         adapter_matrial.notifyDataSetChanged();
                     }
                       adapter_matrial.setLoaded();
                 }

             }, 500);
         }
     });
adpater_gas.setOnLoadMoreListener(new OnloadMoreListener() {
    @Override
    public void onLoadMore() {
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for(int i=0 ;i<19;i++){
                    detail3.add(detail3.get(i));
                    adpater_gas.notifyItemInserted(detail3.size());
                    adpater_gas.notifyDataSetChanged();
                }
                adpater_gas.setLoaded();
            }
        },500);

    }
});
adpater_durchmesser.setOnLoadMoreListener(new OnloadMoreListener() {
    @Override
    public void onLoadMore() {
        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<230;i++){
                    detail4.add(detail4.get(i));
                    adpater_durchmesser.notifyItemInserted(detail4.size());
                    adpater_durchmesser.notifyDataSetChanged();
                }
                adpater_durchmesser.setLoaded();
            }
        },200);
    }
});


       /* listView3=view.findViewById(R.id.listdrei);
        detail3=init_durchmesser();
        KennlineAdapter_Verfahren kennlineAdapter2 =new KennlineAdapter_Verfahren(getActivity(),detail3);
        listView3.setAdapter(kennlineAdapter2);
        listView4=view.findViewById(R.id.listvier);
        detail4=init_Gas();
        KennlineAdapter_Verfahren kennlineAdapter3=new KennlineAdapter_Verfahren(getActivity(),detail4);
        listView4.setAdapter(kennlineAdapter3);*/
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

 private  ArrayList intit_verfahren(){
   ArrayList detail=new ArrayList();
     detail.add(new Kennline_text("MIG/MAG Normal"));
     detail.add(new Kennline_text("MIG/MAG synregy"));
     detail.add(new Kennline_text("PLUS"));
     detail.add(new Kennline_text("ElECTRODE"));
     detail.add(new Kennline_text("WIG"));
     detail.add(new Kennline_text("WIG PULSEN"));
     detail.add(new Kennline_text("WIG SPEED"));


     return detail;
 }
 private  List init_matrial(){
     List  detail2 = new ArrayList<>();
     detail2.add(new Kennline_text("Fe"));
     detail2.add(new Kennline_text("Cr/Ni"));
     detail2.add(new Kennline_text("AL/Mg"));
     detail2.add(new Kennline_text("AL/Si"));
     detail2.add(new Kennline_text("Cu/Si"));
     detail2.add(new Kennline_text("Al/mg3"));
     detail2.add(new Kennline_text("Al/mg5"));
     detail2.add(new Kennline_text("Al/mg4/5Mn"));
     detail2.add(new Kennline_text("Al/Bz"));
     detail2.add(new Kennline_text("Spezial"));
     return detail2;
 }

 private  List init_durchmesser(){
     List  detail3 = new ArrayList<>();
     detail3.add(new Kennline_text("0.8"));
    double count= 9;
     for (int i=1;i<153;i++){
      detail3.add(new Kennline_text(Double.toString(count/10)));
      //System.out.println(count);
         count =  count + 1;
     }

     return detail3;
 }
 private  List init_Gas(){
     List  detail4 = new ArrayList<>();
     detail4.add(new Kennline_text("82%AR / 18%CO"));
     detail4.add(new Kennline_text("98%AR / 2%CO"));
     detail4.add(new Kennline_text("100%AR"));
     detail4.add(new Kennline_text("100%CO"));
     detail4.add(new Kennline_text("92%AR / 8%CO"));
     detail4.add(new Kennline_text("99%AR / 1%O2"));
     detail4.add(new Kennline_text("98%AR / 2%O2"));
     detail4.add(new Kennline_text("97%AR / 3%O2"));
     detail4.add(new Kennline_text("92%AR / 8%O2"));
     detail4.add(new Kennline_text("90%AR / 5%O2/ 5%CO"));
     detail4.add(new Kennline_text("100%HE"));
     detail4.add(new Kennline_text("80%AR / 20%HE"));
     detail4.add(new Kennline_text("69%AR/ 30%HE/1%O2"));
     detail4.add(new Kennline_text("50Ar / 50%HE"));
     detail4.add(new Kennline_text("98Ar / 2%H2"));
     detail4.add(new Kennline_text("94Ar / 6%H2"));
     detail4.add(new Kennline_text("50Ar / 50%H2"));
     detail4.add(new Kennline_text("30Ar / 70%H2"));
     detail4.add(new Kennline_text("Spezial"));
     return detail4;
 }
}
