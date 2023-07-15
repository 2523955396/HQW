package com.huanqi.http.download;


import java.io.File;
import java.io.IOException;

public interface HQWDownloadListener {
    void onDownloading(File file,long ProgressSize,long MaxSize,String HQWProgressSize,String HQWMaxSize);
    void onSucceed(File file);
    void onStop(File file);
    void onFailed(File file, Exception e);
}
