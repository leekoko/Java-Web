<%@page import="com.kcat.TmDownImgUtil"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="jQuery/jquery-1.11.3.js"></script>
<title>Insert title here</title>
<%
//获取url
	String url=request.getParameter("url");
//获取源代码
	String html=TmDownImgUtil.htmlSource(url, "utf-8");
//将源代码输出到页面对象中
	pageContext.setAttribute("htmlsource", html);

%>

</head>
<body>
	<div class="box">
		<h1>爬虫页面</h1>
		<textarea style="width: 1120px; height: 460px; overflow: auto;" id="source">${htmlsource}</textarea>
	</div>
	<form action="download.jsp" method="post">
		<input type="submit" value="下载"/> 
	</form>
	<h3>一共抓取到<span id="count"></span>张图片</h3>
	<script type="text/javascript">
		//解析源代码获取图片素材
		var source=$("#source").val();
		//jq 对象
		var $source=$(source);
		//计数
		var i=0;
		//遍历所有img图片
		$source.find("img").each(function(){
			var src=$(this).attr("src");
			if(src!=""&&src.length>0&&src.indexOf(".jpg")!=1){
				$("body").append("<div style='float:left; margin:5px;'><img src='"+src+"' width='180' height='180'/></div>");
				i++;
			}
			$("#count").text(i);
		});
	</script>

</body>
</html>