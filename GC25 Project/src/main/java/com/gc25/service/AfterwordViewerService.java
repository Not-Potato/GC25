package com.gc25.service;

import com.gc25.dao.AfterwordBoardDAO;
import com.gc25.dao.AfterwordViewerDAO;
import com.gc25.dao.RecommendDAO;
import com.gc25.dto.AfterwordBoardDTO;

public class AfterwordViewerService {
	AfterwordViewerDAO afterwordViewrDao;
	RecommendDAO recommendDao;
	
	public AfterwordViewerService () {
		afterwordViewrDao = new AfterwordViewerDAO();
		recommendDao = new RecommendDAO();
	}
	
	//게시글 가져오기
	public AfterwordBoardDTO getAfterwordBoard(int boardNum) {
		return afterwordViewrDao.getAfterwordBoard(boardNum);
	}
	
	//좋아요 수 업데이트 
	public void setRecommend(int memberNum, int boardNum, int bBoard ) {
		recommendDao.setRecommend(memberNum, boardNum, bBoard);
	}
}