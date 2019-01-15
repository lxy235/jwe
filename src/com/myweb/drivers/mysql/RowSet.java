package com.myweb.drivers.mysql;

import com.sun.deploy.util.StringUtils;

import java.util.ArrayList;

public class RowSet {
    /**
     * 字段列表
     */
    protected ArrayList<String> fields = new ArrayList<String>();
    /**
     * 参数列表
     */
    protected ArrayList<Object> values = new ArrayList<Object>();

    /**
     * 添加字符
     *
     * @param fieldName
     * @param fieldValue
     */
    public void addString(String fieldName, String fieldValue) {
        if(null!=fieldName && null!=fieldValue) {
            fields.add('`' + fieldName + '`');
            values.add('"' + fieldValue + '"');
        }
    }

    /**
     * 添加数值
     *
     * @param fieldName
     * @param fieldValue
     */
    public void addNumber(String fieldName, Number fieldValue) {
        if(null!=fieldName && null!=fieldValue) {
            fields.add('`' + fieldName + '`');
            values.add(String.valueOf(fieldValue));
        }
    }

    /**
     * 获取字段，逗号分隔
     *
     * @return
     */
    public String getFields() {
        return StringUtils.join(fields, ",");
    }

    /**
     * 获取参数，逗号分隔
     *
     * @return
     */
    public String getValues() {
        return StringUtils.join(values, ",");
    }
}
