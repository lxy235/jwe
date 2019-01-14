package com.myweb.drivers;

import com.myweb.utils.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    if(null==conn) {
                        String user = config.getAttribute("user");
                        String passwd = config.getAttribute("passwd");
                        String host = config.getAttribute("host");
                        String port = config.getAttribute("port");
                        String db = config.getAttribute("db");
                        String dsn = "jdbc:mysql://"+host+":"+port+"/"+db+"";
                        Connection conn = DriverManager.getConnection(dsn,user, passwd);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        return conn;
    }
}
