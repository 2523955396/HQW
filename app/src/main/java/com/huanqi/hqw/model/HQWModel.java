package com.huanqi.hqw.model;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.huanqi.hqw.activity.HQWActivity;
import com.huanqi.hqw.fragment.HQWFragment;

public class HQWModel {
    HQWFragment hqwFragment;
    HQWActivity hqwActivity;
    Activity activity;
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

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void setHqwFragment(HQWFragment hqwFragment) {
        this.hqwFragment = hqwFragment;
    }

    public HQWFragment getHqwFragment() {
        return hqwFragment;
    }

    public void onCreate() {
    }
    public void onCreate(Activity activity) {
        this.activity=activity;
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
    public void onCreate(HQWActivity hqwActivity,View view) {
        this.hqwActivity=hqwActivity;
        this.view=view;
    }
    public void onCreate(HQWFragment hqwFragment) {
        this.hqwFragment=hqwFragment;
    }
    public void onCreate(HQWFragment hqwFragment,View view) {
        this.hqwFragment=hqwFragment;
        this.view=view;
    }
    public void onPause() {
    }
    public void onRestart() {
    }
    public void onResume() {
    }
    public void onDestroy() {
    }

    public void initView(){

    }
    public void initData(){

    }
}
