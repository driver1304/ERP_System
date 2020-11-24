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

<h4>List of employee: </h4>

<form method="get" action="/admin/employee/register">
    <button class="btn" type="submit">Add employee</button>
</form>

<table border="1px">
    <tr>
        <th> Lp.</th>
        <th>First name</th>
        <th>Last name</th>
        <th>Actions</th>
    </tr>

    <c:forEach items="${employees}" var="employee" varStatus="stat">
    <tr>
        <td>${stat.count}</td>
        <td>${employee.firstName}</td>
        <td>${employee.lastName}</td>
        <td><a href="http://localhost:8080/bookform/delete/${employee.id}">Delete</a>
            <a href="http://localhost:8080/bookform/edit/${employee.id}">Edit</a></td>
    </tr>

    </c:forEach>

</table>

<form method="get" action="/admin/homepage">
    <button class="btn btn-primary" type="submit">Homepage</button>
</form>


</body>
</html>