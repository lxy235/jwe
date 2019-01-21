package com.frame.drivers.mysql;

import com.frame.drivers.DbDriver;
import com.frame.utils.Config;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

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
            try {
                ps = connection.prepareStatement("SET NAMES "+config.getAttribute("mysql.charset"));
                rs = ps.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public ArrayList<HashMap<String, Object>> fetchAll(String fields, String tableName, String where) {
        if(null==fields) {
            fields = "*";
        }
        String sql = "select " + fields + " from `" + tableName + "` where " + where;
        ArrayList<HashMap<String, Object>> rows = new ArrayList<HashMap<String, Object>>();
        try {
            ps = getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            ArrayList<String> columnNames = _getColumnNames(rs);
            while (rs.next()) {
                HashMap<String, Object> row = new HashMap<String, Object>();
                for (String columnName : columnNames
                ) {
                    row.put(columnName, rs.getObject(columnName));
                }
                rows.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows;
    }

    @Override
    public ArrayList<HashMap<String, Object>> fetchAll(String fields, String tableName, Statement statement) {
        if(null==fields) {
            fields = "*";
        }
        String where = statement.getWhere();
        String sql = "select " + fields + " from `" + tableName + "` where " + where;
        ArrayList<HashMap<String, Object>> rows = new ArrayList<HashMap<String, Object>>();
        try {
            ps = getConnection().prepareStatement(sql);
            HashMap<Integer, Object> params = statement.getParams();
            for(Integer index:params.keySet())
            {
                ps.setObject(index, params.get(index));
            }
            rs = ps.executeQuery();
            ArrayList<String> columnNames = _getColumnNames(rs);
            while (rs.next()) {
                HashMap<String, Object> row = new HashMap<String, Object>();
                for (String columnName : columnNames
                ) {
                    row.put(columnName, rs.getObject(columnName));
                }
                rows.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows;
    }

    @Override
    public ArrayList<HashMap<String, Object>> fetchAll(Field field, String tableName, Statement statement) {
        String fields = field.getFields();
        String where = statement.getWhere();
        String sql = "select " + fields + " from `" + tableName + "` where " + where;
        ArrayList<HashMap<String, Object>> rows = new ArrayList<HashMap<String, Object>>();
        try {
            ps = getConnection().prepareStatement(sql);
            HashMap<Integer, Object> params = statement.getParams();
            for(Integer index:params.keySet())
            {
                ps.setObject(index, params.get(index));
            }
            rs = ps.executeQuery();
            ArrayList<String> columnNames = _getColumnNames(rs);
            while (rs.next()) {
                HashMap<String, Object> row = new HashMap<String, Object>();
                for (String columnName : columnNames
                ) {
                    row.put(columnName, rs.getObject(columnName));
                }
                rows.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows;
    }

    @Override
    public HashMap<String, Object> fetchOne(String fields, String tableName, String primaryId, Integer id) {
        if(null==fields) {
            fields = "*";
        }
        String sql = "select " + fields + " from `" + tableName + "` where " + primaryId + " = " + id;
        HashMap<String, Object> info = null;
        try {
            ps = getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            info = _getOneData(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return info;
    }

    @Override
    public HashMap<String, Object> fetchOne(String fields, String tableName, Statement statement) {
        if(null==fields) {
            fields = "*";
        }
        String where = statement.getWhere();
        String sql = "select " + fields + " from `" + tableName + "` where " + where;
        HashMap<String, Object> info = null;
        try {
            ps = getConnection().prepareStatement(sql);
            HashMap<Integer, Object> params = statement.getParams();
            for(Integer index:params.keySet())
            {
                ps.setObject(index, params.get(index));
            }
            rs = ps.executeQuery();
            info = _getOneData(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return info;
    }

    @Override
    public HashMap<String, Object> fetchOne(Field field, String tableName, Statement statement) {
        String fields = field.getFields();
        String where = statement.getWhere();
        String sql = "select " + fields + " from `" + tableName + "` where " + where;
        HashMap<String, Object> info = null;
        try {
            ps = getConnection().prepareStatement(sql);
            HashMap<Integer, Object> params = statement.getParams();
            for(Integer index:params.keySet())
            {
                ps.setObject(index, params.get(index));
            }
            rs = ps.executeQuery();
            info = _getOneData(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return info;
    }

    @Override
    public HashMap<String, Object> fetchOne(String fields, String tableName, String where) {
        if(null==fields) {
            fields = "*";
        }
        String sql = "select " + fields + " from `" + tableName + "` where " + where;
        HashMap<String, Object> info = null;
        try {
            ps = getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            info = _getOneData(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return info;
    }

    @Override
    public Integer insert(String tableName, RowSet rowset) {
        Integer nums = 0;
        String fieldstr = rowset.getFields();
        String valuestr = rowset.getValues();
        String sql = "insert into `" + tableName + "` (" + fieldstr + ") values (" + valuestr + ")";
        try {
            ps = getConnection().prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                nums = rs.getInt(1);
            }
        } catch (SQLException e) {
            return 0;
        }
        return nums;
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
    public Object update(String tableName, RowSet rowset, Statement statement){
        String where = statement.getWhere();
        String sets = rowset.getSets();
        String sql = "update `" + tableName + "` set " + sets + " where " + where + "";
        Integer nums = 0;
        try {
            ps = getConnection().prepareStatement(sql);
            HashMap<Integer, Object> params = statement.getParams();
            for(Integer index:params.keySet())
            {
                ps.setObject(index, params.get(index));
            }
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
    public Object delete(String tableName, Statement statement){
        String where = statement.getWhere();
        String sql = "delete from `" + tableName + "` where " + where;
        Integer nums = 0;
        try {
            ps = getConnection().prepareStatement(sql);
            HashMap<Integer, Object> params = statement.getParams();
            for(Integer index:params.keySet())
            {
                ps.setObject(index, params.get(index));
            }
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
    public void beginTransaction() {
        try {
            getConnection().setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void commit() {
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

    /**
     * 获取数据列
     *
     * @param rs
     * @return
     */
    private ArrayList<String> _getColumnNames(ResultSet rs) {
        Integer columnCount = null;
        ArrayList<String> columnNames = new ArrayList<String>();
        try {
            columnCount = rs.getMetaData().getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                columnNames.add(rs.getMetaData().getColumnName(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columnNames;
    }

    /**
     * 获取单行数据
     *
     * @param rs
     * @return
     */
    private HashMap<String, Object> _getOneData(ResultSet rs) {
        HashMap<String, Object> info = new HashMap<String, Object>();
        try {
            ArrayList<String> columnNames = _getColumnNames(rs);
            while (rs.next()) {
                for (String columnName : columnNames
                ) {
                    info.put(columnName, rs.getObject(columnName));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return info;
    }

    @Override
    public Field getFiled() {
        return new Field();
    }

    @Override
    public RowSet getRowSet() {
        return new RowSet();
    }

    @Override
    public Statement getStatement() {
        return new Statement();
    }
}
