<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}" />
<%
	// 스크립틀릿으로 처리해 본 메뉴 클래스 추가 
	String pgName = "";
	pgName = (String)request.getAttribute("pageName");
	
	String menu1 = "";
	String menu2 = "";
	String menu3 = "";
	
	switch(pgName) {
		case "foreword" : 
			menu1 = "on";
			break;
		case "afterword" :
			menu2 = "on";			
			break;
		case "map" :
			menu3 = "on";
			break;
	}

%>
<c:set var="memberNumber" value="${ sessionScope.memberNumber }" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>header</title>
	<link href="<c:url value='/resources/css/custom.css' />" rel="stylesheet">
	<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
</head>
<body>

	<header id="header">
		<div class="inner">
			<h1>
				<a href="${contextPath}/main"><img src="<c:url value='/resources/images/logo.svg' />" alt="GC25 Logo image" width="100"></a>
			</h1>
			<nav class="nav">
				<ul class="gnb">
					<li><a href="${contextPath}/foreword/board.do" class="<%= menu1 %>">상담 후기</a></li>
					<li><a href="${contextPath}/afterword/board.do" class="<%= menu2 %>">수강 후기</a></li>
					<li><a href="${contextPath}/academy/map.do" class="<%= menu3 %>">학원 찾기</a></li>
				</ul>
			</nav>
				
			<div class="search">
				<form id="searchForm">
					<input type="text" placeholder="검색어를 입력하세요" name="googleSearch">
					<button><i class="xi-search"></i></button>
				</form>
			</div>

			<ul class="top-menu">
<c:choose>
	<c:when test="${ empty memberNumber }" >
					<li>
						<a href="${contextPath}/mem/login.do">Login</a>
					</li>
					<li>
						<a href="${contextPath}/mem/signup.do">SignUp</a>
					</li>
	</c:when>
	<c:otherwise>
					<li>
						<a href="${contextPath}/mem/mypage.do">MyPage</a>
					</li>
					<li>
						<a href="${contextPath}/mem/logout.do">Logout</a>
					</li>
	</c:otherwise>
</c:choose>
			</ul>
		</div>
	</header>
  
	<script src="<c:url value='/resources/js/jquery.js' />"></script>

 	<script>
	  	document.getElementById("searchForm").addEventListener("submit", function(event) {
	    event.preventDefault(); // 폼 제출 이벤트 막기

	    var searchInput = document.getElementsByName("googleSearch")[0].value; // 검색어 가져오기
	    var encodedSearchInput = encodeURIComponent(searchInput); // 검색어 인코딩
	    var googleSearchURL = "https://www.google.com/search?q=site:gc25.com " + encodedSearchInput; // 구글 검색 URL 생성

	    window.location.href = googleSearchURL; // 구글 검색 페이지로 리다이렉트
	  });
	</script> 

</body>
</html>