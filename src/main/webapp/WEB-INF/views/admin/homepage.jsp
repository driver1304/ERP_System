<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Homepage</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <link href="https://getbootstrap.com/docs/4.0/examples/signin/signin.css" rel="stylesheet" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" />


</head>
<body>

<p>
<h3 style="text-align:left">
    <a style="color: lightgray" href="http://localhost:8080/admin/homepage"><security:authentication property="principal.company.name"/></a>
</h3>



Hello, <security:authentication property="principal.firstName"/> <security:authentication property="principal.lastName"/>.</p>
</p>
<br>

<div>
<form method="get" action="/admin/employee/">
    <button  class="btn" type="submit">Employees</button>
</form>
<form method="get" action="/admin/task/">
    <button  class="btn" type="submit">Tasks</button>
</form>
</div>
<br>
<br>
<br>
<br>
<br>
<br>
<form method="get" action="/perform_logout">
    <button class="btn btn-primary" type="submit">Logout</button>
</form>


</body>
</html>