<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.gc25.dao.MemberDAO" %>
<%@ page import="com.gc25.dto.MemberDTO" %>
<%@ page import="com.gc25.service.MemberService" %>
<%@ page import="java.io.PrintWriter"%>
<%
	request.setAttribute("pageName", "mypage");
%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1"> 
	<title>GC25-마이페이지</title>
	<script src="https://code.jquery.com/jquery-3.7.0.js" integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>	
	<!--부트스트랩 css link-->
	<link rel="stylesheet" href="../css/bootstrap.min.css">
	<!--커스텀 css link-->
	<link rel="stylesheet" href="../css/custom.css">
	
	<!-- <link rel="stylesheet" href="../css/mypage.css"> -->
	
</head>
<body>
<%@ include file = "./common/header.jsp" %>
<%
	
	String memberEmail = (String)session.getAttribute("memberEmail");

	if (memberEmail != null) {
   	 //로그인이 되어있는 경우
		memberEmail=(String)session.getAttribute("memberEmail");
   	 
	} else {
    // 로그인 되어있지 않은 경우
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('로그인이 필요한 페이지입니다.')");
		script.println("location.href='/views/login.jsp';");
		script.println("</script>");
		script.close(); //오류생기면 이 jsp 페이지 종료
	}
	
	String memberImageFileName2 = (String)session.getAttribute("memberImageFileName");
	System.out.println("jsp memberImageFileName2 : " + memberImageFileName2);
%>

<div class="container"> 
	 <h2 class="mp_h2">마이페이지</h2>
	 <div class="bodyContainer"> 
      <div id="profile">
        <form method="post" enctype="multipart/form-data" name="proflieImgChange" id="proflieImgChange" action="/mem/proflieImgChange"> <!-- 파일을 업로드 할 땐 항상 post방식 get은 사용 할 수 없다. -->
        	<img src="../resources/images/<%= memberImageFileName2 %>" alt="기본이미지" id="profileImg" >
        	<input type="file" name="profile" id="">
        	<button class="profileBtn" onclick="profileChange()">수정</button>
        </form>
        <form method="post" enctype="multipart/form-data" name="/mem/proflieImgRemove" action="/mem/proflieImgRemove">
        <button id="removeBtn" name="imgRemoveBtn" onclick="imgRemove();"> 삭제 </button>
        </form>
      </div>
      <div id="inputBox">
         <table>
             <tr>
      			<td>이메일</td>
                <td>
                    <input type="text" name="memberEmail" id="memberEmail" placeholder="${memberEmail}" readonly>
               </td>
            </tr>
             <tr>
                 <td>비밀번호</td>
                 <td>
                     <input type="password" name="memberPwd" onkeyup="passwordCheck()" id="memberPwd" placeholder="비밀번호를 입력하세요." >
                </td>
                 <td>
                   	 <h5 id="passwordRegCheckMessage"></h5>
                 </td>
            </tr>
            <tr>     
                 <td>비밀번호 확인</td>
                 <td>
                   	<input type="password" name="memberPwd2" onkeyup="passwordCheck()" id="memberPwd2" placeholder="비밀번호를 다시 입력하세요." >
                 </td>       
                 <td>
                    <h5 id="passwordCheckMessage"></h5>
                 </td>   
            </tr>  
             <tr>
                 <td>닉네임</td>
                 <td>  
                      <input type="text" name="memberNickname" id="memberNickname" placeholder="${memberNickname}">
                 </td>  
                 <td>
                      <button class="nicknameCheckBtn" onclick="nicknameCheck()">중복 확인</button>
                 </td> 
               		 <td>
                        <div id="nicknameCheckMessage"></div>
                    </td>
             </tr>
              <tr>
                  <td>
                       <div id="nicknameCheckMessage"></div>
                  </td>
              </tr>
                
            </table>
		 </div>
	  </div>
	  <div>
		   <button id="mypageSubmitBtn" name="mypageSubmitBtn" onclick="mypageSubmit()">적용</button>
	 </div>
</div>    
<%@ include file = "./common/footer.jsp" %>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script type="text/javascript">

var nicknameOverlapCheck = false;
//비밀번호 일치 확인
function passwordCheck(){
	console.log("passwordRegCheckMessage")
	let passwordReg =/^(?=.[A-Za-z])(?=.*\d)(?=.*[@!%*#?&])[A-Za-z\d@!%*#?&]{8,12}$/;
	let memberPwd = $("#memberPwd").val();
	let memberPwd2 = $("#memberPwd2").val();
	if (!passwordReg.test(memberPwd)) {
		$("#passwordRegCheckMessage").html("비밀번호는 8~12자로 반드시 대,소문자,숫자,특수문자를 포함해야 합니다.");
		return false;
	} else {
		$("#passwordRegCheckMessage").html("");
	}
	
	if(memberPwd !== memberPwd2) {
		$("#passwordCheckMessage").html("비밀번호가 서로 일치하지 않습니다.");
	} else {
		$("#passwordCheckMessage").html("");
	}

}

//닉네임 중복확인 


function nicknameCheck() {

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
		url : "/mem/nicknameCheck",
		data : { 
			memberNickname : memberNickname,
			nicknameOverlapCheck : nicknameOverlapCheck
		},
		success : function(result) {
			console.log(result);
			if(result == 1) {
				$("#nicknameCheckMessage").text("사용할 수 있는 닉네임입니다.");
				console.log("통신하니:"+result);
			} else {
				$("#nicknameCheckMessage").text("사용할 수 없는 닉네임입니다.");
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


function profileChange() {

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
			history.back();
		}
	
}

function changeSubmit(userResponse) {
	console.log("changeSubmit : " + userResponse);
	var form = new FormData(document.getElementById("proflieImgChange"));
	form.append("userResponse",userResponse);
	  
	$.ajax({
			type :"post",
			async : true,
			url : "/mem/proflieImgChange",
			processData: false,
			contentType: false,
			data: form,
			success : function(result) {
				console.log(result);
				if(userResponse){
					alert("이미지 전송이 완료되었습니다.");
					
				}
			},
			error : function(result)  {
				alert("오류가 발생했습니다.");
			},
			complete : function(result) {
				
			}
		});
}
function imgRemove(){

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
			url : "/mem/proflieImgRemove",
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
	
}
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
	
		$.ajax({
			type :"post",
			async : true,
			url : "/mem/mypage",
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
				  	location.href="/index.jsp";
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
					alert("가입에 실패했습니다. 잠시후 다시 시도해 주세요.");
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
	
</script>


</body>
</html>