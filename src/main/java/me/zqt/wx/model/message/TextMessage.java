package me.zqt.wx.model.message;

import lombok.Data;

/**
 * @auther: zqtao
 * @description: 文本消息
 * @version: 1.0
 */
@Data
public class TextMessage extends BaseMessage {
    /**
     * 消息内容
     */
    private String Content;
}
