package com.gc25.memberemailutil;

import java.security.MessageDigest;

public class SHA256 {
//일반적으로 이메일 인증시 기존 존재하는 이메일의 해쉬값을 적용해서 사용자가 인증코드로 인증하도록
	
	//특정한 값을 넣었을 때 sha256으로 그 값의 해쉬값 구하는
	public static String getSHA256(String input) {
		//stringBuffer는 문자열을 추가하거나 변경 할 때 주로 사용하는 자료형
		
	        if (input == null) {
	            return "???"; // or throw an exception, depending on your requirements
	        }
		
		
		StringBuffer result = new StringBuffer();
		try {
			//메시지 다이제스트(Message Digest)란, 메시지를 해시(Hash)하는 것을 의미한다. 
			//임의의 길이를 가진 메시지를 MD함수에 넣으면 일정한 길이를 가진 데이터를 얻는다. 
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			//salt는 데이터, 비번, 통과암호를 해쉬처리하는 랜덤데이터?? 비번보호위해 사용
			byte[]salt = "Hello! This is salt.".getBytes();
			digest.reset();
			digest.update(salt);
			byte[]chars=digest.digest(input.getBytes("UTF-8"));
			System.out.println(input.getBytes("UTF-8"));
			for(int i = 0; i<chars.length; i++) {
				String hex = Integer.toHexString(0xff & chars[i]);
				if(hex.length()==1)result.append("0");
				result.append(hex);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return result.toString();
	}

}