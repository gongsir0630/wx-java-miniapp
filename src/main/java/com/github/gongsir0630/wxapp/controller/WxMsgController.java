package com.github.gongsir0630.wxapp.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaSubscribeMessage;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.gongsir0630.wxapp.config.WxMaConfiguration;
import com.github.gongsir0630.wxapp.controller.res.CodeMsg;
import com.github.gongsir0630.wxapp.controller.res.Result;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 码之泪殇 GitHub: https://github.com/gongsir0630
 * @date 2021/3/21 11:42
 * 你的指尖,拥有改变世界的力量
 * 描述: 订阅消息接口
 */
@RestController
@RequestMapping("wx/msg/{appid}")
@Slf4j
public class WxMsgController {

    /**
     * 推送订阅消息
     */
    @PostMapping("/send")
    public void sendSubMsg(@PathVariable String appid,
                           String toUser,
                           String templateId,
                           String data) {
        WxMaSubscribeMessage message = new WxMaSubscribeMessage();
        message.setData(JSONObject.parseArray(data,WxMaSubscribeMessage.Data.class));
        message.setTemplateId(templateId);
        message.setToUser(toUser);
        log.info("--->>>来自[{}]的订阅消息发送请求:[{}]",appid,message);
        final WxMaService wxMaService = WxMaConfiguration.getMaService(appid);
        try {
            // 发送订阅消息
            wxMaService.getMsgService().sendSubscribeMsg(message);
        } catch (WxErrorException e) {
            log.error(e.getMessage());
        }
    }
}
