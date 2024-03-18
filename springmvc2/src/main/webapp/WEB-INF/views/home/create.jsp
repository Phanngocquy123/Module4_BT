<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 3/15/2024
  Time: 10:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Thêm mới</h1>
<f:form method="post" enctype="multipart/form-data" modelAttribute="pro">
    <p><b>Mã sp: </b> <f:input path="id" /><br><f:errors path="id" cssStyle="color: red" /></p>
    <p><b>Tên sp: </b> <f:input path="name" /></p>
    <p><b>Giá sp: </b> <f:input path="price" /></p>
    <p><b>Ảnh sp: </b> <f:input path="image" type="file" /></p>
    <p><b>Ngày tạo: </b> <f:input path="created" type="date" /></p>
    <p><b>Trạng thái: </b> <f:input path="status" /></p>
    <button>Lưu</button>
</f:form>
</body>
</html>
