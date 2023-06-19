package com.gc25.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.gc25.dto.MemberDTO;


public class MemberDAO {
	
	//db연결을 위한 connection 변수 선언
	private Connection con; 
	//sql문 실행을 위한 변수 선언
	private PreparedStatement pstmt;
	// connection pool에서 db 연결정보 조회 위한 변수선언
	private DataSource ds;
	private ResultSet rs;
	
	
	
	public MemberDAO() {
		//DB 연결시에는 꼭 예외처리!
		try {
			//InitialContext -> JNDI namespace의 모든 명명된 객체를 찾을 때 사용하는 객체 생성
			Context ctx = new InitialContext();
			//lookup() 메서드 -> object로 반환하므로 context로 캐스팅
			//java:/comp/env 웹어플의 구성된 엔트리와 리소스들이 배치되어있는 부분
			Context env = (Context)ctx.lookup("java:/comp/env");
			ds = (DataSource)env.lookup("jdbc/oracle");
		}catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	//이메일 중복 확인
	public int emailCheck(String memberEmail) {
		try {
			con = ds.getConnection();
			int result;
			
			//MEMBER table에서 memberEmail로 찾기
			String query = """
					SELECT * FROM GC25_MEMBER WHERE m_email= ? 
				""";
			pstmt = con.prepareStatement(query);
			//콘솔창 쿼리 확인
			System.out.println(query);
			
			pstmt.setString(1, memberEmail);
			System.out.println(memberEmail);
			rs = pstmt.executeQuery();
			
			//같은 이름의 email이 이미 존재 하는 경우나 빈 값인 경우
			if (rs.next() || memberEmail.equals("")) {
				result= 0; 
				
			//같은 이름의 email이 존재하지 않는 경우
			}else {
				result= 1;
			}
			System.out.println(result);
			return result;
			
			
	
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
			}catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return -1;
	}
	
	//닉네임 중복 확인
		public int nicknameCheck(String memberNickname) {
			try {
				con = ds.getConnection();
				int result;
				
				//MEMBER table에서 memberEmail로 찾기
				String query = """
						SELECT * FROM GC25_MEMBER WHERE m_nickname= ? 
					""";
				pstmt = con.prepareStatement(query);
				//콘솔창 쿼리 확인
				System.out.println(query);
				
				pstmt.setString(1, memberNickname);
				System.out.println(memberNickname);
				rs = pstmt.executeQuery();
				
				//같은 이름의 email이 이미 존재 하는 경우나 빈 값인 경우
				if (rs.next() || memberNickname.equals("")) {
					result= 0; 
					
				//같은 이름의 email이 존재하지 않는 경우
				}else {
					result= 1;
				}
				System.out.println(result);
				return result;
				
				
		
			}catch(Exception ex) {
				ex.printStackTrace();
			}finally {
				try {
					if(rs != null) rs.close();
					if(pstmt != null) pstmt.close();
				}catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			return -1;
		}
		
	//이메일 내보내기
	public String getMemberEmail(String memberEmail) {
		
			try {
				con = ds.getConnection();
				
				//MEMBER table에서 memberEmail로 찾기
				String query = """
						SELECT m_email FROM GC25_MEMBER WHERE m_email= ? 
					""";
				pstmt = con.prepareStatement(query);
				//콘솔창 쿼리 확인
				System.out.println(query);
				
				pstmt.setString(1, memberEmail);
				System.out.println(memberEmail);
				rs = pstmt.executeQuery();
				
				//같은 이름의 email 찾아서 반환
				if (rs.next()){
					return rs.getString(1);
				}
		
			}catch(Exception ex) {
				ex.printStackTrace();
			}finally {
				try {
					if(rs != null) rs.close();
					if(pstmt != null) pstmt.close();
				}catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			return null; //오류발생시 null값 반환
		}
	

	
	public String getMemberImageFileName(String memberEmail) {
		try {
			con = ds.getConnection();
			
			//MEMBER table에서 memberEmail로 찾기
			String query = """
					SELECT m_imagefilename FROM GC25_MEMBER WHERE m_email= ? 
				""";
			pstmt = con.prepareStatement(query);
			//콘솔창 쿼리 확인
			System.out.println(query);
			
			pstmt.setString(1, memberEmail);
			System.out.println("dao memberemail 삭제삭제: "+memberEmail);
			rs = pstmt.executeQuery();
			
			//같은 이름의 email 찾아서 반환
			if (rs.next()){
				String memberImageFileName = rs.getString("m_imagefilename");
				if(memberImageFileName.equals("") || memberImageFileName==null) {
					return "profile.jpg";
				}
				else {
					return rs.getString("m_imagefilename");
				}
			}
//			}	return rs.getString("memberImageFileName");
	
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
			}catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return "profile.jpg"; //오류발생시 null값 반환
	}
	
	public String setMemberImageFileName(String memberImageFileName, String memberEmail) {
		try {
			con = ds.getConnection();
			
			if(memberImageFileName==null || memberImageFileName.equals("")) {
				return "profile.jpg";
			}
			
			
			String query = """
				UPDATE GC25_MEMBER SET m_imagefilename = ? WHERE m_email = ?
				""";
			pstmt = con.prepareStatement(query);
			//콘솔창 쿼리 확인
			System.out.println(query);
			
			pstmt.setString(1, memberImageFileName);
			System.out.println(memberImageFileName);
			pstmt.setString(2, memberEmail);
			System.out.println(memberEmail);
			
			int updateCount = pstmt.executeUpdate();

			
			//같은 이름의 email 찾아서 반환
			if (updateCount > 0){
				return memberImageFileName;
			}
	
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
			}catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return "profile.jpg"; //오류발생시 
	}
		
	
	public String delMemberImageFileName(String memberEmail) {
		try {
			con = ds.getConnection();
			
			String query = """
				UPDATE GC25_MEMBER SET m_imagefilename = 'profile.jpg' WHERE m_email = ?
				""";
			pstmt = con.prepareStatement(query);
			//콘솔창 쿼리 확인
			System.out.println(query);
			
			pstmt.setString(1, memberEmail);
			System.out.println("delete email:"+memberEmail);
			
			pstmt.executeUpdate();

			System.out.println("이미지 삭제 완료");
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
			}catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null; //오류발생시 
	}
		
	//회원 정보 저장
	//MemberDTO import
	public int addMember(String memberEmail, String memberPwd,
			String memberNickname, int memberStatus, String memberImageFileName) {
		try {
			//connection 하나 가져오기
			con = ds.getConnection();
			//MEMBER table에 추가
			String query = "INSERT INTO GC25_MEMBER VALUES (seq_GC25_MEMBER.nextval, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(query);
			//m_index는 auto_increment열 -> 데이터베이스가 자동으로 증가시키는 값이므로, 값을 직접 설정할 필요 없음.
			
			pstmt.setString(1, memberEmail);
			pstmt.setString(2, memberPwd);
			pstmt.setString(3, memberNickname);
			pstmt.setInt(4, memberStatus);
			pstmt.setString(5, memberImageFileName);
			
			//준비된 sql문 실행 -> 실행된 sql 갯수 반환 (등록에 실패하면 0이하의 값 반환)
			return pstmt.executeUpdate();
		
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
			}catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return -1;
	}
	
	//로그인
	public int memberLogin(String memberEmail,String memberPwd) {
		try {
			//connection 하나 가져오기
			con = ds.getConnection();
			int result;
			//MEMBER table에 추가
			String query = """
						SELECT m_pwd FROM GC25_MEMBER WHERE m_email = ?
					""";
			pstmt = con.prepareStatement(query);
			System.out.println(query);
			
			pstmt.setString(1, memberEmail);
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				if(rs.getString(1).equals(memberPwd)) {
					result= 1;
				}else {
					result= 0;
				}
				System.out.println("비교결과:"+result);
				return result;
			}
			
			//아이디 없다면?? rs.next 존재 x
			return -1;
			
		}catch(Exception ex) {
			ex.printStackTrace();
		
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				
			}catch (Exception ex) {
				ex.printStackTrace();
				
			}
		}
		return -2;
	}
	
	//사용자 정보 가져오기
		public MemberDTO getMember(String memberEmail) {
				MemberDTO member = new MemberDTO();
			
				try {
					con = ds.getConnection();
					
					//MEMBER table에서 memberEmail로 찾기
					String query = """
							SELECT m_email , m_nickname , m_status, m_imagefilename  FROM GC25_MEMBER WHERE m_email= ? 
						""";
					pstmt = con.prepareStatement(query);
					//콘솔창 쿼리 확인
					System.out.println(query);
					
					pstmt.setString(1, memberEmail);
					System.out.println(memberEmail);
					rs = pstmt.executeQuery();
					
					//같은 이름의 email 찾아서 반환
					if (rs.next()){
						member.setMemberEmail(rs.getString("m_email"));
						member.setMemberNickname(rs.getString("m_nickname"));
						member.setMemberStatus(rs.getInt("m_status"));
						member.setMemberImageFileName(rs.getString("m_imagefilename"));
					
					}
			
				}catch(Exception ex) {
					ex.printStackTrace();
				}finally {
					try {
						if(rs != null) rs.close();
						if(pstmt != null) pstmt.close();
					}catch (Exception ex) {
						ex.printStackTrace();
					}
				}
				System.out.println(member);
				return member; //오류발생시 null값 반환
			}
		
		//회원 정보 수정
		public int updateMember(String memberEmail,String memberPwd,
				String memberNickname, String memberImageFileName) {
			try {
				//connection 하나 가져오기
				con = ds.getConnection();
				//MEMBER table에 추가
				String query = """
						UPDATE GC25_MEMBER SET m_pwd = ? , m_nickname = ? ,m_imagefilename = ? WHERE m_email = ?
						""";
				pstmt = con.prepareStatement(query);
				//m_index는 auto_increment열 -> 데이터베이스가 자동으로 증가시키는 값이므로, 값을 직접 설정할 필요 없음.
				pstmt.setString(1, memberPwd);
				pstmt.setString(2, memberNickname);
				pstmt.setString(3, memberImageFileName);
				pstmt.setString(4, memberEmail);
				
				//준비된 sql문 실행 -> 실행된 sql 갯수 반환 (등록에 실패하면 0이하의 값 반환)
				return pstmt.executeUpdate();
			
			}catch(Exception ex) {
				ex.printStackTrace();
			}finally {
				try {
					if(rs != null) rs.close();
					if(pstmt != null) pstmt.close();
				}catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			return -1;
		}
		
		public int getMemberNumber(String memberEmail) {
			try {
				//connection 하나 가져오기
				con = ds.getConnection();
				//MEMBER table에 추가
				String query = """
						SELECT m_number
						FROM GC25_MEMBER
						WHERE m_email = ?
						""";
				pstmt = con.prepareStatement(query);
				//m_index는 auto_increment열 -> 데이터베이스가 자동으로 증가시키는 값이므로, 값을 직접 설정할 필요 없음.
				pstmt.setString(1, memberEmail);
				rs = pstmt.executeQuery();
				
				if (rs.next()) {
					 int memberNumber = rs.getInt(1);
		            return memberNumber;
		        }
				
			}catch(Exception ex) {
				ex.printStackTrace();
			}finally {
				try {
					if(rs != null) rs.close();
					if(pstmt != null) pstmt.close();
				}catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			
			return 0;
		}
		
		public int withdrawal(int memNum) {
			int result = 0;
			
			try {
				con = ds.getConnection();
				String query = """
							DELETE FROM GC25_MEMBER
								WHERE m_number = ?
						""";
				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, memNum);
				result = pstmt.executeUpdate();
				System.out.println("DAO: "+result);
			} catch (Exception ex) {
				ex.printStackTrace();
			}finally {
				try {
					if(rs != null) rs.close();
					if(pstmt != null) pstmt.close();
				}catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			return result;
		}
		
}