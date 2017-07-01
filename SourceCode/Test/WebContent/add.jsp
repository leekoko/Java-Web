<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"  import="javastudy.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>

</head>
<body>


<form method="post" action="AddIt">
	<table>
		<tr><td>序号</td><td><input type="text" name="name"></td></tr>
		<tr><td>作者</td><td><input type="text" name="author" "></td></tr>
		<tr><td>价格</td><td><input type="text" name="price" ></td></tr>
		<tr><td>出版社</td><td><input type="text" name="publisher"></td></tr>
		<tr><td colspan="2"><input type="submit" value="添加图书"></td><td><div id="info"></div></td></tr>
	</table>
</form>







</body>
</html>