<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Details-task</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <link href="https://getbootstrap.com/docs/4.0/examples/signin/signin.css" rel="stylesheet" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" />


</head>
<body>
<h3 style="text-align:left">
    <a style="color: lightgray" href="http://localhost:8080/admin/homepage">
        <security:authentication property="principal.company.name"/>
    </a>
</h3>

<h5 style="color: lightgray"> ${task.name} </h5>
<p>
<div>Start date: ${task.startTerm}</div>
<div>Dealine: ${task.deadline}</div>
<div>Hours budget: ${task.hoursBudget} <span style="color: red"> ${message}</span></div>
<div>Completed: ${task.completed}</div>
<div> Description: ${task.description}</div>
</p>

<p>
<table border="1px">
    <tr>
        <th colspan="4" style="text-align:center; padding: 10px">Assigned employees</th>
    </tr>
    <tr>
        <th style="min-width:30px"> Lp.</th>
        <th style="min-width:300px">Employee</th>
        <th style="min-width:200px; text-align: center">Hours budget for task</th>

    </tr>

    <c:forEach items="${allEmployeesForTask}" var="employee" varStatus="stat">
        <tr>
            <td>${stat.count}</td>
            <td><a style="color: white" href="http://localhost:8080/admin/employee/show/${employee.id}">
                    ${employee.firstName} ${employee.lastName}</a></td>
            <td style="text-align: center">${hoursBudgetForUserForTask.get(employee.id)}</td>
        </tr>

    </c:forEach>
    <tr style="color: lightgray;padding: 5px">
        <td style="text-align: center" colspan="2">Sum of allocated hours :</td>
        <td style="text-align: center">${sumOfAllocatedHours} / ${task.hoursBudget}</td>
    </tr>
</table>
</p>

<br>
<p>
<form method="get" action="/admin/task/">
    <button class="btn" type="submit">Back to list of tasks</button>
</form>
</p>

<form method="get" action="/perform_logout">
    <button class="btn btn-primary" type="submit">Logout</button>
</form>


</body>
</html>