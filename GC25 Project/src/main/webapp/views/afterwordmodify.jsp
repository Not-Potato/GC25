<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- jstl 사용 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 현재 페이지 정보 -->
<c:set var="contextPath"  value="${pageContext.request.contextPath}" />
<%
request.setAttribute("pageName", "afterword");
%>
<%@ page import="com.gc25.dto.AfterwordBoardDTO" %>
<!-- 회원등급에 따른 상세페이지 접근 구분 -->
<!-- 회원등급에 따른 상세페이지 접근 구분 -->
<%
     // 로그인 여부 확인
    Object memberNumberObj = session.getAttribute("memberNumber");
	
	if (memberNumberObj == null) {
		// 로그인이 되어있지 않은 경우, 로그인 페이지로 리다이렉트
        out.println("<script>alert('로그인 해주세요.'); window.location.href='http://localhost:8080/views/login.jsp'; </script>");
	}
	
	AfterwordBoardDTO afterwordBoardDTO = (AfterwordBoardDTO)session.getAttribute("afterwordBoardDTO");
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>GC25</title>
	<!-- 커스텀.css / reset.css / 파비콘 / x-icon / date range picker -->
	<link href="<c:url value='/resources/css/custom.css' />" rel="stylesheet">
	<link href="<c:url value='/resources/css/reset.css' />" rel="stylesheet">
	<link rel="shortcut icon" type="image/x-icon" href="/resources/images/mini_logo.png">
	<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
	<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
</head>
<body>
	<div id="wrap">
    	<jsp:include page="./common/header.jsp"></jsp:include>
        <!-- header include 영역 -->

      	<div class="board-page foreword-page write-page foreword-write-page">
			<div class="inner">
				<div class="title">
					<h2>수강 후기</h2>
					<p>앗! 빠뜨리거나 더할 것이 있으신가요?</p>				
				</div>

				<form class="after-form" name="posting_form" onsubmit="return posting();" method="POST" action="${contextPath}/afterword/modifyupload.do?boardNum=${afterwordBoardDTO.getBoardNumber()}">
					<!-- 첫 번째 줄 (학원 명 / 과정 구분) -->
					<div class="first-line">
						<div>
							<input type="text" id="academy-name" class="box" placeholder="학원 명" autocomplete="off" name="academyName" value="${afterwordBoardDTO.getAcademyName()}">
							<input type="hidden" name="academyNum" id="academyNum" value="">
							<div class="search-list">
								<ul class="d-none" id="none">
									<!-- 자동 완성 검색 결과 div 들어갈 곳 -->
								</ul>
							</div>
						</div>
						<div class="course-dropdown">
							<select class="box form-select" id="course" name="course">
						 		<option disabled value="">----- 과정 구분 -----</option>
	       				 		<c:choose>
				            		<c:when test="${afterwordBoardDTO.getCourse() == '프론트엔드'}">
						                <option value="프론트엔드" selected>프론트엔드</option>
						                <option value="백엔드">백엔드</option>
						                <option value="풀스택">풀스택</option>
				            		</c:when>
				            		<c:when test="${afterwordBoardDTO.getCourse() == '백엔드'}">
						                <option value="프론트엔드">프론트엔드</option>
						                <option value="백엔드" selected>백엔드</option>
						                <option value="풀스택">풀스택</option>
				            		</c:when>
				            		<c:when test="${afterwordBoardDTO.getCourse() == '풀스택'}">
						                <option value="프론트엔드">프론트엔드</option>
						                <option value="백엔드">백엔드</option>
						                <option value="풀스택" selected>풀스택</option>
				            		</c:when>
				            		<c:otherwise>
						            	<option disabled selected value="">----- 과정 구분 -----</option> 
						                <option value="프론트엔드">프론트엔드</option>
						                <option value="백엔드">백엔드</option>
						                <option value="풀스택">풀스택</option>
				            		</c:otherwise>
	       				 		</c:choose>
						</select>	
					</div>
				</div>
				
				<!-- 두 번째 줄(강사명 / 개강~종강 / 전공·비전공여부 / 유·무상 여부)  -->
				<div class="addition-first-line">
<%-- 					<input class="box" type="text" id="teacher" placeholder="강사 명" maxlength="10" oninput="limitMaxLength(this);" name="teacher" value="${afterwordBoardDTO.getTeacherName()}"> --%>
					<input class="box" type="text" id="teacher" placeholder="강사 명" maxlength="10" oninput="limitMaxLength(this);" name="teacher" value="${afterwordBoardDTO.getTeacherName()}">
                    <input class="box" type="text" id="openToEnd" autocomplete="off"  name="openToEnd" value="" />
               
                    <div>
						<div class="chk-box">
							<input class="chk" type="checkbox" role="switch" id="major" name="major" value="${afterwordBoardDTO.getMajor()}">
							<label id="majorLabel" for="major">${afterwordBoardDTO.getMajor()}</label>
						</div>
						<div class="chk-box">
							<input class="chk" type="checkbox" role="switch" id="cost" name="cost" value="${afterwordBoardDTO.getCost()}">
							<label id="costLabel" for="cost">${afterwordBoardDTO.getCost()}</label>
						</div>
					</div>
				</div>	
						
                <!-- 제목 -->
				<div class="second-line">
					<input type="text" class="input-title box" id="title" placeholder="제목" maxlength="30" oninput="limitMaxLength(this);" name="title" value="${afterwordBoardDTO.getTitle()}">
				</div>
				
				<!-- 내용 -->
				<div class="third-line">
					<textarea id="contents" class="input-contents" rows="15" placeholder="내용" maxlength="1500" oninput="limitMaxLength(this);" name="contents" >${afterwordBoardDTO.getContents()}</textarea>
				</div>	
						
				<!-- 점수 4개 -->
				<div class="addition-second-line">
					<!-- 전체 만족도 -->
					<div class="starBox">
						<label>전체</label><br>
						<span class="emptyStar" id="score1">
							★★★★★
							<span class="fillStar" style="width: ${afterwordBoardDTO.getTotalScore()*10}%">★★★★★</span>
							<input type="range" value="${afterwordBoardDTO.getTotalScore()}" step="1" min="0" max="10" id="totalScore" name="totalScore">
						</span>
					</div>

					<!-- 강사 만족도 -->
					<div class="starBox">
						<label>강사</label>
						<span class="emptyStar" id="score2">
							★★★★★
							<span class="fillStar" style="width: ${afterwordBoardDTO.getTeacherScore()*10}%">★★★★★</span>
							<input type="range" value="${afterwordBoardDTO.getTeacherScore()}" step="1" min="0" max="10"
							id="teacherScore" name="teacherScore">
						</span>
					</div>

					<!-- 학원 시설 만족도 -->
					<div class="starBox">
						<label>학원 시설</label>
						<span class="emptyStar" id="score3">
							★★★★★
							<span class="fillStar" style="width: ${afterwordBoardDTO.getFacilityScore()*10}%">★★★★★</span>
							<input type="range" value="${afterwordBoardDTO.getFacilityScore()}" step="1" min="0" max="10"
							id="facScore" name="facScore">
						</span>
					</div>


					<!-- 커리큘럼 만족도 -->
					<div class="starBox">
						<label>커리큘럼</label>
						<span class="emptyStar" id="score4">
							★★★★★
							<span class="fillStar" style="width: ${afterwordBoardDTO.getCurriculumScore()*10}%">★★★★★</span>
							<input type="range" value="${afterwordBoardDTO.getCurriculumScore()}" step="1" min="0" max="10"
							id="curriScore" name="curriScore">
						</span>
					</div>
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
   
   
   
	<!-- <script src="https://code.jquery.com/jquery-3.4.1.js"></script> -->
	<script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
	<script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
	<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
	<script src="../resources/js/academynameinput.js"></script>
	<script src="../resources/js/custom.js"></script>
	<script>
		let num = document.getElementById("academyNum");
	    let openDate = "<%= afterwordBoardDTO.getOpenDate()%>";
		let endDate = "<%= afterwordBoardDTO.getEndDate() %>";
		
		let academyName = $("#academyName").val();
		  
		  if (!academyName) {
	    	  // DB에서 가져온 값 유지
	    	  academyName = "${afterwordBoardDTO.getAcademyName()}";
	    	}
		function posting() {	
		    // 사용자 입력 값 받아오기
		    
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
		    
		    console.log("학원이름: " + academyName);
		    console.log("강사 이름: " + teacher);
		    console.log("코스: " + course);
		    console.log("타이틀: " + title);
		    console.log("내용: " + contents);
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
		
		// enter key 입력 --> form 제출 X
		document.posting_form.addEventListener("keydown", evt => {
			if (evt.code === "Enter" || evt.keyCode === 13) evt.preventDefault();
		});
		
	// 체크 여부에 따라 label.text와 input.val 변경
		  let toggleCheckbox = (checkboxId, labelId, value1, value2, isChecked) => {
			  let $checkbox = $(checkboxId);
			  let $label = $(labelId);

			  if (isChecked) {
			    $checkbox.prop("checked", true);
			    $label.text(value2);
			    $checkbox.val(value2);
			  } else {
			    $checkbox.prop("checked", false);
			    $label.text(value1);
			    $checkbox.val(value1);
			  }

			  $checkbox.on("click", function () {
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

			// DB에서 가져온 값에 따라 체크박스 초기 상태 설정
			let majorValueFromDB = "<%= afterwordBoardDTO.getMajor() %>"; 
			let costValueFromDB = "<%= afterwordBoardDTO.getCost() %>"; 

			
			toggleCheckbox("#major", "#majorLabel", "전공", "비전공", majorValueFromDB === "비전공");
			toggleCheckbox("#cost", "#costLabel", "유상", "무상", costValueFromDB === "무상");
		 
		 
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
                "startDate": openDate,
                "endDate":  endDate, 
                "drops": "down"
            }, function (start, end, label) {
                console.log('New date range selected: ' + start.format('YYYY-MM-DD') + ' to ' + end.format('YYYY-MM-DD') + ' (predefined range: ' + label + ')');
                
                if(start && end) {
                	openDate = start.format('YYYY-MM-DD');
                    endDate = end.format('YYYY-MM-DD');
                } else {
                	$("#openToEnd").val(openDate + ' ~ ' + endDate);
                	
                }
              
            });
        });		
	</script>
</body>
</html>