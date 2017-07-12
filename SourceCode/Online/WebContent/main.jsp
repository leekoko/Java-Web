<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="jQuery/jquery-1.11.3.js"></script>
<script type="text/javascript">
$(function(){
	$("#online").click(function(){
		location.href="online.jsp";
	});
	$("#visitor").click(function(){
		location.href="visitor.jsp";
	});
	$("#history").click(function(){
		location.href="history.jsp";
	});
	$("#login").click(function(){
		location.href="login.jsp";
	});
});
</script>
</head>

<body>
<input type="button" id="online" value="在线用户">
<input type="button" id="visitor" value="历史访客">
<input type="button" id="history" value="访客轨迹">
<input type="button" id="login" value="登录界面">
</body>
</html>