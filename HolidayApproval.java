package com.my.dto;

public class HolidayApproval {

	private int holidayNumber;
	private int holidayApprovalStatus;
	private int holidayApprovalFirst;
	private int holidayApprovalSecond;
	private int holidayApprovalThird;
	
	public HolidayApproval(int holidayNumber, int holidayApprovalStatus, int holidayApprovalFirst,
			int holidayApprovalSecond, int holidayApprovalThird) {
		super();
		this.holidayNumber = holidayNumber;
		this.holidayApprovalStatus = holidayApprovalStatus;
		this.holidayApprovalFirst = holidayApprovalFirst;
		this.holidayApprovalSecond = holidayApprovalSecond;
		this.holidayApprovalThird = holidayApprovalThird;
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

	public int getHolidayApprovalFirst() {
		return holidayApprovalFirst;
	}

	public void setHolidayApprovalFirst(int holidayApprovalFirst) {
		this.holidayApprovalFirst = holidayApprovalFirst;
	}

	public int getHolidayApprovalSecond() {
		return holidayApprovalSecond;
	}

	public void setHolidayApprovalSecond(int holidayApprovalSecond) {
		this.holidayApprovalSecond = holidayApprovalSecond;
	}

	public int getHolidayApprovalThird() {
		return holidayApprovalThird;
	}

	public void setHolidayApprovalThird(int holidayApprovalThird) {
		this.holidayApprovalThird = holidayApprovalThird;
	}

	@Override
	public String toString() {
		return "HolidayApproval [holidayNumber=" + holidayNumber + ", holidayApprovalStatus=" + holidayApprovalStatus
				+ ", holidayApprovalFirst=" + holidayApprovalFirst + ", holidayApprovalSecond=" + holidayApprovalSecond
				+ ", holidayApprovalThird=" + holidayApprovalThird + "]";
	}
	
	
	
	

}