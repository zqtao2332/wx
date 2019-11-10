package me.zqt.wx.utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import me.zqt.wx.model.message.resp.ImageRespMessage;
import me.zqt.wx.model.message.resp.TextRespMessage;
import me.zqt.wx.model.message.resp.VoiceRespMessage;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther: zqtao
 * @description: 消息工具类
 * @version: 1.0
 */
public class WechatMessageUtil {
    /**
     * @param @param  request
     * @param @return 微信XML 请求解析结果
     * @param @throws Exception
     * @Description: 解析微信发来的请求（XML）, 并将结果封装在 Map 中返回
     */
    public static Map<String, String> parseXml(HttpServletRequest request)
            throws Exception {
        // 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap<>();
        // 从request中取得输入流
        InputStream inputStream = request.getInputStream();
        // 读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        // 得到xml根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        List<Element> elementList = root.elements();

        // 遍历所有子节点
        for (Element e : elementList)
            map.put(e.getName(), e.getText());

        // 释放资源
        inputStream.close();
        inputStream = null;

        return map;
    }

    /**
     * 对象到xml的处理
     */
    private static XStream xstream = new XStream(new XppDriver() {
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                // 对所有xml节点的转换都增加CDATA标记
                // 微信   <ToUserName><![CDATA[toUser]]></ToUserName>
                boolean cdata = true;

                @SuppressWarnings("rawtypes")
                public void startNode(String name, Class clazz) {
                    super.startNode(name, clazz);
                }

                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    } else {
                        writer.write(text);
                    }
                }
            };
        }
    });

    /**
     * @param textMessage 文本消息对象
     * @return xml
     * @Description: 文本消息对象转换成xml
     */
    public static String textMessageToXml(TextRespMessage textMessage) {
        xstream.alias("xml", textMessage.getClass());
        return xstream.toXML(textMessage);
    }

    /**
     * @param imageMessage
     * @Description: 图片消息对象转换成xml
     */
    public static String imageMessageToXml(ImageRespMessage imageMessage) {
        xstream.alias("xml", imageMessage.getClass());
        return xstream.toXML(imageMessage);
    }

    /**
     * @param @param voiceMessage
     * @Description: 语音消息对象转换成xml
     */
    public static String voiceMessageToXml(VoiceRespMessage voiceReqMessage) {
        xstream.alias("xml", voiceReqMessage.getClass());
        return xstream.toXML(voiceReqMessage);
    }

}
