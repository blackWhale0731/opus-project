package com.my.repository;

import java.util.List;

import com.my.dto.HolidayType;
import com.my.exception.SelectException;

public interface HolidayTypeRepository {
	/**
	 * 휴가유형 반환
	 * @return
	 * @throws SelectException
	 */
	public List<HolidayType> selectHolidayType() throws SelectException;
}
