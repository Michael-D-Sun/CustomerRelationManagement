<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Michael
  Date: 2020/8/3
  Time: 21:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<h1>Register</h1>
<p style="color: red;font-weight: 900">${msg}</p>
<form action="<c:url value="/RegisterServlet"/>" method="post">
    Username:<input type="text" name="username"/>${errors.username}<br/>
    Password:<input type="text" name="password"/>${errors.password}<br/>
    <input type="submit" value="Register"/>
</form>
</body>
</html>