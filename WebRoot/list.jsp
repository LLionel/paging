<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>客户列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
<h3 align="center">客户列表</h3>
<table border="1" width="70%" align="center">
	<tr>
		<th>客户姓名</th>
		<th>性别</th>
		<th>生日</th>
		<th>手机</th>
		<th>邮箱</th>
		<th>描述</th>
		<th>操作</th>
	</tr>
	<c:forEach items = "${requestScope.pb.beanList }" var = "customer">
	
		<tr>
			<td>${customer.cname }</td>
			<td>${customer.gender }</td>
			<td>${customer.birthday }</td>
			<td>${customer.cellphone }</td>
			<td>${customer.email }</td>
			<td>${customer.description }</td>
			<td>
				<a href="<c:url value='/servlet/CustomerServlet?method=preEdit&cid=${customer.cid}'/>">编辑</a>
				<a href="<c:url value='/servlet/CustomerServlet?method=delete&cid=${customer.cid}'/>">删除</a>
			</td>
		</tr>
	
	</c:forEach>
	
</table>

	<c:choose>
	
		<c:when test="${pb.tp<=10 }">
			<c:set var="begin" value = "1"></c:set>
			<c:set var = "end" value = "${pb.tp}"></c:set>
		</c:when>
		<c:otherwise>
			<c:set var = "begin" value = "${pb.pc-5 }"></c:set>
			<c:set var = "end" value = "${pb.pc+4 }"></c:set>
			
			<c:if test="${begin<1 }">
				<c:set var = "begin" value = "1"></c:set>
				<c:set var = "end" value = "10"></c:set>
			</c:if>
			<c:if test="${pb.tp<end }">
			<c:set var = "begin" value = "${pb.tp-9 }"></c:set>
				<c:set var = "end" value = "${pb.tp }"></c:set>
			</c:if>
			
			
		</c:otherwise>
	</c:choose>
	
<center>
	<br/>
	第${pb.pc }页/共${pb.tp } &nbsp;
	<a href = "<c:url value = '/servlet/CustomerServlet?method=findAll' />">首页</a>
	<c:if test = "${pb.pc>1 }">
		<a href = "<c:url value = '/servlet/CustomerServlet?method=findAll&pc=${pb.pc-1 }'/>">上一页</a>
	</c:if>
	
	<c:forEach var = "i" begin = "${begin }" end = "${end }">
		<c:choose>
			<c:when test="${i!=pb.pc }">
				<a href = "<c:url value = '/servlet/CustomerServlet?method=findAll&pc=${i }'/>"> [${i }]</a>
			</c:when>
		<c:otherwise>[${i }]</c:otherwise>
		</c:choose>
		
	</c:forEach>
	
	<c:if test = "${pb.pc<pb.tp }">
		<a href = "<c:url value = '/servlet/CustomerServlet?method=findAll&pc=${pb.pc+1 }'/>">下一页</a>
	</c:if>
	<a href = "<c:url value = '/servlet/CustomerServlet?method=findAll&pc=${pb.tp}'/>">尾页</a>
	
</center>
  </body>
</html>
