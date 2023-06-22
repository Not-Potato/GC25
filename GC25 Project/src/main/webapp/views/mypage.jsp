<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.gc25.dao.MemberDAO" %>
<%@ page import="com.gc25.dto.MemberDTO" %>
<%@ page import="com.gc25.service.MemberService" %>
<%@ page import="java.io.PrintWriter"%>
<%

	
	String memberEmail = (String)session.getAttribute("memberEmail");
	String memberNickname = (String)session.getAttribute("memberNickname");

	if (memberEmail != null) {
   	 //로그인이 되어있는 경우
		memberEmail=(String)session.getAttribute("memberEmail");
   	 
	} else {
    // 로그인 되어있지 않은 경우
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('로그인이 필요한 페이지입니다.')");
		script.println("location.href='/mem/login.do';");
		script.println("</script>");
		script.close(); //오류생기면 이 jsp 페이지 종료
	}
	String fileName = (String)session.getAttribute("fileName");
	String memberImageFileName = (String)session.getAttribute("memberImageFileName");
	System.out.println("jsp memberImageFileName : " + memberImageFileName);
	

	request.setAttribute("pageName", "mypage");
%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1"> 
	<title>GC25-마이페이지</title>
	<link href="/resources/css/reset.css" rel="stylesheet">
	<link href="/resources/css/custom.css" rel="stylesheet">
	<link rel="shortcut icon" type="image/x-icon" href="/resources/images/mini_logo.png">
	<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.css" />
	<script src="https://code.jquery.com/jquery-3.7.0.js" integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>	
	
</head>
<body>
	<jsp:include page="./common/header.jsp"></jsp:include>
	
	<div class="container"> 
		<div class="title">
			<h2 class="mp_h2">마이페이지</h2>
			<p>나의 소중한 정보를 확인하고 원한다면 변경해 보세요!</p>
		</div>
		
		<div class="bodyContainer">
			<div class="inner">
				<div class="my-page">
					<div id="profile" class="mp-profile">
						<img src="../resources/images/profileimages/<%= memberImageFileName %>" alt="<%= memberImageFileName %>">
						
						<form method="post" class="image-form" enctype="multipart/form-data" name="proflieImgChange" id="proflieImgChange" action="/mem/proflieImgChange"> <!-- 파일을 업로드 할 땐 항상 post방식 get은 사용 할 수 없다. -->
							<input class="upload-name" value="첨부파일" placeholder="첨부파일">
							<input type="file" id="file" name="profile" accept="image/*">
							<label for="file">파일찾기</label> 
							<input type="reset" class="reset-btn" value="초기화">
						</form>
						<!-- <div class="edit-del"> 
							<button class="profileBtn" onclick="profileChange()">수정</button> 
							<form method="post" enctype="multipart/form-data" name="/mem/proflieImgRemove" action="/mem/proflieImgRemove">
							<button id="removeBtn" name="imgRemoveBtn" onclick="imgdel();"> 삭제 </button>
							</form>
						</div> -->
					</div>
		
			        <div id="inputBox" class="input-box">
						<p>
			                <label for="">이메일</label>
			                <input type="text" name="memberEmail" id="memberEmail" placeholder="${memberEmail}" readonly>
						</p>
			            <p>
			                <label for="">비밀번호</label>
			                <input type="password" name="memberPwd" onkeyup="passwordCheck()" id="memberPwd" placeholder="비밀번호를 입력하세요." >
			                <span id="passwordRegCheckMessage"><i class='xi-close-circle no'></i>비밀번호는 8~12자로 반드시 대/소문자 및 숫자, 특수문자를 포함해야 합니다.</span>
			            </p>
			            <p>
			                <label for="">비밀번호 확인</label>
			                <input type="password" name="memberPwd2" onkeyup="passwordCheck()" id="memberPwd2" placeholder="비밀번호를 다시 입력하세요." >
			                <span id="passwordCheckMessage"><i class='xi-close-circle no'></i>비밀번호가 서로 일치하지 않습니다.</span>
			            </p>
			            <p class="nickname">
			                <label for="">닉네임</label>
			                <input type="text" name="memberNickname" id="memberNickname" placeholder="${ memberNickname }">
			                <button class="nicknameCheckBtn" onclick="nickcheck()">중복 확인</button>
			                <span id="nicknameCheckMessage"><i class='xi-close-circle no'></i>중복 확인 후 닉네임 변경이 가능합니다.</span>
			            </p>
			            <p class="btn-group">
			                <button id="mypageSubmitBtn" name="mypageSubmitBtn" onclick="mypageSubmit()">적용</button>
							<button type="button" class="btn btn-quit" onclick="withdrawal()">회원 탈퇴</button>
			            </p>
			        </div>
				</div>
			</div>
		</div>
	</div>    
	<jsp:include page="./common/footer.jsp"></jsp:include>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
	<script type="text/javascript">

	let ok = "<i class='xi-check-circle ok'></i>";
	let not_ok = "<i class='xi-close-circle no'></i>";
	
	$("#file").on('change',function(){
		  var fileName = $("#file").val();
		  $(".upload-name").val(fileName);
	});
	
	var nicknameOverlapCheck = false;
	//비밀번호 일치 확인
	function passwordCheck(){
		console.log("passwordRegCheckMessage")
		let passwordReg =/^(?=.[A-Za-z])(?=.*\d)(?=.*[@!%*#?&])[A-Za-z\d@!%*#?&]{8,12}$/;
		let memberPwd = $("#memberPwd").val();
		let memberPwd2 = $("#memberPwd2").val();
		if (!passwordReg.test(memberPwd)) {
			$("#passwordRegCheckMessage").html(not_ok + "비밀번호는 8~12자로 반드시 대/소문자 및 숫자, 특수문자를 포함해야 합니다.");
			return false;
		} else {
			$("#passwordRegCheckMessage").html(ok + "조건을 충족합니다!");
		}
		
		if(memberPwd !== memberPwd2) {
			$("#passwordCheckMessage").html(not_ok + "비밀번호가 서로 일치하지 않습니다.");
		} else {
			$("#passwordCheckMessage").html(ok + "비밀번호와 일치합니다!");
		}
	
	}
	
	//닉네임 중복확인 
	
	
	function nickcheck() {
	
		event.preventDefault();
		nicknameOverlapCheck = true;
		
		let memberNickname = $("#memberNickname").val();
		console.log(memberNickname);
		
		if(memberNickname=="") {
			alert("닉네임을 입력하세요.");
			return;
			}
		
		$.ajax({
			type :"post",
			async : true,
			url : "/mem/nickcheck.do",
			data : { 
				memberNickname : memberNickname,
				nicknameOverlapCheck : nicknameOverlapCheck
			},
			success : function(result) {
				console.log(result);
				if(result == 1) {
					$("#nicknameCheckMessage").html(ok + "사용할 수 있는 닉네임입니다.");
					console.log("통신하니:"+result);
				} else {
					$("#nicknameCheckMessage").html(not_ok + "사용할 수 없는 닉네임입니다.");
					console.log("통신하니:"+result);
				}
			},
			error : function(result)  {
				alert("오류가 발생했습니다.");
			},
			complete : function(result) {
				
			}
		});
	}
	
/* 	function profileChange() {
	
		event.preventDefault();
		
		var userResponse = confirm("이미지를 변경하시겠습니까?");
			
			if(userResponse){
				userResponse=true;
				console.log(userResponse);
				changeSubmit(userResponse);
				
			}else{
				userResponse=false;
				console.log(userResponse);
				alert('이미지 변경이 취소되었습니다.');
				
			}
		
	} */
	
	
	function changeSubmit(userResponse) {
		
		console.log("changeSubmit : " + userResponse);
		var form = new FormData(document.getElementById("proflieImgChange"));
		form.append("userResponse",userResponse);
		
		$.ajax({
				type :"post",
				async : true,
				url : "/mem/imgchange.do",
				processData: false,
				contentType: false,
				data: form,
				success : function(result) {
					console.log("result: "+result);
					if(userResponse){
						alert("프로필 사진 변경이 완료되었습니다.");
					}
				},
				error : function(result)  {
					alert("프로필 사진 변경 관련 오류가 발생했습니다.");
				},
				complete : function(result) {
					
				}
			});
	}
/* 	function imgdel(){
	
		event.preventDefault();
		
		var userResponse = confirm("이미지를 삭제하시겠습니까?");
			
			if(userResponse){
				userResponse=true;
				console.log("이미지 삭제:"+userResponse);
				
			
			}else{
				userResponse=false;
				console.log(userResponse);
				alert('삭제 취소 되었습니다.');
				history.back();
			}
			
			$.ajax({
				type :"post",
				async : true,
				url : "/mem/imgdel.do",
				data : { 
					userResponse : userResponse
				},
				success : function(result) {
					if(userResponse){
						console.log(result);
						alert('삭제 완료 되었습니다.');
					}
				},
				error : function(result)  {
					alert("오류가 발생했습니다.");
				},
				complete : function(result) {
					
				}
			});
		
	} */
	function mypageSubmit() {
		
			event.preventDefault();
			
			let memberPwd = $("#memberPwd").val();
			let memberPwd2 = $("#memberPwd2").val();
			let memberNickname = $("#memberNickname").val();
			
			console.log(memberPwd);
			console.log(memberPwd2);
			console.log(memberEmail);
			
			
			if(memberPwd=="") {
				alert("비밀번호를 입력하세요.");
				return;
			}
			
			/* 수정 + 적용 버튼 합치기 test 중 */
			if ($(".upload-name").val() != "첨부파일" && memberPwd == memberPwd2
					&& $("#passwordRegCheckMessage").text() == "조건을 충족합니다!"
					&& $("#nicknameCheckMessage").text() != "사용할 수 없는 닉네임입니다.") {			
				console.log('됨?');
				changeSubmit(true);
			}
			
			$.ajax({
				type :"post",
				async : true,
				url : "/mem/mpsubmit.do",
				data : {
					memberPwd : memberPwd,
					memberPwd2 : memberPwd2,
					memberNickname : memberNickname,
					nicknameOverlapCheck : nicknameOverlapCheck
					
				},
				success : function(result) {
					console.log(result);
					if(result == 1) {
					  	alert ("회원정보수정이 완료되었습니다.");
					  	console.log("수정완료"+result);
					  	location.href="/main";
					}else if(result == -100){
						  alert("로그인을 해 주세요.");
						  console.log(result);
					}else if(result == -200){
						  alert("중복된 닉네임입니다.");
						  console.log(result);
					}else if(result == -300){
						  alert("닉네임 중복체크를 해 주세요.");
						  console.log(result);
					}else if(result == -400){
						  alert("비밀번호를 입력 해 주세요.");
						  console.log(result);
					}else if(result == -500){
						  alert("비밀번호가 같지 않습니다.");
						  console.log(result);
					}else if(result == -600){
						  alert("비밀번호는 반드시 영문자,숫자,특수문자를 포함하여 8~12자여야 합니다.");
						  console.log();
					}else {
						alert("수정에 실패했습니다. 잠시후 다시 시도해 주세요.");
						console.log();
						location.reload();
					}
					
				},
				error : function(result)  {
					alert("오류가 발생했습니다.");
					console.log("왜 오류??: "+ result);
				},
				complete : function(result) {
					
				}
			});
		
		}
		
		function withdrawal() {
			if(confirm("정말 탈퇴하시겠습니까?")) {
				// 사용자: 예
				location.href = '/mem/withdrawal.do';
			} else {
				// 사용자: 아니오
			}
		}
	</script>


</body>
</html>