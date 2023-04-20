package com.huanqi.hqw.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

/**
 * 建议滑动时重新请求布局，可以重新绘制,不然按最长的View展示
 *  hqwViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
 *             @Override
 *             public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
 *
 *             }
 *
 *             @Override
 *             public void onPageSelected(int position) {
 *                 hqwViewPager.requestLayout();
 *             }
 *
 *             @Override
 *             public void onPageScrollStateChanged(int state) {
 *
 *             }
 *         });
 */
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
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // find the current child view
        // and you must cache all the child view
        // use setOffscreenPageLimit(adapter.getCount())
        if (isRedraw){
            View view = getChildAt(getCurrentItem());
            if (view != null) {
                // measure the current child view with the specified measure spec
                view.measure(widthMeasureSpec, heightMeasureSpec);
            }
            setMeasuredDimension(getMeasuredWidth(), measureHeight(heightMeasureSpec, view));
        }

    }

    /**
     * Determines the height of this view
     *
     * @param measureSpec A measureSpec packed into an int
     * @param view the base view with already measured height
     *
     * @return The height of the view, honoring constraints from measureSpec
     */
    private int measureHeight(int measureSpec, View view) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            // set the height from the base view if available
            if (view != null) {
                result = view.getMeasuredHeight();
            }
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    /**
     * 单独测量view获取尺寸
     *
     * @param view
     */
    public void measeureView(View view) {

        int intw = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        int inth = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        // 重新测量view
        view.measure(intw, inth);

        // 以上3句可简写成下面一句
        //view.measure(0,0);

        // 获取测量后的view尺寸
        int intwidth = view.getMeasuredWidth();
        int intheight = view.getMeasuredHeight();
    }


}

