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
    <a style="color: lightgray" href="http://localhost:8080/user/homepage">
        <security:authentication property="principal.company.name"/>
    </a>
</h3>

<h5 style="color: lightgray"> ${task.name} </h5>
<p>
<div>Start date: ${task.startTerm}</div>
<div>Dealine: ${task.deadline}</div>
<div>Total hours budget: ${task.hoursBudget} <span style="color: red"> ${message}</span></div>
<div>Completed: ${task.completed}</div>
<div> Description: ${task.description}</div>
</p>

<p>
<table border="1px" style="width: 600px">
    <tr>
        <th colspan="2" style="text-align:center; padding: 10px">Your details</th>
    </tr>
    <tr>
        <th style="width:480px; text-align: center" >Description</th>
        <th style="width:120px; text-align: center">Hours budget for task</th>
    </tr>


    <tr>
        <td style="text-align: left">${userTaskHoursBudget.description}</td>
        <td style="text-align: center">${userTaskHoursBudget.hoursBudget}</td>
    </tr>

</table>
</p>

<p>
<form method="get" action="/user/task">
    <button class="btn" type="submit">Back to list of task</button>
</form>
</p>
<br>

<form method="get" action="/perform_logout">
    <button class="btn btn-primary" type="submit">Logout</button>
</form>


</body>
</html>