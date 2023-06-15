package com.gc25.dto;

public class MemberDTO {
	private String memberEmail;
	private String memberPwd;
	private String memberNickname;
	private int memberStatus;
	private String memberImageFileName;
	private String memberNumber;
	
	public MemberDTO() {
		
	}

	public MemberDTO(String memberEmail, String memberPwd,
			String memberNickname, int memberStatus, String memberImageFileName) {
		
		this.memberEmail = memberEmail;
		this.memberPwd = memberPwd;
		this.memberNickname = memberNickname;
		this.memberStatus = memberStatus;
		this.memberImageFileName = memberImageFileName;
		
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public String getMemberPwd() {
		return memberPwd;
	}

	public void setMemberPwd(String memberPwd) {
		this.memberPwd = memberPwd;
	}

	public String getMemberNickname() {
		return memberNickname;
	}

	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}

	public int getMemberStatus() {
		
		return memberStatus;
	}

	public void setMemberStatus(int memberStatus) {
		this.memberStatus = memberStatus;
	}

	public String getMemberImageFileName() {
		String memberImageFileName = "/profile.jpg";
		return memberImageFileName;
	}

	public void setMemberImageFileName(String memberImageFileName) {
		this.memberImageFileName = memberImageFileName;
	}

	public String getMemberNumber() {
		return memberNumber;
	}

	public void setMemberNumber(String memberNumber) {
		this.memberNumber = memberNumber;
	}
	
	
	
	
}



