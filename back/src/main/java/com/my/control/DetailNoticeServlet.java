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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.dto.Notice;
import com.my.exception.SelectException;
import com.my.repository.NoticeOracleRepository;
import com.my.repository.NoticeRepository;

/**
 * Servlet implementation class DetailNoticeServlet
 */
@WebServlet("/detailnotice")
public class DetailNoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 공지사항 상세보기
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String result = "";
		
				
				// DB
				NoticeRepository repository = new NoticeOracleRepository();
				try {
				Notice n = repository.selectByNoticeId(1);
				Map<String, Object> map = new HashMap<>();
				map.put("status", 1);
				map.put("n", n);
				
				ObjectMapper mapper = new ObjectMapper();
				
				result = mapper.writeValueAsString(map);
				System.out.println("result : " + result);
				out.print(result);
				
				} catch (SelectException e) {
					e.printStackTrace();
					Map<String, Object> map = new HashMap<>();
					map.put("status", 0);
					map.put("msg", e.getMessage());
					
					ObjectMapper mapper = new ObjectMapper();
					result = mapper.writeValueAsString(map);
					System.out.println("result : " + result);
					out.print(result);
				}	
				
			}
		
	}

