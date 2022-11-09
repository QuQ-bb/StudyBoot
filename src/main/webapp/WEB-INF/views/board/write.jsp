<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:import url="../temp/boot.jsp"></c:import>
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css" rel="stylesheet">
<style>
	.popover {
		display: none;
	}
</style>
<title>Insert title here</title>
<!-- <script defer type="text/javascript" src="/js/fileManager.js"></script> -->
</head>
<body>
	<h1>Board Write Page</h1>
	
	<section class="container" style="width: 60%;">
		<form:form modelAttribute="qnaVO" method="post" enctype="multipart/form-data">
		<sec:csrfInput/>
		<%-- <form action="/qna/write" method="post" enctype="multipart/form-data"> --%>
			<div class="input-group mb-3">
				<span class="input-group-text">Writer</span>
				<form:input path="writer" cssClass="form-control" id="writer"/>
				<form:errors path="writer"></form:errors>
				<!-- <input type="text" class="form-control" id="writer" name="writer" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default"> -->
			</div>

			<div class="input-group mb-3">
				<span class="input-group-text">Title</span>
				<form:input path="title" cssClass="form-control" id="title"/>
				<form:errors path="title"></form:errors>
				<!-- <input type="text" class="form-control" id="title" name="title" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default"> -->
			</div>

			<div class="input-group mb-3">
				<span class="input-group-text">Contents</span>
				<form:textarea path="contents" cssClass="form-control" id="contents"/>
				<form:errors path="contents"></form:errors>
				<!-- <textarea class="form-control" id="contents" name="contents"></textarea> -->
			</div>
	

			<div class="mb-3" id="add">
			
			</div>
			<div class="mb-3" id="btns">
				<button type="button" id="addbtn">FileAdd</button>
			</div>
<!-- 			<div class="input-group mb-3">
				<input type="file" class="form-control" name="files" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
			</div>

			<div class="input-group mb-3">
				<input type="file" class="form-control" name="files" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
			</div> -->

			<button class="btn btn-primary" type="submit">WRITE</button>
		<%-- </form> --%>
		</form:form>
	</section>
	<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
	<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script>
	<script>
		$(document).ready(function () {
	        $('#contents').summernote({
				height: 250
			});
		});
	</script>
</body>
</html>