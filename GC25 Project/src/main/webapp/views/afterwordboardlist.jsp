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
	<link href="<c:url value='/resources/css/bootstrap.min.css' />" rel="stylesheet">
	<link href="<c:url value='/resources/css/custom.css' />" rel="stylesheet">
</head>
<body>
	<div id="wrap" class="w-100">
		<jsp:include page="./common/header.jsp"></jsp:include>
        <!-- header include 영역 -->
        
        <section class="mt-5">
            <div class="inner m-auto">
                <h2 class="text-center p-4 bg-light border rounded-pill mb-4 m-auto text-primary w-800">수강 후기</h2>
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
								<li><a class="dropdown-item" href="${contextPath}/afterwordboard?searchType=최신순">최신순</a></li>
								<li><hr class="dropdown-divider"></li>
								<li><a class="dropdown-item" href="${contextPath}/afterwordboard?searchType=추천순">추천순</a></li>
								<li><hr class="dropdown-divider"></li>
								<li><a class="dropdown-item" href="${contextPath}/afterwordboard?searchType=댓글순">댓글순</a></li>
								<li><hr class="dropdown-divider"></li>
								<li><a class="dropdown-item" href="${contextPath}/afterwordboard?searchType=조회순">조회순</a></li>
							</ul>
						</div>
					</div>
					
					

			
					<div class="w-800">
						
						<!-- 비어 있는지 여부 검사하여 출력-->
						<c:choose>
						
						<c:when test="${ empty list }" >
							<div class="card my-3">
								<h5 class="card-header">등록된 글이 없습니다.</h5>
								<div class="card-body">
									<h5 class="card-title">등록된 글이 업습니다.</h5>
									<p class="card-text">등록된 글 업음.</p>
									<a href="#" class="btn btn-primary">버튼이 필요한가??</a>
								</div>
							</div>
						</c:when>
						
						<c:otherwise>
							<c:forEach  var="after" items="${ list }" varStatus="afterNum" >
								<div class="card my-3" onclick="location.href='${contextPath}/afterwordboard/afterwordboardviewer.do?boardNum=${ list[afterNum.index].boardNumber }'">
									<h5 class="card-header">${ list[afterNum.index].title }</h5>
									<div class="card-body">
										<h6 class="card-title">${ list[afterNum.index].contents }</h6>
										<p class="card-text">
											조회 수: ${ list[afterNum.index].views }
											댓글 수: ${ list[afterNum.index].commentCount }
											추천 수: ${ list[afterNum.index].recommend }
											작성 일자: ${ list[afterNum.index].writeDate }
										</p>
										<a href="#" class="btn btn-primary">버튼이 필요한가??</a>
									</div>
								</div>
							</c:forEach>
						</c:otherwise>
					</c:choose>
					
					</div>
					
					<nav>
						<ul class="pagination justify-content-center">
							<!-- "이전 페이지 버튼" disabled(비활성화) / 활성화 -->
							<c:choose>
								<c:when test="${ pageNum <= pagePerScreen }">
									<li class="page-item disabled"><a class="page-link" href="#">◀</a></li>
								</c:when>
								<c:otherwise>
									<li class="page-item"><a class="page-link" href="${contextPath}/afterwordboard?pageNum=${ startPage - 1 }&searchType=${ searchType }">◀</a></li>
								</c:otherwise>
							</c:choose>
							
							<!-- 1페이지 | 2페이지 | 3페이지 등.. 보여 주는 부분 -->
							<c:forEach var="page" begin="${ startPage }" end="${ endPage }" step="1">
								<c:choose>
									<c:when test="${ page == pageNum }">
										<li class="page-item active"><a class="page-link" href="#">${ page }</a></li>
									</c:when>
									<c:otherwise>
										<li class="page-item"><a class="page-link" href="${contextPath}/afterwordboard?pageNum=${ page }&searchType=${ searchType }">${ page }</a></li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							
							<!-- "다음 페이지 버튼" disabled(비활성화) / 활성화 -->
							<c:choose>
								<c:when test="${ endPage != totalPage }">
									<li class="page-item disabled"><a class="page-link" href="${contextPath}/afterwordboard?pageNum=${ endPage + 1 }&searchType=${ searchType }">▶</a></li>
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
