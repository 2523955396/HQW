package com.huanqi.hqw.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.huanqi.hqw.Interface.orientation;
import com.huanqi.hqw.Interface.permission;

public class HQWActivity extends AppCompatActivity {
    Toast toast;
    int maxpermission = 0;//授权了多少个
    boolean isfirstfailure=false;
    public  permission permission;
    public orientation orientation;

    public void setToast(String text){
        if (toast!=null){
            toast.cancel();
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                toast= Toast.makeText(HQWActivity.this,text,Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
    public void cancelToast(){
        if (toast!=null){
            toast.cancel();
        }
    }

    public void GotoActivity(Class<?> activity,boolean isflash){
        startActivity(new Intent(this,activity));
        if (isflash){
            finish();
        }
    }

    public  void HQWOrientation(orientation orientation){
        this.orientation=orientation;
    }

    public String HQWgetOrientation(){
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            return "竖屏";
        }else {
            return "横屏";
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
           orientation.horizontal();
        }else{
            orientation.vertical();
        }
    }


    public void HQWPermissions(String[] permissions,com.huanqi.hqw.Interface.permission permission) {
        requestPermissions(permissions, 777);
        this.permission = permission;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 777) {
            for (int i = 0; i < permissions.length; i++) {
                if (ContextCompat.checkSelfPermission(this, permissions[i]) == PackageManager.PERMISSION_GRANTED) {
                    maxpermission++;
                    if (maxpermission == permissions.length) {
                        permission.onsucceed();
                    }
                } else {
                    if (isfirstfailure==false){
                        permission.onfirstfailure();
                        isfirstfailure=true;
                    }else {
                        permission.onfailure();
                    }
                }
            }

        }
    }

//    public void HQWsetScreen(boolean islighting){
//        if (islighting){
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);//屏幕常亮
//        }else {
//            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);//关闭屏幕常亮
//        }
//    }
}
