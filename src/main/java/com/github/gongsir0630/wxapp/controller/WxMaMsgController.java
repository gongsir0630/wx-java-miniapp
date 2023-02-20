package com.github.gongsir0630.wxapp.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.gongsir0630.wxapp.config.WxMaConfiguration;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaSubscribeMessage;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * @author Kyle <gongsir0630@gmail.com>
 * Created on 2023/02/02
 */
@RestController
@RequestMapping("wx/msg/{appid}")
@Slf4j
public class WxMaMsgController {

    /**
     * 推送订阅消息
     */
    @PostMapping("/send")
    public void sendSubMsg(@PathVariable String appid,
                           String toUser,
                           String templateId,
                           String data) {
        WxMaSubscribeMessage message = new WxMaSubscribeMessage();
        message.setData(JSONObject.parseArray(data, WxMaSubscribeMessage.MsgData.class));
        message.setTemplateId(templateId);
        message.setToUser(toUser);
        log.info("收到订阅消息发送请求, appid==>{}, message==>{}", appid, JSONObject.toJSON(message));
        final WxMaService wxMaService = WxMaConfiguration.getMaService(appid);
        try {
            // 发送订阅消息
            wxMaService.getMsgService().sendSubscribeMsg(message);
        } catch (WxErrorException e) {
            log.error(e.getMessage());
        }
    }
}
