

/**
 * @Autor Sakhr Aljendi
 * @Version 1.0
 * 09.01.2020
 */
package com.jess.gdfp.View;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jess.gdfp.R;

import java.util.ArrayList;

public class RVAdapter extends ArrayAdapter<Details> {
    ImageView imageView;
    private  View listitem;
   private Details currentDetails;
   TextView textView;
    ArrayList <Details>  details  =new ArrayList<>();



    public RVAdapter(Activity context , ArrayList<Details> details){
        super(context,0,details);
    }





    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        listitem=convertView;
        if(listitem==null){
            listitem = LayoutInflater.from(getContext()).inflate(R.layout.listitem,parent,false);
        }
        currentDetails = getItem(position);
        TextView textView =(TextView) listitem.findViewById(R.id.title);
        textView.setText(currentDetails.getTitel());
        imageView = (ImageView) listitem.findViewById(R.id.Poster);
        //  imageView.setImageResource(currentDetails.getImage());

        return listitem;
    }
}
