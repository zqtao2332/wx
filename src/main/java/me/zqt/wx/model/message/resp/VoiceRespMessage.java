package me.zqt.wx.model.message.resp;

import lombok.Data;
import me.zqt.wx.model.message.VoiceModel;
import me.zqt.wx.model.message.BasicMessage;

/**
 * @auther: zqtao
 * @description: 请求消息之语音消息
 * @version: 1.0
 */
@Data
public class VoiceRespMessage extends BasicMessage {
    /**
     * 视频
     */
    private VoiceModel Voice;
}
