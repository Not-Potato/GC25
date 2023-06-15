<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.gc25.dao.MemberDAO" %>
<%@ page import="com.gc25.memberEmailUtil.SHA256" %>
<%@ page import="com.gc25.service.MemberService" %>
<%@ page import="java.io.PrintWriter" %>

<%
	request.setCharacterEncoding("UTF-8");	
    PrintWriter script = response.getWriter();
	String code = null;
	String memberEmail = null;
	memberEmail = (String) session.getAttribute("memberEmail");
	System.out.println("memberEmail: 여기서 안되니??" +memberEmail);
	
	if(request.getParameter("code")!=null){
		code=request.getParameter("code");
	}
	
	//현재 사용자가 보낸 코드가 정확히 해당 사용자의 해시값 적용한 이메일주소와 일치하는지.
	boolean isRight = (new SHA256().getSHA256(memberEmail).equals(code))? true : false;
	session.setAttribute("isRight" , isRight);
	
	System.out.println("code"+code);
	System.out.println("memberemailcode"+new SHA256().getSHA256(memberEmail));
	System.out.println("hashcheck : "+ isRight);
	
	if(isRight) {
		script.println("<script>");
		script.println("alert('인증에 성공했습니다. 세션이 만료되기 전에 회원가입을 진행 해 주세요.')");
		script.println("window.close();"); //창 닫힘
		script.println("</script>");
		
	} else {
		script.println("<script>");
		script.println("alert('인증에 실패했습니다. 처음부터 다시 진행 해 주세요.')");
		script.println("window.close();");
		script.println("</script>");
		
	}
%>