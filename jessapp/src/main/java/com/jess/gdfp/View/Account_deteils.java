package com.jess.gdfp.View;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.jess.gdfp.DatenBank.User_Datenbank;
import com.jess.gdfp.DatenBank.Users;
import com.jess.gdfp.R;

import java.util.ArrayList;
import java.util.List;

public class Account_deteils extends AppCompatActivity {
    private  Intent intent;
    private List<Users> users;
    private Button add;
    private Button remove;
    private UserAdapter adapter;
    public static int id;
    User_Datenbank user_datenbank = new User_Datenbank(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        init();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_deteils);
        RecyclerView rc = (RecyclerView)findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rc.setLayoutManager(llm);

        adapter = new UserAdapter(users);
        rc.setAdapter(adapter);
        add=(Button)findViewById(R.id.add);
        remove=(Button)findViewById(R.id.remove);
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Remove();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickAdd_Account();
            }
        });
    }


    private  void init(){
        users = new ArrayList<>();

        users= user_datenbank.getAllusers();


    }

    void onClickAdd_Account(){
        intent= new Intent(this, Add_Account.class);
        startActivity(intent);
    }

    public void Remove (){

        Users user = user_datenbank.getUser(id);
        user_datenbank.deletUser(user);

        intent =new Intent(this, Account_deteils.class);
        startActivity(intent);

    }
}