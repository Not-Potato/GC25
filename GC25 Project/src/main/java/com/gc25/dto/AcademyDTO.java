package com.gc25.dto;

public class AcademyDTO {
	
	int academyNumber; //학원 고유번호 
	String academyName; //학원 명
	String academyTel; //학원 전화번호
	String academyAddress; //학원 주소
	int academyAvgScore; //학원 만족도
	
	

	@Override
	public String toString() {
		return "(academyNumber: " + this.academyNumber + ", academyName: " + this.academyName + ")";
	}

	//기본생성자
	public AcademyDTO() {
	}
	
	//num과 name만 받는 생성자
	public AcademyDTO(int academyNumber, String academyName) {
		this.academyNumber = academyNumber;
		this.academyName = academyName;
	}
	

	//생성자
	public AcademyDTO(int academyNumber, String academyName, String academyTel, String academyAddress,
			int academyAvgScore) {
		super();
		this.academyNumber = academyNumber;
		this.academyName = academyName;
		this.academyTel = academyTel;
		this.academyAddress = academyAddress;
		this.academyAvgScore = academyAvgScore;
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

	public int getAcademyAvgScore() {
		return academyAvgScore;
	}

	public void setAcademyAvgScore(int academyAvgScore) {
		this.academyAvgScore = academyAvgScore;
	}
	
	
	
	
	
}