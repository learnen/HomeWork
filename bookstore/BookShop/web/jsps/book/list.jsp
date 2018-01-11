<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>图书列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">

      <link rel="stylesheet" href="../../bookshop/js/bootstrap.min.css">
      <script type="text/javascript" src="../../bookshop/js/jquery.min.js"></script>
      <script type="text/javascript" src="../../bookshop/js/bootstrap.min.js"></script>


<style type="text/css">
	body {
		font-size: 10pt;
	}
	.icon {
		margin:10px;
		border: solid 2px gray;
		width: 160px;
		height: 180px;
		text-align: center;
		float: left;
	}
</style>
  </head>
  
  <body>

<c:forEach items="${requestScope.AllBook}" var="item" begin="0" end="${requestScope.AllBook.size()}" step="1" varStatus="itemVs">
    <c:if test="${itemVs.index >= (param.page - 1) * 4 && itemVs.index < param.page * 4}">
        <div class="icon">
            <a href="<c:url value='/book?method=load&bid=${item.bid}'/>"><img src="<c:url value="${item.image}"/>" border="0"/></a>
            <br/>
            <a href="<c:url value='/book?method=load&bid=${item.bid}'/>">${item.bname}</a>
        </div>
   </c:if>
</c:forEach>

<c:forEach items="${requestScope.category}" var="item" begin="0" end="${requestScope.category.size()}" step="1" varStatus="itemVs">
    <c:if test="${itemVs.index >= (param.page - 1) * 4 && itemVs.index < param.page * 4}">
        <div class="icon">
            <a href="<c:url value='/book?method=load&bid=${item.bid}'/>"><img src="<c:url value="${item.image}"/>" border="0"/></a>
            <br/>
            <a href="<c:url value='/book?method=load&bid=${item.bid}'/>">${item.bname}</a>
        </div>
    </c:if>
</c:forEach>


<c:set var="page" value="${param.page}" scope="request"/>
<c:set var="begin" value="${param.page -1}" scope="request"/>
<c:set var="end" value="${param.page + 1}" scope="request"/>

<c:forEach begin="${begin}" end="0">

        <c:set var="end" value="${end+1}" scope="request"/>

    <c:set var="begin" value="${begin+1}" scope="request"/>
</c:forEach>

<c:if test="${requestScope.AllBook != null}">
    <c:forEach begin="${requestScope.AllBook.size() div 4  + 2 }" end="${end}">
        <c:set var="end" value="${end-1}" scope="request"/>
        <c:if test="${begin > 1}">
            <c:set var="begin" value="${begin-1}" scope="request"/>
        </c:if>
    </c:forEach>
</c:if>

<c:if test="${requestScope.category != null}">
    <c:forEach begin="${requestScope.category.size() div 4  + 1.8 }" end="${end}">
        <c:set var="end" value="${end-1}" scope="request"/>
        <c:if test="${begin > 1}">
            <c:set var="begin" value="${begin-1}" scope="request"/>
        </c:if>
    </c:forEach>
</c:if>

<nav class="container">
    <ul class="pagination" style="display: block;float: left;width: 100%">
        <c:if test="${requestScope.AllBook != null}">
            <c:if test="${param.page != 1}">
                <li>
                    <a href="/bookshop/book?method=findAll&page=${param.page-1}" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
            </c:if>
            <c:forEach var="allPage" items="${requestScope.AllBook}" begin="${begin}" end="${end}" step="1" varStatus="allPageVs">
                <c:if test="${param.page == allPageVs.index}">
                    <li class="active"><a class="active" href="/bookshop/book?method=findAll&page=${allPageVs.index}">${allPageVs.index}</a></li>
                </c:if>

                <c:if test ="${param.page != allPageVs.index }">
                    <li><a href="/bookshop/book?method=findAll&page=${allPageVs.index}">${allPageVs.index}</a></li>
                </c:if>

            </c:forEach>
            <c:if test="${param.page <=  requestScope.AllBook.size() div 4 }">
                <li>
                    <a href="/bookshop/book?method=findAll&page=${param.page + 1 }" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </c:if>
        </c:if>

        <c:if test="${requestScope.category != null}">
            <c:if test="${param.page != 1}">
                <li>
                    <a href="/bookshop/book?method=findByCategory&cid=${param.cid}&page=${param.page-1}" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
            </c:if>
            <c:forEach var="allPage" items="${requestScope.category}" begin="${begin}" end="${end}" step="1" varStatus="allPageVs">
                <c:if test="${param.page == allPageVs.index}">
                    <li class="active"><a class="active" href="/bookshop/book?method=findByCategory&cid=${param.cid}&page=${allPageVs.index}">${allPageVs.index}</a></li>
                </c:if>

                <c:if test ="${param.page != allPageVs.index }">
                    <li><a href="/bookshop/book?method=findByCategory&cid=${param.cid}&page=${allPageVs.index}">${allPageVs.index}</a></li>
                </c:if>
            </c:forEach>
            <c:if test="${param.page <  requestScope.category.size() div 4 }">
                <li>
                    <a href="/bookshop/book?method=findByCategory&cid=${param.cid}&page=${param.page + 1 }" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </c:if>
        </c:if>

    </ul>
</nav>


  </body>
 
</html>

