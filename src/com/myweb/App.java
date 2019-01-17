package com.myweb;

import com.myweb.views.View;

/**
 * @Description 应用基础类，初始化应用，获取应用公共对象
 * @auther Administrator
 * @create 2019-01-13 22:43
 */
public class App {
    public static Db db = null;
    public static Db getDb() {
        if (null == db) {
            return new Db();
        }
        return db;
    }
}