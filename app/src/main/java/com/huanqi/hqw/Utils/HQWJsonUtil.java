package com.huanqi.hqw.Utils;

import org.json.JSONObject;

public class HQWJsonUtil {

    public String JsontoString(String jsontext,String key){
        String textout="";
        try {
            textout=new JSONObject(jsontext).getString(key);
        }catch (Exception v){
        }
        return textout;
    }
}
