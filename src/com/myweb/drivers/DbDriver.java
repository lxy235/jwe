package com.myweb.drivers;

import com.myweb.drivers.mysql.RowSet;
import com.myweb.drivers.mysql.Statement;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

public interface DbDriver {
    ArrayList<HashMap<String, Object>> fetchAll(String fields, String tableName, String where);

    ArrayList<HashMap<String, Object>> fetchAll(String fields, String tableName, Statement statement);

    HashMap<String, Object> fetchOne(String fields, String tableName, String primaryId, Integer id);

    HashMap<String, Object> fetchOne(String fields, String tableName, String where);

    Boolean insert(String tableName, RowSet rowset);

    Object update(String tableName, String sets, String where);

    Object update(String tableName, String sets, String primaryId, Integer id);

    Object delete(String tableName, String where);

    Object delete(String tableName, String primaryId, Integer id);

    Object query(String sql);

    Object execute(String sql);

    void startTransaction();

    void endTransaction();

    void rollback();

    Integer getLastId();
}
