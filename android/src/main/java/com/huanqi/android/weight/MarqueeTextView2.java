package com.huanqi.android.weight;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.widget.TextView;


/*
/*自动跑马灯
 */
public class MarqueeTextView2 extends androidx.appcompat.widget.AppCompatTextView {
    /** 是否停止滚动 */
    private boolean mStopMarquee;
    private String mText;
    private float mCoordinateX;
    private float mTextWidth;
    private float windowWith;

    public MarqueeTextView2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public void setText(String text) {
        this.mText = text;
        mTextWidth = getPaint().measureText(mText);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        windowWith = displayMetrics.widthPixels;
        if (mHandler.hasMessages(0))
            mHandler.removeMessages(0);
        mHandler.sendEmptyMessageDelayed(0, 1000);
    }


    @SuppressLint("NewApi")
    @Override
    protected void onAttachedToWindow() {
        mStopMarquee = false;
        if (!(mText == null || mText.isEmpty()))
            mHandler.sendEmptyMessageDelayed(0, 1000);
        super.onAttachedToWindow();
    }


    @Override
    protected void onDetachedFromWindow() {
        mStopMarquee = true;
        if (mHandler.hasMessages(0))
            mHandler.removeMessages(0);
        super.onDetachedFromWindow();
    }


    @SuppressLint("NewApi")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Rect rect=new Rect();
        getPaint().getTextBounds(mText,0,mText.length(),rect);
        if (!(mText == null || mText.isEmpty()))
            canvas.drawText(mText, mCoordinateX, getHeight()/2- getPaint().getFontMetricsInt().descent/2+rect.height()/2, getPaint());
    }


    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    if(mCoordinateX < 0 && Math.abs(mCoordinateX) > mTextWidth){
                        mCoordinateX = windowWith;
                    }else{
                        mCoordinateX -= 1;
                    }
                    invalidate();
                    sendEmptyMessageDelayed(0,10);
                    break;
            }
        }
    };
}



