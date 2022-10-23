<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="./temp/boot.jsp"></c:import>
<link href="/css/test.css" rel="stylesheet">
</head>
<body>
	<h1>Index Page</h1>
	<img src="/images/iu1.jpg">
	<a href="/qna/list?page=1">QnA</a>
	<div>
		<img src="/file/qna/1ca6475e-37be-47ca-8350-7881491a2408_KakaoTalk_20220827_202803205_09.jpg">
		<img src="/file/notice/KakaoTalk_20221020_144855641_01.jpg">
		<a href="/fileDown/qna?fileNum=2">QnaDown</a>
		<a href="/fileDown/notice?fileNum=2">NoticeDown</a>
	</div>
</body>
</html>