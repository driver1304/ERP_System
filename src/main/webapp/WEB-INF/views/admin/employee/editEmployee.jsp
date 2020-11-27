<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Employee-register</title>
    <link href="/WEB-INF/css/myStyle.css" rel="stylesheet" type="text/css">
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <link href="https://getbootstrap.com/docs/4.0/examples/signin/signin.css" rel="stylesheet" crossorigin="anonymous">

</head>
<body style="background-color: #3e3e3e; color: white">


<form:form class="form-signin" method="post" modelAttribute="employee">
    <h2 class="form-signin-heading">Register new employee</h2>


    <p>
        <label for="firstName" class="sr-only">First name: </label>
        <form:input type="text" class="form-control" maxlength="100" path="firstName" placeholder="First name"
                    required=""
                    autofocus=""/>
        <form:errors path="firstName" cssClass="validation"/>
    </p>
    <p>
        <label for="lastName" class="sr-only">Last name: </label>
        <form:input type="text" class="form-control" maxlength="100" path="lastName" placeholder="Last name" required=""
                    autofocus=""/>
        <form:errors path="lastName" cssClass="validation"/>
    </p>
    <p>
        <label for="email" class="sr-only">Email: </label>
        <form:input type="email" class="form-control" maxlength="100" path="email" placeholder="Email address"
                    required=""
                    autofocus=""/>
        <form:errors path="email" cssClass="validation"/>
    </p>
    <p>
        <form:hidden path="password"/>
    </p>


    <c:if test="${not empty message}">
        <h5 style="color: red; text-align:center">${message}</h5>
    </c:if>
    <p>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Edit</button>
    </p>

</form:form>

<form class="form-signin" method="get" action="/admin/employee">
    <button class="btn btn-primary btn-block" type="submit">Back to list of employees</button>
</form>

</body>
</html>
