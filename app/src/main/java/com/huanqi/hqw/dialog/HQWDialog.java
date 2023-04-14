package com.huanqi.hqw.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.transition.Slide;

import com.huanqi.hqw.R;


/**
 * Dialog By焕奇灵动
 */
public class HQWDialog extends Dialog {


    public HQWDialog(@NonNull Context context) {
        super(context);
    }

    public HQWDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected HQWDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    /**
     * 扩展边框边距
     */
    public void setBorderExtension() {//扩展边框边距
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
        getWindow().setAttributes(layoutParams);
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        getWindow().setBackgroundDrawableResource(R.color.transparency);//设置边框颜色,这个是替换,写死就行 注:原边框背景是一张图片
    }

    /**
     * 设置取消背景阴影
     */
    public void setShadow() {
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }

    /**
     * 去除状态栏 全屏展示
     */
    public void setFullscreen() {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    /**
     * 设置位置
     */
    public void setGravity(@Slide.GravityFlag int gravity) {
        getWindow().setGravity(gravity);
    }



    public void initView() {
    }

    public void initData() {
    }
}
