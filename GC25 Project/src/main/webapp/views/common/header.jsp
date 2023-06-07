<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String pgName = (String)request.getAttribute("pageName");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>header</title>
<link href="../../css/bootstrap.min.css" rel="stylesheet">

</head>
<body>

	<header class="p-3 bg-light text-white">
		<div class="container">
			<div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
			
				<ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
					<li>
						<a class="navbar-brand" href="#">
                			<img src="./../images/logo.svg" alt="GC25 Logo image" width="100">
						</a>
					</li>
<%
	if (pgName == "one") {
%>
			    	<li><a href="#" id="one" class="nav-link px-2 text-primary">상담 후기</a></li>
					<li><a href="#" id="two" class="nav-link px-2 text-black">수강 후기</a></li>
					<li><a href="#" id="thr" class="nav-link px-2 text-black">학원 찾기</a></li>
<% } else if (pgName == "two") {%>
					<li><a href="#" id="one" class="nav-link px-2 text-black">상담 후기</a></li>
					<li><a href="#" id="two" class="nav-link px-2 text-primary">수강 후기</a></li>
					<li><a href="#" id="thr" class="nav-link px-2 text-black">학원 찾기</a></li>
<% } else if (pgName == "thr") {%>
					<li><a href="#" id="one" class="nav-link px-2 text-black">상담 후기</a></li>
					<li><a href="#" id="two" class="nav-link px-2 text-black">수강 후기</a></li>
					<li><a href="#" id="thr" class="nav-link px-2 text-primary">학원 찾기</a></li>
<% } else { %>
					<li><a href="#" id="one" class="nav-link px-2 text-black">상담 후기</a></li>
					<li><a href="#" id="two" class="nav-link px-2 text-black">수강 후기</a></li>
					<li><a href="#" id="thr" class="nav-link px-2 text-black">학원 찾기</a></li>
<% } %>
				</ul>
			
				<form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3">
					<input type="search" class="form-control form-control-dark" placeholder="검색어를 입력하세요" aria-label="Search">
				</form>
			
				<div class="text-end">
				<button type="button" class="btn btn-outline-primary me-2">Login</button>
				<button type="button" class="btn btn-primary">Sign-up</button>
				</div>
			</div>
		</div>
	</header>
  
	<script src="../../js/bootstrap.min.js"></script>
</body>
</html>