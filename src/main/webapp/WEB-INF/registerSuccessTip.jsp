<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/1/5
  Time: 16:41
  To change this template use File | Settings | File Templates.
--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path ;
    request.setAttribute("path", basePath);
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册成功</title>
    <meta http-equiv="refresh" content="3;url=toPage/toLogin.do"/>
</head>
<body>
    <h1>${result.message}!!!</h1>
</body>

</html>
