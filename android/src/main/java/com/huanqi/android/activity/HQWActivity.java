package com.huanqi.android.activity;

import static androidx.activity.result.ActivityResultCallerKt.registerForActivityResult;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Build;
import android.os.IBinder;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.activity.result.contract.ActivityResultContracts.RequestMultiplePermissions;
import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import com.huanqi.android.Interface.HQWOrientation;
import com.huanqi.android.Interface.HQWPermission;
import com.huanqi.android.Utils.HQWLogUtil;
import com.huanqi.android.model.HQWModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HQWActivity extends Activity {
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
                toast = Toast.makeText(HQWActivity.this, text, Toast.LENGTH_SHORT);
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

    /**
     * 获取屏幕竖屏横屏状态
     * @param orientation 状态回调(实时)
     * */
    public void HQWOrientation(HQWOrientation orientation) {
        this.orientation = orientation;
    }


    /**
     * 设置屏幕竖屏横屏状态
     * */
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

    /**
     * 获取屏幕竖屏横屏状态
     * @value 0为竖屏
     * @value 1为横屏
     * */
    public int HQWgetOrientation() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            return 0;
        } else {
            return 1;
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 777) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                permission.onsucceed();
            } else {
                permission.onfailure();
            }

        }
    }

    /**
     * HQW获取权限
     * @param permissions 需要的权限
     * @param permission 获取权限回调
     * @value 建议配合HQWPermissionUtil.PermissionDispose()使用
     * */
    public void HQWPermissions(String[] permissions, HQWPermission permission) {
        this.permission = permission;
        ActivityCompat.requestPermissions(this, permissions, 777);
    }

    /**
     * 判断是否已经获取权限
     * @param permission 需要判断的权限
     * */
    public boolean HQWISPermission(String permission) {
        if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED)
            return true;
        else
            return false;
    }

    /**
     * 判断是否已经获取多个权限
     * @param permissions 需要判断的权限们
     * */
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


    /**
     * 设置屏幕常亮
     * @param isLighting 是否常亮
     * */
    public void HQWsetScreen(boolean isLighting) {
        if (isLighting) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);//屏幕常亮
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);//关闭屏幕常亮
        }
    }

    /**
     * 设置拦截截屏录屏
     * @param isSecure 是否拦截截屏录屏
     * */
    public void HQWsetSecure(boolean isSecure) {
        if (isSecure) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);//不允许截屏录屏
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_SECURE);//允许截屏录屏
        }
    }

    /**
     * 设置使用HQW状态栏导航栏
     * @param statusBarColor 设置状态栏颜色
     * @param statusBarTextColor 设置状态栏文字颜色false为白色 true为黑色
     * @param isShowStatusBar 是否显示状态栏
     * @param navigationBarColor 设置底部导航栏颜色
     * @param NavigationBarTextColor  设置底部导航栏文字颜色false为白色 true为黑色
     * @param isShowStatusBar 是否显示底部状态栏
     * @param isImmerse 是否沉浸状态栏
     */
    public void HQWsetStatusNavigationBar(int statusBarColor, boolean statusBarTextColor, boolean isShowStatusBar, int navigationBarColor, boolean NavigationBarTextColor, boolean isShowNavigationBar,boolean isImmerse) {
        Window window = getWindow();
        window.setStatusBarColor(statusBarColor);
        window.setNavigationBarColor(navigationBarColor);
        WindowCompat.getInsetsController(window, window.getDecorView()).setAppearanceLightStatusBars(statusBarTextColor);
        WindowCompat.getInsetsController(window, window.getDecorView()).setAppearanceLightNavigationBars(NavigationBarTextColor);
        if (isShowStatusBar){
            WindowCompat.getInsetsController(window,window.getDecorView()).show(WindowInsetsCompat.Type.statusBars());
        }else {
            WindowCompat.getInsetsController(window,window.getDecorView()).hide(WindowInsetsCompat.Type.statusBars());
        }
        if (isShowNavigationBar){
            WindowCompat.getInsetsController(window,window.getDecorView()).show(WindowInsetsCompat.Type.navigationBars());
        }else {
            WindowCompat.getInsetsController(window,window.getDecorView()).hide(WindowInsetsCompat.Type.navigationBars());
        }
        WindowCompat.setDecorFitsSystemWindows(window, !isImmerse);
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
