package com.my.dto;

public class HolidayInfo {
	private int employeeId;
	private int holidayTotal;
	private int holidayUsing;
	////////////////////////
	public HolidayInfo() {
		
	}
	public HolidayInfo(int employeeId, int holidayTotal, int holidayUsing) {
		super();
		this.employeeId = employeeId;
		this.holidayTotal = holidayTotal;
		this.holidayUsing = holidayUsing;
	}
//	public void print() {
//		System.out.println("생성 = "+holidayTotal+"/ 잔여"+(holidayTotal-holidayUsing));
//	}
//	public int holiday() {
//		return holidayTotal + holidayUsing;
//	}
	public String toString() {
		return "생성 = "+holidayTotal+"/ 잔여"+(holidayTotal-holidayUsing);
	}
	///////////////////////
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public int getHolidayTotal() {
		return holidayTotal;
	}
	public void setHolidayTotal(int holidayTotal) {
		this.holidayTotal = holidayTotal;
	}
	public int getHolidayUsing() {
		return holidayUsing;
	}
	public void setHolidayUsing(int holidayUsing) {
		this.holidayUsing = holidayUsing;
	}

	
	
}