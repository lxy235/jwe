package com.myweb.servlets.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Set;

@WebFilter(filterName = "MyWebFilter")
public class MyWebFilter implements Filter {

    private Set<String> prefixIignores = new HashSet<String>();

    public void destroy() {
        prefixIignores = null;
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
            if(_canIgnore((HttpServletRequest) req)) {
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
        String cp = config.getServletContext().getContextPath();
        String ignoresParam = config.getInitParameter("ignores");
        String[] ignoreArray = ignoresParam.split(",");
        for (String s : ignoreArray) {
            prefixIignores.add(cp + s);
        }
    }
    private boolean _canIgnore(HttpServletRequest request) {
        String url = request.getRequestURI();
        for (String ignore : prefixIignores) {
            if (url.startsWith(ignore)) {
                return true;
            }
        }
        return false;
    }
}
