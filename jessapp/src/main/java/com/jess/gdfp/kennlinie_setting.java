package com.jess.gdfp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.jess.gdfp.Controller.kennlinieAdapte;
import com.jess.gdfp.DatenBank.Jobs;
import com.jess.gdfp.DatenBank.Kennlinie;
import com.jess.gdfp.View.kennlinie;

import java.util.ArrayList;


public class kennlinie_setting extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kennlinie);
        ListView ls = findViewById(R.id.kennlinie_setting);
        ArrayList myArray = initkennline();
        kennlinieAdapte myAdapter = new kennlinieAdapte(this,myArray);
        ls.setAdapter(myAdapter);

    }

private  String[] params={
            " Kennlinie Setting Nr" ," Kennlinie Setting Type"," Verfahren"," Drahtdurchmessr","Gas "," Werkstof"," Regler Type "," Nach Gas",
        " Upslope" ," DownSlopeString" ," Zundung Prozess" ," End Krater Prozess" ," Zuend Dauer" ," Edn Kar Dauer" ," Frei Brand Prozess" ," PP Energie" ," PP Time1 ",
        " PP Time 2" , " PP LEC E2" , " Protokoll Type " ,  " Drossel Res 1 " , " Drossel Res 2 " ," Drossel Res 3 "," Drossel Res 4 "," Drossel Res 5 " ," Drossel Res 6 "," Drossel Res 7 ",
        " LBR Mode" ," Erkennung " ," Aufhebung" ," Verwzeit PN ", " Verwzeit PN " ," StromSchwell" ," PositivZeit" ," Kenn ESS NR " ," Kenn Bez 1"," Kenn Bez 2" ," Kenn Komm", "Grund CRC"
};
    private ArrayList initkennline() {
        ArrayList Kennline_array = new ArrayList<>();
        for( int i=0;i<params.length;i++){
            Kennline_array.add(new com.jess.gdfp.DatenBank.Kennlinie(params[i],"-"));
        }
            return Kennline_array;
    }


}
