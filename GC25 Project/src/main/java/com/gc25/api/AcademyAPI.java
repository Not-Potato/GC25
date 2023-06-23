package com.gc25.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


@WebServlet("/AcademyAPI")

	public class AcademyAPI extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private DataSource ds; // connection pool에서 db 연결 정보 조회
	
	public AcademyAPI() {
	super();
	}

    // 컨텍스트 초기화 매개변수에서 Connection Pool 설정
    @Override
    public void init() throws ServletException {
        super.init();
        try {
	        Context ctx = new InitialContext();
	        Context env = (Context) ctx.lookup("java:/comp/env");	
	        ds = (DataSource) env.lookup("jdbc/oracle");
        }catch (Exception ex) {
        	ex.printStackTrace();
        }
        
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	

//학원 리스트 > 
// DB 중복 저장 막기 위해 총 페이지 숫자를 범위로 넣어주세요! 
//학원명과 총 페이지 수 한 세트씩 주석을 풀어가면서 시간 차를 두고 run on server해주세요.
	 
//	String academyName = "이젠아카데미캠퍼스";
//	int totalPage = 3; 
	
//	String academyName = "그린컴퓨터아트";
//	int totalPage = 3; 
	
//	String academyName = "메가스터디컴퓨터";
//	int totalPage = 2; 
	
//	String academyName = "하이미디어컴퓨터학원";
//	int totalPage = 3; 
	
//	String academyName = "더조은아카데미";
//	int totalPage = 2; 
	
//	String academyName = "에듀윌국비";
//	int totalPage = 1; 
	
//	String academyName = "비트교육센터";
//	int totalPage = 1; 
	
//외 검색결과가 안나오지?ㅠㅠ
	String academyName = "코리아IT";
	int totalPage = 1; 
	
	
	
	//영어 검색 시 사용
	//IT 1~3
		//String apiUrl = "https://dapi.kakao.com/v2/local/search/keyword.json?page=3&size=15&sort=accuracy&query=IT&category_group_code=AC5";
	
	//한글 검색 시 사용
		// 필요한 문자열 URL인코딩
		
for (int i = 1; i <= totalPage; i++) {
		String keyword = academyName; //검색어
		String encodedKeyword = URLEncoder.encode(keyword, "UTF-8");
		String apiUrl = "https://dapi.kakao.com/v2/local/search/keyword.json?page="+i+"&size=15&sort=accuracy&query="+encodedKeyword+"&category_group_code=AC5";
		
	
	//공통 사용 부분
	String apiKey = "3c83577caf87dbd45512e540187b2595";
	
	
	
	URL url = new URL(apiUrl);
	
	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	
	conn.setRequestMethod("GET");
	
	conn.setRequestProperty("Authorization", "KakaoAK " + apiKey);
	
	
	int responseCode = conn.getResponseCode();
	
	if (responseCode == HttpURLConnection.HTTP_OK) {
	
	BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	
	String inputLine;
	
	StringBuffer content = new StringBuffer();
	
	
	while ((inputLine = in.readLine()) != null) {
	
	content.append(inputLine);
	
	}
	
	in.close();
	
	
	response.setContentType("application/json; charset=UTF-8");
	
	response.setCharacterEncoding("UTF-8");
	
	response.getWriter().write(content.toString());
	
	
	
	try {
	
	// JSON형식에서 내가 원하는 정보 가져오는 코드	
	 JSONParser parser = new JSONParser();	
	 JSONObject jsonObject;	
	 jsonObject = (JSONObject) parser.parse(content.toString());
	 JSONArray documents = (JSONArray) jsonObject.get("documents");	
	 for (Object obj : documents) {	
	 JSONObject document = (JSONObject) obj;	
		
	 // 필요한 부분 가져오기	
	 
	 //학원 명
	 String place_name = (String) document.get("place_name");
	 //학원 도로명주소
	 String road_address_name = (String) document.get("road_address_name");
	 // 학원 주소
	 String address_name = (String) document.get("address_name");
	 // 학원 전화번호
	 String phone = (String) document.get("phone"); 
	 // 학원 홈페이지
	 String place_url = (String) document.get("place_url");	
	 //위도
	 String x = (String) document.get("x");
	//경도
	 String y = (String) document.get("y");
	 
	 // Connection Pool에서 Connection 가져오기
     try (Connection con = ds.getConnection()) {
         // PreparedStatement를 사용하여 쿼리 실행
         String insertQuery = "INSERT INTO GC25_ACADEMY (a_number , a_name, a_tel, a_address, a_roadAddress, a_url, a_x, a_y) VALUES (seq_GC25_ACADEMY.nextval, ?, ?, ?, ?, ?, ?, ?)";
         try (PreparedStatement statement = con.prepareStatement(insertQuery)) {
             statement.setString(1, place_name);
             statement.setString(2, phone);
             statement.setString(3, address_name);
             statement.setString(4, road_address_name);
             statement.setString(5, place_url);
             statement.setString(6, x);
             statement.setString(7, y);

             statement.executeUpdate();
             
         }
         
     } catch (Exception e) {
         e.printStackTrace();
     }
 }
	 
	 
//	   String filePath = "C://academydatabase/data"+i+".json";
//       JsonFileWriter.writeJsonToFile(jsonObject, filePath);
	
	} catch (ParseException e) {
	
	// TODO Auto-generated catch block
	
	e.printStackTrace();
	
	}
	
	} else {
	
	// 요청이 실패한 경우 처리할 로직
	
	System.out.println("API 요청 실패: " + responseCode);
	
	}
} //end of for
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	doGet(request, response);
	
	}

}
