package com.huanqi.android.Utils;

import android.content.Context;
import android.text.TextPaint;


/**
 * 文字处理类 By焕奇灵动
 */
public class HQWTextUtil {

    /**
     * 文字绘制前测量宽度
     *
     * @param context 上下文
     * @param text    文字
     * @param size    文字大小
     */
    public static int getWidth(Context context, String text, float size) {
        float textSize = context.getResources().getDisplayMetrics().scaledDensity * size;
        TextPaint paint = new TextPaint();
        paint.setTextSize(textSize);
        int width = (int) paint.measureText(text);
        return width;
    }
}
