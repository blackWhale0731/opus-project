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
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.dto.Calendar;
import com.my.exception.SelectException;
import com.my.repository.CalendarOracleRepository;
import com.my.repository.CalendarRepository;

@WebServlet("/viewcalendar")
public class ViewCalendarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		//로그인된 ID얻기
//		int loginedId = new Integer(request.getParameter("loginInfo"));
		int loginedId = 220702;
		
		//요청전달데이터 calYM이 2022-07
		String calYM = request.getParameter("calYM");
		
		CalendarRepository repository = new CalendarOracleRepository();
		String []revealedArr = request.getParameterValues("revealed");
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<>();
		try {
			List<Calendar> list = null;
			if(revealedArr != null) {
				//.equals("employee"))
				if(revealedArr.length == 2) { //개인, 부서 둘다선택된 경우
					
					list = repository.selectCalBoth(calYM,loginedId);
				}else {
					switch(revealedArr[0]) {
					case "employee":
						list = repository.selectCalMyExcludeDept(calYM, loginedId);
						
						break;
					case "department":
						list = repository.selectCalDeptExcludeMy(calYM, loginedId);
						break;
					}
				}
				map.put("status", 1);
				map.put("calendars", list);
			}
		}catch(SelectException e) {
			map.put("status", 0);
			map.put("msg", e.getMessage());
		}
		String result = mapper.writeValueAsString(map);
		System.out.println(map);
		out.print(result);
	}

}
