package com.huanqi.hqw.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.huanqi.hqw.R;

public class HQWDialog extends Dialog {

    public HQWDialog(@NonNull Context context,int layout) {
        super(context);
        View view = LayoutInflater.from(context).inflate(layout, null);
        setContentView(view);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setBackgroundDrawableResource(R.color.transparency);
    }
}
