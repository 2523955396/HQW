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

import com.huanqi.hqw.Interface.FileDownloadCallBack;
import com.huanqi.hqw.Interface.orientation;
import com.huanqi.hqw.Interface.permission;
import com.huanqi.hqw.Utils.HQWDeviceInfoUtil;
import com.huanqi.hqw.Utils.HQWFileUtil;
import com.huanqi.hqw.Utils.HQWImageUtil;
import com.huanqi.hqw.Utils.HQWLogUtil;
import com.huanqi.hqw.Utils.HQWMd5Util;
import com.huanqi.hqw.Utils.HQWSDCardUtil;
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

    String url="https://alpan.51huanqi.cn/d/%E5%BD%B1%E8%A7%86%E7%AB%99%E7%82%B9/%E7%94%B5%E5%BD%B1/%E5%93%A5%E6%96%AF%E6%8B%89/%E5%93%A5%E6%96%AF%E6%8B%89%E5%A4%A7%E6%88%98%E9%87%91%E5%88%9A-2021_BD%E4%B8%AD%E8%8B%B1%E5%8F%8C%E5%AD%97V2%E5%AD%97%E5%B9%95%E7%B2%BE%E8%AF%91%E7%89%88.mp4";
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
    HQWHttp hqwHttp;
    @Override
    public void initView() {
        super.initView();
        Button button = findViewById(R.id.xiazai);
        Button zanting = findViewById(R.id.zanting);
        button.setOnClickListener(v -> {
            hqwHttp = HQWHttp.FileResumeDownload(url, HQWFileUtil.SDFile("/对对对", "1.mp4"), 500, new FileDownloadCallBack() {
                @Override
                public void progress(long ProgressSize, long MaxSize, String HQWProgressSize, String HQWMaxSize) {

                }

                @Override
                public void onsuccess(File file, int state) {

                }

                @Override
                public void onfailed(Call call, IOException e) {

                }
            });
        });
        zanting.setOnClickListener(v -> {
            hqwHttp.cancelResume();
        });
    }
}