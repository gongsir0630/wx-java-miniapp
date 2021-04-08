package com.github.gongsir0630.wxapp.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.alibaba.fastjson.JSONObject;
import com.github.gongsir0630.wxapp.config.WxMaConfiguration;
import com.github.gongsir0630.wxapp.controller.res.CodeMsg;
import com.github.gongsir0630.wxapp.controller.res.Result;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author 码之泪殇 GitHub: https://github.com/gongsir0630
 * @date 2021/3/21 11:42
 * 你的指尖,拥有改变世界的力量
 * 描述:
 */
@RestController
@RequestMapping("wx/user/{appid}")
@Slf4j
public class WxMaUserController {

    /**
     * 登陆接口
     */
    @PostMapping("/login")
    public Result<JSONObject> login(@PathVariable String appid, String code) {
        log.info("wxApp登录请求: {appid:[{}], code:[{}]}",appid,code);
        if (StringUtils.isBlank(code)) {
            return Result.fail(CodeMsg.NO_CODE, null);
        }

        final WxMaService wxService = WxMaConfiguration.getMaService(appid);

        try {
            WxMaJscode2SessionResult session = wxService.getUserService().getSessionInfo(code);
            log.info("session from wx: [{}]",session.toString());
            //TODO 可以增加自己的逻辑，关联业务相关数据
            JSONObject data = new JSONObject();
            data.put("openId",session.getOpenid());
            data.put("sessionKey",session.getSessionKey());
            return Result.success(data);
        } catch (WxErrorException e) {
            log.error(e.getMessage(), e);
            return Result.fail(new CodeMsg(-1,e.getMessage()),null);
        }
    }
}
