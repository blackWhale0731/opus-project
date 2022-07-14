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
import com.my.dto.HolidayInfo;
import com.my.exception.SelectException;
import com.my.repository.HolidayInfoOracleRepository;
import com.my.repository.HolidayInfoRepository;

/**
 * Servlet implementation class HolidayReturnServlet
 * 로그인한 사원 휴가 일수 반환
 * SELECT holiday_total, holiday_using FROM holiday_info WHERE employee_id = ?
 */
@WebServlet("/holidayreturn")
public class HolidayReturnServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// 로그인 상태 확인
		//HttpSession session = request.getSession();
		//Integer loginedId = (Integer)session.getAttribute("loginInfo"); //integer타입
		Integer loginedId = new Integer(request.getParameter("loginInfo"));
		//Integer loginedId = 220702;
		response.setContentType("application/json;charset=UTF-8"); // 응답할 문자열 형식
		PrintWriter out = response.getWriter();  // 외부로부터 입력된 값 out에 입력?
		HolidayInfoRepository repository = new HolidayInfoOracleRepository();    // holidayinfo~를 이용할 repository선언
		
		//Integer holidayinfo; //info에 있는 모든 사번을 입력할 변수
		HolidayInfo holidayinfo;
		ObjectMapper mapper = new ObjectMapper();  // json형식으로 문자 출력하기 위한 mapper변수 선언
		Map<String, Object> map = new HashMap<>();  // 출력값 입력받기 위한 map 선언?
		try {
			System.out.println(repository);
			holidayinfo = repository.selectHolidayInfoByEmployeeId(loginedId);  // info의 모든 사번 입력
//			out.print(holidayinfo.getHolidayTotal(), holidayinfo.getHolidayUsing());
//			for( int i=0; i<holidayinfo.size(); i++ ) {  // 사번의 수만큼 반복해서 지금 로그인된 사번과 비교
//				if(loginedId == holidayinfo.get(i).getEmployeeId()) { // 로그인 사번과 동일한 사번의 직원이 가진 총 휴일과 남은 휴일을 가져온다
//					map.put("status", 1);  // 정상동작했다는 표시
//					out.print(holiday_total, holiday_using);
//				}
//			}
			map.put("status", 1);
			map.put("holidayInfo", holidayinfo);
//			map.put("getHolidayTotal", holidayinfo.getHolidayTotal());
//			map.put("getHolidayUsing", holidayinfo.getHolidayUsing());
		} catch (SelectException e) {
			e.printStackTrace();
			map.put("status", 0);  // 정상동작 하지 못했을때 표시
		}
		String result = mapper.writeValueAsString(map);
		System.out.println(result);
		out.print(result);
	}
	
}


