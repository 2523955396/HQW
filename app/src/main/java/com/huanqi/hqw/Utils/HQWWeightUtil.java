package com.huanqi.hqw.Utils;

import android.content.Context;

public class HQWWeightUtil {
    public static int dptopx(Context context, float dpValue) {//DP转换为PX
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int pxtodp(Context context, float pxValue) {//PX转换为DP
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
