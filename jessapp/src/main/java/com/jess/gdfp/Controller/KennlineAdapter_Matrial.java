package com.jess.gdfp.Controller;

import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jess.gdfp.DatenBank.Kennline_text;
import com.jess.gdfp.IO.OnloadMoreListener;
import com.jess.gdfp.MainActivity;
import com.jess.gdfp.R;

import java.util.List;

public   class KennlineAdapter_Matrial extends RecyclerView.Adapter {
    static Button mitte1;
    private final int ViEW_REPLAY=0;
    private final int VIEW_ITEM = 1;
    static  int x;
    private int lastVisibleItem, totalItemCount;
    static  int count;
    static int first;
    static int last;
    private boolean loading;
    private static Button mitte;
    private  View view;
    private OnloadMoreListener onLoadMoreListener;
    List<Kennline_text> kennline_texts;

    protected Handler handler;
    public static class KennlineHolder extends RecyclerView.ViewHolder {
        Button button;

        KennlineHolder(View viewitem) {
            super(viewitem);
            button = viewitem.findViewById(R.id.listButton);
            mitte=viewitem.findViewById(R.id.listButton);
        }

    }
    public static class KennlineHolder2 extends RecyclerView.ViewHolder {
        Button button;

        KennlineHolder2(View viewitem) {
            super(viewitem);
            button = viewitem.findViewById(R.id.listButton);
        }
    }

    public KennlineAdapter_Matrial(List<Kennline_text> kennline_texts , RecyclerView recyclerView) {

        this.kennline_texts = kennline_texts;

        if(recyclerView.getLayoutManager() instanceof LinearLayoutManager){

            final LinearLayoutManager linearLayoutManager =(LinearLayoutManager)recyclerView.getLayoutManager();
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);

                    totalItemCount = linearLayoutManager.getItemCount();
                    count=totalItemCount;
                    lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                    last=lastVisibleItem;
                    first=linearLayoutManager.findFirstVisibleItemPosition();
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
        if (i == VIEW_ITEM) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_for_kennlinie, viewGroup, false);
            myViewholder = new KennlineHolder(v);
        } else {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_for_kennlinie2, viewGroup, false);
            myViewholder= new KennlineHolder2(v);
        }



        return  myViewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int i) {
        if(viewHolder instanceof KennlineHolder){
            ((KennlineHolder) viewHolder).button.setText(kennline_texts.get(i).getTitel());
            x =(last +first)/2;

            if(i==x){
                ((KennlineHolder) viewHolder).button.setTextColor(Color.GREEN);
                String MATERIAL = (String)((KennlineHolder) viewHolder).button.getText();
                //MainActivity.setMaterial(MATERIAL);
                //System.out.println(MATERIAL);
            }else {
                ((KennlineHolder) viewHolder).button.setTextColor(Color.GRAY);
            }

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
