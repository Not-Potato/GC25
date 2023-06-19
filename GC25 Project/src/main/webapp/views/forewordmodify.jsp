<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	<title>GC25 | Foreword Write Page</title>
	<!-- 커스텀.css / reset.css / 파비콘 / x-icon -->
	<link href="<c:url value='/resources/css/custom.css' />" rel="stylesheet">
	<link href="<c:url value='/resources/css/reset.css' />" rel="stylesheet">
	<link rel="shortcut icon" type="image/x-icon" href="/resources/images/mini_logo.png">
	<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
</head>
<body>
	<div id="wrap" class="w-100">
    	<jsp:include page="./common/header.jsp"></jsp:include>
        <!-- header include 영역 -->

        <section id="" class="mt-5">
            <div class="inner m-auto">
                <h2 class="text-center p-4 bg-light border rounded-pill mb-4 m-auto text-primary w-800">상담 후기 작성(수정 중)</h2>
            </div>
        </section>

        <main id="container" class="main">
            <section id="content">
                <div class="inner m-auto">
                	<!-- onsubmit enter로 submit 되는 것 방지하는 옵션 -->
                    <form class="row g-3 m-auto w-800" name="postingForm" onsubmit="return posting();" method="POST" action="${contextPath}/foreword/modifyupload.do?boardNum=${forewordBoardDTO.getBoardNumber()}">

						<!-- 학원 이름 입력 창 -->
                        <div class="col-md-6">
							<input type="text" id="academyName" placeholder="학원 이름을 입력해 주세요." value="${forewordBoardDTO.getAcademyName()}"
								class="form-control" autocomplete="off" name="academyName">
							<input type="hidden" name="academyNum" id="academyNum" class="" value="">
							<!-- TODO: autoComplete 요소 삭제하고 스크립트(통신) 발생 시에 생성되게끔 스크립트 변경하기 -->
							<div class="searchList">
								<div class="autoComplete col-md-12">
									<!-- 자동 완성 검색 결과 div 들어갈 곳 -->
								</div>
							</div>
						</div>

                        <!-- 과정 구분 -->
				<div class="col-md-6">
				    <select class="form-select" id="course" name="course">
				        <option disabled value="">----- 과정 구분 -----</option>
				        <c:choose>
				            <c:when test="${forewordBoardDTO.getCourse() == '프론트엔드'}">
				                <option value="프론트엔드" selected>프론트엔드</option>
				                <option value="백엔드">백엔드</option>
				                <option value="풀스택">풀스택</option>
				            </c:when>
				            <c:when test="${forewordBoardDTO.getCourse() == '백엔드'}">
				                <option value="프론트엔드">프론트엔드</option>
				                <option value="백엔드" selected>백엔드</option>
				                <option value="풀스택">풀스택</option>
				            </c:when>
				            <c:when test="${forewordBoardDTO.getCourse() == '풀스택'}">
				                <option value="프론트엔드">프론트엔드</option>
				                <option value="백엔드">백엔드</option>
				                <option value="풀스택" selected>풀스택</option>
				            </c:when>
				            <c:otherwise>
				                <option value="프론트엔드">프론트엔드</option>
				                <option value="백엔드">백엔드</option>
				                <option value="풀스택">풀스택</option>
				            </c:otherwise>
				        </c:choose>
				    </select>
				</div>
 

 
                        <!-- 제목 -->
                        <div class="col-12">
                            <input type="text" class="form-control" id="title" placeholder="제목" 
                            maxlength="30" oninput="limitMaxLength(this);" name="title" value="${forewordBoardDTO.getTitle()}">
                        
                        </div>

                        <!-- 내용 -->
                        <div class="col-12">
                            <textarea class="form-control" id="contents" rows="15" placeholder="내용" 
                            maxlength="1500" oninput="limitMaxLength(this);" name="contents"> ${forewordBoardDTO.getContents()}</textarea>
                        </div>

                        <!-- 버튼 -->
                        <div class="d-flex justify-content-center">
                            <button type="reset" class="btn btn-outline-primary me-2">취소</button>
                            <button type="submit" class="btn btn-primary ms-2">수정</button>
                        </div>
                    </form>
                </div>
            </section>
        </main>
        <!-- main -->

		<!-- footer include 영역 -->
        <jsp:include page="./common/footer.jsp"></jsp:include>
    </div>
    <script src="../resources/js/bootstrap.min.js"></script>
	<script src="../resources/js/popper.js"></script>
	<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
	<script src="../resources/js/academynameinput.js"></script>
	<script src="../resources/js/custom.js"></script>
	<script>
		let num = document.getElementById("academyNum");
	
		function posting() {	
		    // 사용자 입력 값 받아오기
		    let academyName = $("#academyName").val();
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
		
		document.postingForm.addEventListener("keydown", evt => {
			if (evt.code === "Enter") evt.preventDefault();
		});
		
        
	</script>
</body>
</html>