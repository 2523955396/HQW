package com.huanqi.android.Utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.ViewTreeObserver;
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

    public static int getPhoneStatusBarHeight(Context context) {
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return context.getResources().getDimensionPixelSize(resourceId);
        }
        return 0;
    }


    public static int getPhoneWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getPhoneHeigh(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }


    public static void drawMonitor(View view,DrawCallback drawCallback){
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                drawCallback.onSuccess();
                view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    public interface DrawCallback{
        void onSuccess();
    }


}
