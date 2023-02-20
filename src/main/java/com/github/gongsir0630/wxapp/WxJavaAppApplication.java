package com.github.gongsir0630.wxapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

/**
 * @author Kyle <gongsir0630@gmail.com>
 * Created on 2023/02/02
 */
@SpringBootApplication
public class WxJavaAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(WxJavaAppApplication.class, args);
    }

    /**
     * fastjson 配置注入: 使用阿里巴巴的 fastjson 处理 json 信息
     * @return HttpMessageConverters
     */
    @Bean
    public HttpMessageConverters httpMessageConverters() {
        FastJsonHttpMessageConverter jsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        // fastjson 配置
        FastJsonConfig config = new FastJsonConfig();
        config.setSerializerFeatures(SerializerFeature.PrettyFormat);
        config.setDateFormat("yyyy-MM-dd");
        // 配置注入消息转换器
        jsonHttpMessageConverter.setFastJsonConfig(config);
        // 让 spring 使用自定义的消息转换器
        return new HttpMessageConverters(jsonHttpMessageConverter);
    }

}
