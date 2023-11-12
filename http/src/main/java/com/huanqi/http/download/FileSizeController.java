package com.huanqi.http.download;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;

public interface FileSizeController {//检查文件大小是否相等

    void equality();//相等

    void unlikeness();//不相等

    void onFailed(File file, Exception e);//网络错误
}