<%--
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
  <h1>花果山福地,水帘洞洞天</h1>
  <h1>大圣此欲何去?  踏南门,碎凌霄</h1>
  <h1>若一去不回?</h1>
  <h1>便一去不回</h1>
    <table border="1"></table>
  </body>
  <script type="text/javascript">
      $.post(
          "./index",function (data, status) {
              $("table").append('<tr>' +
                  '<td>姓名</td>' +
                  '<td>密码</td>' +
                  '<td>昵称</td>' +
                  '</tr>')
              var json = JSON.parse(data);
              for (var i = 0 ; i < json.length ; i++){
                  $('table').append('<tr>' +
                      '<td>'+json[i].username+'</td>' +
                      '<td>'+json[i].password+'</td>' +
                      '<td>'+json[i].nickname+'</td>' +
                      '</tr>')
              }
          }
      )
  </script>
</html>
