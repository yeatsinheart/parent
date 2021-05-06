package com.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Slf4j
public class JsonUtil {
    public static ObjectMapper getMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.configure(SerializationFeature.INDENT_OUTPUT, false);
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);

        mapper.registerModule(new Jdk8Module());
        JavaTimeModule module = new JavaTimeModule();
        module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        mapper.registerModule(module);

        return mapper;
    }

    public static <T> String toJsonStr(T entity) {
        String json = null;
        try {
            json = getMapper().writeValueAsString(entity);
        } catch (JsonProcessingException e) {
            log.error("{}", e);
        }
        return json;
    }

    @SneakyThrows
    public static <T> byte[] toJsonBytes(T entity) {
        return getMapper().writeValueAsBytes(entity);
    }

    public static <T> JsonNode toJsonNode(T entity) {
        return getMapper().valueToTree(entity);
    }

    public static <T> boolean toJsonFile(String filepath, T entity) {
        File file = new File(filepath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException var4) {
                var4.printStackTrace();
                return false;
            }
        }

        return toJsonFile(new File(filepath), entity);
    }

    @SneakyThrows
    public static <T> boolean toJsonFile(File file, T entity) {
        try {
            getMapper().writeValue(file, entity);
            return true;
        } catch (FileNotFoundException var3) {
            log.error("File not exists");
            var3.printStackTrace();
            return false;
        }
    }

    @SneakyThrows
    public static <T> T toObj(String json, Class<T> type) {
        return getMapper().readValue(json, type);
    }

    @SneakyThrows
    public static Map<String, Object> toMap(String json) {
        return (Map) getMapper().readValue(json, Map.class);
    }

    @SneakyThrows
    public static <T> Map<String, T> toMap(String json, Class<T> type) {
        return (Map) getMapper().readValue(json, new TypeReference<Map<String, T>>() {
        });
    }

    public static <T> T map2obj(Map map, Class<T> type) {
        return getMapper().convertValue(map, type);
    }

    @SneakyThrows
    public static <T> List<T> toList(String json, Class<T> T) {
        ObjectMapper mapper = getMapper();
        CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, T);
        return (List) mapper.readValue(json, type);
    }

    @SneakyThrows
    public static JsonNode toJsonNode(String json) {
        return getMapper().readTree(json);
    }

    public static ObjectNode toObjectNode() {
        return JsonNodeFactory.instance.objectNode();
    }

    public static boolean isJsonString(String json) {
        try {
            getMapper().readTree(json);
            return true;
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.debug("check input string is json format;json : " + json + " ; exception;" + e.getMessage());
            }
            return false;
        }
    }

    public static ArrayNode arrayNode() {
        return JsonNodeFactory.instance.arrayNode();
    }

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap();
        map.put("role", "1001");
        map.put("time", "2016-07-07");
        map.put("test", null);
        map.put("dtime", LocalDateTimeUtil.timestamp13());
        map.put("dcdtime", LocalDateTime.now());
        map.put("dctime", new Date());
        Map<String, Object> rtnMap = toMap(toJsonStr(map));
        System.out.println(rtnMap);
    }

}
