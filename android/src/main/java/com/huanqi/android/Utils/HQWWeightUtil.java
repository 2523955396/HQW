package com.huanqi.android.Utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;

import androidx.fragment.app.Fragment;

public class HQWWeightUtil {
    public static int dptopx(Context context, float dpValue) {//DP转换为PX
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int pxtodp(Context context, float pxValue) {//PX转换为DP
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static void hideSystemNavigationBar(Activity activity) {//隐藏标题栏
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) {
            View view = activity.getWindow().getDecorView();
            view.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            View decorView = activity.getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    public static void showSystemNavigationBar(Activity activity) {//显示标题栏
        if (Build.VERSION.SDK_INT >= 19) {
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        View decorView = activity.getWindow().getDecorView();
        decorView.setSystemUiVisibility(0);
    }

    public static int getPhoneWidth(Activity activity) {
        return activity.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getPhoneHigh(Activity activity) {
        return activity.getResources().getDisplayMetrics().heightPixels;
    }

    public static int getPhoneWidth(Fragment fragment) {
        return fragment.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getPhoneHigh(Fragment fragment) {
        return fragment.getResources().getDisplayMetrics().heightPixels;
    }

    public static int getPhoneWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getPhoneHigh(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }



}