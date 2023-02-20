package com.github.gongsir0630.wxapp.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.gongsir0630.wxapp.config.WxMaConfiguration;
import com.github.gongsir0630.wxapp.entity.model.Message;
import com.github.gongsir0630.wxapp.enums.ResultCode;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 微信小程序用户相关接口
 *
 * @author Kyle <gongsir0630@gmail.com>
 * Created on 2023/02/02
 */
@RestController
@RequestMapping("wx/user/{appid}")
@Slf4j
public class WxMaUserController {

    /**
     * 登陆接口
     */
    @PostMapping("/login")
    public Message<JSONObject> login(@PathVariable String appid, String code) {
        log.info("wxApp登录请求: appid==>{}, code==>{}", appid, code);
        if (StringUtils.isBlank(code)) {
            return Message.fail(ResultCode.NO_CODE);
        }

        final WxMaService wxService = WxMaConfiguration.getMaService(appid);

        try {
            WxMaJscode2SessionResult session = wxService.getUserService().getSessionInfo(code);
            log.info("get session from wx, session==>{}", session.toString());
            JSONObject data = new JSONObject();
            data.put("openId", session.getOpenid());
            data.put("sessionKey", session.getSessionKey());
            return Message.success(data);
        } catch (WxErrorException e) {
            log.error(e.getMessage(), e);
            return Message.fail(ResultCode.NO_CODE, e.getMessage());
        }
    }

}
