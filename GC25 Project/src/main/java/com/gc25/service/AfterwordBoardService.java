package com.gc25.service;

import java.util.ArrayList;

import com.gc25.dao.AfterwordBoardDAO;
import com.gc25.dto.AfterwordBoardDTO;

public class AfterwordBoardService {
	AfterwordBoardDAO dao;
	
	public AfterwordBoardService() {
		dao = new AfterwordBoardDAO();
	}
	
	// "정렬 기준, 페이지 넘버"로 목록 조회하기 --> ArrayList 반환
	public ArrayList<AfterwordBoardDTO> getList(String searchType, int pageNo) {
		ArrayList<AfterwordBoardDTO> list = dao.getList(searchType, pageNo);

		return list;
	}
	
	public int getTotalPage() {
		return dao.getTotalPage();
	}
	
}