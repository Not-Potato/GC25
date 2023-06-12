<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.gc25.dao.MemberDAO" %>
<%@ page import="com.gc25.memberEmailUtil.SHA256" %>
<%@ page import="com.gc25.service.MemberService" %>
<%@ page import="java.io.PrintWriter" %>

<%
	MemberService memberService = new MemberService();
	request.setCharacterEncoding("UTF-8");
	String code = null;
	String memberEmail = null;
	memberEmail=memberService.getMemberEmail(memberEmail);
	
	if(request.getParameter("code")!=null){
		code=request.getParameter("code");
	}
	
	//현재 사용자가 보낸 코드가 정확히 해당 사용자의 해시값 적용한 이메일주소와 일치하는지.
	boolean isRight = (new SHA256().getSHA256(memberEmail).equals(code))? true : false;
	
	if(isRight) {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('인증에 성공했습니다.')");
		script.println("location.href='/index.jsp';");
		script.println("</script>");
		
	} else {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('인증에 실패했습니다.')");
		script.println("location.href='/views/signup.jsp';");
		script.println("</script>");
		
	}
%>