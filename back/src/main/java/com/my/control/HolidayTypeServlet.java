package com.my.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.dto.HolidayType;
import com.my.exception.SelectException;
import com.my.repository.HolidayTypeOracleRepository;
import com.my.repository.HolidayTypeRepository;

/**
 * Servlet implementation class HolidayTypeServlet
 * 휴가 유형 반환
 */
@WebServlet("/holidaytype")
public class HolidayTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		HolidayTypeRepository repository = new HolidayTypeOracleRepository();
		List<HolidayType> holidayTypes;
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<>();
		try {
			holidayTypes = repository.selectHolidayType();
			map.put("status", 1);
			map.put("holidayTypes", holidayTypes);
		} catch (SelectException e) {
			e.printStackTrace();
//			holidayTypes = new ArrayList<>();
			map.put("status", 0);
		}
		
		
		String result = mapper.writeValueAsString(map);
		System.out.println(result);
//		String result = "[";
//		for( int i=0; i<holidayTypes.size(); i++ ) {
//			if(i>0) {
//				result += ",";
//			}
//			HolidayType holidaytype = holidayTypes.get(i);
			
//			result += "{";
//			result += "\"holiday_type\":"; result+="\""+ holidaytype.getHolidayType() +"\""; result += ",";
//			result += "\"holiday_type_name\":"; result+="\""+ holidaytype.getHolidayTypeName() +"\""; result += ",";
//			result += "}";
//		}
		
//		result += "]";
		out.print(result);
	}

}
