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
	
	//댓글 리스트  전체 보기 (확인용) 
	public ArrayList<CommentDTO> selectAllComments() {
		ArrayList<CommentDTO> list = new ArrayList(); 
		
		try {
			con = ds.getConnection(); 
			
			String query ="""
					SELECT c_number,ab_number,fb_number,m_number,c_contents,c_date
					FROM GC25_COMMENT; 
					""";
			
		pstmt = con.prepareStatement(query); 
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			int commentNumber = rs.getInt("c_number");
			int aBoardNumber = rs.getInt("ab_number");
			int fBoardNumber = rs.getInt("fb_number");
			int memberNumber = rs.getInt("m_number");
			String commentContents = rs.getString("c_contents");
			Date commentDate = rs.getDate("c_date");
			
			CommentDTO comment = new CommentDTO(); 
			
			comment.setCommentNumber(commentNumber); 
			comment.setaBoardNumber(aBoardNumber);
			comment.setfBoardNumber(fBoardNumber);
			comment.setMemberNumber(memberNumber);
			comment.setCommentContents(commentContents);
			comment.setCommentDate(commentDate);
			
			list.add(comment); 
		}
		rs.close(); 
		pstmt.close(); 
		con.close(); 
			
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return list; 
	}
	
}//end of CommentDAO
