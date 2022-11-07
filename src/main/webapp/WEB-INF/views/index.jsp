<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>    <!-- 에러문구 출력을 위한 태그api -->
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="./temp/boot.jsp"></c:import>
<link href="/css/test.css" rel="stylesheet">
<script defer src="/js/test.js"></script>
<!-- defer : 로딩과 동시에 실행 -->
</head>
<body>
	<h1>Index Page</h1>
	<h1><spring:message code="hi" var="h"></spring:message> </h1>
	<h1><spring:message code="test" text="code가 없을때 출력하는 메세지"></spring:message> </h1>
	
	<img src="/images/iu1.jpg">
	<a href="/qna/list?page=1">QnA</a>
	<!-- 로그인 성공 시 -->
	<sec:authorize access="isAuthenticated()"> <!-- 인증이 되엇습니까 하고 묻는것 했다면 true true라면 보여주세요 -->
		<sec:authentication property="Principal" var="user"/>
	응애<h3><spring:message code="welcome" arguments="${user.id}" ></spring:message> </h3>
	<h3><spring:message code="welcome2" arguments="${user.id},${user.name}" argumentSeparator="," ></spring:message> </h3>
	<a href="/member/mypage">MYPAGE</a>
	<a href="/member/logout">LOGOUT</a>
<%-- 	  역할은 ${member.roleVOs[0].roleName} --%>
	</sec:authorize>
	  
	<!-- 로그인 전 -->
	<sec:authorize access="!isAuthenticated()">
	<a href="/member/add">JOIN</a>
	<a href="/member/login">Login</a>
	</sec:authorize>
	
	
	<sec:authorize url="/admin"> <!-- config에 등록된 권한을 url의 주소로 사용하면 권한이 자동으로 적용됨  -->
		<a href="/admin">ADMIN</a>
	</sec:authorize>
	<sec:authorize access="hasAnyRole('MANAGER','ADMIN')">
		<a href="/manager">MANAGER</a>
	</sec:authorize>
<%-- 	<sec:authorize access="hasRole('USER','MANAGER','ADMIN')">
		<a href="/user"></a>
	</sec:authorize> --%>
	<div>
		<img src="/file/qna/1ca6475e-37be-47ca-8350-7881491a2408_KakaoTalk_20220827_202803205_09.jpg">
		<img src="/file/notice/KakaoTalk_20221020_144855641_01.jpg">
		<a href="/fileDown/qna?fileNum=2">QnaDown</a>
		<a href="/fileDown/notice?fileNum=2">NoticeDown</a>
	</div>
<button id="btn" type="button">CLICK</button>

<button class="buttons">BTN1</button>
<button class="buttons">BTN2</button>
<button class="buttons">BTN3</button>

<div id="test">

	
</div>
<h1>${h}</h1>
<h1>${h}</h1>
<h1>${h}</h1>
<h1>${h}</h1>
<h1>${h}</h1>
<h1>${h}</h1>

</body>
</html>