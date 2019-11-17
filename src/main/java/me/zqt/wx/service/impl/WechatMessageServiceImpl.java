package me.zqt.wx.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.zqt.wx.constant.LogConstant;
import me.zqt.wx.constant.MessageTypeConstant;
import me.zqt.wx.model.message.resp.TextRespMessage;
import me.zqt.wx.service.WechatMessageService;
import me.zqt.wx.service.message.ImageMessageService;
import me.zqt.wx.service.message.TextMessageService;
import me.zqt.wx.service.message.VoiceMessageService;
import me.zqt.wx.utils.MessageRespFactoryUtil;
import me.zqt.wx.utils.WechatMessageXMLParseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @auther: zqtao
 * @description: 微信消息处理核心service 实现类
 * @version: 1.0
 */
@Service
@Slf4j
public class WechatMessageServiceImpl implements WechatMessageService {

    @Autowired
    private TextMessageService textMessageService;
    @Autowired
    private ImageMessageService imageMessageService;
    @Autowired
    private VoiceMessageService voiceMessageService;

    // 临时测试消息接口响应方法
    private String testMsgInterface(String content) {
        String fromUserName = "owUDX0kj8U55iVrCqbLgMufa_kq8";
        String toUserName = "gh_f572346b9834";
        String msgType = "text";

        MessageRespFactoryUtil<TextRespMessage> factoryUtil = new MessageRespFactoryUtil<>();
        TextRespMessage text = factoryUtil.getInstance(new TextRespMessage(), fromUserName, toUserName, msgType);
        text.setContent("您发送的是:  " + content + "消息");
        return WechatMessageXMLParseUtil.parseObjMessageToXml(text);
    }

    /**
     * 微信消息处理方法
     *
     * @param request request
     * @return 响应消息的XML数据包
     */
    @Override
    public String newMessageRequest(HttpServletRequest request) {
        log.info(LogConstant.LOG_INFO.replace("INFO", "newMessageRequest 开始处理消息"));
        String respMsg = "";
        try {
            // xml请求解析
            Map<String, String> requestMap = WechatMessageXMLParseUtil.parseXml(request);
            String msgType = requestMap.get("MsgType");
            // 文本消息
            if (msgType.equals(MessageTypeConstant.REQ_MESSAGE_TYPE_TEXT)) {
                respMsg = textMessageService.messageHandler(requestMap);
            }
            // 图片消息
            else if (msgType.equals(MessageTypeConstant.REQ_MESSAGE_TYPE_IMAGE)) {
                respMsg = imageMessageService.messageHandler(requestMap);
            }
            // 语音消息
            else if (msgType.equals(MessageTypeConstant.REQ_MESSAGE_TYPE_VOICE)) {
                respMsg = voiceMessageService.messageHandler(requestMap);
            }
            // 视频消息
            else if (msgType.equals(MessageTypeConstant.REQ_MESSAGE_TYPE_VIDEO)) {
                respMsg = testMsgInterface("视频");
                log.info(respMsg);
            }
            // 地理位置消息
            else if (msgType.equals(MessageTypeConstant.REQ_MESSAGE_TYPE_LOCATION)) {
                log.info(LogConstant.LOG_INFO.replace("INFO", "消息类型：地理位置消息"));
                respMsg = testMsgInterface("地理位置");
            }
            // 链接消息
            else if (msgType.equals(MessageTypeConstant.REQ_MESSAGE_TYPE_LINK)) {
                respMsg = testMsgInterface("链接");
            }
            // 事件推送
            else if (msgType.equals(MessageTypeConstant.REQ_MESSAGE_TYPE_EVENT)) {
                // 事件类型
                String eventType = requestMap.get("Event");
                // 订阅
                if (eventType.equals(MessageTypeConstant.EVENT_TYPE_SUBSCRIBE)) {
                    respMsg = testMsgInterface("谢谢您的关注");
                }
                // 取消订阅
                else if (eventType.equals(MessageTypeConstant.EVENT_TYPE_UNSUBSCRIBE)) {
                    // TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
                }
                // 自定义菜单点击事件
                else if (eventType.equals(MessageTypeConstant.EVENT_TYPE_CLICK)) {
                    // TODO 自定义菜单权没有开放，暂不处理该类消息
                }
            }
        } catch (Exception e) {
            log.error(LogConstant.LOG_ERROR.replace("ERROR", e.getMessage()));
        } finally {
            log.info(LogConstant.LOG_INFO.replace("INFO", "newMessageRequest 结束处理消息"));
        }
        return respMsg;
    }
}
