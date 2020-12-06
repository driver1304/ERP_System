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
<form class="form-signin" style="max-width: 650px" method="post">
    <h3 class="form-signin-heading" style="text-align: center">Add employee to task</h3>

    <p class="form-signin-heading" style="text-align: center;">
      <span>  ${taskName} </span>
        <span style="color: lightgray">Hours budget to be allocated:</span> <span style="color: red"> ${hoursBudgetToBeAllocated}</span>
    </p>

<div style="position: center; text-align: center">
    <table border="1px" >

        <tr>
            <th style="width: 200px; text-align: center">Employee</th>
            <th style="text-align: center; width: 120px">Budget hours for employee</th>
            <th  style="text-align: center; width: 330px">Description</th>
        </tr>

        <c:forEach items="${employeesToAdd}" var="employee" varStatus="stat">
            <tr>
                <td style="text-align: center">${employee.firstName} ${employee.lastName}</td>
                <td style="text-align: center">
                    <input style="height: 50px; text-align: center" type="number" name="${employee.id}" min="0" step="1" class="form-control"/>
                </td>
                <td style="text-align: center">
                    <textarea style="height: 50px; text-align: left" name="${employee.id}_desc" class="form-control">
                    </textarea>
                </td>
            </tr>

        </c:forEach>
    </table>
</div>

    <h5 style="color: red; text-align: center">${param.get('message')}</h5>
    <div style="text-align: center">
        <button  class="btn btn-primary" style="width: 200px" type="submit">Save</button>
    </div>

</form>

<form class="form-signin" method="get" action="/admin/task/show/${taskId}">
    <button class="btn btn-block" style="width: 300px" type="submit">Back to task details</button>
</form>

</body>
</html>
