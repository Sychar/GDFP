package com.jess.gdfp.DatenBank;

import android.content.Context;
import android.content.res.AssetManager;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MyJeson {


   public MyJeson(){

}
    private String stringFromStream(InputStream is) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null)
                sb.append(line).append("\n");
            reader.close();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String readJeson(String filename ,Context context) {
         String result;

        AssetManager am = context.getAssets();
        try {
            InputStream is = am.open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            result=new String(buffer,"UTF-8");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public void removeObjekt (JSONArray  myarray ,int index){
       myarray.remove(index);
    }
}