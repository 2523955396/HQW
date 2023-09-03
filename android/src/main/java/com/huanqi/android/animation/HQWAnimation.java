package com.huanqi.android.animation;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.Transformation;

import androidx.annotation.NonNull;

/**
 * 动画类 By焕奇灵动
 */
public class HQWAnimation {

    /**
     * 平移动画
     * view
     * XorY true横向平移 Y纵向平移
     * f 平移多少px
     * time 时间 毫秒1:1000
     */
    public static void MoveXY(View view, boolean XorY, Float f, int time) {
        ObjectAnimator animation;
        if (XorY) {
            animation = ObjectAnimator.ofFloat(view, "translationX", f);
        } else {
            animation = ObjectAnimator.ofFloat(view, "translationY", f);
        }
        animation.setDuration(time);
        animation.start();
    }

    /**
     * 镜像旋转动画
     * @param view
     * @param XorY true纵向旋转 false横向旋转
     * @param start 旋转的开始角度
     * @param end 旋转的结束角度
     * @param time 时间 毫秒1:1000
     */
    public static void RotateXY(View view, boolean XorY, Float start, Float end, int time) {
        ObjectAnimator animation;
        if (XorY) {
            animation = ObjectAnimator.ofFloat(view, "rotationX", start, end);
        } else {
            animation = ObjectAnimator.ofFloat(view, "rotationY", start, end);
        }
        animation.setDuration(time);
        animation.start();
    }


    /**
     * 旋转动画
     * @param view
     * @param start 旋转的开始角度
     * @param end 旋转的结束角度
     * @param time 时间 毫秒1:1000
     * @param animationListener 监听事件
     */
    public static void Rotate(View view, Float start, Float end, int time, AnimationListener animationListener) {
        RotateAnimation rotateAnimation = new RotateAnimation(
                start,
                end,
                Animation.RELATIVE_TO_SELF,
                0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f);
        view.setAnimation(rotateAnimation);
        rotateAnimation.setDuration(time);
        rotateAnimation.setFillAfter(true);
//        rotateAnimation.setRepeatCount(1);//旋转添加次数次数 无限Animation.INFINITE
//        rotateAnimation.setRepeatMode(Animation.RESTART);//旋转模式 正转RESTART或反转REVERSE
        view.startAnimation(rotateAnimation);
        rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                if (animationListener!=null){
                    animationListener.onAnimationStart(animation);
                }
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                if (animationListener!=null){
                    animationListener.onAnimationEnd(animation);
                }
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
                if (animationListener!=null){
                    animationListener.onAnimationRepeat(animation);
                }
            }
        });
    }


    /**
     * 淡入淡出动画
     * @param view
     * @param start 开始的透明度
     * @param end 结束的透明度
     * @param time 时间 毫秒1:1000
     * @param animationListener 监听事件
     */
    public static void Alpha(View view,float start,float end,int time,AnimationListener animationListener){
        AlphaAnimation alphaAnimation=new AlphaAnimation(start,end);
        view.setAnimation(alphaAnimation);
        alphaAnimation.setDuration(time);
        view.startAnimation(alphaAnimation);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                if (animationListener!=null){
                    animationListener.onAnimationStart(animation);
                }
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                if (animationListener!=null){
                    animationListener.onAnimationEnd(animation);
                }
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
                if (animationListener!=null){
                    animationListener.onAnimationRepeat(animation);
                }
            }
        });

    }





    /**
     * 高宽调整动画
     * view
     * WorH true为宽 false为高
     * f 高宽调整大小
     * time 时间 毫秒1:1000
     */
    public static void Value(View view, boolean WorH, int i, int time) {
        ValueAnimator valueAnimator = null;
        if (WorH) {
            valueAnimator = ValueAnimator.ofInt(view.getMeasuredWidth(), i);
        } else {
            valueAnimator = ValueAnimator.ofInt(view.getMeasuredHeight(), i);
        }
        valueAnimator.setDuration(time);
        valueAnimator.start();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(@NonNull ValueAnimator animation) {
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                if (WorH) {
                    layoutParams.width = (int) animation.getAnimatedValue();
                } else {
                    layoutParams.height = (int) animation.getAnimatedValue();
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
