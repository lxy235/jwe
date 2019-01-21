﻿<%--
Created by IntelliJ IDEA.
User: Administrator
Date: 2019/1/8
Time: 17:48
To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="utf-8" import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>${title}</title>
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
    <script language="JavaScript" src="js/jquery.js"></script>
    <script src="js/cloud.js" type="text/javascript"></script>

    <script language="javascript">
        $(function () {
            $('.loginbox').css({'position': 'absolute', 'left': ($(window).width() - 692) / 2});
            $(window).resize(function () {
                $('.loginbox').css({'position': 'absolute', 'left': ($(window).width() - 692) / 2});
            })
        });
    </script>

</head>

<body style="background-color:#df7611; background-image:url(images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">


<div id="mainBody">
    <div id="cloud1" class="cloud"></div>
    <div id="cloud2" class="cloud"></div>
</div>


<div class="logintop">
    <span>欢迎登录后台管理界面平台</span>
    <ul>
        <li><a href="#">回首页</a></li>
        <li><a href="#">帮助</a></li>
        <li><a href="#">关于</a></li>
    </ul>
</div>

<div class="loginbody">

    <span class="systemlogo"></span>

    <div class="loginbox">
        <form action="login" enctype="application/x-www-form-urlencoded" method="post">
            <ul>
                <li><input name="username" type="text" class="loginuser" placeholder="用户名"/></li>
                <li><input name="password" type="password" class="loginpwd" placeholder="密码"/></li>
                <li><input type="submit" class="loginbtn" value="登录"/>
                    <label><input name="" type="checkbox" value="" checked="checked"/>记住密码</label>
                    <label><a href="#">忘记密码？</a></label>
                </li>
            </ul>
        </form>

    </div>

</div>


<div class="loginbm">版权所有 2014 <a href="http://www.uimaker.com">uimaker.com</a> 仅供学习交流，勿用于任何商业用途</div>


</body>

</html>