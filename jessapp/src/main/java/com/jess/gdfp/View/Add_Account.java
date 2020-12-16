package com.jess.gdfp.View;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.jess.gdfp.DatenBank.User_Datenbank;
import com.jess.gdfp.DatenBank.Users;
import com.jess.gdfp.R;

public class Add_Account extends AppCompatActivity {
    private String name ;
    private static  String prveling;
    private Users user;
    private Button add;
    EditText editText;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__account);
        editText =(EditText) findViewById(R.id.enterName);
        add = (Button) findViewById(R.id.add_user);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add();
            }
        });

    }

    public void onRadioButton(View view){
        boolean cheaked=((RadioButton) view).isChecked();
        switch (view.getId()){
            case R.id.master:
                if(cheaked){
                    prveling="master";
                    break;
                }
            case R.id.general:
                if(cheaked){
                    prveling="General";
                    break;
                }
        }
    }

    public void add(){
        name= editText.getText().toString();
        user =new Users(name,prveling);
        User_Datenbank user_datenbank = new User_Datenbank(this);
        user_datenbank.addUser(user);
        intent = new Intent(this, Account_deteils.class);
        startActivity(intent);

    }

}