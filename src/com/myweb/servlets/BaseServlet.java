package com.myweb.servlets;


import com.myweb.View;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.UnsupportedEncodingException;

@WebServlet(name = "BaseServlet")
public class BaseServlet extends HttpServlet {
    /**
     * 视图对象
     */
    protected View view = null;

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) {
        view = new View(req, resp);
        _setCharset(req, resp);
        _service(req, resp);
    }

    /**
     * 业务逻辑，子类覆盖这个方法
     *
     * @param req
     * @param resp
     */
    public void _service(HttpServletRequest req, HttpServletResponse resp) {
    }

    /**
     * 设置请求和响应编码
     *
     * @param request
     * @param response
     */
    private void _setCharset(HttpServletRequest request, HttpServletResponse response) {
        //设置请求编码格式
        try {
            request.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //设置相应编码格式
        response.setContentType("text/html;charset=utf-8");
    }

    /**
     * 快捷输出
     *
     * @param value
     */
    protected void dd(Object value) {
        System.out.println(value);
    }
}
