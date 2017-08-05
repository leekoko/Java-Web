<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>java爬虫抓图</title>
<script type="text/javascript">
	function validator() {
		//判断网址合法性
		//获取输入框的值
		var url=document.getElementById("url").value;
		//判断不是为空或者长度为0
		if(url=""||url.length==0){
			alert("请输入url");
			document.getElementById("url").focus();   //获取焦点
			return false;
		}
		if(url!=""&&url.indexOf("http://")==-1){
			alert("请输入正确的url");
			document.getElementById("url").focus();   //获取焦点
			return false;
			
		}
		
	}

</script>
</head>
<body>
	<div class="box">
		<h1>爬虫页面</h1>
		<form action="source.jsp" method="post" onsubmit="return validator();">
			<input type="text" placeholder="请输入URL：如http://www.qq.com" name="url" id="url">
			<input type="submit" value="获取源代码"/> 
		</form>
	</div>


</body>
</html>