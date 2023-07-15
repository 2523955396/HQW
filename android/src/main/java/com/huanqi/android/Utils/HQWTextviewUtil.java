package com.huanqi.android.Utils;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 文字布局处理类 By焕奇灵动
 */
public class HQWTextviewUtil {


        private Context mContext;
        private HyperLinkOnClickListener hyperLinkOnClickListener;
        public HQWTextviewUtil(Context mContext,HyperLinkOnClickListener hyperLinkOnClickListener) {
            this.mContext = mContext;
            this.hyperLinkOnClickListener = hyperLinkOnClickListener;
        }
        public static HQWTextviewUtil interceptHyperLink(Context context, TextView textView, HyperLinkOnClickListener hyperLinkOnClickListener){
            HQWTextviewUtil hqwTextviewUtil= new HQWTextviewUtil(context,hyperLinkOnClickListener);
            hqwTextviewUtil.interceptHyperLink(textView);
            return hqwTextviewUtil;
        }
        /**
         * 拦截超链接
         * @param tv
         */
        public void interceptHyperLink(TextView tv) {
            tv.setMovementMethod(LinkMovementMethod.getInstance());
            CharSequence text = tv.getText();
            SpannableStringBuilder stringBuilder=identifyUrl(text);
            tv.setText(stringBuilder);
        }
        LinkedList<String> mStringList = new LinkedList<String>();
        LinkedList<UrlInfo> mUrlInfos = new LinkedList<UrlInfo>();
        private String pattern =
                "((http|ftp|https)://)(([a-zA-Z0-9\\._-]+\\.[a-zA-Z]{2,6})|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,4})*(/[a-zA-Z0-9\\&%_\\./-~-]*)?|(([a-zA-Z0-9\\._-]+\\.[a-zA-Z]{2,6})|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,4})*(/[a-zA-Z0-9\\&%_\\./-~-]*)?";

        Pattern r = Pattern.compile(pattern);
        Matcher m;
        int flag= Spanned.SPAN_EXCLUSIVE_EXCLUSIVE;

        class UrlInfo {
            public int start;
            public int end;
        }

        public SpannableStringBuilder identifyUrl(CharSequence text) {
            mStringList.clear();
            mUrlInfos.clear();

            CharSequence contextText;
            CharSequence clickText;
            text = text == null ? "" : text;
            contextText = text;
            clickText = null;
            m = r.matcher(contextText);
            //匹配成功
            while (m.find()) {
                //得到网址数
                UrlInfo info = new UrlInfo();
                info.start = m.start();
                info.end = m.end();
                mStringList.add(m.group());
                mUrlInfos.add(info);
            }
            return joinText(clickText, contextText);
        }

        /** 拼接文本 */
        private SpannableStringBuilder joinText(CharSequence clickSpanText,
                                                CharSequence contentText) {
            SpannableStringBuilder spanBuilder;
            if (clickSpanText != null) {
                spanBuilder = new SpannableStringBuilder(clickSpanText);
            } else {
                spanBuilder = new SpannableStringBuilder();
            }
            if (mStringList.size() > 0) {
                //只有一个网址
                if (mStringList.size() == 1) {
                    String preStr = contentText.toString().substring(0, mUrlInfos.get(0).start);
                    spanBuilder.append(preStr);
                    String url = mStringList.get(0);
                    CustomUrlSpan customUrlSpan = new CustomUrlSpan(mContext,url);
                    int start = spanBuilder.length();
                    spanBuilder.append(url, new UnderlineSpan(), flag);
                    int end = spanBuilder.length();
                    if (start >= 0 && end > 0 && end > start) {
                        spanBuilder.setSpan(customUrlSpan, start, end, flag);
                    }
                    String nextStr = contentText.toString().substring(mUrlInfos.get(0).end);
                    spanBuilder.append(nextStr);
                } else {
                    //有多个网址
                    for (int i = 0; i < mStringList.size(); i++) {
                        String url = mStringList.get(i);
                        CustomUrlSpan customUrlSpan = new CustomUrlSpan(mContext,url);
                        if (i == 0) {
                            //拼接第1个span的前面文本
                            String headStr =
                                    contentText.toString().substring(0, mUrlInfos.get(0).start);
                            spanBuilder.append(headStr);
                        }
                        if (i == mStringList.size() - 1) {
                            //拼接最后一个span的后面的文本
                            int start = spanBuilder.length();
                            spanBuilder.append(mStringList.get(i), new UnderlineSpan(), flag);
                            int end = spanBuilder.length();
                            if (start >= 0 && end > 0 && end > start) {
                                spanBuilder.setSpan(customUrlSpan, start, end, flag);
                            }
                            String footStr = contentText.toString().substring(mUrlInfos.get(i).end);
                            spanBuilder.append(footStr);
                        }
                        if (i != mStringList.size() - 1) {
                            //拼接两两span之间的文本
                            int start = spanBuilder.length();
                            spanBuilder.append(mStringList.get(i), new UnderlineSpan(), flag);
                            int end = spanBuilder.length();
                            if (start >= 0 && end > 0 && end > start) {
                                spanBuilder.setSpan(customUrlSpan, start, end, flag);
                            }
                            String betweenStr = contentText.toString()
                                    .substring(mUrlInfos.get(i).end,
                                            mUrlInfos.get(i + 1).start);
                            spanBuilder.append(betweenStr);
                        }
                    }
                }
            } else {
                spanBuilder.append(contentText);
            }
            return spanBuilder;
        }

        public class CustomUrlSpan extends ClickableSpan {

            private Context context;
            private String url;
            public CustomUrlSpan(Context context,String url){
                this.context = context;
                this.url = url;
            }
            @Override
            public void onClick(View view) {
                hyperLinkOnClickListener.OnClick(context,url,view);
                HQWLogUtil.logi("点击链接",url);
            }
        }

        public interface HyperLinkOnClickListener{
            void OnClick(Context context,String url,View view);
        }
    }