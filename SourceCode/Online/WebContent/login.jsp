<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="myuser.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="jQuery/jquery-1.11.3.js"></script>
<script>
$(function(){
	$("#btn").click(function(){
		if($.trim($("input[name='uname']").val()).length==0){
			$("#info").css("color","red").html("请输入用户名！");
			$("input[name='uname']").select();
			$("input[name='uname']").focus();
			return false;
		}
		if($.trim($("input[name='pwd']").val()).length==0){
			$("#info").css("color","red").html("请输入密码！");
			$("input[name='pwd']").select();
			$("input[name='pwd']").focus();
			return false;
		}
//在这里都是有输入的,限制重复登录(Ajax):前端查后台
//设置Ajax同步运行
//post请求页面:判断返回data长度大于0，返回false
		$.ajaxSetup({async:false});  //设置异步运行为null
		var info="";
		$.post("CheckExists",{username:$("input[type='text'][name='uname']").val()},function(data){
			info=data;
		});
		if(info.length>0){
			alert(info);
			return false;
		}
		return true;
	});
});

</script>
</head>

<body>
<a href="main.jsp">主页面</a> <a href="login.jsp">登录页面</a> <a href="online.jsp">在线用户</a> <a href="visitor.jsp">历史访客</a>  <a href="history.jsp">访问记录</a> <br><br>
<form action="CheckIt" method="post">
<table class="gridtable">
<tr><th colspan="2">用户登录信息</th></tr>
<tr><td>用户名</td><td><input type="text" name="uname"></td></tr>
<tr><td>密码</td><td><input type="password" name="pwd"></td></tr>
<tr>
	<td colspan="2"><input type="submit" id="btn" value="登录" style="float: left"><div id="info" style="float:left"></div></td>
</tr>
</table>
</form>

</body>
</html>