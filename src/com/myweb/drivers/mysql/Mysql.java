package com.myweb.drivers.mysql;

import com.myweb.drivers.DbDriver;
import com.myweb.utils.Config;

import java.sql.*;

public class Mysql extends DbDriver {
    Config config = null;
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    public Mysql() {
        config = new Config("db.properties");
    }
    public Connection getConnection() {
        try {
            String driverclass = config.getAttribute("driverclass");
            Class.forName(driverclass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            if(null==connection) {
                String user = config.getAttribute("user");
                String passwd = config.getAttribute("passwd");
                String host = config.getAttribute("host");
                String port = config.getAttribute("port");
                String db = config.getAttribute("db");
                String dsn = "jdbc:mysql://"+host+":"+port+"/"+db+"";
                connection = DriverManager.getConnection(dsn,user, passwd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    public ResultSet fetchAll(String fields, String tableName, String where) {
        String sql = "select "+fields+" from `"+tableName+"` where "+where;
        try {
            ps = getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
}
