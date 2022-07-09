package com.my.repository;

import com.my.dto.Employee;
import com.my.exception.SelectException;
import com.my.exception.UpdateException;

public interface EmployeeRepository {

	/**
	 * 입력한 사번과 비밀번호와 일치하는 사원을 반환한다
	 * @param employeeId 사번
	 * @param employeePassword 비밀번호
	 * @throws SelectException 해당하는 사원이 없으면 "사번 혹은 비밀번호가 일치하지 않습니다"상세메세지를 갖는 예외가 발생한다
	 */
	public Employee selectByIdAndPwd(int employeeId, String employeePassword) throws SelectException;
	
	
	/**
	 * 로그인한 사원의 비밀번호를 업데이트한다
	 * @param employeeId 사번 
	 * @pemployeePassword 비밀번호
	 * @throws UpdateException 비밀번호 형식이 맞지 않으면 "비밀번호는 최소  6자리 이상이어야합니다"상세메세지를 갖는 예외가 발생한다
	 */
	public void update(int employeeId, String employeePassword) throws UpdateException;
	
	/**
	 * 로그인한 사원의 마이페이지 정보를 반환한다
	 * @param employeeId
	 * @throws SelectException
	 */
	public Employee selectMyPage(int employeeId) throws SelectException;
	
}
