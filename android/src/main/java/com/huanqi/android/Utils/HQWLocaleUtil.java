package com.huanqi.android.Utils;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;

import java.util.Locale;

/**
 *HQW语言切换
 @用法
 protected void attachBaseContext(Context newBase) {
 super.attachBaseContext(LocaleUtil.init(newBase));
 }
 * */
public class HQWLocaleUtil {
    public static void setLanguage(Context context, String language, String country) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = new Locale(language, country);
        resources.updateConfiguration(configuration, metrics);
    }


    /**
     * 初始化语言 语言包切换
     *
     * @param context 上下文
     * @param locale  语言包language,country
     * @例子 new Locale("en","US")
     * @例子 new Locale("vi","VN")
     * @例子 new Locale("km","KH")
     */
    public static Context init(Context context, Locale locale) {
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return context.createConfigurationContext(configuration);
        } else {
            DisplayMetrics metrics = resources.getDisplayMetrics();
            resources.updateConfiguration(configuration, metrics);
            return context;
        }
    }
}
