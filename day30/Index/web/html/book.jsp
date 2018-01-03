<%--
  Created by IntelliJ IDEA.
  User: lanou
  Date: 2018/1/3
  Time: 20:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>书籍详情</title>
    <script src="../js/jquery-3.2.1.min.js" type="text/javascript"></script>
    <style type="text/css">
        span{
            /*background: #aeaeae;*/
            white-space: pre-wrap;
            /*white-space: pre-line;*/
            white-space: -o-pre-wrap;
            /*white-space: -moz-pre-wrap !important;*/
            word-wrap: break-word;
            display: inline-block;
        }
        li{
            line-height: 100%;
        }
        /*li span:nth-of-type(2){*/
            /*width: 50%;*/
        /*}*/
    </style>
</head>
<body>
   <ol>
       <li><span>id:</span></li>
       <li><span>书名:</span></li>
       <li><span>作者:</span></li>
       <li><span>价格:</span></li>
       <li><span style="display: block">封面:</span></li>
       <li><span style="display: block">简介:</span></li>
   </ol>
</body>
<script type="text/javascript">
    $.post("../book",{
        "bid":<%= request.getParameter("bid")%>
    },function (data, status) {
//        console.log(data.bname);
        var book = JSON.parse(data);
        $('ol li').eq(0).append("<span>"+book.id+"</span>");
        $('ol li').eq(1).append("<span>"+book.bname+"</span>");
        $('ol li').eq(2).append("<span>"+book.author+"</span>");
        $('ol li').eq(3).append("<span>"+book.price+"</span>");
        $('ol li').eq(4).append($('<img>').attr("src",book.cover));
        $('ol li').eq(5).append("<span >"+book.intro+"</span>");

    })
</script>
</html>
