package com.backend.models;

public class UserModel extends Model {
    /**
     * 数据表名称
     */
    protected String tableName = "t_user";
    /**
     * 数据表主键
     */
    protected String primaryId = "id";

    public UserModel() {
        setTableName(tableName);
        setPrimaryId(primaryId);
    }
}
