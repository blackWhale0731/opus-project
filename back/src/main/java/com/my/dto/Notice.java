package com.my.dto;

import java.util.Date;

public class Notice {
	private int noticeId;
	private String noticeCnt;
	private String noticeTitle;
	private Date noticeTime;	//java.util.date 권장
	private Employee employee;
	
	public Notice() {}
	
	

	public Notice(int noticeId, String noticeCnt, String noticeTitle, Date noticeTime, Employee employee) {
		super();
		this.noticeId = noticeId;
		this.noticeCnt = noticeCnt;
		this.noticeTitle = noticeTitle;
		this.noticeTime = noticeTime;
		this.employee = employee;
	}

	

	public Notice(int noticeId, String noticeCnt, String noticeTitle) {
		super();
		this.noticeId = noticeId;
		this.noticeCnt = noticeCnt;
		this.noticeTitle = noticeTitle;
	
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

	@Override
	public String toString() {
		return "Notice [noticeId=" + noticeId + ", noticeCnt=" + noticeCnt + ", noticeTitle=" + noticeTitle
				+ ", noticeTime=" + noticeTime + ", employee=" + employee + "]";
	}
	
	
	
	
}
