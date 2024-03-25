<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>AdminLTE 3 | Blank Page</title>

    <!-- Google Font: Source Sans Pro -->
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="/assets/plugins/fontawesome-free/css/all.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="/assets/dist/css/adminlte.min.css">
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
<body class="hold-transition sidebar-mini">
<div class="wrapper">
    <jsp:include page="${pageContext.request.contextPath}/WEB-INF/layout/nav.jsp"></jsp:include>
    <jsp:include page="${pageContext.request.contextPath}/WEB-INF/layout/aside.jsp"></jsp:include>
<%--    <jsp:include page="../layout/nav.jsp"></jsp:include>--%>
<%--    <jsp:include page="../layout/aside.jsp"></jsp:include>--%>

    <div class="content-wrapper">
        <h1>Danh sách tài khoản</h1>
        <p><a href="/admin/users/create">Thêm mới</a></p>
        <div>
            <form method="get" action="/admin/users/index">
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
                        <li class="active"><a href="/admin/users/index?page=${page}&key=${key}">${page}</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="/admin/users/index?page=${page}&key=${key}">${page}</a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </ul>
    </div>
    <jsp:include page="${pageContext.request.contextPath}/WEB-INF/layout/footer.jsp"></jsp:include>
<%--    <jsp:include page="../layout/footer.jsp"></jsp:include>--%>
</div>
<script src="/assets/plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="/assets/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE App -->
<script src="/assets/dist/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="/assets/dist/js/demo.js"></script>
</body>
</html>