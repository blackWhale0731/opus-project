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
import com.my.dto.ApprovalLevel;
import com.my.exception.SelectException;
import com.my.repository.ApprovalLevelOracleRepository;
import com.my.repository.ApprovalLevelRepository;
/**
 * Servlet implementation class ApprovalLevelServlet
 * 결재자 반환
 */
@WebServlet("/approvallevel")
public class ApprovalLevelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		ApprovalLevelRepository repository = new ApprovalLevelOracleRepository();
		List<ApprovalLevel> approvalLevels;
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<>();
		try {
			approvalLevels = repository.selectApprovalLevel();
			map.put("status", 1);
			map.put("approvallevels", approvalLevels);
		} catch (SelectException e) {
			e.printStackTrace();
			map.put("status", 0);
		}
		
		String result = mapper.writeValueAsString(map);
		System.out.println(result);
		out.print(result);

	}

}