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

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>GC25 | Foreword Review Page</title>
	<link href="<c:url value='../css/bootstrap.min.css' />" rel="stylesheet">
	<link href="<c:url value='../css/custom.css' />" rel="stylesheet">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	
	<!-- 좋아요 아이콘 -->
	<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
	<style>
	.material-symbols-outlined {
	  font-variation-settings:
							  'FILL' 0,
							  'wght' 300,
							  'GRAD' 200,
							  'opsz' 24
	}
	</style>
	<!-- 좋아요 아이콘 CSS 끝-->
</head>
<body>
	<div id="wrap" class="w-100">
		<jsp:include page="./common/header.jsp"></jsp:include>
	 	<!-- header include 영역 -->
		<p>게시글 번호: ${forewordBoardDTO.getBoardNumber()}</p>
 		 <section id="" class="mt-5">
 		 	 <div class="inner m-auto">
                <h2 class="text-center p-4 bg-light border rounded-pill mb-4 m-auto text-primary w-800">상담 후기</h2>
            </div>
 		 </section>
 		 
 		  <main id="container" class="main">
 			  <section id="content">
 	
 				<div class="inner m-auto w-800 mb-3">
 				 	<a href="${contextPath}/foreword/board.do?boardNum=${forewordBoardDTO.getBoardNumber()}" class="BtnType SizeS btn_gray btn_back_list" onmousedown="DETAILPAGE.Detail.gaEvent('qst_detail', 'resume_total')"><button type="button" class="btn btn-outline-primary"> &lt; 전체 게시글</button></a>
 				</div>

 				 		<div class="inner m-auto border border-2 rounded-2 w-800">
	 				 		<div class="container">	
	 				 			<div class= "row"> 
	 								<h2 class="col mt-5 mb-5" >${forewordBoardDTO.getTitle()}</h2>
	 							</div>
	 						</div>		
	 						
	 						<!-- 게시글 정보 -->
 							<div class="container mt-2 ml-1 mr-1">
		 						<div class="row">
		 							<div class="col-8"> 
		 								<img src="../resources/images/${forewordBoardDTO.getImageFileName()}" alt="${forewordBoardDTO.getImageFileName()}" style="width:30px; heigh:30px; border-radius:50%; object-fit:cover;">
		 								<span>${forewordBoardDTO.getNickname()}</span>
		 								
		 							</div>
		 							<div class="col-4 d-flex justify-content-between">
		 								<div class= "align-self-center d-flex">
		 									<span class="material-symbols-outlined">edit</span>
		 									<div id=writeDate></div>
		 									<div id="writeDateValue" style="display: none;">${forewordBoardDTO.getWriteDate()}</div>
		 								</div>	
		 								<div class= "align-self-center d-flex">
		 									<span class="material-symbols-outlined me-2">visibility</span>
		 									<span>${forewordBoardDTO.getViews()}</span>
		 								</div>
		 								<div class= "align-self-center d-flex">
		 									<span class="material-symbols-outlined me-2">chat_bubble</span>
		 									<span>${forewordBoardDTO.getCommentCount()}</span>
		 								</div>
		 							</div>
		 						</div>	
		 					</div>
	 							
	 		
	 						<!--게시글 내용-->
	 						<div class= "row"> 
	 							<div>
	 								<div class="col-12 mt-3 mb-3 p-3">${forewordBoardDTO.getContents()}</div>
								</div>
							</div>	
	 							
	 							
	 						<!-- 학원 정보 -->	
	 						<div class="container mt-3 p-3">
		 						<div class="row">
		 							<div class="col-8 text-center">
								  		<h3>${forewordBoardDTO.getAcademyName()}</h3>
								  	</div>
								  	
								  	<div class="col-4"> 
								  		<div class="starBox">
								  			 <span class="emptyStar" id="score1">
	       									 	 ★★★★★
	      									 	<span class="fillStar"></span>
	        								 	<input type="hidden" value="3" id="totalScore" name="totalScore">
	    									 </span>
								  		</div> <!-- end of starBox  -->
									</div>
		 						</div>
	 						</div>
	 						
	 						<div class="container mt-3 mb-5">
								  <div class="row">
								    <div class="col text-center" style="font-weight: bolder;"> 과정구분 : </div> 
								    <div class="col text-center">${forewordBoardDTO.getCourse()}</div>
								    <div class="col text-center" style="font-weight: bolder;">학원 주소 : </div>
								    <div class="col text-center">${forewordBoardDTO.getCourse()}</div>
								  </div>
							</div>
	 						
		
				    		<!-- 좋아요 아이콘 보여주기 -->
								<div class="row w-800  mt-3 p-3">
										<div class="text-end">	
											<a href="${contextPath}/foreword/recommend.do?boardNum=${forewordBoardDTO.getBoardNumber()}" onclick="likeForm.submit(); return false;">
												<span class="material-symbols-outlined me-2">thumb_up</span> 
											</a>
											<span class="icon-font">${forewordBoardDTO.getRecommend()}</span>
											<form id="likeForm" style="display: none;" action="${contextPath}/foreword/recommend.do?boardNum=${forewordBoardDTO.getBoardNumber()}" method="post">
												<input type="hidden" name="postId" value="123">
											</form>
										</div>
									</div>
 						</div> <!-- end of 메인쪽 inner m-auto  -->
 						
 						<!-- 삭제 & 수정 버튼 -->
 						<div class="inner m-auto w-800 mb-3">
 							<div class="text-end mt-3">
								
								<%-- 현재 사용자 ID 세션에서 가져오기 --%>
								<%
								int memberNumber = (Integer) session.getAttribute("memberNumber");
								%>

								<%-- 글 작성자 ID 가져오기 --%>
								<%
								ForewordBoardDTO forewordBoardDTO = (ForewordBoardDTO)session.getAttribute("forewordBoardDTO");
								int authorId = forewordBoardDTO.getMemberNumber();
								%>
						
								<%-- 현재 사용자와 글 작성자가 일치할 경우에만 버튼 보여주기 --%>
								<% if (memberNumber == authorId) { %>
								  <div class="inner m-auto w-800 mb-3">
								    <div class="text-end mt-3">
								      <form class="btn btn-outline-primary me-2" action="${contextPath}/foreword/modify.do?boardNum=${forewordBoardDTO.getBoardNumber()}"  method="post">
								      	<button type="submit" class="btn btn-outline-primary me-2">글 수정</button>
								      </form>
								      
								      <form class="btn btn-primary me-2" action="${contextPath}/foreword/delete.do?boardNum=${forewordBoardDTO.getBoardNumber()}" method="post">
								      	<button type="submit" class="btn btn-outline-primary me-2">글 삭제</button>
								      </form>
								 
								    </div>
								  </div>
								<% } %>
							</div>
						</div>
						 
						<hr>
						<br>
						<br>
						<br>

						<!-- 댓글창 -->
						<div class="inner m-auto w-800">
				
						    <!-- 댓글입력 -->
						    <div class="col-12">
						    	<!--  내용 -->
						    	<form id= "commetForm" action="${contextPath}/comment/fbCommentRegister.do?boardNum=${forewordBoardDTO.getBoardNumber()}" method="post"> 
                           	 		<textarea class="form-control" id= "commentContents"  name="commentContents" rows="5" placeholder="댓글을 입력해주세요" maxlength="999" oninput="limitMaxLength(this);" name="contents" style="overflow-y: auto;"></textarea>
                        		
                        		 	 <!-- 버튼 -->
                        			<div class="d-grid gap-2 d-md-flex justify-content-md-end">
                        				<button type="submit" class="btn btn-secondary btn-sm mt-2">등록</button>                       	
                        			</div>
                        		</form>	
                        	</div>
                        	

					
						</div> <!--  댓글창 끝 -->
					
				
			 		 <!-- 댓글목록-->
						<div class="inner m-auto w-800 mt-3 border"> 
						<c:forEach var="c" items="${commentList}" varStatus="afterNum" >
							 <div class="container mb-3 border">	  
								  <div class="row">
								    <div class="col-sm-3">
								      <span>
								      		<img src="../resources/images/${commentList[afterNum.index].getImageFileName()}" alt= "${commentList[afterNum.index].getImageFileName()}" id=" ${commentList[afterNum.index].getImageFileName()}" style="width:30px; heigh:30px; border-radius:50%; object-fit:cover;">
								     </span>
								      <span>${commentList[afterNum.index].getNickname()}</span>
								    </div>
								    <div class="col-sm-9">
								      <div class="row">
								        <div class="col">
								          ${commentList[afterNum.index].getCommentContents()}
								        </div>
								      </div>
								    </div>
								  </div>	
								</div>
							</c:forEach>
						</div> <!-- 댓글목록 끝 -->
		
		
			</section>
		</main>
		<!-- main -->
		 
		<!-- footer include 영역 -->
		<jsp:include page="./common/footer.jsp"></jsp:include>
	</div><!-- end of wrap -->
	    

		
	<script src="<c:url value='./js/bootstrap.min.js' />"></script>
	<script src="<c:url value='./js/bootstrap.bundle.min.js' />"></script>
	<script src="<c:url value='./js/jquery.js' />"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
	<script src="<c:url value='./js/custom.js' />"></script>
	<script src="<c:url value='./js/jquery.js' />"></script> 
	<script>
	
		
/*     	var likeForm = document.getElementById("likeForm");
    	var likeButton = document.getElementById("likeButton");

    	likeButton.addEventListener("click", function() {
        	likeForm.submit();
   		 });  
	     */
 
    	
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
  		
		
		
		
  		/* 평점 별점 표시  */
 /*  		  let totalScoreInput = document.querySelector('#totalScore');
    	  let totalScore = parseFloat(totalScoreInput.value);
    	  let star = document.querySelector('#score1 .fillStar');
    	  let filledStars = Math.round(totalScore);

    	  for (let i = 0; i < filledStars; i++) {
    		  star.textContent += '★';
    		} */

    	// 값 받기
    	/*console.log(`전체 별점: ${totalScore}`); */
	</script>

</body>
</html>