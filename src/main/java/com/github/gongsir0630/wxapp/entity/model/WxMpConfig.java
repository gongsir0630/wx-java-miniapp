package com.github.gongsir0630.wxapp.entity.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 微信公众号配置信息
 *
 * @author Kyle <gongsir0630@gmail.com>
 * Created on 2023/02/02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("wx_mp_config")
public class WxMpConfig {
    @TableId(value = "appid", type = IdType.INPUT)
    private String appid;
    private String secret;
    private String token;
    private String aesKey;
}
