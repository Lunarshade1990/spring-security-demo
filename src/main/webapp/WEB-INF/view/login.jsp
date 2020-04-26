<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 30045033
  Date: 19.04.2020
  Time: 13:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>My Custom Login PAge</h3>
<c:if test="${param.error != null}">
    <i>Invalid password or username</i>
</c:if>
<form:form action="${pageContext.request.contextPath}/authenticateUser" method="post">
    <p>
        User name: <input type="text" name="username"/>
    </p>
    <p>
        Password: <input type="password" name="password"/>
    </p>
    <input type="submit" value="Login"/>
</form:form>
</body>
</html>
