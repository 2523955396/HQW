package com.huanqi.hqw.Interface;

import com.huanqi.hqw.bean.DownloadFileBean;

import java.io.File;
import java.io.IOException;
import java.util.Timer;

import okhttp3.Call;
import okhttp3.Response;
import okio.BufferedSink;

public interface File_download {
    void progress(long ProgressSize,long MaxSize,String HQWProgressSize,String HQWMaxSize);
    void onsuccess(File file,String state);
    void onfailed();
    void console(DownloadFileBean downloadFileBean);
}
