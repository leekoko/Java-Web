<%@page import="myhis.HistoryDAO"%>
<%@page import="myhis.History"%>
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
历史轨迹表
<br><br>
<a href="main.jsp">主页面</a> <a href="login.jsp">登录页面</a> <a href="online.jsp">在线用户</a> <a href="visitor.jsp">历史访客</a>  <a href="history.jsp">访问记录</a> <br><br>
<table border="1" class="gridtable">
	<tr>
		<th>序号</th>
		<th>到访ID</th>
		<th>访问时间</th>
		<th>访问页面</th>
	</tr>
<% 
	HistoryDAO dao=new HistoryDAO();
	dao.setPageSize(10);
	if(session.getAttribute("page")==null){
		dao.setPageNo(1);
		session.setAttribute("page", 1);
	}else{
		dao.setPageNo(Integer.parseInt(session.getAttribute("page").toString()));
	}
	dao.computePgeCount();   //初始化总页数
	
	ArrayList<History> al=dao.getPageDate();

	
	for(int i=0;i<al.size();i++){
		History his=al.get(i);
		
		SimpleDateFormat sFormat=new SimpleDateFormat("yyy-MM-dd HH:mm:ss"); //处理时间格式
		String vtime=sFormat.format(his.getVisitTime());
		
		String url=his.getUrl();
%>
	<tr>
		<td><%=i+1 %></td>
		<td><%=his.getId()%></td>
		<td><%=vtime%></td>
		<td><%=url%></td>
	</tr>
<%	} %>
</table> 
<a href="PageServlet?op=prev">上一页</a>	<%=session.getAttribute("page") %>/<%=dao.getPageCount() %>	<a href="PageServlet?op=next">下一页</a>
</body>
</html>