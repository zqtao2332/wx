package me.zqt.wx.utils;

import java.io.UnsupportedEncodingException;

/**
 * @auther: zqtao
 * @description: 通用工具类
 * @version: 1.0
 */
public class CommonUtil {
    /**
     * 计算采用utf-8编码方式时字符串所占字节数
     *
     * @param content 需要计算的内容
     * @return 字节数
     */
    public static int getByteSize(String content) {
        if (content != null) {
            try {
                return content.getBytes("utf-8").length;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }
}
