package com.frame.drivers.mysql;

import com.sun.deploy.util.StringUtils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

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
     * 参数列表
     */
    protected HashMap<String, Object> sets = new HashMap<String, Object>();

    /**
     * 添加字符
     *
     * @param fieldName
     * @param fieldValue
     */
    public void addString(String fieldName, String fieldValue) {
        if (null != fieldName && null != fieldValue) {
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
        if (null != fieldName && null != fieldValue) {
            fields.add('`' + fieldName + '`');
            values.add(String.valueOf(fieldValue));
        }
    }

    /**
     * 添加日期
     *
     * @param fieldName
     * @param fieldValue
     */
    public void addDate(String fieldName, Date fieldValue) {
        if (null != fieldName) {
            fields.add('`' + fieldName + '`');
            if (null == fieldValue) {
                fieldValue = new Date(System.currentTimeMillis());
            }
            values.add('"'+fieldValue.toString()+'"');
        }
    }

    /**
     * 添加日期
     *
     * @param fieldName
     */
    public void addDate(String fieldName) {
        if (null != fieldName) {
            fields.add('`' + fieldName + '`');
            Date fieldValue = new Date(System.currentTimeMillis());
            values.add('"'+fieldValue.toString()+'"');
        }
    }

    /**
     * 添加时间戳
     *
     * @param fieldName
     * @param fieldValue
     */
    public void addTimestamp(String fieldName, Timestamp fieldValue) {
        if (null != fieldName) {
            if (null == fieldValue) {
                fieldValue = new Timestamp(System.currentTimeMillis());
            }
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            fields.add('`' + fieldName + '`');
            values.add('"'+df.format(fieldValue.getTime())+'"');
        }
    }

    /**
     * 添加时间戳
     *
     * @param fieldName
     */
    public void addTimestamp(String fieldName) {
        if (null != fieldName) {
            fields.add('`' + fieldName + '`');
            Timestamp fieldValue = new Timestamp(System.currentTimeMillis());
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            values.add('"'+df.format(fieldValue.getTime())+'"');
        }
    }

    /**
     * 添加大文本
     *
     * @param fieldName
     * @param fieldValue
     */
    protected void addText(String fieldName, String fieldValue) {
        if (null != fieldName && null != fieldValue) {
            fields.add('`' + fieldName + '`');
            BufferedReader fieldClob = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(fieldValue.getBytes())));
            values.add(fieldClob);
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

    /**
     * 要update的字段以及字段值，逗号拼接起来返回
     *
     * @return
     */
    public String getSets() {
        StringBuilder str = new StringBuilder();
        String fieldValStr = "";
        if (null != sets) {
            for (String key : sets.keySet()) {
                str.append("`" + key + "`='" + sets.get(key) + "',");
            }
            fieldValStr = str.substring(0, str.length() - 1);
        }
        return fieldValStr;
    }

    /**
     * 给map集合赋值
     *
     * @return
     */
    public void addSet(String name, Object value) {
        if (null != name && null != value) {
            sets.put(name, value);
        }
    }
}
