package com.github.gongsir0630.wxapp.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Kyle <gongsir0630@gmail.com>
 * Created on 2023/02/02
 */
@Configuration
@MapperScan("com.github.gongsir0630.wxapp.mapper")
public class MybatisPlusConfig {
}
