package com.huanqi.android.Utils;

import android.Manifest;
import android.os.Build;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件权限处理类
 * */
public class HQWPermissionUtil {
    /**
     * @param InputPermissions 需要处理的安卓动权
     * 安卓动权修改如下:
     * @旧:(文件读写)||安卓13-
     * READ_EXTERNAL_STORAGE
     * WRITE_EXTERNAL_STORAGE
     * @新:(文件读写)||安卓13&+
     * READ_MEDIA_IMAGES
     * READ_MEDIA_AUDIO
     * READ_MEDIA_VIDEO
     * @旧:(蓝牙权限)||安卓12&+
     * BLUETOOTH_CONNECT
     */
    public static String[] PermissionDispose(String[] InputPermissions) {//安卓13动权处理
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < InputPermissions.length; i++) {
            if (Build.VERSION.SDK_INT >= 33) {//当SDK等级大于等于安卓13时
                if (InputPermissions[i] != Manifest.permission.READ_EXTERNAL_STORAGE
                        &&InputPermissions[i] != Manifest.permission.WRITE_EXTERNAL_STORAGE) {//当sdk高于33时不具备read write识别权限
                    stringList.add(InputPermissions[i]);
                }
            } else if (Build.VERSION.SDK_INT>=31&&Build.VERSION.SDK_INT < 33){//当SDK等级大于等于安卓12时
                if (InputPermissions[i] != Manifest.permission.READ_MEDIA_AUDIO
                        &&InputPermissions[i] != Manifest.permission.READ_MEDIA_IMAGES
                        &&InputPermissions[i] != Manifest.permission.READ_MEDIA_VIDEO) {//当sdk高于等于31时需要全新蓝牙权限 BLUETOOTH_CONNECT
                    stringList.add(InputPermissions[i]);
                }

            }//todo 如果有其他版本需要单独修改
            else {
                if (InputPermissions[i] != Manifest.permission.READ_MEDIA_AUDIO
                        &&InputPermissions[i] != Manifest.permission.READ_MEDIA_IMAGES
                        &&InputPermissions[i] != Manifest.permission.READ_MEDIA_VIDEO
                        &&InputPermissions[i] != Manifest.permission.BLUETOOTH_CONNECT) {//当sdk低于33时不具备audio image video识别权限
                    stringList.add(InputPermissions[i]);
                }
            }
        }
        String[] OutPermissions = new String[stringList.size()];
        for (int i = 0; i < stringList.size(); i++) {
            OutPermissions[i] = stringList.get(i);
        }
        return OutPermissions;
    }
}
