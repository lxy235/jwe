package com.frame.views;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class View {
    protected static String viewRootPath = "";
    protected static String templateFileExtension = ".jsp";
    protected HttpServletRequest req = null;
    protected HttpServletResponse resq = null;
    public HashMap<String, Object> viewData = new HashMap<String, Object>();

    public static String getViewRootPath() {
        return viewRootPath;
    }

    public static void setViewRootPath(String viewRootPath) {
        View.viewRootPath = viewRootPath;
    }

    public View(HttpServletRequest request, HttpServletResponse response) {
        req = request;
        resq = response;
        String appName = req.getServletContext().getInitParameter("appName");
        setViewRootPath("/"+appName+"/views/");
    }

    public void assign(String name, Object value) {
        if(null!=name && null!=value) {
            viewData.put(name, value);
        }
    }
    public void render(String template, HashMap<String, Object> viewData) {
        try {
            if(!viewData.isEmpty()) {
                for(String name:viewData.keySet()) {
                    req.setAttribute(name, viewData.get(name));
                }
            }
            template = viewRootPath+template+templateFileExtension;
            req.getRequestDispatcher(template).forward(req, resq);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void render(String template) {
        try {
            if(!viewData.isEmpty()) {
                for(String name:viewData.keySet()) {
                    req.setAttribute(name, viewData.get(name));
                }
            }
            template = viewRootPath+template+templateFileExtension;
            req.getRequestDispatcher(template).forward(req, resq);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String getViewPath() {
        return viewRootPath;
    }
}
