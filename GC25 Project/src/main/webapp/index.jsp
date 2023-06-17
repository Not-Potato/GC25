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
	<link href="<c:url value='/resources/css/bootstrap.min.css' />" rel="stylesheet">
	<link href="<c:url value='/resources/css/custom.css' />" rel="stylesheet">
</head>
<body>
	<div id="wrap" class="w-100">
		<jsp:include page="./views/common/header.jsp"></jsp:include>
        <!-- header include 영역 -->

        <section id="visual" class="">
            <div class="inner m-auto">
                <!-- 배너 영역 -->
                <!-- <img src=""> -->
                <div class="p-4 p-md-5 mb-4 text-white rounded bg-dark">
                    <div class="col-md-6 px-0">
                        <h1 class="display-4 fst-italic">배너 들어갈 자리</h1>
                        <p class="lead my-3">
                        	배너 들어갈 자리,,.. 배너 들어갈 자리,,..,,, 배너 들어갈 자리,...,,
                        	<a href="${contextPath}/afterwordboard/afterwordboardlist.do" class="nav-link px-2">수강 후기</a>
                        </p>
                        <p class="lead mb-0"><a href="#" class="text-white fw-bold">ㅂㅐ너자리.</a></p>
                    </div>
                </div>
            </div>
        </section>
        <!-- visual -->

        <main id="container" class="main">
            <section id="content">
                <div class="inner m-auto d-flex">
                    <div class="w-50 pe-3">
                        <!-- 강의 카드 -->
                        <div class="card bg-light mt-3">
                            <!-- 강의 소개 -->
                            <div class="card-header bg-light">
                                <div class="row">
                                    <div class="text-center" style="height: 100px;">
                                    	<h2 style="line-height: 100px">상담 후기 게시판</h2>
                                    </div>
                                </div>
                            </div>
                            <!-- 강의 소개 종료 -->
                            
							<c:forEach var="fb" items="${ fList }" varStatus="vs" begin="0" end="4" step="1">
	                            <div class="card-body border-bottom">
	                            	<div class="row">
									<div class="col-10">
										${ fList[vs.index].views }
										${ fList[vs.index].writeDate }
									</div>
									<div class="col-2">
										${ fList[vs.index].recommend }
										${ fList[vs.index].commentCount }
									</div>
									</div>
									<div>
										<h4>${ fList[vs.index].title }</h4>
											${ fList[vs.index].contents }
									</div>
	                            </div>
                            </c:forEach>
                        </div>
                        <!-- 강의 카드 종료 -->
                    </div>

                    <div class="w-50 ps-3">
                        <!-- 강의 카드 -->
                        <div class="card bg-light mt-3">
                            <!-- 강의 소개 -->
                            <div class="card-header bg-light">
                                <div class="row">
                                    <div class="text-center" style="height: 100px;">
                                    	<h2 style="line-height: 100px">수강 후기 게시판</h2>
                                    </div>
                                </div>
                            </div>
                            <c:forEach var="fb" items="${ aList }" varStatus="vs" begin="0" end="4" step="1">
	                            <div class="card-body border-bottom">
	                            	<div class="row">
									<div class="col-10">
										${ aList[vs.index].views }
										${ aList[vs.index].writeDate }
									</div>
									<div class="col-2">
										${ aList[vs.index].recommend }
										${ aList[vs.index].commentCount }
									</div>
									</div>
									<div>
										<h4>${ aList[vs.index].title }</h4>
											${ aList[vs.index].contents }
									</div>
	                            </div>
                            </c:forEach>
                        <!-- 강의 카드 종료 -->

                    </div>
                </div>
            </section>
        </main>
        <!-- main -->

        <aside class="position-absolute end-0 top-50 me-5">
            <div class="card" style="width: 150px;">
                <div class="card-header">
                    리뷰 많은 순
                </div>
                <ul class="list-group list-group-flush">
                     <c:forEach var="ac" items="${ revList }" varStatus="vs" >
                        <li class="list-group-item">
							${ revList[vs.index].academyName }
							${ revList[vs.index].academyReviewCount }
						</li>
                   </c:forEach>
                </ul>
            </div>
            <div class="card mt-4" style="width: 150px;">
                <div class="card-header">
                    평점 좋은 순
                </div>
                <ul class="list-group list-group-flush">
                    <c:forEach var="ac" items="${ avgList }" varStatus="vs" >
                        <li class="list-group-item">
							${ avgList[vs.index].academyName }
							${ avgList[vs.index].academyAvgScore }
						</li>
                   </c:forEach>
                </ul>
            </div>
        </aside>

        <!-- footer include 영역 -->
        <jsp:include page="./views/common/footer.jsp"></jsp:include>
    </div>
    <script src="<c:url value='/resources/js/bootstrap.min.js' />"></script>
	<script src="<c:url value='/resources/js/jquery.js' />"></script>
	<script src="<c:url value='/resources/js/popper.js' />"></script>
</body>
</html>