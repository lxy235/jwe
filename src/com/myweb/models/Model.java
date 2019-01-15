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

//    /**
//     * 删除操作
//     *
//     * @param where 查询条件
//     * @return 成功返回删除记录数，失败返回false
//     */
//    public Object Delete(String where) {
//        return dbDriver.Delete(tableName, where);
//    }
//
//    /**
//     * 删除操作
//     *
//     * @param id 主键id
//     * @return 成功返回删除记录数，失败返回false
//     */
//    public Object Delete(int id) {
//        return dbDriver.Delete(tableName, primaryId, id);
//    }
//
//    /**
//     * 插入数据
//     *
//     * @param values
//     * @return 成功true，失败false
//     */
//    public boolean insert(HashMap<String, Object> values) {
//        return dbDriver.Insert(tableName, values);
//    }
//
//    /**
//     * 更新数据
//     *
//     * @param id 主键id
//     * @param sets 更新字段
//     * @return 成功返回更新记录数，失败返回false
//     */
//    public Object update(int id, HashMap<String, Object> sets) {
//        return dbDriver.Update(tableName, primaryId, id, sets);
//    }
//
//    /**
//     * 更新数据
//     *
//     * @param where 更新条件
//     * @param sets 更新字段
//     * @return 成功返回更新记录数，失败返回false
//     */
//    public Object update(String where, HashMap<String, Object> sets) {
//        return dbDriver.Update(tableName, where, sets);
//    }
}
