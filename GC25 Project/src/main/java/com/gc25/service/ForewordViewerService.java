package com.gc25.service;

import java.util.ArrayList;

import com.gc25.dao.ForewordBoardDAO;
import com.gc25.dao.ForewordViewerDAO;
import com.gc25.dao.RecommendDAO;
import com.gc25.dto.ForewordBoardDTO;

public class ForewordViewerService {
	ForewordViewerDAO forewordViewrDao;
	RecommendDAO recommendDao;
	
	public ForewordViewerService() {
		forewordViewrDao = new ForewordViewerDAO();
		recommendDao = new RecommendDAO();
	}
	
	//게시글 가져오기
	public ForewordBoardDTO getForewordBoard(int boardNum) {
		return forewordViewrDao.getForewordBoard(boardNum);
	}
	
	//좋아요 수 업데이트 
	public void setRecommend(int memberNum, int boardNum, int aBoard ) {
		
		
		
		recommendDao.fbSetRecommend(memberNum, boardNum, aBoard);
	}
}