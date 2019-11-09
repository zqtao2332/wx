package me.zqt.wx.service.impl;

import me.zqt.wx.constan.MessageTypeConstant;
import me.zqt.wx.model.VoiceModel;
import me.zqt.wx.model.message.ImageMessage;
import me.zqt.wx.model.ImageModel;
import me.zqt.wx.model.message.TextMessage;
import me.zqt.wx.model.message.VoiceMessage;
import me.zqt.wx.service.WechatMessageService;
import me.zqt.wx.utils.MessageRespFactoryUtil;
import me.zqt.wx.utils.WechatMessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @auther: zqtao
 * @description: 微信消息处理核心service 实现类
 * @version: 1.0
 */
@Service
public class WechatMessageServiceImpl implements WechatMessageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WechatMessageServiceImpl.class);

    @Override
    public String newMessageRequest(HttpServletRequest request) {
        LOGGER.info("------------start handle wx message  -------------");
        String respMsg = null;

        try {
            // xml请求解析
            Map<String, String> requestMap = WechatMessageUtil.parseXml(request);
            // 发送方帐号（open_id）
            String fromUserName = requestMap.get("FromUserName");
            // 公众帐号
            String toUserName = requestMap.get("ToUserName");
            // 消息类型
            String msgType = requestMap.get("MsgType");

            LOGGER.info("FromUserName is:" + fromUserName + ", ToUserName is:" + toUserName + ", MsgType is:" + msgType);

            // 文本消息
            if (msgType.equals(MessageTypeConstant.REQ_MESSAGE_TYPE_TEXT)) {
                // 消息内容
                String content = requestMap.get("Content");
                LOGGER.info("这是一个文本内容" + content);
                //自动回复
                MessageRespFactoryUtil<TextMessage> factoryUtil = new MessageRespFactoryUtil<>();
                TextMessage text = factoryUtil.getInstance(new TextMessage(), fromUserName, toUserName, msgType);
                text.setContent("这是一个文本内容" + content);
                respMsg = WechatMessageUtil.textMessageToXml(text);

                LOGGER.info(respMsg);
            } else if (msgType.equals(MessageTypeConstant.REQ_MESSAGE_TYPE_IMAGE)) {
                // 图片消息
                String mediaId = requestMap.get("MediaId");
                LOGGER.info("----------------    这个一个图片    ： " + mediaId);

                MessageRespFactoryUtil<ImageMessage> factoryUtil = new MessageRespFactoryUtil<>();
                ImageMessage image = factoryUtil.getInstance(new ImageMessage(), fromUserName, toUserName, msgType);

                ImageModel imageModel = new ImageModel();
                imageModel.setMediaId(mediaId);
                image.setImage(imageModel);

                respMsg = WechatMessageUtil.imageMessageToXml(image);
                LOGGER.info(respMsg);
            } else if (msgType.equals(MessageTypeConstant.REQ_MESSAGE_TYPE_VOICE)) {
                String mediaId = requestMap.get("MediaId");
                LOGGER.info("----------------    这个一条语音    ： " + mediaId);

                MessageRespFactoryUtil<VoiceMessage> factoryUtil = new MessageRespFactoryUtil<>();
                VoiceMessage voice = factoryUtil.getInstance(new VoiceMessage(), fromUserName, toUserName, msgType);

                VoiceModel voiceModel = new VoiceModel();
                voiceModel.setMediaId(mediaId);
                voice.setVoice(voiceModel);

                respMsg = WechatMessageUtil.voiceMessageToXml(voice);
                LOGGER.info(respMsg);
            }

        } catch (Exception e) {
            LOGGER.error("error......");
        }
        return respMsg;
    }
}
