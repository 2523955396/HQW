package com.huanqi.hqw;

import android.Manifest;
import android.os.Bundle;

import com.huanqi.hqw.Interface.HQWPermission;
import com.huanqi.hqw.Utils.HQWLogUtil;
import com.huanqi.hqw.activity.HQWActivity;

import retrofit2.Retrofit;

public class MainActivity extends HQWActivity {

    String[] PERMISSIONS = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initModel(new MainModel());

        HQWPermissions(PERMISSIONS, new HQWPermission() {
            @Override
            public void onsucceed() {
                HQWLogUtil.logi("授权了","授权了");
            }
            @Override
            public void onfailure() {

            }
        });
    }
}