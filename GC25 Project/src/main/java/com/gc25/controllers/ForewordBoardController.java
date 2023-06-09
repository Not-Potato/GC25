package com.gc25.controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gc25.dto.ForewordBoardDTO;
import com.gc25.service.ForewordBoardService;

@WebServlet("/forewordboard/*")
public class ForewordBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ForewordBoardDTO dto;
	ForewordBoardService service;

	public void init(ServletConfig config) throws ServletException {
		dto = new ForewordBoardDTO();
		service = new ForewordBoardService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		
		String board = "/views";
		String nextPage = "";
		String action = request.getPathInfo();
		
		ArrayList<ForewordBoardDTO> list = new ArrayList<>();
		
		try {
			// 넘어온 주소가 /forewordboard 혹은 /forewordboard/인 경우 첫 페이지로 이동
			if (action == null || action.equals("/")) action = "/forewordboardlist.do";
			
			switch (action) {
				case "/forewordboardlist.do" -> {
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
					
					nextPage = board + "/forewordboardlist.jsp";
				}
				
				// 위에 있는 case 중에 해당되는 거 없으면 무조건 여기로 이동
				default -> {
					nextPage = board + "/forewordboardlist.jsp";
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
