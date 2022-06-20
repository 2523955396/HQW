package com.huanqi.hqw.http;

import androidx.annotation.NonNull;

import com.huanqi.hqw.Interface.FileDownloadCallBack;
import com.huanqi.hqw.bean.DownloadFileBean;
import com.huanqi.hqw.Utils.HQWFileUtil;

import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.BufferedSink;
import okio.Okio;

public class HQWHttp {
    public static void DownloadFile(String url, File file, FileDownloadCallBack callBack) {
        OkHttpClient okHttpClient = new OkHttpClient();
//        okHttpClient.newBuilder().readTimeout(1, TimeUnit.MINUTES);
//        okHttpClient.newBuilder().writeTimeout(1, TimeUnit.MINUTES);
//        okHttpClient.newBuilder().connectTimeout(5, TimeUnit.MINUTES);
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callBack.onfailed();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                ResponseBody responseBody = response.body();
                BufferedSink sink = Okio.buffer(Okio.sink(file));
                Timer timer = new Timer();
                DownloadFileBean downloadFileBean = new DownloadFileBean();
                downloadFileBean.setCall(call);
                downloadFileBean.setSink(sink);
                downloadFileBean.setTimer(timer);
                downloadFileBean.setResponse(response);
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        callBack.progress(file.length(), responseBody.contentLength(), HQWFileUtil.getPrintSize(file.length()), HQWFileUtil.getPrintSize(responseBody.contentLength()));
                        callBack.onsuccess(file, "下载中");

                    }
                }, 0, 1000);
                callBack.console(downloadFileBean);
                sink.writeAll(responseBody.source());
                //下载完成执行
                downloadFileBean.cancel();
                callBack.onsuccess(file, "下载完成");
            }
        });
    }

    public static void HttpDownloadFile(Call call, String url, File file, FileDownloadCallBack callBack) {
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callBack.onfailed();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                ResponseBody responseBody = response.body();
                BufferedSink sink = Okio.buffer(Okio.sink(file));
                Timer timer = new Timer();
                DownloadFileBean downloadFileBean = new DownloadFileBean();
                downloadFileBean.setCall(call);
                downloadFileBean.setSink(sink);
                downloadFileBean.setTimer(timer);
                downloadFileBean.setResponse(response);
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        callBack.progress(file.length(), responseBody.contentLength(), HQWFileUtil.getPrintSize(file.length()), HQWFileUtil.getPrintSize(responseBody.contentLength()));
                        callBack.onsuccess(file, "下载中");

                    }
                }, 0, 1000);
                callBack.console(downloadFileBean);
                sink.writeAll(responseBody.source());
                //下载完成执行
                downloadFileBean.cancel();
                callBack.onsuccess(file, "下载完成");
            }
        });
    }
}
