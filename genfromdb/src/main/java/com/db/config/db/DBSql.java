package com.db.config.db;

import com.db.config.ConnectInfo;
import org.springframework.util.CollectionUtils;

import java.util.List;

import static com.db.config.db.DBType.MYSQL;
import static com.db.config.db.DBType.POSTGRESQL;

/**
 * @program: freework
 * @description: this is a 数据库信息
 * @author: Yeats
 * @create: 2019-06-03 14:20
 **/
public enum DBSql {
    /**
     * TODO 获取到语句后，需要进行 #{tableName}替换表名 '表名'
     */
    MYSQL_SQL(MYSQL,
            "SELECT  " +
                    "    table_name name, " +
                    "    engine, " +
                    "    table_comment comment, " +
                    "    create_time createTime " +
                    "FROM " +
                    "    information_schema.tables " +
                    "WHERE " +
                    "    table_schema = (SELECT DATABASE()) AND  table_name in (#{tables})  ORDER BY createTime ",
            "SELECT \n" +
                    "A.TABLE_NAME 'table_name', \n" +
                    "A.COLUMN_NAME 'name', \n" +
                    "A.DATA_TYPE 'raw_type', \n" +
                    "A.COLUMN_TYPE 'type',\n" +
                    "CASE WHEN A.NUMERIC_PRECISION IS NOT NULL THEN A.NUMERIC_PRECISION  WHEN A.CHARACTER_MAXIMUM_LENGTH IS NOT NULL THEN A.CHARACTER_MAXIMUM_LENGTH ELSE NULL  END 'length',\n" +
                    "CASE WHEN A.NUMERIC_SCALE IS NOT NULL THEN A.NUMERIC_SCALE ELSE NULL  END 'scale',\n" +
                    "CASE WHEN A.IS_NULLABLE = 'YES' THEN 'false' ELSE 'true'  END 'notNull', A.COLUMN_KEY 'key', A.COLUMN_COMMENT 'comment' \n" +
                    "FROM information_schema.COLUMNS A \n" +
                    "WHERE TABLE_NAME='#{tableName}' AND TABLE_SCHEMA=(SELECT DATABASE()) \n" +
                    "ORDER BY TABLE_NAME "),
    POSTGRESQL_SQL(POSTGRESQL,
            "select relname \"name\",objsubid,description \"comment\" \n" +
                    "from pg_description join pg_class \n" +
                    "on pg_description.objoid = pg_class.oid \n" +
                    "WHERE objsubid=0 and relname in (#{tables})  ",
            "SELECT \"A\".*,\"B\".TYPE ,\"B\".COMMENT,\"B\".KEY FROM(\n" +
                    "\tSELECT \n" +
                    "\t\"A\".TABLE_NAME \"table_name\", \n" +
                    "\t\"A\".COLUMN_NAME \"name\", \n" +
                    "\t\"A\".DATA_TYPE \"raw_type\", \n" +
                    "\tCASE WHEN \"A\".NUMERIC_PRECISION IS NOT NULL THEN \"A\".NUMERIC_PRECISION  WHEN \"A\".CHARACTER_MAXIMUM_LENGTH IS NOT NULL THEN \"A\".CHARACTER_MAXIMUM_LENGTH ELSE NULL  END   \"length\",\n" +
                    "\tCASE WHEN \"A\".NUMERIC_SCALE IS NOT NULL THEN \"A\".NUMERIC_SCALE ELSE NULL  END \"scale\",\n" +
                    "\tCASE WHEN \"A\".IS_NULLABLE='YES' THEN 'false' ELSE 'true'  END \"notNull\"\n" +
                    "\tFROM information_schema.COLUMNS \"A\" \n" +
                    "\tWHERE TABLE_NAME='#{tableName}'\n" +
                    ") \"A\" LEFT JOIN (SELECT distinct * from ( \n" +
                    "\tSELECT A.attname AS name, format_type(A.atttypid, A.atttypmod) AS type,col_description(A.attrelid, A.attnum) AS comment, (CASE C.contype WHEN 'p' THEN 'PRI' ELSE '' END) AS key  \n" +
                    "\tFROM pg_attribute A LEFT JOIN pg_constraint C ON A.attnum = C.conkey[1] AND A.attrelid = C.conrelid  \n" +
                    "\tWHERE  A.attrelid = 'public.#{tableName}'::regclass AND A.attnum > 0 AND NOT A.attisdropped ORDER  BY A.attnum ) as foo\n" +
                    ") \"B\" \n" +
                    "ON \"A\".NAME=\"B\".NAME"),
    NULL();
    private String tableSql;
    private String columnSql;
    private DBType type;

    public static DBSql getTableSqlByDriver(ConnectInfo info) {
        for (DBSql base : DBSql.values()) {
            if (base.getType().equals(info.getDriver())) {
                return base;
            }
        }
        return null;
    }

    DBSql() {
    }

    DBSql(DBType type, String tableSql, String columnSql) {
        this.tableSql = tableSql;
        this.columnSql = columnSql;
        this.type = type;
    }

    public String getTableSql(List<String> tables) {
        if (CollectionUtils.isEmpty(tables))
            return tableSql
                    .replaceAll("AND  table_name in \\(#\\{tables\\}\\)", "")
                    .replaceAll("AND A.tablename in \\(#\\{tables\\}\\)", "")
                    .replaceAll("and relname in \\(#\\{tables\\}\\)", "");
        return tableSql.replaceAll("#\\{tables\\}", "'" + String.join("','", tables) + "'").replaceAll("\\[", "").replaceAll("\\]", "");
    }

    public String getColumnSql(String tableName) {
        String columnssql =  columnSql.replaceAll("#\\{tableName\\}", "" + tableName + "");
        return columnssql;
    }

    public DBType getType() {
        return type;
    }
}
