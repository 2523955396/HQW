package com.huanqi.android.Utils;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.text.TextUtils;


import com.huanqi.android.Interface.Image.HttpImageBitmap;
import com.huanqi.android.Interface.Image.HttpImageDrawable;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLConnection;


/**
 * 图片处理类 By焕奇灵动
 */
public class HQWImageUtil {

    /**
     * 分享图片
     * @param context 上下文
     * @param bitmap  位图
     * @param title 图片标题
     */
    public static void sharePictures(Context context,Bitmap bitmap,String title){
        Uri uri = Uri.parse(MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, title, null));
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        intent.setType("image/*");
        context.startActivity(Intent.createChooser(intent, title));
    }


    /**
     * 图片质量压缩
     *
     * @param compressFormat Bitmap.CompressFormat.JPEG PNG WEBP WEBP_LOSSY WEBP_LOSSLESS
     * @param bitmap         图片位图
     * @param file           图片质量压缩后输出位置
     * @param quality        质量程度 由100到0 从高到低
     */
    public static void qualityCompress(Bitmap.CompressFormat compressFormat, Bitmap bitmap, File file, int quality) {//jpg
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(compressFormat, quality, baos);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存位图
     *
     * @param context        上下文
     * @param bitmap         位图
     * @param file           文件位置
     * @param compressFormat Bitmap.CompressFormat.JPEG PNG WEBP WEBP_LOSSY WEBP_LOSSLESS
     * @param quality        质量程度 由100到0 从高到低
     * @param imageCallback  成功失败回调
     */
    public static void saveBitmap(Context context, Bitmap bitmap, File file, Bitmap.CompressFormat compressFormat, int quality, ImageCallback imageCallback) {
        ContentValues values = new ContentValues();
        //        values.put(MediaStore.Images.Media.DESCRIPTION, "This is an image");//图片提示信息
        //        values.put(MediaStore.Images.Media.TITLE, System.currentTimeMillis()+".png");//图片标题
        values.put(MediaStore.Images.Media.DISPLAY_NAME, file.getName());//图片名称
        values.put(MediaStore.Images.Media.RELATIVE_PATH, "DCIM/Camera");//储存地址
        if (compressFormat == Bitmap.CompressFormat.PNG) {
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/png");//图片类型
        } else if (compressFormat == Bitmap.CompressFormat.JPEG) {
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");//图片类型
        } else if (compressFormat == Bitmap.CompressFormat.WEBP) {
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/webp");//图片类型
        }


        ContentResolver resolver = context.getContentResolver();
        Uri insertUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);//添加到图库
        if (insertUri != null) {
            try {
                OutputStream outputStream = resolver.openOutputStream(insertUri);
                bitmap.compress(compressFormat, quality, outputStream);
                imageCallback.onSuccess(file);
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                imageCallback.onFail(file);
            }
        }
        //通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                Uri.fromFile(new File(file.getPath()))));
    }


    /**
     * 图片信息复制
     *
     * @param oldFilePath 旧图片地址
     * @param newFilePath 新图片地址
     */
    public static void saveExif(String oldFilePath, String newFilePath) {
        try {
            ExifInterface oldExif = new ExifInterface(oldFilePath);
            ExifInterface newExif = new ExifInterface(newFilePath);
            Class<ExifInterface> cls = ExifInterface.class;
            Field[] fields = cls.getFields();
            for (int i = 0; i < fields.length; i++) {
                String fieldName = fields[i].getName();
                if (!TextUtils.isEmpty(fieldName) && fieldName.startsWith("TAG")) {
                    String fieldValue = fields[i].get(cls).toString();
                    String attribute = oldExif.getAttribute(fieldValue);
                    if (attribute != null) {
                        newExif.setAttribute(fieldValue, attribute);
                    }
                }
            }
            newExif.saveAttributes();
        } catch (Exception v) {

        }

    }

    /**
     * 网络位图请求
     *
     * @param img 图片网络地址
     */
    public static Bitmap bitmap(String img) {
        Bitmap bitmap = null;
        try {
            URL url = new URL(img);
            URLConnection urlConnection = url.openConnection();

            InputStream inputStream = urlConnection.getInputStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
            return bitmap;
        } catch (Exception v) {
        }
        return bitmap;
    }

    /**
     * 网络异步请求位图
     *
     * @param url             图片网络地址
     * @param httpImageBitmap 异步请求回调
     */
    public static void AsyncBitmap(String url, HttpImageBitmap httpImageBitmap) {
        new AsyncTask<String, String, Bitmap>() {
            @Override
            protected Bitmap doInBackground(String... strings) {
                return bitmap(url);
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                super.onPostExecute(bitmap);
                httpImageBitmap.callback(bitmap);
            }
        }.execute();
    }

    /**
     * 网络绘制请求
     *
     * @param img 图片网络地址
     */
    public static Drawable drawable(String img) {
        Drawable drawable = null;
        try {
            URL url = new URL(img);
            URLConnection urlConnection = url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            drawable = Drawable.createFromStream(inputStream, null);
            return drawable;
        } catch (Exception v) {
        }
        return drawable;
    }

    /**
     * 网络异步请求绘制
     *
     * @param url               图片网络地址
     * @param httpImageDrawable 异步请求回调
     */
    public static void AsyncDrawable(String url, HttpImageDrawable httpImageDrawable) {
        new AsyncTask<String, String, Drawable>() {
            @Override
            protected Drawable doInBackground(String... strings) {
                return drawable(url);
            }

            @Override
            protected void onPostExecute(Drawable drawable) {
                super.onPostExecute(drawable);
                httpImageDrawable.callback(drawable);
            }
        }.execute();
    }

    public interface ImageCallback {
        public void onSuccess(File file);

        public void onFail(File file);
    }
}
