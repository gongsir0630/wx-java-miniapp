package com.github.gongsir0630.wxapp.controller;

import java.io.IOException;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.gongsir0630.wxapp.config.WxMpConfiguration;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

/**
 * @author Kyle <gongsir0630@gmail.com>
 * Created on 2023/02/02
 */
@RestController
@RequestMapping("/wx/mp/portal/{appid}")
public class WxMpPortalController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping(produces = "text/plain;charset=utf-8")
    public void authGet(@PathVariable String appid,
                          @RequestParam(name = "signature", required = false) String signature,
                          @RequestParam(name = "timestamp", required = false) String timestamp,
                          @RequestParam(name = "nonce", required = false) String nonce,
                          @RequestParam(name = "echostr", required = false) String echostr,
            HttpServletResponse response) throws IOException {
        this.logger.info("\n接收到来自微信服务器的认证消息：signature = [{}], timestamp = [{}], nonce = [{}], echostr = [{}]",
                signature, timestamp, nonce, echostr);

        if (StringUtils.isAnyBlank(signature, timestamp, nonce, echostr)) {
            throw new IllegalArgumentException("请求参数非法，请核实!");
        }

        final WxMpService wxService = WxMpConfiguration.getMpService(appid);

        if (wxService.checkSignature(timestamp, nonce, signature)) {
            response.getWriter().println(echostr);
            return;
        }

        response.getWriter().println("非法请求");
    }

    @PostMapping(produces = "application/text; charset=UTF-8")
    public void post(@PathVariable String appid,
                       @RequestParam(name = "msg_signature", required = false) String msgSignature,
                       @RequestParam(name = "encrypt_type", required = false) String encryptType,
                       @RequestParam(name = "signature", required = false) String signature,
                       @RequestParam("timestamp") String timestamp,
                       @RequestParam("nonce") String nonce,
            HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setCharacterEncoding("UTF-8");

        ServletInputStream requestBody = request.getInputStream();
        this.logger.info("\n接收微信请求：[msg_signature=[{}], encrypt_type=[{}], signature=[{}]," +
                        " timestamp=[{}], nonce=[{}], requestBody=[\n{}\n] ",
                msgSignature, encryptType, signature, timestamp, nonce, requestBody);

        final WxMpService wxService = WxMpConfiguration.getMpService(appid);

        encryptType = StringUtils.defaultString("raw");
        if ("raw".equals(encryptType)) {
            // 明文传输的消息
            WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(requestBody);
            logger.info("明文消息inMessage==>{}", JSONObject.toJSON(inMessage));
            WxMpXmlOutMessage outMessage = this.route(inMessage, appid);
            if(outMessage == null) {
                //为null，说明路由配置有问题，需要注意
                response.getWriter().println("");
                return;
            }
            response.getWriter().println(outMessage.toXml());
            return;
        }

        if ("aes".equals(encryptType)) {
            // 是aes加密的消息
            WxMpXmlMessage inMessage = WxMpXmlMessage.fromEncryptedXml(
                    requestBody, wxService.getWxMpConfigStorage(), timestamp, nonce, msgSignature);
            WxMpXmlOutMessage outMessage = this.route(inMessage, appid);
            if(outMessage == null) {
                //为null，说明路由配置有问题，需要注意
                response.getWriter().println("");
                return;
            }
            response.getWriter().println(outMessage.toEncryptedXml(wxService.getWxMpConfigStorage()));
            return;
        }

        response.getWriter().println("不可识别的加密类型");
    }

    private WxMpXmlOutMessage route(WxMpXmlMessage message, String appid) {
        try {
            return WxMpConfiguration.getRouter(appid).route(message);
        } catch (Exception e) {
            this.logger.error(e.getMessage(), e);
            return null;
        }
    }
}
