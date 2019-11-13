package me.zqt.wx.model.menu;

import lombok.Data;

/**
 * @auther: zqtao
 * @Description: click类型的按钮（有type、name、key3个属性）
 * @version: 1.0
 */
@Data
public class ClickButton extends BasicButton {
    /**
     * 菜单KEY值，用于消息接口推送，不超过128字节
     */
    private String key;
}
