

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
- HQWsetStatusNavigationBar(int statusBarColor, boolean statusBarTextColor, boolean isShowStatusBar, int navigationBarColor, boolean NavigationBarTextColor, boolean isShowNavigationBar);//设置状态栏导航栏状态及颜色
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

#### HQWFileUtil 文件处理类
- File(String catalogue,String filename);//目录,文件名 如果文件名为空自动创建
- SDFile(String catalogue,String filename);//手机SD卡目录,文件名 如果文件名为空自动创建
- deleteFile(File file);//删除目录下所以文件
- getPrintSize();//文件大小转换 参数为xxB,结果保留2位小数
- UritoFilePath(Context context, Uri imageUri);//Uri格式转换为FilePath路径 返回String类型
- getRealFilePath(final Context context, final Uri uri);//Uri格式转换为FilePath路径 返回String类型 安卓4.4以下使用
- uriToFileApiQ(Context context, Uri uri);//Uri格式转换为FilePath路径 安卓10以上适配

#### HQWFragmentUtil Fragment处理类
- removeAllFragments(FragmentActivity context);//删除所有fragment

#### HQWImageUtil 图片处理类
    /**
    * 图片质量压缩
    * Compressformat   Bitmap.CompressFormat.JPEG PNG WEBP WEBP_LOSSY WEBP_LOSSLESS
    * bmp 图片位图
    * file 图片质量压缩后输出位置
    * quality 质量程度 由100到0 从高到低
    */
- qualityCompress(Bitmap.CompressFormat Compressformat, Bitmap bmp, File file, int quality);
- saveExif(String oldFilePath, String newFilePath);//图片信息复制
- bitmap(String img);网络位图请求 返回Bitmap
- AsyncBitmap(String url, HttpImageBitmap httpImageBitmap);//网络异步请求位图
- drawable(String img);//网络绘制请求
- AsyncDrawable(String url,HttpImageDrawable httpImageDrawable);网络异步请求绘制

#### HQWJsonUtil Json处理类
- ObjectGetValue(String message,String key);//Json {} 格式给message和key拿到 value
- ArrayGetValue(String message,int key);//ArrayJson [] 格式给message和key(第几个)拿到 value

#### HQWMd5Util MD5加密处理类
- getResult(String s);//md5加密 大写

#### HQWSDCardUtil SD卡信息类
- getRAMInfo(Context context);//获取手机RAM 信息
- isSDCardMount();//判断SD是否挂载
- getStorageInfo(Context context, int type);//获取手机存储ROM信息 type 0为内部储存 1为外部储存
- getStoragePath(Context context, int type);//使用反射方法获取手机存储路径 type 0为内部储存 1为外部储存 在填写1的时候如果没有外部储存会导致闪退
- getTotalRAM(Context context);//获取 手机 RAM 信息
- getTotalRAMOther(Context context);//获取 手机 RAM 信息
- getAvailableRAM(Context context);//获取手机可用RAM
- getTotalInternalMemorySize(Context context);//获取手机内部存储空间 以M,G为单位的容量
- getAvailableInternalMemorySize(Context context);//获取手机内部可用存储空间 以M,G为单位的容量
- getTotalExternalMemorySize(Context context);//获取手机外部存储空间 以M,G为单位的容量
- getAvailableExternalMemorySize(Context context);//获取手机外部可用存储空间 以M,G为单位的容量
- getSDCardInfo();//SD卡信息
- getSDCardTotalStorage(long totalByte);//获取 SD 卡总存储空间

#### HQWTimeUtil 帧时间处理类
- ValueForTime(int timeMs);//时间转换

#### HQWWeightUtil Weight处理类
- dptopx(Context context, float dpValue);//DP转换为PX
- pxtodp(Context context, float pxValue);//PX转换为DP
- hideSystemNavigationBar(Activity activity);//隐藏标题栏
- showSystemNavigationBar(Activity activity);//显示标题栏

#### HQWUtil  
- getVersionCode(Context context);//获取版本号
- getVersionName(Context context)；//获取版本名称
- InstallApk(Context context, File file);//安装apk

