<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/1/8
  Time: 17:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" import="java.util.*" %>
<html>
<head>
    <title>用户登录</title>
</head>
<body>
    <form action="/login" method="post" enctype="application/x-www-form-urlencoded">
        <input type="text" name="username" value=""/>
        <input type="password" name="password" value=""/>
        <button name="submit">登录</button>
        <%String errmsg = request.getAttribute("errmsg")==null?"":(String) request.getAttribute("errmsg");%>
        <%=errmsg%>
    </form>
</body>
</html>