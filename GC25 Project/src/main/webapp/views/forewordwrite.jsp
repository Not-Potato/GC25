<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- jstl 사용 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 현재 페이지 정보 -->
<c:set var="contextPath"  value="${pageContext.request.contextPath}" />
<%
	request.setAttribute("pageName", "foreword");
%>

<!-- 회원등급에 따른 상세페이지 접근 구분 -->
<%
     // 로그인 여부 확인
    Object memberNumberObj = session.getAttribute("memberNumber");
	
	if (memberNumberObj == null) {
		// 로그인이 되어있지 않은 경우, 로그인 페이지로 리다이렉트
        out.println("<script>alert('로그인 해주세요.'); window.location.href='http://localhost:8080/views/login.jsp'; </script>");
	}
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>GC25 | 상담후기 글쓰기</title>
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

		<div class="board-page foreword-page write-page foreword-write-page">
			<div class="inner">
				<div class="title">
					<h2>상담 후기 작성</h2>
					<p>나의 경험을 다른 이용자와 공유해보세요!</p>				
				</div>
				
				<form class="foreword-form" name="posting_form" onsubmit="return posting();" method="POST" action="${contextPath}/foreword/upload.do">
					<!-- 첫 번째 줄 (학원 명 / 과정 구분) -->
					<div class="first-line">
						<div>
							<input type="text" id="academy-name" class="box" placeholder="학원 명" autocomplete="off" name="academyName">
							<input type="hidden" name="academyNum" id="academyNum" value="">
							<div class="search-list">
								<ul class="d-none" id="none">
									<!-- 자동 완성 검색 결과 div 들어갈 곳 -->
								</ul>
							</div>
						</div>
							<div class="course-dropdown">

								<select class="box form-select" id="course" name="course">
									<!-- 선택할 수 없는 옵션의 value를 null로 지정 -->
									<option disabled selected value="">----- 과정 구분 -----</option>
									<option value="프론트엔드">프론트엔드</option>
									<option value="백엔드">백엔드</option>
									<option value="풀스택">풀스택</option>
								</select>
							</div>
					</div>
					
					<!-- 제목 -->
					<div class="second-line">
						<input type="text" class="input-title box" id="title" placeholder="제목" 
						maxlength="30" oninput="limitMaxLength(this);" name="title">
					</div>
					
					<!-- 내용 -->
					<div class="third-line">
						<textarea id="contents" class="input-contents" rows="15" placeholder="내용" 
						maxlength="1500" oninput="limitMaxLength(this);" name="contents"></textarea>
					</div>
					
					<!-- 버튼 -->
					<div class="btn-group">
						<button type="reset" class="box">취소</button>
						<button type="submit" class="box">작성</button>
					</div>
				</form>
			</div>
		</div>

		<!-- footer include 영역 -->
        <jsp:include page="./common/footer.jsp"></jsp:include>
    </div>

	<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
	<script src="../resources/js/academynameinput.js"></script>
	<script>
		let num = document.getElementById("academyNum");
		
		function posting() {	
		    // 사용자 입력 값 받아오기
		    let academyName = $("#academy-name").val();
		    let course = $("#course").val();
		    let title = $("#title").val();
		    let contents = $("#contents").val();
		    
		    let check1 = isEmpty(academyName);
		    let check2 = isEmpty(course);
		    let check3 = isEmpty(title);
		    let check4 = isEmpty(contents);
      
 		    // 만약 하나라도 true면 --> 입력되지 않은 값이 존재하는 경우
			if (check1 || check2 || check3 || check4) {		
		        alert('입력되지 않은 값이 존재합니다!');
		        return false;
		    } 
	
		    //TODO: 학원명 - DB에 없는 데이터 입력할 수 없도록 제한
		    $.ajax({
		        url: "/academy/searchjustone",
		        method: "get",
		        data: "academyName="+academyName,
	
		        // DB에 검색한 결과 == 1 : (존재함) 0 : (존재하지않음)로 반환
		        success: function(result) {
		            
		            // 정상적인 학원 이름이 들어온 경우
		            if(result == 0) {
		                // 존재하지 않는 학원 이름이 들어온 경우
		                console.log('DB Table에 존재하지 않는 학원 이름');
		                alert('학원 명을 확인해 주세요!');
		            } else {
		                console.log('글 작성 진행');
		                num.value = result;
		                document.posting_form.submit();
		            }
		        },
	
		        error: function() {
		            console.log('글 작성 - ajax 통신 오류 발생!');
		        }
		    });
		    return false;
		}
		
		// 값이 비어 있는지 체크하는 함수
	    // 비어 있으면 --> true
 		let isEmpty = function(value){
		    if( value == "" || value == null || value == undefined || ( value != null && typeof value == "object" && !Object.keys(value).length ) ){
		        return true;
		    }else{
		        return false;
		    }
		}; 
		
 		document.posting-form.addEventListener("keydown", evt => {
			if (evt.code === "Enter" || evt.keyCode === 13) evt.preventDefault();
		});
		
 		// 글자수 제한 함수
 		function limitMaxLength(e) {
 			if (e.value.length > e.maxLength) {
 				e.value = e.value.slice(0, e.maxLength);
 				alert('입력 가능한 범위를 초과했습니다!');
 			}
 		}
 		
 		function selectCourse(course) {
 		    // 선택한 과정에 대한 동작 정의
 		    console.log(course);
 		    
 		    // 선택한 과정을 버튼에 표시
 		    document.querySelector('.coursebtn').textContent = course;
 		   	document.querySelector('.course-dropdown').classList.remove('show');
 		  }
	</script>
</body>
</html>