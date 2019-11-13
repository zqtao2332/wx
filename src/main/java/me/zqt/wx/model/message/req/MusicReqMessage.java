package me.zqt.wx.model.message.req;

import lombok.Data;
import me.zqt.wx.model.message.MusicModel;

/**
 * @auther: zqtao
 * @description: 请求消息之音乐消息
 * @version: 1.0
 */
@Data
public class MusicReqMessage extends ReqBasicMessage {
    /**
     * 音乐
     */
    private MusicModel Music;

}
