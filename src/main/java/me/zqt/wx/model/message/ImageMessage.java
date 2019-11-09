package me.zqt.wx.model.message;

import lombok.Getter;
import lombok.Setter;
import me.zqt.wx.model.ImageModel;

/**
 * @auther: zqtao
 * @description: 图片消息
 * @version: 1.0
 */
@Setter
@Getter
public class ImageMessage extends BaseMessage {
    /**
     * 图片
     */
    private ImageModel Image;

}
