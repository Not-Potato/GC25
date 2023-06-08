package com.gc25.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.gc25.dto.AfterwordBoardDTO;

public class AfterwordBoardDAO {
	private Connection con; // db 연결을 위한 connection 변수
	private PreparedStatement pstmt; // SQL문 실행을 위한 변수
	private DataSource ds; // connection pool에서 db 연결 정보 조회

	public AfterwordBoardDAO() {
		try {
			Context ctx = new InitialContext(); // 톰캣에 저장되어 있는 context 정보 조회를 위한 설정
			Context env = (Context)ctx.lookup("java:/comp/env"); // context에 저장되어 있는 환경(설정) 정보 조회용
			ds = (DataSource) env.lookup("jdbc/oracle"); // connection pool 정보 조회
		} catch (Exception ex) {
			ex.printStackTrace(); // console 창에 로그(메시지) 출력
		}
	}
	
	// 목록 반환 메소드
	// (정렬 구분, 페이지 번호)
	public ArrayList<AfterwordBoardDTO> getList(String searchType, int pageNumber) {
		ArrayList<AfterwordBoardDTO> list = null;
		String query = "";
		
		try {
			con = ds.getConnection();
			if (searchType.equals("최신순")) {
				query = "SELECT ab_number, m_number, a_number, a_name, ab_course, ab_date, ab_teacher, ab_totalscore, ab_open, ab_end, ab_major, ab_cost, ab_teacherscore, ab_facilityscore, ab_curriculumscore, ab_title, ab_contents, ab_recommned, ab_views FROM (SELECT * FROM GC25_AFTERWORD_BOARD ORDER BY ab_date DESC) WHERE ROWNUM >= ? AND ROWNUM <= ?";
			} else if (searchType.equals("추천순")) {
				query = "SELECT * FROM (SELECT * FROM GC25_AFTERWORD_BOARD ORDER BY ab_recommned DESC) WHERE ROWNUM >= ? AND ROWNUM <= ?";
			}
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, Integer.toString(pageNumber));
			pstmt.setString(2, Integer.toString(pageNumber * 10));
			ResultSet rs = pstmt.executeQuery();
			
			System.out.println("파라미터 추가한 sql! " + query);
			rs = pstmt.executeQuery();
			System.out.println("받아온 값! " + rs);
			
			while (rs.next()) {
				System.out.println("bbb");
				int aBoardNumber = rs.getInt("ab_number");
				int memberNumber = rs.getInt("m_number");
				int academyNumber = rs.getInt("a_number");
				String academyName = rs.getString("a_name");
				Date aBoardDate = rs.getDate("ab_date");
				String aBoardTeacher = rs.getString("ab_teacher");
				int aBoardTotalScore = rs.getInt("ab_totalscore");
				Date aBoardOpen = rs.getDate("ab_open");
				Date aBoardEnd = rs.getDate("ab_end");
				int aBoardMajor = rs.getInt("ab_major");
				int aBoardCost = rs.getInt("ab_cost");
				int aBoardTeacherScore = rs.getInt("ab_teacherscore");
				String aBoardFacilityScore = rs.getString("ab_facilityscore");
				String aBoardCurriculumScore = rs.getString("ab_curriculumscore");
				String aBoardTitle = rs.getString("ab_title");
				String aBoardContents = rs.getString("ab_contents");
				int aBoardRecommned = rs.getInt("ab_recommned");
				int aBoardViews = rs.getInt("ab_views");

				AfterwordBoardDTO a = (new AfterwordBoardDTO(aBoardNumber, memberNumber, academyNumber, academyName,
						aBoardDate, aBoardTeacher, aBoardTotalScore, aBoardOpen, aBoardEnd, aBoardMajor, aBoardCost,
						aBoardTeacherScore, aBoardFacilityScore, aBoardCurriculumScore, aBoardTitle, aBoardContents,
						aBoardRecommned, aBoardViews));

				System.out.println(a);
			}
			
			System.out.println("반복문 종료지점");

			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	public List<AfterwordBoardDTO> listAfterwordBoard() {
		List<AfterwordBoardDTO> list = new ArrayList<>();

		try {
			con = ds.getConnection();
			String query = "SELECT * FROM GC25_AFTERWORD_BOARD";
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();


			while (rs.next()) {
				System.out.println("bbb");
				int aBoardNumber = rs.getInt("ab_number");
				int memberNumber = rs.getInt("m_number");
				int academyNumber = rs.getInt("a_number");
				String academyName = rs.getString("a_name");
				Date aBoardDate = rs.getDate("ab_date");
				String aBoardTeacher = rs.getString("ab_teacher");
				int aBoardTotalScore = rs.getInt("ab_totalscore");
				Date aBoardOpen = rs.getDate("ab_open");
				Date aBoardEnd = rs.getDate("ab_end");
				int aBoardMajor = rs.getInt("ab_major");
				int aBoardCost = rs.getInt("ab_cost");
				int aBoardTeacherScore = rs.getInt("ab_teacherscore");
				String aBoardFacilityScore = rs.getString("ab_facilityscore");
				String aBoardCurriculumScore = rs.getString("ab_curriculumscore");
				String aBoardTitle = rs.getString("ab_title");
				String aBoardContents = rs.getString("ab_contents");
				int aBoardRecommned = rs.getInt("ab_recommned");
				int aBoardViews = rs.getInt("ab_views");

				AfterwordBoardDTO a = (new AfterwordBoardDTO(aBoardNumber, memberNumber, academyNumber, academyName,
						aBoardDate, aBoardTeacher, aBoardTotalScore, aBoardOpen, aBoardEnd, aBoardMajor, aBoardCost,
						aBoardTeacherScore, aBoardFacilityScore, aBoardCurriculumScore, aBoardTitle, aBoardContents,
						aBoardRecommned, aBoardViews));

				System.out.println(a);
			}

			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return list;
	}
}