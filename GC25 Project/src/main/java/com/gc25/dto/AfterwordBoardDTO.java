package com.gc25.dto;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class AfterwordBoardDTO {

	int boardNumber;
	int memberNumber;
	int academyNumber;
	String academyName;
	String course;
	String writeDate;
	String teacherName;
	int totalScore;
	String openDate;
	String endDate;
	String major;
	String cost;
	int teacherScore;
	int facilityScore;
	int curriculumScore;
	String title;
	String contents;
	int recommend;
	int views;
	int commentCount;
	
	public AfterwordBoardDTO(int boardNumber, int memberNumber, int academyNumber, String academyName,
			String course, Timestamp writeDate, String teacherName, int totalScore, String openDate, String endDate, String major,
			String cost, int teacherScore, int facilityScore, int curriculumScore, String title, String contents,
			int recommend, int views, int commentCount) {
		this.boardNumber = boardNumber;
		this.memberNumber = memberNumber;
		this.academyNumber = academyNumber;
		this.academyName = academyName;
		this.course = course;
		this.setWriteDate(writeDate);
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
		this.recommend = recommend;
		this.views = views;
		this.commentCount = commentCount;
	}
	
	public AfterwordBoardDTO() {
		// TODO Auto-generated constructor stub
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
	
//	public void setFacilityScore(String facilityScore) {
//		this.facilityScore = facilityScore;
//	}
	// 타입 String 에서 int 로 변경
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
	
}