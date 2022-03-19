package com.huanqi.hqw.fragment;

import android.content.Intent;
import android.content.res.Configuration;
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

    public  void HQWorientation(orientation orientation){
        this.orientation=orientation;
    }

    public String HQWgetorientation(){
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            return "竖屏";
        }else {
            return "横屏";
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            orientation.horizontal();
        }else{
            orientation.vertical();
        }
    }
}
