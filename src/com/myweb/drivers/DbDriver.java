package com.myweb.drivers;

import java.sql.ResultSet;

public interface DbDriver {
    ResultSet fetchAll(String fields, String tableName, String where);
    ResultSet fetchOne(String fields, String tableName, String primaryId, Integer id);
    Boolean insert(String tableName, String fields, String values);
    Object update(String tableName, String sets, String where);
    Object update(String tableName, String sets, String primaryId, Integer id);
    Object delete(String tableName, String where);
    Object delete(String tableName, String primaryId, Integer id);
    Object query(String sql);
    Object execute(String sql);
}
