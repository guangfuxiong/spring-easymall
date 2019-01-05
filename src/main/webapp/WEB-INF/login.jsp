<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/1/5
  Time: 14:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path ;
    request.setAttribute("path", basePath);
%>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" href="${path}/css/login.css"/>
    <title>EasyMall欢迎您登陆</title>
</head>
<body>
<h1>欢迎登陆EasyMall</h1>
<form action="#" method="POST">
    <table>
        <tr>
            <td class="tdx">用户名:</td>
            <td><input type="text" name="username" value=''/></td>
        </tr>
        <tr>
            <td class="tdx">密码:</td>
            <td><input type="password" name="password"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="checkbox" name="remname" value="true"/>记住用户名
                <input type="checkbox" name="autologin" value="true"/>30天内自动登陆
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="登陆"/>
                <font color="red"></font>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
