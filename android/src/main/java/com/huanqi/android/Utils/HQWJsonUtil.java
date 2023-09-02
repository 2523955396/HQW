package com.huanqi.android.Utils;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;


/**
 * Json处理类
 *
 * @Value Object object=new Gson().fromJson("",Object.class);
 * @Value List<?> lists=new Gson().fromJson("",new TypeToken<List<?>>() {}.getType());
 */
public class HQWJsonUtil {

    public static String ObjectGetValue(String message, String key) {
        String textout = "";
        try {
            textout = new JSONObject(message).getString(key);
        } catch (Exception v) {
        }
        return textout;
    }

    public static String ArrayGetValue(String message, int key) {
        String textout = "";
        try {
            textout = new JSONArray(message).getString(key);
        } catch (Exception v) {
        }
        return textout;
    }


}
