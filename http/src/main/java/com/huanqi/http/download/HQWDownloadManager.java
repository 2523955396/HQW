package com.huanqi.http.download;

import androidx.annotation.NonNull;

import com.huanqi.android.Utils.HQWFileUtil;
import com.huanqi.android.Utils.HQWLogUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class HQWDownloadManager {

    public static void DownloadFile(String url, File file, HttpMachine httpMachine, DownloadListener callBack) {
        Call call = DownloadConfig.getInstance().getCall(url);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callBack.onFailed(file, e);
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
                        callBack.onDownloading(file, file.length(), contentLength, HQWFileUtil.getPrintSize(file.length()), HQWFileUtil.getPrintSize(contentLength));
                    }
                    call.cancel();
                    callBack.onSucceed(file);

                } catch (Exception v) {
                    call.cancel();
                    is.close();
                    out.flush();
                    out.close();
                    callBack.onFailed(file, v);
                }
            }
        });
        if (httpMachine != null) {
            httpMachine.setController(new HttpController() {
                @Override
                public void Stop() {
                    if (call != null) {
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
    public static void ResumeDownloadFile(String url, File file, HttpMachine httpMachine, DownloadListener downloadListener) {
        DownloadRegister downloadRegister = new DownloadRegister();
        ResumeDownloadCheckFileSize(url, file, downloadRegister, new FileSizeController() {
            @Override
            public void equality() {//文件存在并且相等
                downloadListener.onSucceed(file);
            }

            @Override
            public void unlikeness() {//文件不存在或不相等
                Call call = DownloadConfig.getInstance().getCall(url, file, downloadRegister.getHttpFileCountSize());
                downloadRegister.setCall(call);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        downloadListener.onFailed(file, e);
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        InputStream is = null;
                        FileOutputStream out = null;
                        try {
                            ResponseBody responseBody = response.body();
                            is = responseBody.byteStream();
                            out = new FileOutputStream(file, true);
                            byte[] buffer = new byte[1024];
                            int len = -1;
                            while ((len = is.read(buffer)) != -1) {
                                out.write(buffer, 0, len);
                                downloadListener.onDownloading(file, file.length(), downloadRegister.getHttpFileCountSize(), HQWFileUtil.getPrintSize(file.length()), HQWFileUtil.getPrintSize(downloadRegister.getHttpFileCountSize()));

                            }
                            call.cancel();
                            downloadListener.onSucceed(file);

                        } catch (Exception v) {
                            call.cancel();
                            is.close();
                            out.flush();
                            out.close();
                            downloadListener.onStop(file);
                        }
                    }
                });
            }

            @Override
            public void onFailed(File file, Exception e) {
                downloadListener.onFailed(file, e);
            }


        });


        if (httpMachine != null) {
            httpMachine.setController(new HttpController() {
                @Override
                public void Stop() {
                    if (downloadRegister.getCall() != null) {
                        downloadRegister.getCall().cancel();
                    }
                }

                @Override
                public void Start() {
                    ResumeDownloadFile(url, file, httpMachine, downloadListener);
                }
            });
        }
    }


    /**
     * 断点续传文件相等检查
     *
     * @param url                超链接
     * @param file               文件保存地址
     * @param downloadRegister   网络文件大小寄存器
     * @param fileSizeController 文件大小相等与否回调
     */
    public static void ResumeDownloadCheckFileSize(String url, File file, DownloadRegister downloadRegister, FileSizeController fileSizeController) {
        DownloadConfig.getInstance().getCall(url).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                fileSizeController.onFailed(file, e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                downloadRegister.setFileCountSize((file.exists() && file.length() > 0) ? file.length() : 0);
                downloadRegister.setHttpFileCountSize(response.body().contentLength());
                if (file.exists() && file.length() >= response.body().contentLength()) {//文件存在并且相等
                    fileSizeController.equality();
                } else {
                    fileSizeController.unlikeness();
                }
            }
        });
    }
}
