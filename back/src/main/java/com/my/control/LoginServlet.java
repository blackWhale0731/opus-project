package com.my.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.sql.MyConnection;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8"); 
		PrintWriter out = response.getWriter();	//응답출력스트림 얻기
		
		//요청전달데이터 얻기
		int employeeId = Integer.parseInt(request.getParameter("employeeId"));//int??
		String employeePassword = request.getParameter("employeePassword");
		
		//DB와 연결
		Connection con = null;
		
		//SQL송신
		PreparedStatement pstmt = null;
		
		//송신결과
		ResultSet rs = null;
		
		//응답결과
		String result = "{\"status:\":0}";
		
		//세션(클라이언트별 객체) 얻기
		HttpSession session = request.getSession();
		session.removeAttribute("loginInfo");
		try {
			con = MyConnection.getConnection();
			String selectByIdAndPwd = "SELECT * FROM employee WHERE employee_id= ? AND employee_password= ?";
			pstmt = con.prepareStatement(selectByIdAndPwd);
			pstmt.setInt(1, employeeId); //int?
			pstmt.setString(2, employeePassword);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {//로그인 성공인 경우
				result = "{\"status\":1}";
				session.setAttribute("loginInfo", employeeId);
//				out.print("session.isNew()=" +session.isNew());
//				out.print("session.getId()=" +session.getId());
//				int loginedId = (Integer)session.getAttribute("loginInfo");
				
			}else {
				ObjectMapper mapper = new ObjectMapper();
				Map <String, Object> map= new LinkedHashMap<>();
				map.put("msg", "사번 혹은 비밀번호가 일치하지 않습니다");
				
					
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//DB와의 연결 닫기
			MyConnection.close(rs, pstmt, con);
		}
			

		out.print(result);
		
	}
	
}
