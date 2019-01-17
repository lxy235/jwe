package com.myweb.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@WebServlet(name = "IndexServlet")
public class IndexServlet extends BaseServlet {
    @Override
    public void _service(HttpServletRequest req, HttpServletResponse resp) {
        //视图展示1
        view.assign("title", "Hello");
        view.render("user/login");

        //视图展示2
        HashMap<String, Object> viewData = new HashMap<String, Object>();
        viewData.put("title", "Hello");
        view.render("user/login", viewData);
    }
}
