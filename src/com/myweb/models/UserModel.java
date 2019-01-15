package com.myweb.models;

public class UserModel extends Model {
    /**
     * 数据表名称
     */
    protected String tableName = "user";
    /**
     * 数据表主键
     */
    protected String primaryId = "ID";

    public UserModel() {
        setTableName(tableName);
        setPrimaryId(primaryId);
    }
}
