package com.huanqi.hqw.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.huanqi.hqw.Interface.permission;

public class HQWActivity extends AppCompatActivity {
    Toast toast;
    int maxpermission = 0;//授权了多少个
    boolean isfirstfailure=false;
    public  permission permission;

    public void setToast(String text){
        if (toast!=null){
            toast.cancel();
        }
        toast= Toast.makeText(this,text,Toast.LENGTH_SHORT);
        toast.show();
    }

    public void GotoActivity(Class<?> activity){
        startActivity(new Intent(this,activity));
        finish();
    }
    public void HQWpermissions(String[] permissions,com.huanqi.hqw.Interface.permission permission) {
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
}
