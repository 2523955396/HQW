package com.huanqi.http.download;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class DownloadConfig {

    public static DownloadConfig getInstance() {
        return new DownloadConfig();
    }


    /**
     * 普通下载
     */
    public Call getCall(String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = Client().newCall(request);
        return call;
    }

    /**
     * 断点续传
     */
    public Call getCall(String url, File file, long httpFileCountSize) {
        Request request = new Request.Builder()
                .addHeader("RANGE", "bytes=" + (file.exists() ? file.length() : 0) + "-" + httpFileCountSize)
                .url(url)
                .build();
        Call call = Client().newCall(request);
        return call;
    }

    private OkHttpClient Client() {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.newBuilder().connectTimeout(5, TimeUnit.MINUTES);
        okHttpClient.newBuilder().readTimeout(5, TimeUnit.MINUTES);
        okHttpClient.newBuilder().writeTimeout(5, TimeUnit.MINUTES);
        okHttpClient.newBuilder().callTimeout(5, TimeUnit.MINUTES);
        return okHttpClient;
    }

}
