package com.gc25.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gc25.dto.AfterwordBoardDTO;
import com.gc25.dto.CommentDTO;
import com.gc25.dto.MemberDTO;
import com.gc25.service.CommentService;
import com.gc25.service.MemberService;


@WebServlet("/comment/*")
public class CommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CommentDTO commentDTO;
	CommentService commentService;
	MemberDTO memberDTO;
	MemberService memberService;
	
	
	public void init(ServletConfig config) throws ServletException {
		commentDTO = new CommentDTO();
		commentService = new CommentService();
		memberDTO = new MemberDTO();
		memberService = new MemberService();
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		
		String views = "/views";
		String nextPage = "";
		String action = request.getPathInfo();
		ArrayList<CommentDTO> commentList = new ArrayList<CommentDTO>();
		
		try {
			// 넘어온 주소가 /afterwordboard 혹은 /afterwordboard/인 경우 첫 페이지로 이동
			if (action == null || action.equals("/*")) action = "/abcommentRegister.do";
			
			switch (action) {
			//수강후기 댓글 등록
				case "/abCommentRegister.do" -> {
					
					//해당 게시글의 게시글 번호 가져오기
					String aBoardNumStr = request.getParameter("boardNum");
					int aBoardNum = Integer.parseInt(aBoardNumStr);
					
					//수강후기 게시글이니 상담후기게시글은 기본 0으로 셋팅
					int fBoardNum = 0;
					int memberNum = 10000;
					
					//클라이언트로부터 댓글 내용 받기
					String commentContents = request.getParameter("commentContents");
					
					
					
					// 사용자정보 가져오기
					//HttpSession session = request.getSession();
					//String memberEmail = (String) session.getAttribute("memberEmail");
					//String commentContents = request.getParameter("CommentContents");
					//String memberNumber = memberService.getMemberNumber(memberEmail);
				
					commentService.abAddComment(aBoardNum, fBoardNum, memberNum, commentContents);
					
//					nextPage = "/afterword/viewer.do";
					
					PrintWriter out = response.getWriter();
					// forward 시 주소가 그대로 유지됨(/abCommentRegister.do)
					// 그 상태에서 f5(새로고침) --> 댓글 중복으로 작성됨
					// 얼럿 창 띄우면서 확인 누르면 기존 페이지로 이동하게끔 처리
					out.print("<script>");
					out.print("alert(\"댓글 작성이 완료되었습니다!\");");
					out.print("document.location.href = \"/afterword/viewer.do?boardNum=" + aBoardNum + "\";");
					out.print("</script>");
					
				}
				//상담후기 댓글 등록
				case "/fbCommentRegister.do" -> {
					
					
					//해당 게시글의 게시글 번호 가져오기
					String fBoardNumStr = request.getParameter("boardNum");
					int fBoardNum = Integer.parseInt(fBoardNumStr);
					
					//수강후기 게시글이니 상담후기게시글은 기본 0으로 셋팅
					int aBoardNum = 0;
					int memberNum = 10000;
					
					//클라이언트로부터 댓글 내용 받기
					String commentContents = request.getParameter("commentContents");
					
			
					// 사용자정보 가져오기
					//HttpSession session = request.getSession();
					//String memberEmail = (String) session.getAttribute("memberEmail");
					//String commentContents = request.getParameter("CommentContents");
					//String memberNumber = memberService.getMemberNumber(memberEmail);
				
					commentService.fbAddComment(aBoardNum, fBoardNum, memberNum, commentContents);
					
					//nextPage = "/foreword/viewer.do";
					
					PrintWriter out = response.getWriter();
					// forward 시 주소가 그대로 유지됨(/fbCommentRegister.do)
					// 그 상태에서 f5(새로고침) --> 댓글 중복으로 작성됨
					// 얼럿 창 띄우면서 확인 누르면 기존 페이지로 이동하게끔 처리
					out.print("<script>");
					out.print("alert(\"댓글 작성이 완료되었습니다!\");");
					out.print("document.location.href = \"/foreword/viewer.do?boardNum=" + fBoardNum + "\";");
					out.print("</script>");
				}
			}//end of switch
			
			
			
			// 만약 "nextPage"가 존재한다면 포워드 Go!
			if (!nextPage.equals("")) {
				RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
				dispatch.forward(request, response);
			}
				
			
 		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		
		
	} //end of doGet

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}