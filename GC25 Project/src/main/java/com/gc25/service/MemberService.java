package com.gc25.service;

import com.gc25.dao.MemberDAO;
import com.gc25.dto.MemberDTO;

public class MemberService {
	
	private MemberDAO memberDAO;
	
	public MemberService() {
		memberDAO  = new MemberDAO();
	
	}
	
	public int emailCheck(String memberEmail) {
		
		int result = memberDAO.emailCheck(memberEmail);
		
		return result;
	}
	
	public int nicknameCheck(String memberNickname) {
		
		int result = memberDAO.nicknameCheck(memberNickname);
		
		return result;
	}
	
	public String getMemberEmail(String memberEmail) {
		
		memberEmail = memberDAO.getMemberEmail(memberEmail);
		
		return memberEmail;
	}
	
	public int memberLogin(String memberEmail,String memberPwd) {

		int result = memberDAO.memberLogin(memberEmail, memberPwd);
		
		return result;
	}
	
	public String getMemberImageFileName(String memberImageFileName, String memberEmail) {
		
		String result = memberDAO.getMemberImageFileName(memberEmail);
		
		return result;
	}
	
	public String setMemberImageFileName(String memberImageFileName, String memberEmail) {
		
		String result = memberDAO.setMemberImageFileName(memberImageFileName, memberEmail);
		
		return result;
	}
	
	public int addMember (MemberDTO memberDTO) {
		
		String memberEmail = memberDTO.getMemberEmail();
	    String memberPwd = memberDTO.getMemberPwd();
	    String memberNickname = memberDTO.getMemberNickname();
	    int memberStatus = memberDTO.getMemberStatus();
	    String memberImageFileName = memberDTO.getMemberImageFileName();

	    int result = memberDAO.addMember(memberEmail, memberPwd, memberNickname, memberStatus, memberImageFileName);
		
		return result;
	}
	
	public int updateMember (String memberEmail, String memberPwd, String memberNickname, String memberImageFileName) {
			
		int result = memberDAO.updateMember(memberEmail, memberPwd, memberNickname, memberImageFileName);
		
		return result;
	}
	
	public int getMemberNumber(String memberEmail) {
		
		int result=memberDAO.getMemberNumber(memberEmail);
		
		return result;
	}
	
	public MemberDTO getMember (String memberEmail) {
		
		MemberDTO memberDTO = memberDAO.getMember(memberEmail);
		
		return memberDTO;
	}
	
}
