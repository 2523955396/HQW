package com.huanqi.android.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebStorage;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 富文本Webview
 * by 焕奇灵动
 */

public class RichWebView extends WebView {

    private List<String> images = new ArrayList<>();//图片集合

    OnImageListener onImageListener;

    public void setOnImageListener(OnImageListener onImageListener) {
        this.onImageListener = onImageListener;
    }

    public void setRithText(String data) {
        loadDataWithBaseURL(null, data, "text/html", "utf-8", null);
    }

    public RichWebView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        webConfig();
        init();
    }


    public void webConfig() {
        WebSettings webSettings = getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setMediaPlaybackRequiresUserGesture(false);
        webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);//https http混淆
        webSettings.setAllowFileAccess(true);
        webSettings.setLoadWithOverviewMode(true);
//        webSettings.setUseWideViewPort(true);
//        webSettings.setSupportZoom(true);

        webSettings.setGeolocationEnabled(true);
        webSettings.setDefaultTextEncodingName("utf-8");
//        webSettings.setUserAgentString("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36");
    }

    public void init() {
        setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                String jsCode = "javascript:(function(){" +
                        "var imgs=document.getElementsByTagName(\"img\");" +
                        "for(var i=0;i<imgs.length;i++){" +
                        "imgs[i].onclick=function(){" +
                        "window.ImageOnclick.image(this.src);" +
                        "}}})()";
                loadUrl(jsCode);
//                loadUrl("javascript:window.APP.resize(document.body.getBoundingClientRect().height)");
            }
        });

        addJavascriptInterface(new ImageOnclick() {
            @JavascriptInterface
            @Override
            public void image(String url) {
                for (int i = 0; i < images.size(); i++) {
                    if (images.get(i).equals(url)) {
                        setImageListenerCallback(1, images, i);
                    }
                }
            }
        }, "ImageOnclick");


        setOnLongClickListener(view -> {
            String clickUrl = getHitTestResult().getExtra();
            if (clickUrl != null) {
                if (clickUrl.contains(".png") || clickUrl.contains(".jpg") || clickUrl.contains(".jpeg") || clickUrl.contains(".webp")) {//这里主要处理图片,如果需要video自行添加
                    for (int i = 0; i < images.size(); i++) {
                        if (images.get(i).equals(clickUrl)) {
                            setImageListenerCallback(2, images, i);
                        }
                    }
                }
            }
            return true;
        });
    }


    private void setImageListenerCallback(int type, List<String> images, int position) {
        post(new Runnable() {
            @Override
            public void run() {
                switch (type) {
                    case 1:
                        onImageListener.ImageOnclick(images, position);
                        break;
                    case 2:
                        onImageListener.ImageOnLongclick(images, position);
                        break;
                }
            }
        });
    }

    //HTML图片单击事件
    private interface ImageOnclick {
        void image(String url);
    }

    //富文本处理
    @Override
    public void loadDataWithBaseURL(@Nullable String baseUrl, @NonNull String data, @Nullable String mimeType, @Nullable String encoding, @Nullable String historyUrl) {
        super.loadDataWithBaseURL(baseUrl, getNewContent(data), mimeType, encoding, historyUrl);
        images = getImgStr(data);//长按的图片集合

    }


    public interface OnImageListener {
        void ImageOnclick(List<String> images, int position);

        void ImageOnLongclick(List<String> images, int position);
    }

    @Override
    public void destroy() {
        super.destroy();
        WebStorage.getInstance().deleteAllData();
        clearCache(true);
        clearHistory();
        clearFormData();
        clearSslPreferences();
        clearMatches();
        getContext().deleteDatabase("webview.db");
        getContext().deleteDatabase("webviewCache.db");
    }


    ////////////////////////工具处理///////////////////////////

    //  implementation 'org.jsoup:jsoup:1.15.4'
    public static String getNewContent(String htmltext) {//给html img添加属性
        Document doc = Jsoup.parse(htmltext);
        Elements elements = doc.getElementsByTag("img");
        for (Element element : elements) {
            element.attr("width", "100%").attr("height", "auto");
        }
        return doc.toString();
    }


    /**
     * 获取图片字符串中所有链接
     */
    public static List<String> getImgStr(String htmlStr) {
        List<String> pics = new ArrayList<>();
        String img = "";
        Pattern p_image;
        Matcher m_image;
        //     String regEx_img = "<img.*src=(.*?)[^>]*?>"; //图片链接地址
        String regEx_img = "<img.*src\\s*=\\s*(.*?)[^>]*?>";
        p_image = Pattern.compile
                (regEx_img, Pattern.CASE_INSENSITIVE);
        m_image = p_image.matcher(htmlStr);
        while (m_image.find()) {
            // 得到<img />数据
            img = m_image.group();
            // 匹配<img>中的src数据
            Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(img);
            while (m.find()) {
                pics.add(m.group(1));
            }
        }
        return pics;
    }
}
