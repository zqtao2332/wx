package me.zqt.wx.init;

import me.zqt.wx.constan.SignatureConstant;
import me.zqt.wx.model.AccessToken;
import me.zqt.wx.model.menu.*;
import me.zqt.wx.service.MenuManagerService;
import me.zqt.wx.service.impl.MenuManagerServiceImpl;
import me.zqt.wx.utils.AccessTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @auther: zqtao
 * @description: 菜单管理器类: 初始化自定义菜单
 * @version: 1.0
 */
public class MenuManagerDriverMain {
    private static Logger LOGGER = LoggerFactory.getLogger(MenuManagerDriverMain.class);

    public static void main(String[] args) {

        // 第三方用户唯一凭证
        String appId = SignatureConstant.APP_ID;
        // 第三方用户唯一凭证密钥
        String appSecret = SignatureConstant.APP_SECRET;

        // 调用接口获取access_token
        AccessToken at = AccessTokenUtil.getAccessToken(appId, appSecret);
        LOGGER.info("-------------AccessToken is : " + at.getToken());

        if (at != null) {
            MenuManagerService menuManagerService = new MenuManagerServiceImpl();
            // 调用接口创建菜单
            int result = menuManagerService.createMenu(getMenu(), at.getToken());
//            int result = menuManagerService.deleteMenu(at.getToken());
            // 判断菜单创建结果
            if (0 == result)
                LOGGER.info("菜单创建成功！");
            else
                LOGGER.info("菜单创建失败，错误码：" + result);
        }
    }

    /**
     * 组装菜单数据
     */
    private static Menu getMenu() {
        ClickButton btn11 = new ClickButton();
        btn11.setName("天气预报");
        btn11.setType("click");
        btn11.setKey("11");

        ClickButton btn12 = new ClickButton();
        btn12.setName("公交查询");
        btn12.setType("click");
        btn12.setKey("12");

        ClickButton btn21 = new ClickButton();
        btn21.setName("歌曲点播");
        btn21.setType("click");
        btn21.setKey("21");

        ClickButton btn22 = new ClickButton();
        btn22.setName("经典游戏");
        btn22.setType("click");
        btn22.setKey("22");

        ViewButton btn31 = new ViewButton();
        btn31.setName("Q友圈");
        btn31.setType("view");
        btn31.setUrl("http://www.baidu.com");

        ViewButton btn32 = new ViewButton();
        btn32.setName("Github");
        btn32.setType("view");
        btn32.setUrl("https://github.com/zqtao2332");

        ComplexButton mainBtn1 = new ComplexButton();
        mainBtn1.setName("生活助手");
        mainBtn1.setSub_button(new ClickButton[]{btn11, btn12});

        ComplexButton mainBtn2 = new ComplexButton();
        mainBtn2.setName("休闲驿站");
        mainBtn2.setSub_button(new ClickButton[]{btn21, btn22});

        ComplexButton mainBtn3 = new ComplexButton();
        mainBtn3.setName("更多体验");
        mainBtn3.setSub_button(new ViewButton[]{btn31, btn32});

        Menu menu = new Menu();
        menu.setButton(new BaseButton[]{mainBtn1, mainBtn2, mainBtn3});
        return menu;
    }
}
