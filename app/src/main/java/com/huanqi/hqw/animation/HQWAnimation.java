package com.huanqi.hqw.animation;

import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

import com.huanqi.hqw.Utils.HQWWeightUtil;

public class HQWAnimation {

    //平移动画
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

    //Apater删除动画
    private void ViewDelte(final View view, Animation.AnimationListener animationListener) {
        final int originHeight = view.getMeasuredHeight();
        Animation animation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1.0f) {
                    view.setVisibility(View.GONE);
                } else {
                    view.getLayoutParams().height = originHeight + (int) (originHeight * interpolatedTime);
                    view.requestLayout();
                }
            }
            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        if (animationListener != null) {
            animation.setAnimationListener(animationListener);
        }
        animation.setDuration(300);
        view.startAnimation(animation);
    }
}
