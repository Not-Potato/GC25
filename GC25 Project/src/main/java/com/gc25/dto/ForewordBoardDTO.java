package com.gc25.dto;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class ForewordBoardDTO {
	
	int boardNumber;// 게시글 고유번호
	int memberNumber;// 작성자(회원) 고유번호
	int academyNumber;// 학원 고유번호
	String academyName;// 학원 명
	String course;// 과정 구분 (*이전 누락 건)
	Timestamp writeDate;// 작성일
	String title;// 글 제목
	String contents;// 글 내용
	int recommend;// 추천 수
	int views;// 조회 수
	int commentCount;// 댓글 수
	
	
	public ForewordBoardDTO() {
	
	}


	public ForewordBoardDTO(int boardNumber, int memberNumber, int academyNumber, String academyName,
			String course, Timestamp writeDate, String title, String contents) {
		this.boardNumber = boardNumber;
		this.memberNumber = memberNumber;
		this.academyNumber = academyNumber;
		this.academyName = academyName;
		this.course = course;
		this.setWriteDate(writeDate);
		this.title = title;
		this.contents = contents;
		this.recommend = 0;
		this.views = 0;
		this.commentCount = 0;
	}

	public int getBoardNumber() {
		return boardNumber;
	}

	public void setBoardNumber(int boardNumber) {
		this.boardNumber = boardNumber;
	}

	public int getMemberNumber() {
		return memberNumber;
	}

	public void setMemberNumber(int memberNumber) {
		this.memberNumber = memberNumber;
	}

	public int getAcademyNumber() {
		return academyNumber;
	}

	public void setAcademyNumber(int academyNumber) {
		this.academyNumber = academyNumber;
	}

	public String getAcademyName() {
		return academyName;
	}

	public void setAcademyName(String academyName) {
		this.academyName = academyName;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public Timestamp getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(Timestamp d) {
	    
	    // 날짜 형식을 원하는 형식으로 포맷팅
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
		this.writeDate = d;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public int getRecommend() {
		return recommend;
	}

	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	
	
	
}