<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:import url="../temp/boot.jsp"></c:import>
<title>Insert title here</title>
</head>
<body>
	<table class="table table-hover">
		<thead>
			<tr>
				<th>Num</th>
				<th>Writer</th>
				<th>Title</th>
				<th>regDate</th>
				<th>Hit</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="vo">
				<tr>
					<td>${vo.num}</td>
					<td>${vo.writer}</td>
					<td><a href="/qna/detail?num=${vo.num}">${vo.title}</a></td>
					<td>${vo.regDate}</td>
					<td>${vo.hit}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<div>
		<a href="/qna/write" class="btn btn-primary">WRITE</a>
	</div>

	<script type="text/javascript">
		let result = '${param.result}';
		if(result != "") {
			if(result == 1) {
				alert("등록 성공");
			} else {
				alert("등록 실패");
			}
		}
	</script>
</body>
</html>