package com.gc25.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gc25.dto.CommentDTO;
import com.gc25.dto.ForewordBoardDTO;
import com.gc25.service.CommentService;
import com.gc25.service.ForewordBoardService;
import com.gc25.service.ForewordViewerService;

@WebServlet("/forewordboard/*")
public class ForewordBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ForewordBoardDTO dto;
	ForewordBoardService service;
	ForewordViewerService forewordViewerService;
	CommentService commentService;
	
	public void init(ServletConfig config) throws ServletException {
		dto = new ForewordBoardDTO();
		commentService = new CommentService();
		service = new ForewordBoardService();
		forewordViewerService = new ForewordViewerService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		HttpSession session = request.getSession();
		
		String board = "/views";
		String nextPage = "";
		String action = request.getPathInfo();
		ArrayList<ForewordBoardDTO> list = new ArrayList<>();
		
		try {
			// 넘어온 주소가 /forewordboard 혹은 /forewordboard/인 경우 첫 페이지로 이동
			if (action == null || action.equals("/*")) action = "/forewordboard.do";
			
			switch (action) {
				case "/forewordboard.do" -> {
					// 현재 페이지의 정렬 기준, 페이지 넘버 가지고 오기
					String searchType = request.getParameter("searchType");
					String pageNumStr = request.getParameter("pageNum");
					
					// 정렬 기준 값이 존재하지 않으면 "최신순" 적용 / 있으면 값 그대로 유지
					searchType = (searchType == null || searchType.equals("") ? "최신순" : searchType);
					// 페이지 넘버 값이 없으면 1 적용 / 있으면 그 값 그대로 유지					
					pageNumStr = (pageNumStr == null || pageNumStr.equals("") ? "1" : pageNumStr);
					
					// 받아온 pageNumStr int로 캐스팅
					int pageNum = Integer.parseInt(pageNumStr);
					
					// 한 페이지에 보여질 게시글 수
					int pagePerScreen = 10;
					// 현재 게시물의 전체 페이지 수
					int totalPage = service.getTotalPage();
					
					// 마지막 페이지 = 현재페이지 + (페이지당 게시글 수 - (현재페이지 % 페이지당 게시글 수))
					int endPage = pageNum + (pagePerScreen - (pageNum % pagePerScreen));
					int startPage = endPage - (pagePerScreen - 1);
					if (endPage > totalPage) endPage = totalPage;
					
				
					// 리스트 불러오기
					list = service.getList(searchType, pageNum);
					
					request.setAttribute("searchType", searchType);
					request.setAttribute("pageNum", pageNum);
					request.setAttribute("totalPage", totalPage);
					request.setAttribute("startPage", startPage);
					request.setAttribute("endPage", endPage);
					request.setAttribute("pagePerScreen", pagePerScreen);
					
					// 리스트 반환
					request.setAttribute("list", list);
					
					//사용자 아이디 심어주기 (TEST)
					int memberNum = 10000;
					session.setAttribute("memberNum", memberNum); 
					
					nextPage = board + "/forewordboard.jsp";
				}
				
				
			//게시글 상세보기	
				case "/forewordviewer.do"-> {
					
					//해당 게시글의 게시글 번호 가져오기
					String boardNumStr = request.getParameter("boardNum");
					int boardNum = Integer.parseInt(boardNumStr);
					
					//상담후기 게시글이니 수강후기게시글은 기본 0으로 셋팅??이게 필요한가??? 
					int aBoardNum = 0;
					
					//본문 가져오기
					ForewordBoardDTO forewordBoardDTO = forewordViewerService.getForewordBoard(boardNum);					
					request.setAttribute("forewordBoardDTO", forewordBoardDTO);
					
					//댓글 리스트 가져오기
					ArrayList<CommentDTO> commentList =  commentService.getForewordComment(boardNum);
				 	request.setAttribute("commentList", commentList);
				 	
				 	
					//사용자 아이디 심어주기 (TEST)
					int memberNum = 10000;
					session.setAttribute("memberEmail", memberNum); 
					
					
					nextPage = board + "/forewordviewer.jsp";					
				}
				
			//추천(좋아요) 수 업데이트	
				case "/recommend.do" ->{
					//해당 게시글의 게시글 번호 가져오기
					String boardNumStr = request.getParameter("boardNum");
					int boardNum = Integer.parseInt(boardNumStr);
					
					//사용자 아이디 가져오기
					int memberNum= (Integer) session.getAttribute("memberNum"); 
					int aBoard = 0; 
					
					//게시글 좋아요 수 +1 (DB에 업데이트)
					forewordViewerService.setRecommend(memberNum, boardNum, aBoard);
	
					//다음페이지 이동
					nextPage = "/forewordboard/forewordviewer.do";
					
				}
				
				// 위에 있는 case 중에 해당되는 거 없으면 무조건 여기로 이동
				default -> {
					nextPage = board + "/forewordboard.jsp";
				}
			}//end fo switch
			
			
			
			// 만약 "nextPage"가 존재한다면 포워드 Go!
			if (!nextPage.equals("")) {
				RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
				dispatch.forward(request, response);
			}
			
 		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}