package com.huanqi.android.Utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


/**
 * Json处理类
 * By 焕奇灵动
 */
public class HQWJsonUtil {

    /**
     * JsonObject快速数据获取
     * @param data 数据
     * @param key Key
     */
    public static String ObjectValue(String data, String key) {
        String value = "";
        try {
            value = new JSONObject(data).getString(key);
        } catch (Exception v) {
        }
        return value;
    }

    /**
     * JsonArray快速数据获取
     * @param data 数据
     * @param index 获取第几个
     */
    public static String ArrayValue(String data, int index) {
        String value = "";
        try {
            value = new JSONArray(data).getString(index);
        } catch (Exception v) {
        }
        return value;
    }


    /**
     * json转换Object对象
     * @param data 数据
     * @param obj  数据结构
     */
    public static <T> T fromJson(String data, Class obj) {
        T object = (T) new Gson().fromJson(data, obj);
        return object;
    }


    /**
     * json转换List<?>
     * @param data 数据
     * @param obj  数据结构
     */
    public static <T> List<T> fromJsonList(String data, Class obj) {
        Type listType = com.google.gson.internal.$Gson$Types.newParameterizedTypeWithOwner(null, ArrayList.class, obj);
        return new Gson().fromJson(data, listType);
    }


}
