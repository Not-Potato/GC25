<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}" />
<%
	// 스크립틀릿으로 처리해 본 메뉴 클래스 추가 
	String pgName = "";
	pgName = (String)request.getAttribute("pageName");
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
	
	/* 	
	int memberNumber = 0;
	memberNumber = session.getAttribute("memberNumber") == null ? 0: (Integer)session.getAttribute("memberNumber");
	System.out.println("세션에 저장된 회원번호 [" + memberNumber + "]");
	
	// 세션에 저장된 회원 번호가 없으면 (= 로그인 정보가 없으면) --> guest = true;
	// 있으면 --> guest = false;
	boolean guest = true;
	guest = (memberNumber == 0) ? true :  false;
	System.out.println("접속자는 손님(비회원)인가요? [" + guest + "]"); 
	// JSTL로 해결 완료
	*/
%>
<c:set var="memberNumber" value="${sessionScope.memberNumber}" />
<c:set var="guest" value="${empty memberNumber}" />
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
						<a class="navbar-brand" href="${contextPath}/">
                			<img src="<c:url value='/resources/images/logo.svg' />" alt="GC25 Logo image" width="100">
						</a>
					</li>
			    	<li><a href="${contextPath}/foreword/board.do" class="nav-link px-2 <%= menu1 %>">상담 후기</a></li>
					<li><a href="${contextPath}/afterword/board.do" class="nav-link px-2 <%= menu2 %>">수강 후기</a></li>
					<li><a href="${contextPath}/academy/map.do" class="nav-link px-2 <%= menu3 %>">학원 찾기</a></li>
				</ul>
			
				<form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3">
					<input type="search" class="form-control form-control-dark" placeholder="검색어를 입력하세요" aria-label="Search">
				</form>
			
				<div class="text-end">
				
				<!-- 현재 불러온 회원번호(from 세션)와 guest 여부 출력 -->
				<%-- 		
					<c:out value="${memberNumber}" />
					<c:out value="${guest}" /> 
				--%>
				
				<!-- 로그인 정보 없을 때와 있을 때 우측 상단 btn -->
				<c:choose>
					<c:when test="${guest}" >
						<button type="button" class="btn btn-outline-primary me-2" onclick="location.href='${contextPath}/mem/login.do'">Login</button>
						<button type="button" class="btn btn-primary" onclick="location.href='${contextPath}/mem/signup.do'">Sign-up</button>
					</c:when>
					<c:otherwise>
						<button type="button" class="btn btn-outline-primary me-2" onclick="location.href='${contextPath}/mem/mypage.do'">My Page</button>
						<button type="button" class="btn btn-primary" onclick="location.href='${contextPath}/mem/logout.do'">Logout</button>
					</c:otherwise>
				</c:choose>
				</div>
			</div>
		</div>
	</header>
  
	<script src="<c:url value='/resources/js/bootstrap.min.js' />"></script>
	<script src="<c:url value='/resources/js/jquery.js' />"></script>
</body>
</html>