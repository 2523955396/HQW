

#### HQWActivity  注:HQWFragment与之几乎一样,所以不单独板书
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
- initView();//初始化界面 //自由继承
- initData();//初始化数据 //自由继承


#### HQWDialog
- setBorderExtension();//扩展边框边距
- setCancelBackgroundShadow;//设置取消背景阴影
- setFullscreen();//去除状态栏 全屏展示
- cancelFullscreen();//正常状态
- setViewFullscreen();//设置View全屏覆盖状态栏
- setGravity();//设置位置 注 如果使用setBorderExtension()会无效
- initView();//初始化界面 //自由继承
- initData();//初始化数据 //自由继承

#### HQWModel(HQWActivity HQWFragment View模型,建议配合使用) 自定义扩展
- onCreate();//创建
- onCreate(View view);//创建View
- onCreate(View view, Context context);//创建View 带上下文
- onCreate(HQWActivity hqwActivity);//创建HQWActivity
- onCreate(HQWActivity hqwActivity,View view);//创建HQWActivity View
- onCreate(HQWFragment hqwFragment);//创建HQWFragment
- onCreate(HQWFragment hqwFragment,View view);//创建HQWFragment View
- onPause();//界面暂停扩展
- onRestart();//刷新扩展
- onResume();//重启扩展
- onDestroy();//销毁扩展
- initView();//初始化界面 //自由继承
- initData();//初始化数据 //自由继承


















