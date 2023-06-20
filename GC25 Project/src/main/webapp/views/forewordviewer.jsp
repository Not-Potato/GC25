<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.gc25.dto.ForewordBoardDTO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}" />
<%
	request.setAttribute("pageName", "foreword");
     // 로그인 여부 확인
    Object memberNumberObj = session.getAttribute("memberNumber");
	int memberNumber = 0;
	int memberStatus = 0;

	if (memberNumberObj == null) {
		// 로그인이 되어있지 않은 경우, 로그인 페이지로 리다이렉트
        out.println("<script>alert('로그인 해주세요.'); window.location.href='http://localhost:8080/views/login.jsp'; </script>");  
	}else {
		// 로그인 되어 있는 경우, 회원 상태 확인
		memberNumber = (Integer) memberNumberObj;
        memberStatus = (Integer) session.getAttribute("memberStatus");

        if (memberStatus == 0) {   	 
        // 회원 상태가 0일 때, 게시판 리스트 페이지로 리다이렉트하고 알림창 표시
          out.println("<script>alert('현재 페이지는 우수회원만 접근 가능합니다.'); window.location.href='http://localhost:8080/forerword/board.do'; </script>");
		}
	}
	// 회원 정보와 게시글 주인의 회원 번호 가져오기
	ForewordBoardDTO forewordBoardDTO = (ForewordBoardDTO)session.getAttribute("forewordBoardDTO");
	int authorId = forewordBoardDTO.getMemberNumber();
	boolean mine = memberNumber == authorId ? true : false;
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
	
	<div class="board-page foreword-page view-page">
		<div class="inner">
			<div class="title">
				<h2>상담 후기</h2>
				<p>다른 이용자의 경험을 공유받고 금쪽 같은 시간을 아껴 보세요!</p>
			</div>
			
			<div class="con">
				<div class="btn-group">
					<button type="button" class="btn" onclick=" location.href='${contextPath}/foreword/board.do?boardNum=${forewordBoardDTO.getBoardNumber()}' ">목록</button>
					
					<div class="">
<% if (mine) { %>
						<button type="button" class="btn" onclick=" location.href='${contextPath}/foreword/modify.do?boardNum=${forewordBoardDTO.getBoardNumber()}'">수정</button>
						<button type="button" class="btn" onclick=" location.href='${contextPath}/foreword/delete.do?boardNum=${forewordBoardDTO.getBoardNumber()}'">삭제</button>
<% } else {%>
						<button type="button" class="btn none">수정</button>				
						<button type="button" class="btn none">삭제</button>				
<% } %>			
					</div>
				</div>
				
				<div class="post">
					<div class="info">
						<h4>${forewordBoardDTO.getTitle()}</h4>
						
						<div class="profile">
							<img src="../resources/images/${forewordBoardDTO.getImageFileName()}" alt="${forewordBoardDTO.getImageFileName()}" style="width:30px; heigh:30px; border-radius:50%; object-fit:cover;">
							<p>${forewordBoardDTO.getNickname()}</p>
						</div>
						
						<div id="writeDateValue" class="d-none"> ${forewordBoardDTO.getWriteDate()}</div>
						
						<div>
							<div class="i-left">
								<em><i class="xi-maker"></i>${forewordBoardDTO.getAcademyName()}</em>
								<em>${forewordBoardDTO.getCourse()}</em>
								<em id="writeDate"></em>
								<i class="xi-pen"></i>
							</div>
							<div class="i-right">
								<em><i class="xi-eye"></i> ${forewordBoardDTO.getViews()}</em>
								<em><i class="xi-comment"></i> ${forewordBoardDTO.getCommentCount()}</em>
								<em><i class="xi-thumbs-up"></i> ${forewordBoardDTO.getRecommend()}</em>
							</div>
						</div>
					</div>
					
					<div class="text">
						<p>${forewordBoardDTO.getContents()}</p>
					</div>

					<div class="like">
						<a href="${contextPath}/foreword/recommend.do?boardNum=${forewordBoardDTO.getBoardNumber()}">
							<i class="xi-thumbs-up"></i>
							<p>Good!</p>
						</a>
					</div>

					<div class="academy-info">
						<p>학원 이름: ${forewordBoardDTO.getAcademyName()}</p>				
						<div class="starBox">
							<p>학원 평점 : ${forewordBoardDTO.getAcademyAvgScore()} </p>
							<span class="emptyStar">
								★★★★★
								<span class="fillStar" style="width: ${forewordBoardDTO.getAcademyAvgScore()*10}%" >★★★★★</span>
							</span> 
						</div>
					</div>
					
					<div class="comment-write">
						<em><i class="xi-comment"></i> ${forewordBoardDTO.getCommentCount()}</em>
						<form id= "commetForm" action="${contextPath}/comment/fbCommentRegister.do?boardNum=${forewordBoardDTO.getBoardNumber()}" method="post"> 
							<textarea class="" id= "commentContents"  name="commentContents" rows="5" placeholder="댓글을 입력해주세요" maxlength="999" oninput="limitMaxLength(this);" name="contents" style="overflow-y: auto;"></textarea>
							<button type="submit" class="btn">등록</button>                       	
						</form>
					</div>
				</div>
				
				<div class="comments">
<c:forEach var="c" items="${commentList}" varStatus="afterNum" >
					<p>댓글 작성사 이미지: <img src="../resources/images/${commentList[afterNum.index].getImageFileName()}" alt= "${commentList[afterNum.index].getImageFileName()}" id=" ${commentList[afterNum.index].getImageFileName()}" style="width:30px; heigh:30px; border-radius:50%; object-fit:cover;">
					<p>댓글 작성자 닉네임: <span>${commentList[afterNum.index].getNickname()}</span></p>
					<p>댓글 리스트 ${commentList[afterNum.index].getCommentContents()} </p>
</c:forEach>
				</div>
			</div>
		</div>
	</div>
		
	<!-- footer include 영역 -->
	<jsp:include page="./common/footer.jsp"></jsp:include>
	<script src="<c:url value='./js/jquery.js' />"></script>
	<script src="<c:url value='./js/custom.js' />"></script>
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