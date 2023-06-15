package com.gc25.service;

import java.util.ArrayList;

import com.gc25.dao.AcademyDAO;
import com.gc25.dto.AcademyDTO;

import oracle.sql.json.OracleJsonArray;

public class AcademyService {
	AcademyDAO academyDAO;
	
	public AcademyService() {
		academyDAO = new AcademyDAO(); 
	}
	
	
	public ArrayList<AcademyDTO> listAcademys(int pageNo) {
		ArrayList<AcademyDTO> academyList = academyDAO.selectAllArticles(pageNo);
		return  academyList; 
	}

	public int getTotalPage( ) {
		return academyDAO.getTotalPage();
	}
	
	public ArrayList<AcademyDTO> autoComplete(String keyword) {
		ArrayList<AcademyDTO> academyList = academyDAO.autoComplete(keyword);
		return academyList; 
	}
	
	public int searchJustOne(String academyName) {
		return academyDAO.searchJustOne(academyName);
	}
}
