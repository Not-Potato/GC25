<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- jstl 사용 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 현재 페이지 정보 -->
<c:set var="contextPath"  value="${pageContext.request.contextPath}" />
<%
	request.setAttribute("pageName", "afterword");
%>

<%@ page import="com.gc25.dto.AfterwordBoardDTO" %>

<!-- 회원등급에 따른 상세페이지 접근 구분 -->
<%
    // 회원 상태 확인
    int memberStatus = (Integer) session.getAttribute("memberStatus");
    if (memberStatus == 0) {
        // 회원 상태가 0일 때, 게시판 리스트 페이지로 리다이렉트하고 알림창 표시  
        out.println("<script>alert('현재페이지는 우수회원 만 접근가능합니다.'); window.location.href='${contextPath}/afterword/board.do'; </script>");
    } else {
     	// 아래 코드 부분 구현
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>GC25 | 수강후기 상세보기</title>
	<!-- 커스텀.css / reset.css / 파비콘 / x-icon / date range picker -->
	<link href="<c:url value='/resources/css/custom.css' />" rel="stylesheet">
	<link href="<c:url value='/resources/css/reset.css' />" rel="stylesheet">
	<link rel="shortcut icon" type="image/x-icon" href="/resources/images/mini_logo.png">
	<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
	<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
	



</head>
<body>

		<jsp:include page="./common/header.jsp"></jsp:include>
		<!-- header include 영역 -->


<!--  css 적용 필요한 부분 -->	

        <p>게시판 타이틀: 수강 후기</p>
      
 		<!-- (전체 게시글보기는 단순 뒤로가기 기능으로 꼭 구현 안해도됨! -->
 		<p>전체 게시글보기: 뒤로가기버튼 
 		<a href="${contextPath}/afterword/board.do?boardNum=${afterwordBoardDTO.getBoardNumber()}" class="BtnType SizeS btn_gray btn_back_list" onmousedown="DETAILPAGE.Detail.gaEvent('qst_detail', 'resume_total')"><button type="button" class="btn btn-outline-primary"> &lt; 전체 게시글</button></a>
 		</p>
 		
             
    <!-- 삭제 & 수정 버튼은 글쓴이와 현재 접속한 사람이 일치할때만 화면에 보여짐 -->
      
				<%-- 현재 사용자 ID 세션에서 가져오기 --%>
				<%
				int memberNumber = (Integer) session.getAttribute("memberNumber");
				%>

				<%-- 글 작성자 ID 가져오기 --%>
				<%
				AfterwordBoardDTO afterwordBoardDTO = (AfterwordBoardDTO)session.getAttribute("afterwordBoardDTO");
				int authorId = afterwordBoardDTO.getMemberNumber();
				%>
		
				<%-- 현재 사용자와 글 작성자가 일치할 경우에만 버튼 보여주기 --%>
				<% if (memberNumber == authorId) { %>
				
				      <form class="btn btn-outline-primary me-2" action="${contextPath}/afterword/modify.do?boardNum=${afterwordBoardDTO.getBoardNumber()}"  method="post">
				      	<button type="submit" class="btn btn-outline-primary me-2">글 수정</button>
				      </form>
				      
				      <form class="btn btn-primary me-2" action="${contextPath}/afterword/delete.do?boardNum=${afterwordBoardDTO.getBoardNumber()}" method="post">
				      	<button type="submit" class="btn btn-outline-primary me-2">글 삭제</button>
				      </form>
				 
				<% } %>
		
	
	
		<!-- 게시글 내용 -->		
	
	  
		 <p> 게시글 제목 : ${afterwordBoardDTO.getTitle()}</p>
	 	 <p> 글쓴이 이미지: <img src="../resources/images/${afterwordBoardDTO.getImageFileName()}" alt="${afterwordBoardDTO.getImageFileName()}" style="width:30px; heigh:30px; border-radius:50%; object-fit:cover;"></p>
		 <p> 글쓴이 닉네임: ${afterwordBoardDTO.getNickname()}</p>
		 <p> 작성일: <div id="writeDateValue" style="display: none;">${afterwordBoardDTO.getWriteDate()}</div></p>
		 		"writeDateValue"id가 아래 스크립트에서 사용됩니다.
		 		<p>	작성일 기준 현재 시간에서 빼는 script 실행 결과 출력부: <div id=writeDate></div></p>
		 <p>조회수: ${afterwordBoardDTO.getViews()}</p>
		 <p>댓글수: ${afterwordBoardDTO.getCommentCount()}</p>
		 <p>글 내용:  ${afterwordBoardDTO.getContents()}</p>			
	 			
	
	 				
		<p>학원이름: ${afterwordBoardDTO.getAcademyName()}</p>
		<p>학원 별점: ${afterwordBoardDTO.getTotalScore()} </p>
								  	<div class="col-4"> 
								  		<div class="starBox">
								  			 <span class="emptyStar" id="score5">
	       									 	★★★★★
	      									 	<span class="fillStar" style="width:${afterwordBoardDTO.getTotalScore()*10}%">★★★★★</span>
	        								 	<%-- <input type="range" value="${afterwordBoardDTO.getTotalScore()}" step="1" min="0" max="10" 
	        								 		id="totalScore" name="totalScore">--%>
	    									 </span>
								  		</div> <!-- end of starBox  -->
									</div>
		 	
	 				  
	 			<!-- 수강 후기 정보 -->		  
					    <p> 과정구분 :  ${afterwordBoardDTO.getCourse()} </p> 
					    <p> 강사명: ${afterwordBoardDTO.getTeacherName()}</p>
					    <p> 개강일 : ${afterwordBoardDTO.getOpenDate()} </p>
					    <p> 종강일 : ${afterwordBoardDTO.getEndDate()}</p>
					    <p> 전공여부 : ${afterwordBoardDTO.getMajor()}</p>
					    <p> 유/무상여부 : ${afterwordBoardDTO.getCost()}</p>				    
					    <p> 전체 만족도 : ${afterwordBoardDTO.getTotalScore()}</p>
					    <p> 강사 만족도 : ${afterwordBoardDTO.getTeacherScore()}</p>
					    <p> 학원시설 만족도 : ${afterwordBoardDTO.getFacilityScore()}</p>
					    <p> 커리큘럼 만족도 :${afterwordBoardDTO.getCurriculumScore()}</p>
			
	 				  
	 					<p>좋아요 아이콘 클릭 시 controller로 이동하면서 좋아요 숫자 +1 함 
							<a href="${contextPath}/afterword/recommend.do?boardNum=${afterwordBoardDTO.getBoardNumber()}" onclick="likeForm.submit(); return false;">	
							</a>
						</p>
							
						<p>추천수 : ${afterwordBoardDTO.getRecommend()} </p>
							<form id="likeForm" style="display: none;" action="${contextPath}/afterword/recommend.do?boardNum=${afterwordBoardDTO.getBoardNumber()}" method="post">
								<input type="hidden" name="postId" value="123">
							</form>

	 	
	 	
	    
			<!-- 댓글입력창 -->
		    	<form id= "commetForm" action="${contextPath}/comment/abCommentRegister.do?boardNum=${afterwordBoardDTO.getBoardNumber()}" method="post"> 
                <textarea class="form-control" id= "commentContents"  name="commentContents" rows="5" placeholder="댓글을 입력해주세요" maxlength="999" oninput="limitMaxLength(this);" name="contents" style="overflow-y: auto;"></textarea>
            <!-- 버튼 -->
                 <button type="submit" class="btn btn-secondary btn-sm mt-2">등록</button>                       	
                  </form>	
		
			
			
			
			
			<!-- 댓글목록-->
			
			<c:forEach var="c" items="${commentList}" varStatus="afterNum" >
				<p> 댓글 작성자 이미지: <img src="../resources/images/${commentList[afterNum.index].getImageFileName()}" alt= "${commentList[afterNum.index].getImageFileName()}" id=" ${commentList[afterNum.index].getImageFileName()}" style="width:30px; heigh:30px; border-radius:50%; object-fit:cover;"></p>
				<p> 댓글 작성자 닉네임:  ${commentList[afterNum.index].getNickname()}</p>
				<p> 댓글 리스트:  ${commentList[afterNum.index].getCommentContents()} </p>	   
			</c:forEach>

		
		<!-- footer include 영역 -->
		<jsp:include page="./common/footer.jsp"></jsp:include>

<% } %>
		
		
		
 	<!-- <script src="../resources/js/bootstrap.min.js"></script>-->
	<!-- <script src="../resources/js/popper.js"></script> -->
	<!-- <script src="https://code.jquery.com/jquery-3.4.1.js"></script> -->
	<script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
	<script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
	<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
	<script src="../resources/js/academynameinput.js"></script>
	<script src="../resources/js/custom.js"></script>
	<script>
	

	    /* 게시글 시간 계산 (몇시간 전) */
	    var writeDateValueElement = document.querySelector("#writeDateValue");
		var writeDateElement = document.querySelector("#writeDate");
		
		if (writeDateValueElement && writeDateElement) {
		  var serverTimestamp = new Date(writeDateValueElement.textContent).getTime();
		
		  var now = new Date();
		  var currentTimestamp = now.getTime(); // 현재 타임스탬프
		
		  // 시간 차이 계산 (밀리초 단위)
		  var timeDiff = currentTimestamp - serverTimestamp;
		
		  // 시간 차이를 "n시간 전", "n일 전" 등으로 변환하여 표시
		  var hours = Math.floor(timeDiff / (1000 * 60 * 60)); // 시간 단위로 변환
		  var days = Math.floor(hours / 24); // 일 단위로 변환
		
		  // 화면에 표시
		  var displayText;
		  if (hours < 1) {
		    displayText = "방금 전";
		  }else if (hours < 2) {
			    displayText = "1 시간 전";
		  }else if (hours < 3) {
			    displayText = "2 시간 전";
		  }else if (hours < 5) {
			    displayText = "5 시간 전";
		  }else if (hours < 24) {
		    displayText = hours + "시간 전";
		  } else {
		    displayText = days + "일 전";
		  }
		
		  writeDateElement.textContent = displayText;
		 	console.log(displayText);
		} else {
		  console.error("writeDateValueElement or writeDateElement is null");
		}
  		
		  
	</script>


</body>
</html>