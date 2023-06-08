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
			ds = (DataSource)env.lookup("jdbc/oracle");
		}catch (Exception ex) {
			ex.printStackTrace();
		}	
	}
	
	
	// 학원 리스트 전체 보기 (확인용)
	public ArrayList<AcademyDTO> selectAllArticle() {
		ArrayList<AcademyDTO> list = new ArrayList();
		
		try {
			con = ds.getConnection();
			
			String query ="""
					SELECT a_number,a_name,a_tel,a_address,a_avgscore
					FROM GC25_ACADEMY	
				""";
			
			
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int academyNumber = rs.getInt("a_number");
				String academyName = rs.getString("a_name"); 
				String academyTel = rs.getString("a_tel"); 
				String academyAddress = rs.getString("a_address");
				int academyAvgScore = rs.getInt("a_avgscore");
				
				AcademyDTO academy = new AcademyDTO(); 
				
				academy.setAcademyNumber(academyNumber);
				academy.setAcademyName(academyName);
				academy.setAcademyTel(academyTel);
				academy.setAcademyAddress(academyAddress);
				academy.setAcademyAvgScore(academyAvgScore);
				
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
	
	
}//end of academyDAO
