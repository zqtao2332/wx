package me.zqt.wx.service.message;

import java.util.Map;

/**
 * @auther: zqtao
 * @description: 图片消息业务层
 * @version: 1.0
 */
public interface TextMessageService {

     String messageHandler(Map<String, String> requestMap);
}
