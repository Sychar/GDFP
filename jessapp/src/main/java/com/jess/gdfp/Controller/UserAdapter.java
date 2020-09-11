package com.jess.gdfp.Controller;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.jess.gdfp.DatenBank.Users;
import com.jess.gdfp.IO.ItemClickListener;
import com.jess.gdfp.R;
import com.jess.gdfp.View.Account_deteils;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    public static class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

       TextView name;
      TextView preveling;
      CheckBox checkBox;
      ItemClickListener itemClickListener;

        UserViewHolder(View itemView) {
          super(itemView);
            CardView cv = (CardView) itemView.findViewById(R.id.cv);
            name =(TextView) itemView.findViewById(R.id.name_user);
            preveling =(TextView) itemView.findViewById(R.id.preveling);

            checkBox.setOnClickListener(this);

        }
        public void setItemClickListener(ItemClickListener ic){
            this.itemClickListener =ic ;
        }

        @Override
        public void onClick(View view) {
            this.itemClickListener.onIteClick(view,getLayoutPosition());
        }
    }
    List<Users> users;
    public UserAdapter(List<Users> users){
        this.users=users;
    }
    @Override
    public int getItemCount() {
        return users.size();
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.account_item,parent,false);
        UserViewHolder uvh= new UserViewHolder(v);
        return  uvh;

    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {


     holder.name.setText(users.get(position).getName());
     holder.preveling.setText(users.get(position).getPreveling());
     holder.setItemClickListener(new ItemClickListener() {
         @Override
         public void onIteClick(View view, int pos) {
             CheckBox ch =(CheckBox)view;
             if(ch.isChecked()){
                 Account_deteils.id=pos;

             }
         }
     });


    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
