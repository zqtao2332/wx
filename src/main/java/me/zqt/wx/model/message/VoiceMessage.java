package me.zqt.wx.model.message;

import lombok.Data;
import me.zqt.wx.model.VoiceModel;

/**
 * @auther: zqtao
 * @description: 语音消息
 * @version: 1.0
 */
@Data
public class VoiceMessage extends BaseMessage {
    /**
     * 视频
     */
    private VoiceModel Voice;
}
