package com.myweb.drivers.mysql;


import java.util.HashMap;

public class Statement {
    /**
     * 查询条件
     */
    protected String where = "";
    /**
     * 查询参数
     */
    protected HashMap<Integer, Object> params = new HashMap<Integer, Object>();

    /**
     * 获取查询条件
     *
     * @return
     */
    public String getWhere() {
        return where;
    }

    /**
     * 设置查询条件
     *
     * @param where
     */
    public void setWhere(String where) {
        this.where = where;
    }

    /**
     * 获取参数
     *
     * @return
     */
    public HashMap<Integer, Object> getParams() {
        return params;
    }

    /**
     * 设置参数
     *
     * @param index
     * @param value
     */
    public void setParam(Integer index, Object value) {
        if (null != index && null != value) {
            params.put(index, value);
        }
    }
}
