package com.my.dto;

public class HolidayTypes {
	private int holidayType;
	private String holidayTypeName;
	public int getHolidayType() {
		return holidayType;
	}
	public void setHolidayType(int holidayType) {
		this.holidayType = holidayType;
	}
	public String getHolidayTypeName() {
		return holidayTypeName;
	}
	public void setHolidayTypeName(String holidayTypeName) {
		this.holidayTypeName = holidayTypeName;
	}
	public HolidayTypes(int holidayType, String holidayTypeName) {
		super();
		this.holidayType = holidayType;
		this.holidayTypeName = holidayTypeName;
	}
	@Override
	public String toString() {
		return "HolidayTypes [holidayType=" + holidayType + ", holidayTypeName=" + holidayTypeName + "]";
	}
}