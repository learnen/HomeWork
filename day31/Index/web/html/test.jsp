<%--
  Created by IntelliJ IDEA.
  User: lanou
  Date: 2018/1/4
  Time: 20:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>开始</title>
</head>
<body ng-app="starter" ng-controller="startCtrl" ng-cloak>

<input id="input" type="text" ng-model="text">
<h1>{{text}}</h1>
</body>
<script src="../js/jquery-3.2.1.min.js"></script>
<script src="../js/angular.js"></script>
<script src="../js/angular-route.js"></script>
<script type="text/javascript">
    (function () {
        var app = angular.module("starter",[]);
        app.controller("startCtrl",function ($scope) {

        })
    }())
</script>
</html>
