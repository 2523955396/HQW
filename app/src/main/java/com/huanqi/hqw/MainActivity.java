package com.huanqi.hqw;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import com.huanqi.android.Interface.HQWPermission;
import com.huanqi.android.Utils.HQWLocaleUtil;
import com.huanqi.android.Utils.HQWLogUtil;
import com.huanqi.android.Utils.HQWPermissionUtil;
import com.huanqi.android.activity.HQWActivity;

import com.huanqi.http.upload.HQWUploadManger;

import org.json.JSONArray;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class MainActivity extends HQWActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HQWPermissions(HQWPermissionUtil.PermissionDispose(new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.READ_MEDIA_IMAGES,
                Manifest.permission.READ_MEDIA_VIDEO,
                Manifest.permission.READ_MEDIA_AUDIO
        }), new HQWPermission() {
            @Override
            public void onsucceed() {
                HQWLogUtil.logi("不错不错","成功");
            }

            @Override
            public void onfailure() {
                HQWLogUtil.logi("不错不错","失败");
            }
        });
        HQWLocaleUtil.init(this,new Locale("en","US"));
    }


}