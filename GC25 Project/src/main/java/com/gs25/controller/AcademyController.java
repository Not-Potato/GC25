package com.gs25.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gc25.dto.AcademyDTO;
import com.gs25.service.AcademyService;

@WebServlet("/academymap/*")
public class AcademyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
		AcademyService academyService; 
		AcademyDTO academyDTO; 
	
	public void init (ServletConfig config) throws ServletException {
		academyService = new AcademyService(); 
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String views ="/views";
		String nextPage = ""; 
		String action = request.getPathInfo();
		System.out.println("controller action:" + action);
		
		ArrayList<AcademyDTO> academyList = new ArrayList(); 
		
		
		try {
			// 넘어온 주소가 /academymap 혹은 /academymap/ 인 경우 첫 페이지로 이동
			if (action == null || action.equals("/")) action = "listAcademy.do";
			else if (action.equals("/listAcademy.do")) action = "listAcademy.do";
			
			switch(action) {
				case "listAcademy.do" -> {
					// 현재 페이지 넘버 가지고 오기
					String pageNumStr = request.getParameter("pageNum");
					
					System.out.println("controller pageNumStr:" + pageNumStr);
					
					
					// 페이지 넘버 값이 없으면 1 적용 / 있으면 그 값 그대로 유지
					pageNumStr = (pageNumStr == null || pageNumStr.equals("") ? "1" : pageNumStr); 
					
					// 받아온 pageNumStr int로 캐스팅 
					int pageNum = Integer.parseInt(pageNumStr);
					
					System.out.println("controller pageNum:" + pageNum);
					
					// 한 페이지에 보여질 학원글 수
					int pagePerScreen = 1; 
					
					// 현재 게시물의 전체 페이지 수 
					int totalPage = academyService.getTotalPage();
					System.out.println("서비스에서 가져온 controller totalPage:" + totalPage);
					
					
					// 마지막 페이지 = 현재페이지 + (페이지당 글 수 - (현재페이지 % 페이지당 글 수) 
					int endPage = totalPage;
					int startPage = endPage - (endPage -1);
					
					System.out.println("controller startPage:" + startPage);
					
				
					// 리스트 불러오기
					academyList = academyService.listAcademys(pageNum);
					
					//
					request.setAttribute("pageNum", pageNum);
					request.setAttribute("totalPage", totalPage);
					request.setAttribute("startPage", startPage);
					request.setAttribute("endPage", endPage);
					request.setAttribute("pagePerScreen", pagePerScreen);
					
					// 리스트 반환
					request.setAttribute("academyList", academyList);
					
					nextPage ="../views/academymap2.jsp";
				}
			}	
			
			// 만약에 "nextPage"가 존재한다면 포워드 Go!
			if (!nextPage.equals("")) {
				RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
				dispatch.forward(request, response);
			}
			
		}catch (Exception ex) {
			ex.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
