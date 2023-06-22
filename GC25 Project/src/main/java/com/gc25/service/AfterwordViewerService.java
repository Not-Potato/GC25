package com.gc25.service;

import com.gc25.dao.AfterwordBoardDAO;
import com.gc25.dao.AfterwordViewerDAO;
import com.gc25.dao.RecommendDAO;
import com.gc25.dto.AfterwordBoardDTO;
import com.gc25.dto.ForewordBoardDTO;

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
	
	//게시글 수정
	public void modifyAfterwordBoard(AfterwordBoardDTO afterwordBoardDTO) {
		afterwordViewrDao.modifyAfterwordBoard (afterwordBoardDTO);
	}
	
	//좋아요 수 업데이트 
	public void setRecommend(int memberNum, int boardNum, int bBoard ) {
		recommendDao.setRecommend(memberNum, boardNum, bBoard);
	}
	
	// 게시글 삭제
	public void deleteAfterwordBoard(int boardNum, String academyName) {
	afterwordViewrDao.deleteAfterwordBoard (boardNum, academyName);
	}
	
	// 좋아요 여부 확인
	public int getRecommend(int memberNum, int boardNum, String where) {
		return recommendDao.getRecommend(memberNum, boardNum, where);
	}
}