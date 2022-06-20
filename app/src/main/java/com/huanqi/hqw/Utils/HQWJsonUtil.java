package com.huanqi.hqw.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

public class HQWJsonUtil {

    public static String ObjectGetValue(String jsontext,String key){
        String textout="";
        try {
            textout=new JSONObject(jsontext).getString(key);
        }catch (Exception v){
        }
        return textout;
    }

    public static String ArrayGetValue(String jsontext,int key){
        String textout="";
        try {
            textout=new JSONArray(jsontext).getString(key);
        }catch (Exception v){
        }
        return textout;
    }
}
