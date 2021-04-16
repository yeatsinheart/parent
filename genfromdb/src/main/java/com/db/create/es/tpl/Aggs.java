package com.db.create.es.tpl;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;
import java.util.Set;

public class Aggs {
    public static void aggs(Map.Entry<String, Object> aggs, StringBuffer content, Set set, int level, String parent) {
        String op = aggs.getValue().toString();
        if (op.equals("{}")) {
            content.append("//todo 補充聚合操作哦。。。。searchSourceBuilder.aggregation();\r\n");
        } else {
            ((JSONObject) aggs.getValue()).entrySet().forEach(opContent -> {
                String prefix = null==parent?"op":parent;
                //聚合操作的名字
                String opName = prefix + opContent.getKey();
                //聚合操作的内容
                ((JSONObject) opContent.getValue()).entrySet().forEach(opDetail -> {
                    if (opDetail.getKey().equals("terms")) {
                        content.append(" TermsBuilder " + opName + " = AggregationBuilders.terms(\"" + opName + "\");\r\n");
                        Terms.terms(opDetail,content,set,opName);
                    }else
                    if (opDetail.getKey().equals("date_histogram")) {
                        content.append(" DateHistogramBuilder " + opName + " = AggregationBuilders.dateHistogram(\"" + opName + "\");\r\n");
                        DateHistogram.dateHis(opDetail,content,set,opName);
                    }else
                    if (opDetail.getKey().equals("sum")) {
                        content.append(" SumBuilder " + opName + " = AggregationBuilders.sum(\"" + opName + "\");\r\n");
                        Sum.sum(opDetail,content,set,opName);
                    }else
                    if (opDetail.getKey().equals("top_hits")) {
                        content.append(" TopHitsBuilder " + opName + " = AggregationBuilders.topHits(\"" + opName + "\");\r\n");
                        TopHits.topHits(opDetail,content,set,opName);
                    }else
                    if (opDetail.getKey().equals("cardinality")) {
                        content.append(" CardinalityBuilder " + opName + " = AggregationBuilders.cardinality(\"" + opName + "\");\r\n");
                        Cardinality.cardinality(opDetail,content,set,opName);
                    }else
                    if (opDetail.getKey().equals("value_count")) {
                        content.append(" ValueCountBuilder " + opName + " = AggregationBuilders.count(\"" + opName + "\");\r\n");
                        ValueCount.count(opDetail,content,set,opName);
                    }else
                    if (opDetail.getKey().equals("avg")) {
                        content.append(" AvgBuilder " + opName + " = AggregationBuilders.avg(\"" + opName + "\");\r\n");
                        Avg.avg(opDetail,content,set,opName);
                    }else
                    if (opDetail.getKey().equals("max")) {
                        content.append(" MaxBuilder " + opName + " = AggregationBuilders.max(\"" + opName + "\");\r\n");
                        MaxB.max(opDetail,content,set,opName);
                    }else
                    if (opDetail.getKey().equals("aggs") || opDetail.getKey().equals("aggregations")) {
                        //content(level,content,opName,parent);
                            int oplevel = level+1;
                            aggs(opDetail, content, set, oplevel, opName);
                    }
                });
                content(level,content,opName,parent);
            });
        }

    }
    public static void  content(int level,StringBuffer content,String opName,String parent){
        if (level==0) {
            content.append(" searchSourceBuilder.aggregation(" + opName + ");\r\n\r\n");
        } else {
            content.append(parent + ".subAggregation(" + opName + ");\r\n\r\n");
        }
    }
}
