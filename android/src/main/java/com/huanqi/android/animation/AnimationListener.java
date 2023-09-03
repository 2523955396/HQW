package com.huanqi.android.animation;

import android.view.animation.Animation;

public interface AnimationListener {

    default void onAnimationStart(Animation animation){}
    default void onAnimationEnd(Animation animation){}
    default void onAnimationRepeat(Animation animation){}
}
