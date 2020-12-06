<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Details-employee</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <link href="https://getbootstrap.com/docs/4.0/examples/signin/signin.css" rel="stylesheet" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" />


</head>
<body>
<h3 style="text-align:left">
    <a style="color: lightgray" href="http://localhost:8080/user/homepage"><security:authentication
            property="principal.company.name"/></a>
</h3>
<p>
<h5>${employee.firstName} ${employee.lastName}</h5>
<h7>${employee.email}</h7>
</p>
<p>
<table border="1px">
    <tr>
        <th colspan="4" style="text-align:center; padding: 10px">Active projects</th>
    </tr>
    <tr>
        <th style="min-width:30px"> Lp.</th>
        <th style="min-width:300px">Title</th>
        <th style="min-width:200px; text-align: center">Hours budget for task</th>
        <th style="min-width:150px; text-align: center">Deadline</th>
    </tr>

    <c:forEach items="${userActiveTasks}" var="task" varStatus="stat">
        <tr>
            <td style="text-align: center">${stat.count}.</td>
            <td><a style="color: white" href="http://localhost:8080/user/task/show/${task.id}">
                    ${task.name}</a></td>
            <td style="text-align: center">
                    ${hoursBudgetForUserForTask.get(task.id).hoursBudget}
            </td>
            <td style="text-align: center">${task.deadline}</td>
                    </tr>
    </c:forEach>
</table>
</p>
<br>
<p>
<table border="1px">
    <tr>
        <th colspan="4" style="text-align:center; padding: 10px">Completed projects</th>
    </tr>
    <tr>
        <th style="min-width:30px"> Lp.</th>
        <th style="min-width:300px">Title</th>
        <th style="min-width:200px; text-align: center">Hours budget for task</th>
        <th style="min-width:150px; text-align: center">Deadline</th>
    </tr>

    <c:forEach items="${userCompletedTasks}" var="task" varStatus="stat">
        <tr>
            <td style="text-align: center">${stat.count}.</td>
            <td><a style="color: white" href="http://localhost:8080/user/task/show/${task.id}">
                    ${task.name}</a></td>
            <td style="text-align: center">
                    ${hoursBudgetForUserForTask.get(task.id).hoursBudget}
            </td>
            <td style="text-align: center">${task.deadline}</td>
        </tr>
    </c:forEach>
</table>
</p>
<br>
<br>

<p>
<form method="get" action="/user/homepage">
    <button class="btn" type="submit">Back to homepage</button>
</form>
</p>

<form method="get" action="/perform_logout">
    <button class="btn btn-primary" type="submit">Logout</button>
</form>

</body>
</html>