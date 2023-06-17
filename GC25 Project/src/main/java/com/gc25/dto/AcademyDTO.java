package com.gc25.dto;

public class AcademyDTO {
	
	int academyNumber; //학원 고유번호 
	String academyName; //학원 명
	String academyTel; //학원 전화번호
	String academyAddress; //학원 주소
	String academyRodeAddress; //학원 도로주소
	double academyAvgScore; //학원 만족도
	String academyUrl; 
	String academyX; //위치 경도 
	String academyY; //위치 위도
	int academyReviewCount; //리뷰 수
	

	@Override
	public String toString() {
		return "AcademyDTO [academyNumber=" + academyNumber + ", academyName=" + academyName + ", academyTel="
				+ academyTel + ", academyAddress=" + academyAddress + ", academyRodeAddress=" + academyRodeAddress
				+ ", academyAvgScore=" + academyAvgScore + ", academyUrl=" + academyUrl + ", academyX=" + academyX
				+ ", academyY=" + academyY + ", academyReviewCount=" + academyReviewCount + "]";
	}

	public AcademyDTO() {
		super();
	}
	
	public AcademyDTO(String academyName, int academyAvgScore) {
		this.academyName = academyName;
		this.academyAvgScore = academyAvgScore;
	}


	public AcademyDTO(int academyNumber, String academyName, String academyTel, String academyAddress,
			String academyRodeAddress, double academyAvgScore, String academyUrl, String academyX, String academyY , int academyReviewCount) {
		super();
		this.academyNumber = academyNumber;
		this.academyName = academyName;
		this.academyTel = academyTel;
		this.academyAddress = academyAddress;
		this.academyRodeAddress = academyRodeAddress;
		this.academyAvgScore = academyAvgScore;
		this.academyUrl = academyUrl;
		this.academyX = academyX;
		this.academyY = academyY;
		this.academyReviewCount = academyReviewCount;
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


	public String getAcademyTel() {
		return academyTel;
	}


	public void setAcademyTel(String academyTel) {
		this.academyTel = academyTel;
	}


	public String getAcademyAddress() {
		return academyAddress;
	}


	public void setAcademyAddress(String academyAddress) {
		this.academyAddress = academyAddress;
	}


	public String getAcademyRodeAddress() {
		return academyRodeAddress;
	}


	public void setAcademyRodeAddress(String academyRodeAddress) {
		this.academyRodeAddress = academyRodeAddress;
	}


	public double getAcademyAvgScore() {
		return academyAvgScore;
	}


	public void setAcademyAvgScore(double academyAvgScore) {
		this.academyAvgScore = academyAvgScore;
	}


	public String getAcademyUrl() {
		return academyUrl;
	}


	public void setAcademyUrl(String academyUrl) {
		this.academyUrl = academyUrl;
	}


	public String getAcademyX() {
		return academyX;
	}


	public void setAcademyX(String academyX) {
		this.academyX = academyX;
	}


	public String getAcademyY() {
		return academyY;
	}


	public void setAcademyY(String academyY) {
		this.academyY = academyY;
	}

	public int getAcademyReviewCount() {
		return academyReviewCount;
	}

	public void setAcademyReviewCount(int academyReviewCount) {
		this.academyReviewCount = academyReviewCount;
	}
	

}