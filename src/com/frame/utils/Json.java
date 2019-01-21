package com.frame.utils;

import com.google.gson.Gson;

public class Json {
    /**
     * json对象
     */
    protected static Gson gson = null;

    static {
        gson = new Gson();
    }

    /**
     * json解码to对象
     * @param jsonstr
     * @return
     */
    public static Object decode(String jsonstr) {
        return gson.fromJson(jsonstr, Object.class);
    }

    /**
     * json编码to字符串
     * @param jsonObject
     * @return
     */
    public static String encode(Object jsonObject) {
        return gson.toJson(jsonObject);
    }
}
