package com.gc25.service;

import java.util.ArrayList;

import com.gc25.dao.CommentDAO;
import com.gc25.dto.CommentDTO;

public class CommentService {

	CommentDAO commentDAO;
	
	public CommentService() {
		commentDAO = new CommentDAO();
	}
	
		
	public ArrayList<CommentDTO> getAfterwordComment(int boardNum) {
		return commentDAO.getAfterwordComment(boardNum);
	}
	
	public ArrayList<CommentDTO> getForewordComment(int boardNum) {
		return commentDAO.getForewordComment(boardNum);
	}
	
	public void abAddComment(int aBoardNum, int fBoardNum, int memberNum, String commentContents) {
		commentDAO.abAddComment(aBoardNum, fBoardNum, memberNum, commentContents);
	}
	
	public void fbAddComment(int aBoardNum, int fBoardNum, int memberNum, String commentContents) {
		System.out.println("commnet서비스 확인용 출력입니다.");
		commentDAO.fbAddComment(aBoardNum, fBoardNum, memberNum, commentContents);
	}
}