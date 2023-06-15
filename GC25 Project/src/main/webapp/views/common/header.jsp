<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}" />
<%
	String pgName = (String)request.getAttribute("pageName");
	String menu1 = "text-black";
	String menu2 = "text-black";
	String menu3 = "text-black";
	switch(pgName) {
		case "foreword" : 
			menu1 = "text-primary";
			break;
		case "afterword" :
			menu2 = "text-primary";			
			break;
		case "map" :
			menu3 = "text-primary";
			break;
	}
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>header</title>
	<link href="<c:url value='/resources/css/bootstrap.min.css' />" rel="stylesheet">
	<link href="<c:url value='/resources/css/custom.css' />" rel="stylesheet">

</head>
<body>

	<header class="header">
		<div class="p-4 bg-light w-100 mb-5">
			<div class="container d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
			
				<ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
					<li>
						<a class="navbar-brand" href="#">
                			<img src="<c:url value='../images/logo.svg' />" alt="GC25 Logo image" width="100">
						</a>
					</li>
			    	<li><a href="${contextPath}/forewordboard/forewordboard.do" class="nav-link px-2 <%= menu1 %>">상담 후기</a></li>
					<li><a href="${contextPath}/afterwordboard/afterwordboard.do" class="nav-link px-2 <%= menu2 %>">수강 후기</a></li>
					<li><a href="${contextPath}/academymap/listAcademy.do" class="nav-link px-2 <%= menu3 %>">학원 찾기</a></li>
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
  
	<script src="<c:url value='/resources/js/bootstrap.min.js' />"></script>
	<script src="<c:url value='/resources/js/jquery.js' />"></script>
</body>
</html>