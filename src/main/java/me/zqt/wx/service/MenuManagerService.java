package me.zqt.wx.service;

import me.zqt.wx.model.menu.Menu;

/**
 * @auther: zqtao
 * @description: 微信自定义菜单核心服务接口
 * 创建
 * 删除
 * 查询
 * @version: 1.0
 */
public interface MenuManagerService {
    /**
     * 创建菜单
     *
     * @param menu        菜单实例
     * @param accessToken 有效的access_token
     * @return 0表示成功，其他值表示失败
     */
    int createMenu(Menu menu, String accessToken);

    /**
     * 删除菜单
     * @param accessToken 有效的access_token
     * @return 0表示成功，其他值表示失败
     */
    int deleteMenu(String accessToken);
}
