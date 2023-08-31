package com.huanqi.hqw;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import com.google.gson.Gson;
import com.huanqi.android.Interface.HQWPermission;
import com.huanqi.android.Utils.HQWJsonUtil;
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

    String aaaa="{\n" +
            "  \"id\": 1001,\n" +
            "  \"type\": \"site\",\n" +
            "  \"name\": \"Runoob\",\n" +
            "  \"description\": \"https://www.runoob.com\",\n" +
            "  \"price\": 2.55,\n" +
            "  \"available\": {\n" +
            "    store: 42,\n" +
            "    likes: 600\n" +
            "  },\n" +
            "  \"toppings\": [\n" +
            "    { \"id\": 5001, \"type\": \"A\" },\n" +
            "    { \"id\": 5002, \"type\": \"B\" },\n" +
            "    { \"id\": 5005, \"type\": \"C\" },\n" +
            "    { \"id\": 5003, \"type\": \"D\" },\n" +
            "    { \"id\": 5004, \"type\": \"E\" }\n" +
            "  ],\n" +
            "  \"uuids\": [\n" +
            "    \"826b23ce-2669-4122-981f-3e2e4429159d\",\n" +
            "    \"e32111a0-6a87-49ab-b58f-a01bf8d28ba0\",\n" +
            "    \"c055a894-698e-41c0-b85f-7510a7351d9d\",\n" +
            "  ],\n" +
            "}\n";

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
    }


}