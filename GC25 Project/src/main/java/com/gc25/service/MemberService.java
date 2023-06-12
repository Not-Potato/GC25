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
		
		String result = memberDAO.getMemberEmail(memberEmail);
		
		return result;
	}
	public boolean setMemberEmail(String memberEmail){
		
		boolean result = memberDAO.setMemberEmail(memberEmail);
		
		return result;
	}
	
	public int memberLogin(String memberEmail,String memberPwd) {

		int result = memberDAO.memberLogin(memberEmail, memberPwd);
		
		return result;
	}
	
	public String getMemberImageFileName(String memberImageFileName, String memberEmail) {
		
		String result = memberDAO.getMemberImageFileName(memberImageFileName, memberEmail);
		
		return result;
	}
	
	public String setMemberImageFileName(String memberImageFileName, String memberEmail) {
		
		String result = memberDAO.setMemberImageFileName(memberImageFileName, memberEmail);
		
		return result;
	}
	
	public int updateMember (String memberEmail, String memberPwd, String memberNickname, String memberImageFileName) {
			
		int result = memberDAO.updateMember(memberEmail, memberPwd, memberNickname, memberImageFileName);
		
		return result;
	}
	
}
