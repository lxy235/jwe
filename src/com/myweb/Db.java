package com.myweb;

import com.myweb.drivers.DbDriver;
import com.myweb.utils.Config;

public class Db {
    Config config = null;

    public Db() {
        config = new Config("db.properties");
    }

    public DbDriver getDbDriver() {
        String dbDriverName = config.getAttribute("driver");
        DbDriver dbDriver = null;
        Class dbDriverClass = null;
        try {
            dbDriverClass = Class.forName("com.myweb.drivers.mysql." + dbDriverName);
            dbDriver = (DbDriver) dbDriverClass.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return dbDriver;
    }
}
