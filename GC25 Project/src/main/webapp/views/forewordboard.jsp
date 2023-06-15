<%@page import="java.util.ArrayList"%>
<%@page import="com.gc25.service.ForewordBoardService"%>
<%@page import="com.gc25.dto.ForewordBoardDTO"%>
<%@page import="com.gc25.dao.ForewordBoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- jstl 사용 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 현재 페이지 정보 -->
<c:set var="contextPath"  value="${pageContext.request.contextPath}" />
<%
	request.setAttribute("pageName", "foreword");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>GC25 | Foreword</title>
	<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,200,0,0" />
	<link href="<c:url value='/resources/css/bootstrap.min.css' />" rel="stylesheet">
	<link href="<c:url value='/resources/css/custom.css' />" rel="stylesheet">
</head>
<body>
	<div id="wrap" class="w-100">
		<jsp:include page="./common/header.jsp"></jsp:include>
        <!-- header include 영역 -->
        
        <section class="mt-5">
            <div class="inner m-auto">
                <h2 class="text-center p-4 bg-light border rounded-pill mb-4 m-auto text-primary w-800">상담 후기</h2>
            </div>
        </section>
        
        <main id="container" class="main">
			<section id="content">
                <div class="w-800 m-auto">
					<div class="d-flex justify-content-between">
						<button type="button" class="btn btn-primary">글 작성</button>
						
						<div class="btn-group">
							<button type="button" class="btn btn-outline-primary dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
								${ searchType }
							</button>
							<ul class="dropdown-menu">
								<li><a class="dropdown-item" href="${contextPath}/forewordboard?searchType=최신순">최신순</a></li>
								<li><hr class="dropdown-divider"></li>
								<li><a class="dropdown-item" href="${contextPath}/forewordboard?searchType=추천순">추천순</a></li>
								<li><hr class="dropdown-divider"></li>
								<li><a class="dropdown-item" href="${contextPath}/forewordboard?searchType=댓글순">댓글순</a></li>
								<li><hr class="dropdown-divider"></li>
								<li><a class="dropdown-item" href="${contextPath}/forewordboard?searchType=조회순">조회순</a></li>
							</ul>
						</div>
					</div>
					
					

			
					<div class="w-800">
						
						<!-- 비어 있는지 여부 검사하여 출력-->
						<c:choose>
						
						<c:when test="${ empty list }" >
							<div class="card my-3">
								<h5 class="card-header">　</h5>
								<div class="card-body">
									<h5 class="card-title">　</h5>
									<p class="card-text">　</p>
									<a href="#" class="btn btn-primary">　</a>
								</div>
							</div>
						</c:when>
						
						<c:otherwise>
							<c:forEach  var="after" items="${ list }" varStatus="afterNum" >
								<div class="card my-3" onclick="location.href='${contextPath}/forewordboard/forewordviewer.do?boardNum=${ list[afterNum.index].boardNumber }'">
									<h5 class="card-header fw-bold">
										${ list[afterNum.index].title }
									</h5>
									<div class="card-body">
										<p class="card-text">
											${ list[afterNum.index].contents }
										</p>
										<p class="card-text fw-light text-end text-primary ">
											${ list[afterNum.index].academyName }
										</p>
									</div>
									<div class="card-footer text-muted d-flex justify-content-between">
										<div class="align-self-center d-flex">
											<span class="material-symbols-outlined me-2">visibility</span>
											${ list[afterNum.index].views }
										</div>
										<div class="align-self-center d-flex">
											<span class="material-symbols-outlined me-2">chat_bubble</span>
											 ${ list[afterNum.index].commentCount }
										</div>
										<div class="align-self-center d-flex">
											<span class="material-symbols-outlined me-2">thumb_up</span>
											${ list[afterNum.index].recommend }
										</div>
										<div class="align-self-center d-flex">
											<span class="material-symbols-outlined">edit</span>
											${ list[afterNum.index].writeDate }
										</div>
  									</div>
								</div>
							</c:forEach>
						</c:otherwise>
					</c:choose>
					
					</div>
					
					<nav>
						<ul class="pagination justify-content-center mt-5">
							<!-- "이전 페이지 버튼" disabled(비활성화) / 활성화 -->
							<c:choose>
								<c:when test="${ pageNum <= pagePerScreen }">
									<li class="page-item disabled"><a class="page-link" href="#">◀</a></li>
								</c:when>
								<c:otherwise>
									<li class="page-item"><a class="page-link" href="${contextPath}/forewordboard?pageNum=${ startPage - 1 }&searchType=${ searchType }">◀</a></li>
								</c:otherwise>
							</c:choose>
							
							<!-- 1페이지 | 2페이지 | 3페이지 등.. 보여 주는 부분 -->
							<c:forEach var="page" begin="${ startPage }" end="${ endPage }" step="1">
								<c:choose>
									<c:when test="${ page == pageNum }">
										<li class="page-item active"><a class="page-link" href="#">${ page }</a></li>
									</c:when>
									<c:otherwise>
										<li class="page-item"><a class="page-link" href="${contextPath}/forewordboard?pageNum=${ page }&searchType=${ searchType }">${ page }</a></li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							
							<!-- "다음 페이지 버튼" disabled(비활성화) / 활성화 -->
							<c:choose>
								<c:when test="${ endPage != totalPage }">
									<li class="page-item disabled"><a class="page-link" href="${contextPath}/forewordboard?pageNum=${ endPage + 1 }&searchType=${ searchType }">▶</a></li>
								</c:when>
								<c:otherwise>
									<li class="page-item disabled"><a class="page-link" href="#">▶</a></li>
								</c:otherwise>
							</c:choose>
						</ul>
					</nav>
				</div>
			</section>
			
		</main>
		
		<!-- footer include 영역 -->
        <jsp:include page="./common/footer.jsp"></jsp:include>
	</div>
	<script src="<c:url value='/resources/js/bootstrap.min.js' />"></script>
	<script src="<c:url value='/resources/js/bootstrap.bundle.min.js' />"></script>
	<script src="<c:url value='/resources/js/jquery.js' />"></script>
</body>
</html>