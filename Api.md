

#### HQWActivity
- setToast(String text);//原生提示
- cancelToast();//原生提示清除
- GotoActivity(Class<?> activity, boolean isfinish);//跳转Activity界面 是否清除本界面
- HQWsetOrientation(String name);//设置屏幕方式 横屏 竖屏 继承 自动等等
- HQWgetOrientation();//获取屏幕旋转状态 横屏或竖屏 返回String字符串方式
- HQWOrientation(HQWOrientation orientation);//旋转监听 横屏或竖屏
- HQWPermissions(String[] permissions, HQWPermission permission);//权限获取
- HQWISPermission(String permission);//权限是否获取
- HQWISPermissions(String[] permissions);//多个权限是否获取
- HQWsetScreen(boolean islighting);//设置屏幕是否常亮
- HQWsetStatusBar(int color, int state);//设置状态栏颜色和形态;
- HQWgetStatusBarHeight();//获取状态栏高度 单位 px
- initModel();//适配HQWModel



#### HQWActivity
- setToast(String text);//原生提示
- cancelToast();//原生提示清除
- GotoActivity(Class<?> activity, boolean isfinish);//跳转Activity界面 是否清除本界面
- HQWsetOrientation(String name);//设置屏幕方式 横屏 竖屏 继承 自动等等
- HQWgetOrientation();//获取屏幕旋转状态 横屏或竖屏 返回String字符串方式
- HQWOrientation(HQWOrientation orientation);//旋转监听 横屏或竖屏
- HQWPermissions(String[] permissions, HQWPermission permission);//权限获取
- HQWISPermission(String permission);//权限是否获取
- HQWISPermissions(String[] permissions);//多个权限是否获取
- HQWsetScreen(boolean islighting);//设置屏幕是否常亮
- HQWsetStatusBar(int color, int state);//设置状态栏颜色和形态;
- HQWgetStatusBarHeight();//获取状态栏高度 单位 px
- initModel();//适配HQWModel