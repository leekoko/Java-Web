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
DBLib lib=new DBLib();
lib.create_data();
lib.create_form();
lib.add_data(application.getRealPath("")+"/data/data.txt");
out.print("初始化成功");
%>

</body>
</html>