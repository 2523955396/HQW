### 焕奇灵动Beta[v1.0]

#### 关于
- 让开发者在更短的代码中实现动画效果
- 本库功能针对界面,动画,下载,上传实现封装,后续更会增加多线程传输,影音解码,字幕外挂,字幕调节等等
- 本库先进入测试阶段,更多详细内容请看完成的计划

#### 安装教程  ![最新版本](https://jitpack.io/v/com.gitee.BAILIS/HQW.svg "version")

setting.gradle
```
repositories {
        maven { url 'https://jitpack.io' }
    }
```
build.gradle
```
implementation 'com.gitee.BAILIS:HQW:'version'
```
#### 准备进行and进行中的计划
1. 简易封装各组件,达成最简化
2. 下载功能简化 下载进度 下载成功 下载失败 网络监测
3. 上传功能简化 上传进度 上传成功 上传失败 网络监测
4. 文件转换简化 字节转换为带标签 MB GB 文件File转Uri Uri转File
5. 对兼容各大android系统做准备并通知

#### 完成的计划
1. 权限获取(Beta v0.3)
2. 文件下载(Beta v0.3.2)


#### 整库情况
1. 本库支持android6~11,android12正在预备适配中
