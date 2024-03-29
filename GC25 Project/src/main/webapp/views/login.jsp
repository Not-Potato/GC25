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
	<title>GC25</title>
	<script src="https://code.jquery.com/jquery-3.7.0.js" integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>	

	<!-- 커스텀.css / reset.css / 파비콘 / x-icon -->
	<link href="<c:url value='../resources/css/custom.css' />" rel="stylesheet">
	<link href="<c:url value='../resources/css/reset.css' />" rel="stylesheet">
	<link rel="shortcut icon" type="image/x-icon" href="/resources/images/mini_logo.png">
	<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">

</head>
<body class="text-center">
	<%@ include file = "./common/header.jsp" %>	
	<!-- header include 영역 -->
	
	<div class="login-page">
		<div class="inner">
			<div class="title">
			    <h2>로그인</h2>
			</div>
			
			<div class="login-bg">
			    <form method="post" action="/mem/login/result.do" onsubmit="return memberLogin();" name="memberlogin" >
			     	<div>
			     		<p>
				     		<label for="memberEmail">아이디(E-Mail)</label>
				    		<input type="text" id="memberEmail" name="memberEmail" class="box" placeholder="아이디(E-Mail)를 입력해 주세요">
			     		</p>
			     		<p>
				     		<label for="memberPwd">비밀번호</label>
					    	<input type="password" class="box" id="memberPwd" name="memberPwd" placeholder="비밀번호를 입력해 주세요">
					    </p>	
			     	</div>
			     		
		     		<button class="" type="submit">로그인</button> 
			    </form>
			</div>
		</div>
	</div>
 	<%@ include file = "./common/footer.jsp" %>
 
<script src="<c:url value='../resources/js/jquery.js' />"></script>

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
