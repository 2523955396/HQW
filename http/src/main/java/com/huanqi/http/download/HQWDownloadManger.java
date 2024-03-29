package com.huanqi.http.download;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;

import com.huanqi.android.Utils.HQWFileUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class HQWDownloadManger {

    private static Handler handler = new Handler(Looper.getMainLooper());

    interface HttpController {
        void Stop();
        void Start();
    }

    public static class HttpMachine {
        HttpController httpController;
        public void setController(HttpController httpController) {
            this.httpController = httpController;
        }

        public void stop() {
            if (httpController!=null){
                httpController.Stop();
            }
        }

        public void start() {
            if (httpController!=null){
                httpController.Start();
            }
        }

    }

    /**
     * 下载功能
     * url 超链接
     * file 文件保存地址
     * HttpMachine 下载管理器
     * callBack调用回调
     */
    public static void DownloadFile(String url, File file, HttpMachine httpMachine, HQWDownloadListener callBack) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                handler.post(() ->{
                    callBack.onFailed(file,e);
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                InputStream is = null;
                FileOutputStream out = null;
                try {
                    ResponseBody responseBody = response.body();
                    long contentLength = responseBody.contentLength();
                    is = responseBody.byteStream();
                    out = new FileOutputStream(file);
                    byte[] buffer = new byte[1024];
                    int len = -1;
                    while ((len = is.read(buffer)) != -1) {
                        out.write(buffer, 0, len);
                        handler.post(()->{
                            callBack.onDownloading(file,file.length(), contentLength, HQWFileUtil.getPrintSize(file.length()), HQWFileUtil.getPrintSize(contentLength));
                        });
                    }
                    call.cancel();
                    handler.post(()->{
                        callBack.onSucceed(file);
                    });
                } catch (Exception v) {
                    call.cancel();
                    is.close();
                    out.flush();
                    out.close();
                    handler.post(()->{
                        callBack.onFailed(file,v);
                    });
                }
            }
        });
        if (httpMachine!=null){
            httpMachine.setController(new HttpController() {
                @Override
                public void Stop() {
                    if (call!=null){
                        call.cancel();
                    }
                }
                @Override
                public void Start() {
                    DownloadFile(url, file, httpMachine, callBack);
                }
            });
        }
    }

    /**
     * 断点续传下载功能
     * url 超链接
     * file 文件保存地址
     * HttpMachine 下载管理器
     * callBack调用回调
     */
    public static void ResumeDownloadFile(String url, File file, HttpMachine httpMachine, HQWDownloadListener callBack) {
        HQWDownloadRegister hqwDownloadRegister=new HQWDownloadRegister();

        Request  request = new Request.Builder()
                .url(url)
                .build();
        Call call = new OkHttpClient().newCall(request);
        hqwDownloadRegister.setCall(call);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                handler.post(()->{
                    callBack.onFailed(file,e);
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                ResponseBody responseBody = response.body();
                hqwDownloadRegister.setContentLength(responseBody.contentLength());
                call.cancel();

                Request request=null;
                if (!file.exists()){//判断为 否 断电传输 或者 文件不存在时
                    request = new Request.Builder()
                            .url(url)
                            .build();
                }else {//请求头 请求字节位置
                    request = new Request.Builder()
                            .addHeader("RANGE", "bytes=" + file.length() + "-")
                            .url(url)
                            .build();
                }

                call= new OkHttpClient().newCall(request);
                hqwDownloadRegister.setCall(call);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        handler.post(()->{
                            callBack.onFailed(file,e);
                        });
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        InputStream is = null;
                        FileOutputStream out = null;
                        try {
                            ResponseBody responseBody = response.body();
//                            long contentLength = responseBody.contentLength();
//                            hqwDownloadRegister.setContentLength(contentLength);
                            is = responseBody.byteStream();
                            out = new FileOutputStream(file,true);
                            byte[] buffer = new byte[1024];
                            int len = -1;
                            while ((len = is.read(buffer)) != -1) {
                                out.write(buffer, 0, len);
                                handler.post(()->{
                                    callBack.onDownloading(file,file.length(), hqwDownloadRegister.getContentLength(), HQWFileUtil.getPrintSize(file.length()), HQWFileUtil.getPrintSize(hqwDownloadRegister.getContentLength()));
                                });
                            }
                            call.cancel();
                            handler.post(()->{
                                callBack.onSucceed(file);
                            });
                        } catch (Exception v) {
                            call.cancel();
                            is.close();
                            out.flush();
                            out.close();
                            handler.post(()->{
                                callBack.onStop(file);
                            });
                        }
                    }
                });
            }
        });
        if (httpMachine!=null){
            httpMachine.setController(new HttpController() {
                @Override
                public void Stop() {
                    if (hqwDownloadRegister.getCall()!=null){
                        hqwDownloadRegister.getCall().cancel();
                    }
                }
                @Override
                public void Start() {
                    DownloadFile(url, file, httpMachine, callBack);
                }
            });
        }
    }


    static class HQWDownloadRegister{
        Call call=null;
        long contentLength=0;

        public Call getCall() {
            return call;
        }

        public void setCall(Call call) {
            this.call = call;
        }

        public long getContentLength() {
            return contentLength;
        }

        public void setContentLength(long contentLength) {
            this.contentLength = contentLength;
        }
    }
}
