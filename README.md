### 焕奇灵动[v1.0]

#### 关于
- 让开发者在更短的代码中实现动画效果
- 本库功能针对界面,动画,下载,上传实现封装,后续更会增加多线程传输,影音解码,字幕外挂,字幕调节等等
- 本库先进入测试阶段,更多详细内容请看Api

#### 安装教程  ![最新版本](https://jitpack.io/v/com.gitee.BAILIS/HQW.svg "version")

setting.gradle
```
repositories {
        maven { url 'https://jitpack.io' }
    }
```
build.gradle
```
implementation 'com.gitee.BAILIS:HQW:version'
```
#### 准备进行and进行中的计划
1. 简易封装各组件,达成最简化
2. 下载功能简化 下载进度 下载成功 下载失败 网络监测
3. 设备信息获取,界面监听,位移,旋转,动画渲染,图片绘制
4. 文件转换简化 字节转换为带标签 MB GB 文件File转Uri Uri转File
5. 对兼容各大android系统做准备并通知

#### Api文档详细 
[点击查看](https://gitee.com/BAILIS/HQW/blob/master/Api.md)


#### 整库情况
1. 已支持安卓13 MinSDK21 TarGetSDK33
2. 内含EXOPlayer Ffmpeg扩展仓库,需要自取video Module
