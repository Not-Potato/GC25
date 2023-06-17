package com.gc25.service;

import java.util.ArrayList;

import com.gc25.dao.AcademyDAO;
import com.gc25.dao.AfterwordBoardDAO;
import com.gc25.dao.ForewordBoardDAO;
import com.gc25.dto.AcademyDTO;
import com.gc25.dto.AfterwordBoardDTO;
import com.gc25.dto.ForewordBoardDTO;
	

public class IndexService {
		
		ForewordBoardDAO fdao;
		AfterwordBoardDAO adao;
		AcademyDAO acdao = new AcademyDAO();
		
	public ArrayList<ForewordBoardDTO> fgetList(String searchType, int pageNo) {
		ArrayList<ForewordBoardDTO> alist = fdao.getList(searchType, pageNo);
		
		return alist;
	}

	public ArrayList<AfterwordBoardDTO> agetList(String searchType, int pageNo) {
		ArrayList<AfterwordBoardDTO> flist = adao.getList(searchType, pageNo);
		
		return flist;
	}
	
	public ArrayList<AcademyDTO> getAvg () {
		ArrayList<AcademyDTO> academyAvg =acdao.getAvg();
		return academyAvg;
	}
	
	public ArrayList<AcademyDTO> getRev () {
		ArrayList<AcademyDTO> academyRev =acdao.getRev();
		return academyRev;
	}

	//public ArrayList<AcademyDTO>

}
