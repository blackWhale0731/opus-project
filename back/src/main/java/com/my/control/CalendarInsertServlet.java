package com.my.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.dto.CalType;
import com.my.dto.Calendar;
import com.my.dto.Employee;
import com.my.exception.InsertException;
import com.my.repository.CalendarOracleRepository;
import com.my.repository.CalendarRepository;

@WebServlet("/calendar")
public class CalendarInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap();

		CalendarRepository repository = new CalendarOracleRepository();
		
		Calendar calendar = new Calendar();
		
//		int calNo= Integer.parseInt(request.getParameter("cal_no"));
//		int employeeId = Integer.parseInt(request.getParameter("employee_id"));
//		HttpSession session = request.getSession();
		
//		Integer loginedId = (Integer)session.getAttribute("loginedId");
//		Employee e = new Employee();
//		e.setEmployeeId(loginedId.intValue());
		
		Employee e = new Employee();
		e.setEmployeeId(220712);
		
		calendar.setEmployee(e);
		
		int calType = Integer.parseInt(request.getParameter("cal_type"));
		CalType ct = new CalType();
		ct.setCalType(calType);
		calendar.setCalType(ct);
		
		int calRevealed= Integer.parseInt(request.getParameter("cal_revealed"));
		calendar.setCalRevealed(calRevealed);
		
		String calCnt = request.getParameter("cal_cnt");
		calendar.setCalCnt(calCnt);
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date calStart = formatter.parse(request.getParameter("cal_start"));
			calendar.setCalStart(calStart);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			Date calEnd = formatter.parse(request.getParameter("cal_end"));
			calendar.setCalEnd(calEnd);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		try {
			repository.insert(calendar);
			map.put("status", 1);
			
		} catch (InsertException ex) {
			map.put("status", -1);
			map.put("msg", ex.getMessage());
			ex.printStackTrace();
		}
		
		out.print(mapper.writeValueAsString(map));
	
	}

}