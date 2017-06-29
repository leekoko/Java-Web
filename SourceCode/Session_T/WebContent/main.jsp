<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>

<script type="text/javascript" src="jQuery/jquery-1.11.3.js"></script>
<script type="text/javascript">
	$(function(){
		$("#c").click(function(){
			if($.trim($("#color").val()).length==0){
				$("#color").focus();
				return;
			}
			$("body").css("background-color",$("#color").val());
			$.ajax("save.jsp?color="+$("#color").val());
		});
		$("#p1").bind("click",function(){
			window.open("show.jsp");
		});
		
	});

</script>

</head>
<body>

<input type="button" value="设置颜色" id="c"/><input type="text" id="color"><br>
<input type="button" value="打开窗口1" id="p1"/>







</body>
</html>