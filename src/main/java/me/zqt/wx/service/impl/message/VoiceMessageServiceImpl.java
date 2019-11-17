package me.zqt.wx.service.impl.message;

import lombok.extern.slf4j.Slf4j;
import me.zqt.wx.constant.LogConstant;
import me.zqt.wx.model.message.VoiceModel;
import me.zqt.wx.model.message.resp.VoiceRespMessage;
import me.zqt.wx.service.message.ImageMessageService;
import me.zqt.wx.service.message.VoiceMessageService;
import me.zqt.wx.utils.MessageRespFactoryUtil;
import me.zqt.wx.utils.WechatMessageXMLParseUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @auther: zqtao
 * @description:
 * @version: 1.0
 */
@Slf4j
@Service
public class VoiceMessageServiceImpl implements VoiceMessageService {
    @Override
    public String messageHandler(Map<String, String> requestMap) {
        log.info(LogConstant.LOG_INFO.replace("INFO", "消息类型：文本消息"));
        // 消息内容
        String content = requestMap.get("Content");

        MessageRespFactoryUtil<VoiceRespMessage> factoryUtil = new MessageRespFactoryUtil<>();
        VoiceRespMessage message = factoryUtil.getInstance(new VoiceRespMessage(), requestMap);

        VoiceModel voiceModel = new VoiceModel();
        voiceModel.setMediaId(requestMap.get("MediaId"));
        message.setVoice(voiceModel);

        return WechatMessageXMLParseUtil.parseObjMessageToXml(message);
    }
}
