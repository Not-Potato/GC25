package com.gc25.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.gc25.dto.AfterwordBoardDTO;
import com.gc25.dto.ForewordBoardDTO;

public class ForewordViewerDAO {
	private Connection con;
	private PreparedStatement pstmt;
	private DataSource ds;
	
	
	public ForewordViewerDAO() {
		try {
			Context ctx = new InitialContext(); // 톰캣에 저장되어 있는 context 정보 조회를 위한 설정
			Context env = (Context)ctx.lookup("java:/comp/env"); // context에 저장되어 있는 환경(설정) 정보 조회용
			ds = (DataSource) env.lookup("jdbc/oracle"); // connection pool 정보 조회
		
		} catch (Exception ex) {
			ex.printStackTrace(); // 
		}
	}
	
	public ForewordBoardDTO getForewordBoard(int boardNum) {
			//게시글 상세 보기로 게시글 클릭 시 views 수 증가와 동시에 게시글 불러와야함
			// views수 1 증가 쿼리 update
			String updateQuery = "UPDATE GC25_FOREWORD_BOARD SET Fb_views = fb_views + 1 WHERE fb_number  = ?";
		
			try {
				con = ds.getConnection();	
				pstmt = con.prepareStatement(updateQuery);
				pstmt.setInt(1,boardNum);
				int updateRs = pstmt.executeUpdate();

			} catch(Exception ex) {
				ex.printStackTrace();
			}
			
			// update된 view포함 게시글 가져오기
			ForewordBoardDTO b = new ForewordBoardDTO();
			
			try {
				
				String query = "SELECT * FROM GC25_FOREWORD_BOARD WHERE fb_number = ?";
				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, boardNum);
				ResultSet rs = pstmt.executeQuery();
				
				
				if(rs.next()) {				
					int fBoardNumber = rs.getInt("fb_number");
					int mNumber = rs.getInt("m_number");
					int academyNumber = rs.getInt("a_number");
					String academyName = rs.getString("a_name");
					String fBoardCourse = rs.getString("fb_course");
					Timestamp fBoardDate = rs.getTimestamp("fb_date");
					String fBoardTitle = rs.getString("fb_title");
					String fBoardContents = rs.getString("fb_contents");
					int fBoardRecommend = rs.getInt("fb_recommend");
					int fBoardViews = rs.getInt("fb_views");
					int fBoardCommentCount = rs.getInt("fb_commentcount");
					
					b.setBoardNumber(fBoardNumber);
					b.setMemberNumber(mNumber);
					b.setAcademyNumber(academyNumber);
					b.setAcademyName(academyName);
					b.setCourse(fBoardCourse);
					b.setWriteDate(fBoardDate);
					b.setTitle(fBoardTitle);
					b.setContents(fBoardContents);
					b.setRecommend(fBoardRecommend);
					b.setViews(fBoardViews);
					b.setCommentCount(fBoardCommentCount);
					
				}
				
				con.close();
				pstmt.close();
				rs.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			
			return b;
	}
}// end of class ForewordViewerDAO	