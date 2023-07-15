package com.huanqi.http.upload;

import android.os.Handler;
import android.os.Looper;
import android.webkit.MimeTypeMap;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 文件上传封装
 * 2023/07/15
 * */

public class HQWUploadManger {
    public static final String FILE_TYPE_FILE="file";


    private static Handler handler = new Handler(Looper.getMainLooper());

    public static void startUpload(String url, File file,String FileType, MultipartBodyCallback multipartBodyCallback, UploadCallback uploadCallback) {
        OkHttpClient.Builder httpClient = new OkHttpClient().newBuilder();
        httpClient.callTimeout(5, TimeUnit.MINUTES);
        httpClient.connectTimeout(5, TimeUnit.MINUTES);

        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart(FileType, file.getName(), RequestBody.create(file, MediaType.parse(getMimeType(file.getAbsolutePath()))));
        multipartBodyCallback.addPart(builder);

        ExMultipartBody exMultipartBody = new ExMultipartBody(builder.build(), new ExMultipartBody.UploadProgressListener() {
            @Override
            public void onProgress(int progress, int max) {
                handler.post(() -> {
                    uploadCallback.onProgress(progress, max);
                });
            }
        });
        Request request = new Request.Builder().url(url).post(exMultipartBody).build();
        httpClient.build().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                handler.post(() -> {
                    uploadCallback.onFailure(e);
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                handler.post(() -> {
                    uploadCallback.onSuccess();
                });
            }
        });
    }


    /**
     * 获取文件类型
     */
    private static String getMimeType(String url) {
        String type = null;
        String extension = MimeTypeMap.getFileExtensionFromUrl(url);
        if (extension != null) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
        }
        return type;
    }


    public interface MultipartBodyCallback {
        void addPart(MultipartBody.Builder multipartBody);
    }

    public interface UploadCallback {
        void onProgress(int progress, int max);

        void onFailure(IOException e);

        void onSuccess();

    }
}
