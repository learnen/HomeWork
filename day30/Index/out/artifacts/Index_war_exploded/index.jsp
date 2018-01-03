<%@ page import="com.onlywd.bean.User" %><%--
  Created by IntelliJ IDEA.
  User: lanou
  Date: 2017/12/29
  Time: 15:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>花果山福地,水帘洞冬天</title>
    <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
  </head>
  <body>



      <%
        if (session.getAttribute("user") != null){
            User user = (User)session.getAttribute("user");
            System.out.println(user);
      %>
        <a class="logout" href="html/login.html">退出</a>
      <br>
        <h1>欢迎<%= user.getUsername()%>登录主页</h1>
        <table class="book" border="1"></table>

      <%  }else{ %>
        <a href="html/login.html">不好意思,请先登录</a>
      <%
        }
      %>

  </body>
  <script type="text/javascript">

      $(".logout").click(function (e) {

         $.get("index",function () {
             
         })

      })

      if ($(".book").length) {
          $.post(
              "index", function (data, status) {
                  $("table").append('<tr>' +
                      '<td>id</td>' +
                      '<td>书名</td>' +
                      '<td>作者</td>' +
                      '<td>价格</td>' +
                      '<td>封面</td>' +
                      '</tr>')
                  var json = JSON.parse(data);
                  console.log(data);
                for (var i = 0 ; i < json.length ; i++){
                    $('table').append('<tr>' +
                        '<td>' + json[i].id + '</td>' +
                        '<td><a href=html/book.jsp?bid='+json[i].id+'>' + json[i].bname + '</td>' +
                        '<td>' + json[i].author + '</td>' +
                        '<td>' + json[i].price + '</td>' +
                        '</tr>')

                }

              }
          )
      }
  </script>
</html>
