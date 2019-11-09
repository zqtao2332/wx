package me.zqt.wx.model.menu;

import lombok.Data;

/**
 * @auther: zqtao
 * @description: 菜单按钮基类
 * 所有一级菜单、二级菜单都共有相同的属性，name type
 * @version: 1.0
 */

@Data
public class BaseButton {
    /**
     * 菜单标题，不超过16个字节，子菜单不超过60个字节
     */
    private String name;
    /**
     * 菜单的响应动作类型，view表示网页类型，click表示点击类型，miniprogram表示小程序类型
     */
    private String type;
}
