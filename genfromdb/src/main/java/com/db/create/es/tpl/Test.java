package com.db.create.es.tpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.db.create.freemarker.Freemarker;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Test {
    public static int num = 0;
    public static String espath = "F:\\code\\report\\application\\lucene";
    public static Set set = new HashSet();

    public static void main(String[] args) {
        //要遍历的路径
        File file = new File(espath);        //获取其file对象
        func(file);
        set.forEach(s -> System.out.println(s));
    }

    public static String toUpperFristChar(String string) {
        string = string.substring(0, 1).toUpperCase() + string.substring(1);
        return string;
    }

    public static void readToBuffer(StringBuffer buffer, String filePath) throws IOException {
        InputStream is = new FileInputStream(filePath);
        String line; // 用来保存每行读取的内容
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        line = reader.readLine();
        while (line != null) {
            buffer.append(line); // 将读到的内容添加到 buffer 中d
            buffer.append("\r\n"); // 添加换行符
            line = reader.readLine(); // 读取下一行
        }
        reader.close();
        is.close();
    }

    private static void func(File file) {
        File[] fs = file.listFiles();
        for (File f : fs) {
            if (f.isDirectory())
                func(f);
            if (f.isFile()) {
                //若是文件，直接打印
                if (!f.getAbsolutePath().split("\\.")[1].equals("tpl")) continue;
                num++;
                String path = f.getAbsolutePath();
                path = path.replaceAll("F:\\\\code\\\\report\\\\application\\\\lucene", "");
                String[] info = path.split("\\\\");
                String last = info[info.length - 1];
                String fileName = last.split("\\.")[0];
                info[info.length - 1] = toUpperFristChar(fileName) + ".java";
                String packageInfo = "";
                String packagePath = "";
                String raw = "";
                for (int i = 0; i < info.length - 1; i++) {
                    String part = info[i];
                    packageInfo += part + ".";
                    packagePath += part + "\\";
                    raw += part + "/";
                }
                //File create = new File("F:\\\\create"+packagePath+toUpperFristChar(fileName) + ".java");
                Map<String, Object> data = new HashMap<>();
                data.put("pac", packageInfo.subSequence(0, packageInfo.length() - 1));
                data.put("class", toUpperFristChar(fileName)+"Dao");
                data.put("raw", raw + fileName);
                StringBuffer sb = new StringBuffer();
                StringBuffer content = new StringBuffer();

                try {
                    readToBuffer(sb, f.getAbsolutePath());
                    JSONObject json = JSON.parseObject(sb.toString().replaceAll("%s", "{}").replaceAll("%d", "888888"));
                    //从第一层开始解析
                    json.entrySet().stream().forEach(k -> {
                        //最外层的DSL
                        String key = k.getKey();

                        if (key.equals("size")) {
                            if (k.getValue().equals("888888") || k.getValue().equals(888888)) {
                                content.append("// todo 填充 size ；searchSourceBuilder.size(" + k.getValue() + ");\r\n");
                            } else {
                                content.append("searchSourceBuilder.size(" + k.getValue() + ");\r\n");
                            }
                        } else if (key.equals("from")) {
                            if (k.getValue().equals("888888") || k.getValue().equals(888888)) {
                                content.append("// todo 填充 from ；searchSourceBuilder.from(" + k.getValue() + ");\r\n");
                            } else {
                                content.append("searchSourceBuilder.from(" + k.getValue() + ");\r\n");
                            }
                        } else if (key.equals("fields")) {
                            String fields = k.getValue().toString().replaceAll("\\[", "").replaceAll("\\]", "");
                            content.append("searchSourceBuilder.fields(Arrays.asList(" + fields + "));\r\n");
                        } else if (key.equals("query")) {
                            ((JSONObject) k.getValue()).entrySet().forEach(queryKey -> {
                                if (queryKey.getKey().equals("bool")) {
                                    content.append("BoolQueryBuilder selectBool = QueryBuilders.boolQuery();\r\n");
                                    ((JSONObject) queryKey.getValue()).entrySet().forEach(boolKey -> {
                                        if (boolKey.getKey().equals("filter")) {

                                            Object filterObject = boolKey.getValue();
                                            if (filterObject instanceof JSONObject) {
                                                ((JSONObject) boolKey.getValue()).entrySet().forEach(filterKey -> {

                                                    if (filterKey.getKey().equals("terms")) {

                                                        ((JSONObject) filterKey.getValue()).entrySet().forEach(t -> {
                                                            String values = t.getValue().toString();
                                                            values = values.replaceAll("\\[", "").replaceAll("\\]", "");
                                                            if ("{}".equals(values)) {
                                                                content.append("//todo 待補充 terms "+ t.getKey() + "查詢條件 selectBool.filter( QueryBuilders.termsQuery(\"" + t.getKey() + "\",Arrays.asList()));\r\n");
                                                            } else {
                                                                content.append(" selectBool.filter( QueryBuilders.termsQuery(\"" + t.getKey() + "\",Arrays.asList(" + values + ")));\r\n");
                                                            }

                                                        });

                                                    }else
                                                    if (filterKey.getKey().equals("not")) {
                                                        ((JSONObject) filterKey.getValue()).entrySet().forEach(t -> {
                                                            ((JSONObject) t.getValue()).entrySet().forEach(notTerms -> {

                                                                String values = notTerms.getValue().toString();
                                                                values = values.replaceAll("\\[", "").replaceAll("\\]", "");
                                                                if ("{}".equals(values)) {
                                                                    content.append("//todo 補充 "+ notTerms.getKey() + " not 條件 select.filter(QueryBuilders.notQuery(QueryBuilders.termsQuery(\"" + notTerms.getKey() + "\",Arrays.asList())));\r\n");
                                                                } else {
                                                                    content.append(" select.filter(QueryBuilders.notQuery(QueryBuilders.termsQuery(\"" + notTerms.getKey() + "\",Arrays.asList(" + values + "))));\r\n");
                                                                }
                                                            });


                                                        });
                                                    }else {

                                                    }
                                                });
                                            }else
                                            if (filterObject instanceof JSONArray) {
                                                ((JSONArray) filterObject).forEach(filterTermsArray -> {
                                                    if (filterTermsArray.toString().equals("{}")) {
                                                        content.append("//todo 待補充terms條件 selectBool.filter( );\r\n");
                                                    }else{

                                                        ((JSONObject) filterTermsArray).entrySet().forEach(t -> {
                                                            if (t.getKey().equals("terms")) {
                                                                ((JSONObject) t.getValue()).entrySet().forEach(te -> {

                                                                    String values = te.getValue().toString();
                                                                    values = values.replaceAll("\\[", "").replaceAll("\\]", "");
                                                                    if ("{}".equals(values)) {
                                                                        content.append("//todo 補充" + te.getKey() + "的值  selectBool.filter( QueryBuilders.termsQuery(\"" + te.getKey() + "\",Arrays.asList()));\r\n");
                                                                    } else {
                                                                        content.append(" selectBool.filter( QueryBuilders.termsQuery(\"" + te.getKey() + "\",Arrays.asList(" + values + ")));\r\n");
                                                                    }
                                                                });
                                                            }


                                                        });
                                                    }

                                                });
                                            }
                                        }else
                                        if (boolKey.getKey().equals("should")) {
                                            ((JSONArray) boolKey.getValue()).forEach(t -> {
                                                if (t.toString().equals("{}")) {
                                                    content.append("//todo 補充shoud條件 selectBool.should(QueryBuilders.termsQuery(\"\", Arrays.asList()));\r\n");
                                                }else{
                                                    ((JSONObject) t).entrySet().forEach(s -> {
                                                        if (s.getKey().equals("term")) {
                                                            ((JSONObject) s.getValue()).entrySet().forEach(ts -> {
                                                                    content.append(" selectBool.should(QueryBuilders.termsQuery(\"" + ts.getKey() + "\", Arrays.asList(" + ts.getValue() + ")));\r\n");
                                                            });
                                                        }else{


                                                        }
                                                    });
                                                }
                                            });
                                        }else
                                        if (boolKey.getKey().equals("must")) {

                                            ((JSONArray) boolKey.getValue()).forEach(s -> {
                                                if (s.toString().equals("{}")) {
                                                    content.append("//todo 補充must條件selectBool.must(QueryBuilders.termsQuery(\"\",Arrays.asList()));\r\n");
                                                } else {

                                                    ((JSONObject) s).entrySet().forEach(t -> {
                                                        if (t.getKey().equals("term")) {
                                                            ((JSONObject) t.getValue()).entrySet().forEach(ts -> {
                                                                String value_temp = ts.getValue().toString();
                                                                if (value_temp.equals("{}") || value_temp.equals("\"{}\"") ){

                                                                    content.append("//todo 補充" + ts.getKey() + "值selectBool.must(QueryBuilders.termsQuery(\"" + ts.getKey() + "\",Arrays.asList(" + ts.getValue() + ")));\r\n");
                                                                }else if( value_temp.equals("888888")|| value_temp.equals(888888)) {

                                                                    content.append("//todo 補充" + ts.getKey() + "值selectBool.must(QueryBuilders.termsQuery(\"" + ts.getKey() + "\",Arrays.asList(" + ts.getValue() + ")));\r\n");
                                                                } else {
                                                                    content.append(" selectBool.must(QueryBuilders.termsQuery(\"" + ts.getKey() + "\",Arrays.asList(" + ts.getValue() + ")));\r\n");
                                                                }

                                                            });
                                                        } else {
                                                            ((JSONObject) t.getValue()).entrySet().forEach(ts -> {
                                                                    content.append(" //todo  待補充起始時間 selectBool.must(QueryBuilders.rangeQuery(\"" + ts.getKey() + "\").gte().lte().format(\"epoch_millis\"));\r\n");
                                                            });

                                                        }
                                                    });
                                                }
                                            });
                                        }else
                                        if (boolKey.getKey().equals("adjust_pure_negative")) {
                                            content.append(" selectBool.adjustPureNegative(true);\r\n");
                                        }else
                                        if (boolKey.getKey().equals("minimum_should_match")) {
                                            if (boolKey.getValue().equals("888888")||boolKey.getValue().equals(888888)) {
                                                content.append("//todo 補充minimumNumberShouldMatch值 selectBool.minimumNumberShouldMatch(" + boolKey.getValue() + ");\r\n");
                                            } else {
                                                content.append(" selectBool.minimumNumberShouldMatch(" + boolKey.getValue() + ");\r\n");
                                            }

                                        }else
                                        if (boolKey.getKey().equals("must_not")) {
                                            ((JSONArray) boolKey.getValue()).forEach(s -> {
                                                if (s.toString().equals("{}")) {
                                                    content.append("//todo selectBool.mustNot(QueryBuilders.termsQuery(\"\",Arrays.asList()));\r\n");
                                                } else {

                                                    ((JSONObject) s).entrySet().forEach(t -> {
                                                        if (t.getKey().equals("term")) {
                                                            ((JSONObject) t.getValue()).entrySet().forEach(ts -> {
                                                                content.append("//todo 補充" + ts.getKey() + "值 selectBool.mustNot(QueryBuilders.termsQuery(\"" + ts.getKey() + "\",Arrays.asList()));\r\n");
                                                            });

                                                        }
                                                    });
                                                }
                                            });

                                        }else
                                        if (boolKey.getKey().equals("boost")) {

                                            content.append(" selectBool.boost(" + boolKey.getValue() + ");;\r\n");
                                        }

                                    });
                                    content.append("searchSourceBuilder.query(selectBool);\r\n");
                                }else
                                if (queryKey.getKey().equals("term")) {
                                    ((JSONObject) queryKey.getValue()).entrySet().forEach(termsKey -> {
                                        content.append("//todo 待補充term查詢 TermsQueryBuilder "+termsKey.getKey()+"SelectTerms =  QueryBuilders.termsQuery(\"" + termsKey.getKey() + "\",Arrays.asList());\r\n");
                                        content.append("//todo 待補充term查詢 searchSourceBuilder.query("+termsKey.getKey()+"SelectTerms);\r\n");
                                    });
                                }

                            });
                        } else if (key.equals("_source")) {
                            String sources = k.getValue().toString();
                            if(sources.startsWith("[")){
                                String sourcesRaw = sources.replaceAll("\\[", "").replaceAll("\\]", "");
                                content.append("searchSourceBuilder.fetchSource(new String[]{" + sourcesRaw + "},new String[]{});\r\n");
                            }else{
                                content.append("searchSourceBuilder.fetchSource(new String[]{\"" + k.getValue() + "\"},new String[]{});\r\n");
                            }
                        } else if (key.equals("sort")) {
                            ((JSONArray)k.getValue()).forEach(t->{
                                ((JSONObject)t).entrySet().forEach(ts->{
                                    content.append("// todo 排序" + k.getValue() + "\r\n");
                                    content.append("//searchSourceBuilder.sort(\""+ts.getKey()+"\",);;\r\n");
                                });
                            });

                        }else
                        if (key.equals("aggregations") || key.equals("aggs")) {
                            Aggs.aggs(k, content, set, 0, null);
                        } else {

                            content.append("//todo  代码未覆盖 " + k.getValue() + "\r\n");
                        }

                    });
                    data.put("content", sb);
                    data.put("java", content);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Freemarker.write("src/main/java/com/auto/database/create/freemarker/model/", "es.ftl", data, "F:\\code\\es-spring-demo\\src\\main\\java\\com\\example\\esspringdemo\\service\\game\\dao" + packagePath, toUpperFristChar(fileName) + "Dao.java");
            }

        }

    }
}
