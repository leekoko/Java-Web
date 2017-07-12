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
	$("[type='button']").click(function(){
		$.post("InitIt",function(data){
			$("#info").html(data);	
		});
	});
});
</script>
</head>
<body>
<input type="button" value="初始化数据库">
<div id="info"></div>
</body>
</html>