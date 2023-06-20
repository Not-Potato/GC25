package com.gc25.controllers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.simple.JSONObject;

import com.gc25.dto.MemberDTO;
import com.gc25.memberemailutil.*;
import com.gc25.service.MemberService;



 
@WebServlet("/mem/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		
		String views ="/views";
		String nextPage = ""; 
		String action = request.getPathInfo();
		
		
		MemberService memberService = new MemberService();
		MemberDTO memberDTO = new MemberDTO();
		JSONObject jsonResult = new JSONObject();
		
		
		
		
		try {												//컨트롤러에 있는 페이지만...
			if (action == null || action.equals("/")) action="/mypage.do";
			
			System.out.println(action);
			
			switch(action) {
				case "/emailCheck.do" -> {
					
					String memberEmail = request.getParameter("memberEmail");
					String emailOverlapCheckParam;
					
					try {
					//이메일 중복 확인 
					session = request.getSession(); // 세션 가져오기
					session.setMaxInactiveInterval(1800);//30분 세션유지
					
					//email 버튼 click 했다면 넘어오는 emailOverlapCheckParam
					emailOverlapCheckParam = request.getParameter("emailOverlapCheck");
					System.out.println("이메일버튼 on/off 여부: " + emailOverlapCheckParam);
					//getparameter는 string 타입이기 때문에 boolean으로 형 변환 click 했다면 true 넘어옴.
					boolean emailOverlapCheck = Boolean.parseBoolean(emailOverlapCheckParam);
					System.out.println("emailOverlapCheck???? :" + emailOverlapCheck);
					session.setAttribute("emailOverlapCheck", emailOverlapCheck);
					
					//email 중복확인 했다면??
					if(emailOverlapCheck) {
						//db에서 중복되는 email 확인
						int emailCheckSuccess = memberService.emailCheck(memberEmail);
						System.out.println("emailCheckSuccess: " + emailCheckSuccess);
						//중복되는 email 없다면
						if(emailCheckSuccess == 1) {
							//세션에 email 중복 여부와 email 저장
							session.setAttribute("emailCheckSuccess", emailCheckSuccess);
							session.setAttribute("memberEmail", memberEmail);
							//ajax로 넘겨주기
							PrintWriter out = response.getWriter();
							out.print(emailCheckSuccess);
							return;
						}
					    else if (emailCheckSuccess == 0) {
							//세션에 email 중복 여부 저장
					    	session.setAttribute("emailCheckSuccess", emailCheckSuccess);
							
					    	PrintWriter out = response.getWriter();
							out.print(emailCheckSuccess);
							return;
					    	}
						} //if 
							
					}catch(Exception ex) {
						ex.printStackTrace();
					}
				}//case
				case "/nickcheck.do" -> {
					
					String memberNickname = request.getParameter("memberNickname");
					String nicknameOverlapCheckParam;

					try {
					session = request.getSession(); // 세션 가져오기
					session.setMaxInactiveInterval(1800);//30분 세션유지
					
					//nickname 버튼 click 했다면 넘어오는 nicknameOverlapCheck
					nicknameOverlapCheckParam = request.getParameter("nicknameOverlapCheck");
					System.out.println("이메일버튼 on/off 여부: " + nicknameOverlapCheckParam);
					//getparameter는 string 타입이기 때문에 boolean으로 형 변환 click 했다면 true 넘어옴.
					boolean nicknameOverlapCheck = Boolean.parseBoolean(nicknameOverlapCheckParam);
					System.out.println("nicknameOverlapCheck???? :" + nicknameOverlapCheck);
					
					if(nicknameOverlapCheck){
						int nicknameCheckSuccess = memberService.nicknameCheck(memberNickname);
						System.out.println("nicknameCheckSuccess: "+nicknameCheckSuccess);
						if(nicknameCheckSuccess == 1) {
							 session.setAttribute("nicknameCheckSuccess", nicknameCheckSuccess);
							 session.setAttribute("memberNickname", memberNickname);
							 PrintWriter out = response.getWriter();
							 //jsonResult.put("result", nicknameCheckSuccess);
							 out.print(nicknameCheckSuccess);
							 return;
						} else if (nicknameCheckSuccess == 0) {
							session.setAttribute("nicknameCheckSuccess", nicknameCheckSuccess);
							PrintWriter out = response.getWriter();
							//jsonResult.put("result", nicknameCheckSuccess);
							out.print(nicknameCheckSuccess);
							return;
						}
					}
					
					
					}catch(Exception ex) {
						ex.printStackTrace();
					}
				} //case end
				
				case "/emailSend.do" -> {
				
					String memberEmail = request.getParameter("memberEmail");
					String emailOverlapCheckParam;

					
					System.out.println(action);
					PrintWriter out = response.getWriter();
					
					
					int emailCheckSuccess = memberService.emailCheck(memberEmail);
					
					emailOverlapCheckParam = request.getParameter("emailOverlapCheck");
					boolean emailOverlapCheck = (boolean) session.getAttribute("emailOverlapCheck");
					
					System.out.println("emailOverlapCheck emailsend에서 :" + emailOverlapCheck);
					System.out.println("emailCheckSuccess emailsend에서 :" + emailCheckSuccess);
					
					if(emailOverlapCheck == false ||  emailOverlapCheckParam == null) {
						System.out.println("return:"+emailCheckSuccess);
					  //jsonResult.put("result", false);
						int result=0;
						out.print(result);
						
					}
					System.out.println("success:"+emailCheckSuccess);
					if (emailOverlapCheck) {
						//jsonResult.put("result", true);
						if(emailCheckSuccess == 0) {
							int result=-1;
							out.print(result);
						}
						else{
							int result = 1;
							out.print(result);
							System.out.println("emailsendresult:"+result);
						
						
					
					
					//이메일 중복확인을 했다면
					//Gmail 에서 아웃룩이나 다른 전자 메일 프로그램을 통해 메일을 보낼 수 있도록 하기 위해 
					//Gmail SMTP(Simple Mail Transfer Protocol) 설정
					//memberEmail = memberService.getMemberEmail(memberEmail);
							
						System.out.println("이메일이메일!!!!"+emailCheckSuccess);
						System.out.println("emailHashCheck"+memberEmail);
						String host="http://localhost:8080/views/";
						String from="bko2369@gmail.com";
						String to = memberEmail;
						String subject="이메일 인증 메일입니다.";
						String content="다음 링크에 접속하여 이메일 인증을 진행하세요."+
							"<br><a href ='" + host + "emailhashcheck.jsp?code="+ new SHA256().getSHA256(to)+ "'>이메일 인증하기</a>";
						//실제 smtp에 접속하기 위한 정보
						Properties p = new Properties();
						p.put("mail.smtp.user",from); //관리자 구글 이메일 계정
						p.put("mail.smtp.host","smtp.googlemail.com"); //구글에서 제공하는 smtp 서버
						p.put("mail.smtp.port","465"); //port 구글서비스가 제공하는 번호
						p.put("mail.smtp.starttls.enable","true");
						p.put("mail.smtp.auth","true");
						p.put("mail.smtp.debug","true");
						p.put("mail.smtp.socketFactory.port","465");
						p.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
						p.put("mail.smtp.socketFactory.fallback","false");
						
						try{
							Authenticator auth = new Gmail();
							Session ses = Session.getInstance(p,auth);
							ses.setDebug(true);
							MimeMessage msg = new MimeMessage(ses);
							msg.setSubject(subject);
							Address fromAddr = new InternetAddress(from);
							msg.setFrom(fromAddr);
							Address toAddr = new InternetAddress(to);
							msg.addRecipient(Message.RecipientType.TO, toAddr);
							msg.setContent(content,"text/html;charset=UTF-8" );
							Transport.send(msg);
							
			
						}catch(Exception ex){
							ex.printStackTrace();
							PrintWriter script = response.getWriter();
							script.println("<script>");
							script.println("alert('오류가 발생했습니다.')");
							script.println("</script>");
							script.close(); //오류생기면 이 jsp 페이지 종료
							return;
						}
					}
						
					}
					   
				}//case	   
				
				case "/login.do" -> {
					System.out.println("로그인페이지");
					nextPage = views + "/login.jsp";
					
				}
				
				case "/mypage.do" -> {
					System.out.println("마이페이지");
					nextPage = views + "/mypage.jsp";
					
					
				}
				case "/logout.do" -> {
					
					session.invalidate();
					
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("alert('로그아웃 되었습니다.')");
					script.println("location.replace('/views/login.jsp')");
					script.println("</script>");
					
					//nextPage = views + "/login.jsp";
					System.out.println("로그아웃");
					
					
				}
				case "/signup.do" -> {
					System.out.println("회원가입");
					nextPage = views + "/signup.jsp";
					
				}
				
				
				
				case "/login/result.do" -> {
					
					String memberEmail = request.getParameter("memberEmail");
					String memberPwd = request.getParameter("memberPwd");
					String memberNickname = request.getParameter("memberNickname");
					int memberStatus = memberDTO.getMemberStatus();
					String memberImageFileName = memberDTO.getMemberImageFileName();
					
					try {
						System.out.println("로그인주소:"+action);
						
						PrintWriter script = response.getWriter();
					
						
						System.out.println("로그인 이메일?? : " + memberEmail);
						System.out.println("로그인 패스워드?? :" + memberPwd);

						int loginSuccess = memberService.memberLogin(memberEmail, memberPwd);
						
						System.out.println("비교결과 넘어왔나??:"+loginSuccess);
						
						// 비밀번호 틀린 경우
						if(loginSuccess == 0) {
							script.println("<script>");
							script.println("alert('비밀번호를 다시 입력 해 주세요.')");
							script.println("history.back();");
							script.println("</script>");
						
						// 로그인 성공 (비밀번호 일치한 경우)
						} else if (loginSuccess == 1) {
							System.out.println("들어오나보자");
							System.out.println("contextPath : " + request.getContextPath());
							// nextPage="/index.jsp";
							PrintWriter out = response.getWriter();
							out.println("<script>");
							out.println("alert('로그인 성공!')");
							out.println("location.replace('/main');");
							out.println("</script>");

							//세션 설정
							session = request.getSession(); // 세션 가져오기
							session.setMaxInactiveInterval(1800);//30분 세션유지
							
							//회원번호 세션에 저장하기 위해 가져오기
							int memberNumber = memberService.getMemberNumber(memberEmail);
							//닉네임, 이메일 세션에 저장하기 위해 가져오기
							MemberDTO member= memberService.getMember(memberEmail);
							
							memberNickname = member.getMemberNickname();
							memberStatus = member.getMemberStatus();
							memberImageFileName = memberService.getMemberImageFileName(memberEmail);
							
							//세션에 저장
							session.setAttribute("memberEmail",memberEmail);
//							session.setAttribute("memberNumber", memberNumber);
							//test
//							session.setAttribute("memberNumber", 10000);
							session.setAttribute("memberNumber", memberNumber);
							session.setAttribute("memberNickname", memberNickname);
							session.setAttribute("memberStatus", memberStatus);
							session.setAttribute("memberImageFileName",memberImageFileName);
							//세션에 이메일, 회원번호,닉네임,이미지 저장
							System.out.println("이메일 : " + memberEmail);
							System.out.println("회원번호 : " +memberNumber);
							System.out.println("닉네임 : "+memberNickname);
							System.out.println("이미지 파일 : "+memberImageFileName);
							System.out.println("등급 : "+memberStatus);
							
							//response.sendRedirect(request.getContextPath() + "/main");
							
						// DB에 저장된 아이디가 아닌 경우
						} else if (loginSuccess == -1) { 
							script.println("<script>");
							script.println("alert('존재하지 않는 아이디입니다.')");
							script.println("history.back();");
							script.println("</script>");
						
						// DB 오류가 발생한 경우
						}else if (loginSuccess == -2) { 
							script.println("<script>");
							script.println("alert('오류가 발생했습니다.')");
							script.println("history.back();");
							script.println("</script>");
								
						}
					}catch(Exception ex) {
						ex.printStackTrace();
					}
			  }
				case "/signup/result.do" -> {
					
					String memberEmail = request.getParameter("memberEmail");
					String memberPwd = request.getParameter("memberPwd");
					String memberPwd2 = request.getParameter("memberPwd2"); //비밀번호 확인용
					String memberNickname = request.getParameter("memberNickname");
					int memberStatus = memberDTO.getMemberStatus();
					String memberImageFileName = memberDTO.getMemberImageFileName();
					
					String emailOverlapCheckParam;
					String nicknameOverlapCheckParam;
					
					try {
					PrintWriter script = response.getWriter();
					PrintWriter out = response.getWriter();
					//이메일 중복체크 여부
					emailOverlapCheckParam = request.getParameter("emailOverlapCheck");
					boolean emailOverlapCheck = Boolean.parseBoolean(emailOverlapCheckParam);
					//닉네임 중복체크 여부
					nicknameOverlapCheckParam = request.getParameter("nicknameOverlapCheck");
					boolean nicknameOverlapCheck = Boolean.parseBoolean(nicknameOverlapCheckParam);
					//비밀번호 패턴 정규식 -> js에도 있지만 그건 사용자 보여주기 위함.
					String passwordReg = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@!%*#?&])[A-Za-z\\d@!%*#?&]{8,12}$";
					//세션에 저장되어 있는 이메일,닉네임 중복검사 결과 가져오기
					int emailCheckSuccess = (int)session.getAttribute("emailCheckSuccess");
					int nicknameCheckSuccess = (int)session.getAttribute("nicknameCheckSuccess");
					
					System.out.println("ec : "+emailCheckSuccess+"nc : "+nicknameCheckSuccess);
					System.out.println("이메일버튼 on/off 여부: " + emailOverlapCheckParam);
					System.out.println("닉네임버튼 on/off 여부: " + nicknameOverlapCheckParam);
					
					//이메일,비밀번호,닉네임을 저장한다.
					if(request.getParameter("memberEmail")!=null){
						memberEmail=request.getParameter("memberEmail");
					}
					if(request.getParameter("memberPwd")!=null){
						memberPwd=request.getParameter("memberPwd");
						System.out.println("비밀번호 넘어오나? : " +memberPwd);
						System.out.println("정규식에 맞나??"+memberPwd.matches(passwordReg));
						
					}
					if(request.getParameter("memberNickname")!=null){
						memberNickname=request.getParameter("memberNickname");
					}
					
					int result = 0 ; 
				
					//이메일,닉네임 중복 없고 비밀번호 두 개 일치했다면 -> 회원 객체 하나 만들고 result에 그 수 넣음
					if (emailCheckSuccess == 1 && nicknameCheckSuccess == 1 && memberPwd.equals(memberPwd2)) {
						//이메일 중복
						if(emailCheckSuccess ==0) {
							result = -200; 
							out.print(result);
							session.removeAttribute("emailOverlapCheck");
							 return;
						} //닉네임 중복
						else if(nicknameCheckSuccess ==0) {
							result = -300; 
							out.print(result);
							
							 return;
						}//이메일 중복체크 안 함
						else if(emailOverlapCheck==false) {
							result = -400; 
							out.print(result);
							
							 return;
						}//닉네임 중복체크 안 함
						else if(nicknameOverlapCheck==false) {
							result = -500; 
							out.print(result);
							
							 return;
						}//비밀번호 불일치
						else if (!memberPwd.equals(memberPwd2)) {
							result = -600; 
							out.print(result);
							
							return;
						}//비밀번호 정규식에 부합
						else if (!memberPwd.matches(passwordReg)) {
							result = -700; 
							out.print(result);
							
							return;
						}
						//이메일 인증이 안 되어 있다면 (emailhashcheck.jsp에서 session으로 isRight에 인증 여부 저장함.)
						boolean isRight = (boolean) session.getAttribute("isRight");
						if (isRight == false) {
							System.out.println(isRight);
							result = -800; 
							out.print(result);
							session.removeAttribute("emailOverlapCheck");
							return;
						}
						result = memberService.addMember(new MemberDTO(memberEmail, memberPwd, memberNickname, memberStatus, memberImageFileName));
						System.out.println("제발"+result);
						
						if (result==1) {
							 // jsonResult.put("result", result);
							  out.print(result);
							 //이메일 인증 세션 만료
							  session.removeAttribute("isRight");
							return;
							}
						
							//입력창 빈 곳이 있다면
							else if (memberEmail==null || memberEmail.equals("") ||
							    memberPwd==null || memberPwd.equals("")|| 
								memberPwd2==null || memberPwd2.equals("") || 
								memberNickname==null || memberNickname.equals("")) {
								session.removeAttribute("isRight");
								result = -800; 
								out.print(result);
								return;
							}
						}
					nextPage="/main";
					
					} catch (Exception ex) {
						ex.printStackTrace();
//						PrintWriter out = response.getWriter();
//						int result = -1000;
//						out.print(result);
					}
				}
				case "/mpsubmit.do" -> { 
					
					int result = 0;
					
					String memberEmail = request.getParameter("memberEmail");
					String memberPwd = request.getParameter("memberPwd");
					String memberPwd2 = request.getParameter("memberPwd2"); //비밀번호 확인용
					String memberNickname = request.getParameter("memberNickname");
					String memberImageFileName = memberDTO.getMemberImageFileName();

					String nicknameOverlapCheckParam;
					
					PrintWriter out = response.getWriter();
					String passwordReg = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@!%*#?&])[A-Za-z\\d@!%*#?&]{8,12}$";
					
					//로그인 정보 확인
				    memberEmail = (String)session.getAttribute("memberEmail");
				    if (memberEmail != null) {
				        //로그인이 되어있는 경우
				    	memberEmail=(String)session.getAttribute("memberEmail");
				    } else {
				        // 로그인 되어있지 않은 경우
				    	result=-100;
				    	out.print(result);
						return;
				    }
				    
					//닉네임 중복 확인
				    session = request.getSession(); // 세션 가져오기
					session.setMaxInactiveInterval(1800);//30분 세션유지
				    //nickname 버튼 click 했다면 넘어오는 nicknameOverlapCheck
					nicknameOverlapCheckParam = request.getParameter("nicknameOverlapCheck");
					//System.out.println("mypage 이메일버튼 on/off 여부: " + nicknameOverlapCheckParam);
					//getparameter는 string 타입이기 때문에 boolean으로 형 변환 click 했다면 true 넘어옴.
					boolean nicknameOverlapCheck = Boolean.parseBoolean(nicknameOverlapCheckParam);
					int nicknameCheckSuccess = memberService.nicknameCheck(memberNickname);
					
					//닉네임 바꾸지 않는다면 -> 중복검사 할 필요 없음.
					if(memberNickname.equals("") || memberNickname==null) {
						session.setAttribute("nicknameCheckSuccess", nicknameCheckSuccess);
						nicknameOverlapCheck=true;
						MemberDTO member= memberService.getMember(memberEmail);
						memberNickname = member.getMemberNickname();
					}
					else {
						if(nicknameOverlapCheck){
							nicknameCheckSuccess = memberService.nicknameCheck(memberNickname);
							System.out.println("memberNickname: "+memberNickname);
							System.out.println("nicknameCheckSuccess: "+nicknameCheckSuccess);
							//중복검사했고 중복되지 않은 닉네임이라면
							if(nicknameCheckSuccess == 1) {
								 memberNickname = request.getParameter("memberNickname"); 
								 session.setAttribute("nicknameCheckSuccess", nicknameCheckSuccess);
								 session.setAttribute("memberNickname", memberNickname);
							} else if (nicknameCheckSuccess == 0){
								 result= -200;
								 out.print(result);
								 return;
							}
						}else {
							result= -300;
							 out.print(result);
							 return;
						}
					}
					
					if (memberPwd==null || memberPwd.equals("")|| memberPwd2==null || memberPwd2.equals("")) {
						 result= -400;
						 out.print(result);
						 return;		
					} 
				
				//비밀번호 불일치
				if (!memberPwd.equals(memberPwd2)) {
					result = -500; 
					out.print(result);
					
					return;
				}//비밀번호 정규식에 부합
				else if (!memberPwd.matches(passwordReg)) {
					result = -600; 
					out.print(result);
					return;
				}
				
				memberImageFileName = memberService.setMemberImageFileName(memberImageFileName, memberEmail);
				System.out.println("삭제후 이미지"+memberImageFileName);
				 //비밀번호 받아서 세팅
			    if(request.getParameter("memberPwd")!=null){
					memberPwd=request.getParameter("memberPwd");
					System.out.println("비밀번호 넘어오나? : " +memberPwd);
					System.out.println("정규식에 맞나??"+memberPwd.matches(passwordReg));
					
				//이미지 파일 세팅
				memberImageFileName = (String) session.getAttribute("fileName");
				System.out.println(memberImageFileName);
				
				session.setAttribute("memberImageFileName", memberImageFileName);
				session.setAttribute("memberNickname", memberNickname);
			
				int updateSuccess = memberService.updateMember(memberEmail, memberPwd, memberNickname, memberImageFileName);
				if(updateSuccess==1) {
					result = 1; 
					out.print(result);
					return;
				}		
				
					
				}//case
				}
				case "/imgchange.do" -> {
				
					//memberService.getMemberImageFileName(memberImageFileName, memberEmail);
					//파일 저장할 경로
					File curPath=new File("C:\\Users\\bko23\\git\\GC25\\GC25 Project\\src\\main\\webapp\\resources\\images\\");
//					System.out.println(curPath);
					//DiskFileItemFactory는 FileItem 객체를 생성하기 위한 팩토리 클래스  
					//->getSize(), getName(), isFormField 등을 제공 -> 업로드된 파일의 정보(이름, 크기, 타입 등)를 확인하고, 데이터에 접근하여 원하는 처리를 수행 
					DiskFileItemFactory factory=new DiskFileItemFactory();
					//파일을 저장할 폴더 지정
					factory.setRepository(curPath);
					//저장할 파일 사이즈 지정
					factory.setSizeThreshold(1024*1024*5);
					//HTTP 요청으로부터 업로드된 파일을 추출하고 처리
					ServletFileUpload upload = new ServletFileUpload(factory);
				
					try {
						List <FileItem> items=upload.parseRequest(request); //request로 form이 리스트 형태로 넘어오면 
						//파일목록 (items)에서 첫번째 파일 아이템을 가져옴
						//System.out.println("아이템:"+items);
						for(FileItem fileItem : items) {
							
							//객체가 일판필드인지 파일필드인지 확인
							if(!fileItem.isFormField()) { //파일필드면 false반환
//								System.out.println(fileItem.getFieldName()+"="+fileItem.getString("UTF-8"));
							
								if(fileItem.getSize()>0) {
								String fullpath = fileItem.getName();
								//파일경로에서 마지막 \\를 찾아 저장 ex)C:\path\to\file.text라면
								// idx=\file.txt
								int idx=fullpath.lastIndexOf("\\"); //윈도우 사용자
								
								if(idx==-1) {
									idx=fullpath.lastIndexOf("/"); //만약 맥,리눅스 사용자라면
								}
								//idx는 " \ " 포함되어 있으므로 전체 경로에서 idx + 1 해주면 파일명만 저장 
								String fileName = fullpath.substring(idx+1);
								File uploadFile = new File(curPath+"\\"+fileName);
								
								//업로드 확장자 제한
								String fileExtension="";
								List<String> allowedExtensions = new ArrayList<>(Arrays.asList("jpg", "png", "gif", "svg" , "jpeg"));
								if (fileName != null && !fileName.isEmpty()) {
									fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
								}
								
								if (!allowedExtensions.contains(fileExtension.toLowerCase())) {
									PrintWriter out = response.getWriter();
									out.println("허용되지 않는 파일 확장자입니다.");
								} else {
									fileItem.write(uploadFile);
									System.out.println("파일 이름 : " + fileName);
									session.setAttribute("fileName", fileName);
								}
								
								}
							}//if	
							
						}//for
					}
					catch(Exception ex) {
					ex.printStackTrace();
					}
				
				}//case
				case "/imgdel.do" -> {
					
					//로그인 한 이메일 세션에서 가져오기
					String memberEmail = (String)session.getAttribute("memberEmail");
					System.out.println("컨트롤러에서 이메일:"+memberEmail);
					//이메일로 이미지파일 이름 받아 오기
					String memberImageFileName = memberService.getMemberImageFileName(memberEmail);
					
					String userResponseParam = request.getParameter("userResponse");
					boolean userResponse = Boolean.parseBoolean(userResponseParam);
					
					if(userResponse) {
						memberImageFileName = memberService.getMemberImageFileName(memberEmail);
						 String filePath = "C:\\Users\\bko23\\git\\GC25\\GC25 Project\\src\\main\\webapp\\resources\\images\\";
					     File file = new File(filePath+memberImageFileName);
					      
					     if (!memberImageFileName.equals("profile.jpg")) {
						     if (file.delete()) {
						        System.out.println("이미지가 삭제되었습니다.");
						        memberService.delMemberImageFileName(memberEmail);
						        file=new File(filePath+memberService.getMemberImageFileName(memberEmail));
						        session.setAttribute("fileName", "profile.jpg");
						        // memberImageFileName=memberService.setMemberImageFileName(memberImageFileName,memberEmail);
						        //session.setAttribute("fileName", file);
						        
						     } else {
						        System.out.println("이미지 삭제를 실패했습니다.");
						     }
					     }
					}
				}//case
				
				case "/withdrawal.do" -> {
					// 세션에 저장된 현재 로그인되어 있는 계정의 회원번호 가져오기
					int memberNumber = (int) session.getAttribute("memberNumber");
					// service --> DAO -->  DB 삭제 진행
					int result = memberService.withdrawal(memberNumber);
					System.out.println("컨트롤러: " + result);
					
					// "회원 탈퇴 처리가 완료되었습니다" 
					// "그동안 이용해 주셔서 감사합니다"
					PrintWriter script = response.getWriter();
					if (result == 1) {
						script.println("<script>");
						script.println(		"alert('그동안 이용해 주셔서 감사합니다.')");
						script.println(		"location.replace('mypage.do')");
						script.println("</script>");
					} else {
						script.println("<script>");
						script.println(		"alert('오류가 발생했습니다.')");
						script.println(		"location.replace('mypage.do')");
						script.println("</script>");
					}
				}
				
			}//switch
			
			if (!nextPage.equals("")) {
				System.out.println(nextPage);
				RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
				dispatch.forward(request, response);
			}
			
		}//try		
		catch(Exception ex) {
			ex.printStackTrace();
		}	
	}//dopost	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}//class	

	