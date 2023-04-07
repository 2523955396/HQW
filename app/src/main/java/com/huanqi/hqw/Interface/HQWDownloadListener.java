package com.huanqi.hqw.Interface;


import java.io.File;

public interface HQWDownloadListener {
    void onDownloading(File file,long ProgressSize,long MaxSize,String HQWProgressSize,String HQWMaxSize);
    void onSucceed(File file);
    void onStop(File file);
    void onFailed(File file);
}
