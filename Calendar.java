package com.my.dto;


public class Calendar {
	private int calNo;
	private int employeeId;
	private int calType;
	private int calRevealed;
	private String calCnt;
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
	public Calendar(int calNo, int employeeId, int calType, int calRevealed, String calCnt) {
		super();
		this.calNo = calNo;
		this.employeeId = employeeId;
		this.calType = calType;
		this.calRevealed = calRevealed;
		this.calCnt = calCnt;
	}
	@Override
	public String toString() {
		return "Calendar [calNo=" + calNo + ", employeeId=" + employeeId + ", calType=" + calType + ", calRevealed="
				+ calRevealed + ", calCnt=" + calCnt + "]";
	}
}
