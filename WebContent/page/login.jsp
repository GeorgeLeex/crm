<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css">
    <style type="text/css">
        #header{
            height: 41px;
            background-image: url("../img/login-top-bg.gif");
        }
        #content{
            height: 532px;
            background-image: url("../img/login_bg.jpg");
        }
        #foot{
            height: 64px;
            background-color: #1d3647;
        }
        #login_title{
            margin: 0px;
        }
        #login_content{
            width: 28%;
            margin: 0 auto;
            padding-top: 60px;
        }
        #login_btn{

        }
        .form-group{
            padding: 10px 0px;
        }
        #login_btn h2{
            display: inline;
            word-spacing: 80px;
            line-height: 25px;
        }
        #checkInput{
            width: 54%;
        }
        #checkInput~img{
            height: 46px;
            width: 30%;
        }
        #changeImg{
            font-size: 16px;
            cursor: pointer;
        }
        #changeImg{
            position: absolute;
            /*top: 20px;*/
        }
    </style>
</head>
<body>
<div id="header"></div>
<div id="content">
    <div id="login_content">
        <form id="login_form" onsubmit="return checkVerCode()" action="${pageContext.request.contextPath}/LoginServlet" method="post">
            <div class="form-group text-center">
                <h1 id="login_title">客户关系管理系统</h1>
            </div>
            <div class="form-group form-group-lg">
                <div class="input-group">
                    <span class="input-group-addon "><span class="glyphicon glyphicon-user"></span></span>
                    <input name="account" class="form-control" placeholder="请输入手机号码/邮箱/用户名">
                </div>
            </div>
            <div class="form-group form-group-lg">
                <div class="input-group">
                    <span class="input-group-addon "><span class="glyphicon glyphicon-asterisk"></span></span>
                    <input name="password" class="form-control" type="password" placeholder="请输入密码">
                </div>
            </div>
            <div class="form-group form-group-lg form-inline">
                <div class="input-group">
                    <span class="input-group-addon "><span class="glyphicon glyphicon-info-sign"></span></span>
                    <input id="checkInput" name="verifi" class="form-control" placeholder="请输入验证码" onkeyup="getVerCode()">
                    <img id="verifyImg" alt="验证码" class="img-rounded">
                    <a id="changeImg" onclick="javascript:document.getElementById('verifyImg').src='${pageContext.request.contextPath}/VerifyCodeServlet?date='+new Date();">看不清<br>换一张</a>
                </div>
            </div>
            <div class="form-group form-group-lg">
                <!--<input id="login_btn" class="btn btn-primary" value="登录">
                <input id="reset_btn" class="btn btn-warning" value="重置">-->
                <button id="login_btn" type="button" class="btn btn-lg btn-primary form-control" onclick="login()"><h2>登 录</h2></button>
            </div>
        </form>
        <input type="hidden" id="hiddenMsg" value="">
    </div>
</div>
<div id="foot"></div>
</body>
<script src="../resource/js/jquery-3.2.1.min.js"></script>
<script src="../resource/bootstrap/js/bootstrap.min.js"></script>
<script>
	function login() {
		window.location.href="main.html";
	}
</script>
<!--<script>
    window.onload=function () {
        document.getElementById("verifyImg").src="${pageContext.request.contextPath}/VerifyCodeServlet?date="+new Date();
    }
    function checkVerCode() {
        var input = document.getElementById("checkInput");
        var inputVal = input.value;
        var verCode = document.getElementById("hiddenMsg").value;
        var lowerCase = inputVal.toLowerCase();
        if ((inputVal != "" && inputVal != null && inputVal != undefined) && (lowerCase == verCode)) {
            return true;
        } else {
            alert("验证码输入有误");
            return false;
        }
    }
    function getVerCode() {
        $.getJSON("${pageContext.request.contextPath}/GetVerifyCodeServlet", function (json) {
            if (json == null || json == "") {
                console.log("It's null");
            } else {
                $("#hiddenMsg").val(json);
            }
        })
    }
</script>-->
</html>
