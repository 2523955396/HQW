package com.huanqi.hqw.Utils;

import android.graphics.Bitmap;
import android.media.ExifInterface;
import android.text.TextUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;


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
    public static void saveExif(String oldFilePath, String newFilePath) throws Exception {
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
    }
}
