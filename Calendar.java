package com.my.dto;

import java.time.LocalDateTime;

public class Calendar {
	private int calNo;
	private int employeeId;
	private int calType;
	private int calRevealed;
	private String calCnt;
	private LocalDateTime calStart;    // 날짜를 받을 변수를 몰라서 String 사용
	private LocalDateTime calEnd;
	public int getCalNo() {
		return calNo;
	}
	public void setCalNo(int calNo) {
		this.calNo = calNo;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public int getCalType() {
		return calType;
	}
	public void setCalType(int calType) {
		this.calType = calType;
	}
	public int getCalRevealed() {
		return calRevealed;
	}
	public void setCalRevealed(int calRevealed) {
		this.calRevealed = calRevealed;
	}
	public String getCalCnt() {
		return calCnt;
	}
	public void setCalCnt(String calCnt) {
		this.calCnt = calCnt;
	}
	public LocalDateTime getCalStart() {
		return calStart;
	}
	public void setCalStart(LocalDateTime calStart) {
		this.calStart = calStart;
	}
	public LocalDateTime getCalEnd() {
		return calEnd;
	}
	public void setCalEnd(LocalDateTime calEnd) {
		this.calEnd = calEnd;
	}
	public Calendar(int calNo, int employeeId, int calType, int calRevealed, String calCnt, LocalDateTime calStart,
			LocalDateTime calEnd) {
		super();
		this.calNo = calNo;
		this.employeeId = employeeId;
		this.calType = calType;
		this.calRevealed = calRevealed;
		this.calCnt = calCnt;
		this.calStart = calStart;
		this.calEnd = calEnd;
	}
	@Override
	public String toString() {
		return "Calendar [calNo=" + calNo + ", employeeId=" + employeeId + ", calType=" + calType + ", calRevealed="
				+ calRevealed + ", calCnt=" + calCnt + ", calStart=" + calStart + ", calEnd=" + calEnd + "]";
	}
}
