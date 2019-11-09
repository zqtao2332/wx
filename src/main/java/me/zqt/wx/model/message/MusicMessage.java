package me.zqt.wx.model.message;

import lombok.Getter;
import lombok.Setter;
import me.zqt.wx.model.MusicModel;
import me.zqt.wx.model.message.BaseMessage;

/**
 * @auther: zqtao
 * @description: 音乐消息
 * @version: 1.0
 */
@Setter
@Getter
public class MusicMessage extends BaseMessage {
    /**
     * 音乐
     */
    private MusicModel Music;

}
