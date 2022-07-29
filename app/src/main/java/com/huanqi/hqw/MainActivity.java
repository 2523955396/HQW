package com.huanqi.hqw;

import android.Manifest;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.huanqi.hqw.Interface.FileDownloadCallBack;
import com.huanqi.hqw.Interface.orientation;
import com.huanqi.hqw.Interface.permission;
import com.huanqi.hqw.Utils.HQWDeviceInfoUtil;
import com.huanqi.hqw.Utils.HQWFileUtil;
import com.huanqi.hqw.Utils.HQWImageUtil;
import com.huanqi.hqw.Utils.HQWLogUtil;
import com.huanqi.hqw.Utils.HQWMd5Util;
import com.huanqi.hqw.Utils.HQWSDCardUtil;
import com.huanqi.hqw.Utils.HQWWeightUtil;
import com.huanqi.hqw.activity.HQWActivity;
import com.huanqi.hqw.animation.HQWAnimation;
import com.huanqi.hqw.dialog.HQWDialog;
import com.huanqi.hqw.http.HQWHttp;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;

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
        HQWPermissions(PERMISSIONS, new permission() {
            @Override
            public void onsucceed() {
                initView();
            }
            @Override
            public void onfailure() {

            }
        });
    }
    @Override
    public void initView() {
        super.initView();
        Button button = findViewById(R.id.xiazai);
        Button zanting = findViewById(R.id.zanting);
        HQWWeightUtil.showSystemNavigationBar(this);
    }

    @Override
    public void initData() {
        super.initData();
    }
}