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

import com.gc25.dto.CommentDTO;
import com.gc25.dto.ForewordBoardDTO;
import com.gc25.service.CommentService;
import com.gc25.service.ForewordBoardService;
import com.gc25.service.ForewordViewerService;

@WebServlet("/foreword/*")
public class ForewordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ForewordBoardDTO dto;
	ForewordBoardService service;
	ForewordViewerService forewordViewerService;
	CommentService commentService;

	public void init(ServletConfig config) throws ServletException {
		dto = new ForewordBoardDTO();
		service = new ForewordBoardService();
		forewordViewerService = new ForewordViewerService();
		commentService = new CommentService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		HttpSession session = request.getSession();

		String views = "/views";
		String nextPage = "";
		String action = request.getPathInfo();

		ArrayList<ForewordBoardDTO> list = new ArrayList<>();

		try {
			// 넘어온 주소가 /forewordboard 혹은 /forewordboard/인 경우 첫 페이지로 이동
			if (action == null || action.equals("/"))
				action = "/board.do";

			switch (action) {
			// 디폴트 페이지 = 게시판 (글 목록)
			case "/board.do" -> {
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
				if (endPage > totalPage)
					endPage = totalPage;

				System.out.println("현재 정렬 기준: " + searchType);
				System.out.println("현재 페이지 번호: " + pageNum);

				// 리스트 불러오기
				list = service.getList(searchType, pageNum);

				//
				request.setAttribute("searchType", searchType);
				request.setAttribute("pageNum", pageNum);
				request.setAttribute("totalPage", totalPage);
				request.setAttribute("startPage", startPage);
				request.setAttribute("endPage", endPage);
				request.setAttribute("pagePerScreen", pagePerScreen);

				// 리스트 반환
				request.setAttribute("list", list);

				nextPage = views + "/forewordboard.jsp";
			}
			// 글 작성 페이지
			case "/write.do" -> {
				System.out.println("글 작성 페이지로 이동!!!");
				nextPage = views + "/forewordwrite.jsp";
			}
			// 글 업로드 --> 작성 완료 얼럿 창 --> 목록으로 이동
			case "/upload.do" -> {
				System.out.println("포스팅 발행!!!!");

				// session에 저장되어 있는 회원번호(현재 접속 중인) dto에 담기
				dto.setMemberNumber((Integer) (session.getAttribute("memberNumber")));
//					dto.setMemberNumber(10020);

				// write.do(글 작성 페이지)에서 받아온 정보를 dto에 담기
				// 학원번호, 학원이름, 과정구분, 제목, 내용
		
				dto = new ForewordBoardDTO();
				dto.setAcademyNumber(Integer.parseInt(request.getParameter("academyNum")));
				dto.setAcademyName(request.getParameter("academyName"));
				dto.setCourse(request.getParameter("course"));
				dto.setTitle(request.getParameter("title"));
				dto.setContents(request.getParameter("contents"));

				forewordViewerService.modifyForewordBoard(dto);

				PrintWriter out = response.getWriter();
				// forward 시 주소가 그대로 유지됨(upload.do)
				// 그 상태에서 f5(새로고침) --> 글 중복으로 작성됨
				// 얼럿 창 띄우면서 확인 누르면 기본 페이지로 이동하게끔 처리
				out.print("""
						<script>
							alert("게시글 작성 성공!");
							document.location.href = "%s/foreword";
						</script>
						""".formatted(request.getContextPath()));
			}
			// 게시글 상세보기
			case "/viewer.do" -> {

				// 해당 게시글의 게시글 번호 가져오기
				String boardNumStr = request.getParameter("boardNum");
				int boardNum = Integer.parseInt(boardNumStr);

				// 상담후기 게시글이니 수강후기게시글은 기본 0으로 셋팅??이게 필요한가???
				int aBoardNum = 0;

				// 본문 가져오기
				ForewordBoardDTO forewordBoardDTO = forewordViewerService.getForewordBoard(boardNum);
				request.setAttribute("forewordBoardDTO", forewordBoardDTO);

				// 댓글 리스트 가져오기
				ArrayList<CommentDTO> commentList = commentService.getForewordComment(boardNum);
				System.out.println("forewordController commentList 확인용:" + commentList);
				request.setAttribute("commentList", commentList);

				// 사용자 아이디 심어주기 (TEST)
				// request.getAttribute("memberNumber");
				int memberNumber = 10000;
				session.setAttribute("memberNumber", memberNumber);
				session.setAttribute("forewordBoardDTO", forewordBoardDTO);
				// request.setAttribte 하면 안됨.... 왜인지 모르겠음

				nextPage = views + "/forewordviewer.jsp";
			}
			// 추천(좋아요) 수 업데이트
			case "/recommend.do" -> {
				// 해당 게시글의 게시글 번호 가져오기
				String boardNumStr = request.getParameter("boardNum");
				int boardNum = Integer.parseInt(boardNumStr);

				// 사용자 아이디 가져오기
				// test용
				// int memberNum= (Integer) session.getAttribute("memberNumber");
				int memberNumber = 10000;
				int aBoard = 0;

				// 게시글 좋아요 수 +1 (DB에 업데이트)
				forewordViewerService.setRecommend(memberNumber, boardNum, aBoard);

				// 다음페이지 이동
				nextPage = "/foreword/viewer.do";

			}

			// 글 수정
			case "/modify.do" -> {
				// 해당 게시글의 게시글 번호 가져오기
				String boardNumStr = request.getParameter("boardNum");
				int boardNum = Integer.parseInt(boardNumStr);

				// 사용자 아이디 가져오기
				// test용
				// int memberNum= (Integer) session.getAttribute("memberNumber");
				int memberNumber = 10000;
				int aBoard = 0;

				// 글번호 db에서 정보 가져와서 수정jsp페이지로 보내주기 // 본문 가져오기
				ForewordBoardDTO forewordBoardDTO = forewordViewerService.getForewordBoard(boardNum);
				request.setAttribute("forewordBoardDTO", forewordBoardDTO);

				// 세센에 DB내용과 사용자 정보 심어서 다음페잊로 보내주기
				session.setAttribute("forewordBoardDTO", forewordBoardDTO);
				session.setAttribute("memberNumber", memberNumber);
				/*
				 * forewordViewerService.modifyForewordBoard(boardNum, academyNum, academyName,
				 * course, title, contents);
				 * 
				 * 
				 * 
				 * //변경된 내용 가져오기 //본문 가져오기 ForewordBoardDTO forewordBoardDTO =
				 * forewordViewerService.getForewordBoard(boardNum);
				 * request.setAttribute("forewordBoardDTO", forewordBoardDTO);
				 * 
				 * //댓글 리스트 가져오기 ArrayList<CommentDTO> commentList =
				 * commentService.getForewordComment(boardNum);
				 * System.out.println("forewordController commentList 확인용:"+ commentList);
				 * request.setAttribute("commentList", commentList);
				 */

				// 사용자 아이디 심어주기 (TEST)
				// request.getAttribute("memberNumber");

				// 다음페이지 이동
				nextPage = views + "/forewordmodify.jsp";
			}

			// 글 수정 업로드
			case "/modifyupload.do" -> {
				System.out.println("수정내용 업로드");

				// 해당 게시글의 게시글 번호 가져오기
				String boardNumStr = request.getParameter("boardNum");
				int boardNum = Integer.parseInt(boardNumStr);

				// 사용자 아이디 가져오기
				// test용
				// int memberNum= (Integer) session.getAttribute("memberNumber");
				int memberNumber = 10000;
				int aBoard = 0;

				ForewordBoardDTO forewordBoardDTO = (ForewordBoardDTO) session.getAttribute("forewordBoardDTO");
				// session에 저장되어 있는 회원번호(현재 접속 중인) dto에 담기
				forewordBoardDTO.setMemberNumber((Integer)(session.getAttribute("memberNumber")));
//		
				// write.do(글 작성 페이지)에서 받아온 정보를 dto에 담기
				// 학원번호, 학원이름, 과정구분, 제목, 내용
				forewordBoardDTO.setAcademyNumber(Integer.parseInt(request.getParameter("academyNum")));
				forewordBoardDTO.setAcademyName(request.getParameter("academyName"));
				forewordBoardDTO.setCourse(request.getParameter("course"));
				forewordBoardDTO.setTitle(request.getParameter("title"));
				forewordBoardDTO.setContents(request.getParameter("contents"));

				service.upload(dto);

				PrintWriter out = response.getWriter();
				// forward 시 주소가 그대로 유지됨(upload.do)
				// 그 상태에서 f5(새로고침) --> 글 중복으로 작성됨
				// 얼럿 창 띄우면서 확인 누르면 기본 페이지로 이동하게끔 처리
				out.print("""
						<script>
							alert("게시글 작성 성공!");
							document.location.href = "%s/foreword";
						</script>
						""".formatted(request.getContextPath()));


			
				// 다음페이지 이동
				nextPage = views + "/forewordviewer.jsp";
			}

			// 디폴트 페이지 = 게시판 (글 목록)
			default -> {
				nextPage = "/foreword/board.do";
			}
			}

			// 만약 "nextPage"가 존재한다면 포워드 Go!
			if (!nextPage.equals("")) {
				RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
				dispatch.forward(request, response);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
