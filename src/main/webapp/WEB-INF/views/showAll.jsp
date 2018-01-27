<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="requestPath" value="${requestScope['javax.servlet.forward.request_uri']}"/>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="${contextPath}/resources/style/font-awesome.min.css">
    <link rel="stylesheet" href="${contextPath}/resources/style/bootstrap.min.css">
    <link rel="stylesheet" href="${contextPath}/resources/style/common.css">
    <link rel="stylesheet" href="${contextPath}/resources/style/table.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="shortcut icon" href="${contextPath}/resources/img/favicon-linux.ico"/>

    <title>Fowls</title>
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
                <li><a href="${contextPath}/show">Show fowls</a></li>
            </ul>
        </div>
    </div>
</div>

<div id="fowl-list">
    <div class="container">
        <c:choose>
            <c:when test="${fowlList != null}">
                <div class="container">
                    <div class="table-wrapper">
                        <div class="table-title">
                            <div class="row">
                                <div class="col-sm-6">
                                    <h2>List of <b>Fowls</b></h2>
                                </div>
                                <div class="col-sm-6">
                                    <a href="#addFowlModal" class="btn btn-success" data-toggle="modal"><i
                                            class="material-icons">&#xE147;</i> <span>Add New Fowl</span></a>
                                    <a href="#deleteFowlModal" class="btn btn-danger" data-toggle="modal"><i
                                            class="material-icons">&#xE15C;</i> <span>Delete</span></a>
                                    <a href="#statisticsModal" onclick="setupStatistics()" class="btn btn-warning" data-toggle="modal"><i
                                            class="material-icons">computer</i> <span>Statistics</span></a>
                                </div>
                            </div>
                        </div>
                        <table class="table table-striped table-hover">
                            <thead>
                            <tr>
                                <th>
							<span class="custom-checkbox">
								<input type="checkbox" id="selectAll">
								<label for="selectAll"></label>
							</span>
                                </th>
                                <th>Id</th>
                                <th>Name</th>
                                <th>Number</th>
                                <th>Description</th>
                                <th>Actions</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="fowl" items="${fowlList}">
                                <tr id="fowl_${fowl.id}">
                                    <td>
							<span class="custom-checkbox">
								<input type="checkbox" id="checkbox1" name="options[]" value="${fowl.id}">
								<label for="checkbox1"></label>
							</span>
                                    </td>
                                    <td>${fowl.id}</td>
                                    <td>${fowl.name}</td>
                                    <td>${fowl.number}</td>
                                    <td>${fowl.description}</td>
                                    <td>
                                        <a onclick="editHandle(new Fowl('${fowl.id}','${fowl.name}','${fowl.number}','${fowl.description}'))"
                                           href="#editFowlModal" class="edit" data-toggle="modal">
                                            <i class="material-icons" data-toggle="tooltip"
                                               title="Edit">&#xE254;</i></a>
                                        <a href="#"
                                           onclick="document.getElementById('deleteSingleForm_${fowl.id}').submit()"
                                           class="delete" data-toggle="modal"><i
                                                class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
                                        <form id="deleteSingleForm_${fowl.id}" action="${contextPath}/delete"
                                              method="post">
                                            <input type="hidden" name="deleteId" value="${fowl.id}">
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>

                <div id="statisticsModal" class="modal fade">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h4 class="modal-title">Statistics</h4>
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                    &times;
                                </button>
                            </div>
                            <div class="modal-body">
                                <h3 class="modal-title">Total fowl count</h3>
                                <span id="fowlCount"></span>
                                <h3 class="modal-title">Total number of species</h3>
                                <span id="fowlSpeciesCount"></span>
                            </div>
                            <div class="modal-footer">
                                <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                            </div>
                        </div>
                    </div>
                </div>

                <div id="addFowlModal" class="modal fade">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <form:form action="${contextPath}/add" method="post" modelAttribute="fowl">
                                <div class="modal-header">
                                    <h4 class="modal-title">Add Fowl</h4>
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                        &times;
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <div class="form-group">
                                        <label>Name</label>
                                        <form:input type="text" class="form-control" path="name"/>
                                        <form:errors path="name" cssClass="error-message"/>
                                    </div>
                                    <div class="form-group">
                                        <label>Amount</label>
                                        <form:input type="text" class="form-control" path="number"/>
                                        <form:errors path="number" cssClass="error-message"/>
                                    </div>
                                    <div class="form-group">
                                        <label>Description</label>
                                        <form:textarea class="form-control" path="description"/>
                                        <form:errors path="description" cssClass="error-message"/>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                                    <input type="submit" class="btn btn-success" value="Add">
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>

                <div id="editFowlModal" class="modal fade">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <form action="${contextPath}/edit" method="post">
                                <div class="modal-header">
                                    <h4 class="modal-title">Edit Fowl</h4>
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                        &times;
                                    </button>
                                </div>
                                <input type="hidden" id="editId" name="id"/>
                                <div class="modal-body">
                                    <div class="form-group">
                                        <label>Name</label>
                                        <input id="editName" name="name" type="text" class="form-control" required>
                                    </div>
                                    <div class="form-group">
                                        <label>Number</label>
                                        <input id="editNumber" name="number" type="text" class="form-control" required>
                                    </div>
                                    <div class="form-group">
                                        <label>Description</label>
                                        <textarea id="editDescription" name="description" class="form-control"
                                                  required></textarea>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel"
                                           onclick="cancelEditModalHandle()">
                                    <input type="submit" class="btn btn-info" value="Save">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

                <div id="deleteFowlModal" class="modal fade">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <form method="post" action="${contextPath}/delete" id="deleteFowlForm">
                                <div class="modal-header">
                                    <h4 class="modal-title">Delete Fowl</h4>
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                        &times;
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <p>Are you sure you want to delete these Records?</p>
                                    <p class="text-warning">
                                        <small>This action cannot be undone.</small>
                                    </p>
                                </div>
                                <div class="modal-footer">
                                    <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                                    <input type="button" class="btn btn-danger" value="Delete"
                                           onclick="deleteHandle(document.getElementsByName('options[]'))"
                                           id="deleteButtonBtn">
                                    <input type="submit" style="display: none" id="deleteSubmitBtn">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <p>No records yet</p>
            </c:otherwise>
        </c:choose>
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
<script type="text/javascript" src="${contextPath}/resources/script/table.js"></script>
<script type="text/javascript" src="${contextPath}/resources/script/editModalHandler.js"></script>
<script type="text/javascript" src="${contextPath}/resources/script/deleteModalHandler.js"></script>
<script src="${contextPath}/resources/script/date.js"></script>
<script src="${contextPath}/resources/script/statisticsModal.js"></script>
</body>
</html>
