<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="com.gc25.dao.MemberDAO" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="javax.naming.*" %>
<%
	request.setAttribute("pageName", "login");
%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1"> 
	<title>GC25 로그인</title>
	<script src="https://code.jquery.com/jquery-3.7.0.js" integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>	
	<!--부트스트랩 css link-->
	<link rel="stylesheet" href="../css/bootstrap.min.css">
	<!--커스텀 css link-->
	<link rel="stylesheet" href="../css/custom.css">
	
</head>
<body>
<%@ include file = "./common/header.jsp" %>
	<h1 class="signup_h1">로그인</h1>
    <form method="post" action="/mem/login">
        <table>
            <tr>
                <td>이메일</td>
                <td>
                    <input type="text" id="memberEmail">
                </td>           
            </tr>
            <tr>
                <td>비밀번호</td>
                <td>
                    <input type="password" id="memberPwd">
                </td>
            </tr>
        </table>
        <button class="loginBtn" onclick="memberLogin();">로그인</button>
    </form>
 <%@ include file = "./common/footer.jsp" %>
 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script type="text/javascript">

		//로그인
		function memberLogin(){
		
			event.preventDefault();
			
			 let memberEmail = $("#memberEmail").val();
             let memberPwd = $("#memberPwd").val();
			
			 console.log(memberEmail);
			
			if(memberEmail=="") {
				alert("이메일을 입력하세요.");
				return;
				}
			if(memberPwd=="") {
				alert("비밀번호를 입력하세요.");
				return;
				}
			
			$.ajax({
				type :"post",
				async : true,
				//controller 주소 입력
				url : "/mem/login",
				dataType : "text",
				//memberEmail을 가져와서 변수 memberEmail에 할당
				   data: {
					   memberEmail : memberEmail,
					   memberPwd : memberPwd
                    },
                    success: function(result) {
                        if (result == 1) {
                            // 로그인 성공
                            alert("로그인 성공!");
                            console.log(result);
                            location.href = "/index.jsp";
                            
                        } else if (result == 0){
                            // 로그인 실패
                            alert("비밀번호를 다시 입력 해 주세요.");
                            console.log(result);
                        } else if (result == -1){
                            // 로그인 실패
                            alert("잘못된 아이디입니다.");
                            console.log(result);
                        } else if (result == 0){
                            // 로그인 실패
                            alert("오류가 발생했습니다.");
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
</body>

</html>