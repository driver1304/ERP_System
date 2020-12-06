<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Homepage</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <link href="https://getbootstrap.com/docs/4.0/examples/signin/signin.css" rel="stylesheet" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css"/>


</head>
<body>


<form:form class="form-signin" method="post" modelAttribute="user">
    <h2 style="text-align: center" class="form-signin-heading">Register</h2>


    <p>
        <label for="firstName" class="sr-only">First name: </label>
        <form:input type="text" class="form-control" maxlength="40" path="firstName" placeholder="First name"
                    required="true"
                    autofocus="true"/>
        <form:errors path="firstName" cssClass="validation"/>
    </p>
    <p>
        <label for="lastName" class="sr-only">Last name: </label>
        <form:input type="text" class="form-control" maxlength="40" path="lastName" placeholder="Last name"
                    required="true"/>
        <form:errors path="lastName" cssClass="validation"/>
    </p>
    <p>
        <label for="email" class="sr-only">Email: </label>
        <form:input type="text" class="form-control" maxlength="100" path="email" placeholder="Email address"
                    required="true"/>
        <form:errors path="email" cssClass="validation"/>
    </p>
    <p>
        <label for="company.name" class="sr-only">Company: </label>
        <form:input type="text" class="form-control" maxlength="40" path="company.name" placeholder="Company name"
                    required="true"/>
        <c:if test="${not empty messageCompany}">
            <span class="validation">${messageCompany}</span>
        </c:if>
    </p>

    <p>
        <label for="password" class="sr-only">Password: </label>
        <form:input type="password" class="form-control" path="password" placeholder="Password" required="true"/>
        <form:errors path="password" cssClass="validation"/>
    </p>


    <c:if test="${not empty message}">
        <h5 style="color: red; text-align:center">${message}</h5>
    </c:if>
    <p>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Register</button>
    </p>

</form:form>

<form class="form-signin" method="get" action="/login.html">
    <h5 style="text-align:center"> To login click the button below!</h5>
    <button class="btn btn-primary btn-block" type="submit">Login</button>
</form>

</body>
</html>
