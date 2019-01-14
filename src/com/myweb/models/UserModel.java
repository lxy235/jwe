package com.myweb.models;

public class UserModel extends Model {
    /**
     * 数据字段验证规则
     */
    public String[] rules = null;
    /**
     * 过滤器对象
     */
    public Object filters = null;
    /**
     * 数据表名称
     */
    public String tableName = "users";
}
