<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Danh sách tài khoản</title>
    <style>
        .pagination {
            list-style-type: none;
            margin: 0;
            padding: 0;
            overflow: hidden;
        }

        .pagination li {
            float: left;
            margin-right: 5px;
        }

        .pagination li a {
            text-decoration: none;
        }

        .pagination li.active a {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<h1>Danh sách tài khoản</h1>
<p><a href="/users/create">Thêm mới</a></p>
<div>
    <form method="get" action="/users/index">
        <input type="hidden" name="action" value="index">
        <b>Tìm kiếm: </b>
        <input type="text" name="key" value="${key}" placeholder="Nhập từ khóa tìm kiếm">
        <button type="submit">Tìm kiếm</button>
    </form>
</div>
<table border="1" cellpadding="5" cellspacing="0" width="100%">
    <tr>
        <th>Avatar</th>
        <th>Email</th>
        <th>Phone</th>
        <th>Address</th>
        <th>Birthday</th>
        <th>Created</th>
        <th>Status</th>
        <th>Action</th>
    </tr>
    <c:forEach var="u" items="${userList}">
        <tr>
            <td>
                <img src="${u.avatar}" alt="" width="100">
            </td>
            <td>${u.email}</td>
            <td>${u.phone}</td>
            <td>${u.address}</td>
            <td><fmt:formatDate value="${u.birthday}" pattern="dd-MM-yyyy"/></td>
            <td><fmt:formatDate value="${u.created}" pattern="dd-MM-yyyy HH:mm:ss"/></td>
            <td>${u.status}</td>
            <td>
                <a href="/users/edit/${u.id}">Edit</a> |
                <a href="/users/delete/${u.id}" onclick="return confirm('Bạn có muốn xóa không?')">Remove</a>
            </td>
        </tr>
    </c:forEach>
</table>
<ul class="pagination">
    <c:forEach begin="1" end="${totalPage}" var="page">
        <c:choose>
            <c:when test="${page == currentPage}">
                <li class="active"><a href="/users/index?page=${page}&key=${key}">${page}</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="/users/index?page=${page}&key=${key}">${page}</a></li>
            </c:otherwise>
        </c:choose>
    </c:forEach>
</ul>
</body>
</html>