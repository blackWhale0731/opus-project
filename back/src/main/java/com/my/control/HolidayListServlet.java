package com.my.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.dto.HolidayRequest;
import com.my.exception.SelectException;
import com.my.repository.HolidayRequestOracleRepository;
import com.my.repository.HolidayRequestRepository;


@WebServlet("/holidaylist")
public class HolidayListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
//		int holidayNumber = Integer.parseInt (request.getParameter("holidayNumber"));
//		Integer.parseInt(employeeId);
	
		int employeeId = Integer.parseInt(request.getParameter("employeeId"));
		
		int cntPerPage = 10; //1페이지당 보여줄 목록수
		String strCurrentPage = request.getParameter("currentPage");
		
		int currentPage = 1;
		if(strCurrentPage != null && !strCurrentPage.equals("")) {
			currentPage = Integer.parseInt(strCurrentPage);//1, 2
		}
		
		int endRow =  currentPage * cntPerPage;//10, 20
		int startRow = endRow - cntPerPage + 1; // 10-10+1, 20-10+1
		
		//String startRow = request.getParameter("startRow");
		//String endRow = request.getParameter("endRow");
		
		HolidayRequestRepository repository = new HolidayRequestOracleRepository();
		
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<>();
		try {
			List<HolidayRequest> holidayrequests = repository.selectHolidayByEmployeeId(startRow, endRow, employeeId);
			map.put("status", 1);
			map.put("holidayrequests", holidayrequests);
		} catch (SelectException e) {
			e.printStackTrace();
			map.put("status", 0);
		}
		String result = mapper.writeValueAsString(map);
		System.out.println(map);
		out.print(result);
		
	 }

}
