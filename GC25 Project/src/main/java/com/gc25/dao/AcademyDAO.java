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
	
	
	// 전체 리스트 보기
	public ArrayList<AcademyDTO> selectAllArticle() {
		ArrayList<AcademyDTO> list = new ArrayList();
		
		try {
			con = ds.getConnection();
			
			String query ="""
					SELECT a_index, a_name, a_tel, a_address, a_postal, a_satisfaction
					FROM a_academy		
				""";
			
			
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int a_index = rs.getInt("a_index");
				String a_name = rs.getString("a_name"); 
				int a_tel = rs.getInt("a_tel"); 
				String a_address = rs.getString("a_address");
				int a_satisfaction = rs.getInt("a_satisfaction");
				
				AcademyDTO academy = new AcademyDTO(); 
				
				academy.setA_index(a_index);
				academy.setA_name(a_name);
				academy.setA_tel(a_tel);
				academy.setA_address(a_address);
				academy.setA_satisfaction(a_satisfaction);
				
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
	
	
	
	// 검색결과보기
}//end of academyDAO
