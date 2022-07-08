package com.my.dto;

import java.time.LocalDateTime;
import java.util.Date;

public class Employee { 
	private int employeeId;
	private String employeePassword;
	private String employeeNameKr;
	private String employeeNameEng;
	private Date employeeHiredate;
	private String employeePhonenumber;
	private String employeeEmail;
	private String employeeAddress;
	private char employeeGender;
	private Date employeeBirthday;
	private int employeeResign;
	
	private Department department;
	//private List<Department> department;
	
	private HolidayInfo holidayInfo;
	
	public Employee() {
		
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeePassword() {
		return employeePassword;
	}

	public void setEmployeePassword(String employeePassword) {
		this.employeePassword = employeePassword;
	}

	public String getEmployeeNameKr() {
		return employeeNameKr;
	}

	public void setEmployeeNameKr(String employeeNameKr) {
		this.employeeNameKr = employeeNameKr;
	}

	public String getEmployeeNameEng() {
		return employeeNameEng;
	}

	public void setEmployeeNameEng(String employeeNameEng) {
		this.employeeNameEng = employeeNameEng;
	}

	public Date getEmployeeHiredate() {
		return employeeHiredate;
	}

	public void setEmployeeHiredate(Date employeeHiredate) {
		this.employeeHiredate = employeeHiredate;
	}

	public String getEmployeePhonenumber() {
		return employeePhonenumber;
	}

	public void setEmployeePhonenumber(String employeePhonenumber) {
		this.employeePhonenumber = employeePhonenumber;
	}

	public String getEmployeeEmail() {
		return employeeEmail;
	}

	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}

	public String getEmployeeAddress() {
		return employeeAddress;
	}

	public void setEmployeeAddress(String employeeAddress) {
		this.employeeAddress = employeeAddress;
	}

	public char getEmployeeGender() {
		return employeeGender;
	}

	public void setEmployeeGender(char employeeGender) {
		this.employeeGender = employeeGender;
	}

	public Date getEmployeeBirthday() {
		return employeeBirthday;
	}

	public void setEmployeeBirthday(Date employeeBirthday) {
		this.employeeBirthday = employeeBirthday;
	}

	public int getEmployeeResign() {
		return employeeResign;
	}

	public void setEmployeeResign(int employeeResign) {
		this.employeeResign = employeeResign;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public HolidayInfo getHolidayInfo() {
		return holidayInfo;
	}

	public void setHolidayInfo(HolidayInfo holidayInfo) {
		this.holidayInfo = holidayInfo;
	}
	
	
	
	
}