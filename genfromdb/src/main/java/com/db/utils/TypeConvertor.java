///*
// * Copyright (c) 2011-2020, baomidou (jobob@qq.com).
// * <p>
// * Licensed under the Apache License, Version 2.0 (the "License"); you may not
// * use this file except in compliance with the License. You may obtain a copy of
// * the License at
// * <p>
// * https://www.apache.org/licenses/LICENSE-2.0
// * <p>
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
// * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
// * License for the specific language governing permissions and limitations under
// * the License.
// */
//package com.db.utils;
//
//import com.db.config.db.DBType;
//import com.db.config.JavaRelation2DataBase;
//import com.db.config.JavaTypeInfo;
//import com.db.config.db.Column;
//import com.db.config.db.Table;
//
//import java.util.concurrent.ConcurrentHashMap;
//
///**
// * 字段类型转换
// *
// * @author YangHu, tangguo
// * @since 2016/8/30
// */
//public class TypeConvertor {
//    public static ConcurrentHashMap<DBType, ConcurrentHashMap<String, JavaTypeInfo>> map = new ConcurrentHashMap();
//    public static ConcurrentHashMap<String, JavaTypeInfo> typeMap = new ConcurrentHashMap();
//
//    public static JavaTypeInfo getJavaTypeInfo(Table table, Column column) {
//        if (null != map.get(table.getDataBase().getDriver()) && null != map.get(table.getDataBase().getDriver()).get(column.getDataType())) {
//            return map.get(table.getDataBase().getDriver()).get(column.getDataType());
//        }
//        for (JavaRelation2DataBase dbinfo : JavaRelation2DataBase.values()) {
//            /**数据库类型相同*/
//            if (dbinfo.getDBType().equals(table.getDataBase().getDriver())) {
//                /**表名起步*/
//              //  System.out.println(column.getDataType() + "对比" + dbinfo.getColumnType());
//                if (column.getDataType().equals(dbinfo.getColumnType()) || column.getDataType().startsWith(dbinfo.getColumnType())) {
//                    typeMap.put(column.getDataType(), dbinfo.getJavaType());
//                    map.put(table.getDataBase().getDriver(), typeMap);
//                    return dbinfo.getJavaType();
//                }
//            }
//        }
//        return null;
//    }
//}
