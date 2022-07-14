package com.my.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.dto.Employee;
import com.my.exception.SelectException;
import com.my.repository.EmployeeOracleRepository;
import com.my.repository.EmployeeRepository;

@WebServlet("/viewmypage")
public class ViewMyPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		String result = "";

		
		// 1. 요청전달 데이터
		Integer loginedId = new Integer(request.getParameter("loginInfo"));
		// 테스트
//		int loginedId = 220702;

		// 2.DB에서 직원정보 검색
		EmployeeRepository repository = new EmployeeOracleRepository();

		try {
			Employee em = repository.selectMyPage(loginedId);

			Map<String, Object> map = new HashMap<>();
			map.put("status", 1);
			map.put("Employee", em);

			result = mapper.writeValueAsString(map);
			System.out.println("result : " + result);
			out.print(result);

		} catch (SelectException e) {
			e.printStackTrace();
			Map<String, Object> map = new HashMap<>();
			map.put("status", 0);
//				map.put("status", "SelectException");

			result = mapper.writeValueAsString(map);
			System.out.println("result : " + result);
			out.print(result);
		}
	}
}
