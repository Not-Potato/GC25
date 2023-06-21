package com.gc25.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
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
import com.gc25.dto.ForewordBoardDTO;
import com.gc25.service.AfterwordBoardService;
import com.gc25.service.AfterwordViewerService;
import com.gc25.service.CommentService;

@WebServlet("/afterword/*")
public class AfterwordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AfterwordBoardDTO dto;
	AfterwordBoardService service;
	AfterwordViewerService afterwordViewerService;
	CommentService commentService;

	public void init(ServletConfig config) throws ServletException {
		dto = new AfterwordBoardDTO();
		service = new AfterwordBoardService();
		afterwordViewerService = new AfterwordViewerService();
		commentService = new CommentService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		HttpSession session = request.getSession();
		
		String views = "/views";
		String nextPage = "";
		String action = request.getPathInfo();
		
		ArrayList<AfterwordBoardDTO> list = new ArrayList<>();
		
		try {
			// 넘어온 주소가 /afterwordboard 혹은 /afterwordboard/인 경우 첫 페이지로 이동
			if (action == null || action.equals("/")) action = "/board.do";
			
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
					if (endPage > totalPage) endPage = totalPage;
					
					
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
					
					nextPage = views + "/afterwordboard.jsp";
				}
				// 글 작성 페이지
				case "/write.do" -> {
					System.out.println("글 작성 페이지로 이동!!!");
					nextPage = views + "/afterwordwrite.jsp";
				}
				// 글 업로드 --> 작성 완료 얼럿 창 --> 목록으로 이동
				case "/upload.do" -> {
					System.out.println("포스팅 발행!!!!");
					
					// session에 저장되어 있는 회원번호(현재 접속 중인) dto에 담기
					dto.setMemberNumber((Integer)(session.getAttribute("memberNumber")));
//					dto.setMemberNumber(10020);
					
					// 개강일과 종강일은 같이 들어오기 때문에 받아서 split으로 잘라서 담아야 함
					String openToEnd = request.getParameter("openToEnd");
					String open = openToEnd.split(" ~ ")[0];
					String end = openToEnd.split(" ~ ")[1];
					
					// 체크박스 --> null(off)로 넘어오면 각각 "비전공", "무상"
					String major = request.getParameter("major") == null ? "비전공" : request.getParameter("major");
					String cost = request.getParameter("cost") == null ? "무상" : request.getParameter("cost");

					// write.do(글 작성 페이지)에서 받아온 정보를 dto에 담기
					// 학원번호, 학원이름, 과정구분, 제목, 내용
					dto.setAcademyNumber(Integer.parseInt(request.getParameter("academyNum")));
					dto.setAcademyName(request.getParameter("academyName"));
					dto.setCourse(request.getParameter("course"));
					dto.setTitle(request.getParameter("title"));
					dto.setContents(request.getParameter("contents"));
					// 수강후기에서 추가된 항목들도 dto에 담기
					// 강사 명, 개강일, 종강일, 전공/비전공 여부, 유/무상 여부, 전체 만족도, 강사 만족도, 학원시설 만족도, 커리큘럼 만족도
					dto.setTeacherName(request.getParameter("teacher"));
					dto.setOpenDate(open);
					dto.setEndDate(end);
					dto.setMajor(major);
					dto.setCost(cost);
					dto.setTotalScore(Integer.parseInt(request.getParameter("totalScore")));
					dto.setTeacherScore(Integer.parseInt(request.getParameter("teacherScore")));
					dto.setFacilityScore(Integer.parseInt(request.getParameter("facScore")));
					dto.setCurriculumScore(Integer.parseInt(request.getParameter("curriScore")));
					
					service.upload(dto);
					
					session.setAttribute("memberStatus", 1);
					
					PrintWriter out = response.getWriter();
					// forward 시 주소가 그대로 유지됨(upload.do) 
					// 그 상태에서 f5(새로고침) --> 글 중복으로 작성됨
					// 얼럿 창 띄우면서 확인 누르면 기본 페이지로 이동하게끔 처리
					out.print("<script>");
					out.print("alert(\"게시글 작성이 완료되었습니다!\");");
					out.print("document.location.href = \"/afterword\";");
					out.print("</script>");
				}
				
				//게시글 상세보기	
				case "/viewer.do" -> {
				
					//해당 게시글의 게시글 번호 가져오기
					String boardNumStr = request.getParameter("boardNum");
					int boardNum = Integer.parseInt(boardNumStr);
					
					
					//수강후기 게시글이니 상담후기게시글은 기본 0으로 셋팅
					int fBoardNum = 0;
					
					//본문 가져오기 
				 	AfterwordBoardDTO afterwordBoardDTO = afterwordViewerService.getAfterwordBoard(boardNum);
				 	request.setAttribute("afterwordBoardDTO", afterwordBoardDTO);
				 	session.setAttribute("afterwordBoardDTO", afterwordBoardDTO);
				 	
				 	//댓글 리스트 가져오기 
				 	ArrayList<CommentDTO> commentList =  commentService.getAfterwordComment(boardNum);
				 	request.setAttribute("commentList", commentList);
				 	
				 	
					//사용자 아이디 심어주기 (TEST)
					//int memberNum = 10000;
					//session.setAttribute("memberEmail", memberNum); 
					//memberNum = (Integer)session.getAttribute("memberNumber");
					//session.setAttribute("memberNumber", memberNum);
					
				 	
					
					//다음페이지 이동
					nextPage = views + "/afterwordviewer.jsp";
				}
				
			//추천(좋아요) 수 업데이트	
				case "/recommend.do" ->{
					//해당 게시글의 게시글 번호 가져오기
					String boardNumStr = request.getParameter("boardNum");
					int boardNum = Integer.parseInt(boardNumStr);
					
					//사용자 아이디 가져오기
					int memberNum= (Integer) session.getAttribute("memberNumber"); 
					int bBoard = 0; 
					
					//게시글 좋아요 수 +1 (DB에 업데이트)
					afterwordViewerService.setRecommend(memberNum, boardNum, bBoard);
	
					//다음페이지 이동
					nextPage = "/afterword/viewer.do";
					
				}
				
				// 글 수정
				case "/modify.do" -> {
					// 해당 게시글의 게시글 번호 가져오기
					String boardNumStr = request.getParameter("boardNum");
					int boardNum = Integer.parseInt(boardNumStr);

					int bBoard = 0;

					// 글번호 db에서 정보 가져와서 수정jsp페이지로 보내주기 // 본문 가져오기
					AfterwordBoardDTO afterwordBoardDTO = afterwordViewerService.getAfterwordBoard(boardNum);
					request.setAttribute("afterwordBoardDTO", afterwordBoardDTO);

					// 세센에 DB내용과 사용자 정보 심어서 다음페이지로 보내주기
					session.setAttribute("afterwordBoardDTO", afterwordBoardDTO);
					
					// 다음페이지 이동
					nextPage = views + "/afterwordmodify.jsp";
				}

				// 글 수정 업로드
				case "/modifyupload.do" -> {

					// 해당 게시글의 게시글 번호 가져오기
					String boardNumStr = request.getParameter("boardNum");
					int boardNum = Integer.parseInt(boardNumStr);

				
					int bBoard = 0;

					AfterwordBoardDTO afterwordBoardDTO = (AfterwordBoardDTO) session.getAttribute("afterwordBoardDTO");
					// session에 저장되어 있는 회원번호(현재 접속 중인) dto에 담기
					afterwordBoardDTO.setMemberNumber((Integer)(session.getAttribute("memberNumber")));
			
					// write.do(글 작성 페이지)에서 받아온 정보를 dto에 담기
					// 학원번호, 학원이름, 과정구분, 제목, 내용
					afterwordBoardDTO.setAcademyNumber(Integer.parseInt(request.getParameter("academyNum")));
					afterwordBoardDTO.setAcademyName(request.getParameter("academyName"));
					afterwordBoardDTO.setCourse(request.getParameter("course"));
					afterwordBoardDTO.setTitle(request.getParameter("title"));
					afterwordBoardDTO.setContents(request.getParameter("contents"));

					afterwordViewerService.modifyAfterwordBoard(afterwordBoardDTO);

					PrintWriter out = response.getWriter();
					// forward 시 주소가 그대로 유지됨(upload.do)
					// 그 상태에서 f5(새로고침) --> 글 중복으로 작성됨
					// 얼럿 창 띄우면서 확인 누르면 기본 페이지로 이동하게끔 처리
					out.print("""
							<script>
								alert("게시글 수정 성공!");
								document.location.href = "%s/afterword";
							</script>
							""".formatted(request.getContextPath()));

				}case "/delete.do" -> {

					// 해당 게시글의 게시글 번호 가져오기
					String boardNumStr = request.getParameter("boardNum");
					int boardNum = Integer.parseInt(boardNumStr);

				
					//본문 삭제
					afterwordViewerService.deleteAfterwordBoard(boardNum);
					//댓글 삭제
					commentService.deleteFbComment(boardNum);
					

					// 다음페이지 이동
					nextPage = "/foreword/board.do";
				}
				
				// 디폴트 페이지 = 게시판 (글 목록)
				default -> {
					nextPage = "/afterword/board.do";
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
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
