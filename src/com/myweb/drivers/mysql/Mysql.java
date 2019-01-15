package com.myweb.drivers.mysql;

import com.myweb.drivers.DbDriver;
import com.myweb.utils.Config;

import java.sql.*;

public class Mysql implements DbDriver {
    Config config = null;
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public Mysql() {
        config = new Config("db.properties");
    }

    public Connection getConnection() {
        try {
            String driverclass = config.getAttribute("mysql.driverclass");
            Class.forName(driverclass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            if (null == connection) {
                String user = config.getAttribute("mysql.user");
                String passwd = config.getAttribute("mysql.passwd");
                String host = config.getAttribute("mysql.host");
                String port = config.getAttribute("mysql.port");
                String db = config.getAttribute("mysql.db");
                String dsn = "jdbc:mysql://" + host + ":" + port + "/" + db + "";
                connection = DriverManager.getConnection(dsn, user, passwd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public ResultSet fetchAll(String fields, String tableName, String where) {
        String sql = "select " + fields + " from `" + tableName + "` where " + where;
        try {
            ps = getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public ResultSet fetchOne(String fields, String tableName, String primaryId, Integer id) {
        String sql = "select " + fields + " from `" + tableName + "` where " + primaryId + " = " + id;
        try {
            ps = getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Boolean insert(String tableName, String fields, String values) {
        String sql = "insert into `" + tableName + "` (" + fields + ") values (" + values + ")";
        try {
            ps = getConnection().prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    @Override
    public Object update(String tableName, String sets, String where) {
        String sql = "update `" + tableName + "` set " + sets + " where " + where + "";
        Integer nums = 0;
        try {
            ps = getConnection().prepareStatement(sql);
            nums = ps.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return nums;
    }

    @Override
    public Object update(String tableName, String sets, String primaryId, Integer id) {
        String sql = "update `" + tableName + "` set " + sets + " where " + primaryId + " = " + id;
        Integer nums = 0;
        try {
            ps = getConnection().prepareStatement(sql);
            nums = ps.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return nums;
    }

    @Override
    public Object delete(String tableName, String where) {
        String sql = "delete from `" + tableName + "` where " + where;
        Integer nums = 0;
        try {
            ps = getConnection().prepareStatement(sql);
            nums = ps.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return nums;
    }

    @Override
    public Object delete(String tableName, String primaryId, Integer id) {
        String sql = "delete from `" + tableName + "` where " + primaryId + " = " + id;
        Integer nums = 0;
        try {
            ps = getConnection().prepareStatement(sql);
            nums = ps.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return nums;
    }

    @Override
    public Object query(String sql) {
        try {
            ps = getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Object execute(String sql) {
        Integer nums = 0;
        try {
            ps = getConnection().prepareStatement(sql);
            nums = ps.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return nums;
    }

    @Override
    public void startTransaction() {
        try {
            getConnection().setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void endTransaction() {
        try {
            getConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void rollback() {
        try {
            getConnection().rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Integer getLastId() {
        return null;
    }
}
