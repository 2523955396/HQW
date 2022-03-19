package com.huanqi.hqw.Utils;

import android.os.Environment;

import java.io.File;
import java.text.DecimalFormat;

public class HQWFileUtil {

    public static File File(String catalogue,String filename){
        File filecatalogue=new File(catalogue);
        if (!filecatalogue.exists()){
            filecatalogue.mkdirs();
        }
        File fileentire=new File(filecatalogue+"/"+filename);
        return fileentire;
    }

    public static File SDFile(String catalogue,String filename){
        File filecatalogue=new File(Environment.getExternalStorageDirectory()+catalogue);
        if (!filecatalogue.exists()){
            filecatalogue.mkdirs();
        }
        File fileentire=new File(filecatalogue+"/"+filename);
        return fileentire;
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

}
