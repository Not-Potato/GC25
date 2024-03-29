package com.gc25.service;

import java.util.ArrayList;
import com.gc25.dao.ForewordBoardDAO;
import com.gc25.dto.ForewordBoardDTO;

public class ForewordBoardService {
	ForewordBoardDAO dao;
	
	public ForewordBoardService() {
		dao = new ForewordBoardDAO();
	}
	
	// "정렬 기준, 페이지 넘버"로 목록 조회하기 --> ArrayList 반환
	public ArrayList<ForewordBoardDTO> getList(String searchType, int pageNo) {
		ArrayList<ForewordBoardDTO> list = dao.getList(searchType, pageNo);
		return list;
	}
	
	public int getTotalPage() {
		return dao.getTotalPage();
	}
	
	public int upload(ForewordBoardDTO dto) {
		return dao.upload(dto);
	}
	
}
