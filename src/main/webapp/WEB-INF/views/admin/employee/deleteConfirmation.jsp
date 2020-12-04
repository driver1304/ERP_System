<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Delete-employee</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <link href="https://getbootstrap.com/docs/4.0/examples/signin/signin.css" rel="stylesheet" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" />


</head>
<body>

<div style="text-align: center">
    <h5 style="color: lightgray;">Are you sure you want to delete employee:
        <span style="color: white"> ${employee.firstName} ${employee.lastName}?</span>
    </h5></div>


<form style="text-align: center" method="post">
    <input style="height: 35px; width: 80px; position: center" class="btn" type="submit" name="decision" value="yes"/>
    <input style="height: 35px; width: 80px; position: center" class="btn btn-primary" type="submit" name="decision"
           value="no"/>
</form>

</body>
</html>