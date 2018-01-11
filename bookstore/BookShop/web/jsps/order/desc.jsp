<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>订单详细</title>
    
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
	
	#pay {
		background: url(<c:url value='/images/all.png'/>) no-repeat;
		display: inline-block;
		
		background-position: 0 -412px;
		margin-left: 30px;
		height: 36px;
		width: 146px;
	}
	#pay:HOVER {
		background: url(<c:url value='/images/all.png'/>) no-repeat;
		display: inline-block;
		
		background-position: 0 -448px;
		margin-left: 30px;
		height: 36px;
		width: 146px;
	}
</style>
  </head>
  
  <body>
<h1>当前订单</h1>

<table border="1" width="100%" cellspacing="0" background="black">
	<c:if test="${requestScope.book != null}">
		<tr bgcolor="gray" bordercolor="gray">
			<td colspan="6">
				订单编号：${requestScope.book.oid}　成交时间：${requestScope.book.ordertime}　金额：<font color="red"><b>${requestScope.book.total}元</b></font>
			</td>
		</tr>
		<c:forEach items="${requestScope.book.orderItems}" var="orderItem" begin="0"
				   end="${requestScope.book.orderItems.size()}" step="1">
			<%--<c:forEach items= "${book}" var="data" begin="0" end="${book.size()}" step="1">--%>
				<tr bordercolor="gray" align="center">
					<td width="15%">
						<div><img src="<c:url value='/${orderItem.book.image}'/>" height="75"/></div>
					</td>
					<td>书名：${orderItem.book.bname}</td>
					<td>单价：${orderItem.book.price}</td>
					<td>作者：${orderItem.book.author}</td>
					<td>数量：${orderItem.count}</td>
					<td>小计：${orderItem.count * orderItem.book.price}</td>
				</tr>
			<%--</c:forEach>--%>

		</c:forEach>
	</c:if>


	<c:if test="${requestScope.oneOrder != null}">
		<tr bgcolor="gray" bordercolor="gray">
			<td colspan="6">
				订单编号：${requestScope.oneOrder.oid}　成交时间：${requestScope.oneOrder.ordertime}　金额：<font color="red"><b>${requestScope.oneOrder.total}元</b></font>
			</td>
		</tr>

		<c:forEach items= "${requestScope.oneOrder.orderItems}" var="data" begin="0" end="${requestScope.oneOrder.orderItems.size()}" step="1">
			<tr bordercolor="gray" align="center">
				<td width="15%">
					<div><img src="<c:url value='/${data.book.image}'/>" height="75"/></div>
				</td>
				<td>书名：${data.book.bname}</td>
				<td>单价：${data.book.price}</td>
				<td>作者：${data.book.author}</td>
				<td>数量：${data.count}</td>
				<td>小计：${data.subtotal}</td>
			</tr>
		</c:forEach>
	</c:if>





</table>
<br/>
<form method="post" action="/bookshop/order?method=pay" id="form" target="body">
	收货地址：<input type="text" name="address" size="50" value="北京市海淀区金燕龙大厦2楼216室无敌收"/><br/>
<c:if test="${requestScope.oneOrder != null}">
	<input type="hidden" name="oid" value="${requestScope.oneOrder.oid}">
</c:if>
	<c:if test="${requestScope.book != null}">
		<input type="hidden" name="oid" value="${requestScope.book.oid}">
	</c:if>

	选择银行：<br/>
	<input type="radio" name="pd_FrpId" value="ICBC-NET-B2C" checked="checked"/>工商银行
	<img src="../../../bookshop/bank_img/icbc.bmp" align="middle"/>
	<input type="radio" name="pd_FrpId" value="BOC-NET-B2C"/>中国银行
	<img src="../../../bookshop/bank_img/bc.bmp" align="middle"/><br/><br/>
	<input type="radio" name="pd_FrpId" value="ABC-NET-B2C"/>农业银行
	<img src="../../../bookshop/bank_img/abc.bmp" align="middle"/>
	<input type="radio" name="pd_FrpId" value="CCB-NET-B2C"/>建设银行
	<img src="../../../bookshop/bank_img/ccb.bmp" align="middle"/><br/><br/>
	<input type="radio" name="pd_FrpId" value="BOCO-NET-B2C"/>交通银行
	<img src="../../../bookshop/bank_img/bcc.bmp" align="middle"/><br/>
</form>
<a id="pay" href="javascript:document.getElementById('form').submit();"></a>

  </body>
</html>

