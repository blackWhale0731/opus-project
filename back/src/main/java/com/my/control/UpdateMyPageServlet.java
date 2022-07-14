package com.my.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.exception.UpdateException;
import com.my.repository.EmployeeOracleRepository;
import com.my.repository.EmployeeRepository;
import com.my.sql.MyConnection;

@WebServlet("/updatemypage")
public class UpdateMyPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 인코딩
		response.setContentType("application/json;charset=UTF-8");
		
		// 세션에서 로그인 아이디 가져오기
		int loginedId = new Integer(request.getParameter("loginInfo"));
		
		// 테스트용
//		int loginedId = 220702;
		
		
		// 변경할 패스워드 값 가져오기
		String newPassword = request.getParameter("newPassword");

		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<>();
		String result = mapper.writeValueAsString(map);

		PrintWriter out = response.getWriter();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		int rs = 0;
		
		try {
			con = MyConnection.getConnection();
			String updateSQL = "UPDATE employee SET employee_password = ? WHERE employee_id = ?";
			pstmt = con.prepareStatement(updateSQL);
			pstmt.setString(1, newPassword);
			pstmt.setInt(2, loginedId);
			rs = pstmt.executeUpdate();

			if (rs == 1) {
				map.put("status", 1);
				map.put("msg", "비밀번호 변경 완료");
				result = mapper.writeValueAsString(map);
				request.setAttribute("msg", "비밀번호 변경을 완료했습니다");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MyConnection.close(pstmt, con);
		}
		out.print(result);
	}

}
