package com.gc25.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.gc25.memberEmailUtil.Gmail;
import com.gc25.memberEmailUtil.SHA256;
import com.gc25.service.MemberService;
 
@WebServlet("/mem/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		
		String action = request.getPathInfo();
		MemberService memberService = new MemberService();
		
		//사용자의 회원가입 요청 처리 -> 회원가입페이지에서 보내는 파라미터
		String memberEmail = request.getParameter("memberEmail");
		String memberEmailHash = request.getParameter("memberEmailHash");		
		String memberEmailChecked = request.getParameter("memberEmailChecked");
		String memberPwd = request.getParameter("memberPwd");
		String memberPwd2 = request.getParameter("memberPwd2"); //비밀번호 확인용
		String memberNickname = request.getParameter("memberNickname");
		String memberStatus = request.getParameter("memberStatus");
		String memberImageFileName = request.getParameter("memberImageFileName");
		
		try {
			if (action == null || action.equals("/")) action="/index.jsp";
			
			System.out.println(action);
			
			switch(action) {
				case "/emailCheck" -> {
					
					//이메일 중복 확인 
					int emailCheckSuccess = memberService.emailCheck(memberEmail);
					System.out.println("emailCheckSuccess: "+emailCheckSuccess);
					if(emailCheckSuccess == 1) {
						 PrintWriter out = response.getWriter();
						 out.print(emailCheckSuccess);
						 return;
					} else if (emailCheckSuccess == 0) {
						PrintWriter out = response.getWriter();
						out.print(emailCheckSuccess);
						return;
					}
					break;
				}
				case "/nicknameCheck" -> {	
					//닉네임 중복 확인
					int nicknameCheckSuccess = memberService.nicknameCheck(memberNickname);
					System.out.println("nicknameCheckSuccess: "+nicknameCheckSuccess);
					if(nicknameCheckSuccess == 1) {
						 PrintWriter out = response.getWriter();
						 out.print(nicknameCheckSuccess);
						 return;
					} else if (nicknameCheckSuccess == 0) {
						PrintWriter out = response.getWriter();
						out.print(nicknameCheckSuccess);
						return;
					}
					break;
				} //case end
				
				case "/emailHashCheck" -> {
				System.out.println(action);
				int emailCheckSuccess = memberService.emailCheck(memberEmail);
				int nicknameCheckSuccess = memberService.nicknameCheck(memberNickname);
				System.out.println("emailHashCheck!!!!!!!!!!");
				
				
					
					
					//이메일 인증
						if(emailCheckSuccess == 0) {
							PrintWriter script = response.getWriter();
							script.println("<script>");
							script.println("alert('이메일 중복확인을 해주세요.')");
							script.println("</script>");
						}
						else {
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
							"<br><a href ='" + host + "emailCheck.jsp?code="+ new SHA256().getSHA256(to)+ "'>이메일 인증하기</a>";
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
							
							PrintWriter script = response.getWriter();
							script.println("<script>");
							script.println("alert('인증코드가 발송되었습니다.')");
							script.println("</script>");
							
							
						}catch(Exception ex){
							ex.printStackTrace();
							PrintWriter script = response.getWriter();
							script.println("<script>");
							script.println("alert('오류가 발생했습니다.')");
							script.println("</script>");
							script.close(); //오류생기면 이 jsp 페이지 종료
							return;
						}
//						String code = null;
//						if(request.getParameter("code")!=null){
//							code=request.getParameter("code");
//						}
//						//현재 사용자가 보낸 코드가 정확히 해당 사용자의 해시값 적용한 이메일주소와 일치하는지.
//						boolean isRight = (new SHA256().getSHA256(memberEmail).equals(code))? true : false;
//						
//						System.out.println(isRight);
//						System.out.println("controller emailhashcheck:"+new SHA256().getSHA256(memberEmail));
//						System.out.println("controller emailhashcheck code :"+code);
						
//						if(isRight) {
//							PrintWriter out = response.getWriter();
//							out.print(isRight);
//							return;
//						} else {
//							 PrintWriter out = response.getWriter();
//							 out.print(isRight);
//							 return;
//						}
					}//else	
						break;
				}//case
			
				case "/login" -> {
				System.out.println("로그인주소:"+action);
				System.out.println("왜 null이야?? :"+memberEmail);
				System.out.println("대체왜안돼??:"+request.getParameter("memberEmail"));
				
				//사용자가 전송한 email과 pwd 담기
				if(request.getParameter("memberEmail")!=null) {
					memberEmail=request.getParameter("memberEmail");
					System.out.println(memberEmail);
				}
				if(request.getParameter("memberPwd")!=null) {
					memberPwd=request.getParameter("memberPwd");
					System.out.println(memberPwd);
				}
				if(memberEmail == null || memberPwd == null){
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("alert('입력이 안 된 사항이 있습니다.')");
					script.println("history.back();");
					script.println("</script>");
				}
				else {
					System.out.println("여기까지왔니?"+memberPwd);
					int loginSuccess = memberService.memberLogin(memberEmail, memberPwd);
					System.out.println("비교결과 넘어왔나??:"+loginSuccess);
					if(loginSuccess == 1) {
						PrintWriter out = response.getWriter();
						out.print(loginSuccess);
						session.setAttribute("memberEmail",memberEmail);
						return;
					}else if (loginSuccess == 0) { //관습적으로 로그인 성공은 1 실패는 0 반환 dao에서 그렇게 선언했기 때문에 0은 로그인 실패
						PrintWriter out = response.getWriter();
						out.print(loginSuccess);
						return;
					} else if (loginSuccess == -1) { 
						PrintWriter out = response.getWriter();
						out.print(loginSuccess);
						return;
					}else if (loginSuccess == -2) { 
						PrintWriter out = response.getWriter();
						out.print(loginSuccess);	
					}
				}
			  }
				case "/mypage" -> {
					
					System.out.println("여기 세션 : " + session.getAttribute("memberEmail"));
					
						
					//닉네임 중복 확인
					int nicknameCheckSuccess = memberService.nicknameCheck(memberNickname);
					System.out.println("nicknameCheckSuccess: "+nicknameCheckSuccess);
					if(nicknameCheckSuccess == 1) {
						 PrintWriter out = response.getWriter();
						 out.print(nicknameCheckSuccess);
						 return;
					} else if (nicknameCheckSuccess == 0) {
						PrintWriter out = response.getWriter();
						out.print(nicknameCheckSuccess);
						return;
					}
					
					if (memberPwd==null || memberPwd.equals("")|| memberPwd2==null || memberPwd2.equals("")) {
							 
						PrintWriter script = response.getWriter();
						script.println("<script>");
						script.println("alert('수정을 취소합니다.')");
						script.println("location.href='/views/login.jsp';");
						script.println("</script>");
									
					}
					
					int updateSuccess = memberService.updateMember(memberEmail, memberPwd, memberNickname, memberImageFileName);
					
					if (updateSuccess == 1){
					    PrintWriter out = response.getWriter();
						out.print(updateSuccess);
						return;
					
					}else if (updateSuccess == 0) {
						PrintWriter out = response.getWriter();
						out.print(updateSuccess);
						return;
					}
					
				}//case
				
				case "/imgChange" -> {
					memberService.getMemberImageFileName(memberImageFileName, memberEmail);
					File curPath=new File("C:\\Users\\bko23\\git\\GC25\\GC25 Project\\src\\main\\webapp\\images");
					DiskFileItemFactory factory=new DiskFileItemFactory();
					//파일을 저장할 폴더 지정
					factory.setRepository(curPath);
					//저장할 파일 사이즈 지정
					factory.setSizeThreshold(1024*1024);
					
					ServletFileUpload upload = new ServletFileUpload(factory);
				
					try {
						List items=upload.parseRequest(request); //request로 form이 리스트 형태로 넘어오면 
						
						for(int i = 0; i< items.size();i++) {
							FileItem fi = (FileItem)items.get(i);
							if(fi.isFormField()) {
								System.out.println(fi.getFieldName()+"="+fi.getString("UTF-8"));
							}else {
								//저장 할 때 주소로 넘어오는데 파일 이름, 확장자만 사용?? ->윈도우는 주소에 \ 맥,리눅스는 /
								//업로드한 파일 사이즈 확인, 크기가 0이 아닐 때만 저장
								if(fi.getSize()>0) {
									String fullpath = fi.getName();
									int idx=fullpath.lastIndexOf("\\"); //윈도우 사용자
									
									if(idx==-1) {
										idx=fullpath.lastIndexOf("/"); //맥,리눅스 사용자  
									}
									String fileName = fullpath.substring(idx+1); //전체 경로에서 파일 이름만 가져오기
									File uploadFile = new File(curPath + "\\" + fileName);
									
									fi.write(uploadFile); //업로드한 파일 저장
								}
							}
						}
					}catch(Exception ex) {
						ex.printStackTrace();
					}
					
				}
			}	//switch
			
			
		}catch(Exception ex) {
				ex.printStackTrace();
		}
		
		}
		
		
		
//		
//		
//		//파라미터 값 중에 전달 안 되는 값이 있을 시 오류 발생시킴
//		if (memberEmail==null || memberEmail.equals("") ||
//		   memberPwd==null || memberPwd.equals("")
//		    || memberPwd2==null || memberPwd2.equals("") || memberNickname==null || memberNickname.equals("")
//		   ) {
//			request.getSession().setAttribute("messageType", "오류메시지");
//			request.getSession().setAttribute("messageContent", "모든 내용을 입력하세요.");
//			response.sendRedirect("/views/signup.jsp");
//			return;
//			
//		}
//		//아이디 중복 여부 검사
//		
//		
//		
//		
//		
//		//회원 가입시 두 비밀번호가 일치하지 않는다면?
//		if (!memberPwd.equals(memberPwd2)) {
//				request.getSession().setAttribute("messageType", "오류메시지");
//				request.getSession().setAttribute("messageContent", "비밀번호가 일치하지 않습니다.");
//				response.sendRedirect("/views/signup.jsp");
//				return;
//				
//		}
//			
//		
		
		
		//여기까지 함수가 종료되지 않았다면 회원정보 모두 입력 받은 상태
//		int result = new MemberDAO().addMember(memberEmail, memberPwd, memberNickname);
//		
//		if(result == 1 ) {
//			request.getSession().setAttribute("messageType", "가입 성공 메시지");
//			request.getSession().setAttribute("messageContent", "회원가입에 성공했습니다.");
//			response.sendRedirect("/index.jsp");
//			return;
//		}else {
//			request.getSession().setAttribute("messageType", "오류메시지");
//			request.getSession().setAttribute("messageContent", "이미 존재하는 이메일입니다.");
//			response.sendRedirect("/views/signup.jsp");
//			return;
//			
//		}
	}
