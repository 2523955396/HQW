package com.huanqi.android.Utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.List;

/**
 * 获取手机其它app信息 By焕奇灵动
 */
public class HQWAndroidUtil {
    public static final String WebView = "com.google.android.webview";
    public static final String GooglePlay = "com.android.vending";

    public static final int WebViewMinVersionCode = 573514731;//版本号 114.0.5735.147

    public static final String GooglePlayWebVeiwURL = "https://play.google.com/store/apps/details?id=com.google.android.webview";
    public static final String GooglePlayWebVeiwMarket = "market://details?id=com.google.android.webview";

    /**
     * 检查应用app是否存在
     *
     * @param context     上下文
     * @param packageName 包名
     */
    public static boolean isAppExist(Context context, String packageName) {
        PackageManager packageManager = context.getPackageManager();
        List pinfo = packageManager.getInstalledPackages(0);
        for (int i = 0; i < pinfo.size(); i++) {
            if (((PackageInfo) pinfo.get(i)).packageName.equals(packageName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取App的版本Code 默认为0
     *
     * @param context     上下文
     * @param packageName 包名
     */
    public static int getAppVersionCode(Context context, String packageName) {
        PackageManager packageManager = context.getPackageManager();
        List pinfo = packageManager.getInstalledPackages(0);
        for (int i = 0; i < pinfo.size(); i++) {
            if (((PackageInfo) pinfo.get(i)).packageName.equals(packageName)) {
                return ((PackageInfo) pinfo.get(i)).versionCode;
            }
        }
        return 0;
    }
}
