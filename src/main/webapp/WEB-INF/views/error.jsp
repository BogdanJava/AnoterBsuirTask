<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="${contextPath}/resources/style/font-awesome.min.css">
    <link rel="stylesheet" href="${contextPath}/resources/style/bootstrap.min.css">
    <link rel="stylesheet" href="${contextPath}/resources/style/common.css">
    <link rel="shortcut icon" href="${contextPath}/resources/img/favicon-linux.ico"/>

    <title>Error</title>
</head>
<body>

<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>

        <a class="navbar-brand" href="${contextPath}/">Poultry farm</a>

        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="${contextPath}/welcome">Welcome</a></li>
                <li><a href="${contextPath}/add">Add</a></li>
                <li><a href="${contextPath}/show">Show fowls</a> </li>
            </ul>
        </div>
    </div>
</div>

<p>
    oops. some error: ${error}
</p>

<div id="footer">
    <div class="container">
        <div class="row centered">
            <a href="#"><i class="fa fa-twitter"></i></a>
            <a href="#"><i class="fa fa-facebook"></i></a>
            <a href="#"><i class="fa fa-twitch"></i></a>
        </div>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/script/bootstrap.min.js"></script>
</body>
</html>
