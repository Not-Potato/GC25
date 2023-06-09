package com.gc25.dto;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class ForewordBoardDTO {
	// 게시글 고유번호
	int boardNumber;
	// 작성자(회원) 고유번호
	int memberNumber;
	// 학원 고유번호
	int academyNumber;
	// 학원 명
	String academyName;
	// 과정 구분 (*이전 누락 건)
	String course;
	// 작성일
	String writeDate;
	// 글 제목
	String title;
	// 글 내용
	String contents;
	// 추천 수
	int recommned;
	// 조회 수
	int views;
	// 댓글 수
	int commentCount;
	
	public ForewordBoardDTO() {
		
	}

	public ForewordBoardDTO(int boardNumber, int memberNumber, int academyNumber, String academyName,
			String course, Timestamp d, String title, String contents) {
		this.boardNumber = boardNumber;
		this.memberNumber = memberNumber;
		this.academyNumber = academyNumber;
		this.academyName = academyName;
		this.course = course;
		this.setWriteDate(d);;
		this.title = title;
		this.contents = contents;
		this.recommned = 0;
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

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(Timestamp d) {
		Date date = new Date(d.getTime());
	    
	    // 날짜 형식을 원하는 형식으로 포맷팅
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
		this.writeDate = sdf.format(date);
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

	public int getRecommned() {
		return recommned;
	}

	public void setRecommned(int recommned) {
		this.recommned = recommned;
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
