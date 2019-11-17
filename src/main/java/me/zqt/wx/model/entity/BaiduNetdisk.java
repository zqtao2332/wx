package me.zqt.wx.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @auther: zqtao
 * @description: 百度网盘链接实体
 * @version: 1.0
 */
@Data
//@TableName("tb_baidu_netdisk") 已在yml 中配置了prefix = tb_
public class BaiduNetdisk implements Serializable {

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
     * 有效性(有/失效，默认有效)
     */
    private Integer validity;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}