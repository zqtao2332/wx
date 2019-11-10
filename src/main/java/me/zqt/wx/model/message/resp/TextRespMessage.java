package me.zqt.wx.model.message.resp;

import lombok.Data;
import me.zqt.wx.model.message.BasicMessage;

/**
 * @auther: zqtao
 * @description: 响应消息之文本消息
 * @version: 1.0
 */
@Data
public class TextRespMessage extends BasicMessage {
    /**
     * 消息内容
     */
    private String Content;
}
