package com.my.repository;

import com.my.dto.HolidayInfo;
import com.my.exception.SelectException;
import com.my.exception.UpdateException;

public interface HolidayInfoRepository {
	/**
	 * 
	 * 로그인한 사원의 휴가 일수를 반환한다
	 * @return
	 * @throws SelectException
	 */
	
	public HolidayInfo selectHolidayInfoByEmployeeId(int employeeId) throws SelectException;
	
	/** 
	 * 로그인한 사원 휴가일수를 변경한다
	 * @throws UpdateException
	 */
	
	public void update(int employeeId) throws UpdateException;

}
