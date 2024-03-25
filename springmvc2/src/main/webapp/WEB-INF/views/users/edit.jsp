<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>AdminLTE 3 | Blank Page</title>
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
    <link rel="stylesheet" href="/assets/plugins/fontawesome-free/css/all.min.css">
    <link rel="stylesheet" href="/assets/dist/css/adminlte.min.css">

</head>
<body class="hold-transition sidebar-mini">
<div class="wrapper">
    <jsp:include page="../layout/nav.jsp"></jsp:include>
    <jsp:include page="../layout/aside.jsp"></jsp:include>

    <div class="content-wrapper">
        <h1>Thông tin tài khoản</h1>
        <f:form method="post" enctype="multipart/form-data" modelAttribute="user">
            <f:input path="id" type="hidden"/>
            <f:input path="avatar" type="hidden"/>
            <f:input path="created" type="hidden"/>
            <table>
                <tr>
                    <th>Email</th>
                    <td><f:input path="email"/></td>
                    <th><f:errors path="email"/></th>
                </tr>
                <tr>
                    <th>Password</th>
                    <td><f:input path="password"/></td>
                    <th><f:errors path="password"/></th>
                </tr>
                <tr>
                    <th>Phone</th>
                    <td><f:input path="phone"/></td>
                    <th><f:errors path="phone"/></th>
                </tr>
                <tr>
                    <th>Image</th>
                    <td><f:input path="image" type="file"/></td>
                    <th><f:errors path="image"/></th>
                </tr>
                <tr>
                    <th>Birthday</th>
                    <td><f:input path="birthday" type="date"/></td>
                    <th><f:errors path="birthday"/></th>
                </tr>
                <tr>
                    <th>Address</th>
                    <td><f:input path="address"/></td>
                    <th><f:errors path="address"/></th>
                </tr>
                <tr>
                    <th>Status</th>
                    <td><f:input path="status"/></td>
                    <th><f:errors path="status"/></th>
                </tr>
            </table>
            <button>Save</button>
        </f:form>
    </div>
    <jsp:include page="../layout/footer.jsp"></jsp:include>
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