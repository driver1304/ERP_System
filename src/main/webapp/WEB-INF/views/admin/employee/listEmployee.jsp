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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" />


</head>
<body>
<h3 style="text-align:left">
    <a style="color: lightgray" href="http://localhost:8080/admin/homepage"><security:authentication property="principal.company.name"/></a>
</h3>

<p>
<table border="1px">
    <tr>
        <th colspan="3" style="text-align:center; padding: 10px">Employees</th>
    </tr>
    <tr>
        <th style="min-width:30px"> Lp.</th>
        <th style="min-width:300px">Name</th>
        <th style="min-width:150px; text-align: center">Actions</th>
    </tr>

    <c:forEach items="${employees}" var="employee" varStatus="stat">
    <tr>
        <td>${stat.count}</td>
        <td><a style="color: white" href="http://localhost:8080/admin/employee/show/${employee.id}">${employee.firstName} ${employee.lastName}</a>
               </td>
        <td style="text-align: center">
            <a href="http://localhost:8080/admin/employee/edit/${employee.id}">Edit</a>
            <a href="http://localhost:8080/admin/employee/delete/${employee.id}">Delete</a></td>
    </tr>

    </c:forEach>

</table>
</p>
<p>
<form method="get" action="/admin/employee/register">
    <button class="btn" type="submit">Register new employee</button>
</form>
</p>
<br>
<br>
<form method="get" action="/perform_logout">
    <button class="btn btn-primary" type="submit">Logout</button>
</form>


</body>
</html>