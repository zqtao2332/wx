package me.zqt.wx.service.message;

import java.util.Map;

/**
 * @auther: zqtao
 * @description: 图片消息业务层
 * @version: 1.0
 */
public interface VoiceMessageService {

    String messageHandler(Map<String, String> requestMap);
}
