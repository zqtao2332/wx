package me.zqt.wx.init;

import lombok.extern.slf4j.Slf4j;
import me.zqt.wx.constant.LogConstant;
import me.zqt.wx.constant.SignatureConstant;
import me.zqt.wx.model.AccessToken;
import me.zqt.wx.model.menu.*;
import me.zqt.wx.service.MenuManagerService;
import me.zqt.wx.service.impl.MenuManagerServiceImpl;
import me.zqt.wx.utils.AccessTokenUtil;

/**
 * @auther: zqtao
 * @description: 菜单管理器类: 初始化自定义菜单
 * @version: 1.0
 */
@Slf4j
public class MenuManagerDriverMain {

    public static void main(String[] args) {
        log.info(LogConstant.LOG_INFO.replace("INFO", "开始初始化自定义菜单"));
        // 第三方用户唯一凭证
        String appId = SignatureConstant.APP_ID;
        // 第三方用户唯一凭证密钥
        String appSecret = SignatureConstant.APP_SECRET;

        // 调用接口获取access_token
        AccessToken at = AccessTokenUtil.getAccessToken(appId, appSecret);
        log.info(LogConstant.LOG_INFO.replace("INFO", "AccessToken is :" + at.getToken()));

        if (at != null) {
            MenuManagerService menuManagerService = new MenuManagerServiceImpl();
            // 调用接口创建菜单
            int result = menuManagerService.createMenu(getMenu(), at.getToken());
//            int result = menuManagerService.deleteMenu(at.getToken());
            // 调用接口删除菜单
//            int result = menuManagerService.deleteMenu(at.getToken());
            // 判断菜单创建结果
            if (0 == result)
                log.info(LogConstant.LOG_INFO.replace("INFO", "菜单操作成功!"));
            else
                log.info(LogConstant.LOG_INFO.replace("INFO", "菜单操作失败，错误码：" + result));
        }
        log.info(LogConstant.LOG_INFO.replace("ERROR", "结束初始化自定义菜单"));
    }

    /**
     * 组装菜单数据
     */
    private static Menu getMenu() {

        // 子按钮（菜单）
        ClickButton btn11 = new ClickButton();
        btn11.setName("开发工具");
        btn11.setType("click");
        btn11.setKey("11");

        ViewButton btn12 = new ViewButton();
        btn12.setName("资源合集");
        btn12.setType("view");
        btn12.setUrl("https://www.baidu.com/");

        ViewButton btn21 = new ViewButton();
        btn21.setName("知乎");
        btn21.setType("view");
        btn21.setUrl("https://www.zhihu.com/people/zqtao23/activities");

        ViewButton btn22 = new ViewButton();
        btn22.setName("简书");
        btn22.setType("view");
        btn22.setUrl("https://www.jianshu.com/u/7110a2ba6f9e");

        ViewButton btn31 = new ViewButton();
        btn31.setName("资源屋");
        btn31.setType("view");
        btn31.setUrl("http://www.baidu.com");

        ViewButton btn32 = new ViewButton();
        btn32.setName("Github");
        btn32.setType("view");
        btn32.setUrl("https://github.com/zqtao2332");

        ViewButton btn33 = new ViewButton();
        btn33.setName("博客");
        btn33.setType("view");
        btn33.setUrl("http://www.zqtaotao.cn");


        // 一级菜单
        ComplexButton mainBtn1 = new ComplexButton();
        mainBtn1.setName("开发助手");
        mainBtn1.setSub_button(new BasicButton[]{btn11, btn12});

        ComplexButton mainBtn2 = new ComplexButton();
        mainBtn2.setName("知识驿站");
        mainBtn2.setSub_button(new BasicButton[]{btn21, btn22});

        ComplexButton mainBtn3 = new ComplexButton();
        mainBtn3.setName("更多体验");
        mainBtn3.setSub_button(new BasicButton[]{btn31, btn32, btn33});

        Menu menu = new Menu();
        menu.setButton(new BasicButton[]{mainBtn1, mainBtn2, mainBtn3});
        return menu;
    }

    private static Menu getMenu2() {
        ClickButton btn11 = new ClickButton();
        btn11.setName("天气预报");
        btn11.setType("click");
        btn11.setKey("11");

        ViewButton btn12 = new ViewButton();
        btn12.setName("百度");
        btn12.setType("view");
        btn12.setUrl("https://www.baidu.com/");

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

        ViewButton btn33 = new ViewButton();
        btn33.setName("博客");
        btn33.setType("view");
        btn33.setUrl("http://www.zqtaotao.cn");

        ComplexButton mainBtn1 = new ComplexButton();
        mainBtn1.setName("生活助手");
        mainBtn1.setSub_button(new BasicButton[]{btn11, btn12});

        ComplexButton mainBtn2 = new ComplexButton();
        mainBtn2.setName("休闲驿站");
        mainBtn2.setSub_button(new ClickButton[]{btn21, btn22});

        ComplexButton mainBtn3 = new ComplexButton();
        mainBtn3.setName("更多体验");
        mainBtn3.setSub_button(new ViewButton[]{btn31, btn32, btn33});

        Menu menu = new Menu();
        menu.setButton(new BasicButton[]{mainBtn1, mainBtn2, mainBtn3});
        return menu;
    }
}
