<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'regist.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  
  <body>
  <h1>请注册</h1> <br/>
   <p style = "color: red; font-weight: 900">${msg }</p>
   <form action = "${pageContext.request.contextPath }/RegistServlet" method = "post">
   		姓 名：<input type = "text" name = "username" value = "${user.username }"/> ${errors.username }<br/>
   		密 码：<input type = "password" name = "password" value = "${user.password }" />${errors.password }<br/>
   		<input type = "submit" value = "注册"/>
   </form>
  </body>
</html>
