package com.my.dto;

public class ApprovalLevel {
	private Employee employee; //?
//	private int employee_id;
	//private String employee_name_kr;//////

	public ApprovalLevel(Employee employee) {
		this.employee = employee;
	} 
	
	///////////////////////////
//	public ApprovalLevel(int employee_id) {
//		super();
//		this.employee_id = employee_id;
//	} 
//	public ApprovalLevel(String employee_name_kr) {
//		super();
//		this.employee_name_kr = employee_name_kr;
//	} 
//	public ApprovalLevel(int employee_id, String employee_name_kr) {
//		super();
//		this.employee_id = employee_id;
//		this.employee_name_kr = employee_name_kr;
//	} 
	///////////////////////////
	
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
		
}