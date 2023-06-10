<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- jstl 사용 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 현재 페이지 정보 -->
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<c:set var="academyList" value="${requestScope.academyList}"/>

<%
	request.setAttribute("pageName", "academymap");
%>
<!DOCTYPE html>
<html lang="ko-KR">
<head>
	<meta charset="UTF-8">
	<title> 학원 검색 </title>
	<link rel="stylesheet" href="../css/bootstrap.min.css">
	<link rel="stylesheet" href="../css/custom.css">
<!--  	<link rel="stylesheet" type="text/css" href="../css/academymap2.css">  -->
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
   		<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script> 

</head>
<body>
	<div id="wrap" class="w-100">
 		<jsp:include page="../views/common/header.jsp"></jsp:include>
   		<!-- header include 영역 -->
 
		
		<section id="" class="mt-5">
			<div class="inner m-auto">
				<h2 class="text-center p-4 bg-light border rounded-pill mb-4 m-auto text-primary w-800">학원 위치 보기</h2>
			</div>
		</section> 
		
		<main id="container" class="main">
			<section id="content">
			
				<!-- 지도 출력 -->
				
				<div class="inner m-auto">
					<div class="position-relative">
						<div class="col-12">
							<div style="display:flex; justify-content:center;">
								<div id="map" style="width:70%; height:470px;"></div>	
							</div>
							<br>
							<br>
							
							<div style="display:flex; justify-content:center;">
								<input id="search_value" type="text" onkeyup ='searchmap()' style="width: 600px" style="width: 600px" placeholder="검색어를 입력하세요  ex) 안양 컴퓨터학원" > 
								<p class="text-center"><a class="btn btn-primary" href="/academymap/listAcademy.do">검색</a></p>
							</div>
							<br>
							<br>
						</div>
					</div>
				</div>	
				
	
		
				<!-- 설명 출력 -->
				
				<div class="container text-center">
					<c:choose>
						<c:when test="${empty academyList}">
			            			<p>결과 없음! 검색어를 입력해주세요.</p>
						</c:when> 
						
					 	<c:otherwise>
							<c:forEach var="academy" items="${academyList}">
					  			<div class="row">
					   	 			<h3 class="col-6 col-md-4">학원명</h3>
					   				<div class="col-md-8">
		            					<p>${academy.academyName}</p>
					    			</div>
					  			</div>
					 			<br>
					 			<br>
					
					
					  			<div class="row">
					    			<h3 class="col-6 col-md-4">학원 주소</h3>
					    			<div class="col-md-8">
		            					<p>${academy.academyAddress}</p>
	       				 		 
					    			</div>
					 			</div>
					   			<br>
					  			<br>
					  
					    		<div class="row">
					    			<h3 class="col-6 col-md-4"> 학원 평점</h3>
					    			<div class="col-md-8">	
					    				<p>${academy.academyAvgScore}</p>
					    			</div>
					 	 		</div>
								<hr>
					 		</c:forEach>
					 		
					
							<nav aria-label="...">
								<ul class="pagination justify-content-center">
							
									
		<%-- 								<c:if test="${pageNum == pagePerScreen}">	
											console.log(pageNum); 
											console.log(pagePerScreen); 
											    <li class="page-item disabled"><a class="page-link">Previous</a></li>	    
										</c:if >	 
										<c:if test="${pageNum < pagePerScreen}">
											<li class="page-item"><a class="page-link" href="${contextPath}/academymap?pageNum=${ startPage - 1 }">Previous</a></li>
										</c:if> 
									 --%>
								
									<c:forEach var="page" begin="${ startPage }" end="${endPage}" step="1">	
										<c:choose> 
											<c:when test="${ page == pageNum }"> 
											    <li class="page-item active"><a class="page-link" href="#">${page}</a></li>
											</c:when>
											<c:otherwise>   
											    <li class="page-item"><a class="page-link" href="${contextPath}/academymap2?pageNum=${ page }">${page}</a></li>
										   </c:otherwise>
										</c:choose>	    
									</c:forEach>	
										   
								    	 
<%-- 										<c:if test="${endPage != totalPage}">   
											<li class="page-item disabled"><a class="page-link" href="#">Next</a></li>
										</c:if >
										<c:if test="${!endPage != totalPage}">
											<li class="page-item"><a class="page-link" href="#">Next</a></li>
										</c:if>	     --%>
							  </ul>
							</nav>
					 	</c:otherwise> 
					</c:choose>
				</div><!-- 설명 출력 끝 -->
					
			</section>
	 </main>
 
     	<jsp:include page="../views/common/footer.jsp"></jsp:include>  
	</div><!-- end of wrap -->	  
    
    
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=3f9658f5229a3c587b3729aa940473f7&libraries=services"></script>    
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>	
   <script>
	// 마커를 클릭하면 장소명을 표출할 인포윈도우 입니다
	var infowindow = new kakao.maps.InfoWindow({zIndex:1});
	
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	    mapOption = {
	        center: new kakao.maps.LatLng(37.39884708067333, 126.92081108993685), // 지도의 중심좌표
	        level: 3 // 지도의 확대 레벨
	    };  
	
	
	function searchmap() {
		var searchValue = document.getElementById('search_value').value;
		ps.keywordSearch(searchValue, placesSearchCB); 
	};
	
	// 지도를 생성합니다    
	var map = new kakao.maps.Map(mapContainer, mapOption); 
	
	// 장소 검색 객체를 생성합니다
	var ps = new kakao.maps.services.Places(); 
	

	// 키워드로 장소를 검색합니다
	//ps.keywordSearch(search_value, placesSearchCB); 
	
	// 키워드 검색 완료 시 호출되는 콜백함수 입니다
	function placesSearchCB (data, status, pagination) {
	    if (status === kakao.maps.services.Status.OK) {
	
	        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
	        // LatLngBounds 객체에 좌표를 추가합니다
	        var bounds = new kakao.maps.LatLngBounds();
	
	        for (var i=0; i<data.length; i++) {
	            displayMarker(data[i]);    
	            bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x));
	        }       
	
	        // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
	        map.setBounds(bounds);
	    } 
	}
	

	// 지도에 마커를 표시하는 함수입니다
	function displayMarker(place) {
	    
	    // 마커를 생성하고 지도에 표시합니다
	    var marker = new kakao.maps.Marker({
	        map: map,
	        position: new kakao.maps.LatLng(place.y, place.x) 
	    });
	    
	    // 마커에 클릭이벤트를 등록합니다
	    kakao.maps.event.addListener(marker, 'click', function() {
	        // 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다
	        infowindow.setContent('<div style="padding:5px;font-size:12px;">' + place.place_name + '</div>');
	        infowindow.open(map, marker);
	    });  
	}
	
	// 마커를 담을 배열입니다
	var markers = [];
	// 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
	function addMarker(position, idx, title) {
	    var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
	        imageSize = new kakao.maps.Size(36, 37),  // 마커 이미지의 크기
	        imgOptions =  {
	            spriteSize : new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
	            spriteOrigin : new kakao.maps.Point(0, (idx*46)+10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
	            offset: new kakao.maps.Point(13, 37) // 마커 좌표에 일치시킬 이미지 내에서의 좌표
	        },
	        markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
	            marker = new kakao.maps.Marker({
	            position: position, // 마커의 위치
	            image: markerImage 
	        });

	    marker.setMap(map); // 지도 위에 마커를 표출합니다
	    markers.push(marker);  // 배열에 생성된 마커를 추가합니다

	    return marker;
	}	
	
	for(var i = 0; i < markers.length; i++) {
		console.log(markers[i]);
	}

	// 지도 위에 표시되고 있는 마커를 모두 제거합니다
/* 	function removeMarker() {
	    for ( var i = 0; i < markers.length; i++ ) {
	        markers[i].setMap(null);
	    }   
	    markers = [];
	}
	 */

	</script>
</body>
