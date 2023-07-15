package com.huanqi.android.Utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;

/**
 * 时间处理类 by焕奇灵动
 */
public class HQWTimeUtil {

    //--------------------------视频时间转换------------------------------//
    public static String ValueForTime(int timeMs) {
        if (timeMs <= 0 || timeMs >= 24 * 60 * 60 * 1000) {
            return "00:00";
        }
        int totalSeconds = timeMs / 1000;
        int seconds = totalSeconds % 60;
        int minutes = (totalSeconds / 60) % 60;
        int hours = totalSeconds / 3600;
        StringBuilder stringBuilder = new StringBuilder();
        Formatter mFormatter = new Formatter(stringBuilder, Locale.getDefault());
        if (hours > 0) {
            return mFormatter.format("%d:%02d:%02d", hours, minutes, seconds).toString();
        } else {
            return mFormatter.format("%02d:%02d", minutes, seconds).toString();
        }
    }



    //--------------------------年月份处理类------------------------------//

    public static final int TYPE_Date= -1;//年月份
    public static final int TYPE_Year = 0;//年份
    public static final int TYPE_Month = 1;//月份
    public static final int TYPE_Day = 2;//日


    /**
     * 根据属性获取之前的时间或之后的时间
     * @param TimeType 属性
     * @param month 根据属性获取之前或之后的时间
     * 列: TYPE_Month  -3   如:当前时间2023-06-16  获取的为前3个月的月份 返回应该为3
     */
    public static String getCurrentTime(int TimeType, int month) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        switch (TimeType) {
            case TYPE_Year:
                calendar.add(Calendar.YEAR, month);
                break;
            case TYPE_Month:
                calendar.add(Calendar.MONTH, month);
                break;
            case TYPE_Day:
                calendar.add(Calendar.DAY_OF_MONTH, month);
                break;
            default:
                calendar.add(Calendar.DATE, month);
                break;
        }

        date = calendar.getTime();
        switch (TimeType) {
            case TYPE_Year:
                return new SimpleDateFormat("yyyy").format(date);
            case TYPE_Month:
                return new SimpleDateFormat("MM").format(date);
            case TYPE_Day:
                return new SimpleDateFormat("dd").format(date);
            default:
                return new SimpleDateFormat("yyyyMMdd").format(date);
        }
    }
}
