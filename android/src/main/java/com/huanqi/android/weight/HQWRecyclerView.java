package com.huanqi.android.weight;

import android.content.Context;
import android.util.AttributeSet;

import androidx.recyclerview.widget.RecyclerView;

/**
 * 使用的记得HQWRecyclerView.setNestedScrollingEnabled(false);不然还是冲突
 */

public class HQWRecyclerView extends RecyclerView {
    public HQWRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HQWRecyclerView(Context context) {
        super(context);
    }

    public HQWRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

}