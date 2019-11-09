package me.zqt.wx.model.message;

import lombok.Getter;
import lombok.Setter;
import me.zqt.wx.model.VideoModel;
import me.zqt.wx.model.VoiceModel;

/**
 * @auther: zqtao
 * @description: 语音消息
 * @version: 1.0
 */
@Setter
@Getter
public class VoiceMessage extends BaseMessage {
    /**
     * 视频
     */
    private VoiceModel Voice;
}
