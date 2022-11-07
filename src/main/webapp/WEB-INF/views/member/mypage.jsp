<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../temp/boot.jsp"></c:import>
</head>
<body>
		<h3>MY PAGE</h3>	
		<sec:authentication property="Principal" var="user"/>
		<h3>ID : ${user.id} </h3> <!-- [key=value] 기준은 '=' -->
		
		<h3>NAME :<sec:authentication property="Principal.name"/> </h3>
		<h3>EMAIL :<sec:authentication property="Principal.email"/> </h3><!-- Principal 생략가능 -->
</body>
</html>