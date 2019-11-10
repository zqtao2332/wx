package me.zqt.wx.model.message.req;

import lombok.Data;

/**
 * @auther: zqtao
 * @description: 请求消息之语音消息
 * @version: 1.0
 */
@Data
public class VoiceReqMessage extends ReqBasicMessage {
    /**
     * 语音消息媒体id，可以调用获取临时素材接口拉取数据。
     */
    private String MediaId;

    /**
     * 语音格式，如amr，speex等
     */
    private String Format;
}
