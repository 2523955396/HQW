package com.huanqi.android.Utils;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

public class HQWJsonUtil {

    public static String ObjectGetValue(String message,String key){
        String textout="";
        try {
            textout=new JSONObject(message).getString(key);
        }catch (Exception v){
        }
        return textout;
    }

    public static String ArrayGetValue(String message,int key){
        String textout="";
        try {
            textout=new JSONArray(message).getString(key);
        }catch (Exception v){
        }
        return textout;
    }


}
