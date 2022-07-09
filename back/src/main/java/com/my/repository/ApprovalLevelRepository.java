package com.my.repository;

import java.util.List;

import com.my.dto.ApprovalLevel;
import com.my.exception.SelectException;

public interface ApprovalLevelRepository {
	/**
	 * 결재자를 반환한다
	 * @return
	 * @throws SelectException
	 */
	List<ApprovalLevel> selectApprovalLevel() throws SelectException;
}
