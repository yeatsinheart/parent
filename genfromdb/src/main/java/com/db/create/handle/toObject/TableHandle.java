package com.db.create.handle.toObject;

import com.db.config.db.Column;
import com.db.config.db.DBType;
import com.db.config.db.Table;
import com.db.utils.NameConvertor;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableHandle {
    public static Map toMap(DBType dbType, Table table, String tablePrefix, String tableSuffix, String columnPrefix, String columnSuffix) {
        Map<String, Object> map = new HashMap();
        map.put("name", table.getName());
        String fName = table.getName();
        if (table.getName().startsWith(tablePrefix)) {
            fName = fName.replaceAll(tablePrefix, "");
        }
        if (table.getName().endsWith(tableSuffix)) {
            fName = fName.replaceAll(tableSuffix, "");
        }
        map.put("nameFormatted", fName);
        map.put("objectName", NameConvertor.underlineToCamel((String) map.get("nameFormatted")));
        String c = StringUtils.isBlank(table.getComment()) ? "" : table.getComment();
        map.put("comment", c.replaceAll("\\\r", ";").replaceAll("\\\n", ";"));
        List<Map> columns = new ArrayList<>();
        List<Column> columnList = table.getColumnList();
        for (Column column : columnList) {
            columns.add(ColumnHandle.toMap(dbType, column, columnPrefix, columnSuffix));
        }
        map.put("columns", columns);
        return map;
    }
}
