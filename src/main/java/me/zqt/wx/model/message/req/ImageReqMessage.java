package me.zqt.wx.model.message.req;

import lombok.Data;

/**
 * @auther: zqtao
 * @description: 请求消息之图片消息
 * @version: 1.0
 */
@Data
public class ImageReqMessage extends ReqBasicMessage {
    /**
     * 媒体文件ID
     */
    private String MediaId;
}
