package com.huanqi.hqw.animation;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;

import androidx.annotation.NonNull;

import com.huanqi.hqw.Utils.HQWWeightUtil;

/**
 * 动画类 By焕奇灵动
 */
public class HQWAnimation {

    /**
     * 平移
     * view
     * XorY true横向平移 Y纵向平移
     * f 平移多少px
     * time 时间 毫秒1:1000
     */
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

    /**
     * 旋转动画
     * view
     * XorY true纵向旋转 false横向旋转
     * f 旋转的开始角度
     * ff 旋转的结束角度
     * time 时间 毫秒1:1000
     */
    public static void WidghtRotateXY(View view,boolean XorY,Float f,Float ff,int time){
        ObjectAnimator animation;
        if (XorY){
            animation = ObjectAnimator.ofFloat(view, "rotationX", f,ff);
        }else {
            animation = ObjectAnimator.ofFloat(view, "rotationY", f,ff);
        }
        animation.setDuration(time);
        animation.start();
    }


    /**
     * 高宽调整动画
     * view
     * WorH true为宽 false为高
     * f 高宽调整大小
     * time 时间 毫秒1:1000
     */
    public static void WidghtValue(View view,boolean WorH,int i,int time){
        ValueAnimator valueAnimator=ValueAnimator.ofInt(view.getMeasuredHeight(), i);
        valueAnimator.setDuration(time);
        valueAnimator.start();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(@NonNull ValueAnimator animation) {
                ViewGroup.LayoutParams layoutParams= view.getLayoutParams();
                if (WorH){
                    layoutParams.width=(int)animation.getAnimatedValue();
                }else {
                    layoutParams.height=(int)animation.getAnimatedValue();
                }
                view.setLayoutParams(layoutParams);
            }
        });
    }



    /**
     * Apater删除动画，从下往上收缩
     */
    private static void ViewDelte(final View view, Animation.AnimationListener animationListener) {
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
