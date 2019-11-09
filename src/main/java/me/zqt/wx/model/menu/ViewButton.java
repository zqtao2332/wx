package me.zqt.wx.model.menu;

import lombok.Getter;
import lombok.Setter;

/**
 * @auther: zqtao
 * @description: view类型的按钮(有type 、 name 、 url三个属性)
 * @version: 1.0
 */
@Setter
@Getter
public class ViewButton extends BaseButton {
    /**
     * 网页链接，用户点击菜单可打开链接，不超过1024字节。
     * type为miniprogram时，不支持小程序的老版本客户端将打开本url。
     */
    private String url;
}
