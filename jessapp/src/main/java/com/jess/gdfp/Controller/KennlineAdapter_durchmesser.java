package com.jess.gdfp.Controller;

import android.graphics.Color;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;

import com.jess.gdfp.DatenBank.Kennline_text;
import com.jess.gdfp.GlobalVariable;
import com.jess.gdfp.IO.OnloadMoreListener;
import com.jess.gdfp.MainActivity;
import com.jess.gdfp.R;
import com.jess.gdfp.WeldingProcess;

import java.util.ArrayList;
import java.util.List;

public class KennlineAdapter_durchmesser extends RecyclerView.Adapter{
    private final int ViEW_REPLAY = 0;
    private final int VIEW_ITEM = 1;
    private int lastVisibleItem, totalItemCount;
    private boolean loading;
    static int first;
    static int last;
    static  int x;


    private OnloadMoreListener onLoadMoreListener;
    List<Kennline_text> kennline_texts;

    public static class KennlineHolder extends RecyclerView.ViewHolder {

      Button button;
        KennlineHolder(View view) {
            super(view);
            int pos[]=new int[2];
              button = view.findViewById(R.id.listButton);
              int[] location = new int[2];
             // FrameLayout frameLayout= view.findViewById(R.id.myframe_location);
              //frameLayout.getLocationInWindow(location);
              System.out.println("the location"+location[1]);
              button.getLocationOnScreen(pos);
              System.out.println(pos[1]);

        }
    }

    public KennlineAdapter_durchmesser(List<Kennline_text> kennline_texts, RecyclerView recyclerView) {
        this.kennline_texts = kennline_texts;
        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {

            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);


                    first=linearLayoutManager.findFirstCompletelyVisibleItemPosition();
                    lastVisibleItem = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                    last=lastVisibleItem;

                    if (!loading && (totalItemCount <= totalItemCount)) {

                        if (onLoadMoreListener != null) {
                            onLoadMoreListener.onLoadMore();
                        }
                        loading = true;
                    }

                }
            });
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder myViewholder;
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_for_kennlinie, viewGroup, false);
        myViewholder = new KennlineAdapter_durchmesser.KennlineHolder(v);
        return myViewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {


        if (viewHolder instanceof KennlineHolder) {
            int pos[]=new int[2];
            ((KennlineHolder) viewHolder).button.setText(kennline_texts.get(i).getTitel());
            ((KennlineHolder) viewHolder).button.getLocationOnScreen(pos);


        }
        System.out.println("the first on  "+first +"\n" + "the last on  "+ last);
        x =(last +first)/2;
        if(i==x){

            ((KennlineHolder) viewHolder).button.setTextColor(Color.GREEN);



            String DRAHTDM = (String)((KennlineHolder) viewHolder).button.getText();
            WeldingProcess.setDrahtDM(DRAHTDM);
            System.out.println(DRAHTDM);
        }else {
            ((KennlineHolder) viewHolder).button.setTextColor(Color.WHITE);
        }
    }
    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return kennline_texts.size();
    }
    public void setLoaded() {
        loading = false;
    }

    @Override
    public int getItemViewType(int position) {
        return kennline_texts.get(position) != null ?VIEW_ITEM : ViEW_REPLAY;
    }

    public void setOnLoadMoreListener(OnloadMoreListener onLoadMoreListener){
        this.onLoadMoreListener=onLoadMoreListener;
    }


}

