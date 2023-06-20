package com.gc25.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.gc25.dto.AfterwordBoardDTO;
import com.gc25.dto.ForewordBoardDTO;

public class AfterwordViewerDAO {
	private Connection con; // db연결을 위한 connection 변수
	private PreparedStatement pstmt; // sql문 실행을 위한 변수
	private DataSource ds; // connection pool 에서 db 연결 정보 조회
	
	
	public AfterwordViewerDAO() {
		try {
			Context ctx = new InitialContext(); // 톰캣에 저장되어 있는 context 정보를 조회를 위한 설정 
			Context env = (Context) ctx.lookup("java:/comp/env"); // context 저장되어 있는 환경 정보 조회용
			ds = (DataSource) env.lookup("jdbc/oracle");
		} catch (NamingException ex) {
			ex.printStackTrace();
		} 
	}
	
	public AfterwordBoardDTO getAfterwordBoard(int boardNum) {
		//게시글 상세 보기로 게시글 클릭 시 views 수 증가와 동시에 게시글 불러와야함
		// views수 1 증가 쿼리 update
		String updateQuery = "UPDATE GC25_AFTERWORD_BOARD SET ab_views = ab_views + 1 WHERE ab_number = ?";
	
		
		try {
			con = ds.getConnection();	
			pstmt = con.prepareStatement(updateQuery);
			pstmt.setInt(1,boardNum);
			int updateRs = pstmt.executeUpdate();

		} catch(Exception ex) {
			ex.printStackTrace();
		}
			
		
		// update된 view 포함 게시글 가져오기
		AfterwordBoardDTO a = new AfterwordBoardDTO();
		
		 try {
			 
			
				String query = "SELECT * FROM GC25_AFTERWORD_BOARD WHERE ab_number = ?";
				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, boardNum);
				ResultSet rs = pstmt.executeQuery();
				
				
				if(rs.next()) {
					int boardNumber = rs.getInt("ab_number");
					int memberNumber = rs.getInt("m_number");
					int academyNumber = rs.getInt("a_number");
					String academyName = rs.getString("a_name");
					String afterBoardCourse = rs.getString("ab_course");
					Timestamp writeDate = rs.getTimestamp("ab_date");
					String afterBoardTeacher = rs.getString("ab_teacher");
					int afterwordBoardTotalScore = rs.getInt("ab_totalscore");
					String afterwordOpen = rs.getString("ab_open");
					String afterwordEnd = rs.getString("ab_end");
					String afterwordMajor = rs.getString("ab_major");
					String afterwordCost = rs.getString("ab_cost");
					int afterwordTeacherScore = rs.getInt("ab_teacherscore");
					int afterwordFacilityscore = rs.getInt("ab_facilityscore");
					int afterwordCurriculumScore = rs.getInt("ab_curriculumscore");
					String title = rs.getString("ab_title");
					String contents = rs.getString("ab_contents");
					int recommend = rs.getInt("ab_recommend");
					int views = rs.getInt("ab_views");
					int commentCount = rs.getInt("ab_commentcount");
					
	
					// 
					a.setBoardNumber(boardNumber);
					a.setMemberNumber(memberNumber);
					a.setAcademyNumber(academyNumber);
					a.setAcademyName(academyName);
					a.setWriteDate(writeDate);
					a.setTeacherName(afterBoardTeacher);
					a.setTotalScore(afterwordBoardTotalScore);
					a.setOpenDate(afterwordOpen);
					a.setEndDate(afterwordEnd);
					a.setMajor(afterwordMajor);
					a.setCost(afterwordCost);
					a.setTeacherScore(afterwordTeacherScore);
					a.setFacilityScore(afterwordFacilityscore);
					a.setCurriculumScore(afterwordCurriculumScore);
					a.setWriteDate(writeDate);
					a.setTitle(title);
					a.setContents(contents);
					a.setRecommend(recommend);
					a.setViews(views);
					a.setCommentCount(commentCount);

				}
			
				con.close();
				pstmt.close();
				rs.close();
				
			} catch(Exception ex) {
			ex.printStackTrace();
			}
	
			return a;
	}
	
	
	//게시글 수정
	
		public void modifyAfterwordBoard(AfterwordBoardDTO afterwordBoardDTO) {

			
			try {
				
				String updateQuery = """
									UPDATE GC25_AFTERWORD_BOARD
									SET 
									a_number = ?,
									a_name = ?,
									ab_course = ?,
									ab_title = ?,
									ab_contents = ?
									WHERE ab_number = ?
					
							""";
				
				con = ds.getConnection();	
				pstmt = con.prepareStatement(updateQuery);
				pstmt.setInt(1, afterwordBoardDTO.getAcademyNumber());
		
				pstmt.setString(2, afterwordBoardDTO.getAcademyName());
				pstmt.setString(3, afterwordBoardDTO.getCourse());
				pstmt.setString(4, afterwordBoardDTO.getTitle());
				pstmt.setString(5, afterwordBoardDTO.getContents());
				pstmt.setInt(6, afterwordBoardDTO.getBoardNumber());
				
				pstmt.executeUpdate();			
				
				
				con.close();
				pstmt.close();
			
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			
		}
		
		//게시글 삭제 
		public void deleteAfterwordBoard(int boardNum) {
			
			String deleteQuery = "DELETE GC25_AFTERWORD_BOARD WHERE ab_number  = ?";
		
			try {
				
				con = ds.getConnection();	
				pstmt = con.prepareStatement(deleteQuery);
				pstmt.setInt(1,boardNum);
				pstmt.executeUpdate();
				
				con.close();
				pstmt.close();

			} catch(Exception ex) {
				ex.printStackTrace();
			}
			
	}
	
}// end of class AfterwordViewerDAO	