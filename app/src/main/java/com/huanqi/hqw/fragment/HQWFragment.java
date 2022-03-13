package com.huanqi.hqw.fragment;

import android.content.Intent;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.huanqi.hqw.Interface.permission;

public class HQWFragment extends Fragment {
    Toast toast;
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

    public void GotoActivity(Class<?> activity){
        startActivity(new Intent(getActivity(),activity));
    }
}
