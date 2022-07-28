package com.huanqi.hqw.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.huanqi.hqw.Interface.orientation;
import com.huanqi.hqw.Interface.permission;
import com.huanqi.hqw.Utils.HQWLogUtil;

import java.util.Map;

public class HQWActivity extends AppCompatActivity {
    Toast toast;
    int maxpermission = 0;//权限授权了多少个
    public permission permission;
    public orientation orientation;

    public void setToast(String text) {
        if (toast != null) {
            toast.cancel();
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                toast = Toast.makeText(HQWActivity.this, text, Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    public void cancelToast() {
        if (toast != null) {
            toast.cancel();
        }
    }

    public void GotoActivity(Class<?> activity, boolean isflash) {
        startActivity(new Intent(this, activity));
        if (isflash) {
            finish();
        }
    }

    public void HQWOrientation(orientation orientation) {
        this.orientation = orientation;
    }


    public void HQWsetOrientation(String name) {
        if (name.equals("横屏")) {//设置横屏
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else if (name.equals("竖屏")) {//设置竖屏
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else if (name.equals("首选")) {//用户当前的首选方向
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER);
        } else if (name.equals("继承")) {//继承Activity堆栈中当前Activity下面的那个Activity的方向
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_BEHIND);
        } else if (name.equals("自动")) {//由物理感应器决定显示方向，它取决于用户如何持有设备，当 设备 被旋转时方向会随之变化——在横屏与竖屏之间
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
        } else if (name.equals("忽略")) {//忽略物理感应器——即显示方向与物理感应器无关，不管用户如何旋转设备显示方向都不会随着改变("unspecified"设置除外)
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        } else if (name.equals("默认")) {//未指定，此为默认值，由Android系统自己选择适当的方向，选择策略视具体设备的配置情况而定，因此不同的设备会有不同的方向选择
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    public String HQWgetOrientation() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            return "竖屏";
        } else {
            return "横屏";
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (orientation != null) {
            if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                orientation.horizontal();
            } else {
                orientation.vertical();
            }
        }

    }

    ActivityResultLauncher<String[]> resultLauncherpermission=registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), new ActivityResultCallback<Map<String, Boolean>>() {
        @Override
        public void onActivityResult(Map<String, Boolean> result) {
            boolean ISNICE=true;
            for (Map.Entry<String,Boolean> maps:result.entrySet()){
                if (maps.getValue()==false){
                    ISNICE=false;
                }
            }
            if (ISNICE){
                permission.onsucceed();
            }else {
                permission.onfailure();
            }
        }
    });


    public void HQWPermissions(String[] permissions, permission permission) {
//        requestPermissions(permissions, 777);
        this.permission = permission;
        resultLauncherpermission.launch(permissions);
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == 777) {
//            for (int i = 0; i < permissions.length; i++) {
//                if (ContextCompat.checkSelfPermission(this, permissions[i]) == PackageManager.PERMISSION_GRANTED) {
//                    maxpermission++;
//                    if (maxpermission == permissions.length) {
//                        permission.onsucceed();
//                    }
//                } else {
//                    permission.onfailure();
//                    maxpermission = 0;
//                    return;
//                }
//            }
//
//        }
//    }

    public void HQWsetScreen(boolean islighting) {
        if (islighting) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);//屏幕常亮
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);//关闭屏幕常亮
        }
    }

    /**
     * 设置使用HQW状态栏
     * 列：Color.WHITE
     * 列：View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void HQWsetStatusBar(int color, int textcolor) {
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(color);//状态栏颜色
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.getDecorView().setSystemUiVisibility(textcolor);//状态栏字体颜色
        }
    }

    public void initView(){

    }
    public void initData(){

    }

}
