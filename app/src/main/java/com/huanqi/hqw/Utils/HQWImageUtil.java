package com.huanqi.hqw.Utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.widget.Toast;

import com.huanqi.hqw.Interface.Image.HttpImageBitmap;
import com.huanqi.hqw.Interface.Image.HttpImageDrawable;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
     * Bitmap bitmap 位图
     * File file 文件位置
     * Compressformat   Bitmap.CompressFormat.JPEG PNG WEBP WEBP_LOSSY WEBP_LOSSLESS
     * quality 质量程度 由100到0 从高到低
     * ImageCallback imageCallback 成功失败回调
     */
    public static void saveBitmap(Context context, Bitmap bitmap,File file,Bitmap.CompressFormat compressFormat,int quality,ImageCallback imageCallback) {
        //设置图片名称，要保存png，这里后缀就是png，要保存jpg，后缀就用jpg
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(compressFormat, quality, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 下面的步骤必须有，不然在相册里找不到图片，若不需要让用户知道你保存了图片，可以不写下面的代码。
        // 把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    file.getAbsolutePath(), file.getName(), null);
            imageCallback.onSuccess();
        } catch (FileNotFoundException e) {
            imageCallback.onFail();
            e.printStackTrace();
        }
        //通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                Uri.fromFile(new File(file.getPath()))));
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

    interface ImageCallback{
        void onSuccess();
        void onFail();
    }
}
