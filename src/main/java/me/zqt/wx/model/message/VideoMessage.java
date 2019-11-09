package me.zqt.wx.model.message;

import lombok.Data;
import me.zqt.wx.model.VideoModel;

/**
 * @auther: zqtao
 * @description: 视频消息
 * @version: 1.0
 */
@Data
public class VideoMessage extends BaseMessage {
    /**
     * 声音
     */
    private VideoModel Video;
}
