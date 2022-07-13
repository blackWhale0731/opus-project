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
import com.my.dto.HolidayRequest;
import com.my.repository.HolidayRequestOracleRepository;
import com.my.repository.HolidayRequestRepository;
import com.my.sql.MyConnection;

@WebServlet("/holidayrequestcancel")
public class HolidayRequestCancelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L; 
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		
		Integer holidayNumber = new Integer(request.getParameter("holidayNumber"));

		PrintWriter out = response.getWriter();
		HolidayRequestRepository repository = new HolidayRequestOracleRepository();
		HolidayRequest holidayrequest; 
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<>();
		String result = mapper.writeValueAsString(map);
		
		Connection con =null;
		PreparedStatement pstmt = null;
		int rs = 0;
		
		//테스트용
		int loginedId = 220707;
		
				try {
					con = MyConnection.getConnection();
					String UpdateSQL = "UPDATE holiday_request SET holiday_request_status = -2 WHERE holiday_number =? AND employee_id = ?";
					pstmt = con.prepareStatement(UpdateSQL);
					pstmt.setInt(1, holidayNumber);
					pstmt.setInt(2, loginedId);
					rs = pstmt.executeUpdate();
					
					
					if(rs == 1) {
				
					map.put("status", 1);
					result = mapper.writeValueAsString(map);
					request.setAttribute("msg", "업데이트 완료");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					map.put("status", 0);
					e1.printStackTrace();
				}finally {
					MyConnection.close(pstmt, con);
			}
				
	}

}
