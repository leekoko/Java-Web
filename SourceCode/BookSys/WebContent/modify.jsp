<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="javastudy.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>

</head>
<body>
<%
	int id =Integer.parseInt(request.getParameter("id"));
	BookDAO dao=new BookDAO();
	Book book=dao.getBookById(id);
%>

<form method="post" action="Modify">
	<table>
		<input type="hidden" name="id" value="<%=id%>" />
		<tr><td>图书名称</td><td><input type="text" name="name" value="<%=book.getName()%>"></td></tr>
		<tr><td>作者</td><td><input type="text" name="author" value="<%=book.getAuthor()%>"></td></tr>
		<tr><td>价格</td><td><input type="text" name="price" value="<%=book.getPrice()%>"></td></tr>
		<tr><td>出版社</td><td><input type="text" name="publisher" value="<%=book.getPublisher()%>"></td></tr>
		<tr><td colspan="2"><input type="submit" value="修改图书信息"></td><td><div id="info"></div></td></tr>
	</table>
</form>

</body>
</html>