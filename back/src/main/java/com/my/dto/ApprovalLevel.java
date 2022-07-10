package com.my.dto;

public class ApprovalLevel {
	private Employee employee; //?
	private int employee_id;

	public ApprovalLevel(Employee employee) {
		employee = employee;
	} 
	
	///////////////////////////
	public ApprovalLevel(int employee_id) {
		super();
		this.employee_id = employee_id;
	} 
	///////////////////////////
	
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
		
}