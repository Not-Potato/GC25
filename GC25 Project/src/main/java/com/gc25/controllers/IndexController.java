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

import com.gc25.dto.AcademyDTO;
import com.gc25.dto.AfterwordBoardDTO;
import com.gc25.dto.CommentDTO;
import com.gc25.dto.ForewordBoardDTO;
import com.gc25.service.IndexService;
import com.gc25.service.AfterwordBoardService;
import com.gc25.service.AfterwordViewerService;
import com.gc25.service.CommentService;
import com.gc25.service.ForewordBoardService;
import com.gc25.service.ForewordViewerService;

@WebServlet("/main")
public class IndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ForewordBoardDTO fdto;
	ForewordBoardService fservice;

	AfterwordBoardDTO adto;
	AfterwordBoardService aservice;
	
	AcademyDTO acDto;
	IndexService indexService;
	
	public void init(ServletConfig config) throws ServletException {
		
		fdto = new ForewordBoardDTO();
		fservice = new ForewordBoardService();
	
		adto = new AfterwordBoardDTO();
		aservice = new AfterwordBoardService();
		
		acDto = new AcademyDTO();
		indexService = new IndexService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		HttpSession session = request.getSession();

		String nextPage = "";
		String action = request.getPathInfo();
		
		ArrayList<AfterwordBoardDTO> aList = new ArrayList<>();
		ArrayList<ForewordBoardDTO> fList = new ArrayList<>();
		ArrayList<AcademyDTO> academyAvg = new ArrayList<>();
		ArrayList<AcademyDTO> academyRev = new ArrayList<>();
		
		try {
			// 넘어온 주소가 없거나 /인 경우 --> /로 변경
			if (action == null || action.equals("/")) action = "/main";
			
			switch (action) {
				// 디폴트 페이지 => main
				case "/main" -> {
					// 정렬 기준 = 최신순
					String searchType = "최신순";
					// 현재 페이지 = 1
					int pageNum = 1;
					
					// 리스트 불러오기
					aList = aservice.getList(searchType, pageNum);
					fList = fservice.getList(searchType, pageNum);
					academyAvg = indexService.getAvg();
					academyRev = indexService.getRev();
					
					// 리스트 내보내기
					request.setAttribute("aList", aList);
					request.setAttribute("fList", fList);
					request.setAttribute("avgList", academyAvg);
					request.setAttribute("revList", academyRev);
					
					//test
					session.setAttribute("memberNumber", 10000);
					
					nextPage = "/index.jsp";
				}
				// 디폴트 페이지 = main
				default -> {
					nextPage = "/main";
				}
			}
			
			// 만약 "nextPage"가 존재한다면 리다렉 Go!
			if (!nextPage.equals("")) {
				RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
				dispatch.forward(request, response);
//				response.sendRedirect(nextPage);
			}
 		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
