package com.nacos.converter;

import com.alibaba.nacos.api.config.convert.NacosConfigConverter;
import com.nacos.config.Api;

public class DemoNacosConfigConverter implements NacosConfigConverter<Api> {
    // api 开关
    // tenant 开关
    @Override
    public boolean canConvert(Class<Api> aClass) {
        return true;
    }

    @Override
    public Api convert(String s) {
        return null;//JSON.parseObject(source, User.class);
    }
    /*
    @NacosConfigListener(dataId = "api", converter = DemoNacosConfigConverter.class)
    public void onApi(Api api) {
    }
    */
}
