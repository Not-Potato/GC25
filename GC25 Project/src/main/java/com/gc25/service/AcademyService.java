package com.gc25.service;
import java.util.ArrayList;

import com.gc25.dao.AcademyDAO;
import com.gc25.dto.AcademyDTO;

public class AcademyService {
	AcademyDAO academyDAO;
	
	public AcademyService() {
		academyDAO = new AcademyDAO(); 
	}
	
	
	public ArrayList<AcademyDTO> listAcademys(int pageNo, String searchValue) {
		ArrayList<AcademyDTO> academyList = academyDAO.selectArticle(pageNo, searchValue);
		return  academyList; 
	}

	public int getTotalPage(String searchValue) {
		return academyDAO.getTotalPage(searchValue );
	}

}

