package com.gc25.dto;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class ForewordBoardDTO {
	// 게시글 고유번호
	private int boardNumber;
	// 작성자(회원) 고유번호
	private int memberNumber;
	// 학원 고유번호
	private int academyNumber;
	// 학원 명
	private String academyName;
	// 과정 구분 (*이전 누락 건)
	private String course;
	// 작성일
	private String writeDate;
	// 글 제목
	private String title;
	// 글 내용
	private String contents;
	// 추천 수
	private int recommend;
	// 조회 수
	private int views;
	// 댓글 수
	private int commentCount;
	
	// 사용자 정보 가져오기 위한 추가 항목
	private String nickname; // 닉네임
	private String imageFileName; // 이미지
	
	//학원만족도(별점)
	private double academyAvgScore;// 학원 만족도
	
	
	public ForewordBoardDTO() {
		
	}

	public ForewordBoardDTO(int boardNumber, int memberNumber, int academyNumber, String academyName,
			String course, Timestamp d, String title, String contents, String nickname, String imageFileName, double academyAvgScore) {
		this.boardNumber = boardNumber;
		this.memberNumber = memberNumber;
		this.academyNumber = academyNumber;
		this.academyName = academyName;
		this.course = course;
		this.setWriteDate(d);;
		this.title = title;
		this.contents = contents;
		this.recommend = 0;
		this.views = 0;
		this.commentCount = 0;
		this.nickname = nickname;
		this.imageFileName = imageFileName;
		
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

	
	public double getAcademyAvgScore() {
		return academyAvgScore;
	}

	public void setAcademyAvgScore(double academyAvgScore) {
		this.academyAvgScore = academyAvgScore;
	}

	@Override
	public String toString() {
		return String.format("글번호: %d, 작성자 고유번호: %d, 학원 고유번호: %d, 학원 명: %s, 과정구분: %s, 작성일: %s, 제목: %s, 내용: %s, 추천수: %d, 조회수: %d, 댓글수: %d 학원평점: %d", 
				this.boardNumber, this.memberNumber, this.academyNumber, this.academyName, this.course, this.writeDate, this.title, this.contents, this.recommend, this.views, this.commentCount, this.academyAvgScore);
	}
	
	
	
}
