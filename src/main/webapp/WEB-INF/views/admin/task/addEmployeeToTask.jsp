<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Add employee to task</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <link href="https://getbootstrap.com/docs/4.0/examples/signin/signin.css" rel="stylesheet" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" />


</head>
<body>
<form class="form-signin" method="post">
    <h3 class="form-signin-heading" style="text-align: center">Add employee to task</h3>
    <p class="form-signin-heading" style="text-align: center;">
      <span>  ${taskName} </span>
        <span style="color: lightgray">Hours budget to be allocated:</span> <span style="color: red"> ${hoursBudgetToBeAllocated}</span></p>
    <table border="1px">
        <tr>
            <th style="min-width: 200px; text-align: center">Employee</th>
            <th width="350" style="text-align: center">Budget hours for employee</th>
        </tr>

        <c:forEach items="${employeesToAdd}" var="employee" varStatus="stat">
            <tr>
                <td style="text-align: center">${employee.firstName} ${employee.lastName}</td>
                <td style="text-align: center">
                    <input type="number" name="${employee.lastName}" min="0" step="1" class="form-control"
                           placeholder=""/>
                </td>
            </tr>

        </c:forEach>
    </table>

    <h5 style="color: red; text-align: center">${param.get('message')}</h5>
    <p>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Save</button>
    </p>

</form>

<form class="form-signin" method="get" action="/admin/task/show/${taskId}">
    <button class="btn btn-primary btn-block" type="submit">Back to task details</button>
</form>

</body>
</html>
