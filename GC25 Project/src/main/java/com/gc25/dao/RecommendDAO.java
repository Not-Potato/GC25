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
	

	//수강후기 게시글 내 좋아요 수 업데이트
	public void setRecommend(int memberNum, int boardNum, int bBoard) {
	
		// 게시글 당 사용자가 좋아요 1번만 누를수 있게 하는 장치
		try {
			
			con = ds.getConnection();	
			
			String checkQuery = "SELECT COUNT(*) AS like_count FROM GC25_RECOMMEND WHERE M_NUMBER = ? AND AB_NUMBER = ?";
			pstmt = con.prepareStatement(checkQuery);
			pstmt.setInt(1, memberNum);
			pstmt.setInt(2, boardNum);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
					int likeCount = rs.getInt("like_count");
					if (likeCount > 0) {
						return;// 매소드 종료
					}
			}
		
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		
	// 만약 사용자가 좋아요 누르지 않았다면 아래 코드부 진행
		// 게시글 내 추천 수 증가
		try {
			
			String updateQueryAfterwordBoard = "UPDATE GC25_AFTERWORD_BOARD SET ab_recommend = ab_recommend + 1 WHERE AB_NUMBER = ?";
			pstmt = con.prepareStatement(updateQueryAfterwordBoard);
			pstmt.setInt(1,boardNum);
			int updateRs = pstmt.executeUpdate();
			
		}catch (Exception ex) {
			ex.printStackTrace();
		}
	
		// 추천내용 DB에 추가	
		String updateQueryRecommend = """
									INSERT INTO GC25_RECOMMEND 
									(R_NUMBER, M_NUMBER, FB_NUMBER, AB_NUMBER) 
									VALUES (SEQ_GC25_RECOMMEND.nextval, ?, ?, ? )
								""";
		try {
		
			pstmt = con.prepareStatement(updateQueryRecommend);
			pstmt.setInt(1,memberNum);
			pstmt.setInt(2,0);
			pstmt.setInt(3,boardNum);
			pstmt.executeUpdate();
			
			con.close();
			pstmt.close();
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
			
	}// end of setRecommend
	
	
	
	
	//상담후기 게시글
	public void fbSetRecommend(int memberNum, int boardNum, int bBoard) {
		
		// 게시글 당 사용자가 좋아요 1번만 누를수 있게 하는 장치
		try {
			
			con = ds.getConnection();	
			
			String checkQuery = "SELECT COUNT(*) AS like_count FROM GC25_RECOMMEND WHERE m_number = ? AND fb_number = ?";
			pstmt = con.prepareStatement(checkQuery);
			pstmt.setInt(1, memberNum);
			pstmt.setInt(2, boardNum);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int likeCount = rs.getInt("like_count");
				if (likeCount > 0) {
					return; // 이미 좋아요 눌렀다면 매소드 종료
				}
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		
			// 만약 사용자가 좋아요 누르지 않았다면 아래 코드부 진행
			// 게시글_ 추천 수 증가
			String updateQueryforewordBoard = "UPDATE GC25_FOREWORD_BOARD SET fb_recommend = fb_recommend + 1 WHERE fB_number = ?";
			
			try {
				con = ds.getConnection();	
				//추천 수 증가
				pstmt = con.prepareStatement(updateQueryforewordBoard);
				pstmt.setInt(1,boardNum);
				int updateRs = pstmt.executeUpdate();
				
			} catch(Exception ex) {
				ex.printStackTrace();
			}
				
				
		// 추천내용 DB에 추가	
			String updateQueryRecommend = """
										INSERT INTO GC25_RECOMMEND 
										(r_number, m_number, fb_number, ab_number) 
										VALUES (SEQ_GC25_RECOMMEND.nextval, ?, ?, ? )
									""";
			try {
			
				pstmt = con.prepareStatement(updateQueryRecommend);
				pstmt.setInt(1,memberNum);
				pstmt.setInt(2,boardNum);
				pstmt.setInt(3,0);
				pstmt.executeUpdate();
				
				 //  첫 좋아요 클릭  시 참 반환
				
			
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		
		}
	
}//end of class recommendDAO