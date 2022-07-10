package com.my.repository;

import java.util.Date;
import java.util.List;

import com.my.dto.Calendar;
import com.my.exception.InsertException;
import com.my.exception.SelectException;

public interface CalendarRepository {

	/**
	 * 달력정보를 추가한다
	 * @param calendar 달력정보
	 * @throws InsertException 
	 */
	public void insert(Calendar calendar) throws InsertException;
	
	/**
	 * 로그인한 사원의 달력정보를 반환한다
	 * @param Month 월
	 * @param employeeId 로그인한 사원의 사번
	 * @param MonthButton 월 넘김 버튼 클릭 수 
	 * @return
	 * @throws SelectException
	 */
	public List<Calendar> selectCalByEmployeeId(Date Month, int employeeId, int MonthButton) throws SelectException;
	
	/**
	 * 로그인한 사원과 같은 부서 사원들의 공개체크된 달력정보를 반환한다
	 * @return
	 * @throws SelectException
	 */
	public List<Calendar> selectCalByDepartmentId(Date Month, int employeeId,int MonthButton) throws SelectException;
}
