<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'bookdesc.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
	body {
		font-size: 10pt;
		background: rgb(254,238,189);
	}
	div {
		margin:20px;
		border: solid 2px gray;
		width: 150px;
		height: 150px;
		text-align: center;
	}
	li {
		margin: 10px;
	}
</style>
  </head>
  
  <body>
  <div>
    <img src="<c:url value='/${requestScope.book.image}'/>" border="0"/>
  </div>
  <form style="margin:20px;" id="form" action="/bookshop/adminBook" method="post">
  	图书名称：<input type="text" name="bname" value="${requestScope.book.bname}"/><br/>
  	图书单价：<input type="text" name="price" value="${requestScope.book.price}"/><br/>
  	图书作者：<input type="text" name="author" value="${requestScope.book.author}"/><br/>
  	图书分类：<select style="width: 150px; height: 20px;" name="cid">
		  <c:forEach items="${requestScope.categories}" var="category" begin="0" end="${requestScope.categories.size()}" step="1">
			  <c:if test="${category.cid == requestScope.book.cid}">
				  <option value="${category.cid}" selected="selected">${category.cname}</option>
			  </c:if>
			  <c:if test="${category.cid != requestScope.book.cid}">
				  <option value="${category.cid}">${category.cname}</option>
			  </c:if>

		  </c:forEach>
    	</select><br/>
	  <input type="hidden" name="image" value="${requestScope.book.image}">
	  <input type="hidden" name="bid" value="${requestScope.book.bid}">
	  <input type="submit" name="method" value="del" onclick="return confirm('是否真要删除该图书？');"/>

	  <input type="submit" name="method" value="mod"/>
  </form>
  </body>
</html>
