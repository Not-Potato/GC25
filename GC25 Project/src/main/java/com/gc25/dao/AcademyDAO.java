package com.gc25.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.gc25.dto.AcademyDTO;

public class AcademyDAO {
	private Connection con; // db 연결을 위한 connection 변수
	private PreparedStatement pstmt; // SQL문 실행을 위한 변수
	private DataSource ds; // connection pool에서 db 연결 정보 조회
	
	
	public AcademyDAO() {
		try {
			Context ctx = new InitialContext(); 
			Context env = (Context) ctx.lookup("java:/comp/env");	
			ds = (DataSource) env.lookup("jdbc/oracle");
		}catch (Exception ex) {
			ex.printStackTrace();
		}	
	}
	
	
	// 학원 리스트 전체 보기 (확인용)
//	public ArrayList<AcademyDTO> selectArticle(int pageNumber) {
//		ArrayList<AcademyDTO> list = new ArrayList<AcademyDTO>();
//		
//		try {
//			con = ds.getConnection();
//			
//			String query ="""
//						SELECT a_name, a_tel, a_address, a_roadAddress, a_avgscore, a_url, a_x, a_y
//						FROM GC25_ACADEMY
//					""";	
//			System.out.println(query);
//			
//			pstmt = con.prepareStatement(query);
//			ResultSet rs = pstmt.executeQuery();
//			
//			while (rs.next()) {
//				String academyName = rs.getString("a_name");			
//				String academyTel = rs.getString("a_tel"); 
//				String academyAddress = rs.getString("a_address");
//				String academyRoadAddress = rs.getString("a_roadAddress");
//				int academyAvgScore = rs.getInt("a_url");
//				String academyUrl = rs.getString("place_url"); 
//				String academyX = rs.getString("x");
//				String academyY = rs.getString("y");
//				
//				AcademyDTO academy = new AcademyDTO(); 
//				
//				academy.setAcademyName(academyName);
//				academy.setAcademyTel(academyTel);
//				academy.setAcademyAddress(academyAddress);
//				academy.setAcademyRodeAddress(academyRoadAddress);
//				academy.setAcademyUrl(academyUrl);
//				academy.setAcademyAvgScore(academyAvgScore);
//				academy.setAcademyX(academyX);
//				academy.setAcademyY(academyY);
//			
//				list.add(academy); 
//			}
//			
//			rs.close(); 
//			pstmt.close(); 
//			con.close(); 
//		
//		}catch(Exception ex) {
//			ex.printStackTrace();
//		}
//		
//		return list; 
//	}
	
	// 검색어 적용 학원 리스트 가져오기
		public ArrayList<AcademyDTO> selectArticle(int pageNumber , String searchValue) {
			ArrayList<AcademyDTO> list = new ArrayList<AcademyDTO>();
			
			try {
				con = ds.getConnection();
				
				String query ="""
							SELECT a_name, a_tel, a_address, a_roadAddress, a_avgscore, a_url, a_x, a_y
							FROM GC25_ACADEMY 
							WHERE a_name LIKE ? OR a_address LIKE ? OR a_roadAddress LIKE ?
						""";	
				
				pstmt = con.prepareStatement(query);
			
				pstmt.setString(1,"%"+searchValue+"%"); 
				pstmt.setString(2,"%" +searchValue+"%"); 
				pstmt.setString(3,"%" +searchValue+"%");
				
				System.out.println(query);
				
			
				
				ResultSet rs = pstmt.executeQuery();
				
				while (rs.next()) {
					String academyName = rs.getString("a_name");			
					String academyTel = rs.getString("a_tel"); 
					String academyAddress = rs.getString("a_address");
					String academyRoadAddress = rs.getString("a_roadAddress");
					int academyAvgScore = rs.getInt("a_avgscore");
					String academyUrl = rs.getString("a_url"); 
					String academyX = rs.getString("a_x");
					String academyY = rs.getString("a_y");
					
					AcademyDTO academy = new AcademyDTO(); 
					
					academy.setAcademyName(academyName);
					academy.setAcademyTel(academyTel);
					academy.setAcademyAddress(academyAddress);
					academy.setAcademyRodeAddress(academyRoadAddress);
					academy.setAcademyUrl(academyUrl);
					academy.setAcademyAvgScore(academyAvgScore);
					academy.setAcademyX(academyX);
					academy.setAcademyY(academyY);
				
					list.add(academy); 
				}
				
				rs.close(); 
				pstmt.close(); 
				con.close(); 
			
			}catch(Exception ex) {
				ex.printStackTrace();
			}
			
			return list; 
		}
	
	
	//검색결과기준 전체 페이지 가져오기
	public int getTotalPage(String searchValue) {
		int totalPage = 0;
		
		try {
			
				con = ds.getConnection(); 
			
			//	String query = "SELECT CEIL(COUNT(*)) total_page from GC25_ACADEMY";
				String query = """
								SELECT COUNT(DISTINCT a_number) AS TOTAL_COUNT FROM GC25_ACADEMY
								WHERE a_name LIKE ? OR a_address LIKE ? OR a_roadAddress LIKE ?
						""";
				System.out.println(query);
				
				pstmt = con.prepareStatement(query); 
				
				pstmt.setString(1,"%"+searchValue+"%"); 
				pstmt.setString(2,"%" +searchValue+"%"); 
				pstmt.setString(3,"%" +searchValue+"%");
				
				
				ResultSet rs = pstmt.executeQuery();
						
				
				if (rs.next()) {
					totalPage = rs.getInt(1);
				
				
					System.out.println("전체 페이지:" + totalPage);
				}
				rs.close(); 
				pstmt.close(); 
				con.close(); 
				
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return totalPage;
	}
	
	
	
}//end of academyDAO
