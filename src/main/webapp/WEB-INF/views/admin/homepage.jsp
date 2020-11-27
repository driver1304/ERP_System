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
    <link href="<c:url value="/WEB-INF/css/myStyle.css" />" rel="stylesheet">
</head>

<body style="background-color: #3e3e3e; color: white">

<p>
<h4 style="text-align:left"><security:authentication property="principal.company.name"/></h4>
<security:authentication property="principal.firstName"/> <security:authentication property="principal.lastName"/></p>
</p>
<br>


<form method="get" action="/admin/employee/">
    <button style="display: inline;" class="btn" type="submit">Employees</button>
</form>
<form method="get" action="/admin/task/">
    <button style="display: inline;" class="btn" type="submit">Tasks</button>
</form>

<%--<security:authorize access="hasRole('ROLE_ADMIN')">--%>
<%--    <p>This text is only visible to an admin</p>--%>
<%--    <p>Użytkownik: <security:authentication property="principal.lastName"/>--%>
<%--        <br/>Firma: <security:authentication property="principal.company.name"/></p>--%>

<%--    <a href="<c:url value="/admin/adminpage.html" />">Admin Page</a>--%>
<%--    <br/>--%>
<%--</security:authorize>--%>
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