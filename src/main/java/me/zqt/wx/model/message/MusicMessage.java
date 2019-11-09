package me.zqt.wx.model.message;

import lombok.Data;
import me.zqt.wx.model.MusicModel;

/**
 * @auther: zqtao
 * @description: 音乐消息
 * @version: 1.0
 */
@Data
public class MusicMessage extends BaseMessage {
    /**
     * 音乐
     */
    private MusicModel Music;

}
