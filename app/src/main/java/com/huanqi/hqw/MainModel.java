package com.huanqi.hqw;

import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.huanqi.android.activity.HQWActivity;
import com.huanqi.android.model.HQWModel;

public class MainModel extends HQWModel {


    CardView cardView;
    @Override
    public void onCreate(HQWActivity hqwActivity) {
        super.onCreate(hqwActivity);
        initView();
    }

    @Override
    public void initView() {
        super.initView();
       cardView = findViewById(R.id.testaaaaaa);
    }

    @Override
    public void initData() {
        super.initData();

    }







}
