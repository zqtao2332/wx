package me.zqt.wx.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.zqt.wx.constan.LogConstant;
import me.zqt.wx.constan.WechatInterface;
import me.zqt.wx.model.menu.Menu;
import me.zqt.wx.service.MenuManagerService;
import me.zqt.wx.utils.HttpRequestUtil;
import net.sf.json.JSONObject;


/**
 * @auther: zqtao
 * @description: 微信自定义菜单核心服务实现类
 * @version: 1.0
 */
@Slf4j
public class MenuManagerServiceImpl implements MenuManagerService {

    /**
     * 创建菜单
     *
     * @param menu        菜单实例
     * @param accessToken 有效的access_token
     * @return 0表示成功，其他值表示失败
     */
    @Override
    public int createMenu(Menu menu, String accessToken) {
        log.info(LogConstant.LOG_INFO.replace("INFO","开始创建菜单"));

        int result = 0;
        // MENU_CREATE_URL菜单创建（POST） 限100（次/天）
        // 拼装创建菜单的url
        String url = WechatInterface.MENU_CREATE_URL.replace("ACCESS_TOKEN", accessToken);
        // 将菜单对象转换成json字符串
        String jsonMenu = JSONObject.fromObject(menu).toString();
        // 调用接口创建菜单
        JSONObject jsonObject = HttpRequestUtil.httpRequest(url, "POST", jsonMenu);

        if (null != jsonObject) {
            if (0 != jsonObject.getInt("errcode")) {
                result = jsonObject.getInt("errcode");
                log.error("======>>>   创建菜单失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
        }
        log.info(LogConstant.LOG_INFO.replace("INFO","结束创建菜单"));
        return result;
    }

    /**
     * 删除菜单
     * 对应创建接口，正确的Json返回结果:
     * {"errcode":0,"errmsg":"ok"}
     *
     * @param accessToken 有效的access_token
     * @return 0表示成功，其他值表示失败
     */
    @Override
    public int deleteMenu(String accessToken) {
        int result = 0;
        String url = WechatInterface.MENU_DELETE_URL.replace("ACCESS_TOKEN", accessToken);
        // 调用接口删除菜单
        JSONObject jsonObject = HttpRequestUtil.httpRequest(url, "GET", null);
        if (null != jsonObject) {
            if (0 != jsonObject.getInt("errcode")) {
                result = jsonObject.getInt("errcode");
                log.error("======>>>   删除菜单失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
        }
        return result;
    }
}
