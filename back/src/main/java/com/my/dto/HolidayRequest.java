package com.my.dto;

import java.util.Date;

public class HolidayRequest {
	private int holidayNumber;
	private int holidayApprovalStatus;
	private ApprovalLevel holidayApprovalFirst; 
	private ApprovalLevel holidayApprovalSecond;
	private ApprovalLevel holidayApprovalThird;
	private String holidayDesc;
	private Date holidayStart;
	private Date holidayEnd;
	private HolidayType holidayType;
	private Employee employee;

	

	public HolidayRequest(int holidayNumber, int holidayApprovalStatus, ApprovalLevel holidayApprovalFirst,
			ApprovalLevel holidayApprovalSecond, ApprovalLevel holidayApprovalThird, String holidayDesc,
			Date holidayStart, Date holidayEnd, HolidayType holidayType) {
		super();
		this.holidayNumber = holidayNumber;
		this.holidayApprovalStatus = holidayApprovalStatus;
		this.holidayApprovalFirst = holidayApprovalFirst;
		this.holidayApprovalSecond = holidayApprovalSecond;
		this.holidayApprovalThird = holidayApprovalThird;
		this.holidayDesc = holidayDesc;
		this.holidayStart = holidayStart;
		this.holidayEnd = holidayEnd;
		this.holidayType = holidayType;
	}


	public HolidayRequest() {
		
	}


	public int getHolidayNumber() {
		return holidayNumber;
	}


	public void setHolidayNumber(int holidayNumber) {
		this.holidayNumber = holidayNumber;
	}


	public int getHolidayApprovalStatus() {
		return holidayApprovalStatus;
	}


	public void setHolidayApprovalStatus(int holidayApprovalStatus) {
		this.holidayApprovalStatus = holidayApprovalStatus;
	}


	public ApprovalLevel getHolidayApprovalFirst() {
		return holidayApprovalFirst;
	}


	public void setHolidayApprovalFirst(ApprovalLevel holidayApprovalFirst) {
		this.holidayApprovalFirst = holidayApprovalFirst;
	}


	public ApprovalLevel getHolidayApprovalSecond() {
		return holidayApprovalSecond;
	}


	public void setHolidayApprovalSecond(ApprovalLevel holidayApprovalSecond) {
		this.holidayApprovalSecond = holidayApprovalSecond;
	}


	public ApprovalLevel getHolidayApprovalThird() {
		return holidayApprovalThird;
	}


	public void setHolidayApprovalThird(ApprovalLevel holidayApprovalThird) {
		this.holidayApprovalThird = holidayApprovalThird;
	}


	public String getHolidayDesc() {
		return holidayDesc;
	}


	public void setHolidayDesc(String holidayDesc) {
		this.holidayDesc = holidayDesc;
	}


	public Date getHolidayStart() {
		return holidayStart;
	}


	public void setHolidayStart(Date holidayStart) {
		this.holidayStart = holidayStart;
	}


	public Date getHolidayEnd() {
		return holidayEnd;
	}


	public void setHolidayEnd(Date holidayEnd) {
		this.holidayEnd = holidayEnd;
	}


	public HolidayType getHolidayType() {
		return holidayType;
	}


	public void setHolidayType(HolidayType holidayType) {
		this.holidayType = holidayType;
	}


	public Employee getEmployee() {
		return employee;
	}


	public void setEmployee(Employee employee) {
		this.employee = employee;
	}




	
	
	
}