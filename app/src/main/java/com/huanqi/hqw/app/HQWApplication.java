package com.huanqi.hqw.app;

import android.app.Application;

public class HQWApplication extends Application {

   public static HQWApplication hqwApplication;
    @Override
    public void onCreate() {
        super.onCreate();
        hqwApplication=this;
    }

    public static HQWApplication Info(){
        return hqwApplication;
    }
}
