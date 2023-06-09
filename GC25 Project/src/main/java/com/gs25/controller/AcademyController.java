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

		//테스트 출력
		System.out.println("controller action 출력: "+ action );
		
		ArrayList<AcademyDTO> academyList = new ArrayList(); 
		
		//테스트 출력
		System.out.println("action이 null인가?" + action == null);
		System.out.println("action이 '/' 인가? "+ action.equals("/"));
		System.out.println("action이 null이거나 '/'인가요? "+ action == null || action.equals("/"));
		
		try {
			if (action == null || action.equals("/")) action = "listAcademy.do";
			else if (action.equals("/listAcademy.do")) action = "listAcademy.do";
			
			switch(action) {
				case "listAcademy.do" -> {
					academyList = academyService.listAcademys();
					request.setAttribute("academyList", academyList);
					
					nextPage ="../views/academymap2.jsp";
				}
			}	
			
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
