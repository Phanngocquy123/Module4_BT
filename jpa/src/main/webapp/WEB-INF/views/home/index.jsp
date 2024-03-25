<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Home pages</h1>
<h2>${txt}</h2>
<table>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Price</th>
        <th>Status</th>
    </tr>
    <c:forEach var="c" items="${data}">
        <tr>
            <td>${c.id}</td>
            <td>${c.name}</td>
            <td>${c.price}</td>
            <td>${c.status}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
