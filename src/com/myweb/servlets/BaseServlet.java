package com.myweb.servlets;

import com.myweb.App;
import com.myweb.View;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.UnsupportedEncodingException;

@WebServlet(name = "BaseServlet")
public class BaseServlet extends HttpServlet {
    public View view = null;

    @Override
    public void init() throws ServletException {
        super.init();
        this.view = App.getView();
    }

    public Cookie setCookie(String key, String val, int age, String path) {
        Cookie cookieData = new Cookie(key, val);
        if(0 != age) {
            cookieData.setMaxAge(age);
        }
        if(null != path) {
            cookieData.setPath(path);
        }
        return cookieData;
    }
    public void setCharset(HttpServletRequest request, HttpServletResponse response) {
        //设置请求编码格式
        try {
            request.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //设置相应编码格式
        response.setContentType("text/html;charset=utf-8");
    }
    public void dd(Object value) {
        System.out.println(value);
    }
}
