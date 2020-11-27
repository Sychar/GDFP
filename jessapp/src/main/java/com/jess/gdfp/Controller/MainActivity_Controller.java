package com.jess.gdfp.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jess.gdfp.DatenObjekteSend;
import com.jess.gdfp.GlobalVariable;
import com.jess.gdfp.R;
import com.jess.gdfp.MainActivity;

public class MainActivity_Controller extends AppCompatActivity {
Intent intent;
MainActivity mainActivity;

public MainActivity_Controller(MainActivity mainActivity){ this.mainActivity=mainActivity; }
public MainActivity_Controller(){ }

public  void minus_plus_interagieren(ProgressBar progressBar_minus ,ProgressBar progressBar_plus ,View view){
    int x = progressBar_plus.getProgress();
    int y = progressBar_minus.getProgress();
    if (view.getId() == R.id.Drossel_plus) {
        GlobalVariable.korrektur_display = GlobalVariable.Lichtbogenkorrektur1+1;
        DatenObjekteSend sendKorrektur = new DatenObjekteSend();
        sendKorrektur.ChangeParameter(21, GlobalVariable.korrektur_display, 1);
    }
    if (view.getId() == R.id.Drossel_minus) {
        GlobalVariable.korrektur_display = GlobalVariable.Lichtbogenkorrektur1-1;
        DatenObjekteSend sendKorrektur = new DatenObjekteSend();
        sendKorrektur.ChangeParameter(21, GlobalVariable.korrektur_display, 1);
    }
}
    public void ChangeTextprogressBar(Button btn , TextView txtProgress ,String temp ,boolean cheack )
                                     {
        int id = btn.getId();
        if (id == R.id.Value1) {
            TextView textView =  findViewById(R.id.Label1);
            String s = textView.getText().toString();
            String s1 = btn.getText().toString();
            String s2 = txtProgress.getText().toString();
            textView.setText(temp);
            btn.setText(s2);
            txtProgress.setText(s1);
            temp = s;
            cheack = false;
        }
        if (id == R.id.Value2) {

            TextView textView = findViewById(R.id.Label2);
            String s = textView.getText().toString();
            String s1 = btn.getText().toString();

            String s2 = txtProgress.getText().toString();
            textView.setText(temp);
            txtProgress.setText(s1);
            btn.setText(s2);
            temp = s;
            cheack = false;
        }
        if (id == R.id.Value3) {
            TextView textView = findViewById(R.id.Label3);
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
