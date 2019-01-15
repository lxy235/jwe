package com.myweb.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private String path = "";
    private Properties pro = null;
    private FileInputStream file = null;

    public Config(String filename) {
        path = System.getProperty("user.dir") + "/conf/";
        pro = new Properties();
        try {
            filename = path + filename;
            file = new FileInputStream(filename);
            try {
                pro.load(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getAttribute(String key) {
        return pro.getProperty(key);
    }
}
