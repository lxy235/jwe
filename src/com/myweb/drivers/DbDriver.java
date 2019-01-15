package com.myweb.drivers;

import java.sql.ResultSet;

public interface DbDriver {
    public ResultSet fetchAll(String fields, String tableName, String where);
}
