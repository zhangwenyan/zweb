<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title></title>
    <style type="text/css">
        .login-page {
            width: 360px;
            padding: 12% 0 0;
            margin: auto;
        }
        .form {
            position: relative;
            z-index: 1;
            background: #FFFFFF;
            max-width: 360px;
            margin: 0 auto 100px;
            padding: 45px;
            text-align: center;
            box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
        }
        .form input {
            font-family: "Roboto", sans-serif;
            outline: 0;
            background: #f2f2f2;
            width: 100%;
            border: 0;
            margin: 0 0 15px;
            padding: 15px;
            box-sizing: border-box;
            font-size: 14px;
        }
        .form button {
            font-family: "Microsoft YaHei","Roboto", sans-serif;
            text-transform: uppercase;
            outline: 0;
            background: #4CAF50;
            width: 100%;
            border: 0;
            padding: 15px;
            color: #FFFFFF;
            font-size: 14px;
            -webkit-transition: all 0.3 ease;
            transition: all 0.3 ease;
            cursor: pointer;
        }
        .form button:hover,.form button:active,.form button:focus {
            background: #43A047;
        }
        .form .message {
            margin: 15px 0 0;
            color: #b3b3b3;
            font-size: 12px;
        }
        .form .message a {
            color: #4CAF50;
            text-decoration: none;
        }
        .form .register-form {
            display: none;
        }
        .container {
            position: relative;
            z-index: 1;
            max-width: 300px;
            margin: 0 auto;
        }
        .container:before, .container:after {
            content: "";
            display: block;
            clear: both;
        }
        .container .info {
            margin: 50px auto;
            text-align: center;
        }
        .container .info h1 {
            margin: 0 0 15px;
            padding: 0;
            font-size: 36px;
            font-weight: 300;
            color: #1a1a1a;
        }
        .container .info span {
            color: #4d4d4d;
            font-size: 12px;
        }
        .container .info span a {
            color: #000000;
            text-decoration: none;
        }
        .container .info span .fa {
            color: #EF3B3A;
        }
        body {
            background: #76b852; /* fallback for old browsers */
            background: -webkit-linear-gradient(right, #76b852, #8DC26F);
            background: -moz-linear-gradient(right, #76b852, #8DC26F);
            background: -o-linear-gradient(right, #76b852, #8DC26F);
            background: linear-gradient(to left, #76b852, #8DC26F);
            font-family: "Roboto", sans-serif;
            -webkit-font-smoothing: antialiased;
            -moz-osx-font-smoothing: grayscale;
        }
        .shake_effect{
            -webkit-animation-name: shake;
            animation-name: shake;
            -webkit-animation-duration: 1s;
            animation-duration: 1s;
        }
        @-webkit-keyframes shake {
            from, to {
                -webkit-transform: translate3d(0, 0, 0);
                transform: translate3d(0, 0, 0);
            }

            10%, 30%, 50%, 70%, 90% {
                -webkit-transform: translate3d(-10px, 0, 0);
                transform: translate3d(-10px, 0, 0);
            }

            20%, 40%, 60%, 80% {
                -webkit-transform: translate3d(10px, 0, 0);
                transform: translate3d(10px, 0, 0);
            }
        }

        @keyframes shake {
            from, to {
                -webkit-transform: translate3d(0, 0, 0);
                transform: translate3d(0, 0, 0);
            }

            10%, 30%, 50%, 70%, 90% {
                -webkit-transform: translate3d(-10px, 0, 0);
                transform: translate3d(-10px, 0, 0);
            }

            20%, 40%, 60%, 80% {
                -webkit-transform: translate3d(10px, 0, 0);
                transform: translate3d(10px, 0, 0);
            }
        }
        p.center{
            color: #fff;font-family: "Microsoft YaHei";
        }
    </style>
</head>
<body>
<div class="htmleaf-container">
    <div id="wrapper" class="login-page">
        <div id="login_form" class="form">
            <form class="register-form">
                <input type="text" placeholder="用户名" id="r_user_name"/>
                <input type="password" placeholder="密码" id="r_password" />
                <input type="text" placeholder="电子邮件" id="r_emial"/>
                <button id="create">创建账户</button>
                <p class="message">已经有了一个账户? <a href="#">立刻登录</a></p>
            </form>
            <form class="login-form">
                <input type="text" placeholder="用户名" id="user_name" value="admin"/>
                <input type="password" placeholder="密码" id="password" value="888888"/>
                <button id="login">登　录</button>
                <p class="message">还没有账户? <a href="#">立刻创建</a></p>
            </form>
        </div>
    </div>
</div>

<script src="/js/jquery.min.js"></script>
<script src="/js/layer/layer.js"></script>
<script type="text/javascript">
    function check_login()
    {
        var name=$("#user_name").val();
        var pass=$("#password").val();
        var index = layer.load(1, {
            shade: [0.1,'#fff'] //0.1透明度的白色背景
        });
        $.post("/User.action",{action:"login",username:name,password:pass},function(result){
            if(result.success){
                layer.load();
                window.location.href='/page/home/';
                return;
            }
            layer.msg(result.msg);
            $("#password").val("");
        },"json").complete(function(){
            layer.close(index);
        });

    }
    function check_register(){
        var name = $("#r_user_name").val();
        var pass = $("#r_password").val();
        var email = $("r_email").val();
        if(name!="" && pass=="" && email != "")
        {
            alert("注册成功！");
            $("#user_name").val("");
            $("#password").val("");
        }
        else
        {
            $("#login_form").removeClass('shake_effect');
            setTimeout(function()
            {
                $("#login_form").addClass('shake_effect')
            },1);
        }
    }
    $(function(){
        $("#create").click(function(){
            check_register();
            return false;
        })
        $("#login").click(function(){
            check_login();
            return false;
        })
        $('.message a').click(function () {
            $('form').animate({
                height: 'toggle',
                opacity: 'toggle'
            }, 'slow');
        });
    })
</script>
</body>
</html>
<%
System.out.print("tttttLogin");
%>