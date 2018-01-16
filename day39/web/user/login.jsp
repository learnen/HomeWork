<%--
  Created by IntelliJ IDEA.
  User: lanou
  Date: 2018/1/15
  Time: 20:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录界面</title>
</head>
<body>
<h1 style="color: red">${requestScope.msg}</h1>
    <form action="/user/login" method="post">
        请输入用户名:<input type="text" name="username">
        <br/>
        请输入密&nbsp;码:<input type="password" name="password">
        <br/>
        <input type="submit" value="登录">
    </form>
    <a href="register.jsp">还没有注册?去注册</a>
</body>
</html>
