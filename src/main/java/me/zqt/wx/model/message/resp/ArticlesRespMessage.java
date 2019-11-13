package me.zqt.wx.model.message.resp;

import lombok.Data;
import me.zqt.wx.model.message.ArticleModel;
import me.zqt.wx.model.message.BasicMessage;

import java.util.List;

/**
 * @auther: zqtao
 * @description: 请求消息之图文消息
 * @version: 1.0
 */
@Data
public class ArticlesRespMessage extends BasicMessage {
    /**
     * 图文消息个数，限制为10条以内
     */
    private Integer ArticleCount;

    /**
     * 多条图文消息信息，默认第一个item为大图
     */
    private List<ArticleModel> Articles;

    /**
     * 位0x0001被标志时，星标刚收到的消息
     */
    private int FuncFlag;
}
