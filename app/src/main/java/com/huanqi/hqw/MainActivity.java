package com.huanqi.hqw;

import android.Manifest;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.huanqi.android.Interface.HQWPermission;
import com.huanqi.android.activity.HQWActivity;
import com.huanqi.hqw.databinding.ActivityMainBinding;
import com.huanqi.http.upload.HQWUploadManger;

import java.io.File;
import java.io.IOException;
import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Retrofit;

public class MainActivity extends HQWActivity {


    String[] PERMISSIONS = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_MEDIA_VIDEO,
            Manifest.permission.READ_MEDIA_AUDIO,
            Manifest.permission.READ_MEDIA_IMAGES
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initModel(new MainModel());

        HQWPermissions(PERMISSIONS, new HQWPermission() {
            @Override
            public void onsucceed() {
            }
            @Override
            public void onfailure() {

            }
        });
    }
}