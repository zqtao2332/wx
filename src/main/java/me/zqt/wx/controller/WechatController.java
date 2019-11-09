package me.zqt.wx.controller;

import lombok.extern.slf4j.Slf4j;
import me.zqt.wx.constan.LogConstant;
import me.zqt.wx.service.WechatMessageService;
import me.zqt.wx.utils.SignUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * @auther: zqtao
 * @description: 微信请求核心处理，验证入口;验证服务号是否合法，以及消息转发
 * @version: 1.0
 */
@RestController
@RequestMapping("/wechat")
@Slf4j
public class WechatController {

    @Autowired
    WechatMessageService wechatMessageService;

    /**
     * 微信服务号连接校验
     */
    @RequestMapping(method = RequestMethod.GET)
    public void get(HttpServletRequest request, HttpServletResponse response) {
        log.info(LogConstant.LOG_INFO.replace("INFO", "开始处理微信签名校验"));
        // 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");
        log.info("signature is :" + signature
                + "\ntimestamp is :" + timestamp
                + "\nnonce is :" + nonce
                + "\nechostr is :" + echostr);

        PrintWriter out = null;
        try {
            out = response.getWriter();
            // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，否则接入失败
            if (SignUtil.checkSignature(signature, timestamp, nonce)) {
                out.print(echostr);
            }
        } catch (IOException e) {
            e.printStackTrace();
            log.error(LogConstant.LOG_ERROR.replace("ERROR", e.getMessage()));
        } finally {
            out.close();
            out = null;
            log.info(LogConstant.LOG_INFO.replace("INFO", "结束处理微信签名校验"));
        }
    }

    /**
     * 处理各种请求
     *
     * @param request
     * @param response
     */
    @RequestMapping(method = RequestMethod.POST)
    public void post(HttpServletRequest request, HttpServletResponse response) {
        log.info(LogConstant.LOG_INFO.replace("INFO", "开始处理微信发过来的消息"));
        try {
            // 微信服务器POST消息时用的是UTF-8编码，在接收时也要用同样的编码，否则中文会乱码；
            request.setCharacterEncoding("UTF-8");
            // 在响应消息（回复消息给用户）时，也将编码方式设置为UTF-8
            response.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            log.info(LogConstant.LOG_ERROR.replace("ERROR", "编码错误"));
        }

        // 调用核心业务类接收消息、处理消息
        String respMessage = wechatMessageService.newMessageRequest(request);

        // 响应消息
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.print(respMessage);
        } catch (IOException e) {
            e.printStackTrace();
            log.error(LogConstant.LOG_ERROR.replace("ERROR", e.getMessage()));
        } finally {
            out.close();
            out = null;
            log.info(LogConstant.LOG_INFO.replace("INFO", "结束处理微信发过来的消息"));
        }
    }

}
