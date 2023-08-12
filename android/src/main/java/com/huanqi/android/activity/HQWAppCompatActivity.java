package com.huanqi.android.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.huanqi.android.Interface.HQWOrientation;
import com.huanqi.android.Interface.HQWPermission;
import com.huanqi.android.model.HQWModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HQWAppCompatActivity extends AppCompatActivity {
    Toast toast;
    public HQWPermission permission;
    public HQWOrientation orientation;

    public void setToast(String text) {
        if (toast != null) {
            toast.cancel();
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                toast = Toast.makeText(HQWAppCompatActivity.this, text, Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    public void cancelToast() {
        if (toast != null) {
            toast.cancel();
        }
    }

    public void GotoActivity(Class<?> activity, boolean isfinish) {
        startActivity(new Intent(this, activity));
        if (isfinish) {
            finish();
        }
    }

    public void HQWOrientation(HQWOrientation orientation) {
        this.orientation = orientation;
    }


    public void HQWsetOrientation(String name) {
        if (name.equals("横屏")) {//设置横屏
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else if (name.equals("竖屏")) {//设置竖屏
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else if (name.equals("首选")) {//用户当前的首选方向
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER);
        } else if (name.equals("继承")) {//继承Activity堆栈中当前Activity下面的那个Activity的方向
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_BEHIND);
        } else if (name.equals("自动")) {//由物理感应器决定显示方向，它取决于用户如何持有设备，当 设备 被旋转时方向会随之变化——在横屏与竖屏之间
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
        } else if (name.equals("忽略")) {//忽略物理感应器——即显示方向与物理感应器无关，不管用户如何旋转设备显示方向都不会随着改变("unspecified"设置除外)
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        } else if (name.equals("默认")) {//未指定，此为默认值，由Android系统自己选择适当的方向，选择策略视具体设备的配置情况而定，因此不同的设备会有不同的方向选择
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    public String HQWgetOrientation() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            return "竖屏";
        } else {
            return "横屏";
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (orientation != null) {
            if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                orientation.horizontal();
            } else {
                orientation.vertical();
            }
        }

    }


    ActivityResultLauncher<String[]> resultLauncherpermission = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), new ActivityResultCallback<Map<String, Boolean>>() {
        @Override
        public void onActivityResult(Map<String, Boolean> result) {
            boolean ISNICE = true;
            for (Map.Entry<String, Boolean> maps : result.entrySet()) {
                if (maps.getValue() == false) {
                    ISNICE = false;
                }
            }
            if (ISNICE) {
                permission.onsucceed();
            } else {
                permission.onfailure();
            }
        }
    });

    public void HQWPermissions(String[] permissions, HQWPermission permission) {
        this.permission = permission;
        resultLauncherpermission.launch(permissions);
    }

    public boolean HQWISPermission(String permission) {
        if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED)
            return true;
        else
            return false;
    }

    public List<Boolean> HQWISPermissions(String[] permissions) {
        List<Boolean> booleans = new ArrayList<>();
        for (int i = 0; i < permissions.length; i++) {
            if (ContextCompat.checkSelfPermission(this, permissions[i]) == PackageManager.PERMISSION_GRANTED)
                booleans.add(true);
            else
                booleans.add(false);
        }
        return booleans;
    }


    public void HQWsetScreen(boolean islighting) {
        if (islighting) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);//屏幕常亮
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);//关闭屏幕常亮
        }
    }

    /**
     * 设置使用HQW状态栏
     * 状态栏背景颜色：Color.WHITE
     * 状态栏属性：
     * 该方式已经废弃
     * 请采用WindowInsetsCompat(androidx.core:core-ktx)
     *
     * @deprecated View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR 显示状态栏可设置背景颜色
     * @deprecated View.SYSTEM_UI_FLAG_FULLSCREEN 全屏去除状态栏
     * @deprecated View.SYSTEM_UI_FLAG_HIDE_NAVIGATION 显示状态栏 不显示状态栏文字 不全屏
     * @deprecated View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN 全屏显示状态栏
     *
     *
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void HQWsetStatusBar(int color, int windowInsetsController) {
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(color);//状态栏颜色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.getDecorView().setSystemUiVisibility(windowInsetsController);//状态栏字体颜色
        }
    }

    //获取状态栏的高度 单位px
    public int HQWgetStatusBarHeight() {
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return getResources().getDimensionPixelSize(resourceId);
        }
        return 0;
    }


    HQWModel hqwModel;

    public void initModel(HQWModel hqwModel) {
        this.hqwModel = hqwModel;
        hqwModel.onCreate(this);
    }

    public void setHqwModel(HQWModel hqwModel) {
        this.hqwModel = hqwModel;
    }

    public HQWModel getHqwModel() {
        return hqwModel;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (hqwModel != null) {
            hqwModel.onPause();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (hqwModel != null) {
            hqwModel.onRestart();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (hqwModel != null) {
            hqwModel.onResume();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (hqwModel != null) {
            hqwModel.onDestroy();
        }
    }


    /////////////////////////////////点击空白区域隐藏软键盘 自行集成复制使用///////////////////////////////////////

//    /**
//     * 点击事件x坐标
//     */
//    private float downEventX;
//    /**
//     * 点击事件y坐标
//     */
//    private float downEventY;
//
//    /**
//     * 获取点击事件
//     */
//    @CallSuper
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
//            // 记录按下坐标
//            downEventX = ev.getRawX();
//            downEventY = ev.getRawY();
//        } else if (ev.getAction() == MotionEvent.ACTION_UP
//                || ev.getAction() == MotionEvent.ACTION_CANCEL) {
//            // 处理滑动时不关闭键盘
//            if (ev.getRawX() == downEventX && ev.getRawY() == downEventY) {
//                View view = getCurrentFocus();
//                if (isShouldHideKeyBord(view, ev)) {
//                    hideSoftInput(view.getWindowToken());
//                }
//            }
//        }
//        return super.dispatchTouchEvent(ev);
//    }
//
//    /**
//     * 判定当前是否需要隐藏
//     */
//    protected boolean isShouldHideKeyBord(View v, MotionEvent ev) {
//        if (v != null && (v instanceof EditText)) {
//            int[] l = {0, 0};
//            v.getLocationInWindow(l);
//            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left + v.getWidth();
//            return !(ev.getX() > left && ev.getX() < right && ev.getY() > top && ev.getY() < bottom);
//        }
//        return false;
//    }
//
//    /**
//     * 隐藏软键盘
//     */
//    private void hideSoftInput(IBinder token) {
//        if (token != null) {
//            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//            manager.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
//        }
//    }

    /////////////////////////////////隐藏软键盘///////////////////////////////////////
}
