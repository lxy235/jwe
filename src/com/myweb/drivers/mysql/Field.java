package com.myweb.drivers.mysql;


import java.util.ArrayList;
import com.sun.deploy.util.StringUtils;

public class Field {
    /**
     * 数据字段
     */
    protected ArrayList<String> fields = new ArrayList<String>();

    /**
     * 获取字段，逗号分隔
     * @return
     */
    public String getFields() {
        if(fields.isEmpty()) {
            return "*";
        }
        return StringUtils.join(fields, ",");
    }

    /**
     * 设置字段
     * @param fieldName
     */
    public void setField(String fieldName) {
        if(null!=fieldName) {
            fields.add("`"+fieldName+"`");
        }
    }

    /**
     * 设置字段
     * @param fieldArray
     */
    public void setField(String[] fieldArray) {
        if(null!=fieldArray) {
            for (String fieldName:fieldArray
                 ) {
                fields.add("`"+fieldName+"`");
            }
        }
    }
}
