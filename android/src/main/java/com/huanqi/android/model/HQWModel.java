package com.huanqi.android.model;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.huanqi.android.activity.HQWActivity;
import com.huanqi.android.activity.HQWAppCompatActivity;
import com.huanqi.android.dialog.HQWDialog;
import com.huanqi.android.fragment.HQWFragment;

public class HQWModel {
    HQWFragment hqwFragment;
    HQWActivity hqwActivity;
    HQWAppCompatActivity hqwAppCompatActivity;
    Activity activity;
    AppCompatActivity appCompatActivity;
    Fragment fragment;
    Dialog dialog;
    HQWDialog hqwDialog;
    View view;
    Context context;

    public AppCompatActivity getAppCompatActivity() {
        return appCompatActivity;
    }

    public void setAppCompatActivity(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }

    public Dialog getDialog() {
        return dialog;
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }

    public HQWDialog getHqwDialog() {
        return hqwDialog;
    }

    public void setHqwDialog(HQWDialog hqwDialog) {
        this.hqwDialog = hqwDialog;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public HQWAppCompatActivity getHqwAppCompatActivity() {
        return hqwAppCompatActivity;
    }

    public void setHqwAppCompatActivity(HQWAppCompatActivity hqwAppCompatActivity) {
        this.hqwAppCompatActivity = hqwAppCompatActivity;
    }

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
        this.activity = activity;
    }
    public void onCreate(Activity activity, View view) {
        this.activity = activity;
        this.view = view;
    }

    public void onCreate(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }
    public void onCreate(AppCompatActivity appCompatActivity, View view) {
        this.appCompatActivity = appCompatActivity;
        this.view = view;
    }


    public void onCreate(View view) {
        this.view = view;
    }

    public void onCreate(View view, Context context) {
        this.view = view;
        this.context = context;
    }




    public void onCreate(HQWActivity hqwActivity) {
        this.hqwActivity = hqwActivity;
    }

    public void onCreate(HQWActivity hqwActivity, View view) {
        this.hqwActivity = hqwActivity;
        this.view = view;
    }


    public void onCreate(HQWAppCompatActivity hqwAppCompatActivity) {
        this.hqwAppCompatActivity = hqwAppCompatActivity;
    }

    public void onCreate(HQWAppCompatActivity hqwAppCompatActivity, View view) {
        this.hqwAppCompatActivity = hqwAppCompatActivity;
        this.view = view;
    }

    public void onCreate(Fragment fragment) {
        this.fragment = fragment;
    }

    public void onCreate(Fragment fragment, View view) {
        this.fragment = fragment;
        this.view = view;
    }

    public void onCreate(HQWFragment hqwFragment) {
        this.hqwFragment = hqwFragment;
    }

    public void onCreate(HQWFragment hqwFragment, View view) {
        this.hqwFragment = hqwFragment;
        this.view = view;
    }

    public void onCreate(Dialog dialog) {
        this.dialog = dialog;
    }

    public void onCreate(Dialog dialog, View view) {
        this.dialog = dialog;
        this.view = view;
    }

    public void onCreate(HQWDialog hqwDialog) {
        this.dialog = dialog;
    }

    public void onCreate(HQWDialog hqwDialog, View view) {
        this.hqwDialog = hqwDialog;
        this.view = view;
    }



    public void onPause() {
    }

    public void onRestart() {
    }

    public void onResume() {
    }

    public void onDestroy() {
    }

    public void initView() {

    }

    public void initData() {

    }
}
