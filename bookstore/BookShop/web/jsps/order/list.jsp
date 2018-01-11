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
		border: solid 2px gray;
		width: 75px;
		height: 75px;
		text-align: center;
	}
	li {
		margin: 10px;
	}
	
	#buy {
		background: url(<c:url value='/images/all.png'/>) no-repeat;
		display: inline-block;
		
		background-position: 0 -902px;
		margin-left: 30px;
		height: 36px;
		width: 146px;
	}
	#buy:HOVER {
		background: url(<c:url value='/images/all.png'/>) no-repeat;
		display: inline-block;
		
		background-position: 0 -938px;
		margin-left: 30px;
		height: 36px;
		width: 146px;
	}
</style>
  </head>
  
  <body>
<h1>我的订单</h1>

<table border="1" width="100%" cellspacing="0" background="black">


<c:forEach items="${requestScope.order}" var="item" begin="0" end="${requestScope.order.size()}" step="1">
	<c:if test="${item.state != 4 and item.state != 5 }">
		<tr bgcolor="gray" bordercolor="gray">
			<td colspan="6">
				订单编号：${item.oid}　成交时间：${item.ordertime}　金额：<font color="red"><b>${item.total}</b></font>　

				<c:if test="${item.state == 1}">
				<a href="<c:url value='/order?method=load&oid=${item.oid}'/>">付款</a>
				</c:if>
				<c:if test="${item.state == 2}">
					等待发货
				</c:if>
				<c:if test="${item.state == 3}">
					<a href="<c:url value='/order?method=confirm&oid=${item.oid}'/>">确认收货</a>
				</c:if>
			</td>
		</tr>
		<c:forEach items="${item.orderItems}" var="data" begin="0" end="${item.orderItems.size()}" step="1">
			<tr bordercolor="gray" align="center">
				<td width="15%">
					<div><img src="<c:url value='/${data.book.image}'/>" height="75"/></div>
				</td>
				<td>书名：${data.book.bname}</td>
				<td>单价：${data.book.price}元</td>
				<td>作者：${data.book.author}</td>
				<td>数量：${data.count}</td>
				<td>小计：${data.subtotal}元</td>
			</tr>
		</c:forEach>
	</c:if>
</c:forEach>

</table>
  </body>
</html>
