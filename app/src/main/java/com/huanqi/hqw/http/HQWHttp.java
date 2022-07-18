package com.huanqi.hqw.http;

import android.util.Log;

import androidx.annotation.NonNull;

import com.huanqi.hqw.Interface.FileDownloadCallBack;
import com.huanqi.hqw.Utils.HQWFileUtil;
import com.huanqi.hqw.Utils.HQWLogUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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

/**
 * 网络传输类 By焕奇灵动
 */
public class HQWHttp {
    public static final int DOWNLOADING = 1;//下载中
    public static final int COMPLETE = 2;//下载完成
    public boolean IsDownloading = false;//是否正在下载
    public Call call;
    public BufferedSink bufferedSink;
    public Timer timer;
    public Response response;
    public InputStream inputStream;
    public FileOutputStream fileOutputStream;


    public void setResponse(Response response) {
        this.response = response;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public void setCall(Call call) {
        this.call = call;
    }

    public void setBufferedSink(BufferedSink bufferedSink) {
        this.bufferedSink = bufferedSink;
    }

    public void setFileOutputStream(FileOutputStream fileOutputStream) {
        this.fileOutputStream = fileOutputStream;
    }

    public void setDownloading(boolean downloading) {
        IsDownloading = downloading;
    }

    public boolean isDownloading() {
        return IsDownloading;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    /**
     * 下载功能
     * url 超链接
     * file 文件保存地址
     * calltime 返回调用时间 单位1000=1秒
     * callBack调用回调
     */
    public void DownloadFile(String url, File file, int calltime, FileDownloadCallBack callBack) {
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
                BufferedSink bufferedSink = Okio.buffer(Okio.sink(file));
                Timer timer = new Timer();
                setCall(call);
                setResponse(response);
                setBufferedSink(bufferedSink);
                setTimer(timer);
                setDownloading(true);
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        callBack.progress(file.length(), responseBody.contentLength(), HQWFileUtil.getPrintSize(file.length()), HQWFileUtil.getPrintSize(responseBody.contentLength()));
                        callBack.onsuccess(file, DOWNLOADING);

                    }
                }, 0, calltime);
                bufferedSink.writeAll(responseBody.source());
                //下载完成执行
                cancel();
                callBack.progress(file.length(), responseBody.contentLength(), HQWFileUtil.getPrintSize(file.length()), HQWFileUtil.getPrintSize(responseBody.contentLength()));
                callBack.onsuccess(file, COMPLETE);
                setDownloading(false);
            }
        });
    }

    /**
     * 下载功能
     * url 超链接
     * file 文件保存地址
     * calltime 返回调用时间 单位1000=1秒
     * callBack调用回调
     */
    public void CallDownloadFile(Call call, File file, int calltime, FileDownloadCallBack callBack) {
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callBack.onfailed();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                ResponseBody responseBody = response.body();
                BufferedSink bufferedSink = Okio.buffer(Okio.sink(file));
                Timer timer = new Timer();
                setCall(call);
                setResponse(response);
                setBufferedSink(bufferedSink);
                setTimer(timer);
                setDownloading(true);
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        callBack.progress(file.length(), responseBody.contentLength(), HQWFileUtil.getPrintSize(file.length()), HQWFileUtil.getPrintSize(responseBody.contentLength()));
                        callBack.onsuccess(file, DOWNLOADING);

                    }
                }, 0, calltime);
                bufferedSink.writeAll(responseBody.source());
                //下载完成执行
                cancel();
                callBack.progress(file.length(), responseBody.contentLength(), HQWFileUtil.getPrintSize(file.length()), HQWFileUtil.getPrintSize(responseBody.contentLength()));
                callBack.onsuccess(file, COMPLETE);
                setDownloading(false);
            }
        });
    }

    /**
     * 下载功能关闭清空
     */
    public void cancel() {
        try {
            if (call != null) {
                call.cancel();
            }
            if (bufferedSink.isOpen()) {
                bufferedSink.flush();
                bufferedSink.close();
            }
            if (timer != null) {
                timer.cancel();
            }
            if (response != null) {
                response.close();
            }
            setDownloading(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 断点续传 下载功能
     * url 超链接
     * file 文件保存地址
     * calltime 返回调用时间 单位1000=1秒
     * callBack调用回调
     */
    public void DownloadFileResume(String url, File file, int calltime, FileDownloadCallBack callBack) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callBack.onfailed();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                long maxlength = response.body().contentLength();
                call.cancel();
                response.close();
                Request request;
                if (file.exists()) {
                    request = new Request.Builder()
                            .addHeader("RANGE", "bytes=" + file.length() + "-")
                            .url(url)
                            .build();
                } else {
                    request = new Request.Builder()
                            .url(url)
                            .build();
                }
                OkHttpClient okHttpClient = new OkHttpClient();
                Call newcall = okHttpClient.newCall(request);
                newcall.enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        callBack.onfailed();
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        ResponseBody responseBody = response.body();
                        if (file.length() >= maxlength) {
                            cancelResume();
                            callBack.onsuccess(file, COMPLETE);
                            return;
                        }

                        Timer timer = new Timer();
                        InputStream inputStream = responseBody.byteStream();
                        FileOutputStream fileOutputStream = new FileOutputStream(file, true);
                        setCall(newcall);
                        setResponse(response);
                        setTimer(timer);
                        setFileOutputStream(fileOutputStream);
                        setInputStream(inputStream);
                        setDownloading(true);
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                callBack.progress(file.length(), maxlength, HQWFileUtil.getPrintSize(file.length()), HQWFileUtil.getPrintSize(maxlength));
                                callBack.onsuccess(file, DOWNLOADING);
                            }
                        }, 0, calltime);

                        long tmpLength = 0;
                        byte[] bytes = new byte[2048];
                        int len = 0;
                        while ((len = inputStream.read(bytes)) != -1) {
                            if (fileOutputStream != null) {
                                fileOutputStream.write(bytes, 0, len);
                                tmpLength += len;
                            } else {
                                cancelResume();
                            }
                        }
                        cancelResume();
                        callBack.progress(file.length(), maxlength, HQWFileUtil.getPrintSize(file.length()), HQWFileUtil.getPrintSize(maxlength));
                        callBack.onsuccess(file, COMPLETE);
                        setDownloading(false);
                    }
                });
            }
        });
    }


    /**
     * 断点续传下载功能关闭清空
     */
    public void cancelResume() {
        try {
            if (call != null) {
                call.cancel();
            }
            if (timer != null) {
                timer.cancel();
            }
            if (response != null) {
                response.close();
            }
            if (fileOutputStream != null) {
                fileOutputStream.flush();
                fileOutputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            setDownloading(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
