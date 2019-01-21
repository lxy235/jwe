package com.frame.utils;

public class Cookie {
    public javax.servlet.http.Cookie setCookie(String key, String val, int age, String path) {
        javax.servlet.http.Cookie cookieData = new javax.servlet.http.Cookie(key, val);
        if (0 != age) {
            cookieData.setMaxAge(age);
        }
        if (null != path) {
            cookieData.setPath(path);
        }
        return cookieData;
    }
}
