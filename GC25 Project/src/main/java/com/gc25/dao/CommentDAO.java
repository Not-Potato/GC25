package com.gc25.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.gc25.dto.CommentDTO;

public class CommentDAO {
	private Connection con; // db 연결을 위한 connection 변수
	private PreparedStatement pstmt; // SQL문 실행을 위한 변수
	private DataSource ds; // connection pool에서 db 연결 정보 조회
	
	
	public CommentDAO() {
		try {
			Context ctx = new InitialContext();
			Context env = (Context) ctx.lookup("java:/comp/env");	
			ds = (DataSource)env.lookup("jdbc/oracle");			
		}catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	//수강후기 게시판 댓글 작성 
	public void abAddComment(int aBoardNum, int bBoardNum, int memberNum, String commentContents) {
		
		//댓글 DB에 추가	
		String query ="""
					INSERT INTO GC25_COMMENT 
					(c_number, ab_number, fb_number, m_number, c_contents,c_date) 
					VALUES (seq_GC25_COMMENT.nextval, ?, ?, ?, ?, SYSDATE)
				""";

		try {
			con = ds.getConnection(); 
			pstmt = con.prepareStatement(query); 
			
			pstmt.setInt(1, aBoardNum);
			pstmt.setInt(2, 0);
			pstmt.setInt(3, memberNum);
			pstmt.setString(4, commentContents);
			pstmt.executeUpdate(); 	
			
			
		}catch (Exception ex) {
				ex.printStackTrace();
		}

	//수강후기게시글에 댓글 숫자 +1 	
		try {
			
			String updateCommentCountQuery ="""
					UPDATE GC25_AFTERWORD_BOARD 
					SET AB_COMMENTCOUNT = AB_COMMENTCOUNT + 1 
					WHERE AB_NUMBER = ?
				"""; 
			
			con = ds.getConnection();	
			pstmt = con.prepareStatement(updateCommentCountQuery); 
			pstmt.setInt(1, aBoardNum);
			pstmt.executeUpdate(); 	
			
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		
		
	} //end of abAddComment
	
	
	
	//상담후기 게시판 댓글 작성 
	public void fbAddComment(int aBoardNum, int bBoardNum, int memberNum, String commentContents) {
		
		//댓글 DB에 추가	
		String query ="""
					INSERT INTO GC25_COMMENT 
					(c_number, ab_number, fb_number, m_number, c_contents, c_date) 
					VALUES (seq_GC25_COMMENT.nextval, ?, ?, ?, ?, SYSDATE)
				""";

		try {
			con = ds.getConnection(); 
			pstmt = con.prepareStatement(query); 
			
			pstmt.setInt(1, 0);
			pstmt.setInt(2, bBoardNum);
			pstmt.setInt(3, memberNum);
			pstmt.setString(4, commentContents);
			pstmt.executeUpdate(); 	
			
			
		}catch (Exception ex) {
				ex.printStackTrace();
		}

	//상담후기게시글에 댓글 숫자 +1 	
		try {
			
			String updateCommentCountQuery ="""
					UPDATE GC25_FOREWORD_BOARD 
					SET FB_COMMENTCOUNT = FB_COMMENTCOUNT + 1 
					WHERE FB_NUMBER = ?
				"""; 
			
			con = ds.getConnection();	
			pstmt = con.prepareStatement(updateCommentCountQuery); 
			pstmt.setInt(1, bBoardNum);
			pstmt.executeUpdate(); 	
			
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		
	} //end of abAddComment
	
	

	//댓글 수강후기게시판 댓글가져오기(id 매개변수 활용)  
	public ArrayList<CommentDTO> getAfterwordComment(int boardNum) {
		ArrayList<CommentDTO> commentList = new ArrayList(); 
		
		try {
			con = ds.getConnection(); 
			
			String query ="""
					SELECT c.c_number, c.ab_number, c.fb_number, c.m_number, c.c_contents, c.c_date, m.m_nickname AS nickname, m.m_imagefilename AS image
					FROM GC25_COMMENT c
					JOIN GC25_MEMBER m ON c.m_number = m.m_number
					WHERE c.ab_number = ?
					ORDER BY c.c_date DESC
					""";
			
		pstmt = con.prepareStatement(query); 
		pstmt.setInt(1, boardNum);
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			
			int commentNumber = rs.getInt("c_number");
			int aBoardNumber = rs.getInt("ab_number");
			int fBoardNumber = rs.getInt("fb_number");
			int memberNumber = rs.getInt("m_number");
			String commentContents = rs.getString("c_contents");
			Date commentDate = rs.getDate("c_date");
			
			String nickname = rs.getString("nickname");
			String imageFileName = rs.getString("image");
			
			
			CommentDTO comment = new CommentDTO(); 
			
			comment.setCommentNumber(commentNumber); 
			comment.setaBoardNumber(aBoardNumber);
			comment.setfBoardNumber(fBoardNumber);
			comment.setMemberNumber(memberNumber);
			comment.setCommentContents(commentContents);
			comment.setCommentDate(commentDate);
			
			comment.setNickname(nickname);
			comment.setImageFileName(imageFileName);
			
			commentList.add(comment); 
			
		}
		rs.close(); 
		pstmt.close(); 
		con.close(); 
			
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return commentList; 
	}
	

	//댓글 상담후기게시판 댓글가져오기(id 매개변수 활용)  
	public ArrayList<CommentDTO> getForewordComment(int boardNum) {
		ArrayList<CommentDTO> commentList = new ArrayList(); 
		
		try {
			con = ds.getConnection(); 
			
			String query ="""
					SELECT c.c_number, c.ab_number, c.fb_number, c.m_number, c.c_contents, c.c_date, m.m_nickname AS nickname, m.m_imagefilename AS image
					FROM GC25_COMMENT c
					JOIN GC25_MEMBER m ON c.m_number = m.m_number
					WHERE c.fb_number = ?
					ORDER BY c.c_date DESC
					""";
			
		pstmt = con.prepareStatement(query); 
		pstmt.setInt(1, boardNum);
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			int commentNumber = rs.getInt("c_number");
			int aBoardNumber = rs.getInt("ab_number");
			int fBoardNumber = rs.getInt("fb_number");
			int memberNumber = rs.getInt("m_number");
			String commentContents = rs.getString("c_contents");
			Date commentDate = rs.getDate("c_date");
			
			String nickname = rs.getString("nickname");
			String imageFileName = rs.getString("image");
			
			
			CommentDTO comment = new CommentDTO(); 
			
			comment.setCommentNumber(commentNumber); 
			comment.setaBoardNumber(aBoardNumber);
			comment.setfBoardNumber(fBoardNumber);
			comment.setMemberNumber(memberNumber);
			comment.setCommentContents(commentContents);
			comment.setCommentDate(commentDate);
			
			comment.setNickname(nickname);
			comment.setImageFileName(imageFileName);
			
			commentList.add(comment); 
			
		}
		rs.close(); 
		pstmt.close(); 
		con.close(); 
			
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return commentList; 
	}
	
	//게시글 삭제 시 댓글도 함께 삭제되어야함
	//상담후기 게시판 댓글 삭제
		public void deleteFbComment(int bBoardNum) {
			
			//댓글 DB에 추가	
			String query = "DELETE GC25_COMMENT WHERE fb_number  = ?";
					

			try {
				con = ds.getConnection(); 
				pstmt = con.prepareStatement(query); 
				
				pstmt.setInt(1, bBoardNum);
			
				pstmt.executeUpdate(); 					
								
			}catch (Exception ex) {
					ex.printStackTrace();
			}
		}	
	
}//end of CommentDAO