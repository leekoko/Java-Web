<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆页面</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入 Bootstrap -->
    <link href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
    <script src="https://code.jquery.com/jquery.js"></script>
    <!-- 包括所有已编译的插件 -->
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <!-- HTML5 Shim 和 Respond.js 用于让 IE8 支持 HTML5元素和媒体查询 -->
    <!-- 注意： 如果通过 file://  引入 Respond.js 文件，则该文件无法起效果 -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="row clearfix" style="margin-top: 30px;">
                <h1>华工随堂助手</h1><p>本网站会爬取题目，匹配答案后列出来</p>
                <div class="col-md-4 column">
                </div>
                <div class="col-md-4 column">
                    <form>
                        <div class="form-group">
                            <label for="account">账号(身份证)</label><input type="account" class="form-control" id="account" />
                        </div>
                        <div class="form-group">
                            <label for="password">密码（默认：hnlg+身份证后六位）</label><input type="password" class="form-control" id="password" />
                        </div>
                    </form>
                    <button class="btn btn-default" onclick="login()">登陆</button>
                    <p style="color: red" id="message"></p>
                </div>
                <div class="col-md-4 column">

                </div>
                <p style="color: linen">仅供学习娱乐，交流使用</p>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    function login(){
        var account = $("#account").val();
        var password = $("#password").val();
        if(account.trim() === '' || password.trim() === ''){
            $("#message").text("账号密码不能为空");
            return;
        }else {
            $("#message").text("");
        }
        $.ajax({
            url: '/problem/loginWeb',
            data: {userName: account, password: password},
            async: false,
            type: "POST",
            dataType: "json",
            success: function (result) {
                var data = result.data;
                if(data !== ''){
                    window.location.href = "/page/index?data="+data+"&account="+account;
                }
            }
        });
    }


</script>
</html>

