package com.db.create.es.tpl;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;
import java.util.Set;

public class Terms {
    public static void terms(Map.Entry<String, Object> aggs, StringBuffer content, Set set, String opName) {
        ((JSONObject) aggs.getValue()).entrySet().forEach(s -> {
            if (s.getKey().equals("field")) {
                if (s.getValue().equals("{}")) {
                    content.append("//todo " + opName + ".field(" + s.getValue() + ");\r\n");
                } else {
                    content.append(opName + ".field(\"" + s.getValue() + "\");\r\n");
                }
            }else
            if (s.getKey().equals("size")) {
                if (s.getValue().equals("888888")||s.getValue().equals(888888)) {
                    content.append("//todo 補充size" + opName + ".size(" + s.getValue() + ");\r\n");
                } else {
                    content.append(opName + ".size(" + s.getValue() + ");\r\n");
                }
            }else
            if (s.getKey().equals("order")) {
                ((JSONObject)s.getValue()).entrySet().forEach(st->{
                    if (st.getValue().equals("\"{}\"") ||st.getValue().equals("{}")) {
                        content.append("//todo 补充排序条件" + opName + ".order(Terms.Order.aggregation(\""+st.getKey()+"\",false));\r\n");
                    } else {
                        if (st.getValue().equals("asc")) {
                            content.append(opName + ".order(Terms.Order.aggregation(\""+st.getKey()+"\",true));\r\n");
                        }else {
                            content.append(opName + ".order(Terms.Order.aggregation(\""+st.getKey()+"\",false));\r\n");
                        }
                    }
                });

            }
        });
    }
}
