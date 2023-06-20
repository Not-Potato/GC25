<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- jstl 사용 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 현재 페이지 정보 -->
<c:set var="contextPath"  value="${pageContext.request.contextPath}" />
<%
	request.setAttribute("pageName", "afterword");
%>
<!-- 회원등급에 따른 상세페이지 접근 구분 -->
<%
     // 로그인 여부 확인
    Object memberNumberObj = session.getAttribute("memberNumber");
	
	if (memberNumberObj == null) {
		// 로그인이 되어있지 않은 경우, 로그인 페이지로 리다이렉트
        out.println("<script>alert('로그인 해주세요.'); window.location.href='http://localhost:8080/views/login.jsp'; </script>");
	}else {
		// 로그인 되어 있는 경우, 회원 상태 확인
		int memberNumber = (Integer) memberNumberObj;
        int memberStatus = (Integer) session.getAttribute("memberStatus");

        if (memberStatus == 0) { %>
<%      // 회원 상태가 0일 때, 게시판 리스트 페이지로 리다이렉트하고 알림창 표시
          out.println("<script>alert('현재 페이지는 우수회원만 접근 가능합니다.'); window.location.href='http://localhost:8080/afterword/board.do'; </script>");

        } else { 
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>GC25 | Afterword Write Page</title>
	<!-- 커스텀.css / reset.css / 파비콘 / x-icon / date range picker -->
	<link href="<c:url value='/resources/css/custom.css' />" rel="stylesheet">
	<link href="<c:url value='/resources/css/reset.css' />" rel="stylesheet">
	<link rel="shortcut icon" type="image/x-icon" href="/resources/images/mini_logo.png">
	<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
	<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
</head>
<body>
	<div id="wrap" class="w-100">
    	<jsp:include page="./common/header.jsp"></jsp:include>
        <!-- header include 영역 -->

        <section id="" class="mt-5">
            <div class="inner m-auto">
                <h2 class="text-center p-4 bg-light border rounded-pill mb-4 m-auto text-primary w-800">수강 후기 작성</h2>
            </div>
        </section>

        <main id="container" class="main">
            <section id="content">
                <div class="inner m-auto">
                	<!-- onsubmit enter로 submit 되는 것 방지하는 옵션 -->
                    <form class="row g-3 m-auto w-800" name="posting-form" onsubmit="return posting();" method="POST" action="${contextPath}/afterword/upload.do">

						<!-- 학원 이름 입력 창 -->
                        <div class="col-md-6">
							<input type="text" id="academyName"
								class="form-control" autocomplete="off" name="academyName" placeholder="학원 명" >
							<input type="hidden" name="academyNum" id="academyNum" class="" value="">
							<div class="searchList">
								<div class="d-none col-md-12" id="none">
									<!-- 자동 완성 검색 결과 div 들어갈 곳 -->
								</div>
							</div>
						</div>

                        <!-- 과정 구분 -->
                        <div class="col-md-6">
                            <select class="form-select" id="course" name="course">
                            	<!-- 선택할 수 없는 옵션의 value를 null로 지정 -->
                                <option disabled selected value="">----- 과정 구분 -----</option>
                                <option value="프론트엔드">프론트엔드</option>
                                <option value="백엔드">백엔드</option>
                                <option value="풀스택">풀스택</option>
                            </select>
                        </div>
                        
                        <!-- 강사 명 -->
                        <div class="col-md-4">
                            <input type="text" class="form-control" id="teacher" placeholder="강사 명" 
                            maxlength="10" oninput="limitMaxLength(this);" name="teacher">
                        </div>
                        
                        <!-- 개강 ~ 종강 -->
						<div class="col-md-4">
							<input class="col-md-12 form-control" type="text" id="openToEnd" name="openToEnd" value="" />
						</div>
						
                        <!-- 전공/비전공 여부 -->
                        <div class="col-md-2 mt-4 pt-2">
							<div class="form-check form-switch">
								<div>
								<input class="form-check-input" type="checkbox" role="switch" id="major" name="major" value="비전공">
								<label class="form-check-label" id="majorLabel" for="major">비전공</label>
								</div>
							</div>
						</div>

                        <!-- 유/무상 여부 -->
                        <div class="col-md-2 mt-4 pt-2">
							<div class="form-check form-switch">
								<input class="form-check-input" type="checkbox" role="switch" id="cost" name="cost" value="무상">
								<label class="form-check-label" id="costLabel" for="cost">무상</label>
							</div>
						</div>
						
						<!-- 전체 만족도 -->
						<div class="starBox col-3">
							<label>전체</label><br>
							<span class="emptyStar" id="score1">
								★★★★★
								<span class="fillStar">★★★★★</span>
								<input type="range" value="0" step="1" min="0" max="10"
								id="totalScore" name="totalScore">
							</span>
						</div>

						<!-- 강사 만족도 -->
						<div class="starBox col-3">
							<label>강사</label>
							<span class="emptyStar" id="score2">
								★★★★★
								<span class="fillStar">★★★★★</span>
								<input type="range" value="0" step="1" min="0" max="10"
								id="teacherScore" name="teacherScore">
							</span>
						</div>

						<!-- 학원 시설 만족도 -->
						<div class="starBox col-3">
							<label>학원 시설</label>
							<span class="emptyStar" id="score3">
								★★★★★
								<span class="fillStar">★★★★★</span>
								<input type="range" value="0" step="1" min="0" max="10"
								id="facScore" name="facScore">
							</span>
						</div>

						<!-- 커리큘럼 만족도 -->
						<div class="starBox col-3">
							<label>커리큘럼</label>
							<span class="emptyStar" id="score4">
								★★★★★
								<span class="fillStar">★★★★★</span>
								<input type="range" value="0" step="1" min="0" max="10"
								id="curriScore" name="curriScore">
							</span>
						</div>

                        <!-- 제목 -->
                        <div class="col-12">
                            <input type="text" class="form-control" id="title" placeholder="제목" 
                            maxlength="30" oninput="limitMaxLength(this);" name="title">
                        </div>

                        <!-- 내용 -->
                        <div class="col-12">
                            <textarea class="form-control" id="contents" rows="15" placeholder="내용" 
                            maxlength="1500" oninput="limitMaxLength(this);" name="contents"></textarea>
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
    
<% } %>
		
<% } %>	
    <script src="../resources/js/bootstrap.min.js"></script>
	<!-- <script src="../resources/js/popper.js"></script> -->
	<!-- <script src="https://code.jquery.com/jquery-3.4.1.js"></script> -->
	<script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
	<script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
	<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
	<script src="../resources/js/academynameinput.js"></script>
	<script src="../resources/js/custom.js"></script>
	<script>
		let num = document.getElementById("academyNum");
	    let openDate = ""; // 날짜 선택 시 값 적용됨
		let endDate = ""; // 날짜 선택 시 값 적용됨
		
		function posting() {	
		    // 사용자 입력 값 받아오기
		    let academyName = $("#academyName").val();
		    let course = $("#course").val();
		    let title = $("#title").val();
		    let contents = $("#contents").val();
		    // 수강후기에만 존재하는 항목들
		    let teacher = $("#teacher").val();
		    let major = $("#major").val();
		    let cost = $("#cost").val();
		    let totalScore = $("#totalScore").val();
		    let teacherScore = $("#teacherScore").val();
		    let facScore = $("#facScore").val();
		    let curriScore = $("#curriScore").val();
		    
		    console.log("강사 이름: " + teacher);
		    console.log("전공/비전공: " + major);
		    console.log("유/무상: " + cost);
		    console.log("개강일: " + openDate);
		    console.log("종강일: " + endDate);
		    console.log("총점: " + totalScore);
		    console.log("강사 만족도: " + teacherScore);
		    console.log("시설 만족도: " + facScore);
		    console.log("커리 만족도: " + curriScore);
			
		    let check1 = isEmpty(academyName);
		    let check2 = isEmpty(course);
		    let check3 = isEmpty(title);
		    let check4 = isEmpty(contents);
		    let check5 = isEmpty(teacher);
		    let check6 = isEmpty(openDate);
		    let check7 = isEmpty(endDate);
	
		    // 만약 하나라도 true면 --> 입력되지 않은 값이 존재하는 경우
		    if (check1 || check2 || check3 || check4 || check5 || check6 || check7 || 
		    		(totalScore == 0) || (teacherScore == 0) || (facScore == 0) || (curriScore == 0)) {
		        alert('입력되지 않은 값이 존재합니다!');
		        return false;
		    } 
	
		    // DB에 없는 학원 명 입력할 수 없도록 제한하기 --> 폼 제출 시 ajax로 통신하여 DB 확인
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
		                document.postingForm.submit();
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
		
		// enter key 입력 --> form 제출 X
		document.postingForm.addEventListener("keydown", evt => {
			if (evt.code === "Enter") evt.preventDefault();
		});
		
		// 체크 여부에 따라 label.text와 input.val 변경
 		let toggleCheckbox = (checkboxId, labelId, value1, value2) => {
			$(checkboxId).on("click", function () {
				let currentValue = $(this).val();
				let labelText = $(labelId).text();
				
				if (currentValue === value1) {
					$(labelId).text(value2);
					$(this).val(value2);
				} else {
					$(labelId).text(value1);
					$(this).val(value1);
				}
			});
		};

		toggleCheckbox("#major", "#majorLabel", "전공", "비전공");
		toggleCheckbox("#cost", "#costLabel", "유상", "무상");
		
		// 개강 ~ 종강 선택
		$(function () {
            $('#openToEnd').daterangepicker({
                "locale": {
                    "format": "YYYY-MM-DD",
                    "separator": " ~ ",
                    "applyLabel": "확인",
                    "cancelLabel": "취소",
                    "fromLabel": "From",
                    "toLabel": "To",
                    "customRangeLabel": "Custom",
                    "weekLabel": "W",
                    "daysOfWeek": ["월", "화", "수", "목", "금", "토", "일"],
                    "monthNames": ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"],
                    "firstDay": 1
                },
                "startDate": "2023-01-01",
                "endDate": "2023-01-01",
                "drops": "down"
            }, function (start, end, label) {
                console.log('New date range selected: ' + start.format('YYYY-MM-DD') + ' to ' + end.format('YYYY-MM-DD') + ' (predefined range: ' + label + ')');
                openDate = start.format('YYYY-MM-DD');
                endDate = end.format('YYYY-MM-DD');
                console.log(openDate);
                console.log(endDate);
            });
        });
	</script>
</body>
</html>