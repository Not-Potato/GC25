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
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>GC25 | 수강후기 상세보기</title>
	<!-- 커스텀.css / reset.css / 파비콘 / x-icon -->
	<link href="<c:url value='/resources/css/custom.css' />" rel="stylesheet">
	<link href="<c:url value='/resources/css/reset.css' />" rel="stylesheet">
	<link rel="shortcut icon" type="image/x-icon" href="/resources/images/mini_logo.png">
	<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	
	<!-- 좋아요 아이콘 -->
	<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
	<style>
	.material-symbols-outlined {
	  font-variation-settings:
							  'FILL' 0,
							  'wght' 300,
							  'GRAD' 200,
							  'opsz' 48
	}
	</style>
</head>
<body>
	<div id="wrap" class="w-100">
		<jsp:include page="./common/header.jsp"></jsp:include>
		<!-- header include 영역 -->

		 <section id="" class="mt-5">
		 	<!-- 전체 리스트 보기 버튼 -->
 		 	 <div class="inner m-auto">
                <h2 class="text-center p-4 bg-light border rounded-pill mb-4 m-auto text-primary w-800">수강 후기</h2>
             </div>
             
          	<!-- 삭제 & 수정 버튼 -->
			<div class="inner m-auto w-800 mb-3">
				<div class="text-end mt-3">
				
				<%-- 현재 사용자 ID 세션에서 가져오기 --%>
				<%
				int memberNumber = (Integer) session.getAttribute("memberNumber");
				%>

				<%-- 글 작성자 ID 가져오기 --%>
				<%
				AfterwordBoardDTO afterwordBoardDTO = (AfterwordBoardDTO)session.getAttribute("forewordBoardDTO");
				int authorId = afterwordBoardDTO.getMemberNumber();
				%>
		
				<%-- 현재 사용자와 글 작성자가 일치할 경우에만 버튼 보여주기 --%>
				<% if (memberNumber == authorId) { %>
				  <div class="inner m-auto w-800 mb-3">
				    <div class="text-end mt-3">
				      <form class="btn btn-outline-primary me-2" action="${contextPath}/afterword/modify.do?boardNum=${afterwordBoardDTO.getBoardNumber()}"  method="post">
				      	<button type="submit" class="btn btn-outline-primary me-2">글 수정</button>
				      </form>
				      
				      <form class="btn btn-primary me-2" action="${contextPath}/afterword/delete.do?boardNum=${afterwordBoardDTO.getBoardNumber()}" method="post">
				      	<button type="submit" class="btn btn-outline-primary me-2">글 삭제</button>
				      </form>
				 
				    </div>
				  </div>
				<% } %>
			</div>
		</div> 	
 		 </section>
	
		  <main id="container" class="main">
		  		<section id="content">
		  			  
		  			<div class="inner m-auto w-800 mb-3">
 				 		<a href="${contextPath}/afterword/board.do?boardNum=${afterwordBoardDTO.getBoardNumber()}" class="BtnType SizeS btn_gray btn_back_list" onmousedown="DETAILPAGE.Detail.gaEvent('qst_detail', 'resume_total')"><button type="button" class="btn btn-outline-primary"> &lt; 전체 게시글</button></a>
 					</div>
		  			  
		  			 <!-- 게시글 제목 -->
		  			<div class="inner m-auto border border-2 rounded-2 w-800">
	 				 		<div class="container">	
	 				 			<div class= "row"> 
	 								<h2 class="col mt-5 mb-5" >${afterwordBoardDTO.getTitle()}</h2>
	 							</div>
	 				</div>	
	 				
	 				
					<!-- 게시글 정보 -->
					<div class="container mt-2 ml-1 mr-1">
						<div class="row">
							<div class="col-8"> 
								<img src="../resources/images/${afterwordBoardDTO.getImageFileName()}" alt="${afterwordBoardDTO.getImageFileName()}" style="width:30px; heigh:30px; border-radius:50%; object-fit:cover;">
								<span>${afterwordBoardDTO.getNickname()}</span>
								
							</div>
							<div class="col-4 d-flex justify-content-between">
								<div class= "align-self-center d-flex">
									<span class="material-symbols-outlined">edit</span>
									<div id=writeDate></div>
									<div id="writeDateValue" style="display: none;">${afterwordBoardDTO.getWriteDate()}</div>
								</div>	
								<div class= "align-self-center d-flex">
									<span class="material-symbols-outlined me-2">visibility</span>
									<span>${afterwordBoardDTO.getViews()}</span>
								</div>
								<div class= "align-self-center d-flex">
									<span class="material-symbols-outlined me-2">chat_bubble</span>
									<span>${afterwordBoardDTO.getCommentCount()}</span>
								</div>
							</div>
						</div>	
					</div>
	 							
	 							
					<!--게시글 내용-->
					<div class= "row"> 
							<div>
								<div class="col-12 mt-3 mb-3 p-3">${afterwordBoardDTO.getContents()}</div>
						</div>
					</div>				
	 				  
	 				  
	 				  
	 				
	 				
	 				<!-- 학원 정보 -->	
	 				<div class="container mt-3 p-3">
		 					<div class="row">
		 							<div class="col-8 text-center">
								  		<h3>${afterwordBoardDTO.getAcademyName()}</h3>
								  	</div>
								  	
								  	<div class="col-4"> 
								  		<div class="starBox">
								  			 <span class="emptyStar" id="score1">
	       									 	 ★★★★★
	      									 	<span class="fillStar"></span>
	        								 	<input type="hidden" value="${afterwordBoardDTO.getTotalScore()}" id="totalScore" name="totalScore">
	    									 </span>
								  		</div> <!-- end of starBox  -->
									</div>
		 					</div>
	 				</div>  
	 				  
	 			<!-- 수강 후기 정보 -->		  
	 			<div class="container mt-3 mb-5">
					  <div class="row">
					    <div class="col text-center" style="font-weight: bolder;"> 과정구분 : </div> 
					    <div class="col text-center">${afterwordBoardDTO.getCourse()}</div>
					    
					    <div class="col text-center" style="font-weight: bolder;"> 강사명 : </div>
					    <div class="col text-center">${afterwordBoardDTO.getTeacherName()}</div>
					    
					    <div class="col text-center" style="font-weight: bolder;"> 개강일 : </div> 
					    <div class="col text-center">${afterwordBoardDTO.getOpenDate()}</div>
					    
					    <div class="col text-center" style="font-weight: bolder;"> 종강일 : </div> 
					    <div class="col text-center">${afterwordBoardDTO.getEndDate()}</div>
					    
					    <div class="col text-center" style="font-weight: bolder;"> 전공여부 : </div> 
					    <div class="col text-center">${afterwordBoardDTO.getMajor()}</div>
					    
					    <div class="col text-center" style="font-weight: bolder;"> 유/무상여부 : </div> 
					    <div class="col text-center">${afterwordBoardDTO.getCost()}</div>
					    
					    <div class="col text-center" style="font-weight: bolder;"> 전체 만족도 : </div> 
					    <div class="col text-center">${afterwordBoardDTO.getTotalScore()}</div>
					    
					    <div class="col text-center" style="font-weight: bolder;"> 강사 만족도 : </div> 
					    <div class="col text-center">${afterwordBoardDTO.getTeacherScore()}</div>
					    
					    <div class="col text-center" style="font-weight: bolder;"> 학원시설 만족도 : </div> 
					    <div class="col text-center">${afterwordBoardDTO.getFacilityScore()}</div>
					    
					    <div class="col text-center" style="font-weight: bolder;"> 커리큘럼 만족도 : </div> 
					    <div class="col text-center">${afterwordBoardDTO.getCurriculumScore()}</div>
					  </div>
				</div>	  
	 				  
	 			<!-- 좋아요 아이콘 보여주기 -->
				<div class="row w-800  mt-3 p-3">
						<div class="text-end">	
							<a href="${contextPath}/afterword/recommend.do?boardNum=${afterwordBoardDTO.getBoardNumber()}" onclick="likeForm.submit(); return false;">
								<span class="material-symbols-outlined me-2">thumb_up</span> 
							</a>
							<span class="icon-font">${afterwordBoardDTO.getRecommend()}</span>
							<form id="likeForm" style="display: none;" action="${contextPath}/afterword/recommend.do?boardNum=${afterwordBoardDTO.getBoardNumber()}" method="post">
								<input type="hidden" name="postId" value="123">
							</form>
						</div>
				</div>
	 		</div> <!-- end of 메인쪽 inner m-auto  -->	
	 			
	
	     <hr>
	    		
	    		<!-- 좋아요 아이콘 보여주기 -->
	    		<div class="recommend"> 
	    			<button type="submit" form ="likeForm">
	    				<span class="material-symbols-outlined">
	    					sentiment_very_satisfied
	    				</span>
	    			</button>
	    			<div id=likeCount>좋아요 : ${afterwordBoardDTO.getRecommend()}</div>
	    			
	    			<form id="likeForm" action="${contextPath}/afterword/recommend.do?boardNum=${afterwordBoardDTO.getBoardNumber()}" method="post">
   						<input type="hidden" name="postId" value="123">
					</form>
	    		
	    		</div>
	   
	    <hr>
	    

			<!-- 댓글창 -->
		    <div class="col-12">
		    	<!--  내용 -->
		    	<form id= "commetForm" action="${contextPath}/comment/abCommentRegister.do?boardNum=${afterwordBoardDTO.getBoardNumber()}" method="post"> 
                       	 		<textarea class="form-control" id= "commentContents"  name="commentContents" rows="5" placeholder="댓글을 입력해주세요" maxlength="999" oninput="limitMaxLength(this);" name="contents" style="overflow-y: auto;"></textarea>
                    		
                    		 	 <!-- 버튼 -->
                    			<div class="d-grid gap-2 d-md-flex justify-content-md-end">
                    				<button type="submit" class="btn btn-secondary btn-sm mt-2">등록</button>                       	
                    			</div>
                    		</form>	
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
		
		<div class="inner m-auto w-800">
			  <div class="row">
			    <div class="col-6 col-md-4">
			    	<p>사용자 프로필</p>
			    </div>
			    <div class=".col-md-8">
			     	 <form id= "commetForm" action="${contextPath}/comment/abCommentRegister.do?boardNum=${afterwordBoardDTO.getBoardNumber()}" method="post">  
				    	<input type="text" id= "commentContents"  name="commentContents" style="width: 1000px; height: 200px; overflow-y: auto;">
				   		<button type="submit" id="commentbtn" >등록</button>
			    	</form>
			    </div>
	 		 </div>
	
	
	
	 	 	<!-- 댓글 목록-->
	 	 		<div class="card" style="width: 18rem;">
		 			 <c:forEach var="c" items="${commentList}" varStatus="afterNum" >
						  <ul class="list-group list-group-flush">
						  	<li class="list-group-item">${commentList[afterNum.index].getNickname()}</li> 
						    <li class="list-group-item">${commentList[afterNum.index].getImageFileName()}</li> 
						    <li class="list-group-item">${commentList[afterNum.index].getCommentContents()}</li> 
					  	 </ul>
					</c:forEach>   
		   		</div>
			</div>

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
	
	  	var likeForm = document.getElementById("likeForm");
	    var likeButton = document.getElementById("likeButton");

	    likeButton.addEventListener("click", function() {
	        likeForm.submit();
	    });
	</script>

</body>
</html>