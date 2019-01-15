package com.myweb.models;

import com.myweb.App;
import com.myweb.Db;
import com.myweb.drivers.DbDriver;

import java.sql.Connection;
import java.sql.ResultSet;

public class Model {
    /**
     * 数据字段验证规则
     */
    protected String[] rules = null;
    /**
     * 过滤器对象
     */
    protected Object filters = null;
    /**
     * 数据表名称
     */
    protected String tableName = "";
    /**
     * 数据表主键
     */
    protected String primaryId = "id";
    /**
     * 数据库对象
     */
    protected Db db = null;
    /**
     * 数据库连接对象
     */
    protected Connection connection = null;
    /**
     * 数据库驱动类
     */
    protected DbDriver dbDriver = null;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getPrimaryId() {
        return primaryId;
    }

    public void setPrimaryId(String primaryId) {
        this.primaryId = primaryId;
    }

    /**
     * 初始化
     */
    public Model() {
        db = App.getDb();
        dbDriver = db.getDbDriver();
    }

    /**
     * 获取指定条件的数据
     *
     * @param fields
     * @param where
     * @return
     */
    public ResultSet fetchAll(String fields, String where) {
        return dbDriver.fetchAll(fields, getTableName(), where);
    }

    /**
     * 获取单条记录
     * @param fields
     * @param id
     * @return
     */
    public ResultSet fetchOne(String fields, int id) {
        return dbDriver.fetchOne(fields,getTableName(),getPrimaryId(),id);
    }

    /**
     * 插入数据
     * @param fields
     * @param values
     * @return
     */
    public boolean insert(String fields, String values) {
        return dbDriver.insert(getTableName(),fields,values);
    }

    /**
     * 删除操作
     *
     * @param where 查询条件
     * @return 成功返回删除记录数，失败返回false
     */
    public Object delete(String where) {
        return dbDriver.delete(getTableName(),where);
    }

    /**
     * 删除操作
     *
     * @param id 主键id
     * @return 成功返回删除记录数，失败返回false
     */
    public Object delete(int id) {
        return dbDriver.delete(getTableName(),getPrimaryId(),id);
    }

    /**
     * 更新数据
     * @param sets
     * @param where
     * @return
     */
    public Object update(String sets, String where) {
        return dbDriver.update(getTableName(),sets,where);
    }

    /**
     * 根据ID更新数据
     * @param sets
     * @param id
     * @return
     */
    public Object update(String sets, Integer id) {
        return dbDriver.update(getTableName(), sets, getPrimaryId(), id);
    }
}
