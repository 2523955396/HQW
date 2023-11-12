package com.huanqi.http.download;


import java.io.File;

public interface DownloadListener {
    void onDownloading(File file,long progress,long max,String HQWprogress,String HQWmax);
    void onSucceed(File file);
    void onStop(File file);
    void onFailed(File file, Exception e);
}
