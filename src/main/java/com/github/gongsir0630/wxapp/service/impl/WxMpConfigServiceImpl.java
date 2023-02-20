package com.github.gongsir0630.wxapp.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.gongsir0630.wxapp.entity.model.WxMpConfig;
import com.github.gongsir0630.wxapp.mapper.WxMpConfigMapper;
import com.github.gongsir0630.wxapp.service.WxMpConfigService;

/**
 * @author Kyle <gongsir0630@gmail.com>
 * Created on 2023/02/02
 */
@Service
public class WxMpConfigServiceImpl extends ServiceImpl<WxMpConfigMapper, WxMpConfig>
        implements WxMpConfigService {
}
