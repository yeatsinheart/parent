package com.db.create.object;

import com.db.create.handle.anotation.*;

@TableName("test")
@TableComment("表注释")
@TablePrefix("test")
@TableSuffix("test")
public class Object {
    @ColumnComment("注释test")
    @ColumnName("column_test")
    @ColumnId("key")
    private String test;
    @ColumnComment("注释test2")
    @ColumnName("column_test2")
    @ColumnLength("100")
    @ColumnScale("4")
    private String test2;
}
