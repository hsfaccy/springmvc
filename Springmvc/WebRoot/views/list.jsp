<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>  
<%@ include file="common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<html>  
  <head>  
    <title>测试</title>  
  </head>  
    
  <body>
  	<table>
  	<c:forEach items="${list }" var="list">
  		<tr>
  			<td>${list.user_name }</td>
  			<td>${list.age }</td>
  		</tr>
  	</c:forEach>
  	</table>  
  </body>  
</html>  