package com.my.dto;

import java.util.Date;

public class HolidayRequests {
	private int holidayNumber;
	public int getHolidayNumber() {
		return holidayNumber;
	}
	public void setHolidayNumber(int holidayNumber) {
		this.holidayNumber = holidayNumber;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public int getHolidayType() {
		return holidayType;
	}
	public void setHolidayType(int holidayType) {
		this.holidayType = holidayType;
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
	public String getHolidayFileTitle() {
		return holidayFileTitle;
	}
	public void setHolidayFileTitle(String holidayFileTitle) {
		this.holidayFileTitle = holidayFileTitle;
	}
	public String getFileRoute() {
		return fileRoute;
	}
	public void setFileRoute(String fileRoute) {
		this.fileRoute = fileRoute;
	}
	public HolidayRequests(int holidayNumber, int employeeId, int holidayType, String holidayDesc, Date holidayStart,
			Date holidayEnd, String holidayFileTitle, String fileRoute) {
		super();
		this.holidayNumber = holidayNumber;
		this.employeeId = employeeId;
		this.holidayType = holidayType;
		this.holidayDesc = holidayDesc;
		this.holidayStart = holidayStart;
		this.holidayEnd = holidayEnd;
		this.holidayFileTitle = holidayFileTitle;
		this.fileRoute = fileRoute;
	}
	@Override
	public String toString() {
		return "HolidayRequests [holidayNumber=" + holidayNumber + ", employeeId=" + employeeId + ", holidayType="
				+ holidayType + ", holidayDesc=" + holidayDesc + ", holidayStart=" + holidayStart + ", holidayEnd="
				+ holidayEnd + ", holidayFileTitle=" + holidayFileTitle + ", fileRoute=" + fileRoute + "]";
	}
	private int employeeId;
	private int holidayType;
	private String holidayDesc;
	private Date holidayStart;
	private Date holidayEnd;
	private String holidayFileTitle;
	private String fileRoute;

}
