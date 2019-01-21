package com.myweb.utils;

import javax.servlet.http.HttpServletRequest;
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

    /**
     * 获取客户端IP
     * @param request
     * @return
     */
    public static String getClientIP(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
