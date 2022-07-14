package com.my.repository;

import java.util.List;

import com.my.dto.HolidayRequest;
import com.my.exception.InsertException;
import com.my.exception.SelectException;
import com.my.exception.UpdateException;

public interface HolidayRequestRepository {

	/**
	 * 휴가요청정보를 추가한다
	 * @param holidayrequest 휴가신청정보
	 * @throws
	 */
	public void insertHolidayRequest(HolidayRequest holidayrequest) throws InsertException;
	
	/**
	 * 휴가신청번호로 해당 휴가신청정보를 반환한다
	 * @param holidayNumber 휴가신청번호
	 * @return 휴가신청정보
	 * @throws SelectException
	 */
	public HolidayRequest  selectHolidayRequestByHolidayNumber(int holidayNumber) throws SelectException;
	
	/**
	 * 휴가신청정보들을 반환한다 
	 * @param startRow 한페이지에 반환되는 첫번째 행의 행번호
	 * @param endRow 한페이지에 반환되는 마지막 행의 행번호
	 * @param employeeId 로그인한사번
	 * @return
	 * @throws SelectException
	 */
	public List<HolidayRequest> selectHolidayByEmployeeId(int startRow,int endRow, int employeeId) throws SelectException;

	HolidayRequest selectHolidayRequestByHolidayNumber(int holidayNumber, int employeeId)
			throws UpdateException, SelectException;

	/**
	 * 휴가요청을 요청취소하면 승인상태를 업데이트한다
	 * @param holidayNumber 휴가신청번호
	 * @throws UpdateException
	 */
	public void update(int holidayNumber, int employeeId) throws UpdateException;


}
