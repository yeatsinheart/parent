//package com.nacos.config;
//
//import com.alibaba.nacos.api.config.annotation.NacosConfigListener;
//import com.base.utils.NacosUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Value;
//
//import javax.annotation.PostConstruct;
//import java.io.StringReader;
//import java.util.Properties;
//
//import static com.alibaba.nacos.api.common.Constants.DEFAULT_GROUP;
//
//@Slf4j
//public abstract class AbstractNacosConfig {
//
//    public  static final String DATA_ID = "logging-config";
//    @Value("${nacos.config.server-addr:127.0.0.1:8848}")
//    public String nacosUrl;
//    @Value("${nacos.config.namespace:public}")
//    public String namespace;
//
//
//    @PostConstruct
//    public void start() {
//        // 校验是否存在该配置
//        String config = NacosUtil.getConfig(DATA_ID, DEFAULT_GROUP, namespace, nacosUrl);
//        log.warn(DATA_ID + "配置，启动检查，初始化本地相关配置 nacos中配置信息{}", config);
//        if (StringUtils.isEmpty(config)) {
//            if (!updateToNacos(null)) {
//                log.error(DATA_ID + "Nacos生成配置文件失败");
//            }
//        }
//        if(!initByValue(string2Properties(config))){
//            log.error(DATA_ID + "配置文件加载失败");
//        }
//    }
//
//    public Properties string2Properties(String value) {
//        Properties properties = new Properties();
//        try {
//            properties.load(new StringReader(value));
//        } catch (Exception e) {
//            log.error(DATA_ID + " 配置信息{}加载失败",value);
//        }
//        log.info(DATA_ID + "转换出来的properties信息{}", properties);
//        return properties;
//    }
//
//
//    @NacosConfigListener(dataId = DATA_ID)
//    public void onChange(String value) {
//        log.warn(DATA_ID + " 收到配置变更通知 " + value);
//        try {
//            if (!initByValue(string2Properties(value))) {
//                log.error(DATA_ID + "本地变更时失败，{}",value);
//            }
//        } catch (Exception e) {
//            log.error(DATA_ID + "变更时失败");
//        }
//    }
//
//
//    //监听到配置修改后进行操作
//    public boolean updateToNacos(String config) {
//        log.warn(DATA_ID + " nacos中创建或更新配置信息" + config);
//        if (StringUtils.isEmpty(config)) {
//            return NacosUtil.putConfig(defaultProperties(), "properties", DATA_ID, DEFAULT_GROUP, namespace, nacosUrl);
//        } else {
//            return NacosUtil.putConfig(config, "properties", DATA_ID, DEFAULT_GROUP, namespace, nacosUrl);
//        }
//    }
//
//    /**
//     * 实现根据nacos参数更新运行时内容的方法
//     */
//    public abstract boolean initByValue(Properties properties);
//
//    /**
//     * 默认写入nacos中的String内容，考虑本地还是从数据库中读取出来再写入nacos，但是只是nacos中没有值的时候才会这样搞
//     */
//    public abstract String defaultProperties();
//
//
//}
