package com.huanqi.android.Utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import androidx.core.content.FileProvider;

import java.io.File;

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

    public static void InstallApk(Context context, File file) {
        if (context == null) {
            return;
        }
        String authority = context.getApplicationContext().getPackageName() + ".fileProvider";
        Uri apkUri = FileProvider.getUriForFile(context, authority, file);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        if (Build.VERSION.SDK_INT >= 24) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        }
        context.startActivity(intent);
    }
}
