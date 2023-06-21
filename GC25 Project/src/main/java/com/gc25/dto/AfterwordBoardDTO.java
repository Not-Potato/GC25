package com.gc25.dto;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import oracle.jdbc.proxy._Proxy_;

public class AfterwordBoardDTO {
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
	// 강사 명
	String teacherName;
	// 개강일 --> String 변경
	String openDate;
	// 종강일 --> String 변경
	String endDate;
	// 전공 여부 --> 데이터 타입 변경 ("전공" / "비전공"으로 저장)
//	int major;
	String major;
	// 유/무상 여부 --> 데이터 타입 변경 ("유상" / "무상"으로 저장)
//	int cost;
	String cost;
	// 전체 만족도
	int totalScore;
	// 강사 만족도
	int teacherScore;
	// 학원 시설 만족도 --> 데이터 타입 변경 (1 ~ 10 숫자 저장)
//	String facilityScore;
	int facilityScore;
	// 커리큘럼 만족도  --> 데이터 타입 변경 (1 ~ 10 숫자 저장)
//	String curriculumScore;
	int curriculumScore;
	// 글 제목
	String title;
	// 글 내용
	String contents;
	// 추천 수
	int recommend;
	// 조회 수
	int views;
	// 댓글 수
	int commentCount;
	
	// 사용자 정보 가져오기 위한 추가 항목
	private String nickname; // 닉네임
	private String imageFileName; // 이미지
	
	//학원만족도(별점)
	private double academyAvgScore;// 학원 만족도
	
	public AfterwordBoardDTO() {
		
	}
	
	public AfterwordBoardDTO(int boardNumber, int memberNumber, int academyNumber, String academyName,
			String course,  Timestamp date, String teacherName, int totalScore, String openDate, String endDate,
			String major, String cost, int teacherScore, int facilityScore, int curriculumScore, String title,
			String contents, int commentCount, String nickname, String imageFileName) {
		super();
		this.boardNumber = boardNumber;
		this.memberNumber = memberNumber;
		this.academyNumber = academyNumber;
		this.academyName = academyName;
		this.course = course;
		// 생성자 내에서 세터 호출하는 거 가능하다고는 하는데 실제로 되는지 테스트해 봐야 함!
		this.setWriteDate(date);
		this.teacherName = teacherName;
		this.totalScore = totalScore;
		this.openDate = openDate;
		this.endDate = endDate;
		this.major = major;
		this.cost = cost;
		this.teacherScore = teacherScore;
		this.facilityScore = facilityScore;
		this.curriculumScore = curriculumScore;
		this.title = title;
		this.contents = contents;
		this.recommend = 0;
		this.views = 0;
		this.commentCount = 0;
	}

	public double getAcademyAvgScore() {
		return academyAvgScore;
	}

	public void setAcademyAvgScore(double academyAvgScore) {
		this.academyAvgScore = academyAvgScore;
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

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public int getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}

	public String getOpenDate() {
		return openDate;
	}

	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public int getTeacherScore() {
		return teacherScore;
	}

	public void setTeacherScore(int teacherScore) {
		this.teacherScore = teacherScore;
	}

	public int getFacilityScore() {
		return facilityScore;
	}

	public void setFacilityScore(int facilityScore) {
		this.facilityScore = facilityScore;
	}

	public int getCurriculumScore() {
		return curriculumScore;
	}

	public void setCurriculumScore(int curriculumScore) {
		this.curriculumScore = curriculumScore;
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

	@Override
	public String toString() {
		return "AfterwordBoardDTO [글 번호: " + boardNumber + ", 작성자 번호: " + memberNumber + ", 학원 번호: "
				+ academyNumber + ", 학원 이름: " + academyName + ", 작성일: " + writeDate + ", 강사명: "
				+ teacherName + ", 총점: " + totalScore + ", 개강일: " + openDate
				+ ", 종강일: " + endDate + ", 전공 여부: " + major + ", 유/무상 여부: " + cost
				+ ", 강사 만족도: " + teacherScore + ", 시설 만족도: " + facilityScore
				+ ", 커리큘럼 만족도: " + commentCount + ", 제목: " + title
				+ ", 내용: " + contents + ", 추천 수: " + recommend + ", 조회수: " + views + ", 댓글수: " + commentCount +"]";
	}

}