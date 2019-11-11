package me.zqt.wx.model;

import lombok.Data;

/**
 * @auther: zqtao
 * @description: 视频消息model
 * @version: 1.0
 */
@Data
public class VideoModel {
    /**
     * 通过素材管理中的接口上传多媒体文件，得到的id
     */
    private String MediaId;

    /**
     * 视频消息的标题
     */
    private String Title;

    /**
     * 视频消息的描述
     */
    private String Description;
}
