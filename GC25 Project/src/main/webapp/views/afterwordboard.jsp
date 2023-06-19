<%@page import="java.util.ArrayList"%>
<%@page import="com.gc25.service.AfterwordBoardService"%>
<%@page import="com.gc25.dto.AfterwordBoardDTO"%>
<%@page import="com.gc25.dao.AfterwordBoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- jstl 사용 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 현재 페이지 정보 -->
<c:set var="contextPath"  value="${pageContext.request.contextPath}" />
<%
	request.setAttribute("pageName", "afterword");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>GC25 | Afterword</title>
	<!-- 커스텀.css / reset.css / 파비콘 / x-icon -->
	<link href="<c:url value='/resources/css/custom.css' />" rel="stylesheet">
	<link href="<c:url value='/resources/css/reset.css' />" rel="stylesheet">
	<link rel="shortcut icon" type="image/x-icon" href="/resources/images/mini_logo.png">
	<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
</head>
<body>
	<div id="wrap">
		<jsp:include page="./common/header.jsp"></jsp:include>
        <!-- header include 영역 -->
		<div class="board-page afterword-page">
			<div class="inner">        
				<div class="title">
				    <h2>수강 후기</h2>
				    <p>설명설명설명</p>
				</div>
	        
				<div class="top-menu">
					<button type="button" onclick="location.href='${contextPath}/afterword/write.do'" class="">Write</button>
				
					<div>
						<button type="button" class="" data-bs-toggle="dropdown" aria-expanded="false">
							${ searchType }
						</button>
						<ul>
							<li><a class="" href="${contextPath}/afterword/board?searchType=최신순">최신순</a></li>
							<li><a class="" href="${contextPath}/afterword/board?searchType=추천순">추천순</a></li>
							<li><a class="" href="${contextPath}/afterword/board?searchType=댓글순">댓글순</a></li>
							<li><a class="" href="${contextPath}/afterword/board?searchType=조회순">조회순</a></li>
						</ul>
					</div>
				</div>
						
<!-- 비어 있는지 여부 검사하여 출력-->
<c:choose>
						
	<c:when test="${ empty list }" >
				<div>
					<strong class="">
						<i class="xi-maker"></i>
						아무개학원 아무개점
					</strong>
				</div>
				
				<h3>게시글 제목</h3>
				<ul>
					<li>
						<i class="xi-eye"></i>
						조회 수
					</li>
					<li>
						<i class="xi-comment"></i>
						댓글 수
					</li>
					<li>
						<i class="xi-thumbs-up"></i>
						추천 수
					</li>
					<li>
						<i class="xi-pencil-point"></i>
						작성일
					</li>
				</ul>
	</c:when>
	
	<c:otherwise>
				<ul class="board-list afterword">	
		<c:forEach  var="after" items="${ list }" varStatus="afterNum" >
					<li class="afterword-list" onclick="location.href='${contextPath}/afterword/viewer.do?boardNum=${ list[afterNum.index].boardNumber }'">
						<div>
							<strong class="">
								<i class="xi-maker"></i>
								${ list[afterNum.index].academyName }
							</strong>
						</div>
						
						<h3>${ list[afterNum.index].title }</h3>
						<ul>
							<li>
								<i class="xi-eye"></i>
								${ list[afterNum.index].views }
							</li>
							<li>
								<i class="xi-comment"></i>
								${ list[afterNum.index].commentCount }
							</li>
							<li>
								<i class="xi-thumbs-up"></i>
								${ list[afterNum.index].recommend }
							</li>
							<li>
								<i class="xi-pencil-point"></i>
								${ list[afterNum.index].writeDate }
							</li>
						</ul>
					</li>
		</c:forEach>
				</ul>
	</c:otherwise>
</c:choose>
				<nav>
					<ul class="pagination justify-content-center mt-5">
					<!-- "이전 페이지 버튼" disabled(비활성화) / 활성화 -->
<c:choose>
	<c:when test="${ pageNum <= pagePerScreen }">
						<li class="page-item"><a class="page-link disabled">◀</a></li>
	</c:when>
	<c:otherwise>
						<li class="page-item"><a class="page-link usable" href="${contextPath}/afterword/board.do?pageNum=${ startPage - 1 }&searchType=${ searchType }">◀</a></li>
	</c:otherwise>
</c:choose>
							
					<!-- 1페이지 | 2페이지 | 3페이지 등.. 보여 주는 부분 -->
<c:forEach var="page" begin="${ startPage }" end="${ endPage }" step="1">
	<c:choose>
		<c:when test="${ page == pageNum }">
						<li class="page-item"><a class="page-link active" href="#">${ page }</a></li>
		</c:when>
		<c:otherwise>
						<li class="page-item"><a class="page-link" href="${contextPath}/afterword/board.do?pageNum=${ page }&searchType=${ searchType }">${ page }</a></li>
		</c:otherwise>
	</c:choose>
</c:forEach>
							
				<!-- "다음 페이지 버튼" disabled(비활성화) / 활성화 -->
<c:choose>
	<c:when test="${ endPage != totalPage }">
						<li class="page-item"><a class="page-link usable" href="${contextPath}/afterword/board.do?pageNum=${ endPage + 1 }&searchType=${ searchType }">▶</a></li>
	</c:when>
	<c:otherwise>
						<li class="page-item"><a class="page-link disabled">▶</a></li>
	</c:otherwise>
</c:choose>
					</ul>
				</nav>
			</div>
		</div>
	</div>
		
	<!-- footer include 영역 -->
    <jsp:include page="./common/footer.jsp"></jsp:include>
	<script src="<c:url value='/resources/js/jquery.js' />"></script>
</body>
</html>
