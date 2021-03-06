package me.zqt.wx.utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import me.zqt.wx.model.message.ArticleModel;
import me.zqt.wx.model.message.BasicMessage;
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
public class WechatMessageXMLParseUtil {
    /**
     * @param @param  request
     * @param @return 微信XML 请求解析结果
     * @param @throws Exception
     * @Description: 解析微信发来的请求（XML数据包）, 并将结果封装在 Map 中返回
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
     * 扩展xstream，使其支持微信消息XML数据包中的CDATA块
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
    /*public static String textMessageToXml(TextRespMessage textMessage) {
        xstream.alias("xml", textMessage.getClass());
        return xstream.toXML(textMessage);
    }*/

    /**
     * 将消息对象转换成 xml 字符串
     *
     * @param message 消息对象
     * @return xml 字符串
     */
    public static String parseObjMessageToXml(BasicMessage message) {
        xstream.alias("xml", message.getClass());
        return xstream.toXML(message);
    }


    /**
     * 图文消息对象转换成xml
     *
     * @param message 图文消息对象
     * @return xml
     */
    public static String articlesMessageToXml(BasicMessage message) {
        xstream.alias("xml", message.getClass());
        xstream.alias("item", new ArticleModel().getClass());
        return xstream.toXML(message);
    }
}
