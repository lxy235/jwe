package com.myweb.models;

import com.myweb.App;
import com.myweb.drivers.Db;

public class Model {
    protected Db db = null;
    protected String tableName = "";
    public void Model() {
        this.db = App.getDb();
    }
}
