package com.gc25.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class Test
 */
@WebServlet("/test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DataSource ds; // connection pool에서 db 연결 정보 조회
    
	public Test() {
        super();
    }
	
    @Override
    public void init() throws ServletException {
        try {
            Context ctx = new InitialContext(); // 톰캣에 저장되어 있는 context 정보 조회를 위한 설정
            Context env = (Context) ctx.lookup("java:/comp/env"); // context에 저장되어 있는 환경(설정) 정보 조회용
            ds = (DataSource) env.lookup("jdbc/oracle"); // connection pool 정보 조회
        } catch (Exception ex) {
            ex.printStackTrace(); // console 창에 로그(메시지) 출력
        }
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection connection = null;
        try {
            connection = ds.getConnection();
            System.out.println("데이터베이스 연결 성공!");

            // 여기에서 필요한 추가 작업 수행 가능

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 연결 종료
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

		//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
