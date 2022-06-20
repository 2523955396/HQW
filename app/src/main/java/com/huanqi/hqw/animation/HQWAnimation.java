package com.huanqi.hqw.animation;

import android.animation.ObjectAnimator;
import android.view.View;

import com.huanqi.hqw.Utils.HQWWeightUtil;

public class HQWAnimation {

    public static void WidghtMoveXY(View view,boolean XorY,Float f,int time){
        ObjectAnimator animation;
        if (XorY){
            animation = ObjectAnimator.ofFloat(view, "translationX", f);
        }else {
            animation = ObjectAnimator.ofFloat(view, "translationY", f);
        }
        animation.setDuration(time);
        animation.start();
    }
}
