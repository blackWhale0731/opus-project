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
import com.my.dto.ApprovalLevel;
import com.my.dto.Employee;
import com.my.dto.HolidayRequest;
import com.my.dto.HolidayType;
import com.my.exception.InsertException;
import com.my.repository.HolidayRequestOracleRepository;
import com.my.repository.HolidayRequestRepository;

@WebServlet("/holidayrequestinsert")
public class HolidayRequestInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public HolidayRequestInsertServlet() {
        super();
        
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap();
		
		HolidayRequestRepository repository = new HolidayRequestOracleRepository();
		HolidayRequest holidayRequests = new HolidayRequest();
		
//		int holidayNumber = Integer.parseInt(request.getParameter("holiday_number"));
		
		Employee emp = new Employee();
//		HttpSession session = request.getSession();
//		int loginedId = (Integer)session.getAttribute("loginInfo");
//		emp.setEmployeeId(loginedId);
		//테스트용
		emp.setEmployeeId(220705);
		holidayRequests.setEmployee(emp);
		
//		int employeeId = Integer.parseInt(request.getParameter("employee_id"));
		int holidayType = Integer.parseInt(request.getParameter("holiday_type"));
		HolidayType ht = new HolidayType();
		ht.setHolidayType(holidayType);
		holidayRequests.setHolidayType(ht);
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		try {
			Date holidayStart = formatter.parse(request.getParameter("holiday_start"));
			holidayRequests.setHolidayStart(holidayStart);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			Date holidayEnd = formatter.parse(request.getParameter("holiday_end"));
			holidayRequests.setHolidayEnd(holidayEnd);
		} catch (ParseException pe) {
			pe.printStackTrace();
		}
		
		int holidayApprovalFirst = Integer.parseInt(request.getParameter("holiday_approval_first"));
		ApprovalLevel alFirst = new ApprovalLevel();
		Employee e1 = new Employee();
		e1.setEmployeeId(holidayApprovalFirst);
		alFirst.setEmployee(e1);	
		holidayRequests.setHolidayApprovalFirst(alFirst);
		
		int holidayApprovalSecond = Integer.parseInt(request.getParameter("holiday_approval_second"));		
		ApprovalLevel alSecond = new ApprovalLevel();		
//		alSecond.setEmployeeId(holidayApprovalSecond);
		Employee e2 = new Employee();
		e2.setEmployeeId(holidayApprovalSecond);
		alSecond.setEmployee(e2);
		holidayRequests.setHolidayApprovalSecond(alSecond);
		
		int holidayApprovalThird = Integer.parseInt(request.getParameter("holiday_approval_third"));
		
		ApprovalLevel alThird = new ApprovalLevel();
//		alThird.setEmployeeId(holidayApprovalThird);
		Employee e3 = new Employee();
		e3.setEmployeeId(holidayApprovalThird);
		alThird.setEmployee(e3);
		holidayRequests.setHolidayApprovalThird(alThird);
		
		String holidayDesc = request.getParameter("holiday_desc");
		int holidayApprovalStatus = Integer.parseInt(request.getParameter("holiday_approval_status"));

			try {
				repository.insertHolidayRequest(holidayRequests);
				map.put("status", 1);
			} catch (InsertException ie) {
				map.put("status", 0);
				map.put("msg", ie.getMessage());
				ie.printStackTrace();
			}
			out.print(mapper.writeValueAsString(map));
	}

}
