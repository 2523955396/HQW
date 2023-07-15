package com.huanqi.android.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class HQWViewPagerAdapter extends PagerAdapter {


    Context context;
    List<View> views = new ArrayList<>();

    public HQWViewPagerAdapter(Context context) {
        this.context = context;

    }

    public void setData(List<View> views) {
        this.views = views;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(views.get(position));
        return views.get(position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(views.get(position));
    }

    public View getIndexView(int index) {
        return views.get(index);
    }
}
