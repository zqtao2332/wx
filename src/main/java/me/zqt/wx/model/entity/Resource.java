package me.zqt.wx.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @auther: zqtao
 * @description: 普通资源实体
 * @version: 1.0
 */
@Data
public class Resource implements Serializable {

    /**
     * 主键
     */
    private Long id;
    /**
     * 资源名称
     */
    private String name;

    /**
     * 资源链接
     */
    private String url;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}