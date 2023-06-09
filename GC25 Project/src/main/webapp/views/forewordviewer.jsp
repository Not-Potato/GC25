<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setAttribute("pageName", "foreword");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>GC25 | Foreword Viewer</title>
	<link href="../resources/css/bootstrap.min.css" rel="stylesheet">
	<link href="../resources/css/custom.css" rel="stylesheet">
</head>
<body>
	<div id="wrap" class="w-100">
    	<jsp:include page="./common/header.jsp"></jsp:include>
    	<!-- header include 영역 -->

        <section id="" class="mt-5">
            <div class="inner m-auto">
                <h2 class="text-center p-4 bg-light border rounded-pill mb-4 m-auto text-primary w-800">상담 후기 상세 페이지</h2>
            </div>
        </section>
        
        <main id="container" class="main">
            <section id="content">
                <div class="inner m-auto">
                </div>
			</section>
		</main>
		
		<!-- footer include 영역 -->
        <jsp:include page="./common/footer.jsp"></jsp:include>
    </div>
	<script src="../../resources/js/bootstrap.min.js"></script>
</body>
</html>