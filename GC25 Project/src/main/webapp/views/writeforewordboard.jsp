<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setAttribute("pageName", "foreword");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>GC25 | Write Foreword Page</title>
	<link href="../resources/css/bootstrap.min.css" rel="stylesheet">
	<link href="../resources/css/custom.css" rel="stylesheet">
</head>
<body>
	<div id="wrap" class="w-100">
    	<jsp:include page="./common/header.jsp"></jsp:include>
        <!-- header include 영역 -->

        <section id="" class="mt-5">
            <div class="inner m-auto">
                <h2 class="text-center p-4 bg-light border rounded-pill mb-4 m-auto text-primary w-800">상담 후기 작성</h2>
            </div>
        </section>

        <main id="container" class="main">
            <section id="content">
                <div class="inner m-auto">

                    <form class="row g-3 m-auto w-800">

                        <!-- 학원 이름 -->
                        <div class="col-md-6">
                            <input type="text" class="form-control" id="academyName" placeholder="학원 명">
                        </div>
                        
                        <!-- 과정 구분 -->
                        <div class="col-md-6">
                            <select class="form-select">
                                <option selected>과정 구분</option>
                                <option value="front">프론트엔드</option>
                                <option value="back">백엔드</option>
                                <option value="full">풀스텍</option>
                            </select>
                        </div>

                        <!-- 제목 -->
                        <div class="col-12">
                            <input type="text" class="form-control" id="title" placeholder="제목">
                        </div>

                        <!-- 내용 -->
                        <div class="col-12">
                            <textarea class="form-control" id="contents" rows="15" placeholder="내용"></textarea>
                        </div>

                        <!-- 버튼 -->
                        <div class="d-flex justify-content-center">
                            <button type="reset" class="btn btn-outline-primary me-2">취소</button>
                            <button type="submit" class="btn btn-primary ms-2">작성</button>
                        </div>
                    </form>
                </div>
            </section>
        </main>
        <!-- main -->

		<!-- footer include 영역 -->
        <jsp:include page="./common/footer.jsp"></jsp:include>
    </div>
	<script src="../../resources/js/bootstrap.min.js"></script>
</body>
</html>