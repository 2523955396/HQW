package com.huanqi.hqw.Utils;

import android.content.Context;
import android.widget.Toast;

public class HQWToastUtil {
    public static void setToast(Context context,String text){
        Toast.makeText(context,text,Toast.LENGTH_SHORT).show();
    }
}
