package com.jess.gdfp.Controller;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jess.gdfp.DatenBank.MyJeson;

import org.json.JSONArray;
import org.json.JSONObject;

import org.json.simple.parser.JSONParser;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;

import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class JsonKennlinie {


    public static JSONObject readjson(Context context) {
        Gson gson = new Gson();
        MyJeson myJeson = new MyJeson();
        JSONObject obj;
        String s = myJeson.readJeson("kennlinie_json.json", context);
        // String kennlinieJson = myJeson.readJeson("kennlinie_json.json", context);
        //try {
        //  JSONArray kennlines = new JSONArray(kennlinieJson);
        // kennlines.remove(0);
        //for (int i = 0; i < kennlines.length(); i++) {
        //  JSONObject kenn = kennlines.getJSONObject(i);
        //JSONArray list = kenn.getJSONArray("kennlinie");

        //}
        //} catch (Exception e) {
        //  e.printStackTrace();
        //    }
        try {
            obj = new JSONObject(s);
            return obj;
        } catch (Exception e) {
            e.printStackTrace();

        }

        System.out.println("hallo ist hier mein " + s);
        return null;
    }

    public static void KennlinieSchrieben(Context context) {
        try {
            JSONObject object = new JSONObject();
            JSONObject object1 = new JSONObject();
            JSONObject object2 = new JSONObject();
            JSONObject object3 = new JSONObject();

            object.put("name", "sakhr");
            object1.put("name", "jj");
            object2.put("name", "dkd");


            // org.json.simple.JSONArray jsonArray= new org.json.simple.JSONArray();

            JSONObject main = new JSONObject();
            main.put("DatenBank", readjson(context));
            String userString = main.toString();
            //FileOutputStream fOut = openFileOutput("kennline.json",Context.MODE_PRIVATE);
            //String str = "test data";
            //fOut.write(str.getBytes());
            //fOut.close();
            String yourFilePath = context.getFilesDir() + "/" + "kennline.json";
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

    public static org.json.simple.JSONArray Kennlinielesen(Context context) {
        JSONParser parser = new JSONParser();

        try {
            String yourFilePath = context.getFilesDir() + "/" + "kennline.json";
            Object obj = parser.parse(new FileReader(yourFilePath));
            org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) obj;
            org.json.simple.JSONObject jsonObject2;
            org.json.simple.JSONArray jsonArray;
            jsonObject2 = (org.json.simple.JSONObject) jsonObject.get("DatenBank");
            jsonArray = (org.json.simple.JSONArray) jsonObject2.get("kennlinie");

            //System.out.println("mein kennlinie"+jsonArray.get(70));
            org.json.simple.JSONObject jsonObject14 = (org.json.simple.JSONObject) jsonArray.get(70);
            System.out.println(jsonObject14.get("Verfahren"));
            return jsonArray;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;


    }
public  static List<String[]> get_all_Daten(String Conditon[],Context context){

      List<String[]>alldata =new ArrayList<>();
    org.json.simple.JSONArray jsonArray=Kennlinielesen(context);
    org.json.simple.JSONObject js1;
    for( int i=0;i< jsonArray.size();i++){
        js1=(org.json.simple.JSONObject) jsonArray.get(i);
        String[] mydate =new String[5];
        String s1 =js1.get(Conditon[0]).toString();
        mydate[0]=s1;
        String s2 =js1.get(Conditon[1]).toString();
        mydate[1]=s2;
        String s3 =js1.get(Conditon[2]).toString();
        mydate[2]=s3;
        String s4 =js1.get(Conditon[3]).toString();
        mydate[3]=s4;
        String s5 =js1.get(Conditon[4]).toString();
        mydate[4]=s5;
        System.out.println(mydate);
        alldata.add(mydate);
    }

    return  alldata;
}
    public  static List<String[]> get_all_Daten2(String Conditon[],Context context){

        List<String[]>alldata =new ArrayList<>();
        org.json.simple.JSONArray jsonArray=Kennlinielesen(context);
        org.json.simple.JSONObject js1;
        for( int i=0;i< jsonArray.size();i++){
            js1=(org.json.simple.JSONObject) jsonArray.get(i);
            String[] mydate =new String[13];
            String s1 =js1.get(Conditon[0]).toString();
            mydate[0]=s1;
            String s2 =js1.get(Conditon[1]).toString();
            mydate[1]=s2;
            String s3 =js1.get(Conditon[2]).toString();
            mydate[2]=s3;
            String s4 =js1.get(Conditon[3]).toString();
            mydate[3]=s4;
            String s5 =js1.get(Conditon[4]).toString();
            mydate[4]=s5;
            String s6 =js1.get(Conditon[5]).toString();
            mydate[5]=s6;
            String s7 =js1.get(Conditon[6]).toString();
            mydate[6]=s7;
            String s8 =js1.get(Conditon[7]).toString();
            mydate[7]=s8;
            String s9 =js1.get(Conditon[8]).toString();
            mydate[8]=s9;
            String s10 =js1.get(Conditon[9]).toString();
            mydate[9]=s10;
            String s11 =js1.get(Conditon[10]).toString();
            mydate[10]=s11;
            String s12 =js1.get(Conditon[11]).toString();
            mydate[11]=s12;
            String s13 =js1.get(Conditon[12]).toString();
            mydate[12]=s13;
            System.out.println(mydate);
            alldata.add(mydate);
        }

        return  alldata;
    }

    public static List<org.json.simple.JSONObject> query_kennlinie(int counter, String string, String conditon, String[] conditions , String strings[],Context context) {
        List<org.json.simple.JSONObject> filters = new ArrayList<org.json.simple.JSONObject>();
        org.json.simple.JSONArray jsonArray = Kennlinielesen(context);
        org.json.simple.JSONObject jsonObject1;
        switch (counter) {
            case 1:
                for (int i = 0; i < jsonArray.size(); i++) {
                    jsonObject1 = (org.json.simple.JSONObject) jsonArray.get(i);
                    String s = jsonObject1.get(conditon).toString();
                    if (s.equals(string)) {
                     filters.add(jsonObject1);

                    }
                }
                break;
            case 2:
                for (int i = 0; i < jsonArray.size(); i++) {
                    jsonObject1 = (org.json.simple.JSONObject) jsonArray.get(i);
                    String s1 = jsonObject1.get(conditions[0]).toString();
                    String s2 = jsonObject1.get(conditions[1]).toString();
                    if (s1.equals(strings[0]) && s2.equals(strings[1])) {
                        System.out.println(jsonObject1.get("Werkstof") +"\n");
                    }

                }
                break;

            case 3:
                for (int i = 0; i < jsonArray.size(); i++) {
                    jsonObject1 = (org.json.simple.JSONObject) jsonArray.get(i);
                    String s1 = jsonObject1.get(conditions[0]).toString();
                    String s2 = jsonObject1.get(conditions[1]).toString();
                    String s3 =jsonObject1.get(conditions[2]).toString();
                    if (s1.equals(strings[0]) && s2.equals(strings[1]) && s3.equals(strings[2])) {
                        System.out.println(jsonObject1.get("Werkstof") +"\n");
                    }

                }
             break;

            case 4:
            for (int i = 0; i < jsonArray.size(); i++) {
                jsonObject1 = (org.json.simple.JSONObject) jsonArray.get(i);
                String s1 = jsonObject1.get(conditions[0]).toString();
                String s2 = jsonObject1.get(conditions[1]).toString();
                String s3 =jsonObject1.get(conditions[2]).toString();
                String s4 =jsonObject1.get(conditions[3]).toString();
                if (s1.equals(strings[0]) && s2.equals(strings[1]) && s3.equals(strings[2]) && s4.equals(strings[3])) {

                }

            }
        }
        return filters;
    }


    public static List<String[]> getValue_mitFilter(String[] filters  , List<org.json.simple.JSONObject> list) {
        List<String[]> result = new ArrayList<String[]>();
        String[] s1 =new String[list.size()] ;
        String[] s2 =new String[list.size()];
        String[] s3 =new String[list.size()];
        String[] s4 =new String[list.size()];
        String[] s5 =new String[list.size()];
        String[] s6 =new String[list.size()];
        String[] s7 =new String[list.size()];
        String[] s8 =new String[list.size()];

        int counter = filters.length;
        switch (counter) {
            case 1:
                for (int i = 0; i < list.size(); i++) {
                    String str = list.get(i).get(filters[0]).toString();

                    s1[i] = str;

                }
                result.add(s1);
                break;

            case 2:
                for (int i = 0; i < list.size(); i++) {
                    String str = list.get(i).get(filters[0]).toString();
                   s1[i] = str;

                    String str1 = list.get(i).get(filters[1]).toString();
                    s2[i] = str1;

                }
                result.add(s1);
                result.add(s2);
                break;

            case 3:
                for (int i = 0; i < list.size(); i++) {
                    String str = list.get(i).get(filters[0]).toString();
                    s1[i] = str;
                    String str1 = list.get(i).get(filters[1]).toString();
                    s2[i] = str1;
                    String str2 = list.get(i).get(filters[1]).toString();
                    s3[i] = str2;
                }
                result.add(s1);
                result.add(s2);
                result.add(s3);

                break;

            case 4:
                for (int i = 0; i < list.size(); i++) {
                    String str = list.get(i).get(filters[0]).toString();
                    s1[i] = str;
                    String str1 = list.get(i).get(filters[1]).toString();
                    s2[i] = str1;
                    String str2 = list.get(i).get(filters[2]).toString();
                    s3[i] = str2;
                    String str3 = list.get(i).get(filters[2]).toString();
                    s4[i] = str3;
                }
                result.add(s1);
                result.add(s2);
                result.add(s3);
                result.add(s4);
                break;
            case 5:
                for (int i = 0; i < list.size(); i++) {
                    String str = list.get(i).get(filters[0]).toString();
                    s1[i] = str;
                    String str1 = list.get(i).get(filters[1]).toString();
                    s2[i] = str1;
                    String str2 = list.get(i).get(filters[2]).toString();
                    s3[i] = str2;
                    String str3 = list.get(i).get(filters[3]).toString();
                    s4[i] = str3;
                    String str4 = list.get(i).get(filters[4]).toString();
                    s5[i] = str4;
                }
                result.add(s1);
                result.add(s2);
                result.add(s3);
                result.add(s4);
                result.add(s5);
                break;

            case 6:
                for (int i = 0; i < list.size(); i++) {
                    String str = list.get(i).get(filters[0]).toString();
                    s1[i] = str;
                    String str1 = list.get(i).get(filters[1]).toString();
                    s2[i] = str1;
                    String str2 = list.get(i).get(filters[2]).toString();
                    s3[i] = str2;
                    String str3 = list.get(i).get(filters[3]).toString();
                    s4[i] = str3;
                    String str4 = list.get(i).get(filters[4]).toString();
                    s5[i] = str4;
                    String str5 = list.get(i).get(filters[5]).toString();
                    s6[i] = str5;
                }
                result.add(s1);
                result.add(s2);
                result.add(s3);
                result.add(s4);
                result.add(s5);
                result.add(s6);
                break;
            case 7:
                for (int i = 0; i < list.size(); i++) {
                    String str = list.get(i).get(filters[0]).toString();
                    s1[i] = str;
                    String str1 = list.get(i).get(filters[1]).toString();
                    s2[i] = str1;
                    String str2 = list.get(i).get(filters[2]).toString();
                    s3[i] = str2;
                    String str3 = list.get(i).get(filters[3]).toString();
                    s4[i] = str3;
                    String str4 = list.get(i).get(filters[4]).toString();
                    s5[i] = str4;
                    String str5 = list.get(i).get(filters[5]).toString();
                    s6[i] = str5;
                    String str6 = list.get(i).get(filters[5]).toString();
                    s7[i] = str6;
                }
                result.add(s1);
                result.add(s2);
                result.add(s3);
                result.add(s4);
                result.add(s5);
                result.add(s6);
                result.add(s7);
                break;
            case 8:
                for (int i = 0; i < list.size(); i++) {
                    String str = list.get(i).get(filters[0]).toString();
                    s1[i] = str;
                    String str1 = list.get(i).get(filters[1]).toString();
                    s2[i] = str1;
                    String str2 = list.get(i).get(filters[2]).toString();
                    s3[i] = str2;
                    String str3 = list.get(i).get(filters[3]).toString();
                    s4[i] = str3;
                    String str4 = list.get(i).get(filters[4]).toString();
                    s5[i] = str4;
                    String str5 = list.get(i).get(filters[5]).toString();
                    s6[i] = str5;
                    String str6 = list.get(i).get(filters[5]).toString();
                    s7[i] = str6;
                    String str7 = list.get(i).get(filters[5]).toString();
                    s8[i] = str7;
                }
                result.add(s1);
                result.add(s2);
                result.add(s3);
                result.add(s4);
                result.add(s5);
                result.add(s6);
                result.add(s7);
                result.add(s8);
                break;
        }

        return result;
    }
}