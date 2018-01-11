<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>订单列表</title>
    
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
	* {
		font-size: 11pt;
	}
	div {
		border: solid 2px rgb(78,78,78);
		width: 75px;
		height: 75px;
		text-align: center;
	}
	li {
		margin: 10px;
	}
</style>
  </head>
  
  <body style="background: rgb(254,238,189);">
<h1>我的订单</h1>

<table border="1" width="100%" cellspacing="0" background="black">

 

<c:forEach items="${requestScope.orders}" var="order" begin="0" end="${requestScope.orders.size()}" step="1">
	<c:if test="${order.state != 5}">
		<tr bgcolor="rgb(78,78,78)" bordercolor="rgb(78,78,78)" style="color: white;">
			<td colspan="6">
				订单：${order.oid}　成交时间：${order.ordertime}　金额：<font color="red"><b>${order.total}</b></font>　
				<c:if test="${order.state == 1}">
					<span>未付款</span>
					<%--<a href="javascript:alert('发货成功！')">发货</a>--%>
				</c:if>
				<c:if test="${order.state == 2}">
					<a href="/bookshop/adminOrder?method=changeState&state=2&oid=${order.oid}">发货</a>
				</c:if>
				<c:if test="${order.state == 3}">
					<span>等待收货</span>
				</c:if>
				<c:if test="${order.state == 4}">
					<a href="/bookshop/adminOrder?method=changeState&state=4&oid=${order.oid}">完成订单</a>
				</c:if>
			</td>
		</tr>
		<c:forEach items="${order.orderItems}" var="orderItems" begin="0" end="${order.orderItems.size()}" step="1">\
			<tr bordercolor="rgb(78,78,78)" align="center">
				<td width="15%">
					<div><img src="<c:url value='/${orderItems.book.image}'/>" height="75"/></div>
				</td>
				<td>书名：${orderItems.book.bname}</td>
				<td>单价：${orderItems.book.price}</td>
				<td>作者：${orderItems.book.author}</td>
				<td>数量：${orderItems.count}</td>
				<td>小计：${orderItems.subtotal}元</td>
			</tr>
		</c:forEach>

	</c:if>

</c:forEach>


</table>
  </body>
</html>
