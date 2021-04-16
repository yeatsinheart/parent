package com.db.config.relation;

import com.db.config.db.DBType;
import com.db.config.relation.db.MySQL;
import com.db.config.relation.db.PostgreSQL;
import com.db.config.relation.db.SQL;

public class DataBaseJavaRelation {
    /**
     * 获取对应数据库的映射关系
     */
    public static SQL getRelationByDataBase(DBType DBType) {
        switch (DBType) {
            case MYSQL:
                return new MySQL();
            case POSTGRESQL:
                return new PostgreSQL();
        }
        return null;
    }
}
