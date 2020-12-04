<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Edit-hours budget</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <link href="https://getbootstrap.com/docs/4.0/examples/signin/signin.css" rel="stylesheet" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" />

</head>
<body>

<form:form class="form-signin" method="post" modelAttribute="userTaskHoursBudget">
    <h4 class="form-signin-heading" style="text-align: center">Edit hours budget for task</h4>
    <p class="form-signin-heading" style="text-align: center;">

        <span> ${userTaskHoursBudget.task.name} </span>

        <span style="color: lightgray">Hours budget to be allocated:</span> <span
            style="color: red"> ${hoursBudgetToBeAllocated}</span></p>

    <table border="1px">
        <tr>
            <th style="min-width: 200px; text-align: center">Employee</th>
            <th width="350" style="text-align: center">Budget hours for employee</th>
        </tr>
        <tr>
            <td style="text-align: center">${userTaskHoursBudget.user.firstName} ${userTaskHoursBudget.user.lastName}</td>
            <td style="text-align: center">
                <form:input type="number" min="1" step="1" class="form-control" path="hoursBudget"
                            placeholder="Hours budget"
                            required="true"/>
                <form:errors path="hoursBudget" cssClass="validation"/>
            </td>
        </tr>
    </table>

    <h5 style="color: red; text-align: center">${param.get('message')}</h5>

    <form:hidden path="user"/>
    <form:hidden path="task"/>
    <form:hidden path="id"/>
    <p>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Save</button>
    </p>

</form:form>

<form class="form-signin" method="get" action="/admin/task/show/${taskId}">
    <button class="btn btn-primary btn-block" type="submit">Back to task details</button>
</form>

</body>
</html>
