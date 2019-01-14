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
    <title>欢迎注册EasyMall</title>
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" href="${path}/css/regist.css"/>
    <script src="${path}/js/jquery-1.11.1.min.js"></script>
    <script>
        function checkUserName(obj) {
            var userName = $(obj).val();
            $.ajax({
                type:"post",
                url:"../checkName.do",
                data:{"userName":userName},
                success:function (data) {
                    if(data.code == 1){
                        $("#msg").val(data.message);
                    }else {
                        alert("查询数据异常")
                    }
                }
            });
        }
    </script>
</head>
<body>
<form action="${path}/register.do" method="POST">
    <h1>欢迎注册EasyMall</h1>
    <table>
        <tr>
            <td class="tds">用户名：</td>
            <td>
                <input type="text" name="username" onblur="checkUserName(this)"/>
                <span id="msg" style="color:red"></span>
            </td>
        </tr>
        <tr>
            <td class="tds">密码：</td>
            <td>
                <input type="password" name="password"/>
            </td>
        </tr>
        <tr>
            <td class="tds">确认密码：</td>
            <td>
                <input type="password" name="password2"/>
            </td>
        </tr>
        <tr>
            <td class="tds">昵称：</td>
            <td>
                <input type="text" name="nickname"/>
            </td>
        </tr>
        <tr>
            <td class="tds">邮箱：</td>
            <td>
                <input type="text" name="email"/>
            </td>
        </tr>
        <tr>
            <td class="tds">验证码：</td>
            <td>
                <input type="text" name="valistr"/>
                <img src="${path}/img/regist/yzm.jpg" width="" height="" alt="" />
            </td>
        </tr>
        <tr>
            <td class="sub_td" colspan="2" class="tds">
                <input type="submit" value="注册用户"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>

