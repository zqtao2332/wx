package me.zqt.wx.utils;

import me.zqt.wx.model.message.BasicMessage;

import java.util.Date;
import java.util.Map;

/**
 * @auther: zqtao
 * @description: 微信消息响应类型封装工具，泛型转换成需要的类型
 * 抽取共有属性：String fromUserName, String toUserName, String msgType
 * T extends BaseMessage
 * <p>
 * 例如传入 TextRespMessage 则返回封装了共有属性值的 TextRespMessage对象
 * @version: 1.0
 */
public class MessageRespFactoryUtil<T extends BasicMessage> {

    /**
     * @param messageModel 需求的消息实体类型
     * @param requestMap      微信请求xml数据包解析内容集合
     * @return 根据传递的 messageModel 对象的类型，封装共有属性，返回需要的对象实例
     * @throws Exception
     */
    public T getInstance(T messageModel, Map<String, String> requestMap) {
        // 发送方帐号（open_id）
        messageModel.setToUserName(requestMap.get("FromUserName"));
        // 公众帐号
        messageModel.setFromUserName(requestMap.get("ToUserName"));
        messageModel.setCreateTime(new Date().getTime());
        // 消息类型
        messageModel.setMsgType(requestMap.get("MsgType"));

        return (T) messageModel;
    }

    /**
     * @param messageModel 消息类型实例 如文本类型 TextMessage 对象
     * @param fromUserName fromUserName
     * @param toUserName   toUserName
     * @param msgType      消息类型
     * @return 根据传递的 messageModel 对象的类型，封装共有属性，返回需要的对象实例
     */
    @Deprecated
    public T getInstance(T messageModel, String fromUserName, String toUserName, String msgType) {

        messageModel.setToUserName(fromUserName);
        messageModel.setFromUserName(toUserName);
        messageModel.setCreateTime(new Date().getTime());
        messageModel.setMsgType(msgType);

        return (T) messageModel;
    }
}
