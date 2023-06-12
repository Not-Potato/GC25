<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%   //사용자가 입력한 값은 모두 UTF-8로 처리하겠다.
	
	//로그인이 된 상태의 사용자가 로그아웃을 요청할 떄
	//클라이언트의 모든 세션정보를 파기
	session.invalidate();
	
%>
<script>
	alert('로그아웃 되었습니다.');
	location.href = '/index.jsp';

</script>
	