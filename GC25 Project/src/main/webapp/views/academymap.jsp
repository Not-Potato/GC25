<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- jstl 사용 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 현재 페이지 정보 -->
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<c:set var="academyList" value="${academyList}"/>
<c:set var="pageNum" value="${pageNum}"/>
<c:set var="searchValue" value="${searchValue}"/>
<%
	request.setAttribute("pageName", "map");
%>


<!-- 위도 경도로 지도 위치 나타내기 -->
<!DOCTYPE html>
<html lang="ko-KR">
<head>
	<meta charset="UTF-8">
	<title>GC25</title>
	<!-- 커스텀.css / reset.css / 파비콘 / x-icon -->
	<link href="<c:url value='/resources/css/custom.css' />" rel="stylesheet">
	<link href="<c:url value='/resources/css/reset.css' />" rel="stylesheet">
	<link rel="shortcut icon" type="image/x-icon" href="/resources/images/mini_logo.png">
	<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
	
<!--  	<link rel="stylesheet" type="text/css" href="../css/academymap2.css">  -->
   		<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script> 
   		

</head>
<body>
	<div id="wrap">
 		<jsp:include page="../views/common/header.jsp"></jsp:include>
   		<!-- header include 영역 -->
 		
 		<div class="map-page">
 			<div class="inner">
 				<div class="title">
				    <h2>학원 찾기</h2>
				    <p>궁금한 학원을 키워드로 검색해보세요!</p>
				</div>
 			
 			
 				<div class="search-of-map">
 					<div id="static-map"></div>	
 					
 					<form id="searchForm" action="${contextPath}/academy/map.do?searchValue=${searchValue}">
						<input id="searchValue"  name="searchValue" type="text" placeholder="검색어를 입력하세요  ex)안양 or IT or 컴퓨터학원" > 
						<input class="search-btn" type="submit" value="검색">
					</form>
					
<c:choose>
	<c:when test="${empty academyList}">
					<div class="not-found">
						<p>학원 정보를 조회해 보세요!</p>
					</div>
	</c:when> 
						
	<c:otherwise>
					<div class="academy-found"> 
						<div>
							<h4>
								<i class="xi-school"></i>
								학원 명
							</h4>
							<p>${academyList[pageNum-1].academyName}</p>
						</div>
						<div>
							<h4>
								<i class="xi-call"></i>
								전화번호
							</h4>
							<p>${academyList[pageNum-1].academyTel}</p>
						</div>
						<div>
							<h4>
								<i class="xi-maker"></i>
								학원 주소
							</h4>
							<p>도로명주소: ${academyList[pageNum-1].academyRodeAddress}</p>
							<p>일반 주소: ${academyList[pageNum-1].academyAddress}</p>
						</div>
						  
 						<div>
							<h4>
								<i class="xi-home"></i>
								학원 홈페이지
							</h4>
							<%-- <p>${academyList[pageNum-1].a_url}</p>  --%>
						</div> 
						
						<div>
							<h4>
								<i class="xi-trophy"></i>
								학원 평점
							</h4>
							<div class="map-in-score">
								<p id="academyAvgScore">${academyList[pageNum-1].academyAvgScore} 점</p> 
								<div class="starBox">
									<span class="emptyStar">
										★★★★★ 
										<span class="fillStar" style="width: ${academyList[pageNum-1].academyAvgScore *10}%" >★★★★★</span>
									</span> 
								</div>
							</div>
						</div>
					</div>
					
					<nav aria-label="...">
						<ul class="pagination justify-content-center">
							
	 	<c:if test="${pageNum == 1 && pageNum < -1 }">
							<li class="page-item disabled"><a class="page-link">◀</a></li>	    
		</c:if >	 
										
		<c:if test="${pageNum != 1}">
							<li class="page-item"><a class="page-link" href="${contextPath}/academy/map.do?pageNum=${pageNum-1}&searchValue=${searchValue}">◀</a></li>
		</c:if>  

		<c:forEach var="page" begin="${startPage}" end="${endPage}" step="1">	
			<c:choose> 
				<c:when test="${ page == pageNum }"> 
							<li class="page-item"><a class="page-link active" href="#">${page}</a></li>
				</c:when>
				<c:otherwise>   
							<li class="page-item"><a class="page-link" href="${contextPath}/academy/map.do?pageNum=${page}&searchValue=${searchValue}">${page}</a></li>
				</c:otherwise>
			</c:choose>	    
		</c:forEach>	
										   
								    	 
		<c:if test="${pageNum == endPage && pageNum > endPage}">   
							<li class="page-item"><a class="page-link disabled" href="#">▶</a></li>
		</c:if >
		<c:if test="${ pageNum != endPage}">
							<li class="page-item"><a class="page-link" href="${contextPath}/academy/map.do?pageNum=${pageNum+ 1}&searchValue=${searchValue}">▶</a></li>
		</c:if>	     
						</ul>
					</nav>
	</c:otherwise> 
</c:choose>
				</div>
			</div>  <!-- end of inner -->
		</div>  <!-- end of map-page -->
					
	</div>  <!-- end of wrap -->
					
	<jsp:include page="../views/common/footer.jsp"></jsp:include>  
    
    
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=3f9658f5229a3c587b3729aa940473f7"></script>
	<script>
	
	
		var academyNameElement = document.getElementById("academyName");
		
		var academyName = academyNameElement ? academyNameElement.textContent : "";
		var academyY = "${academyList[pageNum-1].academyY}";
		var academyX = "${academyList[pageNum-1].academyX}";
		
		var marker;
		
		if (academyName !== ""){
		
		// 이미지 지도에 표시할 마커입니다
			marker = {
		    		position: new kakao.maps.LatLng(academyY, academyX), 
		   			 text: academyName // text 옵션을 설정하면 마커 위에 텍스트를 함께 표시할 수 있습니다
			};
		
			
		var staticMapContainer  = document.getElementById('static-map'), // 이미지 지도를 표시할 div
		    staticMapOption = { 
		        center: new kakao.maps.LatLng(academyY, academyX), // 이미지 지도의 중심좌표
		        level: 3, // 이미지 지도의 확대 레벨
		        marker: marker // 이미지 지도에 표시할 마커
		    };
		
		}else {
			marker = {
		           	//position: new kakao.maps.LatLng(37.39876608892914, 126.9209608170776),
		            //text: ""
		        };
		var staticMapContainer  = document.getElementById('static-map'), // 이미지 지도를 표시할 div
	    staticMapOption = { 
	        center: new kakao.maps.LatLng(37.39876608892914, 126.9209608170776), // 이미지 지도의 중심좌표
	        level: 3, // 이미지 지도의 확대 레벨
	        marker: marker // 이미지 지도에 표시할 마커
		    };	
		}
	
		// 이미지 지도를 생성합니다
		var static_map = new kakao.maps.StaticMap(staticMapContainer, staticMapOption);
	
	</script>
</body>
</html>