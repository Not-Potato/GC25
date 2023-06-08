package com.gc25.dto;

import java.sql.Date;

public class AfterwordBoardDTO {
	@Override
	public String toString() {
		return "AfterwordBoardDTO [aBoardNumber=" + aBoardNumber + ", memberNumber=" + memberNumber + ", academyNumber="
				+ academyNumber + ", academyName=" + academyName + ", aBoardDate=" + aBoardDate + ", aBoardTeacher="
				+ aBoardTeacher + ", aBoardTotalScore=" + aBoardTotalScore + ", aBoardOpen=" + aBoardOpen
				+ ", aBoardEnd=" + aBoardEnd + ", aBoardMajor=" + aBoardMajor + ", aBoardCost=" + aBoardCost
				+ ", aBoardTeacherScore=" + aBoardTeacherScore + ", aBoardFacilityScore=" + aBoardFacilityScore
				+ ", aBoardCurriculumScore=" + aBoardCurriculumScore + ", aBoardTitle=" + aBoardTitle
				+ ", aBoardContents=" + aBoardContents + ", aBoardRecommned=" + aBoardRecommned + ", aBoardViews="
				+ aBoardViews + "]";
	}


	int aBoardNumber;
	int memberNumber;
	int academyNumber;
	String academyName;
	Date aBoardDate;
	String aBoardTeacher;
	int aBoardTotalScore;
	Date aBoardOpen;
	Date aBoardEnd;
	int aBoardMajor;
	int aBoardCost;
	int aBoardTeacherScore;
	String aBoardFacilityScore;
	String aBoardCurriculumScore;
	String aBoardTitle;
	String aBoardContents;
	int aBoardRecommned;
	int aBoardViews;
	
	public AfterwordBoardDTO() {
		
	}
	
	public AfterwordBoardDTO(int aBoardNumber, int memberNumber, int academyNumber, String academyName, Date aBoardDate,
			String aBoardTeacher, int aBoardTotalScore, Date aBoardOpen, Date aBoardEnd, int aBoardMajor,
			int aBoardCost, int aBoardTeacherScore, String aBoardFacilityScore, String aBoardCurriculumScore,
			String aBoardTitle, String aBoardContents, int aBoardRecommned, int aBoardViews) {
		this.aBoardNumber = aBoardNumber;
		this.memberNumber = memberNumber;
		this.academyNumber = academyNumber;
		this.academyName = academyName;
		this.aBoardDate = aBoardDate;
		this.aBoardTeacher = aBoardTeacher;
		this.aBoardTotalScore = aBoardTotalScore;
		this.aBoardOpen = aBoardOpen;
		this.aBoardEnd = aBoardEnd;
		this.aBoardMajor = aBoardMajor;
		this.aBoardCost = aBoardCost;
		this.aBoardTeacherScore = aBoardTeacherScore;
		this.aBoardFacilityScore = aBoardFacilityScore;
		this.aBoardCurriculumScore = aBoardCurriculumScore;
		this.aBoardTitle = aBoardTitle;
		this.aBoardContents = aBoardContents;
		this.aBoardRecommned = aBoardRecommned;
		this.aBoardViews = aBoardViews;
	}
	
	public int getaBoardNumber() {
		return aBoardNumber;
	}


	public void setaBoardNumber(int aBoardNumber) {
		this.aBoardNumber = aBoardNumber;
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


	public Date getaBoardDate() {
		return aBoardDate;
	}


	public void setaBoardDate(Date aBoardDate) {
		this.aBoardDate = aBoardDate;
	}


	public String getaBoardTeacher() {
		return aBoardTeacher;
	}


	public void setaBoardTeacher(String aBoardTeacher) {
		this.aBoardTeacher = aBoardTeacher;
	}


	public int getaBoardTotalScore() {
		return aBoardTotalScore;
	}


	public void setaBoardTotalScore(int aBoardTotalScore) {
		this.aBoardTotalScore = aBoardTotalScore;
	}


	public Date getaBoardOpen() {
		return aBoardOpen;
	}


	public void setaBoardOpen(Date aBoardOpen) {
		this.aBoardOpen = aBoardOpen;
	}


	public Date getaBoardEnd() {
		return aBoardEnd;
	}


	public void setaBoardEnd(Date aBoardEnd) {
		this.aBoardEnd = aBoardEnd;
	}


	public int getaBoardMajor() {
		return aBoardMajor;
	}


	public void setaBoardMajor(int aBoardMajor) {
		this.aBoardMajor = aBoardMajor;
	}


	public int getaBoardCost() {
		return aBoardCost;
	}


	public void setaBoardCost(int aBoardCost) {
		this.aBoardCost = aBoardCost;
	}


	public int getaBoardTeacherScore() {
		return aBoardTeacherScore;
	}


	public void setaBoardTeacherScore(int aBoardTeacherScore) {
		this.aBoardTeacherScore = aBoardTeacherScore;
	}


	public String getaBoardFacilityScore() {
		return aBoardFacilityScore;
	}


	public void setaBoardFacilityScore(String aBoardFacilityScore) {
		this.aBoardFacilityScore = aBoardFacilityScore;
	}


	public String getaBoardCurriculumScore() {
		return aBoardCurriculumScore;
	}


	public void setaBoardCurriculumScore(String aBoardCurriculumScore) {
		this.aBoardCurriculumScore = aBoardCurriculumScore;
	}


	public String getaBoardTitle() {
		return aBoardTitle;
	}


	public void setaBoardTitle(String aBoardTitle) {
		this.aBoardTitle = aBoardTitle;
	}


	public String getaBoardContents() {
		return aBoardContents;
	}


	public void setaBoardContents(String aBoardContents) {
		this.aBoardContents = aBoardContents;
	}


	public int getaBoardRecommned() {
		return aBoardRecommned;
	}


	public void setaBoardRecommned(int aBoardRecommned) {
		this.aBoardRecommned = aBoardRecommned;
	}


	public int getaBoardViews() {
		return aBoardViews;
	}


	public void setaBoardViews(int aBoardViews) {
		this.aBoardViews = aBoardViews;
	}

}