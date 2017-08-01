<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="javastudy.*,java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<table>
	<tr><th>序号</th><th>图书名称</th><th>作者</th><th>价格</th><th>出版社</th><th>修改</th><th><input type="checkbox" name="selectit"></th></tr>
<%
	BookDAO dao=new BookDAO();
	ArrayList<Book> al=dao.getBooks();
	for(int i=0;i<al.size();i++){
		Book book=al.get(i);
%>
	<tr>
		<td><%=i+1 %><input type="hidden" name="id" value="<%=book.getId()%>"></td>
		<td><%=book.getName() %></td>
		<td><%=book.getAuthor() %></td>
		<td><%=book.getPrice() %></td>
		<td><%=book.getPublisher() %></td>
		<td><input type="button" name="modifyit" value="修改"></td>
		<td><input type="checkbox" name="selectit"></td>
	</tr>
<%
	}
%>

	<tr>
		<td><input type="button" id="btnDelete" value="Delete"/></td>
		<td><input type="button" id="btnAdd" value="Add"/></td>		
	</tr>
</table>

</body>
</html>