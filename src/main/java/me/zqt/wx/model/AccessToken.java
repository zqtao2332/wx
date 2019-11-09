package me.zqt.wx.model;

import lombok.Data;

/**
 * @auther: zqtao
 * @description: 微信通用接口凭证
 * 调用获取凭证接口后，微信服务器会返回json格式的数据：
 * {"access_token":"ACCESS_TOKEN","expires_in":7200}
 * 将其封装为一个AccessToken对象，对象有二个属性：token和expiresIn
 * @version: 1.0
 */
@Data
public class AccessToken {
    /**
     * 获取到的凭证
     */
    private String token;
    /**
     *  凭证有效时间，单位：秒
     */
    private int expiresIn;
}
