package com.my.repository;

import java.util.List;

import com.my.dto.Notice;
import com.my.exception.SelectException;

public interface NoticeRepository {

	/**
	 * 공지사항들을 반환한다
	 * @param startRow 한페이지에 반환되는 첫번째 행의 행번호
	 * @param endRow 한페이지에 반환되는 마지막 행의 행번호
	 * @return
	 * @throws SelectException
	 */
	public List<Notice> selectNoticeId(int startRow, int endRow) throws SelectException;
	
	/**
	 * 공지제목에 검색어로 포함한 공지사항들을 반환한다
	 * @param noticeTitle
	 * @return
	 * @throws SelectException 검색어를 포함한 공지사항들이 없으면 "검색어를 포함한 제목의 공지사항이 없습니다"라는 상세메세지를 갖는 예외가 발생한다
	 */
	public List<Notice> selectByNoticeTitle(int startRow, int endRow, String noticeTitle) throws SelectException;
	
	/**
	 * 선택한 공지번호의 공지사항을 반환한다
	 * @param noticeId
	 * @return
	 * @throws SelectException
	 */
	public Notice selectByNoticeId(int noticeId) throws SelectException;
}
