package com.huanqi.hqw;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import com.huanqi.hqw.Interface.permission;
import com.huanqi.hqw.activity.HQWActivity;

public class MainActivity extends HQWActivity {
   String[] PERMISSIONS=new String[]{
           Manifest.permission.WRITE_EXTERNAL_STORAGE,
           Manifest.permission.READ_EXTERNAL_STORAGE,
           Manifest.permission.ACCESS_COARSE_LOCATION
   };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HQWpermissions(PERMISSIONS,new permission() {
            @Override
            public void onsucceed() {
            }
            @Override
            public void onfirstfailure() {

            }
            @Override
            public void onfailure() {

            }
        });

    }
}