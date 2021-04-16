package com.db.create.es.tpl;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;
import java.util.Set;

public class Sum {
    public static void sum(Map.Entry<String, Object> aggs, StringBuffer content, Set set, String opName) {
        ((JSONObject) aggs.getValue()).entrySet().forEach(s -> {
            if (s.getKey().equals("field")) {
                content.append(opName + ".field(\"" + s.getValue() + "\");\r\n");
            }
        });
    }
}
