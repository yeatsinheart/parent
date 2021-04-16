package com.db.create.es.tpl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Map;
import java.util.Set;

public class TopHits {
    public static void topHits(Map.Entry<String, Object> aggs, StringBuffer content, Set set, String opName) {
        ((JSONObject) aggs.getValue()).entrySet().forEach(s -> {

            if (s.getKey().equals("size")) {
                content.append(opName + ".setSize(" + s.getValue() + ");\r\n");
            }else
            if (s.getKey().equals("_source")) {

                ((JSONObject)s.getValue()).entrySet().forEach(so -> {
                    String vs = so.getValue().toString();
                    vs= vs.replaceAll("\\[","").replaceAll("\\]","");
                    content.append(opName + ".setFetchSource(new String[]{"+vs+"},new String[]{});\r\n");
                });

            }else
            if (s.getKey().equals("sort")) {
                ((JSONArray)s.getValue()).forEach(so -> {
                    ((JSONObject)so).entrySet().forEach(st->{
                        String vas  = st.getValue().toString();
                        if(vas.contains("asc")){
                            content.append(opName + ".addSort(\"" + st.getKey() + "\",SortOrder.ASC);\r\n");
                        }else{
                            content.append(opName + ".addSort(\"" + st.getKey() + "\",SortOrder.DESC);\r\n");
                        }

                    });
                });

            }
        });
    }
}
