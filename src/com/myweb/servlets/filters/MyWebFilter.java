package com.myweb.servlets.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@WebFilter(filterName = "MyWebFilter")
public class MyWebFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //设置请求和响应字符编码
        try {
            req.setCharacterEncoding("utf-8");
            resp.setContentType("text/html;charset=utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //判断用户登录
        HttpSession session = ((HttpServletRequest) req).getSession();
        if(null==session.getAttribute("username")) {
            if(((HttpServletRequest) req).getRequestURI().contains("login")) {
                //放行
                chain.doFilter(req, resp);
            } else {
                ((HttpServletResponse) resp).sendRedirect("/login");
            }
        } else {
            //放行
            chain.doFilter(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
