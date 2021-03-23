package com.github.gongsir0630.wxapp.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 码之泪殇 GitHub: https://github.com/gongsir0630
 * @date 2021/3/21 10:34
 * 你的指尖,拥有改变世界的力量
 * 描述:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("app")
public class AppConfig {
    @TableId(value = "appid", type = IdType.INPUT)
    private String appid;
    private String secret;
    private String token;
    private String aesKey;
    private String msgDataFormat;
}
