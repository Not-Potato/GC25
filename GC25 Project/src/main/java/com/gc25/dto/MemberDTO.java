package com.gc25.dto;

public class MemberDTO {
	private String memberEmail;
	private String memberEmailHash;
	private String memberEmailChecked;
	private String memberPwd;
	private String memberNickname;
	private String memberStatus;
	private String memberImageFileName;
	
	public MemberDTO() {
		
	}

	public MemberDTO(String memberEmail, String memberEmailHash, String memberEmailChecked, String memberPwd,
			String memberNickname, String memberStatus, String memberImageFileName) {
		
		this.memberEmail = memberEmail;
		this.memberEmailHash = memberEmailHash;
		this.memberEmailChecked = memberEmailChecked;
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

	public String getMemberEmailHash() {
		return memberEmailHash;
	}

	public void setMemberEmailHash(String memberEmailHash) {
		this.memberEmailHash = memberEmailHash;
	}

	public String getMemberEmailChecked() {
		return memberEmailChecked;
	}

	public void setMemberEmailChecked(String memberEmailChecked) {
		this.memberEmailChecked = memberEmailChecked;
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

	public String getMemberStatus() {
		return memberStatus;
	}

	public void setMemberStatus(String memberStatus) {
		this.memberStatus = memberStatus;
	}

	public String getMemberImageFileName() {
		return memberImageFileName;
	}

	public void setMemberImageFileName(String memberImageFileName) {
		this.memberImageFileName = memberImageFileName;
	}
	
	
	
	
}



