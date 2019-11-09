package me.zqt.wx.model.message;

import lombok.Getter;
import lombok.Setter;
import me.zqt.wx.model.ArticleModel;

import java.util.List;

/**
 * @auther: zqtao
 * @description: 图文消息
 * @version: 1.0
 */
@Setter
@Getter
public class ArticlesMessage extends BaseMessage{
    /**
     * 图文消息个数，限制为10条以内
     */
    private Integer ArticleCount;

    /**
     * 多条图文消息信息，默认第一个item为大图
     */
    private List<ArticleModel> Articles;

}
