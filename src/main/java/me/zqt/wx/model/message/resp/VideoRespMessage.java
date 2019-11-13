package me.zqt.wx.model.message.resp;

import lombok.Data;
import me.zqt.wx.model.message.VideoModel;
import me.zqt.wx.model.message.BasicMessage;

/**
 * @auther: zqtao
 * @description: 请求消息之视频消息
 * @version: 1.0
 */
@Data
public class VideoRespMessage extends BasicMessage {
    /**
     * 声音
     */
    private VideoModel Video;
}
