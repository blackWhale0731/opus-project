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
import com.my.exception.UpdateException;
import com.my.repository.HolidayInfoOracleRepository;
import com.my.repository.HolidayInfoRepository;

/**
 * Servlet implementation class HolidayUpdateServlet
 * 로그인한 사원 휴가 일수 변경
 */
@WebServlet("/holidayupdate")
public class HolidayUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 로그인 상태 확인
		//HttpSession session = request.getSession();
		//Integer loginedId = (Integer)session.getAttribute("loginInfo");
		
		Integer loginedId = new Integer(request.getParameter("loginInfo")); //integer타입
		Integer holidayNum = new Integer(request.getParameter("holidayNum"));

		response.setContentType("application/json;charset=UTF-8"); // 응답할 문자열 형식
		PrintWriter out = response.getWriter();  // 외부로부터 입력된 값 out에 입력?
		HolidayInfoRepository repository = new HolidayInfoOracleRepository();    // holidayinfo~를 이용할 repository선언
		//Integer holidayinfo; //info에 있는 모든 사번을 입력할 변수
		HolidayInfo holidayinfo;
		ObjectMapper mapper = new ObjectMapper();  // json형식으로 문자 출력하기 위한 mapper변수 선언
		Map<String, Object> map = new HashMap<>();  // 출력값 입력받기 위한 map 선언?
		try {
			holidayinfo = repository.updateHolidayInfoByEmployeeId(loginedId, holidayNum);  // info의 모든 사번 입력

			//holidayinfo.setHolidayUsing(holidayinfo.getHolidayUsing()-holidayNum);// 입력된 값 만큼 남은 휴가일수에서 제외
			map.put("status", 1);
			map.put("holidayInfo", holidayinfo);
		} catch (SelectException | UpdateException e) {
			e.printStackTrace();
			map.put("status", 0);  // 정상동작 하지 못했을때 표시
		}
		out.print(mapper.writeValueAsString(map));
	}

}
