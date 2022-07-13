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
import com.my.dto.Notice;
import com.my.exception.SelectException;
import com.my.repository.NoticeOracleRepository;
import com.my.repository.NoticeRepository;

/**
 * Servlet implementation class MainNoticeServlet
 * 검색된 제목과 같은 공지사항 가져오기
 */
@WebServlet("/searchnotice")
public class SearchNoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		//1. 요청 전달데이터 얻기
//		String start_row = request.getParameter("start_row");
//		String end_row = request.getParameter("end_row");
		
		//2. DB
		NoticeRepository repository = new NoticeOracleRepository();
		List<Notice> notices;
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<>();
		try {
			notices = repository.selectByNoticeTitle(1, 10, "건강검진");
			map.put("status", 1);
			map.put("notices", notices);
		} catch (SelectException e) {
			e.printStackTrace();
//				notices = new ArrayList<>();
			map.put("status", 0);
			
		}
		
		String result = mapper.writeValueAsString(map);
		System.out.println(result);
//		String result = "[";
//		for(int i=0; i<notices.size(); i++) {
//			if(i > 0) {
//			           result +=",";
//			}
//			Notice n = notices.get(i);
//			Employee em = new Employee();
//			result += "{";
//			result += "\"notice_id\":"; result += "\"" + n.getNoticeId()  + "\""; result +=",";
//			result += "\"employee_id\":"; result += "\"" + em.getEmployeeId()  + "\""; result +=",";	
////			result += "\"notice_cnt\":"; result += "\"" + n.getNoticeCnt()  + "\""; result +=",";	
//			result += "\"notice_title \":"; result += "\"" + n.getNoticeTitle()  + "\""; result +=",";	
//			result += "\"notice_time\":"; result += n.getNoticeTime(); 
//			result += "}";
//		}
//		result += "]";
		out.print(result);
	}
	
	
	
	}
