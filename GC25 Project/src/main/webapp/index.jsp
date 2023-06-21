<%@page import="java.util.ArrayList"%>
<%@page import="com.gc25.dao.AfterwordBoardDAO"%>
<%@page import="com.gc25.dto.AfterwordBoardDTO"%>
<%@page import="com.gc25.dao.ForewordBoardDAO"%>
<%@page import="com.gc25.dto.ForewordBoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}" />
<%
	request.setAttribute("pageName", "index");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>GC25 | Main Page</title>
	<!-- 커스텀.css / reset.css / 파비콘 / x-icon / 비주얼 스와이프 플러긘 -->
	<link href="<c:url value='/resources/css/custom.css' />" rel="stylesheet">
	<link href="<c:url value='/resources/css/reset.css' />" rel="stylesheet">
	<link rel="shortcut icon" type="image/x-icon" href="/resources/images/mini_logo.png">
	<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.css" />
</head>
<body>
	<div id="wrap">
		<jsp:include page="./views/common/header.jsp"></jsp:include>
        <!-- header include 영역 -->

        <section id="visual">
            <div class="inner">
                <!-- 배너 영역 -->
				<div class="big-banner swiper mySwiper">
					<div class="swiper-wrapper">
						<div class="swiper-slide"><img src="./resources/images/main-banner-2.png"></img></div>
						<div class="swiper-slide"><img src="./resources/images/main-banner-3.png"></img></div>
						<div class="swiper-slide"><img src="./resources/images/main-banner-1.png"></img></div>
					</div>
					<div class="swiper-button-next"></div>
					<div class="swiper-button-prev"></div>
					<div class="swiper-pagination"></div>
				</div>
			</div>
        </section>
        <!-- visual -->

        <main id="container" class="main">
            <section id="content">
                <div class="inner">
                	
                	<!-- 상담 후기 게시판 최신 글 다섯 개 -->
                    <div class="board foreword">
                    	<div class="board-title">
                    		<h3 onclick="location.href='/foreword/board.do'" >상담 후기</h3>
                    	</div>
                        
						<ul>
<c:forEach var="fb" items="${ fList }" varStatus="vs" begin="0" end="4" step="1">
							<li class="list-click" onclick="location.href='${contextPath}/foreword/viewer.do?boardNum=${ fList[vs.index].boardNumber }'">
								<div>
									<em>${ fList[vs.index].writeDate }</em>
									<strong>${ fList[vs.index].title }</strong>
									<p>${ fList[vs.index].contents }</p>
								</div>
								<div class="post-stats">
									<ul>
										<li>
											<i class="xi-eye"></i>
											${ fList[vs.index].views }
										</li>
										<li>
											<i class="xi-thumbs-up"></i>
											${ fList[vs.index].recommend }
										</li>
										<li>
											<i class="xi-comment"></i>
											${ fList[vs.index].commentCount }
										</li>
									</ul>
								</div>
							</li>
</c:forEach>
						</ul>
                    </div> <!-- end of .board .foreword -->
                    
                	<!-- 수강 후기 게시판 최신 글 다섯 개 -->
                    <div class="board afterword">
                    	<div class="board-title">
                    		<h3 onclick="location.href='/afterword/board.do'">수강 후기</h3>
                    	</div>
                        
						<ul>
<c:forEach var="ab" items="${ aList }" varStatus="vs" begin="0" end="4" step="1">
							<li class="list-click" onclick="location.href='${contextPath}/afterword/viewer.do?boardNum=${ aList[vs.index].boardNumber }'">
								<div>
									<em>${ aList[vs.index].writeDate }</em>
									<strong>${ aList[vs.index].title }</strong>
									<p>${ aList[vs.index].contents }</p>
								</div>
								<div class="post-stats">
									<ul>
										<li>
											<i class="xi-eye"></i>
											${ aList[vs.index].views }
										</li>
										<li>
											<i class="xi-thumbs-up"></i>
											${ aList[vs.index].recommend }
										</li>
										<li>
											<i class="xi-comment"></i>
											${ aList[vs.index].commentCount }
										</li>
									</ul>
								</div>
							</li>
</c:forEach>
						</ul>
                    </div> <!-- end of .board .afterword -->
                </div>
            </section>
        </main>
        <!-- main -->

        <aside>
			<ul class="rank review-ranking">
             	<li>리뷰 TOP</li>
<c:forEach var="ac" items="${ revList }" varStatus="vs" >
             	<li class="aside-click" onclick="location.href='${contextPath}/academy/map.do?searchValue=${ revList[vs.index].academyName }'">
             		<strong>
						${ revList[vs.index].academyName }
					</strong>
					${ revList[vs.index].academyReviewCount }
				</li>
</c:forEach>
             </ul>
         
             <ul class="rank score-ranking">
              <li>평점 TOP</li>
<c:forEach var="ac" items="${ avgList }" varStatus="vs" >
                 <li class="aside-click" onclick="location.href='${contextPath}/academy/map.do?searchValue=${ avgList[vs.index].academyName }'">
                 	<strong>
						${ avgList[vs.index].academyName }
					</strong>
					${ avgList[vs.index].academyAvgScore }
				</li>
</c:forEach>
			</ul>
        </aside>

        <!-- footer include 영역 -->
        <jsp:include page="./views/common/footer.jsp"></jsp:include>
    </div>
	<script src="<c:url value='/resources/js/jquery.js' />"></script>
    <script src="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.js"></script>
	<script>
		var swiper = new Swiper(".mySwiper", {
			pagination: {
				el: ".swiper-pagination",
				type: "progressbar",
			},
			navigation: {
				nextEl: ".swiper-button-next",
				prevEl: ".swiper-button-prev",
			},
		});
	</script>
</body>
</html>