package com.huanqi.hqw.Utils;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

public class HQWToastUtil {
    public static void setToast(Context context,String text){
        Toast.makeText(context,text,Toast.LENGTH_SHORT).show();
    }
    public static void setToast(View view, String text){
        view.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(view.getContext(),text,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
