package com.gc25.dto;

public class RecommendDTO {
	// 추천 고유 번호
	int recommendNumber;
	// (추천 누른 회원의) 회원 번호
	String memberNumber;
	// 상담 후기 게시판 글 번호
	int fBoardNumber;
	// 수강 후기 게시판 글 번호
	int aBoardNumber;
	
	public RecommendDTO() {
		
	}
	
	public RecommendDTO(int recommendNumber, String memberNumber, int fBoardNumber, int aBoardNumber) {
		this.recommendNumber = recommendNumber;
		this.memberNumber = memberNumber;
		this.fBoardNumber = fBoardNumber;
		this.aBoardNumber = aBoardNumber;
	}
	
	public int getRecommendNumber() {
		return recommendNumber;
	}
	
	public void setRecommendNumber(int recommendNumber) {
		this.recommendNumber = recommendNumber;
	}
	public String getMemberNumber() {
		return memberNumber;
	}
	public void setMemberNumber(String memberNumber) {
		this.memberNumber = memberNumber;
	}
	public int getfBoardNumber() {
		return fBoardNumber;
	}
	public void setfBoardNumber(int fBoardNumber) {
		this.fBoardNumber = fBoardNumber;
	}
	public int getaBoardNumber() {
		return aBoardNumber;
	}
	public void setaBoardNumber(int aBoardNumber) {
		this.aBoardNumber = aBoardNumber;
	}

}
