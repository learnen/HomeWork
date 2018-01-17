<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: lanou
  Date: 2018/1/16
  Time: 19:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <style>
        ul{
            display: inline-block;
            list-style-type: none;
            color: red;
            margin: 0px;
        }
        li{
            height: auto;
        }
    </style>
</head>
<body>
<form action="/user/login" method="post">
    请输入用户名:<input type="text" name="user.username">
    <span><s:fielderror fieldName="user.username"/></span>
    <br/>
    请输入密&nbsp;码:<input type="password" name="user.password">
    <span><s:fielderror fieldName="user.password"/></span>
    <br/>
    <input type="submit" value="登录">
</form>
<a href="register.jsp">还没有注册?去注册</a>
</body>
</body>
</html>
