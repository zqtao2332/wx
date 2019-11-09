package me.zqt.wx.utils;

import me.zqt.wx.model.message.BaseMessage;

import java.util.Date;

/**
 * @auther: zqtao
 * @description: 微信消息响应类型封装工具，泛型转换成需要的类型
 * 抽取共有属性：String fromUserName, String toUserName, String msgType
 * T extends BaseMessage
 * @version: 1.0
 */
public class MessageRespFactoryUtil<T extends BaseMessage> {

    /**
     * @param messageModel 消息类型实例 如文本类型 TextMessage 对象
     * @param fromUserName fromUserName
     * @param toUserName toUserName
     * @param msgType 消息类型
     * @return 根据传递的 messageModel 对象的类型，封装共有属性，返回需要的对象实例
     */
    public T getInstance(T messageModel, String fromUserName, String toUserName, String msgType) {

        messageModel.setToUserName(fromUserName);
        messageModel.setFromUserName(toUserName);
        messageModel.setCreateTime(new Date().getTime());
        messageModel.setMsgType(msgType);

        return (T) messageModel;
    }
}
