package com.huanqi.hqw;

import android.Manifest;
import android.os.Bundle;

import com.huanqi.hqw.Interface.orientation;
import com.huanqi.hqw.Interface.permission;
import com.huanqi.hqw.Utils.HQWLogUtil;
import com.huanqi.hqw.activity.HQWActivity;

import java.util.Timer;
import java.util.TimerTask;

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
        HQWPermissions(PERMISSIONS,new permission() {
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