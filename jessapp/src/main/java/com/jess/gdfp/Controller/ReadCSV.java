package com.jess.gdfp.Controller;

import android.content.Context;
import android.content.res.AssetManager;

import com.jess.gdfp.View.Setting;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadCSV {
   static String [] myArray = new String[491];
    private static String[][] UMLAUT_REPLACEMENTS = { { new String("Ä"), "Ae" }, { new String("Ü"), "Ue" }, { new String("Ö"), "Oe" }, { new String("ä"), "ae" }, { new String("ü"), "ue" }, { new String("ö"), "oe" }, { new String("ß"), "ss" } };
    public static void readCSv(Context context) {
        int i=0;

        AssetManager ms = context.getAssets();
        try {
            InputStreamReader fis = new InputStreamReader(ms.open("DataLogger.csv"), StandardCharsets.UTF_8.name());
            BufferedReader br = new BufferedReader(fis);
            String line ="";

            while ((line = br.readLine()) != null) {

                String[] values = line.split(";");
                myArray[i]=values[0];
                i++;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        int l=0;
      for(int j =0; j<myArray.length;j++){

         myArray[j].trim();
         myArray[j]=myArray[j].replace(" ","_");
         myArray[j]=myArray[j].replace("-","");
          for (int k = 0; k < UMLAUT_REPLACEMENTS.length; k++) {
              myArray[j]= myArray[j].replace(UMLAUT_REPLACEMENTS[k][0], UMLAUT_REPLACEMENTS[k][1]);
          }
         System.out.println(myArray[j]);




      }


    }

    public static void readCSv_internal(Context context,String file_name) {
        int i=0;


        try {
            String yourFilePath = context.getFilesDir() + "/" + file_name;
            InputStream inputStream = new FileInputStream(yourFilePath);
            InputStreamReader fis = new InputStreamReader(inputStream, StandardCharsets.UTF_8.name());
            BufferedReader br = new BufferedReader(fis);
            String line ="";

            while ((line = br.readLine()) != null) {

                String[] values = line.split(";");
                myArray[i]=values[0];
                i++;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        int l=0;
        for(int j =0; j<myArray.length;j++){

            myArray[j].trim();
            myArray[j]=myArray[j].replace(" ","_");
            myArray[j]=myArray[j].replace("-","");
            for (int k = 0; k < UMLAUT_REPLACEMENTS.length; k++) {
                myArray[j]= myArray[j].replace(UMLAUT_REPLACEMENTS[k][0], UMLAUT_REPLACEMENTS[k][1]);
            }
            System.out.println(myArray[j]);




        }


    }

}
