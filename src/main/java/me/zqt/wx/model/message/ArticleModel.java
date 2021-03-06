package me.zqt.wx.model.message;

import lombok.Data;

/**
 * @auther: zqtao
 * @description: 图文消息model
 * @version: 1.0
 */
@Data
public class ArticleModel {

    /**
     * 图文消息名称
     */
    private String Title;

    /**
     * 图文消息描述
     */
    private String Description;

    /**
     * 图片链接，支持JPG、PNG格式，<br>
     * 较好的效果为大图640*320，小图80*80
     */
    private String PicUrl;

    /**
     * 点击图文消息跳转链接
     */
    private String Url;

}
