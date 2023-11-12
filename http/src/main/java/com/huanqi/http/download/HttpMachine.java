package com.huanqi.http.download;

public  class HttpMachine {//下载控制器
    HttpController httpController;

    public void setController(HttpController httpController) {
        this.httpController = httpController;
    }

    public void stop() {
        if (httpController != null) {
            httpController.Stop();
        }
    }

    public void start() {
        if (httpController != null) {
            httpController.Start();
        }
    }
}

interface HttpController {//下载控制器接口

    void Stop();

    void Start();
}
