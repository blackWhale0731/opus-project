package com.my.dto;

import java.time.LocalDateTime;
import java.util.Date;

public class Employees {
	private int employeeId;
	private int departmentId;
	private String employeePassword;
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
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
	public Employees(int employeeId, int departmentId, String employeePassword, String employeeNameKr,
			String employeeNameEng, Date employeeHiredate, String employeePhonenumber, String employeeEmail,
			String employeeAddress, char employeeGender, Date employeeBirthday, int employeeResign) {
		super();
		this.employeeId = employeeId;
		this.departmentId = departmentId;
		this.employeePassword = employeePassword;
		this.employeeNameKr = employeeNameKr;
		this.employeeNameEng = employeeNameEng;
		this.employeeHiredate = employeeHiredate;
		this.employeePhonenumber = employeePhonenumber;
		this.employeeEmail = employeeEmail;
		this.employeeAddress = employeeAddress;
		this.employeeGender = employeeGender;
		this.employeeBirthday = employeeBirthday;
		this.employeeResign = employeeResign;
	}
	@Override
	public String toString() {
		return "Employees [employeeId=" + employeeId + ", departmentId=" + departmentId + ", employeePassword="
				+ employeePassword + ", employeeNameKr=" + employeeNameKr + ", employeeNameEng=" + employeeNameEng
				+ ", employeeHiredate=" + employeeHiredate + ", employeePhonenumber=" + employeePhonenumber
				+ ", employeeEmail=" + employeeEmail + ", employeeAddress=" + employeeAddress + ", employeeGender="
				+ employeeGender + ", employeeBirthday=" + employeeBirthday + ", employeeResign=" + employeeResign
				+ "]";
	}
	private String employeeNameKr;
	private String employeeNameEng;
	private Date employeeHiredate;
	private String employeePhonenumber;
	private String employeeEmail;
	private String employeeAddress;
	private char employeeGender;
	private Date employeeBirthday;
	private int employeeResign;
}
