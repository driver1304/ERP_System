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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css"/>


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
<table border="1px" style="width: 1000px" class="details">
    <tr>
        <th colspan="5" style="text-align:center; padding: 10px">Assigned employees</th>
    </tr>
    <tr>
        <th style="width:30px"> Lp.</th>
        <th style="width:280px; text-align: center">Employee</th>
        <th style="width:450px; text-align: center">Description</th>
        <th style="width:120px; text-align: center">Hours budget for task</th>
        <th style="width:120px; text-align: center">Actions</th>
    </tr>

    <c:forEach items="${allEmployeesForTask}" var="employee" varStatus="stat">
        <tr>
            <td style="text-align: center">${stat.count}.</td>
            <td><a style="color: white" href="http://localhost:8080/admin/employee/show/${employee.id}">
                    ${employee.firstName} ${employee.lastName}</a></td>
            <td style="text-align: left; padding-bottom: 5px">
                <a style="color: white" href="http://localhost:8080/admin/task/employee/editDesc/${task.id}/${employee.id}">
                        ${hoursBudgetForUserForTask.get(employee.id).description}</a>
            </td>
            <td style="text-align: center">
                <a style="color: white" href="http://localhost:8080/admin/task/employee/editHours/${task.id}/${employee.id}">
                        ${hoursBudgetForUserForTask.get(employee.id).hoursBudget}</a>
            </td>
            <td style="text-align: center">
                <a href="http://localhost:8080/admin/task/employee/delete/${task.id}/${employee.id}">Detach</a></td>
        </tr>

    </c:forEach>
    <tr style="color: lightgray;padding: 8px">
        <td style="text-align: center" colspan="3">Sum of allocated hours :</td>
        <td style="text-align: center">${sumOfAllocatedHours} / ${task.hoursBudget}</td>
    </tr>
</table>
</p>

<p>


<h5 style="color: red">${param.get('message')}</h5>

<form method="get" action="/admin/task/employee/add/${task.id}">
    <button class="btn" type="submit">Add employee to task</button>
</form>
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