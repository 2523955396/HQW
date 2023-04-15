

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


#### HQWDialog 弹窗
- setBorderExtension();//扩展边框边距
- setCancelBackgroundShadow;//设置取消背景阴影
- setFullscreen();//去除状态栏 全屏展示
- cancelFullscreen();//正常状态
- setViewFullscreen();//设置View全屏覆盖状态栏
- setGravity();//设置位置 注 如果使用setBorderExtension()会无效
- initView();//初始化界面 //自由继承
- initData();//初始化数据 //自由继承

#### HQWModel(HQWActivity HQWFragment Activity Fragment View模型,建议配合使用) 自定义扩展
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

#### HQWAnimation 动画
- WidghtMoveXY(View view,boolean XorY,Float f,int time);//平移动画 XorY true横向平移 Y纵向平移 f 平移多少px time 时间 毫秒1:1000
- WidghtRotateXY(View view,boolean XorY,Float f,Float ff,int time);//旋转动画 XorY true纵向旋转 false横向旋转 f 旋转的开始角度 ff 旋转的结束角度 time 时间 毫秒1:1000
- WidghtValue(View view,boolean WorH,int i,int time);//高宽调整动画 WorH true为宽 false为高 f 高宽调整大小 time 时间 毫秒1:1000
- ViewDelte(final View view, Animation.AnimationListener animationListener);//Apater删除动画，从下往上收缩 可以用可以不用,自己看情况,建议不用,上面已经有简约动画了;
- HQWViewPagerAnimationOne();//滚筒动画
- HQWViewPagerAnimationTwo();//插入动画

#### HQWDownload 网络文件下载
    /**
     * 下载功能
     * url 超链接
     * file 文件保存地址
     * HttpMachine 下载管理器 可以为null 一键调用
     * callBack调用回调
     */
- DownloadFile(String url, File file, HttpMachine httpMachine, HQWDownloadListener callBack);//下载和取消下载功能
- ResumeDownloadFile(String url, File file, HttpMachine httpMachine, HQWDownloadListener callBack)；下载 暂停 继续下载功能

#### HQWDeviceInfoUtil 设备信息类
- getDeviceWidth(Context context);//获取设备宽度px
- getDeviceHeight(Context context);//获取设备高度px
- getUUID();//获取设备UUID
- getSerialNumber();//获取序列号
- getDeviceManufacturer();//获取厂商名
- getDeviceProduct();//获取产品名
- getDeviceBrand();//获取手机品牌
- getDeviceModel();//获取手机型号
- getDeviceBoard();//获取手机主板名
- getDeviceDevice();//获取手机设备名
- getDeviceFubgerprint();//获取fingerprit 信息
- getDeviceHardware();//获取硬件名
- getDeviceHost();//获取主机
- getDeviceDisplay();//获取显示ID
- getDeviceId();//获取ID
- getDeviceUser();//获取手机用户名
- getDeviceSerial();//获取手机 硬件序列号
- getDeviceSDK();//获取手机Android 系统SDK
- getDeviceAndroidVersion();//获取手机Android 版本
- getDeviceDefaultLanguage();//获取当前手机系统语言。
- getDeviceSupportLanguage();//获取当前系统上的语言列表(Locale列表)

















