package com.huanqi.hqw.fragment;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.huanqi.hqw.Interface.orientation;
import com.huanqi.hqw.Interface.permission;

public class HQWFragment extends Fragment {
    Toast toast;
    public orientation orientation;
    public void setToast(String text){
        if (toast!=null){
            toast.cancel();
        }
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                toast= Toast.makeText(getActivity(),text,Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    public void GotoActivity(Class<?> activity,boolean isflash){
        startActivity(new Intent(getActivity(),activity));
        if (isflash){
            getActivity().finish();
        }
    }

    public  void HQWOrientation(orientation orientation){
        this.orientation=orientation;
    }

    public void HQWsetOrientation(String name){
        if (name.equals("横屏")){//设置横屏
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }else if (name.equals("竖屏")){//设置竖屏
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }else if (name.equals("首选")){//用户当前的首选方向
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER);
        }
        else if (name.equals("继承")){//继承Activity堆栈中当前Activity下面的那个Activity的方向
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_BEHIND);
        }
        else if (name.equals("自动")){//由物理感应器决定显示方向，它取决于用户如何持有设备，当 设备 被旋转时方向会随之变化——在横屏与竖屏之间
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
        }
        else if (name.equals("忽略")){//忽略物理感应器——即显示方向与物理感应器无关，不管用户如何旋转设备显示方向都不会随着改变("unspecified"设置除外)
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        }
        else if (name.equals("默认")){//未指定，此为默认值，由Android系统自己选择适当的方向，选择策略视具体设备的配置情况而定，因此不同的设备会有不同的方向选择
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    public String HQWgetOrientation(){
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            return "竖屏";
        }else {
            return "横屏";
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (orientation!=null){
            if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
                orientation.horizontal();
            }else{
                orientation.vertical();
            }
        }
    }

    public void HQWsetScreen(boolean islighting){
        if (islighting){
            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);//屏幕常亮
        }else {
            getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);//关闭屏幕常亮
        }
    }


    public void initView(){

    }
    public void initData(){

    }
}
