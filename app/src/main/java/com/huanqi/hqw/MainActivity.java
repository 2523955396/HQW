package com.huanqi.hqw;

import android.os.Bundle;
import android.widget.ProgressBar;


import androidx.annotation.Nullable;

import com.huanqi.android.Utils.HQWByteUtil;
import com.huanqi.android.Utils.HQWFileUtil;
import com.huanqi.android.Utils.HQWLogUtil;
import com.huanqi.android.activity.HQWActivity;
import com.huanqi.http.download.DownloadListener;
import com.huanqi.http.download.HQWDownloadManager;
import com.huanqi.http.download.HttpMachine;


import java.io.File;


public class MainActivity extends HQWActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}