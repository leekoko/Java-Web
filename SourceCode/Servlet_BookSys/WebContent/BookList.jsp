<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="javastudy.*,java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<table>
	<tr><th>序号</th><th>图书名称</th><th>作者</th><th>价格</th><th>出版社</th><th>修改</th><th><input type="checkbox" name="selectit"></th></tr>
<%
BookDAO dao=new BookDAO();
ArrayList<Book> al=dao.getBooks();
for(int a=0;a<al.size();a++){
	Book b=al.get(a);
%>	
<tr>
	<th><%=a+1 %></th>
	<th><%=b.getName() %></th>
	<th><%=b.getAuthor() %></th>
	<th><%=b.getPrice() %></th>
	<th><%=b.getPublisher() %></th>
	<th><input type="button" value="modify"></th>
	<th><input type="checkbox" name="selectit"></th>
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