<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../temp/boot.jsp"></c:import>
<script defer src="/js/util.js"></script>
<script defer type="text/javascript" src="/js/memberAdd.js"></script>
</head>
<body>
	<div class="container-fluid">
    	<div class="row mt-5 justify-content-center">
    		<div class="col-lg-6">    		
    			<h1 class="text-center">Join page</h1>
    		</div>
    	</div>
    
    	<div class="row justify-content-center mt-5">
    		<div class="col-lg-6">
        	<form action="add" id="joinForm" method="post">
			  <div class="row mb-3">
			    <label for="inputUserName" class="col-sm-2 col-form-label">ID</label>
			    <div class="col-sm-10">
			      <input type="text" name="id" class="form-control" id="id" placeholder="ID 입력">
				  <div id="check_id" ></div>
			    </div>
			  </div>
			  <!-- <div>
				<button id="idCheck">idCheck</button>
			  </div> -->
			  <div class="row mb-3">
			    <label for="inputPassword" class="col-sm-2 col-form-label">Password</label>
			    <div class="col-sm-10">
			      <input type="password" name="pw" class="form-control" id="pw" placeholder="Password 입력">
				  <div id="check_pw"></div>
			    </div>
			  </div>

			  <div class="row mb-3">
			    <label for="pw2" class="col-sm-2 col-form-label">Password</label>
			    <div class="col-sm-10">
			      <input type="password" name="pw2" class="form-control" id="pw2" placeholder="Password 입력">
				  <div id="check_pw2"></div>
			    </div>
			  </div>


			  <div class="row mb-3">
			    <label for="inputName" class="col-sm-2 col-form-label">Name</label>
			    <div class="col-sm-10">
			      <input type="text" name="name" class="form-control" id="name" placeholder="이름 입력">
				  <div id="check_name"></div>
			    </div>
			  </div>
			  
			  <div class="row mb-3">
			    <label for="inputEmail" class="col-sm-2 col-form-label">Email</label>
			    <div class="col-sm-10">
			      <input type="email" required name="email" class="form-control" id="email" placeholder="Email 입력">
				  <div id="check_email"></div>
			    </div>
			  </div>
			  
			  
			  <div class="row justify-content-end">
			  	<div >
			  		<button type="button" id="joinButton" class="btn btn-primary mb-3">Sign in</button>
			  	</div>
			  </div>
			</form>
        	</div>
        
        </div>
    </div>

	<!--약관 test-->
	<div class="container-fluid">

		<div class="row justify-content-center mt-5">
			<div>
				ALL<input type="checkbox" id="all">
			</div>
	
			<div>
				동의1 <input type="checkbox" class="check">
				<div>
					약관1
				</div>
			</div>
			<div>
				동의2 <input type="checkbox" class="check">
				<div>
					약관2
				</div>
			</div>
			<div>
				동의3 <input type="checkbox" class="check">
				<div>
					약관3
				</div>
			</div>
	
		</div>
	</div>

</body>
</html>