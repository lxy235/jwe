package com.backend.servlets.User;

import com.backend.services.UserService;
import com.backend.servlets.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;


@WebServlet(name = "LoginServlet")
public class LoginServlet extends BaseServlet {

    UserService userService = null;

    @Override
    public void init() throws ServletException {
        super.init();
        userService = new UserService();
    }

    @Override
    public void _service(HttpServletRequest req, HttpServletResponse resp) {
        if(req.getMethod().equals("POST")) {
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            HashMap<String, Object> userinfo = new HashMap<String, Object>();
            if("".equals(username) || "".equals(password)) {
                toFail("请输入用户名和密码！");
            }
            if(userService.login(username, password, userinfo)) {
                //用户对象写到session
                req.getSession().setAttribute("user", userinfo.get("userinfo"));
                try {
                    resp.sendRedirect("index");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                toFail("用户名密码错误！");
            }
        } else {
            HashMap<String, Object> viewData = new HashMap<String, Object>();
            viewData.put("title", "欢迎登录信息管理系统");
            view.render("user/login", viewData);
        }
    }
}
