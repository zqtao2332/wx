package me.zqt.wx.constan;

/**
 * @auther: zqtao
 * @description:
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
