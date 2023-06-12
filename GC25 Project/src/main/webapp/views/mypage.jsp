<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.gc25.dao.MemberDAO" %>
<%@ page import="com.gc25.dto.MemberDTO" %>
<%@ page import="com.gc25.service.MemberService" %>
<%@ page import="java.io.PrintWriter"%>
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
	<link rel="stylesheet" href="../css/mypage.css">
	
</head>
<body>
<%@ include file = "./common/header.jsp" %>
<%
	//로그인 정보 확인
    String memberEmail = (String) session.getAttribute("memberEmail");
    if (memberEmail != null) {
        //로그인이 되어있는 경우
    	memberEmail=(String)session.getAttribute("memberEmail");
    } else {
        // 로그인 되어있지 않은 경우
    	PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('로그인을 해 주세요.')");
		script.println("location.href='/views/login.jsp';");
		script.println("</script>");
		script.close(); //오류생기면 이 jsp 페이지 종료
    }
    MemberDTO member = new MemberDAO().getMember(memberEmail);
%>

<div class="container"> 
	 <h2>마이페이지</h2>
	 <div class="bodyContainer"> 
      <div id="profile">
        <img src="/images/profile.jpg" alt="기본이미지" id="profileImg">
        <form method="post" enctype="multipart/form-data" action="/mem/imgChange"> <!-- 파일을 업로드 할 땐 항상 post방식 get은 사용 할 수 없다. -->
        	<input type="file" id="file">
        	<input type="submit" value="수정">
        </form>
        <form method="post" enctype="multipart/form-data" action="/mem/imgRemove">
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
		   <button>적용</button>
	 </div>
</div>    
<%@ include file = "./common/footer.jsp" %>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script type="text/javascript">

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
		
		url : "/mem/mypage",
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


</script>


</body>
</html>