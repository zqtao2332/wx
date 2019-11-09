package me.zqt.wx.model.menu;

import lombok.Getter;
import lombok.Setter;

/**
 * @auther: zqtao
 * @description: 复合类型的按钮（含有子菜单的一级菜单）
 * @version: 1.0
 */
@Setter
@Getter
public class ComplexButton extends BaseButton {
    private BaseButton[] sub_button;
}
