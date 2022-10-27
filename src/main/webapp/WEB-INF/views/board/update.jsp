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
<script defer type="text/javascript" src="/js/fileManager.js"></script>
</head>
<body>
	<h1>Board Write Page</h1>
	
	<section class="container" style="width: 60%;">
		<form action="update" method="post" enctype="multipart/form-data">
		<input type="hidden" name="num" value="${QnaVO.num}">
			<div class="input-group mb-3">
				<span class="input-group-text">Writer</span>
				<input readonly type="text" class="form-control" id="writer" name="writer" value="${QnaVO.writer}" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
			</div>

			<div class="input-group mb-3">
				<span class="input-group-text">Title</span>
				<input type="text" class="form-control" id="title" name="title" value="${QnaVO.title}" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
			</div>

			<div class="input-group mb-3">
				<span class="input-group-text">Contents</span>
				<textarea class="form-control" id="contents" name="contents">${QnaVO.contents}</textarea>
			</div>
		
		
			<div class="mb-3" id="add">
			<c:forEach items="${QnaVO.qnaFileVOs}" var="file">
			<%-- 	<img src="/file/qna/${file.fileName}">	 --%>
				<p>여기 파일있어요 ${file.oriName}
				<button type="button" class="deleteFile" data-fileNum="${file.fileNum}">DeleteFile</button>
				</p>
			</c:forEach>
			</div>
			
			<div class="mb-3" id="btns">
				<button type="button" id="addbtn">FileAdd</button>
			</div>

			<button class="btn btn-primary" type="submit">UPDATE</button>
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
	/* 	$('#contents').summernote('code','${QnaVO.contents}') */
	</script>
</body>
</html>