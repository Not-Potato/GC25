package com.gs25.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gc25.dto.AcademyDTO;
import com.gs25.service.AcademyService;

@WebServlet("/academymap2/*")
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
		String nextPage =""; 
		String action = request.getPathInfo();
		
		ArrayList<AcademyDTO> academyList = new ArrayList(); 
		
		try {
			if (action == null || action.equals("/")) action = "listAcademy.do";
			
			switch(action) {
				case "listAcademy.do" -> {
					academyList = academyService.listAcademys();
					
					nextPage = views + "/listAcademy.jsp";
				}
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
