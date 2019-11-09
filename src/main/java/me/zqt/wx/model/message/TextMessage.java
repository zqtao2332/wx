package me.zqt.wx.model.message;

import lombok.Getter;
import lombok.Setter;
import me.zqt.wx.model.message.BaseMessage;

/**
 * @auther: zqtao
 * @description: 文本消息
 * @version: 1.0
 */
@Setter
@Getter
public class TextMessage extends BaseMessage {
    /**
     * 消息内容
     */
    private String Content;
}
