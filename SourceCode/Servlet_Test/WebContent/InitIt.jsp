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
	$(".btn").click(function(){
		$.post("init_data",function(data){   //注解方式的url
			$("#info").html(data);
		});
	});
});


</script>
</head>
<body>
<input type="button" class="btn" value="init"/>
<div id="info"></div>
</body>
</html>