package com.my.dto;

import java.util.Date;

public class Notice {
	private int noticeId;
	private String noticeCnt;
	private String noticeTitle;
	private Date noticeTime;	//java.util.date 권장
	private Employee employee;
	
		
	public Notice(int noticeId, int employeeId, String noticeCnt, 
			String noticeTitle, Date noticeTime, Employee employee) {
		super();
		this.noticeId = noticeId;
		this.employee = employee;
		this.noticeCnt = noticeCnt;
		this.noticeTitle = noticeTitle;
		this.noticeTime = noticeTime;
	}

	public Notice(int noticeId, int employeeId, String noticeCnt,
			String noticeTitle, Date noticeTime) { //?? 두개의 케이스 어떤원리?
		super();
		this.noticeId = noticeId;
		this.noticeCnt = noticeCnt;
		this.noticeTitle = noticeTitle;
		this.noticeTime = noticeTime;
	}
	

	public int getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(int noticeId) {
		this.noticeId = noticeId;
	}

	public String getNoticeCnt() {
		return noticeCnt;
	}

	public void setNoticeCnt(String noticeCnt) {
		this.noticeCnt = noticeCnt;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public Date getNoticeTime() {
		return noticeTime;
	}

	public void setNoticeTime(Date noticeTime) {
		this.noticeTime = noticeTime;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	
	
	
	
}
