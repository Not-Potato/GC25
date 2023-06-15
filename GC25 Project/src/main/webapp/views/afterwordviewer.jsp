<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- jstl 사용 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 현재 페이지 정보 -->
<c:set var="contextPath"  value="${pageContext.request.contextPath}" />
<%
	request.setAttribute("pageName", "afterword");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>GC25 | 수강후기 상세보기</title>
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
							  'opsz' 48
	}
	</style>
</head>
<body>
	<div id="wrap" class="w-100">
		<jsp:include page="./common/header.jsp"></jsp:include>
	

 				<div class="container my-5">
	    			<div class="bg-light p-5 rounded">
						<h3>${afterwordBoardDTO.getTitle() }</h3>
						<div>${afterwordBoardDTO.getContents()}</div>
						<div>강사 : ${afterwordBoardDTO.getTeacherName() }</div>
						<div>강사 점수 : ${afterwordBoardDTO.getTeacherScore() }</div>
						<div>기간 : ${afterwordBoardDTO.getOpenDate() } - ${afterwordBoardDTO.getEndDate() }</div>
						<div>recommend: ${afterwordBoardDTO.getRecommend() } </div>
						<div>views:  ${afterwordBoardDTO.getViews() } </div>
	
	    			</div><!-- end of class bd-light p-5 rounded -->
	    		</div><!-- end of class container my-5 -->
	    		
	     <hr>
	    		
	    		<!-- 좋아요 아이콘 보여주기 -->
	    		<div class="recommend"> 
	    			<button type="submit" form ="likeForm">
	    				<span class="material-symbols-outlined">
	    					sentiment_very_satisfied
	    				</span>
	    			</button>
	    			<div id=likeCount>좋아요 : ${afterwordBoardDTO.getRecommend()}</div>
	    			
	    			<form id="likeForm" action="${contextPath}/afterwordboard/recommend.do?boardNum=${afterwordBoardDTO.getBoardNumber()}" method="post">
   						<input type="hidden" name="postId" value="123">
					</form>
	    		
	    		</div>
	   
	    <hr>
	    

			<!-- 댓글창 -->
		<div class="container">
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

	</div><!-- end of wrap -->
		<jsp:include page="./common/footer.jsp"></jsp:include>

	    

		
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