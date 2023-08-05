package com.huanqi.hqw;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import com.huanqi.android.Interface.HQWPermission;
import com.huanqi.android.activity.HQWActivity;
import com.huanqi.hqw.databinding.ActivityMainBinding;
import com.huanqi.http.upload.HQWUploadManger;

import org.json.JSONArray;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class MainActivity extends HQWActivity implements View.OnClickListener {

    String[] Premission = new String[]{
//            Manifest.permission.WRITE_EXTERNAL_STORAGE,
//            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_MEDIA_IMAGES,
            Manifest.permission.READ_MEDIA_AUDIO,
            Manifest.permission.READ_MEDIA_VIDEO,
            Manifest.permission.QUERY_ALL_PACKAGES
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HQWPermissions(Premission, new HQWPermission() {
            @Override
            public void onsucceed() {

            }

            @Override
            public void onfailure() {
                HQWPermissions(Premission, new HQWPermission() {
                    @Override
                    public void onsucceed() {

                    }

                    @Override
                    public void onfailure() {

                    }
                });
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pb_ssssss:
                break;
        }
    }
}