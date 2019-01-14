package com.myweb.servlets.User;

import com.myweb.servlets.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends BaseServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setCharset(request, response);
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if(username.equals("admin") && password.equals("123456")) {
            //设置用户cookie，3天内自动登录
            response.addCookie(setCookie("username", username, 3*24*3600, "/login"));
            //设置session
            request.getSession().setAttribute("username", username);
            response.sendRedirect("/index");
        } else {
            request.setAttribute("errmsg", "用户名或密码错误！");
            request.getRequestDispatcher("/user/login.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setCharset(request, response);
        Cookie[] cks = request.getCookies();
        String username = "";
        for(Cookie c:cks) {
            if("username".equals(c.getName())) {
                username = c.getValue();
                request.getSession().setAttribute("username", username);
            }
        }
        if("".equals(username)) {
            request.getRequestDispatcher("/user/login.jsp").forward(request, response);
        } else {
            response.sendRedirect("/index");
        }
    }
}
