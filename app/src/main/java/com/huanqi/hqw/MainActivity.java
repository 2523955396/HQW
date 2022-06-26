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
import com.huanqi.hqw.dialog.HQWDialog;
import com.huanqi.hqw.http.HQWHttp;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends HQWActivity implements View.OnClickListener {
    String[] PERMISSIONS = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };
    ProgressBar progress;
    HQWHttp hqwHttp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HQWPermissions(PERMISSIONS, new permission() {
            @Override
            public void onsucceed() {

                hqwHttp=  new HQWHttp();
                Button xiazai=findViewById(R.id.xiazai);
                Button zanting=findViewById(R.id.zanting);
                progress=findViewById(R.id.progress);
                xiazai.setOnClickListener(MainActivity.this);
                zanting.setOnClickListener(MainActivity.this);
            }

            @Override
            public void onfailure() {

            }
        });
    }
    String url = "https://alpan.51huanqi.cn/d/影视站点/动漫/动漫2/龙珠超/1.mkv";
    @Override
    public void onClick(View v) {
        switch (v.getId()){
           case  R.id.xiazai:
              if ( hqwHttp.isDownloading()){
                  setToast("正在下载，请勿重复点击");
              }else {
                  hqwHttp.DownloadFileResume(url, HQWFileUtil.SDFile("/对对对", "1.mkv"),1000, new FileDownloadCallBack() {
                      @Override
                      public void progress(long ProgressSize, long MaxSize, String HQWProgressSize, String HQWMaxSize) {
                          progress.setProgress((int)ProgressSize);
                          progress.setMax((int)MaxSize);
                      }

                      @Override
                      public void onsuccess(File file, int state) {
                          if (state==HQWHttp.DOWNLOADING){
                              setToast("下载中");
                          }else if (state==HQWHttp.COMPLETE){
                              setToast("下载完成");
                          }
                      }

                      @Override
                      public void onfailed() {

                      }
                  });
              }


            break;
            case  R.id.zanting:
                hqwHttp.cancelResume();
                setToast("下载停止");
                break;
        }
    }
}