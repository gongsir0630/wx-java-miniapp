package com.github.gongsir0630.wxapp.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.gongsir0630.wxapp.entity.model.WxMaConfig;
import com.github.gongsir0630.wxapp.mapper.WxMaConfigMapper;
import com.github.gongsir0630.wxapp.service.WxMaConfigService;

/**
 * @author Kyle <gongsir0630@gmail.com>
 * Created on 2023/02/02
 */
@Service
public class WxMaConfigServiceImpl extends ServiceImpl<WxMaConfigMapper, WxMaConfig>
        implements WxMaConfigService {
}
