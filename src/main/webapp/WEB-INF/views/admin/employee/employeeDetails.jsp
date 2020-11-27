<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>List of employees</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <link href="https://getbootstrap.com/docs/4.0/examples/signin/signin.css" rel="stylesheet" crossorigin="anonymous">
    <link href="<c:url value="/WEB-INF/css/myStyle.css" />" rel="stylesheet">
</head>

<body style="background-color: #3e3e3e; color: white">


<h5 style="color: lightgray">${employee.firstName} ${employee.lastName}</h5>
<h7>${employee.email}</h7>

<p>
<table border="1px">
    <tr>
        <th colspan="4" style="text-align:center">Active projects</th>
    </tr>
    <tr>
        <th style="min-width:30px"> Lp.</th>
        <th style="min-width:120px">Title</th>
        <th style="min-width:120px">Deadline</th>
        <th style="min-width:120px">Action</th>
    </tr>

    <c:forEach items="${userActiveTasks}" var="task" varStatus="stat">
        <tr>
            <td>${stat.count}</td>
            <td>${task.name}</td>
            <td>${task.deadline}</td>
            <td>
                    <%--                <a href="http://localhost:8888/admin/employee/task/${employee.id}">Details</a>--%>
                <a href="http://localhost:8888/admin/employee/${employee.id}">Edit</a>
                <a href="http://localhost:8888/admin/employee/task/delete/${employee.id}/${task.id}">Delete</a></td>
        </tr>
    </c:forEach>
</table>
</p>
<br>
<p>
<table border="1px">
    <tr>
        <th colspan="4" style="text-align:center">Completed projects</th>
    </tr>
    <tr>
        <th style="min-width:30px"> Lp.</th>
        <th style="min-width:120px">Title</th>
        <th style="min-width:120px">Deadline</th>
        <th style="min-width:120px">Action</th>
    </tr>

    <c:forEach items="${userCompletedTasks}" var="task" varStatus="stat">
        <tr>
            <td>${stat.count}</td>
            <td>${task.name}</td>
            <td>${task.deadline}</td>
            <td>
                    <%--                <a href="http://localhost:8888/admin/employee/task/${employee.id}">Details</a>--%>
                <a href="http://localhost:8080/bookform/delete/${employee.id}">Delete</a></td>
        </tr>
    </c:forEach>
</table>
</p>

<p>
<form method="get" action="/admin/employee/">
    <button class="btn" type="submit">Back to list of Employees</button>
</form>
</p>
<p>
<form method="get" action="/admin/homepage">
    <button class="btn btn-primary" type="submit">Homepage</button>
</form>
</p>

</body>
</html>