package com.my.dto;

import java.util.Date;

public class Calendar {
	private int calNo;
	
//	private int employeeId;
	
	private int calRevealed;
	private String calCnt;
	private Date calStart;
	private Date calEnd;
	private CalType CalType;
	private Employee employee;
	
	public Calendar() {
		
	}

	public int getCalNo() {
		return calNo;
	}

	public void setCalNo(int calNo) {
		this.calNo = calNo;
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

	public Date getCalStart() {
		return calStart;
	}

	public void setCalStart(Date calStart) {
		this.calStart = calStart;
	}

	public Date getCalEnd() {
		return calEnd;
	}

	public void setCalEnd(Date calEnd) {
		this.calEnd = calEnd;
	}

	public CalType getCalType() {
		return CalType;
	}

	public void setCalType(CalType calType) {
		CalType = calType;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	
	



	
	
	
}


