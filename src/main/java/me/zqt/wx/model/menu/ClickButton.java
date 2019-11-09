package me.zqt.wx.model.menu;

import lombok.Getter;
import lombok.Setter;

/**
 * @auther: zqtao
 * @Description: click类型的按钮（有type、name、key3个属性）
 * @version: 1.0
 */
@Setter
@Getter
public class ClickButton extends BaseButton {
    /**
     * 菜单KEY值，用于消息接口推送，不超过128字节
     */
    private String key;
}
