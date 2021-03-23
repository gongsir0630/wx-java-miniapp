package com.github.gongsir0630.wxapp;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;

/**
 * @author gongsir <a href="https://github.com/gongsir0630">码之泪殇</a>
 */
@SpringBootApplication
public class WxJavaMiniappApplication {

    public static void main(String[] args) {
        SpringApplication.run(WxJavaMiniappApplication.class, args);
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
