<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="com.gc25.dao.MemberDAO" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="javax.naming.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1"> 
	<title>GC25</title>
	<script src="https://code.jquery.com/jquery-3.7.0.js" integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>	
	<!--부트스트랩 css link-->
	<link rel="stylesheet" href="../css/bootstrap.min.css">
	<!--커스텀 css link-->
	<link rel="stylesheet" href="../css/custom.css">
	<link rel="stylesheet" href="../css/signup.css">
	
</head>
<body>

<%@ include file = "./common/header.jsp" %>
	
	<div class="body_container">
       	<form>
        <h1>회원가입</h1>
        <div class="table_container">
            <table>
                <tr>
                    <td>이메일</td>
                    <td>
                        <input type="text" name="memberEmail" id="memberEmail" placeholder="이메일을 입력하세요." >
                    </td>
                    <td>
                        <button class="emailCheckBtn" onclick="emailCheck()">중복 확인</button>
                    </td>
                    <td>
                        <button class="emailHashCheckBtn" onclick="emailSend()">이메일 인증</button>
                    </td>
            
                </tr>
                <tr>
                	 <td>
                        <div id="emailCheckMessage"></div>
                    </td>
                </tr>
                <tr>
                    <td>비밀번호</td>
                    <td>
                        <input type="password" onkeyup="passwordCheck()" name="memberPwd" id="memberPwd" placeholder="비밀번호를 입력하세요." >
                    </td>
                    <td>
                    	<h5 id="passwordRegCheckMessage"></h5>
                    </td>
                </tr>
                 
                <tr>
                    <td>비밀번호 확인</td>
                    <td>
                        <input type="password" onkeyup="passwordCheck()" name="memberPwd2" id="memberPwd2" placeholder="비밀번호를 다시 입력하세요." >
                    </td>
                     <td>
                    	<h5 id="passwordCheckMessage"></h5>
                    </td>
                </tr>
                <tr>
                    <td>닉네임</td>
                    <td>
                        <input type="text" name="memberNickname" id="memberNickname" placeholder="닉네임을 입력하세요." >
                    </td>
                    <td>
                        <button class="nicknameCheckBtn" onclick="nicknameCheck()">중복 확인</button>
                    </td>
                </tr>
                  <tr>
                	 <td>
                        <div id="nicknameCheckMessage"></div>
                    </td>
                </tr>
                
            </table>
                <input class="reset_btn" type="reset" value="다시 입력">    
                <button class="signup_btn" onclick="signup()">가입하기</button>
        	</form>
          </div>
       
      


<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script type="text/javascript">
		
		var emailOverlapCheck = false;
		var nicknameOverlapCheck = false;
		
		//이메일 중복 확인
		function emailCheck(){
		//form요소는 새로고침이 default -> preventDefault 해주면 새로고침 방지	
			event.preventDefault();
			emailOverlapCheck = true;
			//memberEmail을 select해서 가져온다.
			let memberEmail = $("#memberEmail").val();
			let emailReg = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;
			console.log("오버랩" + emailOverlapCheck);
			console.log(memberEmail);
			//이메일 값이 비어있는 채로 중복확인 버튼 누른다면?
			if(memberEmail=="") {
				alert("이메일을 입력하세요.");
				return;
				}
			else if (!emailReg.test(memberEmail)) {
				alert("유효하지 않은 이메일 입니다.");
				return false;
			}
			
			//비동기통신->새로고침 없이 페이지 내에서 동작 default=true
			$.ajax({
				type :"post",
				async : true,
				//controller 주소 입력
				url : "/mem/emailCheck",
				//memberEmail을 가져와서 변수 memberEmail에 할당
				data : {
					memberEmail : memberEmail ,
					emailOverlapCheck : emailOverlapCheck
				},
				success : function(result) {
					console.log("오버랩" + emailOverlapCheck);
		
					if(result == 1) {
						$("#emailCheckMessage").text("사용할 수 있는 이메일입니다.");
						console.log(result);
				
					} else {
						$("#emailCheckMessage").text("사용할 수 없는 이메일입니다.");
						console.log(result);
					}
				},
				error : function(result)  {
					alert("오류가 발생했습니다.");
				},
				complete : function(result) {
					
				}
			});
		}
		
		//비밀번호 일치 확인
		function passwordCheck(){
			console.log("passwordRegCheckMessage")
			let passwordReg =/^(?=.[A-Za-z])(?=.*\d)(?=.*[@!%*#?&])[A-Za-z\d@!%*#?&]{8,12}$/;
			let memberPwd = $("#memberPwd").val();
			let memberPwd2 = $("#memberPwd2").val();
			if (!passwordReg.test(memberPwd)) {
				$("#passwordRegCheckMessage").html("비밀번호는 8~12자로 반드시 영문자,숫자,특수문자를 포함해야 합니다.");
				return false;
			} else {
				$("#passwordRegCheckMessage").html("");
			}
			
			if(memberPwd!=memberPwd2) {
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
					memberNickname : memberNickname ,
					nicknameOverlapCheck : nicknameOverlapCheck
				},  
				success : function(result) {
					console.log(result);
					if(result == 1) {
						$("#nicknameCheckMessage").text("사용할 수 있는 닉네임입니다.");
						console.log(result);
						
					} else {
						$("#nicknameCheckMessage").text("사용할 수 없는 닉네임입니다.");
						console.log(result);
					}
				},
				error : function(result)  {
					alert("오류가 발생했습니다.");
				},
				complete : function(result) {
					
				}
			});
		}
		
		//이메일 인증
		function emailSend() {
			event.preventDefault();
			
			let memberEmail = $("#memberEmail").val();
			console.log(memberEmail);
			
			if(memberEmail=="") {
				alert("이메일을 입력하세요.");
				return;
				}
			
			$.ajax({
				type :"post",
				async : true,
				url : "/mem/emailSend",
				data : {
					memberEmail : memberEmail
				},  
				success : function(result) {
					console.log(result);
					if(result==1) {
					  	alert ("인증메일이 발송되었습니다.");
					  	console.log(result);
					  
					} else if (result==0){
					  alert("이메일 중복확인을 해 주세요.");
					  console.log(result);
					  
					} else if (result==-1) {
						alert("이미 가입된 이메일입니다.");
						console.log(result);
					}
				},
				error : function(result)  {
					alert("오류가 발생했습니다.");
				},
				complete : function(result) {
					
				}
			});
	
		}
		function signup(){	
			event.preventDefault();
			let memberEmail = $("#memberEmail").val();
			let memberPwd = $("#memberPwd").val();
			let memberPwd2 = $("#memberPwd2").val();
			let memberNickname = $("#memberNickname").val();
			
			console.log(memberEmail);
			console.log(memberPwd);
			console.log(memberPwd2);
			console.log(memberEmail);
			
			if(memberEmail=="") {
				alert("이메일을 입력하세요.");
				return;
				}
			if(memberPwd=="") {
				alert("비밀번호를 입력하세요.");
				return;
				}
			if(memberNickname=="") {
				alert("닉네임을 입력하세요.");
				return;
				}
			
			$.ajax({
				type :"post",
				async : true,
				url : "/mem/signup",
				data : {
					memberEmail : memberEmail , 
					memberPwd : memberPwd,
					memberPwd2 : memberPwd2,
					memberNickname : memberNickname,
					emailOverlapCheck : emailOverlapCheck ,
					nicknameOverlapCheck : nicknameOverlapCheck
				},
				success : function(result) {
					console.log(result);
					if(result == 1) {
					  	alert ("회원가입이 완료되었습니다.");
					  	console.log("가입완료"+result);
					  	location.href="/index.jsp";
					}else if(result == -200){
						  alert("이미 가입된 이메일입니다.");
						  console.log(result);
					}else if(result == -300){
						  alert("이미 등록된 닉네임입니다.");
						  console.log(result);
						 
					}else if(result == -400){
						  alert("이메일 중복체크를 해 주세요.");
						  console.log(result);
						  
					}else if(result == -500){
						  alert("닉네임 중복체크를 해 주세요.");
						  console.log(result);
						  
					}else if(result == -600){
						  alert("비밀번호가 같지 않습니다.");
						  console.log(result);
						  
					}else if(result == -700){
						  alert("비밀번호는 반드시 영문자,숫자,특수문자를 포함하여 8~12자여야 합니다.");
						  console.log();
						  
					}else if(result == -800){
						  alert("가입란을 모두 입력 해 주세요.");
						  console.log();
					}
					else if(result == -900){
						  alert("이메일 인증을 해 주세요.");
						  console.log();
						  
					}else if(result == -1000){
						  alert("되는거야??.");
						  console.log();
						  
					}
					else {
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

<%@ include file = "./common/footer.jsp" %>

</body>
</html>

