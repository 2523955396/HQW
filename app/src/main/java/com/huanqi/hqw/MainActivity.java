package com.huanqi.hqw;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import com.huanqi.hqw.Interface.File_download;
import com.huanqi.hqw.Interface.permission;
import com.huanqi.hqw.activity.HQWActivity;
import com.huanqi.hqw.bean.DownloadFileBean;
import com.huanqi.hqw.file.HQWFile;
import com.huanqi.hqw.http.HQWHttp;

import java.io.File;

public class MainActivity extends HQWActivity {
    String url="https://alpan.51huanqi.cn/d/影视站点/电影/电影1/蜘蛛侠英雄无归预告1.mp4";
   String[] PERMISSIONS=new String[]{
           Manifest.permission.WRITE_EXTERNAL_STORAGE,
           Manifest.permission.READ_EXTERNAL_STORAGE,
           Manifest.permission.ACCESS_COARSE_LOCATION
   };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HQWpermissions(PERMISSIONS,new permission() {
            @Override
            public void onsucceed() {
                HQWHttp.DownloadFile(url, HQWFile.SDFile("/视频", "失控玩家"), new File_download() {
                    @Override
                    public void progress(long ProgressSize, long MaxSize, String HQWProgressSize, String HQWMaxSize) {

                    }

                    @Override
                    public void onsuccess(File file, String state) {
                        setToast(state);
                    }

                    @Override
                    public void onfailed() {

                    }

                    @Override
                    public void console(DownloadFileBean downloadFileBean) {

                    }
                });
            }
            @Override
            public void onfirstfailure() {

            }
            @Override
            public void onfailure() {

            }
        });

    }
}