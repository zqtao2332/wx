# 							微信公众号开发
**基于Spring Boot2 框架进行微信公众号二次开发。**

**更多内容信息，请关注微信公众号：怪兽疯了**

<img src=" http://resource.zqtaotao.cn/公众号/怪兽疯了.jpg " alt="怪兽疯了" style="zoom: 45%;" />

**开发所用测试公众号，可选择关注，实时查看版本更新内容，所有的开发测试都是在测试公众号进行。**

<img src=" http://resource.zqtaotao.cn/公众号/PC测试号 .png" alt="PC测试号 " style="zoom:80%;" />

​												**注**：二维码无法显示请点击：**[怪兽疯了](http://resource.zqtaotao.cn/公众号/怪兽疯了.jpg)**    ,      **[测试号](http://resource.zqtaotao.cn/公众号/PC测试号 .png)**

### 开发工具

| 工具         | 版本                      |
| :----------- | :------------------------ |
| IDEA         | 2019                      |
| 本地开发系统 | window10 家庭版           |
| 线上测试系统 | Linux centos7  / Ubuntu19 |
| 内网映射工具 | ngrok window版            |



### 功能表

|       功能项       |
| :----------------: |
|    自定义菜单栏    |
| 自动回复文本、语音 |

### 下载运行

选择要下载的版本，在GitHub上点击：`Clone or download`

修改`me.zqt.wx.constant.SignatureConstant.java`替换填写自己的微信公众号信息。

```
// 开发者自定义token
public static final String TOKEN = "随意填写如：mytoken123";
// 第三方用户唯一凭证
public static final String APP_ID = "xxxxxxxxxxxxxxxxxx";
// 第三方用户唯一凭证密钥
public static final String APP_SECRET = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
```

运行   **`me.zqt.wx.WxApplication`**   即可！



### 版本下载

| 版本    | 下载地址                                                     |
| ------- | ------------------------------------------------------------ |
| V 1.0.0 | [https://github.com/zqtao2332/wx/tree/c9829d2cc9ff564500a39b344798effcccd3a5aa](https://github.com/zqtao2332/wx/tree/c9829d2cc9ff564500a39b344798effcccd3a5aa) |



### 版本更新日志

#### **V 1.0.0**

时间节点：[V1.0.0](https://github.com/zqtao2332/wx/tree/c9829d2cc9ff564500a39b344798effcccd3a5aa)

| 更新内容操作 | 更新描述               |
| :----------: | :--------------------- |
|      +       | 新增自定义菜单         |
|      +       | 新增语音消息接收与响应 |
|      +       | 新增图片消息接收与响应 |
|      +       | 新增文本消息接收与响应 |
|      +       | 微信服务器校验         |

