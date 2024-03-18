<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Cập nhật thông tin</title>
</head>
<body>
<h1>Thông tin tài khoản</h1>
<f:form method="post" enctype="multipart/form-data" modelAttribute="user">
    <f:input path="id" type="hidden" />
    <f:input path="avatar" type="hidden" />
    <f:input path="created" type="hidden" />
    <table>
        <tr>
            <th>Email</th>
            <td><f:input path="email" /></td>
            <th><f:errors path="email" /></th>
        </tr>
        <tr>
            <th>Password</th>
            <td><f:input path="password" /></td>
            <th><f:errors path="password" /></th>
        </tr>
        <tr>
            <th>Phone</th>
            <td><f:input path="phone" /></td>
            <th><f:errors path="phone" /></th>
        </tr>
        <tr>
            <th>Image</th>
            <td><f:input path="image" type="file" /></td>
            <th><f:errors path="image" /></th>
        </tr>
        <tr>
            <th>Birthday</th>
            <td><f:input path="birthday" type="date" /></td>
            <th><f:errors path="birthday" /></th>
        </tr>
        <tr>
            <th>Address</th>
            <td><f:input path="address" /></td>
            <th><f:errors path="address" /></th>
        </tr>
        <tr>
            <th>Status</th>
            <td><f:input path="status" /></td>
            <th><f:errors path="status" /></th>
        </tr>
    </table>
    <button>Save</button>
</f:form>
</body>
</html>