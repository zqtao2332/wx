package me.zqt.wx.model.message;

import lombok.Getter;
import lombok.Setter;
import me.zqt.wx.model.VideoModel;
import me.zqt.wx.model.VoiceModel;

/**
 * @auther: zqtao
 * @description: 视频消息
 * @version: 1.0
 */
@Setter
@Getter
public class VideoMessage extends BaseMessage {
    /**
     * 声音
     */
    private VideoModel Video;
}
