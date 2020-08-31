package com.jess.gdfp.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jess.gdfp.R;
import com.jess.gdfp.MainActivity;


public class MainActivity_Controller extends AppCompatActivity {
Intent intent;
MainActivity mainActivity;



public MainActivity_Controller(MainActivity mainActivity){
this.mainActivity=mainActivity;

}
public MainActivity_Controller(){

}

public  void minus_plus_interagieren(ProgressBar progressBar_minus ,ProgressBar progressBar_plus ,View view){
    int x =   progressBar_plus.getProgress();
    int y = progressBar_minus.getProgress();
    if (view.getId() == R.id.plus) {

        if(x==0&&y<=0){
            progressBar_plus.setProgress(x+3);
        }
        else if(x==0&&y>0){
            progressBar_minus.setProgress(y-3);
        }
        else if(x>0){
            progressBar_plus.setProgress(x+3);
        }
    }

    if (view.getId() == R.id.minus) {
        if(x>0){
            progressBar_plus.setProgress(x-3);
        }
        if(x<=0){
            progressBar_minus.setProgress(y+3);
        }

    }
}

    public void ChangeTextprogressBar(Button btn , TextView txtProgress ,String temp ,boolean cheack )
                                     {

        int id = btn.getId();
        if (id == R.id.btn_korrektur) {

            TextView textView =  findViewById(R.id.korrektur);
            String s = textView.getText().toString();
            String s1 = btn.getText().toString();
            String s2 = txtProgress.getText().toString();
            textView.setText(temp);
            btn.setText(s2);
            txtProgress.setText(s1);
            temp = s;
            cheack = false;
        }
        if (id == R.id.btn_mm) {

            TextView textView = findViewById(R.id.Mm);
            String s = textView.getText().toString();
            String s1 = btn.getText().toString();

            String s2 = txtProgress.getText().toString();
            textView.setText(temp);
            txtProgress.setText(s1);
            btn.setText(s2);
            temp = s;
            cheack = false;
        }
        if (id == R.id.btn_current) {
            TextView textView = findViewById(R.id.strom);
            String s = textView.getText().toString();
            String s1 = btn.getText().toString();
            String s2 = txtProgress.getText().toString();
            textView.setText(temp);
            txtProgress.setText(s1);
            btn.setText(s2);
            temp = s;
            cheack = false;
        }
    }

 public  void onClick_newActivity(Class myclass){
    intent = new Intent(mainActivity, myclass);
    mainActivity.startActivity(intent);
 }
public  void  setText(TextView text){

//    mainActivity.TxtProgress=mainActivity.findViewById(R.id.txtpro);
}
}
