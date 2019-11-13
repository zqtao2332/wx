package me.zqt.wx.model.message;

import lombok.Data;

/**
 * @auther: zqtao
 * @description: 音乐消息
 * @version: 1.0
 */
@Data
public class MusicModel {
    /**
     * 音乐名称
     */
    private String Title;

    /**
     * 音乐描述
     */
    private String Description;

    /**
     * 音乐链接
     */
    private String MusicUrl;

    /**
     * 高质量音乐链接，WIFI环境优先使用该链接播放音乐
     */
    private String HQMusicUrl;

    /**
     * 缩略图的媒体id，通过素材管理中的接口上传多媒体文件，得到的 id
     */
    private String ThumbMediaId;
}
