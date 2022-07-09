package com.my.repository;

import java.util.List;

import com.my.dto.CalType;
import com.my.exception.SelectException;

public interface CaltypeRepository {
	/**
	 * 유형드롭다운하면 유형리스트 반환
	 * @return
	 * @throws SelectException
	 */
	public List<CalType> selectCaltype() throws SelectException;
}
