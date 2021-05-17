package com.base.utils;

import com.base.constant.Language;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * i18n util
 */
@Slf4j
public class I18nUtil {
    private static final Map<String, Properties> i18Map = new HashMap<>();

    public static Properties loadI18nProp(String module, String languageCode) {
        Properties prop = null;
        try {
            String i18n = languageCode;
            i18n = Language.getFileCodeByCode(i18n);
            String i18nFile = MessageFormat.format("i18n/{0}_message_{1}.properties", module, i18n);
            Resource resource = new ClassPathResource(i18nFile);
            EncodedResource encodedResource = new EncodedResource(resource, StandardCharsets.UTF_8.name());
            prop = PropertiesLoaderUtils.loadProperties(encodedResource);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return prop;
    }

    /**
     * get val of i18n key
     *
     * @param module   所属模块
     * @param key      key
     * @param language 语言编码
     * @return
     */
    public static String getString(String module, String key, String language) {
        if (StringUtils.isEmpty(language)) {
            language = Language.中文.getCode();
        }
        Properties prop = i18Map.get(module + "::" + language);
        if (null == prop) {
            prop = loadI18nProp(module, language);
            i18Map.put(module + "::" + language, prop);
        }
        if (prop == null) {
            return null;
        }
        String value = prop.getProperty(key);
        return value;
    }


    public static String createSql(String model, String language) {
        Properties properties = loadI18nProp(model, language);
       /* "INSERT INTO global_language_file\n" +
                "                (`path`,`zh_cn`,`en`,`vi`,`type`,`project`)\n" +
                "        VALUES\n" +
                "                ('attr.userAgreement','用户使用协议','User Agreement','sự thỏa thuận của người dùng','page_json','h5');"*/
        StringBuffer sb = new StringBuffer("INSERT INTO casino_global_language_file(`path`,`zh_cn`,`project`) VALUES");
        for (Object key : properties.keySet()) {
            sb.append("(").append("'" + key.toString() + "'").append(",").append("'" + properties.get(key.toString()) + "'")
                    .append(",").append("'java_" + model + "'").append("),");
        }
        return sb.toString();
    }

    /**
     * get mult val of i18n mult key, as json
     *
     * @param keys
     * @return
     */
   /*public static String getMultString(String... keys) {
        Map<String, String> map = new HashMap<String, String>();

        Properties prop = loadI18nProp();
        if (keys != null && keys.length > 0) {
            for (String key : keys) {
                map.put(key, prop.getProperty(key));
            }
        } else {
            for (String key : prop.stringPropertyNames()) {
                map.put(key, prop.getProperty(key));
            }
        }

        String json = JacksonUtil.writeValueAsString(map);
        return json;
    }*/

}
