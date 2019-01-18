package com.myweb.utils;

import java.util.HashMap;

public class Util {
    /**
     * 成功返回code
     */
    private static int SUCCESS = 0;
    /**
     * 失败返回code
     */
    private static int ERROR = 3000;

    /**
     * 成功，返回json
     * @param msg
     * @param data
     * @return
     */
    public static String toSuccess(String msg, HashMap<String, Object> data) {
        HashMap<String, Object> jsondata = new HashMap<String, Object>();
        jsondata.put("result", SUCCESS);
        jsondata.put("msg", msg);
        jsondata.put("data", data);
        return Json.encode(jsondata);
    }

    /**
     * 失败，返回json
     * @param msg
     * @param data
     * @return
     */
    public static String toFail(String msg, HashMap<String, Object> data) {
        HashMap<String, Object> jsondata = new HashMap<String, Object>();
        jsondata.put("result", ERROR);
        jsondata.put("msg", msg);
        jsondata.put("data", data);
        return Json.encode(jsondata);
    }
}
