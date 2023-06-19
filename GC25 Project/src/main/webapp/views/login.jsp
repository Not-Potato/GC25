<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="com.gc25.dao.MemberDAO" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="javax.naming.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

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
	<!-- 커스텀.css / reset.css / 파비콘 / x-icon -->
	<link href="<c:url value='/resources/css/custom.css' />" rel="stylesheet">
	<link href="<c:url value='/resources/css/reset.css' />" rel="stylesheet">
	<link rel="shortcut icon" type="image/x-icon" href="/resources/images/mini_logo.png">
	<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
</head>
<body>
<%@ include file = "./common/header.jsp" %>
	<h1 class="signup_h1">로그인</h1>
    <form method="post" action="/mem/login/result.do" onsubmit="return memberLogin();" name="memberlogin">
        <table>
            <tr>
                <td>이메일</td>
                <td>
                    <input type="text" id="memberEmail" name="memberEmail">
                </td>           
            </tr>
            <tr>
                <td>비밀번호</td>
                <td>
                    <input type="password" id="memberPwd" name="memberPwd">
                </td>
            </tr>
        </table>
        <input type="submit" value="로그인">
        <!-- <button class="loginBtn" onclick="memberLogin();" id="memberLogin">로그인</button> -->
    </form>
 <%@ include file = "./common/footer.jsp" %>
 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script type="text/javascript">

 var contextPath = "${contextPath}";

		//로그인
	function memberLogin(){
		
		event.preventDefault();
			
		let memberEmail = $("#memberEmail").val();
        let memberPwd = $("#memberPwd").val();
			
		console.log("[" + memberEmail + "]");
			
		if(memberEmail=="" || memberEmail == null || memberEmail == undefined || memberPwd==""){
			if (memberEmail=="" && memberPwd=="") {
				alert('입력하지 않은 항목이 존재합니다.');
				return false;			 		
			} else if (memberEmail=="") {
				alert("아이디를 입력하세요.");
				return false;
			} else if(memberPwd=="") {
				alert("비밀번호를 입력하세요.");
				return false;
			}
			return false;
		} else {
			document.memberlogin.submit();
		}
	} 
    </script>
</body>

</html>