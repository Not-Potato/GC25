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
        <form method="post" action="/mem/signup" >
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
                        <button class="emailHashCheckBtn" onclick="emailHashCheck()">이메일 인증</button>
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
                <input class="signup_btn" type="submit" value="가입">
          </div>
        </form>
      </div>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script type="text/javascript">
		
		//이메일 중복 확인
		function emailCheck(){
		//form요소는 새로고침이 default -> preventDefault 해주면 새로고침 방지	
			event.preventDefault();
			//memberEmail을 select해서 가져온다.
			let memberEmail = $("#memberEmail").val();
			let emailReg = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;
			
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
				dataType : "text",
				//memberEmail을 가져와서 변수 memberEmail에 할당
				data : "memberEmail=" + memberEmail , 
				success : function(result) {
					console.log(result);
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
				dataType : "text",
				
				data : "memberNickname=" + memberNickname , 
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
		function emailHashCheck() {
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
				url : "/mem/emailHashCheck",
				dataType : "text",
				data : "memberEmail=" + memberEmail , 
				success : function(result) {
					console.log(result);
					if(result==true) {
					  alert ("인증에 성공했습니다.");
					  console.log(result);
					} else {
					  alert("인증에 실패했습니다.")
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
	</script>

<%@ include file = "./common/footer.jsp" %>

</body>
</html>

