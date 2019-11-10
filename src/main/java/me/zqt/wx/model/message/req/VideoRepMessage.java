package me.zqt.wx.model.message.req;

import lombok.Data;
import me.zqt.wx.model.VideoModel;

/**
 * @auther: zqtao
 * @description: 请求消息之视频消息
 * @version: 1.0
 */
@Data
public class VideoRepMessage extends ReqBasicMessage {
    /**
     * 视频消息媒体id，可以调用获取临时素材接口拉取数据。
     */
    private String MediaId;

    /**
     * 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
     */
    private String ThumbMediaId;
}
