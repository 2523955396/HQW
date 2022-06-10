package com.huanqi.hqw.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.huanqi.hqw.R;

public class HQWDialog extends Dialog {
    public HQWDialog(@NonNull Context context) {
        super(context, R.style.Theme_HQW_Dialog);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);//去除背景阴影
    }
}
