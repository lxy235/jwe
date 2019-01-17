package com.myweb.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    private Properties pro = null;
    private InputStream file = null;

    public Config(String filename) {
        pro = new Properties();
        try {
            file = Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties");
            try {
                pro.load(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getAttribute(String key) {
        return pro.getProperty(key);
    }
}
