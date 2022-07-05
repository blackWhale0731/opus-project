package com.my.dto;

public class CalTypes {
	private int calType;
	private int calTypeName;
	public int getCalType() {
		return calType;
	}
	public void setCalType(int calType) {
		this.calType = calType;
	}
	public int getCalTypeName() {
		return calTypeName;
	}
	public void setCalTypeName(int calTypeName) {
		this.calTypeName = calTypeName;
	}
	public CalTypes(int calType, int calTypeName) {
		super();
		this.calType = calType;
		this.calTypeName = calTypeName;
	}
	@Override
	public String toString() {
		return "CalTypes [calType=" + calType + ", calTypeName=" + calTypeName + "]";
	}
}
