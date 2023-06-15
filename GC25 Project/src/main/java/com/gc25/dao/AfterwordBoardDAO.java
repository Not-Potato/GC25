package com.gc25.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

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
		ArrayList<AfterwordBoardDTO> list = new ArrayList<>();
		String query = "";
		int offset = (pageNumber - 1) * 10;
		// 1페이지 --> 0
		// 2페이지 --> 10
		// 3페이지 --> 20
		
		try {
			con = ds.getConnection();
			if (searchType.equals("최신순")) {
				// 글 제목, 글 내용, 작성시간, 좋아요, 조회수, 댓글수
				query = """
					SELECT ab_number, ab_title, ab_contents, a_name, ab_date, ab_recommend, ab_views, ab_commentcount 
					FROM (SELECT * FROM GC25_AFTERWORD_BOARD ORDER BY ab_date DESC) 
					OFFSET ? ROWS FETCH NEXT 10 ROWS ONLY
				""";
			} else if (searchType.equals("추천순")) {
				// 글 제목, 글 내용, 작성시간, 좋아요, 조회수, 댓글수
				query = """
					SELECT ab_number, ab_title, ab_contents, a_name, ab_date, ab_recommend, ab_views, ab_commentcount
					FROM (SELECT * FROM GC25_AFTERWORD_BOARD ORDER BY ab_recommend DESC) 
						OFFSET ? ROWS FETCH NEXT 10 ROWS ONLY
				""";
			} else if (searchType.equals("댓글순")) {
				// 글 제목, 글 내용, 작성시간, 좋아요, 조회수, 댓글수
				query = """
					SELECT ab_number, ab_title, ab_contents, a_name, ab_date, ab_recommend, ab_views, ab_commentcount
					FROM (SELECT * FROM GC25_AFTERWORD_BOARD ORDER BY ab_commentcount DESC) 
						OFFSET ? ROWS FETCH NEXT 10 ROWS ONLY
				""";
			} else if (searchType.equals("조회순")) {
				// 글 제목, 글 내용, 작성시간, 좋아요, 조회수, 댓글수
				query = """
					SELECT ab_number, ab_title, ab_contents, a_name, ab_date, ab_recommend, ab_views, ab_commentcount
					FROM (SELECT * 
						FROM GC25_AFTERWORD_BOARD 
						ORDER BY ab_views DESC) 
						OFFSET ? ROWS FETCH NEXT 10 ROWS ONLY
				""";
			}
			
			System.out.println(query);
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, offset);
			ResultSet rs = pstmt.executeQuery();
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// 필요한 것만 가져와서 담기
				// ab_number, ab_title, ab_contents, ab_date, ab_recommend, ab_views, ab_commentcount
				int boardNumber = rs.getInt("ab_number");
				Timestamp writeDate = rs.getTimestamp("ab_date");
//				Date aBoardDate = rs.getDate("ab_date");
				String title = rs.getString("ab_title");
				String contents = rs.getString("ab_contents");
				String academyName = rs.getString("a_name");
				int recommend = rs.getInt("ab_recommend");
				int views = rs.getInt("ab_views");
				int commentCount = rs.getInt("ab_commentcount");
				AfterwordBoardDTO a = new AfterwordBoardDTO();
				a.setBoardNumber(boardNumber);
				a.setWriteDate(writeDate);
				a.setTitle(title);
				a.setContents(contents);
				a.setAcademyName(academyName);
				a.setRecommend(recommend);
				a.setViews(views);
				a.setCommentCount(commentCount);

				list.add(a);
			}
			
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	public int getTotalPage() {
		int totalPage = 0;
		
		try {
			con = ds.getConnection();

//			String query = "SELECT CEIL(count(*) / 10) total_page FROM GC25_AFTERWORD_BOARD";
			String query = "SELECT COUNT(DISTINCT ab_number) AS total_count FROM GC25_AFTERWORD_BOARD";
			System.out.println(query);
			
			pstmt = con.prepareStatement(query);
			
			ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			
			double totalCount = rs.getDouble(1);
		    totalPage = (int) Math.ceil(totalCount / 10.0);
			
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalPage;
	}
	
	// 글 업로드
	public int upload(AfterwordBoardDTO dto) {
		int result = 0;
		String query = "";
				
		try {
			// 글 업로드 시작
			con = ds.getConnection();
			// 고유번호, 1 "회원번호", 2 "학원번호", 3 "학원이름", 4 "과정구분", 작성일자, 5 "강사명", 6 "개강일", 7 "종강일", 
			// 8 "전공/비전공", 9 "유/무상", 10 "전체 만족도", 11 "강사 만족도", 12 "시설 만족도", 13 "커리큘럼 만족도" 14 "제목", 15 "내용",
			// 추천수, 조회수, 댓글수
			query = """
						INSERT INTO GC25_AFTERWORD_BOARD (
							ab_number, m_number, a_number, a_name, ab_course, ab_date, 
							ab_teacher, ab_open, ab_end, ab_major, ab_cost, ab_totalscore, ab_teacherscore,
							ab_facilityscore, ab_curriculumscore, ab_title, ab_contents, ab_recommend, ab_views,
							ab_commentcount
						) VALUES (
							seq_GC25_AFTERWORD_BOARD.nextval, ?, ?, ?, ?, SYSDATE, ?, ?, ?, ?, ?, ?, ?, ?, ?, 
							?, ?, 0, 0, 0
						)
					""";
			System.out.println(query);
			pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, dto.getMemberNumber());
			pstmt.setInt(2, dto.getAcademyNumber());
			pstmt.setString(3, dto.getAcademyName());
			pstmt.setString(4, dto.getCourse());
			pstmt.setString(5, dto.getTeacherName());
			pstmt.setString(6, dto.getOpenDate());
			pstmt.setString(7, dto.getEndDate());
			pstmt.setString(8, dto.getMajor());
			pstmt.setString(9, dto.getCost());
			pstmt.setInt(10, dto.getTotalScore());
			pstmt.setInt(11, dto.getTeacherScore());
			pstmt.setInt(12, dto.getFacilityScore());
			pstmt.setInt(13, dto.getCurriculumScore());
			pstmt.setString(14, dto.getTitle());
			pstmt.setString(15, dto.getContents());
			
			result = pstmt.executeUpdate();
			
			if (result == 1) {
				System.out.println("업로드 성공!");
			}
			
			// 글 업로드 종료
			// 회원 등급 확인 시작
			query = """
						UPDATE GC25_MEMBER
						SET m_status = 1
						WHERE m_number = ? AND m_status = 0
					""";
			System.out.println(query);
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, dto.getMemberNumber());
			pstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}
}