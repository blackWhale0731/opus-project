package com.my.dto;

public class ApprovalLevel {
	private int employeeId;

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public ApprovalLevel(int employeeId) {
		super();
		this.employeeId = employeeId;
	}

	@Override
	public String toString() {
		return "ApprovalLevel [employeeId=" + employeeId + "]";
	}

}
