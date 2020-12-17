package com.jess.gdfp.Controller;

import android.content.Context;

import com.jess.gdfp.DatenBank.MyJeson;

import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;

import static com.jess.gdfp.Controller.JsonKennlinie.readjson;

public class Json_Setting {
    /*** write data in Favorits.json **/
    public static void JsonWrite_Favorit(Context context) {
            try {


                JSONObject main = new JSONObject();
                main.put("DatenBank", readjson(context,"Favorit.json"));
                String userString = main.toString();
                String yourFilePath = context.getFilesDir() + "/" + "Fovorits.json";
                File yourFile = new File(yourFilePath);
                FileWriter fileWriter = new FileWriter(yourFile, false);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(userString);
                bufferedWriter.close();
                // FileOutputStream fileOutputStream = new FileOutputStream(yourFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    /*** write data in  Default_config.json **/
    public static void editing_Default_config(Context context) {
        try {

            JSONObject main = new JSONObject();
            main.put("DatenBank", readjson(context,"Default_config.json"));
            String userString = main.toString();
            String yourFilePath = context.getFilesDir() + "/" + "Default_config.json";
            File yourFile = new File(yourFilePath);
            FileWriter fileWriter = new FileWriter(yourFile, false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(userString);
            bufferedWriter.close();
            // FileOutputStream fileOutputStream = new FileOutputStream(yourFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*** write data in  Setting _config.json **/
    public static void editing_setting_config(Context context) {
        try {


            JSONObject main = new JSONObject();
            main.put("DatenBank", readjson(context,"Setting_config.json"));
            String userString = main.toString();
            String yourFilePath = context.getFilesDir() + "/" + "Setting_config.json";
            File yourFile = new File(yourFilePath);
            FileWriter fileWriter = new FileWriter(yourFile, false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(userString);
            bufferedWriter.close();
            // FileOutputStream fileOutputStream = new FileOutputStream(yourFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        }

