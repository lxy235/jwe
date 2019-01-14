package com.myweb.drivers;

import com.myweb.drivers.mysql.Mysql;
import com.myweb.utils.Config;
import java.sql.Connection;

public class Db {
    Config config = null;
    Connection conn = null;
    public Db() {
        config = new Config("db.properties");
    }
    public Connection getConnection() {
        String driver = config.getAttribute("driver");
        switch (driver) {
            case "mysql":
                Mysql mysql = new Mysql();
                conn = mysql.getConnection();
        }
        return conn;
    }
}
