package com.huanqi.hqw;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import com.huanqi.android.Interface.HQWPermission;
import com.huanqi.android.activity.HQWActivity;

import com.huanqi.http.upload.HQWUploadManger;

import org.json.JSONArray;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class MainActivity extends HQWActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


}