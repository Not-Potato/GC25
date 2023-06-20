package com.gc25.dao;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.catalina.connector.Response;

import com.gc25.dto.AcademyDTO;
import com.gc25.dto.AfterwordBoardDTO;

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
	
	
	// 형주 작업 영역

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

	
	
	// 자연 작업 영역
	
	// 자동 완성 함수 (검색어 --> 문자열 포함 여부 검사해서 5개 가져오기)
	public ArrayList<AcademyDTO> autoComplete(String keyword) {
		ArrayList<AcademyDTO> list = new ArrayList<AcademyDTO>();
		System.out.println("auto 메소드 실행");
		
		try {
			con = ds.getConnection();
			
			String query = "SELECT a_number, a_name " +
								"FROM (SELECT a_number, a_name " +
								"FROM GC25_ACADEMY " +
								"WHERE a_name LIKE ?) " +
								"WHERE ROWNUM <= 5";	
			System.out.println("자동완성 쿼리 실행!");
			System.out.println(query);
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "%" + keyword + "%");
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int academyNumber = rs.getInt("a_number");
				String academyName = rs.getString("a_name");
				
				AcademyDTO academy = new AcademyDTO(); 
				
				academy.setAcademyNumber(academyNumber);
				academy.setAcademyName(academyName);
			
				list.add(academy); 
				//System.out.println(academy);
			}
			if(con!=null)try{con.close();} catch(Exception ex){}
			if(pstmt!=null) try{pstmt.close();} catch(Exception ex){}
			if(rs!=null)try{rs.close();} catch(Exception ex){}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
	
	// 글 작성 시 일치하는 학원 명 DB에 존재하는지 검사하는 메소드
	public int searchJustOne(String academyName) {
		String keyword = academyName;
		int result = 0;
		try {
			con = ds.getConnection();
			
			String query = "SELECT COUNT(*) FROM GC25_ACADEMY WHERE a_name = ?";	
			System.out.println(query);
			
			System.out.println("화면 --> dao에 넘어온 학원 이름: " + keyword);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, keyword);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
	            result = rs.getInt(1); // 결과(개수) 가져오기
	        
	        }
			if (result != 0) {
				query = "SELECT a_number FROM GC25_ACADEMY WHERE a_name = ?";	
				System.out.println(query);
				
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, keyword);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					result = rs.getInt("a_number"); // 결과(학원 번호) 가져오기
				}
			
			}
			
			if(con!=null)try{con.close();} catch(Exception ex){}
			if(pstmt!=null) try{pstmt.close();} catch(Exception ex){}
			if(rs!=null)try{rs.close();} catch(Exception ex){}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}
	
	public ArrayList<AcademyDTO> getAvg() {
		ArrayList<AcademyDTO> academyAvg = new ArrayList<>();
		try {
			con = ds.getConnection();
			
			
			String query = """
						SELECT a_name, a_avgscore
						FROM GC25_ACADEMY
						ORDER BY A_AVGSCORE DESC
						FETCH FIRST 3 ROWS ONLY
					""";
			System.out.println(query);
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				
				AcademyDTO academy = new AcademyDTO();
				String academyName = rs.getString("A_NAME");
				double academyAvgScore =rs.getDouble("A_AVGSCORE");
				
	            academy.setAcademyName(academyName);
				academy.setAcademyAvgScore(academyAvgScore);
				
	            academyAvg.add(academy);
	            System.out.println("academy dao avg:"+academyAvg);
			}
			
			if(con!=null)try{con.close();} catch(Exception ex){}
			if(pstmt!=null) try{pstmt.close();} catch(Exception ex){}
			if(rs!=null)try{rs.close();} catch(Exception ex){}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	
		return academyAvg;
	}
	
	
	public ArrayList<AcademyDTO> getRev() {
		ArrayList<AcademyDTO> academyRev = new ArrayList<>();
		try {
			con = ds.getConnection();
			
			
			String query = """
						SELECT a_name, a_reviewcount
						FROM GC25_ACADEMY
						ORDER BY a_reviewcount DESC
						FETCH FIRST 3 ROWS ONLY
					""";
			System.out.println(query);
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				
				AcademyDTO academyR = new AcademyDTO();
				String academyName = rs.getString("A_NAME");
				int academyReviewCount =rs.getInt("A_REVIEWCOUNT");
				
	            academyR.setAcademyName(academyName);
				academyR.setAcademyReviewCount(academyReviewCount);
				
				academyRev.add(academyR);
				System.out.println("academy dao rev:"+academyRev);
	            
			}
			if(con!=null)try{con.close();} catch(Exception ex){}
			if(pstmt!=null) try{pstmt.close();} catch(Exception ex){}
			if(rs!=null)try{rs.close();} catch(Exception ex){}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	
		return academyRev;
	}
	
	
	
	
	
	
}//end of academyDAO