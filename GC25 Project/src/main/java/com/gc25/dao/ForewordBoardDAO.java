package com.gc25.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.gc25.dto.ForewordBoardDTO;

public class ForewordBoardDAO {
	private Connection con; // db 연결을 위한 connection 변수
	private PreparedStatement pstmt; // SQL문 실행을 위한 변수
	private DataSource ds; // connection pool에서 db 연결 정보 조회

	public ForewordBoardDAO() {
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
	public ArrayList<ForewordBoardDTO> getList(String searchType, int pageNumber) {
		ArrayList<ForewordBoardDTO> list = new ArrayList<>();
		String query = "";
		int offset = (pageNumber - 1) * 10;
		// 1페이지 --> 0
		// 2페이지 --> 10
		// 3페이지 --> 20
		
		try {
			con = ds.getConnection();
			if (searchType.equals("최신순")) {
				// 글 번호, 글 제목, 글 내용, 작성시간, 좋아요, 조회수, 댓글수
				query = """
					SELECT fb_number, fb_title, fb_contents, a_name, fb_date, fb_recommend, fb_views, fb_commentcount 
					FROM (SELECT * FROM GC25_FOREWORD_BOARD ORDER BY fb_date DESC) 
					OFFSET ? ROWS FETCH NEXT 10 ROWS ONLY
				""";
			} else if (searchType.equals("추천순")) {
				query = """
					SELECT fb_number, fb_title, fb_contents, a_name, fb_date, fb_recommend, fb_views, fb_commentcount 
					FROM (SELECT * FROM GC25_FOREWORD_BOARD ORDER BY fb_recommend DESC) 
					OFFSET ? ROWS FETCH NEXT 10 ROWS ONLY
				""";
			} else if (searchType.equals("댓글순")) {
				query = """
					SELECT fb_number, fb_title, fb_contents, a_name, fb_date, fb_recommend, fb_views, fb_commentcount 
					FROM (SELECT * FROM GC25_FOREWORD_BOARD ORDER BY fb_commentcount DESC) 
					OFFSET ? ROWS FETCH NEXT 10 ROWS ONLY
				""";
			} else if (searchType.equals("조회순")) {
				query = """
					SELECT fb_number, fb_title, fb_contents, a_name, fb_date, fb_recommend, fb_views, fb_commentcount 
					FROM (SELECT * FROM GC25_FOREWORD_BOARD ORDER BY fb_views DESC) 
					OFFSET ? ROWS FETCH NEXT 10 ROWS ONLY
				""";
			}
			
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, offset);
			ResultSet rs = pstmt.executeQuery();
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// fb_number, fb_title, fb_contents, fb_date, fb_recommned, fb_views, fb_commentcount
				int boardNumber = rs.getInt("fb_number");
				Timestamp writeDate = rs.getTimestamp("fb_date");
//				Date aBoardDate = rs.getDate("fb_date");
				String title = rs.getString("fb_title");
				String contents = rs.getString("fb_contents");
				String academyName = rs.getString("a_name");
				int recommend = rs.getInt("fb_recommend");
				int views = rs.getInt("fb_views");
				int commentCount = rs.getInt("fb_commentcount");
				ForewordBoardDTO f = new ForewordBoardDTO();
				f.setBoardNumber(boardNumber);
				f.setWriteDate(writeDate);
				f.setTitle(title);
				f.setContents(contents);
				f.setAcademyName(academyName);
				f.setRecommend(recommend);
				f.setViews(views);
				f.setCommentCount(commentCount);

				list.add(f);
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	// 전체 페이지 수 가져오기
	public int getTotalPage() {
		int totalPage = 0;
		
		try {
			con = ds.getConnection();

			String query = "SELECT COUNT(DISTINCT fb_number) AS total_count FROM GC25_FOREWORD_BOARD";
			System.out.println(query);
			
			pstmt = con.prepareStatement(query);
			
			ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			
			double totalCount = rs.getDouble(1);
		    totalPage = (int) Math.ceil(totalCount / 10.0);
			
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return totalPage;
	}
	
	// 글 업로드
	public int upload(ForewordBoardDTO dto) {
		int result = 0;
		String query = "";
				
		try {
			// 글 업로드 시작
			con = ds.getConnection();
			// 고유번호, 1 "회원번호", 2 "학원번호", 3 "학원이름", 4 "과정구분", 작성일자, 5 "제목", 6 "내용", 추천수, 조회수, 댓글수
			query = """
						INSERT INTO GC25_FOREWORD_BOARD 
						VALUES(seq_GC25_FOREWORD_BOARD.nextval, ?, ?, ?, ?, SYSDATE, ?, ?, 0, 0, 0)
					""";
			System.out.println(query);
			pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, dto.getMemberNumber());
			pstmt.setInt(2, dto.getAcademyNumber());
			pstmt.setString(3, dto.getAcademyName());
			pstmt.setString(4, dto.getCourse());
			pstmt.setString(5, dto.getTitle());
			pstmt.setString(6, dto.getContents());
			
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