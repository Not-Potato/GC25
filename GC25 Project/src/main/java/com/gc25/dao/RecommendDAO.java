package com.gc25.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.gc25.dto.RecommendDTO;

public class RecommendDAO {
	private Connection con; // db 연결을 위한 connection 변수
	private PreparedStatement pstmt; // SQL문 실행을 위한 변수
	private DataSource ds; // connection pool에서 db 연결 정보 조회

	public RecommendDAO() {
		try {
			Context ctx = new InitialContext(); // 톰캣에 저장되어 있는 context 정보 조회를 위한 설정
			Context env = (Context) ctx.lookup("java:/comp/env"); // context에 저장되어 있는 환경(설정) 정보 조회용
			ds = (DataSource) env.lookup("jdbc/oracle"); // connection pool 정보 조회
		} catch (Exception ex) {
			ex.printStackTrace(); // console 창에 로그(메시지) 출력
		}
	}
	
	public void recommend(int recommendNumber, String memberNumber, int fBoardNumber, int aBoardNumber) {
		try {
			String query = "INSERT INTO RECOMMEND VALUES(?, ?, ?, ?)";
			pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, recommendNumber);
			pstmt.setString(2, memberNumber);
			pstmt.setInt(3, fBoardNumber);
			pstmt.setInt(4, aBoardNumber);
			
			pstmt.executeQuery();
			   
			con.close();
			pstmt.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
