package com.huanqi.hqw.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HQWActivity extends AppCompatActivity {
    Toast toast;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setToast(String text){
        if (toast!=null){
            toast.cancel();
        }
        toast= Toast.makeText(this,text,Toast.LENGTH_SHORT);
        toast.show();
    }

    public void GotoActivity(Class<?> activity){
        startActivity(new Intent(this,activity));
        finish();
    }
}
