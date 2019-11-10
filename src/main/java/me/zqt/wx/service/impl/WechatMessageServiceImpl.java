package me.zqt.wx.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.zqt.wx.constant.LogConstant;
import me.zqt.wx.constant.MessageTypeConstant;
import me.zqt.wx.model.ImageModel;
import me.zqt.wx.model.VideoModel;
import me.zqt.wx.model.VoiceModel;
import me.zqt.wx.model.message.resp.ImageRespMessage;
import me.zqt.wx.model.message.resp.TextRespMessage;
import me.zqt.wx.model.message.resp.VideoRespMessage;
import me.zqt.wx.model.message.resp.VoiceRespMessage;
import me.zqt.wx.service.WechatMessageService;
import me.zqt.wx.utils.MessageRespFactoryUtil;
import me.zqt.wx.utils.WechatMessageUtil;
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

    @Override
    public String newMessageRequest(HttpServletRequest request) {
        log.info(LogConstant.LOG_INFO.replace("INFO", "开始处理消息"));
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

            log.info("FromUserName is:" + fromUserName + ", ToUserName is:" + toUserName + ", MsgType is:" + msgType);

            // 文本消息
            if (msgType.equals(MessageTypeConstant.REQ_MESSAGE_TYPE_TEXT)) {
                // 消息内容
                String content = requestMap.get("Content");
                log.info("这是一个文本内容" + content);
                //自动回复
                MessageRespFactoryUtil<TextRespMessage> factoryUtil = new MessageRespFactoryUtil<>();
                TextRespMessage text = factoryUtil.getInstance(new TextRespMessage(), fromUserName, toUserName, msgType);
                text.setContent("这是一个文本内容" + content);
                respMsg = WechatMessageUtil.textMessageToXml(text);

                log.info(respMsg);
            } else if (msgType.equals(MessageTypeConstant.REQ_MESSAGE_TYPE_IMAGE)) {
                // 图片消息
                String mediaId = requestMap.get("MediaId");
                log.info("----------------    这个一个图片    ： " + mediaId);

                MessageRespFactoryUtil<ImageRespMessage> factoryUtil = new MessageRespFactoryUtil<>();
                ImageRespMessage image = factoryUtil.getInstance(new ImageRespMessage(), fromUserName, toUserName, msgType);

                ImageModel imageModel = new ImageModel();
                imageModel.setMediaId(mediaId);
                image.setImage(imageModel);

                respMsg = WechatMessageUtil.imageMessageToXml(image);
                log.info(respMsg);
            } else if (msgType.equals(MessageTypeConstant.REQ_MESSAGE_TYPE_VOICE)) {
                String mediaId = requestMap.get("MediaId");
                log.info("----------------    这个一条语音    ： " + mediaId);

                MessageRespFactoryUtil<VoiceRespMessage> factoryUtil = new MessageRespFactoryUtil<>();
                VoiceRespMessage voice = factoryUtil.getInstance(new VoiceRespMessage(), fromUserName, toUserName, msgType);

                VoiceModel voiceModel = new VoiceModel();
                voiceModel.setMediaId(mediaId);
                voice.setVoice(voiceModel);

                respMsg = WechatMessageUtil.voiceMessageToXml(voice);
                log.info(respMsg);
            } else if (msgType.equals(MessageTypeConstant.REQ_MESSAGE_TYPE_VIDEO)) {
                String mediaId = requestMap.get("MediaId");
                log.info("----------------    这个一个视频    ： " + mediaId);

                MessageRespFactoryUtil<VideoRespMessage> factoryUtil = new MessageRespFactoryUtil<>();
                VideoRespMessage video = factoryUtil.getInstance(new VideoRespMessage(), fromUserName, toUserName, msgType);

                VideoModel videoModel = new VideoModel();
                videoModel.setMediaId(mediaId);
                videoModel.setTitle("test");
                videoModel.setDescription("this is a test video");
                video.setVideo(videoModel);

                respMsg = WechatMessageUtil.videoMessageToXml(video);
                log.info(respMsg);
            }

        } catch (Exception e) {
            log.error(LogConstant.LOG_ERROR.replace("ERROR", e.getMessage()));
        } finally {
            log.info(LogConstant.LOG_INFO.replace("INFO", "开始处理消息"));
        }
        return respMsg;
    }
}
