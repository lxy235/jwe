package com.myweb.servlets;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "IndexServlet")
public class IndexServlet extends BaseServlet {
    /**
     * 项目主页
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setCharset(request, response);
        //获取全局配置
        ServletContext sc = this.getServletContext();
        String pagesize = sc.getInitParameter("pagesize");
        dd(pagesize);
        //获取servlet自己的配置
        ServletConfig sconfig = this.getServletConfig();
        String charset = sconfig.getInitParameter("charset");
        dd(charset);
        //
        sc.getRealPath("/doc/1.txt");
        String username = (String) request.getSession().getAttribute("username");
        if(null==username) {
            response.sendRedirect("/login");
        } else {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }
}
