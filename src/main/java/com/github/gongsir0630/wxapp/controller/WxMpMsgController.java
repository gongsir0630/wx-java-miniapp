package com.github.gongsir0630.wxapp.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.gongsir0630.wxapp.config.WxMpConfiguration;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;

/**
 * @author Kyle <gongsir0630@gmail.com>
 * Created on 2023/02/02
 */
@RestController
@RequestMapping("wx/mp/msg/{appid}")
@Slf4j
public class WxMpMsgController {

    /**
     * 测试
     */
    @PostMapping("/send")
    public void wxMpSendTest(@PathVariable String appid, String openId) {
        final WxMpService wxMpService = WxMpConfiguration.getMpService(appid);
        List<WxMpTemplateData> data = new ArrayList<>();
        LocalDate now = LocalDate.now();
        data.add(new WxMpTemplateData("date", now.toString(), randomHexColor()));
        data.add(new WxMpTemplateData("city", "北京市", randomHexColor()));
        data.add(new WxMpTemplateData("weather", "晴", randomHexColor()));
        data.add(new WxMpTemplateData("temperature", "29", randomHexColor()));
        data.add(new WxMpTemplateData("humidity", "3", randomHexColor()));
        data.add(new WxMpTemplateData("love_day",
                String.valueOf(now.toEpochDay() - LocalDate.of(2023,2,1).toEpochDay()), randomHexColor()));
        data.add(new WxMpTemplateData("gfBirthDays",
                String.valueOf(LocalDate.of(2023,4,25).toEpochDay() - now.toEpochDay()), randomHexColor()));
        data.add(new WxMpTemplateData("mineBirthDays",
                String.valueOf(LocalDate.of(2023,6,30).toEpochDay() - now.toEpochDay()), randomHexColor()));
        data.add(new WxMpTemplateData("lizhi", "想你啦～", randomHexColor()));
        data.add(new WxMpTemplateData("caihongpi", "欣欣相印", randomHexColor()));
        WxMpTemplateMessage message = WxMpTemplateMessage.builder()
                .toUser(openId)
                .templateId("LAx5xL3dEH-mSZDJ1IdPfMpAkuk6J0kSV8Pjg-OW65c")
                .data(data)
                .build();
        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(message);
        } catch (WxErrorException e) {
            log.error("出错了～, appid=={}, openId==>{}", appid, openId, e);
        }
    }

    private String randomHexColor() {
        //红色
        String red;
        //绿色
        String green;
        //蓝色
        String blue;
        //生成随机对象
        Random random = new Random();
        //生成红色颜色代码
        red = Integer.toHexString(random.nextInt(256)).toUpperCase();
        //生成绿色颜色代码
        green = Integer.toHexString(random.nextInt(256)).toUpperCase();
        //生成蓝色颜色代码
        blue = Integer.toHexString(random.nextInt(256)).toUpperCase();

        //判断红色代码的位数
        red = red.length() == 1 ? "0" + red : red ;
        //判断绿色代码的位数
        green = green.length () ==1 ? "0" + green : green ;
        //判断蓝色代码的位数
        blue = blue.length() == 1 ? "0" + blue : blue ;
        //生成十六进制颜色值
        return "#" + red + green + blue;
    }
}
