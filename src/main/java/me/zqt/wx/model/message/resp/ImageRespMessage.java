package me.zqt.wx.model.message.resp;

import lombok.Data;
import me.zqt.wx.model.message.ImageModel;
import me.zqt.wx.model.message.BasicMessage;

/**
 * @auther: zqtao
 * @description: 响应消息之图片消息
 * @version: 1.0
 */
@Data
public class ImageRespMessage extends BasicMessage {
    /**
     * 图片
     */
    private ImageModel Image;
}
