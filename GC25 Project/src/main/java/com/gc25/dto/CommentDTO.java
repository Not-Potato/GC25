package com.gc25.dto;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class CommentDTO {
	
	private int commentNumber; //댓글 고유번호 (자동넘버) 
	private int aBoardNumber; //수강후기 글 번호 
	private int fBoardNumber; //상담후기 글 번호
	private int memberNumber; //회원 번호
	private String commentContents; //댓글내용
	private String commentDate; //작성일자
	
	// 사용자 정보 가져오기 위한 추가 항목
	private String nickname; // 닉네임
	private String imageFileName; // 이미지
	
	public CommentDTO() {
	}

	public CommentDTO(int commentNumber, int aBoardNumber, int fBoardNumber, int memberNumber, String commentContents,
			Timestamp d, String nickname, String imageFileName) {
		super();
		this.commentNumber = commentNumber;
		this.aBoardNumber = aBoardNumber;
		this.fBoardNumber = fBoardNumber;
		this.memberNumber = memberNumber;
		this.commentContents = commentContents;
		this.setCommentDate(d);;
		this.nickname = nickname;
		this.imageFileName = imageFileName;
	}

	public int getCommentNumber() {
		return commentNumber;
	}

	public void setCommentNumber(int commentNumber) {
		this.commentNumber = commentNumber;
	}

	public int getaBoardNumber() {
		return aBoardNumber;
	}

	public void setaBoardNumber(int aBoardNumber) {
		this.aBoardNumber = aBoardNumber;
	}

	public int getfBoardNumber() {
		return fBoardNumber;
	}

	public void setfBoardNumber(int fBoardNumber) {
		this.fBoardNumber = fBoardNumber;
	}

	public int getMemberNumber() {
		return memberNumber;
	}

	public void setMemberNumber(int memberNumber) {
		this.memberNumber = memberNumber;
	}

	public String getCommentContents() {
		return commentContents;
	}

	public void setCommentContents(String commentContents) {
		this.commentContents = commentContents;
	}

	public String getCommentDate() {
		return commentDate;
	}
	
	public void setCommentDate(Timestamp d) {
		Date date = new Date(d.getTime());
	    
	    // 날짜 형식을 원하는 형식으로 포맷팅
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
		this.commentDate = sdf.format(date);
	}
	
	//DB 조회용
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	@Override
	public String toString() {
		return "CommentDTO [commentNumber=" + commentNumber + ", aBoardNumber=" + aBoardNumber + ", fBoardNumber="
				+ fBoardNumber + ", memberNumber=" + memberNumber + ", commentContents=" + commentContents
				+ ", commentDate=" + commentDate + ", nickname=" + nickname + ", imageFileName=" + imageFileName + "]";
	}
}