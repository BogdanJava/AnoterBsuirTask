<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="${contextPath}/resources/style/font-awesome.min.css">
    <link rel="stylesheet" href="${contextPath}/resources/style/bootstrap.min.css">
    <link rel="stylesheet" href="${contextPath}/resources/style/common.css">
    <link rel="shortcut icon" href="${contextPath}/resources/img/favicon-linux.ico">

    <title>Add</title>
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

<div id="add">
    <div class="container">
        <div class="row centered">
            <h1>Add new fowl</h1>
            <form:form method="post" action="${contextPath}/add" modelAttribute="fowl">
                <h2>Main info</h2>
                <div class="form-group">
                    <form:label for="fowlName" path="name">Name</form:label>
                    <form:input pattern="[a-zA-Zа-яА-Я]*" path="name" id="fowlName" cssClass="form-control"/>
                    <form:errors path="name" cssClass="error-message"/>
                </div>

                <div class="form-group">
                    <form:label for="fowlNumber" path="number">Amount</form:label>
                    <form:input pattern="[0-9]*" path="number" id="fowlNumber" cssClass="form-control"/>
                    <form:errors path="number" cssClass="error-message"/>
                </div>

                <div class="form-group">
                    <form:label for="fowlDesc" path="description">Description</form:label>
                    <form:textarea path="description" id="fowlDesc" cssClass="form-control"/>
                    <form:errors path="description" cssClass="error-message"/>
                </div>

                <button type="submit" class="btn btn-primary">Submit</button>
            </form:form>
        </div>
    </div>
</div>

<div id="footer">
    <div class="container">
        <div class="row centered">
            <span id="currentDate"></span>
        </div>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/script/bootstrap.min.js"></script>
<script src="${contextPath}/resources/script/date.js"></script>
</body>
</html>
