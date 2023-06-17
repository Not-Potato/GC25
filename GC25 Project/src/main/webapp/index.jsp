<%@page import="com.gc25.dao.AfterwordBoardDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gc25.dto.AfterwordBoardDTO"%>
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
	<link rel="shortcut icon" type="image/x-icon" href="/resources/images/mini_logo.png">
	
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
                        	<a href="${contextPath}/afterword/board" class="nav-link px-2">수강 후기</a>
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
                            <!-- 강의 평가 -->
                            <div class="card-body border-bottom">
                                <h5 class="card-title">
                                    게시글 제목<br><small>작성자</small>
                                </h5>
                                <p class="card-text">
                                	글 내용 미리보기 글 내용 미리보기 글 내용 미리보기 글 내용 미리보기 글 내용 미리보기 글 내용 미리보기 </p>
                            </div>
                            <!-- 강의 평가 종료 -->
                            <!-- 강의 평가 -->
                            <div class="card-body border-bottom">
                                <h5 class="card-title">
                                    게시글 제목<br><small>작성자</small>
                                </h5>
                                <p class="card-text">글 내용 미리보기 글 내용 미리보기 글 내용 미리보기 글 내용 미리보기 글 내용 미리보기 글 내용 미리보기 </p>
                            </div>
                            <!-- 강의 평가 종료 -->
                            <!-- 강의 평가 -->
                            <div class="card-body">
                                <h5 class="card-title">
                                    게시글 제목<br><small>작성자</small>
                                </h5>
                                <p class="card-text">글 내용 미리보기 글 내용 미리보기 글 내용 미리보기 글 내용 미리보기 글 내용 미리보기 글 내용 미리보기 </p>
                            </div>
                            <!-- 강의 평가 종료 -->
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
                            <!-- 강의 소개 종료 -->
                            <!-- 강의 평가 -->
                            <div class="card-body border-bottom">
                                <h5 class="card-title">
                                    게시글 제목<br><small>작성자</small>
                                </h5>
                                <p class="card-text">글 내용 미리보기 글 내용 미리보기 글 내용 미리보기 글 내용 미리보기 글 내용 미리보기 글 내용 미리보기 </p>
                            </div>
                            <!-- 강의 평가 종료 -->
                            <!-- 강의 평가 -->
                            <div class="card-body border-bottom">
                                <h5 class="card-title">
                                    게시글 제목<br><small>작성자</small>
                                </h5>
                                <p class="card-text">글 내용 미리보기 글 내용 미리보기 글 내용 미리보기 글 내용 미리보기 글 내용 미리보기 글 내용 미리보기 </p>
                            </div>
                            <!-- 강의 평가 종료 -->
                            <!-- 강의 평가 -->
                            <div class="card-body">
                                <h5 class="card-title">
                                    게시글 제목<br><small>작성자</small>
                                </h5>
                                <p class="card-text">글 내용 미리보기 글 내용 미리보기 글 내용 미리보기 글 내용 미리보기 글 내용 미리보기 글 내용 미리보기 </p>
                            </div>
                            <!-- 강의 평가 종료 -->
                        </div>
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
                    <li class="list-group-item">학원1</li>
                    <li class="list-group-item">학원2</li>
                    <li class="list-group-item">학원3</li>
                </ul>
            </div>
            <div class="card mt-4" style="width: 150px;">
                <div class="card-header">
                    리뷰 많은 순
                </div>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item">학원1</li>
                    <li class="list-group-item">학원2</li>
                    <li class="list-group-item">학원3</li>
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