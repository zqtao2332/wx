package me.zqt.wx.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.zqt.wx.constant.LogConstant;
import me.zqt.wx.constant.MessageTypeConstant;
import me.zqt.wx.model.message.ImageModel;
import me.zqt.wx.model.message.VideoModel;
import me.zqt.wx.model.message.VoiceModel;
import me.zqt.wx.model.message.resp.ImageRespMessage;
import me.zqt.wx.model.message.resp.TextRespMessage;
import me.zqt.wx.model.message.resp.VideoRespMessage;
import me.zqt.wx.model.message.resp.VoiceRespMessage;
import me.zqt.wx.service.WechatMessageService;
import me.zqt.wx.utils.CommonUtil;
import me.zqt.wx.utils.MessageRespFactoryUtil;
import me.zqt.wx.utils.WechatMessageXMLParseUtil;
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
        String respMsg = null;

        try {
            // xml请求解析
            Map<String, String> requestMap = WechatMessageXMLParseUtil.parseXml(request);
            // 发送方帐号（open_id）
            String fromUserName = requestMap.get("FromUserName");
            // 公众帐号
            String toUserName = requestMap.get("ToUserName");
            // 消息类型
            String msgType = requestMap.get("MsgType");

            log.info("FromUserName is:" + fromUserName + ", ToUserName is:" + toUserName + ", MsgType is:" + msgType);

            // 文本消息
            if (msgType.equals(MessageTypeConstant.REQ_MESSAGE_TYPE_TEXT)) {
                log.info(LogConstant.LOG_INFO.replace("INFO", "消息类型：文本消息"));

                // 消息内容
                String content = requestMap.get("Content");
                if (CommonUtil.getByteSize(content) > 2047)
                    content = "请重新编辑发送内容，您发送的内容长度超限，请保持2000字内！！谢谢！";
                //自动回复
                MessageRespFactoryUtil<TextRespMessage> factoryUtil = new MessageRespFactoryUtil<>();
                TextRespMessage text = factoryUtil.getInstance(new TextRespMessage(), fromUserName, toUserName, msgType);
                text.setContent("您发送的是文本内容 ： " + content);
                if (content.equals("源码"))
                    text.setContent("<a href=\"https://github.com/zqtao2332\">公众号开发源码</a>");
                respMsg = WechatMessageXMLParseUtil.parseObjMessageToXml(text);

                log.info(respMsg);
            }
            // 图片消息
            else if (msgType.equals(MessageTypeConstant.REQ_MESSAGE_TYPE_IMAGE)) {
                log.info(LogConstant.LOG_INFO.replace("INFO", "消息类型：图片消息"));
                // 图片消息
                String mediaId = requestMap.get("MediaId");
                log.info("----------------    这个一个图片    ： " + mediaId);

                MessageRespFactoryUtil<ImageRespMessage> factoryUtil = new MessageRespFactoryUtil<>();
                ImageRespMessage image = factoryUtil.getInstance(new ImageRespMessage(), fromUserName, toUserName, msgType);

                ImageModel imageModel = new ImageModel();
                imageModel.setMediaId(mediaId);
                image.setImage(imageModel);

                respMsg = WechatMessageXMLParseUtil.parseObjMessageToXml(image);
                log.info(respMsg);
            }
            // 语音消息
            else if (msgType.equals(MessageTypeConstant.REQ_MESSAGE_TYPE_VOICE)) {
                log.info(LogConstant.LOG_INFO.replace("INFO", "消息类型：语音消息"));
                String mediaId = requestMap.get("MediaId");
                log.info("----------------    这个一条语音    ： " + mediaId);

                MessageRespFactoryUtil<VoiceRespMessage> factoryUtil = new MessageRespFactoryUtil<>();
                VoiceRespMessage voice = factoryUtil.getInstance(new VoiceRespMessage(), fromUserName, toUserName, msgType);

                VoiceModel voiceModel = new VoiceModel();
                voiceModel.setMediaId(mediaId);
                voice.setVoice(voiceModel);

                respMsg = WechatMessageXMLParseUtil.parseObjMessageToXml(voice);
                log.info(respMsg);
            }
            // 视频消息
            else if (msgType.equals(MessageTypeConstant.REQ_MESSAGE_TYPE_VIDEO)) {
                log.info(LogConstant.LOG_INFO.replace("INFO", "消息类型：视频消息"));
                String mediaId = requestMap.get("MediaId");
                log.info("----------------    这个一个视频    ： " + mediaId);

                MessageRespFactoryUtil<VideoRespMessage> factoryUtil = new MessageRespFactoryUtil<>();
                VideoRespMessage video = factoryUtil.getInstance(new VideoRespMessage(), fromUserName, toUserName, msgType);

                VideoModel videoModel = new VideoModel();
                videoModel.setMediaId(mediaId);
                videoModel.setTitle("test");
                videoModel.setDescription("this is a test video");
                video.setVideo(videoModel);

//                respMsg = WechatMessageXMLParseUtil.parseObjMessageToXml(video);
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
