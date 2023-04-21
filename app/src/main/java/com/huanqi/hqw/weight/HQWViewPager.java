package com.huanqi.hqw.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.huanqi.hqw.adapter.HQWViewPagerAdapter;

/*
* 根据子View绘制 如果按照子View 请搭配HQWViewPagerAdapter使用
* */
public class HQWViewPager extends ViewPager {
    private boolean isSwipe = false;//是否禁止横向滑动
    private boolean isRedraw = false;//是否按子View高度进行绘制

    public void setSwipe(boolean isSwipe)
    {
        this.isSwipe = isSwipe;
    }

    public void setRedraw(boolean redraw) {
        isRedraw = redraw;
    }

    public HQWViewPager(@NonNull Context context) {
        super(context);

    }

    public HQWViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return isSwipe&&super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return isSwipe&&super.onInterceptTouchEvent(ev);
    }

    @Override
    public void addOnPageChangeListener(@NonNull OnPageChangeListener listener) {
        super.addOnPageChangeListener(listener);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (isRedraw){
            if (getChildCount() > 0) {
                HQWViewPagerAdapter adapter = (HQWViewPagerAdapter) getAdapter();
                if (adapter == null)
                    throw new NullPointerException("类AutoHeightViewPager中onMeasure方法获得的Adapter为空");
                View view = adapter.getIndexView(getCurrentItem());
                if (view == null)
                    throw new NullPointerException("类AutoHeightViewPager中onMeasure方法获得的view为空");
                view.measure(0, 0);// 手动测量
                super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(measureHeight(heightMeasureSpec, view), MeasureSpec.EXACTLY));
            } else {
                super.onMeasure(widthMeasureSpec, heightMeasureSpec);

            }
        }else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    private int measureHeight(int measureSpec, View view) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            if (view != null) {
                result = view.getMeasuredHeight();
            }
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

}

