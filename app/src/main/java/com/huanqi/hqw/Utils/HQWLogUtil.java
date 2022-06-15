package com.huanqi.hqw.Utils;

import android.util.Log;

/**
 * Log处理类 By焕奇灵动
 */
public class HQWLogUtil {
    public static void logi(String name,String text){
        Log.i(name,text);
    }
    public static void logv(String name,String text){
        Log.v(name,text);
    }
    public static void logd(String name,String text){
        Log.d(name,text);
    }
    public static void loge(String name,String text){
        Log.e(name,text);
    }
    public static void logw(String name,String text){
        Log.w(name,text);
    }
}
