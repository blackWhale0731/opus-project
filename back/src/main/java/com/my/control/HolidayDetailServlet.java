package com.my.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.dto.HolidayRequest;
import com.my.exception.SelectException;
import com.my.repository.HolidayRequestOracleRepository;
import com.my.repository.HolidayRequestRepository;
import com.my.sql.MyConnection;


@WebServlet("/holidaydetail")
public class HolidayDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public HolidayDetailServlet() {
        super();
        
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8"); // MIME 형식 설정
		PrintWriter out = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<>();
		
		String strHoliday_number = request.getParameter("holidayNumber");
		int holiday_number = Integer.parseInt(strHoliday_number);
		
//		int logindeId
		HolidayRequestRepository repository = new HolidayRequestOracleRepository();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		//String result = "{\"status:\":0}";
		
		//새션(클라이언트별 객체) 얻기
//		HttpSession session = request.getSession();
//		session.removeAttribute("");
		
		try {
			HolidayRequest hr = repository.selectHolidayRequestByHolidayNumber(holiday_number);
			
			map.put("status", 1);
			map.put("holidayRequest", hr);
		} catch (SelectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			MyConnection.close(rs, pstmt, con);
		}
		response.setContentType("application/json;charset=UTF-8");
		out.print(mapper.writeValueAsString(map));
				
	}
}



