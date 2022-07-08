package com.my.repository;

import com.my.dto.Employee;
import com.my.exception.SelectException;

public interface EmployeeRepository {

	/**
	 * 입력한 사번과 비밀번호와 일치하는 사원을 반환
	 * @param employeeId 입력한 사번, employeePassword 입력한 비밀번호
	 * @throws SelectException 해당하는 사원이 없으면 "로그인에 실패하였습니다"상세메세지를 갖는 예외가 발생한다
	 */
	public Employee selectByIdAndPwd(int employeeId, String employeePassword) throws SelectException;
	
}
