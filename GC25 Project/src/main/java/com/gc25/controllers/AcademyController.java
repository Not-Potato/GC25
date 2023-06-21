package com.gc25.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.gc25.dto.AcademyDTO;
import com.gc25.service.AcademyService;

@WebServlet("/academy/*")
public class AcademyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AcademyService academyService; 
	AcademyDTO academyDTO; 

    public AcademyController() {
    }

	public void init(ServletConfig config) throws ServletException {
		academyService = new AcademyService(); 
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String views ="/views";
		String nextPage = ""; 
		String action = request.getPathInfo();
		System.out.println("controller action:" + action);
		
		List<AcademyDTO> academyList = new ArrayList<AcademyDTO>(); 
		
		
		try {
			// 넘어온 주소가 /academymap 혹은 /academymap/ 인 경우 첫 페이지로 이동
			if (action == null || action.equals("/")) action = "map.do";
			else if (action.equals("/map.do")) action = "map.do";
			
			switch(action) {
				case "map.do" -> {
					// 현재 페이지 넘버 가지고 오기
					String pageNumStr = request.getParameter("pageNum");
					String searchValue = request.getParameter("searchValue");
					
					if (searchValue != null) {searchValue = searchValue.toUpperCase();}
					
					System.out.println("academycontroller 검색어" + searchValue);
					
					HttpSession session = request.getSession();
					
					
					// 페이지 넘버 값이 없으면 1 적용 / 있으면 그 값 그대로 유지
					pageNumStr = (pageNumStr == null || pageNumStr.equals("") ? "1" : pageNumStr); 
					
					// 받아온 pageNumStr int로 캐스팅 
					int pageNum = Integer.parseInt(pageNumStr);
					
				
					// 한 페이지에 보여질 학원글 수 : 1개
					int pagePerScreen = 1; 
					
					// 현재 게시물의 전체 페이지 수 
					int totalPage = academyService.getTotalPage(searchValue);
					
					
					// 마지막 페이지 = 현재페이지 + (페이지당 글 수 - (현재페이지 % 페이지당 글 수) 
					int endPage = totalPage;
					int startPage = endPage - (endPage -1);
					
					
					// 리스트 불러오기
					academyList = academyService.listAcademys(pageNum, searchValue);
					
					
					request.setAttribute("pageNum", pageNum);
					request.setAttribute("totalPage", totalPage);
					request.setAttribute("startPage", startPage);
					request.setAttribute("endPage", endPage);
					request.setAttribute("pagePerScreen", pagePerScreen);
					
					
					// 리스트 반환
					request.setAttribute("academyList", academyList);
					
					nextPage ="../views/academymap.jsp";
				}
		
				case "/search" -> {
					// 검색창에 입력된 값 받아오기
					String keyword = request.getParameter("keyword");
					System.out.println("컨트롤러에 들어온 키워드: " + keyword);
					
					// keyword 통해 리스트 받아오기
					academyList = academyService.autoComplete(keyword);
					System.out.println(academyList);
					
					// JSONArray 선언
					JSONArray jsonArray = new JSONArray();
					
					// academyList에서 꺼내서 JSONArray에 담기
					for (AcademyDTO data : academyList) {
						JSONObject academyData = new JSONObject();
						academyData.put("academyNumber", data.getAcademyNumber());
						academyData.put("academyName", data.getAcademyName());
						jsonArray.add(academyData);
					}
					
					// 전송할 JSON 객체 생성
					JSONObject jobj = new JSONObject();
					// JSON 객체에 JSONArray에 담기
					jobj.put("list", jsonArray);
					// 출력해 보기
					System.out.println(jobj.toJSONString());
					
					// 보낼 양식 정하기
					response.setContentType("application/x-json; charset=utf-8");
					// 보내기
					response.getWriter().print(jobj.toJSONString());
				}
				case "/searchjustone" -> {
					// 입력된 academyName 값 받아오기
					String academyName = request.getParameter("academyName");
					System.out.println("컨트롤러에 들어온 학원명: " + academyName);
					
					// academyName와 일치하는 Data 존재하는지 확인하기
					int result = academyService.searchJustOne(academyName);
					System.out.println("존재하면 학원 번호가, 존재하지 않으면 0이 출력되어야 함!! 결과는 [" + result + "]");
					
					response.setContentType("text/html; charset=UTF-8");
					response.getWriter().print(result);
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
