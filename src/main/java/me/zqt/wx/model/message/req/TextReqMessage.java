package me.zqt.wx.model.message.req;

import lombok.Data;

/**
 * @auther: zqtao
 * @description: 请求消息之文本消息
 * @version: 1.0
 */
@Data
public class TextReqMessage extends ReqBasicMessage {
    /**
     * 消息内容
     */
    private String Content;
}
