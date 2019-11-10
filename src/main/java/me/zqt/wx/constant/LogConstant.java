package me.zqt.wx.constant;

/**
 * @auther: zqtao
 * @description: 日志输入规范常量类
 * @version: 1.0
 */
public class LogConstant {
    public static final String LOG_INFO = "---->>>  ====>  INFO  <====  <<<----";
    public static final String LOG_ERROR = "---->>>  ====>  ERROR  <====  <<<----";
    public static final String LOG_DEBUG = "---->>>  ====>  DEBUG  <====  <<<----";

    // 用法
    // log.info(LogConstant.LOG_INFO.replace("INFO", "内容"));
    // log.info(LogConstant.LOG_ERROR.replace("ERROR", "内容"));
    // log.info(LogConstant.LOG_DEBUG.replace("DEBUG", "内容"));
}
