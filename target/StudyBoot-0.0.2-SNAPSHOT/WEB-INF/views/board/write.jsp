<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
</head>
<body>
	<h1>Board Write Page</h1>
	
	<section class="container" style="width: 60%;">
		<form action="/qna/write" method="post" enctype="multipart/form-data">
			<div class="input-group mb-3">
				<span class="input-group-text">Writer</span>
				<input type="text" class="form-control" id="writer" name="writer" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
			</div>

			<div class="input-group mb-3">
				<span class="input-group-text">Title</span>
				<input type="text" class="form-control" id="title" name="title" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
			</div>

			<div class="input-group mb-3">
				<span class="input-group-text">Contents</span>
				<textarea class="form-control" id="contents" name="contents"></textarea>
			</div>

			<div class="input-group mb-3">
				<input type="file" class="form-control" name="files" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
			</div>

			<div class="input-group mb-3">
				<input type="file" class="form-control" name="files" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
			</div>

			<button class="btn btn-primary" type="submit">WRITE</button>
		</form>
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