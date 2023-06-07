<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<!DOCTYPE html>
<html lang="ko-KR">
<head>
	<meta charset="UTF-8">
	<title> 학원 검색 </title>
	<link rel="stylesheet" type="text/css" href="../css/academymap2.css">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
   	<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>

</head>
<body>

   <div id="container">
        <div id="header">
          <p id="header-map">학원찾기</p>
        </div>
    
            <div id="map" style="width:500px; height:350px;"></div> 

        
        <div id="information"> 
        	<div id="filter-zone">
        			<div id="search">
						<input id="search_value" type="text" onkeyup ='searchmap()'placeholder="안양 컴퓨터학원"> 
					</div>
            		<select id="filter">
    					<option value="nono">정렬필터</option>
    					<option value="highest">모두보기 </option> 
    					<!--  academyDAO selectAll 매소드활용-->
    					<option value="lastest">최신순</option>
    					<option value="highest">평점높은순 </option>
					</select>
					
					
			</div>	
            <div id="academy-name">
                <p id="name">학원 명</p>
                <div id="name-content">
                	
            
                
                </div>
            </div>
            <div id="academy-address">
                <p id="address">학원 주소</p>
                <div id="address-content">경기도 안양시 만안구 안양4동 676-91</div>
            </div>
            <div id="academy-summary">
                <p id="summary">학원 소개</p>
                <div id="name-content">안양에 위치한 그린컴퓨터아특학원입니다. 그것은 아주 거지같기 떄문에 오시지 않는 편이 좋습니다.</div>
            </div>
        </div>
            <nav aria-label="...">
              <ul class="pagination">
                 <li class="page-item disabled">
                 <a class="page-link">Previous</a>
                 </li>
                 <li class="page-item"><a class="page-link" href="#">1</a></li>
                 <li class="page-item active" aria-current="page">
                 <a class="page-link" href="#">2</a>
                 </li>
                 <li class="page-item"><a class="page-link" href="#">3</a></li>
                 <li class="page-item">
                 <a class="page-link" href="#">Next</a>
                </li>
                </ul>
            </nav>
        </div>   
    
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=3f9658f5229a3c587b3729aa940473f7&libraries=services"></script>    
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>	
   <script>
	// 마커를 클릭하면 장소명을 표출할 인포윈도우 입니다
	var infowindow = new kakao.maps.InfoWindow({zIndex:1});
	
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	    mapOption = {
	        center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
	        level: 3 // 지도의 확대 레벨
	    };  
	
	var search_value;
	
	function searchmap() {
		searchValue = document.getElementById('search_value').value;
		ps.keywordSearch(searchValue, placesSearchCB); 
		console.log("");
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
	</script>
</body>
