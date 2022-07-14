package com.my.repository;

import java.util.Date;
import java.util.List;

import com.my.dto.CalType;
import com.my.dto.Calendar;
import com.my.dto.Employee;
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
	 * @return
	 * @throws SelectException
	 */
	public List<Calendar> selectCalMyExcludeDept(String ym, int employeeId) throws SelectException;
	
	/**
	 * 로그인한 사원을 포함한 같은 부서 사원들의 공개체크된 달력정보를 반환한다
	 * @param Month 년월 yyyy-MM
	 * @param employeeId 로그인한 사원의 사번
	 * @return
	 * @throws SelectException
	 */
	public List<Calendar> selectCalBoth(String ym, int employeeId) throws SelectException;
	/**
	 * 로그인한 사원은 제외한 같은 부서 사원들의 공개체크된 달력정보를 반환한다
	 * @param Month
	 * @param employeeId
	 * @return
	 * @throws SelectException
	 */
	public List<Calendar> selectCalDeptExcludeMy(String ym, int employeeId) throws SelectException;
}
