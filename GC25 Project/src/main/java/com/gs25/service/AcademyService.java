package com.gs25.service;
import java.util.ArrayList;

import com.gc25.dao.AcademyDAO;
import com.gc25.dto.AcademyDTO;

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

}

