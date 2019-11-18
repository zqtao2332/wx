package me.zqt.wx.service.impl.message;

import lombok.extern.slf4j.Slf4j;
import me.zqt.wx.constant.LogConstant;
import me.zqt.wx.model.message.resp.TextRespMessage;
import me.zqt.wx.service.BaiduNetdiskService;
import me.zqt.wx.service.ResourceService;
import me.zqt.wx.service.message.TextMessageService;
import me.zqt.wx.utils.CommonUtil;
import me.zqt.wx.utils.MessageRespFactoryUtil;
import me.zqt.wx.utils.WechatMessageXMLParseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @auther: zqtao
 * @description: 文本消息具体处理核心业务类
 * @version: 1.0
 */
@Slf4j
@Service
public class TextMessageServiceImpl implements TextMessageService {

    @Autowired
    private BaiduNetdiskService baiduNetdiskService;

    @Autowired
    private ResourceService resourceService;

    @Override
    public String messageHandler(Map<String, String> requestMap) {
        String respMsg = "";
        log.info(LogConstant.LOG_INFO.replace("INFO", "消息类型：文本消息"));
        // 消息内容
        String content = requestMap.get("Content");

        MessageRespFactoryUtil<TextRespMessage> factoryUtil = new MessageRespFactoryUtil<>();
        TextRespMessage message = factoryUtil.getInstance(new TextRespMessage(), requestMap);

        // 消息长度超限制
        if (CommonUtil.getByteSize(content) > 2047) {
            message.setContent("请重新编辑发送内容，您发送的内容长度超限，请保持2000字内！！谢谢！");
        } else if (content.equals("源码")) {
            message.setContent("<a href=\"https://github.com/zqtao2332\">公众号开发源码</a>");
        } else if (content.split("#")[0].equals("资源")) {
            message.setContent(resourceService.deal(content.split("#")));
        } else {
            message.setContent(content);
        }
        respMsg = WechatMessageXMLParseUtil.parseObjMessageToXml(message);

        log.info(respMsg);
        return respMsg;
    }
}
