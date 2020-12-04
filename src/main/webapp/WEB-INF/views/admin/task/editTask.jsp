<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Edit-task</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <link href="https://getbootstrap.com/docs/4.0/examples/signin/signin.css" rel="stylesheet" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" />


</head>
<body>

<form:form class="form-signin" method="post" modelAttribute="task">
    <h3 style="text-align: center" class="form-signin-heading">Edit task data</h3>


    <p>
        Name:
        <form:input type="text" class="form-control" maxlength="40" path="name" placeholder="Name"
                    autofocus="true" />
        <form:errors path="name" cssClass="validation"/>
    </p>
    <p>
        Description:
        <form:textarea class="form-control" maxlength="300" path="description" placeholder="Description"/>
        <form:errors path="description" cssClass="validation"/>
    </p>

    <p>
        Hours budget:
        <form:input  type="number" min="1" step="1" class="form-control" path="hoursBudget" value="" placeholder="Hours budget"
                     required="true"/>
        <form:errors path="hoursBudget" cssClass="validation"/>
    </p>


    <p>
        Start date:
        <form:input type="date" class="form-control"  path="startTerm" placeholder="Start date"  required="true"/>
        <form:errors path="startTerm" cssClass="validation"/>
    </p>
    <p>
       Deadline:
        <form:input type="date" class="form-control"  path="deadline" placeholder="Deadline"  required="true"/>
        <form:errors  cssClass="validation"/>
    </p>



        <button class="btn btn-lg btn-primary btn-block" type="submit">Save</button>
    </p>

</form:form>

<form class="form-signin" method="get" action="/admin/task">
    <button class="btn btn-primary btn-block" type="submit">Back to list of tasks</button>
</form>

</body>
</html>
