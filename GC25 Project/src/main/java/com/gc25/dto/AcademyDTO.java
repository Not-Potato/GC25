package com.gc25.dto;

public class AcademyDTO {
	
	int a_index; 
	String a_name;
	int a_tel; 
	String a_address;
	int a_postal;
	int a_satisfaction; 

	
	
	public AcademyDTO() {
	}

	public AcademyDTO(String a_name, int a_tel, String a_address, int a_postal, int a_satisfaction) {
		this.a_name = a_name;
		this.a_tel = a_tel;
		this.a_address = a_address;
		this.a_postal = a_postal;
		this.a_satisfaction = a_satisfaction;
	}

	
	
	public int getA_index() {
		return a_index;
	}

	public void setA_index(int a_index) {
		this.a_index = a_index;
	}

	public String getA_name() {
		return a_name;
	}

	public void setA_name(String a_name) {
		this.a_name = a_name;
	}

	public int getA_tel() {
		return a_tel;
	}

	public void setA_tel(int a_tel) {
		this.a_tel = a_tel;
	}

	public String getA_address() {
		return a_address;
	}

	public void setA_address(String a_address) {
		this.a_address = a_address;
	}

	public int getA_postal() {
		return a_postal;
	}

	public void setA_postal(int a_postal) {
		this.a_postal = a_postal;
	}

	public int getA_satisfaction() {
		return a_satisfaction;
	}

	public void setA_satisfaction(int a_satisfaction) {
		this.a_satisfaction = a_satisfaction;
	}
	
	
}
