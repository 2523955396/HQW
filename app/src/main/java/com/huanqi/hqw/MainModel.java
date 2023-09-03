package com.huanqi.hqw;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.google.gson.Gson;
import com.huanqi.android.Utils.HQWJsonUtil;
import com.huanqi.android.Utils.HQWLogUtil;
import com.huanqi.android.activity.HQWActivity;
import com.huanqi.android.animation.HQWAnimation;
import com.huanqi.android.model.HQWModel;

import java.util.List;

public class MainModel extends HQWModel {


    CardView cv1;
    CardView cv2;

    Button addsad;
    @Override
    public void onCreate(HQWActivity hqwActivity) {
        super.onCreate(hqwActivity);
        initView();
        initData();
    }

    @Override
    public void initView() {
        super.initView();
        cv1 = findViewById(R.id.cv1);
        cv2 = findViewById(R.id.cv2);
        cv2.setVisibility(View.GONE);
        addsad=findViewById(R.id.addsad);
        addsad.setOnClickListener((view)->{

        });
    }

    @Override
    public void initData() {
        super.initData();

        List<TestBean> testBean= HQWJsonUtil.fromJsonList(aaaaa,TestBean.class);
        getHqwActivity().setToast(testBean.get(1).getName()+"");

    }



    String aaaaa="[\n" +
            "  {\n" +
            "    \"id\": \"1\",\n" +
            "    \"name\": \"菜鸟教程\",\n" +
            "    \"url\": \"www.runoob.com\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": \"2\",\n" +
            "    \"name\": \"菜鸟工具\",\n" +
            "    \"url\": \"c.runoob.com\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": \"3\",\n" +
            "    \"name\": \"Google\",\n" +
            "    \"url\": \"www.google.com\"\n" +
            "  }\n" +
            "]";





}
