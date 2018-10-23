<%--
  Created by IntelliJ IDEA.
  User: Da
  Date: 2018/9/20
  Time: 16:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>登陆页面</title>
    <meta name="decorator" content="blank"/>
    <script>
        // 判断是登录账号和密码是否为空
        function check(){
            var username = $("#username").val();
            var password = $("#password").val();
            if(username=="" || password==""){
                $("#message").text("账号或密码不能为空！");
                return false;
            }
            return true;
        }
    </script>
    <style type="text/css">
        html, body, table {
            background-color: #f5f5f5;
            width: 100%;
            text-align: center;
        }

        .form-signin-heading {
            font-family: Helvetica, Georgia, Arial, sans-serif, 黑体;
            font-size: 36px;
            margin-bottom: 20px;
            color: #0663a2;
        }

        .form-signin {
            position: relative;
            text-align: left;
            width: 300px;
            padding: 25px 29px 29px;
            margin: 0 auto 20px;
            background-color: #fff;
            border: 1px solid #e5e5e5;
            -webkit-border-radius: 5px;
            -moz-border-radius: 5px;
            border-radius: 5px;
            -webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
            -moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
            box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
        }

        .form-signin .checkbox {
            margin-bottom: 10px;
            color: #0663a2;
        }

        .form-signin .input-label {
            font-size: 16px;
            line-height: 23px;
            color: #999;
        }

        .form-signin .input-block-level {
            font-size: 16px;
            height: auto;
            margin-bottom: 15px;
            padding: 7px;
            *width: 283px;
            *padding-bottom: 0;
            _padding: 7px 7px 9px 7px;
        }

        .form-signin .btn.btn-large {
            font-size: 16px;
        }

        .form-signin #themeSwitch {
            position: absolute;
            right: 15px;
            bottom: 10px;
        }

        .form-signin div.validateCode {
            padding-bottom: 15px;
        }

        .mid {
            vertical-align: middle;
        }

        .header {
            height: 80px;
            padding-top: 20px;
        }

        .alert {
            position: relative;
            width: 300px;
            margin: 0 auto;
            *padding-bottom: 0px;
        }

        label.error {
            background: none;
            width: 270px;
            font-weight: normal;
            color: inherit;
            margin: 0;
        }

        body {

            background-image: url(/static/images/back.jpg);
            background-repeat: no-repeat;
            background-position: center;
            background-attachment: fixed;
            background-size: contain;
        }
    </style>


</head>
<body>
<!--[if lte IE 6]><br/>
<div class='alert alert-block' style="text-align:left;padding-bottom:10px;"><a class="close" data-dismiss="alert">x</a>
    <h4>温馨提示：</h4>
    <p>你使用的浏览器版本过低。为了获得更好的浏览体验，我们强烈建议您 <a href="http://browsehappy.com" target="_blank">升级</a> 到最新版本的IE浏览器，或者使用较新版本的
        Chrome、Firefox、Safari 等。</p></div><![endif]-->

<div class="header">
        <font color="red">
            <%-- 提示信息--%>
            <span id="message">${msg1}</span>
        </font>
</div>

<h1 class="form-signin-heading">登陆页面</h1>

<form id="loginForm" class="form-signin" action="/user/checkLogin" method="post" onsubmit="return check()">
    <label class="input-label" for="username">用户名</label><br/>
    <input type="text" id="username" name="username" class="input-block-level required"/><br/>
    <label class="input-label" for="password">密码</label><br/>
    <input type="password" id="password" name="password" class="input-block-level required"/>
    <br/>
    <input class="btn btn-large btn-primary" type="submit" value="登 录"/>&nbsp;&nbsp;
    <%--<label for="rememberMe" title="下次不需要再登录"><input type="checkbox" id="rememberMe" name="rememberMe" ${rememberMe ? 'checked' : ''}/> 记住我（公共场所慎用）</label>--%>

</form>

<div class="footer">
    Copyright &copy; 2018 - Development By Da V1.0.0
</div>
<%-- jquery 核心 JS 文件 --%>
<script type="text/javascript" src="/static/jquery/jquery-1.12.4.min.js"></script>
<%--<script src="/static/flash/zoom.min.js" type="text/javascript"></script>--%>
</body>
</html>
