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
    <title>注册界面</title>
</head>
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
<body>
<h1>欢迎注册</h1>
    <form action="/user/register" method="post">
        请输入用户名:<input type="text" name="user.username">
        <span><s:fielderror fieldName="user.username"/></span>
        <br/>
        请输入密&nbsp;码:<input type="password" name="user.password">
        <span><s:fielderror fieldName="user.password"/></span>
        <br/>
        请确认密&nbsp;码:<input type="password" name="confirmPassword">
        <span><s:fielderror fieldName="confirmPassword"/></span>
        <br/>
        请输入电话号码:<input type="text" name="user.phonenumber">
        <span><s:fielderror fieldName="user.phonenumber"/></span>
        <br/>
        请输入邮箱:<input type="text" name="user.email">
        <span><s:fielderror fieldName="user.email"/></span>
        <br/>
        <img src="${pageContext.request.contextPath}/user/codeImage" alt="这是一个验证码">
        <br/>
        请输入验证码:<input type="text" name="code">
        <span><s:fielderror fieldName="code"/></span>
        <br/>
        <input type="submit" value="注册">
    </form>
</body>
</html>
