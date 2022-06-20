package com.huanqi.hqw.Utils;

import android.app.Activity;
import android.content.Context;

public class HQWUtil {

    public static int getVersionCode(Context context) {
        int versioncode = 0;
        try {
            versioncode = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (Exception v) {
        }
        return versioncode;
    }

    public static String getVersionName(Context context) {
        String versionname = "";
        try {
            versionname = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Exception v) {
        }
        return versionname;
    }
}
