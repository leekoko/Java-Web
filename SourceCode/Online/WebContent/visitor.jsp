<%@page import="myuser.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,myvisit.*,java.text.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="CSS/Untitled-1.css">

</head>
<body>
历史访客表
<br><br>
<a href="main.jsp">主页面</a> <a href="login.jsp">登录页面</a> <a href="online.jsp">在线用户</a> <a href="visitor.jsp">历史访客</a>  <a href="history.jsp">访问记录</a> <br><br>
<table border="1" class="gridtable">
	<tr>
		<th>序号</th>
		<th>用户名</th>
		<th>IP地址</th>
		<th>来访时间</th>
		<th>离开时间</th>
		<th>来自的URL</th>	
	</tr>
<% 
	VisitorDAO dao=new VisitorDAO();
	ArrayList<Visitor> al=dao.getVisitors();
	for(int i=0;i<al.size();i++){
		Visitor v=al.get(i);
		
		SimpleDateFormat sFormat=new SimpleDateFormat("yyy-MM-dd HH:mm:ss"); //处理时间格式
		String vtime=sFormat.format(v.getVisitTime());
		String url="";
		if(v.getComeFrom()!=null){
			url=v.getComeFrom();
		}
		String ltime="";
		if(v.getLeftTime()!=null){
			ltime=sFormat.format(v.getLeftTime());
		}

		UserDAO udao=new UserDAO();
		String userName=udao.getNameById(v.getUserId());  //获取到用户名
%>
	<tr>
		<td><%=i %></td>
		<td><%=userName%></td>
		<td><%=v.getIp() %></td>
		<td><%=vtime %></td>
		<td><%=ltime %></td>
		<td><%= url%></td>
	</tr>
<%	} %>
</table> 
</body>
</html>