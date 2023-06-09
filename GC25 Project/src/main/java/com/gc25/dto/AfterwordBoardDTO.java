package com.gc25.dto;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class AfterwordBoardDTO {
//	@Override
//	public String toString() {
//		return "AfterwordBoardDTO [aBoardNumber=" + aBoardNumber + ", memberNumber=" + memberNumber + ", academyNumber="
//				+ academyNumber + ", academyName=" + academyName + ", aBoardDate=" + aBoardDate + ", aBoardTeacher="
//				+ aBoardTeacher + ", aBoardTotalScore=" + aBoardTotalScore + ", aBoardOpen=" + aBoardOpen
//				+ ", aBoardEnd=" + aBoardEnd + ", aBoardMajor=" + aBoardMajor + ", aBoardCost=" + aBoardCost
//				+ ", aBoardTeacherScore=" + aBoardTeacherScore + ", aBoardFacilityScore=" + aBoardFacilityScore
//				+ ", aBoardCurriculumScore=" + aBoardCurriculumScore + ", aBoardTitle=" + aBoardTitle
//				+ ", aBoardContents=" + aBoardContents + ", aBoardRecommned=" + aBoardRecommned + ", aBoardViews="
//				+ aBoardViews + "]";
//	}
//
//	// 게시글 고유번호
//	int aBoardNumber;
//	// 작성자(회원) 고유번호
//	int memberNumber;
//	// 학원 고유번호
//	int academyNumber;
//	// 학원 명
//	String academyName;
//	// 과정 구분 (*이전 누락 건)
//	String aBoardCourse;
//	// 작성일
//	String aBoardDate;
//	// 강사 명
//	String aBoardTeacher;
//	// 평점
//	int aBoardTotalScore;
//	// 개강일
//	Date aBoardOpen;
//	// 종강일
//	Date aBoardEnd;
//	// 전공 여부
//	int aBoardMajor;
//	// 유/무상 여부
//	int aBoardCost;
//	// 강사 만족도
//	int aBoardTeacherScore;
//	// 학원 시설
//	String aBoardFacilityScore;
//	// 커리큘럼 만족도
//	String aBoardCurriculumScore;
//	// 글 제목
//	String aBoardTitle;
//	// 글 내용
//	String aBoardContents;
//	// 추천 수
//	int aBoardRecommned;
//	// 조회 수
//	int aBoardViews;
//	// 댓글 수
//	int aBoardCommentCount;
//	
//	public AfterwordBoardDTO() {
//		
//	}
//	
//	public AfterwordBoardDTO(int aBoardNumber, int memberNumber, int academyNumber, String academyName, String aBoardCourse, Timestamp date,
//			String aBoardTeacher, int aBoardTotalScore, Date aBoardOpen, Date aBoardEnd, int aBoardMajor,
//			int aBoardCost, int aBoardTeacherScore, String aBoardFacilityScore, String aBoardCurriculumScore,
//			String aBoardTitle, String aBoardContents, int aBoardRecommned, int aBoardViews, int aBoardCommentCount) {
//		this.aBoardNumber = aBoardNumber;
//		this.memberNumber = memberNumber;
//		this.academyNumber = academyNumber;
//		this.academyName = academyName;
//		this.aBoardCourse = aBoardCourse;
//		// 생성자 내에서 세터 호출하는 거 가능하다고는 하는데 실제로 되는지 테스트해 봐야 함!
//		this.setaBoardDate(date);
//		this.aBoardTeacher = aBoardTeacher;
//		this.aBoardTotalScore = aBoardTotalScore;
//		this.aBoardOpen = aBoardOpen;
//		this.aBoardEnd = aBoardEnd;
//		this.aBoardMajor = aBoardMajor;
//		this.aBoardCost = aBoardCost;
//		this.aBoardTeacherScore = aBoardTeacherScore;
//		this.aBoardFacilityScore = aBoardFacilityScore;
//		this.aBoardCurriculumScore = aBoardCurriculumScore;
//		this.aBoardTitle = aBoardTitle;
//		this.aBoardContents = aBoardContents;
//		this.aBoardRecommned = aBoardRecommned;
//		this.aBoardViews = aBoardViews;
//		this.aBoardCommentCount = aBoardCommentCount;
//	}
//	
//	public int getaBoardNumber() {
//		return aBoardNumber;
//	}
//
//
//	public void setaBoardNumber(int aBoardNumber) {
//		this.aBoardNumber = aBoardNumber;
//	}
//
//
//	public int getMemberNumber() {
//		return memberNumber;
//	}
//
//
//	public void setMemberNumber(int memberNumber) {
//		this.memberNumber = memberNumber;
//	}
//
//
//	public int getAcademyNumber() {
//		return academyNumber;
//	}
//
//
//	public void setAcademyNumber(int academyNumber) {
//		this.academyNumber = academyNumber;
//	}
//
//
//	public String getAcademyName() {
//		return academyName;
//	}
//
//
//	public void setAcademyName(String academyName) {
//		this.academyName = academyName;
//	}
//
//
//	public String getaBoardDate() {
//		return aBoardDate;
//	}
//
//
//	public void setaBoardDate(Timestamp aBoardDate) {
//		Date date = new Date(aBoardDate.getTime());
//        
//        // 날짜 형식을 원하는 형식으로 포맷팅
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//		this.aBoardDate = sdf.format(date);
//	}
//
//
//	public String getaBoardTeacher() {
//		
//		return aBoardTeacher;
//	}
//
//
//	public void setaBoardTeacher(String aBoardTeacher) {
//		this.aBoardTeacher = aBoardTeacher;
//	}
//
//
//	public int getaBoardTotalScore() {
//		return aBoardTotalScore;
//	}
//
//
//	public void setaBoardTotalScore(int aBoardTotalScore) {
//		this.aBoardTotalScore = aBoardTotalScore;
//	}
//
//
//	public Date getaBoardOpen() {
//		return aBoardOpen;
//	}
//
//
//	public void setaBoardOpen(Date aBoardOpen) {
//		this.aBoardOpen = aBoardOpen;
//	}
//
//
//	public Date getaBoardEnd() {
//		return aBoardEnd;
//	}
//
//
//	public void setaBoardEnd(Date aBoardEnd) {
//		this.aBoardEnd = aBoardEnd;
//	}
//
//
//	public int getaBoardMajor() {
//		return aBoardMajor;
//	}
//
//
//	public void setaBoardMajor(int aBoardMajor) {
//		this.aBoardMajor = aBoardMajor;
//	}
//
//
//	public int getaBoardCost() {
//		return aBoardCost;
//	}
//
//
//	public void setaBoardCost(int aBoardCost) {
//		this.aBoardCost = aBoardCost;
//	}
//
//
//	public int getaBoardTeacherScore() {
//		return aBoardTeacherScore;
//	}
//
//
//	public void setaBoardTeacherScore(int aBoardTeacherScore) {
//		this.aBoardTeacherScore = aBoardTeacherScore;
//	}
//
//
//	public String getaBoardFacilityScore() {
//		return aBoardFacilityScore;
//	}
//
//
//	public void setaBoardFacilityScore(String aBoardFacilityScore) {
//		this.aBoardFacilityScore = aBoardFacilityScore;
//	}
//
//
//	public String getaBoardCurriculumScore() {
//		return aBoardCurriculumScore;
//	}
//
//
//	public void setaBoardCurriculumScore(String aBoardCurriculumScore) {
//		this.aBoardCurriculumScore = aBoardCurriculumScore;
//	}
//
//
//	public String getaBoardTitle() {
//		return aBoardTitle;
//	}
//
//
//	public void setaBoardTitle(String aBoardTitle) {
//		this.aBoardTitle = aBoardTitle;
//	}
//
//
//	public String getaBoardContents() {
//		return aBoardContents;
//	}
//
//
//	public void setaBoardContents(String aBoardContents) {
//		this.aBoardContents = aBoardContents;
//	}
//
//
//	public int getaBoardRecommend() {
//		return aBoardRecommend;
//	}
//
//
//	public void setaBoardRecommend(int aBoardRecommend) {
//		this.aBoardRecommend = aBoardRecommend;
//	}
//
//
//	public int getaBoardViews() {
//		return aBoardViews;
//	}
//
//
//	public void setaBoardViews(int aBoardViews) {
//		this.aBoardViews = aBoardViews;
//	}
//
//	public int getaBoardCommentCount() {
//		return aBoardCommentCount;
//	}
//
//	public void setaBoardCommentCount(int aBoardCommentCount) {
//		this.aBoardCommentCount = aBoardCommentCount;
//	}
//
//	public String getaBoardCourse() {
//		return aBoardCourse;
//	}
//
//	public void setaBoardCourse(String aBoardCourse) {
//		this.aBoardCourse = aBoardCourse;
//	}

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
	// 평점
	int totalScore;
	// 개강일
	Date openDate;
	// 종강일
	Date endDate;
	// 전공 여부
	int major;
	// 유/무상 여부
	int cost;
	// 강사 만족도
	int teacherScore;
	// 학원 시설
	String facilityScore;
	// 커리큘럼 만족도
	String curriculumScore;
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
	
	public AfterwordBoardDTO() {
		
	}
	
	public AfterwordBoardDTO(int boardNumber, int memberNumber, int academyNumber, String academyName,
			String course,  Timestamp date, String teacherName, int totalScore, Date openDate, Date endDate,
			int major, int cost, int teacherScore, String facilityScore, String curriculumScore, String title,
			String contents) {
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

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getMajor() {
		return major;
	}

	public void setMajor(int major) {
		this.major = major;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getTeacherScore() {
		return teacherScore;
	}

	public void setTeacherScore(int teacherScore) {
		this.teacherScore = teacherScore;
	}

	public String getFacilityScore() {
		return facilityScore;
	}

	public void setFacilityScore(String facilityScore) {
		this.facilityScore = facilityScore;
	}

	public String getCurriculumScore() {
		return curriculumScore;
	}

	public void setCurriculumScore(String curriculumScore) {
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