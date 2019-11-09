package me.zqt.wx.service;

import javax.servlet.http.HttpServletRequest;

/**
 * @auther: zqtao
 * @description: 微信消息处理核心service 接口
 * @version: 1.0
 */
public interface WechatMessageService {

    /**
     * 微信新消息处理
     * @param request
     * @return
     */
    String newMessageRequest(HttpServletRequest request);
}
