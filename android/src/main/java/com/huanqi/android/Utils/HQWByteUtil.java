package com.huanqi.android.Utils;

import android.os.Build;
import android.os.Environment;
import android.os.StatFs;

import java.io.File;
import java.text.DecimalFormat;

/**
 *文件转换
 */
public class HQWByteUtil {


    /**
     * android 7.0及以下按照1024计算 以上按照1000计算
     * 转换文件大小  参数为xxB,结果保留2位小数
     * @param size 单位字节
     */
    public static String ByteConversion1024(long size) {
        DecimalFormat df = new DecimalFormat("0.00");
        if (size < 0) {
            size = 0;
        }
        return size >= 1024 ? size / 1024 >= 1024 ? size / 1024 / 1024 >= 1024 ? df.format((float) size / 1024 / 1024 / 1024) + "GB" : df.format((float) size / 1024 / 1024) + "MB" : df.format((float) size / 1024) + "KB" : size + "B";
    }


    /**
     * android 7.0及以下按照1024计算 以上按照1000计算
     * 转换文件大小  参数为xxB,结果保留2位小数
     * @param size 单位字节
     */
    public static String ByteConversion1000(long size) {
        DecimalFormat df = new DecimalFormat("0.00");
        if (size < 0) {
            size = 0;
        }
        return size >= 1000 ? size / 1000 >= 1000 ? size / 1000 / 1000 >= 1000 ? df.format((float) size / 1000 / 1000 / 1000) + "GB" : df.format((float) size / 1000 / 1000) + "MB" : df.format((float) size / 1000) + "KB" : size + "B";
    }


    /***********************************字节转换率 GB MB KB B android7及以下为1024 安卓7往上是1000*******************************************/

    public static final int BYTE_TYPE_GB = 0;
    public static final int BYTE_TYPE_MB = 1;
    public static final int BYTE_TYPE_KB = 2;
    public static final int BYTE_TYPE_B = 3;

    public static long ByteConversion1024(long size, int BYTE_TYPE) {
        if (size<0){
            return 0;
        }else {
            switch (BYTE_TYPE) {
                case BYTE_TYPE_GB:
                    return size / 1024 / 1024 / 1024;
                case BYTE_TYPE_MB:
                    return size / 1024 / 1024 ;
                case BYTE_TYPE_KB:
                    return size / 1024 ;
                case BYTE_TYPE_B:
                    return size;
                default:
                    return size;
            }
        }
    }


    public static long ByteConversion1000(long size, int BYTE_TYPE) {
        if (size<0){
            return 0;
        }else {
            switch (BYTE_TYPE) {
                case BYTE_TYPE_GB:
                    return size / 1000 / 1000 / 1000;
                case BYTE_TYPE_MB:
                    return size / 1000 / 1000 ;
                case BYTE_TYPE_KB:
                    return size / 1000 ;
                case BYTE_TYPE_B:
                    return size;
                default:
                    return size;
            }
        }
    }



    /**
     * 获取SD卡剩余空间
     * @param BYTE_TYPE 文件输出类型 B KB MB GB
     * */
    public static long getExternalStorageFreeSpace(int BYTE_TYPE) {
        File file = Environment.getExternalStorageDirectory();
        StatFs statFs = new StatFs(file.getPath());
        long availableBlocksLong = statFs.getAvailableBlocksLong();
        long blockSizeLong = statFs.getBlockSizeLong();
        long SDCount = 0;

        if (Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.N) {//1000转换率
            SDCount = ByteConversion1000(availableBlocksLong * blockSizeLong,BYTE_TYPE);
        } else {
            SDCount = ByteConversion1024(availableBlocksLong * blockSizeLong,BYTE_TYPE);
        }
        return SDCount;
    }

}
