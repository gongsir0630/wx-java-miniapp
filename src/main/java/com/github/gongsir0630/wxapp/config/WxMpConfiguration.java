package com.github.gongsir0630.wxapp.config;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.github.gongsir0630.wxapp.entity.model.WxMpConfig;
import com.github.gongsir0630.wxapp.service.WxMpConfigService;
import com.google.common.collect.Maps;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxRuntimeException;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;

/**
 * @author Kyle <gongsir0630@gmail.com>
 * Created on 2023/02/02
 */
@Configuration
@Slf4j
public class WxMpConfiguration {
    private final WxMpConfigService wxMpConfigService;

    private static final Map<String, WxMpMessageRouter> routers = Maps.newHashMap();
    private static Map<String, WxMpService> mpServiceMap;

    @Autowired
    public WxMpConfiguration(WxMpConfigService wxMpConfigService) {
        this.wxMpConfigService = wxMpConfigService;
    }

    public static WxMpService getMpService(String appid) {
        WxMpService wxService = mpServiceMap.get(appid);
        if (wxService == null) {
            throw new IllegalArgumentException(String.format("未找到对应appid=[%s]的配置，请核实！", appid));
        }

        return wxService;
    }

    public static WxMpMessageRouter getRouter(String appid) {
        return routers.get(appid);
    }

    @PostConstruct
    public void init() {
        // 查询所有公众号的配置
        List<WxMpConfig> configs = wxMpConfigService.list();
        if (configs == null) {
            throw new WxRuntimeException("无相关app数据");
        }

        mpServiceMap = configs.stream()
                .map(a -> {
                    WxMpDefaultConfigImpl config = new WxMpDefaultConfigImpl();
                    config.setAppId(a.getAppid());
                    config.setSecret(a.getSecret());
                    config.setToken(a.getToken());
                    config.setAesKey(a.getAesKey());

                    WxMpService service = new WxMpServiceImpl();
                    service.setWxMpConfigStorage(config);

                    routers.put(a.getAppid(), this.newRouter(service));
                    return service;
                }).collect(Collectors.toMap(k -> k.getWxMpConfigStorage().getAppId(), v -> v));
    }

    private WxMpMessageRouter newRouter(WxMpService service) {
        final WxMpMessageRouter router = new WxMpMessageRouter(service);
        router.rule()
                .async(false)
                .content("周宇欣") // 拦截内容为“周宇欣”的消息
                .handler(handler)
                .end();
        return router;
    }

    private final WxMpMessageHandler handler = (wxMpXmlMessage, map, wxMpService, wxSessionManager) ->
            WxMpXmlOutMessage.TEXT().content("爱你奥～欣欣宝贝")
                    .fromUser(wxMpXmlMessage.getToUser())
                    .toUser(wxMpXmlMessage.getFromUser())
                    .build();

}
