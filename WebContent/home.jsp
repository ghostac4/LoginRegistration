<%@page import="com.bridgelabz.model.UserModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Login and Registration</title>
<link rel="stylesheet" href="css/main.css">
</head>
<body>
<%
	String username="";
	if(request.getSession().getAttribute("user")!=null){
	   username = ((UserModel) request.getSession().getAttribute("user")).getName();
	}
%>
<h1>Welcome, <%=username %></h1>
<a href='LogoutPage'><button type="button"><p>Logout</p></button></a>
</body>
</html>