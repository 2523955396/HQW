package com.huanqi.hqw.model;

import android.content.Context;
import android.view.View;

import com.huanqi.hqw.activity.HQWActivity;

public class HQWModel {
    HQWActivity hqwActivity;
    View view;
    Context context;
    public HQWActivity getHqwActivity() {
        return hqwActivity;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setHqwActivity(HQWActivity hqwActivity) {
        this.hqwActivity = hqwActivity;
    }

    public void setView(View view) {
        this.view = view;
    }

    public View getView() {
        return view;
    }
    public Context getContext() {
        return context;
    }
    public void onCreate() {
    }
    public void onCreate(View view) {
        this.view=view;
    }
    public void onCreate(View view, Context context) {
        this.view=view;
        this.context=context;
    }
    public void onCreate(HQWActivity hqwActivity) {
        this.hqwActivity=hqwActivity;
    }
    public void onPause() {
    }
    public void onRestart() {
    }
    public void onResume() {
    }
    public void onDestroy() {
    }
}
