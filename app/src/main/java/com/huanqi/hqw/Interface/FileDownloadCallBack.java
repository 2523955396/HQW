package com.huanqi.hqw.Interface;


import java.io.File;
import java.io.IOException;
import java.util.Timer;

import okhttp3.Call;
import okhttp3.Response;
import okio.BufferedSink;

public interface FileDownloadCallBack {
    void progress(long ProgressSize,long MaxSize,String HQWProgressSize,String HQWMaxSize);
    void onsuccess(File file,int state);
    void onfailed();
}
