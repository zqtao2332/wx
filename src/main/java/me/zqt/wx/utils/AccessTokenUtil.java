package me.zqt.wx.utils;


import me.zqt.wx.constan.WechatInterface;
import me.zqt.wx.model.AccessToken;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @auther: zqtao
 * @description: 公众平台AccessToken 获取工具类
 * @version: 1.0
 */

public class AccessTokenUtil {
    private static Logger LOGGER = LoggerFactory.getLogger(AccessTokenUtil.class);

    /**
     * 获取access_token
     *
     * @param appid     凭证
     * @param appsecret 密钥
     * @return 接口访问凭证
     */
    public static AccessToken getAccessToken(String appid, String appsecret) {
        AccessToken accessToken = null;

        // access_token的接口地址ACCESS_TOKEN_URL （GET） 限200（次/天）
        String requestUrl = WechatInterface.ACCESS_TOKEN_URL.replace("APPID", appid).replace("APPSECRET", appsecret);
        JSONObject jsonObject = HttpRequestUtil.httpRequest(requestUrl, "GET", null);
        // 如果请求成功
        if (null != jsonObject) {
            try {
                accessToken = new AccessToken();
                accessToken.setToken(jsonObject.getString("access_token"));
                accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
            } catch (JSONException e) {
                accessToken = null;
                // 获取token失败
                LOGGER.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
        }
        return accessToken;
    }
}
