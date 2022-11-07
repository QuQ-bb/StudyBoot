<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
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
    		<form:form modelAttribute="memberVO" method="post" >
        <%-- 	<form action="add" id="joinForm" method="post"> --%>
			  <div class="row mb-3">
			    <label for="inputUserName" class="col-sm-2 col-form-label">ID</label>
			    <div class="col-sm-10">
			      <form:input path="id" cssClass="form-control" id="id"/>
					 
			     <!--  <input type="text" name="id" class="form-control" id="id" placeholder="ID 입력"> -->
					<div id="check_id" >
					<form:errors path="id"></form:errors> <!--백엔드에서 검증이 끝나야지 결과값을 출력해줌  -->
					</div>
			    </div>
			  </div>
			  <!-- <div>
				<button id="idCheck">idCheck</button>
			  </div> -->
			  <div class="row mb-3">
			    <label for="inputPassword" class="col-sm-2 col-form-label">Password</label>
			    <div class="col-sm-10">
			    	<form:password path="pw" cssClass="form-control" id="pw" />
			    	<form:errors path="pw"></form:errors>
			     <!--  <input type="password" name="pw" class="form-control" id="pw" placeholder="Password 입력"> -->
				  <div id="check_pw"></div>
			    </div>
			  </div>

			  <div class="row mb-3">
			    <label for="pw2" class="col-sm-2 col-form-label">Password</label>
			    <div class="col-sm-10">
			    	<form:password path="pwCheck" cssClass="form-control" id="pw2"/> <!-- 서버에서 검증을 해주어야하기때문에 나중에getter를 만들어줘야함 -->
			    	<form:errors path="pwCheck" ></form:errors>
			      <!-- <input type="password" name="pw2" class="form-control" id="pw2" placeholder="Password 입력"> -->
				  <div id="check_pw2"></div>
			    </div>
			  </div>


			  <div class="row mb-3">
			    <label for="inputName" class="col-sm-2 col-form-label">Name</label>
			    <div class="col-sm-10">
			    	<form:input path="name" cssClass="form-control" id="name"/>
			    	<form:errors path="name" ></form:errors>
			   <!--    <input type="text" name="name" class="form-control" id="name" placeholder="이름 입력"> -->
				  <div id="check_name">
				  	${name}
				  </div>
			    </div>
			  </div>
			  
			  <div class="row mb-3">
			    <label for="inputEmail" class="col-sm-2 col-form-label">Email</label>
			    <div class="col-sm-10">
			    	<form:input path="email" cssClass="form-control" id="email"/>
			    	<form:errors path="email" ></form:errors>
			     <!--  <input type="email" required name="email" class="form-control" id="email" placeholder="Email 입력"> -->
				  <div id="check_email"></div>
			    </div>
			  </div>
			  
			  <div class="row mb-3">
			    <label for="inputEmail" class="col-sm-2 col-form-label">Age</label>
			    <div class="col-sm-10">
			    	<form:input path="age" cssClass="form-control" id="age"/>
			    	<form:errors path="age" ></form:errors>
			    </div>
			  </div>
			  <div class="row mb-3">
			    <label for="inputEmail" class="col-sm-2 col-form-label">Birth</label>
			    <div class="col-sm-10">
			    	<form:input path="birth" cssClass="form-control" id="birth"/>
			    	<form:errors path="birth" ></form:errors>
			    </div>
			  </div>
			  
			  <div class="row justify-content-end">
			  	<div >
			  		<button type="submit" id="joinButton" class="btn btn-primary mb-3">Sign in</button>
			  	</div>
			  </div>
		<%-- 	</form> --%>
    		</form:form> <!--action 생략하면 현재 url이 action으로 지정이 된답니다  -->
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