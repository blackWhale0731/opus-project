package com.my.repository;

import com.my.dto.Employee;
import com.my.exception.SelectException;

public interface EmployeeRepository {

	
	/**
	 * 로그인한 사원의 마이페이지 정보를 반환한다
	 * @param employeeId
	 * @throws SelectException
	 */
	public Employee selectMyPage(int employeeId) throws SelectException;
	
}
