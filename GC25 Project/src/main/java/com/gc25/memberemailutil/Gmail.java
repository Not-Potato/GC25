package com.gc25.memberemailutil;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class Gmail extends Authenticator{

	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		//return super.getPasswordAuthentication(); -> 부모 메서드 호출
		return new PasswordAuthentication("bko2369@gmail.com","giaahazmprkvsbux");
	}
	
}