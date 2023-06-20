<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- jstl 사용 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 현재 페이지 정보 -->
<c:set var="contextPath"  value="${pageContext.request.contextPath}" />
<%
	request.setAttribute("pageName", "foreword");
%>
<%@ page import="com.gc25.dto.ForewordBoardDTO" %>

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
<%  	 // 회원 상태가 0일 때, 게시판 리스트 페이지로 리다이렉트하고 알림창 표시
          out.println("<script>alert('현재 페이지는 우수회원만 접근 가능합니다.'); window.location.href='http://localhost:8080/forerword/board.do'; </script>");
        } else { 
%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>GC25 | Foreword Review Page</title>
	<!-- 커스텀.css / reset.css / 파비콘 / x-icon -->
	<link href="<c:url value='/resources/css/custom.css' />" rel="stylesheet">
	<link href="<c:url value='/resources/css/reset.css' />" rel="stylesheet">
	<link rel="shortcut icon" type="image/x-icon" href="/resources/images/mini_logo.png">
	<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">	
</head>
<body>
	
		<jsp:include page="./common/header.jsp"></jsp:include>
	 	<!-- header include 영역 -->
		
		<div class="board-page foreword-page write-page foreword-write-page">
			<div class="inner">
				<div class="title">
					<h2>상담 후기 작성</h2>
					<p>설명설명설명</p>
				</div>
			</div>
		</div>
		
        <p>게시판 타이틀: 상담 후기</p>
      
 		<!-- (전체 게시글보기는 단순 뒤로가기 기능으로 꼭 구현 안해도됨! -->
 		<p>전체 게시글보기: 뒤로가기버튼 
 		<a href="${contextPath}/foreword/board.do?boardNum=${forewordBoardDTO.getBoardNumber()}" class="BtnType SizeS btn_gray btn_back_list" onmousedown="DETAILPAGE.Detail.gaEvent('qst_detail', 'resume_total')"><button type="button" class="btn btn-outline-primary"> &lt; 전체 게시글</button></a>
 		</p>
 		
 		
 		
 		<!-- 삭제 & 수정 버튼은 글쓴이와 현재 접속한 사람이 일치할때만 화면에 보여짐 -->
			<%-- 현재 사용자 ID 세션에서 가져오기 --%>
				
			<%-- 글 작성자 ID 가져오기 --%>
				<%
				ForewordBoardDTO forewordBoardDTO = (ForewordBoardDTO)session.getAttribute("forewordBoardDTO");
				int authorId = forewordBoardDTO.getMemberNumber();
				%>
	
			<%-- 현재 사용자와 글 작성자가 일치할 경우에만 버튼 보여주기 --%>
				<% if (memberNumber == authorId) { %>
				      <form class="btn btn-outline-primary me-2" action="${contextPath}/foreword/modify.do?boardNum=${forewordBoardDTO.getBoardNumber()}"  method="post">
				      	<button type="submit" class="btn btn-outline-primary me-2">글 수정</button>
				      </form>
				      
				      <form class="btn btn-primary me-2" action="${contextPath}/foreword/delete.do?boardNum=${forewordBoardDTO.getBoardNumber()}" method="post">
				      	<button type="submit" class="btn btn-outline-primary me-2">글 삭제</button>
				      </form>
				 
				<% } %>
				 
 		
 				
	 	<!-- 게시글 내용 -->			 
	 	<p>게시글 제목: ${forewordBoardDTO.getTitle()}</p>
		<p>글쓴이 이미지: <img src="../resources/images/${forewordBoardDTO.getImageFileName()}" alt="${forewordBoardDTO.getImageFileName()}" style="width:30px; heigh:30px; border-radius:50%; object-fit:cover;"> </p>
		<p>글쓴이 닉네임: ${forewordBoardDTO.getNickname()}</p>
		<p>작성일: <i class="xi-pencil-point"></i> <div id="writeDateValue" style="display: none;"> ${forewordBoardDTO.getWriteDate()}</div></p>
				"writeDateValue"id가 아래 스크립트에서 사용됩니다.
				<p>	작성일 기준 현재 시간에서 빼는 script 실행 결과 출력부 <div id=writeDate></div>
		<p>조회수: <i class="xi-eye"></i> ${forewordBoardDTO.getViews()}</p>
		<p>댓글수: <i class="xi-comment"></i> ${forewordBoardDTO.getCommentCount()}</p>
	 	<p>글 내용:  ${forewordBoardDTO.getContents()}</p>
						
	 					
		<p>학원이름: ${forewordBoardDTO.getAcademyName()}</p>				
		<p>과정구분 :${forewordBoardDTO.getCourse()}</p> 
		<p>코스: ${forewordBoardDTO.getCourse()}</p>
		
		<p> 수강후기에서 가져온 전체 만족도 : ${forewordBoardDTO.getAcademyAvgScore()} </p>
		<div class="starBox col-3">
								<span class="emptyStar" id="score4">
									★★★★★ 
									<span class="fillStar" style="width: ${forewordBoardDTO.getAcademyAvgScore()*10}%" >★★★★★</span>
										 <input type="range" value="0" step="1" min="0" max="10"
									id="curriScore" name="curriScore"> 
								 	</span> 
							</div>
			  	
	    <p> 강사 만족도 : ${afterwordBoardDTO.getTeacherScore()}</p>
	    <p> 학원시설 만족도 : ${afterwordBoardDTO.getFacilityScore()}</p>
	    <p> 커리큘럼 만족도 :${afterwordBoardDTO.getCurriculumScore()}</p>
			
		<p>좋아요 아이콘 클릭 시 controller로 이동하면서 좋아요 숫자 +1 함 </p>
			<a href="${contextPath}/foreword/recommend.do?boardNum=${forewordBoardDTO.getBoardNumber()}">
				<i class="xi-thumbs-up"></i>
			</a>
		</p>
		<p>추천수: <i class="xi-thumbs-up"></i> ${forewordBoardDTO.getRecommend()}</p>
		<form id="likeForm" style="display: none;" action="${contextPath}/foreword/recommend.do?boardNum=${forewordBoardDTO.getBoardNumber()}" method="post">
			<input type="hidden" name="postId" value="123">
		</form>
			
			
			
	
		<!-- 댓글입력창 -->
		<form id= "commetForm" action="${contextPath}/comment/fbCommentRegister.do?boardNum=${forewordBoardDTO.getBoardNumber()}" method="post"> 
        <textarea class="form-control" id= "commentContents"  name="commentContents" rows="5" placeholder="댓글을 입력해주세요" maxlength="999" oninput="limitMaxLength(this);" name="contents" style="overflow-y: auto;"></textarea>
        <!-- 버튼 -->
       <button type="submit" class="btn btn-secondary btn-sm mt-2">등록</button>                       	
       </form>	<!-- 댓글 제출 -->
                    
                        	

					
			
				
		 <!-- 댓글목록-->
		<c:forEach var="c" items="${commentList}" varStatus="afterNum" >
		  	<p>댓글 작성사 이미지: <img src="../resources/images/${commentList[afterNum.index].getImageFileName()}" alt= "${commentList[afterNum.index].getImageFileName()}" id=" ${commentList[afterNum.index].getImageFileName()}" style="width:30px; heigh:30px; border-radius:50%; object-fit:cover;">
			<p>댓글 작성자 닉네임: <span>${commentList[afterNum.index].getNickname()}</span></p>
			<p>댓글 리스트 ${commentList[afterNum.index].getCommentContents()} </p>
		</c:forEach>
		

		
		<!-- footer include 영역 -->
		<jsp:include page="./common/footer.jsp"></jsp:include>

<% } %>
		
	<script src="<c:url value='./js/bootstrap.min.js' />"></script>
	<script src="<c:url value='./js/bootstrap.bundle.min.js' />"></script>
	<script src="<c:url value='./js/jquery.js' />"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
	<script src="<c:url value='./js/custom.js' />"></script>
	<script src="<c:url value='./js/jquery.js' />"></script> 
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
  		
		
<% } %>


	</script>

</body>
</html>