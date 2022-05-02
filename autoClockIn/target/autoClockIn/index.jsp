<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>添加记录</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/addUser" method="post">
    <label>
        姓名：
        <input type="text" name="name">
    </label>
    <label>
        身份证号：
        <input type="text" name="id">
    </label>
    <input type="submit" value="提交">
</form>
</body>
</html>
