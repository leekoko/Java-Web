<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="javastudy.*,java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="jQuery/jquery-1.11.3.js"></script>
<script type="text/javascript">
$(function(){
	$("#btnAdd").click(function(){
		location.href="add.jsp";
	});
	$("input[type='button'][name='modifyit']").click(function(){
		//获取id
		var id=$(this).parent().parent().children().eq(0).children().eq(0).val();
		//页面跳转
		location.href="modify.jsp?id="+id;
		
	});
	$("input[type='checkbox'][name='selectit']:first").click(function(){
		$("input[type='checkbox'][name='selectit']").not(":first").prop("checked",$(this).prop("checked"));
	});
	$("#btnDelete").click(function(){
		var ids="";
		var num=0;
		$("input[type='checkbox'][name='selectit']").each(function(i,e){
			if(i>0){
				if($(this).prop("checked")){
					var id=$(this).parent().parent().children().eq(0).children().eq(0).val();
					ids+=id;
					num++;
					if(!$(this).is(":last")){
						ids+=",";
					}
				}
			}
		});
		var answer =confirm("你确定要删除这"+num+"本图书吗？");
		if(!answer){
			return;
		}
		location.href="DeleteIt?ids="+ids;
	});

	
});


</script>

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