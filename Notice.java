package com.my.dto;

import java.time.LocalDateTime;

public class Notice {
	private int noticeId;
	private int employeeId;
	private String noticeCnt;
	private String noticeTitle;
	private String noticeFileRoute;
	private String noticeFileName;
	private LocalDateTime noticeTime;
	public int getNoticeId() {
		return noticeId;
	}
	public void setNoticeId(int noticeId) {
		this.noticeId = noticeId;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
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
	public String getNoticeFileRoute() {
		return noticeFileRoute;
	}
	public void setNoticeFileRoute(String noticeFileRoute) {
		this.noticeFileRoute = noticeFileRoute;
	}
	public String getNoticeFileName() {
		return noticeFileName;
	}
	public void setNoticeFileName(String noticeFileName) {
		this.noticeFileName = noticeFileName;
	}
	public LocalDateTime getNoticeTime() {
		return noticeTime;
	}
	public void setNoticeTime(LocalDateTime noticeTime) {
		this.noticeTime = noticeTime;
	}
	public Notice(int noticeId, int employeeId, String noticeCnt, String noticeTitle, String noticeFileRoute,
			String noticeFileName, LocalDateTime noticeTime) {
		super();
		this.noticeId = noticeId;
		this.employeeId = employeeId;
		this.noticeCnt = noticeCnt;
		this.noticeTitle = noticeTitle;
		this.noticeFileRoute = noticeFileRoute;
		this.noticeFileName = noticeFileName;
		this.noticeTime = noticeTime;
	}
	@Override
	public String toString() {
		return "Notice [noticeId=" + noticeId + ", employeeId=" + employeeId + ", noticeCnt=" + noticeCnt
				+ ", noticeTitle=" + noticeTitle + ", noticeFileRoute=" + noticeFileRoute + ", noticeFileName="
				+ noticeFileName + ", noticeTime=" + noticeTime + "]";
	}
}
