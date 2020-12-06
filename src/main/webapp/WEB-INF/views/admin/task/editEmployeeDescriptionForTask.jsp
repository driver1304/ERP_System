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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css"/>

</head>
<body>

<form:form class="form-signin" method="post"  style="max-width: 650px"
           modelAttribute="userTaskHoursBudget">
    <h4 class="form-signin-heading" style="text-align: center">Edit description for task</h4>
    <p class="form-signin-heading" style="text-align: center;">
        <span> ${userTaskHoursBudget.task.name} </span>

    <div style="position: center; text-align: center">
        <table border="1px">
            <tr>
                <th style="width: 200px; text-align: center">Employee</th>
                <th  style="text-align: center; width: 450px">Description</th>
            </tr>
            <tr>
                <td style="text-align: center">${userTaskHoursBudget.user.firstName} ${userTaskHoursBudget.user.lastName}</td>
                <td style="text-align: center">
                    <form:textarea class="form-control" cssStyle="height: 100px" path="description"/>
                    <form:errors path="description" cssClass="validation"/>
                </td>
            </tr>
        </table>
    </div>

    <form:hidden path="user"/>
    <form:hidden path="task"/>
    <form:hidden path="id"/>
    <form:hidden path="hoursBudget"/>

    <div style="text-align: center; padding: 20px">
        <button  class="btn btn-primary" style="width: 200px" type="submit">Save</button>
    </div>

</form:form>

<form class="form-signin" method="get" action="/admin/task/show/${taskId}">
    <button class="btn  btn-block" type="submit">Back to task details</button>
</form>

</body>
</html>
