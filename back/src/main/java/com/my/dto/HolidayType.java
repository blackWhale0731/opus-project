package com.my.dto;

public class HolidayType {
	private int holidayType;
	private String holidayTypeName;
	
	public HolidayType() {
		
	}
	///////////////////////////////////
	public HolidayType(int holidayType, String holidayTypeName) {
			super();
			this.holidayType = holidayType;
			this.holidayTypeName = holidayTypeName;
	}
	////////////////////////////////////

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
	
	
}