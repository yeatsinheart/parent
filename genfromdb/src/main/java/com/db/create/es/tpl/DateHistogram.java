package com.db.create.es.tpl;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;
import java.util.Set;

public class DateHistogram {
    public static void dateHis(Map.Entry<String, Object> aggs, StringBuffer content, Set set, String opName) {
        ((JSONObject) aggs.getValue()).entrySet().forEach(s -> {

            if (s.getKey().equals("field")) {
                content.append(opName + ".field(\"" + s.getValue() + "\");\r\n");
            } else if (s.getKey().equals("offset")) {
                content.append("//todo " + opName + ".offset(\"" + s.getValue() + "\");\r\n");
            } else if (s.getKey().equals("format")) {
                content.append(" " + opName + ".format(\"" + s.getValue() + "\");\r\n");
            } else if (s.getKey().equals("interval")) {
                if (s.getValue().equals("{}")) {
                    content.append("//todo " + opName + ".interval(\"" + s.getValue() + "\");\r\n");
                } else if (s.getValue().equals("1d")) {
                    content.append(" " + opName + ".interval(DateHistogramInterval.DAY);\r\n");
                } else if (s.getValue().equals("1h")) {
                    content.append(" " + opName + ".interval(DateHistogramInterval.HOUR);\r\n");
                } else if (s.getValue().equals("1M")) {
                    content.append(" " + opName + ".interval(DateHistogramInterval.MONTH);\r\n");
                } else if (s.getValue().equals("10m")) {
                    content.append(" " + opName + ".interval(DateHistogramInterval.minutes(10));\r\n");
                }

            } else if (s.getKey().equals("time_zone")) {
                content.append(" " + opName + ".timeZone(\"" + s.getValue() + "\");\r\n");

            } else if (s.getKey().equals("min_doc_count")) {
                content.append(" " + opName + ".minDocCount(" + s.getValue() + ");\r\n");
            }

        });
    }
}
