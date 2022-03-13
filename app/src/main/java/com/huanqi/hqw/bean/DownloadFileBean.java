package com.huanqi.hqw.bean;

import android.util.Log;

import java.io.IOException;
import java.util.Timer;

import okhttp3.Call;
import okhttp3.Response;
import okio.BufferedSink;

public class DownloadFileBean {
    Call call;
    Response response;
    BufferedSink sink;
    Timer timer;

    public void cancel() {
        try {
            if (call!=null){
                call.cancel();
            }
            if (sink.isOpen()){
                sink.flush();
                sink.close();
            }
            if (timer!=null){
                Log.i("setcancel: ","执行方法");
                timer.cancel();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setCall(Call call) {
        this.call = call;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public void setSink(BufferedSink sink) {
        this.sink = sink;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public BufferedSink getSink() {
        return sink;
    }

    public Call getCall() {
        return call;
    }

    public Response getResponse() {
        return response;
    }

    public Timer getTimer() {
        return timer;
    }
}
