package com.huanqi.hqw.http;

import android.util.Log;

import androidx.annotation.NonNull;

import com.huanqi.hqw.Interface.File_download;
import com.huanqi.hqw.bean.DownloadFileBean;
import com.huanqi.hqw.file.HQWFile;

import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.BufferedSink;
import okio.Okio;

public class HQWHttp {
    public static void DownloadFile(String url, File file, File_download file_download){
        OkHttpClient okHttpClient=new OkHttpClient();
//        okHttpClient.newBuilder().readTimeout(1, TimeUnit.MINUTES);
//        okHttpClient.newBuilder().writeTimeout(1, TimeUnit.MINUTES);
//        okHttpClient.newBuilder().connectTimeout(5, TimeUnit.MINUTES);
        Request request=new Request.Builder().url(url).build();
        Call call=okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                file_download.onfailed();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    ResponseBody responseBody=response.body();
                    if (file.length()>=responseBody.contentLength()){
                        file_download.onsuccess(file,"已下载");
                    }else {
                        BufferedSink sink = Okio.buffer(Okio.sink(file));
                        Timer timer=new Timer();
                        DownloadFileBean downloadFileBean=new DownloadFileBean();
                        downloadFileBean.setCall(call);
                        downloadFileBean.setSink(sink);
                        downloadFileBean.setTimer(timer);
                        downloadFileBean.setResponse(response);
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                file_download.progress(file.length(),responseBody.contentLength(),HQWFile.getPrintSize(file.length()),HQWFile.getPrintSize(responseBody.contentLength()));
                                file_download.onsuccess(file,"下载中");
                                if (file.length()>=responseBody.contentLength()){
                                    downloadFileBean.cancel();
                                    file_download.onsuccess(file,"下载完成");
                                }
                            }
                        },0,1000);
                        file_download.console(downloadFileBean);
                        sink.writeAll(responseBody.source());
                    }

            }
        });
    }
}
