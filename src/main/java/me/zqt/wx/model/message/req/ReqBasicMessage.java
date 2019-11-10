package me.zqt.wx.model.message.req;

import lombok.Data;
import me.zqt.wx.model.message.BasicMessage;

/**
 * @auther: zqtao
 * @description: 请求消息之请求消息基类
 * @version: 1.0
 */
@Data
public class ReqBasicMessage extends BasicMessage {
    /**
     * 消息id，64位整型
     */
    private long MsgId;
    /**
     * 位0x0001被标志时，星标刚收到的消息
     */
    private int FuncFlag;
}
