package me.zqt.wx.model.message;

import lombok.Data;
import me.zqt.wx.model.ImageModel;

/**
 * @auther: zqtao
 * @description: 图片消息
 * @version: 1.0
 */
@Data
public class ImageMessage extends BaseMessage {
    /**
     * 图片
     */
    private ImageModel Image;

}
