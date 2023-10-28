package com.huanqi.hqw;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huanqi.android.Interface.HQWPermission;
import com.huanqi.android.Utils.HQWJsonUtil;
import com.huanqi.android.Utils.HQWLocaleUtil;
import com.huanqi.android.Utils.HQWLogUtil;
import com.huanqi.android.Utils.HQWPermissionUtil;
import com.huanqi.android.activity.HQWActivity;


import org.json.JSONArray;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class MainActivity extends HQWActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToast("对的");

    }
}