package me.zqt.wx.service.impl.message;

import lombok.extern.slf4j.Slf4j;
import me.zqt.wx.constant.LogConstant;
import me.zqt.wx.model.message.ImageModel;
import me.zqt.wx.model.message.resp.ImageRespMessage;
import me.zqt.wx.service.message.ImageMessageService;
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
public class ImageMessageServiceImpl implements ImageMessageService {
    @Override
    public String messageHandler(Map<String, String> requestMap) {
        log.info(LogConstant.LOG_INFO.replace("INFO", "消息类型：文本消息"));
        // 消息内容
        String content = requestMap.get("Content");

        MessageRespFactoryUtil<ImageRespMessage> factoryUtil = new MessageRespFactoryUtil<>();
        ImageRespMessage message = factoryUtil.getInstance(new ImageRespMessage(), requestMap);

        ImageModel imageModel = new ImageModel();
        imageModel.setMediaId(requestMap.get("MediaId"));
        message.setImage(imageModel);

        return WechatMessageXMLParseUtil.parseObjMessageToXml(message);
    }
}
