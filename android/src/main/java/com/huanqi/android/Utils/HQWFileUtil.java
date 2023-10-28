package com.huanqi.android.Utils;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.FileUtils;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.OpenableColumns;

import androidx.annotation.RequiresApi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;

/**
 * 文件处理类 By焕奇灵动
 */
public class HQWFileUtil {

    /**
     * 创建普通文件
     */
    public static File File(String catalogue,String filename){
        File filecatalogue=new File(catalogue);
        if (!filecatalogue.exists()){
            filecatalogue.mkdirs();
        }
        File fileentire=new File(filecatalogue+"/"+filename);
        return fileentire;
    }

    /**
     * 创建SD卡文件
     */
    public static File SDFile(String catalogue,String filename){ //catalogue格式为  /电影   filename格式为 喜洋洋.mp4
        File filecatalogue=new File(Environment.getExternalStorageDirectory()+catalogue);
        if (!filecatalogue.exists()){
            filecatalogue.mkdirs();
        }
        File fileentire=new File(filecatalogue+"/"+filename);
        return fileentire;
    }

    /**
     * 手机视频路径
     */
    public static File Movies(Context context,String filename){
        File file=context.getExternalFilesDir(Environment.DIRECTORY_MOVIES);
        if (!file.exists()){
            file.mkdirs();
        }
        return new File(file+"/"+filename);
    }

    /**
     * 手机图片路径
     */
    public static File Pictures(Context context,String filename){
        File file=context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if (!file.exists()){
            file.mkdirs();
        }
        return new File(file+"/"+filename);
    }

    /**
     * 手机音乐路径
     */
    public static File Music(Context context,String filename){
        File file=context.getExternalFilesDir(Environment.DIRECTORY_MUSIC);
        if (!file.exists()){
            file.mkdirs();
        }
        return new File(file+"/"+filename);
    }

    /**
     * 手机下载路径
     */
    public static File Downloads(Context context,String filename){
        File file=context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
        if (!file.exists()){
            file.mkdirs();
        }
        return new File(file+"/"+filename);
    }

    /**
     * 手机DCIM路径
     */
    public static File DCIM(Context context,String filename){
        File file=context.getExternalFilesDir(Environment.DIRECTORY_DCIM);
        if (!file.exists()){
            file.mkdirs();
        }
        return new File(file+"/"+filename);
    }

    /**
     * 手机DOCUMENTS路径
     */
    public static File Documents(Context context,String filename){
        File file=context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        if (!file.exists()){
            file.mkdirs();
        }
        return new File(file+"/"+filename);
    }


    /**
     * 复制文件
     */
    public static File copyFile(InputStream inputStream,File file) {
        OutputStream outPut = null;
        try {
            outPut = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int read;
            while ((read = inputStream.read(buffer)) != -1) {
                outPut.write(buffer, 0, read);
            }
            inputStream.close();
            outPut.flush();
            outPut.close();
            return file;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除所有文件
     */
    public static void deleteFile(File file) {
        if (file.exists()) {
            if (file.isFile()) {
                file.delete();
            } else if (file.isDirectory()) {
                File files[] = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    deleteFile(files[i]);
                }
            }
            file.delete();
        } else {
            System.out.println("删除文件不存在");
        }
    }

    /**
     * 转换文件大小  参数为xxB,结果保留2位小数
     */
    public static String getPrintSize(long size) {
        DecimalFormat df=new DecimalFormat("0.00");
        if (size < 0) {
            size = 0;
        }
        return size >= 1024 ? size / 1024 >= 1024 ? size / 1024 / 1024 >= 1024 ? df.format((float)size / 1024 / 1024 / 1024) + "GB" : df.format((float)size / 1024 / 1024) + "MB" : df.format((float)size / 1024) + "KB" : size + "B";
    }


    /**
     * Uri格式转换为FilePath路径
     */
    public static String UritoFilePath(Context context, Uri imageUri) {
        if (context == null || imageUri == null) {
            return null;
        }

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return getRealFilePath(context, imageUri);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.Q && DocumentsContract.isDocumentUri(context, imageUri)) {
            if (isExternalStorageDocument(imageUri)) {
                String docId = DocumentsContract.getDocumentId(imageUri);
                String[] split = docId.split(":");
                String type = split[0];
                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            } else if (isDownloadsDocument(imageUri)) {
                String id = DocumentsContract.getDocumentId(imageUri);
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
                return getDataColumn(context, contentUri, null, null);
            } else if (isMediaDocument(imageUri)) {
                String docId = DocumentsContract.getDocumentId(imageUri);
                String[] split = docId.split(":");
                String type = split[0];
                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                String selection = MediaStore.Images.Media._ID + "=?";
                String[] selectionArgs = new String[]{split[1]};
                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            return uriToFileApiQ(context,imageUri);
        }
        else if ("content".equalsIgnoreCase(imageUri.getScheme())) {
            if (isGooglePhotosUri(imageUri)) {
                return imageUri.getLastPathSegment();
            }
            return getDataColumn(context, imageUri, null, null);
        }
        else if ("file".equalsIgnoreCase(imageUri.getScheme())) {
            return imageUri.getPath();
        }
        return null;
    }

    //android4.4以下使用
    private static String getRealFilePath(final Context context, final Uri uri) {
        if (null == uri) {
            return null;
        }
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            String[] projection = {MediaStore.Images.ImageColumns.DATA};
            Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }
    private static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    private static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    private static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        String column = MediaStore.Images.Media.DATA;
        String[] projection = {column};
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return null;
    }

    private static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    private static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }


    //Android10以上另一种写法
    private static String getFileFromContentUri(Context context, Uri uri) {
        if (uri == null) {
            return null;
        }
        String filePath;
        String[] filePathColumn = {MediaStore.MediaColumns.DATA, MediaStore.MediaColumns.DISPLAY_NAME};
        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursor = contentResolver.query(uri, filePathColumn, null,
                null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            try {
                filePath = cursor.getString(cursor.getColumnIndex(filePathColumn[0]));
                return filePath;
            } catch (Exception e) {
            } finally {
                cursor.close();
            }
        }
        return "";
    }

    //Android10以上适配
    @RequiresApi(api = Build.VERSION_CODES.Q)
    private static String uriToFileApiQ(Context context, Uri uri) {
        File file = null;
        //android10以上转换
        if (uri.getScheme().equals(ContentResolver.SCHEME_FILE)) {
            file = new File(uri.getPath());
        } else if (uri.getScheme().equals(ContentResolver.SCHEME_CONTENT)) {
            //把文件复制到沙盒目录
            ContentResolver contentResolver = context.getContentResolver();
            Cursor cursor = contentResolver.query(uri, null, null, null, null);
            if (cursor.moveToFirst()) {
                String displayName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                try {
                    InputStream is = contentResolver.openInputStream(uri);
                    File cache = new File(context.getExternalCacheDir().getAbsolutePath(), Math.round((Math.random() + 1) * 1000) + displayName);
                    FileOutputStream fos = new FileOutputStream(cache);
                    FileUtils.copy(is, fos);
                    file = cache;
                    fos.close();
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return file.getAbsolutePath();
    }

}
