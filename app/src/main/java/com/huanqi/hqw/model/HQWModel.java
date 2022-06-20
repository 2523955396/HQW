package com.huanqi.hqw.model;

import android.content.Context;
import android.view.View;

import com.huanqi.hqw.activity.HQWActivity;

public class HQWModel {
    HQWActivity activity;
    View view;
    Context context;
    public HQWActivity getActivity() {
        return activity;
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
    public void onCreate(HQWActivity activity) {
        this.activity=activity;
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
