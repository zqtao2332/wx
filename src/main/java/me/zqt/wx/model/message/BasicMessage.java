package me.zqt.wx.model.message;

import lombok.Data;

/**
 * @auther: zqtao
 * @description: 消息常规基类：所有类型的消息都共通的特征
 * @version: 1.0
 */
@Data
public class BasicMessage {
    /**
     * 开发者微信号
     */
    private String ToUserName;
    /**
     * 发送方帐号（一个OpenID）
     */
    private String FromUserName;
    /**
     * 消息创建时间 （整型）
     */
    private long CreateTime;
    /**
     * 消息类型（text/image/location/link）
     */
    private String MsgType;
}
