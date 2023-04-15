package com.huanqi.hqw.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.os.AsyncTask;
import android.text.TextUtils;

import com.huanqi.hqw.Interface.Image.HttpImageBitmap;
import com.huanqi.hqw.Interface.Image.HttpImageDrawable;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLConnection;


/**
 * 图片处理类 By焕奇灵动
 */
public class HQWImageUtil {

    /**
    * 图片质量压缩
    * Compressformat   Bitmap.CompressFormat.JPEG PNG WEBP WEBP_LOSSY WEBP_LOSSLESS
    * bmp 图片位图
    * file 图片质量压缩后输出位置
    * quality 质量程度 由100到0 从高到低
    */
    public static void qualityCompress(Bitmap.CompressFormat Compressformat, Bitmap bmp, File file, int quality) {//jpg
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Compressformat, quality, baos);
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
    *图片信息复制
    */
    public static void saveExif(String oldFilePath, String newFilePath)  {
        try {
            ExifInterface oldExif=new ExifInterface(oldFilePath);
            ExifInterface newExif=new ExifInterface(newFilePath);
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
        }catch (Exception v){

        }

    }

    /**
     *网络位图请求
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
     *网络异步请求位图
     */
    public static void AsyncBitmap(String url, HttpImageBitmap httpImageBitmap){
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
     *网络绘制请求
     */
    public static Drawable drawable(String img){
        Drawable drawable = null;
        try {
            URL url = new URL(img);
            URLConnection urlConnection = url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            drawable= Drawable.createFromStream(inputStream,null);
            return drawable;
        } catch (Exception v) {
        }
        return drawable;
    }

    /**
     *网络异步请求绘制
     */
    public static void AsyncDrawable(String url,HttpImageDrawable httpImageDrawable){
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


}
