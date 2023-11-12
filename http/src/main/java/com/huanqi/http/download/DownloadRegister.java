package com.huanqi.http.download;

import okhttp3.Call;

/**
 *下载寄存器
 */
public class DownloadRegister {
   private Call call = null;
    private long fileCountSize=0;//本地文件大小
    private long httpFileCountSize = 0;//网络文件总长度

    public Call getCall() {
        return call;
    }

    public void setCall(Call call) {
        this.call = call;
    }

    public long getFileCountSize() {
        return fileCountSize;
    }

    public void setFileCountSize(long fileCountSize) {
        this.fileCountSize = fileCountSize;
    }

    public long getHttpFileCountSize() {
        return httpFileCountSize;
    }

    public void setHttpFileCountSize(long httpFileCountSize) {
        this.httpFileCountSize = httpFileCountSize;
    }
}
